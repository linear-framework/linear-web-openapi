package com.linearframework.web.openapi.model

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime}
import java.util.Date

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
sealed trait OpenApiSchema {
  val `type`: String
  val title: Option[String]
  val multipleOf: Option[Int]
  val maximum: Option[Double]
  val exclusiveMaximum: Option[Boolean]
  val minimum: Option[Double]
  val exclusiveMinimum: Option[Boolean]
  val maxLength: Option[Int]
  val minLength: Option[Int]
  val pattern: Option[String]
  val maxItems: Option[Int]
  val minItems: Option[Int]
  val uniqueItems: Option[Boolean]
  val maxProperties: Option[Int]
  val minProperties: Option[Int]
  val required: Option[List[String]]
  val enum: Option[List[String]]
  val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]]
  val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]]
  val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]]
  val not: Option[List[Either[OpenApiSchema, OpenApiReference]]]
  val items: Option[Either[OpenApiSchema, OpenApiReference]]
  val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]]
  val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]]
  val description: Option[String]
  val format: Option[String]
  val default: Option[Any]
  val nullable: Option[Boolean]
  val discriminator: Option[OpenApiDiscriminator]
  val readOnly: Option[Boolean]
  val writeOnly: Option[Boolean]
  val xml: Option[OpenApiXml]
  val externalDocs: Option[OpenApiExternalDocumentation]
  val example: Option[Any]
  val deprecated: Option[Boolean]
}

case class IntSchema(
  override val title: Option[String] = None,
  override val multipleOf: Option[Int] = None,
  override val exclusiveMaximum: Option[Boolean] = None,
  @transient max: Option[Int] = None,
  override val exclusiveMinimum: Option[Boolean] = None,
  @transient min: Option[Int] = None,
  @transient options: Option[List[Int]] = None,
  override val description: Option[String] = None,
  @transient defaultVal: Option[Int] = None,
  @transient exampleVal: Option[Int] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "integer"
  final override val format: Option[String] = Some("int32")
  final override val maximum: Option[Double] = max.map(_.toDouble)
  final override val minimum: Option[Double] = min.map(_.toDouble)
  final override val maxLength: Option[Int] = None
  final override val minLength: Option[Int] = None
  final override val pattern: Option[String] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val enum: Option[List[String]] = options.map(_.map(_.toString))
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val default: Option[Any] = defaultVal
  final override val xml: Option[OpenApiXml] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
  final override val example: Option[Any] = exampleVal
}

case class LongSchema(
  override val title: Option[String] = None,
  override val multipleOf: Option[Int] = None,
  override val exclusiveMaximum: Option[Boolean] = None,
  @transient max: Option[Long] = None,
  override val exclusiveMinimum: Option[Boolean] = None,
  @transient min: Option[Long] = None,
  @transient options: Option[List[Long]] = None,
  override val description: Option[String] = None,
  @transient defaultVal: Option[Long] = None,
  @transient exampleVal: Option[Long] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "integer"
  final override val format: Option[String] = Some("int64")
  final override val maximum: Option[Double] = max.map(_.toDouble)
  final override val minimum: Option[Double] = min.map(_.toDouble)
  final override val maxLength: Option[Int] = None
  final override val minLength: Option[Int] = None
  final override val pattern: Option[String] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val enum: Option[List[String]] = options.map(_.map(_.toString))
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val default: Option[Any] = defaultVal
  final override val xml: Option[OpenApiXml] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
  final override val example: Option[Any] = exampleVal
}

case class FloatSchema(
  override val title: Option[String] = None,
  override val multipleOf: Option[Int] = None,
  override val exclusiveMaximum: Option[Boolean] = None,
  @transient max: Option[Float] = None,
  override val exclusiveMinimum: Option[Boolean] = None,
  @transient min: Option[Float] = None,
  @transient options: Option[List[Float]] = None,
  override val description: Option[String] = None,
  @transient defaultVal: Option[Float] = None,
  @transient exampleVal: Option[Float] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "number"
  final override val format: Option[String] = Some("float")
  final override val maximum: Option[Double] = max.map(_.toDouble)
  final override val minimum: Option[Double] = min.map(_.toDouble)
  final override val maxLength: Option[Int] = None
  final override val minLength: Option[Int] = None
  final override val pattern: Option[String] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val enum: Option[List[String]] = options.map(_.map(_.toString))
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val default: Option[Any] = defaultVal
  final override val xml: Option[OpenApiXml] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
  final override val example: Option[Any] = exampleVal
}

case class DoubleSchema(
  override val title: Option[String] = None,
  override val multipleOf: Option[Int] = None,
  override val maximum: Option[Double] = None,
  override val exclusiveMaximum: Option[Boolean] = None,
  override val minimum: Option[Double] = None,
  override val exclusiveMinimum: Option[Boolean] = None,
  @transient options: Option[List[Double]] = None,
  override val description: Option[String] = None,
  @transient defaultVal: Option[Double] = None,
  @transient exampleVal: Option[Double] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "number"
  final override val format: Option[String] = Some("float")
  final override val maxLength: Option[Int] = None
  final override val minLength: Option[Int] = None
  final override val pattern: Option[String] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val enum: Option[List[String]] = options.map(_.map(_.toString))
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val default: Option[Any] = defaultVal
  final override val xml: Option[OpenApiXml] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
  final override val example: Option[Any] = exampleVal
}

