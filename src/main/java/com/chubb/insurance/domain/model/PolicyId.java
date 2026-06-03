package com.chubb.insurance.domain.model;

import java.util.UUID;

public record PolicyId(UUID value) {

    public PolicyId {

        if (value == null) {
            throw new IllegalArgumentException(
                    "Policy id cannot be null");
        }
    }

    public static PolicyId generate() {
        return new PolicyId(UUID.randomUUID());
    }
}