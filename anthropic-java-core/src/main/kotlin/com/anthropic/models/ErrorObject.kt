// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitInvalidRequestError(
                    invalidRequestError: InvalidRequestError
                ): Type = Type.INVALID_REQUEST_ERROR

                override fun visitAuthenticationError(
                    authenticationError: AuthenticationError
                ): Type = Type.AUTHENTICATION_ERROR

                override fun visitBillingError(billingError: BillingError): Type =
                    Type.BILLING_ERROR

                override fun visitPermissionError(permissionError: PermissionError): Type =
                    Type.PERMISSION_ERROR

                override fun visitNotFoundError(notFoundError: NotFoundError): Type =
                    Type.NOT_FOUND_ERROR

                override fun visitRateLimitError(rateLimitError: RateLimitError): Type =
                    Type.RATE_LIMIT_ERROR

                override fun visitTimeoutError(timeoutError: GatewayTimeoutError): Type =
                    Type.TIMEOUT_ERROR

                override fun visitApiError(apiError: ApiErrorObject): Type = Type.API_ERROR

                override fun visitOverloadedError(overloadedError: OverloadedError): Type =
                    Type.OVERLOADED_ERROR

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun message(): String =
        accept(
            object : Visitor<String> {
                override fun visitInvalidRequestError(
                    invalidRequestError: InvalidRequestError
                ): String = invalidRequestError.message()

                override fun visitAuthenticationError(
                    authenticationError: AuthenticationError
                ): String = authenticationError.message()

                override fun visitBillingError(billingError: BillingError): String =
                    billingError.message()

                override fun visitPermissionError(permissionError: PermissionError): String =
                    permissionError.message()

                override fun visitNotFoundError(notFoundError: NotFoundError): String =
                    notFoundError.message()

                override fun visitRateLimitError(rateLimitError: RateLimitError): String =
                    rateLimitError.message()

                override fun visitTimeoutError(timeoutError: GatewayTimeoutError): String =
                    timeoutError.message()

                override fun visitApiError(apiError: ApiErrorObject): String = apiError.message()

                override fun visitOverloadedError(overloadedError: OverloadedError): String =
                    overloadedError.message()

                override fun unknown(json: JsonValue?): String =
                    json.getProperty<String>("message").getRequired("message")
            }
        )

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
     * Optional<String> result = errorObject.accept(new ErrorObject.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitInvalidRequestError(InvalidRequestError invalidRequestError) {
     *         return Optional.of(invalidRequestError.toString());
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

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
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

        return other is ErrorObject &&
            invalidRequestError == other.invalidRequestError &&
            authenticationError == other.authenticationError &&
            billingError == other.billingError &&
            permissionError == other.permissionError &&
            notFoundError == other.notFoundError &&
            rateLimitError == other.rateLimitError &&
            timeoutError == other.timeoutError &&
            apiError == other.apiError &&
            overloadedError == other.overloadedError
    }

    override fun hashCode(): Int =
        Objects.hash(
            invalidRequestError,
            authenticationError,
            billingError,
            permissionError,
            notFoundError,
            rateLimitError,
            timeoutError,
            apiError,
            overloadedError,
        )

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

        /**
         * Returns an immutable instance of [ErrorObject] whose [ofInvalidRequestError] variant is
         * built from the given required [message].
         */
        @JvmStatic
        fun ofInvalidRequestError(message: String) =
            ofInvalidRequestError(InvalidRequestError.of(message))

        @JvmStatic
        fun ofAuthenticationError(authenticationError: AuthenticationError) =
            ErrorObject(authenticationError = authenticationError)

        /**
         * Returns an immutable instance of [ErrorObject] whose [ofAuthenticationError] variant is
         * built from the given required [message].
         */
        @JvmStatic
        fun ofAuthenticationError(message: String) =
            ofAuthenticationError(AuthenticationError.of(message))

        @JvmStatic
        fun ofBillingError(billingError: BillingError) = ErrorObject(billingError = billingError)

        /**
         * Returns an immutable instance of [ErrorObject] whose [ofBillingError] variant is built
         * from the given required [message].
         */
        @JvmStatic fun ofBillingError(message: String) = ofBillingError(BillingError.of(message))

        @JvmStatic
        fun ofPermissionError(permissionError: PermissionError) =
            ErrorObject(permissionError = permissionError)

        /**
         * Returns an immutable instance of [ErrorObject] whose [ofPermissionError] variant is built
         * from the given required [message].
         */
        @JvmStatic
        fun ofPermissionError(message: String) = ofPermissionError(PermissionError.of(message))

        @JvmStatic
        fun ofNotFoundError(notFoundError: NotFoundError) =
            ErrorObject(notFoundError = notFoundError)

        /**
         * Returns an immutable instance of [ErrorObject] whose [ofNotFoundError] variant is built
         * from the given required [message].
         */
        @JvmStatic fun ofNotFoundError(message: String) = ofNotFoundError(NotFoundError.of(message))

        @JvmStatic
        fun ofRateLimitError(rateLimitError: RateLimitError) =
            ErrorObject(rateLimitError = rateLimitError)

        /**
         * Returns an immutable instance of [ErrorObject] whose [ofRateLimitError] variant is built
         * from the given required [message].
         */
        @JvmStatic
        fun ofRateLimitError(message: String) = ofRateLimitError(RateLimitError.of(message))

        @JvmStatic
        fun ofTimeoutError(timeoutError: GatewayTimeoutError) =
            ErrorObject(timeoutError = timeoutError)

        /**
         * Returns an immutable instance of [ErrorObject] whose [ofTimeoutError] variant is built
         * from the given required [message].
         */
        @JvmStatic
        fun ofTimeoutError(message: String) = ofTimeoutError(GatewayTimeoutError.of(message))

        @JvmStatic fun ofApiError(apiError: ApiErrorObject) = ErrorObject(apiError = apiError)

        /**
         * Returns an immutable instance of [ErrorObject] whose [ofApiError] variant is built from
         * the given required [message].
         */
        @JvmStatic fun ofApiError(message: String) = ofApiError(ApiErrorObject.of(message))

        @JvmStatic
        fun ofOverloadedError(overloadedError: OverloadedError) =
            ErrorObject(overloadedError = overloadedError)

        /**
         * Returns an immutable instance of [ErrorObject] whose [ofOverloadedError] variant is built
         * from the given required [message].
         */
        @JvmStatic
        fun ofOverloadedError(message: String) = ofOverloadedError(OverloadedError.of(message))
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
