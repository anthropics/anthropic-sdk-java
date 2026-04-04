// JS compile-only stubs for com.fasterxml.jackson.databind.deser
package com.fasterxml.jackson.databind.deser

import com.fasterxml.jackson.databind.BeanProperty
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

interface ContextualDeserializer {
    fun createContextual(context: DeserializationContext, property: BeanProperty?): JsonDeserializer<*>
}
