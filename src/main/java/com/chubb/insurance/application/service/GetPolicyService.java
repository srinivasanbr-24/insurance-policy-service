package com.chubb.insurance.application.service;


import com.chubb.insurance.application.exception.PolicyNotFoundException;
import com.chubb.insurance.application.ports.in.GetPolicyUseCase;
import com.chubb.insurance.application.ports.out.PolicyRepository;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;

public class GetPolicyService
        implements GetPolicyUseCase {

    private final PolicyRepository repository;

    public GetPolicyService(
            PolicyRepository repository) {

        this.repository = repository;
    }

    @Override
    public Policy getPolicy(
            PolicyId policyId) {

        return repository
                .findById(policyId)
                .orElseThrow(() ->
                        new PolicyNotFoundException(
                                "Policy not found"));
    }
}
