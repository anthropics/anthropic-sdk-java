// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

@NoAutoDetect
class BetaErrorResponse
@JsonCreator
private constructor(
    @JsonProperty("error")
    @ExcludeMissing
    private val error: JsonField<BetaError> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun error(): BetaError = error.getRequired("error")

    fun type(): Type = type.getRequired("type")

    @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<BetaError> = error

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaErrorResponse = apply {
        if (validated) {
            return@apply
        }

        error().validate()
        type()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var error: JsonField<BetaError>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaErrorResponse: BetaErrorResponse) = apply {
            error = betaErrorResponse.error
            type = betaErrorResponse.type
            additionalProperties = betaErrorResponse.additionalProperties.toMutableMap()
        }

        fun error(error: BetaError) = error(JsonField.of(error))

        fun error(error: JsonField<BetaError>) = apply { this.error = error }

        fun error(betaInvalidRequestError: BetaInvalidRequestError) =
            error(BetaError.ofBetaInvalidRequestError(betaInvalidRequestError))

        fun error(betaAuthenticationError: BetaAuthenticationError) =
            error(BetaError.ofBetaAuthenticationError(betaAuthenticationError))

        fun error(betaBillingError: BetaBillingError) =
            error(BetaError.ofBetaBillingError(betaBillingError))

        fun error(betaPermissionError: BetaPermissionError) =
            error(BetaError.ofBetaPermissionError(betaPermissionError))

        fun error(betaNotFoundError: BetaNotFoundError) =
            error(BetaError.ofBetaNotFoundError(betaNotFoundError))

        fun error(betaRateLimitError: BetaRateLimitError) =
            error(BetaError.ofBetaRateLimitError(betaRateLimitError))

        fun error(betaGatewayTimeoutError: BetaGatewayTimeoutError) =
            error(BetaError.ofBetaGatewayTimeoutError(betaGatewayTimeoutError))

        fun error(betaApiError: BetaApiError) = error(BetaError.ofBetaApiError(betaApiError))

        fun error(betaOverloadedError: BetaOverloadedError) =
            error(BetaError.ofBetaOverloadedError(betaOverloadedError))

        fun type(type: Type) = type(JsonField.of(type))

        fun type(type: JsonField<Type>) = apply { this.type = type }

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

        fun build(): BetaErrorResponse =
            BetaErrorResponse(
                checkNotNull(error) { "`error` is required but was not set" },
                checkNotNull(type) { "`type` is required but was not set" },
                additionalProperties.toImmutable(),
            )
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val ERROR = of("error")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            ERROR,
        }

        enum class Value {
            ERROR,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                ERROR -> Value.ERROR
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                ERROR -> Known.ERROR
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaErrorResponse && error == other.error && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(error, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaErrorResponse{error=$error, type=$type, additionalProperties=$additionalProperties}"
}
