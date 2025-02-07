#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <unistd.h>

#define SERVEUR_BACKLOG 16
#define TAILLE_INITIALE 100

int main (int argc, char * argv []) {

    if (argc != 2) {
        fprintf(stderr, "2 arguments attendus\n");
        exit(EXIT_FAILURE);
    }

    int lu, port = atoi(argv[1]), sockClient, sockServ;
    pid_t fils;
    struct sockaddr_in adrs = {
        .sin_family = AF_INET,
        .sin_port = htons(port)
    };
    struct sockaddr_in org_cnxion;
    socklen_t taille_origine = sizeof org_cnxion;

    if ((sockServ = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        perror("Socket : ");
        exit(EXIT_FAILURE);
    }

    if (bind(sockServ, (struct sockaddr *)&adrs, sizeof adrs) < 0) {
        perror("Bind : ");
        exit(EXIT_FAILURE);
    }

    if (listen(sockServ, SERVEUR_BACKLOG) != 0) {
        perror("Listen ");
        exit(EXIT_FAILURE);
    }

    while (1) {

        if ((sockClient = accept(sockServ, (struct sockaddr *)&org_cnxion, &taille_origine)) < 0) {
            perror("Accept ");
            exit(EXIT_FAILURE);
        }

        if ((fils = fork()) < 0) {
            perror("Fork ");
            exit(EXIT_FAILURE);
        }

        if (fils != 0) {

            if (close(sockServ) != 0) {
                perror("Close ");
                exit(EXIT_FAILURE);
            }

            fprintf(stdout, "Adresse et n° de port utilisé : [%s ; %i]\n",
                    inet_ntoa(org_cnxion.sin_addr), ntohs(org_cnxion.sin_port));

            char * message = malloc(TAILLE_INITIALE * sizeof(char));
            if (message == NULL) {
                fprintf(stderr, "Erreur d'allocation de mémoire\n");
                exit(EXIT_FAILURE);
            }
            int taille_message = TAILLE_INITIALE;

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
            }

            free(message);

            if (lu < 0) {
                perror("Read : ");
                exit(EXIT_FAILURE);
            }
        }

        if (close(sockClient) != 0) {
            perror("Close ");
            exit(EXIT_FAILURE);
        }
    }
}
