package com.anthropic.models.beta.compliance.review.items

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

/** Who sent a message. */
@JsonDeserialize(using = BetaComplianceReviewActor.Deserializer::class)
@JsonSerialize(using = BetaComplianceReviewActor.Serializer::class)
class BetaComplianceReviewActor
private constructor(
    private val user: BetaComplianceReviewUserActor? = null,
    private val api: BetaComplianceReviewApiActor? = null,
    private val serviceAccount: BetaComplianceReviewServiceAccountActor? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            user != null -> Type.USER_ACTOR
            api != null -> Type.API_ACTOR
            serviceAccount != null -> Type.SERVICE_ACCOUNT_ACTOR
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** A signed-in user. */
    fun user(): Optional<BetaComplianceReviewUserActor> = Optional.ofNullable(user)

    /** An API key. */
    fun api(): Optional<BetaComplianceReviewApiActor> = Optional.ofNullable(api)

    /** A service account. */
    fun serviceAccount(): Optional<BetaComplianceReviewServiceAccountActor> =
        Optional.ofNullable(serviceAccount)

    fun isUser(): Boolean = user != null

    fun isApi(): Boolean = api != null

    fun isServiceAccount(): Boolean = serviceAccount != null

    /** A signed-in user. */
    fun asUser(): BetaComplianceReviewUserActor = user.getOrThrow("user")

    /** An API key. */
    fun asApi(): BetaComplianceReviewApiActor = api.getOrThrow("api")

    /** A service account. */
    fun asServiceAccount(): BetaComplianceReviewServiceAccountActor =
        serviceAccount.getOrThrow("serviceAccount")

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
     * Optional<String> result = betaComplianceReviewActor.accept(new BetaComplianceReviewActor.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitUser(BetaComplianceReviewUserActor user) {
     *         return Optional.of(user.toString());
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
            user != null -> visitor.visitUser(user)
            api != null -> visitor.visitApi(api)
            serviceAccount != null -> visitor.visitServiceAccount(serviceAccount)
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
    fun validate(): BetaComplianceReviewActor = apply {
        if (validated) {
            return@apply
        }

        when {
            user != null -> user.validate()
            api != null -> api.validate()
            serviceAccount != null -> serviceAccount.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaComplianceReviewActor: $_json")
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
        when {
            user != null -> user.validity()
            api != null -> api.validity()
            serviceAccount != null -> serviceAccount.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewActor &&
            user == other.user &&
            api == other.api &&
            serviceAccount == other.serviceAccount
    }

    override fun hashCode(): Int = Objects.hash(user, api, serviceAccount)

    override fun toString(): String =
        when {
            user != null -> "BetaComplianceReviewActor{user=$user}"
            api != null -> "BetaComplianceReviewActor{api=$api}"
            serviceAccount != null -> "BetaComplianceReviewActor{serviceAccount=$serviceAccount}"
            _json != null -> "BetaComplianceReviewActor{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaComplianceReviewActor")
        }

    companion object {

        /** A signed-in user. */
        @JvmStatic
        fun ofUser(user: BetaComplianceReviewUserActor) = BetaComplianceReviewActor(user = user)

        /** An API key. */
        @JvmStatic
        fun ofApi(api: BetaComplianceReviewApiActor) = BetaComplianceReviewActor(api = api)

        /**
         * Returns an immutable instance of [BetaComplianceReviewActor] whose [ofApi] variant is
         * built from the given required [apiKeyId].
         */
        @JvmStatic fun ofApi(apiKeyId: String) = ofApi(BetaComplianceReviewApiActor.of(apiKeyId))

        /** A service account. */
        @JvmStatic
        fun ofServiceAccount(serviceAccount: BetaComplianceReviewServiceAccountActor) =
            BetaComplianceReviewActor(serviceAccount = serviceAccount)

        /**
         * Returns an immutable instance of [BetaComplianceReviewActor] whose [ofServiceAccount]
         * variant is built from the given required [serviceAccountId].
         */
        @JvmStatic
        fun ofServiceAccount(serviceAccountId: String) =
            ofServiceAccount(BetaComplianceReviewServiceAccountActor.of(serviceAccountId))
    }

    /**
     * An interface that defines how to map each variant of [BetaComplianceReviewActor] to a value
     * of type [T].
     */
    interface Visitor<out T> {

        /** A signed-in user. */
        fun visitUser(user: BetaComplianceReviewUserActor): T

        /** An API key. */
        fun visitApi(api: BetaComplianceReviewApiActor): T

        /** A service account. */
        fun visitServiceAccount(serviceAccount: BetaComplianceReviewServiceAccountActor): T

        /**
         * Maps an unknown variant of [BetaComplianceReviewActor] to a value of type [T].
         *
         * An instance of [BetaComplianceReviewActor] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaComplianceReviewActor: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaComplianceReviewActor>(BetaComplianceReviewActor::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaComplianceReviewActor {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "user_actor" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComplianceReviewUserActor>())
                        ?.let { BetaComplianceReviewActor(user = it, _json = json) }
                        ?: BetaComplianceReviewActor(_json = json)
                }
                "api_actor" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComplianceReviewApiActor>())
                        ?.let { BetaComplianceReviewActor(api = it, _json = json) }
                        ?: BetaComplianceReviewActor(_json = json)
                }
                "service_account_actor" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComplianceReviewServiceAccountActor>(),
                        )
                        ?.let { BetaComplianceReviewActor(serviceAccount = it, _json = json) }
                        ?: BetaComplianceReviewActor(_json = json)
                }
            }

            return BetaComplianceReviewActor(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaComplianceReviewActor>(BetaComplianceReviewActor::class) {

        override fun serialize(
            value: BetaComplianceReviewActor,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.user != null -> generator.writeObject(value.user)
                value.api != null -> generator.writeObject(value.api)
                value.serviceAccount != null -> generator.writeObject(value.serviceAccount)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaComplianceReviewActor")
            }
        }
    }

    class Type private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val USER_ACTOR = Type(JsonField.of("user_actor"))

            @JvmField val API_ACTOR = Type(JsonField.of("api_actor"))

            @JvmField val SERVICE_ACCOUNT_ACTOR = Type(JsonField.of("service_account_actor"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "user_actor" -> USER_ACTOR
                    "api_actor" -> API_ACTOR
                    "service_account_actor" -> SERVICE_ACCOUNT_ACTOR
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            USER_ACTOR,
            API_ACTOR,
            SERVICE_ACCOUNT_ACTOR,
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
            USER_ACTOR,
            API_ACTOR,
            SERVICE_ACCOUNT_ACTOR,
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
                USER_ACTOR -> Value.USER_ACTOR
                API_ACTOR -> Value.API_ACTOR
                SERVICE_ACCOUNT_ACTOR -> Value.SERVICE_ACCOUNT_ACTOR
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
                USER_ACTOR -> Known.USER_ACTOR
                API_ACTOR -> Known.API_ACTOR
                SERVICE_ACCOUNT_ACTOR -> Known.SERVICE_ACCOUNT_ACTOR
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
