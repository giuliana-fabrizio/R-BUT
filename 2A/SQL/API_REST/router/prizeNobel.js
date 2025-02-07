import express from "express";
const router = new express.Router();

import {listerCategoriePrixNobel, plusGrandNBPrix, pasPrixNobel} from "../controller/prizeNobel.js";

// ============================================================================ F4
router.get('/', listerCategoriePrixNobel);
/**
 * @swagger
 * /prizeNobel/:
 *   get:
 *      description: Use to list all prize's category
 *      tags:
 *          - f4
 *      responses:
 *          '200':
 *              description: Category successfully listed
 *          '500':
 *              description: Internal server error
 *          '400':
 *              description: Bad request
 */
// ============================================================================ F5
router.get('/nbMaxPrix', plusGrandNBPrix);
/**
 * @swagger
 * /prizeNobel/nbMaxPrix:
 *   get:
 *      description: Print the category with the most laureates
 *      tags:
 *          - f5
 *      responses:
 *          '200':
 *              description: Category successfully printed
 *          '500':
 *              description: Internal server error
 *          '400':
 *              description: Bad request
 */
// ============================================================================ F7
router.get('/aucun', pasPrixNobel);
/**
 * @swagger
 * /prizeNobel/aucun:
 *   get:
 *      description: Print years where nobody has gotten a prize
 *      tags:
 *          - f7
 *      responses:
 *          '200':
 *              description: Years successfully printed
 *          '500':
 *              description: Internal server error
 *          '400':
 *              description: Bad request
 */

export default router;
