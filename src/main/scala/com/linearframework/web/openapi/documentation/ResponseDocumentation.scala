package com.linearframework.web.openapi.documentation

import com.linearframework.web.ContentType

case class ResponseDocumentation(
  private[web] val description: String,
  private[web] val contentTypes: Map[ContentType, SchemaDocumentation] = Map()
)