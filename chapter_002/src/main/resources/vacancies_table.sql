create table vacancies (
	id serial primary key,
	name varchar(2000),
	author varchar(255),
	views int,
	create_date timestamp
)