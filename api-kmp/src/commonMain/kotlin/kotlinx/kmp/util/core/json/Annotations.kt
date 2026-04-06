@file:Suppress("unused")

package kotlinx.kmp.util.core.json

import kotlin.reflect.KClass

/**
 * KMP-compatible annotation declarations for JSON serialization.
 *
 * On JVM, these resolve to real Jackson annotations via actual typealias.
 * On JS/Native, `@OptionalExpectation` silently drops them — model classes
 * compile without annotations, and the KotlinxApiJsonBackend handles
 * serialization via @Serializable/@SerialName instead.
 *
 * This design means model files can use `@JsonProperty("field_name")` on
 * all platforms. On JVM it drives Jackson; on non-JVM it's a no-op.
 */

// --- JSON property annotations ---

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonProperty(val value: String = "")

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonCreator(val mode: Int = 0)

/** Constants matching Jackson's JsonCreator.Mode ordinals for use in @JsonCreator(mode = ...) */
object JsonCreatorMode {
    const val DEFAULT = 0
    const val DELEGATING = 1
    const val PROPERTIES = 2
    const val DISABLED = 3
}

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonAnyGetter()

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonAnySetter()

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonIgnore(val value: Boolean = true)

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION,
    AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonInclude()

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonClassDescription(val value: String = "")

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonPropertyDescription(val value: String = "")

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonTypeName(val value: String = "")

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonValue(val value: Boolean = true)

// --- Serialization control annotations ---

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonDeserialize(val using: KClass<*>)

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
expect annotation class JsonSerialize(val using: KClass<*>)
