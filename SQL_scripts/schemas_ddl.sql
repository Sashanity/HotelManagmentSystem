drop database if exists infinity_hotel;
create database infinity_hotel;
use infinity_hotel;


drop table if exists Rooms;
create table Rooms(
	roomID INT NOT NULL PRIMARY KEY,
	type VARCHAR(30)
);

drop table if exists RoomTypes;
create table RoomTypes(
	type VARCHAR(30),
	rate VARCHAR(30)
);	

drop table if exists Guests;
create table Guests(
	gID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	firstN VARCHAR(30),
	lastN VARCHAR(30)
) AUTO_INCREMENT = 0000;

-- This is admin table that is avalible only for hotel stuff
drop table if exists Staff;
create table Staff(
	sID INT NOT NULL PRIMARY KEY,
	firstN VARCHAR(30),
	lastN VARCHAR(30),
	role VARCHAR(30)
);	

drop table if exists ServiceRoom;
create table ServiceRoom(
	roomID INT,
	serviceType VARCHAR(30),
	sID INT,
	serviceDate DATE DEFAULT '0000-00-00',
	FOREIGN KEY (sID) REFERENCES Staff (sID),
	FOREIGN KEY (roomID) REFERENCES Rooms (roomID)
);

drop table if exists RoomKeys;
create table RoomKeys(
	roomID INT,
	gID INT,
	keyPassword VARCHAR(30),
	FOREIGN KEY (gID) REFERENCES Guests (gID),
	FOREIGN KEY (roomID) REFERENCES Rooms (roomID)
);


drop table if exists Reservations;
create table Reservations(
	bookingID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	gID INT,
	roomID INT,
	startDate DATE NOT NULL,
	endDate DATE NOT NULL,
	numPeople INT,
	totalDue DOUBLE NOT NULL,
	CONSTRAINT FOREIGN KEY (gID) REFERENCES Guests (gID) ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (roomID) REFERENCES Rooms (roomID) ON DELETE CASCADE
	-- created_on DATETIME NOT NULL DEFAULT NOW()
) AUTO_INCREMENT = 0000;


drop table if exists Transactions;
create table Transactions(
	transID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	bookingID INT,
	type VARCHAR(30),
	time_stamp  TIMESTAMP NOT NULL,
	sID INT,
	CONSTRAINT FOREIGN KEY (sID) REFERENCES Staff (sID) ON DELETE NO ACTION,
	FOREIGN KEY (bookingID) REFERENCES Reservations (bookingID) 
) AUTO_INCREMENT = 0000;


create table TransactionsArchive(
	transID INT NOT NULL PRIMARY KEY,
	bookingID INT,
	type VARCHAR(30),
	time_stamp  TIMESTAMP NOT NULL,
	sID INT
);

DELIMITER $$

CREATE PROCEDURE archiveTransactions(IN cutOff date)
BEGIN
	insert into TransactionsArchive
	select * from Transactions where date(time_stamp)<=cutOff
	for update;
	delete from Transactions where date(time_stamp)<=cutOff;
END $$

DELIMITER ;
