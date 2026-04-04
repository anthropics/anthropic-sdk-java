package com.fasterxml.jackson.databind.deser.std
import com.fasterxml.jackson.databind.*; import com.fasterxml.jackson.databind.type.LogicalType
abstract class StdDeserializer<T> : JsonDeserializer<T> { constructor(vc: Any?); open fun logicalType(): LogicalType = LogicalType.Other; override fun getNullValue(context: DeserializationContext?): T? = null }
