CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer references company(id),
CONSTRAINT person_pkey PRIMARY KEY (id)
);


 -- Select the name of the company with the maximum number of persons + number of persons in this company

select count(person.company_id) as employees, company.name from person 
inner join company on (person.company_id = company.id) 
group by company.name order by employees desc limit 1;


 -- Select names of all persons that are NOT in the company with id = 5

select person.name from person inner join company on (person.company_id = company.id)
where person.company_id != 5;


 -- Select company name of each person

select person.name, company.name from person 
inner join company on (company.id = person.company_id);
