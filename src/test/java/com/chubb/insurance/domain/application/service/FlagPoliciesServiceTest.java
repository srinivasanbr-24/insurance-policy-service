package com.chubb.insurance.domain.application.service;

import com.chubb.insurance.application.exception.PolicyNotFoundException;
import com.chubb.insurance.application.ports.out.PolicyEventPublisher;
import com.chubb.insurance.application.ports.out.PolicyRepository;
import com.chubb.insurance.application.service.FlagPoliciesService;
import com.chubb.insurance.domain.event.PolicyFlaggedEvent;
import com.chubb.insurance.domain.exception.InvalidPolicyStateException;
import com.chubb.insurance.domain.exception.PolicyAlreadyFlaggedException;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;
import com.chubb.insurance.support.TestPolicyFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlagPoliciesServiceTest {

    @Mock
    private PolicyRepository repository;

    @Mock
    private PolicyEventPublisher eventPublisher;

    @InjectMocks
    private FlagPoliciesService service;

    @Test
    void shouldFlagPolicyAndPublishEvent() {

        UUID id = UUID.randomUUID();

        Policy policy =
                TestPolicyFactory.activePolicy(id);

        when(repository.findById(new PolicyId(id)))
                .thenReturn(Optional.of(policy));

        service.flagPolicies(Set.of(id));

        verify(repository).save(policy);

        verify(eventPublisher)
                .publish(any(PolicyFlaggedEvent.class));
    }


    @Test
    void shouldFlagMultiplePolicies() {

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Policy policy1 =
                TestPolicyFactory.activePolicy(id1);

        Policy policy2 =
                TestPolicyFactory.activePolicy(id2);

        when(repository.findById(new PolicyId(id1)))
                .thenReturn(Optional.of(policy1));

        when(repository.findById(new PolicyId(id2)))
                .thenReturn(Optional.of(policy2));

        service.flagPolicies(Set.of(id1, id2));

        verify(repository, times(2))
                .save(any());

        verify(eventPublisher, times(2))
                .publish(any());
    }


    @Test
    void shouldThrowWhenPolicyNotFound() {

        UUID id = UUID.randomUUID();

        when(repository.findById(new PolicyId(id)))
                .thenReturn(Optional.empty());

        assertThrows(
                PolicyNotFoundException.class,
                () -> service.flagPolicies(Set.of(id))
        );
    }


    @Test
    void shouldThrowWhenPolicyAlreadyFlagged() {

        UUID id = UUID.randomUUID();

        Policy policy =
                TestPolicyFactory.flaggedPolicy(id);

        when(repository.findById(new PolicyId(id)))
                .thenReturn(Optional.of(policy));

        assertThrows(
                PolicyAlreadyFlaggedException.class,
                () -> service.flagPolicies(Set.of(id))
        );

        verify(eventPublisher, never())
                .publish(any());
    }


    @Test
    void shouldThrowWhenPolicyExpired() {

        UUID id = UUID.randomUUID();

        Policy policy =
                TestPolicyFactory.expiredPolicy(id);

        when(repository.findById(new PolicyId(id)))
                .thenReturn(Optional.of(policy));

        assertThrows(
                InvalidPolicyStateException.class,
                () -> service.flagPolicies(Set.of(id))
        );

        verify(eventPublisher, never())
                .publish(any());
    }






}
