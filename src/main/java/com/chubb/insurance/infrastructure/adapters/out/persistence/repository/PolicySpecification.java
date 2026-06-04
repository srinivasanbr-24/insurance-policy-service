package com.chubb.insurance.infrastructure.adapters.out.persistence.repository;

import com.chubb.insurance.domain.model.PolicyStatus;
import com.chubb.insurance.infrastructure.adapters.out.persistence.entity.PolicyJpaEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public final class PolicySpecification {

    private PolicySpecification() {
    }

    public static Specification<PolicyJpaEntity> statusEquals(
            PolicyStatus status
    ) {

        return (root, query, cb) ->
                status == null
                        ? null
                        : cb.equal(
                        root.get("status"),
                        status.name()
                );
    }

    public static Specification<PolicyJpaEntity> lineOfBusinessEquals(
            String lineOfBusiness
    ) {

        return (root, query, cb) ->
                lineOfBusiness == null || lineOfBusiness.isBlank()
                        ? null
                        : cb.equal(
                        root.get("lineOfBusiness"),
                        lineOfBusiness
                );
    }

    public static Specification<PolicyJpaEntity> regionEquals(
            String region
    ) {

        return (root, query, cb) ->
                region == null || region.isBlank()
                        ? null
                        : cb.equal(
                        cb.lower(root.get("region")),
                        region.toLowerCase()
                );
    }

    public static Specification<PolicyJpaEntity> effectiveDateFrom(
            LocalDate effectiveDateFrom
    ) {

        return (root, query, cb) ->
                effectiveDateFrom == null
                        ? null
                        : cb.greaterThanOrEqualTo(
                        root.get("effectiveDate"),
                        effectiveDateFrom
                );
    }

    public static Specification<PolicyJpaEntity> effectiveDateTo(
            LocalDate effectiveDateTo
    ) {

        return (root, query, cb) ->
                effectiveDateTo == null
                        ? null
                        : cb.lessThanOrEqualTo(
                        root.get("effectiveDate"),
                        effectiveDateTo
                );
    }

    public static Specification<PolicyJpaEntity> searchText(
            String search
    ) {

        return (root, query, cb) -> {

            if (search == null || search.isBlank()) {
                return null;
            }

            String pattern =
                    "%" + search.toLowerCase() + "%";

            return cb.or(

                    cb.like(
                            cb.lower(root.get("policyNumber")),
                            pattern
                    ),

                    cb.like(
                            cb.lower(root.get("policyholderName")),
                            pattern
                    ),

                    cb.like(
                            cb.lower(root.get("underwriter")),
                            pattern
                    )
            );
        };
    }
}