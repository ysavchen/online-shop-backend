insert into genres(id, name) values (1, 'Computers & Technology');

insert into authors(id, name, surname) values (1, 'Philip', 'Pratt');
insert into authors(id, name, surname) values (2, 'Michael', 'Learn');

insert into books (id, title, author_id, genre_id) values (1, 'A Guide to SQL', 1, 1);
insert into books (id, title, author_id, genre_id) values (2, 'Concepts of Database Management', 1, 1);
insert into books (id, title, author_id, genre_id) values (3, 'SQL Programming and Coding', 2, 1);