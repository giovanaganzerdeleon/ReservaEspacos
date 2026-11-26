package com.projeto.controller;

import com.projeto.config.SessionIdentity;
import com.projeto.model.ItemCampus;
import com.projeto.service.ItemReservaService;
import com.projeto.service.ItemReservaService.DadosItem;
import com.projeto.service.ItemReservaService.ItemDisponibilidade;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemReservaService service;
    private final SessionIdentity sessionIdentity;

    public ItemController(ItemReservaService service, SessionIdentity sessionIdentity) {
        this.service = service;
        this.sessionIdentity = sessionIdentity;
    }

    @GetMapping
    public List<ItemResponse> listar() {
        return service.listarItens().stream().map(ItemResponse::from).toList();
    }

    @GetMapping("/disponiveis")
    public List<ItemDisponibilidadeResponse> listarDisponiveis(
            @RequestParam LocalDate data,
            @RequestParam LocalTime horaInicio,
            @RequestParam LocalTime horaFim,
            @RequestParam(required = false) Long reservaId,
            HttpServletRequest request
    ) {
        Long usuarioId = sessionIdentity.getUserId(request);
        return service.listarDisponibilidade(
                        usuarioId,
                        data,
                        horaInicio,
                        horaFim,
                        reservaId
                ).stream()
                .map(ItemDisponibilidadeResponse::from)
                .toList();
    }

    @PostMapping
    public ResponseEntity<ItemResponse> cadastrar(@RequestBody DadosItem dados) {
        ItemCampus item = service.cadastrarItem(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(ItemResponse.from(item));
    }

    public record ItemResponse(Long id, String nome, String categoria, int quantidadeEstoque) {

        private static ItemResponse from(ItemCampus item) {
            return new ItemResponse(
                    item.getId(),
                    item.getNome(),
                    item.getCategoria(),
                    item.getQuantidadeEstoque()
            );
        }
    }

    public record ItemDisponibilidadeResponse(
            Long id,
            String nome,
            String categoria,
            int quantidadeEstoque,
            int quantidadeDisponivel
    ) {

        private static ItemDisponibilidadeResponse from(ItemDisponibilidade disponibilidade) {
            ItemCampus item = disponibilidade.item();
            return new ItemDisponibilidadeResponse(
                    item.getId(),
                    item.getNome(),
                    item.getCategoria(),
                    item.getQuantidadeEstoque(),
                    disponibilidade.quantidadeDisponivel()
            );
        }
    }
}
