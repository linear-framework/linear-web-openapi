package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiExample(
  summary: Option[String] = None,
  description: Option[String] = None,
  value: Option[Any] = None,
  externalValue: Option[String] = None
)
