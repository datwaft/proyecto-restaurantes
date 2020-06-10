insert into `User`(`email`, `password`, `name`, `client`, `admin`, `cellphone`) values 
("admin@mail.com", "password", "Administrator", 0, 1, "00000000"),
("user@mail.com", "password", "User", 1, 0, "87980934");
select * from `User`;