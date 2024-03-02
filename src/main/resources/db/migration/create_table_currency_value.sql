CREATE TABLE IF NOT EXISTS currency_value
(
    currency_value_id       UUID PRIMARY KEY          DEFAULT uuid_generate_v4(),
    currency_value_date     DATE             NOT NULL DEFAULT CURRENT_DATE,
    exchange_rate           DOUBLE PRECISION NOT NULL,
    source_currency_id      UUID             NOT NULL,
    destination_currency_id UUID             NOT NULL,
    FOREIGN KEY (source_currency_id) REFERENCES currency (currency_id),
    FOREIGN KEY (destination_currency_id) REFERENCES currency (currency_id)
);