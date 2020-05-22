insert into authors(id, full_name) values (1, 'Author One');
insert into authors(id, full_name) values (2, 'Author Two');

insert into books (id, title, description, author_id, image, price) values (1, 'Book One', 'Description One', 1, '/imageOne', 22.95);
insert into books (id, title, description, author_id, image, price) values (2, 'Book Two', 'Description Two', 2, '/imageTwo', 46.00);

insert into users (id, first_name, last_name, email, password) values (1, 'John', 'Doe', 'john.doe@test.com', '$2a$10$Wfn//hRL3NrA9DG0fYRtYuhzLZc8CLDNKvv4Twcx55XEDsWABlD8K')