// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

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

@JsonDeserialize(using = BetaError.Deserializer::class)
@JsonSerialize(using = BetaError.Serializer::class)
class BetaError
private constructor(
    private val invalidRequest: BetaInvalidRequestError? = null,
    private val authentication: BetaAuthenticationError? = null,
    private val billing: BetaBillingError? = null,
    private val permission: BetaPermissionError? = null,
    private val notFound: BetaNotFoundError? = null,
    private val rateLimit: BetaRateLimitError? = null,
    private val timeout: BetaGatewayTimeoutError? = null,
    private val api: BetaApiError? = null,
    private val overloaded: BetaOverloadedError? = null,
    private val _json: JsonValue? = null,
) {

    fun invalidRequest(): Optional<BetaInvalidRequestError> = Optional.ofNullable(invalidRequest)

    fun authentication(): Optional<BetaAuthenticationError> = Optional.ofNullable(authentication)

    fun billing(): Optional<BetaBillingError> = Optional.ofNullable(billing)

    fun permission(): Optional<BetaPermissionError> = Optional.ofNullable(permission)

    fun notFound(): Optional<BetaNotFoundError> = Optional.ofNullable(notFound)

    fun rateLimit(): Optional<BetaRateLimitError> = Optional.ofNullable(rateLimit)

    fun timeout(): Optional<BetaGatewayTimeoutError> = Optional.ofNullable(timeout)

    fun api(): Optional<BetaApiError> = Optional.ofNullable(api)

    fun overloaded(): Optional<BetaOverloadedError> = Optional.ofNullable(overloaded)

    fun isInvalidRequest(): Boolean = invalidRequest != null

    fun isAuthentication(): Boolean = authentication != null

    fun isBilling(): Boolean = billing != null

    fun isPermission(): Boolean = permission != null

    fun isNotFound(): Boolean = notFound != null

    fun isRateLimit(): Boolean = rateLimit != null

    fun isTimeout(): Boolean = timeout != null

    fun isApi(): Boolean = api != null

    fun isOverloaded(): Boolean = overloaded != null

    fun asInvalidRequest(): BetaInvalidRequestError = invalidRequest.getOrThrow("invalidRequest")

    fun asAuthentication(): BetaAuthenticationError = authentication.getOrThrow("authentication")

    fun asBilling(): BetaBillingError = billing.getOrThrow("billing")

    fun asPermission(): BetaPermissionError = permission.getOrThrow("permission")

    fun asNotFound(): BetaNotFoundError = notFound.getOrThrow("notFound")

    fun asRateLimit(): BetaRateLimitError = rateLimit.getOrThrow("rateLimit")

    fun asTimeout(): BetaGatewayTimeoutError = timeout.getOrThrow("timeout")

    fun asApi(): BetaApiError = api.getOrThrow("api")

    fun asOverloaded(): BetaOverloadedError = overloaded.getOrThrow("overloaded")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            invalidRequest != null -> visitor.visitInvalidRequest(invalidRequest)
            authentication != null -> visitor.visitAuthentication(authentication)
            billing != null -> visitor.visitBilling(billing)
            permission != null -> visitor.visitPermission(permission)
            notFound != null -> visitor.visitNotFound(notFound)
            rateLimit != null -> visitor.visitRateLimit(rateLimit)
            timeout != null -> visitor.visitTimeout(timeout)
            api != null -> visitor.visitApi(api)
            overloaded != null -> visitor.visitOverloaded(overloaded)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): BetaError = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitInvalidRequest(invalidRequest: BetaInvalidRequestError) {
                    invalidRequest.validate()
                }

                override fun visitAuthentication(authentication: BetaAuthenticationError) {
                    authentication.validate()
                }

                override fun visitBilling(billing: BetaBillingError) {
                    billing.validate()
                }

                override fun visitPermission(permission: BetaPermissionError) {
                    permission.validate()
                }

                override fun visitNotFound(notFound: BetaNotFoundError) {
                    notFound.validate()
                }

                override fun visitRateLimit(rateLimit: BetaRateLimitError) {
                    rateLimit.validate()
                }

                override fun visitTimeout(timeout: BetaGatewayTimeoutError) {
                    timeout.validate()
                }

                override fun visitApi(api: BetaApiError) {
                    api.validate()
                }

                override fun visitOverloaded(overloaded: BetaOverloadedError) {
                    overloaded.validate()
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
                override fun visitInvalidRequest(invalidRequest: BetaInvalidRequestError) =
                    invalidRequest.validity()

                override fun visitAuthentication(authentication: BetaAuthenticationError) =
                    authentication.validity()

                override fun visitBilling(billing: BetaBillingError) = billing.validity()

                override fun visitPermission(permission: BetaPermissionError) =
                    permission.validity()

                override fun visitNotFound(notFound: BetaNotFoundError) = notFound.validity()

                override fun visitRateLimit(rateLimit: BetaRateLimitError) = rateLimit.validity()

                override fun visitTimeout(timeout: BetaGatewayTimeoutError) = timeout.validity()

                override fun visitApi(api: BetaApiError) = api.validity()

                override fun visitOverloaded(overloaded: BetaOverloadedError) =
                    overloaded.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaError && invalidRequest == other.invalidRequest && authentication == other.authentication && billing == other.billing && permission == other.permission && notFound == other.notFound && rateLimit == other.rateLimit && timeout == other.timeout && api == other.api && overloaded == other.overloaded /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(invalidRequest, authentication, billing, permission, notFound, rateLimit, timeout, api, overloaded) /* spotless:on */

    override fun toString(): String =
        when {
            invalidRequest != null -> "BetaError{invalidRequest=$invalidRequest}"
            authentication != null -> "BetaError{authentication=$authentication}"
            billing != null -> "BetaError{billing=$billing}"
            permission != null -> "BetaError{permission=$permission}"
            notFound != null -> "BetaError{notFound=$notFound}"
            rateLimit != null -> "BetaError{rateLimit=$rateLimit}"
            timeout != null -> "BetaError{timeout=$timeout}"
            api != null -> "BetaError{api=$api}"
            overloaded != null -> "BetaError{overloaded=$overloaded}"
            _json != null -> "BetaError{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaError")
        }

    companion object {

        @JvmStatic
        fun ofInvalidRequest(invalidRequest: BetaInvalidRequestError) =
            BetaError(invalidRequest = invalidRequest)

        @JvmStatic
        fun ofAuthentication(authentication: BetaAuthenticationError) =
            BetaError(authentication = authentication)

        @JvmStatic fun ofBilling(billing: BetaBillingError) = BetaError(billing = billing)

        @JvmStatic
        fun ofPermission(permission: BetaPermissionError) = BetaError(permission = permission)

        @JvmStatic fun ofNotFound(notFound: BetaNotFoundError) = BetaError(notFound = notFound)

        @JvmStatic fun ofRateLimit(rateLimit: BetaRateLimitError) = BetaError(rateLimit = rateLimit)

        @JvmStatic fun ofTimeout(timeout: BetaGatewayTimeoutError) = BetaError(timeout = timeout)

        @JvmStatic fun ofApi(api: BetaApiError) = BetaError(api = api)

        @JvmStatic
        fun ofOverloaded(overloaded: BetaOverloadedError) = BetaError(overloaded = overloaded)
    }

    /** An interface that defines how to map each variant of [BetaError] to a value of type [T]. */
    interface Visitor<out T> {

        fun visitInvalidRequest(invalidRequest: BetaInvalidRequestError): T

        fun visitAuthentication(authentication: BetaAuthenticationError): T

        fun visitBilling(billing: BetaBillingError): T

        fun visitPermission(permission: BetaPermissionError): T

        fun visitNotFound(notFound: BetaNotFoundError): T

        fun visitRateLimit(rateLimit: BetaRateLimitError): T

        fun visitTimeout(timeout: BetaGatewayTimeoutError): T

        fun visitApi(api: BetaApiError): T

        fun visitOverloaded(overloaded: BetaOverloadedError): T

        /**
         * Maps an unknown variant of [BetaError] to a value of type [T].
         *
         * An instance of [BetaError] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaError: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaError>(BetaError::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaError {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "invalid_request_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaInvalidRequestError>())?.let {
                        BetaError(invalidRequest = it, _json = json)
                    } ?: BetaError(_json = json)
                }
                "authentication_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaAuthenticationError>())?.let {
                        BetaError(authentication = it, _json = json)
                    } ?: BetaError(_json = json)
                }
                "billing_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBillingError>())?.let {
                        BetaError(billing = it, _json = json)
                    } ?: BetaError(_json = json)
                }
                "permission_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaPermissionError>())?.let {
                        BetaError(permission = it, _json = json)
                    } ?: BetaError(_json = json)
                }
                "not_found_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaNotFoundError>())?.let {
                        BetaError(notFound = it, _json = json)
                    } ?: BetaError(_json = json)
                }
                "rate_limit_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRateLimitError>())?.let {
                        BetaError(rateLimit = it, _json = json)
                    } ?: BetaError(_json = json)
                }
                "timeout_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaGatewayTimeoutError>())?.let {
                        BetaError(timeout = it, _json = json)
                    } ?: BetaError(_json = json)
                }
                "api_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaApiError>())?.let {
                        BetaError(api = it, _json = json)
                    } ?: BetaError(_json = json)
                }
                "overloaded_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaOverloadedError>())?.let {
                        BetaError(overloaded = it, _json = json)
                    } ?: BetaError(_json = json)
                }
            }

            return BetaError(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaError>(BetaError::class) {

        override fun serialize(
            value: BetaError,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.invalidRequest != null -> generator.writeObject(value.invalidRequest)
                value.authentication != null -> generator.writeObject(value.authentication)
                value.billing != null -> generator.writeObject(value.billing)
                value.permission != null -> generator.writeObject(value.permission)
                value.notFound != null -> generator.writeObject(value.notFound)
                value.rateLimit != null -> generator.writeObject(value.rateLimit)
                value.timeout != null -> generator.writeObject(value.timeout)
                value.api != null -> generator.writeObject(value.api)
                value.overloaded != null -> generator.writeObject(value.overloaded)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaError")
            }
        }
    }
}
