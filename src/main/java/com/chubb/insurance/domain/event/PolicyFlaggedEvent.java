package com.chubb.insurance.domain.event;


import com.chubb.insurance.domain.model.PolicyId;

import java.time.Instant;

public record PolicyFlaggedEvent(
        PolicyId policyId,
        Instant occurredAt
) {
}
