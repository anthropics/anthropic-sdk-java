// JS compile-only stub for com.fasterxml.jackson.databind.json.JsonMapper
package com.fasterxml.jackson.databind.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.Module as JacksonModule
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.cfg.CoercionConfig
import com.fasterxml.jackson.databind.type.LogicalType
import kotlin.reflect.KClass

class JsonMapper : ObjectMapper() {

    companion object {
        fun builder(): Builder = Builder()
    }

    class Builder {
        fun addModule(module: JacksonModule): Builder = this
        fun withCoercionConfig(logicalType: LogicalType, configurer: (CoercionConfig) -> Unit): Builder = this
        fun serializationInclusion(inclusion: JsonInclude.Include): Builder = this
        fun defaultPropertyInclusion(inclusion: JsonInclude.Value): Builder = this
        fun disable(feature: DeserializationFeature): Builder = this
        fun disable(feature: SerializationFeature): Builder = this
        fun disable(feature: MapperFeature): Builder = this
        fun enable(feature: DeserializationFeature): Builder = this
        fun enable(feature: SerializationFeature): Builder = this
        fun enable(feature: MapperFeature): Builder = this
        fun build(): JsonMapper = JsonMapper()
    }
}
