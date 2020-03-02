package com.linearframework.web.openapi.documentation

import com.linearframework.web.ContentType

case class RequestDocumentation (
  private[web] val description: String,
  private[web] val required: Boolean,
  private[web] val contentTypes: Map[ContentType, SchemaDocumentation] = Map()
)