package com.chubb.insurance.infrastructure.adapters.out.persistence.mapper;

import com.chubb.insurance.domain.model.*;
import com.chubb.insurance.infrastructure.adapters.out.persistence.entity.PolicyJpaEntity;
import com.chubb.insurance.support.TestPolicyFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyPersistenceMapperTest {

    private final PolicyPersistenceMapper mapper =
            new PolicyPersistenceMapper();

    @Test
    void shouldMapJpaEntityToDomain() {

        PolicyJpaEntity entity =
                new PolicyJpaEntity();

        entity.setId(UUID.randomUUID());
        entity.setPolicyNumber("POL-1001");
        entity.setPolicyholderName("John Tan");
        entity.setUnderwriter("Chubb APAC");
        entity.setLineOfBusiness(LineOfBusiness.PROPERTY);
        entity.setStatus(PolicyStatus.ACTIVE);
        entity.setRegion("Singapore");
        entity.setCurrencyCode("SGD");
        entity.setPremiumAmount(BigDecimal.valueOf(10000));
        entity.setEffectiveDate(LocalDate.now());
        entity.setExpirationDate(LocalDate.now().plusYears(1));
        entity.setFlaggedForReview(false);

        Policy policy =
                mapper.toDomain(entity);

        assertThat(policy.getPolicyNumber().value())
                .isEqualTo("POL-1001");

        assertThat(policy.getStatus())
                .isEqualTo(PolicyStatus.ACTIVE);
    }

    @Test
    void shouldMapDomainToJpaEntity() {

        Policy policy =
                TestPolicyFactory.createPolicy();

        PolicyJpaEntity entity =
                mapper.toJpa(policy);

        assertThat(entity.getPolicyNumber())
                .isEqualTo(
                        policy.getPolicyNumber().value()
                );

        assertThat(entity.getStatus().name())
                .isEqualTo("ACTIVE");
    }
}