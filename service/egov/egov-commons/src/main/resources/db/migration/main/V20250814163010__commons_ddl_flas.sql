CREATE TABLE IF NOT EXISTS flas_rooms (
    id serial primary key,
    name character varying(150) NOT NULL,
	area numeric (10,2),
	minheight numeric (10,2),
	maxheight numeric (10,2),
	avgheight numeric (10,2),
	length numeric (10,2),
	breadth numeric (10,2),
	totalarea numeric (10,2),
	breathingspace numeric (10,2),
    createddate timestamp without time zone
);