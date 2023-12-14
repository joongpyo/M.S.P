use medicine;

create table med_user(
medicine_id int not null auto_increment,
user_id varchar(20) not null unique,
user_passwd varchar(20) not null,
user_name varchar(10) not null,
user_email varchar(20) not null unique,
primary key(medicine_id)
);


create table board_qna(
  q_id int not null auto_increment,
  q_subject varchar(255) not null,
  q_content text,
  q_visit int,
  q_regdate date,
  q_orgName varchar(255),
  q_savedFileName varchar(255),
  q_savedFilePathName varchar(255),
  q_savedFileSize bigint,
  q_folderName varchar(10),
  q_ext varchar(20),
  q_grp int,
  q_seq int,
  q_depth int,
  q_medicine_id_fk int,
  foreign key(q_medicine_id_fk) references med_user(medicine_id),
  primary key(q_id)
);

1. user
create table user(
u_id int not null auto_increment,
user_id varchar(20) not null unique,
user_passwd vahchar(20) not null,
user_email varchar(50) not null unique,
primary key(u_id)
);

2. 약 DB
create table medicine(
med_id int not null auto_increment,
med_name varchar(30) not null,
med_dis_one varchar(10) not null,
med_dis_two varchar(10) not null,
med_dis_three varchar(10) not null,
med_type varchar(10) not null,
med_stoer char(1) default 'Y' not null,
med_com varchar(20) not null,
med_age char(1) not null,
med_pregnant char(1) default 'N'
med_reg date not null,
med_isFils not null,
primary key(med_id)
);


create table disease(
dis_id int not null auto_increment,
dis_name varchar(20) not null,
dis_pain varchar(20) not null,
primary key(dis_id)
);

마이페이지 >> 약저장
create table med_${userid}(
med_u_id int not null auto_increment,
med_name varchar(30) not null,
med_dis_one varchar(10) not null,
med_dis_two varchar(10) not null,
med_dis_three varchar(10) not null,
med_type varchar(10) not null,
med_stoer char(1) default 'Y' not null,
med_com varchar(20) not null,
med_age char(1) not null,
med_pregnant char(1) default 'N'
med_reg date not null,
primary key(med_u_id)
);


create table notice(
notice_id int not null auto_increment,
notice_subject varchar(30) not null,
notice_writer varchar(10) not null,
notice_content text not null,
notice_visit int not null,
notice_reg date not null,
primary key(notice_id)
);


create table qna(
qna_id int not null auto_increment,
qna_subject varchar(30) not null,
qna_writer varchar(10) not null,
qna_content text not null,
qna_visit int not null,
qna_reg date not null,
u_id int not null auto_increment,
grp int,
seq int,
depth int,
primary key(qna_id),
foreign key(u_id) REFERENCES user(u_id)
);

// table 조인해야함

create table review(
rev_id int not null auto_increment,
rev_subject varchar(30) not null,
rev_writer varchar(10) not null,
rev_content text not null,
rev_visit int not null,
rev_reg date not null,
grp int,
seq int,
depth int,
u_id int not null auto_increment,
primary key(rev_id),
foreign key(u_id) references user(u_id)
);

