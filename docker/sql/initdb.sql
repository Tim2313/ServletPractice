CREATE TABLE developer(
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    programmingLanguage VARCHAR(50) NOT NULL
);

INSERT INTO developer(firstname, lastname, age, programmingLanguage)
VALUES ('Tima', 'Javax', 21, 'Java');

INSERT INTO developer(firstname, lastname, age, programmingLanguage)
VALUES ('Maga', 'Tclich', 22, 'TCL');

INSERT INTO developer(firstname, lastname, age, programmingLanguage)
VALUES ('Yarik', 'Bagach', 23, 'Arbitraj');

INSERT INTO developer(firstname, lastname, age, programmingLanguage)
VALUES ('Sasha', 'Front', 24, 'JSREACT');

INSERT INTO developer(firstname, lastname, age, programmingLanguage)
VALUES ('Vlad', 'Clen', 23, 'PHP');
