package com.chubb.insurance.infrastructure.adapters.in.web.controller;

import com.chubb.insurance.application.model.query.PolicySearchQuery;
import com.chubb.insurance.application.model.result.PolicySearchResult;
import com.chubb.insurance.application.model.result.PolicySummaryResult;
import com.chubb.insurance.application.ports.in.FlagPoliciesUseCase;
import com.chubb.insurance.application.ports.in.GetPolicySummaryUseCase;
import com.chubb.insurance.application.ports.in.GetPolicyUseCase;
import com.chubb.insurance.application.ports.in.SearchPoliciesUseCase;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;
import com.chubb.insurance.generated.api.PoliciesApi;
import com.chubb.insurance.generated.model.*;
import com.chubb.insurance.infrastructure.adapters.in.web.mapper.PolicyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PolicyController implements PoliciesApi {

    private final GetPolicyUseCase getPolicyUseCase;
    private final SearchPoliciesUseCase searchPoliciesUseCase;
    private final FlagPoliciesUseCase flagPoliciesUseCase;
    private final GetPolicySummaryUseCase getPolicySummaryUseCase;
    private final PolicyMapper mapper;

    @Override
    public ResponseEntity<PolicyResponse> getPolicyById(UUID id) {

        Policy policy =
                getPolicyUseCase.getPolicy(
                        new PolicyId(id));

        return ResponseEntity.ok(
                mapper.toResponse(policy));
    }

    @Override
    public ResponseEntity<PolicyPageResponse> searchPolicies(
            Integer page,
            Integer size,
            String sort,
            PolicyStatus status,
            LineOfBusiness lineOfBusiness,
            String region,
            LocalDate effectiveDateFrom,
            LocalDate effectiveDateTo,
            String search) {

        String sortBy = "premiumAmount";
        String sortDirection = "desc";

        if (sort != null && sort.contains(",")) {
            String[] parts = sort.split(",");
            sortBy = parts[0];
            sortDirection = parts[1];
        }

        PolicySearchQuery query =
                new PolicySearchQuery(
                        page,
                        size,
                        sortBy,
                        sortDirection,
                        search,
                        status != null
                                ? com.chubb.insurance.domain.model.PolicyStatus.valueOf(status.name())
                                : null,
                        lineOfBusiness != null
                                ? com.chubb.insurance.domain.model.LineOfBusiness.valueOf(lineOfBusiness.name())
                                : null,
                        region,
                        effectiveDateFrom,
                        effectiveDateTo
                );

        PolicySearchResult result =
                searchPoliciesUseCase.search(query);

        return ResponseEntity.ok(
                mapper.toPageResponse(result)
        );
    }


    @Override
    public ResponseEntity<Void> flagPolicies(
            BulkFlagRequest request) {

        flagPoliciesUseCase.flagPolicies(
                new HashSet<>(request.getPolicyIds()));

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PolicySummaryResponse> getPolicySummary() {

        PolicySummaryResult summary =
                getPolicySummaryUseCase.getSummary();

        return ResponseEntity.ok(
                mapper.toSummaryResponse(summary));
    }
}