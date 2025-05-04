-- Drop table if it exists (optional, good for repeatable testing)
DROP TABLE IF EXISTS greek_god;

-- Create the table if it doesn't exist
CREATE TABLE IF NOT EXISTS greek_god (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);