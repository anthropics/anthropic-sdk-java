// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

class WebSearchToolResultError
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val errorCode: JsonField<ErrorCode>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code")
        @ExcludeMissing
        errorCode: JsonField<ErrorCode> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(errorCode, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun errorCode(): ErrorCode = errorCode.getRequired("error_code")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("web_search_tool_result_error")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [errorCode].
     *
     * Unlike [errorCode], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error_code") @ExcludeMissing fun _errorCode(): JsonField<ErrorCode> = errorCode

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [WebSearchToolResultError].
         *
         * The following fields are required:
         * ```java
         * .errorCode()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WebSearchToolResultError]. */
    class Builder internal constructor() {

        private var errorCode: JsonField<ErrorCode>? = null
        private var type: JsonValue = JsonValue.from("web_search_tool_result_error")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(webSearchToolResultError: WebSearchToolResultError) = apply {
            errorCode = webSearchToolResultError.errorCode
            type = webSearchToolResultError.type
            additionalProperties = webSearchToolResultError.additionalProperties.toMutableMap()
        }

        fun errorCode(errorCode: ErrorCode) = errorCode(JsonField.of(errorCode))

        /**
         * Sets [Builder.errorCode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.errorCode] with a well-typed [ErrorCode] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun errorCode(errorCode: JsonField<ErrorCode>) = apply { this.errorCode = errorCode }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("web_search_tool_result_error")
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
         * Returns an immutable instance of [WebSearchToolResultError].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .errorCode()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): WebSearchToolResultError =
            WebSearchToolResultError(
                checkRequired("errorCode", errorCode),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): WebSearchToolResultError = apply {
        if (validated) {
            return@apply
        }

        errorCode().validate()
        _type().let {
            if (it != JsonValue.from("web_search_tool_result_error")) {
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
    @JvmSynthetic
    internal fun validity(): Int =
        (errorCode.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("web_search_tool_result_error")) 1 else 0 }

    class ErrorCode @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val INVALID_TOOL_INPUT = of("invalid_tool_input")

            @JvmField val UNAVAILABLE = of("unavailable")

            @JvmField val MAX_USES_EXCEEDED = of("max_uses_exceeded")

            @JvmField val TOO_MANY_REQUESTS = of("too_many_requests")

            @JvmField val QUERY_TOO_LONG = of("query_too_long")

            @JvmStatic fun of(value: String) = ErrorCode(JsonField.of(value))
        }

        /** An enum containing [ErrorCode]'s known values. */
        enum class Known {
            INVALID_TOOL_INPUT,
            UNAVAILABLE,
            MAX_USES_EXCEEDED,
            TOO_MANY_REQUESTS,
            QUERY_TOO_LONG,
        }

        /**
         * An enum containing [ErrorCode]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ErrorCode] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            INVALID_TOOL_INPUT,
            UNAVAILABLE,
            MAX_USES_EXCEEDED,
            TOO_MANY_REQUESTS,
            QUERY_TOO_LONG,
            /**
             * An enum member indicating that [ErrorCode] was instantiated with an unknown value.
             */
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
                INVALID_TOOL_INPUT -> Value.INVALID_TOOL_INPUT
                UNAVAILABLE -> Value.UNAVAILABLE
                MAX_USES_EXCEEDED -> Value.MAX_USES_EXCEEDED
                TOO_MANY_REQUESTS -> Value.TOO_MANY_REQUESTS
                QUERY_TOO_LONG -> Value.QUERY_TOO_LONG
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
                INVALID_TOOL_INPUT -> Known.INVALID_TOOL_INPUT
                UNAVAILABLE -> Known.UNAVAILABLE
                MAX_USES_EXCEEDED -> Known.MAX_USES_EXCEEDED
                TOO_MANY_REQUESTS -> Known.TOO_MANY_REQUESTS
                QUERY_TOO_LONG -> Known.QUERY_TOO_LONG
                else -> throw AnthropicInvalidDataException("Unknown ErrorCode: $value")
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
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): ErrorCode = apply {
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

            return other is ErrorCode && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WebSearchToolResultError &&
            errorCode == other.errorCode &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(errorCode, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "WebSearchToolResultError{errorCode=$errorCode, type=$type, additionalProperties=$additionalProperties}"
}
