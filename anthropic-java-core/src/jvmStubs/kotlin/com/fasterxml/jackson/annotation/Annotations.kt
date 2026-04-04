package com.fasterxml.jackson.annotation
annotation class JsonProperty(val value: String = "", val required: Boolean = false, val defaultValue: String = "")
annotation class JsonCreator(val mode: Mode = Mode.DEFAULT) { enum class Mode { DEFAULT, DELEGATING, PROPERTIES, DISABLED } }
annotation class JsonAnyGetter
annotation class JsonAnySetter
annotation class JsonInclude(val value: Include = Include.ALWAYS, val content: Include = Include.ALWAYS) { enum class Include { ALWAYS, NON_NULL, NON_ABSENT, NON_EMPTY, NON_DEFAULT, CUSTOM, USE_DEFAULTS }; class Value private constructor() { fun withValueFilter(filterType: Any?): Value = this; companion object { fun construct(valueInclusion: Include, contentInclusion: Include): Value = Value() } } }
annotation class JsonTypeName(val value: String = "")
annotation class JsonValue
annotation class JsonIgnore
annotation class JacksonAnnotationsInside
