-------- CREATE TABLE category
create table category
(
    id serial
        constraint category_pk
            primary key,
    category_name varchar
);

create unique index category_category_name_uindex
    on "category" (category_name);

-------- CREATE TABLE user
create table "w_user"
(
    id serial
        constraint w_user_pk
            primary key,
    username varchar not null,
    user_password varchar not null,
    user_role varchar not null,
    create_date timestamp WITHOUT TIME ZONE not null
);

create unique index w_user_username_uindex
    on "w_user" (username);

-------- CREATE TABLE customer
create table customer
(
    id serial
        constraint customer_pk
            primary key,
    customer_name varchar not null,
    customer_type varchar not null,
    customer_address varchar not null
);

create unique index customer_customer_name_uindex
    on customer (customer_name);

-------- CREATE TABLE item
create table item
(
    id serial
        constraint item_pk
            primary key,
    item_name varchar not null,
    category_id int
        constraint item_category_id_fk
            references category,
    create_date timestamp without time zone not null,
    user_id int not null
        constraint item_w_user_id_fk
            references "w_user"
);

create unique index item_item_name_uindex
    on item (item_name);

-------- CREATE TABLE details
create table details
(
    id serial
        constraint details_pk
            primary key,
    details_type varchar not null,
    create_date timestamp without time zone not null,
    user_id int not null
        constraint details_w_user_id_fk
            references "w_user",
    customer_id int not null
        constraint details_customer_id_fk
            references customer,
    item_id int not null
        constraint details_item_id_fk
            references item,
    quantity numeric not null,
    additional_info varchar
);

create unique index details_id_uindex
    on details (id);