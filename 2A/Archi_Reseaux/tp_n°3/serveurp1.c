# include <stdio.h>
# include <stdlib.h>
# include "../client_serveur.h"

# define DEBUG 0
# define PORT 7777
# define SIZE = 16

int main (void) {
    int fils, socket_client, socket_serveur, taille;

    socket_serveur = creer_serveur_tcp(PORT, DEBUG);
    if (socket_serveur < 0) {
        fprintf(stderr, "Erreur lors de la création du serveur\n");
        return EXIT_FAILURE;
    }

    while (1) {
        socket_client = attendre_client_tcp(socket_serveur, DEBUG);
        if (socket_client < 0) {
            fprintf(stderr, "Echec de l'attente du client\n");
            return EXIT_FAILURE;
        }

        if ((fils = fork()) == -1) {
            perror("Error fork : ");
            return EXIT_FAILURE;
        }

        if (fils == 0) {
            close(socket_serveur);
            if (read(socket_client, &taille, sizeof(int)) != sizeof(int)) {
                perror("Error read from server : ");
                close(socket_client);
                return EXIT_FAILURE;
            }

            if (taille > 16) {
                fprintf(stderr, "Fermeture socket client %i", socket_client);
                close(socket_client);
            }

            int tab [taille];

            if (read(socket_client, tab, sizeof(tab)) < 0) {
                perror("Error read from client : ");
                close(socket_client);
                return EXIT_FAILURE;
            }

            int ecr = 0;

            for (int lect = 0; lect < taille; lect += 1) {
                if (tab[lect] != 0) {
                    tab[ecr] = tab[lect];
                    ecr += 1;
                }
            }

            if (write(socket_client, &ecr, sizeof(int)) != (int)sizeof(int)) {
                perror("Error write to client : ");
                close(socket_client);
                return EXIT_FAILURE;
            }

            if (write(socket_client, tab, ecr * sizeof(int)) < 0) {
                perror("Error write to client : ");
                close(socket_client);
                return EXIT_FAILURE;
            }
        }
        close(socket_client);
    }

    return EXIT_SUCCESS;
}