SRC = $(wildcard *.c)
BIN = $(SRC:%.c=%)

CC = gcc
CFLAGS = -std=gnu11 -g -Wall -Wextra -Og
LDLIBS = ../client_serveur.a

.PHONY: all clean

all: $(BIN)

clean:
	$(RM) $(BIN)