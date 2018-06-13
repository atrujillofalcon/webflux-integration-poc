package com.eci.sterling.api.webfluxintegration.poc.integration.service;

import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import com.eci.sterling.api.webfluxintegration.poc.integration.gateway.OrderJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

/**
 * @author Arnaldo Trujillo
 */
@Service
public class SterlingService {

    @Autowired
    private OrderJoiner joiner;

    @ServiceActivator(inputChannel = "confirmedChannel", outputChannel = "aggregateOrderChannel")
    public Order confirmOrder(Order order) {
        return setLineItemsStock(order, true);
    }

    @ServiceActivator(inputChannel = "unconfirmedChannel", outputChannel = "aggregateOrderChannel")
    public Order unconfirmOrder(Order order) {
        return setLineItemsStock(order, false);
    }

    private Order setLineItemsStock(Order order, boolean stockConfirmed) {
        order.setConfirmed(true);
        order.getLineItems().stream().findFirst().ifPresent(lineItem -> lineItem.setStockConfirmed(stockConfirmed));
        return order;
    }

}
