package com.anthropic.models.beta.sessions.events

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

/**
 * Event emitted when the agent calls a custom tool. The session goes idle until the client sends a
 * `user.custom_tool_result` event with the result. The client can send it as soon as this event
 * arrives, without waiting for `session.status_idle`.
 */
class BetaManagedAgentsAgentCustomToolUseEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val input: JsonField<Input>,
    private val name: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val type: JsonValue,
    private val sessionThreadId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("input") @ExcludeMissing input: JsonField<Input> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("session_thread_id")
        @ExcludeMissing
        sessionThreadId: JsonField<String> = JsonMissing.of(),
    ) : this(id, input, name, processedAt, type, sessionThreadId, mutableMapOf())

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
     * Name of the custom tool being called.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * Timestamp when this tool use was processed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processedAt(): OffsetDateTime = processedAt.getRequired("processed_at")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("agent.custom_tool_use")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * When set, this event was cross-posted from a subagent's thread to surface its custom tool use
     * on the primary thread's stream. Empty on the thread's own events. Informational only: the
     * server routes the matching `user.custom_tool_result` by `custom_tool_use_id`, so clients do
     * not send it back.
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
         * [BetaManagedAgentsAgentCustomToolUseEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .input()
         * .name()
         * .processedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentCustomToolUseEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var input: JsonField<Input>? = null
        private var name: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonValue = JsonValue.from("agent.custom_tool_use")
        private var sessionThreadId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentCustomToolUseEvent: BetaManagedAgentsAgentCustomToolUseEvent
        ) = apply {
            id = betaManagedAgentsAgentCustomToolUseEvent.id
            input = betaManagedAgentsAgentCustomToolUseEvent.input
            name = betaManagedAgentsAgentCustomToolUseEvent.name
            processedAt = betaManagedAgentsAgentCustomToolUseEvent.processedAt
            type = betaManagedAgentsAgentCustomToolUseEvent.type
            sessionThreadId = betaManagedAgentsAgentCustomToolUseEvent.sessionThreadId
            additionalProperties =
                betaManagedAgentsAgentCustomToolUseEvent.additionalProperties.toMutableMap()
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

        /** Name of the custom tool being called. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** Timestamp when this tool use was processed. */
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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("agent.custom_tool_use")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * When set, this event was cross-posted from a subagent's thread to surface its custom tool
         * use on the primary thread's stream. Empty on the thread's own events. Informational only:
         * the server routes the matching `user.custom_tool_result` by `custom_tool_use_id`, so
         * clients do not send it back.
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
         * Returns an immutable instance of [BetaManagedAgentsAgentCustomToolUseEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .input()
         * .name()
         * .processedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentCustomToolUseEvent =
            BetaManagedAgentsAgentCustomToolUseEvent(
                checkRequired("id", id),
                checkRequired("input", input),
                checkRequired("name", name),
                checkRequired("processedAt", processedAt),
                type,
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
    fun validate(): BetaManagedAgentsAgentCustomToolUseEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        input().validate()
        name()
        processedAt()
        _type().let {
            if (it != JsonValue.from("agent.custom_tool_use")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
            (if (name.asKnown().isPresent) 1 else 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("agent.custom_tool_use")) 1 else 0 } +
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentCustomToolUseEvent &&
            id == other.id &&
            input == other.input &&
            name == other.name &&
            processedAt == other.processedAt &&
            type == other.type &&
            sessionThreadId == other.sessionThreadId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, input, name, processedAt, type, sessionThreadId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentCustomToolUseEvent{id=$id, input=$input, name=$name, processedAt=$processedAt, type=$type, sessionThreadId=$sessionThreadId, additionalProperties=$additionalProperties}"
}
