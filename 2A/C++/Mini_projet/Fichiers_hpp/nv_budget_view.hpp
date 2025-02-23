#ifndef NV_BUDGET_VIEW_HPP
#define NV_BUDGET_VIEW_HPP

#include <QtWidgets>

#include "data.hpp"

class NvBudgetView {

public:
    NvBudgetView();
    void init();
    void setStyle();
    void addToWindow();

    QComboBox *getComboBoxJourDebut();
    QComboBox *getComboBoxJourFin();
    QComboBox *getComboBoxAnneeDebut();
    QComboBox *getComboBoxMoisDebut();
    QComboBox *getComboBoxMoisFin();
    QComboBox *getComboBoxAnneeFin();

    QTableWidget *getTableWidgetRevenus();
    QTableWidget *getTableWidgetDepensesFixes();
    QTableWidget *getTableWidgetDepensesVariables();
    QTableWidget *getTableWidgetAutresDepenses();

    QPushButton *getBtnValide();

    QFrame *getFrame();

    private:
        QLabel *labelPage;
        QLabel *labelDateDebut;
        QLabel *labelDateFin;
        QLabel *labelPrevoir;
        QLabel *labelRevenu;
        QLabel *labelFixes;
        QLabel *labelVariables;
        QLabel *labelAutres;

        QComboBox *comboBoxJourDebut;
        QComboBox *comboBoxJourFin;
        QComboBox *comboBoxAnneeDebut;
        QComboBox *comboBoxMoisDebut;
        QComboBox *comboBoxMoisFin;
        QComboBox *comboBoxAnneeFin;

        QDoubleValidator *validateur;
        QLineEdit *lineText;

        QTableWidget *tableWidget_revenus;
        QTableWidget *tableWidget_depenses_fixes;
        QTableWidget *tableWidget_depenses_variables;
        QTableWidget *tableWidget_autres_depenses;

        QPushButton *btnValide;

        QHBoxLayout *hBoxDateDebut;
        QHBoxLayout *hBoxDateFin;

        QFrame *frame;
        QGridLayout *gridLayoutTables;
        QGridLayout *layout;
};

#endif