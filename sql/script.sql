insert into `admin`(`username`, `password`) values
('admin', 'password');

insert into `user`(`email`, `password`, `firstName`, `lastName`, `cellphone`) values
('user@mail.com', 'password', 'Default', 'User', '12345678'),
('david@mail.com', 'password', 'David', 'Guevara', '86081212'); 

insert into `address`(`user`, `address1`, `address2`, `city`, `state`, `postcode`, `country`) values
('user@mail.com', 'Del ConCasa 500m este 25m norte', 'Tercera casa después del parque a mano izquierda', 'San Francisco', 'Heredia', '50234', 'Costa Rica'),
('david@mail.com', 'Del AM PM 600m sur 25m oeste', 'Quinta casa después del bazar a mano derecha', 'San Bernardo', 'Heredia', '405645', 'Costa Rica'),
('david@mail.com', 'Del AM PM 400m norte 25m este', 'Segunda casa después del play a mano derecha', 'Heredia', 'Heredia', '10234', 'Costa Rica');


insert into `category`(`description`) values
('Appetizer'),
('Main Course'),
('Salads'),
('Seafoods'),
('Traditional'),
('Vegetarian'),
('Soups'),
('Desserts'),
('Drinks'),
('Specials'),
('Rice Dishes');

insert into `dish`(`category`, `price`, `name`, `description`) values
(1, 3900, "Vento d'Oro", 'Viento dorado bien ricolin'),
(1, 5000, "Vento d'Plata", 'Viento plateado bien ricolin tintin'),
(3, 49000, 'Espaguetti', 'Rico espaguetti'),
(4, 29000, 'Ensalada griega', 'Rica ensalada'),
(5, 45000, 'Ensalada rusa', 'Rica ensalada vegetariana');

select * from additionalCategory;
select * from additional;