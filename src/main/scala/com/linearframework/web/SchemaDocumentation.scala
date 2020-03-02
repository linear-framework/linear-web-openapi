package com.linearframework.web

import java.time.{LocalDate, LocalDateTime}
import java.util.Date
import scala.jdk.CollectionConverters._
import scala.reflect.ClassTag

sealed trait SchemaDocumentation {
  private[web] val title: Option[String]
  private[web] val description: Option[String]
}

case class IntSchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val multipleOf: Option[Int] = None,
  private[web] val minimum: Option[Int] = None,
  private[web] val maximum: Option[Int] = None,
  private[web] val options: Set[Int] = Set(),
  private[web] val default: Option[Int] = None,
  private[web] val example: Option[Int] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): IntSchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Marks this item as being a multiple of the given number
    */
  def isMultipleOf(value: Int): IntSchemaDocumentation = {
    this.copy(multipleOf = Option(value))
  }

  /**
    * Sets the minimum value of this item
    */
  def minimum(value: Int): IntSchemaDocumentation = {
    this.copy(minimum = Option(value))
  }

  /**
    * Sets the maximum value of this item
    */
  def maximum(value: Int): IntSchemaDocumentation = {
    this.copy(maximum = Option(value))
  }

  /**
    * Marks this item as having a defined set of possible values which it may contain
    */
  def options(values: Int*): IntSchemaDocumentation = {
    this.copy(options = values.toSet)
  }

  /**
    * Sets the default value of this item
    */
  def default(value: Int): IntSchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: Int): IntSchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): IntSchemaDocumentation = {
    this.copy(required = true)
  }
}

case class LongSchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val multipleOf: Option[Int] = None,
  private[web] val minimum: Option[Long] = None,
  private[web] val maximum: Option[Long] = None,
  private[web] val options: Set[Long] = Set(),
  private[web] val default: Option[Long] = None,
  private[web] val example: Option[Long] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): LongSchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Marks this item as being a multiple of the given number
    */
  def isMultipleOf(value: Int): LongSchemaDocumentation = {
    this.copy(multipleOf = Option(value))
  }

  /**
    * Sets the minimum value of this item
    */
  def minimum(value: Long): LongSchemaDocumentation = {
    this.copy(minimum = Option(value))
  }

  /**
    * Sets the maximum value of this item
    */
  def maximum(value: Long): LongSchemaDocumentation = {
    this.copy(maximum = Option(value))
  }

  /**
    * Marks this item as having a defined set of possible values which it may contain
    */
  def options(values: Long*): LongSchemaDocumentation = {
    this.copy(options = values.toSet)
  }

  /**
    * Sets the default value of this item
    */
  def default(value: Long): LongSchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: Long): LongSchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): LongSchemaDocumentation = {
    this.copy(required = true)
  }
}

case class FloatSchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val minimum: Option[Float] = None,
  private[web] val exclusiveMinimum: Boolean = false,
  private[web] val maximum: Option[Float] = None,
  private[web] val exclusiveMaximum: Boolean = false,
  private[web] val options: Set[Float] = Set(),
  private[web] val default: Option[Float] = None,
  private[web] val example: Option[Float] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): FloatSchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the non-exclusive minimum value of this item
    * (i.e., the value may be the minimum)
    */
  def minimum(value: Float): FloatSchemaDocumentation = {
    this.copy(minimum = Option(value), exclusiveMinimum = false)
  }

  /**
    * Sets the exclusive minimum value of this item
    * (i.e., the value may NOT be the minimum)
    */
  def minimumExcluding(value: Float): FloatSchemaDocumentation = {
    this.copy(minimum = Option(value), exclusiveMinimum = true)
  }

  /**
    * Sets the non-exclusive maximum value of this item
    * (i.e., the value may be the maximum)
    */
  def maximum(value: Float): FloatSchemaDocumentation = {
    this.copy(maximum = Option(value), exclusiveMaximum = false)
  }

  /**
    * Sets the maximum value of this item
    * (i.e., the value may NOT be the maximum)
    */
  def maximumExcluding(value: Float): FloatSchemaDocumentation = {
    this.copy(maximum = Option(value), exclusiveMaximum = true)
  }

  /**
    * Marks this item as having a defined set of possible values which it may contain
    */
  def options(values: Float*): FloatSchemaDocumentation = {
    this.copy(options = values.toSet)
  }

  /**
    * Sets the default value of this item
    */
  def default(value: Float): FloatSchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: Float): FloatSchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): FloatSchemaDocumentation = {
    this.copy(required = true)
  }
}

case class DoubleSchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val minimum: Option[Double] = None,
  private[web] val exclusiveMinimum: Boolean = false,
  private[web] val maximum: Option[Double] = None,
  private[web] val exclusiveMaximum: Boolean = false,
  private[web] val options: Set[Double] = Set(),
  private[web] val default: Option[Double] = None,
  private[web] val example: Option[Double] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): DoubleSchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the non-exclusive minimum value of this item
    * (i.e., the value may be the minimum)
    */
  def minimum(value: Double): DoubleSchemaDocumentation = {
    this.copy(minimum = Option(value), exclusiveMinimum = false)
  }

  /**
    * Sets the exclusive minimum value of this item
    * (i.e., the value may NOT be the minimum)
    */
  def minimumExcluding(value: Double): DoubleSchemaDocumentation = {
    this.copy(minimum = Option(value), exclusiveMinimum = true)
  }

  /**
    * Sets the non-exclusive maximum value of this item
    * (i.e., the value may be the maximum)
    */
  def maximum(value: Double): DoubleSchemaDocumentation = {
    this.copy(maximum = Option(value), exclusiveMaximum = false)
  }

  /**
    * Sets the maximum value of this item
    * (i.e., the value may NOT be the maximum)
    */
  def maximumExcluding(value: Double): DoubleSchemaDocumentation = {
    this.copy(maximum = Option(value), exclusiveMaximum = true)
  }

  /**
    * Marks this item as having a defined set of possible values which it may contain
    */
  def options(values: Double*): DoubleSchemaDocumentation = {
    this.copy(options = values.toSet)
  }

  /**
    * Sets the default value of this item
    */
  def default(value: Double): DoubleSchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: Double): DoubleSchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): DoubleSchemaDocumentation = {
    this.copy(required = true)
  }
}

case class StringSchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val minLength: Option[Int] = None,
  private[web] val maxLength: Option[Int] = None,
  private[web] val pattern: Option[String] = None,
  private[web] val options: Set[String] = Set(),
  private[web] val default: Option[String] = None,
  private[web] val example: Option[String] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): StringSchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the minimum length for this item
    */
  def minLength(length: Int): StringSchemaDocumentation = {
    this.copy(minLength = Option(length))
  }

  /**
    * Sets the maximum length for this item
    */
  def maxLength(length: Int): StringSchemaDocumentation = {
    this.copy(maxLength = Option(length))
  }

  /**
    * Sets the regular expression which this item must match
    */
  def pattern(pattern: String): StringSchemaDocumentation = {
    this.copy(pattern = Option(pattern))
  }

  /**
    * Marks this item as having a defined set of possible values which it may contain
    */
  def options(values: String*): StringSchemaDocumentation = {
    this.copy(options = values.toSet)
  }

  /**
    * Sets the default value of this item
    */
  def default(value: String): StringSchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: String): StringSchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): StringSchemaDocumentation = {
    this.copy(required = true)
  }
}

case class PasswordSchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val minLength: Option[Int] = None,
  private[web] val maxLength: Option[Int] = None,
  private[web] val pattern: Option[String] = None,
  private[web] val required: Boolean = true
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): PasswordSchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the minimum length for this item
    */
  def minLength(length: Int): PasswordSchemaDocumentation = {
    this.copy(minLength = Option(length))
  }

  /**
    * Sets the maximum length for this item
    */
  def maxLength(length: Int): PasswordSchemaDocumentation = {
    this.copy(maxLength = Option(length))
  }

  /**
    * Sets the regular expression which this item must match
    */
  def pattern(pattern: String): PasswordSchemaDocumentation = {
    this.copy(pattern = Option(pattern))
  }

  /**
    * Marks this item as required
    */
  def require(): PasswordSchemaDocumentation = {
    this.copy(required = true)
  }
}

