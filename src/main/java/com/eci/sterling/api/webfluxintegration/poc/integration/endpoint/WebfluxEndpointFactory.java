package com.eci.sterling.api.webfluxintegration.poc.integration.endpoint;

import com.eci.sterling.api.webfluxintegration.poc.domain.LineItem;
import com.eci.sterling.api.webfluxintegration.poc.domain.Order;
import com.eci.sterling.api.webfluxintegration.poc.integration.gateway.OrderProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.webflux.inbound.WebFluxInboundEndpoint;
import org.springframework.messaging.MessageChannel;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Configuration
@EnableIntegration
public class WebfluxEndpointFactory {

    @Autowired
    OrderProcessor orderProcessor;

    @Bean
    public WebFluxInboundEndpoint jsonInboundEndpoint(@Autowired MessageChannel fluxResultChannel) {
        WebFluxInboundEndpoint endpoint = new WebFluxInboundEndpoint();
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setMethods(HttpMethod.POST);
        requestMapping.setPathPatterns("/order");
        endpoint.setRequestMapping(requestMapping);
        endpoint.setRequestChannel(fluxResultChannel);
        return endpoint;
    }

    @ServiceActivator(inputChannel = "fluxResultChannel")
    public void getOriginalOrderAndSplit() {
        orderProcessor.processOrder(Mono.just(new Order("1", null, false, Arrays.asList(
                new LineItem("motorola g5"),
                new LineItem("cesta de naranjas"),
                new LineItem("palomitas")
        ))));
    }


}
