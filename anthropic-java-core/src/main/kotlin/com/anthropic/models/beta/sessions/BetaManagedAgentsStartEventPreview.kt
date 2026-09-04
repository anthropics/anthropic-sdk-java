// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

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

@JsonDeserialize(using = BetaManagedAgentsStartEventPreview.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsStartEventPreview.Serializer::class)
class BetaManagedAgentsStartEventPreview
private constructor(
    private val agentMessage: BetaManagedAgentsAgentMessagePreview? = null,
    private val agentThinking: BetaManagedAgentsAgentThinkingPreview? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessagePreview
                ): Type = Type.AGENT_MESSAGE

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingPreview
                ): Type = Type.AGENT_THINKING

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun id(): String =
        accept(
            object : Visitor<String> {
                override fun visitAgentMessage(
                    agentMessage: BetaManagedAgentsAgentMessagePreview
                ): String = agentMessage.id()

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingPreview
                ): String = agentThinking.id()

                override fun unknown(json: JsonValue?): String =
                    json.getProperty<String>("id").getRequired("id")
            }
        )

    fun agentMessage(): Optional<BetaManagedAgentsAgentMessagePreview> =
        Optional.ofNullable(agentMessage)

    fun agentThinking(): Optional<BetaManagedAgentsAgentThinkingPreview> =
        Optional.ofNullable(agentThinking)

    fun isAgentMessage(): Boolean = agentMessage != null

    fun isAgentThinking(): Boolean = agentThinking != null

    fun asAgentMessage(): BetaManagedAgentsAgentMessagePreview =
        agentMessage.getOrThrow("agentMessage")

    fun asAgentThinking(): BetaManagedAgentsAgentThinkingPreview =
        agentThinking.getOrThrow("agentThinking")

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
     * Optional<String> result = betaManagedAgentsStartEventPreview.accept(new BetaManagedAgentsStartEventPreview.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAgentMessage(BetaManagedAgentsAgentMessagePreview agentMessage) {
     *         return Optional.of(agentMessage.toString());
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
            agentMessage != null -> visitor.visitAgentMessage(agentMessage)
            agentThinking != null -> visitor.visitAgentThinking(agentThinking)
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
    fun validate(): BetaManagedAgentsStartEventPreview = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitAgentMessage(agentMessage: BetaManagedAgentsAgentMessagePreview) {
                    agentMessage.validate()
                }

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingPreview
                ) {
                    agentThinking.validate()
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
                override fun visitAgentMessage(agentMessage: BetaManagedAgentsAgentMessagePreview) =
                    agentMessage.validity()

                override fun visitAgentThinking(
                    agentThinking: BetaManagedAgentsAgentThinkingPreview
                ) = agentThinking.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsStartEventPreview &&
            agentMessage == other.agentMessage &&
            agentThinking == other.agentThinking
    }

    override fun hashCode(): Int = Objects.hash(agentMessage, agentThinking)

    override fun toString(): String =
        when {
            agentMessage != null -> "BetaManagedAgentsStartEventPreview{agentMessage=$agentMessage}"
            agentThinking != null ->
                "BetaManagedAgentsStartEventPreview{agentThinking=$agentThinking}"
            _json != null -> "BetaManagedAgentsStartEventPreview{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsStartEventPreview")
        }

    companion object {

        @JvmStatic
        fun ofAgentMessage(agentMessage: BetaManagedAgentsAgentMessagePreview) =
            BetaManagedAgentsStartEventPreview(agentMessage = agentMessage)

        /**
         * Returns an immutable instance of [BetaManagedAgentsStartEventPreview] whose
         * [ofAgentMessage] variant is built from the given required [id].
         */
        @JvmStatic
        fun ofAgentMessage(id: String) =
            ofAgentMessage(
                BetaManagedAgentsAgentMessagePreview.builder()
                    .type(BetaManagedAgentsAgentMessagePreview.Type.AGENT_MESSAGE)
                    .id(id)
                    .build()
            )

        @JvmStatic
        fun ofAgentThinking(agentThinking: BetaManagedAgentsAgentThinkingPreview) =
            BetaManagedAgentsStartEventPreview(agentThinking = agentThinking)

        /**
         * Returns an immutable instance of [BetaManagedAgentsStartEventPreview] whose
         * [ofAgentThinking] variant is built from the given required [id].
         */
        @JvmStatic
        fun ofAgentThinking(id: String) =
            ofAgentThinking(
                BetaManagedAgentsAgentThinkingPreview.builder()
                    .type(BetaManagedAgentsAgentThinkingPreview.Type.AGENT_THINKING)
                    .id(id)
                    .build()
            )
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsStartEventPreview] to
     * a value of type [T].
     */
    interface Visitor<out T> {

        fun visitAgentMessage(agentMessage: BetaManagedAgentsAgentMessagePreview): T

        fun visitAgentThinking(agentThinking: BetaManagedAgentsAgentThinkingPreview): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsStartEventPreview] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsStartEventPreview] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsStartEventPreview: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsStartEventPreview>(
            BetaManagedAgentsStartEventPreview::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsStartEventPreview {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "agent.message" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentMessagePreview>(),
                        )
                        ?.let {
                            BetaManagedAgentsStartEventPreview(agentMessage = it, _json = json)
                        } ?: BetaManagedAgentsStartEventPreview(_json = json)
                }
                "agent.thinking" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentThinkingPreview>(),
                        )
                        ?.let {
                            BetaManagedAgentsStartEventPreview(agentThinking = it, _json = json)
                        } ?: BetaManagedAgentsStartEventPreview(_json = json)
                }
            }

            return BetaManagedAgentsStartEventPreview(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsStartEventPreview>(
            BetaManagedAgentsStartEventPreview::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsStartEventPreview,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.agentMessage != null -> generator.writeObject(value.agentMessage)
                value.agentThinking != null -> generator.writeObject(value.agentThinking)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsStartEventPreview")
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

            @JvmField val AGENT_MESSAGE = of("agent.message")

            @JvmField val AGENT_THINKING = of("agent.thinking")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            AGENT_MESSAGE,
            AGENT_THINKING,
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
            AGENT_MESSAGE,
            AGENT_THINKING,
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
                AGENT_MESSAGE -> Value.AGENT_MESSAGE
                AGENT_THINKING -> Value.AGENT_THINKING
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
                AGENT_MESSAGE -> Known.AGENT_MESSAGE
                AGENT_THINKING -> Known.AGENT_THINKING
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
