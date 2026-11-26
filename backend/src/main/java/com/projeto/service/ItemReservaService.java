package com.projeto.service;

import com.projeto.model.ItemCampus;
import com.projeto.model.ReservaItem;
import com.projeto.model.Usuario;
import com.projeto.repository.ItemCampusRepository;
import com.projeto.repository.ReservaItemRepository;
import com.projeto.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ItemReservaService {

    private final ItemCampusRepository itemRepository;
    private final ReservaItemRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;

    public ItemReservaService(
            ItemCampusRepository itemRepository,
            ReservaItemRepository reservaRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.itemRepository = itemRepository;
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<ItemCampus> listarItens() {
        return itemRepository.findAll();
    }

    @Transactional
    public ItemCampus cadastrarItem(DadosItem dados) {
        validarItem(dados);
        return itemRepository.save(new ItemCampus(
                dados.nome().trim(),
                dados.categoria().trim(),
                dados.quantidadeEstoque()
        ));
    }

    @Transactional(readOnly = true)
    public List<ItemDisponibilidade> listarDisponibilidade(
            Long usuarioId,
            LocalDate data,
            LocalTime horaInicio,
            LocalTime horaFim,
            Long ignorarReservaId
    ) {
        validarPeriodo(data, horaInicio, horaFim);
        validarReservaIgnorada(usuarioId, ignorarReservaId);

        return itemRepository.findAll().stream()
                .map(item -> {
                    long reservada = quantidadeReservada(item.getId(), data, horaInicio, horaFim, ignorarReservaId);
                    int disponivel = (int) Math.max(0L, item.getQuantidadeEstoque() - reservada);
                    return new ItemDisponibilidade(item, disponivel);
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReservaItem> listarReservasDoUsuario(Long usuarioId) {
        return reservaRepository.findByUsuario_IdOrderByDataAscHoraInicioAsc(usuarioId);
    }

    @Transactional
    public ReservaItem reservar(Long usuarioId, DadosReservaItem dados) {
        validarReserva(dados);
        ItemCampus item = buscarItemParaAtualizacao(dados.itemId());
        validarQuantidadeDisponivel(item, dados, null);
        Usuario usuario = buscarUsuario(usuarioId);

        return reservaRepository.save(new ReservaItem(
                item,
                usuario,
                dados.data(),
                dados.horaInicio(),
                dados.horaFim(),
                dados.quantidade()
        ));
    }

    @Transactional
    public ReservaItem atualizarReserva(Long usuarioId, Long reservaId, DadosReservaItem dados) {
        validarReserva(dados);
        ReservaItem reserva = reservaRepository.findByIdForUpdate(reservaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada."));
        verificarProprietario(reserva, usuarioId);
        if (reserva.isCancelada()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Uma reserva cancelada não pode ser editada.");
        }

        ItemCampus item = buscarItemParaAtualizacao(dados.itemId());
        validarQuantidadeDisponivel(item, dados, reservaId);
        reserva.setItem(item);
        reserva.setData(dados.data());
        reserva.setHoraInicio(dados.horaInicio());
        reserva.setHoraFim(dados.horaFim());
        reserva.setQuantidade(dados.quantidade());
        return reservaRepository.save(reserva);
    }

    @Transactional
    public void cancelarReserva(Long usuarioId, Long reservaId) {
        ReservaItem reserva = reservaRepository.findByIdForUpdate(reservaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada."));
        verificarProprietario(reserva, usuarioId);
        reserva.setCancelada(true);
        reservaRepository.save(reserva);
    }

    private void validarItem(DadosItem dados) {
        if (dados == null || dados.nome() == null || dados.categoria() == null
                || dados.nome().isBlank() || dados.categoria().isBlank()
                || dados.nome().trim().length() > 120 || dados.categoria().trim().length() > 80) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe nome e categoria válidos para o item.");
        }
        if (dados.quantidadeEstoque() == null || dados.quantidadeEstoque() < 1
                || dados.quantidadeEstoque() > 10000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O estoque deve ter entre 1 e 10.000 unidades."
            );
        }
    }

    private void validarReserva(DadosReservaItem dados) {
        if (dados == null || dados.itemId() == null || dados.data() == null
                || dados.horaInicio() == null || dados.horaFim() == null || dados.quantidade() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Preencha item, data, horários e quantidade."
            );
        }
        validarPeriodo(dados.data(), dados.horaInicio(), dados.horaFim());
        if (dados.quantidade() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A quantidade deve ser pelo menos 1.");
        }
    }

    private void validarPeriodo(LocalDate data, LocalTime horaInicio, LocalTime horaFim) {
        if (data == null || horaInicio == null || horaFim == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe data, horário inicial e final.");
        }
        if (data.isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data não pode estar no passado.");
        }
        if (!horaInicio.isBefore(horaFim)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O horário final deve ser depois do inicial.");
        }
    }

    private void validarQuantidadeDisponivel(ItemCampus item, DadosReservaItem dados, Long ignorarReservaId) {
        long reservada = quantidadeReservada(
                item.getId(),
                dados.data(),
                dados.horaInicio(),
                dados.horaFim(),
                ignorarReservaId
        );
        int disponivel = (int) Math.max(0L, item.getQuantidadeEstoque() - reservada);
        if (dados.quantidade() > disponivel) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Há apenas " + disponivel + " unidade(s) disponível(is) nesse horário."
            );
        }
    }

    private long quantidadeReservada(
            Long itemId,
            LocalDate data,
            LocalTime horaInicio,
            LocalTime horaFim,
            Long ignorarReservaId
    ) {
        Long quantidade = reservaRepository.somarQuantidadeReservada(
                itemId,
                data,
                horaInicio,
                horaFim,
                ignorarReservaId
        );
        return quantidade == null ? 0L : quantidade;
    }

    private void validarReservaIgnorada(Long usuarioId, Long reservaId) {
        if (reservaId == null) {
            return;
        }
        ReservaItem reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada."));
        verificarProprietario(reserva, usuarioId);
        if (reserva.isCancelada()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Uma reserva cancelada não pode ser editada.");
        }
    }

    private ItemCampus buscarItemParaAtualizacao(Long itemId) {
        return itemRepository.findByIdForUpdate(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado."));
    }

    private Usuario buscarUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sessão inválida."));
    }

    private void verificarProprietario(ReservaItem reserva, Long usuarioId) {
        if (!reserva.getUsuario().getId().equals(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você só pode alterar suas próprias reservas.");
        }
    }

    public record DadosItem(String nome, String categoria, Integer quantidadeEstoque) {
    }

    public record DadosReservaItem(
            Long itemId,
            LocalDate data,
            LocalTime horaInicio,
            LocalTime horaFim,
            Integer quantidade
    ) {
    }

    public record ItemDisponibilidade(ItemCampus item, int quantidadeDisponivel) {
    }
}
