package com.eazybank.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator eazyBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(predicate -> predicate
                        .path("/eazybank/accounts/**")
                        .filters(f -> f.rewritePath("/eazybank/accounts/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config
                                    .setName("accountsCircuitBreaker")
                                    .setFallbackUri("forward:/contactSupport")
                                )
                        )
                        .uri("http://accounts:8080")) //use load balancer to redirect for ACCOUNTS ms
                .route(predicate -> predicate
                        .path("/eazybank/loans/**")
                        .filters(f -> f.rewritePath("/eazybank/loans/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                //.circuitBreaker(config -> config.setName("loansCircuitBreaker")
                                //)
                                .retry(retryConfig -> retryConfig
                                        .setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000),2, true)
                                )
                        )
                        .uri("http://loans:8090"))
                .route(predicate -> predicate
                        .path("/eazybank/cards/**")
                        .filters(f -> f.rewritePath("/eazybank/cards/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("cardsCircuitBreaker")
                                )
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter()) // pass redis rate limiter bean
                                        .setKeyResolver(userKeyResolver()) // set key resolver
                                )
                        )
                        .uri("http://cards:9000"))
                .build();
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig
                        .custom()
                                .timeoutDuration(Duration.ofSeconds(4)) //timelimiter for circuit breaker
                        .build()
                )
                .build());
    }


    @Bean
    public RedisRateLimiter redisRateLimiter() {
        //add 1 token per second, burst capacity 1, cost per request is 1 token
        return new RedisRateLimiter(1, 1, 1);
    }


    @Bean
    KeyResolver userKeyResolver() {
        //this logic can be as complex as needed
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getQueryParams().getFirst("user"))//get user principal
                .defaultIfEmpty("anonymous");
    }

}
