package com.eci.sterling.api.webfluxintegration.poc.integration.splitter;

import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MessageEndpoint
public class OrderRouter {

    @Router(inputChannel = "processOrderChannel")
    public String resolveOrderItemChannel(Order orderItem) {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) % 2 == 0 ? "confirmedChannel" : "unconfirmedChannel";
    }

}