/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eci.sterling.api.webfluxintegration.poc.integration.splitter;

import com.eci.sterling.api.webfluxintegration.poc.domain.LineItem;
import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ReleaseStrategy;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marius Bogoevici
 */
@MessageEndpoint
public class OrderAggregator {

    @Aggregator(inputChannel = "aggregateOrderChannel", outputChannel = "fluxResponseChannel", sendTimeout = "10000", sendPartialResultsOnExpiry = "true")
    public Mono<Order> joinProcessedOrders(List<Order> processedOrders) {
        System.out.println(processedOrders.size());

        if (true)
            throw new IllegalArgumentException("Testing error handling");

        Order firstOrder = processedOrders.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found items to aggregate"));

        List<LineItem> processedItems = processedOrders.stream()
                .map(Order::getLineItems)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return Mono.just(new Order(firstOrder.getOrderId(), null, true, processedItems));
    }

    @CorrelationStrategy
    public String correlateByOrderParentId(Order order) {
        return order.getParentOrderId();
    }

    /**
     * Aquí se define la condición para
     *
     * @param orders
     * @return
     */
    @ReleaseStrategy
    public boolean isCompleteAggregation(List<Order> orders) {
        return orders.size() >= 3;
    }

}
