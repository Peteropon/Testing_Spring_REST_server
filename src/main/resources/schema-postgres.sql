DROP TABLE IF EXISTS flights;
CREATE TABLE flights(id serial PRIMARY KEY, start varchar(100), destination varchar(100), duration integer not null);