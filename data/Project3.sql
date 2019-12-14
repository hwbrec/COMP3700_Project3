CREATE TABLE Customers (
	CustomerID	INTEGER NOT NULL,
	Name	TEXT NOT NULL,
	Phone	INTEGER,
	Address	TEXT,
	PRIMARY KEY(CustomerID)
);
CREATE TABLE Products (
	ProductID	INTEGER NOT NULL,
	Name	TEXT NOT NULL,
	Price	DECIMAL NOT NULL,
	Quantity	DECIMAL,
	PRIMARY KEY(ProductID)
);
CREATE TABLE Purchases (
	PurchaseID	INTEGER NOT NULL,
	CustomerID	INTEGER NOT NULL,
	ProductID	INTEGER NOT NULL,
	Price		DECIMAL NOT NULL,
	Quantity	DECIMAL NOT NULL,
	Cost		DECIMAL NOT NULL,
	Tax			DECIMAL NOT NULL,
	Total		DECIMAL NOT NULL,
	Date		TEXT,
	PRIMARY KEY(PurchaseID),
	CONSTRAINT fk_ProductID FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
	CONSTRAINT fk_CustomerID FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Customers (CustomerID, Name, Phone, Address)
VALUES(001, 'John Doe', 1234567654, '123 Wood Ln'), (002, 'Jane Doe', 9807651234, '123 Wood Ln'),
	(003, 'Mark Dosof', 5678978765, '44 Shady Acre St'), (004, 'Jake Su', 5681548963, '56 Windy Rd'),
	(005, 'Don Lon', 1256541235, '176 Loop Ave');

INSERT INTO Products (ProductID, Name, Price, Quantity)
VALUES(001, 'Tent', 50.00, 5.0), (002, 'Grill', 75.00, 2.0),
	(003, 'Lighter', 1.50, 20.0), (004, 'Lantern', 20.00, 5.0),
	(005, 'Wood Bundle', 10.00, 10.0);

INSERT INTO Purchases (PurchaseID, CustomerID, ProductID, Price, Quantity, Cost, Tax, Total, Date)
VALUES(001, 003, 002, 75.00, 1.0, 75.00, 6.00, 81.00, 'Oct 13 2019'), (002, 001, 001, 50.00, 1.0, 50.00, 4.00, 54.00, 'Oct 15 19'), (003, 004, 001, 50.00, 2.0, 100.00, 8.00, 108.00, 'Oct 16 19'),
	(004, 003, 003, 1.50, 5.0, 7.50, 0.60, 8.10, 'Oct 16 19'), (005, 005, 002, 75.00, 1.0, 75.00, 6.00, 81.00, 'Oct 17 19'), (006, 002, 003, 1.50, 3.0, 4.50, 0.36, 4.86, 'Oct 17 19'),
	(007, 002, 004, 20.00, 2.0, 40.00, 3.20, 43.20, 'Oct 17 19'), (008, 002, 005, 10.00, 3.0, 30.00, 2.40, 32.40, 'Oct 18 19'), (009, 004, 004, 20.00, 1.0, 20.00, 1.60, 21.60, 'Oct 19 19'),
	(010, 005, 005, 10.00, 4.0, 40.00, 3.20, 43.20, 'Oct 19 19');