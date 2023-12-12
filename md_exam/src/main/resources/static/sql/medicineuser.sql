use medicine;

create table med_user(
medicine_id int not null auto_increment,
user_id varchar(20) not null unique,
user_passwd varchar(20) not null,
user_name varchar(10) not null,
user_email varchar(20) not null unique,
primary key(medicine_id)
);




