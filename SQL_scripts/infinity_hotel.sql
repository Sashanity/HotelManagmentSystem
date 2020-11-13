drop database if exists infinity_hotel;
create database infinity_hotel;
use infinity_hotel;

/* DCL */
CREATE USER 'hoteluser'@'localhost' IDENTIFIED BY 'p123456d';
GRANT ALL PRIVILEGES ON infinity_hotel . * TO 'hoteluser'@'localhost';
FLUSH PRIVILEGES;