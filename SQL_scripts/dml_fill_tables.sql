-- use infinity_hotel;
-- creates rooms
insert into Rooms values(101, 'single');
insert into Rooms values(102, 'single');
insert into Rooms values(103, 'single');
insert into Rooms values(104, 'single');
insert into Rooms values(105, 'single');
insert into Rooms values(106, 'single');
insert into Rooms values(107, 'single');
insert into Rooms values(108, 'single');
insert into Rooms values(109, 'single');

insert into Rooms values(110, 'double');
insert into Rooms values(111, 'double');
insert into Rooms values(112, 'double');
insert into Rooms values(113, 'double');
insert into Rooms values(114, 'double');
insert into Rooms values(115, 'double');
insert into Rooms values(116, 'double');
insert into Rooms values(117, 'double');
insert into Rooms values(118, 'double');

insert into Rooms values(201, 'triple');
insert into Rooms values(202, 'triple');
insert into Rooms values(203, 'triple');
insert into Rooms values(204, 'triple');

insert into Rooms values(205, 'family room');
insert into Rooms values(206, 'family room');
insert into Rooms values(207, 'family room');
insert into Rooms values(208, 'family room');

insert into Rooms values(301, 'suite');
insert into Rooms values(302, 'suite');
insert into Rooms values(303, 'suite');
insert into Rooms values(304, 'suite');
insert into Rooms values(305, 'suite');

insert into Rooms values(405, 'presidential suite');

-- create RoomType
insert into RoomTypes values('single', 50);
insert into RoomTypes values('double', 70);
insert into RoomTypes values('triple', 100);
insert into RoomTypes values('family room', 200);
insert into RoomTypes values('suite', 400);
insert into RoomTypes values('presidential suite', 1000);

-- create staff

insert into Staff values(12341, 'Alden','Cantrell', 'reception');
insert into Staff values(12342, 'Kierra','Gently', 'reception');
insert into Staff values(12351, 'Alvaro','Mcgee', 'cleaning');
insert into Staff values(12352, 'Ashley','Robson', 'cleaning');
insert into Staff values(12353, 'John','Lore', 'cleaning');
insert into Staff values(12354, 'Aman','Shaw', 'cleaning');
insert into Staff values(12361, 'Daria','Drew', 'room service');
insert into Staff values(12362, 'Rhodri','Baker', 'room service');
insert into Staff values(12371, 'Darrell','Roche', 'mainteince');
insert into Staff values(12381, 'Mayra','Lew', 'manager');
insert into Staff values(12391, 'Kierra','Johnson', 'admin');

-- create guests

insert into Guests values(null,'Maria','Ko');
insert into Guests values(null,'Libby','Avery');
insert into Guests values(null,'John','Avery');
insert into Guests values(null,'Bob','Avery');
insert into Guests values(null,'Maryam','Holden');
insert into Guests values(null,'John','Low');
insert into Guests values(null,'Tamera','Lowe');
insert into Guests values(null,'John','Hartman');
insert into Guests values(null,'Ann',' Potter');
insert into Guests values(null,'Nile','Levine');
insert into Guests values(null,'Linda','Levine');


-- create Service room records

-- insert into ServiceRoom values(101,'cleaning', 12351, '2020-11-15');
-- insert into ServiceRoom values(102,'cleaning', 12351, '2020-11-15');
-- insert into ServiceRoom values(103,'cleaning', 12352, '2020-11-15');
-- insert into ServiceRoom values(104,'cleaning', 12352, '2020-11-15');
-- insert into ServiceRoom values(105,'cleaning', 12352, '2020-11-15');
-- insert into ServiceRoom values(106,'cleaning', 12354, '2020-11-15');
-- insert into ServiceRoom values(107,'cleaning', 12354, '2020-11-15');
-- insert into ServiceRoom values(108,'cleaning', 12353, '2020-11-15');
-- insert into ServiceRoom values(109,'cleaning', 12353, '2020-11-15');
-- insert into ServiceRoom values(110,'cleaning', 12351, '2020-11-15');
-- insert into ServiceRoom values(111,'cleaning', 12352, '2020-11-15');
-- insert into ServiceRoom values(112,'cleaning', 12352, '2020-11-15');
-- insert into ServiceRoom values(113,'cleaning', 12353, '2020-11-15');
-- insert into ServiceRoom values(114,'cleaning', 12353, '2020-11-15');
-- insert into ServiceRoom values(115,'cleaning', 12353, '2020-11-15');
-- insert into ServiceRoom values(116,'cleaning', 12354, '2020-11-15');
-- insert into ServiceRoom values(117,'cleaning', 12354, '2020-11-15');
-- insert into ServiceRoom values(118,'cleaning', 12351, '2020-11-15');
-- insert into ServiceRoom values(201,'cleaning', 12354, '2020-11-15');
-- insert into ServiceRoom values(202,'cleaning', 12351, '2020-11-15');
-- insert into ServiceRoom values(203,'cleaning', 12351, '2020-11-15');
-- insert into ServiceRoom values(204,'cleaning', 12352, '2020-11-15');
-- insert into ServiceRoom values(205,'cleaning', 12354, '2020-11-15');
-- insert into ServiceRoom values(206,'cleaning', 12353, '2020-11-15');
-- insert into ServiceRoom values(207,'cleaning', 12351, '2020-11-15');
-- insert into ServiceRoom values(208,'cleaning', 12352, '2020-11-15');
-- insert into ServiceRoom values(301,'cleaning', 12352, '2020-11-15');
-- insert into ServiceRoom values(302,'cleaning', 12351, '2020-11-15');
-- insert into ServiceRoom values(303,'cleaning', 12354, '2020-11-15');
-- insert into ServiceRoom values(304,'cleaning', 12351, '2020-11-15');
-- insert into ServiceRoom values(305,'cleaning', 12352, '2020-11-15');
-- insert into ServiceRoom values(405,'cleaning', 12354, '2020-11-15');


-- create reservation
insert into Reservations values(null,1,101,'2020-11-16','2020-11-18',1,100.00);
insert into Reservations values(null,2,110,'2020-10-01','2020-10-10',2,630.00);
insert into Reservations values(null,1,103,'2020-09-16','2020-10-03',1,1000.00);
insert into Reservations values(null,1,103,'2020-09-16','2020-10-03',1,1000.00);
insert into Reservations values(null,4,106,'2020-11-16','2020-11-20',1,2000.00);
-- insert into Reservations values(null,1,101,'2020-11-16','2020-11-18',1,100.00,NOW());

-- create transaction

-- SET time_zone='+00:00';
insert into Transactions values(null, 2, 'check in', '2020-11-16 13:00:01',12341);
insert into Transactions values(null, 2, 'check out', '2020-11-18 12:00:01',12342);

-- create a Key for a guest

insert into RoomKeys values(101,1,'pwd123');

-- source C:\Users\Aleksandra\Desktop\Study\SJSU\Fall 2020\CS_157A\HotelManagmentSystem\SQL_scripts\schemas_ddl.sql
-- source C:\Users\Aleksandra\Desktop\Study\SJSU\Fall 2020\CS_157A\HotelManagmentSystem\SQL_scripts\dml_fill_tables.sql


