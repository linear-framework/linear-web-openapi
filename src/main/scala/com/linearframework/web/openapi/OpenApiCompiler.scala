package com.linearframework.web.openapi

import com.linearframework.web._
import com.linearframework.web.openapi.model._

object OpenApiCompiler {

  def compile(info: OpenApiInfo, authentication: Map[String, OpenApiSecurityScheme], endpointDocumentation: List[EndpointDocumentation]): OpenApiDocument = {
    val paths: Map[String, OpenApiPathItem] =
      endpointDocumentation
        .groupBy(_.path)
        .map { case (path: String, docs: List[EndpointDocumentation]) =>
            path.replaceAll("[:]([^/]*)", "{$1}") ->
            OpenApiPathItem(
              get = docs.find(_.method == GET).map(toOperation),
              put = docs.find(_.method == PUT).map(toOperation),
              post = docs.find(_.method == POST).map(toOperation),
              delete = docs.find(_.method == DELETE).map(toOperation),
              options = docs.find(_.method == OPTIONS).map(toOperation),
              head = docs.find(_.method == HEAD).map(toOperation),
              trace = docs.find(_.method == TRACE).map(toOperation)
            )
        }

    val components = {
      if (authentication.isEmpty) {
        None
      }
      else {
        Some(
          OpenApiComponents(
            securitySchemes = Some(
              authentication.map { case (key, value) =>
                key -> Left(value)
              }
            )
          )
        )
      }
    }

    OpenApiDocument(
      info = info,
      paths = paths,
      components = components
    )
  }

  private def toOperation(doc: EndpointDocumentation): OpenApiOperation = {
    OpenApiOperation(
      tags = if (doc.tags.isEmpty) None else Some(doc.tags),
      summary = Option(doc.summary),
      description = doc.description,
      operationId = doc.id,
      parameters = generateParameters(doc),
      requestBody = generateRequestBody(doc.request),
      responses = generateResponses(doc.response),
      deprecated = if (doc.deprecated) Some(true) else None,
      security = if (doc.authentication.isEmpty) None else Some(List(doc.authentication))
    )
  }

  private def generateParameters(doc: EndpointDocumentation): Option[List[Either[OpenApiParameter, OpenApiReference]]] = {
    if (doc.parameters.isEmpty) {
      None
    }
    else {
      val params = doc.parameters.map {
        case path: PathParam =>
          OpenApiPathParameter(
            name = path.name,
            description = path.description,
            deprecated = if (path.deprecated) Some(true) else None,
            schema = generateSchema(path.schema),
            example = path.example
          )

        case query: QueryParam =>
          OpenApiQueryParameter(
            name = query.name,
            required = if (query.required) Some(true) else None,
            deprecated = if (query.deprecated) Some(true) else None,
            allowEmptyValue = if (query.allowEmptyValue) Some(true) else None,
            allowReserved = if (query.allowReserved) Some(true) else None,
            schema = generateSchema(query.schema),
            example = query.example
          )

        case header: Header =>
          OpenApiHeaderParameter(
            name = header.name,
            description = header.description,
            required = if (header.required) Some(true) else None,
            deprecated = if (header.deprecated) Some(true) else None,
            schema = generateSchema(header.schema),
            example = header.example
          )

        case cookie: Cookie =>
          OpenApiCookieParameter(
            name = cookie.name,
            description = cookie.description,
            required = if (cookie.required) Some(true) else None,
            deprecated = if (cookie.deprecated) Some(true) else None,
            schema = generateSchema(cookie.schema),
            example = cookie.example
          )
      }

      Some(params.map(Left(_)))
    }
  }

  private def generateRequestBody(doc: Option[RequestDocumentation]): Option[Either[OpenApiRequestBody, OpenApiReference]] = {
    doc match {
      case None =>
        None
      case Some(request) =>
        val content: Map[String, OpenApiMediaType] =
          request.contentTypes.map { case (contentType: ContentType, schema: SchemaDocumentation) =>
            contentType.toString.replaceAll(";(.*)$", "") ->
            OpenApiMediaType(
              schema = Some(Left(generateSchema(schema)))
            )
          }

        Some(
          Left(
            OpenApiRequestBody(
              description = if (request.description.isEmpty) None else Some(request.description),
              content = content,
              required = if (request.required) Some(true) else None
            )
          )
        )
    }
  }

  private def generateResponses(doc: Map[HttpStatus, ResponseDocumentation]): Map[String, Either[OpenApiResponse, OpenApiReference]] = {
    if (doc.isEmpty) {
      Map("default" -> Left(OpenApiResponse("")))
    }
    else {
      doc.map { case (status: HttpStatus, response: ResponseDocumentation) =>
        val content: Map[String, OpenApiMediaType] =
          response.contentTypes.map { case (contentType: ContentType, schema: SchemaDocumentation) =>
            contentType.toString.replaceAll(";(.*)$", "") ->
              OpenApiMediaType(
                schema = Some(Left(generateSchema(schema)))
              )
          }

        status.code.toString ->
        Left(
          OpenApiResponse(
            description = response.description,
            content = Some(content)
          )
        )
      }
    }
  }

