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
    email_id bigint
);

drop table if exists addresses;
create table addresses (
    id bigint primary key auto_increment,
    address varchar(255) not null,
    zip_code varchar(255)
);

drop table if exists phones;
create table phones (
    id bigint primary key auto_increment,
    phone varchar(255) not null
);

drop table if exists emails;
create table emails (
    id bigint primary key auto_increment,
    email varchar(255) not null
);

drop table if exists order_to_books;
create table order_to_books (
    order_id bigint not null,
    book_id bigint not null
);

drop table if exists users;
create table users (
    id bigint primary key auto_increment,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email_id bigint not null,
    password varchar(255) not null
);

alter table books
add foreign key (author_id) references authors(id) on delete cascade;

alter table orders
add foreign key (address_id) references addresses(id) on delete cascade;

alter table orders
add foreign key (phone_id) references phones(id) on delete cascade;

alter table orders
add foreign key (email_id) references emails(id) on delete cascade;

alter table users
add foreign key (email_id) references emails(id) on delete cascade;