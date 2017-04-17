DROP DATABASE IF EXISTS warehouse;
 
CREATE DATABASE warehouse CHARACTER SET utf8 COLLATE utf8_general_ci;
 
USE warehouse;
 
DROP TABLE IF EXISTS providers;
 
CREATE TABLE providers (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL,
	isDebtor BOOLEAN NOT NULL,
	PRIMARY KEY (id)
);
 
INSERT INTO providers (name, isDebtor) VALUES ('Provider 1', false);
INSERT INTO providers (name, isDebtor) VALUES ('Provider 2', false);
INSERT INTO providers (name, isDebtor) VALUES ('Provider 3', false);
INSERT INTO providers (name, isDebtor) VALUES ('Provider 4', false);
INSERT INTO providers (name, isDebtor) VALUES ('Provider 5', false);