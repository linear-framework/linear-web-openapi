package com.linearframework.web.internal

import com.linearframework.web._
import com.linearframework.web.openapi.model.OpenApiDocument

private[web] class OpenApiController(path: String, openApi: OpenApiDocument) extends Controller {
  // todo: JSON
//  private lazy val cache = openApi.json
//
//  GET(path) { (_, response) =>
//    response.setContentType(JSON)
//    cache
//  }

}
