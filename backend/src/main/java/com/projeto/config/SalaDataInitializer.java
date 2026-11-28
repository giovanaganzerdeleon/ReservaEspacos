package com.projeto.config;

import com.projeto.model.Salas;
import com.projeto.repository.SalasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalaDataInitializer implements CommandLineRunner {

    private final SalasRepository salasRepository;

    public SalaDataInitializer(SalasRepository salasRepository) {
        this.salasRepository = salasRepository;
    }

    @Override
    public void run(String... args) {
        if (salasRepository.count() == 0) {
            salasRepository.saveAll(List.of(
                    new Salas("Sala 101", 30, true),
                    new Salas("Sala 102", 20, true),
                    new Salas("Laboratório de Informática", 24, true),
                    new Salas("Sala multimídia", 18, true)
            ));
        }
    }
}
