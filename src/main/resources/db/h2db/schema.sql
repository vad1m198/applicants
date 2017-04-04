CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50),
  second_name VARCHAR(50),
  email VARCHAR(50) UNIQUE,
  password VARCHAR(50)
);

CREATE TABLE user_roles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  role VARCHAR(50)
);

CREATE TABLE user_user_role (
  	user_id BIGINT,
	role_id BIGINT,
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (role_id) REFERENCES user_roles(id)
);

CREATE TABLE products (
    code VARCHAR(4) PRIMARY KEY,
    name VARCHAR(30),
    price DECIMAL(20, 2)
);

CREATE TABLE shopping_cart_answers (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	submitted BOOLEAN,
    bugs CLOB,
    ideas CLOB,
    improvements CLOB,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);