case class StringSchema(
  override val title: Option[String] = None,
  override val maxLength: Option[Int] = None,
  override val minLength: Option[Int] = None,
  override val pattern: Option[String] = None,
  override val enum: Option[List[String]] = None,
  override val description: Option[String] = None,
  @transient defaultVal: Option[String] = None,
  @transient exampleVal: Option[String] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "string"
  final override val format: Option[String] = None
  final override val multipleOf: Option[Int] = None
  final override val maximum: Option[Double] = None
  final override val exclusiveMaximum: Option[Boolean] = None
  final override val minimum: Option[Double] = None
  final override val exclusiveMinimum: Option[Boolean] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val default: Option[Any] = defaultVal
  final override val xml: Option[OpenApiXml] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
  final override val example: Option[Any] = exampleVal
}

case class PasswordSchema(
  override val title: Option[String] = None,
  override val maxLength: Option[Int] = None,
  override val minLength: Option[Int] = None,
  override val pattern: Option[String] = None,
  override val description: Option[String] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "string"
  final override val format: Option[String] = Some("password")
  final override val multipleOf: Option[Int] = None
  final override val maximum: Option[Double] = None
  final override val exclusiveMaximum: Option[Boolean] = None
  final override val minimum: Option[Double] = None
  final override val exclusiveMinimum: Option[Boolean] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val enum: Option[List[String]] = None
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val default: Option[Any] = None
  final override val xml: Option[OpenApiXml] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
  final override val example: Option[Any] = None
}

case class Base64Schema(
  override val title: Option[String] = None,
  override val maxLength: Option[Int] = None,
  override val minLength: Option[Int] = None,
  override val enum: Option[List[String]] = None,
  override val description: Option[String] = None,
  @transient defaultVal: Option[String] = None,
  @transient exampleVal: Option[String] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "string"
  final override val format: Option[String] = Some("byte")
  final override val multipleOf: Option[Int] = None
  final override val maximum: Option[Double] = None
  final override val exclusiveMaximum: Option[Boolean] = None
  final override val minimum: Option[Double] = None
  final override val exclusiveMinimum: Option[Boolean] = None
  final override val pattern: Option[String] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val default: Option[Any] = defaultVal
  final override val xml: Option[OpenApiXml] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
  final override val example: Option[Any] = exampleVal
}

case class BinarySchema(
  override val title: Option[String] = None,
  override val maxLength: Option[Int] = None,
  override val minLength: Option[Int] = None,
  override val description: Option[String] = None,
  override val default: Option[Any] = None,
  override val example: Option[Any] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "string"
  final override val format: Option[String] = Some("binary")
  final override val multipleOf: Option[Int] = None
  final override val maximum: Option[Double] = None
  final override val exclusiveMaximum: Option[Boolean] = None
  final override val minimum: Option[Double] = None
  final override val exclusiveMinimum: Option[Boolean] = None
  final override val pattern: Option[String] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val enum: Option[List[String]] = None
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val xml: Option[OpenApiXml] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
}

case class DateSchema(
  override val title: Option[String] = None,
  override val description: Option[String] = None,
  @transient defaultVal: Option[Date] = None,
  @transient exampleVal: Option[Date] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "string"
  final override val format: Option[String] = Some("date")
  final override val multipleOf: Option[Int] = None
  final override val maximum: Option[Double] = None
  final override val exclusiveMaximum: Option[Boolean] = None
  final override val minimum: Option[Double] = None
  final override val exclusiveMinimum: Option[Boolean] = None
  final override val maxLength: Option[Int] = None
  final override val minLength: Option[Int] = None
  final override val pattern: Option[String] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val enum: Option[List[String]] = None
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val default: Option[Any] = defaultVal.map(it => new SimpleDateFormat("yyyy-MM-dd").format(it))
  final override val xml: Option[OpenApiXml] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
  final override val example: Option[Any] = exampleVal.map(it => new SimpleDateFormat("yyyy-MM-dd").format(it))
}

