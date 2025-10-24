package com.health.gateway_service.security;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class JwtAuthenticationFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public GlobalFilter customFilter() {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getPath().toString();
            // Skip auth endpoints
            if (path.startsWith("/auth/")) {
                return chain.filter(exchange);
            }

            String header = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (header == null || !header.startsWith("Bearer ")) {
                return this.onError(exchange, "Missing Authorization Header", HttpStatus.UNAUTHORIZED);
            }

            String token = header.substring(7);
            if (!jwtUtil.validateToken(token)) {
                return this.onError(exchange, "Invalid JWT token", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }
}

