package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiDiscriminator(
  propertyName: String,
  mapping: Option[Map[String, String]]
)
