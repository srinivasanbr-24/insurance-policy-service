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
}
