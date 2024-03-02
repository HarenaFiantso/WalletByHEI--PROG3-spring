CREATE TABLE IF NOT EXISTS transfer_history
(
    transfer_history_id   UUID PRIMARY KEY   DEFAULT uuid_generate_v4(),
    transfer_date         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    debit_transaction_id  UUID      NOT NULL,
    credit_transaction_id UUID      NOT NULL,
    FOREIGN KEY (debit_transaction_id) REFERENCES transaction (transaction_id),
    FOREIGN KEY (credit_transaction_id) REFERENCES transaction (transaction_id)
);