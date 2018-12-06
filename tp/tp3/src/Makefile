PROG = server
CC = g++
LDFLAGS = -lboost_system -lcrypto -lssl -lnavajo -lpthread -pthread -L /usr/local/lib
CPPFLAGS = -g -Wall -std=c++11 -L /usr/lib  -L /usr/local/lib
OBJS = Rest.o ServerFpga.o main.o
PRECOMPILED_HEADER = Rest.h.gch ServerFpga.h.gch

$(PROG) : $(OBJS)
	$(CC) $(LDFLAGS) -o $(PROG) $(OBJS) 
Rest.o : Rest.h
	$(CC) $(CPPFLAGS) -c Rest.cpp Rest.h	
MyServer.o : MyServer.h
	$(CC) $(CPPFLAGS) -c ServerFpga.cpp ServerFpga.h
main.o :
	$(CC) $(CPPFLAGS) -c main.cpp
clean:
	rm -f core $(PROG) $(OBJS) $(PRECOMPILED_HEADER)