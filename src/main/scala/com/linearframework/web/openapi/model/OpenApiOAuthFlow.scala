package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
sealed trait OpenApiOAuthFlow {
  val authorizationUrl: Option[String]
  val tokenUrl: Option[String]
  val refreshUrl: Option[String]
  val scopes: Map[String, String]
}

case class OpenApiImplicitOAuthFlow(
  override val authorizationUrl: Option[String],
  override val refreshUrl: Option[String] = None,
  override val scopes: Map[String, String]
) extends OpenApiOAuthFlow {
  final override val tokenUrl: Option[String] = None
}

case class OpenApiPasswordOAuthFlow(
  override val tokenUrl: Option[String],
  override val refreshUrl: Option[String] = None,
  override val scopes: Map[String, String]
) extends OpenApiOAuthFlow {
  final override val authorizationUrl: Option[String] = None
}

case class OpenApiClientCredentialsOAuthFlow(
  override val tokenUrl: Option[String],
  override val refreshUrl: Option[String] = None,
  override val scopes: Map[String, String]
) extends OpenApiOAuthFlow {
  final override val authorizationUrl: Option[String] = None
}

case class OpenApiAuthorizationCodeOAuthFlow(
  override val authorizationUrl: Option[String],
  override val tokenUrl: Option[String],
  override val refreshUrl: Option[String] = None,
  override val scopes: Map[String, String]
) extends OpenApiOAuthFlow