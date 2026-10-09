package com.anthropic.models.beta.agents

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentParams
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * The agent can spawn session threads. Each thread runs a predefined agent, which is a saved agent
 * in `predefined_agents`, or an inline agent, which the agent defines when it spawns the thread and
 * which is not saved. If `inline_agents` is disabled, `predefined_agents` must name at least one
 * agent.
 */
class BetaManagedAgentsMultiagentSubagentsEnabledParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val inlineAgents: JsonField<BetaManagedAgentsMultiagentInlineAgentsParams>,
    private val predefinedAgents: JsonField<List<BetaManagedAgentsMultiagentPredefinedAgentParams>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("inline_agents")
        @ExcludeMissing
        inlineAgents: JsonField<BetaManagedAgentsMultiagentInlineAgentsParams> = JsonMissing.of(),
        @JsonProperty("predefined_agents")
        @ExcludeMissing
        predefinedAgents: JsonField<List<BetaManagedAgentsMultiagentPredefinedAgentParams>> =
            JsonMissing.of(),
    ) : this(type, inlineAgents, predefinedAgents, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("enabled")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Whether the agent can define inline agents when it spawns session threads. Defaults to
     * enabled.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun inlineAgents(): Optional<BetaManagedAgentsMultiagentInlineAgentsParams> =
        inlineAgents.getOptional("inline_agents")

    /**
     * Predefined agents that this agent can spawn as session threads. At most 20. Defaults to null.
     * Null and an empty list both mean no predefined agents. This list is separate from
     * `workflows.predefined_agents`, and an agent in one list is not added to the other.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun predefinedAgents(): Optional<List<BetaManagedAgentsMultiagentPredefinedAgentParams>> =
        predefinedAgents.getOptional("predefined_agents")

    /**
     * Returns the raw JSON value of [inlineAgents].
     *
     * Unlike [inlineAgents], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("inline_agents")
    @ExcludeMissing
    fun _inlineAgents(): JsonField<BetaManagedAgentsMultiagentInlineAgentsParams> = inlineAgents

    /**
     * Returns the raw JSON value of [predefinedAgents].
     *
     * Unlike [predefinedAgents], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("predefined_agents")
    @ExcludeMissing
    fun _predefinedAgents(): JsonField<List<BetaManagedAgentsMultiagentPredefinedAgentParams>> =
        predefinedAgents

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [BetaManagedAgentsMultiagentSubagentsEnabledParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMultiagentSubagentsEnabledParams]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("enabled")
        private var inlineAgents: JsonField<BetaManagedAgentsMultiagentInlineAgentsParams> =
            JsonMissing.of()
        private var predefinedAgents:
            JsonField<MutableList<BetaManagedAgentsMultiagentPredefinedAgentParams>>? =
            null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsMultiagentSubagentsEnabledParams:
                BetaManagedAgentsMultiagentSubagentsEnabledParams
        ) = apply {
            type = betaManagedAgentsMultiagentSubagentsEnabledParams.type
            inlineAgents = betaManagedAgentsMultiagentSubagentsEnabledParams.inlineAgents
            predefinedAgents =
                betaManagedAgentsMultiagentSubagentsEnabledParams.predefinedAgents
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties =
                betaManagedAgentsMultiagentSubagentsEnabledParams.additionalProperties
                    .toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("enabled")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Whether the agent can define inline agents when it spawns session threads. Defaults to
         * enabled.
         */
        fun inlineAgents(inlineAgents: BetaManagedAgentsMultiagentInlineAgentsParams?) =
            inlineAgents(JsonField.ofNullable(inlineAgents))

        /** Alias for calling [Builder.inlineAgents] with `inlineAgents.orElse(null)`. */
        fun inlineAgents(inlineAgents: Optional<BetaManagedAgentsMultiagentInlineAgentsParams>) =
            inlineAgents(inlineAgents.getOrNull())

        /**
         * Sets [Builder.inlineAgents] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inlineAgents] with a well-typed
         * [BetaManagedAgentsMultiagentInlineAgentsParams] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun inlineAgents(inlineAgents: JsonField<BetaManagedAgentsMultiagentInlineAgentsParams>) =
            apply {
                this.inlineAgents = inlineAgents
            }

        /**
         * Alias for calling [inlineAgents] with
         * `BetaManagedAgentsMultiagentInlineAgentsParams.ofEnabled(enabled)`.
         */
        fun inlineAgents(enabled: BetaManagedAgentsMultiagentInlineAgentsEnabledParams) =
            inlineAgents(BetaManagedAgentsMultiagentInlineAgentsParams.ofEnabled(enabled))

        /**
         * Alias for calling [inlineAgents] with
         * `BetaManagedAgentsMultiagentInlineAgentsParams.ofDisabled(disabled)`.
         */
        fun inlineAgents(disabled: BetaManagedAgentsMultiagentInlineAgentsDisabledParams) =
            inlineAgents(BetaManagedAgentsMultiagentInlineAgentsParams.ofDisabled(disabled))

        /**
         * Predefined agents that this agent can spawn as session threads. At most 20. Defaults to
         * null. Null and an empty list both mean no predefined agents. This list is separate from
         * `workflows.predefined_agents`, and an agent in one list is not added to the other.
         */
        fun predefinedAgents(
            predefinedAgents: List<BetaManagedAgentsMultiagentPredefinedAgentParams>?
        ) = predefinedAgents(JsonField.ofNullable(predefinedAgents))

        /** Alias for calling [Builder.predefinedAgents] with `predefinedAgents.orElse(null)`. */
        fun predefinedAgents(
            predefinedAgents: Optional<List<BetaManagedAgentsMultiagentPredefinedAgentParams>>
        ) = predefinedAgents(predefinedAgents.getOrNull())

        /**
         * Sets [Builder.predefinedAgents] to an arbitrary JSON value.
         *
         * You should usually call [Builder.predefinedAgents] with a well-typed
         * `List<BetaManagedAgentsMultiagentPredefinedAgentParams>` value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun predefinedAgents(
            predefinedAgents: JsonField<List<BetaManagedAgentsMultiagentPredefinedAgentParams>>
        ) = apply { this.predefinedAgents = predefinedAgents.map { it.toMutableList() } }

        /**
         * Adds a single [BetaManagedAgentsMultiagentPredefinedAgentParams] to [predefinedAgents].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPredefinedAgent(predefinedAgent: BetaManagedAgentsMultiagentPredefinedAgentParams) =
            apply {
                predefinedAgents =
                    (predefinedAgents ?: JsonField.of(mutableListOf())).also {
                        checkKnown("predefinedAgents", it).add(predefinedAgent)
                    }
            }

        /**
         * Alias for calling [addPredefinedAgent] with
         * `BetaManagedAgentsMultiagentPredefinedAgentParams.ofString(string)`.
         */
        fun addPredefinedAgent(string: String) =
            addPredefinedAgent(BetaManagedAgentsMultiagentPredefinedAgentParams.ofString(string))

        /**
         * Alias for calling [addPredefinedAgent] with
         * `BetaManagedAgentsMultiagentPredefinedAgentParams.ofBetaManagedAgentsAgentParams(betaManagedAgentsAgentParams)`.
         */
        fun addPredefinedAgent(betaManagedAgentsAgentParams: BetaManagedAgentsAgentParams) =
            addPredefinedAgent(
                BetaManagedAgentsMultiagentPredefinedAgentParams.ofBetaManagedAgentsAgentParams(
                    betaManagedAgentsAgentParams
                )
            )

        /**
         * Alias for calling [addPredefinedAgent] with
         * `BetaManagedAgentsMultiagentPredefinedAgentParams.ofSelf(self)`.
         */
        fun addPredefinedAgent(self: BetaManagedAgentsMultiagentSelfParams) =
            addPredefinedAgent(BetaManagedAgentsMultiagentPredefinedAgentParams.ofSelf(self))

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaManagedAgentsMultiagentSubagentsEnabledParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsMultiagentSubagentsEnabledParams =
            BetaManagedAgentsMultiagentSubagentsEnabledParams(
                type,
                inlineAgents,
                (predefinedAgents ?: JsonMissing.of()).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
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
    fun validate(): BetaManagedAgentsMultiagentSubagentsEnabledParams = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("enabled")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        inlineAgents().ifPresent { it.validate() }
        predefinedAgents().ifPresent { it.forEach { it.validate() } }
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
        type.let { if (it == JsonValue.from("enabled")) 1 else 0 } +
            (inlineAgents.asKnown().getOrNull()?.validity() ?: 0) +
            (predefinedAgents.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMultiagentSubagentsEnabledParams &&
            type == other.type &&
            inlineAgents == other.inlineAgents &&
            predefinedAgents == other.predefinedAgents &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, inlineAgents, predefinedAgents, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMultiagentSubagentsEnabledParams{type=$type, inlineAgents=$inlineAgents, predefinedAgents=$predefinedAgents, additionalProperties=$additionalProperties}"
}
