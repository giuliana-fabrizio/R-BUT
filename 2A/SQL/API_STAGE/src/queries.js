const getEntreprise = "select * from entreprise;";

const getProfesseursPlrsSoutenances = "select p.noProfesseur, p.nomProfesseur, count(scp.noProfesseur)\
                                    from soutient as st\
                                    inner join secompose as scp on scp.idJury = st.idJury\
                                    inner join professeur as p on p.noProfesseur = scp.noProfesseur\
                                    group by p.noProfesseur\
                                    having count(scp.noProfesseur) >= $1\
                                    ;";

const addEntreprise = "insert into entreprise values (default, $1)";

const addProf = "insert into professeur values (default, $1)";

const verifSalle = "select * from salle where idSalle = $1;";

const addJury = "insert into jury values (default, $1, $2);";

const verifEtudiant = "select * from etudiant where noEtudiant = $1;";

const verifJury = "select * from jury where idJury = $1;";

const addSoutenance = "insert into soutient values ($1, $2, $3, $4);";

const verifEntreprise = "select * from entreprise where idEntreprise = $1;";

const verifEntrepriseLie = "select * from etudiant where idEntreprise = $1;";

const rmEntreprise = "delete from entreprise where idEntreprise = $1;";

module.exports = {
    getEntreprise,
    getProfesseursPlrsSoutenances,
    addEntreprise,
    addProf,
    verifSalle,
    addJury,
    verifEtudiant,
    verifJury,
    addSoutenance,
    verifEntreprise,
    verifEntrepriseLie,
    rmEntreprise
}
