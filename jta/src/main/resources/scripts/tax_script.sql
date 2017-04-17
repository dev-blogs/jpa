DROP DATABASE IF EXISTS tax;
 
CREATE DATABASE tax CHARACTER SET utf8 COLLATE utf8_general_ci;
 
USE tax;
 
DROP TABLE IF EXISTS taxpayers;
 
CREATE TABLE taxpayers (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL,
	snils VARCHAR(16),
	isDebtor BOOLEAN NOT NULL,
	PRIMARY KEY (id)
);
 
INSERT INTO taxpayers (name, snils, isDebtor) VALUES ('Provider 6', '66666', false);
INSERT INTO taxpayers (name, snils, isDebtor) VALUES ('Provider 7', '77777', true);
INSERT INTO taxpayers (name, snils, isDebtor) VALUES ('Provider 8', '88888', true);
INSERT INTO taxpayers (name, snils, isDebtor) VALUES ('Provider 9', '99999', false);
INSERT INTO taxpayers (name, snils, isDebtor) VALUES ('Provider 10', '10101', false);