package org.rainn.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    public void configuraMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/chat");
        registry.setApplicationDestinationPrefixes("/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").addInterceptors(httpSessionHandshakeInterceptor())
                .withSockJS();
    }

    @Bean
    public HttpSessionHandshakeInterceptor httpSessionHandshakeInterceptor(){
        return new HttpSessionHandshakeInterceptor();
    }
}
