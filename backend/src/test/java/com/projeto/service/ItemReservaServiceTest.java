package com.projeto.service;

import com.projeto.model.ItemCampus;
import com.projeto.model.ReservaItem;
import com.projeto.model.Usuario;
import com.projeto.repository.ItemCampusRepository;
import com.projeto.repository.ReservaItemRepository;
import com.projeto.repository.UsuarioRepository;
import com.projeto.service.ItemReservaService.DadosReservaItem;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemReservaServiceTest {

    @Mock
    private ItemCampusRepository itemRepository;

    @Mock
    private ReservaItemRepository reservaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ItemReservaService service;

    private ItemCampus item;
    private Usuario usuario;
    private LocalDate data;

    @BeforeEach
    void setUp() {
        item = new ItemCampus("Projetor", "Audiovisual", 4);
        item.setId(5L);
        usuario = new Usuario("Ana", "ana@example.com", "hash");
        usuario.setId(7L);
        data = LocalDate.now().plusDays(1);
    }

    @Test
    void reservarDeveSalvarQuandoHaEstoqueNoPeriodo() {
        DadosReservaItem dados = dados(2, "09:00", "10:00");
        when(itemRepository.findByIdForUpdate(5L)).thenReturn(Optional.of(item));
        when(reservaRepository.somarQuantidadeReservada(
                5L,
                data,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                null
        )).thenReturn(1L);
        when(usuarioRepository.findById(7L)).thenReturn(Optional.of(usuario));
        when(reservaRepository.save(any(ReservaItem.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ReservaItem reserva = service.reservar(7L, dados);

        assertThat(reserva.getItem()).isSameAs(item);
        assertThat(reserva.getUsuario()).isSameAs(usuario);
        assertThat(reserva.getQuantidade()).isEqualTo(2);
        verify(reservaRepository).save(reserva);
    }

    @Test
    void reservarDeveRejeitarQuantidadeAcimaDoEstoqueDisponivel() {
        DadosReservaItem dados = dados(2, "09:00", "10:00");
        when(itemRepository.findByIdForUpdate(5L)).thenReturn(Optional.of(item));
        when(reservaRepository.somarQuantidadeReservada(
                5L,
                data,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                null
        )).thenReturn(3L);

        assertThatThrownBy(() -> service.reservar(7L, dados))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("apenas 1 unidade");
        verify(reservaRepository, never()).save(any(ReservaItem.class));
    }

    @Test
    void atualizarDeveDesconsiderarAQuantidadeDaPropriaReserva() {
        ReservaItem existente = new ReservaItem(
                item,
                usuario,
                data,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                2
        );
        DadosReservaItem dados = dados(4, "10:00", "11:00");
        when(reservaRepository.findByIdForUpdate(12L)).thenReturn(Optional.of(existente));
        when(itemRepository.findByIdForUpdate(5L)).thenReturn(Optional.of(item));
        when(reservaRepository.somarQuantidadeReservada(
                5L,
                data,
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                12L
        )).thenReturn(0L);
        when(reservaRepository.save(existente)).thenReturn(existente);

        ReservaItem atualizada = service.atualizarReserva(7L, 12L, dados);

        assertThat(atualizada.getQuantidade()).isEqualTo(4);
        verify(reservaRepository).somarQuantidadeReservada(
                5L,
                data,
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                12L
        );
    }

    @Test
    void disponibilidadeDeveRetornarEstoqueRestante() {
        when(reservaRepository.somarQuantidadeReservada(
                5L,
                data,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                null
        )).thenReturn(3L);
        when(itemRepository.findAll()).thenReturn(java.util.List.of(item));

        var disponibilidade = service.listarDisponibilidade(
                7L,
                data,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                null
        );

        assertThat(disponibilidade).singleElement()
                .extracting(ItemReservaService.ItemDisponibilidade::quantidadeDisponivel)
                .isEqualTo(1);
    }

    private DadosReservaItem dados(int quantidade, String inicio, String fim) {
        return new DadosReservaItem(
                5L,
                data,
                LocalTime.parse(inicio),
                LocalTime.parse(fim),
                quantidade
        );
    }
}
