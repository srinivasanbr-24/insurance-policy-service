package com.chubb.insurance.infrastructure.adapters.in.web.mapper;

import com.chubb.insurance.application.model.result.PolicySummaryResult;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyStatus;
import com.chubb.insurance.generated.model.PolicyResponse;
import com.chubb.insurance.generated.model.PolicySummaryResponse;
import com.chubb.insurance.infrastructure.adapters.in.web.mapper.PolicyMapper;
import com.chubb.insurance.support.TestPolicyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class PolicyMapperTest {

    private PolicyMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new PolicyMapper();
    }

    @org.junit.jupiter.api.Test
    void shouldMapPolicyToResponse() {

        Policy policy =
                TestPolicyFactory.createPolicy();

        PolicyResponse response =
                mapper.toResponse(policy);

        assertThat(response.getPolicyNumber())
                .isEqualTo("POL-1001");

        assertThat(response.getPolicyholderName())
                .isEqualTo("John Tan");

        assertThat(response.getStatus().name())
                .isEqualTo(
                       "ACTIVE"
                );
    }

    @Test
    void shouldMapSummaryResponse() {

        PolicySummaryResult summary =
                new PolicySummaryResult(
                        Map.of("ACTIVE", 10L),
                        Map.of(
                                "PROPERTY",
                                BigDecimal.valueOf(10000)
                        ),
                        5
                );

        PolicySummaryResponse response =
                mapper.toSummaryResponse(summary);

        assertThat(
                response.getStatusCounts()
                        .get("ACTIVE")
        ).isEqualTo(10);

        assertThat(
                response.getExpiringSoonCount()
        ).isEqualTo(5);
    }
}