case class LocalDateSchema(
  override val title: Option[String] = None,
  override val description: Option[String] = None,
  @transient defaultVal: Option[LocalDate] = None,
  @transient exampleVal: Option[LocalDate] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "string"
  final override val format: Option[String] = Some("date")
  final override val multipleOf: Option[Int] = None
  final override val maximum: Option[Double] = None
  final override val exclusiveMaximum: Option[Boolean] = None
  final override val minimum: Option[Double] = None
  final override val exclusiveMinimum: Option[Boolean] = None
  final override val maxLength: Option[Int] = None
  final override val minLength: Option[Int] = None
  final override val pattern: Option[String] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val enum: Option[List[String]] = None
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val default: Option[Any] = defaultVal.map(it => it.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
  final override val xml: Option[OpenApiXml] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
  final override val example: Option[Any] = exampleVal.map(it => it.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
}

case class LocalDateTimeSchema(
  override val title: Option[String] = None,
  override val description: Option[String] = None,
  @transient defaultVal: Option[LocalDateTime] = None,
  @transient exampleVal: Option[LocalDateTime] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "string"
  final override val format: Option[String] = Some("date")
  final override val multipleOf: Option[Int] = None
  final override val maximum: Option[Double] = None
  final override val exclusiveMaximum: Option[Boolean] = None
  final override val minimum: Option[Double] = None
  final override val exclusiveMinimum: Option[Boolean] = None
  final override val maxLength: Option[Int] = None
  final override val minLength: Option[Int] = None
  final override val pattern: Option[String] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val enum: Option[List[String]] = None
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val default: Option[Any] = defaultVal.map(it => it.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
  final override val xml: Option[OpenApiXml] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
  final override val example: Option[Any] = exampleVal.map(it => it.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
}

case class BooleanSchema(
  override val title: Option[String] = None,
  override val description: Option[String] = None,
  @transient defaultVal: Option[Boolean] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  @transient exampleVal: Option[Boolean] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "boolean"
  final override val format: Option[String] = None
  final override val multipleOf: Option[Int] = None
  final override val maximum: Option[Double] = None
  final override val exclusiveMaximum: Option[Boolean] = None
  final override val minimum: Option[Double] = None
  final override val exclusiveMinimum: Option[Boolean] = None
  final override val maxLength: Option[Int] = None
  final override val minLength: Option[Int] = None
  final override val pattern: Option[String] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val enum: Option[List[String]] = None
  final override val required: Option[List[String]] = None
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val default: Option[Any] = defaultVal
  final override val discriminator: Option[OpenApiDiscriminator] = None
  final override val xml: Option[OpenApiXml] = None
  final override val externalDocs: Option[OpenApiExternalDocumentation] = None
  final override val example: Option[Any] = exampleVal
}

case class ArraySchema(
  override val title: Option[String] = None,
  override val maxItems: Option[Int] = None,
  override val minItems: Option[Int] = None,
  override val uniqueItems: Option[Boolean] = None,
  override val items: Option[Either[OpenApiSchema, OpenApiReference]],
  override val description: Option[String] = None,
  override val default: Option[Any] = None,
  override val nullable: Option[Boolean] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val xml: Option[OpenApiXml] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val example: Option[Any] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "array"
  final override val format: Option[String] = None
  final override val multipleOf: Option[Int] = None
  final override val maximum: Option[Double] = None
  final override val exclusiveMaximum: Option[Boolean] = None
  final override val minimum: Option[Double] = None
  final override val exclusiveMinimum: Option[Boolean] = None
  final override val maxLength: Option[Int] = None
  final override val minLength: Option[Int] = None
  final override val pattern: Option[String] = None
  final override val maxProperties: Option[Int] = None
  final override val minProperties: Option[Int] = None
  final override val required: Option[List[String]] = None
  final override val enum: Option[List[String]] = None
  final override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None
  final override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None
  final override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None
  final override val discriminator: Option[OpenApiDiscriminator] = None
}

case class ObjectSchema(
  override val title: Option[String] = None,
  override val maxProperties: Option[Int] = None,
  override val minProperties: Option[Int] = None,
  override val required: Option[List[String]] = None,
  override val allOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None,
  override val oneOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None,
  override val anyOf: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None,
  override val not: Option[List[Either[OpenApiSchema, OpenApiReference]]] = None,
  override val properties: Option[Map[String, Either[OpenApiSchema, OpenApiReference]]] = None,
  override val additionalProperties: Option[Either[OpenApiSchema, OpenApiReference]] = None,
  override val description: Option[String] = None,
  override val default: Option[Any] = None,
  override val nullable: Option[Boolean] = None,
  override val discriminator: Option[OpenApiDiscriminator] = None,
  override val readOnly: Option[Boolean] = None,
  override val writeOnly: Option[Boolean] = None,
  override val xml: Option[OpenApiXml] = None,
  override val externalDocs: Option[OpenApiExternalDocumentation] = None,
  override val example: Option[Any] = None,
  override val deprecated: Option[Boolean] = None
) extends OpenApiSchema {
  final override val `type`: String = "object"
  final override val format: Option[String] = None
  final override val multipleOf: Option[Int] = None
  final override val maximum: Option[Double] = None
  final override val exclusiveMaximum: Option[Boolean] = None
  final override val minimum: Option[Double] = None
  final override val exclusiveMinimum: Option[Boolean] = None
  final override val maxLength: Option[Int] = None
  final override val minLength: Option[Int] = None
  final override val pattern: Option[String] = None
  final override val maxItems: Option[Int] = None
  final override val minItems: Option[Int] = None
  final override val uniqueItems: Option[Boolean] = None
  final override val enum: Option[List[String]] = None
  final override val items: Option[Either[OpenApiSchema, OpenApiReference]] = None
}