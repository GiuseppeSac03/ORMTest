create database students;

use students;

create table STUDENT (
   id INT NOT NULL,
   first_name VARCHAR(32) default NULL,
   last_name  VARCHAR(32) default NULL,
   age     INT  default NULL,
   PRIMARY KEY (id)
);

create table COURSE (
   id INT NOT NULL,
   name VARCHAR(64) default NULL,
   PRIMARY KEY (id)
);

create table REGISTRATION (
   id INT NOT NULL,
   student_id INT NOT NULL,
   course_id INT NOT NULL,
   grade INT default NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (student_id) REFERENCES STUDENT (ID),
   FOREIGN KEY (course_id) REFERENCES COURSE (ID)
);
