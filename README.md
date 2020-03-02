# linear-web-openapi
OpenAPI extensions for the Web module of the **Linear Framework**.

## API

### Quick Demo
```scala
package com.example
import com.linearframework.web._

object App {
  def main(args: Array[String]): Unit = {
    SwaggerServer
      .autoScan("com.example")
      .openApi(
        title = "My Example API",
        version = "v2"
      )
      .openApiAuth(
        "api_key" -> OpenApiApiKeySecurityScheme("keyName", "header")
      )
      .start()
  }
}

object HelloController extends Controller with Swagger {
  PUT("/greetings/:greetingId") { (_, _) =>
    ...
  }.documentation("Updates a greeting") {
     _.parameters(
        PathParam("greetingId").asLong { _.require() },
        Header("Authorization").asString()
      )
      .request("Request Body")(
        PLAIN_TEXT -> Body.String("Greeting").example("Hello!").description("The updated greeting")
      )
      .response(INTERNAL_SERVER_ERROR, "An error was thrown")(
        HTML -> Body.String("Error message").example("500 Internal Server Error")
      )
      .authentication("api_key")
  }
}
```


### SwaggerServer
The `SwaggerServer` has two methods that are needed for hosting OpenAPI documents:
```scala
SwaggerServer

  // (optional) enables OpenAPI (Swagger) documentation;
  //            defaults to no documentation
  .openApi(
    title = "My App", 
    version = "v1.0",
    path = "/swagger.json",
    info = _.description("My Application API")
            .contact(name = "Billy", email = "billy@email.biz")
            .license("Apache 2.0")
  )
  
  // (optional) describes the authentication schemes available to endpoints for OpenAPI documentation.
  //            supports basic, bearer, api key, OAuth2, and/or OpenID Connect.
  //            requires openApi() to have been called previously;
  //            defaults to no authentication schemes
  .openApiAuth(
    "jwt" -> OpenApiHttpSecurityScheme("bearer", httpBearerFormat = "JWT"),
    "apiKey" -> OpenApiApiKeySecurityScheme("X-API-KEY", apiKeyIn = "header")
  )
```

A `SwaggerServer` should be initialized in the same was as the default Linear `Server` object; the APIs of the two objects are the same
except for the above methods.


### Swagger
`Swagger` is a trait that, when mixed in with a Linear `Controller`, enables the generation of Swagger documentation:

```scala
import com.linearframework.web._

object MyController extends Controller with Swagger{
  
  PUT("/api/libraries/:libraryId/book") { (request, response) =>
    ...
  }.documentation("Adds a new book to the given library") {
     _.parameters(
        PathParam("libraryId").description("ID of the Library").asLong(),
        QueryParam("librarianId").description("ID of the Librarian filing the book").asLong { _.require() }
      )
      .request("Record of the Book to be filed", required = true)(
        JSON -> Body.Object("Book Record")
                    .withString("author")
                    .withString("title")
                    .withString("type", _.options("Fiction", "Non-Fiction"))
                    .withDate("publicationDate")
                    .withArray("tags", _.asStringArray())
      )
      .response(OK, "Book stored successfully")(
        PLAIN_TEXT -> Body.String("Success message").example("OK")
      )
      .response(NOT_FOUND, "Library or Librarian ID not found")(
        JSON -> Body.Object("Error message").withString("message", _.example("Library not found"))
      )
      .authentication("jwt")
  }

}
```
