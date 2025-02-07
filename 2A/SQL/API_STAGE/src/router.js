const express = require('express');
const controller = require('./controlleur');
const router = new express.Router();

router.get("/getEntreprise", controller.getEntreprise);
/**
 * @swagger
 * /stage/getEntreprise:
 *   get:
 *      description: Utilisée pour lister toutes les entreprises
 *      tags:
 *          - Entreprise
 *      responses:
 *          '200':
 *              description: Entreprises listées avec succès
 */

router.get("/getProfPlrsSoutenances/:nb", controller.getProfesseursPlrsSoutenances);
/**
 * @swagger
 * /stage/getProfPlrsSoutenances/{nb}:
 *   get:
 *      description: Utilisée pour lister les profs devant assister à plus de nb soutenances
 *      tags:
 *          - Professeur
 *      parameters:
 *          - in: path
 *            name: nb
 *            type: integer
 *            required: true
 *            description: Nombre de soutenances minimum
 *      responses:
 *          '200':
 *              description: Professeurs listés avec succès
 */

router.post("/addEntreprise", controller.addEntreprise);
/**
 * @swagger
 * /stage/addEntreprise:
 *   post:
 *      description: Utilisée pour ajouter une entreprise
 *      tags:
 *          - Entreprise
 *      parameters:
 *          - in: query
 *            name: nomEntreprise
 *            description: Nom de l'entreprise à ajouter
 *            schema:
 *              type: object
 *              required:
 *                  - nomEntreprise
 *              properties:
 *                  nomEntreprise:
 *                      type: string
 *      responses:
 *          '200':
 *              description: Entreprise ajoutée avec succès
 */

router.post("/addProf", controller.addProf);
/**
 * @swagger
 * /stage/addProf:
 *   post:
 *      description: Utilisée pour ajouter un professeur
 *      tags:
 *          - Professeur
 *      parameters:
 *          - in: query
 *            name: nomProfesseur
 *            description: Nom du professeur à ajouter
 *            schema:
 *              type: object
 *              required:
 *                  - nomProfesseur
 *              properties:
 *                  nomProfesseur:
 *                      type: string
 *      responses:
 *          '200':
 *              description: Professeur ajouté avec succès
 */

router.post("/addJury", controller.addJury);
/**
 * @swagger
 * /stage/addJury:
 *   post:
 *      description: Utilisée pour ajouter un jury
 *      tags:
 *          - Jury
 *      parameters:
 *          - in: query
 *            name: nomJury
 *            description: Nom du jury à ajouter
 *            schema:
 *              type: object
 *              required:
 *                  - nomJury
 *              properties:
 *                  nomJury:
 *                      type: string
 *                      minLength: 1
 *                      maxLength: 20
 *          - in: query
 *            name: idSalle
 *            description: Id de la salle du jury
 *            schema:
 *              type: object
 *              required:
 *                  - idSalle
 *              properties:
 *                  idSalle:
 *                      type: integer
 *      responses:
 *          '200':
 *              description: Jury ajouté avec succès
 */

router.post("/addSoutenance", controller.addSoutenance);
/**
 * @swagger
 * /stage/addSoutenance:
 *   post:
 *      description: Utilisée pour ajouter un jury
 *      tags:
 *          - Soutenance
 *      parameters:
 *          - in: query
 *            name: noEtudiant
 *            description: Id de l'étudiant
 *            schema:
 *              type: object
 *              required:
 *                  - noEtudiant
 *              properties:
 *                  noEtudiant:
 *                      type: integer
 *          - in: query
 *            name: idJury
 *            description: Id de la salle du jury
 *            schema:
 *              type: object
 *              required:
 *                  - idJury
 *              properties:
 *                  idJury:
 *                      type: integer
 *          - in: query
 *            name: dateSout
 *            description: Date de la soutenance
 *            schema:
 *              type: object
 *              required:
 *                  - dateSout
 *              properties:
 *                  dateSout:
 *                      type: string
 *          - in: query
 *            name: note
 *            description: Nom du jury à ajouter
 *            schema:
 *              type: object
 *              required:
 *                  - note
 *              properties:
 *                  note:
 *                      type: integer
 *      responses:
 *          '200':
 *              description: Soutenance ajoutée avec succès
 */

router.delete("/rmEntreprise/:idEntreprise", controller.rmEntreprise);
/**
 * @swagger
 * /stage/rmEntreprise/{idEntreprise}:
 *   delete:
 *      description: Utilisée pour supprimer une entreprise
 *      tags:
 *          - Entreprise
 *      parameters:
 *          - in: path
 *            name: idEntreprise
 *            type: integer
 *            required: true
 *            description: Id de l'entreprise à supprimer
 *      responses:
 *          '200':
 *              description: Jury ajouté avec succès
 */

module.exports = router;
