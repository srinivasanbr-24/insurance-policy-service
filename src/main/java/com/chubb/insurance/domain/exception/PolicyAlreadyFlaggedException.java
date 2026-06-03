package com.chubb.insurance.domain.exception;


public class PolicyAlreadyFlaggedException
        extends DomainException {

    public PolicyAlreadyFlaggedException(
            String message) {

        super(message);
    }
}