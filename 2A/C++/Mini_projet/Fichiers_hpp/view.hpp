#ifndef VIEW_HPP
#define VIEW_HPP

#include "accueil_view.hpp"
#include "lister_toutes_depenses_view.hpp"
#include "menu.hpp"
#include "nv_budget_view.hpp"
#include "nvle_depense_view.hpp"
#include "utilisateur_model.hpp"

#include <iostream>
#include <QtWidgets>

class View {

    public:
        View(Utilisateur *user);
        void setStyle();
        void addWidget();
        void addToScene(int nb);
        Menu *getMenu();
        AccueilView *getAccueilView();
        NvBudgetView *getNvBudgetView();
        NvleDepenseView *getNvleDepenseView();
        ListerToutesDepensesView *getListerToutesDepensesView();
        QWidget *getWindow();

    private:
        Menu *menu;
        Utilisateur *user;
        AccueilView *accueilView;
        NvBudgetView *nvBudgetView;
        NvleDepenseView *nvleDepenseView;
        ListerToutesDepensesView *listerToutesDepensesView;

        QStackedWidget *stackedWidget;
        QVBoxLayout *vBoxLayout;
        QWidget *window;
};

#endif