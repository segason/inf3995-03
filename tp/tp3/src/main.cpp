#include <cstddef>
#include <cstdint>
#include <new>
#include <iostream>

#include "ServerFpga.h"


int main()
{
	ServerFpga server("132.207.89.35", 80);

	server.init();
	server.exec();
	return 0;
}

