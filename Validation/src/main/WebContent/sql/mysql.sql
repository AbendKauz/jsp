use jsptest;

create table test01(
	name varchar(10),
	age int
);

insert into test01 values("철수", 20);
insert into test01 values("영희", 23);

select * from test01;

delete from test01 where name="영희";

select * from test01;
