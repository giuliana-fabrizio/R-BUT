#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "../client_serveur.h"

#define DEBUG 0
#define PORT 6666

int main (void) {
    char * serveur = "127.0.0.1";
    int socket, cpt = 0, somme;
    unsigned taille;

    if ((socket = creer_client_tcp(serveur, PORT, DEBUG)) < 0) {
        perror("Erreur création client : ");
        return EXIT_FAILURE;
    }

    fprintf(stdout, "Saisir un entier non signé sur 4 octets : ");
    if (scanf("%u", &taille) < 0) {
        perror("Erreur scanf : ");
        return EXIT_FAILURE;
    }

    int tab [taille];
    // int * tab = malloc(sizeof tab[0] * taille);
    // if (tab == NULL) {
    //     fprintf(stderr, "Erreur tableau par allocation dynamique\n");
    //     return EXIT_FAILURE;
    // }
    fprintf(stdout, "Saisir %u entier : ", taille);
    while (cpt < (int)taille) {
        if (scanf("%i", &tab[cpt]) < 0) {
            perror("Erreur scanf : ");
            return EXIT_FAILURE;
        }
        cpt += 1;
    }

    if (write(socket, &taille, sizeof taille) != sizeof taille) {
        perror("Erreur write : ");
        return EXIT_FAILURE;
    }

    if (write(socket, tab, sizeof tab) != (int)sizeof tab) {
        perror("Erreur write : ");
        return EXIT_FAILURE;
    }

    if (read(socket, &somme, sizeof somme) != sizeof somme) {
        perror("Erreur read : ");
        return EXIT_FAILURE;
    }

    fprintf(stdout, "Somme reçue : %i\n", somme);
    return EXIT_SUCCESS;
}
