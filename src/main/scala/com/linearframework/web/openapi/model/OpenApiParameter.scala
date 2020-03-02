package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
sealed trait OpenApiParameter {
  val name: String
  val in: String
  val description: Option[String]
  val required: Option[Boolean]
  val deprecated: Option[Boolean]
  val allowEmptyValue: Option[Boolean]
  val style: Option[String]
  val explode: Option[Boolean]
  val allowReserved: Option[Boolean]
  val schema: Option[Either[OpenApiSchema, OpenApiReference]]
  val example: Option[Any]
  val examples: Option[Map[String, Either[OpenApiExample, OpenApiReference]]]
  val content: Option[Map[String, OpenApiMediaType]]
}

case class OpenApiPathParameter(
  override val name: String,
  override val description: Option[String] = None,
  override val deprecated: Option[Boolean] = None,
  override val style: Option[String] = None,
  override val schema: Option[Either[OpenApiSchema, OpenApiReference]] = None,
  override val example: Option[Any] = None
) extends OpenApiParameter {
  final override val in: String = "path"
  final override val required: Option[Boolean] = Some(true)
  final override val allowEmptyValue: Option[Boolean] = None
  final override val explode: Option[Boolean] = None
  final override val allowReserved: Option[Boolean] = None
  final override val examples: Option[Map[String, Either[OpenApiExample, OpenApiReference]]] = None
  final override val content: Option[Map[String, OpenApiMediaType]] = None
}

case class OpenApiQueryParameter(
  override val name: String,
  override val description: Option[String] = None,
  override val required: Option[Boolean] = None,
  override val deprecated: Option[Boolean] = None,
  override val allowEmptyValue: Option[Boolean] = None,
  override val style: Option[String] = None,
  override val explode: Option[Boolean] = None,
  override val allowReserved: Option[Boolean] = None,
  override val schema: Option[Either[OpenApiSchema, OpenApiReference]] = None,
  override val example: Option[Any] = None
) extends OpenApiParameter {
  final override val in: String = "query"
  final override val examples: Option[Map[String, Either[OpenApiExample, OpenApiReference]]] = None
  final override val content: Option[Map[String, OpenApiMediaType]] = None
}

case class OpenApiHeaderParameter(
  override val name: String,
  override val description: Option[String] = None,
  override val required: Option[Boolean] = None,
  override val deprecated: Option[Boolean] = None,
  override val style: Option[String] = None,
  override val explode: Option[Boolean] = None,
  override val schema: Option[Either[OpenApiSchema, OpenApiReference]] = None,
  override val example: Option[Any] = None,
  override val examples: Option[Map[String, Either[OpenApiExample, OpenApiReference]]] = None,
  override val content: Option[Map[String, OpenApiMediaType]] = None
) extends OpenApiParameter {
  final override val in: String = "header"
  final override val allowEmptyValue: Option[Boolean] = None
  final override val allowReserved: Option[Boolean] = None
}

case class OpenApiCookieParameter(
  override val name: String,
  override val description: Option[String] = None,
  override val required: Option[Boolean] = None,
  override val deprecated: Option[Boolean] = None,
  override val style: Option[String] = None,
  override val explode: Option[Boolean] = None,
  override val schema: Option[Either[OpenApiSchema, OpenApiReference]] = None,
  override val example: Option[Any] = None,
  override val examples: Option[Map[String, Either[OpenApiExample, OpenApiReference]]] = None,
  override val content: Option[Map[String, OpenApiMediaType]] = None
) extends OpenApiParameter {
  final override val in: String = "cookie"
  final override val allowEmptyValue: Option[Boolean] = None
  final override val allowReserved: Option[Boolean] = None
}