-- Drop table if it exists (optional, good for repeatable testing)
DROP TABLE IF EXISTS gods;

-- Create the gods table
CREATE TABLE gods (
    id BIGSERIAL PRIMARY KEY,        -- Use BIGSERIAL for auto-incrementing BIGINT in PostgreSQL
    name VARCHAR(255) NOT NULL      -- Standard VARCHAR, adjust size if needed
);

-- Optional: Add an index on the name column if needed for frequent lookups
-- CREATE INDEX idx_gods_name ON gods(name); 