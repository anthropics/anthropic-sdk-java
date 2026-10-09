package com.anthropic.models.beta.agents

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Multiagent configuration with three members, each enabled or disabled on its own. On an update,
 * if the agent's stored `multiagent` also has type `multiagent_20261001`, this configuration is
 * merged into the stored one, level by level, instead of replacing it. A key that the update omits
 * keeps its stored value. A key sent as null takes its default, on create as well, so `"workflows":
 * null` enables workflows. An object sent with a `type` other than the stored one replaces the
 * stored object, and the keys that it omits take their defaults. A `predefined_agents` list that is
 * sent replaces the stored list. Every object that is sent needs its `type`, and an enabled
 * `advisor` needs its `model`. Other validation applies to the merged result.
 */
class BetaManagedAgentsMultiagent20261001Params
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val advisor: JsonField<BetaManagedAgentsMultiagentAdvisorParams>,
    private val subagents: JsonField<BetaManagedAgentsMultiagentSubagentsParams>,
    private val workflows: JsonField<BetaManagedAgentsMultiagentWorkflowsParams>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("advisor")
        @ExcludeMissing
        advisor: JsonField<BetaManagedAgentsMultiagentAdvisorParams> = JsonMissing.of(),
        @JsonProperty("subagents")
        @ExcludeMissing
        subagents: JsonField<BetaManagedAgentsMultiagentSubagentsParams> = JsonMissing.of(),
        @JsonProperty("workflows")
        @ExcludeMissing
        workflows: JsonField<BetaManagedAgentsMultiagentWorkflowsParams> = JsonMissing.of(),
    ) : this(type, advisor, subagents, workflows, mutableMapOf())

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
     * Whether the session's primary thread can consult an advisor model. Defaults to disabled.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun advisor(): Optional<BetaManagedAgentsMultiagentAdvisorParams> =
        advisor.getOptional("advisor")

    /**
     * Whether the agent can spawn session threads. Defaults to enabled.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun subagents(): Optional<BetaManagedAgentsMultiagentSubagentsParams> =
        subagents.getOptional("subagents")

    /**
     * Whether the agent can start workflow runs. Defaults to enabled.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun workflows(): Optional<BetaManagedAgentsMultiagentWorkflowsParams> =
        workflows.getOptional("workflows")

    /**
     * Returns the raw JSON value of [advisor].
     *
     * Unlike [advisor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("advisor")
    @ExcludeMissing
    fun _advisor(): JsonField<BetaManagedAgentsMultiagentAdvisorParams> = advisor

    /**
     * Returns the raw JSON value of [subagents].
     *
     * Unlike [subagents], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("subagents")
    @ExcludeMissing
    fun _subagents(): JsonField<BetaManagedAgentsMultiagentSubagentsParams> = subagents

    /**
     * Returns the raw JSON value of [workflows].
     *
     * Unlike [workflows], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workflows")
    @ExcludeMissing
    fun _workflows(): JsonField<BetaManagedAgentsMultiagentWorkflowsParams> = workflows

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
         * [BetaManagedAgentsMultiagent20261001Params].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMultiagent20261001Params]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("multiagent_20261001")
        private var advisor: JsonField<BetaManagedAgentsMultiagentAdvisorParams> = JsonMissing.of()
        private var subagents: JsonField<BetaManagedAgentsMultiagentSubagentsParams> =
            JsonMissing.of()
        private var workflows: JsonField<BetaManagedAgentsMultiagentWorkflowsParams> =
            JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsMultiagent20261001Params: BetaManagedAgentsMultiagent20261001Params
        ) = apply {
            type = betaManagedAgentsMultiagent20261001Params.type
            advisor = betaManagedAgentsMultiagent20261001Params.advisor
            subagents = betaManagedAgentsMultiagent20261001Params.subagents
            workflows = betaManagedAgentsMultiagent20261001Params.workflows
            additionalProperties =
                betaManagedAgentsMultiagent20261001Params.additionalProperties.toMutableMap()
        }

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

        /**
         * Whether the session's primary thread can consult an advisor model. Defaults to disabled.
         */
        fun advisor(advisor: BetaManagedAgentsMultiagentAdvisorParams?) =
            advisor(JsonField.ofNullable(advisor))

        /** Alias for calling [Builder.advisor] with `advisor.orElse(null)`. */
        fun advisor(advisor: Optional<BetaManagedAgentsMultiagentAdvisorParams>) =
            advisor(advisor.getOrNull())

        /**
         * Sets [Builder.advisor] to an arbitrary JSON value.
         *
         * You should usually call [Builder.advisor] with a well-typed
         * [BetaManagedAgentsMultiagentAdvisorParams] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun advisor(advisor: JsonField<BetaManagedAgentsMultiagentAdvisorParams>) = apply {
            this.advisor = advisor
        }

        /**
         * Alias for calling [advisor] with
         * `BetaManagedAgentsMultiagentAdvisorParams.ofEnabled(enabled)`.
         */
        fun advisor(enabled: BetaManagedAgentsMultiagentAdvisorEnabledParams) =
            advisor(BetaManagedAgentsMultiagentAdvisorParams.ofEnabled(enabled))

        /**
         * Alias for calling [advisor] with the following:
         * ```java
         * BetaManagedAgentsMultiagentAdvisorEnabledParams.builder()
         *     .model(model)
         *     .build()
         * ```
         */
        fun enabledAdvisor(model: String) =
            advisor(BetaManagedAgentsMultiagentAdvisorEnabledParams.builder().model(model).build())

        /**
         * Alias for calling [advisor] with
         * `BetaManagedAgentsMultiagentAdvisorParams.ofDisabled(disabled)`.
         */
        fun advisor(disabled: BetaManagedAgentsMultiagentAdvisorDisabledParams) =
            advisor(BetaManagedAgentsMultiagentAdvisorParams.ofDisabled(disabled))

        /** Whether the agent can spawn session threads. Defaults to enabled. */
        fun subagents(subagents: BetaManagedAgentsMultiagentSubagentsParams?) =
            subagents(JsonField.ofNullable(subagents))

        /** Alias for calling [Builder.subagents] with `subagents.orElse(null)`. */
        fun subagents(subagents: Optional<BetaManagedAgentsMultiagentSubagentsParams>) =
            subagents(subagents.getOrNull())

        /**
         * Sets [Builder.subagents] to an arbitrary JSON value.
         *
         * You should usually call [Builder.subagents] with a well-typed
         * [BetaManagedAgentsMultiagentSubagentsParams] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun subagents(subagents: JsonField<BetaManagedAgentsMultiagentSubagentsParams>) = apply {
            this.subagents = subagents
        }

        /**
         * Alias for calling [subagents] with
         * `BetaManagedAgentsMultiagentSubagentsParams.ofEnabled(enabled)`.
         */
        fun subagents(enabled: BetaManagedAgentsMultiagentSubagentsEnabledParams) =
            subagents(BetaManagedAgentsMultiagentSubagentsParams.ofEnabled(enabled))

        /**
         * Alias for calling [subagents] with
         * `BetaManagedAgentsMultiagentSubagentsParams.ofDisabled(disabled)`.
         */
        fun subagents(disabled: BetaManagedAgentsMultiagentSubagentsDisabledParams) =
            subagents(BetaManagedAgentsMultiagentSubagentsParams.ofDisabled(disabled))

        /** Whether the agent can start workflow runs. Defaults to enabled. */
        fun workflows(workflows: BetaManagedAgentsMultiagentWorkflowsParams?) =
            workflows(JsonField.ofNullable(workflows))

        /** Alias for calling [Builder.workflows] with `workflows.orElse(null)`. */
        fun workflows(workflows: Optional<BetaManagedAgentsMultiagentWorkflowsParams>) =
            workflows(workflows.getOrNull())

        /**
         * Sets [Builder.workflows] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workflows] with a well-typed
         * [BetaManagedAgentsMultiagentWorkflowsParams] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun workflows(workflows: JsonField<BetaManagedAgentsMultiagentWorkflowsParams>) = apply {
            this.workflows = workflows
        }

        /**
         * Alias for calling [workflows] with
         * `BetaManagedAgentsMultiagentWorkflowsParams.ofEnabled(enabled)`.
         */
        fun workflows(enabled: BetaManagedAgentsMultiagentWorkflowsEnabledParams) =
            workflows(BetaManagedAgentsMultiagentWorkflowsParams.ofEnabled(enabled))

        /**
         * Alias for calling [workflows] with
         * `BetaManagedAgentsMultiagentWorkflowsParams.ofDisabled(disabled)`.
         */
        fun workflows(disabled: BetaManagedAgentsMultiagentWorkflowsDisabledParams) =
            workflows(BetaManagedAgentsMultiagentWorkflowsParams.ofDisabled(disabled))

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
         * Returns an immutable instance of [BetaManagedAgentsMultiagent20261001Params].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsMultiagent20261001Params =
            BetaManagedAgentsMultiagent20261001Params(
                type,
                advisor,
                subagents,
                workflows,
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
    fun validate(): BetaManagedAgentsMultiagent20261001Params = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("multiagent_20261001")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        advisor().ifPresent { it.validate() }
        subagents().ifPresent { it.validate() }
        workflows().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("multiagent_20261001")) 1 else 0 } +
            (advisor.asKnown().getOrNull()?.validity() ?: 0) +
            (subagents.asKnown().getOrNull()?.validity() ?: 0) +
            (workflows.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMultiagent20261001Params &&
            type == other.type &&
            advisor == other.advisor &&
            subagents == other.subagents &&
            workflows == other.workflows &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, advisor, subagents, workflows, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMultiagent20261001Params{type=$type, advisor=$advisor, subagents=$subagents, workflows=$workflows, additionalProperties=$additionalProperties}"
}
