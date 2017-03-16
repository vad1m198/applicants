CREATE TABLE users
(
    id serial NOT NULL,
    first_name character varying(36) NOT NULL,
    last_name character varying(36) NOT NULL,
    email character varying(50) NOT NULL,
    password character varying(16) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email)
)

CREATE TABLE user_roles
(
    id serial NOT NULL,
    role character varying(50) NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (id),
    CONSTRAINT user_roles_role_key UNIQUE (role)
)

CREATE TABLE user_role_id_user_id
(
    user_id integer,
    role_id integer,
    CONSTRAINT user_role_id_user_id_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES public.user_roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_role_id_user_id_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE products
(
    code character varying(10) NOT NULL,
    name character varying(255) NOT NULL,
    price double precision NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (code)
)

CREATE TABLE shopping_cart_answers
(
    id serial NOT NULL,
    bugs text,
    test_cases text,
    improvements text,
    applicant_id integer,
    created_date timestamp without time zone NOT NULL DEFAULT now(),
    CONSTRAINT shopping_cart_answers_pkey PRIMARY KEY (id),
    CONSTRAINT shopping_cart_answers_applicant_id_fkey FOREIGN KEY (applicant_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

insert into users (first_name,last_name,email,password)
values ('Ivan', 'Ivanov', 'ivan.ivanov@test.com', 'password')

insert into user_roles (role) 
values ('SHOPPING_CART_USER')

insert into user_role_id_user_id
values(1,1)

insert into products
values ('P001', 'Samsung S8', '103'),('P002', 'Apple iPhone 7', '115')