DROP TABLE IF EXISTS RECIPIENT;
DROP sequence IF EXISTS RECIPIENT_ID_SEQ;

create sequence RECIPIENT_ID_SEQ start 1;

create table RECIPIENT (
	RECIPIENT_ID bigint auto_increment  not null,
	NAME varchar(40) not null,
	EMAIL varchar(40) not null, 
	PRIMARY KEY  (RECIPIENT_ID), 
	UNIQUE KEY RECIPIENT_EMAIL_UNIQUE (EMAIL)  
);

insert into RECIPIENT (name, email) values ('Wong Ah Peng', 'ahpeng@gmail.com');
insert into RECIPIENT (name, email) values ('Koi Redbean', 'redbean@hotmail.com');
insert into RECIPIENT (name, email) values ('Ali Ahmad', 'ali93@gmail.com');
insert into RECIPIENT (name, email) values ('Fatimah binti Farah', 'farah_1993@hotmail.com');
insert into RECIPIENT (name, email) values ('Pratap AL Rammsey', 'ramsey_52@outlook.com');
insert into RECIPIENT (name, email) values ('Khalil Mohamad', 'khalil@gmail.com');
insert into RECIPIENT (name, email) values ('Tan Seng Guan', 'tansenghuan@hotmail.com');


DROP TABLE IF EXISTS SPECIAL_OFFER;
DROP sequence IF EXISTS SPECIAL_OFFER_ID_SEQ;

create sequence SPECIAL_OFFER_ID_SEQ start 1;

create table SPECIAL_OFFER (
	SPECIAL_OFFER_ID bigint auto_increment  not null,
	NAME varchar(100) not null,
	FIXED_PERCENTAGE_DISCOUNT DECIMAL(20, 2) not null, 
	EFFECTIVE_FLAG varchar(2) not null, 
	PRIMARY KEY  (SPECIAL_OFFER_ID) 
);

insert into SPECIAL_OFFER (name, fixed_percentage_discount, effective_flag) values ('KFC Nov Special Offer', 5.0, 'N');
insert into SPECIAL_OFFER (name, fixed_percentage_discount, effective_flag) values ('Tealive 11.11 Offer', 25.0, 'N');
insert into SPECIAL_OFFER (name, fixed_percentage_discount, effective_flag) values ('Family Mart Special Offer', 1.5, 'N');
insert into SPECIAL_OFFER (name, fixed_percentage_discount, effective_flag) values ('GSC Christmas Special', 10, 'N');
insert into SPECIAL_OFFER (name, fixed_percentage_discount, effective_flag) values ('IKEA Meal Special', 4.25, 'N');

DROP TABLE IF EXISTS VOUCHER_CODE;
create table VOUCHER_CODE (
	VOUCHER_CODE_ID varchar(50) not null,
	RECIPIENT_ID bigint not null,
	SPECIAL_OFFER_ID bigint not null,
	EXPIRATION_DATE date, 
	USED_FREQUENCY int, 
	USAGE_DATE date, 
	PRIMARY KEY  (VOUCHER_CODE_ID) 
);