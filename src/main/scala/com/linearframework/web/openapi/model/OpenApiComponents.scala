package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiComponents(
  schemas: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None,
  responses: Option[Map[String, Either[OpenApiResponse, OpenApiReference]]] = None,
  parameters: Option[Map[String, Either[OpenApiParameter, OpenApiReference]]] = None,
  examples: Option[Map[String, Either[OpenApiExample, OpenApiReference]]] = None,
  requestBodies: Option[Map[String, Either[OpenApiRequestBody, OpenApiReference]]] = None,
  headers: Option[Map[String, Either[OpenApiHeader, OpenApiReference]]] = None,
  securitySchemes: Option[Map[String, Either[OpenApiSecurityScheme, OpenApiReference]]] = None,
  links: Option[Map[String, Either[OpenApiLink, OpenApiReference]]] = None,
  callbacks: Option[Map[String, Either[Map[String, OpenApiPathItem], OpenApiReference]]] = None
)
