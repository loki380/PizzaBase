
/* CREATE TABLE AND RELATION */

CREATE TABLE Address (
  idAddress INTEGER PRIMARY KEY IDENTITY(1, 1),
  street VARCHAR(30) CHECK(len(street) > 0)NULL,
  nrHouse INTEGER CHECK(nrHouse > 0) NOT NULL,
  nrFlat INTEGER CHECK(nrFlat > 0) NULL,
  locality VARCHAR(30) CHECK(len(locality) > 0)NOT NULL,
  postcode CHAR(6) CHECK(postcode LIKE '[0-9][0-9]-[0-9][0-9][0-9]') NOT NULL
);

CREATE TABLE Sauce (
  idSauce INTEGER PRIMARY KEY IDENTITY(1, 1),
  name VARCHAR(30) UNIQUE CHECK(len(name)>0) NOT NULL,
  price NUMERIC(3,2) CHECK(price >= 0) NOT NULL
);

CREATE TABLE Size (
  idSize INTEGER PRIMARY KEY IDENTITY(1, 1),
  diameter DECIMAL(4,2) NOT NULL,
  name VARCHAR(30) CHECK(name = 'Large' OR name ='Medium' OR name ='Small' OR name='Extra Large')
);

CREATE TABLE Supplier (
  idSupplier INTEGER PRIMARY KEY IDENTITY(1, 1),
  Address_idAddress INTEGER REFERENCES Address(idAddress),
  name VARCHAR(30) UNIQUE CHECK(len(name) > 0) NOT NULL
);

CREATE TABLE Category (
  idCategory INTEGER PRIMARY KEY IDENTITY(1, 1),
  name VARCHAR(30) CHECK(len(name)>0) UNIQUE NOT NULL
);

CREATE TABLE Customer (
  idCustomer INTEGER PRIMARY KEY IDENTITY(1, 1),
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
  idIngredient INTEGER PRIMARY KEY IDENTITY(1, 1),
  Supplier_idSupplier INTEGER REFERENCES Supplier(idSupplier),
  name VARCHAR(30) UNIQUE CHECK(len(name)>0) NULL,
  mass DECIMAL(5,2) CHECK(mass >= 0) NOT NULL,
  price NUMERIC(4,2) CHECK(price >= 0) NOT NULL
);

CREATE TABLE Orderr (
  idOrder INTEGER PRIMARY KEY IDENTITY(1, 1),
  Customer_idCustomer INTEGER REFERENCES Customer(idCustomer),
  Driver_idDriver INTEGER REFERENCES Driver(idDriver),
  Sauce_idSauce INTEGER REFERENCES Sauce(idSauce),
  dateProduction DATE CHECK(dateProduction <= GETDATE())NOT NULL,
  price_delivery NUMERIC(3,2) CHECK(price_delivery >= 0) NULL
);

CREATE TABLE Pizza (
  idPizza INTEGER PRIMARY KEY IDENTITY(1, 1),
  Category_idCategory INTEGER REFERENCES Category(idCategory),
  Size_idSize INTEGER REFERENCES Size(idSize),
  name VARCHAR(30) UNIQUE CHECK(len(name)>0) NOT NULL,
  description TEXT NOT NULL,
  price NUMERIC(4,2) CHECK(price >= 0) NOT NULL
);

CREATE TABLE Order_has_Pizza (
  Orderr_idOrder INTEGER REFERENCES Orderr(idOrder),
  Pizza_idPizza INTEGER REFERENCES Pizza(idPizza),
  amount INTEGER CHECK(amount > 0) NOT NULL,
);

CREATE TABLE Pizza_has_Ingredient (
  Pizza_idPizza INTEGER REFERENCES Pizza(idPizza),
  Ingredient_idIngredient INTEGER REFERENCES Ingredient(idIngredient)
);

CREATE TABLE Users (
  idUser INTEGER PRIMARY KEY IDENTITY(1, 1),
  Login VARCHAR(30) UNIQUE NOT NULL,
  Password VARCHAR(32) NOT NULL,
);

/* EXAMPLE DATA */
INSERT INTO Users(Login,Password)
VALUES ('admin','A7954119936A22D3073AE37952DC03A5');

