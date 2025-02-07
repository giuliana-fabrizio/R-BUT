#include <arpa/inet.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

#define BACKLOG 16
#define PORT 4021
#define SIZEFICH 256

int main (void) {
    int fils, socket_serv, socket_client;

    if ((socket_serv = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        perror("Erreur socket : ");
        return EXIT_FAILURE;
    }

    struct sockaddr_in addr = {
        .sin_family = AF_INET,
        .sin_port = PORT
    };

    if (bind(socket_serv, (struct sockaddr *)&addr, sizeof addr) == -1) {
        perror("Erreur bind : ");
        return EXIT_FAILURE;
    }

    while (1) {
        if (listen(socket_serv, BACKLOG) == -1) {
            perror("Erreur bind : ");
            return EXIT_FAILURE;
        }

        if ((socket_client = accept(socket_serv, NULL, NULL)) == -1) {
            perror("Erreur accept : ");
            return EXIT_FAILURE;
        }

        if ((fils = fork()) == -1) {
            perror("Erreur fork : ");
            return EXIT_FAILURE;
        }

        if (fils == 0) {
            close(socket_serv);

            char fichier [SIZEFICH];
            if (read(socket_client, fichier, sizeof fichier) < 0) {
                perror("Erreur read : ");
                return EXIT_FAILURE;
            }
            fprintf(stdout, "Message reçu : %s est le nom du fichier voulu\n", fichier);
            char * cmd_print [] = {"cat", fichier, NULL};
            if (dup2(socket_client, STDOUT_FILENO) < 0) {
                perror("Erreur dup2 : ");
                return EXIT_FAILURE;
            }
            execvp(cmd_print[0], cmd_print);
            return EXIT_FAILURE;
        }
        close(socket_client);
    }
    return EXIT_SUCCESS;
}