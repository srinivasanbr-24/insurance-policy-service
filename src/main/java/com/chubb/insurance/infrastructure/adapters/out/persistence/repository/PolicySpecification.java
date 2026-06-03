package com.chubb.insurance.infrastructure.adapters.out.persistence.repository;

import com.chubb.insurance.domain.model.PolicyStatus;
import com.chubb.insurance.infrastructure.adapters.out.persistence.entity.PolicyJpaEntity;
import org.springframework.data.jpa.domain.Specification;

public final class PolicySpecification {

    private PolicySpecification() {
    }

    public static Specification<PolicyJpaEntity> statusEquals(
            PolicyStatus status
    ) {

        return (root, query, cb) ->
                status == null
                        ? null
                        : cb.equal(root.get("status"), status);
    }
}
