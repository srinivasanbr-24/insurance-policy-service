package com.chubb.insurance.domain.model;


public record PolicyNumber(String value) {

    public PolicyNumber {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Policy number cannot be empty");
        }
    }
}