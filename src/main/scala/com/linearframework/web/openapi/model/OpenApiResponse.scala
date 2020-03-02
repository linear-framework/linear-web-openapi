package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiResponse(
  description: String,
  headers: Option[Map[String, Either[OpenApiHeader, OpenApiReference]]] = None,
  content: Option[Map[String, OpenApiMediaType]] = None,
  links: Option[Map[String, Either[OpenApiLink, OpenApiReference]]] = None
)
