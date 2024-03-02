CREATE DATABASE wallet;

\c wallet

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Types ENUM
CREATE TYPE currency_name AS ENUM ('Euro', 'Ariary');
CREATE TYPE currency_code AS ENUM ('EUR', 'MGA');
CREATE TYPE account_type AS ENUM ('BANK', 'CASH', 'MOBILE MONEY');
CREATE TYPE transaction_type AS ENUM ('CREDIT', 'DEBIT');

-- Table Currency
CREATE TABLE IF NOT EXISTS currency
(
    currency_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    currency_name currency_name NOT NULL,
    currency_code currency_code NOT NULL
);

-- Table Currency Value
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

-- Table Account
CREATE TABLE IF NOT EXISTS account
(
    account_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    account_name VARCHAR(255) NOT NULL,
    account_type account_type NOT NULL,
    currency_id  UUID         NOT NULL,
    FOREIGN KEY (currency_id) REFERENCES currency (currency_id)
);

-- Table Category
CREATE TABLE IF NOT EXISTS category
(
    category_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    category_name VARCHAR(50) NOT NULL
);

-- Table Transaction
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

-- Table Transfer History
CREATE TABLE IF NOT EXISTS transfer_history
(
    transfer_history_id   UUID PRIMARY KEY   DEFAULT uuid_generate_v4(),
    transfer_date         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    debit_transaction_id  UUID      NOT NULL,
    credit_transaction_id UUID      NOT NULL,
    FOREIGN KEY (debit_transaction_id) REFERENCES transaction (transaction_id),
    FOREIGN KEY (credit_transaction_id) REFERENCES transaction (transaction_id)
);
