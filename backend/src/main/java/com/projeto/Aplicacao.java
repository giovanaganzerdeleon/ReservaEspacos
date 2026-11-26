package com.projeto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.web.server.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.projeto.model")
@EnableJpaRepositories(basePackages = "com.projeto.repository")
public class Aplicacao {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aplicacao.class);

    public static void main(String[] args) {
        SpringApplication.run(Aplicacao.class, args);
    }

    @EventListener(WebServerInitializedEvent.class)
    public void mostrarMensagemDePronto(WebServerInitializedEvent event) {
        LOGGER.info("ReservaCampus esta pronto. Acesse http://127.0.0.1:{}",
                event.getWebServer().getPort());
    }
}