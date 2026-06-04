package com.chubb.insurance.application.ports.out;

import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;

import java.util.Optional;

public interface PolicyCache {

    Optional<Policy> findById(PolicyId id);

    void put(Policy policy);

    void evict(PolicyId id);
}