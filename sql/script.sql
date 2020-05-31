insert into `User`(`id`, `password`, `client`, `administrator`, `name`, `cellphone`) values 
("admin", "password", 0, 1, "Administrator", "00000000"),
("user", "password", 1, 0, "User", "87980934");
select * from `User`;