package com.chubb.insurance.support;

import com.chubb.insurance.domain.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public final class TestPolicyFactory {

    private TestPolicyFactory() {
    }

    public static Policy createPolicy() {

        return Policy.restore(
                new PolicyId(UUID.randomUUID()),
                new PolicyNumber("POL-1001"),
                "John Tan",
                "Chubb APAC",
                LineOfBusiness.PROPERTY,
                PolicyStatus.ACTIVE,
                "Singapore",
                new Money(
                        BigDecimal.valueOf(10000),
                        CurrencyCode.SGD
                ),
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                false
        );
    }

    public static Policy activePolicy(UUID id) {

        return Policy.create(
                new PolicyId(id),
                new PolicyNumber("POL-1001"),
                "John Doe",
                "CHUBB",
                LineOfBusiness.PROPERTY,
                PolicyStatus.ACTIVE,
                "APAC",
                new Money(
                        BigDecimal.valueOf(1000),
                        CurrencyCode.USD
                ),
                LocalDate.now(),
                LocalDate.now().plusYears(1)
        );
    }


    public static Policy flaggedPolicy(UUID id) {
        return Policy.restore(
                new PolicyId(id),
                new PolicyNumber("POL-FLAG-1001"),
                "Jane Flagged",
                "CHUBB",
                LineOfBusiness.PROPERTY,
                PolicyStatus.ACTIVE, // IMPORTANT: still ACTIVE in your model
                "APAC",
                new Money(BigDecimal.valueOf(5000), CurrencyCode.USD),
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                true // flaggedForReview = TRUE
        );
    }


    public static Policy expiredPolicy(UUID id) {
        return Policy.restore(
                new PolicyId(id),
                new PolicyNumber("POL-EXP-1001"),
                "Old Policy Holder",
                "CHUBB",
                LineOfBusiness.PROPERTY,
                PolicyStatus.EXPIRED,
                "APAC",
                new Money(BigDecimal.valueOf(8000), CurrencyCode.USD),
                LocalDate.now().minusYears(2),
                LocalDate.now().minusDays(1),
                false
        );
    }

    public static Policy flaggedPolicy() {
        return flaggedPolicy(UUID.randomUUID());
    }

    public static Policy expiredPolicy() {
        return expiredPolicy(UUID.randomUUID());
    }
}
