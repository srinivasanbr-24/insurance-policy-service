package com.chubb.insurance.infrastructure.adapters.out.kafka.message;

import java.time.Instant;
import java.util.UUID;

public record PolicyFlaggedMessage(
        UUID policyId,
        Instant occurredAt
) {
}
