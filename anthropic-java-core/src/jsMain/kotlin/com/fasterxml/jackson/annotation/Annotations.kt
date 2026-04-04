// JS compile-only stubs for com.fasterxml.jackson.annotation
package com.fasterxml.jackson.annotation

@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonProperty(val value: String = "", val required: Boolean = false)

@Target(AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonCreator(val mode: Mode = Mode.DEFAULT) {
    enum class Mode { DEFAULT, DELEGATING, PROPERTIES, DISABLED }
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonAnyGetter

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonAnySetter

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonInclude(val value: Include = Include.ALWAYS, val content: Include = Include.ALWAYS) {
    enum class Include { ALWAYS, NON_NULL, NON_ABSENT, NON_EMPTY, NON_DEFAULT, CUSTOM, USE_DEFAULTS }
    class Value private constructor() {
        fun withValueFilter(filterType: Any?): Value = this
        companion object {
            fun construct(valueInclusion: Include, contentInclusion: Include): Value = Value()
        }
    }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonTypeName(val value: String = "")

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonValue

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonIgnore
