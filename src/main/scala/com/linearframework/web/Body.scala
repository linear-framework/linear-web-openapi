package com.linearframework.web

trait Body {
  def Int(description: String): IntSchemaDocumentation = IntSchemaDocumentation(Option(description))
  def Long(description: String): LongSchemaDocumentation = LongSchemaDocumentation(Option(description))
  def Float(description: String): FloatSchemaDocumentation = FloatSchemaDocumentation(Option(description))
  def Double(description: String): DoubleSchemaDocumentation = DoubleSchemaDocumentation(Option(description))
  def String(description: String): StringSchemaDocumentation = StringSchemaDocumentation(Option(description))
  def Password(description: String): PasswordSchemaDocumentation = PasswordSchemaDocumentation(Option(description))
  def Base64(description: String): Base64SchemaDocumentation = Base64SchemaDocumentation(Option(description))
  def Binary(description: String): BinarySchemaDocumentation = BinarySchemaDocumentation(Option(description))
  def Date(description: String): DateSchemaDocumentation = DateSchemaDocumentation(Option(description))
  def LocalDate(description: String): LocalDateSchemaDocumentation = LocalDateSchemaDocumentation(Option(description))
  def LocalDateTime(description: String): LocalDateTimeSchemaDocumentation = LocalDateTimeSchemaDocumentation(Option(description))
  def Boolean(description: String): BooleanSchemaDocumentation = BooleanSchemaDocumentation(Option(description))
  def Array(description: String): ArraySchemaDocumentation = ArraySchemaDocumentation(Option(description))
  def Object(description: String): ObjectSchemaDocumentation = ObjectSchemaDocumentation(Option(description))
}

/**
  * Contains schema customization techniques for various Request/Response body types
  */
object Body extends Body
