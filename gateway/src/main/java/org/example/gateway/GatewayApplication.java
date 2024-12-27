package org.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		//System.out.println("GatewayApplication.myRoutes");
		return builder.routes()
				.route("main-service-route", r -> r.path("/main/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://MAIN-SERVICE"))
				.route("websocket-route", r -> r.path("/ws-orders/**")
						.uri("lb://MAIN-SERVICE"))
				.route("author-service-route", r -> r.path("/author/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://AUTHOR-SERVICE"))
				.route("spark-service-route", r -> r.path("/spark/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8093"))
				.build();
	}

	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("Service is unavailable, please try again later.");
	}
}
