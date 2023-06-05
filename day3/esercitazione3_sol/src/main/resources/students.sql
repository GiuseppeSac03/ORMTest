create database students;

use students;

create table STUDENT (
   id INT NOT NULL auto_increment,
   first_name VARCHAR(32) NOT NULL,
   last_name  VARCHAR(32) NOT NULL,
   age     INT,
   PRIMARY KEY (id)
);

create table COURSE (
   id INT NOT NULL auto_increment,
   name VARCHAR(64) NOT NULL,
   PRIMARY KEY (id)
);

create table REGISTRATION (
   id INT NOT NULL auto_increment,
   student_id INT NOT NULL,
   course_id INT NOT NULL,
   grade INT,
   PRIMARY KEY (id),
   FOREIGN KEY (student_id) REFERENCES STUDENT (ID),
   FOREIGN KEY (course_id) REFERENCES COURSE (ID)
);
