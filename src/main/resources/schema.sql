drop table CURRENCY if exists;
drop table USERS if exists;
drop table SUC_TRANSACTION if exists;
drop table ORDERS if exists;
drop table LIMIT_ORDER if exists;
drop table CAN_ORDERS if exists;



--create table CURRENCY (
--CID int(20) NOT NULL AUTO_INCREMENT,
--PAIR varchar(255) NOT NULL,
--RATE double NOT NULL,
--PRIMARY KEY(CID) 
--);

create table SUC_TRANSACTION(
TID int(20) NOT NULL AUTO_INCREMENT,
BUYER varchar(255) NOT NULL,
SELLER varchar (255) NOT NULL,
PAIR varchar(255) NOT NULL,
PRICE DOUBLE(20) NOT NULL,
QTY int(20) NOT NULL,
TIME_STAMP varchar(100),
PRIMARY KEY(TID)
);

create table ORDERS(
OID int(20) NOT NULL AUTO_INCREMENT,
USERNAME varchar(255) NOT NULL,
ORDER_TYPE varchar(255) NOT NULL,
PRICE double(20) NOT NULL,
QTY int(20) NOT NULL,
PAIR varchar(255) NOT NULL,
SIDE VARCHAR(255) NOT NULL,
TIME_STAMP VARCHAR(255),
PRIMARY KEY(OID)
);

create table USERS(
USERNAME VARCHAR(255) NOT NULL,
PASSWORD VARCHAR(255) NOT NULL,
PRIMARY KEY(USERNAME)
);

create table CAN_ORDERS(
CID int(20) NOT NULL AUTO_INCREMENT,
USERNAME varchar(255) NOT NULL,
ORDER_TYPE varchar(255) NOT NULL,
PRICE int(20) NOT NULL,
QTY int(20) NOT NULL,
PAIR varchar(255) NOT NULL,
SIDE VARCHAR(255) NOT NULL,
PRIMARY KEY(CID)
);

--alter table ORDERS ADD FOREIGN KEY(USERNAME) references USERS(USERNAME);