  private def generateSchema(doc: Option[SchemaDocumentation]): Option[Either[OpenApiSchema, OpenApiReference]] = {
    doc match {
      case Some(schema) =>
        Some(Left(generateSchema(schema)))
      case None =>
        None
    }
  }

  private def generateSchema(doc: SchemaDocumentation): OpenApiSchema = {
    doc match {

      case schema: IntSchemaDocumentation =>
        IntSchema(
          title = schema.title,
          multipleOf = schema.multipleOf,
          max = schema.maximum,
          min = schema.minimum,
          options = if (schema.options.isEmpty) None else Some(schema.options.toList),
          description = schema.description,
          defaultVal = schema.default,
          exampleVal = schema.example,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: LongSchemaDocumentation =>
        LongSchema(
          title = schema.title,
          multipleOf = schema.multipleOf,
          max = schema.maximum,
          min = schema.minimum,
          options = if (schema.options.isEmpty) None else Some(schema.options.toList),
          description = schema.description,
          defaultVal = schema.default,
          exampleVal = schema.example,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: FloatSchemaDocumentation =>
        FloatSchema(
          title = schema.title,
          exclusiveMaximum = if (schema.exclusiveMaximum) Some(true) else None,
          max = schema.maximum,
          exclusiveMinimum = if (schema.exclusiveMinimum) Some(true) else None,
          min = schema.minimum,
          options = if (schema.options.isEmpty) None else Some(schema.options.toList),
          description = schema.description,
          defaultVal = schema.default,
          exampleVal = schema.example,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: DoubleSchemaDocumentation =>
        DoubleSchema(
          title = schema.title,
          exclusiveMaximum = if (schema.exclusiveMaximum) Some(true) else None,
          maximum = schema.maximum,
          exclusiveMinimum = if (schema.exclusiveMinimum) Some(true) else None,
          minimum = schema.minimum,
          options = if (schema.options.isEmpty) None else Some(schema.options.toList),
          description = schema.description,
          defaultVal = schema.default,
          exampleVal = schema.example,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: StringSchemaDocumentation =>
        StringSchema(
          title = schema.title,
          maxLength = schema.maxLength,
          minLength = schema.minLength,
          pattern = schema.pattern,
          enum = if (schema.options.isEmpty) None else Some(schema.options.toList),
          description = schema.description,
          defaultVal = schema.default,
          exampleVal = schema.example,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: PasswordSchemaDocumentation =>
        PasswordSchema(
          title = schema.title,
          maxLength = schema.maxLength,
          minLength = schema.minLength,
          pattern = schema.pattern,
          description = schema.description,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: Base64SchemaDocumentation =>
        Base64Schema(
          title = schema.title,
          maxLength = schema.maxLength,
          minLength = schema.minLength,
          description = schema.description,
          defaultVal = schema.default,
          exampleVal = schema.example,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: BinarySchemaDocumentation =>
        BinarySchema(
          title = schema.title,
          description = schema.description,
          default = schema.default,
          example = schema.example,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: DateSchemaDocumentation =>
        DateSchema(
          title = schema.title,
          description = schema.description,
          defaultVal = schema.default,
          exampleVal = schema.example,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: LocalDateSchemaDocumentation =>
        LocalDateSchema(
          title = schema.title,
          description = schema.description,
          defaultVal = schema.default,
          exampleVal = schema.example,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: LocalDateTimeSchemaDocumentation =>
        LocalDateTimeSchema(
          title = schema.title,
          description = schema.description,
          defaultVal = schema.default,
          exampleVal = schema.example,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: BooleanSchemaDocumentation =>
        BooleanSchema(
          title = schema.title,
          description = schema.description,
          defaultVal = schema.default,
          exampleVal = schema.example,
          nullable = if (schema.required) None else Some(true)
        )

      case schema: ArraySchemaDocumentation =>
        ArraySchema(
          title = schema.title,
          maxItems = schema.maxSize,
          minItems = schema.minSize,
          uniqueItems = if (schema.uniqueItems) Some(true) else None,
          items = generateSchema(schema.itemSchema),
          description = schema.description,
          default = schema.default,
          nullable = if (schema.required) None else Some(true),
          example = schema.example
        )

      case schema: ObjectSchemaDocumentation =>
        ObjectSchema(
          title = schema.title,
          required = if (schema.requiredProperties.isEmpty) None else Some(schema.requiredProperties.toList),
          properties =
            if (schema.properties.isEmpty) {
              None
            }
            else {
              Some(
                schema.properties.map { case (name: String, property: SchemaDocumentation) =>
                  name -> Left(generateSchema(property))
                }
              )
            },
          description = schema.description,
          default = schema.default,
          nullable = if (schema.required) None else Some(true),
          example = schema.example
        )
    }
  }

}
