const express = require('express');
const controller = require('../controller/user.controller');
const router = express.Router();

/**
 * @swagger
 * /user:
 *   get:
 *      description: Get a user
 *      tags:
 *          - USER
 *      parameters:
 *          - in: query
 *            name: id
 *            type: string
 *            required: true
 *            description: Id of the user
 *      responses:
 *          '200':
 *              description: Results gotten successfully
 *          '404':
 *              description: Not found
 */
router.get("/", controller.getUser);


/**
 *  @swagger
 *  /user/update:
 *    put:
 *      tags:
 *        - USER
 *      description: Update a user with the specified properties.
 *      parameters:
 *        - in: body
 *          name: user
 *          description: The new user properties to update.
 *          schema:
 *            type: object
 *            required:
 *              - age
 *              - company
 *              - firstName
 *              - gender
 *              - name
 *              - job
 *              - id
 *            properties:
 *              age:
 *                type: number
 *              company:
 *                type: string
 *              firstname:
 *                type: string
 *              gender:
 *                type: string
 *              name:
 *                type: string
 *              job:
 *                type: string
 *              id:
 *                type: number
 *              id_place:
 *                type: number
 *            example:
 *              user:
 *                age: 30
 *                company: CMA-CGM
 *                firstname: John
 *                gender: men
 *                name: Doe
 *                id: 3
 *                id_place: 1
 *      responses:
 *        '200':
 *          description: User updated succeffuly
 *        '500':
 *          description: Internal server error
 */
router.put("/update", controller.updateUser)

/**
 * @swagger
 * /user/login:
 *   get:
 *      description: Login a user
 *      tags:
 *          - USER
 *      parameters:
 *          - in: query
 *            name: email
 *            type: string
 *            required: true
 *            description: User's email
 *          - in: query
 *            name: mdp
 *            type: string
 *            required: true
 *            description: User's password
 *      responses:
 *          '200':
 *              description: Login successful
 *          '401':
 *              description: Invalid credentials
 *          '500':
 *              description: Internal server error
 */
router.get("/login", controller.loginUser);

module.exports = router;
