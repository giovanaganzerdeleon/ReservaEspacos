
package com.projeto.controller;

import com.projeto.model.Equipamentos;
import com.projeto.service.EquipamentosService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentosController {

    private final EquipamentosService service;

    public EquipamentosController(EquipamentosService service) {
        this.service = service;
    }

    // Listar todos
    @GetMapping
    public List<Equipamentos> listarTodos() {
        return service.listarTodos();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipamentos> buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar pelo nome
    @GetMapping("/buscar/nome")
    public List<Equipamentos> buscarPorNome(
            @RequestParam String nome) {

        return service.buscarPorNome(nome);
    }

    // Buscar pelo tipo
    @GetMapping("/buscar/tipo")
    public List<Equipamentos> buscarPorTipo(
            @RequestParam String tipo) {

        return service.buscarPorTipo(tipo);
    }

    // Buscar pelo modelo
    @GetMapping("/buscar/modelo")
    public List<Equipamentos> buscarPorModelo(
            @RequestParam String modelo) {

        return service.buscarPorModelo(modelo);
    }

    // Cadastrar
    @PostMapping
    public Equipamentos cadastrar(
            @RequestBody Equipamentos equipamento) {

        return service.cadastrar(equipamento);
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Equipamentos> atualizar(
            @PathVariable Long id,
            @RequestBody Equipamentos dados) {

        return service.atualizar(id, dados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!service.excluir(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
