# include <stdio.h>
# include <stdlib.h>
# include "../client_serveur.h"
# include <unistd.h>

# define DEBUG 0
# define PORT 7777

int main (void) {
    int fils, socket_client, socket_serveur;

    socket_serveur = creer_serveur_tcp(PORT, DEBUG);
    if (socket_serveur < 0) {
        fprintf(stderr, "Erreur creation de serveur : ");
        return EXIT_FAILURE;
    }

    while (1) {
        socket_client = attendre_client_tcp(socket_serveur, DEBUG);
        if (socket_client < 0) {
            fprintf(stderr, "Echec attente d'un client : ");
            return EXIT_FAILURE;
        }

        if ((fils = fork()) == -1) {
            perror("Erreur fork : ");
            return EXIT_FAILURE;
        }

        if (fils == 0) {
            if (close(socket_serveur) < 0) {
                perror("Erreur close : ");
                return EXIT_FAILURE;
            }

            int attendu, ecr = 0;

            if (read(socket_client, &attendu, sizeof attendu) != sizeof attendu) {
                perror("Error read : ");
                return EXIT_FAILURE;
            }

            int * tab = malloc(sizeof(int) * attendu);
            if (tab == NULL) {
                perror("Error malloc : ");
                return EXIT_FAILURE;
            }

            if (read(socket_client, tab, sizeof tab[0] * attendu) != (int)sizeof tab[0] * attendu) {
                perror("Error read from client : ");
                close(socket_client);
                return EXIT_FAILURE;
            }

            for (int lis = 0; lis < attendu; lis += 1) {
                if (tab[lis] != 0) {
                    tab[ecr] = tab[lis];
                    ecr += 1;
                }
            }

            // realloc(tab, ecr * sizeof(int));

            if(write(socket_client, &ecr, sizeof(int)) != (int)sizeof(int)) {
                perror("Error write : ");
                return EXIT_FAILURE;
            }

            if (write(socket_client, tab, ecr * sizeof tab[0]) != (int)(ecr * sizeof tab[0])) {
                perror("Error write to client : ");
                close(socket_client);
                return EXIT_FAILURE;
            }
        }

        if (close(socket_client) < 0) {
            perror("Erreur close : ");
            return EXIT_FAILURE;
        }
        fprintf(stdout, "Fermeture socket client %i\n", socket_client);
    }
}