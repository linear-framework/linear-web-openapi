package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiRequestBody(
  description: Option[String] = None,
  content: Map[String, OpenApiMediaType],
  required: Option[Boolean] = None
)
