-- Book a room
INSERT INTO Reservations VALUES (NULL, gID, roomID, startDate, endDate, numPeople, totalDue);

-- delete a room by reservationId
DELETE FROM Reservations WHERE bookingID = bookingID;

-- make changes to a reservation only certain changes are allowed otherwise delete and create new
UPDATE Reservations SET numPeople = value WHERE bookingID = bookingID;
UPDATE Reservations SET roomID = value WHERE bookingID = bookingID;

-- delete a reservation
DELETE FROM Reservations WHERE bookingID = bookingID;

-- search queries for different scenerios:
-- see reservation by booking id or guestid or roomid
SELECT * FROM Reservations WHERE bookingID = bookingID;
SELECT * FROM Reservations WHERE gID = gID;
SELECT * FROM Reservations WHERE roomID = roomID;

-- show all occupied rooms by date range
SELECT roomID FROM Reservations WHERE ( startDate > queryStart AND startDate < queryEnd )
                                   OR ( endDate > queryStart AND endDate < queryEnd );

-- show all free rooms by date range (mySQL negation)
SELECT roomID FROM Rooms LEFT JOIN
     (SELECT roomID FROM Reservations WHERE ( startDate > queryStart AND startDate < queryEnd )
                                         OR ( endDate > queryStart AND endDate < queryEnd ))
         AS R
     USING (roomID)
WHERE R.roomID IS NULL;

