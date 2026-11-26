package com.projeto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthenticatedSessionInterceptor authInterceptor;

    public WebConfig(AuthenticatedSessionInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(
                        "/salas",
                        "/salas/**",
                        "/reservas",
                        "/reservas/**",
                        "/itens",
                        "/itens/**",
                        "/reservas-itens",
                        "/reservas-itens/**"
                );
    }
}
