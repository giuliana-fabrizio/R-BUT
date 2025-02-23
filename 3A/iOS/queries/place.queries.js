const getDetails = "select * from users where id_place = $1;";

const updateLocation = "update users set id_place = $2 where id = $1;";

const getAllPlaces = "select * from places;";

const addPlace = 'INSERT INTO places (name_place, description_place, email, telephone, site_web, latitude, longitude) VALUES ($1, $2, $3, $4, $5, $6, $7);';

module.exports = {
    getDetails,
    updateLocation,
    getAllPlaces,
    addPlace
}
