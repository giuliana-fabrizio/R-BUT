Drop table if exists Reserve;
Drop table if exists Trajet;
Drop table if exists Vehicule;
Drop table if exists Habitant;
Drop table if exists Marque;
Drop table if exists Couleur;
Drop table if exists Ville;


CREATE TABLE Marque
(
    Id_marque  INT auto_increment,
    Nom_Marque VARCHAR(50),
    PRIMARY KEY (Id_marque)
);

CREATE TABLE Couleur
(
    Id_couleur  INT auto_increment,
    Nom_Couleur VARCHAR(50),
    PRIMARY KEY (Id_couleur)
);

CREATE TABLE Ville
(
    Id_ville  INT auto_increment,
    Nom_ville VARCHAR(50),
    PRIMARY KEY (Id_ville)
);

CREATE TABLE Habitant
(
    Id_Inscription INT auto_increment,
    Nom            VARCHAR(50),
    Prenom         VARCHAR(25),
    Adresse        VARCHAR(50),
    Date_naissance DATE,
    Telephone      VARCHAR(10),
    Id_ville       INT NOT NULL,
    PRIMARY KEY (Id_Inscription),
    FOREIGN KEY (Id_ville) REFERENCES Ville (Id_ville) on delete cascade
);

CREATE TABLE Vehicule
(
    Immat          VARCHAR(9) NOT NULL,
    Id_marque      INT        NOT NULL,
    Id_couleur     INT        NOT NULL,
    Id_Inscription INT        NOT NULL,
    PRIMARY KEY (Immat),
    FOREIGN KEY (Id_marque) REFERENCES Marque (Id_marque) on delete cascade,
    FOREIGN KEY (Id_couleur) REFERENCES Couleur (Id_couleur) on delete cascade,
    FOREIGN KEY (Id_Inscription) REFERENCES Habitant (Id_Inscription) on delete cascade
);

CREATE TABLE Trajet
(
    Id_trajet      INT auto_increment,
    Horaire_depart DATETIME,
    Nb_Place       INT,
    Id_ville_A     INT        NOT NULL,
    Id_ville_R     INT        NOT NULL,
    Immat          VARCHAR(9) NOT NULL,
    Id_Inscription INT        NOT NULL,
    PRIMARY KEY (Id_trajet),
    FOREIGN KEY (Id_ville_A) REFERENCES Ville (Id_ville) on delete cascade,
    FOREIGN KEY (Id_ville_R) REFERENCES Ville (Id_ville) on delete cascade,
    FOREIGN KEY (Immat) REFERENCES Vehicule (Immat) on delete cascade,
    FOREIGN KEY (Id_Inscription) REFERENCES Habitant (Id_Inscription) on delete cascade
);


CREATE TABLE Reserve
(
    Id_Inscription INT NOT NULL,
    Id_trajet      INT NOT NULL,
    PRIMARY KEY (Id_Inscription, Id_trajet),
    FOREIGN KEY (Id_Inscription) REFERENCES Habitant (Id_Inscription) on delete cascade,
    FOREIGN KEY (Id_trajet) REFERENCES Trajet (Id_trajet) on delete cascade
);

Select *
from Trajet;

INSERT INTO Marque (Nom_Marque)
VALUES ('Renault'),
       ('Volks Wagen'),
       ('Fiat');

INSERT INTO Couleur (Nom_Couleur)
VALUES ('Bleu'),
       ('Rouge'),
       ('Vert');

INSERT INTO Ville (Nom_ville)
VALUES ('Eloie'),
       ('Botans'),
       ('Autrechene');

INSERT INTO Habitant (Nom, Prenom, Adresse, Date_naissance, Telephone, Id_ville)
VALUES ('Richard', 'Luc', '10rue de la peste', '1985-04-12', '0666066606', 2),
       ('Dubois', 'Gorgette', '4 rue du filon', '1921-10-05', '0600000000', 1),
       ('Benzai', 'Shiotomata', 'Chem. du Gros Chêne', '2000-09-01', '0612345678', 3);

