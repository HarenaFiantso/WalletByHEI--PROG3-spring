CREATE TABLE IF NOT EXISTS transaction
(
    transaction_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    transaction_date TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    transaction_type transaction_type NOT NULL,
    amount           DOUBLE PRECISION NOT NULL,
    label            VARCHAR(255)     NOT NULL,
    account_id       UUID             NOT NULL,
    category_id      UUID             NOT NULL,
    FOREIGN KEY (account_id) REFERENCES account (account_id),
    FOREIGN KEY (category_id) REFERENCES category (category_id)
);