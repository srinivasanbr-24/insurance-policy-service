package com.chubb.insurance.infrastructure.adapters.out.persistence.adapter;

import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;
import com.chubb.insurance.infrastructure.adapters.out.persistence.entity.PolicyJpaEntity;
import com.chubb.insurance.infrastructure.adapters.out.persistence.mapper.PolicyPersistenceMapper;
import com.chubb.insurance.infrastructure.adapters.out.persistence.repository.SpringDataPolicyRepository;
import com.chubb.insurance.support.TestPolicyFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PolicyRepositoryAdapterTest {

    @Mock
    private SpringDataPolicyRepository repository;

    @Mock
    private PolicyPersistenceMapper mapper;

    @InjectMocks
    private PolicyRepositoryAdapter adapter;

    @Test
    void shouldFindPolicyById() {

        UUID id = UUID.randomUUID();

        PolicyJpaEntity entity =
                new PolicyJpaEntity();

        Policy policy =
                TestPolicyFactory.createPolicy();

        when(repository.findById(id))
                .thenReturn(Optional.of(entity));

        when(mapper.toDomain(entity))
                .thenReturn(policy);

        Optional<Policy> result =
                adapter.findById(
                        new PolicyId(id)
                );

        assertThat(result)
                .isPresent();
    }

    @Test
    void shouldSavePolicy() {

        Policy policy =
                TestPolicyFactory.createPolicy();

        PolicyJpaEntity entity =
                new PolicyJpaEntity();

        when(mapper.toJpa(policy))
                .thenReturn(entity);

        adapter.save(policy);

        verify(repository)
                .save(entity);
    }
}