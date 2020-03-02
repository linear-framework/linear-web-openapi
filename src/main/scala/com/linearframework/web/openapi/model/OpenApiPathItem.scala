package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiPathItem(
  $ref: Option[String] = None,
  summary: Option[String] = None,
  description: Option[String] = None,
  get: Option[OpenApiOperation] = None,
  put: Option[OpenApiOperation] = None,
  post: Option[OpenApiOperation] = None,
  delete: Option[OpenApiOperation] = None,
  options: Option[OpenApiOperation] = None,
  head: Option[OpenApiOperation] = None,
  patch: Option[OpenApiOperation] = None,
  trace: Option[OpenApiOperation] = None,
  servers: Option[List[OpenApiServer]] = None,
  parameters: Option[List[Either[OpenApiParameter, OpenApiReference]]] = None
)
