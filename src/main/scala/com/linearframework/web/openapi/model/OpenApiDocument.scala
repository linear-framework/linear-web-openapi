package com.linearframework.web.openapi.model

/**
  * Scala implementation of the OpenAPI Specification.
  * @see [[https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.2.md]]
  */
case class OpenApiDocument(
  info: OpenApiInfo,
  servers: Option[List[OpenApiServer]] = None,
  paths: Map[String, OpenApiPathItem],
  components: Option[OpenApiComponents] = None,
  security: Option[Map[String, List[String]]] = None,
  tags: Option[List[OpenApiTag]] = None,
  externalDocs: Option[OpenApiExternalDocumentation] = None
) {
  final val openapi: String = "3.0.2"
}
