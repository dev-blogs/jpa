DROP DATABASE IF EXISTS warehouse;

CREATE DATABASE warehouse CHARACTER SET utf8;
 
USE warehouse;

CREATE TABLE warehouses (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    address VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMP,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP,    
    UNIQUE UQ_ADDRESS_1 (address),
    PRIMARY KEY (id)
);
 
CREATE TABLE items (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMP,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP,
    warehouse_id INT UNSIGNED,
    PRIMARY KEY (id),
    FOREIGN KEY (warehouse_id) REFERENCES warehouses (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
 
CREATE TABLE providers (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMP,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE warehouses_chronology (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    address VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMP,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP,
    audit_revision INT NOT NULL,
    action_type int,
    audit_revision_end int,
    audit_revision_end_ts TIMESTAMP,    
    UNIQUE UQ_ADDRESS_CHRONOLOGY_1 (address),
    PRIMARY KEY (id, audit_revision)
);

CREATE TABLE items_chronology (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMP,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP,    
    audit_revision INT NOT NULL,
    action_type int,
    audit_revision_end int,
    audit_revision_end_ts TIMESTAMP,
    warehouse_id INT UNSIGNED,
    PRIMARY KEY (id, audit_revision),
    FOREIGN KEY (warehouse_id) REFERENCES warehouses (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE providers_chronology (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMP,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP,
    audit_revision INT NOT NULL,
    action_type int,
    audit_revision_end int,
    audit_revision_end_ts TIMESTAMP,
    PRIMARY KEY (id, audit_revision)
);
 
CREATE TABLE items_providers (
    item_id INT UNSIGNED NOT NULL,
    provider_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (item_id) REFERENCES items (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (provider_id) REFERENCES providers (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE REVINFO (
	rev INT NOT NULL AUTO_INCREMENT,
	revtstmp BIGINT NOT NULL,
	PRIMARY KEY (rev, revtstmp)
);