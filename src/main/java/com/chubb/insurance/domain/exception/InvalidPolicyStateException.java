package com.chubb.insurance.domain.exception;


public class InvalidPolicyStateException
        extends DomainException {

    public InvalidPolicyStateException(
            String message) {

        super(message);
    }
}
