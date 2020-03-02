package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiOAuthFlows(
  `implicit`: Option[OpenApiImplicitOAuthFlow] = None,
  password: Option[OpenApiPasswordOAuthFlow] = None,
  clientCredentials: Option[OpenApiClientCredentialsOAuthFlow] = None,
  authorizationCode: Option[OpenApiAuthorizationCodeOAuthFlow] = None
)
