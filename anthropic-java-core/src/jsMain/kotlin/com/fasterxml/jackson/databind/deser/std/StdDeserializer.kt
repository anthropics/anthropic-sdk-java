// JS compile-only stub for StdDeserializer
package com.fasterxml.jackson.databind.deser.std

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.type.LogicalType
import kotlin.reflect.KClass

abstract class StdDeserializer<T> : JsonDeserializer<T> {
    constructor(vc: KClass<*>?)
    constructor(vc: Any?) // For type.java patterns

    open fun logicalType(): LogicalType = LogicalType.Other

    override fun getNullValue(context: DeserializationContext?): T? = null
}
