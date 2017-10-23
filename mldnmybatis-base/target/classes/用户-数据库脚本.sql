DROP DATABASE IF EXISTS mldn ;
CREATE DATABASE mldn CHARACTER SET UTF8 ;
USE mldn ;
CREATE TABLE member(
	mid			VARCHAR(50) ,
	name		VARCHAR(50) ,
	age			INT ,
	birthday	DATETIME ,
	salary		DOUBLE ,
	note		TEXT ,
	CONSTRAINT pk_mid PRIMARY KEY(mid)
) engine="innodb";
