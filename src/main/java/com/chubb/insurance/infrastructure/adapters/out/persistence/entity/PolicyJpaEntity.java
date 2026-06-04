package com.chubb.insurance.infrastructure.adapters.out.persistence.entity;

import com.chubb.insurance.domain.model.LineOfBusiness;
import com.chubb.insurance.domain.model.PolicyStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "policies")
@Getter
@Setter
public class PolicyJpaEntity extends BaseAuditEntity {

    @Id
    private UUID id;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "policyholder_name")
    private String policyholderName;

    private String underwriter;

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;

    @Enumerated(EnumType.STRING)
    private LineOfBusiness lineOfBusiness;

    private String region;

    @Column(name = "premium_amount")
    private BigDecimal premiumAmount;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "flagged_for_review")
    private boolean flaggedForReview;

    @Version
    private Long version;
}