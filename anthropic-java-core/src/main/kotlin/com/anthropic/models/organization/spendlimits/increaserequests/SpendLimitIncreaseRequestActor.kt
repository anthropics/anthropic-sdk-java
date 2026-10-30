package com.anthropic.models.organization.spendlimits.increaserequests

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.organization.spendlimits.SpendLimitScopedApiKeyActor
import com.anthropic.models.organization.spendlimits.SpendLimitUserActor
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

/** Who submitted the increase request. */
@JsonDeserialize(using = SpendLimitIncreaseRequestActor.Deserializer::class)
@JsonSerialize(using = SpendLimitIncreaseRequestActor.Serializer::class)
class SpendLimitIncreaseRequestActor
private constructor(
    private val user: SpendLimitUserActor? = null,
    private val scopedApiKey: SpendLimitScopedApiKeyActor? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            user != null -> Type.USER_ACTOR
            scopedApiKey != null -> Type.SCOPED_API_KEY_ACTOR
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /**
     * A user within the organization. `name` and `email_address` are null when the underlying
     * account is unavailable or has been deleted; `deleted` is true only for deleted accounts.
     */
    fun user(): Optional<SpendLimitUserActor> = Optional.ofNullable(user)

    /** A scoped Admin API key acting on behalf of the organization. */
    fun scopedApiKey(): Optional<SpendLimitScopedApiKeyActor> = Optional.ofNullable(scopedApiKey)

    fun isUser(): Boolean = user != null

    fun isScopedApiKey(): Boolean = scopedApiKey != null

    /**
     * A user within the organization. `name` and `email_address` are null when the underlying
     * account is unavailable or has been deleted; `deleted` is true only for deleted accounts.
     */
    fun asUser(): SpendLimitUserActor = user.getOrThrow("user")

    /** A scoped Admin API key acting on behalf of the organization. */
    fun asScopedApiKey(): SpendLimitScopedApiKeyActor = scopedApiKey.getOrThrow("scopedApiKey")

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
     * Optional<String> result = spendLimitIncreaseRequestActor.accept(new SpendLimitIncreaseRequestActor.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitUser(SpendLimitUserActor user) {
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
            scopedApiKey != null -> visitor.visitScopedApiKey(scopedApiKey)
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
    fun validate(): SpendLimitIncreaseRequestActor = apply {
        if (validated) {
            return@apply
        }

        when {
            user != null -> user.validate()
            scopedApiKey != null -> scopedApiKey.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown SpendLimitIncreaseRequestActor: $_json"
                )
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
            scopedApiKey != null -> scopedApiKey.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SpendLimitIncreaseRequestActor &&
            user == other.user &&
            scopedApiKey == other.scopedApiKey
    }

    override fun hashCode(): Int = Objects.hash(user, scopedApiKey)

    override fun toString(): String =
        when {
            user != null -> "SpendLimitIncreaseRequestActor{user=$user}"
            scopedApiKey != null -> "SpendLimitIncreaseRequestActor{scopedApiKey=$scopedApiKey}"
            _json != null -> "SpendLimitIncreaseRequestActor{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid SpendLimitIncreaseRequestActor")
        }

    companion object {

        /**
         * A user within the organization. `name` and `email_address` are null when the underlying
         * account is unavailable or has been deleted; `deleted` is true only for deleted accounts.
         */
        @JvmStatic
        fun ofUser(user: SpendLimitUserActor) = SpendLimitIncreaseRequestActor(user = user)

        /** A scoped Admin API key acting on behalf of the organization. */
        @JvmStatic
        fun ofScopedApiKey(scopedApiKey: SpendLimitScopedApiKeyActor) =
            SpendLimitIncreaseRequestActor(scopedApiKey = scopedApiKey)

        /**
         * Returns an immutable instance of [SpendLimitIncreaseRequestActor] whose [ofScopedApiKey]
         * variant is built from the given required [scopedApiKeyId].
         */
        @JvmStatic
        fun ofScopedApiKey(scopedApiKeyId: String) =
            ofScopedApiKey(SpendLimitScopedApiKeyActor.of(scopedApiKeyId))
    }

    /**
     * An interface that defines how to map each variant of [SpendLimitIncreaseRequestActor] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /**
         * A user within the organization. `name` and `email_address` are null when the underlying
         * account is unavailable or has been deleted; `deleted` is true only for deleted accounts.
         */
        fun visitUser(user: SpendLimitUserActor): T

        /** A scoped Admin API key acting on behalf of the organization. */
        fun visitScopedApiKey(scopedApiKey: SpendLimitScopedApiKeyActor): T

        /**
         * Maps an unknown variant of [SpendLimitIncreaseRequestActor] to a value of type [T].
         *
         * An instance of [SpendLimitIncreaseRequestActor] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown SpendLimitIncreaseRequestActor: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<SpendLimitIncreaseRequestActor>(SpendLimitIncreaseRequestActor::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): SpendLimitIncreaseRequestActor {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "user_actor" -> {
                    return tryDeserialize(node, jacksonTypeRef<SpendLimitUserActor>())?.let {
                        SpendLimitIncreaseRequestActor(user = it, _json = json)
                    } ?: SpendLimitIncreaseRequestActor(_json = json)
                }
                "scoped_api_key_actor" -> {
                    return tryDeserialize(node, jacksonTypeRef<SpendLimitScopedApiKeyActor>())
                        ?.let { SpendLimitIncreaseRequestActor(scopedApiKey = it, _json = json) }
                        ?: SpendLimitIncreaseRequestActor(_json = json)
                }
            }

            return SpendLimitIncreaseRequestActor(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<SpendLimitIncreaseRequestActor>(SpendLimitIncreaseRequestActor::class) {

        override fun serialize(
            value: SpendLimitIncreaseRequestActor,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.user != null -> generator.writeObject(value.user)
                value.scopedApiKey != null -> generator.writeObject(value.scopedApiKey)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid SpendLimitIncreaseRequestActor")
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

            @JvmField val SCOPED_API_KEY_ACTOR = Type(JsonField.of("scoped_api_key_actor"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "user_actor" -> USER_ACTOR
                    "scoped_api_key_actor" -> SCOPED_API_KEY_ACTOR
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
            SCOPED_API_KEY_ACTOR,
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
            SCOPED_API_KEY_ACTOR,
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
                SCOPED_API_KEY_ACTOR -> Value.SCOPED_API_KEY_ACTOR
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
                SCOPED_API_KEY_ACTOR -> Known.SCOPED_API_KEY_ACTOR
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
