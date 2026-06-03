package com.chubb.insurance.application.service;

import com.chubb.insurance.application.model.query.PolicySearchQuery;
import com.chubb.insurance.application.model.result.PolicySearchResult;
import com.chubb.insurance.application.ports.in.SearchPoliciesUseCase;
import com.chubb.insurance.application.ports.out.PolicyRepository;

public class SearchPoliciesService
        implements SearchPoliciesUseCase {

    private final PolicyRepository repository;

    public SearchPoliciesService(
            PolicyRepository repository) {

        this.repository = repository;
    }

    @Override
    public PolicySearchResult search(
            PolicySearchQuery query) {

        return repository.search(query);
    }
}