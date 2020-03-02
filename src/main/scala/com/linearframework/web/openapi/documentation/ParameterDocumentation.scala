package com.linearframework.web.openapi.documentation

sealed trait ParameterDocumentation {
  private[web] val name: String
  private[web] val required: Boolean
  private[web] val description: Option[String] = None
  private[web] val deprecated: Boolean = false
  private[web] val schema: Option[SchemaDocumentation] = None
}

case class PathParam (
  private[web] override val name: String,
  private[web] override val description: Option[String] = None,
  private[web] override val deprecated: Boolean = false,
  private[web] override val schema: Option[SchemaDocumentation] = None,
  private[web] val example: Option[String] = None
) extends ParameterDocumentation {
  final override private[web] val required = true

  /**
    * Sets the long-form description of this path parameter
    */
  def description(description: String): PathParam = {
    this.copy(description = Option(description))
  }

  /**
    * Marks this path parameter as deprecated
    */
  def deprecate(): PathParam = {
    this.copy(deprecated = true)
  }

  /**
    * Sets the type of this path parameter to an integer
    * and allows for customizing the schema
    */
  def asInteger(customize: IntSchemaDocumentation => IntSchemaDocumentation = identity): PathParam = {
    this.copy(schema = Option(customize(IntSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this path parameter to a Long
    * and allows for customizing the schema
    */
  def asLong(customize: LongSchemaDocumentation => LongSchemaDocumentation = identity): PathParam = {
    this.copy(schema = Option(customize(LongSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this path parameter to a Float
    * and allows for customizing the schema
    */
  def asFloat(customize: FloatSchemaDocumentation => FloatSchemaDocumentation = identity): PathParam = {
    this.copy(schema = Option(customize(FloatSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this path parameter to a Float
    * and allows for customizing the schema
    */
  def asDouble(customize: DoubleSchemaDocumentation => DoubleSchemaDocumentation = identity): PathParam = {
    this.copy(schema = Option(customize(DoubleSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this path parameter to a String
    * and allows for customizing the schema
    */
  def asString(customize: StringSchemaDocumentation => StringSchemaDocumentation = identity): PathParam = {
    this.copy(schema = Option(customize(StringSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this path parameter to a Base64-encoded String
    * and allows for customizing the schema
    */
  def asBase64(customize: Base64SchemaDocumentation => Base64SchemaDocumentation = identity): PathParam = {
    this.copy(schema = Option(customize(Base64SchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this path parameter to a Boolean value
    * and allows for customizing the schema
    */
  def asBoolean(customize: BooleanSchemaDocumentation => BooleanSchemaDocumentation = identity): PathParam = {
    this.copy(schema = Option(customize(BooleanSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this path parameter to a Boolean value
    * and allows for customizing the schema
    */
  def asObject(customize: BooleanSchemaDocumentation => BooleanSchemaDocumentation = identity): PathParam = {
    this.copy(schema = Option(customize(BooleanSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets an example value for this path parameter
    */
  def example(example: String): PathParam = {
    this.copy(example = Option(example))
  }

}

case class QueryParam (
  private[web] override val name: String,
  private[web] override val required: Boolean = false,
  private[web] override val description: Option[String] = None,
  private[web] override val deprecated: Boolean = false,
  private[web] val allowEmptyValue: Boolean = false,
  private[web] val allowReserved: Boolean = false,
  private[web] override val schema: Option[SchemaDocumentation] = None,
  private[web] val example: Option[String] = None
) extends ParameterDocumentation {

  /**
    * Marks this query parameter as required
    */
  def require(): QueryParam = {
    this.copy(required = true)
  }

  /**
    * Sets the long-form description of this query parameter
    */
  def description(description: String): QueryParam = {
    this.copy(description = Option(description))
  }

  /**
    * Marks this query parameter as deprecated
    */
  def deprecate(): QueryParam = {
    this.copy(deprecated = true)
  }

  /**
    * Marks this query parameter as allowing null/empty values
    */
  def allowEmptyValues(): QueryParam = {
    this.copy(allowEmptyValue = true)
  }

  /**
    * Marks this query parameter as allowing characters that are typically reserved
    * due to URL encoding
    */
  def allowReservedCharacters(): QueryParam = {
    this.copy(allowReserved = true)
  }

  /**
    * Sets the type of this query parameter to an integer
    * and allows for customizing the schema
    */
  def asInteger(customize: IntSchemaDocumentation => IntSchemaDocumentation = identity): QueryParam = {
    this.copy(schema = Option(customize(IntSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this query parameter to a Long
    * and allows for customizing the schema
    */
  def asLong(customize: LongSchemaDocumentation => LongSchemaDocumentation = identity): QueryParam = {
    this.copy(schema = Option(customize(LongSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this query parameter to a Float
    * and allows for customizing the schema
    */
  def asFloat(customize: FloatSchemaDocumentation => FloatSchemaDocumentation = identity): QueryParam = {
    this.copy(schema = Option(customize(FloatSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this query parameter to a Float
    * and allows for customizing the schema
    */
  def asDouble(customize: DoubleSchemaDocumentation => DoubleSchemaDocumentation = identity): QueryParam = {
    this.copy(schema = Option(customize(DoubleSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this query parameter to a String
    * and allows for customizing the schema
    */
  def asString(customize: StringSchemaDocumentation => StringSchemaDocumentation = identity): QueryParam = {
    this.copy(schema = Option(customize(StringSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this query parameter to a Base64-encoded String
    * and allows for customizing the schema
    */
  def asBase64(customize: Base64SchemaDocumentation => Base64SchemaDocumentation = identity): QueryParam = {
    this.copy(schema = Option(customize(Base64SchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this query parameter to a Boolean value
    * and allows for customizing the schema
    */
  def asBoolean(customize: BooleanSchemaDocumentation => BooleanSchemaDocumentation = identity): QueryParam = {
    this.copy(schema = Option(customize(BooleanSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets an example value for this query parameter
    */
  def example(example: String): QueryParam = {
    this.copy(example = Option(example))
  }

}

case class Header (
  private[web] override val name: String,
  private[web] override val required: Boolean = false,
  private[web] override val description: Option[String] = None,
  private[web] override val deprecated: Boolean = false,
  private[web] override val schema: Option[SchemaDocumentation] = None,
  private[web] val example: Option[Any] = None
) extends ParameterDocumentation {

  /**
    * Marks this header as required
    */
  def require(): Header = {
    this.copy(required = true)
  }

  /**
    * Sets the long-form description of this header
    */
  def description(description: String): Header = {
    this.copy(description = Option(description))
  }

  /**
    * Marks this header as deprecated
    */
  def deprecate(): Header = {
    this.copy(deprecated = true)
  }

  /**
    * Sets the type of this header to an integer
    * and allows for customizing the schema
    */
  def asInteger(customize: IntSchemaDocumentation => IntSchemaDocumentation = identity): Header = {
    this.copy(schema = Option(customize(IntSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this header to a Long
    * and allows for customizing the schema
    */
  def asLong(customize: LongSchemaDocumentation => LongSchemaDocumentation = identity): Header = {
    this.copy(schema = Option(customize(LongSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this header to a Float
    * and allows for customizing the schema
    */
  def asFloat(customize: FloatSchemaDocumentation => FloatSchemaDocumentation = identity): Header = {
    this.copy(schema = Option(customize(FloatSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this header to a Float
    * and allows for customizing the schema
    */
  def asDouble(customize: DoubleSchemaDocumentation => DoubleSchemaDocumentation = identity): Header = {
    this.copy(schema = Option(customize(DoubleSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this header to a String
    * and allows for customizing the schema
    */
  def asString(customize: StringSchemaDocumentation => StringSchemaDocumentation = identity): Header = {
    this.copy(schema = Option(customize(StringSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this header to a Base64-encoded String
    * and allows for customizing the schema
    */
  def asBase64(customize: Base64SchemaDocumentation => Base64SchemaDocumentation = identity): Header = {
    this.copy(schema = Option(customize(Base64SchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this header to a Boolean value
    * and allows for customizing the schema
    */
  def asBoolean(customize: BooleanSchemaDocumentation => BooleanSchemaDocumentation = identity): Header = {
    this.copy(schema = Option(customize(BooleanSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this header to a Binary value
    * and allows for customizing the schema
    */
  def asBinary(customize: BinarySchemaDocumentation => BinarySchemaDocumentation = identity): Header = {
    this.copy(schema = Option(customize(BinarySchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this header to an Array value
    * and allows for customizing the schema
    */
  def asArray(customize: ArraySchemaDocumentation => ArraySchemaDocumentation = identity): Header = {
    this.copy(schema = Option(customize(ArraySchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this header to an Object value
    * and allows for customizing the schema
    */
  def asObject(customize: ObjectSchemaDocumentation => ObjectSchemaDocumentation = identity): Header = {
    this.copy(schema = Option(customize(ObjectSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets an example value for this header
    */
  def example(example: Any): Header = {
    this.copy(example = Option(example))
  }

}

case class Cookie (
  private[web] override val name: String,
  private[web] override val required: Boolean = false,
  private[web] override val description: Option[String] = None,
  private[web] override val deprecated: Boolean = false,
  private[web] override val schema: Option[SchemaDocumentation] = None,
  private[web] val example: Option[Any] = None
) extends ParameterDocumentation {

  /**
    * Marks this cookie as required
    */
  def require(): Cookie = {
    this.copy(required = true)
  }

  /**
    * Sets the long-form description of this cookie
    */
  def description(description: String): Cookie = {
    this.copy(description = Option(description))
  }

  /**
    * Marks this cookie as deprecated
    */
  def deprecate(): Cookie = {
    this.copy(deprecated = true)
  }

  /**
    * Sets the type of this cookie to an integer
    * and allows for customizing the schema
    */
  def asInteger(customize: IntSchemaDocumentation => IntSchemaDocumentation = identity): Cookie = {
    this.copy(schema = Option(customize(IntSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this cookie to a Long
    * and allows for customizing the schema
    */
  def asLong(customize: LongSchemaDocumentation => LongSchemaDocumentation = identity): Cookie = {
    this.copy(schema = Option(customize(LongSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this cookie to a Float
    * and allows for customizing the schema
    */
  def asFloat(customize: FloatSchemaDocumentation => FloatSchemaDocumentation = identity): Cookie = {
    this.copy(schema = Option(customize(FloatSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this cookie to a Float
    * and allows for customizing the schema
    */
  def asDouble(customize: DoubleSchemaDocumentation => DoubleSchemaDocumentation = identity): Cookie = {
    this.copy(schema = Option(customize(DoubleSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this cookie to a String
    * and allows for customizing the schema
    */
  def asString(customize: StringSchemaDocumentation => StringSchemaDocumentation = identity): Cookie = {
    this.copy(schema = Option(customize(StringSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this cookie to a Base64-encoded String
    * and allows for customizing the schema
    */
  def asBase64(customize: Base64SchemaDocumentation => Base64SchemaDocumentation = identity): Cookie = {
    this.copy(schema = Option(customize(Base64SchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this cookie to a Boolean value
    * and allows for customizing the schema
    */
  def asBoolean(customize: BooleanSchemaDocumentation => BooleanSchemaDocumentation = identity): Cookie = {
    this.copy(schema = Option(customize(BooleanSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this cookie to a Binary value
    * and allows for customizing the schema
    */
  def asBinary(customize: BinarySchemaDocumentation => BinarySchemaDocumentation = identity): Cookie = {
    this.copy(schema = Option(customize(BinarySchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this cookie to an Array value
    * and allows for customizing the schema
    */
  def asArray(customize: ArraySchemaDocumentation => ArraySchemaDocumentation = identity): Cookie = {
    this.copy(schema = Option(customize(ArraySchemaDocumentation(Option(name)))))
  }

  /**
    * Sets the type of this cookie to an Object value
    * and allows for customizing the schema
    */
  def asObject(customize: ObjectSchemaDocumentation => ObjectSchemaDocumentation = identity): Cookie = {
    this.copy(schema = Option(customize(ObjectSchemaDocumentation(Option(name)))))
  }

  /**
    * Sets an example value for this cookie
    */
  def example(example: Any): Cookie = {
    this.copy(example = Option(example))
  }
}