use test01;

create table member(
	id int not null auto_increment,
	name varchar(100) not null,
	pw varchar(50) not null,
	primary key(id)
);

desc member;

drop table member;

alter table member add phone varchar(100);

alter table member drop column phone;

alter table member change pw passwd varchar(60);

alter table member change passwd passwd varchar(60) not null;

rename table member to member2;

rename table member2 to student;

show tables;

desc student;

insert into student values('1','홍길동','1234');

select * from student;

insert into student values('2','abc','1234');
insert into student values('3','def','1234');

update student set name='zzz' where name = 'def';

delete from student where name = 'zzz';

select * from student;





create table member(
	id varchar(20) not null,
	passwd varchar(20),
	name varchar(30),
	primary key(id)
);

select * from member;