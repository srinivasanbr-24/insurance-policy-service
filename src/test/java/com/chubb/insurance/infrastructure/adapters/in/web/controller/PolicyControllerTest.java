package com.chubb.insurance.infrastructure.adapters.in.web.controller;

import com.chubb.insurance.application.ports.in.FlagPoliciesUseCase;
import com.chubb.insurance.application.ports.in.GetPolicySummaryUseCase;
import com.chubb.insurance.application.ports.in.GetPolicyUseCase;
import com.chubb.insurance.application.ports.in.SearchPoliciesUseCase;
import com.chubb.insurance.infrastructure.adapters.in.web.mapper.PolicyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PolicyController.class)
class PolicyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetPolicyUseCase getPolicyUseCase;

    @MockitoBean
    private SearchPoliciesUseCase searchPoliciesUseCase;

    @MockitoBean
    private FlagPoliciesUseCase flagPoliciesUseCase;

    @MockitoBean
    private GetPolicySummaryUseCase getPolicySummaryUseCase;

    @MockitoBean
    private PolicyMapper mapper;
}
