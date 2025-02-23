const getUser = "select * from users where id = $1;";

const updateUser = "update users set age = $1, company = $2, firstname = $3, gender = $4, name_user = $5, job = $6, email = $7 where id = $8;";

const loginUser = "SELECT * FROM users WHERE email = $1 AND mdp = $2;";

module.exports = {
    getUser,
    updateUser,
    loginUser
}
