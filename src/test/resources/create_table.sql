CREATE SCHEMA IF NOT EXISTS currency;
create table  `currency`(
`id` bigint unsigned auto_increment  not null,
`currency_id` bigint not null ,
`currency_name` varchar(20) not null ,
`state`  tinyint(3) default 0 not null,
primary key(`id`),
unique key(`currency_id`)
);

CREATE SCHEMA IF NOT EXISTS user;
create table `user`(
`id` bigint unsigned auto_increment  not null,
`username` varchar(10) not null ,
`phone`  varchar(11)  not null,
`state`  tinyint(3) default 0 not null,
`create_at` bigint ,
primary key(`id`),
unique key(`username`)
);

CREATE SCHEMA IF NOT EXISTS user_address;
create table `user_address`(
`id` bigint unsigned auto_increment not null,
`user_id` bigint unsigned  not null ,
`address`  varchar(50)  not null,
`state`  tinyint(3) default 0 not null,
primary key(`id`)
);


CREATE SCHEMA IF NOT EXISTS deposit;
create table `deposit`(
`id` bigint unsigned auto_increment not null,
`user_id` bigint unsigned  not null ,
`address`  varchar(50)  not null,
`currency_name` varchar(20) not null ,
`txhash` bigint(20) not null,
`amount`  decimal(36,18) unsigned NOT NULL,
`height` bigint not null,
`confirm` int not null,
`state`  tinyint(3) default 0 not null,
`create_at` bigint,
`version` bigint(20) unsigned NOT NULL DEFAULT '0',
primary key(`id`),
unique key(`currency_name`,`txhash`)
);

CREATE SCHEMA IF NOT EXISTS account;
create table account(
`id` bigint unsigned auto_increment not null,
`user_id` bigint unsigned  not null ,
`currency_name` varchar(20) not null,
`address`  varchar(50)  not null,
`balance` decimal(36,18) unsigned NOT NULL,
`state`  tinyint(3) default 0 not null,
primary key(`id`),
unique key(`user_id`,`currency_name`)
);