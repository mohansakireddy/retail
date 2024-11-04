DROP TABLE IF EXISTS retailer.TRANSACTION;
DROP TABLE IF EXISTS retailer.customer;


CREATE TABLE retailer.CUSTOMER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE retailer.TRANSACTION (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT ,
    transaction_date DATE NOT NULL,
    amount DOUBLE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES CUSTOMER(id)
);


-- inserting data into customer table
INSERT INTO retailer.CUSTOMER (name) VALUES ('mohan');
INSERT INTO retailer.CUSTOMER (name) VALUES ('siva');

-- inserting data into transaction table
-- customer 1
INSERT INTO retailer.TRANSACTION (customer_id, transaction_date, amount) VALUES (1,'2024-10-24',175);
INSERT INTO retailer.TRANSACTION (customer_id, transaction_date, amount) VALUES (1,'2024-09-24',75);
INSERT INTO retailer.TRANSACTION (customer_id, transaction_date, amount) VALUES (1,'2024-08-24',5);

-- transaction for customer 2


INSERT INTO retailer.TRANSACTION (customer_id, transaction_date, amount) VALUES (2,'2024-10-24',175);
INSERT INTO retailer.TRANSACTION (customer_id, transaction_date, amount) VALUES (2,'2024-09-24',75);
INSERT INTO retailer.TRANSACTION (customer_id, transaction_date, amount) VALUES (2,'2024-05-24',155);
