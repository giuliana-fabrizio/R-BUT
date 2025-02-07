DROP TABLE IF EXISTS obtient, prizes, category, laureates;

CREATE TABLE laureates(
   id_lrt INT,
   firstname VARCHAR(100),
   surname VARCHAR(100),
   CONSTRAINT pk_laureates PRIMARY KEY(id_lrt)
);

CREATE TABLE category(
   id_category SERIAL,
   libelle_category VARCHAR(100),
   CONSTRAINT pk_category PRIMARY KEY(id_category)
);

CREATE TABLE prizes(
   id_prize SERIAL,
   annee INT,
   id_category INT,
   CONSTRAINT pk_prizes PRIMARY KEY(id_prize),
   CONSTRAINT fk_category_prizes FOREIGN KEY(id_category) REFERENCES category(id_category)
);

CREATE TABLE obtient(
   id_lrt INT,
   id_prize INT,
   motivation TEXT,
   share INT,
   CONSTRAINT pk_obtient PRIMARY KEY(id_lrt, id_prize),
   CONSTRAINT fk_laureates_obtient FOREIGN KEY(id_lrt) REFERENCES laureates(id_lrt) ON DELETE CASCADE,
   CONSTRAINT fk_prizes_obtient FOREIGN KEY(id_prize) REFERENCES prizes(id_prize)
);
