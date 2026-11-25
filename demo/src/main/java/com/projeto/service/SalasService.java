
package com.projeto.service;

import com.projeto.model.Salas;
import com.projeto.repository.SalasRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalasService {


    private final SalasRepository repository;

    public SalasService(SalasRepository repository) {
        this.repository = repository;
    }

    // =========================================
    // LISTAR TODAS AS SALAS
    // =========================================

    public List<Salas> listarTodas() {
        return repository.findAll();
    }

    // =========================================
    // LISTAR SALAS DISPONIVEIS
    // =========================================

    public List<Salas> listarDisponiveis() {
        return repository.findByDisponibilidadeTrue();
    }

    // =========================================
    // BUSCAR SALA POR ID
    // =========================================

    public Optional<Salas> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // =========================================
    // CADASTRAR SALA
    // =========================================

    public Salas cadastrar(Salas sala) {
        sala.setId(null);
        return repository.save(sala);
    }

    // =========================================
    // ATUALIZAR SALA
    // =========================================

    public Optional<Salas> atualizar(Long id, Salas dados) {

        return repository.findById(id).map(sala -> {

            sala.setNome(dados.getNome());
            sala.setCapacidade(dados.getCapacidade());
            sala.setDisponibilidade(dados.isDisponibilidade());

            return repository.save(sala);
        });
    }

    // =========================================
    // EXCLUIR SALA
    // =========================================

    public boolean excluir(Long id) {

        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
