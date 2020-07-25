
CREATE DATABASE news_portal;
\c news_portal;
CREATE TABLE news(id serial PRIMARY KEY, content VARCHAR, publisher VARCHAR);
CREATE TABLE department_news(id serial PRIMARY KEY, content VARCHAR, publisher VARCHAR, departmentId INTEGER);
CREATE TABLE users(id serial PRIMARY KEY, position VARCHAR, role VARCHAR, department VARCHAR);
CREATE TABLE departments(id serial PRIMARY KEY, departmentId INTEGER);

CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;

