CREATE INDEX idx_policy_number
ON policies(policy_number);

CREATE INDEX idx_policy_status
ON policies(status);

CREATE INDEX idx_policy_lob
ON policies(line_of_business);

CREATE INDEX idx_policy_region
ON policies(region);

CREATE INDEX idx_policy_effective_date
ON policies(effective_date);

CREATE INDEX idx_policy_expiration_date
ON policies(expiration_date);

CREATE INDEX idx_policy_status_lob_region
ON policies(status, line_of_business, region);