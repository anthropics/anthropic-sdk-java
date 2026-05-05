// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentSelfParams
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional

/** An entry in a multiagent roster: an agent ID string, a versioned agent reference, or `self`. */
@JsonDeserialize(using = BetaManagedAgentsMultiagentRosterEntryParams.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsMultiagentRosterEntryParams.Serializer::class)
class BetaManagedAgentsMultiagentRosterEntryParams
private constructor(
    private val string: String? = null,
    private val agent: BetaManagedAgentsAgentParams? = null,
    private val self: BetaManagedAgentsMultiagentSelfParams? = null,
    private val _json: JsonValue? = null,
) {

    fun string(): Optional<String> = Optional.ofNullable(string)

    /**
     * Specification for an Agent. Provide a specific `version` or use the short-form
     * `agent="agent_id"` for the most recent version
     */
    fun agent(): Optional<BetaManagedAgentsAgentParams> = Optional.ofNullable(agent)

    /**
     * Sentinel roster entry meaning "the agent that owns this configuration". Resolved server-side
     * to a concrete agent reference.
     */
    fun self(): Optional<BetaManagedAgentsMultiagentSelfParams> = Optional.ofNullable(self)

    fun isString(): Boolean = string != null

    fun isAgent(): Boolean = agent != null

    fun isSelf(): Boolean = self != null

    fun asString(): String = string.getOrThrow("string")

    /**
     * Specification for an Agent. Provide a specific `version` or use the short-form
     * `agent="agent_id"` for the most recent version
     */
    fun asAgent(): BetaManagedAgentsAgentParams = agent.getOrThrow("agent")

    /**
     * Sentinel roster entry meaning "the agent that owns this configuration". Resolved server-side
     * to a concrete agent reference.
     */
    fun asSelf(): BetaManagedAgentsMultiagentSelfParams = self.getOrThrow("self")

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
     * Optional<String> result = betaManagedAgentsMultiagentRosterEntryParams.accept(new BetaManagedAgentsMultiagentRosterEntryParams.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitString(String string) {
     *         return Optional.of(string.toString());
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
            string != null -> visitor.visitString(string)
            agent != null -> visitor.visitAgent(agent)
            self != null -> visitor.visitSelf(self)
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
    fun validate(): BetaManagedAgentsMultiagentRosterEntryParams = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitString(string: String) {}

                override fun visitAgent(agent: BetaManagedAgentsAgentParams) {
                    agent.validate()
                }

                override fun visitSelf(self: BetaManagedAgentsMultiagentSelfParams) {
                    self.validate()
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
                override fun visitString(string: String) = 1

                override fun visitAgent(agent: BetaManagedAgentsAgentParams) = agent.validity()

                override fun visitSelf(self: BetaManagedAgentsMultiagentSelfParams) =
                    self.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMultiagentRosterEntryParams &&
            string == other.string &&
            agent == other.agent &&
            self == other.self
    }

    override fun hashCode(): Int = Objects.hash(string, agent, self)

    override fun toString(): String =
        when {
            string != null -> "BetaManagedAgentsMultiagentRosterEntryParams{string=$string}"
            agent != null -> "BetaManagedAgentsMultiagentRosterEntryParams{agent=$agent}"
            self != null -> "BetaManagedAgentsMultiagentRosterEntryParams{self=$self}"
            _json != null -> "BetaManagedAgentsMultiagentRosterEntryParams{_unknown=$_json}"
            else ->
                throw IllegalStateException("Invalid BetaManagedAgentsMultiagentRosterEntryParams")
        }

    companion object {

        @JvmStatic
        fun ofString(string: String) = BetaManagedAgentsMultiagentRosterEntryParams(string = string)

        /**
         * Specification for an Agent. Provide a specific `version` or use the short-form
         * `agent="agent_id"` for the most recent version
         */
        @JvmStatic
        fun ofAgent(agent: BetaManagedAgentsAgentParams) =
            BetaManagedAgentsMultiagentRosterEntryParams(agent = agent)

        /**
         * Sentinel roster entry meaning "the agent that owns this configuration". Resolved
         * server-side to a concrete agent reference.
         */
        @JvmStatic
        fun ofSelf(self: BetaManagedAgentsMultiagentSelfParams) =
            BetaManagedAgentsMultiagentRosterEntryParams(self = self)
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsMultiagentRosterEntryParams] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitString(string: String): T

        /**
         * Specification for an Agent. Provide a specific `version` or use the short-form
         * `agent="agent_id"` for the most recent version
         */
        fun visitAgent(agent: BetaManagedAgentsAgentParams): T

        /**
         * Sentinel roster entry meaning "the agent that owns this configuration". Resolved
         * server-side to a concrete agent reference.
         */
        fun visitSelf(self: BetaManagedAgentsMultiagentSelfParams): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsMultiagentRosterEntryParams] to a value of
         * type [T].
         *
         * An instance of [BetaManagedAgentsMultiagentRosterEntryParams] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsMultiagentRosterEntryParams: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsMultiagentRosterEntryParams>(
            BetaManagedAgentsMultiagentRosterEntryParams::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsMultiagentRosterEntryParams {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsAgentParams>())?.let {
                            BetaManagedAgentsMultiagentRosterEntryParams(agent = it, _json = json)
                        },
                        tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsMultiagentSelfParams>(),
                            )
                            ?.let {
                                BetaManagedAgentsMultiagentRosterEntryParams(
                                    self = it,
                                    _json = json,
                                )
                            },
                        tryDeserialize(node, jacksonTypeRef<String>())?.let {
                            BetaManagedAgentsMultiagentRosterEntryParams(string = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> BetaManagedAgentsMultiagentRosterEntryParams(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsMultiagentRosterEntryParams>(
            BetaManagedAgentsMultiagentRosterEntryParams::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsMultiagentRosterEntryParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.string != null -> generator.writeObject(value.string)
                value.agent != null -> generator.writeObject(value.agent)
                value.self != null -> generator.writeObject(value.self)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaManagedAgentsMultiagentRosterEntryParams"
                    )
            }
        }
    }
}
