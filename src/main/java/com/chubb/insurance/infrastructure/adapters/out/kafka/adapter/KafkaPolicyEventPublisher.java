package com.chubb.insurance.infrastructure.adapters.out.kafka.adapter;

import com.chubb.insurance.application.ports.out.PolicyEventPublisher;
import com.chubb.insurance.domain.event.PolicyFlaggedEvent;
import com.chubb.insurance.infrastructure.adapters.out.kafka.message.PolicyFlaggedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaPolicyEventPublisher
        implements PolicyEventPublisher {

    private static final String TOPIC =
            "policy.flagged";

    private final KafkaTemplate<
            String,
            PolicyFlaggedMessage> kafkaTemplate;

    @Override
    public void publish(
            PolicyFlaggedEvent event
    ) {

        PolicyFlaggedMessage message =
                new PolicyFlaggedMessage(
                        event.policyId().value(),
                        event.occurredAt()
                );

        kafkaTemplate.send(
                TOPIC,
                event.policyId()
                        .value()
                        .toString(),
                message
        );
    }
}