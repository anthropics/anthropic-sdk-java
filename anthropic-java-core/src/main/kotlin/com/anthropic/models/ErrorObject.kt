// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@JsonDeserialize(using = ErrorObject.Deserializer::class)
@JsonSerialize(using = ErrorObject.Serializer::class)
class ErrorObject
private constructor(
    private val invalidRequestError: InvalidRequestError? = null,
    private val authenticationError: AuthenticationError? = null,
    private val billingError: BillingError? = null,
    private val permissionError: PermissionError? = null,
    private val notFoundError: NotFoundError? = null,
    private val rateLimitError: RateLimitError? = null,
    private val timeoutError: GatewayTimeoutError? = null,
    private val apiError: ApiErrorObject? = null,
    private val overloadedError: OverloadedError? = null,
    private val _json: JsonValue? = null,
) {

    fun invalidRequestError(): Optional<InvalidRequestError> =
        Optional.ofNullable(invalidRequestError)

    fun authenticationError(): Optional<AuthenticationError> =
        Optional.ofNullable(authenticationError)

    fun billingError(): Optional<BillingError> = Optional.ofNullable(billingError)

    fun permissionError(): Optional<PermissionError> = Optional.ofNullable(permissionError)

    fun notFoundError(): Optional<NotFoundError> = Optional.ofNullable(notFoundError)

    fun rateLimitError(): Optional<RateLimitError> = Optional.ofNullable(rateLimitError)

    fun timeoutError(): Optional<GatewayTimeoutError> = Optional.ofNullable(timeoutError)

    fun apiError(): Optional<ApiErrorObject> = Optional.ofNullable(apiError)

    fun overloadedError(): Optional<OverloadedError> = Optional.ofNullable(overloadedError)

    fun isInvalidRequestError(): Boolean = invalidRequestError != null

    fun isAuthenticationError(): Boolean = authenticationError != null

    fun isBillingError(): Boolean = billingError != null

    fun isPermissionError(): Boolean = permissionError != null

    fun isNotFoundError(): Boolean = notFoundError != null

    fun isRateLimitError(): Boolean = rateLimitError != null

    fun isTimeoutError(): Boolean = timeoutError != null

    fun isApiError(): Boolean = apiError != null

    fun isOverloadedError(): Boolean = overloadedError != null

    fun asInvalidRequestError(): InvalidRequestError =
        invalidRequestError.getOrThrow("invalidRequestError")

    fun asAuthenticationError(): AuthenticationError =
        authenticationError.getOrThrow("authenticationError")

    fun asBillingError(): BillingError = billingError.getOrThrow("billingError")

    fun asPermissionError(): PermissionError = permissionError.getOrThrow("permissionError")

    fun asNotFoundError(): NotFoundError = notFoundError.getOrThrow("notFoundError")

    fun asRateLimitError(): RateLimitError = rateLimitError.getOrThrow("rateLimitError")

    fun asTimeoutError(): GatewayTimeoutError = timeoutError.getOrThrow("timeoutError")

    fun asApiError(): ApiErrorObject = apiError.getOrThrow("apiError")

    fun asOverloadedError(): OverloadedError = overloadedError.getOrThrow("overloadedError")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            invalidRequestError != null -> visitor.visitInvalidRequestError(invalidRequestError)
            authenticationError != null -> visitor.visitAuthenticationError(authenticationError)
            billingError != null -> visitor.visitBillingError(billingError)
            permissionError != null -> visitor.visitPermissionError(permissionError)
            notFoundError != null -> visitor.visitNotFoundError(notFoundError)
            rateLimitError != null -> visitor.visitRateLimitError(rateLimitError)
            timeoutError != null -> visitor.visitTimeoutError(timeoutError)
            apiError != null -> visitor.visitApiError(apiError)
            overloadedError != null -> visitor.visitOverloadedError(overloadedError)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): ErrorObject = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitInvalidRequestError(invalidRequestError: InvalidRequestError) {
                    invalidRequestError.validate()
                }

                override fun visitAuthenticationError(authenticationError: AuthenticationError) {
                    authenticationError.validate()
                }

                override fun visitBillingError(billingError: BillingError) {
                    billingError.validate()
                }

                override fun visitPermissionError(permissionError: PermissionError) {
                    permissionError.validate()
                }

                override fun visitNotFoundError(notFoundError: NotFoundError) {
                    notFoundError.validate()
                }

                override fun visitRateLimitError(rateLimitError: RateLimitError) {
                    rateLimitError.validate()
                }

                override fun visitTimeoutError(timeoutError: GatewayTimeoutError) {
                    timeoutError.validate()
                }

                override fun visitApiError(apiError: ApiErrorObject) {
                    apiError.validate()
                }

                override fun visitOverloadedError(overloadedError: OverloadedError) {
                    overloadedError.validate()
                }
            }
        )
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
        accept(
            object : Visitor<Int> {
                override fun visitInvalidRequestError(invalidRequestError: InvalidRequestError) =
                    invalidRequestError.validity()

                override fun visitAuthenticationError(authenticationError: AuthenticationError) =
                    authenticationError.validity()

                override fun visitBillingError(billingError: BillingError) = billingError.validity()

                override fun visitPermissionError(permissionError: PermissionError) =
                    permissionError.validity()

                override fun visitNotFoundError(notFoundError: NotFoundError) =
                    notFoundError.validity()

                override fun visitRateLimitError(rateLimitError: RateLimitError) =
                    rateLimitError.validity()

                override fun visitTimeoutError(timeoutError: GatewayTimeoutError) =
                    timeoutError.validity()

                override fun visitApiError(apiError: ApiErrorObject) = apiError.validity()

                override fun visitOverloadedError(overloadedError: OverloadedError) =
                    overloadedError.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ErrorObject && invalidRequestError == other.invalidRequestError && authenticationError == other.authenticationError && billingError == other.billingError && permissionError == other.permissionError && notFoundError == other.notFoundError && rateLimitError == other.rateLimitError && timeoutError == other.timeoutError && apiError == other.apiError && overloadedError == other.overloadedError /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(invalidRequestError, authenticationError, billingError, permissionError, notFoundError, rateLimitError, timeoutError, apiError, overloadedError) /* spotless:on */

    override fun toString(): String =
        when {
            invalidRequestError != null -> "ErrorObject{invalidRequestError=$invalidRequestError}"
            authenticationError != null -> "ErrorObject{authenticationError=$authenticationError}"
            billingError != null -> "ErrorObject{billingError=$billingError}"
            permissionError != null -> "ErrorObject{permissionError=$permissionError}"
            notFoundError != null -> "ErrorObject{notFoundError=$notFoundError}"
            rateLimitError != null -> "ErrorObject{rateLimitError=$rateLimitError}"
            timeoutError != null -> "ErrorObject{timeoutError=$timeoutError}"
            apiError != null -> "ErrorObject{apiError=$apiError}"
            overloadedError != null -> "ErrorObject{overloadedError=$overloadedError}"
            _json != null -> "ErrorObject{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ErrorObject")
        }

    companion object {

        @JvmStatic
        fun ofInvalidRequestError(invalidRequestError: InvalidRequestError) =
            ErrorObject(invalidRequestError = invalidRequestError)

        @JvmStatic
        fun ofAuthenticationError(authenticationError: AuthenticationError) =
            ErrorObject(authenticationError = authenticationError)

        @JvmStatic
        fun ofBillingError(billingError: BillingError) = ErrorObject(billingError = billingError)

        @JvmStatic
        fun ofPermissionError(permissionError: PermissionError) =
            ErrorObject(permissionError = permissionError)

        @JvmStatic
        fun ofNotFoundError(notFoundError: NotFoundError) =
            ErrorObject(notFoundError = notFoundError)

        @JvmStatic
        fun ofRateLimitError(rateLimitError: RateLimitError) =
            ErrorObject(rateLimitError = rateLimitError)

        @JvmStatic
        fun ofTimeoutError(timeoutError: GatewayTimeoutError) =
            ErrorObject(timeoutError = timeoutError)

        @JvmStatic fun ofApiError(apiError: ApiErrorObject) = ErrorObject(apiError = apiError)

        @JvmStatic
        fun ofOverloadedError(overloadedError: OverloadedError) =
            ErrorObject(overloadedError = overloadedError)
    }

    /**
     * An interface that defines how to map each variant of [ErrorObject] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitInvalidRequestError(invalidRequestError: InvalidRequestError): T

        fun visitAuthenticationError(authenticationError: AuthenticationError): T

        fun visitBillingError(billingError: BillingError): T

        fun visitPermissionError(permissionError: PermissionError): T

        fun visitNotFoundError(notFoundError: NotFoundError): T

        fun visitRateLimitError(rateLimitError: RateLimitError): T

        fun visitTimeoutError(timeoutError: GatewayTimeoutError): T

        fun visitApiError(apiError: ApiErrorObject): T

        fun visitOverloadedError(overloadedError: OverloadedError): T

        /**
         * Maps an unknown variant of [ErrorObject] to a value of type [T].
         *
         * An instance of [ErrorObject] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ErrorObject: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<ErrorObject>(ErrorObject::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ErrorObject {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "invalid_request_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<InvalidRequestError>())?.let {
                        ErrorObject(invalidRequestError = it, _json = json)
                    } ?: ErrorObject(_json = json)
                }
                "authentication_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<AuthenticationError>())?.let {
                        ErrorObject(authenticationError = it, _json = json)
                    } ?: ErrorObject(_json = json)
                }
                "billing_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BillingError>())?.let {
                        ErrorObject(billingError = it, _json = json)
                    } ?: ErrorObject(_json = json)
                }
                "permission_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<PermissionError>())?.let {
                        ErrorObject(permissionError = it, _json = json)
                    } ?: ErrorObject(_json = json)
                }
                "not_found_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<NotFoundError>())?.let {
                        ErrorObject(notFoundError = it, _json = json)
                    } ?: ErrorObject(_json = json)
                }
                "rate_limit_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<RateLimitError>())?.let {
                        ErrorObject(rateLimitError = it, _json = json)
                    } ?: ErrorObject(_json = json)
                }
                "timeout_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<GatewayTimeoutError>())?.let {
                        ErrorObject(timeoutError = it, _json = json)
                    } ?: ErrorObject(_json = json)
                }
                "api_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<ApiErrorObject>())?.let {
                        ErrorObject(apiError = it, _json = json)
                    } ?: ErrorObject(_json = json)
                }
                "overloaded_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<OverloadedError>())?.let {
                        ErrorObject(overloadedError = it, _json = json)
                    } ?: ErrorObject(_json = json)
                }
            }

            return ErrorObject(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<ErrorObject>(ErrorObject::class) {

        override fun serialize(
            value: ErrorObject,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.invalidRequestError != null ->
                    generator.writeObject(value.invalidRequestError)
                value.authenticationError != null ->
                    generator.writeObject(value.authenticationError)
                value.billingError != null -> generator.writeObject(value.billingError)
                value.permissionError != null -> generator.writeObject(value.permissionError)
                value.notFoundError != null -> generator.writeObject(value.notFoundError)
                value.rateLimitError != null -> generator.writeObject(value.rateLimitError)
                value.timeoutError != null -> generator.writeObject(value.timeoutError)
                value.apiError != null -> generator.writeObject(value.apiError)
                value.overloadedError != null -> generator.writeObject(value.overloadedError)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ErrorObject")
            }
        }
    }
}
