package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
sealed trait OpenApiLink {
  val operationRef: Option[String]
  val operationId: Option[String]
  val parameters: Option[Map[String, Any]]
  val requestBody: Option[Any]
  val description: Option[String]
  val server: Option[OpenApiServer]
}

case class OpenApiLinkById(
  override val operationId: Option[String],
  override val parameters: Option[Map[String, Any]] = None,
  override val requestBody: Option[Any] = None,
  override val description: Option[String] = None,
  override val server: Option[OpenApiServer] = None
) extends OpenApiLink {
  final override val operationRef: Option[String] = None
}

case class OpenApiLinkByRef(
  override val operationRef: Option[String],
  override val parameters: Option[Map[String, Any]] = None,
  override val requestBody: Option[Any] = None,
  override val description: Option[String] = None,
  override val server: Option[OpenApiServer] = None
) extends OpenApiLink {
  final override val operationId: Option[String] = None
}