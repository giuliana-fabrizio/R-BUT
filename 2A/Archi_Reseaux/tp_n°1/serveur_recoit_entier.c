#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "../client_serveur.h"

#define DEBUG 0
#define PORT 6666

int main(void) {
    int socket_client, socket_serv;

    if((socket_serv = creer_serveur_tcp(PORT, DEBUG)) < 0) {
        perror("Erreur création serveur : ");
        return EXIT_FAILURE;
    }

    if ((socket_client = attendre_client_tcp(socket_serv, DEBUG)) < 0) {
        perror("Erreur attente client");
        return EXIT_FAILURE;
    }

    unsigned valeur;
    if (read(socket_client, &valeur, sizeof valeur) != sizeof valeur) {
        perror("Erreur read : ");
        return EXIT_FAILURE;
    }

    fprintf(stdout, "Valeur reçue : %u \n", valeur);

    if (close(socket_client) < 0) {
        perror("Erreur close : ");
        return EXIT_FAILURE;
    }
    return EXIT_SUCCESS;
}