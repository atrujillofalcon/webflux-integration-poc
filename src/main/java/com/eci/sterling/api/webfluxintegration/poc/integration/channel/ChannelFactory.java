package com.eci.sterling.api.webfluxintegration.poc.integration.channel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.FluxMessageChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

/**
 * @author Arnaldo Trujillo
 */
@Configuration
@EnableIntegration
public class ChannelFactory {

    @Bean
    public MessageChannel fluxRequestChannel() {
        return new FluxMessageChannel();
    }

    @Bean
    public MessageChannel fluxResponseChannel() {
        return new FluxMessageChannel();
    }

    @Bean
    public MessageChannel splitterChannel() {
        return new FluxMessageChannel();
    }

    @Bean
    public MessageChannel aggregateOrderChannel() {
        return new FluxMessageChannel();
    }

    @Bean
    public MessageChannel confirmedChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public MessageChannel unconfirmedChannel() {
        return new FluxMessageChannel();
    }

    @Bean
    //este sería el canal que donde se ejecutaria Sterling
    public MessageChannel processOrderChannel() {
        return new FluxMessageChannel();
    }



}
