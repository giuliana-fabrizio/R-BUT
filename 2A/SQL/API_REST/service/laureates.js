import postG from "../db.js";
import {lrt, infoLrt, plrsPrix, nbPrixParAnnee, nbPrixParAnneeTrieAsc, nbPrixParAnneeTrieDesc, supLrt, verifId, verifAnnee, verifCat, updateMot} from "../bdd/rqts.js";

export default class FSLaureates {

// ====================================================================== F1
    async listerLaureates(callback) {
        postG.query(lrt, (error, result) => {
            if (error || (result.rowCount == 0)) return callback(error, "Pas de résultat");
            return callback(null, result.rows);
        });
    }

// ====================================================================== F2
    async infoPrix(id, callback) {
        postG.query(infoLrt, [id], (error, result) => {
            if (error || (result.rowCount == 0)) return callback(error, "Pas de lauréat correspondant");
            return callback(null, result.rows);
        });
    }

// ====================================================================== F3

    async plrsPrix(callback) {
        postG.query(plrsPrix, (error, result) => {
            if (error || (result.rowCount == 0)) return callback(error, "Pas de résultat");
            return callback(null, result.rows);
        });
    }
// ====================================================================== F6

    async nbLaureates(callback) {
        postG.query(nbPrixParAnnee, (error, result) => {
            if (error || (result.rowCount == 0)) return callback(error, "Pas de résultat");
            return callback(null, result.rows);
        });
    }

// ====================================================================== F8

    async nbLaureatesTrieAsc(callback) {
        postG.query(nbPrixParAnneeTrieAsc, (error, result) => {
            if (error || (result.rowCount == 0)) return callback(error, "Pas de résultat");
            return callback(null, result.rows);
        });
    }

    async nbLaureatesTrieDesc(callback) {
        postG.query(nbPrixParAnneeTrieDesc, (error, result) => {
            if (error || (result.rowCount == 0)) return callback(error, "Pas de résultat");
            return callback(null, result.rows);
        });
    }

// ====================================================================== F9
    async deleteLaureat(id, callback) {
        postG.query(supLrt, [id], (error, result) => {
            if (error || (result.rowCount == 0)) return callback(error, "Pas de lauréat correspondant");
            return callback(null, "Lauréat supprimé");
        });
    }

// ====================================================================== F10
    async updateMotivation(id, annee, cat, motivation, callback) {

        try {
            const verifieId = await postG.query(verifId, [id]);
            if (verifieId.rowCount == 0) return callback(null, "Pas de lauréat correspondant");

            const verifieAnnee = await postG.query(verifAnnee, [annee]);
            if (verifieAnnee.rowCount == 0) return callback(null, "Pas d'année correspondante");

            const verifieCat = await postG.query(verifCat, [cat]);
            if (verifieCat.rowCount == 0) return callback(null, "Pas de categorie correspondante");
        } catch (error) {
            return callback(error);
        }

        postG.query(updateMot, [motivation, id, annee, cat], (error, result) => {
            if (error || result.rowCount == 0) return callback(error,
                "Modification impossible, le lauréat n'existe pas pour cette année et - ou cette catégorie là");
            return callback(null, "Motivation modifiée");
        });
    }
}
