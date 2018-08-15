create table type(
	id serial primary key,
	name varchar(255)
);


create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price smallint
);

insert into type(name) values('МОЛОКО');
insert into type(name) values('СЫР');
insert into type(name) values('МОРОЖЕННОЕ');
insert into type(name) values('ЙОГУРТ');

insert into product(name, type_id, expired_date, price) values('Бурёнка', 1, '2018-08-01', 23);
insert into product(name, type_id, expired_date, price) values('Простоквашино', 1, '2018-07-25', 26);
insert into product(name, type_id, expired_date, price) values('Ферма', 1, '2018-08-01', 20);
insert into product(name, type_id, expired_date, price) values('Селянское', 1, '2018-09-01', 22);
insert into product(name, type_id, expired_date, price) values('Гаудаб', 2, '2018-12-01', 50);
insert into product(name, type_id, expired_date, price) values('Голландский', 2, '2018-12-23', 56);
insert into product(name, type_id, expired_date, price) values('Чеддер', 2, '2019-01-15', 65);
insert into product(name, type_id, expired_date, price) values('Рудь', 3, '2019-03-15', 16);
insert into product(name, type_id, expired_date, price) values('Мороженное Три медведя', 3, '2019-02-10', 19);
insert into product(name, type_id, expired_date, price) values('Мороженное Белая Бяроза', 3, '2019-01-10', 17);
insert into product(name, type_id, expired_date, price) values('Мороженное Премия', 3, '2019-03-10', 15);
insert into product(name, type_id, expired_date, price) values('Ферма', 4, '2019-03-01', 20);
insert into product(name, type_id, expired_date, price) values('Галичина', 4, '2019-03-07', 22);
insert into product(name, type_id, expired_date, price) values('Актуаль', 4, '2019-03-15', 23);

select * from product as prod
where prod.type_id=2;

select * from product as prod
where prod.name like '%Мороженное%';

select * from product as prod
where prod.expired_date < '2018-09-01';

select * from product
where (price) in (
	select max(price)
	from product
);

select pr.type_id, count(pr.type_id) as prod_count 
from product as pr group by pr.type_id;

select * from product as prod 
inner join type as typ on typ.id = prod.type_id
where typ.name = 'СЫР' or typ.name = 'МОЛОКО';

select typ.name from poduct as prod
inner join type as typ
group by typ.name
having count(prod.id) < 10;

select * from product as prod 
inner join type as typ on typ.id = prod.type_id;