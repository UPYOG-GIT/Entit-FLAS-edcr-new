CREATE TABLE IF NOT EXISTS state.flas_rooms
(
    id bigint PRIMARY KEY,
    applicationnumber character varying(150),
    name character varying(150),
    area character varying(64),
    minheight character varying(64),
    maxheight character varying(64),
    avgheight character varying(64),
    length character varying(64),
    breadth character varying(64),
    totalarea character varying(64),
    breathingspace character varying(64),
    createddate timestamp without time zone
);


CREATE SEQUENCE IF NOT EXISTS seq_edcr_flas
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;