package com.chubb.insurance.application.service;

import com.chubb.insurance.application.exception.PolicyNotFoundException;
import com.chubb.insurance.application.ports.in.GetPolicyUseCase;
import com.chubb.insurance.application.ports.out.PolicyCache;
import com.chubb.insurance.application.ports.out.PolicyRepository;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetPolicyService
        implements GetPolicyUseCase {

    private final PolicyRepository repository;

    private final PolicyCache cache;

    @Override
    public Policy getPolicy(
            PolicyId id
    ) {

        return cache.findById(id)
                .orElseGet(() -> {

                    Policy policy =
                            repository.findById(id)
                                    .orElseThrow(
                                            () -> new PolicyNotFoundException(
                                                    "Policy not found"
                                            )
                                    );

                    cache.put(policy);

                    return policy;
                });
    }
}