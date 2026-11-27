package com.projeto.service;

import com.projeto.model.Usuario;
import com.projeto.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

@Service
public class AuthService {

    private static final int PASSWORD_MIN_LENGTH = 8;

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrar(String nome, String email, String senha) {
        String nomeNormalizado = nome == null ? "" : nome.trim();
        String emailNormalizado = normalizarEmail(email);

        if (nomeNormalizado.isBlank() || nomeNormalizado.length() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe um nome válido.");
        }
        if (!emailNormalizado.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$") || emailNormalizado.length() > 150) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe um e-mail válido.");
        }
        if (senha == null || senha.length() < PASSWORD_MIN_LENGTH) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A senha deve ter pelo menos 8 caracteres.");
        }
        if (repository.findByEmailIgnoreCase(emailNormalizado).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma conta com este e-mail.");
        }

        Usuario usuario = new Usuario(nomeNormalizado, emailNormalizado, passwordEncoder.encode(senha));
        return repository.save(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        String emailNormalizado = normalizarEmail(email);
        Usuario usuario = repository.findByEmailIgnoreCase(emailNormalizado)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha incorretos."));

        if (!senhaEstaCorreta(senha, usuario.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha incorretos.");
        }

        return usuario;
    }

    private boolean senhaEstaCorreta(String senhaInformada, String senhaArmazenada) {
        if (senhaInformada == null || senhaArmazenada == null) {
            return false;
        }

        try {
            return passwordEncoder.matches(senhaInformada, senhaArmazenada);
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sessão inválida."));
    }

    private String normalizarEmail(String email) {
        return email == null ? "" : email.trim().toLowerCase(Locale.ROOT);
    }
}
