use WebMarketDB;

create table product(
	p_id varchar(10) not null,
	p_name varchar(10),
	p_unitPrice integer,
	p_description text,
	p_category varchar(20),
	p_manufacturer varchar(20),
	p_unitsInStock long,
	p_condition varchar(20),
	p_filename varchar(20),
	primary key(p_id)
)default charset=utf8;

desc product;

insert into product values('P1234', 'iPhone 6s', 800000, '1900 retina display', 'Smart Phone', 'Apple', 1000, 'New', 'P1234.png');
insert into product values('P1235', 'LG PC gram', 1500000, '3.3inch ips led display', 'Notebook', 'LG', 1000, 'Refurbished', 'P1235.png');
insert into product values('P1236', 'GalaxyTab', 900000, '3.3inch ips panel super amoled', 'Tablet', 'Samsung', 1000, 'Old', 'P1236.png');

select * from product;

delete from product;


create table member(
	id varchar(10) not null,	
	password varchar(10) not null,
	name varchar(10) not null,
	gender varchar(4),
	birth varchar(10),
	mail varchar(30),
	phone varchar(20),
	address varchar(90),
	regist_day varchar(50),
	primary key(id)
) default charset=utf8;

desc member;

select * from member;

-- 게시판 기본 DB 테이블 생성

create table board(
	-- 게시판 글 순번, 자동으로 숫자가 증가함
	num int not null auto_increment,
	-- 회원 아이디, 필수 입력
	id varchar(10) not null,
	-- 회원 이름, 필수 입력
	name varchar(10) not null,
	-- 게시물 제목, 필수 입력
	subject varchar(100) not null,
	-- 게시물 내용, 필수 입력
	content text not null,
	-- 게시글 등록 일자
	regist_day varchar(30),
	-- 게시글 조회수
	hit int,
	-- 게시글 등록 ip
	ip varchar(20),
	-- 기본키 : 순번
	primary key(num)
) default charset=utf8;

select * from board;

desc board;
