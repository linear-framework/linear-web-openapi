package com.linearframework.web.openapi.model

import com.google.gson._
import com.linearframework.json._
import java.lang.reflect.{ParameterizedType, Type}
import java.time.{LocalDate, LocalDateTime}
import java.util.Date

/**
  * Custom JSON serialization techniques for OpenAPI documents
  */
trait OpenApiJson extends Json {

  override protected def customJson(gsonBuilder: GsonBuilder): GsonBuilder = {
    gsonBuilder
      .setPrettyPrinting()
      .registerTypeAdapter(classOf[Date], DateAdapter)
      .registerTypeAdapter(classOf[LocalDate], LocalDateAdapter)
      .registerTypeAdapter(classOf[LocalDateTime], LocalDateTimeAdapter)
      .registerTypeAdapter(classOf[List[_]], SeqJsonAdapter)
      .registerTypeAdapter(classOf[Map[_, _]], MapJsonAdapter)
      .registerTypeAdapter(classOf[Either[_, _]], OpenApiEitherSerializer)
      .registerTypeAdapter(classOf[Option[_]], OpenApiOptionSerializer)
  }

  private object OpenApiEitherSerializer extends JsonSerializer[Either[_, _]] {
    override def serialize(obj: Either[_, _], t: Type, jsc: JsonSerializationContext): JsonElement = {
      val contents = obj match {
        case Left(left) =>
          left
        case Right(right) =>
          right
      }
      jsc.serialize(contents)
    }
  }

  private object OpenApiOptionSerializer extends JsonSerializer[Option[_]] {
    override def serialize(obj: Option[_], t: Type, jsc: JsonSerializationContext): JsonElement = {
      jsc.serialize(obj.orNull, t.asInstanceOf[ParameterizedType].getActualTypeArguments()(0))
    }
  }

}
