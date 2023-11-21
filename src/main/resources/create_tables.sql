create table category
(
    category      integer not null
        primary key,
    category_name varchar(128)
);

alter table category
    owner to postgres;

create table employees
(
    id           serial
        primary key,
    last_name    text not null,
    name         text not null,
    middle_name  text not null,
    date_birth   date not null,
    phone_number text not null,
    address      text,
    rank_id      integer
        references ranks
);

alter table employees
    owner to postgres;

create table orders
(
    id            serial
        primary key,
    supplier_id   integer
        references suppliers,
    name_product  varchar(128) not null,
    count_product integer      not null,
    price_product integer      not null,
    date_order    date
);

alter table orders
    owner to postgres;

create table products
(
    id            serial
        primary key,
    supplier_id   integer
        references suppliers,
    name          varchar(128) not null,
    count         integer      not null,
    price_for_one integer      not null,
    category_name integer
        references category
);

alter table products
    owner to postgres;

create table ranks
(
    id        serial
        primary key,
    rank_name text,
    salary    integer not null
);

alter table ranks
    owner to postgres;

create table sales
(
    id          serial
        primary key,
    product_id  integer
        references products,
    count       integer not null,
    employee_id integer
        references employees,
    date_sales  date
);

alter table sales
    owner to postgres;

create table suppliers
(
    id           serial
        primary key,
    name         varchar(128) not null,
    address      varchar(128) not null,
    e_mail       varchar(128) not null,
    phone_number varchar(128) not null
);

alter table suppliers
    owner to postgres;

