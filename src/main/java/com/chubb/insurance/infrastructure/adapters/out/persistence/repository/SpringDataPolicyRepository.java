package com.chubb.insurance.infrastructure.adapters.out.persistence.repository;


import com.chubb.insurance.infrastructure.adapters.out.persistence.entity.PolicyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SpringDataPolicyRepository
        extends JpaRepository<PolicyJpaEntity, UUID>,
        JpaSpecificationExecutor<PolicyJpaEntity> {
}
