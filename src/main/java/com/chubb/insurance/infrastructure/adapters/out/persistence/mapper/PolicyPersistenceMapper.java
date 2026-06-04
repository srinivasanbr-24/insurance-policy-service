package com.chubb.insurance.infrastructure.adapters.out.persistence.mapper;

import com.chubb.insurance.domain.model.*;
import com.chubb.insurance.infrastructure.adapters.out.persistence.entity.PolicyJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class PolicyPersistenceMapper {

    public Policy toDomain(
            PolicyJpaEntity entity
    ) {

        return Policy.restore(
                new PolicyId(entity.getId()),
                new PolicyNumber(entity.getPolicyNumber()),
                entity.getPolicyholderName(),
                entity.getUnderwriter(),
                LineOfBusiness.valueOf(String.valueOf(entity.getLineOfBusiness())),
                PolicyStatus.valueOf(String.valueOf(entity.getStatus())),
                entity.getRegion(),
                new Money(
                        entity.getPremiumAmount(),
                        CurrencyCode.valueOf(entity.getCurrencyCode())
                ),
                entity.getEffectiveDate(),
                entity.getExpirationDate(),
                entity.isFlaggedForReview()
        );
    }

    public PolicyJpaEntity toJpa(
            Policy policy
    ) {

        PolicyJpaEntity entity =
                new PolicyJpaEntity();

        entity.setId(
                policy.getId().value()
        );

        entity.setPolicyNumber(
                policy.getPolicyNumber().value()
        );

        entity.setPolicyholderName(
                policy.getPolicyholderName()
        );

        entity.setUnderwriter(
                policy.getUnderwriter()
        );

        entity.setLineOfBusiness(
                LineOfBusiness.valueOf(policy.getLineOfBusiness().name())
        );

        entity.setStatus(
                PolicyStatus.valueOf(policy.getStatus().name())
        );

        entity.setRegion(
                policy.getRegion()
        );

        entity.setPremiumAmount(
                policy.getPremium().amount()
        );

        entity.setCurrencyCode(
                String.valueOf(com.chubb.insurance.generated.model.CurrencyCode.valueOf(
                        policy.getPremium()
                                .currency()
                                .name()
                ))
        );

        entity.setEffectiveDate(
                policy.getEffectiveDate()
        );

        entity.setExpirationDate(
                policy.getExpirationDate()
        );

        entity.setFlaggedForReview(
                policy.isFlaggedForReview()
        );

        return entity;
    }
}