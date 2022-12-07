PROG = cube
CC = g++ -std=c++17 -pedantic-errors -ansi -Wextra -Wall -Wmissing-declarations -Winit-self #strict

include common.mk

$(PROG): $(OBJS)
	$(CC) -o $@ $(OBJS) $(LDFLAGS)
