
drop table if exists admin;
create table admin
(
    id       int(11)     not null primary key auto_increment,
    username varchar(40) not null unique,
    password varchar(40) not null
);



drop table if exists student;
create table student
(
    id           int(11)     not null primary key auto_increment,
    username     varchar(40) not null unique,
    password     varchar(40) not null,
    studentCode  varchar(64) not null unique,
    name         varchar(20) not null,
    studentClass int(11),
    age          int(8),
    sex          varchar(4),
    birthday     date        not null,
    address      varchar(64),
    phone        varchar(64),
    email        varchar(64)
);


drop table if exists teacher;
create table teacher
(
    id       int(11)     not null primary key auto_increment,
    username varchar(40) not null unique,
    password varchar(40) not null,
    name     varchar(20) not null,
    phone    varchar(64),
    email    varchar(64)
);



drop table if exists course;
create table course
(
    id           int(11)     not null primary key auto_increment,
    courseName   varchar(40) not null,
    academicYear varchar(40) not null,
    term         varchar(40) not null,
    teacherId    INT(11)     NOT NULL,
    commitStatus VARCHAR(50)
);



DROP TABLE if EXISTS chooseCourse;
CREATE TABLE chooseCourse
(
    id        INT(11) NOT NULL PRIMARY KEY auto_increment,
    studentId INT(11) NOT NULL,
    courseId  INT(11) NOT NULL,
    foreign key (studentId) references student (id) on delete cascade on update cascade,
    FOREIGN KEY (courseId) REFERENCES course (id) ON DELETE CASCADE on UPDATE CASCADE
);



DROP TABLE if EXISTS grade;
create TABLE grade
(
    id        INT(11) NOT NULL PRIMARY KEY auto_increment,
    courseId  INT(11) NOT NULL,
    studentId INT(11) NOT NULL,
    score     INT(8)  NOT NULL,
    FOREIGN KEY (courseId) REFERENCES course (id) on DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (studentId) REFERENCES student (id) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE if EXISTS gradeDraft;
create TABLE gradeDraft
(
    id        INT(11) NOT NULL PRIMARY KEY auto_increment,
    courseId  INT(11) NOT NULL,
    studentId INT(11) NOT NULL,
    score     INT(8)  NOT NULL,
    FOREIGN KEY (courseId) REFERENCES course (id) on DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (studentId) REFERENCES student (id) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into admin(username, password)values ('admin', '123');