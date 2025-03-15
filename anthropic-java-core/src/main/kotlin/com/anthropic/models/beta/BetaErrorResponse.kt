// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

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

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun error(): BetaError = error.getRequired("error")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("error")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [error].
     *
     * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
     */
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

        /**
         * Returns a mutable builder for constructing an instance of [BetaErrorResponse].
         *
         * The following fields are required:
         * ```java
         * .error()
         * ```
         */
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

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed [BetaError] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun error(error: JsonField<BetaError>) = apply { this.error = error }

        /** Alias for calling [error] with `BetaError.ofInvalidRequest(invalidRequest)`. */
        fun error(invalidRequest: BetaInvalidRequestError) =
            error(BetaError.ofInvalidRequest(invalidRequest))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaInvalidRequestError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun invalidRequestError(message: String) =
            error(BetaInvalidRequestError.builder().message(message).build())

        /** Alias for calling [error] with `BetaError.ofAuthentication(authentication)`. */
        fun error(authentication: BetaAuthenticationError) =
            error(BetaError.ofAuthentication(authentication))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaAuthenticationError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun authenticationError(message: String) =
            error(BetaAuthenticationError.builder().message(message).build())

        /** Alias for calling [error] with `BetaError.ofBilling(billing)`. */
        fun error(billing: BetaBillingError) = error(BetaError.ofBilling(billing))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaBillingError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun billingError(message: String) =
            error(BetaBillingError.builder().message(message).build())

        /** Alias for calling [error] with `BetaError.ofPermission(permission)`. */
        fun error(permission: BetaPermissionError) = error(BetaError.ofPermission(permission))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaPermissionError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun permissionError(message: String) =
            error(BetaPermissionError.builder().message(message).build())

        /** Alias for calling [error] with `BetaError.ofNotFound(notFound)`. */
        fun error(notFound: BetaNotFoundError) = error(BetaError.ofNotFound(notFound))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaNotFoundError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun notFoundError(message: String) =
            error(BetaNotFoundError.builder().message(message).build())

        /** Alias for calling [error] with `BetaError.ofRateLimit(rateLimit)`. */
        fun error(rateLimit: BetaRateLimitError) = error(BetaError.ofRateLimit(rateLimit))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaRateLimitError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun rateLimitError(message: String) =
            error(BetaRateLimitError.builder().message(message).build())

        /** Alias for calling [error] with `BetaError.ofGatewayTimeout(gatewayTimeout)`. */
        fun error(gatewayTimeout: BetaGatewayTimeoutError) =
            error(BetaError.ofGatewayTimeout(gatewayTimeout))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaGatewayTimeoutError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun gatewayTimeoutError(message: String) =
            error(BetaGatewayTimeoutError.builder().message(message).build())

        /** Alias for calling [error] with `BetaError.ofApi(api)`. */
        fun error(api: BetaApiError) = error(BetaError.ofApi(api))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaApiError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun apiError(message: String) = error(BetaApiError.builder().message(message).build())

        /** Alias for calling [error] with `BetaError.ofOverloaded(overloaded)`. */
        fun error(overloaded: BetaOverloadedError) = error(BetaError.ofOverloaded(overloaded))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaOverloadedError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun overloadedError(message: String) =
            error(BetaOverloadedError.builder().message(message).build())

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("error")
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
