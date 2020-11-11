/* INITIALIZATION */
DROP DATABASE IF EXISTS infinity_hotel;
CREATE DATABASE infinity_hotel;
USE infinity_hotel;

/* DCL */
CREATE USER 'hoteluser'@'localhost' IDENTIFIED BY 'p123456d';
GRANT ALL PRIVILEGES ON infinity_hotel . * TO 'hoteluser'@'localhost';
FLUSH PRIVILEGES;