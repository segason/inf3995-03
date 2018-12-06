#pragma once

#include <restbed>
#include <iostream>
#include "defaultResponseBody.h"


std::shared_ptr<restbed::Response> createResponse(int statusCode, const std::string& body);
std::multimap< std::string, std::string > createResponseHeaders(size_t contentLength);

std::shared_ptr<restbed::Response> createOkResponse(const std::string& body = responseBody::OK); //200
std::shared_ptr<restbed::Response> createBadRequestResponse(const std::string& body = responseBody::BAD_REQUEST); //400      
std::shared_ptr<restbed::Response> createUnauthorizedResponse(const std::string& body = responseBody::UNAUTHORIZED); //401
std::shared_ptr<restbed::Response> createForbiddenResponse(const std::string& body = responseBody::FORBIDDEN); //403
std::shared_ptr<restbed::Response> createMethodNotAllowedResponse(const std::string& body = responseBody::METHOD_NOT_ALLOWED); //405
std::shared_ptr<restbed::Response> createConflictResponse(const std::string& body = responseBody::CONFLICT); //409
std::shared_ptr<restbed::Response> createRequestEntityTooLargeResponse(const std::string& body = responseBody::REQUEST_ENTITY_TOO_LARGE); //413
std::shared_ptr<restbed::Response> createUnsupportedMediaTypeResponse(const std::string& body = responseBody::UNSUPPORTED_MEDIA_TYPE); //415
std::shared_ptr<restbed::Response> createInternalServerErrorResponse(const std::string& body = responseBody::INTERNAL_SERVER_ERROR); //500
void sendResponse(const std::shared_ptr< restbed::Session >& session, const std::shared_ptr<restbed::Response>& response);
