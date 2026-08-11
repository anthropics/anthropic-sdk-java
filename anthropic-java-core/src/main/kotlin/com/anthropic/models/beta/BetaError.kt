// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
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

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitInvalidRequest(invalidRequest: BetaInvalidRequestError): Type =
                    Type.INVALID_REQUEST_ERROR

                override fun visitAuthentication(authentication: BetaAuthenticationError): Type =
                    Type.AUTHENTICATION_ERROR

                override fun visitBilling(billing: BetaBillingError): Type = Type.BILLING_ERROR

                override fun visitPermission(permission: BetaPermissionError): Type =
                    Type.PERMISSION_ERROR

                override fun visitNotFound(notFound: BetaNotFoundError): Type = Type.NOT_FOUND_ERROR

                override fun visitRateLimit(rateLimit: BetaRateLimitError): Type =
                    Type.RATE_LIMIT_ERROR

                override fun visitTimeout(timeout: BetaGatewayTimeoutError): Type =
                    Type.TIMEOUT_ERROR

                override fun visitApi(api: BetaApiError): Type = Type.API_ERROR

                override fun visitOverloaded(overloaded: BetaOverloadedError): Type =
                    Type.OVERLOADED_ERROR

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun message(): String =
        accept(
            object : Visitor<String> {
                override fun visitInvalidRequest(invalidRequest: BetaInvalidRequestError): String =
                    invalidRequest.message()

                override fun visitAuthentication(authentication: BetaAuthenticationError): String =
                    authentication.message()

                override fun visitBilling(billing: BetaBillingError): String = billing.message()

                override fun visitPermission(permission: BetaPermissionError): String =
                    permission.message()

                override fun visitNotFound(notFound: BetaNotFoundError): String = notFound.message()

                override fun visitRateLimit(rateLimit: BetaRateLimitError): String =
                    rateLimit.message()

                override fun visitTimeout(timeout: BetaGatewayTimeoutError): String =
                    timeout.message()

                override fun visitApi(api: BetaApiError): String = api.message()

                override fun visitOverloaded(overloaded: BetaOverloadedError): String =
                    overloaded.message()
            }
        )

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

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaError.accept(new BetaError.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitInvalidRequest(BetaInvalidRequestError invalidRequest) {
     *         return Optional.of(invalidRequest.toString());
     *     }
     *
     *     // ...
     *
     *     @Override
     *     public Optional<String> unknown(JsonValue json) {
     *         // Or inspect the `json`.
     *         return Optional.empty();
     *     }
     * });
     * ```
     *
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
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

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
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

        return other is BetaError &&
            invalidRequest == other.invalidRequest &&
            authentication == other.authentication &&
            billing == other.billing &&
            permission == other.permission &&
            notFound == other.notFound &&
            rateLimit == other.rateLimit &&
            timeout == other.timeout &&
            api == other.api &&
            overloaded == other.overloaded
    }

    override fun hashCode(): Int =
        Objects.hash(
            invalidRequest,
            authentication,
            billing,
            permission,
            notFound,
            rateLimit,
            timeout,
            api,
            overloaded,
        )

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

        /**
         * Returns an immutable instance of [BetaError] whose [ofInvalidRequest] variant is built
         * from the given required [message].
         */
        @JvmStatic
        fun ofInvalidRequest(message: String) =
            ofInvalidRequest(BetaInvalidRequestError.of(message))

        @JvmStatic
        fun ofAuthentication(authentication: BetaAuthenticationError) =
            BetaError(authentication = authentication)

        /**
         * Returns an immutable instance of [BetaError] whose [ofAuthentication] variant is built
         * from the given required [message].
         */
        @JvmStatic
        fun ofAuthentication(message: String) =
            ofAuthentication(BetaAuthenticationError.of(message))

        @JvmStatic fun ofBilling(billing: BetaBillingError) = BetaError(billing = billing)

        /**
         * Returns an immutable instance of [BetaError] whose [ofBilling] variant is built from the
         * given required [message].
         */
        @JvmStatic fun ofBilling(message: String) = ofBilling(BetaBillingError.of(message))

        @JvmStatic
        fun ofPermission(permission: BetaPermissionError) = BetaError(permission = permission)

        /**
         * Returns an immutable instance of [BetaError] whose [ofPermission] variant is built from
         * the given required [message].
         */
        @JvmStatic fun ofPermission(message: String) = ofPermission(BetaPermissionError.of(message))

        @JvmStatic fun ofNotFound(notFound: BetaNotFoundError) = BetaError(notFound = notFound)

        /**
         * Returns an immutable instance of [BetaError] whose [ofNotFound] variant is built from the
         * given required [message].
         */
        @JvmStatic fun ofNotFound(message: String) = ofNotFound(BetaNotFoundError.of(message))

        @JvmStatic fun ofRateLimit(rateLimit: BetaRateLimitError) = BetaError(rateLimit = rateLimit)

        /**
         * Returns an immutable instance of [BetaError] whose [ofRateLimit] variant is built from
         * the given required [message].
         */
        @JvmStatic fun ofRateLimit(message: String) = ofRateLimit(BetaRateLimitError.of(message))

        @JvmStatic fun ofTimeout(timeout: BetaGatewayTimeoutError) = BetaError(timeout = timeout)

        /**
         * Returns an immutable instance of [BetaError] whose [ofTimeout] variant is built from the
         * given required [message].
         */
        @JvmStatic fun ofTimeout(message: String) = ofTimeout(BetaGatewayTimeoutError.of(message))

        @JvmStatic fun ofApi(api: BetaApiError) = BetaError(api = api)

        /**
         * Returns an immutable instance of [BetaError] whose [ofApi] variant is built from the
         * given required [message].
         */
        @JvmStatic fun ofApi(message: String) = ofApi(BetaApiError.of(message))

        @JvmStatic
        fun ofOverloaded(overloaded: BetaOverloadedError) = BetaError(overloaded = overloaded)

        /**
         * Returns an immutable instance of [BetaError] whose [ofOverloaded] variant is built from
         * the given required [message].
         */
        @JvmStatic fun ofOverloaded(message: String) = ofOverloaded(BetaOverloadedError.of(message))
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

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val INVALID_REQUEST_ERROR = of("invalid_request_error")

            @JvmField val AUTHENTICATION_ERROR = of("authentication_error")

            @JvmField val BILLING_ERROR = of("billing_error")

            @JvmField val PERMISSION_ERROR = of("permission_error")

            @JvmField val NOT_FOUND_ERROR = of("not_found_error")

            @JvmField val RATE_LIMIT_ERROR = of("rate_limit_error")

            @JvmField val TIMEOUT_ERROR = of("timeout_error")

            @JvmField val API_ERROR = of("api_error")

            @JvmField val OVERLOADED_ERROR = of("overloaded_error")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            INVALID_REQUEST_ERROR,
            AUTHENTICATION_ERROR,
            BILLING_ERROR,
            PERMISSION_ERROR,
            NOT_FOUND_ERROR,
            RATE_LIMIT_ERROR,
            TIMEOUT_ERROR,
            API_ERROR,
            OVERLOADED_ERROR,
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            INVALID_REQUEST_ERROR,
            AUTHENTICATION_ERROR,
            BILLING_ERROR,
            PERMISSION_ERROR,
            NOT_FOUND_ERROR,
            RATE_LIMIT_ERROR,
            TIMEOUT_ERROR,
            API_ERROR,
            OVERLOADED_ERROR,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
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
                INVALID_REQUEST_ERROR -> Value.INVALID_REQUEST_ERROR
                AUTHENTICATION_ERROR -> Value.AUTHENTICATION_ERROR
                BILLING_ERROR -> Value.BILLING_ERROR
                PERMISSION_ERROR -> Value.PERMISSION_ERROR
                NOT_FOUND_ERROR -> Value.NOT_FOUND_ERROR
                RATE_LIMIT_ERROR -> Value.RATE_LIMIT_ERROR
                TIMEOUT_ERROR -> Value.TIMEOUT_ERROR
                API_ERROR -> Value.API_ERROR
                OVERLOADED_ERROR -> Value.OVERLOADED_ERROR
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
                INVALID_REQUEST_ERROR -> Known.INVALID_REQUEST_ERROR
                AUTHENTICATION_ERROR -> Known.AUTHENTICATION_ERROR
                BILLING_ERROR -> Known.BILLING_ERROR
                PERMISSION_ERROR -> Known.PERMISSION_ERROR
                NOT_FOUND_ERROR -> Known.NOT_FOUND_ERROR
                RATE_LIMIT_ERROR -> Known.RATE_LIMIT_ERROR
                TIMEOUT_ERROR -> Known.TIMEOUT_ERROR
                API_ERROR -> Known.API_ERROR
                OVERLOADED_ERROR -> Known.OVERLOADED_ERROR
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
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

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Type = apply {
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

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
