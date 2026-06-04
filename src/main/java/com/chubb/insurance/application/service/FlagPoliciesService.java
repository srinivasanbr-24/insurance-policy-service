package com.chubb.insurance.application.service;

import com.chubb.insurance.application.exception.PolicyNotFoundException;
import com.chubb.insurance.application.ports.in.FlagPoliciesUseCase;
import com.chubb.insurance.application.ports.out.PolicyCache;
import com.chubb.insurance.application.ports.out.PolicyEventPublisher;
import com.chubb.insurance.application.ports.out.PolicyRepository;
import com.chubb.insurance.domain.event.PolicyFlaggedEvent;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;


@RequiredArgsConstructor
public class FlagPoliciesService
        implements FlagPoliciesUseCase {

    private final PolicyRepository repository;

    private final PolicyEventPublisher eventPublisher;

    private final PolicyCache cache;

    @Override
    public void flagPolicies(
            Set<UUID> policyIds
    ) {

        for (UUID policyId : policyIds) {

            Policy policy =
                    repository.findById(
                                    new PolicyId(policyId)
                            )
                            .orElseThrow(
                                    () -> new PolicyNotFoundException(
                                            "Policy not found"
                                    )
                            );

            PolicyFlaggedEvent event =
                    policy.flagForReview();

            repository.save(policy);

            cache.evict(
                    policy.getId()
            );

            eventPublisher.publish(event);
        }
    }
}