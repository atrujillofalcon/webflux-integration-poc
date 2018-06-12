package com.eci.sterling.api.webfluxintegration.poc.integration.service;

import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import com.eci.sterling.api.webfluxintegration.poc.integration.gateway.OrderJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * @author Arnaldo Trujillo
 */
@Component
public class SterlingService {

    @Autowired
    private OrderJoiner joiner;

    @ServiceActivator(inputChannel = "confirmedChannel", outputChannel = "aggregateOrderChannel")
    public Order confirmOrder(Order order) {
        order.setConfirmed(true);
        order.getLineItems().stream().findFirst().ifPresent(lineItem -> lineItem.setStockConfirmed(true));
        return order;
    }

    @ServiceActivator(inputChannel = "unconfirmedChannel", outputChannel = "aggregateOrderChannel")
    public Order unconfirmOrder(Order order) {
        order.setConfirmed(false);
        order.getLineItems().stream().findFirst().ifPresent(lineItem -> lineItem.setStockConfirmed(true));
        return order;
    }

}
