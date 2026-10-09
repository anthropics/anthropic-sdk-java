package com.anthropic.models.beta.sessions

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentInlineAgents
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentInlineAgentsDisabled
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentInlineAgentsEnabled
import com.anthropic.models.beta.agents.BetaManagedAgentsSessionThreadAgent
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/** The agent can start workflow runs. */
class BetaManagedAgentsSessionMultiagentWorkflowsEnabled
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val inlineAgents: JsonField<BetaManagedAgentsMultiagentInlineAgents>,
    private val predefinedAgents: JsonField<List<BetaManagedAgentsSessionThreadAgent>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("inline_agents")
        @ExcludeMissing
        inlineAgents: JsonField<BetaManagedAgentsMultiagentInlineAgents> = JsonMissing.of(),
        @JsonProperty("predefined_agents")
        @ExcludeMissing
        predefinedAgents: JsonField<List<BetaManagedAgentsSessionThreadAgent>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(inlineAgents, predefinedAgents, type, mutableMapOf())

    /**
     * Whether a run's plan can define inline agents, which are not saved.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun inlineAgents(): BetaManagedAgentsMultiagentInlineAgents =
        inlineAgents.getRequired("inline_agents")

    /**
     * Full `agent` definitions of the predefined agents, which are saved agents that a run's plan
     * can use.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun predefinedAgents(): List<BetaManagedAgentsSessionThreadAgent> =
        predefinedAgents.getRequired("predefined_agents")

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
     * Returns the raw JSON value of [inlineAgents].
     *
     * Unlike [inlineAgents], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("inline_agents")
    @ExcludeMissing
    fun _inlineAgents(): JsonField<BetaManagedAgentsMultiagentInlineAgents> = inlineAgents

    /**
     * Returns the raw JSON value of [predefinedAgents].
     *
     * Unlike [predefinedAgents], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("predefined_agents")
    @ExcludeMissing
    fun _predefinedAgents(): JsonField<List<BetaManagedAgentsSessionThreadAgent>> = predefinedAgents

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
         * [BetaManagedAgentsSessionMultiagentWorkflowsEnabled].
         *
         * The following fields are required:
         * ```java
         * .inlineAgents()
         * .predefinedAgents()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionMultiagentWorkflowsEnabled]. */
    class Builder internal constructor() {

        private var inlineAgents: JsonField<BetaManagedAgentsMultiagentInlineAgents>? = null
        private var predefinedAgents: JsonField<MutableList<BetaManagedAgentsSessionThreadAgent>>? =
            null
        private var type: JsonValue = JsonValue.from("enabled")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSessionMultiagentWorkflowsEnabled:
                BetaManagedAgentsSessionMultiagentWorkflowsEnabled
        ) = apply {
            inlineAgents = betaManagedAgentsSessionMultiagentWorkflowsEnabled.inlineAgents
            predefinedAgents =
                betaManagedAgentsSessionMultiagentWorkflowsEnabled.predefinedAgents
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaManagedAgentsSessionMultiagentWorkflowsEnabled.type
            additionalProperties =
                betaManagedAgentsSessionMultiagentWorkflowsEnabled.additionalProperties
                    .toMutableMap()
        }

        /** Whether a run's plan can define inline agents, which are not saved. */
        fun inlineAgents(inlineAgents: BetaManagedAgentsMultiagentInlineAgents) =
            inlineAgents(JsonField.of(inlineAgents))

        /**
         * Sets [Builder.inlineAgents] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inlineAgents] with a well-typed
         * [BetaManagedAgentsMultiagentInlineAgents] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun inlineAgents(inlineAgents: JsonField<BetaManagedAgentsMultiagentInlineAgents>) = apply {
            this.inlineAgents = inlineAgents
        }

        /**
         * Alias for calling [inlineAgents] with
         * `BetaManagedAgentsMultiagentInlineAgents.ofEnabled(enabled)`.
         */
        fun inlineAgents(enabled: BetaManagedAgentsMultiagentInlineAgentsEnabled) =
            inlineAgents(BetaManagedAgentsMultiagentInlineAgents.ofEnabled(enabled))

        /**
         * Alias for calling [inlineAgents] with
         * `BetaManagedAgentsMultiagentInlineAgents.ofDisabled(disabled)`.
         */
        fun inlineAgents(disabled: BetaManagedAgentsMultiagentInlineAgentsDisabled) =
            inlineAgents(BetaManagedAgentsMultiagentInlineAgents.ofDisabled(disabled))

        /**
         * Full `agent` definitions of the predefined agents, which are saved agents that a run's
         * plan can use.
         */
        fun predefinedAgents(predefinedAgents: List<BetaManagedAgentsSessionThreadAgent>) =
            predefinedAgents(JsonField.of(predefinedAgents))

        /**
         * Sets [Builder.predefinedAgents] to an arbitrary JSON value.
         *
         * You should usually call [Builder.predefinedAgents] with a well-typed
         * `List<BetaManagedAgentsSessionThreadAgent>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun predefinedAgents(
            predefinedAgents: JsonField<List<BetaManagedAgentsSessionThreadAgent>>
        ) = apply { this.predefinedAgents = predefinedAgents.map { it.toMutableList() } }

        /**
         * Adds a single [BetaManagedAgentsSessionThreadAgent] to [predefinedAgents].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPredefinedAgent(predefinedAgent: BetaManagedAgentsSessionThreadAgent) = apply {
            predefinedAgents =
                (predefinedAgents ?: JsonField.of(mutableListOf())).also {
                    checkKnown("predefinedAgents", it).add(predefinedAgent)
                }
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
         * Returns an immutable instance of [BetaManagedAgentsSessionMultiagentWorkflowsEnabled].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .inlineAgents()
         * .predefinedAgents()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSessionMultiagentWorkflowsEnabled =
            BetaManagedAgentsSessionMultiagentWorkflowsEnabled(
                checkRequired("inlineAgents", inlineAgents),
                checkRequired("predefinedAgents", predefinedAgents).map { it.toImmutable() },
                type,
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
    fun validate(): BetaManagedAgentsSessionMultiagentWorkflowsEnabled = apply {
        if (validated) {
            return@apply
        }

        inlineAgents().validate()
        predefinedAgents().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("enabled")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
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
        (inlineAgents.asKnown().getOrNull()?.validity() ?: 0) +
            (predefinedAgents.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            type.let { if (it == JsonValue.from("enabled")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionMultiagentWorkflowsEnabled &&
            inlineAgents == other.inlineAgents &&
            predefinedAgents == other.predefinedAgents &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(inlineAgents, predefinedAgents, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionMultiagentWorkflowsEnabled{inlineAgents=$inlineAgents, predefinedAgents=$predefinedAgents, type=$type, additionalProperties=$additionalProperties}"
}