case class Base64SchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val minLength: Option[Int] = None,
  private[web] val maxLength: Option[Int] = None,
  private[web] val default: Option[String] = None,
  private[web] val example: Option[String] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): Base64SchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the minimum length for this item
    */
  def minLength(length: Int): Base64SchemaDocumentation = {
    this.copy(minLength = Option(length))
  }

  /**
    * Sets the maximum length for this item
    */
  def maxLength(length: Int): Base64SchemaDocumentation = {
    this.copy(maxLength = Option(length))
  }

  /**
    * Sets the default value of this item
    */
  def default(value: String): Base64SchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: String): Base64SchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): Base64SchemaDocumentation = {
    this.copy(required = true)
  }
}

case class BinarySchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val default: Option[Any] = None,
  private[web] val example: Option[Any] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): BinarySchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the default value of this item
    */
  def default(value: String): BinarySchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: String): BinarySchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): BinarySchemaDocumentation = {
    this.copy(required = true)
  }
}

case class DateSchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val default: Option[Date] = None,
  private[web] val example: Option[Date] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): DateSchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the default value of this item
    */
  def default(value: Date): DateSchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: Date): DateSchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): DateSchemaDocumentation = {
    this.copy(required = true)
  }
}

case class LocalDateSchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val default: Option[LocalDate] = None,
  private[web] val example: Option[LocalDate] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): LocalDateSchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the default value of this item
    */
  def default(value: LocalDate): LocalDateSchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: LocalDate): LocalDateSchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): LocalDateSchemaDocumentation = {
    this.copy(required = true)
  }
}

case class LocalDateTimeSchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val default: Option[LocalDateTime] = None,
  private[web] val example: Option[LocalDateTime] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): LocalDateTimeSchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the default value of this item
    */
  def default(value: LocalDateTime): LocalDateTimeSchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: LocalDateTime): LocalDateTimeSchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): LocalDateTimeSchemaDocumentation = {
    this.copy(required = true)
  }
}

case class BooleanSchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val default: Option[Boolean] = None,
  private[web] val example: Option[Boolean] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): BooleanSchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the default value of this item
    */
  def default(value: Boolean): BooleanSchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: Boolean): BooleanSchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): BooleanSchemaDocumentation = {
    this.copy(required = true)
  }
}

case class ArraySchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val itemSchema: Option[SchemaDocumentation] = None,
  private[web] val uniqueItems: Boolean = false,
  private[web] val minSize: Option[Int] = None,
  private[web] val maxSize: Option[Int] = None,
  private[web] val default: Option[Iterable[_]] = None,
  private[web] val example: Option[Iterable[_]] = None,
  private[web] val required: Boolean = false
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): ArraySchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the array item type to Integer
    */
  def asIntegerArray(customize: IntSchemaDocumentation => IntSchemaDocumentation = identity): ArraySchemaDocumentation = {
    this.copy(itemSchema = Option(customize(IntSchemaDocumentation(None))))
  }

  /**
    * Sets the array item type to Long
    */
  def asLongArray(customize: LongSchemaDocumentation => LongSchemaDocumentation = identity): ArraySchemaDocumentation = {
    this.copy(itemSchema = Option(customize(LongSchemaDocumentation(None))))
  }

  /**
    * Sets the array item type to Float
    */
  def asFloatArray(customize: FloatSchemaDocumentation => FloatSchemaDocumentation = identity): ArraySchemaDocumentation = {
    this.copy(itemSchema = Option(customize(FloatSchemaDocumentation(None))))
  }

  /**
    * Sets the array item type to double
    */
  def asDoubleArray(customize: DoubleSchemaDocumentation => DoubleSchemaDocumentation = identity): ArraySchemaDocumentation = {
    this.copy(itemSchema = Option(customize(DoubleSchemaDocumentation(None))))
  }

  /**
    * Sets the array item type to string
    */
  def asStringArray(customize: StringSchemaDocumentation => StringSchemaDocumentation = identity): ArraySchemaDocumentation = {
    this.copy(itemSchema = Option(customize(StringSchemaDocumentation(None))))
  }

  /**
    * Sets the array item type to boolean
    */
  def asBooleanArray(customize: BooleanSchemaDocumentation => BooleanSchemaDocumentation = identity): ArraySchemaDocumentation = {
    this.copy(itemSchema = Option(customize(BooleanSchemaDocumentation(None))))
  }

  /**
    * Sets the array item type to a nested array
    */
  def asNestedArrays(customize: ArraySchemaDocumentation => ArraySchemaDocumentation = identity): ArraySchemaDocumentation = {
    this.copy(itemSchema = Option(customize(ArraySchemaDocumentation(None))))
  }

  /**
    * Sets the array item type to object
    */
  def asObjectArray(customize: ObjectSchemaDocumentation => ObjectSchemaDocumentation = identity): ArraySchemaDocumentation = {
    this.copy(itemSchema = Option(customize(ObjectSchemaDocumentation(None))))
  }
  
  /**
    * Marks this array as disallowing duplicate items
    */
  def unique(): ArraySchemaDocumentation = {
    this.copy(uniqueItems = true)
  }

  /**
    * Sets the minimum size of this array
    */
  def minSize(size: Int): ArraySchemaDocumentation = {
    this.copy(minSize = Option(size))
  }

  /**
    * Sets the maximum size of this array
    */
  def maxSize(size: Int): ArraySchemaDocumentation = {
    this.copy(maxSize = Option(size))
  }
  
  /**
    * Sets the default value of this item
    */
  def default(value: Iterable[_]): ArraySchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: Iterable[_]): ArraySchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): ArraySchemaDocumentation = {
    this.copy(required = true)
  }
}

