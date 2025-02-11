// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
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
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun error(): BetaError = error.getRequired("error")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<BetaError> = error

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaErrorResponse = apply {
        if (validated) {
            return@apply
        }

        error().validate()
        _type().let {
            if (it != JsonValue.from("error")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaErrorResponse]. */
    class Builder internal constructor() {

        private var error: JsonField<BetaError>? = null
        private var type: JsonValue = JsonValue.from("error")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaErrorResponse: BetaErrorResponse) = apply {
            error = betaErrorResponse.error
            type = betaErrorResponse.type
            additionalProperties = betaErrorResponse.additionalProperties.toMutableMap()
        }

        fun error(error: BetaError) = error(JsonField.of(error))

        fun error(error: JsonField<BetaError>) = apply { this.error = error }

        fun error(invalidRequest: BetaInvalidRequestError) =
            error(BetaError.ofInvalidRequest(invalidRequest))

        fun invalidRequestError(message: String) =
            error(BetaInvalidRequestError.builder().message(message).build())

        fun error(authentication: BetaAuthenticationError) =
            error(BetaError.ofAuthentication(authentication))

        fun authenticationError(message: String) =
            error(BetaAuthenticationError.builder().message(message).build())

        fun error(billing: BetaBillingError) = error(BetaError.ofBilling(billing))

        fun billingError(message: String) =
            error(BetaBillingError.builder().message(message).build())

        fun error(permission: BetaPermissionError) = error(BetaError.ofPermission(permission))

        fun permissionError(message: String) =
            error(BetaPermissionError.builder().message(message).build())

        fun error(notFound: BetaNotFoundError) = error(BetaError.ofNotFound(notFound))

        fun notFoundError(message: String) =
            error(BetaNotFoundError.builder().message(message).build())

        fun error(rateLimit: BetaRateLimitError) = error(BetaError.ofRateLimit(rateLimit))

        fun rateLimitError(message: String) =
            error(BetaRateLimitError.builder().message(message).build())

        fun error(gatewayTimeout: BetaGatewayTimeoutError) =
            error(BetaError.ofGatewayTimeout(gatewayTimeout))

        fun gatewayTimeoutError(message: String) =
            error(BetaGatewayTimeoutError.builder().message(message).build())

        fun error(api: BetaApiError) = error(BetaError.ofApi(api))

        fun apiError(message: String) = error(BetaApiError.builder().message(message).build())

        fun error(overloaded: BetaOverloadedError) = error(BetaError.ofOverloaded(overloaded))

        fun overloadedError(message: String) =
            error(BetaOverloadedError.builder().message(message).build())

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

        fun build(): BetaErrorResponse =
            BetaErrorResponse(
                checkRequired("error", error),
                type,
                additionalProperties.toImmutable(),
            )
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
