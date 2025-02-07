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
select p.noProfesseur, p.nomProfesseur, count(scp.noProfesseur)
from professeur as p
inner join secompose as scp on scp.noProfesseur = p.noProfesseur
group by p.noProfesseur
-- having count(scp.noProfesseur) > 5
;

select p.noProfesseur, p.nomProfesseur, count(scp.noProfesseur)
from soutient as st
inner join secompose as scp on scp.idJury = st.idJury
inner join professeur as p on p.noProfesseur = scp.noProfesseur
group by p.noProfesseur
having count(scp.noProfesseur) >= 5
;

/* ******************** rqt 6 ******************** */
select etu.nomEtu, s.nomSalle, p.nomProfesseur, st.dateSout, e.nomEntreprise
from etudiant as etu
inner join entreprise as e on e.idEntreprise = etu.idEntreprise
inner join professeur as p on p.noProfesseur = etu.noProfesseur
inner join soutient as st on st.noEtudiant = etu.noEtudiant
inner join jury as j on j.idJury = st.idJury
inner join salle as s on s.idSalle = j.idJury
;

/* ******************** rqt 7 ******************** */
-- select s.idSalle as noSalle, s.nomSalle, count(st.noEtudiant, st.idJury, st.dateSout)
-- from salle as s
-- inner join jury as j on j.
