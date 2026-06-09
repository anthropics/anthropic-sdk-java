// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
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

        /** Parameters for sending an interrupt to pause the agent. */
        @JvmStatic
        fun ofUserInterrupt(userInterrupt: BetaManagedAgentsUserInterruptEventParams) =
            BetaManagedAgentsEventParams(userInterrupt = userInterrupt)

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
         * Privileged context for the accompanying turn and all subsequent turns, appended to the
         * session's system context as a `role: "system"` turn rather than replacing the top-level
         * system prompt. At most one per request: it must be the final event and immediately follow
         * the `user.message`, `user.tool_result`, or `user.custom_tool_result` it accompanies. Only
         * supported on models that accept mid-conversation system messages.
         */
        @JvmStatic
        fun ofSystemMessage(systemMessage: BetaManagedAgentsSystemMessageEventParams) =
            BetaManagedAgentsEventParams(systemMessage = systemMessage)
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
}
