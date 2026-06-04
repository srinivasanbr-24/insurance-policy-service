package com.chubb.insurance.application.service;

import com.chubb.insurance.application.exception.PolicyNotFoundException;
import com.chubb.insurance.application.ports.in.FlagPoliciesUseCase;
import com.chubb.insurance.application.ports.out.PolicyEventPublisher;
import com.chubb.insurance.application.ports.out.PolicyRepository;
import com.chubb.insurance.domain.event.PolicyFlaggedEvent;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;

import java.util.Set;
import java.util.UUID;

public class FlagPoliciesService
        implements FlagPoliciesUseCase {

    private final PolicyRepository repository;
    private final PolicyEventPublisher eventPublisher;

    public FlagPoliciesService(
            PolicyRepository repository,
            PolicyEventPublisher eventPublisher
    ) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void flagPolicies(Set<UUID> policyIds) {

        for (UUID id : policyIds) {

            Policy policy =
                    repository.findById(
                                    new PolicyId(id)
                            )
                            .orElseThrow(() ->
                            new PolicyNotFoundException(
                                    "Policy not found"));


            PolicyFlaggedEvent event =
                    policy.flagForReview();

            repository.save(policy);

            eventPublisher.publish(event);
        }
    }
}