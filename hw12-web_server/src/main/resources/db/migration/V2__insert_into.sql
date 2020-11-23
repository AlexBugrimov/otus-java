INSERT INTO address (id, street)
VALUES (1, "ул. Академика Королева 10"),
       (2, "ул. Зеленодольская 8"),
       (3, "ул. Ленина 21"),
       (4, "ул. Пушкина 2");

INSERT INTO users (name, password, address_id)
VALUES ("Alex", "password", 1),
       ("Ivan", "password", 2),
       ("Bob", "password", 3),
       ("Vlad", "password", 4);