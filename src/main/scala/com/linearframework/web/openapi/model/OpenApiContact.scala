package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiContact(
  name: Option[String] = None,
  url: Option[String] = None,
  email: Option[String] = None
)
