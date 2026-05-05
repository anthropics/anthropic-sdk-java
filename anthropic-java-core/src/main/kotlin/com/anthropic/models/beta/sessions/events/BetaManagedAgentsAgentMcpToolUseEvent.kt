// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Event emitted when the agent invokes a tool provided by an MCP server. */
class BetaManagedAgentsAgentMcpToolUseEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val input: JsonField<Input>,
    private val mcpServerName: JsonField<String>,
    private val name: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val type: JsonField<Type>,
    private val evaluatedPermission: JsonField<EvaluatedPermission>,
    private val sessionThreadId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("input") @ExcludeMissing input: JsonField<Input> = JsonMissing.of(),
        @JsonProperty("mcp_server_name")
        @ExcludeMissing
        mcpServerName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("evaluated_permission")
        @ExcludeMissing
        evaluatedPermission: JsonField<EvaluatedPermission> = JsonMissing.of(),
        @JsonProperty("session_thread_id")
        @ExcludeMissing
        sessionThreadId: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        input,
        mcpServerName,
        name,
        processedAt,
        type,
        evaluatedPermission,
        sessionThreadId,
        mutableMapOf(),
    )

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Input parameters for the tool call.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun input(): Input = input.getRequired("input")

    /**
     * Name of the MCP server providing the tool.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun mcpServerName(): String = mcpServerName.getRequired("mcp_server_name")

    /**
     * Name of the MCP tool being used.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processedAt(): OffsetDateTime = processedAt.getRequired("processed_at")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * AgentEvaluatedPermission enum
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun evaluatedPermission(): Optional<EvaluatedPermission> =
        evaluatedPermission.getOptional("evaluated_permission")

    /**
     * When set, this event was cross-posted from a subagent's thread to surface its permission
     * request on the primary thread's stream. Empty on the thread's own events. Echo this on a
     * `user.tool_confirmation` event to route the approval back.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun sessionThreadId(): Optional<String> = sessionThreadId.getOptional("session_thread_id")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [input].
     *
     * Unlike [input], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input") @ExcludeMissing fun _input(): JsonField<Input> = input

    /**
     * Returns the raw JSON value of [mcpServerName].
     *
     * Unlike [mcpServerName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mcp_server_name")
    @ExcludeMissing
    fun _mcpServerName(): JsonField<String> = mcpServerName

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [processedAt].
     *
     * Unlike [processedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("processed_at")
    @ExcludeMissing
    fun _processedAt(): JsonField<OffsetDateTime> = processedAt

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [evaluatedPermission].
     *
     * Unlike [evaluatedPermission], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("evaluated_permission")
    @ExcludeMissing
    fun _evaluatedPermission(): JsonField<EvaluatedPermission> = evaluatedPermission

    /**
     * Returns the raw JSON value of [sessionThreadId].
     *
     * Unlike [sessionThreadId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("session_thread_id")
    @ExcludeMissing
    fun _sessionThreadId(): JsonField<String> = sessionThreadId

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
         * [BetaManagedAgentsAgentMcpToolUseEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .input()
         * .mcpServerName()
         * .name()
         * .processedAt()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentMcpToolUseEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var input: JsonField<Input>? = null
        private var mcpServerName: JsonField<String>? = null
        private var name: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonField<Type>? = null
        private var evaluatedPermission: JsonField<EvaluatedPermission> = JsonMissing.of()
        private var sessionThreadId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentMcpToolUseEvent: BetaManagedAgentsAgentMcpToolUseEvent
        ) = apply {
            id = betaManagedAgentsAgentMcpToolUseEvent.id
            input = betaManagedAgentsAgentMcpToolUseEvent.input
            mcpServerName = betaManagedAgentsAgentMcpToolUseEvent.mcpServerName
            name = betaManagedAgentsAgentMcpToolUseEvent.name
            processedAt = betaManagedAgentsAgentMcpToolUseEvent.processedAt
            type = betaManagedAgentsAgentMcpToolUseEvent.type
            evaluatedPermission = betaManagedAgentsAgentMcpToolUseEvent.evaluatedPermission
            sessionThreadId = betaManagedAgentsAgentMcpToolUseEvent.sessionThreadId
            additionalProperties =
                betaManagedAgentsAgentMcpToolUseEvent.additionalProperties.toMutableMap()
        }

        /** Unique identifier for this event. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Input parameters for the tool call. */
        fun input(input: Input) = input(JsonField.of(input))

        /**
         * Sets [Builder.input] to an arbitrary JSON value.
         *
         * You should usually call [Builder.input] with a well-typed [Input] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun input(input: JsonField<Input>) = apply { this.input = input }

        /** Name of the MCP server providing the tool. */
        fun mcpServerName(mcpServerName: String) = mcpServerName(JsonField.of(mcpServerName))

        /**
         * Sets [Builder.mcpServerName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mcpServerName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mcpServerName(mcpServerName: JsonField<String>) = apply {
            this.mcpServerName = mcpServerName
        }

        /** Name of the MCP tool being used. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** A timestamp in RFC 3339 format */
        fun processedAt(processedAt: OffsetDateTime) = processedAt(JsonField.of(processedAt))

        /**
         * Sets [Builder.processedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.processedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun processedAt(processedAt: JsonField<OffsetDateTime>) = apply {
            this.processedAt = processedAt
        }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** AgentEvaluatedPermission enum */
        fun evaluatedPermission(evaluatedPermission: EvaluatedPermission) =
            evaluatedPermission(JsonField.of(evaluatedPermission))

        /**
         * Sets [Builder.evaluatedPermission] to an arbitrary JSON value.
         *
         * You should usually call [Builder.evaluatedPermission] with a well-typed
         * [EvaluatedPermission] value instead. This method is primarily for setting the field to an
         * undocumented or not yet supported value.
         */
        fun evaluatedPermission(evaluatedPermission: JsonField<EvaluatedPermission>) = apply {
            this.evaluatedPermission = evaluatedPermission
        }

        /**
         * When set, this event was cross-posted from a subagent's thread to surface its permission
         * request on the primary thread's stream. Empty on the thread's own events. Echo this on a
         * `user.tool_confirmation` event to route the approval back.
         */
        fun sessionThreadId(sessionThreadId: String?) =
            sessionThreadId(JsonField.ofNullable(sessionThreadId))

        /** Alias for calling [Builder.sessionThreadId] with `sessionThreadId.orElse(null)`. */
        fun sessionThreadId(sessionThreadId: Optional<String>) =
            sessionThreadId(sessionThreadId.getOrNull())

        /**
         * Sets [Builder.sessionThreadId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sessionThreadId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun sessionThreadId(sessionThreadId: JsonField<String>) = apply {
            this.sessionThreadId = sessionThreadId
        }

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
         * Returns an immutable instance of [BetaManagedAgentsAgentMcpToolUseEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .input()
         * .mcpServerName()
         * .name()
         * .processedAt()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentMcpToolUseEvent =
            BetaManagedAgentsAgentMcpToolUseEvent(
                checkRequired("id", id),
                checkRequired("input", input),
                checkRequired("mcpServerName", mcpServerName),
                checkRequired("name", name),
                checkRequired("processedAt", processedAt),
                checkRequired("type", type),
                evaluatedPermission,
                sessionThreadId,
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
    fun validate(): BetaManagedAgentsAgentMcpToolUseEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        input().validate()
        mcpServerName()
        name()
        processedAt()
        type().validate()
        evaluatedPermission().ifPresent { it.validate() }
        sessionThreadId()
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (input.asKnown().getOrNull()?.validity() ?: 0) +
            (if (mcpServerName.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (evaluatedPermission.asKnown().getOrNull()?.validity() ?: 0) +
            (if (sessionThreadId.asKnown().isPresent) 1 else 0)

    /** Input parameters for the tool call. */
    class Input
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Input]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Input]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(input: Input) = apply {
                additionalProperties = input.additionalProperties.toMutableMap()
            }

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
             * Returns an immutable instance of [Input].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Input = Input(additionalProperties.toImmutable())
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
        fun validate(): Input = apply {
            if (validated) {
                return@apply
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Input && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Input{additionalProperties=$additionalProperties}"
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

            @JvmField val AGENT_MCP_TOOL_USE = of("agent.mcp_tool_use")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            AGENT_MCP_TOOL_USE
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
            AGENT_MCP_TOOL_USE,
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
                AGENT_MCP_TOOL_USE -> Value.AGENT_MCP_TOOL_USE
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
                AGENT_MCP_TOOL_USE -> Known.AGENT_MCP_TOOL_USE
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

    /** AgentEvaluatedPermission enum */
    class EvaluatedPermission
    @JsonCreator
    private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val ALLOW = of("allow")

            @JvmField val ASK = of("ask")

            @JvmField val DENY = of("deny")

            @JvmStatic fun of(value: String) = EvaluatedPermission(JsonField.of(value))
        }

        /** An enum containing [EvaluatedPermission]'s known values. */
        enum class Known {
            ALLOW,
            ASK,
            DENY,
        }

        /**
         * An enum containing [EvaluatedPermission]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [EvaluatedPermission] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ALLOW,
            ASK,
            DENY,
            /**
             * An enum member indicating that [EvaluatedPermission] was instantiated with an unknown
             * value.
             */
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
                ALLOW -> Value.ALLOW
                ASK -> Value.ASK
                DENY -> Value.DENY
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
                ALLOW -> Known.ALLOW
                ASK -> Known.ASK
                DENY -> Known.DENY
                else -> throw AnthropicInvalidDataException("Unknown EvaluatedPermission: $value")
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
        fun validate(): EvaluatedPermission = apply {
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

            return other is EvaluatedPermission && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentMcpToolUseEvent &&
            id == other.id &&
            input == other.input &&
            mcpServerName == other.mcpServerName &&
            name == other.name &&
            processedAt == other.processedAt &&
            type == other.type &&
            evaluatedPermission == other.evaluatedPermission &&
            sessionThreadId == other.sessionThreadId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            input,
            mcpServerName,
            name,
            processedAt,
            type,
            evaluatedPermission,
            sessionThreadId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentMcpToolUseEvent{id=$id, input=$input, mcpServerName=$mcpServerName, name=$name, processedAt=$processedAt, type=$type, evaluatedPermission=$evaluatedPermission, sessionThreadId=$sessionThreadId, additionalProperties=$additionalProperties}"
}
