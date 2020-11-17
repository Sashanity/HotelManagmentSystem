-- create an archive for reservations

create table ReservationsArchive(
    bookingID INT NOT NUL PRIMARY KEY,
	gID INT,
	roomID INT,
	startDate DATE NOT NULL,
	endDate DATE NOT NULL,
	numPeople INT,
	totalDue DOUBLE NOT NULL,
)
