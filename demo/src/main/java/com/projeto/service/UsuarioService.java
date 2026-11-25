
package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projeto.model.Usuario;
import com.projeto.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Listar todos os usuários
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário por ID
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Buscar usuário por email
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Cadastrar usuário
    public Usuario cadastrar(Usuario usuario) {

        usuario.setId(null);

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Este email já está cadastrado.");
        }

        return usuarioRepository.save(usuario);
    }

    // Atualizar usuário
    public Optional<Usuario> atualizar(Long id, Usuario dados) {

        return usuarioRepository.findById(id).map(usuario -> {

            Optional<Usuario> existente =
                    usuarioRepository.findByEmail(dados.getEmail());

            if (existente.isPresent() &&
                !existente.get().getId().equals(id)) {
                throw new IllegalArgumentException("Este email já está cadastrado.");
            }

            usuario.setNome(dados.getNome());
            usuario.setEmail(dados.getEmail());

            if (dados.getSenha() != null && !dados.getSenha().isBlank()) {
                usuario.setSenha(dados.getSenha());
            }

            return usuarioRepository.save(usuario);
        });
    }

    // Excluir usuário
    public boolean excluir(Long id) {

        if (!usuarioRepository.existsById(id)) {
            return false;
        }

        usuarioRepository.deleteById(id);
        return true;
    }
}
