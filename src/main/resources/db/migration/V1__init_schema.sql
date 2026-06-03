CREATE TABLE policies
(
    id UUID PRIMARY KEY,

    policy_number VARCHAR(50) NOT NULL,
    policyholder_name VARCHAR(255) NOT NULL,

    underwriter VARCHAR(255) NOT NULL,

    line_of_business VARCHAR(30) NOT NULL,

    status VARCHAR(30) NOT NULL,

    region VARCHAR(100) NOT NULL,

    currency_code VARCHAR(10) NOT NULL,

    premium_amount NUMERIC(19,2) NOT NULL,

    effective_date DATE NOT NULL,

    expiration_date DATE NOT NULL,

    flagged_for_review BOOLEAN NOT NULL DEFAULT FALSE,

    version BIGINT NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(100) NOT NULL,

    updated_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100) NOT NULL
);