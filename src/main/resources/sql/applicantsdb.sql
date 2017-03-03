-- Database: applicantsdb

-- DROP DATABASE applicantsdb;

CREATE DATABASE applicantsdb;
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
USE applicantsdb;

CREATE TABLE public.users
(
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    first_name character varying(36) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(36) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(16) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;
	
INSERT INTO users (first_name, last_name, email, password)
VALUES ('Ivan', 'Ivanov', 'test@example.com', 'qwerty');

    create table products (
        code varchar(20) not null unique,
        name varchar(255) not null,
        price double precision not null,
        primary key (Code)
    );
    
    insert into products (code, name, price)
	values ('P002', 'Samsung S7', 99);