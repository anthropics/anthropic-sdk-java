package com.anthropic.models.beta.organization.claudetag

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

/** The settings level a value comes from. More types can be added. */
@JsonDeserialize(using = BetaClaudeTagSettingSource.Deserializer::class)
@JsonSerialize(using = BetaClaudeTagSettingSource.Serializer::class)
class BetaClaudeTagSettingSource
private constructor(
    private val slackChannel: BetaClaudeTagSlackChannelSettingSource? = null,
    private val slackWorkspace: BetaClaudeTagSlackWorkspaceSettingSource? = null,
    private val slackParentChannel: BetaClaudeTagSlackParentChannelSettingSource? = null,
    private val slackDefault: BetaClaudeTagSlackDefaultSettingSource? = null,
    private val default_: BetaClaudeTagDefaultSettingSource? = null,
    private val slackGridChannel: BetaClaudeTagSlackGridChannelSettingSource? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            slackChannel != null -> Type.SLACK_CHANNEL
            slackWorkspace != null -> Type.SLACK_WORKSPACE
            slackParentChannel != null -> Type.SLACK_PARENT_CHANNEL
            slackDefault != null -> Type.SLACK_DEFAULT
            default_ != null -> Type.DEFAULT
            slackGridChannel != null -> Type.SLACK_GRID_CHANNEL
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun slackChannelId(): Optional<String> =
        when {
            slackChannel != null -> Optional.of(slackChannel.slackChannelId())
            slackWorkspace != null -> Optional.empty()
            slackParentChannel != null -> Optional.of(slackParentChannel.slackChannelId())
            slackDefault != null -> Optional.empty()
            default_ != null -> Optional.empty()
            slackGridChannel != null -> Optional.empty()
            else -> _json.getProperty<String>("slack_channel_id").asKnown()
        }

    /** The value comes from the addressed Slack channel itself. */
    fun slackChannel(): Optional<BetaClaudeTagSlackChannelSettingSource> =
        Optional.ofNullable(slackChannel)

    /** The value comes from a Slack workspace of the organization. */
    fun slackWorkspace(): Optional<BetaClaudeTagSlackWorkspaceSettingSource> =
        Optional.ofNullable(slackWorkspace)

    /**
     * The value comes from the channel that a code channel was started from. A parent channel
     * contributes `instructions` and `environment_id` only.
     */
    fun slackParentChannel(): Optional<BetaClaudeTagSlackParentChannelSettingSource> =
        Optional.ofNullable(slackParentChannel)

    /** The value comes from the organization's Slack defaults, which have no identifier. */
    fun slackDefault(): Optional<BetaClaudeTagSlackDefaultSettingSource> =
        Optional.ofNullable(slackDefault)

    /** No settings level sets a value, and the built-in default applies. */
    fun default_(): Optional<BetaClaudeTagDefaultSettingSource> = Optional.ofNullable(default_)

    /**
     * The value comes from the addressed channel as it is set up under another workspace of its
     * Enterprise Grid: a channel shared across the Grid keeps one Slack channel identifier in every
     * workspace that sees it.
     */
    fun slackGridChannel(): Optional<BetaClaudeTagSlackGridChannelSettingSource> =
        Optional.ofNullable(slackGridChannel)

    fun isSlackChannel(): Boolean = slackChannel != null

    fun isSlackWorkspace(): Boolean = slackWorkspace != null

    fun isSlackParentChannel(): Boolean = slackParentChannel != null

    fun isSlackDefault(): Boolean = slackDefault != null

    fun isDefault(): Boolean = default_ != null

    fun isSlackGridChannel(): Boolean = slackGridChannel != null

    /** The value comes from the addressed Slack channel itself. */
    fun asSlackChannel(): BetaClaudeTagSlackChannelSettingSource =
        slackChannel.getOrThrow("slackChannel")

    /** The value comes from a Slack workspace of the organization. */
    fun asSlackWorkspace(): BetaClaudeTagSlackWorkspaceSettingSource =
        slackWorkspace.getOrThrow("slackWorkspace")

    /**
     * The value comes from the channel that a code channel was started from. A parent channel
     * contributes `instructions` and `environment_id` only.
     */
    fun asSlackParentChannel(): BetaClaudeTagSlackParentChannelSettingSource =
        slackParentChannel.getOrThrow("slackParentChannel")

    /** The value comes from the organization's Slack defaults, which have no identifier. */
    fun asSlackDefault(): BetaClaudeTagSlackDefaultSettingSource =
        slackDefault.getOrThrow("slackDefault")

    /** No settings level sets a value, and the built-in default applies. */
    fun asDefault(): BetaClaudeTagDefaultSettingSource = default_.getOrThrow("default_")

    /**
     * The value comes from the addressed channel as it is set up under another workspace of its
     * Enterprise Grid: a channel shared across the Grid keeps one Slack channel identifier in every
     * workspace that sees it.
     */
    fun asSlackGridChannel(): BetaClaudeTagSlackGridChannelSettingSource =
        slackGridChannel.getOrThrow("slackGridChannel")

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
     * Optional<String> result = betaClaudeTagSettingSource.accept(new BetaClaudeTagSettingSource.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitSlackChannel(BetaClaudeTagSlackChannelSettingSource slackChannel) {
     *         return Optional.of(slackChannel.toString());
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
            slackChannel != null -> visitor.visitSlackChannel(slackChannel)
            slackWorkspace != null -> visitor.visitSlackWorkspace(slackWorkspace)
            slackParentChannel != null -> visitor.visitSlackParentChannel(slackParentChannel)
            slackDefault != null -> visitor.visitSlackDefault(slackDefault)
            default_ != null -> visitor.visitDefault(default_)
            slackGridChannel != null -> visitor.visitSlackGridChannel(slackGridChannel)
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
    fun validate(): BetaClaudeTagSettingSource = apply {
        if (validated) {
            return@apply
        }

        when {
            slackChannel != null -> slackChannel.validate()
            slackWorkspace != null -> slackWorkspace.validate()
            slackParentChannel != null -> slackParentChannel.validate()
            slackDefault != null -> slackDefault.validate()
            default_ != null -> default_.validate()
            slackGridChannel != null -> slackGridChannel.validate()
            else ->
                throw AnthropicInvalidDataException("Unknown BetaClaudeTagSettingSource: $_json")
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
            slackChannel != null -> slackChannel.validity()
            slackWorkspace != null -> slackWorkspace.validity()
            slackParentChannel != null -> slackParentChannel.validity()
            slackDefault != null -> slackDefault.validity()
            default_ != null -> default_.validity()
            slackGridChannel != null -> slackGridChannel.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeTagSettingSource &&
            slackChannel == other.slackChannel &&
            slackWorkspace == other.slackWorkspace &&
            slackParentChannel == other.slackParentChannel &&
            slackDefault == other.slackDefault &&
            default_ == other.default_ &&
            slackGridChannel == other.slackGridChannel
    }

    override fun hashCode(): Int =
        Objects.hash(
            slackChannel,
            slackWorkspace,
            slackParentChannel,
            slackDefault,
            default_,
            slackGridChannel,
        )

    override fun toString(): String =
        when {
            slackChannel != null -> "BetaClaudeTagSettingSource{slackChannel=$slackChannel}"
            slackWorkspace != null -> "BetaClaudeTagSettingSource{slackWorkspace=$slackWorkspace}"
            slackParentChannel != null ->
                "BetaClaudeTagSettingSource{slackParentChannel=$slackParentChannel}"
            slackDefault != null -> "BetaClaudeTagSettingSource{slackDefault=$slackDefault}"
            default_ != null -> "BetaClaudeTagSettingSource{default_=$default_}"
            slackGridChannel != null ->
                "BetaClaudeTagSettingSource{slackGridChannel=$slackGridChannel}"
            _json != null -> "BetaClaudeTagSettingSource{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaClaudeTagSettingSource")
        }

    companion object {

        /** The value comes from the addressed Slack channel itself. */
        @JvmStatic
        fun ofSlackChannel(slackChannel: BetaClaudeTagSlackChannelSettingSource) =
            BetaClaudeTagSettingSource(slackChannel = slackChannel)

        /**
         * Returns an immutable instance of [BetaClaudeTagSettingSource] whose [ofSlackChannel]
         * variant is built from the given required [slackChannelId].
         */
        @JvmStatic
        fun ofSlackChannel(slackChannelId: String) =
            ofSlackChannel(BetaClaudeTagSlackChannelSettingSource.of(slackChannelId))

        /** The value comes from a Slack workspace of the organization. */
        @JvmStatic
        fun ofSlackWorkspace(slackWorkspace: BetaClaudeTagSlackWorkspaceSettingSource) =
            BetaClaudeTagSettingSource(slackWorkspace = slackWorkspace)

        /**
         * Returns an immutable instance of [BetaClaudeTagSettingSource] whose [ofSlackWorkspace]
         * variant is built from the given required [slackWorkspaceId].
         */
        @JvmStatic
        fun ofSlackWorkspace(slackWorkspaceId: String) =
            ofSlackWorkspace(BetaClaudeTagSlackWorkspaceSettingSource.of(slackWorkspaceId))

        /**
         * The value comes from the channel that a code channel was started from. A parent channel
         * contributes `instructions` and `environment_id` only.
         */
        @JvmStatic
        fun ofSlackParentChannel(slackParentChannel: BetaClaudeTagSlackParentChannelSettingSource) =
            BetaClaudeTagSettingSource(slackParentChannel = slackParentChannel)

        /**
         * Returns an immutable instance of [BetaClaudeTagSettingSource] whose
         * [ofSlackParentChannel] variant is built from the given required [slackChannelId].
         */
        @JvmStatic
        fun ofSlackParentChannel(slackChannelId: String) =
            ofSlackParentChannel(BetaClaudeTagSlackParentChannelSettingSource.of(slackChannelId))

        /** The value comes from the organization's Slack defaults, which have no identifier. */
        @JvmStatic
        fun ofSlackDefault(slackDefault: BetaClaudeTagSlackDefaultSettingSource) =
            BetaClaudeTagSettingSource(slackDefault = slackDefault)

        /** No settings level sets a value, and the built-in default applies. */
        @JvmStatic
        fun ofDefault(default_: BetaClaudeTagDefaultSettingSource) =
            BetaClaudeTagSettingSource(default_ = default_)

        /**
         * The value comes from the addressed channel as it is set up under another workspace of its
         * Enterprise Grid: a channel shared across the Grid keeps one Slack channel identifier in
         * every workspace that sees it.
         */
        @JvmStatic
        fun ofSlackGridChannel(slackGridChannel: BetaClaudeTagSlackGridChannelSettingSource) =
            BetaClaudeTagSettingSource(slackGridChannel = slackGridChannel)

        /**
         * Returns an immutable instance of [BetaClaudeTagSettingSource] whose [ofSlackGridChannel]
         * variant is built from the given required [slack].
         */
        @JvmStatic
        fun ofSlackGridChannel(slack: BetaClaudeTagSlackChannelIdentifiers) =
            ofSlackGridChannel(BetaClaudeTagSlackGridChannelSettingSource.of(slack))
    }

    /**
     * An interface that defines how to map each variant of [BetaClaudeTagSettingSource] to a value
     * of type [T].
     */
    interface Visitor<out T> {

        /** The value comes from the addressed Slack channel itself. */
        fun visitSlackChannel(slackChannel: BetaClaudeTagSlackChannelSettingSource): T

        /** The value comes from a Slack workspace of the organization. */
        fun visitSlackWorkspace(slackWorkspace: BetaClaudeTagSlackWorkspaceSettingSource): T

        /**
         * The value comes from the channel that a code channel was started from. A parent channel
         * contributes `instructions` and `environment_id` only.
         */
        fun visitSlackParentChannel(
            slackParentChannel: BetaClaudeTagSlackParentChannelSettingSource
        ): T

        /** The value comes from the organization's Slack defaults, which have no identifier. */
        fun visitSlackDefault(slackDefault: BetaClaudeTagSlackDefaultSettingSource): T

        /** No settings level sets a value, and the built-in default applies. */
        fun visitDefault(default_: BetaClaudeTagDefaultSettingSource): T

        /**
         * The value comes from the addressed channel as it is set up under another workspace of its
         * Enterprise Grid: a channel shared across the Grid keeps one Slack channel identifier in
         * every workspace that sees it.
         */
        fun visitSlackGridChannel(slackGridChannel: BetaClaudeTagSlackGridChannelSettingSource): T

        /**
         * Maps an unknown variant of [BetaClaudeTagSettingSource] to a value of type [T].
         *
         * An instance of [BetaClaudeTagSettingSource] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaClaudeTagSettingSource: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaClaudeTagSettingSource>(BetaClaudeTagSettingSource::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaClaudeTagSettingSource {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "slack_channel" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeTagSlackChannelSettingSource>(),
                        )
                        ?.let { BetaClaudeTagSettingSource(slackChannel = it, _json = json) }
                        ?: BetaClaudeTagSettingSource(_json = json)
                }
                "slack_workspace" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeTagSlackWorkspaceSettingSource>(),
                        )
                        ?.let { BetaClaudeTagSettingSource(slackWorkspace = it, _json = json) }
                        ?: BetaClaudeTagSettingSource(_json = json)
                }
                "slack_parent_channel" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeTagSlackParentChannelSettingSource>(),
                        )
                        ?.let { BetaClaudeTagSettingSource(slackParentChannel = it, _json = json) }
                        ?: BetaClaudeTagSettingSource(_json = json)
                }
                "slack_default" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeTagSlackDefaultSettingSource>(),
                        )
                        ?.let { BetaClaudeTagSettingSource(slackDefault = it, _json = json) }
                        ?: BetaClaudeTagSettingSource(_json = json)
                }
                "default" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaClaudeTagDefaultSettingSource>())
                        ?.let { BetaClaudeTagSettingSource(default_ = it, _json = json) }
                        ?: BetaClaudeTagSettingSource(_json = json)
                }
                "slack_grid_channel" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeTagSlackGridChannelSettingSource>(),
                        )
                        ?.let { BetaClaudeTagSettingSource(slackGridChannel = it, _json = json) }
                        ?: BetaClaudeTagSettingSource(_json = json)
                }
            }

            return BetaClaudeTagSettingSource(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaClaudeTagSettingSource>(BetaClaudeTagSettingSource::class) {

        override fun serialize(
            value: BetaClaudeTagSettingSource,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.slackChannel != null -> generator.writeObject(value.slackChannel)
                value.slackWorkspace != null -> generator.writeObject(value.slackWorkspace)
                value.slackParentChannel != null -> generator.writeObject(value.slackParentChannel)
                value.slackDefault != null -> generator.writeObject(value.slackDefault)
                value.default_ != null -> generator.writeObject(value.default_)
                value.slackGridChannel != null -> generator.writeObject(value.slackGridChannel)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaClaudeTagSettingSource")
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

            @JvmField val SLACK_CHANNEL = Type(JsonField.of("slack_channel"))

            @JvmField val SLACK_WORKSPACE = Type(JsonField.of("slack_workspace"))

            @JvmField val SLACK_PARENT_CHANNEL = Type(JsonField.of("slack_parent_channel"))

            @JvmField val SLACK_DEFAULT = Type(JsonField.of("slack_default"))

            @JvmField val DEFAULT = Type(JsonField.of("default"))

            @JvmField val SLACK_GRID_CHANNEL = Type(JsonField.of("slack_grid_channel"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "slack_channel" -> SLACK_CHANNEL
                    "slack_workspace" -> SLACK_WORKSPACE
                    "slack_parent_channel" -> SLACK_PARENT_CHANNEL
                    "slack_default" -> SLACK_DEFAULT
                    "default" -> DEFAULT
                    "slack_grid_channel" -> SLACK_GRID_CHANNEL
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SLACK_CHANNEL,
            SLACK_WORKSPACE,
            SLACK_PARENT_CHANNEL,
            SLACK_DEFAULT,
            DEFAULT,
            SLACK_GRID_CHANNEL,
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
            SLACK_CHANNEL,
            SLACK_WORKSPACE,
            SLACK_PARENT_CHANNEL,
            SLACK_DEFAULT,
            DEFAULT,
            SLACK_GRID_CHANNEL,
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
                SLACK_CHANNEL -> Value.SLACK_CHANNEL
                SLACK_WORKSPACE -> Value.SLACK_WORKSPACE
                SLACK_PARENT_CHANNEL -> Value.SLACK_PARENT_CHANNEL
                SLACK_DEFAULT -> Value.SLACK_DEFAULT
                DEFAULT -> Value.DEFAULT
                SLACK_GRID_CHANNEL -> Value.SLACK_GRID_CHANNEL
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
                SLACK_CHANNEL -> Known.SLACK_CHANNEL
                SLACK_WORKSPACE -> Known.SLACK_WORKSPACE
                SLACK_PARENT_CHANNEL -> Known.SLACK_PARENT_CHANNEL
                SLACK_DEFAULT -> Known.SLACK_DEFAULT
                DEFAULT -> Known.DEFAULT
                SLACK_GRID_CHANNEL -> Known.SLACK_GRID_CHANNEL
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
