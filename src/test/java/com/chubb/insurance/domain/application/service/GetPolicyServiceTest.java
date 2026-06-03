package com.chubb.insurance.domain.application.service;

import com.chubb.insurance.application.ports.out.PolicyRepository;
import com.chubb.insurance.application.service.GetPolicyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class GetPolicyServiceTest {

    @Mock
    private PolicyRepository repository;

    @InjectMocks
    private GetPolicyService service;

    @Test
    void shouldReturnPolicy() {

    }
}
