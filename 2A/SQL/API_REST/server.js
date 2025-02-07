import body_parser from "body-parser";
import express from "express";
import handlebars from "express-handlebars";
const port = 4000;
const app = express();

app.use(body_parser.json());
app.use(body_parser.urlencoded({
    extended: false
}));

// ===================================================================================================================== swaggerDocs
import swaggerJsdoc from "swagger-jsdoc";
import swaggerUI from "swagger-ui-express";
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
    apis: ["server.js", "./router/*.js"],
};

const swaggerDocs = swaggerJsdoc(swaggerOption);
app.use('/api-docs', swaggerUI.serve, swaggerUI.setup(swaggerDocs))

// ===================================================================================================================== router laureates
import {default as laureateRouter} from "./router/laureates.js";
app.use('/laureate', laureateRouter);

// ===================================================================================================================== router prize nobel
import {default as prizeNobelRouter} from "./router/prizeNobel.js";
app.use('/prizeNobel', prizeNobelRouter);

app.listen(port, () => {
    console.log(`Le serveur écoute sur le port ${port}`);
});
