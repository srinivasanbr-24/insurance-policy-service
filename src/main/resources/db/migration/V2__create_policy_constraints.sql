ALTER TABLE policies
ADD CONSTRAINT uk_policy_number
UNIQUE (policy_number);

ALTER TABLE policies
ADD CONSTRAINT chk_policy_status
CHECK (
    status IN (
        'ACTIVE',
        'EXPIRED',
        'PENDING',
        'CANCELLED'
    )
);

ALTER TABLE policies
ADD CONSTRAINT chk_lob
CHECK (
    line_of_business IN (
        'PROPERTY',
        'CASUALTY',
        'AH',
        'MARINE'
    )
);

ALTER TABLE policies
ADD CONSTRAINT chk_dates
CHECK (
    expiration_date > effective_date
);