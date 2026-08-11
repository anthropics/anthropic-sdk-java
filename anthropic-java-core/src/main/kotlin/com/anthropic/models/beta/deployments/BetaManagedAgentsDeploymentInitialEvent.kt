// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsSystemContentBlock
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
 * An event sent to a session immediately after it is created. Supports `user.message`,
 * `user.define_outcome`, and `system.message`.
 */
@JsonDeserialize(using = BetaManagedAgentsDeploymentInitialEvent.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsDeploymentInitialEvent.Serializer::class)
class BetaManagedAgentsDeploymentInitialEvent
private constructor(
    private val userMessage: BetaManagedAgentsDeploymentUserMessageEvent? = null,
    private val userDefineOutcome: BetaManagedAgentsDeploymentUserDefineOutcomeEvent? = null,
    private val systemMessage: BetaManagedAgentsDeploymentSystemMessageEvent? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsDeploymentUserMessageEvent
                ): Type = Type.USER_MESSAGE

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsDeploymentUserDefineOutcomeEvent
                ): Type = Type.USER_DEFINE_OUTCOME

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsDeploymentSystemMessageEvent
                ): Type = Type.SYSTEM_MESSAGE

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    /** A user message sent to the session. */
    fun userMessage(): Optional<BetaManagedAgentsDeploymentUserMessageEvent> =
        Optional.ofNullable(userMessage)

    /** An outcome the agent should work toward. The agent begins work on receipt. */
    fun userDefineOutcome(): Optional<BetaManagedAgentsDeploymentUserDefineOutcomeEvent> =
        Optional.ofNullable(userDefineOutcome)

    /**
     * Privileged context for the accompanying turn and all subsequent turns, appended to the
     * session's system context as a `role: "system"` turn rather than replacing the top-level
     * system prompt.
     */
    fun systemMessage(): Optional<BetaManagedAgentsDeploymentSystemMessageEvent> =
        Optional.ofNullable(systemMessage)

    fun isUserMessage(): Boolean = userMessage != null

    fun isUserDefineOutcome(): Boolean = userDefineOutcome != null

    fun isSystemMessage(): Boolean = systemMessage != null

    /** A user message sent to the session. */
    fun asUserMessage(): BetaManagedAgentsDeploymentUserMessageEvent =
        userMessage.getOrThrow("userMessage")

    /** An outcome the agent should work toward. The agent begins work on receipt. */
    fun asUserDefineOutcome(): BetaManagedAgentsDeploymentUserDefineOutcomeEvent =
        userDefineOutcome.getOrThrow("userDefineOutcome")

    /**
     * Privileged context for the accompanying turn and all subsequent turns, appended to the
     * session's system context as a `role: "system"` turn rather than replacing the top-level
     * system prompt.
     */
    fun asSystemMessage(): BetaManagedAgentsDeploymentSystemMessageEvent =
        systemMessage.getOrThrow("systemMessage")

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
     * Optional<String> result = betaManagedAgentsDeploymentInitialEvent.accept(new BetaManagedAgentsDeploymentInitialEvent.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitUserMessage(BetaManagedAgentsDeploymentUserMessageEvent userMessage) {
     *         return Optional.of(userMessage.toString());
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
            userMessage != null -> visitor.visitUserMessage(userMessage)
            userDefineOutcome != null -> visitor.visitUserDefineOutcome(userDefineOutcome)
            systemMessage != null -> visitor.visitSystemMessage(systemMessage)
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
    fun validate(): BetaManagedAgentsDeploymentInitialEvent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsDeploymentUserMessageEvent
                ) {
                    userMessage.validate()
                }

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsDeploymentUserDefineOutcomeEvent
                ) {
                    userDefineOutcome.validate()
                }

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsDeploymentSystemMessageEvent
                ) {
                    systemMessage.validate()
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
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsDeploymentUserMessageEvent
                ) = userMessage.validity()

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsDeploymentUserDefineOutcomeEvent
                ) = userDefineOutcome.validity()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsDeploymentSystemMessageEvent
                ) = systemMessage.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsDeploymentInitialEvent &&
            userMessage == other.userMessage &&
            userDefineOutcome == other.userDefineOutcome &&
            systemMessage == other.systemMessage
    }

    override fun hashCode(): Int = Objects.hash(userMessage, userDefineOutcome, systemMessage)

    override fun toString(): String =
        when {
            userMessage != null ->
                "BetaManagedAgentsDeploymentInitialEvent{userMessage=$userMessage}"
            userDefineOutcome != null ->
                "BetaManagedAgentsDeploymentInitialEvent{userDefineOutcome=$userDefineOutcome}"
            systemMessage != null ->
                "BetaManagedAgentsDeploymentInitialEvent{systemMessage=$systemMessage}"
            _json != null -> "BetaManagedAgentsDeploymentInitialEvent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsDeploymentInitialEvent")
        }

    companion object {

        /** A user message sent to the session. */
        @JvmStatic
        fun ofUserMessage(userMessage: BetaManagedAgentsDeploymentUserMessageEvent) =
            BetaManagedAgentsDeploymentInitialEvent(userMessage = userMessage)

        /**
         * Returns an immutable instance of [BetaManagedAgentsDeploymentInitialEvent] whose
         * [ofUserMessage] variant is built from the given required [content].
         */
        @JvmStatic
        fun ofUserMessage(content: List<BetaManagedAgentsDeploymentUserMessageEvent.Content>) =
            ofUserMessage(
                BetaManagedAgentsDeploymentUserMessageEvent.builder()
                    .type(BetaManagedAgentsDeploymentUserMessageEvent.Type.USER_MESSAGE)
                    .content(content)
                    .build()
            )

        /** An outcome the agent should work toward. The agent begins work on receipt. */
        @JvmStatic
        fun ofUserDefineOutcome(
            userDefineOutcome: BetaManagedAgentsDeploymentUserDefineOutcomeEvent
        ) = BetaManagedAgentsDeploymentInitialEvent(userDefineOutcome = userDefineOutcome)

        /**
         * Privileged context for the accompanying turn and all subsequent turns, appended to the
         * session's system context as a `role: "system"` turn rather than replacing the top-level
         * system prompt.
         */
        @JvmStatic
        fun ofSystemMessage(systemMessage: BetaManagedAgentsDeploymentSystemMessageEvent) =
            BetaManagedAgentsDeploymentInitialEvent(systemMessage = systemMessage)

        /**
         * Returns an immutable instance of [BetaManagedAgentsDeploymentInitialEvent] whose
         * [ofSystemMessage] variant is built from the given required [content].
         */
        @JvmStatic
        fun ofSystemMessage(content: List<BetaManagedAgentsSystemContentBlock>) =
            ofSystemMessage(
                BetaManagedAgentsDeploymentSystemMessageEvent.builder()
                    .type(BetaManagedAgentsDeploymentSystemMessageEvent.Type.SYSTEM_MESSAGE)
                    .content(content)
                    .build()
            )
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsDeploymentInitialEvent] to a value of type [T].
     */
    interface Visitor<out T> {

        /** A user message sent to the session. */
        fun visitUserMessage(userMessage: BetaManagedAgentsDeploymentUserMessageEvent): T

        /** An outcome the agent should work toward. The agent begins work on receipt. */
        fun visitUserDefineOutcome(
            userDefineOutcome: BetaManagedAgentsDeploymentUserDefineOutcomeEvent
        ): T

        /**
         * Privileged context for the accompanying turn and all subsequent turns, appended to the
         * session's system context as a `role: "system"` turn rather than replacing the top-level
         * system prompt.
         */
        fun visitSystemMessage(systemMessage: BetaManagedAgentsDeploymentSystemMessageEvent): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsDeploymentInitialEvent] to a value of type
         * [T].
         *
         * An instance of [BetaManagedAgentsDeploymentInitialEvent] can contain an unknown variant
         * if it was deserialized from data that doesn't match any known variant. For example, if
         * the SDK is on an older version than the API, then the API may respond with new variants
         * that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsDeploymentInitialEvent: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsDeploymentInitialEvent>(
            BetaManagedAgentsDeploymentInitialEvent::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsDeploymentInitialEvent {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "user.message" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsDeploymentUserMessageEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentInitialEvent(userMessage = it, _json = json)
                        } ?: BetaManagedAgentsDeploymentInitialEvent(_json = json)
                }
                "user.define_outcome" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsDeploymentUserDefineOutcomeEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentInitialEvent(
                                userDefineOutcome = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentInitialEvent(_json = json)
                }
                "system.message" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsDeploymentSystemMessageEvent>(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentInitialEvent(
                                systemMessage = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentInitialEvent(_json = json)
                }
            }

            return BetaManagedAgentsDeploymentInitialEvent(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsDeploymentInitialEvent>(
            BetaManagedAgentsDeploymentInitialEvent::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsDeploymentInitialEvent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.userMessage != null -> generator.writeObject(value.userMessage)
                value.userDefineOutcome != null -> generator.writeObject(value.userDefineOutcome)
                value.systemMessage != null -> generator.writeObject(value.systemMessage)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException("Invalid BetaManagedAgentsDeploymentInitialEvent")
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

            @JvmField val USER_MESSAGE = of("user.message")

            @JvmField val USER_DEFINE_OUTCOME = of("user.define_outcome")

            @JvmField val SYSTEM_MESSAGE = of("system.message")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            USER_MESSAGE,
            USER_DEFINE_OUTCOME,
            SYSTEM_MESSAGE,
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
            USER_MESSAGE,
            USER_DEFINE_OUTCOME,
            SYSTEM_MESSAGE,
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
                USER_MESSAGE -> Value.USER_MESSAGE
                USER_DEFINE_OUTCOME -> Value.USER_DEFINE_OUTCOME
                SYSTEM_MESSAGE -> Value.SYSTEM_MESSAGE
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
                USER_MESSAGE -> Known.USER_MESSAGE
                USER_DEFINE_OUTCOME -> Known.USER_DEFINE_OUTCOME
                SYSTEM_MESSAGE -> Known.SYSTEM_MESSAGE
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
