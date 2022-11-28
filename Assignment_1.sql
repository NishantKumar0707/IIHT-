create database assignment_1;
use assignment_1;
create table Worker(
WORKER_ID int not null primary key, FIRST_NAME varchar(25) not null, LAST_NAME varchar(25) , SALARY int not null,JOINING_DATE datetime not null,
DEPARTMENT varchar(10) not null);
create table Bonus(
WORKER_REF_ID int , BONUS_DATE datetime not null, BONUS_AMOUNT int not null);
create table Title(
WORKER_REF_ID int primary key, WORKER_TITLE varchar(18) not null, AFFECTED_FROM datetime not null);
insert into Worker values(001,'Monika','Arora',100000,'2014-02-20 09:00:00','HR'),
(002,'Niharika','Verma',80000,'2014-06-11 09:00:00','Admin'),(003,'Vishal','Singhal',300000,'2014-02-20 09:00:00','HR'),
(004,'Amitabh','Singh',500000,'2014-02-20 09:00:00','Admin'),(005,'Vivek','Bhati',500000,'2014-06-11 09:00:00','Admin'),
(006,'Vipul','Diwan',200000,'2014-06-11 09:00:00','Account'),(007,'Satish','Kumar',75000,'2014-01-20 09:00:00','Account'),
(008,'Geetika','Chauhan',90000,'2014-04-11 09:00:00','Admin');
insert into Bonus values (1,'2016-02-20 00:00:00',5000),(2,'2016-06-11 00:00:00',3000),(3,'2016-02-20 00:00:00',4000),
(1,'2016-02-20 00:00:00',4500),(2,'2016-06-11 00:00:00',3500);
insert into Title values(1,'Manager','2016-02-20 00:00:00'),(2,'Executive','2016-06-11 00:00:00'),
(8,'Executive','2016-06-11 00:00:00'),(5,'Manager','2016-06-11 00:00:000'),(4,'Asst. Manager','2016-06-11 00:00:00'),
(7,'Executive','2016-06-11 00:00:0'),(6,'Lead','2016-06-11 00:00:00'),(3,'Lead','2016-06-11 00:00:00');
#Q-1. Write an SQL query to fetch “FIRST_NAME” from Worker table using the alias name as <WORKER_NAME>.
select FIRST_NAME as WORKER_NAME from Worker;
#Q-2. Write an SQL query to fetch “FIRST_NAME” from Worker table in upper case.
select upper(FIRST_NAME) from Worker;
#Q-3. Write an SQL query to fetch unique values of DEPARTMENT from Worker table.
select distinct DEPARTMENT from Worker;
#Q-4. Write an SQL query to print the first three characters of  FIRST_NAME from Worker table.
select substring(FIRST_NAME,1,3) from Worker; 
#Q-5. Write an SQL query to find the position of the alphabet (‘a’) in the first name column ‘Amitabh’ from Worker table.
select position("a" in FIRST_NAME) from Worker where FIRST_NAME='Amitabh';
#Q-6. Write an SQL query to print the FIRST_NAME from Worker table after removing white spaces from the right side.
select rtrim(FIRST_NAME) from Worker;
#Q-7. Write an SQL query to print the DEPARTMENT from Worker table after removing white spaces from the left side.
select ltrim(DEPARTMENT) from Worker;
#Q-8. Write an SQL query that fetches the unique values of DEPARTMENT from Worker table and prints its length.
select distinct DEPARTMENT, length(DEPARTMENT) as Length from Worker;
#Q-9. Write an SQL query to print the FIRST_NAME from Worker table after replacing ‘a’ with ‘A’.
select replace(FIRST_NAME,'a','A') from Worker;
#Q-10.Write an SQL query to print the FIRST_NAME and LAST_NAME from Worker table into a single column COMPLETE_NAME. A space char should separate them.
select concat(FIRST_NAME,' ',LAST_NAME) as COMPLETE_NAME from Worker;
#Q-11. Write an SQL query to print all Worker details from the Worker table order by FIRST_NAME Ascending.
select * from Worker order by FIRST_NAME;
#Q-12. Write an SQL query to print all Worker details from the Worker table order by FIRST_NAME Ascending and DEPARTMENT Descending.
select * from Worker order by FIRST_NAME ASC , DEPARTMENT DESC;
#Q-13. Write an SQL query to print details for Workers with the first name as “Vipul” and “Satish” from Worker table.
select * from Worker where FIRST_NAME='Vipul'or FIRST_NAME='Satish';
#Q-14. Write an SQL query to print details of workers excluding first names, “Vipul” and “Satish” from Worker table.
select * from Worker where FIRST_NAME not in('Vipul','Satish');
#Q-15. Write an SQL query to print details of Workers with DEPARTMENT name as “Admin”.
select * from Worker where DEPARTMENT='Admin';
#Q-16. Write an SQL query to print details of the Workers whose FIRST_NAME contains ‘a’.
select * from Worker where FIRST_NAME like '%a%';
#Q-17. Write an SQL query to print details of the Workers whose FIRST_NAME ends with ‘a’.
select * from Worker where FIRST_NAME like '%a';
#Q-18. Write an SQL query to print details of the Workers whose FIRST_NAME ends with ‘h’ and contains six alphabets.
select * from Worker where FIRST_NAME like '%h' and length(FIRST_NAME)=6;
#Q-19. Write an SQL query to print details of the Workers whose SALARY lies between 100000 and 500000.
select * from Worker where SALARY>100000 and SALARY<500000;
#Q-20. Write an SQL query to print details of the Workers who have joined in Feb’2014.
select * from Worker where year(JOINING_DATE)=2014 and month(JOINING_DATE)=02;
#Q-21. Write an SQL query to fetch the count of employees working in the department ‘Admin’.
select count(DEPARTMENT) as count_of_employees_working_in_the_Admin  from Worker where DEPARTMENT='Admin';
#Q-22. Write an SQL query to fetch worker names with salaries >= 50000 and <= 100000.
select * from Worker where SALARY >= 50000 and SALARY <= 100000;
#Q-23. Write an SQL query to fetch the no. of workers for each department in the descending order.
select DEPARTMENT, count(DEPARTMENT) as No_of_Workers from Worker group by DEPARTMENT order by count(DEPARTMENT) DESC;
#Q-24. Write an SQL query to print details of the Workers who are also Managers.
select * from Worker where WORKER_ID in (select WORKER_REF_ID from Title where WORKER_TITLE='Manager');
#Q-25. Write an SQL query to fetch duplicate records having matching data in some fields of a table.
select distinct* from Worker where DEPARTMENT in(select DEPARTMENT from Worker group by DEPARTMENT having count(DEPARTMENT)>1) or 
JOINING_DATE in(select JOINING_DATE from Worker group by JOINING_DATE having count(JOINING_DATE)>1 ) or
 SALARY in (select SALARY from WORKER group by SALARY having count(SALARY)>1 );
#Q-26. Write an SQL query to show only odd rows from a table.
select * from Worker where WORKER_ID%2!=0;
#Q-27. Write an SQL query to show only even rows from a table.
select * from Worker where WORKER_ID%2=0;
#Q-28. Write an SQL query to clone a new table from another table.
create table Rex as select * from Worker;
#Q-29. Write an SQL query to fetch intersecting records of two tables.
select distinct * from  Worker inner join Bonus where WORKER_ID=WORKER_REF_ID;
#Q-30. Write an SQL query to show records from one table that another table does not have.
select WORKER_ID from Worker where WORKER_ID not in (select distinct WORKER_REF_ID from Bonus);



