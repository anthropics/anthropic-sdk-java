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
class ErrorResponse
@JsonCreator
private constructor(
    @JsonProperty("error")
    @ExcludeMissing
    private val error: JsonField<ErrorObject> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun error(): ErrorObject = error.getRequired("error")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<ErrorObject> = error

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): ErrorResponse = apply {
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

    /** A builder for [ErrorResponse]. */
    class Builder internal constructor() {

        private var error: JsonField<ErrorObject>? = null
        private var type: JsonValue = JsonValue.from("error")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(errorResponse: ErrorResponse) = apply {
            error = errorResponse.error
            type = errorResponse.type
            additionalProperties = errorResponse.additionalProperties.toMutableMap()
        }

        fun error(error: ErrorObject) = error(JsonField.of(error))

        fun error(error: JsonField<ErrorObject>) = apply { this.error = error }

        fun error(invalidRequestError: InvalidRequestError) =
            error(ErrorObject.ofInvalidRequestError(invalidRequestError))

        fun invalidRequestErrorError(message: String) =
            error(InvalidRequestError.builder().message(message).build())

        fun error(authenticationError: AuthenticationError) =
            error(ErrorObject.ofAuthenticationError(authenticationError))

        fun authenticationErrorError(message: String) =
            error(AuthenticationError.builder().message(message).build())

        fun error(billingError: BillingError) = error(ErrorObject.ofBillingError(billingError))

        fun billingErrorError(message: String) =
            error(BillingError.builder().message(message).build())

        fun error(permissionError: PermissionError) =
            error(ErrorObject.ofPermissionError(permissionError))

        fun permissionErrorError(message: String) =
            error(PermissionError.builder().message(message).build())

        fun error(notFoundError: NotFoundError) = error(ErrorObject.ofNotFoundError(notFoundError))

        fun notFoundErrorError(message: String) =
            error(NotFoundError.builder().message(message).build())

        fun error(rateLimitError: RateLimitError) =
            error(ErrorObject.ofRateLimitError(rateLimitError))

        fun rateLimitErrorError(message: String) =
            error(RateLimitError.builder().message(message).build())

        fun error(gatewayTimeoutError: GatewayTimeoutError) =
            error(ErrorObject.ofGatewayTimeoutError(gatewayTimeoutError))

        fun gatewayTimeoutErrorError(message: String) =
            error(GatewayTimeoutError.builder().message(message).build())

        fun error(api: ApiErrorObject) = error(ErrorObject.ofApi(api))

        fun apiError(message: String) = error(ApiErrorObject.builder().message(message).build())

        fun error(overloadedError: OverloadedError) =
            error(ErrorObject.ofOverloadedError(overloadedError))

        fun overloadedErrorError(message: String) =
            error(OverloadedError.builder().message(message).build())

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

        fun build(): ErrorResponse =
            ErrorResponse(checkRequired("error", error), type, additionalProperties.toImmutable())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ErrorResponse && error == other.error && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(error, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ErrorResponse{error=$error, type=$type, additionalProperties=$additionalProperties}"
}
