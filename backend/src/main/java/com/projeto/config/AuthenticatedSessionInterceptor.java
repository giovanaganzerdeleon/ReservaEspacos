package com.projeto.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AuthenticatedSessionInterceptor implements HandlerInterceptor {
    

    private final SessionIdentity sessionIdentity;

    public AuthenticatedSessionInterceptor(SessionIdentity sessionIdentity) {
        this.sessionIdentity = sessionIdentity;
    }

   
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws IOException {

    // Libera as requisições de preflight do CORS
    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
        return true;
    }

    if (sessionIdentity.getUserId(request) != null) {
        return true;
    }

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write("{\"error\":\"Faça login para continuar.\"}");
    return false;
}

}


