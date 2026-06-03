package com.chubb.insurance.application.ports.in;


import com.chubb.insurance.application.model.query.PolicySearchQuery;
import com.chubb.insurance.application.model.result.PolicySearchResult;

public interface SearchPoliciesUseCase {

    PolicySearchResult search(
            PolicySearchQuery query
    );
}
