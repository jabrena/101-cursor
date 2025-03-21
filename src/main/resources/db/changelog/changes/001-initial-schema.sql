--liquibase formatted sql
--changeset jabrena:1

CREATE TABLE IF NOT EXISTS greek_god (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
); 