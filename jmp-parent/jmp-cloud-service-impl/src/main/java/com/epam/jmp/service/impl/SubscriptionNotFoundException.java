package com.epam.jmp.service.impl;

public class SubscriptionNotFoundException extends RuntimeException{
    public SubscriptionNotFoundException(String message) {
        super(message);
    }

}
