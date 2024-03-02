CREATE TABLE IF NOT EXISTS currency
(
    currency_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    currency_name currency_name NOT NULL,
    currency_code currency_code NOT NULL
);