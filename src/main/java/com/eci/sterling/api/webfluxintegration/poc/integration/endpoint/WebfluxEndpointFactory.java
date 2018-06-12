package com.eci.sterling.api.webfluxintegration.poc.integration.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.webflux.inbound.WebFluxInboundEndpoint;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class WebfluxEndpointFactory {

    @Bean
    public WebFluxInboundEndpoint jsonInboundEndpoint(@Autowired MessageChannel fluxRequestChannel, @Autowired MessageChannel fluxResponseChannel) {
        WebFluxInboundEndpoint endpoint = new WebFluxInboundEndpoint();
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setMethods(HttpMethod.POST);
        requestMapping.setPathPatterns("/order");
        endpoint.setRequestMapping(requestMapping);
        endpoint.setRequestChannel(fluxRequestChannel);
//        endpoint.setReplyChannel(fluxResponseChannel);
        return endpoint;
    }


}
