CREATE TABLE user
(
    id   BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    age  INT(3)
);

CREATE TABLE account
(
    no   BIGINT(20) NOT NULL AUTO_INCREMENT,
    type VARCHAR(255),
    rest NUMBER
);
