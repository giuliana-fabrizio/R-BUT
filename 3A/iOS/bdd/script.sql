drop table if exists users;
-- drop table if exists companies;
drop table if exists places;

-- create table companies (
--     id              serial,
--     name_company    varchar(20),
--     constraint pk_companies primary key (id)
-- );

create table places (
    id                  serial,
    name_place          varchar(20),
    description_place   TEXT,
    email               VARCHAR(255),
    telephone           VARCHAR(20),
    site_web            VARCHAR(255),
    latitude            numeric(9, 6),
    longitude           numeric(9, 6),
    constraint pk_place primary key (id)
);


CREATE TABLE users (
    id              SERIAL,
    age             INT,
    company         VARCHAR(100),
    firstname       VARCHAR(20),
    gender          VARCHAR(10),
    name_user       VARCHAR(20),
    job             VARCHAR(60),
    email           VARCHAR(100),
    type_user        VARCHAR(50),
    mdp             VARCHAR(255),
    id_place        INT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT fk_users_places FOREIGN KEY (id_place) REFERENCES places(id)
);

