DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
    id INT(4) NOT NULL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(100) NOT NULL,
    created TIMESTAMP,
    modified TIMESTAMP
);