INSERT INTO Vehicule (Immat, Id_marque, Id_couleur, Id_Inscription)
VALUES ('AA123AA', 2, 1, 3),
       ('BB123BB', 3, 2, 1),
       ('CC123CC', 2, 3, 1);

INSERT INTO Trajet (Horaire_depart, Nb_Place, Id_ville_A, Id_ville_R, Immat, Id_Inscription)
VALUES ('2021-01-01 20:10:10', 3, 1, 2, 'AA123AA', 1),
       ('2021-02-02 20:10:10', 2, 2, 1, 'BB123BB', 2),
       ('2018-09-24 22:21:20', 2, 2, 1, 'BB123BB', 2);

INSERT INTO Reserve (Id_Inscription, Id_trajet)
VALUES (2, 1),
       (3, 1),
       (3, 2),
       (1, 1);

Select *
from Trajet;

-- requête Trajet--

-- DELETE FROM Trajet
-- WHERE Nb_place=3;

-- UPDATE Trajet
-- SET Nb_place=4
-- WHERE Immat ='BB123BB';
-- Select * from Trajet;

-- Nombre de trajets proposé --
SELECT count(Id_trajet) AS Nb_trajet_propose_par_personne, Habitant.Nom, Habitant.Prenom
FROM Trajet
         INNER JOIN Habitant ON Habitant.Id_Inscription = Trajet.Id_Inscription
GROUP BY Habitant.Nom, Habitant.Prenom
ORDER BY Habitant.Nom;

-- Nombre de trajets par année --
SELECT COUNT(Trajet.Id_trajet) AS Nombre_de_trajet,
    YEAR (Trajet.Horaire_depart) AS Année
FROM Trajet
GROUP BY YEAR (Horaire_depart);



-- requête Véhicule --

/*UPDATE Vehicule
SET Id_couleur=3
WHERE Id_marque=3;*/


/*delete from Vehicule where Immat = 'BB123BB';*/


-- Nombre de voiture par marque --
SELECT count(Immat) AS Nb_vehicule_par_marque, Marque.Nom_Marque
FROM Vehicule
         INNER JOIN Marque ON Marque.Id_marque = Vehicule.Id_marque
GROUP BY Marque.Nom_Marque
ORDER BY Marque.Nom_Marque;



-- requête Habitant --

/*update Habitant set Nom='Banzai' where Nom='Benzai';
select * from Habitant;*/

/*delete from Habitant where Id_Inscription=2;
select * from Habitant;*/

-- Compte le nombre d'inscriptions --
select count(Id_Inscription) as Nombre_habitant_inscrits
from Habitant;

-- requête Reserve --

/*DELETE from Reserve
WHERE Id_trajet = 1 AND Id_Inscription = 1;
SELECT * from Reserve;*/


-- On trace les reservations de Georgette --
SELECT CONCAT(H.Prenom, ' ', H.Nom) AS Passager,
       H.Telephone                  AS Telephone_Passager,
       T.Horaire_depart,
       D.Nom_ville                  AS Départ,
       A.Nom_ville                  AS Arrivé,
       T.Immat,
       CONCAT(C.Prenom, ' ', C.Nom) AS Conducteur,
       C.Telephone                  AS Telephone_Conducteur
FROM Reserve R
         INNER JOIN Habitant H on R.Id_Inscription = H.Id_Inscription
         INNER JOIN Trajet T on R.Id_trajet = T.Id_trajet
         INNER JOIN Ville D on T.Id_ville_A = D.Id_ville
         INNER JOIN Ville A on T.Id_ville_R = A.Id_ville
         INNER JOIN Habitant C on T.Id_Inscription = C.Id_Inscription
WHERE H.Id_Inscription = 2;

-- Compte le nombre de reservation par personne --
SELECT COUNT(*) AS Nb_Reservation, CONCAT(H.Prenom, ' ', H.Nom) AS Passager
FROM Reserve R
         INNER JOIN Habitant H on R.Id_Inscription = H.Id_Inscription
GROUP BY Passager;