package com.chubb.insurance.infrastructure.adapters.out.cache.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CachedPolicy(

        UUID id,
        String policyNumber,
        String policyholderName,
        String underwriter,
        String lineOfBusiness,
        String status,
        String region,
        BigDecimal premiumAmount,
        String currency,
        LocalDate effectiveDate,
        LocalDate expirationDate,
        boolean flaggedForReview

) implements Serializable {
}