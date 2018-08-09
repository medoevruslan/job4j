create table engine (
id serial primary key, 
name varchar(255)
); 

create table transmission (
id serial primary key,
name varchar(255)
 );

create table body (
id serial primary key,
name varchar(255)
 );

create table car (
id serial primary key,
name varchar(255),
body_id int references body(id),
engine_id int references engine(id),
transmissiont_id int references transmission(id)
);

insert into body(name) values('universal');
insert into body(name) values('hatchback');
insert into body(name) values('sedan');

insert into engine(name) values('vee');
insert into engine(name) values('straight');
insert into engine(name) values('inline');

insert into transmission(name) values('automatic');
insert into transmission(name) values('automated-manual');
insert into transmission(name) values('manual');

insert into car(name, body_id, engine_id, transmission_id) values('BMW5', 3, 2, 3);
insert into car(name, body_id, engine_id, transmission_id) values('NISSAN', 2, 3, 3);
insert into car(name, body_id, engine_id, transmission_id) values('OPEL', 1, 3, 2);

select car.name, body.name, transmission.name from car 
inner join body on car.body_id = body.id 
inner join engine on car.engine_id = engine.id
inner join transmission on car.transmission.id = transmission.id;

select engine.name from engine left join car on car.engine_id = engine.id where car.id is null;
select transmission.name from transmission left join car on car.transmission_id = transmission.id where car.id is null;
