DROP TABLE IF EXISTS RentalEvent;
DROP TABLE IF EXISTS Boat;
DROP TABLE IF EXISTS Customer;

CREATE TABLE Boat (
                      id INT AUTO_INCREMENT NOT NULL,
                      brand VARCHAR(255) NOT NULL,
                      model VARCHAR(255) NOT NULL,
                      pricePerDay DOUBLE PRECISION NOT NULL,
                      seats INT NOT NULL,
                      plateNumber VARCHAR(50) UNIQUE NOT NULL,
                      available BOOLEAN NOT NULL,
                      CONSTRAINT pk_boat PRIMARY KEY (id)
);

CREATE TABLE Customer (
                          id INT AUTO_INCREMENT NOT NULL,
                          firstName VARCHAR(255) NOT NULL,
                          lastName VARCHAR(255) NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL,
                          boatLicense VARCHAR(255) NOT NULL,
                          countrycode VARCHAR(10) NOT NULL,
                          CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE RentalEvent (
                             id INT AUTO_INCREMENT NOT NULL,
                             boatId INT NOT NULL,
                             customerId INT NOT NULL,
                             rentalDate DATE NOT NULL,
                             returnDate DATE,
                             totalCost DOUBLE PRECISION NOT NULL,
                             isClosed BOOLEAN NOT NULL,
                             CONSTRAINT pk_rentalevent PRIMARY KEY (id)
);

ALTER TABLE RentalEvent
    ADD CONSTRAINT fk_rentalevent_boat FOREIGN KEY (boatId) REFERENCES Boat(id);

ALTER TABLE RentalEvent
    ADD CONSTRAINT fk_rentalevent_customer FOREIGN KEY (customerId) REFERENCES Customer(id);

-- Sample Boat Inserts (mimicking Java loop)
INSERT INTO Boat(id, brand, model, available, pricePerDay, seats, plateNumber)
VALUES (1, 'Bayliner', 'VR5', TRUE, 220.0, 7, 'PLT101'),
       (2, 'Sea Ray', 'SPX 210', FALSE, 240.0, 8, 'PLT102'),
       (3, 'Chaparral', 'H20 19', TRUE, 260.0, 6, 'PLT103');
ALTER TABLE Boat ALTER COLUMN id RESTART WITH 4;

INSERT INTO Customer (id, firstName, lastName, email, boatLicense, countrycode)
VALUES
    (1, 'Alice', 'Waters', 'alice@example.com', 'LIC101', 'US'),
    (2, 'Bob', 'Stone', 'bob@example.com', 'LIC102', 'US'),
    (3, 'Charlie', 'Lake', 'charlie@example.com', 'LIC103', 'CA'),
    (4, 'Diana', 'Coast', 'diana@example.com', 'LIC104', 'UK'),
    (5, 'Evan', 'Harbor', 'evan@example.com', 'LIC105', 'AU');
ALTER TABLE Customer ALTER COLUMN id RESTART WITH 6;

INSERT INTO RentalEvent(id, customerId, boatId, rentalDate, returnDate, totalCost, isClosed)
VALUES
    (1, 1, 1, '2024-06-01', '2024-06-03', 440.0,true),
    (2, 2, 2, '2024-06-05', '2024-06-06', 240.0,true),
    (3, 3, 3, '2024-06-10', '2024-06-12', 520.0,true);
ALTER TABLE RentalEvent ALTER COLUMN id RESTART WITH 4;