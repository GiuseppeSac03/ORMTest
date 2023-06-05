create database courses;

use courses;

create table COURSE (
   course_id INT NOT NULL auto_increment,
   name VARCHAR(64) NOT NULL,
   start_date DATE,
   end_date DATE,
   PRIMARY KEY (course_id)
);

create table MODULE (
   module_id INT NOT NULL auto_increment,
   name VARCHAR(64) NOT NULL,
   teacher VARCHAR(64),
   course_id  INT NOT NULL,
   PRIMARY KEY (module_id),
   FOREIGN KEY (course_id) REFERENCES COURSE (course_id)
);
