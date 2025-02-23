#ifndef ACCUEIL_VIEW_HPP
#define ACCUEIL_VIEW_HPP

#include <iostream>
#include <iomanip>
#include <sstream>
#include <QtCharts>
#include <QtWidgets>

#include "data.hpp"
#include "utilisateur_model.hpp"

class AccueilView {

    public:
        AccueilView(Utilisateur *user);
        void init();
        void setStyle();
        void addToWindow();
        void clearAll();
        QFrame* getFrame();

    private:
        Utilisateur *user;

        QLabel *labelDateBudget;
        QLabel *labelRevenu;
        QLabel *labelFixes;
        QLabel *labelVariables;
        QLabel *labelAutres;

        QProgressBar *pgBarDepense;

        QPieSeries *valeurs_depenses_fixes;
        QPieSeries *valeurs_depenses_variables;
        QPieSeries *valeurs_autres_depenses;

        QChart *chart_depenses_fixes;
        QChart *chart_depenses_variables;
        QChart *chart_autres_depenses;

        QChartView *chartView_depenses_fixes;
        QChartView *chartView_depenses_variables;
        QChartView *chartView_autres_depenses;

        QTableWidget *tableWidget_revenus;
        QTableWidget *tableWidget_depenses_fixes;
        QTableWidget *tableWidget_depenses_variables;
        QTableWidget *tableWidget_autres_depenses;

        QFrame *frame;

        QHBoxLayout *hBox_revenus;
        QHBoxLayout *hBox_depenses_fixes;
        QHBoxLayout *hBox_depenses_variables;
        QHBoxLayout *hBox_autres_depenses;

        QGridLayout *gridLayout;
        QGridLayout *layout;
};

#endif