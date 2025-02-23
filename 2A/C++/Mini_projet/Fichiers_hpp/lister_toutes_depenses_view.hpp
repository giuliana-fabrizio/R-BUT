#ifndef LISTER_TOUTES_DEPENSES_VIEW_HPP
#define LISTER_TOUTES_DEPENSES_VIEW_HPP

#include <iostream>
#include <iomanip>
#include <sstream>
#include <QtWidgets>

#include "structure_model.hpp"
#include "utilisateur_model.hpp"

class ListerToutesDepensesView {

    public:
        ListerToutesDepensesView(Utilisateur *user);
        void init(int index);
        void remplirCategorie(std::vector<Enveloppe> listEnveloppe, QListWidget *list);
        void setStyle();
        void addToWindow();
        void clearAll();
        int getPosBudgetCourant();
        QButtonGroup* getRadioButtonGroup();
        QPushButton *getBtnSupprimerBudget();
        QFrame *getFrame();

    private:
        int posBudgetCourant;
        Utilisateur *user;
        QLabel *labelPage;

        QListWidget *list_revenus;
        QListWidget *list_depenses_fixes;
        QListWidget *list_depenses_variables;
        QListWidget *list_autres_depenses;

        QButtonGroup *radioButtonGroup;
        QPushButton *btnSupprimerBudget;

        QVBoxLayout *vBoxDetail;
        QVBoxLayout *vBoxRadioBtn;
        QFrame *frame;
        QGridLayout *layout;
};

#endif