import postG from "../db.js";
import {ttCat, maxPrix, pasDePrix} from "../bdd/rqts.js";

export default class FSPrixNobel {

// ===================================================================================================================== F4
    listerCategoriePrixNobel(callback) {
        postG.query(ttCat, (error, result) => {
            if (error || (result.rowCount == 0)) return callback(error, "Pas de catégorie");
            return callback(null, result.rows);
        });
    }

// ===================================================================================================================== F5
    async plusGrandNBPrix(callback) {
        postG.query(maxPrix, (error, result) => {
            if (error || (result.rowCount == 0)) return callback(error, "Pas de résultat");
            return callback(null, result.rows);
        });
    }

// ===================================================================================================================== F7
    async pasPrixNobel(callback) {
        postG.query(pasDePrix, (error, result) => {
            if (error || (result.rowCount == 0)) return callback(error, "Toutes les années ont lauréats");
            return callback(null, result.rows);
        });
    }
}
