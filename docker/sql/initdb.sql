CREATE TABLE developer(
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    proglang VARCHAR(50) NOT NULL
);

INSERT INTO developer(firstname, lastname, age, proglang)
VALUES ('Tima', 'Javax', 21, 'Java');

INSERT INTO developer(firstname, lastname, age, proglang)
VALUES ('Maga', 'Tclich', 22, 'TCL');

INSERT INTO developer(firstname, lastname, age, proglang)
VALUES ('Yarik', 'Bagach', 23, 'Arbitraj');

INSERT INTO developer(firstname, lastname, age, proglang)
VALUES ('Sasha', 'Front', 24, 'JSREACT');

INSERT INTO developer(firstname, lastname, age, proglang)
VALUES ('Vlad', 'Clen', 23, 'PHP');
