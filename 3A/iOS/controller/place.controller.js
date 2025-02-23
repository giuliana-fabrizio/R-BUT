const services = require('../services/place.services');

const getDetails = async (req, res) => {
    const id = req.query.id;

    await services.getDetails(id, (error, results) => {
        if (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        return res.status(200).send(results);
    });
}

const updateLocation = async (req, res) => {
    const user_id = req.query.id_user;
    const place_id = req.query.id_place == -1 ? null : req.query.id_place;

    await services.updateLocation(user_id, place_id, (error, results) => {
        if (error) {
            console.log(error)
            return res.status(500).send(error);
        }
        return res.status(200).send(results);
    });
}

const getAllPlaces = async (req, res) => {
    await services.getAllPlaces((error, results) => {
        if (error) {
            return res.status(500).send(error);
        }
        return res.status(200).send(results);
    });
};

const addPlace = async (req, res) => {
    const place = req.body;

    await services.addPlace(place, (error, results) => {
        if (error) {
            console.log(error);
            return res.status(500).send(error);
        }
        return res.status(201).send(results);
    });
};

module.exports = {
    getDetails,
    updateLocation,
    getAllPlaces,
    addPlace
}
