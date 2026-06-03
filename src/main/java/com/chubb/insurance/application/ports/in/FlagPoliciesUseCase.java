package com.chubb.insurance.application.ports.in;


import java.util.Set;
import java.util.UUID;

public interface FlagPoliciesUseCase {

    void flagPolicies(
            Set<UUID> policyIds
    );
}
