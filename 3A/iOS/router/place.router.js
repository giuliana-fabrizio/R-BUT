const express = require('express');
const controller = require('../controller/place.controller');
const router = express.Router();

/**
 * @swagger
 * /place/detail:
 *   get:
 *      description: Get detail of a place
 *      tags:
 *          - PLACE
 *      parameters:
 *          - in: query
 *            name: id
 *            type: string
 *            required: true
 *            description: Id of the place
 *      responses:
 *          '200':
 *              description: Results gotten successfully
 *          '404':
 *              description: Not found
 */
router.get("/detail", controller.getDetails);


/**
 * @swagger
 * /place/location:
 *   put:
 *      description: Update user location
 *      tags:
 *          - PLACE
 *      parameters:
 *          - in: query
 *            name: id_user
 *            type: string
 *            required: true
 *            description: Id of the user
 *          - in: query
 *            name: id_place
 *            type: string
 *            required: true
 *            description: Id of the place
 *      responses:
 *          '200':
 *              description: Results gotten successfully
 *          '404':
 *              description: Not found
 */
router.put("/location", controller.updateLocation);

/**
 * @swagger
 * /place/all:
 *   get:
 *      description: Get all places
 *      tags:
 *          - PLACE
 *      responses:
 *          '200':
 *              description: All places details gotten successfully
 *          '500':
 *              description: Not found
 */
router.get("/all", controller.getAllPlaces);

/**
 *  @swagger
 *  /place:
 *    post:
 *      tags:
 *        - PLACE
 *      description: Create a place
 *      parameters:
 *        - in: body
 *          name: place
 *          description: The new place properties.
 *          schema:
 *            type: object
 *            required:
 *              - name_place
 *              - description_place
 *              - email
 *              - telephone
 *              - site_web
 *              - latitude
 *              - longitude
 *            properties:
 *              name_place:
 *                type: string
 *              description_place:
 *                type: string
 *              email:
 *                type: string
 *              telephone:
 *                type: string
 *              site_web:
 *                type: string
 *              latitude:
 *                type: number
 *              longitude:
 *                type: number
 *            example:
 *              place:
 *                name_place: "Burger King"
 *                description_place: "De bons burgers vous attendent"
 *                email: "bkmarseille@hotmail.com"
 *                telephone: "07 44 56 42 13"
 *                site_web: "https://www.burgerking.fr"
 *                latitude: 51.236
 *                longitude: 59.623
 *      responses:
 *        '200':
 *          description: Place created succeffuly
 *        '500':
 *          description: Internal server error
 */
router.post("/", controller.addPlace);

module.exports = router;
