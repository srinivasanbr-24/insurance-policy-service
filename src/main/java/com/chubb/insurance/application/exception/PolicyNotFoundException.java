package com.chubb.insurance.application.exception;


public class PolicyNotFoundException
        extends RuntimeException {

    public PolicyNotFoundException(
            String message) {

        super(message);
    }
}
