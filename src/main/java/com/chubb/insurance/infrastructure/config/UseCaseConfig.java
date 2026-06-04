package com.chubb.insurance.infrastructure.config;

import com.chubb.insurance.application.ports.in.FlagPoliciesUseCase;
import com.chubb.insurance.application.ports.in.GetPolicySummaryUseCase;
import com.chubb.insurance.application.ports.in.GetPolicyUseCase;
import com.chubb.insurance.application.ports.in.SearchPoliciesUseCase;
import com.chubb.insurance.application.ports.out.PolicyCache;
import com.chubb.insurance.application.ports.out.PolicyEventPublisher;
import com.chubb.insurance.application.ports.out.PolicyRepository;
import com.chubb.insurance.application.service.FlagPoliciesService;
import com.chubb.insurance.application.service.GetPolicyService;
import com.chubb.insurance.application.service.GetPolicySummaryService;
import com.chubb.insurance.application.service.SearchPoliciesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    GetPolicyUseCase getPolicyUseCase(
            PolicyRepository repository,
            PolicyCache cache
    ) {
        return new GetPolicyService(
                repository,
                cache
        );
    }

    @Bean
    SearchPoliciesUseCase searchPoliciesUseCase(
            PolicyRepository repository
    ) {
        return new SearchPoliciesService(
                repository
        );
    }

    @Bean
    GetPolicySummaryUseCase getPolicySummaryUseCase(
            PolicyRepository repository
    ) {
        return new GetPolicySummaryService(
                repository
        );
    }

    @Bean
    FlagPoliciesUseCase flagPoliciesUseCase(
            PolicyRepository repository,
            PolicyEventPublisher publisher,
            PolicyCache cache
    ) {
        return new FlagPoliciesService(
                repository,
                publisher,
                cache
        );
    }
}
