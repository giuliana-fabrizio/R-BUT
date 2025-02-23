const pool = require('../bdd/db');
const userQueries = require('../queries/user.queries');

const getUser = async (id, callback) => {
    await pool.query(userQueries.getUser, [id], async (error, results) => {
        if (error) {
            return callback(error);
        }
        return callback(null, results.rows);
    });
}

const updateUser = async (user, callback) => {
    await pool.query(userQueries.updateUser, [user.age, user.company, user.firstname, user.gender, user.name_user, user.job, user.email, user.id], async (error, results) => {
        if (error) {
            return callback(error);
        }
        return callback(null, results.rows);
    });
}

const loginUser = async (email, mdp, callback) => {
    await pool.query(userQueries.loginUser, [email, mdp], async (error, results) => {
        if (error) {
            return callback(error);
        }

        console.log(results.rows)
        if (results.rowCount === 0) {
            return callback({ message: 'Invalid credentials' });
        }

        const user = results.rows[0];
        return callback(null, user);
    });
}


module.exports = {
    getUser,
    updateUser,
    loginUser
}
