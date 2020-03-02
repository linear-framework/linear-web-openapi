package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiXml(
  name: Option[String] = None,
  namespace: Option[String] = None,
  prefix: Option[String] = None,
  attribute: Option[Boolean] = None,
  wrapped: Option[Boolean] = None
)
