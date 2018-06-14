package com.eci.sterling.api.webfluxintegration.poc.integration.cache;

import org.springframework.context.annotation.Configuration;

/**
 * @author Arnaldo Trujillo
 */
@Configuration
public class MessageStoreConfigurator {

    /*@Lazy
    @Autowired
    HazelcastMessageStore hazelcastMessageStore;

    @Bean
    @ServiceActivator(inputChannel = "aggregateOrderChannel", outputChannel = "fluxResponseChannel")
    public AggregatorFactoryBean buildCustomAggregator() {
        AggregatorFactoryBean aggregatorFactory = new AggregatorFactoryBean();
        aggregatorFactory.setMessageStore(hazelcastMessageStore);
//        aggregatorFactory.setCorrelationStrategy();

        return aggregatorFactory;
    }*/

}
