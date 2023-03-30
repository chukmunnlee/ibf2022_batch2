drop database if exists acmebank;

create database acmebank;

use acmebank;

create table accounts (
	acct_name varchar(32) not null,
	balance float(10, 2) default '0.0',

	primary key(acct_name)
);

insert into accounts(acct_name, balance) values 
	('fred', 1000),
	('barney', 1000),
	('wilma', 1000),
	('betty', 1000);

grant all privileges on acmebank.* to 'fred'@'%';

flush privileges;
