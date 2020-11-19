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
 

