#include "../Fichiers_hpp/menu.hpp"

Menu::Menu() {
    menuBar = new QMenuBar();
    menu = new QMenu("Menu", menuBar);
    init();
}

void Menu::init() {
    pageAccueil = menu->addAction("Accueil");
    pageBudget = menu->addAction("Définir un nouveau budget");
    pageDepense = menu->addAction("Définir une nouvelle dépense");
    pageHistorique = menu->addAction("Historique des dépenses");

    menuBar->addMenu(menu);
}

QMenuBar* Menu::getMenuBar() {
    return menuBar;
}

QAction* Menu::getActionPageAccueil() {
    return pageAccueil;
}

QAction* Menu::getActionPageBudget() {
    return pageBudget;
}

QAction* Menu::getActionPageDepense() {
    return pageDepense;
}

QAction* Menu::getActionPageHistorique() {
    return pageHistorique;
}