package com.anthropic.models.beta.organization.analytics

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

@JsonDeserialize(using = BetaAnalyticsActor.Deserializer::class)
@JsonSerialize(using = BetaAnalyticsActor.Serializer::class)
class BetaAnalyticsActor
private constructor(
    private val user: BetaAnalyticsReportUserActor? = null,
    private val serviceAccount: BetaAnalyticsServiceAccountActor? = null,
    private val federated: BetaAnalyticsFederatedActor? = null,
    private val api: BetaAnalyticsApiActor? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            user != null -> Type.USER_ACTOR
            serviceAccount != null -> Type.SERVICE_ACCOUNT_ACTOR
            federated != null -> Type.FEDERATED_ACTOR
            api != null -> Type.API_ACTOR
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun user(): Optional<BetaAnalyticsReportUserActor> = Optional.ofNullable(user)

    fun serviceAccount(): Optional<BetaAnalyticsServiceAccountActor> =
        Optional.ofNullable(serviceAccount)

    fun federated(): Optional<BetaAnalyticsFederatedActor> = Optional.ofNullable(federated)

    fun api(): Optional<BetaAnalyticsApiActor> = Optional.ofNullable(api)

    fun isUser(): Boolean = user != null

    fun isServiceAccount(): Boolean = serviceAccount != null

    fun isFederated(): Boolean = federated != null

    fun isApi(): Boolean = api != null

    fun asUser(): BetaAnalyticsReportUserActor = user.getOrThrow("user")

    fun asServiceAccount(): BetaAnalyticsServiceAccountActor =
        serviceAccount.getOrThrow("serviceAccount")

    fun asFederated(): BetaAnalyticsFederatedActor = federated.getOrThrow("federated")

    fun asApi(): BetaAnalyticsApiActor = api.getOrThrow("api")

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
     * Optional<String> result = betaAnalyticsActor.accept(new BetaAnalyticsActor.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitUser(BetaAnalyticsReportUserActor user) {
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
            serviceAccount != null -> visitor.visitServiceAccount(serviceAccount)
            federated != null -> visitor.visitFederated(federated)
            api != null -> visitor.visitApi(api)
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
    fun validate(): BetaAnalyticsActor = apply {
        if (validated) {
            return@apply
        }

        when {
            user != null -> user.validate()
            serviceAccount != null -> serviceAccount.validate()
            federated != null -> federated.validate()
            api != null -> api.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaAnalyticsActor: $_json")
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
            serviceAccount != null -> serviceAccount.validity()
            federated != null -> federated.validity()
            api != null -> api.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsActor &&
            user == other.user &&
            serviceAccount == other.serviceAccount &&
            federated == other.federated &&
            api == other.api
    }

    override fun hashCode(): Int = Objects.hash(user, serviceAccount, federated, api)

    override fun toString(): String =
        when {
            user != null -> "BetaAnalyticsActor{user=$user}"
            serviceAccount != null -> "BetaAnalyticsActor{serviceAccount=$serviceAccount}"
            federated != null -> "BetaAnalyticsActor{federated=$federated}"
            api != null -> "BetaAnalyticsActor{api=$api}"
            _json != null -> "BetaAnalyticsActor{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaAnalyticsActor")
        }

    companion object {

        @JvmStatic fun ofUser(user: BetaAnalyticsReportUserActor) = BetaAnalyticsActor(user = user)

        @JvmStatic
        fun ofServiceAccount(serviceAccount: BetaAnalyticsServiceAccountActor) =
            BetaAnalyticsActor(serviceAccount = serviceAccount)

        /**
         * Returns an immutable instance of [BetaAnalyticsActor] whose [ofServiceAccount] variant is
         * built from the given required [serviceAccountId].
         */
        @JvmStatic
        fun ofServiceAccount(serviceAccountId: String) =
            ofServiceAccount(BetaAnalyticsServiceAccountActor.of(serviceAccountId))

        @JvmStatic
        fun ofFederated(federated: BetaAnalyticsFederatedActor) =
            BetaAnalyticsActor(federated = federated)

        @JvmStatic fun ofApi(api: BetaAnalyticsApiActor) = BetaAnalyticsActor(api = api)

        /**
         * Returns an immutable instance of [BetaAnalyticsActor] whose [ofApi] variant is built from
         * the given required [apiKeyId].
         */
        @JvmStatic fun ofApi(apiKeyId: String) = ofApi(BetaAnalyticsApiActor.of(apiKeyId))
    }

    /**
     * An interface that defines how to map each variant of [BetaAnalyticsActor] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitUser(user: BetaAnalyticsReportUserActor): T

        fun visitServiceAccount(serviceAccount: BetaAnalyticsServiceAccountActor): T

        fun visitFederated(federated: BetaAnalyticsFederatedActor): T

        fun visitApi(api: BetaAnalyticsApiActor): T

        /**
         * Maps an unknown variant of [BetaAnalyticsActor] to a value of type [T].
         *
         * An instance of [BetaAnalyticsActor] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaAnalyticsActor: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaAnalyticsActor>(BetaAnalyticsActor::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaAnalyticsActor {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "user_actor" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaAnalyticsReportUserActor>())
                        ?.let { BetaAnalyticsActor(user = it, _json = json) }
                        ?: BetaAnalyticsActor(_json = json)
                }
                "service_account_actor" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaAnalyticsServiceAccountActor>())
                        ?.let { BetaAnalyticsActor(serviceAccount = it, _json = json) }
                        ?: BetaAnalyticsActor(_json = json)
                }
                "federated_actor" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaAnalyticsFederatedActor>())
                        ?.let { BetaAnalyticsActor(federated = it, _json = json) }
                        ?: BetaAnalyticsActor(_json = json)
                }
                "api_actor" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaAnalyticsApiActor>())?.let {
                        BetaAnalyticsActor(api = it, _json = json)
                    } ?: BetaAnalyticsActor(_json = json)
                }
            }

            return BetaAnalyticsActor(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaAnalyticsActor>(BetaAnalyticsActor::class) {

        override fun serialize(
            value: BetaAnalyticsActor,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.user != null -> generator.writeObject(value.user)
                value.serviceAccount != null -> generator.writeObject(value.serviceAccount)
                value.federated != null -> generator.writeObject(value.federated)
                value.api != null -> generator.writeObject(value.api)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaAnalyticsActor")
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

            @JvmField val SERVICE_ACCOUNT_ACTOR = Type(JsonField.of("service_account_actor"))

            @JvmField val FEDERATED_ACTOR = Type(JsonField.of("federated_actor"))

            @JvmField val API_ACTOR = Type(JsonField.of("api_actor"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "user_actor" -> USER_ACTOR
                    "service_account_actor" -> SERVICE_ACCOUNT_ACTOR
                    "federated_actor" -> FEDERATED_ACTOR
                    "api_actor" -> API_ACTOR
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
            SERVICE_ACCOUNT_ACTOR,
            FEDERATED_ACTOR,
            API_ACTOR,
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
            SERVICE_ACCOUNT_ACTOR,
            FEDERATED_ACTOR,
            API_ACTOR,
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
                SERVICE_ACCOUNT_ACTOR -> Value.SERVICE_ACCOUNT_ACTOR
                FEDERATED_ACTOR -> Value.FEDERATED_ACTOR
                API_ACTOR -> Value.API_ACTOR
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
                SERVICE_ACCOUNT_ACTOR -> Known.SERVICE_ACCOUNT_ACTOR
                FEDERATED_ACTOR -> Known.FEDERATED_ACTOR
                API_ACTOR -> Known.API_ACTOR
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
