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

select count(p.company_id) as employee, c.name from person as p
inner join company as c on (c.id = p.company_id) group by c.name
having count(p.company_id) >= all (select count(p.company_id) from person as p
inner join company as c on (c.id = p.company_id) group by c.name)

-- or we can define the maximum of employees with create view

create view persons_in_company as
select count(person.company_id) as employees, company.name from person
inner join company on (person.company_id = company.id)
group by company.name ;

select employees, company_name from persons_in_company where employees = (select max(employees) from persons_in_company)

 -- Select names of all persons that are NOT in the company with id = 5

select person.name from person inner join company on (person.company_id = company.id)
where person.company_id != 5;

/**
 * Select company name of each person
 */
select person.name, company.name from person 
inner join company on (company.id = person.company_id);
