#ifndef MENU_HPP
#define MENU_HPP

#include <QtWidgets>

class Menu {

    public:
        Menu();
        void init();
        QMenuBar *getMenuBar();
        QAction *getActionPageAccueil();
        QAction *getActionPageBudget();
        QAction *getActionPageDepense();
        QAction *getActionPageHistorique();

    private:
        QMenuBar *menuBar;
        QMenu *menu;
        QAction *pageAccueil;
        QAction *pageBudget;
        QAction *pageDepense;
        QAction *pageHistorique;
};

#endif