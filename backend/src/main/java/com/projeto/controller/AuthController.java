package com.projeto.controller;

import com.projeto.model.Usuario;
import com.projeto.config.SessionIdentity;
import com.projeto.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final SessionIdentity sessionIdentity;

    public AuthController(AuthService authService, SessionIdentity sessionIdentity) {
        this.authService = authService;
        this.sessionIdentity = sessionIdentity;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UserResponse> cadastrar(@RequestBody SignupRequest request, HttpServletRequest httpRequest) {
        Usuario usuario = authService.cadastrar(request.nome(), request.email(), request.senha());
        iniciarSessao(httpRequest, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(usuario));
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        Usuario usuario = authService.autenticar(request.email(), request.senha());
        iniciarSessao(httpRequest, usuario);
        return UserResponse.from(usuario);
    }

    @GetMapping("/me")
    public UserResponse usuarioAtual(HttpServletRequest request) {
        Long userId = sessionIdentity.getUserId(request);
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Faça login para continuar.");
        }
        return UserResponse.from(authService.buscarPorId(userId));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.noContent().build();
    }

    private void iniciarSessao(HttpServletRequest request, Usuario usuario) {
        sessionIdentity.startSession(request, usuario.getId());
    }

    public record LoginRequest(String email, String senha) {
    }

    public record SignupRequest(String nome, String email, String senha) {
    }

    public record UserResponse(Long id, String nome, String email) {

        private static UserResponse from(Usuario usuario) {
            return new UserResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
        }
    }
}
