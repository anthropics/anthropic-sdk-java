// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class ErrorResponse
private constructor(
    private val error: JsonField<ErrorObject>,
    private val requestId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error") @ExcludeMissing error: JsonField<ErrorObject> = JsonMissing.of(),
        @JsonProperty("request_id") @ExcludeMissing requestId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(error, requestId, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun error(): ErrorObject = error.getRequired("error")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun requestId(): Optional<String> = requestId.getOptional("request_id")

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
    @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<ErrorObject> = error

    /**
     * Returns the raw JSON value of [requestId].
     *
     * Unlike [requestId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("request_id") @ExcludeMissing fun _requestId(): JsonField<String> = requestId

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
         * Returns a mutable builder for constructing an instance of [ErrorResponse].
         *
         * The following fields are required:
         * ```java
         * .error()
         * .requestId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ErrorResponse]. */
    class Builder internal constructor() {

        private var error: JsonField<ErrorObject>? = null
        private var requestId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("error")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(errorResponse: ErrorResponse) = apply {
            error = errorResponse.error
            requestId = errorResponse.requestId
            type = errorResponse.type
            additionalProperties = errorResponse.additionalProperties.toMutableMap()
        }

        fun error(error: ErrorObject) = error(JsonField.of(error))

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed [ErrorObject] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun error(error: JsonField<ErrorObject>) = apply { this.error = error }

        /**
         * Alias for calling [error] with `ErrorObject.ofInvalidRequestError(invalidRequestError)`.
         */
        fun error(invalidRequestError: InvalidRequestError) =
            error(ErrorObject.ofInvalidRequestError(invalidRequestError))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * InvalidRequestError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun invalidRequestErrorError(message: String) =
            error(InvalidRequestError.builder().message(message).build())

        /**
         * Alias for calling [error] with `ErrorObject.ofAuthenticationError(authenticationError)`.
         */
        fun error(authenticationError: AuthenticationError) =
            error(ErrorObject.ofAuthenticationError(authenticationError))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * AuthenticationError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun authenticationErrorError(message: String) =
            error(AuthenticationError.builder().message(message).build())

        /** Alias for calling [error] with `ErrorObject.ofBillingError(billingError)`. */
        fun error(billingError: BillingError) = error(ErrorObject.ofBillingError(billingError))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BillingError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun billingErrorError(message: String) =
            error(BillingError.builder().message(message).build())

        /** Alias for calling [error] with `ErrorObject.ofPermissionError(permissionError)`. */
        fun error(permissionError: PermissionError) =
            error(ErrorObject.ofPermissionError(permissionError))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * PermissionError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun permissionErrorError(message: String) =
            error(PermissionError.builder().message(message).build())

        /** Alias for calling [error] with `ErrorObject.ofNotFoundError(notFoundError)`. */
        fun error(notFoundError: NotFoundError) = error(ErrorObject.ofNotFoundError(notFoundError))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * NotFoundError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun notFoundErrorError(message: String) =
            error(NotFoundError.builder().message(message).build())

        /** Alias for calling [error] with `ErrorObject.ofRateLimitError(rateLimitError)`. */
        fun error(rateLimitError: RateLimitError) =
            error(ErrorObject.ofRateLimitError(rateLimitError))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * RateLimitError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun rateLimitErrorError(message: String) =
            error(RateLimitError.builder().message(message).build())

        /** Alias for calling [error] with `ErrorObject.ofTimeoutError(timeoutError)`. */
        fun error(timeoutError: GatewayTimeoutError) =
            error(ErrorObject.ofTimeoutError(timeoutError))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * GatewayTimeoutError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun timeoutErrorError(message: String) =
            error(GatewayTimeoutError.builder().message(message).build())

        /** Alias for calling [error] with `ErrorObject.ofApiError(apiError)`. */
        fun error(apiError: ApiErrorObject) = error(ErrorObject.ofApiError(apiError))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * ApiErrorObject.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun apiErrorError(message: String) =
            error(ApiErrorObject.builder().message(message).build())

        /** Alias for calling [error] with `ErrorObject.ofOverloadedError(overloadedError)`. */
        fun error(overloadedError: OverloadedError) =
            error(ErrorObject.ofOverloadedError(overloadedError))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * OverloadedError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun overloadedErrorError(message: String) =
            error(OverloadedError.builder().message(message).build())

        fun requestId(requestId: String?) = requestId(JsonField.ofNullable(requestId))

        /** Alias for calling [Builder.requestId] with `requestId.orElse(null)`. */
        fun requestId(requestId: Optional<String>) = requestId(requestId.getOrNull())

        /**
         * Sets [Builder.requestId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.requestId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun requestId(requestId: JsonField<String>) = apply { this.requestId = requestId }

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

        /**
         * Returns an immutable instance of [ErrorResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .error()
         * .requestId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ErrorResponse =
            ErrorResponse(
                checkRequired("error", error),
                checkRequired("requestId", requestId),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ErrorResponse = apply {
        if (validated) {
            return@apply
        }

        error().validate()
        requestId()
        _type().let {
            if (it != JsonValue.from("error")) {
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
        (error.asKnown().getOrNull()?.validity() ?: 0) +
            (if (requestId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("error")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ErrorResponse &&
            error == other.error &&
            requestId == other.requestId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(error, requestId, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ErrorResponse{error=$error, requestId=$requestId, type=$type, additionalProperties=$additionalProperties}"
}
