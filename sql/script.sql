insert into `admin`(`username`, `password`) values
('admin', 'password');

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