INSERT INTO Driver(name,surname,pesel)
VALUES ('Mateusz','Latos','99119234512');
INSERT INTO Driver(name,surname,pesel)
VALUES ('Kamil','Jankowski','99619234512');
INSERT INTO Driver(name,surname,pesel)
VALUES ('Konrad','Knopik','96119234562');

INSERT INTO Address(street,nrHouse,nrFlat,locality,postcode) 
VALUES ('Kartuska',2, NULL,'Gdañsk','80-840')
INSERT INTO Address(street,nrHouse,nrFlat,locality,postcode) 
VALUES ('Miodowa',15, NULL,'Koœcierzyna','83-400')
INSERT INTO Address(street,nrHouse,nrFlat,locality,postcode) 
VALUES ('S³oneczna',36, NULL,'Koœcierzyna','83-400')
INSERT INTO Address(street,nrHouse,nrFlat,locality,postcode) 
VALUES ('Grunwaldzka',2,1,'Sopot','80-132')
INSERT INTO Address(street,nrHouse,nrFlat,locality,postcode) 
VALUES ('Czekoladowa',3,16,'Gdynia','87-213')
INSERT INTO Address(street,nrHouse,nrFlat,locality,postcode) 
VALUES ('Rolna',12,NULL,'Gdañsk','80-840')

INSERT INTO Supplier(Address_idAddress, name)
VALUES (1,'Eurocash');
INSERT INTO Supplier(Address_idAddress, name)
VALUES (2,'Makro');
INSERT INTO Supplier(Address_idAddress, name)
VALUES (3,'Atlanta A.M.');

INSERT INTO Size(diameter, name)
VALUES (21.5,'Small');
INSERT INTO Size(diameter, name)
VALUES (30.0,'Medium');
INSERT INTO Size(diameter, name)
VALUES (38.5,'Large');

INSERT INTO Category(name)
VALUES ('Italian');
INSERT INTO Category(name)
VALUES ('Vegan');
INSERT INTO Category(name)
VALUES ('Classic');

INSERT INTO Sauce(name,price)
VALUES ('Garlic',3.00);
INSERT INTO Sauce(name,price)
VALUES ('Tomato',2.50);
INSERT INTO Sauce(name,price)
VALUES ('Barbecue',3.50);

INSERT INTO Ingredient(Supplier_idSupplier,name,mass,price)
VALUES (1,'Chicken',100.00,6.50);
INSERT INTO Ingredient(Supplier_idSupplier,name,mass,price)
VALUES (1,'Salami',100.00,6.50);
INSERT INTO Ingredient(Supplier_idSupplier,name,mass,price)
VALUES (1,'Ham',100.00,6.50);
INSERT INTO Ingredient(Supplier_idSupplier,name,mass,price)
VALUES (2,'Olives',100.00,6.50);
INSERT INTO Ingredient(Supplier_idSupplier,name,mass,price)
VALUES (2,'Pineapple',100.00,6.50);
INSERT INTO Ingredient(Supplier_idSupplier,name,mass,price)
VALUES (3,'Cheese',100.00,6.50);
INSERT INTO Ingredient(Supplier_idSupplier,name,mass,price)
VALUES (3,'Mushroom',100.00,6.50);

INSERT INTO Pizza(Category_idCategory,Size_idSize,name,description,price)
VALUES (1,1,'Margherita','Best pizza',23.50);
INSERT INTO Pizza(Category_idCategory,Size_idSize,name,description,price)
VALUES (1,1,'Capriciosa','Best pizza v2',19.00);

INSERT INTO Customer(Address_idAddress,name,surname,tel)
VALUES (3,'Janusz','Kowalski','48666542456');
INSERT INTO Customer(Address_idAddress,name,surname,tel)
VALUES (4,'Robert','Kubica','48666512456');
INSERT INTO Customer(Address_idAddress,name,surname,tel)
VALUES (5,'Jaros³aw','Jarz¹bkowski','48616542456');

