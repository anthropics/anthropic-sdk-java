// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsAdvisor
import com.anthropic.models.beta.agents.BetaManagedAgentsSessionThreadAgent
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Resolved coordinator topology with full agent definitions for each roster member. */
class BetaManagedAgentsSessionMultiagentCoordinator
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val agents: JsonField<List<Agent>>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("agents") @ExcludeMissing agents: JsonField<List<Agent>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(agents, type, mutableMapOf())

    /**
     * Full `agent` definitions the coordinator may spawn as session threads.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun agents(): List<Agent> = agents.getRequired("agents")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Returns the raw JSON value of [agents].
     *
     * Unlike [agents], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("agents") @ExcludeMissing fun _agents(): JsonField<List<Agent>> = agents

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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
         * [BetaManagedAgentsSessionMultiagentCoordinator].
         *
         * The following fields are required:
         * ```java
         * .agents()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionMultiagentCoordinator]. */
    class Builder internal constructor() {

        private var agents: JsonField<MutableList<Agent>>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSessionMultiagentCoordinator:
                BetaManagedAgentsSessionMultiagentCoordinator
        ) = apply {
            agents =
                betaManagedAgentsSessionMultiagentCoordinator.agents
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaManagedAgentsSessionMultiagentCoordinator.type
            additionalProperties =
                betaManagedAgentsSessionMultiagentCoordinator.additionalProperties.toMutableMap()
        }

        /** Full `agent` definitions the coordinator may spawn as session threads. */
        fun agents(agents: List<Agent>) = agents(JsonField.of(agents))

        /**
         * Sets [Builder.agents] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agents] with a well-typed `List<Agent>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun agents(agents: JsonField<List<Agent>>) = apply {
            this.agents = agents.map { it.toMutableList() }
        }

        /**
         * Adds a single [Agent] to [agents].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAgent(agent: Agent) = apply {
            agents =
                (agents ?: JsonField.of(mutableListOf())).also {
                    checkKnown("agents", it).add(agent)
                }
        }

        /** Alias for calling [addAgent] with `Agent.ofAgent(agent)`. */
        fun addAgent(agent: BetaManagedAgentsSessionThreadAgent) = addAgent(Agent.ofAgent(agent))

        /** Alias for calling [addAgent] with `Agent.ofAdvisor(advisor)`. */
        fun addAgent(advisor: BetaManagedAgentsAdvisor) = addAgent(Agent.ofAdvisor(advisor))

        /**
         * Alias for calling [addAgent] with the following:
         * ```java
         * BetaManagedAgentsAdvisor.builder()
         *     .type(BetaManagedAgentsAdvisor.Type.ADVISOR)
         *     .model(model)
         *     .build()
         * ```
         */
        fun addAdvisorAgent(model: String) =
            addAgent(
                BetaManagedAgentsAdvisor.builder()
                    .type(BetaManagedAgentsAdvisor.Type.ADVISOR)
                    .model(model)
                    .build()
            )

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaManagedAgentsSessionMultiagentCoordinator].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .agents()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSessionMultiagentCoordinator =
            BetaManagedAgentsSessionMultiagentCoordinator(
                checkRequired("agents", agents).map { it.toImmutable() },
                checkRequired("type", type),
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
    fun validate(): BetaManagedAgentsSessionMultiagentCoordinator = apply {
        if (validated) {
            return@apply
        }

        agents().forEach { it.validate() }
        type().validate()
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
            (type.asKnown().getOrNull()?.validity() ?: 0)

    /** A session-resolved multiagent roster entry. */
    @JsonDeserialize(using = Agent.Deserializer::class)
    @JsonSerialize(using = Agent.Serializer::class)
    class Agent
    private constructor(
        private val agent: BetaManagedAgentsSessionThreadAgent? = null,
        private val advisor: BetaManagedAgentsAdvisor? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitAgent(agent: BetaManagedAgentsSessionThreadAgent): Type =
                        Type.AGENT

                    override fun visitAdvisor(advisor: BetaManagedAgentsAdvisor): Type =
                        Type.ADVISOR

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        /**
         * Resolved `agent` definition for a single `session_thread`. Snapshot of the agent at
         * thread creation time. The multiagent roster is not repeated here; read it from
         * `Session.agent`.
         */
        fun agent(): Optional<BetaManagedAgentsSessionThreadAgent> = Optional.ofNullable(agent)

        /**
         * Platform advisor roster entry: a model the session's primary thread may consult mid-turn.
         */
        fun advisor(): Optional<BetaManagedAgentsAdvisor> = Optional.ofNullable(advisor)

        fun isAgent(): Boolean = agent != null

        fun isAdvisor(): Boolean = advisor != null

        /**
         * Resolved `agent` definition for a single `session_thread`. Snapshot of the agent at
         * thread creation time. The multiagent roster is not repeated here; read it from
         * `Session.agent`.
         */
        fun asAgent(): BetaManagedAgentsSessionThreadAgent = agent.getOrThrow("agent")

        /**
         * Platform advisor roster entry: a model the session's primary thread may consult mid-turn.
         */
        fun asAdvisor(): BetaManagedAgentsAdvisor = advisor.getOrThrow("advisor")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = agent.accept(new Agent.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitAgent(BetaManagedAgentsSessionThreadAgent agent) {
         *         return Optional.of(agent.toString());
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
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                agent != null -> visitor.visitAgent(agent)
                advisor != null -> visitor.visitAdvisor(advisor)
                else -> visitor.unknown(_json)
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
        fun validate(): Agent = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitAgent(agent: BetaManagedAgentsSessionThreadAgent) {
                        agent.validate()
                    }

                    override fun visitAdvisor(advisor: BetaManagedAgentsAdvisor) {
                        advisor.validate()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitAgent(agent: BetaManagedAgentsSessionThreadAgent) =
                        agent.validity()

                    override fun visitAdvisor(advisor: BetaManagedAgentsAdvisor) =
                        advisor.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Agent && agent == other.agent && advisor == other.advisor
        }

        override fun hashCode(): Int = Objects.hash(agent, advisor)

        override fun toString(): String =
            when {
                agent != null -> "Agent{agent=$agent}"
                advisor != null -> "Agent{advisor=$advisor}"
                _json != null -> "Agent{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Agent")
            }

        companion object {

            /**
             * Resolved `agent` definition for a single `session_thread`. Snapshot of the agent at
             * thread creation time. The multiagent roster is not repeated here; read it from
             * `Session.agent`.
             */
            @JvmStatic
            fun ofAgent(agent: BetaManagedAgentsSessionThreadAgent) = Agent(agent = agent)

            /**
             * Platform advisor roster entry: a model the session's primary thread may consult
             * mid-turn.
             */
            @JvmStatic fun ofAdvisor(advisor: BetaManagedAgentsAdvisor) = Agent(advisor = advisor)

            /**
             * Returns an immutable instance of [Agent] whose [ofAdvisor] variant is built from the
             * given required [model].
             */
            @JvmStatic
            fun ofAdvisor(model: String) =
                ofAdvisor(
                    BetaManagedAgentsAdvisor.builder()
                        .type(BetaManagedAgentsAdvisor.Type.ADVISOR)
                        .model(model)
                        .build()
                )
        }

        /** An interface that defines how to map each variant of [Agent] to a value of type [T]. */
        interface Visitor<out T> {

            /**
             * Resolved `agent` definition for a single `session_thread`. Snapshot of the agent at
             * thread creation time. The multiagent roster is not repeated here; read it from
             * `Session.agent`.
             */
            fun visitAgent(agent: BetaManagedAgentsSessionThreadAgent): T

            /**
             * Platform advisor roster entry: a model the session's primary thread may consult
             * mid-turn.
             */
            fun visitAdvisor(advisor: BetaManagedAgentsAdvisor): T

            /**
             * Maps an unknown variant of [Agent] to a value of type [T].
             *
             * An instance of [Agent] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Agent: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Agent>(Agent::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Agent {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "agent" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsSessionThreadAgent>(),
                            )
                            ?.let { Agent(agent = it, _json = json) } ?: Agent(_json = json)
                    }
                    "advisor" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsAdvisor>())
                            ?.let { Agent(advisor = it, _json = json) } ?: Agent(_json = json)
                    }
                }

                return Agent(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Agent>(Agent::class) {

            override fun serialize(
                value: Agent,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.agent != null -> generator.writeObject(value.agent)
                    value.advisor != null -> generator.writeObject(value.advisor)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Agent")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val AGENT = of("agent")

                @JvmField val ADVISOR = of("advisor")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                AGENT,
                ADVISOR,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                AGENT,
                ADVISOR,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    AGENT -> Value.AGENT
                    ADVISOR -> Value.ADVISOR
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    AGENT -> Known.AGENT
                    ADVISOR -> Known.ADVISOR
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
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
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
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

            @JvmField val COORDINATOR = of("coordinator")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            COORDINATOR
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
            COORDINATOR,
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
                COORDINATOR -> Value.COORDINATOR
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
                COORDINATOR -> Known.COORDINATOR
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionMultiagentCoordinator &&
            agents == other.agents &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(agents, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionMultiagentCoordinator{agents=$agents, type=$type, additionalProperties=$additionalProperties}"
}
