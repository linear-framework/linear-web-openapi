package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiTag(
  name: String,
  description: Option[String] = None,
  externalDocs: Option[OpenApiExternalDocumentation] = None
)
