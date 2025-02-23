#include "../Fichiers_hpp/lister_toutes_depenses_view.hpp"


ListerToutesDepensesView::ListerToutesDepensesView(Utilisateur *user) {
    this->user = user;

    labelPage = new QLabel("Historiques de mes budgets");

    list_revenus = new QListWidget();
    list_depenses_fixes = new QListWidget();
    list_depenses_variables = new QListWidget();
    list_autres_depenses = new QListWidget();

    radioButtonGroup = new QButtonGroup();
    btnSupprimerBudget = new QPushButton("Supprimer ce budget");

    vBoxDetail = new QVBoxLayout();
    vBoxRadioBtn = new QVBoxLayout();
    frame = new QFrame();
    layout = new QGridLayout(frame);

    init(0);
    setStyle();
    addToWindow();
}

void ListerToutesDepensesView::init(int index) {

    if ((int) user->getHistoriqueBudget().size() > 0 &&
        (int) user->getHistoriqueBudget().size() > index) {

        posBudgetCourant = index;

        Budget budget = user->getHistoriqueBudget()[index];

        for (Revenu revenu: budget.list_revenus) {
            std::stringstream format;
            format << std::fixed << std::setprecision(2) << revenu.montant_revenu;
            std::string montant_formate = format.str();

            list_revenus->addItem(
                QString::fromStdString(
                    "\n      • " + revenu.libelle_revenu + " " + montant_formate
                )
            );
        }

        remplirCategorie(budget.depenses_fixes, list_depenses_fixes);
        remplirCategorie(budget.depenses_variables, list_depenses_variables);
        remplirCategorie(budget.autres_depenses, list_autres_depenses);

        int cpt = 0;

        for (Budget b: user->getHistoriqueBudget()) {

            QRadioButton *radioButton = new QRadioButton(QString::fromStdString(
                b.date_debut.toString() + " - " + b.date_fin.toString()
            ));

            radioButtonGroup->addButton(radioButton, cpt);
            vBoxRadioBtn->addWidget(radioButton);

            cpt += 1;
        }
    }
}

void ListerToutesDepensesView::remplirCategorie(std::vector<Enveloppe> listEnveloppe, QListWidget *list) {

    for (Enveloppe enveloppe: listEnveloppe) {

        std::stringstream format_prevu;
        format_prevu << std::fixed << std::setprecision(2) << enveloppe.budget_prevu;
        std::string montant_prevu_formate = format_prevu.str();

        list->addItem(QString::fromStdString("\n      • " + enveloppe.libelle_enveloppe + " " +
                                montant_prevu_formate
        ));
        for (Depense depense: enveloppe.list_depense) {
            std::stringstream format;
            format << std::fixed << std::setprecision(2) << depense.montant_depense;
            std::string montant_formate = format.str();

            list->addItem(QString::fromStdString("\n               • " + depense.date_depense.toString() + " " +
                        montant_formate +"\n                  " + depense.description_depense
            ));
        }
    }
}

void ListerToutesDepensesView::setStyle() {
    list_revenus->setStyleSheet("QListWidget {border: none; gridline-color: white;}");
    list_depenses_fixes->setStyleSheet("QListWidget {border: none; gridline-color: white;}");
    list_depenses_variables->setStyleSheet("QListWidget {border: none; gridline-color: white;}");
    list_autres_depenses->setStyleSheet("QListWidget {border: none; gridline-color: white;}");

    btnSupprimerBudget->setStyleSheet("QPushButton {background: #ffb0cc; padding: 10px;}");

    vBoxDetail->setSpacing(20);
}

void ListerToutesDepensesView::addToWindow() {

    vBoxDetail->addWidget(list_revenus);
    vBoxDetail->addWidget(list_depenses_fixes);
    vBoxDetail->addWidget(list_depenses_variables);
    vBoxDetail->addWidget(list_autres_depenses);

    layout->addLayout(vBoxDetail, 0, 0);
    layout->addWidget(btnSupprimerBudget, 0, 1);
    layout->addLayout(vBoxRadioBtn, 0, 2);
}

void ListerToutesDepensesView::clearAll() {

    list_revenus->clear();
    list_depenses_fixes->clear();
    list_depenses_variables->clear();
    list_autres_depenses->clear();

    while (vBoxRadioBtn->count() > 0) {
        QRadioButton *radioButton = dynamic_cast<QRadioButton *>(vBoxRadioBtn->takeAt(0)->widget());
        radioButtonGroup->removeButton(radioButton);
        delete radioButton;
    }
}

int ListerToutesDepensesView::getPosBudgetCourant() {
    return posBudgetCourant;
}

QButtonGroup* ListerToutesDepensesView::getRadioButtonGroup() {
    return radioButtonGroup;
}

QPushButton* ListerToutesDepensesView::getBtnSupprimerBudget() {
    return btnSupprimerBudget;
}

QFrame* ListerToutesDepensesView::getFrame() {
    return frame;
}