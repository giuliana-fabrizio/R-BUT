const postG = require('../db');
const queries = require('./queries');
const { post } = require('./router');

const getEntreprise = (req, res) => {
    postG.query(queries.getEntreprise, (error, result) => {
        if (error) throw error;
        else res.status(200).json(result.rows);
    });
}

const getProfesseursPlrsSoutenances = (req, res) => {
    const nb = parseInt(req.params.nb);
    postG.query(queries.getProfesseursPlrsSoutenances, [nb], (error, result) => {
        if (error) throw error;
        else res.status(200).json(result.rows);
    });
}

const addEntreprise = (req, res) => {
    const nomEntreprise = req.query.nomEntreprise;
    postG.query(queries.addEntreprise, [nomEntreprise], (error, result) => {
        if (error) throw error;
        else res.status(201).json(result.rows);
    });
}

const addProf = (req, res) => {
    const nomProfesseur = req.query.nomProfesseur;
    postG.query(queries.addProf, [nomProfesseur], (error, result) => {
        if (error) throw error;
        res.status(201).json(result.rows);
    });
}

/** ======================================================================================= dernier argument recuperer seulement */
const addJury = (req, res) => {
    const idSalle = parseInt(req.query.idSalle);
    const nomJury = req.query.nomJury;
    postG.query(queries.verifSalle, [idSalle], (error, result1) => {
        if (result1) {
            postG.query(queries.addJury, [nomJury, idSalle], (error, result2) => {
                if (error) throw error;
                else res.status(201).json(result2.rows);
            });
        }
        else res.send("Pas de salle correspondante.");
    });
}
/** ======================================================================================= dernier argument recuperer seulement */
const addSoutenance = (req, res) => {
    const {noEtudiant, idJury, dateSout, note} = req.query;
    if (note < 0 || note > 20) res.send("Note invalide car non comprise entre 0 et 20.");
    else {
        postG.query(queries.verifEtudiant, [noEtudiant], (error, result1) => {
            if (result1) {
                postG.query(queries.verifJury, [idJury], (error, result2) => {
                    if (result2.rows.length != 0) {
                        postG.query(queries.addSoutenance, [parseInt(noEtudiant), parseInt(idJury), dateSout, parseInt(note)],(error, result3) => {
                            if (error) return res.status(400).send({success : 0, data : error});
                            else res.status(201).json(result3.rows);
                        });
                    }
                    else res.send("Pas de jury correspondant.");
                });
            }
            else res.send("Pas d'étudiant correspondant.");
        });
    }
}

const rmEntreprise = (req, res) => {
    const idEntreprise = req.params.idEntreprise;
    postG.query(queries.verifEntreprise, [idEntreprise], (error, result1) => {
        if (result1.rows.length != 0) {
            postG.query(queries.verifEntrepriseLie, [idEntreprise], (error, result2) => {
                if (result2.rows.length > 0) res.send("L'entreprise est liée à un étudiant.");
                else {
                    postG.query(queries.rmEntreprise, [idEntreprise], (error, result3) => {
                        if (error) return res.status(400).send({success : 0, data : error});
                        else res.status(200).json(result3.rows);
                    });
                }
            });
        }
        else res.send("L'entreprise n'existe pas.");
    });
}

module.exports = {
    getEntreprise,
    getProfesseursPlrsSoutenances,
    addEntreprise,
    addProf,
    addJury,
    addSoutenance,
    rmEntreprise
}
