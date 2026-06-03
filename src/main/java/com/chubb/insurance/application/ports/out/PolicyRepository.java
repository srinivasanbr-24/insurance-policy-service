package com.chubb.insurance.application.ports.out;

import com.chubb.insurance.application.model.query.PolicySearchQuery;
import com.chubb.insurance.application.model.result.PolicySearchResult;
import com.chubb.insurance.application.model.result.PolicySummaryProjection;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;

import java.util.Optional;

public interface PolicyRepository {

    Optional<Policy> findById(
            PolicyId id
    );

    PolicySearchResult search(
            PolicySearchQuery query
    );

    PolicySummaryProjection getSummary();

    void save(
            Policy policy
    );
}