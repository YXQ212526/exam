create table  `currency`(
`id` bigint unsigned auto_increment  not null,
`currency_id` bigint not null ,
`state`  tinyint(3) default 0 not null,
primary key(`id`),
unique key(`currency_id`)
);

create table `user`(
`id` bigint unsigned auto_increment  not null,
`username` varchar(10) not null ,
`phone`  varchar(11)  not null,
`state`  tinyint(3) default 0 not null,
`create_at` bigint ,
primary key(`id`),
unique key(`username`)
);

create table `bind`(
`id` bigint unsigned auto_increment not null,
`user_id` bigint unsigned  not null ,
`address`  varchar(50)  not null,
`state`  tinyint(3) default 0 not null,
primary key(`id`)
);

create table `deposit`(
`id` bigint unsigned auto_increment not null,
`user_id` bigint unsigned  not null ,
`address`  varchar(50)  not null,
`currency_id` bigint not null ,
`txhash` bigint(20) not null,
`amount`  decimal(36,18) unsigned NOT NULL,
`height` bigint not null,
`confirm` int not null,
`create_at` bigint,
`verison` bigint(20) unsigned NOT NULL DEFAULT '0',
primary key(`id`),
unique key(`currency_id`,`txhash`)
);