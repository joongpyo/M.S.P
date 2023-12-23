create table user(
u_id int not null auto_increment,
user_id varchar(20) not null unique,
user_passwd varchar(20) not null,
user_name varchar(20) not null,
user_email varchar(50) not null unique,
primary key(u_id)
);

create table qna(
id int not null auto_increment,
subject varchar(100) not null,
writer varchar(10) not null,
content text not null,
visit int not null,
reg date not null,
grp int,
seq int,
depth int,
isFiles char(1) not null,
comment_count int not null,
u_id_fk int not null,
foreign key(u_id_fk) REFERENCES user(u_id),
primary key(id)
);

create table qna_files(
id int not null,
orgName varchar(255),
savedFileName varchar(255),
savedPathName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20)
);

--b_id_fk이름변경/
create table qna_comment(
c_id int not null auto_increment,
c_writer varchar(20),
c_comment varchar(100),
c_regdate date,
b_id_fk int not null,
foreign key(b_id_fk) references qna(id),
primary key(c_id)
);

create table adminfiles(
id int not null auto_increment,
orgName varchar(255),
savedFileName varchar(255),
savedPathFileName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20),
primary key(id)
);
