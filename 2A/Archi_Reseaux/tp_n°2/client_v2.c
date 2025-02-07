#include <arpa/inet.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

#define PORT 4021

int main (int argc, char * argv []) {
    if (argc != 2) {
        fprintf(stderr, "%i arguments fournis mais 2 sont attendus\n", argc);
        return EXIT_FAILURE;
    }

    int sock;
    char contenu [PORT];

    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        perror("Erreur socket : ");
        return EXIT_FAILURE;
    }

    struct sockaddr_in addr = {
        .sin_family = AF_INET,
        .sin_port = PORT
    };

    if (connect(sock, (struct sockaddr *)&addr, sizeof addr) == -1) {
        perror("Erreur connect : ");
        return EXIT_FAILURE;
    }

    if (write(sock, argv[1], (sizeof argv[1])+1) < 0) {
        perror("Erreur write : ");
        return EXIT_FAILURE;
    }

    if (read(sock, contenu, sizeof contenu) < 0) {
        perror("Erreur read : ");
        return EXIT_FAILURE;
    }

    fprintf(stdout, "Contenu du fichier %s : \n\t%s\n", argv[1], contenu);
    return EXIT_SUCCESS;
}