use medicine;

create table med_user(
medicine_id int not null auto_increment,
user_id varchar(20) not null unique,
user_passwd varchar(20) not null,
user_name varchar(10) not null,
user_email varchar(50) not null unique,
primary key(medicine_id)
);

create table medicine(
med_num int not null auto_increment,
med_name varchar(50) not null,
med_pregnant char(1) default 'N' not null,
med_img varchar(100),
med_efficacy text not null,
med_type varchar(20) not null,
med_store char(1) not null,
med_age int not null,
primary key(med_num)
);

create table disease(
dis_id int not null auto_increment,
dis_name varchar(20) not null,
dis_pain_1 varchar(20) not null,
dis_pain_2 varchar(20),
dis_pain_3 varchar(20),
primary key(dis_id)
);


