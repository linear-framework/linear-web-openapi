package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiServer(
  url: String,
  description: Option[String] = None,
  variables: Option[Map[String, OpenApiServerVariable]] = None
)
