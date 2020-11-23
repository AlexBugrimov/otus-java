CREATE TABLE IF NOT EXISTS address
(
    id     BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(256),
    password   VARCHAR(256),
    address_id BIGINT,
    CONSTRAINT users_address_fk FOREIGN KEY (address_id) REFERENCES address (id)
);


