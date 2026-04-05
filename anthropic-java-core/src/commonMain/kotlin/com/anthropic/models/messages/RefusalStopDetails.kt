// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages
import kotlinx.kmp.util.core.getOptional

import kotlinx.kmp.util.core.Enum
import kotlinx.kmp.util.core.ExcludeMissing
import kotlinx.kmp.util.core.JsonField
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.kmp.util.core.contentHash
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Structured information about a refusal. */
class RefusalStopDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val category: JsonField<Category>,
    private val explanation: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("category") @ExcludeMissing category: JsonField<Category> = JsonMissing.of(),
        @JsonProperty("explanation")
        @ExcludeMissing
        explanation: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(category, explanation, type, mutableMapOf())

    /**
     * The policy category that triggered the refusal.
     *
     * `null` when the refusal doesn't map to a named category.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun category(): Optional<Category> = category.getOptional("category")

    /**
     * Human-readable explanation of the refusal.
     *
     * This text is not guaranteed to be stable. `null` when no explanation is available for the
     * category.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun explanation(): Optional<String> = explanation.getOptional("explanation")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("refusal")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [category].
     *
     * Unlike [category], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("category") @ExcludeMissing fun _category(): JsonField<Category> = category

    /**
     * Returns the raw JSON value of [explanation].
     *
     * Unlike [explanation], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("explanation") @ExcludeMissing fun _explanation(): JsonField<String> = explanation

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        additionalProperties.toMap()

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [RefusalStopDetails].
         *
         * The following fields are required:
         * ```java
         * .category()
         * .explanation()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [RefusalStopDetails]. */
    class Builder internal constructor() {

        private var category: JsonField<Category>? = null
        private var explanation: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("refusal")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(refusalStopDetails: RefusalStopDetails) = apply {
            category = refusalStopDetails.category
            explanation = refusalStopDetails.explanation
            type = refusalStopDetails.type
            additionalProperties = refusalStopDetails.additionalProperties.toMutableMap()
        }

        /**
         * The policy category that triggered the refusal.
         *
         * `null` when the refusal doesn't map to a named category.
         */
        fun category(category: Category?) = category(JsonField.ofNullable(category))

        /** Alias for calling [Builder.category] with `category.orElse(null)`. */
        fun category(category: Optional<Category>) = category(category.getOrNull())

        /**
         * Sets [Builder.category] to an arbitrary JSON value.
         *
         * You should usually call [Builder.category] with a well-typed [Category] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun category(category: JsonField<Category>) = apply { this.category = category }

        /**
         * Human-readable explanation of the refusal.
         *
         * This text is not guaranteed to be stable. `null` when no explanation is available for the
         * category.
         */
        fun explanation(explanation: String?) = explanation(JsonField.ofNullable(explanation))

        /** Alias for calling [Builder.explanation] with `explanation.orElse(null)`. */
        fun explanation(explanation: Optional<String>) = explanation(explanation.getOrNull())

        /**
         * Sets [Builder.explanation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.explanation] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun explanation(explanation: JsonField<String>) = apply { this.explanation = explanation }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("refusal")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [RefusalStopDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .category()
         * .explanation()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): RefusalStopDetails =
            RefusalStopDetails(
                checkRequired("category", category),
                checkRequired("explanation", explanation),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): RefusalStopDetails = apply {
        if (validated) {
            return@apply
        }

        category().ifPresent { it.validate() }
        explanation()
        _type().let {
            if (it != JsonValue.from("refusal")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    internal fun validity(): Int =
        (category.asKnown()?.validity() ?: 0) +
            (if (explanation.asKnown() != null) 1 else 0) +
            type.let { if (it == JsonValue.from("refusal")) 1 else 0 }

    /**
     * The policy category that triggered the refusal.
     *
     * `null` when the refusal doesn't map to a named category.
     */
    class Category @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val CYBER = of("cyber")

            val BIO = of("bio")

            @JvmStatic fun of(value: String) = Category(JsonField.of(value))
        }

        /** An enum containing [Category]'s known values. */
        enum class Known {
            CYBER,
            BIO,
        }

        /**
         * An enum containing [Category]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Category] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            CYBER,
            BIO,
            /** An enum member indicating that [Category] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                CYBER -> Value.CYBER
                BIO -> Value.BIO
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                CYBER -> Known.CYBER
                BIO -> Known.BIO
                else -> throw AnthropicInvalidDataException("Unknown Category: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString() ?: throw AnthropicInvalidDataException("Value is not a String")

        private var validated: Boolean = false

        fun validate(): Category = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Category && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RefusalStopDetails &&
            category == other.category &&
            explanation == other.explanation &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        contentHash(category, explanation, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "RefusalStopDetails{category=$category, explanation=$explanation, type=$type, additionalProperties=$additionalProperties}"
}
