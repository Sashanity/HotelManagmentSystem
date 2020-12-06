-- DELIMITER $$
-- create Trigger trig1 
-- AFTER INSERT ON transactions 
-- FOR EACH ROW
-- when (new.type='check out')
-- BEGIN
--     DELETE FROM Reservations WHERE bookingID=new.bookingID;
-- END; $$
-- DELIMITER ;

-- drop trigger if exists rmOldREs;
-- CREATE TRIGGER rmOldREs
--     AFTER INSERT
--     ON Transactions 
--     when (type='check out')     
--     DELETE FROM Reservations WHERE bookingID=new.bookingID;
 
DELIMITER $$
drop trigger if exists service;
CREATE TRIGGER service
    AFTER INSERT
    ON ServiceRoom
    FOR EACH ROW
    BEGIN
        update ServiceRequest set status=true where reqID=new.reqID;
        update ServiceRequest set time_stamp=NOW() where reqID=new.reqID;
    END; $$
DELIMITER ;

DELIMITER $$
drop trigger if exists service2;
CREATE TRIGGER service2
   AFTER DELETE
   ON ServiceRoom
   FOR EACH ROW
   BEGIN
       update ServiceRequest set status=false where reqID=old.reqID;
       update ServiceRequest set time_stamp=NOW() where reqID=old.reqID;
   END; $$
DELIMITER ;

-- DELIMITER $$
-- drop trigger if exists rmReservation;
-- CREATE TRIGGER rmReservation
--     AFTER INSERT
--     ON Transactions
--     FOR EACH ROW
--     BEGIN
--         delete from Reservations where bookingID=new.bookingID and endDate<=NOW();
--     END; $$
-- DELIMITER ;



-- DELIMITER $$
             --drop trigger if exists rmReservation;
             --CREATE TRIGGER rmReservation
             --    AFTER DELETE
             --    ON Reservations
             --    FOR EACH ROW
             --    BEGIN
             --        delete from Guests where gID=old.gID;
             --        delete from RoomKeys where gID=old.gID;
             --    END; $$
             --DELIMITER ;