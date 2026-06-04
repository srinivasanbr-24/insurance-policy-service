package com.chubb.insurance.domain.application.service;

import com.chubb.insurance.application.exception.PolicyNotFoundException;
import com.chubb.insurance.application.ports.out.PolicyCache;
import com.chubb.insurance.application.ports.out.PolicyRepository;
import com.chubb.insurance.application.service.GetPolicyService;
import com.chubb.insurance.domain.model.Policy;

import com.chubb.insurance.support.TestPolicyFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPolicyServiceTest {

    @Mock
    private PolicyRepository repository;

    @Mock
    private PolicyCache cache;

    @InjectMocks
    private GetPolicyService service;

    @Test
    void shouldReturnPolicyFromRepositoryAndCacheIt() {

        Policy policy =
                TestPolicyFactory.createPolicy();

        when(cache.findById(policy.getId()))
                .thenReturn(Optional.empty());

        when(repository.findById(policy.getId()))
                .thenReturn(Optional.of(policy));

        Policy result =
                service.getPolicy(policy.getId());

        assertThat(result)
                .isEqualTo(policy);

        verify(repository)
                .findById(policy.getId());

        verify(cache)
                .put(policy);
    }

    @Test
    void shouldReturnPolicyFromCache() {

        Policy policy =
                TestPolicyFactory.createPolicy();

        when(cache.findById(policy.getId()))
                .thenReturn(Optional.of(policy));

        Policy result =
                service.getPolicy(policy.getId());

        assertThat(result)
                .isEqualTo(policy);

        verify(repository, never())
                .findById(any());

        verify(cache, never())
                .put(any());
    }

    @Test
    void shouldThrowWhenPolicyNotFound() {

        Policy policy =
                TestPolicyFactory.createPolicy();

        when(cache.findById(policy.getId()))
                .thenReturn(Optional.empty());

        when(repository.findById(policy.getId()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(
                () -> service.getPolicy(policy.getId())
        )
                .isInstanceOf(PolicyNotFoundException.class);
    }
}