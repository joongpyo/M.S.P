create table user(
u_id int not null auto_increment,
user_id varchar(20) not null unique,
user_passwd varchar(20) not null,
user_name varchar(20) not null,
user_email varchar(50) not null unique,
primary key(u_id)
);






--수정
create table board_QnA(
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
board_type int not null,
u_id_fk int not null,
foreign key(u_id_fk) REFERENCES user(u_id),
primary key(id)
);

create table files_QnA(
id int not null,
orgName varchar(255),
savedFileName varchar(255),
savedPathName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20)
);

create table comment_QnA(
c_id int not null auto_increment,
c_writer varchar(20),
c_comment varchar(100),
c_regdate date,
b_id_fk int not null,
foreign key(b_id_fk) references board_QnA(id),
primary key(c_id)
);

create table board_Notice(
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
board_type int not null,
u_id_fk int not null,
foreign key(u_id_fk) REFERENCES user(u_id),
primary key(id)
);

create table files_Notice(
id int not null,
orgName varchar(255),
savedFileName varchar(255),
savedPathName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20)
);

create table comment_Notice(
c_id int not null auto_increment,
c_writer varchar(20),
c_comment varchar(100),
c_regdate date,
b_id_fk int not null,
foreign key(b_id_fk) references board_Notice(id),
primary key(c_id)
);

create table board_Review(
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
board_type int not null,
u_id_fk int not null,
foreign key(u_id_fk) REFERENCES user(u_id),
primary key(id)
);

create table files_Review(
id int not null,
orgName varchar(255),
savedFileName varchar(255),
savedPathName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20)
);

create table comment_Review(
c_id int not null auto_increment,
c_writer varchar(20),
c_comment varchar(100),
c_regdate date,
b_id_fk int not null,
foreign key(b_id_fk) references board_Review(id),
primary key(c_id)
);

create table board_List(
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
board_type int not null,
u_id_fk int not null,
foreign key(u_id_fk) REFERENCES user(u_id),
primary key(id)
);

create table files_List(
id int not null,
orgName varchar(255),
savedFileName varchar(255),
savedPathName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20)
);

create table comment_List(
c_id int not null auto_increment,
c_writer varchar(20),
c_comment varchar(100),
c_regdate date,
b_id_fk int not null,
foreign key(b_id_fk) references board_List(id),
primary key(c_id)
);

create table medicine(
med_id int not null auto_increment,
med_name varchar(30) not null,
med_dis varchar(50) not null,
med_eff text not null,
med_type varchar(10) not null,
med_store char(1) default 'Y' not null,
med_com varchar(20) not null,
med_age char(1) not null,
med_pregnant char(1) default 'N',
med_reg date not null,
is_files char(1) not null,
primary key(med_id)
);

create table files_admin(
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
dis_reg date not null,
primary key(dis_id)
);

