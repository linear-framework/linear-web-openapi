package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiHeader(
  description: Option[String] = None,
  required: Option[Boolean] = None,
  deprecated: Option[Boolean] = None,
  style: Option[String] = None,
  explode: Option[Boolean] = None,
  schema: Option[Either[OpenApiSchema, OpenApiReference]] = None,
  example: Option[Any] = None,
  examples: Option[Map[String, Either[OpenApiExample, OpenApiReference]]] = None,
  content: Option[Map[String, OpenApiMediaType]] = None
)
