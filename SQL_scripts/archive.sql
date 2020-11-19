-- create an archive for reservations

create table ReservationsArchive(
    bookingID INT NOT NULL PRIMARY KEY,
	gID INT,
	roomID INT,
	startDate DATE NOT NULL,
	endDate DATE NOT NULL,
	numPeople INT,
	totalDue DOUBLE NOT NULL
);

create table TransactionsArchive(
	transID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	bookingID INT,
	type VARCHAR(30),
	time_stamp  TIMESTAMP NOT NULL,
	sID INT,
);
-- stored procedure for archiving

DELIMITER $$

CREATE PROCEDURE archiveReservations()
BEGIN
	insert into ReservationsArchive
	select * from Reservations where endDate<=curdate()
	for update;
	delete from Reservations where endDate<=curdate();
END $$

DELIMITER ;
--source C:\Users\Aleksandra\Desktop\Study\SJSU\Fall 2020\CS_157A\HotelManagmentSystem\SQL_scripts\archive.sql

call  archiveReservations();