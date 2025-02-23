#ifndef MODEL_UTILISATEUR_HPP
#define MODEL_UTILISATEUR_HPP

#include <iostream>
#include <vector>

#include "data.hpp"
#include "structure_model.hpp"

class Utilisateur {

    public:
        Utilisateur();
        void ajouterBudget(Date debut, Date fin, std::vector<double> prevision_dps_fixes, std::vector<double> prevision_dps_variables, std::vector<double> prevision_autres_dps, std::vector<Revenu> revenu_dispos);
        void ajouterDepense(Date date, double montant, std::string description, int id_categorie, int id_enveloppe);
        void supprimerBudget(int index);
        std::vector<Budget> getHistoriqueBudget();
        Budget getLastBudget();

private:
    std::vector<Budget> historique_budget;
};

#endif