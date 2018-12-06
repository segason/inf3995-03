#include "Rest.h"

#define TYPE_GET 1

/**
 * Method which is call when user call /test1
 * This method send a response to client
 */ 
bool MyDynamicRepository::Test1::getPage(HttpRequest* request, HttpResponse *response) {
  if (request->getRequestType() == TYPE_GET) {
    response->addSpecificHeader("HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=utf-8");
    const std::string MESSAGE = "test 1 est bon...";
    return fromString(MESSAGE,response);
  }
  return false;
}

/**
 * Method which is call when user call /test2
 * This method send a response to client
 */ 
bool MyDynamicRepository::Test2::getPage(HttpRequest* request, HttpResponse *response) {
  if (request->getRequestType() == TYPE_GET) {
    response->addSpecificHeader("HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=utf-8");
    const std::string MESSAGE = "<a href=\"http://www.polymtl.ca\">Poly Mtl</a>";
    return fromString(MESSAGE,response);
  }
  return false;
}

/**
 * Method which is call when user call /test3
 * This method send a response to client
 */ 
bool MyDynamicRepository::Test3::getPage(HttpRequest* request, HttpResponse *response) {
  if (request->getRequestType() == TYPE_GET) {
    response->addSpecificHeader("HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=utf-8");
    const std::string MESSAGE = "<img src=\"images/test3.png\" alt=\"image du test 3 \" height=\"400\" width=\"600\"/>";
    return fromString(MESSAGE,response);
  }
  return false;
}