#ifndef NVLE_DEPENSE_VIEW_HPP
#define NVLE_DEPENSE_VIEW_HPP

#include <QtWidgets>
#include "data.hpp"

class NvleDepenseView
{

public:
    NvleDepenseView();
    void initPage();
    void initComboBoxEnveloppe(int nb);
    void setStyle();
    void addToWindow();

    QComboBox *getComboBoxJour();
    QComboBox *getComboBoxMois();
    QComboBox *getComboBoxAnnee();
    QComboBox *getComboBoxCategorieDepense();
    QComboBox *getComboBoxEnveloppe();

    QLineEdit *getLineEditDescription();
    QLineEdit *getLineEditMontant();

    QPushButton *getBtnValide();

    QFrame *getFrame();

private:
    QLabel *labelPage;
    QLabel *labelDateDepense;
    QLabel *labelMontantDepense;
    QLabel *labelDescriptionDepense;
    QLabel *labelCategoriesDepense;
    QLabel *labelEnveloppeDepense;

    QComboBox *comboBoxJour;
    QComboBox *comboBoxMois;
    QComboBox *comboBoxAnnee;
    QComboBox *comboBoxCategorieDepense;
    QComboBox *comboBoxEnveloppe;

    QDoubleValidator *validateur;
    QLineEdit *lineEditDescription;
    QLineEdit *lineEditMontant;

    QPushButton *btnValide;

    QHBoxLayout *hBoxDate;
    QHBoxLayout *hBoxDescription;
    QHBoxLayout *hBoxMontant;
    QHBoxLayout *hBoxCategorie;

    QFrame *frame;
    QGridLayout *layout;
};

#endif