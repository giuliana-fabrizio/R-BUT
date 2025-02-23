const pool = require('../bdd/db');
const placeQueries = require('../queries/place.queries');

const getDetails = async (id, callback) => {
    await pool.query(placeQueries.getDetails, [id], async (error, results) => {
        if (error) {
            return callback(error);
        }
        return callback(null, results.rows);
    });
}

const updateLocation = async (user_id, place_id, callback) => {
    await pool.query(placeQueries.updateLocation, [user_id, place_id], async (e, res) => {
        if (e) {
            return callback(e);
        }
        return callback(null, res.rows);
    });
}

const getAllPlaces = async (callback) => {
    await pool.query(placeQueries.getAllPlaces, async (error, results) => {
        if (error) {
            return callback(error);
        }
        return callback(null, results.rows);
    });
};

const addPlace = async (place, callback) => {
    console.log(place)
    await pool.query(placeQueries.addPlace, [place.name_place, place.description_place, place.email, place.telephone, place.site_web, place.latitude, place.longitude], async (error, results) => {
        if (error) {
            return callback(error);
        }
        return callback(null, results.rows);
    });
}

module.exports = {
    getDetails,
    updateLocation,
    getAllPlaces,
    addPlace
}
