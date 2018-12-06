
#include <chrono>
#include <iostream>

#include "ServerFpga.h"

WebServer *myWebServer = NULL;

/**
 * stop the server when we have interrupt
 * @param signal : signal which stop the webServer
 * 
 */ 
void stop(int signal) {
	//NVJ_LOG->append(NVJ_FATAL, "test");
	if (myWebServer != NULL) myWebServer->stopService();
}

/**
 * destructor : delete myWebServer and delete the instance of LogRecorder
 */ 
ServerFpga::~ServerFpga(){
	delete _repoFile;
	delete _repoImage;
	delete myWebServer;
	LogRecorder::freeInstance();
}


/**
 * method public
 * intialize the webServer
 */ 
void ServerFpga::init() {
	initInterruption();
	initServer();
	initLog();
}

/**
 * method public
 * execute the webServer
 */ 
void ServerFpga::exec()
{
	loadImage();
	loadPage();
	myWebServer->startService();
	myWebServer->wait();
	LogRecorder::freeInstance();
}

/**
 * method private
 * initialize logs of server
 */ 
void ServerFpga::initLog(){
	NVJ_LOG->addLogOutput(new LogStdOutput);
	NVJ_LOG->addLogOutput(new LogFile("/var/log/server.log"));
	NVJ_LOG->setDebugMode();
}

/**
 * method private
 * initialize the server
 */ 
void ServerFpga::initServer(){
	myWebServer = new WebServer;
	myWebServer->setServerPort(_port);
	myWebServer->setWebServerName(_host); 
}

/**
 * method private
 * initialize interruption with SIGTERM and SIGINT
 */ 
void ServerFpga::initInterruption(){
	signal( SIGTERM, stop );
	signal( SIGINT, stop );
}

/**
 * method private
 * load all the directory images in the server
 */
void ServerFpga::loadImage(){
	_repoImage = new LocalRepository("images", "../images");
  myWebServer->addRepository(_repoImage);
}

/**
 * method private
 * load all pages (test1, test2, test3) in the server
 */ 
void ServerFpga::loadPage(){
	_repoFile = new MyDynamicRepository;
	myWebServer->addRepository(_repoFile);
}