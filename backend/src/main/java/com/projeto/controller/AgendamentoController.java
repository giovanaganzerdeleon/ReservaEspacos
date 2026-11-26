package com.projeto.controller;

import com.projeto.config.SessionIdentity;
import com.projeto.model.Agendamento;
import com.projeto.service.AgendamentoService;
import com.projeto.service.AgendamentoService.DadosAgendamento;
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
@RequestMapping("/reservas")
public class AgendamentoController {

    private final AgendamentoService service;
    private final SessionIdentity sessionIdentity;

    public AgendamentoController(AgendamentoService service, SessionIdentity sessionIdentity) {
        this.service = service;
        this.sessionIdentity = sessionIdentity;
    }

    @GetMapping("/minhas")
    public List<ReservaResponse> listarMinhas(HttpServletRequest request) {
        return service.listarDoUsuario(sessionIdentity.getUserId(request)).stream()
                .map(ReservaResponse::from)
                .toList();
    }

    @PostMapping
    public ResponseEntity<ReservaResponse> criar(
            @RequestBody DadosAgendamento dados,
            HttpServletRequest request
    ) {
        Agendamento reserva = service.criar(sessionIdentity.getUserId(request), dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(ReservaResponse.from(reserva));
    }

    @PutMapping("/{id}")
    public ReservaResponse atualizar(
            @PathVariable Long id,
            @RequestBody DadosAgendamento dados,
            HttpServletRequest request
    ) {
        return ReservaResponse.from(service.atualizar(sessionIdentity.getUserId(request), id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id, HttpServletRequest request) {
        service.cancelar(sessionIdentity.getUserId(request), id);
        return ResponseEntity.noContent().build();
    }

    public record ReservaResponse(
            Long id,
            Long salaId,
            String salaNome,
            LocalDate data,
            LocalTime horaInicio,
            LocalTime horaFim,
            String status
    ) {

        private static ReservaResponse from(Agendamento reserva) {
            return new ReservaResponse(
                    reserva.getId(),
                    reserva.getSala().getId(),
                    reserva.getSala().getNome(),
                    reserva.getData(),
                    reserva.getHoraInicio(),
                    reserva.getHoraFim(),
                    reserva.isCancelada() ? "CANCELADA" : "ATIVA"
            );
        }
    }
}
