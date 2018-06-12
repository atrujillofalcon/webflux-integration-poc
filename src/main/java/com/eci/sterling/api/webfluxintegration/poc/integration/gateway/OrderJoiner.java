package com.eci.sterling.api.webfluxintegration.poc.integration.gateway;

import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import reactor.core.publisher.Mono;

/**
 * @author Arnaldo Trujillo
 */
@MessagingGateway
public interface OrderJoiner {

    @Gateway(requestChannel = "aggregateOrderChannel",replyChannel = "aggregateOrderChannel")
    void joinOrder(Order order);

}
