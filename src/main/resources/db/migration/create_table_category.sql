CREATE TABLE IF NOT EXISTS category
(
    category_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    category_name VARCHAR(50) NOT NULL
);