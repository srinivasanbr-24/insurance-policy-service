package com.chubb.insurance.domain.model;


import java.math.BigDecimal;

public record Money(
        BigDecimal amount,
        CurrencyCode currency
) {

    public Money {

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Amount must be greater than zero");
        }

        if (currency == null) {
            throw new IllegalArgumentException(
                    "Currency is mandatory");
        }
    }
}
