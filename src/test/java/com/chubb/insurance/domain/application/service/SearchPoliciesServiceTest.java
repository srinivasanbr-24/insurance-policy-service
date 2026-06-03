package com.chubb.insurance.domain.application.service;

import com.chubb.insurance.application.ports.out.PolicyRepository;
import com.chubb.insurance.application.service.SearchPoliciesService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class SearchPoliciesServiceTest {

    @Mock
    PolicyRepository repository;

    @InjectMocks
    SearchPoliciesService service;

    @Test
    public void shouldReturnPagedResult() {

    }
}