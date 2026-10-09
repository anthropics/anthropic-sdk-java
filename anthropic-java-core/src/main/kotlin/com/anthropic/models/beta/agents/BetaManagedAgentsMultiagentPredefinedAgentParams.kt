package com.anthropic.models.beta.agents

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentParams
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional

/**
 * One agent in a `predefined_agents` list. It is an agent ID string, an `agent` reference with an
 * optional `version`, or `self` for the agent that owns this configuration.
 */
@JsonDeserialize(using = BetaManagedAgentsMultiagentPredefinedAgentParams.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsMultiagentPredefinedAgentParams.Serializer::class)
class BetaManagedAgentsMultiagentPredefinedAgentParams
private constructor(
    private val string: String? = null,
    private val betaManagedAgentsAgentParams: BetaManagedAgentsAgentParams? = null,
    private val self: BetaManagedAgentsMultiagentSelfParams? = null,
    private val _json: JsonValue? = null,
) {

    fun string(): Optional<String> = Optional.ofNullable(string)

    /**
     * Specification for an Agent. Provide a specific `version` or use the short-form
     * `agent="agent_id"` for the most recent version
     */
    fun betaManagedAgentsAgentParams(): Optional<BetaManagedAgentsAgentParams> =
        Optional.ofNullable(betaManagedAgentsAgentParams)

    /**
     * Sentinel roster entry meaning "the agent that owns this configuration". Resolved server-side
     * to a concrete agent reference.
     */
    fun self(): Optional<BetaManagedAgentsMultiagentSelfParams> = Optional.ofNullable(self)

    fun isString(): Boolean = string != null

    fun isBetaManagedAgentsAgentParams(): Boolean = betaManagedAgentsAgentParams != null

    fun isSelf(): Boolean = self != null

    fun asString(): String = string.getOrThrow("string")

    /**
     * Specification for an Agent. Provide a specific `version` or use the short-form
     * `agent="agent_id"` for the most recent version
     */
    fun asBetaManagedAgentsAgentParams(): BetaManagedAgentsAgentParams =
        betaManagedAgentsAgentParams.getOrThrow("betaManagedAgentsAgentParams")

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
     * Optional<String> result = betaManagedAgentsMultiagentPredefinedAgentParams.accept(new BetaManagedAgentsMultiagentPredefinedAgentParams.Visitor<Optional<String>>() {
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
            betaManagedAgentsAgentParams != null ->
                visitor.visitBetaManagedAgentsAgentParams(betaManagedAgentsAgentParams)
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
    fun validate(): BetaManagedAgentsMultiagentPredefinedAgentParams = apply {
        if (validated) {
            return@apply
        }

        when {
            string != null -> {}
            betaManagedAgentsAgentParams != null -> betaManagedAgentsAgentParams.validate()
            self != null -> self.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaManagedAgentsMultiagentPredefinedAgentParams: $_json"
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
            string != null -> 1
            betaManagedAgentsAgentParams != null -> betaManagedAgentsAgentParams.validity()
            self != null -> self.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMultiagentPredefinedAgentParams &&
            string == other.string &&
            betaManagedAgentsAgentParams == other.betaManagedAgentsAgentParams &&
            self == other.self
    }

    override fun hashCode(): Int = Objects.hash(string, betaManagedAgentsAgentParams, self)

    override fun toString(): String =
        when {
            string != null -> "BetaManagedAgentsMultiagentPredefinedAgentParams{string=$string}"
            betaManagedAgentsAgentParams != null ->
                "BetaManagedAgentsMultiagentPredefinedAgentParams{betaManagedAgentsAgentParams=$betaManagedAgentsAgentParams}"
            self != null -> "BetaManagedAgentsMultiagentPredefinedAgentParams{self=$self}"
            _json != null -> "BetaManagedAgentsMultiagentPredefinedAgentParams{_unknown=$_json}"
            else ->
                throw IllegalStateException(
                    "Invalid BetaManagedAgentsMultiagentPredefinedAgentParams"
                )
        }

    companion object {

        @JvmStatic
        fun ofString(string: String) =
            BetaManagedAgentsMultiagentPredefinedAgentParams(string = string)

        /**
         * Specification for an Agent. Provide a specific `version` or use the short-form
         * `agent="agent_id"` for the most recent version
         */
        @JvmStatic
        fun ofBetaManagedAgentsAgentParams(
            betaManagedAgentsAgentParams: BetaManagedAgentsAgentParams
        ) =
            BetaManagedAgentsMultiagentPredefinedAgentParams(
                betaManagedAgentsAgentParams = betaManagedAgentsAgentParams
            )

        /**
         * Sentinel roster entry meaning "the agent that owns this configuration". Resolved
         * server-side to a concrete agent reference.
         */
        @JvmStatic
        fun ofSelf(self: BetaManagedAgentsMultiagentSelfParams) =
            BetaManagedAgentsMultiagentPredefinedAgentParams(self = self)

        /**
         * Returns an immutable instance of [BetaManagedAgentsMultiagentPredefinedAgentParams] whose
         * [ofSelf] variant is built from the given required [type].
         */
        @JvmStatic
        fun ofSelf(type: BetaManagedAgentsMultiagentSelfParams.Type) =
            ofSelf(BetaManagedAgentsMultiagentSelfParams.of(type))
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsMultiagentPredefinedAgentParams] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitString(string: String): T

        /**
         * Specification for an Agent. Provide a specific `version` or use the short-form
         * `agent="agent_id"` for the most recent version
         */
        fun visitBetaManagedAgentsAgentParams(
            betaManagedAgentsAgentParams: BetaManagedAgentsAgentParams
        ): T

        /**
         * Sentinel roster entry meaning "the agent that owns this configuration". Resolved
         * server-side to a concrete agent reference.
         */
        fun visitSelf(self: BetaManagedAgentsMultiagentSelfParams): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsMultiagentPredefinedAgentParams] to a value
         * of type [T].
         *
         * An instance of [BetaManagedAgentsMultiagentPredefinedAgentParams] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsMultiagentPredefinedAgentParams: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsMultiagentPredefinedAgentParams>(
            BetaManagedAgentsMultiagentPredefinedAgentParams::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsMultiagentPredefinedAgentParams {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsAgentParams>())?.let {
                            BetaManagedAgentsMultiagentPredefinedAgentParams(
                                betaManagedAgentsAgentParams = it,
                                _json = json,
                            )
                        },
                        tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsMultiagentSelfParams>(),
                            )
                            ?.let {
                                BetaManagedAgentsMultiagentPredefinedAgentParams(
                                    self = it,
                                    _json = json,
                                )
                            },
                        tryDeserialize(node, jacksonTypeRef<String>())?.let {
                            BetaManagedAgentsMultiagentPredefinedAgentParams(
                                string = it,
                                _json = json,
                            )
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> BetaManagedAgentsMultiagentPredefinedAgentParams(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsMultiagentPredefinedAgentParams>(
            BetaManagedAgentsMultiagentPredefinedAgentParams::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsMultiagentPredefinedAgentParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.string != null -> generator.writeObject(value.string)
                value.betaManagedAgentsAgentParams != null ->
                    generator.writeObject(value.betaManagedAgentsAgentParams)
                value.self != null -> generator.writeObject(value.self)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaManagedAgentsMultiagentPredefinedAgentParams"
                    )
            }
        }
    }
}
