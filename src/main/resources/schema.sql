drop table if exists books;
create table books (
    id bigint primary key auto_increment,
    title varchar(255) not null,
    description clob,
    author_id bigint,
    image varchar(255),
    price double not null
);

drop table if exists authors;
create table authors (
    id bigint primary key auto_increment,
    full_name varchar(255) not null
);

drop table if exists orders;
create table orders (
    id bigint primary key auto_increment,
    addressee_name varchar(255) not null,
    address_id bigint,
    phone_id bigint,
    email varchar(255),
    created_at timestamp
);

drop table if exists addresses;
create table addresses (
    id bigint primary key auto_increment,
    address varchar(255) not null
);

drop table if exists phones;
create table phones (
    id bigint primary key auto_increment,
    phone varchar(255) not null
);

drop table if exists order_book;
create table order_book (
    order_id bigint not null,
    book_id bigint not null
);

drop table if exists users;
create table users (
    id bigint primary key auto_increment,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null
);

alter table books
add foreign key (author_id) references authors(id);

alter table orders
add foreign key (address_id) references addresses(id);

alter table orders
add foreign key (phone_id) references phones(id);