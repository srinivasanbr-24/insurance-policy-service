package com.chubb.insurance.application.service;

import com.chubb.insurance.application.model.result.PolicySummaryProjection;
import com.chubb.insurance.application.model.result.PolicySummaryResult;
import com.chubb.insurance.application.ports.in.GetPolicySummaryUseCase;
import com.chubb.insurance.application.ports.out.PolicyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetPolicySummaryService
        implements GetPolicySummaryUseCase {

    private final PolicyRepository repository;

    @Override
    public PolicySummaryResult getSummary() {

        PolicySummaryProjection projection =
                repository.getSummary();

        return new PolicySummaryResult(
                projection.statusCounts(),
                projection.premiumByLineOfBusiness(),
                projection.expiringSoonCount()
        );
    }
}