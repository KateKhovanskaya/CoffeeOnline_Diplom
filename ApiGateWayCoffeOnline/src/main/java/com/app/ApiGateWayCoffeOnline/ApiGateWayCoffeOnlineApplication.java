package com.app.ApiGateWayCoffeOnline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGateWayCoffeOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGateWayCoffeOnlineApplication.class, args);
	}

	@Bean
	public RouteLocator notesRouteLocator(RouteLocatorBuilder builder){
		return builder.routes()
				.route("CoffeeClient", r->r.path("/coffee-online/**")
						.uri("http://localhost:8080/"))
				.route("PaymentService", r->r.path("/paymentService/**")
						.uri("http://localhost:8081/"))
				.route("CoffeeManagerService", r->r.path("/coffeeManagerService/**")
						.uri("http://localhost:8082/"))
				.build();
	}
}
