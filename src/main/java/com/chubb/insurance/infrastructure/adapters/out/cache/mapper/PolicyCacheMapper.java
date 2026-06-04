package com.chubb.insurance.infrastructure.adapters.out.cache.mapper;

import com.chubb.insurance.domain.model.*;
import com.chubb.insurance.infrastructure.adapters.out.cache.model.CachedPolicy;
import org.springframework.stereotype.Component;

@Component
public class PolicyCacheMapper {

    public CachedPolicy toCache(
            Policy policy
    ) {

        return new CachedPolicy(
                policy.getId().value(),
                policy.getPolicyNumber().value(),
                policy.getPolicyholderName(),
                policy.getUnderwriter(),
                policy.getLineOfBusiness().name(),
                policy.getStatus().name(),
                policy.getRegion(),
                policy.getPremium().amount(),
                policy.getPremium().currency().name(),
                policy.getEffectiveDate(),
                policy.getExpirationDate(),
                policy.isFlaggedForReview()
        );
    }

    public Policy toDomain(
            CachedPolicy cached
    ) {

        return Policy.restore(
                new PolicyId(cached.id()),
                new PolicyNumber(cached.policyNumber()),
                cached.policyholderName(),
                cached.underwriter(),
                LineOfBusiness.valueOf(cached.lineOfBusiness()),
                PolicyStatus.valueOf(cached.status()),
                cached.region(),
                new Money(
                        cached.premiumAmount(),
                        CurrencyCode.valueOf(
                                cached.currency()
                        )
                ),
                cached.effectiveDate(),
                cached.expirationDate(),
                cached.flaggedForReview()
        );
    }
}