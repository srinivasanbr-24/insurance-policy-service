package com.chubb.insurance.application.ports.in;


import com.chubb.insurance.application.model.result.PolicySummaryResult;

public interface GetPolicySummaryUseCase {

    PolicySummaryResult getSummary();
}
