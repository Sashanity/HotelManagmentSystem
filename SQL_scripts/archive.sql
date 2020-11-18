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

-- create an archive for transactions
create table TransactionsArchive(
	transID INT NOT NULL PRIMARY KEY,
	bookingID INT,
	type VARCHAR(30),
	time_stamp  TIMESTAMP NOT NULL,
	sID INT
) 

