package com.projeto.service;

import com.projeto.model.Agendamento;
import com.projeto.model.Salas;
import com.projeto.model.Usuario;
import com.projeto.repository.AgendamentoRepository;
import com.projeto.repository.SalasRepository;
import com.projeto.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private SalasRepository salasRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AgendamentoService service;

    private Salas sala;
    private Usuario usuario;
    private LocalDate data;

    @BeforeEach
    void setUp() {
        sala = new Salas("Sala 101", 30, true);
        sala.setId(3L);
        usuario = new Usuario("Ana", "ana@example.com", "hash");
        usuario.setId(7L);
        data = LocalDate.now().plusDays(1);
    }

    @Test
    void criarDeveSalvarReservaSemConflito() {
        AgendamentoService.DadosAgendamento dados = dados("09:00", "10:00");
        when(salasRepository.findByIdForUpdate(3L)).thenReturn(Optional.of(sala));
        when(agendamentoRepository.existeConflito(3L, data, LocalTime.of(9, 0), LocalTime.of(10, 0), null))
                .thenReturn(false);
        when(usuarioRepository.findById(7L)).thenReturn(Optional.of(usuario));
        when(agendamentoRepository.save(any(Agendamento.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Agendamento reserva = service.criar(7L, dados);

        assertThat(reserva.getSala()).isSameAs(sala);
        assertThat(reserva.getUsuario()).isSameAs(usuario);
        assertThat(reserva.getHoraInicio()).isEqualTo(LocalTime.of(9, 0));
        assertThat(reserva.getHoraFim()).isEqualTo(LocalTime.of(10, 0));
        verify(agendamentoRepository).save(reserva);
    }

    @Test
    void criarDeveRejeitarSobreposicao() {
        AgendamentoService.DadosAgendamento dados = dados("09:30", "10:30");
        when(salasRepository.findByIdForUpdate(3L)).thenReturn(Optional.of(sala));
        when(agendamentoRepository.existeConflito(3L, data, LocalTime.of(9, 30), LocalTime.of(10, 30), null))
                .thenReturn(true);

        assertThatThrownBy(() -> service.criar(7L, dados))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("já está reservado");
        verify(agendamentoRepository, never()).save(any(Agendamento.class));
    }

    @Test
    void atualizarDeveIgnorarAPropriaReservaNaVerificacaoDeConflito() {
        Agendamento existente = new Agendamento(sala, usuario, data, LocalTime.of(9, 0), LocalTime.of(10, 0));
        AgendamentoService.DadosAgendamento dados = dados("10:00", "11:00");
        when(agendamentoRepository.findByIdForUpdate(12L)).thenReturn(Optional.of(existente));
        when(salasRepository.findByIdForUpdate(3L)).thenReturn(Optional.of(sala));
        when(agendamentoRepository.existeConflito(
                eq(3L),
                eq(data),
                eq(LocalTime.of(10, 0)),
                eq(LocalTime.of(11, 0)),
                eq(12L)
        )).thenReturn(false);
        when(agendamentoRepository.save(existente)).thenReturn(existente);

        Agendamento atualizada = service.atualizar(7L, 12L, dados);

        assertThat(atualizada.getHoraInicio()).isEqualTo(LocalTime.of(10, 0));
        verify(agendamentoRepository).existeConflito(3L, data, LocalTime.of(10, 0), LocalTime.of(11, 0), 12L);
    }

    @Test
    void atualizarDevePermitirManterSalaAtualIndisponivel() {
        sala.setDisponibilidade(false);
        Agendamento existente = new Agendamento(sala, usuario, data, LocalTime.of(9, 0), LocalTime.of(10, 0));
        AgendamentoService.DadosAgendamento dados = dados("10:00", "11:00");
        when(agendamentoRepository.findByIdForUpdate(12L)).thenReturn(Optional.of(existente));
        when(salasRepository.findByIdForUpdate(3L)).thenReturn(Optional.of(sala));
        when(agendamentoRepository.existeConflito(3L, data, LocalTime.of(10, 0), LocalTime.of(11, 0), 12L))
                .thenReturn(false);
        when(agendamentoRepository.save(existente)).thenReturn(existente);

        Agendamento atualizada = service.atualizar(7L, 12L, dados);

        assertThat(atualizada.getSala()).isSameAs(sala);
        verify(agendamentoRepository).save(existente);
    }

    @Test
    void criarDeveRejeitarIntervaloInvertido() {
        assertThatThrownBy(() -> service.criar(7L, dados("11:00", "10:00")))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("depois do inicial");
        verify(salasRepository, never()).findByIdForUpdate(any());
    }

    private AgendamentoService.DadosAgendamento dados(String inicio, String fim) {
        return new AgendamentoService.DadosAgendamento(
                3L,
                data,
                LocalTime.parse(inicio),
                LocalTime.parse(fim)
        );
    }
}
