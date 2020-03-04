drop database if exists magazine_shop;
create database magazine_shop char set utf8;

use magazine_shop;

create table user (
    id int not null primary key auto_increment,
    firstName varchar(45) not null,
    lastName varchar(45) not null,
    email varchar(50) not null,
    password varchar(10) not null,
    accessLevel varchar(15) not null
);

create table magazine (
	id int not null primary key auto_increment,
    title varchar(45) not null,
    description text not null,
    publishingDate date not null,
    price double not null
);

create table subscribe (
	id int not null primary key auto_increment,
    userID int not null,
    magazineID int not null,
    subscribeStatus boolean default true,
    subscribeDate date not null,
    subscribePeriod int not null
);

alter table subscribe add foreign key (user_id) references user (id);
alter table subscribe add foreign key (magazine_id) references magazine (id);