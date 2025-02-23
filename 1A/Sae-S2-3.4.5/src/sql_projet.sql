DROP TABLE IF EXISTS avis;
DROP TABLE IF EXISTS ligne_de_commande;
DROP TABLE IF EXISTS panier;
DROP TABLE IF EXISTS commande;
DROP TABLE IF EXISTS colore;
DROP TABLE IF EXISTS meubles;
DROP TABLE IF EXISTS materiaux;
DROP TABLE IF EXISTS couleur;
DROP TABLE IF EXISTS types_meubles;
DROP TABLE IF EXISTS fourniseur;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS etat;

CREATE TABLE IF NOT EXISTS types_meubles (
    id_type             INT UNSIGNED AUTO_INCREMENT,
    libelle_type        VARCHAR(50),
    PRIMARY KEY (id_type)
);

CREATE TABLE IF NOT EXISTS couleur (
    id_couleur         INT UNSIGNED AUTO_INCREMENT,
    libelle_couleur    VARCHAR (50),
    PRIMARY KEY (id_couleur)
);

CREATE TABLE IF NOT EXISTS materiaux (
    id_materiaux        INT UNSIGNED AUTO_INCREMENT,
    libelle_materiaux   VARCHAR(50),
    PRIMARY KEY (id_materiaux)
);

CREATE TABLE IF NOT EXISTS fourniseur(
   id_Fourniseur        INT UNSIGNED AUTO_INCREMENT,
   libelle_Fourniseur   VARCHAR(50),
   PRIMARY KEY(id_Fourniseur)
);

CREATE TABLE IF NOT EXISTS user (
    id_User             INT UNSIGNED AUTO_INCREMENT,
    username            VARCHAR(25),
    password            VARCHAR (255),
    role                VARCHAR (25),
    est_actif           VARCHAR (1),
    email               VARCHAR(50),
    PRIMARY KEY (id_User)
);

CREATE TABLE IF NOT EXISTS etat(
   id_etat              INT UNSIGNED AUTO_INCREMENT,
   libelle_etat         VARCHAR(50),
   PRIMARY KEY(id_etat)
);

