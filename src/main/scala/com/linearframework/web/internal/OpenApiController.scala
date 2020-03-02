package com.linearframework.web.internal

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.linearframework.web._
import com.linearframework.web.openapi.model.OpenApiDocument

private[web] class OpenApiController(path: String, openApi: OpenApiDocument) extends Controller {
  private lazy val json = {
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    mapper
  }

  private lazy val cache = json.writeValueAsString(openApi)

  GET(path) { (_, response) =>
    response.setContentType(JSON)
    cache
  }
}
