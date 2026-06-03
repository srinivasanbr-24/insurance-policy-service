package com.chubb.insurance.application.model.result;


import java.math.BigDecimal;
import java.util.Map;

public record PolicySummaryResult(

        Map<String, Long> statusCounts,

        Map<String, BigDecimal> premiumByLineOfBusiness,

        long expiringSoonCount

) {
}
