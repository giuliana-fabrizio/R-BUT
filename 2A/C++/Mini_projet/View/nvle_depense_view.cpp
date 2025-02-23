#include "../Fichiers_hpp/nvle_depense_view.hpp"

NvleDepenseView::NvleDepenseView() {
    labelPage = new QLabel("Ajouter une dépense");
    labelDateDepense = new QLabel("Date de votre dépense");
    labelMontantDepense = new QLabel("Montant dépensé");
    labelDescriptionDepense = new QLabel("Détail de votre dépense");
    labelCategoriesDepense = new QLabel("Catégorie de votre dépense");
    labelEnveloppeDepense = new QLabel("Enveloppe où ranger votre dépense");

    comboBoxJour = new QComboBox();
    comboBoxMois = new QComboBox();
    comboBoxAnnee = new QComboBox();
    comboBoxCategorieDepense = new QComboBox();
    comboBoxEnveloppe = new QComboBox();

    lineEditDescription = new QLineEdit();
    lineEditMontant = new QLineEdit();
    validateur = new QDoubleValidator();

    btnValide = new QPushButton("Valider");

    hBoxDate = new QHBoxLayout();
    hBoxDescription = new QHBoxLayout();
    hBoxMontant = new QHBoxLayout();
    hBoxCategorie = new QHBoxLayout();

    frame = new QFrame();
    layout = new QGridLayout(frame);

    initPage();
    initComboBoxEnveloppe(0);
    setStyle();
    addToWindow();
}

void NvleDepenseView::initPage() {

    for (int i = 1; i <= 31; i += 1) {
        comboBoxJour->addItem(QString::number(i));
        if (i <= 12)
            comboBoxMois->addItem(QString::number(i));
    }

    for (int i = 2023; i <= 2023 + 1; i += 1) comboBoxAnnee->addItem(QString::number(i));

    for (int i = 1; i < (int) Data::composants_budget.size(); i += 1)
        comboBoxCategorieDepense->addItem(QString::fromStdString(Data::composants_budget[i]));

    lineEditMontant->setValidator(validateur);
}

void NvleDepenseView::initComboBoxEnveloppe(int nb) {
    comboBoxEnveloppe->clear();
    std::vector<std::string> list;

    switch (nb) {
        default:
            list = Data::depenses_fixes_predefinies;
            break;

        case 1:
            list = Data::depenses_variables_predefinies;
            break;

        case 2:
            list = Data::autres_depenses_predefinies;
            break;
    }

    for (std::string s : list)
        comboBoxEnveloppe->addItem(QString::fromStdString(s));
}


void NvleDepenseView::setStyle() {

    labelPage->setStyleSheet("QLabel { font: 18pt; font-weight: bold; }");
    labelPage->setAlignment(Qt::AlignCenter);

    hBoxCategorie->setSpacing(200);

    btnValide->setStyleSheet("QPushButton {background: #c4ffdc;}");
}

void NvleDepenseView::addToWindow() {
    hBoxDate->addWidget(labelDateDepense);
    hBoxDate->addWidget(comboBoxJour);
    hBoxDate->addWidget(comboBoxMois);
    hBoxDate->addWidget(comboBoxAnnee);

    hBoxDescription->addWidget(labelDescriptionDepense);
    hBoxDescription->addWidget(lineEditDescription);

    hBoxMontant->addWidget(labelMontantDepense);
    hBoxMontant->addWidget(lineEditMontant);

    hBoxCategorie->addWidget(labelCategoriesDepense);
    hBoxCategorie->addWidget(comboBoxCategorieDepense);
    hBoxCategorie->addWidget(labelEnveloppeDepense);
    hBoxCategorie->addWidget(comboBoxEnveloppe);

    layout->addWidget(labelPage, 0, 0);
    layout->addLayout(hBoxDate, 1, 0);
    layout->addLayout(hBoxMontant, 2, 0);
    layout->addLayout(hBoxDescription, 3, 0);
    layout->addLayout(hBoxCategorie, 4, 0);
    layout->addWidget(btnValide, 5, 0);
}

QComboBox *NvleDepenseView::getComboBoxJour() {
    return comboBoxJour;
}

QComboBox *NvleDepenseView::getComboBoxMois() {
    return comboBoxMois;
}

QComboBox* NvleDepenseView::getComboBoxAnnee() {
    return comboBoxAnnee;
}

QComboBox* NvleDepenseView::getComboBoxCategorieDepense() {
    return comboBoxCategorieDepense;
}

QComboBox *NvleDepenseView::getComboBoxEnveloppe() {
    return comboBoxEnveloppe;
}

QLineEdit *NvleDepenseView::getLineEditDescription() {
    return lineEditDescription;
}

QLineEdit *NvleDepenseView::getLineEditMontant() {
    return lineEditMontant;
}

QPushButton *NvleDepenseView::getBtnValide() {
    return btnValide;
}

QFrame *NvleDepenseView::getFrame() {
    return frame;
}