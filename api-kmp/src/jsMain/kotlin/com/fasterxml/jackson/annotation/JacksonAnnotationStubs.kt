/**
 * JS stubs for Jackson annotations — compile-time only, no-ops at runtime.
 * On JVM, the real Jackson annotation JAR provides these.
 * On JS, these stubs let model code compile with @JsonProperty etc.
 */
package com.fasterxml.jackson.annotation

@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonProperty(val value: String = "")

@Target(AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonCreator(val mode: Mode = Mode.DEFAULT) {
    enum class Mode { DEFAULT, DELEGATING, PROPERTIES, DISABLED }
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonAnyGetter

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonAnySetter

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonIgnore(val value: Boolean = true)

@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION,
    AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonInclude(val value: Include = Include.ALWAYS) {
    enum class Include { ALWAYS, NON_NULL, NON_ABSENT, NON_EMPTY, NON_DEFAULT, CUSTOM, USE_DEFAULTS }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonClassDescription(val value: String = "")

@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonPropertyDescription(val value: String = "")

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonTypeName(val value: String = "")

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonValue(val value: Boolean = true)
