import postG from '../db.js';
import {default as FSLaureates} from '../service/laureates.js';

// ====================================================================== F1
export const listerLaureates = (req, res) => {
    const service = new FSLaureates();
    service.listerLaureates((error, result) => {
        if (error)
            return res.status(400).send({success : 0, data : error});
        return res.status(200).send(result);
    });
}

// ====================================================================== F2
export const infoPrix = (req, res) => {
    const service = new FSLaureates();
    service.infoPrix(req.params.lrtId, (error, result) => {
        if (error)
            return res.status(400).send({success : 0, data : error});
        return res.status(200).send(result);
    });
}

// ====================================================================== F3
export const plrsPrix = (req, res) => {
    const service = new FSLaureates();
    service.plrsPrix((error, result) => {
        if (error)
            return res.status(400).send({success : 0, data : error});
        return res.status(200).send(result);
    });
}

// ====================================================================== F6
export const nbLaureates = (req, res) => {
    const service = new FSLaureates();
    service.nbLaureates((error, result) => {
        if (error)
            return res.status(400).send({success : 0, data : error});
        return res.status(200).send(result);
    });
}

// ====================================================================== F8
export const nbLaureatesTrie = (req, res) => {
    const op = req.params.operation.toLowerCase();
    const service = new FSLaureates();
    if (op == "asc")
        service.nbLaureatesTrieAsc((error, result) => {
            if (error)
                return res.status(400).send({success : 0, data : error});
            return res.status(200).send(result);
        });
    else if (op == "desc")
        service.nbLaureatesTrieDesc((error, result) => {
            if (error)
                return res.status(400).send({success : 0, data : error});
            return res.status(200).send(result);
        });
    else
        return res.status(400).send({success : 0, data : "erreur, asc ou dec attendu"});
}

// ====================================================================== F9
export const deleteLaureat = (req, res) => {
    const service = new FSLaureates();
    service.deleteLaureat(req.params.lrtId, (error, result) => {
        if (error)
            return res.status(400).send({success : 0, data : error});
        return res.status(200).send(result);
    });
}

// ====================================================================== F10
export const updateMotivation = (req, res) => {
    const service = new FSLaureates();
    service.updateMotivation(req.params.lrtId, req.params.annee, req.params.cat.toLowerCase(), req.body.motivation, (error, result) => {
        if (error)
            return res.status(400).send({success : 0, data : error});
        return res.status(200).send(result);
    });
}
