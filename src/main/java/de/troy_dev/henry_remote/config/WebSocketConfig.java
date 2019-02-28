package de.troy_dev.henry_remote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import de.troy_dev.henry_remote.HenrySocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(henrySocketHandler(), "/socket");
    }

    @Bean
    public HenrySocketHandler henrySocketHandler() {
        return new HenrySocketHandler();
    }
}
