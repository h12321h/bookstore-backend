package org.example.functionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class FunctionServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FunctionServiceApplication.class, args);
	}

	@Bean
	public Function<Mono<List<Integer>>, Mono<Integer>> multiply() {
		return mono -> mono.map(list -> {
			if (list.size() >= 2) {
				System.out.println("Multiplying " + list.get(0) + " and " + list.get(1));
				return list.get(0) * list.get(1);
			} else {
				System.out.println("List size insufficient: " + list);
				return 0;
			}
		});
	}
}
