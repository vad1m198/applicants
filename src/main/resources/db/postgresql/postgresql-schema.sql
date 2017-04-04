drop table if exists user_user_role;
drop table if exists shopping_cart_answers;
drop table if exists users;
drop table if exists user_roles;
drop table if exists products;


CREATE TABLE users (
  id bigserial PRIMARY KEY,
  first_name VARCHAR(50),
  second_name VARCHAR(50),
  email VARCHAR(50) UNIQUE,
  password VARCHAR(50)
);

CREATE TABLE user_roles (
  id bigserial PRIMARY KEY,
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
	id bigserial  PRIMARY KEY,
	submitted BOOLEAN,
    bugs text,
    ideas text,
    improvements text,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);