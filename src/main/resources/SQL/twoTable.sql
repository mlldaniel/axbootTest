create table wnr_tb
(
  car_cd           varchar(4),
  category_cd      varchar(2)  not null,
  category_name    varchar(20) not null,
  customer_name    varchar(40),
  subcategory_cd   varchar(2)  not null,
  subcategory_name varchar(20) not null,
  wnr_amount       integer,
  wnr_date         varchar(8)  not null,
  wnr_price        integer,
  wnr_sep          varchar(1)  not null,
  wnr_seq          integer     not null,
  constraint wnr_tb_pkey
  primary key (category_cd, subcategory_cd, wnr_date, wnr_sep, wnr_seq)
);

-- auto-generated definition
create table warehousing_customer_tb
(
  address_name     varchar(200),
  car_cd           varchar(4)  not null,
  category_cd      varchar(2)  not null,
  category_name    varchar(20),
  corporatereg_no  varchar(12),
  customer_name    varchar(40) not null,
  phone_no         varchar(20),
  subcategory_cd   varchar(2)  not null,
  subcategory_name varchar(20),
  zip_cd           varchar(5),
  constraint warehousing_customer_tb_pkey
  primary key (car_cd, category_cd, customer_name, subcategory_cd)
);

