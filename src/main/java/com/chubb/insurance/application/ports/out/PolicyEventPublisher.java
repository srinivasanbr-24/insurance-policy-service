package com.chubb.insurance.application.ports.out;


import com.chubb.insurance.domain.event.PolicyFlaggedEvent;

public interface PolicyEventPublisher {

    void publish(
            PolicyFlaggedEvent event
    );
}
