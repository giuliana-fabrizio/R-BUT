import express from "express";
const router = new express.Router();

import {listerLaureates, plrsPrix, nbLaureates, nbLaureatesTrie, infoPrix, deleteLaureat, updateMotivation} from "../controller/laureates.js";

// ====================================================================== F1
router.get('/list', listerLaureates);
/**
* @swagger
* /laureate/list:
*   get:
*      description: Use to list all laureates without duplicates
*      tags:
*          - f1
*      responses:
*          '200':
*              description: Laureates listed successfully
*          '500':
*              description: Internal server error
*          '400':
*              description: Bad request
*/
// ====================================================================== F2
router.get('/infoPrix/:lrtId', infoPrix);
/**
 * @swagger
 * /laureate/infoPrix/{lrtId}:
 *   get:
 *      description: Use to search all prizes gotten by a laureate in function of his ID
 *      tags:
 *          - f2
 *      parameters:
 *          - in: path
 *            name: lrtId
 *            type: integer
 *            required: true
 *            description: Numeric ID of the laureate
 *      responses:
 *          '200':
 *              description: Laureate find successfully
 *          '500':
 *              description: Internal server error
 *          '400':
 *              description: Bad request
 */
// ====================================================================== F3
router.get('/plrsPrix', plrsPrix);
/**
* @swagger
* /laureate/plrsPrix:
*   get:
*      description: Use to list all laureates who have several prizes
*      tags:
*          - f3
*      responses:
*          '200':
*              description: Laureates listed successfully
*          '500':
*              description: Internal server error
*          '400':
*              description: Bad request
*/
// ====================================================================== F6
router.get('/annee', nbLaureates);
/**
* @swagger
* /laureate/annee:
*   get:
*      description: Print the number of laureates for each year
*      tags:
*          - f6
*      responses:
*          '200':
*              description: Laureates listed successfully
*          '500':
*              description: Internal server error
*          '400':
*              description: Bad request
*/
// ====================================================================== F8
router.get('/annee/trie/:operation', nbLaureatesTrie);
/**
 * @swagger
 * /laureate/annee/trie/{operation}:
 *   get:
 *      description: Print the number of laureates for each year with ascending or descending parameters (optional)
 *      tags:
 *          - f11
 *      parameters:
 *          - in: path
 *            name: operation
 *            type: string
 *            minLength: 3
 *            maxLength: 4
 *            required: false
 *            description: Filter by ascending or descending number
 *      responses:
 *          '200':
 *              description: Laureates listed successfully
 *          '500':
 *              description: Internal server error
 *          '400':
 *              description: Bad request
 */
// ====================================================================== F9
router.delete('/:lrtId', deleteLaureat);
/**
 * @swagger
 * /laureate/{lrtId}:
 *   delete:
 *      description: Delete a laureate with his ID
 *      tags:
 *          - f9
 *      parameters:
 *          - in: path
 *            name: lrtId
 *            type: integer
 *            required: true
 *            description: Numeric ID of the laureate
 *      responses:
 *          '200':
 *              description: Laureate deleted successfully
 *          '500':
 *              description: Internal server error
 *          '400':
 *              description: Bad request
 */
// ====================================================================== F10
router.put('/:lrtId/:annee/:cat', updateMotivation);
/**
 * @swagger
 * /laureate/{lrtId}/{annee}/{cat}:
 *   put:
 *      description: Update laureate's motivation with his ID
 *      tags:
 *          - f10
 *      parameters:
 *          - in: path
 *            name: lrtId
 *            type: integer
 *            required: true
 *            description: Numeric ID of the laureate
 *          - in: path
 *            name: annee
 *            type: string
 *            required: true
 *            description: Numeric year of the prize
 *          - in: path
 *            name: cat
 *            type: string
 *            required: true
 *            description: Category of the prize
 *          - in: body
 *            name: motivation
 *            description: New motivation of the prize
 *            schema:
 *              type: object
 *              required:
 *                  - motivation
 *              properties:
 *                  motivation:
 *                      type: string
 *                      minLength: 1
 *                      maxLength: 250
 *      responses:
 *          '200':
 *              description: Motivation changed successfully
 *          '500':
 *              description: Internal server error
 *          '400':
 *              description: Bad request
 */

export default router;
