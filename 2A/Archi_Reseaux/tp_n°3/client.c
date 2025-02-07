# include <stdio.h>
# include <stdlib.h>
# include "../client_serveur.h"

# define DEBUG 0
# define PORT 7777
# define SIZE = 16

int main (void) {
    char * serveur = "127.0.0.1";
    int cpt = 0, nvlTaille, socket, taille;

    socket = creer_client_tcp(serveur, 7777, DEBUG);
    if (socket < 0) {
        fprintf(stderr, "Echec de la connexion au serveur\n");
        exit(EXIT_FAILURE);
    }

    fprintf(stdout, "Saisir la taille du tableau : ");
    if (scanf("%i", &taille) < 0) {
        perror("Error scanf : ");
        return EXIT_FAILURE;
    }

    int tab[taille];

    if (write(socket, &taille, sizeof taille) != sizeof taille) {
        perror("Error write from server : ");
        return EXIT_FAILURE;
    }

    fprintf(stdout, "Saisir %i entiers : \n", taille);
    while (cpt < taille) {
        if (scanf("%i", &tab[cpt]) < 0) {
            perror("Error scanf : ");
            return EXIT_FAILURE;
        }
        cpt += 1;
    }

    if (write(socket, tab, sizeof tab) != (int)sizeof tab) {
        perror("Error write to server : ");
        return EXIT_FAILURE;
    }

    if (read(socket, &nvlTaille, sizeof(int)) != (int)sizeof(int)) {
        perror("Error read from server : ");
        return EXIT_FAILURE;
    }

    if (read(socket, tab, sizeof(int) * nvlTaille) != (int) sizeof(int) * nvlTaille) {
        perror("Error read from server : ");
        return EXIT_FAILURE;
    }

    fprintf(stdout, "Nouveau tableau :\n\t");
    for (int i=0; i<nvlTaille; i += 1) fprintf(stdout, "%i\t", tab[i]);
    fprintf(stdout, "\n");
}