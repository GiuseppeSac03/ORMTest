create database persons;

use persons;

create table ADDRESS (
   id INT NOT NULL auto_increment,
   street_name VARCHAR(64) NOT NULL,
   city_name VARCHAR(64) NOT NULL,
   state_name VARCHAR(64) NOT NULL,
   zipcode VARCHAR(10) NOT NULL,
   PRIMARY KEY (id)
);

create table PERSON (
   id INT NOT NULL auto_increment,
   first_name VARCHAR(32) NOT NULL,
   last_name  VARCHAR(32) NOT NULL,
   age     INT  NOT NULL,
   address_id    INT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (address_id) REFERENCES ADDRESS (ID)
);

create table DOCUMENT (
   id INT NOT NULL auto_increment,
   code VARCHAR(30) NOT NULL,
   person_id INT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (person_id) REFERENCES PERSON (ID)
);
