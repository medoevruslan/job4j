
 create database job4j_database;

 create table users(
 	id serial primary key,
 	name varchar(2000),
 	role_id int references role(id)
 );


 create table role(
 	id serial primary key,
 	name varchar(2000)
 );


 create table rules(
 	id serial primary key,
 	name varchar(255)
 );


 create table roles_to_rules (
  rule_id int references rules(id),
  role_id int references role(id)
 );


 create table item(
 	id serial primary key,
 	name varchar(255),
	user_id int unique references users(id)
 );


 create table comments(
 	id serial primary key,
 	descr text,
 	item_id int references item(id)
 );


 create table attachs(
 	id serial primary key,
 	descr text,
 	item_id int references item(id)
 );


 create table category(
 	id serial primary key,
 	name character varying(2000)
 );

 alter table item add column category_id int references category(id);


 create table state(
 	id serial primary key,
 	descr character varying(2000)
 );

 alter table item add column state_id int references state(id);

 insert into role(name) values('testRole');

 insert into users(name, role_id) values ('user', 1);

 insert into rules(name) values('testRule');

 insert into roles_to_rules(role_id, rule_id) values(1, 1);

 insert into category(name) values('testCategory');

 insert into state(descr) values('testStateDescription');

 insert into item(name, category_id, state_id) values('testItem', 1, 1);

 insert into comments(descr, item_id) values('testCommentDescription', 1);

 insert into attachs(descr, item_id) values('testAttachDescription', 1);





