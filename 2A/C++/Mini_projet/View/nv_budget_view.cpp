#include "../Fichiers_hpp/nv_budget_view.hpp"

NvBudgetView::NvBudgetView()
{
    labelPage = new QLabel("Définir un nouveau budget");
    labelDateDebut = new QLabel("Date de début :");
    labelDateFin = new QLabel("Date de fin");
    labelPrevoir = new QLabel("Prevoyez vos enveloppes");
    labelRevenu = new QLabel("Vos revenus (nom revenu -- montant)");
    labelFixes = new QLabel("Vos dépenses fixes");
    labelVariables = new QLabel("Vos dépenses variables");
    labelAutres = new QLabel("Vos autres dépenses");

    comboBoxJourDebut = new QComboBox();
    comboBoxJourFin = new QComboBox();
    comboBoxAnneeDebut = new QComboBox();
    comboBoxMoisDebut = new QComboBox();
    comboBoxMoisFin = new QComboBox();
    comboBoxAnneeFin = new QComboBox();

    validateur = new QDoubleValidator();

    tableWidget_revenus = new QTableWidget(5, 2);
    tableWidget_depenses_fixes = new QTableWidget(Data::depenses_fixes_predefinies.size(), 2);
    tableWidget_depenses_variables = new QTableWidget(Data::depenses_variables_predefinies.size(), 2);
    tableWidget_autres_depenses = new QTableWidget(Data::autres_depenses_predefinies.size(), 2);

    btnValide = new QPushButton("Valider");

    hBoxDateDebut = new QHBoxLayout();
    hBoxDateFin = new QHBoxLayout();

    frame = new QFrame();
    gridLayoutTables = new QGridLayout();
    layout = new QGridLayout(frame);

    init();
    setStyle();
    addToWindow();
}

void NvBudgetView::init()
{
    int cpt = 0;

    for (int i = 1; i <= 31; i += 1)
    {
        comboBoxJourDebut->addItem(QString::number(i));
        comboBoxJourFin->addItem(QString::number(i));
        if (i <= 12)
        {
            comboBoxMoisDebut->addItem(QString::number(i));
            comboBoxMoisFin->addItem(QString::number(i));
        }
    }

    for (int i = 2023; i <= 2023 + 1; i += 1) {
        comboBoxAnneeDebut->addItem(QString::number(i));
        comboBoxAnneeFin->addItem(QString::number(i));
    }

    for (int i = 0; i < 5; i += 1) {
        tableWidget_revenus->setCellWidget(i, 0, new QLineEdit());
        lineText = new QLineEdit();
        lineText->setValidator(validateur);
        tableWidget_revenus->setCellWidget(i, 1, lineText);
    }

    for (std::string s : Data::depenses_fixes_predefinies) {
        tableWidget_depenses_fixes->setCellWidget(cpt, 0, new QLabel(QString::fromStdString(s)));
        lineText = new QLineEdit();
        lineText->setValidator(validateur);
        tableWidget_depenses_fixes->setCellWidget(cpt, 1, lineText);

        cpt += 1;
    }

    cpt = 0;
    for (std::string s : Data::depenses_variables_predefinies) {
        tableWidget_depenses_variables->setCellWidget(cpt, 0, new QLabel(QString::fromStdString(s)));
        lineText = new QLineEdit();
        lineText->setValidator(validateur);
        tableWidget_depenses_variables->setCellWidget(cpt, 1, lineText);
        cpt += 1;
    }

    cpt = 0;
    for (std::string s : Data::autres_depenses_predefinies) {
        tableWidget_autres_depenses->setCellWidget(cpt, 0, new QLabel(QString::fromStdString(s)));
        lineText = new QLineEdit();
        lineText->setValidator(validateur);
        tableWidget_autres_depenses->setCellWidget(cpt, 1, lineText);
        cpt += 1;
    }
}

