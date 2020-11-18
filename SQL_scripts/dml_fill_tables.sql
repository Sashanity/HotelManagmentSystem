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