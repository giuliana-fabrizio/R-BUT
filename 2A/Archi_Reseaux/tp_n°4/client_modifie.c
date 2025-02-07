#include <arpa/inet.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <unistd.h>

#define DUREE_ATTENTE 2
#define TAILLE_INITIALE 100

int main (int argc, char * argv []) {

    if (argc != 3) {
        fprintf(stderr, "3 arguments attendus\n");
        exit(EXIT_FAILURE);
    }

    char * message, * messageServ, * nomMachine = argv[1];
    int lu, port = atoi(argv[2]), sock, taille_message = TAILLE_INITIALE, taille_message_serv = TAILLE_INITIALE;

    struct hostent * host;
    struct sockaddr_in adrs = {
        .sin_family = AF_INET,
        .sin_port = htons(port)
    };

    if ((message = malloc(TAILLE_INITIALE * sizeof(char))) == NULL) {
        fprintf(stderr, "Erreur d'allocation de mémoire\n");
        exit(EXIT_FAILURE);
    }

    if ((messageServ = malloc(TAILLE_INITIALE * sizeof(char))) == NULL) {
        fprintf(stderr, "Erreur d'allocation de mémoire\n");
        exit(EXIT_FAILURE);
    }

    if ((host = gethostbyname(nomMachine)) == NULL) {
        perror("Gethostbyname ");
        exit(EXIT_FAILURE);
    }

    adrs.sin_addr.s_addr = ((struct in_addr *)(host->h_addr_list[0]))->s_addr;

    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        perror("Socket ");
        exit(EXIT_FAILURE);
    }

    if (connect(sock, (struct sockaddr *)&adrs, sizeof adrs) != 0) {
        perror("Connect ");
        exit(EXIT_FAILURE);
    }

    while (1) {

        if ((lu = read(STDIN_FILENO, message, taille_message)) < 0) {
            perror("Read ");
            exit(EXIT_FAILURE);
        }

        if (lu >= taille_message) {
            taille_message *= 2;
            if ((message = realloc(message, taille_message * sizeof(char))) == NULL) {
                fprintf(stderr, "Erreur de re-allocation de mémoire\n");
                exit(EXIT_FAILURE);
            }
        }

        if (write(sock, message, lu) != lu) {
            perror("Write ");
            exit(EXIT_FAILURE);
        }

        if ((lu = read(sock, messageServ, taille_message_serv)) < 0) {
            perror("Read ");
            exit(EXIT_FAILURE);
        }

        if (lu >= taille_message_serv) {
            taille_message_serv *= 2;
            if ((messageServ = realloc(messageServ, taille_message_serv * sizeof(char))) == NULL) {
                fprintf(stderr, "Erreur de re-allocation de mémoire\n");
                exit(EXIT_FAILURE);
            }
        }

        if (write(STDOUT_FILENO, messageServ, lu) != lu) {
            perror("Write ");
            exit(EXIT_FAILURE);
        }
    }
}
