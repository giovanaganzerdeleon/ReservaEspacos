
package com.projeto.service;

import com.projeto.model.Equipamentos;
import com.projeto.repository.EquipamentosRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentosService {

    private final EquipamentosRepository repository;

    public EquipamentosService(EquipamentosRepository repository) {
        this.repository = repository;
    }

    // Listar todos
    public List<Equipamentos> listarTodos() {
        return repository.findAll();
    }

    // Buscar por ID
    public Optional<Equipamentos> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Buscar pelo nome
    public List<Equipamentos> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    // Buscar pelo tipo
    public List<Equipamentos> buscarPorTipo(String tipo) {
        return repository.findByTipoContainingIgnoreCase(tipo);
    }

    // Buscar pelo modelo
    public List<Equipamentos> buscarPorModelo(String modelo) {
        return repository.findByModeloContainingIgnoreCase(modelo);
    }

    // Cadastrar
    public Equipamentos cadastrar(Equipamentos equipamento) {
        equipamento.setId(null);
        return repository.save(equipamento);
    }

    // Atualizar
    public Optional<Equipamentos> atualizar(
            Long id, Equipamentos dados) {

        return repository.findById(id).map(equipamento -> {

            equipamento.setNome(dados.getNome());
            equipamento.setModelo(dados.getModelo());
            equipamento.setTipo(dados.getTipo());
            equipamento.setQuantidade(dados.getQuantidade());

            return repository.save(equipamento);
        });
    }

    // Excluir
    public boolean excluir(Long id) {

        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
