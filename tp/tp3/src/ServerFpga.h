#ifndef MY_SERVER_H
#define MY_SERVER_H
#include <cstddef>
#include <cstdint>
#include <string>
#include <new>
#include <signal.h> 

#include "libnavajo/WebServer.hh"
#include "libnavajo/LogStdOutput.hh"
#include "libnavajo/LogRecorder.hh"
#include "libnavajo/LogFile.hh"

#include "Rest.h"


/**
 * Class which create and execute a server
 * 
 */ 
class ServerFpga
{
	public:
		ServerFpga(std::string host, int port) : _host(host), _port(port) {};
		~ServerFpga();
		void exec();
		void init();
		
	private:
		std::string _host;
		int _port;
		MyDynamicRepository* _repoFile;
		LocalRepository* _repoImage;
		void initLog();
		void initServer();
		void initInterruption();
		void loadImage();
		void loadPage();
};

#endif