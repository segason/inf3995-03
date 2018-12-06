#include "DispatcherUser.h"

/**
 * endpoint to identify user
 * 
 */ 

void connect(const std::shared_ptr< restbed::Session > session){
  std::cout << "Tentative d'identification" << std::endl;
  const auto request = session->get_request();
  size_t content_length = request->get_body().size();
  session->fetch(content_length,[](const std::shared_ptr< restbed::Session >& session,
  const restbed::Bytes& body){
    const std::string body_parameter = session->get_request()->get_query_parameter("body","Error getting Query parameter");
    try {
      std::string response = sign_in(body_parameter);
      std::cout << "return response : " << response << std::endl;
      sendResponse(session, createOkResponse(response));
    } catch (BadRequestException& error) {
      std::cout << "error" << std::endl;
      error.print_error();
      sendResponse(session, createBadRequestResponse());
    } catch (ForbiddenException& error) {
      std::cout << "error" << std::endl;
      error.print_error();
      sendResponse(session, createForbiddenResponse());
    }
  });
}

/**
 * endpoint to lock user 
 * 
 */

void lock(const std::shared_ptr< restbed::Session > session) {
  std::cout << "bloquer" << std::endl;
  const auto request = session->get_request();
  size_t content_length = std::stoi(request->get_header("Content-Length"));
  session->fetch(content_length,[](const std::shared_ptr< restbed::Session >& session,
  const restbed::Bytes& body){
    std::cout << "fetch " << std::endl;
    const std::string mac_label = "mac";
    rapidjson::Document document;
    document.SetObject();
    std::string bodyString = std::string(body.begin(), body.end());
    document.Parse<0>(bodyString.c_str(), bodyString.length());
    std::cout << "body : " << bodyString << std::endl;
    std::string mac_value = document[mac_label.c_str()].GetString();
    try {
      const std::string user = "admin";
      checkIfLogin(user);
      lock_user(mac_value, true);
      sendResponse(session, createOkResponse(responseBody::OK));
    } catch (BadRequestException& error) {
      std::cout << "error" << std::endl;
      error.print_error();
      sendResponse(session, createBadRequestResponse());
    } catch (ForbiddenException exception) {
      std::cout << "Requete non autorisé" << std::endl;
      sendResponse(session, createForbiddenResponse());
    }
  });
}

/**
 * endpoint to unlock user 
 * 
 */ 

void unlock(const std::shared_ptr< restbed::Session > session) {
  std::cout << "debloquer" << std::endl;
  const auto request = session->get_request();
  size_t content_length = std::stoi(request->get_header("Content-Length"));
  session->fetch(content_length,[](const std::shared_ptr< restbed::Session >& session,
  const restbed::Bytes& body){
    std::cout << "fetch " << std::endl;
    const std::string mac_label = "mac";
    rapidjson::Document document;
    document.SetObject();
    std::string bodyString = std::string(body.begin(), body.end());
    document.Parse<0>(bodyString.c_str(), bodyString.length());
    std::cout << "body : " << bodyString << std::endl;
    std::string mac_value = document[mac_label.c_str()].GetString();
    try {
      const std::string user = "admin";
      checkIfLogin(user);
      lock_user(mac_value, false);
      sendResponse(session, createOkResponse(responseBody::OK));
    } catch (BadRequestException& error) {
      std::cout << "error" << std::endl;
      error.print_error();
      sendResponse(session, createBadRequestResponse());
    } catch (ForbiddenException exception) {
      std::cout << "Requete non autorisé" << std::endl;
      sendResponse(session, createForbiddenResponse());
    }
  });
}

/**
 * endpoint to get the black list of user
 * 
 */

void get_black_list(const std::shared_ptr< restbed::Session > session) {
  std::cout << "get bkack list " << std::endl;
  try {
    const std::string user = "admin";
    checkIfLogin(user);
    std::string vector_users = getListUsers(get_list_users());
    sendResponse(session, createOkResponse(vector_users));
  } catch (ForbiddenException exception) {
    std::cout << "Requete non autorisé" << std::endl;
    sendResponse(session, createForbiddenResponse());
  }
}


/**
 * endpoint to login the admin
 * 
 */ 

