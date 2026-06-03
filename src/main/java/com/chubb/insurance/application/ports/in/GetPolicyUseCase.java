package com.chubb.insurance.application.ports.in;


import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;

public interface GetPolicyUseCase {

    Policy getPolicy(PolicyId policyId);

}
