package com.projeto.repository;

import com.projeto.model.ItemCampus;
import com.projeto.model.ReservaItem;
import com.projeto.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {
                "spring.datasource.url=jdbc:h2:mem:reservaitems;DB_CLOSE_DELAY=-1",
                "spring.jpa.hibernate.ddl-auto=create-drop",
                "spring.jpa.show-sql=false",
                "spring.main.web-application-type=none"
        }
)
class ReservaItemRepositoryTest {

    @Autowired
    private ItemCampusRepository itemRepository;

    @Autowired
    private ReservaItemRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void somaSomenteReservasAtivasComHorarioSobreposto() {
        ItemCampus item = itemRepository.save(new ItemCampus("Kit de canetões", "Material de sala", 5));
        Usuario usuario = usuarioRepository.save(new Usuario("Ana", "ana@example.com", "hash"));
        LocalDate data = LocalDate.now().plusDays(1);
        ReservaItem primeira = reservaRepository.saveAndFlush(new ReservaItem(
                item,
                usuario,
                data,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                2
        ));
        reservaRepository.saveAndFlush(new ReservaItem(
                item,
                usuario,
                data,
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                1
        ));

        assertThat(reservaRepository.somarQuantidadeReservada(
                item.getId(),
                data,
                LocalTime.of(9, 30),
                LocalTime.of(10, 30),
                null
        )).isEqualTo(3L);
        assertThat(reservaRepository.somarQuantidadeReservada(
                item.getId(),
                data,
                LocalTime.of(9, 30),
                LocalTime.of(10, 30),
                primeira.getId()
        )).isEqualTo(1L);
        assertThat(reservaRepository.somarQuantidadeReservada(
                item.getId(),
                data,
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                null
        )).isNull();

        primeira.setCancelada(true);
        reservaRepository.saveAndFlush(primeira);

        assertThat(reservaRepository.somarQuantidadeReservada(
                item.getId(),
                data,
                LocalTime.of(9, 30),
                LocalTime.of(10, 30),
                null
        )).isEqualTo(1L);
    }
}
