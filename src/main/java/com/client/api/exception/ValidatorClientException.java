package com.client.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidatorClientException extends RuntimeException {

    private String messageCustom;

    public ValidatorClientException(String message, String messageCustom) {
        super(message);
        this.messageCustom = messageCustom;
    }
}