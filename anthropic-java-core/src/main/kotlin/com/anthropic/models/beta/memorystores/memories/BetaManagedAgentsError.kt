// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.BetaApiError
import com.anthropic.models.beta.BetaAuthenticationError
import com.anthropic.models.beta.BetaBillingError
import com.anthropic.models.beta.BetaGatewayTimeoutError
import com.anthropic.models.beta.BetaInvalidRequestError
import com.anthropic.models.beta.BetaNotFoundError
import com.anthropic.models.beta.BetaOverloadedError
import com.anthropic.models.beta.BetaPermissionError
import com.anthropic.models.beta.BetaRateLimitError
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

@JsonDeserialize(using = BetaManagedAgentsError.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsError.Serializer::class)
class BetaManagedAgentsError
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
    private val memoryPreconditionFailed: BetaManagedAgentsMemoryPreconditionFailedError? = null,
    private val memoryPathConflict: BetaManagedAgentsMemoryPathConflictError? = null,
    private val conflict: BetaManagedAgentsConflictError? = null,
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

                override fun visitMemoryPreconditionFailed(
                    memoryPreconditionFailed: BetaManagedAgentsMemoryPreconditionFailedError
                ): Type = Type.MEMORY_PRECONDITION_FAILED_ERROR

                override fun visitMemoryPathConflict(
                    memoryPathConflict: BetaManagedAgentsMemoryPathConflictError
                ): Type = Type.MEMORY_PATH_CONFLICT_ERROR

                override fun visitConflict(conflict: BetaManagedAgentsConflictError): Type =
                    Type.CONFLICT_ERROR

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun message(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitInvalidRequest(
                    invalidRequest: BetaInvalidRequestError
                ): Optional<String> = Optional.of(invalidRequest.message())

                override fun visitAuthentication(
                    authentication: BetaAuthenticationError
                ): Optional<String> = Optional.of(authentication.message())

                override fun visitBilling(billing: BetaBillingError): Optional<String> =
                    Optional.of(billing.message())

                override fun visitPermission(permission: BetaPermissionError): Optional<String> =
                    Optional.of(permission.message())

                override fun visitNotFound(notFound: BetaNotFoundError): Optional<String> =
                    Optional.of(notFound.message())

                override fun visitRateLimit(rateLimit: BetaRateLimitError): Optional<String> =
                    Optional.of(rateLimit.message())

                override fun visitTimeout(timeout: BetaGatewayTimeoutError): Optional<String> =
                    Optional.of(timeout.message())

                override fun visitApi(api: BetaApiError): Optional<String> =
                    Optional.of(api.message())

                override fun visitOverloaded(overloaded: BetaOverloadedError): Optional<String> =
                    Optional.of(overloaded.message())

                override fun visitMemoryPreconditionFailed(
                    memoryPreconditionFailed: BetaManagedAgentsMemoryPreconditionFailedError
                ): Optional<String> = memoryPreconditionFailed.message()

                override fun visitMemoryPathConflict(
                    memoryPathConflict: BetaManagedAgentsMemoryPathConflictError
                ): Optional<String> = memoryPathConflict.message()

                override fun visitConflict(
                    conflict: BetaManagedAgentsConflictError
                ): Optional<String> = conflict.message()
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

    fun memoryPreconditionFailed(): Optional<BetaManagedAgentsMemoryPreconditionFailedError> =
        Optional.ofNullable(memoryPreconditionFailed)

    fun memoryPathConflict(): Optional<BetaManagedAgentsMemoryPathConflictError> =
        Optional.ofNullable(memoryPathConflict)

    fun conflict(): Optional<BetaManagedAgentsConflictError> = Optional.ofNullable(conflict)

    fun isInvalidRequest(): Boolean = invalidRequest != null

    fun isAuthentication(): Boolean = authentication != null

    fun isBilling(): Boolean = billing != null

    fun isPermission(): Boolean = permission != null

    fun isNotFound(): Boolean = notFound != null

    fun isRateLimit(): Boolean = rateLimit != null

    fun isTimeout(): Boolean = timeout != null

    fun isApi(): Boolean = api != null

    fun isOverloaded(): Boolean = overloaded != null

    fun isMemoryPreconditionFailed(): Boolean = memoryPreconditionFailed != null

    fun isMemoryPathConflict(): Boolean = memoryPathConflict != null

    fun isConflict(): Boolean = conflict != null

    fun asInvalidRequest(): BetaInvalidRequestError = invalidRequest.getOrThrow("invalidRequest")

    fun asAuthentication(): BetaAuthenticationError = authentication.getOrThrow("authentication")

    fun asBilling(): BetaBillingError = billing.getOrThrow("billing")

    fun asPermission(): BetaPermissionError = permission.getOrThrow("permission")

    fun asNotFound(): BetaNotFoundError = notFound.getOrThrow("notFound")

    fun asRateLimit(): BetaRateLimitError = rateLimit.getOrThrow("rateLimit")

    fun asTimeout(): BetaGatewayTimeoutError = timeout.getOrThrow("timeout")

    fun asApi(): BetaApiError = api.getOrThrow("api")

    fun asOverloaded(): BetaOverloadedError = overloaded.getOrThrow("overloaded")

    fun asMemoryPreconditionFailed(): BetaManagedAgentsMemoryPreconditionFailedError =
        memoryPreconditionFailed.getOrThrow("memoryPreconditionFailed")

    fun asMemoryPathConflict(): BetaManagedAgentsMemoryPathConflictError =
        memoryPathConflict.getOrThrow("memoryPathConflict")

    fun asConflict(): BetaManagedAgentsConflictError = conflict.getOrThrow("conflict")

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
     * Optional<String> result = betaManagedAgentsError.accept(new BetaManagedAgentsError.Visitor<Optional<String>>() {
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
            memoryPreconditionFailed != null ->
                visitor.visitMemoryPreconditionFailed(memoryPreconditionFailed)
            memoryPathConflict != null -> visitor.visitMemoryPathConflict(memoryPathConflict)
            conflict != null -> visitor.visitConflict(conflict)
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
    fun validate(): BetaManagedAgentsError = apply {
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

                override fun visitMemoryPreconditionFailed(
                    memoryPreconditionFailed: BetaManagedAgentsMemoryPreconditionFailedError
                ) {
                    memoryPreconditionFailed.validate()
                }

                override fun visitMemoryPathConflict(
                    memoryPathConflict: BetaManagedAgentsMemoryPathConflictError
                ) {
                    memoryPathConflict.validate()
                }

                override fun visitConflict(conflict: BetaManagedAgentsConflictError) {
                    conflict.validate()
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

                override fun visitMemoryPreconditionFailed(
                    memoryPreconditionFailed: BetaManagedAgentsMemoryPreconditionFailedError
                ) = memoryPreconditionFailed.validity()

                override fun visitMemoryPathConflict(
                    memoryPathConflict: BetaManagedAgentsMemoryPathConflictError
                ) = memoryPathConflict.validity()

                override fun visitConflict(conflict: BetaManagedAgentsConflictError) =
                    conflict.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsError &&
            invalidRequest == other.invalidRequest &&
            authentication == other.authentication &&
            billing == other.billing &&
            permission == other.permission &&
            notFound == other.notFound &&
            rateLimit == other.rateLimit &&
            timeout == other.timeout &&
            api == other.api &&
            overloaded == other.overloaded &&
            memoryPreconditionFailed == other.memoryPreconditionFailed &&
            memoryPathConflict == other.memoryPathConflict &&
            conflict == other.conflict
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
            memoryPreconditionFailed,
            memoryPathConflict,
            conflict,
        )

    override fun toString(): String =
        when {
            invalidRequest != null -> "BetaManagedAgentsError{invalidRequest=$invalidRequest}"
            authentication != null -> "BetaManagedAgentsError{authentication=$authentication}"
            billing != null -> "BetaManagedAgentsError{billing=$billing}"
            permission != null -> "BetaManagedAgentsError{permission=$permission}"
            notFound != null -> "BetaManagedAgentsError{notFound=$notFound}"
            rateLimit != null -> "BetaManagedAgentsError{rateLimit=$rateLimit}"
            timeout != null -> "BetaManagedAgentsError{timeout=$timeout}"
            api != null -> "BetaManagedAgentsError{api=$api}"
            overloaded != null -> "BetaManagedAgentsError{overloaded=$overloaded}"
            memoryPreconditionFailed != null ->
                "BetaManagedAgentsError{memoryPreconditionFailed=$memoryPreconditionFailed}"
            memoryPathConflict != null ->
                "BetaManagedAgentsError{memoryPathConflict=$memoryPathConflict}"
            conflict != null -> "BetaManagedAgentsError{conflict=$conflict}"
            _json != null -> "BetaManagedAgentsError{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsError")
        }

    companion object {

        @JvmStatic
        fun ofInvalidRequest(invalidRequest: BetaInvalidRequestError) =
            BetaManagedAgentsError(invalidRequest = invalidRequest)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose [ofInvalidRequest]
         * variant is built from the given required [message].
         */
        @JvmStatic
        fun ofInvalidRequest(message: String) =
            ofInvalidRequest(BetaInvalidRequestError.of(message))

        @JvmStatic
        fun ofAuthentication(authentication: BetaAuthenticationError) =
            BetaManagedAgentsError(authentication = authentication)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose [ofAuthentication]
         * variant is built from the given required [message].
         */
        @JvmStatic
        fun ofAuthentication(message: String) =
            ofAuthentication(BetaAuthenticationError.of(message))

        @JvmStatic
        fun ofBilling(billing: BetaBillingError) = BetaManagedAgentsError(billing = billing)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose [ofBilling] variant is
         * built from the given required [message].
         */
        @JvmStatic fun ofBilling(message: String) = ofBilling(BetaBillingError.of(message))

        @JvmStatic
        fun ofPermission(permission: BetaPermissionError) =
            BetaManagedAgentsError(permission = permission)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose [ofPermission] variant is
         * built from the given required [message].
         */
        @JvmStatic fun ofPermission(message: String) = ofPermission(BetaPermissionError.of(message))

        @JvmStatic
        fun ofNotFound(notFound: BetaNotFoundError) = BetaManagedAgentsError(notFound = notFound)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose [ofNotFound] variant is
         * built from the given required [message].
         */
        @JvmStatic fun ofNotFound(message: String) = ofNotFound(BetaNotFoundError.of(message))

        @JvmStatic
        fun ofRateLimit(rateLimit: BetaRateLimitError) =
            BetaManagedAgentsError(rateLimit = rateLimit)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose [ofRateLimit] variant is
         * built from the given required [message].
         */
        @JvmStatic fun ofRateLimit(message: String) = ofRateLimit(BetaRateLimitError.of(message))

        @JvmStatic
        fun ofTimeout(timeout: BetaGatewayTimeoutError) = BetaManagedAgentsError(timeout = timeout)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose [ofTimeout] variant is
         * built from the given required [message].
         */
        @JvmStatic fun ofTimeout(message: String) = ofTimeout(BetaGatewayTimeoutError.of(message))

        @JvmStatic fun ofApi(api: BetaApiError) = BetaManagedAgentsError(api = api)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose [ofApi] variant is built
         * from the given required [message].
         */
        @JvmStatic fun ofApi(message: String) = ofApi(BetaApiError.of(message))

        @JvmStatic
        fun ofOverloaded(overloaded: BetaOverloadedError) =
            BetaManagedAgentsError(overloaded = overloaded)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose [ofOverloaded] variant is
         * built from the given required [message].
         */
        @JvmStatic fun ofOverloaded(message: String) = ofOverloaded(BetaOverloadedError.of(message))

        @JvmStatic
        fun ofMemoryPreconditionFailed(
            memoryPreconditionFailed: BetaManagedAgentsMemoryPreconditionFailedError
        ) = BetaManagedAgentsError(memoryPreconditionFailed = memoryPreconditionFailed)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose
         * [ofMemoryPreconditionFailed] variant is built from the given required [type].
         */
        @JvmStatic
        fun ofMemoryPreconditionFailed(type: BetaManagedAgentsMemoryPreconditionFailedError.Type) =
            ofMemoryPreconditionFailed(BetaManagedAgentsMemoryPreconditionFailedError.of(type))

        @JvmStatic
        fun ofMemoryPathConflict(memoryPathConflict: BetaManagedAgentsMemoryPathConflictError) =
            BetaManagedAgentsError(memoryPathConflict = memoryPathConflict)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose [ofMemoryPathConflict]
         * variant is built from the given required [type].
         */
        @JvmStatic
        fun ofMemoryPathConflict(type: BetaManagedAgentsMemoryPathConflictError.Type) =
            ofMemoryPathConflict(BetaManagedAgentsMemoryPathConflictError.of(type))

        @JvmStatic
        fun ofConflict(conflict: BetaManagedAgentsConflictError) =
            BetaManagedAgentsError(conflict = conflict)

        /**
         * Returns an immutable instance of [BetaManagedAgentsError] whose [ofConflict] variant is
         * built from the given required [type].
         */
        @JvmStatic
        fun ofConflict(type: BetaManagedAgentsConflictError.Type) =
            ofConflict(BetaManagedAgentsConflictError.of(type))
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsError] to a value of
     * type [T].
     */
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

        fun visitMemoryPreconditionFailed(
            memoryPreconditionFailed: BetaManagedAgentsMemoryPreconditionFailedError
        ): T

        fun visitMemoryPathConflict(memoryPathConflict: BetaManagedAgentsMemoryPathConflictError): T

        fun visitConflict(conflict: BetaManagedAgentsConflictError): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsError] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsError] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsError: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsError>(BetaManagedAgentsError::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsError {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "invalid_request_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaInvalidRequestError>())?.let {
                        BetaManagedAgentsError(invalidRequest = it, _json = json)
                    } ?: BetaManagedAgentsError(_json = json)
                }
                "authentication_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaAuthenticationError>())?.let {
                        BetaManagedAgentsError(authentication = it, _json = json)
                    } ?: BetaManagedAgentsError(_json = json)
                }
                "billing_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBillingError>())?.let {
                        BetaManagedAgentsError(billing = it, _json = json)
                    } ?: BetaManagedAgentsError(_json = json)
                }
                "permission_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaPermissionError>())?.let {
                        BetaManagedAgentsError(permission = it, _json = json)
                    } ?: BetaManagedAgentsError(_json = json)
                }
                "not_found_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaNotFoundError>())?.let {
                        BetaManagedAgentsError(notFound = it, _json = json)
                    } ?: BetaManagedAgentsError(_json = json)
                }
                "rate_limit_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRateLimitError>())?.let {
                        BetaManagedAgentsError(rateLimit = it, _json = json)
                    } ?: BetaManagedAgentsError(_json = json)
                }
                "timeout_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaGatewayTimeoutError>())?.let {
                        BetaManagedAgentsError(timeout = it, _json = json)
                    } ?: BetaManagedAgentsError(_json = json)
                }
                "api_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaApiError>())?.let {
                        BetaManagedAgentsError(api = it, _json = json)
                    } ?: BetaManagedAgentsError(_json = json)
                }
                "overloaded_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaOverloadedError>())?.let {
                        BetaManagedAgentsError(overloaded = it, _json = json)
                    } ?: BetaManagedAgentsError(_json = json)
                }
                "memory_precondition_failed_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsMemoryPreconditionFailedError>(),
                        )
                        ?.let {
                            BetaManagedAgentsError(memoryPreconditionFailed = it, _json = json)
                        } ?: BetaManagedAgentsError(_json = json)
                }
                "memory_path_conflict_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsMemoryPathConflictError>(),
                        )
                        ?.let { BetaManagedAgentsError(memoryPathConflict = it, _json = json) }
                        ?: BetaManagedAgentsError(_json = json)
                }
                "conflict_error" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsConflictError>())
                        ?.let { BetaManagedAgentsError(conflict = it, _json = json) }
                        ?: BetaManagedAgentsError(_json = json)
                }
            }

            return BetaManagedAgentsError(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsError>(BetaManagedAgentsError::class) {

        override fun serialize(
            value: BetaManagedAgentsError,
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
                value.memoryPreconditionFailed != null ->
                    generator.writeObject(value.memoryPreconditionFailed)
                value.memoryPathConflict != null -> generator.writeObject(value.memoryPathConflict)
                value.conflict != null -> generator.writeObject(value.conflict)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsError")
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

            @JvmField val MEMORY_PRECONDITION_FAILED_ERROR = of("memory_precondition_failed_error")

            @JvmField val MEMORY_PATH_CONFLICT_ERROR = of("memory_path_conflict_error")

            @JvmField val CONFLICT_ERROR = of("conflict_error")

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
            MEMORY_PRECONDITION_FAILED_ERROR,
            MEMORY_PATH_CONFLICT_ERROR,
            CONFLICT_ERROR,
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
            MEMORY_PRECONDITION_FAILED_ERROR,
            MEMORY_PATH_CONFLICT_ERROR,
            CONFLICT_ERROR,
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
                MEMORY_PRECONDITION_FAILED_ERROR -> Value.MEMORY_PRECONDITION_FAILED_ERROR
                MEMORY_PATH_CONFLICT_ERROR -> Value.MEMORY_PATH_CONFLICT_ERROR
                CONFLICT_ERROR -> Value.CONFLICT_ERROR
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
                MEMORY_PRECONDITION_FAILED_ERROR -> Known.MEMORY_PRECONDITION_FAILED_ERROR
                MEMORY_PATH_CONFLICT_ERROR -> Known.MEMORY_PATH_CONFLICT_ERROR
                CONFLICT_ERROR -> Known.CONFLICT_ERROR
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
