// variables
const cors = require('cors');
const dotenv = require('dotenv');
const express = require('express');
const swaggerJsdoc = require('swagger-jsdoc');
const swaggerUI = require('swagger-ui-express');

const user_router = require('./router/user.router');
const place_router = require('./router/place.router');


const server = express();

// utilisation des variables d'environnement
dotenv.config();

// configuration des requêtes
server.use(cors());
server.use(express.json());


/**
 * Import and define swagger doc
 */

/** Swagger Initialization - START */
const swaggerOption = {
    swaggerDefinition: (swaggerJsdoc.Options = {
        info: {
            title: "API iOS",
            description: "API documentation",
            servers: [`http://localhost:5000/`],
        },
    }),
    apis: ["index.js", "./router/*.js"],
};

const swaggerDocs = swaggerJsdoc(swaggerOption);
server.use("/api-docs", swaggerUI.serve, swaggerUI.setup(swaggerDocs));


// redirections de chaques routes vers les routers appropriés
server.use("/user", user_router);
server.use("/place", place_router);


// lancement du serveur
server.listen(5000, () => {
    console.log(`Server is listening port 5000`);
});
