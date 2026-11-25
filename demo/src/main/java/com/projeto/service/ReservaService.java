
package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.model.Equipamentos;
import com.projeto.model.Reserva;
import com.projeto.model.ReservaEquipamento;
import com.projeto.model.Salas;
import com.projeto.model.Usuario;
import com.projeto.repository.EquipamentosRepository;
import com.projeto.repository.ReservaRepository;
import com.projeto.repository.SalasRepository;
import com.projeto.repository.UsuarioRepository;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final SalasRepository salasRepository;
    private final EquipamentosRepository equipamentosRepository;

    public ReservaService(
            ReservaRepository reservaRepository,
            UsuarioRepository usuarioRepository,
            SalasRepository salasRepository,
            EquipamentosRepository equipamentosRepository) {

        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.salasRepository = salasRepository;
        this.equipamentosRepository = equipamentosRepository;
    }

    // Listar todas as reservas
    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    // Buscar reserva por ID
    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    // Buscar reservas de um usuário
    public List<Reserva> buscarPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuario_Id(usuarioId);
    }

    // Buscar reservas por data
    public List<Reserva> buscarPorData(java.time.LocalDate data) {
        return reservaRepository.findByData(data);
    }

    // Cadastrar reserva
    @Transactional
    public Reserva cadastrar(Reserva reserva) {

        validarHorario(reserva);

        reserva.setId(null);

        // Verifica se o usuário existe
        Usuario usuario = usuarioRepository.findById(
                reserva.getUsuario().getId()
        ).orElseThrow(() ->
                new IllegalArgumentException("Usuário não encontrado.")
        );

        reserva.setUsuario(usuario);

        // Verifica se a sala existe, caso tenha sido informada
        if (reserva.getSala() != null) {

            Salas sala = salasRepository.findById(
                    reserva.getSala().getId()
            ).orElseThrow(() ->
                    new IllegalArgumentException("Sala não encontrada.")
            );

            reserva.setSala(sala);
        }

        // Vincula os equipamentos existentes à reserva
        if (reserva.getEquipamentos() != null) {

            List<ReservaEquipamento> itens =
                    new java.util.ArrayList<>(reserva.getEquipamentos());

            reserva.getEquipamentos().clear();

            for (ReservaEquipamento item : itens) {

                if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                    throw new IllegalArgumentException(
                            "A quantidade do equipamento deve ser maior que zero."
                    );
                }

                if (item.getEquipamento() == null ||
                        item.getEquipamento().getId() == null) {
                    throw new IllegalArgumentException(
                            "Informe um equipamento válido."
                    );
                }

                Equipamentos equipamento = equipamentosRepository.findById(
                        item.getEquipamento().getId()
                ).orElseThrow(() ->
                        new IllegalArgumentException("Equipamento não encontrado.")
                );

                item.setEquipamento(equipamento);
                reserva.adicionarEquipamento(item);
            }
        }

        return reservaRepository.save(reserva);
    }

    // Atualizar reserva
    @Transactional
    public Optional<Reserva> atualizar(Long id, Reserva dados) {

        return reservaRepository.findById(id).map(reserva -> {

            validarHorario(dados);

            Usuario usuario = usuarioRepository.findById(
                    dados.getUsuario().getId()
            ).orElseThrow(() ->
                    new IllegalArgumentException("Usuário não encontrado.")
            );

            reserva.setData(dados.getData());
            reserva.setHorarioInicio(dados.getHorarioInicio());
            reserva.setHorarioFim(dados.getHorarioFim());
            reserva.setTipo(dados.getTipo());
            reserva.setUsuario(usuario);
            reserva.setStatus(dados.getStatus());

            if (dados.getSala() != null) {

                Salas sala = salasRepository.findById(
                        dados.getSala().getId()
                ).orElseThrow(() ->
                        new IllegalArgumentException("Sala não encontrada.")
                );

                reserva.setSala(sala);

            } else {
                reserva.setSala(null);
            }

            // Substitui os equipamentos associados
            reserva.getEquipamentos().clear();

            if (dados.getEquipamentos() != null) {

                for (ReservaEquipamento item : dados.getEquipamentos()) {

                    if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                        throw new IllegalArgumentException(
                                "A quantidade do equipamento deve ser maior que zero."
                        );
                    }

                    Equipamentos equipamento = equipamentosRepository.findById(
                            item.getEquipamento().getId()
                    ).orElseThrow(() ->
                            new IllegalArgumentException("Equipamento não encontrado.")
                    );

                    ReservaEquipamento novoItem = new ReservaEquipamento();
                    novoItem.setQuantidade(item.getQuantidade());
                    novoItem.setEquipamento(equipamento);

                    reserva.adicionarEquipamento(novoItem);
                }
            }

            return reservaRepository.save(reserva);
        });
    }

    // Cancelar reserva
    @Transactional
    public Optional<Reserva> cancelar(Long id) {

        return reservaRepository.findById(id).map(reserva -> {
            reserva.setStatus(
                    com.projeto.model.StatusReserva.CANCELADA
            );

            return reservaRepository.save(reserva);
        });
    }

    // Excluir reserva
    public boolean excluir(Long id) {

        if (!reservaRepository.existsById(id)) {
            return false;
        }

        reservaRepository.deleteById(id);
        return true;
    }

    // Validar horário
    private void validarHorario(Reserva reserva) {

        if (reserva.getData() == null ||
                reserva.getHorarioInicio() == null ||
                reserva.getHorarioFim() == null) {
            throw new IllegalArgumentException(
                    "Data e horários são obrigatórios."
            );
        }

        if (!reserva.getHorarioInicio().isBefore(reserva.getHorarioFim())) {
            throw new IllegalArgumentException(
                    "O horário inicial deve ser anterior ao horário final."
            );
        }

        if (reserva.getUsuario() == null ||
                reserva.getUsuario().getId() == null) {
            throw new IllegalArgumentException(
                    "Informe um usuário para a reserva."
            );
        }
    }
}
