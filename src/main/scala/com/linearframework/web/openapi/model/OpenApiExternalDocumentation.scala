package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiExternalDocumentation(
  description: Option[String] = None,
  url: String
)
