INSERT INTO users 
VALUES ('1','admin', 'admin', 'admin@test.com', '$2a$06$HvSOiTRUpM9fPMdbUsC5oeAriCGLRTShX9wlb667sIs2yZ7FGuGcG'),
		('2','user', 'user', 'user@test.com', '$2a$06$HvSOiTRUpM9fPMdbUsC5oeAriCGLRTShX9wlb667sIs2yZ7FGuGcG');

INSERT INTO user_roles
VALUES ('1','ADMIN'),('2','SHOPPING_CART_USER');

INSERT INTO user_user_role 
VALUES ('1', '1'),('2', '2');

INSERT INTO products
VALUES ('P001', 'Samsung S8', '110'),('P002', 'Apple iPhone 7', '115'), ('P003', 'Samsung S7', '93'),('P004', 'Apple iPhone 6', '99'),
		('P005', 'Xiaomi Mi Mix', '113'),('P006', 'Motorola Moto Z', '89'),('P007', 'ZTE Axon 7 Grey', '101'),('P008', 'HTC 10', '125'),
		('P009', 'Alcatel One Touch', '100'),('P010', 'LeEco Le Pro 3', '75');
    