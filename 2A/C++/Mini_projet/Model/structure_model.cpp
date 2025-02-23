#include "../Fichiers_hpp/structure_model.hpp"

int Date::compareDate(Date date) {
    if (jour == date.jour && mois == date.mois && annee == date.annee) return 0;
    else if (annee < date.annee || (annee == date.annee && (mois < date.mois ||
            (mois == date.mois && jour < date.jour)))) return 1; // 1er < 2e
    else return 2; // 1er > 2e
}

std::string Date::toString() {
    return std::to_string(jour) + "-" + std::to_string(mois) + "-" + std::to_string(annee);
}