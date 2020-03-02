package com.linearframework.web

case class ResponseDocumentation(
  private[web] val description: String,
  private[web] val contentTypes: Map[ContentType, SchemaDocumentation] = Map()
)