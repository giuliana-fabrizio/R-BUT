import Pool from "pg-pool";

const postG = new Pool ({
    user: "giuliana",
    host: "localhost",
    database: "bdd_sql",
    password: "2711",
    port: 5432
});

export default postG;
