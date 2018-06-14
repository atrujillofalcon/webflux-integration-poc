package com.eci.sterling.api.webfluxintegration.poc.integration.cache;

import com.eci.sterling.api.webfluxintegration.poc.integration.splitter.OrderAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.AggregatorFactoryBean;

/**
 * @author Arnaldo Trujillo
 */
@Configuration
public class MessageStoreConfigurator {

    @Lazy
    @Autowired
    private HazelcastMessageStore hazelcastMessageStore;

    @Autowired
    private OrderAggregator orderAggregator;

    @Bean
    @ServiceActivator(inputChannel = "aggregateOrderChannel", outputChannel = "fluxResponseChannel")
    public AggregatorFactoryBean buildCustomAggregator() {
        AggregatorFactoryBean aggregatorFactory = new AggregatorFactoryBean();
        aggregatorFactory.setProcessorBean(orderAggregator);
        aggregatorFactory.setMessageStore(hazelcastMessageStore);

        return aggregatorFactory;
    }

}
