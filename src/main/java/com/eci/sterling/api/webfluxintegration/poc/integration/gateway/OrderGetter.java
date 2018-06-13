package com.eci.sterling.api.webfluxintegration.poc.integration.gateway;

import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.concurrent.Callable;

/**
 * @author Arnaldo Trujillo
 */
@MessagingGateway
public interface OrderGetter {

    @Gateway(requestChannel = "factoryChannel")
    Callable<Order> getNewOrder(String orderId);

}
