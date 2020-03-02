package com.linearframework.web.internal

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer

private[web] class OpenAPIEitherSerializer(c: Class[Either[_, _]]) extends StdSerializer[Either[_, _]](c) {

  override def serialize(t: Either[_, _], jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider): Unit = {
    t match {
      case Right(x) => serializerProvider.defaultSerializeValue(x, jsonGenerator)
      case Left(x) => serializerProvider.defaultSerializeValue(x, jsonGenerator)
    }
  }

}
