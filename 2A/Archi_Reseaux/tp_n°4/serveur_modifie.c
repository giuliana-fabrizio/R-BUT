#include <arpa/inet.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <unistd.h>

#define SERVER_BACKLOG 16
#define NB_CLIENT_INITIAL 10
#define TAILLE_INITIALE 100

int listeClients[NB_CLIENT_INITIAL];
int nbClients = 0;

void supprimerClient(int sockClient) {
    int estTrouve = 0;
    for (int i = 0; i < nbClients - 1; i += 1) {
        if (listeClients[i] == sockClient || estTrouve) {
            estTrouve = 1;
            listeClients[i] = listeClients[i + 1];
        }
    }
    nbClients -= 1;
}

void * fonction (void * arg) {

    char * message = malloc(TAILLE_INITIALE * sizeof(char));
    char reponse [] = "Message bien reçu.\n";
    int lu, sockClient = *((int *)arg), taille_message = TAILLE_INITIALE;

    if (message == NULL) {
        fprintf(stderr, "Erreur d'allocation de mémoire\n");
        exit(EXIT_FAILURE);
    }

    while((lu = read(sockClient, message, taille_message)) > 0) {

        if (lu >= taille_message) {
            taille_message *= 2;
            if ((message = realloc(message, taille_message * sizeof(char))) == NULL) {
                fprintf(stderr, "Erreur de re-allocation de mémoire\n");
                exit(EXIT_FAILURE);
            }
        }

        if (write(STDOUT_FILENO, message, lu) != lu) {
            perror("Write : ");
            exit(EXIT_FAILURE);
        }

        for (int i = 0; i < nbClients; i += 1) {
            if (listeClients[i] != sockClient) {
                if (write(listeClients[i], message, lu) != lu) {
                    perror("Write ");
                    exit(EXIT_FAILURE);
                }
            }
            else 
                if (write(listeClients[i], reponse, sizeof(reponse)) != sizeof(reponse)) {
                    perror("Write ");
                    exit(EXIT_FAILURE);
                }
        }
    }

    free(message);
    supprimerClient(sockClient);

    if (lu < 0) {
        perror("Read ");
        exit(EXIT_FAILURE);
    }

    return NULL;
}

int main (int argc, char * argv []) {

    if (argc != 2) {
        fprintf(stderr, "%i arguments fournis mais 1 attendu\n", argc);
        return EXIT_FAILURE;
    }

    int port = atoi(argv[1]), sockClient, sockServ;

    struct sockaddr_in addr = {
        .sin_family = AF_INET,
        .sin_port = htons(port)
    };
    struct sockaddr_in org_cnxion;
    socklen_t taille_origine = sizeof org_cnxion;

    if ((sockServ = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        perror("Erreur socket ");
        return EXIT_FAILURE;
    }

    if (bind(sockServ, (struct sockaddr *)&addr, sizeof addr) == -1) {
        perror("Erreur bind ");
        return EXIT_FAILURE;
    }

    if (listen(sockServ, SERVER_BACKLOG) < 0) {
        perror("Erreur listen ");
        return EXIT_FAILURE;
    }

    while(1) {

        if ((sockClient = accept(sockServ, (struct sockaddr *)&org_cnxion, &taille_origine)) < 0) {
            perror("Ereur accept ");
            return EXIT_FAILURE;
        }

        listeClients[nbClients] = sockClient;
        nbClients += 1;

        fprintf(stdout, "Adresse et n° de port utilisé : [%s ; %i]\n",
                    inet_ntoa(org_cnxion.sin_addr), ntohs(org_cnxion.sin_port));

        pthread_t thread;
        if (pthread_create(&thread, NULL, fonction, &sockClient) != 0) {
            perror("pthread_create");
            exit(EXIT_FAILURE);
        }
    }
}
