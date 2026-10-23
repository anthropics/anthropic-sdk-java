package com.anthropic.models.beta.organization.claudetag.bundles.targets

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

@JsonDeserialize(using = BetaClaudeTagBundleTargetCreate.Deserializer::class)
@JsonSerialize(using = BetaClaudeTagBundleTargetCreate.Serializer::class)
class BetaClaudeTagBundleTargetCreate
private constructor(
    private val slackChannel: BetaClaudeTagSlackChannelBundleTargetCreate? = null,
    private val slackWorkspace: BetaClaudeTagSlackWorkspaceBundleTargetCreate? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            slackChannel != null -> Type.SLACK_CHANNEL
            slackWorkspace != null -> Type.SLACK_WORKSPACE
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** Attach the Bundle to a Slack channel. */
    fun slackChannel(): Optional<BetaClaudeTagSlackChannelBundleTargetCreate> =
        Optional.ofNullable(slackChannel)

    /** Attach the Bundle to a Slack workspace. */
    fun slackWorkspace(): Optional<BetaClaudeTagSlackWorkspaceBundleTargetCreate> =
        Optional.ofNullable(slackWorkspace)

    fun isSlackChannel(): Boolean = slackChannel != null

    fun isSlackWorkspace(): Boolean = slackWorkspace != null

    /** Attach the Bundle to a Slack channel. */
    fun asSlackChannel(): BetaClaudeTagSlackChannelBundleTargetCreate =
        slackChannel.getOrThrow("slackChannel")

    /** Attach the Bundle to a Slack workspace. */
    fun asSlackWorkspace(): BetaClaudeTagSlackWorkspaceBundleTargetCreate =
        slackWorkspace.getOrThrow("slackWorkspace")

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
     * Optional<String> result = betaClaudeTagBundleTargetCreate.accept(new BetaClaudeTagBundleTargetCreate.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitSlackChannel(BetaClaudeTagSlackChannelBundleTargetCreate slackChannel) {
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
    fun validate(): BetaClaudeTagBundleTargetCreate = apply {
        if (validated) {
            return@apply
        }

        when {
            slackChannel != null -> slackChannel.validate()
            slackWorkspace != null -> slackWorkspace.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaClaudeTagBundleTargetCreate: $_json"
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
            slackChannel != null -> slackChannel.validity()
            slackWorkspace != null -> slackWorkspace.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeTagBundleTargetCreate &&
            slackChannel == other.slackChannel &&
            slackWorkspace == other.slackWorkspace
    }

    override fun hashCode(): Int = Objects.hash(slackChannel, slackWorkspace)

    override fun toString(): String =
        when {
            slackChannel != null -> "BetaClaudeTagBundleTargetCreate{slackChannel=$slackChannel}"
            slackWorkspace != null ->
                "BetaClaudeTagBundleTargetCreate{slackWorkspace=$slackWorkspace}"
            _json != null -> "BetaClaudeTagBundleTargetCreate{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaClaudeTagBundleTargetCreate")
        }

    companion object {

        /** Attach the Bundle to a Slack channel. */
        @JvmStatic
        fun ofSlackChannel(slackChannel: BetaClaudeTagSlackChannelBundleTargetCreate) =
            BetaClaudeTagBundleTargetCreate(slackChannel = slackChannel)

        /**
         * Returns an immutable instance of [BetaClaudeTagBundleTargetCreate] whose [ofSlackChannel]
         * variant is built from the given required [slackChannelId].
         */
        @JvmStatic
        fun ofSlackChannel(slackChannelId: String) =
            ofSlackChannel(BetaClaudeTagSlackChannelBundleTargetCreate.of(slackChannelId))

        /** Attach the Bundle to a Slack workspace. */
        @JvmStatic
        fun ofSlackWorkspace(slackWorkspace: BetaClaudeTagSlackWorkspaceBundleTargetCreate) =
            BetaClaudeTagBundleTargetCreate(slackWorkspace = slackWorkspace)

        /**
         * Returns an immutable instance of [BetaClaudeTagBundleTargetCreate] whose
         * [ofSlackWorkspace] variant is built from the given required [slackWorkspaceId].
         */
        @JvmStatic
        fun ofSlackWorkspace(slackWorkspaceId: String) =
            ofSlackWorkspace(BetaClaudeTagSlackWorkspaceBundleTargetCreate.of(slackWorkspaceId))
    }

    /**
     * An interface that defines how to map each variant of [BetaClaudeTagBundleTargetCreate] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /** Attach the Bundle to a Slack channel. */
        fun visitSlackChannel(slackChannel: BetaClaudeTagSlackChannelBundleTargetCreate): T

        /** Attach the Bundle to a Slack workspace. */
        fun visitSlackWorkspace(slackWorkspace: BetaClaudeTagSlackWorkspaceBundleTargetCreate): T

        /**
         * Maps an unknown variant of [BetaClaudeTagBundleTargetCreate] to a value of type [T].
         *
         * An instance of [BetaClaudeTagBundleTargetCreate] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaClaudeTagBundleTargetCreate: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaClaudeTagBundleTargetCreate>(BetaClaudeTagBundleTargetCreate::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaClaudeTagBundleTargetCreate {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "slack_channel" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeTagSlackChannelBundleTargetCreate>(),
                        )
                        ?.let { BetaClaudeTagBundleTargetCreate(slackChannel = it, _json = json) }
                        ?: BetaClaudeTagBundleTargetCreate(_json = json)
                }
                "slack_workspace" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeTagSlackWorkspaceBundleTargetCreate>(),
                        )
                        ?.let { BetaClaudeTagBundleTargetCreate(slackWorkspace = it, _json = json) }
                        ?: BetaClaudeTagBundleTargetCreate(_json = json)
                }
            }

            return BetaClaudeTagBundleTargetCreate(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaClaudeTagBundleTargetCreate>(BetaClaudeTagBundleTargetCreate::class) {

        override fun serialize(
            value: BetaClaudeTagBundleTargetCreate,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.slackChannel != null -> generator.writeObject(value.slackChannel)
                value.slackWorkspace != null -> generator.writeObject(value.slackWorkspace)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaClaudeTagBundleTargetCreate")
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

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "slack_channel" -> SLACK_CHANNEL
                    "slack_workspace" -> SLACK_WORKSPACE
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
