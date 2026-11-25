
package com.projeto.controller;

import com.projeto.model.Salas;
import com.projeto.service.SalasService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalasController {

    private final SalasService service;

    public SalasController(SalasService service) {
        this.service = service;
    }

    @GetMapping
    public List<Salas> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/disponiveis")
    public List<Salas> listarDisponiveis() {
        return service.listarDisponiveis();
    }

    @GetMapping("/{id}")
    public Salas buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id).orElse(null);
    }

    @PostMapping
    public Salas cadastrar(@RequestBody Salas sala) {
        return service.cadastrar(sala);
    }

    @PutMapping("/{id}")
    public Salas atualizar(
            @PathVariable Long id,
            @RequestBody Salas sala) {

        return service.atualizar(id, sala).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
