package com.linearframework.hello

import com.linearframework.web._
import scala.util.Try

case class ErrorRequestBody(name: String, age: Int)

object HelloController extends Controller with Swagger {

  GET("/hello") { (_, _) =>
    "hello"
  }

  GET("/error") { (_, _) =>
    throw new RuntimeException("Oops!")
  }

  POST("/errors/:errorId") { (_, _) =>
    throw new RuntimeException("Oops!")
  }.documentation("Throws an error") {
     _.description("Ends up throwing a runtime exception")
      .deprecate()
      .parameters(
        QueryParam("severity").asString { _.options("low", "medium", "high") },
        PathParam("errorId").asLong { _.require() },
        Header("Authorization").asString()
      )
      .request("Request Body")(
        JSON -> Body.Object("Error object").from(ErrorRequestBody("Steve", 42)),
        PLAIN_TEXT -> Body.String("Error message").example("Oopsies!").description("an error message")
      )
      .response(INTERNAL_SERVER_ERROR, "An error was thrown")(
        HTML -> Body.String("Error message").example("500 Internal Server Error")
      )
      .authentication("api_key")
  }

  PUT("/errors/:errorId/update") { (_, _) =>
    throw new RuntimeException("Oops!")
  }.documentation("Throws an error") {
     _.description("Ends up throwing a runtime exception")
      .parameters(
        PathParam("errorId").asLong {
          _.require()
            .default(-1L)
            .minimum(0L)
            .maximum(3000L)
            .isMultipleOf(2)
        }
      )
  }

  GET("/registered/all") { (_, _) =>
    all[String].mkString(", ")
  }

  GET("/registered/one") { (_, _) =>
    Try(the[String]).getOrElse("")
  }

}
