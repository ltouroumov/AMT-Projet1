DROP DATABASE IF EXISTS amt;

CREATE DATABASE amt;

USE amt;

CREATE TABLE users (
  username VARCHAR(1024) PRIMARY KEY,
  password VARCHAR(128),
  email VARCHAR(256),
  first_name VARCHAR(1014),
  last_name VARCHAR(1024)
);