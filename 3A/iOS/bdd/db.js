const Pool = require('pg').Pool;

const user = 'postgres'
const host = 'localhost'
const password = '123456'
const database = 'Ios'
const port = 5432;

const pool = new Pool({
    user: user,
    host: host,
    password: password,
    database: database,
    port: port
});

module.exports = pool;