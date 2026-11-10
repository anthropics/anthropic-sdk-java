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
 * How Claude takes part in a Slack channel. A channel alone stores one. More types can be added.
 */
@JsonDeserialize(using = BetaClaudeTagReplyMode.Deserializer::class)
@JsonSerialize(using = BetaClaudeTagReplyMode.Serializer::class)
class BetaClaudeTagReplyMode
private constructor(
    private val automatic: BetaClaudeTagReplyModeAutomatic? = null,
    private val mentionOnly: BetaClaudeTagReplyModeMentionOnly? = null,
    private val alwaysAwake: BetaClaudeTagReplyModeAlwaysAwake? = null,
    private val thread: BetaClaudeTagReplyModeThread? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            automatic != null -> Type.AUTOMATIC
            mentionOnly != null -> Type.MENTION_ONLY
            alwaysAwake != null -> Type.ALWAYS_AWAKE
            thread != null -> Type.THREAD
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /**
     * Claude reads along and joins in when it can help, and pauses on its own in quiet or busy
     * channels.
     */
    fun automatic(): Optional<BetaClaudeTagReplyModeAutomatic> = Optional.ofNullable(automatic)

    /** Claude replies only when it is @-mentioned. */
    fun mentionOnly(): Optional<BetaClaudeTagReplyModeMentionOnly> =
        Optional.ofNullable(mentionOnly)

    /**
     * Like `automatic`, but Claude never pauses on its own. Available where it is enabled for the
     * channel.
     */
    fun alwaysAwake(): Optional<BetaClaudeTagReplyModeAlwaysAwake> =
        Optional.ofNullable(alwaysAwake)

    /**
     * Every new top-level message in the channel gets its own reply from Claude, in a thread under
     * it. Available where it is enabled for the channel, and only once Claude has been used in the
     * channel.
     */
    fun thread(): Optional<BetaClaudeTagReplyModeThread> = Optional.ofNullable(thread)

    fun isAutomatic(): Boolean = automatic != null

    fun isMentionOnly(): Boolean = mentionOnly != null

    fun isAlwaysAwake(): Boolean = alwaysAwake != null

    fun isThread(): Boolean = thread != null

    /**
     * Claude reads along and joins in when it can help, and pauses on its own in quiet or busy
     * channels.
     */
    fun asAutomatic(): BetaClaudeTagReplyModeAutomatic = automatic.getOrThrow("automatic")

    /** Claude replies only when it is @-mentioned. */
    fun asMentionOnly(): BetaClaudeTagReplyModeMentionOnly = mentionOnly.getOrThrow("mentionOnly")

    /**
     * Like `automatic`, but Claude never pauses on its own. Available where it is enabled for the
     * channel.
     */
    fun asAlwaysAwake(): BetaClaudeTagReplyModeAlwaysAwake = alwaysAwake.getOrThrow("alwaysAwake")

    /**
     * Every new top-level message in the channel gets its own reply from Claude, in a thread under
     * it. Available where it is enabled for the channel, and only once Claude has been used in the
     * channel.
     */
    fun asThread(): BetaClaudeTagReplyModeThread = thread.getOrThrow("thread")

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
     * Optional<String> result = betaClaudeTagReplyMode.accept(new BetaClaudeTagReplyMode.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAutomatic(BetaClaudeTagReplyModeAutomatic automatic) {
     *         return Optional.of(automatic.toString());
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
            automatic != null -> visitor.visitAutomatic(automatic)
            mentionOnly != null -> visitor.visitMentionOnly(mentionOnly)
            alwaysAwake != null -> visitor.visitAlwaysAwake(alwaysAwake)
            thread != null -> visitor.visitThread(thread)
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
    fun validate(): BetaClaudeTagReplyMode = apply {
        if (validated) {
            return@apply
        }

        when {
            automatic != null -> automatic.validate()
            mentionOnly != null -> mentionOnly.validate()
            alwaysAwake != null -> alwaysAwake.validate()
            thread != null -> thread.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaClaudeTagReplyMode: $_json")
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
            automatic != null -> automatic.validity()
            mentionOnly != null -> mentionOnly.validity()
            alwaysAwake != null -> alwaysAwake.validity()
            thread != null -> thread.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeTagReplyMode &&
            automatic == other.automatic &&
            mentionOnly == other.mentionOnly &&
            alwaysAwake == other.alwaysAwake &&
            thread == other.thread
    }

    override fun hashCode(): Int = Objects.hash(automatic, mentionOnly, alwaysAwake, thread)

    override fun toString(): String =
        when {
            automatic != null -> "BetaClaudeTagReplyMode{automatic=$automatic}"
            mentionOnly != null -> "BetaClaudeTagReplyMode{mentionOnly=$mentionOnly}"
            alwaysAwake != null -> "BetaClaudeTagReplyMode{alwaysAwake=$alwaysAwake}"
            thread != null -> "BetaClaudeTagReplyMode{thread=$thread}"
            _json != null -> "BetaClaudeTagReplyMode{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaClaudeTagReplyMode")
        }

    companion object {

        /**
         * Claude reads along and joins in when it can help, and pauses on its own in quiet or busy
         * channels.
         */
        @JvmStatic
        fun ofAutomatic(automatic: BetaClaudeTagReplyModeAutomatic) =
            BetaClaudeTagReplyMode(automatic = automatic)

        /** Claude replies only when it is @-mentioned. */
        @JvmStatic
        fun ofMentionOnly(mentionOnly: BetaClaudeTagReplyModeMentionOnly) =
            BetaClaudeTagReplyMode(mentionOnly = mentionOnly)

        /**
         * Like `automatic`, but Claude never pauses on its own. Available where it is enabled for
         * the channel.
         */
        @JvmStatic
        fun ofAlwaysAwake(alwaysAwake: BetaClaudeTagReplyModeAlwaysAwake) =
            BetaClaudeTagReplyMode(alwaysAwake = alwaysAwake)

        /**
         * Every new top-level message in the channel gets its own reply from Claude, in a thread
         * under it. Available where it is enabled for the channel, and only once Claude has been
         * used in the channel.
         */
        @JvmStatic
        fun ofThread(thread: BetaClaudeTagReplyModeThread) = BetaClaudeTagReplyMode(thread = thread)
    }

    /**
     * An interface that defines how to map each variant of [BetaClaudeTagReplyMode] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        /**
         * Claude reads along and joins in when it can help, and pauses on its own in quiet or busy
         * channels.
         */
        fun visitAutomatic(automatic: BetaClaudeTagReplyModeAutomatic): T

        /** Claude replies only when it is @-mentioned. */
        fun visitMentionOnly(mentionOnly: BetaClaudeTagReplyModeMentionOnly): T

        /**
         * Like `automatic`, but Claude never pauses on its own. Available where it is enabled for
         * the channel.
         */
        fun visitAlwaysAwake(alwaysAwake: BetaClaudeTagReplyModeAlwaysAwake): T

        /**
         * Every new top-level message in the channel gets its own reply from Claude, in a thread
         * under it. Available where it is enabled for the channel, and only once Claude has been
         * used in the channel.
         */
        fun visitThread(thread: BetaClaudeTagReplyModeThread): T

        /**
         * Maps an unknown variant of [BetaClaudeTagReplyMode] to a value of type [T].
         *
         * An instance of [BetaClaudeTagReplyMode] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaClaudeTagReplyMode: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaClaudeTagReplyMode>(BetaClaudeTagReplyMode::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaClaudeTagReplyMode {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "automatic" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaClaudeTagReplyModeAutomatic>())
                        ?.let { BetaClaudeTagReplyMode(automatic = it, _json = json) }
                        ?: BetaClaudeTagReplyMode(_json = json)
                }
                "mention_only" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaClaudeTagReplyModeMentionOnly>())
                        ?.let { BetaClaudeTagReplyMode(mentionOnly = it, _json = json) }
                        ?: BetaClaudeTagReplyMode(_json = json)
                }
                "always_awake" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaClaudeTagReplyModeAlwaysAwake>())
                        ?.let { BetaClaudeTagReplyMode(alwaysAwake = it, _json = json) }
                        ?: BetaClaudeTagReplyMode(_json = json)
                }
                "thread" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaClaudeTagReplyModeThread>())
                        ?.let { BetaClaudeTagReplyMode(thread = it, _json = json) }
                        ?: BetaClaudeTagReplyMode(_json = json)
                }
            }

            return BetaClaudeTagReplyMode(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaClaudeTagReplyMode>(BetaClaudeTagReplyMode::class) {

        override fun serialize(
            value: BetaClaudeTagReplyMode,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.automatic != null -> generator.writeObject(value.automatic)
                value.mentionOnly != null -> generator.writeObject(value.mentionOnly)
                value.alwaysAwake != null -> generator.writeObject(value.alwaysAwake)
                value.thread != null -> generator.writeObject(value.thread)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaClaudeTagReplyMode")
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

            @JvmField val AUTOMATIC = Type(JsonField.of("automatic"))

            @JvmField val MENTION_ONLY = Type(JsonField.of("mention_only"))

            @JvmField val ALWAYS_AWAKE = Type(JsonField.of("always_awake"))

            @JvmField val THREAD = Type(JsonField.of("thread"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "automatic" -> AUTOMATIC
                    "mention_only" -> MENTION_ONLY
                    "always_awake" -> ALWAYS_AWAKE
                    "thread" -> THREAD
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            AUTOMATIC,
            MENTION_ONLY,
            ALWAYS_AWAKE,
            THREAD,
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
            AUTOMATIC,
            MENTION_ONLY,
            ALWAYS_AWAKE,
            THREAD,
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
                AUTOMATIC -> Value.AUTOMATIC
                MENTION_ONLY -> Value.MENTION_ONLY
                ALWAYS_AWAKE -> Value.ALWAYS_AWAKE
                THREAD -> Value.THREAD
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
                AUTOMATIC -> Known.AUTOMATIC
                MENTION_ONLY -> Known.MENTION_ONLY
                ALWAYS_AWAKE -> Known.ALWAYS_AWAKE
                THREAD -> Known.THREAD
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
