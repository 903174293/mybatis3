DROP DATABASE IF EXISTS mldn ;
CREATE DATABASE mldn CHARACTER SET UTF8 ;
USE mldn ;
CREATE TABLE news(
	nid 	BIGINT 	AUTO_INCREMENT ,
	title	VARCHAR(50) ,
	pub_date	DATETIME ,
	note	TEXT ,
	CONSTRAINT pk_nid PRIMARY KEY(nid)
) engine="innodb";