CREATE TABLE IF NOT EXISTS meubles(
    id_meuble           INT UNSIGNED AUTO_INCREMENT,
    nom                 VARCHAR(50),
    prix                NUMERIC(12,2),
    dateFabrication     DATE,
    id_MeubleType       INT UNSIGNED,
    id_MeubleFourniseur INT UNSIGNED,
    image               VARCHAR(255),
PRIMARY KEY (id_meuble),
    CONSTRAINT fk_MeubleType 
        FOREIGN KEY (id_MeubleType) REFERENCES types_meubles(id_type) ON DELETE CASCADE,
    CONSTRAINT fk_MeubleFournisseur 
        FOREIGN KEY(id_MeubleFourniseur) REFERENCES fourniseur(id_Fourniseur) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS colore(
    id_ColoreMeuble     INT UNSIGNED,
    id_ColoreCouleur    INT UNSIGNED,
    id_ColoreMateriaux  INT UNSIGNED,
    nbStock             INT,
    PRIMARY KEY (id_ColoreMeuble, id_ColoreCouleur, id_ColoreMateriaux),
    CONSTRAINT fk_id_ColoreMeuble
        FOREIGN KEY (id_ColoreMeuble) REFERENCES meubles(id_meuble),
    CONSTRAINT fk_id_ColoreCouleur
        FOREIGN KEY (id_ColoreCouleur) REFERENCES couleur(id_couleur),
    CONSTRAINT fk_id_ColoreMateriaux
        FOREIGN KEY (id_ColoreMateriaux) REFERENCES materiaux(id_materiaux)
)
;

CREATE TABLE IF NOT EXISTS commande(
   id_cmd           INT UNSIGNED AUTO_INCREMENT,
   date_achat       DATE,
   id_CmdEtat       INT UNSIGNED,
   id_CmdUser       INT UNSIGNED,
   PRIMARY KEY(id_cmd),
   CONSTRAINT fk_CommandeEtat
       FOREIGN KEY(id_CmdEtat) REFERENCES etat(id_etat),
   CONSTRAINT fk_CommandeUser
       FOREIGN KEY(id_CmdUser) REFERENCES user(id_User)
);

CREATE TABLE IF NOT EXISTS panier(
   id_ajout                 INT UNSIGNED AUTO_INCREMENT,
   date_ajout               DATE,
   panier_quantite          INT,
   id_PanierUser            INT UNSIGNED,
   id_PanierMeuble          INT UNSIGNED,
   id_PanierCouleur         INT UNSIGNED,
   id_PanierMateriaux       INT UNSIGNED,
   PRIMARY KEY(id_ajout),
   CONSTRAINT fk_idPanierUser
       FOREIGN KEY(id_PanierUser) REFERENCES user(id_User) ON DELETE CASCADE,
   CONSTRAINT fk_idPanierMeuble
       FOREIGN KEY(id_PanierMeuble) REFERENCES meubles(id_meuble) ON DELETE CASCADE,
    CONSTRAINT fk_idPanierCouleur
       FOREIGN KEY(id_PanierCouleur) REFERENCES couleur(id_couleur) ON DELETE CASCADE,
    CONSTRAINT fk_idPanierMateriaux
       FOREIGN KEY(id_PanierMateriaux) REFERENCES materiaux(id_materiaux) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ligne_de_commande(
   id_LigneMeuble       INT UNSIGNED,
   id_LigneCmd          INT UNSIGNED,
   id_LigneCouleur      INT UNSIGNED,
   id_LigneMateriaux    INT UNSIGNED,
   prix_unit            NUMERIC(12,2),
   quantite             INT,
   PRIMARY KEY(id_LigneMeuble, id_LigneCmd, id_LigneCouleur, id_LigneMateriaux),
   CONSTRAINT fk_idLigneMeuble
       FOREIGN KEY(id_LigneMeuble) REFERENCES meubles(id_meuble),
   CONSTRAINT fk_idLigneCmd
       FOREIGN KEY(id_LigneCmd) REFERENCES commande(id_cmd),
    CONSTRAINT fk_idLigneCouleur
       FOREIGN KEY(id_LigneCouleur) REFERENCES couleur(id_couleur),
    CONSTRAINT fk_idLigneMateriaux
       FOREIGN KEY(id_LigneMateriaux) REFERENCES materiaux(id_materiaux)
);

CREATE TABLE IF NOT EXISTS avis(
    id_avis                     INT UNSIGNED AUTO_INCREMENT,
    libelle_avis                varchar(500),
    note                        NUMERIC(2,1),
    id_AvisUser                 INT UNSIGNED,
    id_AvisMeuble               INT UNSIGNED,
    PRIMARY KEY (id_avis),
    CONSTRAINT fk_id_AvisUser FOREIGN KEY (id_AvisUser) REFERENCES user(id_User),
    CONSTRAINT fk_id_AvisMeuble FOREIGN KEY (id_AvisMeuble) REFERENCES meubles(id_meuble)
);


INSERT INTO types_meubles(libelle_type) VALUES
    ('canape'),
    ('lit'),
    ('commode'),
    ('armoire'),
    ('table'),
    ('cabinet');

INSERT INTO couleur(libelle_couleur) VALUES
    ('rouge'),
    ('vert'),
    ('jaune'),
    ('bleu'),
    ('ebene'),
    ('magenta'),
    ('violet'),
    ('gris'),
    ('satin');

INSERT INTO materiaux(libelle_materiaux) VALUES
    ('bois'),
    ('acier'),
    ('verre'),
    ('aluminium'),
    ('marbre'),
    ('porcelaine');

INSERT INTO fourniseur(libelle_Fourniseur) VALUES
    ('ikea'),
    ('Lapeyre'),
    ('But'),
    ('Conforama'),
    ('Alinea'),
    ('Roche Bobois'),
    ('PerrotConception');

INSERT INTO user(username, password, role, est_actif, email) VALUES
    ('admin', 'sha256$pBGlZy6UukyHBFDH$2f089c1d26f2741b68c9218a68bfe2e25dbb069c27868a027dad03bcb3d7f69a', 'ROLE_admin', 1, 'admin@admin.fr'),
    ('client', 'sha256$Q1HFT4TKRqnMhlTj$cf3c84ea646430c98d4877769c7c5d2cce1edd10c7eccd2c1f9d6114b74b81c4', 'ROLE_client', 1, 'client@client.fr'),
    ('client2', 'sha256$ayiON3nJITfetaS8$0e039802d6fac2222e264f5a1e2b94b347501d040d71cfa4264cad6067cf5cf3', 'ROLE_client', 1, 'client2@client2.fr'),
    ('Mafuo', 'sha256$Q1HFT4TKRqnMhlTj$cf3c84ea646430c98d4877769c7c5d2cce1edd10c7eccd2c1f9d6114b74b81c4', 'ROLE_client', 1, 'Mafuo@gmail.com');

INSERT INTO etat (libelle_etat) VALUES
    ('en attente'),
    ('expedier'),
    ('valider'),
    ('confirmer');

INSERT INTO meubles(nom,prix,dateFabrication,id_MeubleType,id_MeubleFourniseur,image) VALUES
    ('klippan', '50.20', '2020-06-06', 1, 3, 'klippan.png'),
    ('malm', '12.00', '2020-06-06', 2, 5, 'malm.png'),
    ('besta', '14.36', '2020-06-06', 3, 6, 'besta.jpg'),
    ('billy', '24.59', '2020-06-06', 4, 2, 'billy.png'),
    ('friheten', '42.21', '2020-06-06', 1, 5, 'friheten.png'),
    ('brimnes', '11.11', '2019-06-06', 2, 5, 'brimnes.png'),
    ('la commode du luxe', '35.21', '2019-06-06', 3, 1, 'commodeLuxe.jpg'),
    ('lack', '32.25', '2019-12-06', 4, 7, 'lack.png'),
    ('toilette','800','2020-12-15', 6, 3, 'toilette.jpg'),
    ('vilme', '52.36', '2019-12-26', 1, 4, 'vilme.png');

INSERT INTO colore(id_ColoreMeuble, id_ColoreCouleur, id_ColoreMateriaux, nbStock) VALUES
    (1, 5, 6, 45),
    (2, 5, 5, 78),
    (2, 8, 6, 7),
    (4, 2, 1, 156),
    (7, 8, 2, 48),
    (3, 2, 3, 1),
    (4, 9, 4,  15),
    (1, 7, 1,  25),
    (8, 9, 3, 13),
    (1, 2, 2, 15),
    (5, 1, 2, 10),
    (3, 8, 6, 89);

INSERT INTO commande (date_achat, id_CmdEtat, id_CmdUser) VALUES
    ('2003-01-01',4,4),
    ('2015-08-15',2,2),
    ('2020-07-20',3,3),
    ('2021-02-01',1,4);

INSERT INTO panier (date_ajout, panier_quantite, id_PanierUser, id_PanierMeuble, id_PanierCouleur, id_PanierMateriaux) VALUES
    ('2003-01-01',2,4,8,9,6),
    ('2015-08-15',5,2,1,2,2),
    ('2020-07-20',10,3,5,1,1),
    ('2021-02-01',1,1,3,8,4);

INSERT INTO ligne_de_commande (id_LigneMeuble, id_LigneCmd, id_LigneCouleur, id_LigneMateriaux, prix_unit, quantite) VALUES
    (8,1,9,2,'800',2),
    (3,2,2,3,'14.36',1),
    (1,3,4,5,'50.20',2),
    (9,4,3,5,'52.36',5);

INSERT INTO avis(libelle_avis, note, id_AvisUser, id_AvisMeuble) VALUES
    ('Très contente de ce produit, la qualité est au rendez-vous pour un prix dérisoire.', '4.8', 2, 1);