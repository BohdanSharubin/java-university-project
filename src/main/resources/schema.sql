CREATE TABLE IF NOT EXISTS clothes (
                         id SERIAL PRIMARY KEY,
                         type VARCHAR(50) NOT NULL,
                         color VARCHAR(50) NOT NULL,
                         american_size VARCHAR(10),
                         european_size INT
);