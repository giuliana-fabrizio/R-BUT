import fs from "fs";
import postG from "../db.js";

const prizes = JSON.parse(fs.readFileSync("../prize.json"));
import {addLaureates, addCategory, addPrize, insertObtient} from "./data.js";


var listCategory = [], idtf = [], idPrz = 0;

async function add_laureates (id, firstname, surname) {
    await postG.query(addLaureates, [id, firstname, surname]);
    idtf.push(id);
}

async function add_category (nomCategorie) {
    await postG.query(addCategory, [nomCategorie]);
    listCategory.push(nomCategorie);
}

async function add_prize (year, category) {
    await postG.query(addPrize, [year, category]);
    idPrz += 1;
}

async function add_obtient(id_lrt, id_prz, motivation, share) {
    await postG.query(insertObtient, [id_lrt, id_prz, motivation, share]);
}

(async () => {
    for (let prize of prizes) {

        if (!listCategory.includes(prize.category)) await add_category(prize.category);

        await add_prize(prize.year, listCategory.indexOf(prize.category) + 1);

        if (prize.laureates) {
            for (let lrt of prize.laureates) {
                if (!idtf.includes(lrt.id)) await add_laureates(lrt.id, lrt.firstname, lrt.surname);

                await add_obtient(lrt.id, idPrz, lrt.motivation, lrt.share);
            }
        }
    }
})();
