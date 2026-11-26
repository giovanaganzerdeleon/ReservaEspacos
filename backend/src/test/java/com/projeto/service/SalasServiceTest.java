package com.projeto.service;

import com.projeto.model.Salas;
import com.projeto.repository.SalasRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalasServiceTest {

    @Mock
    private SalasRepository repository;

    @InjectMocks
    private SalasService service;

    @Test
    void cadastrarDeveSalvarSala() {
        Salas sala = new Salas("Laboratorio 1", 30, true);
        when(repository.save(any(Salas.class))).thenReturn(sala);

        Salas salva = service.cadastrar(sala);

        assertThat(salva).isSameAs(sala);
        verify(repository).save(sala);
    }

    @Test
    void listarDisponiveisDeveConsultarRepositorio() {
        Salas sala = new Salas("Sala 101", 20, true);
        when(repository.findByDisponibilidadeTrue()).thenReturn(List.of(sala));

        List<Salas> resultado = service.listarDisponiveis();

        assertThat(resultado).containsExactly(sala);
        verify(repository).findByDisponibilidadeTrue();
    }
}
