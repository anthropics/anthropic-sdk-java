// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSystemMessageEventParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserDefineOutcomeEventParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams
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
@JsonDeserialize(using = BetaManagedAgentsDeploymentInitialEventParams.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsDeploymentInitialEventParams.Serializer::class)
class BetaManagedAgentsDeploymentInitialEventParams
private constructor(
    private val userMessage: BetaManagedAgentsUserMessageEventParams? = null,
    private val userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams? = null,
    private val systemMessage: BetaManagedAgentsSystemMessageEventParams? = null,
    private val _json: JsonValue? = null,
) {

    /** Parameters for sending a user message to the session. */
    fun userMessage(): Optional<BetaManagedAgentsUserMessageEventParams> =
        Optional.ofNullable(userMessage)

    /**
     * Parameters for defining an outcome the agent should work toward. The agent begins work on
     * receipt.
     */
    fun userDefineOutcome(): Optional<BetaManagedAgentsUserDefineOutcomeEventParams> =
        Optional.ofNullable(userDefineOutcome)

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

    fun isUserDefineOutcome(): Boolean = userDefineOutcome != null

    fun isSystemMessage(): Boolean = systemMessage != null

    /** Parameters for sending a user message to the session. */
    fun asUserMessage(): BetaManagedAgentsUserMessageEventParams =
        userMessage.getOrThrow("userMessage")

    /**
     * Parameters for defining an outcome the agent should work toward. The agent begins work on
     * receipt.
     */
    fun asUserDefineOutcome(): BetaManagedAgentsUserDefineOutcomeEventParams =
        userDefineOutcome.getOrThrow("userDefineOutcome")

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
     * Optional<String> result = betaManagedAgentsDeploymentInitialEventParams.accept(new BetaManagedAgentsDeploymentInitialEventParams.Visitor<Optional<String>>() {
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
    fun validate(): BetaManagedAgentsDeploymentInitialEventParams = apply {
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

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams
                ) {
                    userDefineOutcome.validate()
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

                override fun visitUserDefineOutcome(
                    userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams
                ) = userDefineOutcome.validity()

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

        return other is BetaManagedAgentsDeploymentInitialEventParams &&
            userMessage == other.userMessage &&
            userDefineOutcome == other.userDefineOutcome &&
            systemMessage == other.systemMessage
    }

    override fun hashCode(): Int = Objects.hash(userMessage, userDefineOutcome, systemMessage)

    override fun toString(): String =
        when {
            userMessage != null ->
                "BetaManagedAgentsDeploymentInitialEventParams{userMessage=$userMessage}"
            userDefineOutcome != null ->
                "BetaManagedAgentsDeploymentInitialEventParams{userDefineOutcome=$userDefineOutcome}"
            systemMessage != null ->
                "BetaManagedAgentsDeploymentInitialEventParams{systemMessage=$systemMessage}"
            _json != null -> "BetaManagedAgentsDeploymentInitialEventParams{_unknown=$_json}"
            else ->
                throw IllegalStateException("Invalid BetaManagedAgentsDeploymentInitialEventParams")
        }

    companion object {

        /** Parameters for sending a user message to the session. */
        @JvmStatic
        fun ofUserMessage(userMessage: BetaManagedAgentsUserMessageEventParams) =
            BetaManagedAgentsDeploymentInitialEventParams(userMessage = userMessage)

        /**
         * Parameters for defining an outcome the agent should work toward. The agent begins work on
         * receipt.
         */
        @JvmStatic
        fun ofUserDefineOutcome(userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams) =
            BetaManagedAgentsDeploymentInitialEventParams(userDefineOutcome = userDefineOutcome)

        /**
         * Privileged context for the accompanying turn and all subsequent turns, appended to the
         * session's system context as a `role: "system"` turn rather than replacing the top-level
         * system prompt. At most one per request: it must be the final event and immediately follow
         * the `user.message`, `user.tool_result`, or `user.custom_tool_result` it accompanies. Only
         * supported on models that accept mid-conversation system messages.
         */
        @JvmStatic
        fun ofSystemMessage(systemMessage: BetaManagedAgentsSystemMessageEventParams) =
            BetaManagedAgentsDeploymentInitialEventParams(systemMessage = systemMessage)
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsDeploymentInitialEventParams] to a value of type [T].
     */
    interface Visitor<out T> {

        /** Parameters for sending a user message to the session. */
        fun visitUserMessage(userMessage: BetaManagedAgentsUserMessageEventParams): T

        /**
         * Parameters for defining an outcome the agent should work toward. The agent begins work on
         * receipt.
         */
        fun visitUserDefineOutcome(
            userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams
        ): T

        /**
         * Privileged context for the accompanying turn and all subsequent turns, appended to the
         * session's system context as a `role: "system"` turn rather than replacing the top-level
         * system prompt. At most one per request: it must be the final event and immediately follow
         * the `user.message`, `user.tool_result`, or `user.custom_tool_result` it accompanies. Only
         * supported on models that accept mid-conversation system messages.
         */
        fun visitSystemMessage(systemMessage: BetaManagedAgentsSystemMessageEventParams): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsDeploymentInitialEventParams] to a value of
         * type [T].
         *
         * An instance of [BetaManagedAgentsDeploymentInitialEventParams] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsDeploymentInitialEventParams: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsDeploymentInitialEventParams>(
            BetaManagedAgentsDeploymentInitialEventParams::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsDeploymentInitialEventParams {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "user.message" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserMessageEventParams>(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentInitialEventParams(
                                userMessage = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentInitialEventParams(_json = json)
                }
                "user.define_outcome" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUserDefineOutcomeEventParams>(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentInitialEventParams(
                                userDefineOutcome = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentInitialEventParams(_json = json)
                }
                "system.message" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsSystemMessageEventParams>(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentInitialEventParams(
                                systemMessage = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentInitialEventParams(_json = json)
                }
            }

            return BetaManagedAgentsDeploymentInitialEventParams(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsDeploymentInitialEventParams>(
            BetaManagedAgentsDeploymentInitialEventParams::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsDeploymentInitialEventParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.userMessage != null -> generator.writeObject(value.userMessage)
                value.userDefineOutcome != null -> generator.writeObject(value.userDefineOutcome)
                value.systemMessage != null -> generator.writeObject(value.systemMessage)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaManagedAgentsDeploymentInitialEventParams"
                    )
            }
        }
    }
}
