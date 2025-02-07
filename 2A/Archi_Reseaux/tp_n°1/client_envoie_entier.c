#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "../client_serveur.h"

#define DEBUG 0
#define PORT 6666

int main(void) {
    int socket;

    if((socket = creer_client_tcp("127.0.0.1", PORT, DEBUG)) < 0) {
        perror("Erreur création du client : ");
        return EXIT_FAILURE;
    }
    fprintf(stdout, "Saisir un entier non signé sur 4 octets : ");
    unsigned valeur;
    if (scanf("%u", &valeur) < 0) {
        perror("Erreur scanf : ");
        return EXIT_FAILURE;
    }
    if (write(socket, &valeur, sizeof valeur) != sizeof valeur) {
        perror("Erreur write : ");
        return EXIT_FAILURE;
    }
    return EXIT_SUCCESS;
}