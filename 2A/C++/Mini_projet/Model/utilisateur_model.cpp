#include "../Fichiers_hpp/utilisateur_model.hpp"


Utilisateur::Utilisateur() {
    historique_budget = {};
}

void Utilisateur::ajouterBudget(Date debut, Date fin, std::vector<double> prevision_dps_fixes,
        std::vector<double> prevision_dps_variables,
        std::vector<double> prevision_autres_dps, std::vector<Revenu> revenu_dispo) {
    Budget budget = {debut, fin, {}, {}, {}, {}};

    for (int i = 0; i < (int) Data::autres_depenses_predefinies.size(); i += 1) {
        Enveloppe enveloppe = {prevision_autres_dps.at(i), Data::autres_depenses_predefinies.at(i), {}};
        budget.autres_depenses.push_back(enveloppe);
    }

    for (int i = 0; i < (int) Data::depenses_fixes_predefinies.size(); i += 1) {
        Enveloppe enveloppe = {prevision_dps_fixes.at(i), Data::depenses_fixes_predefinies.at(i), {}};
        budget.depenses_fixes.push_back(enveloppe);
    }

    for (int i = 0; i < (int) Data::depenses_variables_predefinies.size(); i += 1) {
        Enveloppe enveloppe = {prevision_dps_variables.at(i), Data::depenses_variables_predefinies.at(i), {}};
        budget.depenses_variables.push_back(enveloppe);
    }

    for (Revenu revenu : revenu_dispo)
        budget.list_revenus.push_back(revenu);

    historique_budget.push_back(budget);
}

void Utilisateur::ajouterDepense(Date date, double montant, std::string description, int id_categorie, int id_enveloppe) {

    Depense depense = {date, montant, description};

    switch (id_categorie) {
        default:
            historique_budget[historique_budget.size() - 1].depenses_fixes[id_enveloppe].list_depense.push_back(depense);
            break;

        case 1:
            historique_budget[historique_budget.size() - 1].depenses_variables[id_enveloppe].list_depense.push_back(depense);
            break;

        case 2:
            historique_budget[historique_budget.size() - 1].autres_depenses[id_enveloppe].list_depense.push_back(depense);
            break;
    }
}

void Utilisateur::supprimerBudget(int index) {
    historique_budget.erase(historique_budget.begin() + index);
}

std::vector<Budget> Utilisateur::getHistoriqueBudget() {
    return historique_budget;
}

Budget Utilisateur::getLastBudget() {
    return historique_budget[historique_budget.size() - 1];
}