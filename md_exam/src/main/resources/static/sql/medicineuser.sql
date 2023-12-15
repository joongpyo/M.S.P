--회원 DB
create table user(
u_id int not null auto_increment,
user_id varchar(20) not null unique,
user_passwd varchar(20) not null,
user_name varchar(20) not null,
user_email varchar(50) not null unique,
primary key(u_id)
);


--약 DB
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
med_isFiles char(1) not null,
primary key(med_id)
);

--병명 DB
create table disease(
dis_id int not null auto_increment,
dis_name varchar(20) not null,
dis_sym varchar(255) not null,
primary key(dis_id)
);

--마이페이지 >> 약저장
create table med_${userid}(
med_u_id int not null auto_increment,
med_name varchar(30) not null,
med_dis_one varchar(10) not null,
med_dis_two varchar(10) not null,
med_dis_three varchar(10) not null,
med_type varchar(10) not null,
med_store char(1) default 'Y' not null,
med_com varchar(20) not null,
med_age char(1) not null,
med_pregnant char(1) default 'N',
med_reg date not null,
primary key(med_u_id)
);

--공지사항 DB
create table notice(
notice_id int not null auto_increment,
notice_subject varchar(30) not null,
notice_writer varchar(10) not null,
notice_content text not null,
notice_visit int not null,
notice_reg date not null,
primary key(notice_id)
);

--QnA DB
create table qna(
qna_id int not null auto_increment,
qna_subject varchar(30) not null,
qna_writer varchar(10) not null,
qna_content text not null,
qna_visit int not null,
qna_reg date not null,
grp int,
seq int,
depth int,
isFiles char(1) not null,
u_id_fk int not null,
foreign key(u_id_fk) REFERENCES user(u_id),
primary key(qna_id)
);

--관리자 파일 등록
create table admin_files(
id int not null auto_increment,
orgName varchar(255),
savedFileName varchar(255),
savedPathFileName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20),
primary key(id)
);

--QnA 파일 등록
create table qna_files(
id int not null auto_increment,
orgName varchar(255),
savedFileName varchar(255),
savedPathFileName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20),
primary key(id)
);



// table 조인해야함

--약 복용후기 DB
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
u_id_fk int not null,
primary key(rev_id),
foreign key(u_id_fk) references user(u_id)
);








--insert into medicine values(
--null,
--'타이레놀',
--'감기',
--'몸살에 효과',
--'알약',
--'Y',
--'한국 얀센',
--'2',
--'N',
--now(),
--'Y'
--);
--
--
--
--
--
--약찾기 쿼리문
--select med_name, med_dis, med_eff, med_type, med_com, med_store from medicine
--where med_pregnant = #{pregnant} and med_age = #{age} and med_dis = #{disease} and med_type = #{type};
--
--select med_name, med_dis, med_eff, med_type, med_com, med_store from medicine
--where med_pregnant = 'N' and med_age = 2 and med_dis = '감기' and med_type = '알약';
--
--
--저장할때는 약 정보 저장 누르면 id로 모든 정보가 넘어감



