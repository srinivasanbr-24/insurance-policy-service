package com.chubb.insurance.application.model.result;


import com.chubb.insurance.domain.model.Policy;

import java.util.List;

public record PolicySearchResult(

        List<Policy> content,

        int page,

        int size,

        long totalElements,

        int totalPages

) {
}
