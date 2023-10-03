CREATE TABLE developers(
    user_id SERIAL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    proglang VARCHAR(50) NOT NULL
);

INSERT INTO developers(firstname, lastname, age, proglang)
VALUES ('Tima', 'Javax', 21, 'Java');

INSERT INTO developers(firstname, lastname, age, proglang)
VALUES ('Maga', 'Tclich', 21, 'TCL');

INSERT INTO developers(firstname, lastname, age, proglang)
VALUES ('Yarik', 'Bagach', 21, 'Arbitraj');

INSERT INTO developers(firstname, lastname, age, proglang)
VALUES ('Sasha', 'Front', 21, 'JSREACT');





