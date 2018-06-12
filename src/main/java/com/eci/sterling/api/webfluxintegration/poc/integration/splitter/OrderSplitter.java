package com.eci.sterling.api.webfluxintegration.poc.integration.splitter;

import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Arnaldo Trujillo
 */
@MessageEndpoint
public class OrderSplitter {

    @Splitter(inputChannel = "splitterChannel", outputChannel = "processOrderChannel")
    public Flux<Order> split(Mono<Order> originalOrder) {
        return originalOrder
                .flatMapMany(this::createOrderByLineItem);
    }

    private Flux<Order> createOrderByLineItem(Order originalOrder) {
        return Flux.fromIterable(originalOrder.getLineItems().stream()
                .map(lineItem -> new Order(UUID.randomUUID().toString(), originalOrder.getOrderId(),   //creamos una nueva order por cada lineItem
                        false, Collections.singletonList(lineItem)))
                .collect(Collectors.toList()));
    }

}
