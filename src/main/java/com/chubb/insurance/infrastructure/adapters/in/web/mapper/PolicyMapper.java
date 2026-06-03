package com.chubb.insurance.infrastructure.adapters.in.web.mapper;

import com.chubb.insurance.application.model.result.PolicySearchResult;
import com.chubb.insurance.application.model.result.PolicySummaryResult;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.generated.model.CurrencyCode;
import com.chubb.insurance.generated.model.LineOfBusiness;
import com.chubb.insurance.generated.model.PolicyPageResponse;
import com.chubb.insurance.generated.model.PolicyResponse;
import com.chubb.insurance.generated.model.PolicyStatus;
import com.chubb.insurance.generated.model.PolicySummaryResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PolicyMapper {

    public PolicyResponse toResponse(Policy policy) {

        PolicyResponse response = new PolicyResponse();

        response.setId(
                policy.getId().value()
        );

        response.setPolicyNumber(
                policy.getPolicyNumber().value()
        );

        response.setPolicyholderName(
                policy.getPolicyholderName()
        );

        response.setUnderwriter(
                policy.getUnderwriter()
        );

        response.setRegion(
                policy.getRegion()
        );

        response.setLineOfBusiness(
                LineOfBusiness.valueOf(
                        policy.getLineOfBusiness().name()
                )
        );

        response.setStatus(
                PolicyStatus.valueOf(
                        policy.getStatus().name()
                )
        );

        response.setCurrency(
                CurrencyCode.valueOf(
                        policy.getPremium()
                                .currency()
                                .name()
                )
        );

        response.setPremiumAmount(
                policy.getPremium()
                        .amount()
                        .doubleValue()
        );

        response.setEffectiveDate(
                policy.getEffectiveDate()
        );

        response.setExpirationDate(
                policy.getExpirationDate()
        );

        response.setFlaggedForReview(
                policy.isFlaggedForReview()
        );

        return response;
    }

    public PolicyPageResponse toPageResponse(
            PolicySearchResult result
    ) {

        PolicyPageResponse response =
                new PolicyPageResponse();

        response.setContent(
                result.content()
                        .stream()
                        .map(this::toResponse)
                        .toList()
        );

        response.setPage(
                result.page()
        );

        response.setSize(
                result.size()
        );

        response.setTotalElements(
                Math.toIntExact(
                        result.totalElements()
                )
        );

        response.setTotalPages(
                result.totalPages()
        );

        return response;
    }

    public PolicySummaryResponse toSummaryResponse(
            PolicySummaryResult summary
    ) {

        PolicySummaryResponse response =
                new PolicySummaryResponse();

        response.setStatusCounts(
                summary.statusCounts()
                        .entrySet()
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        e -> e.getValue().intValue()
                                )
                        )
        );

        response.setPremiumByLineOfBusiness(
                summary.premiumByLineOfBusiness()
                        .entrySet()
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        e -> BigDecimal.valueOf(e.getValue().doubleValue())
                                )
                        )
        );

        response.setExpiringSoonCount(
                Math.toIntExact(
                        summary.expiringSoonCount()
                )
        );

        return response;
    }
}