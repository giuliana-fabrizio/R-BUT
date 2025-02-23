#include "../Fichiers_hpp/controller.hpp"

#include <iostream>

Controller::Controller(Utilisateur *user, View *view) {
    this->user = user;
    this->view = view;
    connect();
}

void Controller::connect() {
    QObject::connect(view->getMenu()->getActionPageAccueil(), &QAction::triggered, [=]()
                     { changePage(1); });
    QObject::connect(view->getMenu()->getActionPageBudget(), &QAction::triggered, [=]()
                     { changePage(2); });
    QObject::connect(view->getMenu()->getActionPageDepense(), &QAction::triggered, [=]()
                     { changePage(3); });
    QObject::connect(view->getMenu()->getActionPageHistorique(), &QAction::triggered, [=]()
                     { changePage(4); });

    QObject::connect(view->getNvBudgetView()->getBtnValide(), &QPushButton::clicked, [=]()
                     { addBudget(); });
    QObject::connect(view->getNvleDepenseView()->getBtnValide(), &QPushButton::clicked, [=]()
                     { addDepense(); });

    QObject::connect(view->getNvleDepenseView()->getComboBoxCategorieDepense(), QOverload<int>::of(&QComboBox::currentIndexChanged), [=]() {
        int index = view->getNvleDepenseView()->getComboBoxCategorieDepense()->currentIndex();
        donneInfos(index);
    });

    QObject::connect(view->getListerToutesDepensesView()->getRadioButtonGroup(), QOverload<int>::of(&QButtonGroup::idClicked), [=]() {
        int index = view->getListerToutesDepensesView()->getRadioButtonGroup()->id(
            view->getListerToutesDepensesView()->getRadioButtonGroup()->checkedButton()
        );
        changeDetailBudget(index);
    });

    QObject::connect(view->getListerToutesDepensesView()->getBtnSupprimerBudget(), &QPushButton::clicked, [=]() {
        int index = view->getListerToutesDepensesView()->getPosBudgetCourant();
        supprimerBudget(index);
    });
}

void Controller::changePage(int nb) {
    view->addToScene(nb);
}

void Controller::addBudget() {

    int jourDebut = view->getNvBudgetView()->getComboBoxJourDebut()->currentIndex() + 1;
    int moisDebut = view->getNvBudgetView()->getComboBoxMoisDebut()->currentIndex() + 1;
    int anneeDebut = view->getNvBudgetView()->getComboBoxAnneeDebut()->currentText().toInt();

    int jourFin = view->getNvBudgetView()->getComboBoxJourFin()->currentIndex() + 1;
    int moisFin = view->getNvBudgetView()->getComboBoxMoisFin()->currentIndex() + 1;
    int anneeFin = view->getNvBudgetView()->getComboBoxAnneeFin()->currentText().toInt();

    Date debut = {jourDebut, moisDebut, anneeDebut};
    Date fin = {jourFin, moisFin, anneeFin};

    if (debut.compareDate(fin) != 1) {

        QMessageBox::information(view->getWindow(), "Erreur ⚠️",
            "Date de début doit être inférieur à celle de fin.");
        return;
    }

    std::vector<double> prevision_dps_fixes;
    std::vector<double> prevision_dps_variables;
    std::vector<double> prevision_autres_dps;
    std::vector<Revenu> revenu_dispos;

    double sommeRevenu = 0;
    double sommePrevision = 0;

    // verif si négatif !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    for (int i = 0; i < view->getNvBudgetView()->getTableWidgetRevenus()->rowCount(); i += 1) {

        std::string libelle_revenu = dynamic_cast<QLineEdit*>(
            view->getNvBudgetView()->getTableWidgetRevenus()->cellWidget(i, 0)
        )->text().toStdString();

        double montant = dynamic_cast<QLineEdit*>(
            view->getNvBudgetView()->getTableWidgetRevenus()->cellWidget(i, 1)
        )->text().toDouble();

        if (i == 0 && (libelle_revenu == "" || montant == 0.0)) {
            QMessageBox::information(view->getWindow(), "Erreur ⚠️", "Veuillez renseigner un revenu au minimum.");
            return;
        }

        Revenu revenu = {montant, libelle_revenu};
        revenu_dispos.push_back(revenu);

        sommeRevenu += montant;
    }

    for (int i = 0; i < view->getNvBudgetView()->getTableWidgetDepensesFixes()->rowCount(); i += 1) {
        double prevu = dynamic_cast<QLineEdit*>(
            view->getNvBudgetView()->getTableWidgetDepensesFixes()->cellWidget(i, 1)
        )->text().toDouble();
        prevision_dps_fixes.push_back(prevu);

        sommePrevision += prevu;
    }

    for (int i = 0; i < view->getNvBudgetView()->getTableWidgetDepensesVariables()->rowCount(); i += 1) {
        double prevu = dynamic_cast<QLineEdit*>(
            view->getNvBudgetView()->getTableWidgetDepensesVariables()->cellWidget(i, 1)
        )->text().toDouble();
        prevision_dps_variables.push_back(prevu);

        sommePrevision += prevu;
    }

    for (int i = 0; i < view->getNvBudgetView()->getTableWidgetAutresDepenses()->rowCount(); i += 1) {
        double prevu = dynamic_cast<QLineEdit*>(
            view->getNvBudgetView()->getTableWidgetAutresDepenses()->cellWidget(i, 1)
        )->text().toDouble();
        prevision_autres_dps.push_back(prevu);

        sommePrevision += prevu;
    }

    if (sommePrevision > sommeRevenu) {

        QMessageBox::information(view->getWindow(), "Erreur ⚠️",
            "Somme des revenus > somme de vos prévisions.");
        return;
    }

    user->ajouterBudget(debut, fin, prevision_dps_fixes, prevision_dps_variables, prevision_autres_dps, revenu_dispos);


    view->getAccueilView()->clearAll();
    view->getAccueilView()->init();

    view->getListerToutesDepensesView()->clearAll();
    view->getListerToutesDepensesView()->init(user->getHistoriqueBudget().size() - 1);

    changePage(1);
}

