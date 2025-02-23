#include "./Fichiers_hpp/global.hpp"


int main(int argc, char **argv) {
    QApplication app (argc, argv);

    Utilisateur *user = new Utilisateur();
    View *view = new View(user);
    Controller *controller = new Controller(user, view);

    QMessageBox::information(view->getWindow(), "Information 📇",
            "Allez dans menu > définir un nouveau budget : remplir le formulaire 😆");

    return app.exec();
}