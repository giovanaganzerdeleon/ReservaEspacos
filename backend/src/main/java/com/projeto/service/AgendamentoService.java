package com.projeto.service;

import com.projeto.model.Agendamento;
import com.projeto.model.Salas;
import com.projeto.model.Usuario;
import com.projeto.repository.AgendamentoRepository;
import com.projeto.repository.SalasRepository;
import com.projeto.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final SalasRepository salasRepository;
    private final UsuarioRepository usuarioRepository;

    public AgendamentoService(
            AgendamentoRepository agendamentoRepository,
            SalasRepository salasRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.agendamentoRepository = agendamentoRepository;
        this.salasRepository = salasRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<Agendamento> listarDoUsuario(Long usuarioId) {
        return agendamentoRepository.findByUsuario_IdOrderByDataAscHoraInicioAsc(usuarioId);
    }

    @Transactional
    public Agendamento criar(Long usuarioId, DadosAgendamento dados) {
        validar(dados);
        Salas sala = buscarSalaParaAtualizacao(dados.salaId());
        validarSalaDisponivel(sala);
        verificarConflito(sala, dados, null);
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sessão inválida."));
                verificarConflitoUsuario(usuarioId, dados, null);
                

        return agendamentoRepository.save(new Agendamento(
                sala,
                usuario,
                dados.data(),
                dados.horaInicio(),
                dados.horaFim()
        ));
    }

    @Transactional
    public Agendamento atualizar(Long usuarioId, Long agendamentoId, DadosAgendamento dados) {
        validar(dados);
        Agendamento agendamento = agendamentoRepository.findByIdForUpdate(agendamentoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada."));
        verificarProprietario(agendamento, usuarioId);
        if (agendamento.isCancelada()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Uma reserva cancelada não pode ser editada.");
        }

        Salas sala = buscarSalaParaAtualizacao(dados.salaId());
        boolean mantendoSalaAtual = sala.getId().equals(agendamento.getSala().getId());
        if (!mantendoSalaAtual) {
            validarSalaDisponivel(sala);
        }
        verificarConflito(sala, dados, agendamentoId);
        verificarConflitoUsuario(usuarioId, dados, agendamentoId);

        agendamento.setSala(sala);
        agendamento.setData(dados.data());
        agendamento.setHoraInicio(dados.horaInicio());
        agendamento.setHoraFim(dados.horaFim());
        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void cancelar(Long usuarioId, Long agendamentoId) {
        Agendamento agendamento = agendamentoRepository.findByIdForUpdate(agendamentoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada."));
        verificarProprietario(agendamento, usuarioId);
        agendamento.setCancelada(true);
        agendamentoRepository.save(agendamento);
    }

    private void validar(DadosAgendamento dados) {
        if (dados == null || dados.salaId() == null || dados.data() == null
                || dados.horaInicio() == null || dados.horaFim() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preencha sala, data e horários.");
        }
        if (dados.data().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data não pode estar no passado.");
        }
        if (!dados.horaInicio().isBefore(dados.horaFim())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O horário final deve ser depois do inicial.");
        }
    }

    private Salas buscarSalaParaAtualizacao(Long salaId) {
        return salasRepository.findByIdForUpdate(salaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada."));
    }

    private void validarSalaDisponivel(Salas sala) {
        if (!sala.isDisponibilidade()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A sala está marcada como indisponível.");
        }
    }

    private void verificarConflito(Salas sala, DadosAgendamento dados, Long ignorarId) {
        if (agendamentoRepository.existeConflito(
                sala.getId(),
                dados.data(),
                dados.horaInicio(),
                dados.horaFim(),
                ignorarId
        )) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este horário já está reservado para a sala.");
        }
    }
    private void verificarConflitoUsuario(Long usuarioId, DadosAgendamento dados, Long ignorarId) {
    if (agendamentoRepository.existeConflitoDoUsuario(
            usuarioId,
            dados.data(),
            dados.horaInicio(),
            dados.horaFim(),
            ignorarId
    )) {
        throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Você já possui uma reserva neste horário."
        );
    }
}

    private void verificarProprietario(Agendamento agendamento, Long usuarioId) {
        if (!agendamento.getUsuario().getId().equals(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você só pode alterar suas próprias reservas.");
        }
    }

    public record DadosAgendamento(Long salaId, LocalDate data, LocalTime horaInicio, LocalTime horaFim) {
    }
}
