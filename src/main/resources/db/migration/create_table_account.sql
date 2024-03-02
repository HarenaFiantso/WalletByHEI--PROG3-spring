CREATE TABLE IF NOT EXISTS account
(
    account_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    account_name VARCHAR(255) NOT NULL,
    account_type account_type NOT NULL,
    currency_id  UUID         NOT NULL,
    FOREIGN KEY (currency_id) REFERENCES currency (currency_id)
);