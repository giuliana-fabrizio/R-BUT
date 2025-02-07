drop table if exists secompose;
drop table if exists soutient;
drop table if exists etudiant;
drop table if exists jury;
drop table if exists entreprise;
drop table if exists professeur;
drop table if exists salle;

create table salle (
    idSalle                serial,
    nomSalle                varchar(50),
    constraint pk_salle primary key (idSalle)
)
;

create table professeur (
    noProfesseur            serial,
    nomProfesseur           varchar(20),
    constraint pk_professeur primary key (noProfesseur)
)
;

create table entreprise (
    idEntreprise            serial,
    nomEntreprise           varchar(20),
    constraint pk_entreprise primary key (idEntreprise)
)
;

create table jury (
    idJury                  serial,
    nomJury                 varchar(20),
    idSalle                 int not null,
    constraint pk_jury primary key (idJury),
    constraint fk_sallejury foreign key (idSalle) references salle(idSalle)
)
;

create table etudiant (
    noEtudiant              serial,
    nomEtu                  varchar(20),
    tuteurPresent           varchar(20),
    idEntreprise            int not null,
    noProfesseur            int not null,
    constraint pk_etudiant primary key (noEtudiant),
    constraint fk_etrprsEtu foreign key (idEntreprise) references entreprise(idEntreprise),
    constraint fk_profEtu foreign key (noProfesseur) references professeur(noProfesseur)
)
;

create table soutient (
    noEtudiant              int not null,
    idJury                  int not null,
    dateSout                date,
    note                    int,
    constraint pk_soutient primary key (noEtudiant, idJury, dateSout),
    constraint fk_etuSoutient foreign key (noEtudiant) references etudiant(noEtudiant),
    constraint fk_jurySoutient foreign key (idJury) references jury(idJury)
)
;

create table secompose (
    idJury                  int not null,
    noProfesseur            int not null,
    constraint pk_secompose primary key (idJury, noProfesseur),
    constraint fk_jurySecompose foreign key (idJury) references jury(idJury),
    constraint fk_profSecompose foreign key (noProfesseur) references professeur(noProfesseur)
)
;