INSERT INTO Orderr(Customer_idCustomer,Driver_idDriver,Sauce_idSauce,dateProduction,price_delivery)
VALUES (1,1,1,'2020-05-20',5.00);
INSERT INTO Orderr(Customer_idCustomer,Driver_idDriver,Sauce_idSauce,dateProduction,price_delivery)
VALUES (2,2,2,'2020-05-18',7.00);
INSERT INTO Orderr(Customer_idCustomer,Driver_idDriver,Sauce_idSauce,dateProduction,price_delivery)
VALUES (3,3,3,'2020-04-27',3.00);

INSERT INTO Order_has_Pizza(Orderr_idOrder,Pizza_idPizza,amount)
VALUES (1,1,2);
INSERT INTO Order_has_Pizza(Orderr_idOrder,Pizza_idPizza,amount)
VALUES (1,2,2);
INSERT INTO Order_has_Pizza(Orderr_idOrder,Pizza_idPizza,amount)
VALUES (2,2,3);
INSERT INTO Order_has_Pizza(Orderr_idOrder,Pizza_idPizza,amount)
VALUES (3,1,4);

/* SELECT TABLE */

SELECT * FROM Orderr
SELECT * FROM Driver
SELECT * FROM Sauce
SELECT * FROM Customer
SELECT * FROM Address
SELECT * FROM Supplier
SELECT * FROM Ingredient
SELECT * FROM Pizza
SELECT * FROM Size
SELECT * FROM Category
SELECT * FROM Pizza_has_Ingredient
SELECT * FROM Order_has_Pizza
SELECT * FROM Users

/* DROP TABLE */

DROP TABLE Orderr
DROP TABLE Driver
DROP TABLE Sauce
DROP TABLE Customer
DROP TABLE Ingredient
DROP TABLE Supplier
DROP TABLE Address
DROP TABLE Pizza
DROP TABLE Pizza_has_Ingredient
DROP TABLE Order_has_Pizza
DROP TABLE Size
DROP TABLE Category
DROP TABLE Users

INSERT INTO Sauce
VALUES (1,'Czosnkowy',3.00);

INSERT INTO Address
VALUES (1,'Kartuska',5,NULL,'Koœcierzyna','83-400');

INSERT INTO Customer
VALUES (1,1,'Kamil','Kowalski','48123543657');

INSERT INTO Orderr
VALUES (1,1,1,1,'2020-05-15',NULL);

UPDATE Driver SET name='Krzysiek', surname='Latos' WHERE idDriver=1

INSERT INTO Address(street,nrHouse,locality,postcode) 
VALUES ('Jeden',2,'Koœcierzyna','93-213')

INSERT INTO Supplier(Address_idAddress, name)
VALUES (1,'jeden')

SELECT Ingredient.name as 'Name',mass as 'Weight', price as 'Price', Supplier.name as 'Supplier', FROM Ingredient inner join Supplier
ON Supplier_idSupplier=idSupplier
ORDER BY Ingredient.name

SELECT name, locality, postcode, street, nrHouse FROM Supplier inner join Address ON Address_idAddress=idAddress WHERE idSupplier=1

SELECT idSupplier, name FROM Supplier WHERE idSupplier!=(SELECT Supplier_idSupplier FROM Ingredient WHERE idIngredient=1)

SELECT Ingredient.name,mass,price, Supplier.name, Supplier_idSupplier
                FROM Ingredient inner join Supplier
                ON Supplier_idSupplier=idSupplier
                WHERE idIngredient=1

SELECT Pizza.name as 'Name', description as 'Description', Category.name as 'Category', price as 'Price'
FROM Pizza inner join Category
ON Category_idCategory=idCategory
ORDER BY Pizza.name

SELECT Ingredient.name, Ingredient_idIngredient
                        FROM Pizza_has_Ingredient inner join Ingredient
                        ON Ingredient_idIngredient=idIngredient
                        WHERE Pizza_idPizza=5



SELECT idPizza, name
FROM Pizza
SELECT name as 'Name', (CONCAT(locality,' ',postcode)) as 'Locality', (CONCAT(street,' ',nrHouse)) as 'Street' FROM Supplier inner join Address ON Address_idAddress=idAddress ORDER BY name