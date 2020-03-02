package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiServerVariable(
  enum: Option[List[String]] = None,
  default: String,
  description: Option[String] = None
)
