drop table if exists USERS;

create table USERS (
  ID integer not null auto_increment,
  FIRST_NAME varchar(255) not null,
  SECOND_NAME varchar(255) not null,
  LOGIN varchar(255) not null unique,
  PASSWORD_HASH varchar(255) not null,
  USER_ROLE varchar(255) not null,
  primary key (ID));

insert into USERS (FIRST_NAME, SECOND_NAME, LOGIN, PASSWORD_HASH, USER_ROLE) values (?, ?, ?, ?, ?)