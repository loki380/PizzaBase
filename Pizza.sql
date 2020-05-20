/* TWORZENIE TABEL I ZWIAZKÓW */

CREATE TABLE Address (
  idAddress INTEGER PRIMARY KEY,
  street VARCHAR(30) CHECK(len(street) > 0)NULL,
  nrHouse INTEGER CHECK(nrHouse > 0) NOT NULL,
  nrFlat INTEGER CHECK(nrFlat > 0) NULL,
  locality VARCHAR(30) CHECK(len(locality) > 0)NOT NULL,
  postcode CHAR(6) CHECK(postcode LIKE '[0-9][0-9]-[0-9][0-9][0-9]') NOT NULL
);

CREATE TABLE Category (
  idCategory INTEGER PRIMARY KEY,
  name VARCHAR(30) CHECK(len(name)>0) UNIQUE NOT NULL
);

CREATE TABLE Customer (
  idCustomer INTEGER PRIMARY KEY,
  Address_idAddress INTEGER REFERENCES Address(idAddress),
  name VARCHAR(30) CHECK(len(name)>0) NOT NULL,
  surname VARCHAR(30) CHECK(len(surname)>0) NOT NULL,
  tel CHAR(11) UNIQUE CHECK(len(tel)=11)NOT NULL
);

CREATE TABLE Driver (
  idDriver INTEGER PRIMARY KEY IDENTITY(1, 1),
  name VARCHAR(30) CHECK(len(name)>0) NOT NULL,
  surname VARCHAR(30) CHECK(len(surname)>0) NOT NULL,
  pesel CHAR(11) UNIQUE CHECK(Pesel LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') NOT NULL
);

CREATE TABLE Ingredient (
  idIngredient INTEGER PRIMARY KEY,
  Supplier_idSupplier INTEGER REFERENCES Supplier(idSupplier),
  name VARCHAR(30) UNIQUE CHECK(len(name)>0) NULL,
  mass DECIMAL(4,2) CHECK(mass >= 0) NOT NULL,
  price NUMERIC(3,2) CHECK(price >= 0) NOT NULL
);

CREATE TABLE Orderr (
  idOrder INTEGER PRIMARY KEY,
  Customer_idCustomer INTEGER REFERENCES Customer(idCustomer),
  Driver_idDriver INTEGER REFERENCES Driver(idDriver),
  Sauce_idSauce INTEGER REFERENCES Sauce(idSauce),
  dateProduction DATE CHECK(dateProduction <= GETDATE())NOT NULL,
  price_delivery NUMERIC(3,2) CHECK(price_delivery >= 0) NULL
);

CREATE TABLE Order_has_Pizza (
  Orderr_idOrder INTEGER PRIMARY KEY,
  Pizza_idPizza INTEGER REFERENCES Pizza(idPizza),
  amount INTEGER CHECK(amount > 0) NOT NULL,
);

CREATE TABLE Pizza (
  idPizza INTEGER PRIMARY KEY,
  Category_idCategory INTEGER REFERENCES Category(idCategory),
  Size_idSize INTEGER REFERENCES Size(idSize),
  name VARCHAR(30) UNIQUE CHECK(len(name)>0) NOT NULL,
  description TEXT NOT NULL,
  price NUMERIC(3,2) CHECK(price >= 0) NOT NULL
);

CREATE TABLE Pizza_has_Ingredient (
  Pizza_idPizza INTEGER PRIMARY KEY,
  Ingredient_idIngredient INTEGER REFERENCES Ingredient(idIngredient)
);

CREATE TABLE Sauce (
  idSauce INTEGER PRIMARY KEY,
  name VARCHAR(30) UNIQUE CHECK(len(name)>0) NOT NULL,
  price NUMERIC(3,2) CHECK(price >= 0) NOT NULL
);

CREATE TABLE Size (
  idSize INTEGER PRIMARY KEY,
  diameter DECIMAL(2,2) NOT NULL,
  name VARCHAR(30) CHECK(name = 'Large' OR name ='Medium' OR name ='Small')
);

CREATE TABLE Supplier (
  idSupplier INTEGER PRIMARY KEY,
  Address_idAddress INTEGER REFERENCES Address(idAddress),
  name INTEGER UNIQUE CHECK(len(name) > 0) NOT NULL
);

/*SELECT * FROM Orderr
SELECT * FROM Driver
SELECT * FROM Sauce
SELECT * FROM Customer
SELECT * FROM Address

DROP TABLE Orderr
DROP TABLE Driver
DROP TABLE Sauce
DROP TABLE Address
DROP TABLE Customer
DROP TABLE Supplier
DROP TABLE Size
DROP TABLE Pizza_has_Ingredient
DROP TABLE Order_has_Pizza
DROP TABLE Pizza
DROP TABLE Ingredient
DROP TABLE Category

INSERT INTO Driver
VALUES (1,'Mateusz','Latos','99919234512');

INSERT INTO Driver(name,surname,pesel)
VALUES ('Mateuszx','Latosx','99119234512');

INSERT INTO Sauce
VALUES (1,'Czosnkowy',3.00);

INSERT INTO Address
VALUES (1,'Kartuska',5,NULL,'Koœcierzyna','83-400');

INSERT INTO Customer
VALUES (1,1,'Kamil','Kowalski','48123543657');

INSERT INTO Orderr
VALUES (1,1,1,1,'2020-05-15',NULL);

UPDATE Driver SET name='Krzysiek', surname='Latos' WHERE idDriver=1

*/