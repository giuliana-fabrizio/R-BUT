const express = require('express');
const app = express();
const port = 3000;

const router = require('./src/router');

app.use(express.json());

app.listen(port);

// =====================================================================================================================
app.get("/", (req, res) => res.send("Good bye : D"));

// =====================================================================================================================
app.use("/stage", router);

// ===================================================================================================================== swaggerDocs
const swaggerJsdoc = require('swagger-jsdoc');
const swaggerUI = require('swagger-ui-express');
/** Swagger Initialization - START */
const swaggerOption = {
    swaggerDefinition: (swaggerJsdoc.Options = {
        info: {
            title: "my-notes app",
            description: "API documentation",
            contact: {
                name: "Giuliana GODAIL FABRIZIO",
            },
            servers: ["http://localhost:4000/"],
        },
    }),
    apis: ["server.js", "./src/router.js"],
};

const swaggerDocs = swaggerJsdoc(swaggerOption);
app.use('/api-docs', swaggerUI.serve, swaggerUI.setup(swaggerDocs))
