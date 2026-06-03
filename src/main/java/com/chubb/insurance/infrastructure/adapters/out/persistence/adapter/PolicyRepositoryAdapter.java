package com.chubb.insurance.infrastructure.adapters.out.persistence.adapter;

import com.chubb.insurance.application.model.query.PolicySearchQuery;
import com.chubb.insurance.application.model.result.PolicySearchResult;
import com.chubb.insurance.application.model.result.PolicySummaryProjection;
import com.chubb.insurance.application.ports.out.PolicyRepository;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PolicyRepositoryAdapter
        implements PolicyRepository {

    @Override
    public Optional<Policy> findById(PolicyId id) {
        return Optional.empty();
    }

    @Override
    public PolicySearchResult search(PolicySearchQuery query) {
        return null;
    }

    @Override
    public PolicySummaryProjection getSummary() {
        return null;
    }

    @Override
    public void save(Policy policy) {

    }
}
