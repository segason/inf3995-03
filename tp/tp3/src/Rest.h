#ifndef REST_H
#define REST_H
#include <string.h> 
#include <iostream>
#include <fstream>
#include <ios>
#include "libnavajo/libnavajo.hh"

/**
 * Class which create all page we need (/test1, /test2, /test3)
 */  
class MyDynamicRepository : public DynamicRepository
{  
  /**
   * class which create the /test1 page
   */  
  class Test1: public DynamicPage
  {
    public:
      bool getPage(HttpRequest* request, HttpResponse *response);
  } test1;
  
  /**
   * class which create the /test2 page
   */  
  class Test2: public DynamicPage
  {
    public:
      bool getPage(HttpRequest* request, HttpResponse *response);
  } test2;

  /**
   * class which create the /test3 page
   */  
  class Test3: public DynamicPage
  {
    public: 
      bool getPage(HttpRequest* request, HttpResponse *response);
  } test3;

  public:
    MyDynamicRepository() : DynamicRepository()
    {
      add("test1",&test1);
      add("test2",&test2);
      add("test3",&test3);
    }
};
#endif