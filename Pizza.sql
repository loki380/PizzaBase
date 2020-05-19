/* TWORZENIE TABEL I ZWIAZKÓW */

CREATE TABLE Address (
  idAddress INTEGER PRIMARY KEY,
  street VARCHAR NULL,
  nrHouse INTEGER CHECK(nrHouse > 0) NOT NULL,
  nrFlat INTEGER CHECK(nrFlat > 0) NULL,
  locality VARCHAR NOT NULL,
  postcode CHAR(6) CHECK(postcode LIKE '[0-9][0-9]-[0-9][0-9][0-9]') NOT NULL
);

CREATE TABLE Category (
  idCategory INTEGER PRIMARY KEY,
  name VARCHAR NOT NULL
);

CREATE TABLE Customer (
  idCustomer INTEGER PRIMARY KEY,
  Address_idAddress INTEGER REFERENCES Address(idAddress),
  name VARCHAR NOT NULL,
  surname VARCHAR NOT NULL,
  tel VARCHAR(11) NOT NULL
);

CREATE TABLE Driver (
  idDriver INTEGER PRIMARY KEY,
  name VARCHAR NOT NULL,
  surname VARCHAR NOT NULL,
  pesel CHAR(11) UNIQUE CHECK(Pesel LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') NOT NULL
);

CREATE TABLE Ingredient (
  idIngredient INTEGER PRIMARY KEY,
  Supplier_idSupplier INTEGER REFERENCES Supplier(idSupplier),
  name VARCHAR UNIQUE NULL,
  mass DECIMAL(4,2) CHECK(mass >= 0) NOT NULL,
  price NUMERIC(3,2) CHECK(price >= 0) NOT NULL
);

CREATE TABLE Orderr (
  idOrder INTEGER PRIMARY KEY,
  Customer_idCustomer INTEGER REFERENCES Customer(idCustomer),
  Driver_idDriver INTEGER REFERENCES Driver(idDriver),
  Sauce_idSauce INTEGER REFERENCES Sauce(idSauce),
  datarealizacji DATE CHECK(datarealizacji <= GETDATE())NOT NULL,
  cena_dostawy NUMERIC(3,2) NULL
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
  name VARCHAR NOT NULL,
  description TEXT NOT NULL,
  price NUMERIC(3,2) CHECK(price >= 0) NOT NULL
);

CREATE TABLE Pizza_has_Ingredient (
  Pizza_idPizza INTEGER PRIMARY KEY,
  Ingredient_idIngredient INTEGER REFERENCES Ingredient(idIngredient)
);

CREATE TABLE Sauce (
  idSauce INTEGER PRIMARY KEY,
  name INTEGER NOT NULL,
  price NUMERIC(3,2) CHECK(price >= 0) NOT NULL
);

CREATE TABLE Size (
  idSize INTEGER PRIMARY KEY,
  diameter DECIMAL(2,2) NOT NULL,
  name VARCHAR CHECK(name = 'Large' OR name ='Medium' OR name ='Small')
);

CREATE TABLE Supplier (
  idSupplier INTEGER PRIMARY KEY,
  Address_idAddress INTEGER REFERENCES Address(idAddress),
  name INTEGER NOT NULL
);



