package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiOperation(
  tags: Option[List[String]] = None,
  summary: Option[String] = None,
  description: Option[String] = None,
  externalDocs: Option[OpenApiExternalDocumentation] = None,
  operationId: Option[String] = None,
  parameters: Option[List[Either[OpenApiParameter, OpenApiReference]]] = None,
  requestBody: Option[Either[OpenApiRequestBody, OpenApiReference]] = None,
  responses: Map[String, Either[OpenApiResponse, OpenApiReference]],
  callbacks: Option[Map[String, Either[Map[String, OpenApiPathItem], OpenApiReference]]] = None,
  deprecated: Option[Boolean] = None,
  security: Option[List[Map[String, List[String]]]] = None,
  servers: Option[List[OpenApiServer]] = None
)
