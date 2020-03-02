package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiEncoding(
  contentType: Option[String] = None,
  headers: Option[Map[String, Either[OpenApiHeader, OpenApiReference]]] = None,
  style: Option[String] = None,
  explode: Option[Boolean] = None,
  allowReserved: Option[Boolean] = None
)
