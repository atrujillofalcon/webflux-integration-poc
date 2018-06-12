package com.eci.sterling.api.webfluxintegration.poc.integration.error;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;

/**
 * @author Arnaldo Trujillo
 */
@MessageEndpoint
public class ErrorTransformer {

    @Transformer
    public Message<?> transformAggregateError(Message errorMessage) {
        if (errorMessage instanceof GenericMessage)
            return errorMessage;
        else
            return ((MessagingException) errorMessage.getPayload()).getFailedMessage();
    }
}