void login(const std::shared_ptr< restbed::Session > session) {
  std::cout << "login" << std::endl;
  const auto request = session->get_request();
  size_t content_length = std::stoi(request->get_header("Content-Length"));
  session->fetch( content_length, [ request ]( const std::shared_ptr< restbed::Session > session, const restbed::Bytes & body )
  {
    std::cout << "fetch success" << std::endl;
    const std::string user_json = "usager";
    const std::string password_json = "mot_de_passe";
    rapidjson::Document document;
    document.SetObject();
    std::string bodyString = std::string(body.begin(), body.end());
    std::cout << "body : " << bodyString << std::endl;
    document.Parse<0>(bodyString.c_str(), bodyString.length());
    try {
      if (document.HasParseError())
        throw BadRequestException();
      std::cout << "je passe " << std::endl;
      std::string user = document[user_json.c_str()].GetString();
      std::string password = document[password_json.c_str()].GetString();
      std::cout << "username : " << user << std::endl;
      std::cout << "password " << std::endl;
      loginSupervisor(user, password);
      std::cout << "quoi ? " << std::endl;
      saveLogin(user);
      std::cout << "ok" << std::endl;
      sendResponse(session, createOkResponse("{}"));
    } catch (BadRequestException exception) {
      std::cout << "bad request " << std::endl;
      sendResponse(session, createBadRequestResponse());
    } catch (ForbiddenException exception) {
      std::cout << "Requete non autorisé" << std::endl;
      sendResponse(session, createForbiddenResponse());
    }
  });
}


/**
 * endpoint to logout the admin
 * 
 */

void logout(const std::shared_ptr< restbed::Session > session) {
  std::cout << "logout" << std::endl;
  const std::string user = "admin";
  try {
    checkIfLogin(user);
    logoutSupervisor(user);
    sendResponse(session, createOkResponse("{}"));
  } catch (UnauthorizedException) {
    std::cout << "l'administrateur ne c'est pas connecté au préalable" << std::endl;
    sendResponse(session, createUnauthorizedResponse());
  } catch (ForbiddenException exception) {
    std::cout << "Requete non autorisé" << std::endl;
    sendResponse(session, createForbiddenResponse());
  }
}


/**
 * 
 * endpoint to change the password of the admin user
 * 
 */ 
void set_password(const std::shared_ptr< restbed::Session > session) {
  std::cout << "Tentative changement mot de passe" << std::endl;
  const auto request = session->get_request();
  size_t content_length = std::stoi(request->get_header("Content-Length"));
  session->fetch(content_length,[](const std::shared_ptr< restbed::Session >& session,
  const restbed::Bytes& body){
    const std::string old_password = "ancien";
    const std::string new_password = "nouveau";
    std::string contentJson = std::string(body.begin(), body.end());
    rapidjson::Document document;
    document.SetObject();
    document.Parse<0>(contentJson.c_str(), contentJson.length());
    try {
      const std::string user = "admin";
      checkIfLogin(user);
      if (document.HasParseError())
        throw BadRequestException();
      update_password(document[old_password.c_str()].GetString(), document[new_password.c_str()].GetString());
      sendResponse(session, createOkResponse(responseBody::OK));
    } catch (BadRequestException& error) {
      error.print_error();
      sendResponse(session, createBadRequestResponse(responseBody::BAD_REQUEST_V2));
    } catch (UnauthorizedException& error) {
      error.print_error();
      sendResponse(session, createUnauthorizedResponse());
    } catch (ForbiddenException exception) {
      std::cout << "Requete non autorisé" << std::endl;
      sendResponse(session, createForbiddenResponse());
    }
  });
}

/**
 * endpoint for get all users (admin function)
 * 
 */  
void get_users(const std::shared_ptr< restbed::Session > session) {
  std::cout << "get users " << std::endl;
   try {
      const std::string user = "admin";
      checkIfLogin(user);
      std::string vector_users = getListUsers(get_list_users());
      std::cout << vector_users << std::endl;
      sendResponse(session, createOkResponse(vector_users));
   } catch (ForbiddenException exception) {
     sendResponse(session, createForbiddenResponse());
   }
}