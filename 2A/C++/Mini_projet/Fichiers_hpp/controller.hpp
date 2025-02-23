#ifndef CONTROLLER_HPP
#define CONTROLLER_HPP

#include "utilisateur_model.hpp"
#include "structure_model.hpp"
#include "view.hpp"

class Controller {

    public:
        Controller(Utilisateur *user, View *view);
        void connect();
        void changePage(int nb);
        void addBudget();
        void donneInfos(int index);
        void addDepense();
        void changeDetailBudget(int index);
        void supprimerBudget(int index);

    private:
        Utilisateur *user;
        View *view;
};

#endif