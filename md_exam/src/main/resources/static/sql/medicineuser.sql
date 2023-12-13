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

