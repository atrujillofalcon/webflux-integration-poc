package com.eci.sterling.api.webfluxintegration.poc.integration.gateway;

import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import reactor.core.publisher.Mono;

/**
 * @author Arnaldo Trujillo
 */
@MessagingGateway
public interface OrderProcessor {

    /**
     * Simula a nuestro punto de entrada cuando ya hemos convertido la petición XML a un objeto Java (Order)
     *
     * @param order
     */
    @Gateway(requestChannel = "splitterChannel")
    Mono<Order> processOrder(Mono<Order> order);

}
