INSERT INTO address (street)
VALUES ('ул. Академика Королева 10'),
       ('ул. Зеленодольская 8'),
       ('ул. Ленина 21'),
       ('ул. Пушкина 2');

INSERT INTO users (name, password, address_id)
VALUES ('Alex', 'password', 1),
       ('Ivan', 'password', 2),
       ('Bob', 'password', 3),
       ('Vlad', 'password', 4);