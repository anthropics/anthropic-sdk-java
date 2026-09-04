// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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

/** Union type for event parameters that can be sent to a session. */
@JsonDeserialize(using = BetaManagedAgentsEventParams.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsEventParams.Serializer::class)
class BetaManagedAgentsEventParams
private constructor(
    private val userMessage: BetaManagedAgentsUserMessageEventParams? = null,
    private val userInterrupt: BetaManagedAgentsUserInterruptEventParams? = null,
    private val userToolConfirmation: BetaManagedAgentsUserToolConfirmationEventParams? = null,
    private val userCustomToolResult: BetaManagedAgentsUserCustomToolResultEventParams? = null,
    private val userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams? = null,
    private val userToolResult: BetaManagedAgentsUserToolResultEventParams? = null,
    private val systemMessage: BetaManagedAgentsSystemMessageEventParams? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEventParams
                ): Type = Type.USER_MESSAGE

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEventParams
                ): Type = Type.USER_INTERRUPT

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEventParams
                ): Type = Type.USER_TOOL_CONFIRMATION

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEventParams
                ): Type = Type.USER_CUSTOM_TOOL_RESULT

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams
                ): Type = Type.USER_DEFINE_OUTCOME

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEventParams
                ): Type = Type.USER_TOOL_RESULT

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEventParams
                ): Type = Type.SYSTEM_MESSAGE

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun toolUseId(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEventParams
                ): Optional<String> = Optional.empty()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEventParams
                ): Optional<String> = Optional.empty()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEventParams
                ): Optional<String> = Optional.of(userToolConfirmation.toolUseId())

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEventParams
                ): Optional<String> = Optional.empty()

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams
                ): Optional<String> = Optional.empty()

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEventParams
                ): Optional<String> = Optional.of(userToolResult.toolUseId())

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEventParams
                ): Optional<String> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("tool_use_id").asKnown()
            }
        )

    fun isError(): Optional<Boolean> =
        accept(
            object : Visitor<Optional<Boolean>> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEventParams
                ): Optional<Boolean> = Optional.empty()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEventParams
                ): Optional<Boolean> = Optional.empty()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEventParams
                ): Optional<Boolean> = Optional.empty()

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEventParams
                ): Optional<Boolean> = userCustomToolResult.isError()

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams
                ): Optional<Boolean> = Optional.empty()

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEventParams
                ): Optional<Boolean> = userToolResult.isError()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEventParams
                ): Optional<Boolean> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Boolean> =
                    json.getProperty<Boolean>("is_error").asKnown()
            }
        )

    /** Parameters for sending a user message to the session. */
    fun userMessage(): Optional<BetaManagedAgentsUserMessageEventParams> =
        Optional.ofNullable(userMessage)

    /** Parameters for sending an interrupt to pause the agent. */
    fun userInterrupt(): Optional<BetaManagedAgentsUserInterruptEventParams> =
        Optional.ofNullable(userInterrupt)

    /** Parameters for confirming or denying a tool execution request. */
    fun userToolConfirmation(): Optional<BetaManagedAgentsUserToolConfirmationEventParams> =
        Optional.ofNullable(userToolConfirmation)

    /** Parameters for providing the result of a custom tool execution. */
    fun userCustomToolResult(): Optional<BetaManagedAgentsUserCustomToolResultEventParams> =
        Optional.ofNullable(userCustomToolResult)

    /**
     * Parameters for defining an outcome the agent should work toward. The agent begins work on
     * receipt.
     */
    fun userDefineOutcome(): Optional<BetaManagedAgentsUserDefineOutcomeEventParams> =
        Optional.ofNullable(userDefineOutcome)

    /**
     * Parameters for providing the result of an agent-toolset tool execution. Only valid on
     * `self_hosted` environments, where sandbox-routed tools are executed by the client rather than
     * the server.
     */
    fun userToolResult(): Optional<BetaManagedAgentsUserToolResultEventParams> =
        Optional.ofNullable(userToolResult)

    /**
     * Privileged context for the accompanying turn and all subsequent turns, appended to the
     * session's system context as a `role: "system"` turn rather than replacing the top-level
     * system prompt. At most one per request: it must be the final event and immediately follow the
     * `user.message`, `user.tool_result`, or `user.custom_tool_result` it accompanies. Only
     * supported on models that accept mid-conversation system messages.
     */
    fun systemMessage(): Optional<BetaManagedAgentsSystemMessageEventParams> =
        Optional.ofNullable(systemMessage)

    fun isUserMessage(): Boolean = userMessage != null

    fun isUserInterrupt(): Boolean = userInterrupt != null

    fun isUserToolConfirmation(): Boolean = userToolConfirmation != null

    fun isUserCustomToolResult(): Boolean = userCustomToolResult != null

    fun isUserDefineOutcome(): Boolean = userDefineOutcome != null

    fun isUserToolResult(): Boolean = userToolResult != null

    fun isSystemMessage(): Boolean = systemMessage != null

    /** Parameters for sending a user message to the session. */
    fun asUserMessage(): BetaManagedAgentsUserMessageEventParams =
        userMessage.getOrThrow("userMessage")

    /** Parameters for sending an interrupt to pause the agent. */
    fun asUserInterrupt(): BetaManagedAgentsUserInterruptEventParams =
        userInterrupt.getOrThrow("userInterrupt")

    /** Parameters for confirming or denying a tool execution request. */
    fun asUserToolConfirmation(): BetaManagedAgentsUserToolConfirmationEventParams =
        userToolConfirmation.getOrThrow("userToolConfirmation")

    /** Parameters for providing the result of a custom tool execution. */
    fun asUserCustomToolResult(): BetaManagedAgentsUserCustomToolResultEventParams =
        userCustomToolResult.getOrThrow("userCustomToolResult")

    /**
     * Parameters for defining an outcome the agent should work toward. The agent begins work on
     * receipt.
     */
    fun asUserDefineOutcome(): BetaManagedAgentsUserDefineOutcomeEventParams =
        userDefineOutcome.getOrThrow("userDefineOutcome")

    /**
     * Parameters for providing the result of an agent-toolset tool execution. Only valid on
     * `self_hosted` environments, where sandbox-routed tools are executed by the client rather than
     * the server.
     */
    fun asUserToolResult(): BetaManagedAgentsUserToolResultEventParams =
        userToolResult.getOrThrow("userToolResult")

    /**
     * Privileged context for the accompanying turn and all subsequent turns, appended to the
     * session's system context as a `role: "system"` turn rather than replacing the top-level
     * system prompt. At most one per request: it must be the final event and immediately follow the
     * `user.message`, `user.tool_result`, or `user.custom_tool_result` it accompanies. Only
     * supported on models that accept mid-conversation system messages.
     */
    fun asSystemMessage(): BetaManagedAgentsSystemMessageEventParams =
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
     * Optional<String> result = betaManagedAgentsEventParams.accept(new BetaManagedAgentsEventParams.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitUserMessage(BetaManagedAgentsUserMessageEventParams userMessage) {
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
            userInterrupt != null -> visitor.visitUserInterrupt(userInterrupt)
            userToolConfirmation != null -> visitor.visitUserToolConfirmation(userToolConfirmation)
            userCustomToolResult != null -> visitor.visitUserCustomToolResult(userCustomToolResult)
            userDefineOutcome != null -> visitor.visitUserDefineOutcome(userDefineOutcome)
            userToolResult != null -> visitor.visitUserToolResult(userToolResult)
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
    fun validate(): BetaManagedAgentsEventParams = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitUserMessage(
                    userMessage: BetaManagedAgentsUserMessageEventParams
                ) {
                    userMessage.validate()
                }

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEventParams
                ) {
                    userInterrupt.validate()
                }

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEventParams
                ) {
                    userToolConfirmation.validate()
                }

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEventParams
                ) {
                    userCustomToolResult.validate()
                }

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams
                ) {
                    userDefineOutcome.validate()
                }

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEventParams
                ) {
                    userToolResult.validate()
                }

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEventParams
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
                    userMessage: BetaManagedAgentsUserMessageEventParams
                ) = userMessage.validity()

                override fun visitUserInterrupt(
                    userInterrupt: BetaManagedAgentsUserInterruptEventParams
                ) = userInterrupt.validity()

                override fun visitUserToolConfirmation(
                    userToolConfirmation: BetaManagedAgentsUserToolConfirmationEventParams
                ) = userToolConfirmation.validity()

                override fun visitUserCustomToolResult(
                    userCustomToolResult: BetaManagedAgentsUserCustomToolResultEventParams
                ) = userCustomToolResult.validity()

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams
                ) = userDefineOutcome.validity()

                override fun visitUserToolResult(
                    userToolResult: BetaManagedAgentsUserToolResultEventParams
                ) = userToolResult.validity()

                override fun visitSystemMessage(
                    systemMessage: BetaManagedAgentsSystemMessageEventParams
                ) = systemMessage.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsEventParams &&
            userMessage == other.userMessage &&
            userInterrupt == other.userInterrupt &&
            userToolConfirmation == other.userToolConfirmation &&
            userCustomToolResult == other.userCustomToolResult &&
            userDefineOutcome == other.userDefineOutcome &&
            userToolResult == other.userToolResult &&
            systemMessage == other.systemMessage
    }

    override fun hashCode(): Int =
        Objects.hash(
            userMessage,
            userInterrupt,
            userToolConfirmation,
            userCustomToolResult,
            userDefineOutcome,
            userToolResult,
            systemMessage,
        )

    override fun toString(): String =
        when {
            userMessage != null -> "BetaManagedAgentsEventParams{userMessage=$userMessage}"
            userInterrupt != null -> "BetaManagedAgentsEventParams{userInterrupt=$userInterrupt}"
            userToolConfirmation != null ->
                "BetaManagedAgentsEventParams{userToolConfirmation=$userToolConfirmation}"
            userCustomToolResult != null ->
                "BetaManagedAgentsEventParams{userCustomToolResult=$userCustomToolResult}"
            userDefineOutcome != null ->
                "BetaManagedAgentsEventParams{userDefineOutcome=$userDefineOutcome}"
            userToolResult != null -> "BetaManagedAgentsEventParams{userToolResult=$userToolResult}"
            systemMessage != null -> "BetaManagedAgentsEventParams{systemMessage=$systemMessage}"
            _json != null -> "BetaManagedAgentsEventParams{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsEventParams")
        }

    companion object {

        /** Parameters for sending a user message to the session. */
        @JvmStatic
        fun ofUserMessage(userMessage: BetaManagedAgentsUserMessageEventParams) =
            BetaManagedAgentsEventParams(userMessage = userMessage)

        /**
         * Returns an immutable instance of [BetaManagedAgentsEventParams] whose [ofUserMessage]
         * variant is built from the given required [content].
         */
        @JvmStatic
        fun ofUserMessage(content: List<BetaManagedAgentsUserMessageEventParams.Content>) =
            ofUserMessage(
                BetaManagedAgentsUserMessageEventParams.builder()
                    .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
                    .content(content)
                    .build()
            )

        /** Parameters for sending an interrupt to pause the agent. */
        @JvmStatic
        fun ofUserInterrupt(userInterrupt: BetaManagedAgentsUserInterruptEventParams) =
            BetaManagedAgentsEventParams(userInterrupt = userInterrupt)

        /**
         * Returns an immutable instance of [BetaManagedAgentsEventParams] whose [ofUserInterrupt]
         * variant is built from the given required [type].
         */
        @JvmStatic
        fun ofUserInterrupt(type: BetaManagedAgentsUserInterruptEventParams.Type) =
            ofUserInterrupt(BetaManagedAgentsUserInterruptEventParams.of(type))

        /** Parameters for confirming or denying a tool execution request. */
        @JvmStatic
        fun ofUserToolConfirmation(
            userToolConfirmation: BetaManagedAgentsUserToolConfirmationEventParams
        ) = BetaManagedAgentsEventParams(userToolConfirmation = userToolConfirmation)

        /** Parameters for providing the result of a custom tool execution. */
        @JvmStatic
        fun ofUserCustomToolResult(
            userCustomToolResult: BetaManagedAgentsUserCustomToolResultEventParams
        ) = BetaManagedAgentsEventParams(userCustomToolResult = userCustomToolResult)

        /**
         * Returns an immutable instance of [BetaManagedAgentsEventParams] whose
         * [ofUserCustomToolResult] variant is built from the given required [customToolUseId].
         */
        @JvmStatic
        fun ofUserCustomToolResult(customToolUseId: String) =
            ofUserCustomToolResult(
                BetaManagedAgentsUserCustomToolResultEventParams.builder()
                    .type(
                        BetaManagedAgentsUserCustomToolResultEventParams.Type
                            .USER_CUSTOM_TOOL_RESULT
                    )
                    .customToolUseId(customToolUseId)
                    .build()
            )

        /**
         * Parameters for defining an outcome the agent should work toward. The agent begins work on
         * receipt.
         */
        @JvmStatic
        fun ofUserDefineOutcome(userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams) =
            BetaManagedAgentsEventParams(userDefineOutcome = userDefineOutcome)

        /**
         * Parameters for providing the result of an agent-toolset tool execution. Only valid on
         * `self_hosted` environments, where sandbox-routed tools are executed by the client rather
         * than the server.
         */
        @JvmStatic
        fun ofUserToolResult(userToolResult: BetaManagedAgentsUserToolResultEventParams) =
            BetaManagedAgentsEventParams(userToolResult = userToolResult)

        /**
         * Returns an immutable instance of [BetaManagedAgentsEventParams] whose [ofUserToolResult]
         * variant is built from the given required [toolUseId].
         */
        @JvmStatic
        fun ofUserToolResult(toolUseId: String) =
            ofUserToolResult(
                BetaManagedAgentsUserToolResultEventParams.builder()
                    .type(BetaManagedAgentsUserToolResultEventParams.Type.USER_TOOL_RESULT)
                    .toolUseId(toolUseId)
                    .build()
            )

        /**
         * Privileged context for the accompanying turn and all subsequent turns, appended to the
         * session's system context as a `role: "system"` turn rather than replacing the top-level
         * system prompt. At most one per request: it must be the final event and immediately follow
         * the `user.message`, `user.tool_result`, or `user.custom_tool_result` it accompanies. Only
         * supported on models that accept mid-conversation system messages.
         */
        @JvmStatic
        fun ofSystemMessage(systemMessage: BetaManagedAgentsSystemMessageEventParams) =
            BetaManagedAgentsEventParams(systemMessage = systemMessage)

        /**
         * Returns an immutable instance of [BetaManagedAgentsEventParams] whose [ofSystemMessage]
         * variant is built from the given required [content].
         */
        @JvmStatic
        fun ofSystemMessage(content: List<BetaManagedAgentsSystemContentBlock>) =
            ofSystemMessage(
                BetaManagedAgentsSystemMessageEventParams.builder()
                    .type(BetaManagedAgentsSystemMessageEventParams.Type.SYSTEM_MESSAGE)
                    .content(content)
                    .build()
            )
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsEventParams] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /** Parameters for sending a user message to the session. */
        fun visitUserMessage(userMessage: BetaManagedAgentsUserMessageEventParams): T

        /** Parameters for sending an interrupt to pause the agent. */
        fun visitUserInterrupt(userInterrupt: BetaManagedAgentsUserInterruptEventParams): T

        /** Parameters for confirming or denying a tool execution request. */
        fun visitUserToolConfirmation(
            userToolConfirmation: BetaManagedAgentsUserToolConfirmationEventParams
        ): T

        /** Parameters for providing the result of a custom tool execution. */
        fun visitUserCustomToolResult(
            userCustomToolResult: BetaManagedAgentsUserCustomToolResultEventParams
        ): T

        /**
         * Parameters for defining an outcome the agent should work toward. The agent begins work on
         * receipt.
         */
        fun visitUserDefineOutcome(
            userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams
        ): T

        /**
         * Parameters for providing the result of an agent-toolset tool execution. Only valid on
         * `self_hosted` environments, where sandbox-routed tools are executed by the client rather
         * than the server.
         */
        fun visitUserToolResult(userToolResult: BetaManagedAgentsUserToolResultEventParams): T

        /**
         * Privileged context for the accompanying turn and all subsequent turns, appended to the
         * session's system context as a `role: "system"` turn rather than replacing the top-level
         * system prompt. At most one per request: it must be the final event and immediately follow
         * the `user.message`, `user.tool_result`, or `user.custom_tool_result` it accompanies. Only
         * supported on models that accept mid-conversation system messages.
         */
        fun visitSystemMessage(systemMessage: BetaManagedAgentsSystemMessageEventParams): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsEventParams] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsEventParams] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsEventParams: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsEventParams>(BetaManagedAgentsEventParams::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsEventParams {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "user.message" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserMessageEventParams>(),
                        )
                        ?.let { BetaManagedAgentsEventParams(userMessage = it, _json = json) }
                        ?: BetaManagedAgentsEventParams(_json = json)
                }
                "user.interrupt" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserInterruptEventParams>(),
                        )
                        ?.let { BetaManagedAgentsEventParams(userInterrupt = it, _json = json) }
                        ?: BetaManagedAgentsEventParams(_json = json)
                }
                "user.tool_confirmation" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserToolConfirmationEventParams>(),
                        )
                        ?.let {
                            BetaManagedAgentsEventParams(userToolConfirmation = it, _json = json)
                        } ?: BetaManagedAgentsEventParams(_json = json)
                }
                "user.custom_tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserCustomToolResultEventParams>(),
                        )
                        ?.let {
                            BetaManagedAgentsEventParams(userCustomToolResult = it, _json = json)
                        } ?: BetaManagedAgentsEventParams(_json = json)
                }
                "user.define_outcome" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserDefineOutcomeEventParams>(),
                        )
                        ?.let { BetaManagedAgentsEventParams(userDefineOutcome = it, _json = json) }
                        ?: BetaManagedAgentsEventParams(_json = json)
                }
                "user.tool_result" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserToolResultEventParams>(),
                        )
                        ?.let { BetaManagedAgentsEventParams(userToolResult = it, _json = json) }
                        ?: BetaManagedAgentsEventParams(_json = json)
                }
                "system.message" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSystemMessageEventParams>(),
                        )
                        ?.let { BetaManagedAgentsEventParams(systemMessage = it, _json = json) }
                        ?: BetaManagedAgentsEventParams(_json = json)
                }
            }

            return BetaManagedAgentsEventParams(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsEventParams>(BetaManagedAgentsEventParams::class) {

        override fun serialize(
            value: BetaManagedAgentsEventParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.userMessage != null -> generator.writeObject(value.userMessage)
                value.userInterrupt != null -> generator.writeObject(value.userInterrupt)
                value.userToolConfirmation != null ->
                    generator.writeObject(value.userToolConfirmation)
                value.userCustomToolResult != null ->
                    generator.writeObject(value.userCustomToolResult)
                value.userDefineOutcome != null -> generator.writeObject(value.userDefineOutcome)
                value.userToolResult != null -> generator.writeObject(value.userToolResult)
                value.systemMessage != null -> generator.writeObject(value.systemMessage)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsEventParams")
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

            @JvmField val USER_INTERRUPT = of("user.interrupt")

            @JvmField val USER_TOOL_CONFIRMATION = of("user.tool_confirmation")

            @JvmField val USER_CUSTOM_TOOL_RESULT = of("user.custom_tool_result")

            @JvmField val USER_DEFINE_OUTCOME = of("user.define_outcome")

            @JvmField val USER_TOOL_RESULT = of("user.tool_result")

            @JvmField val SYSTEM_MESSAGE = of("system.message")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            USER_MESSAGE,
            USER_INTERRUPT,
            USER_TOOL_CONFIRMATION,
            USER_CUSTOM_TOOL_RESULT,
            USER_DEFINE_OUTCOME,
            USER_TOOL_RESULT,
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
            USER_INTERRUPT,
            USER_TOOL_CONFIRMATION,
            USER_CUSTOM_TOOL_RESULT,
            USER_DEFINE_OUTCOME,
            USER_TOOL_RESULT,
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
                USER_INTERRUPT -> Value.USER_INTERRUPT
                USER_TOOL_CONFIRMATION -> Value.USER_TOOL_CONFIRMATION
                USER_CUSTOM_TOOL_RESULT -> Value.USER_CUSTOM_TOOL_RESULT
                USER_DEFINE_OUTCOME -> Value.USER_DEFINE_OUTCOME
                USER_TOOL_RESULT -> Value.USER_TOOL_RESULT
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
                USER_INTERRUPT -> Known.USER_INTERRUPT
                USER_TOOL_CONFIRMATION -> Known.USER_TOOL_CONFIRMATION
                USER_CUSTOM_TOOL_RESULT -> Known.USER_CUSTOM_TOOL_RESULT
                USER_DEFINE_OUTCOME -> Known.USER_DEFINE_OUTCOME
                USER_TOOL_RESULT -> Known.USER_TOOL_RESULT
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