case class ObjectSchemaDocumentation(
  private[web] override val title: Option[String],
  private[web] override val description: Option[String] = None,
  private[web] val properties: Map[String, SchemaDocumentation] = Map(),
  private[web] val default: Option[Any] = None,
  private[web] val example: Option[Any] = None,
  private[web] val required: Boolean = false,
  private[web] val requiredProperties: Set[String] = Set()
) extends SchemaDocumentation {

  /**
    * Sets the long-form description for this item
    */
  def description(description: String): ObjectSchemaDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Uses a `Product` (e.g., `case` object) to automatically generate the example and properties for this item
    */
  def from[T <: Product : ClassTag](obj: T): ObjectSchemaDocumentation = {
    this.copy(
      example = Option(obj),
      properties = objectProperties(obj)
    )
  }

  private def objectProperties[T <: Product](obj: T)(implicit classTag: ClassTag[T]): Map[String, SchemaDocumentation] = {
    classTag
      .runtimeClass
      .asInstanceOf[Class[T]]
      .getDeclaredFields
      .map { field =>
        field.setAccessible(true)
        val name: String = field.getName
        val value: Any = field.get(obj)
        field.setAccessible(false)
        name -> generateSchema(name, value)
      }
      .toMap
  }

  private def generateSchema(name: String, value: Any): SchemaDocumentation = {
    value match {
      case v: java.lang.Integer => IntSchemaDocumentation(None, required = true)
      case v: Int => IntSchemaDocumentation(None, required = true)
      case v: java.lang.Long => LongSchemaDocumentation(None, required = true)
      case v: Long => LongSchemaDocumentation(None, required = true)
      case v: java.lang.Float => FloatSchemaDocumentation(None, required = true)
      case v: Float => FloatSchemaDocumentation(None, required = true)
      case v: java.lang.Double => DoubleSchemaDocumentation(None, required = true)
      case v: Double => DoubleSchemaDocumentation(None, required = true)
      case v: java.lang.Boolean => BooleanSchemaDocumentation(None, required = true)
      case v: Boolean => BooleanSchemaDocumentation(None, required = true)
      case v: String if name.trim.toLowerCase.contains("password") => PasswordSchemaDocumentation(None)
      case v: String => StringSchemaDocumentation(None, required = true)
      case v: Date => DateSchemaDocumentation(None, required = true)
      case v: LocalDate => LocalDateSchemaDocumentation(None, required = true)
      case v: LocalDateTime => LocalDateTimeSchemaDocumentation(None, required = true)
      case v: java.util.Map[_, _] => ObjectSchemaDocumentation(None, required = true)
      case v: Map[_, _] => ObjectSchemaDocumentation(None, required = true)
      case v: Array[_] =>
        v.headOption match {
          case Some(item) =>
            ArraySchemaDocumentation(None, itemSchema = Option(generateSchema("item", item)), required = true)
          case None =>
            ArraySchemaDocumentation(None, required = true)
        }
      case v: java.util.Set[_] =>
        v.asScala.headOption match {
          case Some(item) =>
            ArraySchemaDocumentation(None, uniqueItems = true, itemSchema = Option(generateSchema("item", item)), required = true)
          case None =>
            ArraySchemaDocumentation(None, uniqueItems = true, required = true)
        }
      case v: Set[_] =>
        v.headOption match {
          case Some(item) =>
            ArraySchemaDocumentation(None, uniqueItems = true, itemSchema = Option(generateSchema("item", item)), required = true)
          case None =>
            ArraySchemaDocumentation(None, uniqueItems = true, required = true)
        }
      case v: java.lang.Iterable[_] =>
        v.asScala.headOption match {
          case Some(item) =>
            ArraySchemaDocumentation(None, itemSchema = Option(generateSchema("item", item)), required = true)
          case None =>
            ArraySchemaDocumentation(None, required = true)
        }
      case v: Iterable[_] =>
        v.headOption match {
          case Some(item) =>
            ArraySchemaDocumentation(None, itemSchema = Option(generateSchema("item", item)), required = true)
          case None =>
            ArraySchemaDocumentation(None, required = true)
        }
      case v: Product => ObjectSchemaDocumentation(None, required = true).from(v)
      case _ => ObjectSchemaDocumentation(None, required = true)
    }
  }

  /**
    * Adds an integer property to this object
    */
  def withInteger(name: String, customize: IntSchemaDocumentation => IntSchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(IntSchemaDocumentation(None))))
  }

  /**
    * Adds a long property to this object
    */
  def withLong(name: String, customize: LongSchemaDocumentation => LongSchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(LongSchemaDocumentation(None))))
  }

  /**
    * Adds a float property to this object
    */
  def withFloat(name: String, customize: FloatSchemaDocumentation => FloatSchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(FloatSchemaDocumentation(None))))
  }

  /**
    * Adds a double property to this object
    */
  def withDouble(name: String, customize: DoubleSchemaDocumentation => DoubleSchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(DoubleSchemaDocumentation(None))))
  }

  /**
    * Adds a string property to this object
    */
  def withString(name: String, customize: StringSchemaDocumentation => StringSchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(StringSchemaDocumentation(None))))
  }

  /**
    * Adds a password property to this object
    */
  def withPassword(name: String, customize: PasswordSchemaDocumentation => PasswordSchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(PasswordSchemaDocumentation(None))))
  }

  /**
    * Adds a base64-encoded string property to this object
    */
  def withBase64(name: String, customize: Base64SchemaDocumentation => Base64SchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(Base64SchemaDocumentation(None))))
  }

  /**
    * Adds a binary property to this object
    */
  def withBinary(name: String, customize: BinarySchemaDocumentation => BinarySchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(BinarySchemaDocumentation(None))))
  }

  /**
    * Adds a date property to this object
    */
  def withDate(name: String, customize: DateSchemaDocumentation => DateSchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(DateSchemaDocumentation(None))))
  }

  /**
    * Adds a local date property to this object
    */
  def withLocalDate(name: String, customize: LocalDateSchemaDocumentation => LocalDateSchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(LocalDateSchemaDocumentation(None))))
  }

  /**
    * Adds a local date/time property to this object
    */
  def withLocalDateTime(name: String, customize: LocalDateTimeSchemaDocumentation => LocalDateTimeSchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(LocalDateTimeSchemaDocumentation(None))))
  }

  /**
    * Adds a boolean property to this object
    */
  def withBoolean(name: String, customize: BooleanSchemaDocumentation => BooleanSchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(BooleanSchemaDocumentation(None))))
  }

  /**
    * Adds an array property to this object
    */
  def withArray(name: String, customize: ArraySchemaDocumentation => ArraySchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(ArraySchemaDocumentation(None))))
  }

  /**
    * Adds an object property to this object
    */
  def withObject(name: String, customize: ObjectSchemaDocumentation => ObjectSchemaDocumentation = identity): ObjectSchemaDocumentation = {
    this.copy(properties = properties + (name -> customize(ObjectSchemaDocumentation(None))))
  }

  /**
    * Sets the default value of this item
    */
  def default(value: Any): ObjectSchemaDocumentation = {
    this.copy(default = Option(value))
  }

  /**
    * An example of this item
    */
  def example(value: Any): ObjectSchemaDocumentation = {
    this.copy(example = Option(value))
  }

  /**
    * Marks this item as required
    */
  def require(): ObjectSchemaDocumentation = {
    this.copy(required = true)
  }

  /**
    * Adds a property to the list of required properties for this object
    */
  def requireProperty(property: String): ObjectSchemaDocumentation = {
    this.copy(requiredProperties = requiredProperties + property)
  }
}