void Controller::donneInfos(int index) {
    view->getNvleDepenseView()->initComboBoxEnveloppe(index);
}

void Controller::addDepense() {
    if (user->getHistoriqueBudget().size() < 1) {
        QMessageBox::information(view->getWindow(), "Erreur ⛔", "Ajout impossible car aucun budget renseigné.");
        return;
    }

    int jour = view->getNvleDepenseView()->getComboBoxJour()->currentIndex() + 1;
    int mois = view->getNvleDepenseView()->getComboBoxMois()->currentIndex() + 1;
    int annee = view->getNvleDepenseView()->getComboBoxAnnee()->currentText().toInt();
    Date date = {jour, mois, annee};

    Date debut = user->getLastBudget().date_debut;
    Date fin = user->getLastBudget().date_fin;

    if (date.compareDate(debut) == 1) {
        QMessageBox::information(view->getWindow(), "Attention ⚠️", "Date de la dépense soit supérieure ou égale à la date de début du budget actuel.");
        return;
    }
    if (date.compareDate(fin) == 2) {
        QMessageBox::information(view->getWindow(), "Attention ⚠️", "Date de la dépense soit inférieure  ou égale à la date de fin du budget actuel.");
        return;
    }

    int id_categorie = view->getNvleDepenseView()->getComboBoxCategorieDepense()->currentIndex();
    int id_enveloppe = view->getNvleDepenseView()->getComboBoxEnveloppe()->currentIndex();

    double montant = view->getNvleDepenseView()->getLineEditMontant()->text().toDouble();

    std::string description = view->getNvleDepenseView()->getLineEditDescription()->text().toStdString();

    if (montant == 0.0 || description == "") {
        QMessageBox::information(view->getWindow(), "Attention ⚠️", "Description ou montant non renseigné.");
        return;
    }

    user->ajouterDepense(date, montant, description, id_categorie, id_enveloppe);


    view->getAccueilView()->clearAll();
    view->getAccueilView()->init();

    view->getListerToutesDepensesView()->clearAll();
    view->getListerToutesDepensesView()->init(user->getHistoriqueBudget().size() - 1);

    changePage(1);
}

void Controller::changeDetailBudget(int index) {
    view->getListerToutesDepensesView()->clearAll();
    view->getListerToutesDepensesView()->init(index);
}

void Controller::supprimerBudget(int index) {

    if ((int) user->getHistoriqueBudget().size() == 0) {
        QMessageBox::information(view->getWindow(), "Erreur ⛔", "Supression impossible car aucun budget.");
        return;
    }
    user->supprimerBudget(index);

    view->getAccueilView()->clearAll();
    view->getAccueilView()->init();

    view->getListerToutesDepensesView()->clearAll();
    view->getListerToutesDepensesView()->init(user->getHistoriqueBudget().size() - 1);
}
