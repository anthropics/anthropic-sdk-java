package com.anthropic.models.beta.agents

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsAdvisorParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsMultiagentRosterEntryParams
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * A coordinator topology: the session's primary thread orchestrates work by spawning session
 * threads, each running an agent drawn from the `agents` roster.
 */
class BetaManagedAgentsMultiagentCoordinatorParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val agents: JsonField<List<BetaManagedAgentsMultiagentRosterEntryParams>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("agents")
        @ExcludeMissing
        agents: JsonField<List<BetaManagedAgentsMultiagentRosterEntryParams>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(agents, type, mutableMapOf())

    /**
     * Agents the coordinator may spawn as session threads. 1–20 entries. Each entry is an agent ID
     * string, a versioned `{"type":"agent","id","version"}` reference, or `{"type":"self"}` to
     * allow recursive self-invocation. Entries must reference distinct agents (after resolving
     * `self` and string forms); at most one `self`. Referenced agents must exist, must not be
     * archived, and must not themselves have `multiagent` set (depth limit 1).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun agents(): List<BetaManagedAgentsMultiagentRosterEntryParams> = agents.getRequired("agents")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("coordinator")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [agents].
     *
     * Unlike [agents], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("agents")
    @ExcludeMissing
    fun _agents(): JsonField<List<BetaManagedAgentsMultiagentRosterEntryParams>> = agents

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
         * [BetaManagedAgentsMultiagentCoordinatorParams].
         *
         * The following fields are required:
         * ```java
         * .agents()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsMultiagentCoordinatorParams] with the
         * required [agents] set to the given value.
         */
        @JvmStatic
        fun of(agents: List<BetaManagedAgentsMultiagentRosterEntryParams>) =
            builder().agents(agents).build()
    }

    /** A builder for [BetaManagedAgentsMultiagentCoordinatorParams]. */
    class Builder internal constructor() {

        private var agents: JsonField<MutableList<BetaManagedAgentsMultiagentRosterEntryParams>>? =
            null
        private var type: JsonValue = JsonValue.from("coordinator")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsMultiagentCoordinatorParams:
                BetaManagedAgentsMultiagentCoordinatorParams
        ) = apply {
            agents =
                betaManagedAgentsMultiagentCoordinatorParams.agents
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaManagedAgentsMultiagentCoordinatorParams.type
            additionalProperties =
                betaManagedAgentsMultiagentCoordinatorParams.additionalProperties.toMutableMap()
        }

        /**
         * Agents the coordinator may spawn as session threads. 1–20 entries. Each entry is an agent
         * ID string, a versioned `{"type":"agent","id","version"}` reference, or `{"type":"self"}`
         * to allow recursive self-invocation. Entries must reference distinct agents (after
         * resolving `self` and string forms); at most one `self`. Referenced agents must exist,
         * must not be archived, and must not themselves have `multiagent` set (depth limit 1).
         */
        fun agents(agents: List<BetaManagedAgentsMultiagentRosterEntryParams>) =
            agents(JsonField.of(agents))

        /**
         * Sets [Builder.agents] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agents] with a well-typed
         * `List<BetaManagedAgentsMultiagentRosterEntryParams>` value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun agents(agents: JsonField<List<BetaManagedAgentsMultiagentRosterEntryParams>>) = apply {
            this.agents = agents.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaManagedAgentsMultiagentRosterEntryParams] to [agents].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAgent(agent: BetaManagedAgentsMultiagentRosterEntryParams) = apply {
            agents =
                (agents ?: JsonField.of(mutableListOf())).also {
                    checkKnown("agents", it).add(agent)
                }
        }

        /**
         * Alias for calling [addAgent] with
         * `BetaManagedAgentsMultiagentRosterEntryParams.ofString(string)`.
         */
        fun addAgent(string: String) =
            addAgent(BetaManagedAgentsMultiagentRosterEntryParams.ofString(string))

        /**
         * Alias for calling [addAgent] with
         * `BetaManagedAgentsMultiagentRosterEntryParams.ofAgent(agent)`.
         */
        fun addAgent(agent: BetaManagedAgentsAgentParams) =
            addAgent(BetaManagedAgentsMultiagentRosterEntryParams.ofAgent(agent))

        /**
         * Alias for calling [addAgent] with
         * `BetaManagedAgentsMultiagentRosterEntryParams.ofSelf(self)`.
         */
        fun addAgent(self: BetaManagedAgentsMultiagentSelfParams) =
            addAgent(BetaManagedAgentsMultiagentRosterEntryParams.ofSelf(self))

        /**
         * Alias for calling [addAgent] with
         * `BetaManagedAgentsMultiagentRosterEntryParams.ofAdvisor(advisor)`.
         */
        fun addAgent(advisor: BetaManagedAgentsAdvisorParams) =
            addAgent(BetaManagedAgentsMultiagentRosterEntryParams.ofAdvisor(advisor))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("coordinator")
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
         * Returns an immutable instance of [BetaManagedAgentsMultiagentCoordinatorParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .agents()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMultiagentCoordinatorParams =
            BetaManagedAgentsMultiagentCoordinatorParams(
                checkRequired("agents", agents).map { it.toImmutable() },
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
    fun validate(): BetaManagedAgentsMultiagentCoordinatorParams = apply {
        if (validated) {
            return@apply
        }

        agents().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("coordinator")) {
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
        (agents.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            type.let { if (it == JsonValue.from("coordinator")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMultiagentCoordinatorParams &&
            agents == other.agents &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(agents, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMultiagentCoordinatorParams{agents=$agents, type=$type, additionalProperties=$additionalProperties}"
}
