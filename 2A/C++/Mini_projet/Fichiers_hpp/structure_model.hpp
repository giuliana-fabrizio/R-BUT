#include <iostream>
#include <vector>

#ifndef DATE_HPP
#define DATE_HPP

struct Date {
    int jour;
    int mois;
    int annee;

    int compareDate(Date date);
    std::string toString();
};

#endif



#ifndef DEPENSE_HPP
#define DEPENSE_HPP

struct Depense {
    Date date_depense;
    double montant_depense;
    std::string description_depense;
};

#endif



#ifndef ENVELOPPE_HPP
#define ENVELOPPE_HPP

struct Enveloppe {
    double budget_prevu;
    std::string libelle_enveloppe;
    std::vector<Depense> list_depense;
};

#endif



#ifndef REVENU_HPP
#define REVENU_HPP

struct Revenu {
    double montant_revenu;
    std::string libelle_revenu;
};

#endif



#ifndef BUDGET_HPP
#define BUDGET_HPP

struct Budget {
    Date date_debut;
    Date date_fin;

    std::vector<Enveloppe> autres_depenses;
    std::vector<Enveloppe> depenses_fixes;
    std::vector<Enveloppe> depenses_variables;
    std::vector<Revenu> list_revenus;
};

#endif