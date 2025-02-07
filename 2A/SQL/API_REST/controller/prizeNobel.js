import {default as FSPrixNobel} from '../service/prizeNobel.js';

// ===================================================================================================================== F4
export const listerCategoriePrixNobel = (req, res) => {
    const service = new FSPrixNobel();
    service.listerCategoriePrixNobel((error, result) => {
        if (error)
            return res.status(400).send({success : 0, data : error});
        return res.status(200).send(result);
    });
}

// ===================================================================================================================== F5
export const plusGrandNBPrix = (req, res) => {
    const service = new FSPrixNobel();
    service.plusGrandNBPrix((error, result) => {
        if (error)
            return res.status(400).send({success : 0, data : error});
        return res.status(200).send(result);
    });
}

// ===================================================================================================================== F7
export const pasPrixNobel = (req, res) => {
    const service = new FSPrixNobel();
    service.pasPrixNobel((error, result) => {
        if (error)
            return res.status(400).send({success : 0, data : error});
        return res.status(200).send(result);
    });
}
