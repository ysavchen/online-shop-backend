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

alter table books
add foreign key (author_id) references authors(id) on delete cascade;