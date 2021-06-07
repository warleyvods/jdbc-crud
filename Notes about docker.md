# Database instruction set: 🗃️

**To run Mysql with docker:**
docker run --jdbc_study -e MYSQL_ROOT_PASSWORD=123456 -d

**To connect to a terminal(bash) inside container:**
sudo docker exec -it jdbc_study bash

**MySql commands:**
DESC *table name*; <- Describes the data types of the refered table.

## Querys for this exercise:

#### Creating tables:

CREATE TABLE IF NOT EXISTS department (
  id serial PRIMARY KEY,
  name VARCHAR(80),
  PRIMARY KEY(id)
  );

CREATE TABLE IF NOT EXISTS seller (
  id serial PRIMARY KEY,
  name VARCHAR(50),
  lastname VARCHAR(50),
  email VARCHAR(140),
  salary DOUBLE,
  department_id INT, 
  FOREIGN KEY(department_id) REFERENCES department(id)
  ON DELETE CASCADE ON UPDATE CASCADE
);

#### Inserting data:

INSERT INTO department (id, name) VALUES (1, 'Eletronics');
INSERT INTO department (id, name) VALUES (2, 'Computers');
INSERT INTO department (id, name) VALUES (3, 'Photography');
INSERT INTO department (id, name) VALUES (4, 'Office Chairs');

INSERT INTO seller (id, name, lastname, email, salary, department_id) VALUES (1, 'John', 'Doe', 'john@me.com', 2500.00, 1);
INSERT INTO seller (id, name, lastname, email, salary, department_id) VALUES (2, 'Jimmy', 'Toe', 'jimmy@me.com', 8300.00, 1);
INSERT INTO seller (id, name, lastname, email, salary, department_id) VALUES (3, 'Tommy', 'Joe', 'Tommy@me.com', 2300.00, 2);
INSERT INTO seller (id, name, lastname, email, salary, department_id) VALUES (4, 'Bella', 'Smith', 'bella@me.com', 3490.00, 2);
INSERT INTO seller (id, name, lastname, email, salary, department_id) VALUES (5, 'Kristen', 'Adams', 'kristen@me.com', 9750.00, 3);
INSERT INTO seller (id, name, lastname, email, salary, department_id) VALUES (6, 'Ada', 'Love', 'ada@me.com', 7500.00, 3);
INSERT INTO seller (id, name, lastname, email, salary, department_id) VALUES (7, 'Neil', 'Legsweak', 'neil@me.com', 1990.00, 4);
INSERT INTO seller (id, name, lastname, email, salary, department_id) VALUES (8, 'Charlotte', 'Bad', 'charlotte@me.com', 6200.00, 4);

