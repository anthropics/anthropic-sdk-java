package com.anthropic.models.beta.agents

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/** Resolved multiagent configuration with three members, each enabled or disabled on its own. */
class BetaManagedAgentsMultiagent20261001
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val advisor: JsonField<BetaManagedAgentsMultiagentAdvisor>,
    private val subagents: JsonField<BetaManagedAgentsMultiagentSubagents>,
    private val type: JsonValue,
    private val workflows: JsonField<BetaManagedAgentsMultiagentWorkflows>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("advisor")
        @ExcludeMissing
        advisor: JsonField<BetaManagedAgentsMultiagentAdvisor> = JsonMissing.of(),
        @JsonProperty("subagents")
        @ExcludeMissing
        subagents: JsonField<BetaManagedAgentsMultiagentSubagents> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workflows")
        @ExcludeMissing
        workflows: JsonField<BetaManagedAgentsMultiagentWorkflows> = JsonMissing.of(),
    ) : this(advisor, subagents, type, workflows, mutableMapOf())

    /**
     * Whether the session's primary thread can consult an advisor model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun advisor(): BetaManagedAgentsMultiagentAdvisor = advisor.getRequired("advisor")

    /**
     * Whether the agent can spawn session threads.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun subagents(): BetaManagedAgentsMultiagentSubagents = subagents.getRequired("subagents")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("multiagent_20261001")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Whether the agent can start workflow runs.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workflows(): BetaManagedAgentsMultiagentWorkflows = workflows.getRequired("workflows")

    /**
     * Returns the raw JSON value of [advisor].
     *
     * Unlike [advisor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("advisor")
    @ExcludeMissing
    fun _advisor(): JsonField<BetaManagedAgentsMultiagentAdvisor> = advisor

    /**
     * Returns the raw JSON value of [subagents].
     *
     * Unlike [subagents], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("subagents")
    @ExcludeMissing
    fun _subagents(): JsonField<BetaManagedAgentsMultiagentSubagents> = subagents

    /**
     * Returns the raw JSON value of [workflows].
     *
     * Unlike [workflows], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workflows")
    @ExcludeMissing
    fun _workflows(): JsonField<BetaManagedAgentsMultiagentWorkflows> = workflows

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
         * [BetaManagedAgentsMultiagent20261001].
         *
         * The following fields are required:
         * ```java
         * .advisor()
         * .subagents()
         * .workflows()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMultiagent20261001]. */
    class Builder internal constructor() {

        private var advisor: JsonField<BetaManagedAgentsMultiagentAdvisor>? = null
        private var subagents: JsonField<BetaManagedAgentsMultiagentSubagents>? = null
        private var type: JsonValue = JsonValue.from("multiagent_20261001")
        private var workflows: JsonField<BetaManagedAgentsMultiagentWorkflows>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsMultiagent20261001: BetaManagedAgentsMultiagent20261001
        ) = apply {
            advisor = betaManagedAgentsMultiagent20261001.advisor
            subagents = betaManagedAgentsMultiagent20261001.subagents
            type = betaManagedAgentsMultiagent20261001.type
            workflows = betaManagedAgentsMultiagent20261001.workflows
            additionalProperties =
                betaManagedAgentsMultiagent20261001.additionalProperties.toMutableMap()
        }

        /** Whether the session's primary thread can consult an advisor model. */
        fun advisor(advisor: BetaManagedAgentsMultiagentAdvisor) = advisor(JsonField.of(advisor))

        /**
         * Sets [Builder.advisor] to an arbitrary JSON value.
         *
         * You should usually call [Builder.advisor] with a well-typed
         * [BetaManagedAgentsMultiagentAdvisor] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun advisor(advisor: JsonField<BetaManagedAgentsMultiagentAdvisor>) = apply {
            this.advisor = advisor
        }

        /**
         * Alias for calling [advisor] with `BetaManagedAgentsMultiagentAdvisor.ofEnabled(enabled)`.
         */
        fun advisor(enabled: BetaManagedAgentsMultiagentAdvisorEnabled) =
            advisor(BetaManagedAgentsMultiagentAdvisor.ofEnabled(enabled))

        /**
         * Alias for calling [advisor] with the following:
         * ```java
         * BetaManagedAgentsMultiagentAdvisorEnabled.builder()
         *     .model(model)
         *     .build()
         * ```
         */
        fun enabledAdvisor(model: String) =
            advisor(BetaManagedAgentsMultiagentAdvisorEnabled.builder().model(model).build())

        /**
         * Alias for calling [advisor] with
         * `BetaManagedAgentsMultiagentAdvisor.ofDisabled(disabled)`.
         */
        fun advisor(disabled: BetaManagedAgentsMultiagentAdvisorDisabled) =
            advisor(BetaManagedAgentsMultiagentAdvisor.ofDisabled(disabled))

        /** Whether the agent can spawn session threads. */
        fun subagents(subagents: BetaManagedAgentsMultiagentSubagents) =
            subagents(JsonField.of(subagents))

        /**
         * Sets [Builder.subagents] to an arbitrary JSON value.
         *
         * You should usually call [Builder.subagents] with a well-typed
         * [BetaManagedAgentsMultiagentSubagents] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun subagents(subagents: JsonField<BetaManagedAgentsMultiagentSubagents>) = apply {
            this.subagents = subagents
        }

        /**
         * Alias for calling [subagents] with
         * `BetaManagedAgentsMultiagentSubagents.ofEnabled(enabled)`.
         */
        fun subagents(enabled: BetaManagedAgentsMultiagentSubagentsEnabled) =
            subagents(BetaManagedAgentsMultiagentSubagents.ofEnabled(enabled))

        /**
         * Alias for calling [subagents] with
         * `BetaManagedAgentsMultiagentSubagents.ofDisabled(disabled)`.
         */
        fun subagents(disabled: BetaManagedAgentsMultiagentSubagentsDisabled) =
            subagents(BetaManagedAgentsMultiagentSubagents.ofDisabled(disabled))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("multiagent_20261001")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Whether the agent can start workflow runs. */
        fun workflows(workflows: BetaManagedAgentsMultiagentWorkflows) =
            workflows(JsonField.of(workflows))

        /**
         * Sets [Builder.workflows] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workflows] with a well-typed
         * [BetaManagedAgentsMultiagentWorkflows] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun workflows(workflows: JsonField<BetaManagedAgentsMultiagentWorkflows>) = apply {
            this.workflows = workflows
        }

        /**
         * Alias for calling [workflows] with
         * `BetaManagedAgentsMultiagentWorkflows.ofEnabled(enabled)`.
         */
        fun workflows(enabled: BetaManagedAgentsMultiagentWorkflowsEnabled) =
            workflows(BetaManagedAgentsMultiagentWorkflows.ofEnabled(enabled))

        /**
         * Alias for calling [workflows] with
         * `BetaManagedAgentsMultiagentWorkflows.ofDisabled(disabled)`.
         */
        fun workflows(disabled: BetaManagedAgentsMultiagentWorkflowsDisabled) =
            workflows(BetaManagedAgentsMultiagentWorkflows.ofDisabled(disabled))

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
         * Returns an immutable instance of [BetaManagedAgentsMultiagent20261001].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .advisor()
         * .subagents()
         * .workflows()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMultiagent20261001 =
            BetaManagedAgentsMultiagent20261001(
                checkRequired("advisor", advisor),
                checkRequired("subagents", subagents),
                type,
                checkRequired("workflows", workflows),
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
    fun validate(): BetaManagedAgentsMultiagent20261001 = apply {
        if (validated) {
            return@apply
        }

        advisor().validate()
        subagents().validate()
        _type().let {
            if (it != JsonValue.from("multiagent_20261001")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        workflows().validate()
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
        (advisor.asKnown().getOrNull()?.validity() ?: 0) +
            (subagents.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("multiagent_20261001")) 1 else 0 } +
            (workflows.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMultiagent20261001 &&
            advisor == other.advisor &&
            subagents == other.subagents &&
            type == other.type &&
            workflows == other.workflows &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(advisor, subagents, type, workflows, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMultiagent20261001{advisor=$advisor, subagents=$subagents, type=$type, workflows=$workflows, additionalProperties=$additionalProperties}"
}
