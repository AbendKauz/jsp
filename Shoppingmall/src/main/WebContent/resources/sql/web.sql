use webmarketdb;

create table if not exists product(
	p_id varchar(10) not null,	-- 상품코드
	p_name varchar(10),	-- 제품명
	p_unitPrice integer,	-- 가격
	p_description text,	-- 상품설명
	p_category varchar(20),	-- 카테고리
	p_manufacturer varchar(20),	-- 제조사
	p_unitsInStock long,	-- 재고량
	p_condition varchar(20),	-- 상태
	p_fileName varchar(20),	-- 파일
	primary key(p_id)
)default charset=utf8;

show tables;
