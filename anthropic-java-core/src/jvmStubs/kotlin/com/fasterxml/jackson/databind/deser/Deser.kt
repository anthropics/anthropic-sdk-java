package com.fasterxml.jackson.databind.deser
import com.fasterxml.jackson.databind.*
interface ContextualDeserializer { fun createContextual(context: DeserializationContext, property: BeanProperty?): JsonDeserializer<*> }
