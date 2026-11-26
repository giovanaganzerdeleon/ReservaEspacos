package com.projeto.controller;

import com.projeto.config.SessionIdentity;
import com.projeto.model.ReservaItem;
import com.projeto.service.ItemReservaService;
import com.projeto.service.ItemReservaService.DadosReservaItem;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/reservas-itens")
public class ReservaItemController {

    private final ItemReservaService service;
    private final SessionIdentity sessionIdentity;

    public ReservaItemController(ItemReservaService service, SessionIdentity sessionIdentity) {
        this.service = service;
        this.sessionIdentity = sessionIdentity;
    }

    @GetMapping("/minhas")
    public List<ReservaItemResponse> listarMinhas(HttpServletRequest request) {
        Long usuarioId = sessionIdentity.getUserId(request);
        return service.listarReservasDoUsuario(usuarioId).stream().map(ReservaItemResponse::from).toList();
    }

    @PostMapping
    public ResponseEntity<ReservaItemResponse> reservar(
            @RequestBody DadosReservaItem dados,
            HttpServletRequest request
    ) {
        Long usuarioId = sessionIdentity.getUserId(request);
        ReservaItem reserva = service.reservar(usuarioId, dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(ReservaItemResponse.from(reserva));
    }

    @PutMapping("/{id}")
    public ReservaItemResponse atualizar(
            @PathVariable Long id,
            @RequestBody DadosReservaItem dados,
            HttpServletRequest request
    ) {
        Long usuarioId = sessionIdentity.getUserId(request);
        return ReservaItemResponse.from(service.atualizarReserva(usuarioId, id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id, HttpServletRequest request) {
        Long usuarioId = sessionIdentity.getUserId(request);
        service.cancelarReserva(usuarioId, id);
        return ResponseEntity.noContent().build();
    }

    public record ReservaItemResponse(
            Long id,
            Long itemId,
            String itemNome,
            String categoria,
            LocalDate data,
            LocalTime horaInicio,
            LocalTime horaFim,
            int quantidade,
            String status
    ) {

        private static ReservaItemResponse from(ReservaItem reserva) {
            return new ReservaItemResponse(
                    reserva.getId(),
                    reserva.getItem().getId(),
                    reserva.getItem().getNome(),
                    reserva.getItem().getCategoria(),
                    reserva.getData(),
                    reserva.getHoraInicio(),
                    reserva.getHoraFim(),
                    reserva.getQuantidade(),
                    reserva.isCancelada() ? "CANCELADA" : "ATIVA"
            );
        }
    }
}
