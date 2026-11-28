package com.projeto.config;

import com.projeto.model.Salas;
import com.projeto.repository.SalasRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalaDataInitializerTest {

    @Mock
    private SalasRepository salasRepository;

    @InjectMocks
    private SalaDataInitializer initializer;

    @Test
    void criaSalasDeDemonstracaoQuandoRepositorioVazio() {
        List<Salas> salasSalvas = new ArrayList<>();
        when(salasRepository.count()).thenReturn(0L);
        when(salasRepository.saveAll(any())).thenAnswer(invocation -> {
            Iterable<Salas> salas = invocation.getArgument(0);
            salas.forEach(salasSalvas::add);
            return salasSalvas;
        });

        initializer.run();

        assertThat(salasSalvas)
                .extracting(Salas::getNome)
                .containsExactly(
                        "Sala 101",
                        "Sala 102",
                        "Laboratório de Informática",
                        "Sala multimídia"
                );
        assertThat(salasSalvas).allSatisfy(sala -> {
            assertThat(sala.getCapacidade()).isPositive();
            assertThat(sala.isDisponibilidade()).isTrue();
        });
    }

    @Test
    void naoAlteraSalasQuandoRepositorioJaPossuiDados() {
        when(salasRepository.count()).thenReturn(1L);

        initializer.run();

        verify(salasRepository, never()).saveAll(any());
    }
}
