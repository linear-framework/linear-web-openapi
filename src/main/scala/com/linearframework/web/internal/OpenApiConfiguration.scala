package com.linearframework.web.internal

import com.linearframework.web.openapi.model.{OpenApiInfo, OpenApiSecurityScheme}

private[web] case class OpenApiConfiguration(
  path: String,
  info: OpenApiInfo,
  authentication: Map[String, OpenApiSecurityScheme]
)
