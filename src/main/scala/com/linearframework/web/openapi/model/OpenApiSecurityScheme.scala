package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
sealed trait OpenApiSecurityScheme {
  val `type`: String
  val description: Option[String]
  val name: Option[String]
  val in: Option[String]
  val scheme: Option[String]
  val bearerFormat: Option[String]
  val flows: Option[OpenApiOAuthFlows]
  val openIdConnectUrl: Option[String]
}

case class OpenApiApiKeySecurityScheme(
  @transient apiKeyName: String,
  @transient apiKeyIn: String,
  @transient apiKeyDescription: String = null
) extends OpenApiSecurityScheme {
  final override val `type`: String = "apiKey"
  final override val description: Option[String] = Option(apiKeyDescription)
  final override val name: Option[String] = Option(apiKeyName)
  final override val in: Option[String] = Option(apiKeyIn)
  final override val scheme: Option[String] = None
  final override val bearerFormat: Option[String] = None
  final override val flows: Option[OpenApiOAuthFlows] = None
  final override val openIdConnectUrl: Option[String] = None
}

case class OpenApiHttpSecurityScheme(
  @transient httpScheme: String,
  @transient httpDescription: String = null,
  @transient httpBearerFormat: String = null
) extends OpenApiSecurityScheme {
  final override val `type`: String = "http"
  final override val description: Option[String] = Option(httpDescription)
  final override val scheme: Option[String] = Option(httpScheme)
  final override val bearerFormat: Option[String] = Option(httpBearerFormat)
  final override val name: Option[String] = None
  final override val in: Option[String] = None
  final override val flows: Option[OpenApiOAuthFlows] = None
  final override val openIdConnectUrl: Option[String] = None
}

case class OpenApiOAuth2SecurityScheme(
  @transient oathFlows: OpenApiOAuthFlows,
  @transient oathDescription: String = null
) extends OpenApiSecurityScheme {
  final override val `type`: String = "oath2"
  final override val name: Option[String] = None
  final override val in: Option[String] = None
  final override val scheme: Option[String] = None
  final override val bearerFormat: Option[String] = None
  final override val description: Option[String] = Option(oathDescription)
  final override val flows: Option[OpenApiOAuthFlows] = Option(oathFlows)
  final override val openIdConnectUrl: Option[String] = None
}

case class OpenApiOpenIdConnectSecurityScheme(
  @transient openIdUrl: String,
  @transient openIdDescription: String = null
) extends OpenApiSecurityScheme {
  final override val `type`: String = "openIdConnect"
  final override val name: Option[String] = None
  final override val in: Option[String] = None
  final override val scheme: Option[String] = None
  final override val bearerFormat: Option[String] = None
  final override val flows: Option[OpenApiOAuthFlows] = None
  final override val description: Option[String] = Option(openIdDescription)
  final override val openIdConnectUrl: Option[String] = Option(openIdUrl)
}