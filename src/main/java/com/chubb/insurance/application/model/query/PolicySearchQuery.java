package com.chubb.insurance.application.model.query;

import com.chubb.insurance.domain.model.LineOfBusiness;
import com.chubb.insurance.domain.model.PolicyStatus;

import java.time.LocalDate;

public record PolicySearchQuery(

        int page,
        int size,

        String sortBy,
        String sortDirection,

        String search,

        PolicyStatus status,

        LineOfBusiness lineOfBusiness,

        String region,

        LocalDate effectiveDateFrom,
        LocalDate effectiveDateTo

) {
}