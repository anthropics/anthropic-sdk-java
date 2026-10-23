package com.anthropic.models.beta.organization.claudetag

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

/**
 * What Claude does in a channel while a Slack guest is in it, for everyone in the channel. It
 * changes nothing in a channel without Slack guests. More types can be added.
 */
@JsonDeserialize(using = BetaClaudeTagGuestPolicy.Deserializer::class)
@JsonSerialize(using = BetaClaudeTagGuestPolicy.Serializer::class)
class BetaClaudeTagGuestPolicy
private constructor(
    private val allow: BetaClaudeTagGuestPolicyAllow? = null,
    private val restrict: BetaClaudeTagGuestPolicyRestrict? = null,
    private val channelOnly: BetaClaudeTagGuestPolicyChannelOnly? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            allow != null -> Type.ALLOW
            restrict != null -> Type.RESTRICT
            channelOnly != null -> Type.CHANNEL_ONLY
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** Claude answers in the channel. */
    fun allow(): Optional<BetaClaudeTagGuestPolicyAllow> = Optional.ofNullable(allow)

    /** Claude does not answer in the channel. */
    fun restrict(): Optional<BetaClaudeTagGuestPolicyRestrict> = Optional.ofNullable(restrict)

    /** Claude answers with only the Bundles attached to the channel itself. */
    fun channelOnly(): Optional<BetaClaudeTagGuestPolicyChannelOnly> =
        Optional.ofNullable(channelOnly)

    fun isAllow(): Boolean = allow != null

    fun isRestrict(): Boolean = restrict != null

    fun isChannelOnly(): Boolean = channelOnly != null

    /** Claude answers in the channel. */
    fun asAllow(): BetaClaudeTagGuestPolicyAllow = allow.getOrThrow("allow")

    /** Claude does not answer in the channel. */
    fun asRestrict(): BetaClaudeTagGuestPolicyRestrict = restrict.getOrThrow("restrict")

    /** Claude answers with only the Bundles attached to the channel itself. */
    fun asChannelOnly(): BetaClaudeTagGuestPolicyChannelOnly = channelOnly.getOrThrow("channelOnly")

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
     * Optional<String> result = betaClaudeTagGuestPolicy.accept(new BetaClaudeTagGuestPolicy.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAllow(BetaClaudeTagGuestPolicyAllow allow) {
     *         return Optional.of(allow.toString());
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
            allow != null -> visitor.visitAllow(allow)
            restrict != null -> visitor.visitRestrict(restrict)
            channelOnly != null -> visitor.visitChannelOnly(channelOnly)
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
    fun validate(): BetaClaudeTagGuestPolicy = apply {
        if (validated) {
            return@apply
        }

        when {
            allow != null -> allow.validate()
            restrict != null -> restrict.validate()
            channelOnly != null -> channelOnly.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaClaudeTagGuestPolicy: $_json")
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
            allow != null -> allow.validity()
            restrict != null -> restrict.validity()
            channelOnly != null -> channelOnly.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeTagGuestPolicy &&
            allow == other.allow &&
            restrict == other.restrict &&
            channelOnly == other.channelOnly
    }

    override fun hashCode(): Int = Objects.hash(allow, restrict, channelOnly)

    override fun toString(): String =
        when {
            allow != null -> "BetaClaudeTagGuestPolicy{allow=$allow}"
            restrict != null -> "BetaClaudeTagGuestPolicy{restrict=$restrict}"
            channelOnly != null -> "BetaClaudeTagGuestPolicy{channelOnly=$channelOnly}"
            _json != null -> "BetaClaudeTagGuestPolicy{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaClaudeTagGuestPolicy")
        }

    companion object {

        /** Claude answers in the channel. */
        @JvmStatic
        fun ofAllow(allow: BetaClaudeTagGuestPolicyAllow) = BetaClaudeTagGuestPolicy(allow = allow)

        /** Claude does not answer in the channel. */
        @JvmStatic
        fun ofRestrict(restrict: BetaClaudeTagGuestPolicyRestrict) =
            BetaClaudeTagGuestPolicy(restrict = restrict)

        /** Claude answers with only the Bundles attached to the channel itself. */
        @JvmStatic
        fun ofChannelOnly(channelOnly: BetaClaudeTagGuestPolicyChannelOnly) =
            BetaClaudeTagGuestPolicy(channelOnly = channelOnly)
    }

    /**
     * An interface that defines how to map each variant of [BetaClaudeTagGuestPolicy] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        /** Claude answers in the channel. */
        fun visitAllow(allow: BetaClaudeTagGuestPolicyAllow): T

        /** Claude does not answer in the channel. */
        fun visitRestrict(restrict: BetaClaudeTagGuestPolicyRestrict): T

        /** Claude answers with only the Bundles attached to the channel itself. */
        fun visitChannelOnly(channelOnly: BetaClaudeTagGuestPolicyChannelOnly): T

        /**
         * Maps an unknown variant of [BetaClaudeTagGuestPolicy] to a value of type [T].
         *
         * An instance of [BetaClaudeTagGuestPolicy] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaClaudeTagGuestPolicy: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaClaudeTagGuestPolicy>(BetaClaudeTagGuestPolicy::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaClaudeTagGuestPolicy {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "allow" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaClaudeTagGuestPolicyAllow>())
                        ?.let { BetaClaudeTagGuestPolicy(allow = it, _json = json) }
                        ?: BetaClaudeTagGuestPolicy(_json = json)
                }
                "restrict" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaClaudeTagGuestPolicyRestrict>())
                        ?.let { BetaClaudeTagGuestPolicy(restrict = it, _json = json) }
                        ?: BetaClaudeTagGuestPolicy(_json = json)
                }
                "channel_only" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeTagGuestPolicyChannelOnly>(),
                        )
                        ?.let { BetaClaudeTagGuestPolicy(channelOnly = it, _json = json) }
                        ?: BetaClaudeTagGuestPolicy(_json = json)
                }
            }

            return BetaClaudeTagGuestPolicy(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaClaudeTagGuestPolicy>(BetaClaudeTagGuestPolicy::class) {

        override fun serialize(
            value: BetaClaudeTagGuestPolicy,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.allow != null -> generator.writeObject(value.allow)
                value.restrict != null -> generator.writeObject(value.restrict)
                value.channelOnly != null -> generator.writeObject(value.channelOnly)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaClaudeTagGuestPolicy")
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

            @JvmField val ALLOW = Type(JsonField.of("allow"))

            @JvmField val RESTRICT = Type(JsonField.of("restrict"))

            @JvmField val CHANNEL_ONLY = Type(JsonField.of("channel_only"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "allow" -> ALLOW
                    "restrict" -> RESTRICT
                    "channel_only" -> CHANNEL_ONLY
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            ALLOW,
            RESTRICT,
            CHANNEL_ONLY,
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
            ALLOW,
            RESTRICT,
            CHANNEL_ONLY,
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
                ALLOW -> Value.ALLOW
                RESTRICT -> Value.RESTRICT
                CHANNEL_ONLY -> Value.CHANNEL_ONLY
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
                ALLOW -> Known.ALLOW
                RESTRICT -> Known.RESTRICT
                CHANNEL_ONLY -> Known.CHANNEL_ONLY
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
