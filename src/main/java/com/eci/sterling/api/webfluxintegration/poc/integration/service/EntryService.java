package com.eci.sterling.api.webfluxintegration.poc.integration.service;

import com.eci.sterling.api.webfluxintegration.poc.domain.LineItem;
import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import com.eci.sterling.api.webfluxintegration.poc.integration.gateway.OrderProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author Arnaldo Trujillo
 */
@Service
public class EntryService {

    @Autowired
    OrderProcessor orderProcessor;

    @ServiceActivator(inputChannel = "fluxRequestChannel", outputChannel = "splitterChannel")
    public Mono<Order> getOriginalOrderAndSplit() {
        return Mono.just(new Order(UUID.randomUUID().toString(), null, false, Arrays.asList(
                new LineItem("motorola g5"),
                new LineItem("cesta de naranjas"),
                new LineItem("palomitas")
        )));
    }

    @ServiceActivator(inputChannel = "fluxResponseChannel")
    public Mono<Order> returnProcessedOrder(Mono<Order> order) {
        return order;
    }

}
