package com.eci.sterling.api.webfluxintegration.poc.integration.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.router.ErrorMessageExceptionTypeRouter;
import org.springframework.messaging.support.ErrorMessage;

/**
 * @author Arnaldo Trujillo
 */
@Configuration
public class ErrorHandlingService {

    //TODO no me ha llegado a funcionar ninguna configuracion

    @ServiceActivator(inputChannel = "errorChannel")
    public String handleError(ErrorMessage e) {
        System.out.println(e.getPayload().getCause().getMessage());
        return e.getPayload().getCause().getMessage();
    }

    @Bean
    public IntegrationFlow handleError(@Autowired ErrorTransformer errorTransformer,@Autowired ErrorMessageExceptionTypeRouter typeRouter) {
        return flow -> flow.channel("fluxResponseChannel").handle(errorTransformer);
    }

    @Bean
    @ServiceActivator(inputChannel = "fluxResponseChannel")
    public ErrorMessageExceptionTypeRouter routeError() {
        ErrorMessageExceptionTypeRouter router = new ErrorMessageExceptionTypeRouter();
        router.setChannelMapping(RuntimeException.class.getName(), "errorChannel");
        return router;
    }

}
