package com.projeto.service;

import com.projeto.model.Usuario;
import com.projeto.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private AuthService service;

    @Test
    void cadastrarDeveArmazenarSenhaComHash() {
        when(repository.findByEmailIgnoreCase("ana@example.com")).thenReturn(Optional.empty());
        when(repository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario usuario = service.cadastrar(" Ana ", "Ana@Example.com", "senha-segura");

        assertThat(usuario.getNome()).isEqualTo("Ana");
        assertThat(usuario.getEmail()).isEqualTo("ana@example.com");
        assertThat(new BCryptPasswordEncoder().matches("senha-segura", usuario.getSenha())).isTrue();
        assertThat(usuario.getSenha()).isNotEqualTo("senha-segura");
        verify(repository).save(usuario);
    }

    @Test
    void autenticarDeveRejeitarSenhaIncorreta() {
        String hash = new BCryptPasswordEncoder().encode("senha-segura");
        when(repository.findByEmailIgnoreCase("ana@example.com"))
                .thenReturn(Optional.of(new Usuario("Ana", "ana@example.com", hash)));

        assertThatThrownBy(() -> service.autenticar("ana@example.com", "senha-errada"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("E-mail ou senha incorretos");
    }

    @Test
    void autenticarDeveAceitarSenhaCorreta() {
        String hash = new BCryptPasswordEncoder().encode("senha-segura");
        Usuario usuario = new Usuario("Ana", "ana@example.com", hash);
        when(repository.findByEmailIgnoreCase("ana@example.com"))
                .thenReturn(Optional.of(usuario));

        Usuario autenticado = service.autenticar(" Ana@Example.com ", "senha-segura");

        assertThat(autenticado).isSameAs(usuario);
    }

    @Test
    void autenticarDeveRejeitarHashInvalidoSemErroInterno() {
        Usuario usuario = new Usuario("Ana", "ana@example.com", "hash-antigo-invalido");
        when(repository.findByEmailIgnoreCase("ana@example.com"))
                .thenReturn(Optional.of(usuario));

        assertThatThrownBy(() -> service.autenticar("ana@example.com", "senha-segura"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("E-mail ou senha incorretos");
    }

    @Test
    void cadastrarDeveRejeitarSenhaCurta() {
        assertThatThrownBy(() -> service.cadastrar("Ana", "ana@example.com", "123"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("pelo menos 8 caracteres");
    }
}
