package com.chubb.insurance.infrastructure.adapters.out.kafka.mapper;

import com.chubb.insurance.domain.event.PolicyFlaggedEvent;
import com.chubb.insurance.infrastructure.adapters.out.kafka.message.PolicyFlaggedMessage;
import org.springframework.stereotype.Component;

@Component
public class PolicyEventMapper {

    public PolicyFlaggedMessage toMessage(
            PolicyFlaggedEvent event
    ) {

        return new PolicyFlaggedMessage(
                event.policyId().value(),
                event.occurredAt()
        );
    }
}
