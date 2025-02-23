const services = require('../services/user.services');

const getUser = async (req, res) => {
    const id = req.query.id;
    console.log(id)

    await services.getUser(id, (error, results) => {
        if (error) {
            console.log(error);
            return res.status(404).send(error);
        }
        return res.status(200).send(results[0]);
    });
}

const updateUser = async (req, res) => {
    const param = req.body;

    await services.updateUser(param, (error, results) => {
        if (error) {
            console.log(error)
            return res.status(500).send(error);
        }
        return res.status(200).send(results);
    });
}

const loginUser = async (req, res) => {
    const { email, mdp } = req.query;
    console.log(email, "   ", mdp)

    await services.loginUser(email, mdp, (error, user) => {
        if (error) {
            console.log(error)
            return res.status(401).send(error); 
        }

        return res.status(200).send(user);
        
    });
}
 
module.exports = {
    getUser,
    updateUser,
    loginUser
}
