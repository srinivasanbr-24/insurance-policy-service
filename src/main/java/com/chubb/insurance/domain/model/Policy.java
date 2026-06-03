package com.chubb.insurance.domain.model;


import com.chubb.insurance.domain.event.PolicyFlaggedEvent;
import com.chubb.insurance.domain.exception.InvalidPolicyStateException;
import com.chubb.insurance.domain.exception.PolicyAlreadyFlaggedException;

import java.time.LocalDate;

public class Policy {

    private final PolicyId id;

    private final PolicyNumber policyNumber;

    private final String policyholderName;

    private final String underwriter;

    private final LineOfBusiness lineOfBusiness;

    private PolicyStatus status;

    private final String region;

    private final Money premium;

    private final LocalDate effectiveDate;

    private final LocalDate expirationDate;

    private boolean flaggedForReview;

    public Policy(
            PolicyId id,
            PolicyNumber policyNumber,
            String policyholderName,
            String underwriter,
            LineOfBusiness lineOfBusiness,
            PolicyStatus status,
            String region,
            Money premium,
            LocalDate effectiveDate,
            LocalDate expirationDate,
            boolean flaggedForReview
    ) {

        validateDates(
                effectiveDate,
                expirationDate
        );

        this.id = id;
        this.policyNumber = policyNumber;
        this.policyholderName = policyholderName;
        this.underwriter = underwriter;
        this.lineOfBusiness = lineOfBusiness;
        this.status = status;
        this.region = region;
        this.premium = premium;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        this.flaggedForReview = flaggedForReview;
    }

    public PolicyFlaggedEvent flagForReview() {

        if (flaggedForReview) {

            throw new PolicyAlreadyFlaggedException(
                    "Policy already flagged"
            );
        }

        if (status == PolicyStatus.EXPIRED) {

            throw new InvalidPolicyStateException(
                    "Expired policies cannot be flagged"
            );
        }

        flaggedForReview = true;

        return new PolicyFlaggedEvent(
                id,
                java.time.Instant.now()
        );
    }

    private void validateDates(
            LocalDate effectiveDate,
            LocalDate expirationDate
    ) {

        if (!expirationDate.isAfter(effectiveDate)) {

            throw new InvalidPolicyStateException(
                    "Expiration date must be after effective date"
            );
        }
    }

    public boolean isExpiringWithin(int days) {

        return expirationDate.isBefore(
                LocalDate.now().plusDays(days)
        );
    }

    public PolicyId getId() {
        return id;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public boolean isFlaggedForReview() {
        return flaggedForReview;
    }
}
