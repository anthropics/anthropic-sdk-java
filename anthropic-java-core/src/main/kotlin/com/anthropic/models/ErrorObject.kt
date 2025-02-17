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
    private val gatewayTimeoutError: GatewayTimeoutError? = null,
    private val api: ApiErrorObject? = null,
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

    fun gatewayTimeoutError(): Optional<GatewayTimeoutError> =
        Optional.ofNullable(gatewayTimeoutError)

    fun api(): Optional<ApiErrorObject> = Optional.ofNullable(api)

    fun overloadedError(): Optional<OverloadedError> = Optional.ofNullable(overloadedError)

    fun isInvalidRequestError(): Boolean = invalidRequestError != null

    fun isAuthenticationError(): Boolean = authenticationError != null

    fun isBillingError(): Boolean = billingError != null

    fun isPermissionError(): Boolean = permissionError != null

    fun isNotFoundError(): Boolean = notFoundError != null

    fun isRateLimitError(): Boolean = rateLimitError != null

    fun isGatewayTimeoutError(): Boolean = gatewayTimeoutError != null

    fun isApi(): Boolean = api != null

    fun isOverloadedError(): Boolean = overloadedError != null

    fun asInvalidRequestError(): InvalidRequestError =
        invalidRequestError.getOrThrow("invalidRequestError")

    fun asAuthenticationError(): AuthenticationError =
        authenticationError.getOrThrow("authenticationError")

    fun asBillingError(): BillingError = billingError.getOrThrow("billingError")

    fun asPermissionError(): PermissionError = permissionError.getOrThrow("permissionError")

    fun asNotFoundError(): NotFoundError = notFoundError.getOrThrow("notFoundError")

    fun asRateLimitError(): RateLimitError = rateLimitError.getOrThrow("rateLimitError")

    fun asGatewayTimeoutError(): GatewayTimeoutError =
        gatewayTimeoutError.getOrThrow("gatewayTimeoutError")

    fun asApi(): ApiErrorObject = api.getOrThrow("api")

    fun asOverloadedError(): OverloadedError = overloadedError.getOrThrow("overloadedError")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            invalidRequestError != null -> visitor.visitInvalidRequestError(invalidRequestError)
            authenticationError != null -> visitor.visitAuthenticationError(authenticationError)
            billingError != null -> visitor.visitBillingError(billingError)
            permissionError != null -> visitor.visitPermissionError(permissionError)
            notFoundError != null -> visitor.visitNotFoundError(notFoundError)
            rateLimitError != null -> visitor.visitRateLimitError(rateLimitError)
            gatewayTimeoutError != null -> visitor.visitGatewayTimeoutError(gatewayTimeoutError)
            api != null -> visitor.visitApi(api)
            overloadedError != null -> visitor.visitOverloadedError(overloadedError)
            else -> visitor.unknown(_json)
        }
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

                override fun visitGatewayTimeoutError(gatewayTimeoutError: GatewayTimeoutError) {
                    gatewayTimeoutError.validate()
                }

                override fun visitApi(api: ApiErrorObject) {
                    api.validate()
                }

                override fun visitOverloadedError(overloadedError: OverloadedError) {
                    overloadedError.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ErrorObject && invalidRequestError == other.invalidRequestError && authenticationError == other.authenticationError && billingError == other.billingError && permissionError == other.permissionError && notFoundError == other.notFoundError && rateLimitError == other.rateLimitError && gatewayTimeoutError == other.gatewayTimeoutError && api == other.api && overloadedError == other.overloadedError /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(invalidRequestError, authenticationError, billingError, permissionError, notFoundError, rateLimitError, gatewayTimeoutError, api, overloadedError) /* spotless:on */

    override fun toString(): String =
        when {
            invalidRequestError != null -> "ErrorObject{invalidRequestError=$invalidRequestError}"
            authenticationError != null -> "ErrorObject{authenticationError=$authenticationError}"
            billingError != null -> "ErrorObject{billingError=$billingError}"
            permissionError != null -> "ErrorObject{permissionError=$permissionError}"
            notFoundError != null -> "ErrorObject{notFoundError=$notFoundError}"
            rateLimitError != null -> "ErrorObject{rateLimitError=$rateLimitError}"
            gatewayTimeoutError != null -> "ErrorObject{gatewayTimeoutError=$gatewayTimeoutError}"
            api != null -> "ErrorObject{api=$api}"
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
        fun ofGatewayTimeoutError(gatewayTimeoutError: GatewayTimeoutError) =
            ErrorObject(gatewayTimeoutError = gatewayTimeoutError)

        @JvmStatic fun ofApi(api: ApiErrorObject) = ErrorObject(api = api)

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

        fun visitGatewayTimeoutError(gatewayTimeoutError: GatewayTimeoutError): T

        fun visitApi(api: ApiErrorObject): T

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
                    tryDeserialize(node, jacksonTypeRef<InvalidRequestError>()) { it.validate() }
                        ?.let {
                            return ErrorObject(invalidRequestError = it, _json = json)
                        }
                }
                "authentication_error" -> {
                    tryDeserialize(node, jacksonTypeRef<AuthenticationError>()) { it.validate() }
                        ?.let {
                            return ErrorObject(authenticationError = it, _json = json)
                        }
                }
                "billing_error" -> {
                    tryDeserialize(node, jacksonTypeRef<BillingError>()) { it.validate() }
                        ?.let {
                            return ErrorObject(billingError = it, _json = json)
                        }
                }
                "permission_error" -> {
                    tryDeserialize(node, jacksonTypeRef<PermissionError>()) { it.validate() }
                        ?.let {
                            return ErrorObject(permissionError = it, _json = json)
                        }
                }
                "not_found_error" -> {
                    tryDeserialize(node, jacksonTypeRef<NotFoundError>()) { it.validate() }
                        ?.let {
                            return ErrorObject(notFoundError = it, _json = json)
                        }
                }
                "rate_limit_error" -> {
                    tryDeserialize(node, jacksonTypeRef<RateLimitError>()) { it.validate() }
                        ?.let {
                            return ErrorObject(rateLimitError = it, _json = json)
                        }
                }
                "timeout_error" -> {
                    tryDeserialize(node, jacksonTypeRef<GatewayTimeoutError>()) { it.validate() }
                        ?.let {
                            return ErrorObject(gatewayTimeoutError = it, _json = json)
                        }
                }
                "api_error" -> {
                    tryDeserialize(node, jacksonTypeRef<ApiErrorObject>()) { it.validate() }
                        ?.let {
                            return ErrorObject(api = it, _json = json)
                        }
                }
                "overloaded_error" -> {
                    tryDeserialize(node, jacksonTypeRef<OverloadedError>()) { it.validate() }
                        ?.let {
                            return ErrorObject(overloadedError = it, _json = json)
                        }
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
                value.gatewayTimeoutError != null ->
                    generator.writeObject(value.gatewayTimeoutError)
                value.api != null -> generator.writeObject(value.api)
                value.overloadedError != null -> generator.writeObject(value.overloadedError)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ErrorObject")
            }
        }
    }
}
