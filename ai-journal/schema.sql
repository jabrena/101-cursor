CREATE TABLE IF NOT EXISTS greek_god (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
); 