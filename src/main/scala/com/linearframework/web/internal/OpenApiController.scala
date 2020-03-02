package com.linearframework.web.internal

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.linearframework.web._
import com.linearframework.web.openapi.model.OpenApiDocument

private[web] class OpenApiController(path: String, openApi: OpenApiDocument) extends Controller {
  private lazy val json = {
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    mapper.setSerializationInclusion(Include.NON_ABSENT)

    val module = new SimpleModule()
    module.addSerializer(classOf[Either[_, _]], new OpenAPIEitherSerializer(null))
    mapper.registerModule(module)

    mapper
  }

  private lazy val cache = json.writerWithDefaultPrettyPrinter().writeValueAsString(openApi)

  GET(path) { (_, response) =>
    response.setContentType(JSON)
    cache
  }
}
