package com.linearframework.web.openapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.linearframework.web.openapi.model.OpenApiApiKeySecurityScheme
import com.linearframework.web.{RestSpec, SwaggerServer}

class OpenApiSpec extends RestSpec {

  override protected val conf: SwaggerServer.Configuration =
    SwaggerServer.autoScan("com.linearframework.hello")
      .openApi(
        title = "Yo hey",
        version = "v2",
        path = "/swagger.json",
        info = _.description("this is my api")
          .license("Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0")
          .termsOfService("https://policies.google.com/terms")
          .contact("Bill Gates", "bill@microsoft.com")
      )
      .openApiAuth(
        "api_key" -> OpenApiApiKeySecurityScheme("keyName", "header")
      )

  "Swagger Servers" should "support standard server calls" in {
    val result = get("http://localhost:4567/hello")
    result.body should be ("hello")
  }

  "OpenAPI JSON" should "be available at the specified path" in {
    val expected = """
      |{
      |  "info" : {
      |    "title" : "Yo hey",
      |    "description" : "this is my api",
      |    "termsOfService" : "https://policies.google.com/terms",
      |    "contact" : {
      |      "name" : "Bill Gates",
      |      "email" : "bill@microsoft.com"
      |    },
      |    "license" : {
      |      "name" : "Apache 2.0",
      |      "url" : "https://www.apache.org/licenses/LICENSE-2.0"
      |    },
      |    "version" : "v2"
      |  },
      |  "servers" : [ {
      |    "url" : "http://localhost:4567"
      |  } ],
      |  "paths" : {
      |    "/errors/{errorId}/update" : {
      |      "put" : {
      |        "summary" : "Throws an error",
      |        "description" : "Ends up throwing a runtime exception",
      |        "parameters" : [ {
      |          "name" : "errorId",
      |          "schema" : {
      |            "title" : "errorId",
      |            "multipleOf" : 2,
      |            "type" : "integer",
      |            "format" : "int64",
      |            "maximum" : 3000.0,
      |            "minimum" : 0.0,
      |            "default" : -1
      |          },
      |          "in" : "path",
      |          "required" : true
      |        } ],
      |        "responses" : {
      |          "default" : {
      |            "description" : ""
      |          }
      |        }
      |      }
      |    },
      |    "/errors/{errorId}" : {
      |      "post" : {
      |        "summary" : "Throws an error",
      |        "description" : "Ends up throwing a runtime exception",
      |        "parameters" : [ {
      |          "name" : "severity",
      |          "schema" : {
      |            "title" : "severity",
      |            "enum" : [ "low", "medium", "high" ],
      |            "nullable" : true,
      |            "type" : "string"
      |          },
      |          "in" : "query"
      |        }, {
      |          "name" : "errorId",
      |          "schema" : {
      |            "title" : "errorId",
      |            "type" : "integer",
      |            "format" : "int64"
      |          },
      |          "in" : "path",
      |          "required" : true
      |        }, {
      |          "name" : "Authorization",
      |          "schema" : {
      |            "title" : "Authorization",
      |            "nullable" : true,
      |            "type" : "string"
      |          },
      |          "in" : "header"
      |        } ],
      |        "requestBody" : {
      |          "description" : "Request Body",
      |          "content" : {
      |            "application/json" : {
      |              "schema" : {
      |                "title" : "Error object",
      |                "properties" : {
      |                  "name" : {
      |                    "type" : "string"
      |                  },
      |                  "age" : {
      |                    "type" : "integer",
      |                    "format" : "int32"
      |                  }
      |                },
      |                "nullable" : true,
      |                "example" : {
      |                  "name" : "Steve",
      |                  "age" : 42
      |                },
      |                "type" : "object"
      |              }
      |            },
      |            "text/plain" : {
      |              "schema" : {
      |                "title" : "Error message",
      |                "description" : "an error message",
      |                "nullable" : true,
      |                "type" : "string",
      |                "example" : "Oopsies!"
      |              }
      |            }
      |          }
      |        },
      |        "responses" : {
      |          "500" : {
      |            "description" : "An error was thrown",
      |            "content" : {
      |              "text/html" : {
      |                "schema" : {
      |                  "title" : "Error message",
      |                  "nullable" : true,
      |                  "type" : "string",
      |                  "example" : "500 Internal Server Error"
      |                }
      |              }
      |            }
      |          }
      |        },
      |        "deprecated" : true,
      |        "security" : [ {
      |          "api_key" : [ ]
      |        } ]
      |      }
      |    }
      |  },
      |  "components" : {
      |    "securitySchemes" : {
      |      "api_key" : {
      |        "type" : "apiKey",
      |        "name" : "keyName",
      |        "in" : "header"
      |      }
      |    }
      |  },
      |  "openapi" : "3.0.2"
      |}
    """.stripMargin.trim

    val objectMapper = new ObjectMapper()

    val expectedObj = objectMapper.readValue(expected, classOf[Object])
    val expectedJson = objectMapper.writeValueAsString(expectedObj)

    val actual = get("http://localhost:4567/swagger.json").body
    val actualObj = objectMapper.readValue(actual, classOf[Object])
    val actualJson = objectMapper.writeValueAsString(actualObj)

    actualJson should be (expectedJson)
  }

}
