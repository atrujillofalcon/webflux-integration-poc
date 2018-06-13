package com.eci.sterling.api.webfluxintegration.poc.integration.splitter;

import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import java.util.Random;

@MessageEndpoint
public class OrderRouter {

    @Router(inputChannel = "processOrderChannel")
    public String resolveOrderItemChannel(Order orderItem) {
        return new Random().nextInt() % 2 == 0 ? "confirmedChannel" : "unconfirmedChannel";
    }

}