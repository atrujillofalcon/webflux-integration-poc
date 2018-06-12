package com.eci.sterling.api.webfluxintegration.poc.integration.service;

import com.eci.sterling.api.webfluxintegration.poc.domain.LineItem;
import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import com.eci.sterling.api.webfluxintegration.poc.integration.gateway.OrderProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * @author Arnaldo Trujillo
 */
@Service
public class EntryService {

    @Autowired
    OrderProcessor orderProcessor;

    @ServiceActivator(inputChannel = "fluxRequestChannel")
    public void getOriginalOrderAndSplit() {
        orderProcessor.processOrder(Mono.just(new Order("1", null, false, Arrays.asList(
                new LineItem("motorola g5"),
                new LineItem("cesta de naranjas"),
                new LineItem("palomitas")
        ))));
    }

}
