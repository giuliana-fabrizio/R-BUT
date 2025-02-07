#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "../client_serveur.h"

#define DEBUG 0
#define PORT 6666

void terminaison() {
    fprintf(stderr, "\nsignal reçu, terminaison du serveur.\n");
    exit(0);
}

int main (void) {
    int socket_serv, socket_client, fils;

    if ((socket_serv = creer_serveur_tcp(PORT, DEBUG)) < 0) {
        perror("Erreur création serveur : ");
        return EXIT_FAILURE;
    }

    struct sigaction action = {
        .sa_handler = terminaison,
        .sa_flags   = 0,
    };

    while (1) {
        if((socket_client = attendre_client_tcp(socket_serv, DEBUG)) < 0) {
            perror("Erreur attente client : ");
            return EXIT_FAILURE;
        }

        if (sigaction(SIGUSR1, &action, NULL) == -1) fprintf(stderr, "\n erreur pour le signal %i : \n", SIGUSR1);

        if ((fils = fork()) == -1) {
            perror("Erreur fork : ");
            return EXIT_FAILURE;
        }

        if (fils == 0) {
            if (close(socket_serv) < 0) {
                perror("Erreur close : ");
                return EXIT_FAILURE;
            }

            unsigned taille;
            if (read(socket_client, &taille, sizeof taille) != sizeof taille) {
                perror("Erreur read : ");
                return EXIT_FAILURE;
            }

            int tab [taille];
            // int * tab = malloc(taille * sizeof tab[0]);
            // if (tab == NULL) {
            //     fprintf(stderr, "Erreur tableau par allocation dynamique\n");
            //     return EXIT_FAILURE;
            // }

            if (read(socket_client, tab, sizeof tab) != (int)sizeof tab) {
                perror("Erreur read : ");
                return EXIT_FAILURE;
            }

            int somme = 0;
            for (unsigned i = 0; i < taille; i += 1) somme += tab[i];

            if (write(socket_client, &somme, sizeof somme) != sizeof somme) {
                perror("Erreur write : ");
                return EXIT_FAILURE;
            }
        }

        if (close(socket_client) < 0) {
            perror("Erreur close : ");
            return EXIT_FAILURE;
        }
    }
}