void NvBudgetView::setStyle() {
    labelPage->setStyleSheet("QLabel { font: 18pt; font-weight: bold; }");
    labelPage->setAlignment(Qt::AlignCenter);
    labelPrevoir->setStyleSheet("QLabel { font: 15pt; }");
    labelPrevoir->setAlignment(Qt::AlignCenter);
    labelPrevoir->setContentsMargins(0, 0, 0, 30);
    labelDateDebut->setStyleSheet("QLabel { font: 12pt; }");
    labelDateFin->setStyleSheet("QLabel { font: 12pt; }");
    labelRevenu->setStyleSheet("QLabel { font: 12pt; }");
    labelRevenu->setContentsMargins(0, 0, 0, 10);
    labelFixes->setStyleSheet("QLabel { font: 12pt; }");
    labelFixes->setContentsMargins(0, 0, 0, 10);
    labelVariables->setStyleSheet("QLabel { font: 12pt; }");
    labelVariables->setContentsMargins(0, 0, 0, 10);
    labelAutres->setStyleSheet("QLabel { font: 12pt; }");
    labelAutres->setContentsMargins(0, 0, 0, 10);

    for (int i = 0; i < 2; i += 1) {
        tableWidget_revenus->setColumnWidth(i, 200);
        tableWidget_depenses_fixes->setColumnWidth(i, 200);
        tableWidget_depenses_variables->setColumnWidth(i, 200);
        tableWidget_autres_depenses->setColumnWidth(i, 200);
    }

    tableWidget_revenus->setStyleSheet("QTableWidget {border: none; gridline-color: white;}");
    tableWidget_depenses_fixes->setStyleSheet("QTableWidget {border: none; gridline-color: white;}");
    tableWidget_depenses_variables->setStyleSheet("QTableWidget {border: none; gridline-color: white;}");
    tableWidget_autres_depenses->setStyleSheet("QTableWidget {border: none; gridline-color: white;}");

    tableWidget_revenus->horizontalHeader()->setVisible(false);
    tableWidget_revenus->verticalHeader()->setVisible(false);
    tableWidget_depenses_fixes->horizontalHeader()->setVisible(false);
    tableWidget_depenses_fixes->verticalHeader()->setVisible(false);
    tableWidget_depenses_variables->horizontalHeader()->setVisible(false);
    tableWidget_depenses_variables->verticalHeader()->setVisible(false);
    tableWidget_autres_depenses->horizontalHeader()->setVisible(false);
    tableWidget_autres_depenses->verticalHeader()->setVisible(false);

    hBoxDateDebut->setContentsMargins(0, 60, 0, 10);
    hBoxDateFin->setContentsMargins(0, 10, 0, 40);

    gridLayoutTables->setContentsMargins(500, 0, 500, 0);

    btnValide->setStyleSheet("QPushButton {background: #c4ffdc;}");
}

void NvBudgetView::addToWindow() {
    hBoxDateDebut->addWidget(labelDateDebut);
    hBoxDateDebut->addWidget(comboBoxJourDebut);
    hBoxDateDebut->addWidget(comboBoxMoisDebut);
    hBoxDateDebut->addWidget(comboBoxAnneeDebut);

    hBoxDateFin->addWidget(labelDateFin);
    hBoxDateFin->addWidget(comboBoxJourFin);
    hBoxDateFin->addWidget(comboBoxMoisFin);
    hBoxDateFin->addWidget(comboBoxAnneeFin);

    gridLayoutTables->addWidget(labelRevenu, 1, 0);
    gridLayoutTables->addWidget(tableWidget_revenus, 2, 0);
    gridLayoutTables->addWidget(labelFixes, 1, 1);
    gridLayoutTables->addWidget(tableWidget_depenses_fixes, 2, 1);
    gridLayoutTables->addWidget(labelVariables, 3, 0);
    gridLayoutTables->addWidget(tableWidget_depenses_variables, 4, 0);
    gridLayoutTables->addWidget(labelAutres, 3, 1);
    gridLayoutTables->addWidget(tableWidget_autres_depenses, 4, 1);

    layout->addWidget(labelPage, 0, 0);
    layout->addLayout(hBoxDateDebut, 1, 0);
    layout->addLayout(hBoxDateFin, 2, 0);
    layout->addWidget(labelPrevoir, 3, 0);
    layout->addLayout(gridLayoutTables, 4, 0);
    layout->addWidget(btnValide, 5, 0);
}

QComboBox *NvBudgetView::getComboBoxJourDebut()
{
    return comboBoxJourDebut;
}

QComboBox *NvBudgetView::getComboBoxJourFin()
{
    return comboBoxJourFin;
}

QComboBox *NvBudgetView::getComboBoxMoisDebut()
{
    return comboBoxMoisDebut;
}

QComboBox* NvBudgetView::getComboBoxAnneeDebut() {
    return comboBoxAnneeDebut;
}

QComboBox* NvBudgetView::getComboBoxMoisFin() {
    return comboBoxMoisFin;
}

QComboBox* NvBudgetView::getComboBoxAnneeFin() {
    return comboBoxAnneeFin;
}

QTableWidget* NvBudgetView::getTableWidgetRevenus() {
    return tableWidget_revenus;
}

QTableWidget *NvBudgetView::getTableWidgetDepensesFixes()
{
    return tableWidget_depenses_fixes;
}

QTableWidget *NvBudgetView::getTableWidgetDepensesVariables()
{
    return tableWidget_depenses_variables;
}

QTableWidget *NvBudgetView::getTableWidgetAutresDepenses()
{
    return tableWidget_autres_depenses;
}

QPushButton *NvBudgetView::getBtnValide()
{
    return btnValide;
}

QFrame *NvBudgetView::getFrame()
{
    return frame;
}