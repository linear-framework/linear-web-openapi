package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiMediaType(
  schema: Option[Either[OpenApiSchema, OpenApiReference]] = None,
  example: Option[Any] = None,
  examples: Option[Map[String, Either[OpenApiExample, OpenApiReference]]] = None,
  encoding: Option[Map[String, OpenApiEncoding]] = None
)
