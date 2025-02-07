drop table if exists secompose;
drop table if exists soutient;
drop table if exists etudiant;
drop table if exists jury;
drop table if exists entreprise;
drop table if exists professeur;
drop table if exists salle;

create table salle (
    idSalle                int not null auto_increment,
    nomSalle                varchar(50),
    constraint pk_salle primary key (idSalle)
)
;

create table professeur (
    noProfesseur            int not null auto_increment,
    nomProfesseur           varchar(20),
    constraint pk_professeur primary key (noProfesseur)
)
;

create table entreprise (
    idEntreprise            int not null auto_increment,
    nomEntreprise           varchar(20),
    constraint pk_entreprise primary key (idEntreprise)
)
;

create table jury (
    idJury                  int not null auto_increment,
    nomJury                 varchar(20),
    idSalle                 int not null,
    constraint pk_jury primary key (idJury),
    constraint fk_sallejury foreign key (idSalle) references salle(idSalle)
)
;

create table etudiant (
    noEtudiant              int not null auto_increment,
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

/* ******************** rqt 1 ******************** */
-- select p.nomProfesseur
-- from professeur as p
-- where p.noProfesseur in (
--     select e.noProfesseur
--     from etudiant as e
--     where e.nomEtu like 'toto'
-- )
-- ;

select p.nomProfesseur
from professeur as p
inner join etudiant as etu on etu.noProfesseur = p.noProfesseur
where etu.nomEtu like 'toto'
;

/* ******************** rqt 2 ******************** */
select count(distinct noProfesseur)
from secompose
;

/* ******************** rqt 3 ******************** */
select p.nomProfesseur
from secompose as scp
inner join soutient as st on st.idJury = scp.idJury
inner join etudiant as etu on etu.noEtudiant = st.noEtudiant
inner join professeur as p on p.noProfesseur = scp.noProfesseur
where etu.nomEtu like 'toto'
;

/* ******************** rqt 4 ******************** */
select e.nomEtu
from etudiant as e
inner join soutient as st on st.noEtudiant = e.noEtudiant
where st.note in (
    select sout.note
    from soutient as sout
    inner join etudiant as etu on etu.noEtudiant = sout.noEtudiant
    where etu.nomEtu like 'toto'
)
;

/* ******************** rqt 5 ******************** */
select p.noProfesseur, p.nomProfesseur, sum(scp.noProfesseur) as nbSoutenances
from professeur as p
inner join secompose as scp on scp.noProfesseur = p.noProfesseur
having nbSoutenances > 5
;

/* ******************** rqt 6 ******************** */

