create table user(
u_id int not null auto_increment,
user_id varchar(20) not null unique,
user_passwd varchar(20) not null,
user_name varchar(20) not null,
user_email varchar(50) not null unique,
primary key(u_id)
);

create table medicine(
med_id int not null auto_increment,
med_name varchar(30) not null,
med_dis varchar(10) not null,
med_eff text not null,
med_type varchar(10) not null,
med_store char(1) default 'Y' not null,
med_com varchar(20) not null,
med_age char(1) not null,
med_pregnant char(1) default 'N',
med_reg date not null,
isFiles char(1) not null,
primary key(med_id)
);

create table qna(
id int not null auto_increment,
subject varchar(30) not null,
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

create table notice(
id int not null auto_increment,
subject varchar(30) not null,
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

create table notice_files(
id int not null,
orgName varchar(255),
savedFileName varchar(255),
savedPathName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20)
);


create table review(
id int not null auto_increment,
subject varchar(30) not null,
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

create table qna_comment(
c_id int not null auto_increment,
b_id int not null,
c_writer varchar(20),
c_comment varchar(100),
c_regdate date,
foreign key(b_id) references qna(id),
primary key(c_id)
);


create table admin_files(
id int not null auto_increment,
orgName varchar(255),
savedFileName varchar(255),
savedPathName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20),
primary key(id)
);

create table disease(
  dis_id int not null auto_increment,
  dis_name varchar(20) not null,
  dis_sym varchar(255) not null,
  dis_reg date,
  primary key(dis_id)
  );
