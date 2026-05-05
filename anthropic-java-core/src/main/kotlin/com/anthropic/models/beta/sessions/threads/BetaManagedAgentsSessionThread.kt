// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.Enum
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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * An execution thread within a `session`. Each session has one primary thread plus zero or more
 * child threads spawned by the coordinator.
 */
class BetaManagedAgentsSessionThread
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val agent: JsonField<BetaManagedAgentsSessionThreadAgent>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val parentThreadId: JsonField<String>,
    private val sessionId: JsonField<String>,
    private val stats: JsonField<BetaManagedAgentsSessionThreadStats>,
    private val status: JsonField<BetaManagedAgentsSessionThreadStatus>,
    private val type: JsonField<Type>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val usage: JsonField<BetaManagedAgentsSessionThreadUsage>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("agent")
        @ExcludeMissing
        agent: JsonField<BetaManagedAgentsSessionThreadAgent> = JsonMissing.of(),
        @JsonProperty("archived_at")
        @ExcludeMissing
        archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("parent_thread_id")
        @ExcludeMissing
        parentThreadId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("session_id") @ExcludeMissing sessionId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("stats")
        @ExcludeMissing
        stats: JsonField<BetaManagedAgentsSessionThreadStats> = JsonMissing.of(),
        @JsonProperty("status")
        @ExcludeMissing
        status: JsonField<BetaManagedAgentsSessionThreadStatus> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("usage")
        @ExcludeMissing
        usage: JsonField<BetaManagedAgentsSessionThreadUsage> = JsonMissing.of(),
    ) : this(
        id,
        agent,
        archivedAt,
        createdAt,
        parentThreadId,
        sessionId,
        stats,
        status,
        type,
        updatedAt,
        usage,
        mutableMapOf(),
    )

    /**
     * Unique identifier for this thread.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Resolved `agent` definition for a single `session_thread`. Snapshot of the agent at thread
     * creation time. The multiagent roster is not repeated here; read it from `Session.agent`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun agent(): BetaManagedAgentsSessionThreadAgent = agent.getRequired("agent")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedAt(): Optional<OffsetDateTime> = archivedAt.getOptional("archived_at")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Parent thread that spawned this thread. Null for the primary thread.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun parentThreadId(): Optional<String> = parentThreadId.getOptional("parent_thread_id")

    /**
     * The session this thread belongs to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun sessionId(): String = sessionId.getRequired("session_id")

    /**
     * Timing statistics for a session thread.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun stats(): Optional<BetaManagedAgentsSessionThreadStats> = stats.getOptional("stats")

    /**
     * SessionThreadStatus enum
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): BetaManagedAgentsSessionThreadStatus = status.getRequired("status")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * Cumulative token usage for a session thread across all turns.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun usage(): Optional<BetaManagedAgentsSessionThreadUsage> = usage.getOptional("usage")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [agent].
     *
     * Unlike [agent], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("agent")
    @ExcludeMissing
    fun _agent(): JsonField<BetaManagedAgentsSessionThreadAgent> = agent

    /**
     * Returns the raw JSON value of [archivedAt].
     *
     * Unlike [archivedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("archived_at")
    @ExcludeMissing
    fun _archivedAt(): JsonField<OffsetDateTime> = archivedAt

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [parentThreadId].
     *
     * Unlike [parentThreadId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("parent_thread_id")
    @ExcludeMissing
    fun _parentThreadId(): JsonField<String> = parentThreadId

    /**
     * Returns the raw JSON value of [sessionId].
     *
     * Unlike [sessionId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("session_id") @ExcludeMissing fun _sessionId(): JsonField<String> = sessionId

    /**
     * Returns the raw JSON value of [stats].
     *
     * Unlike [stats], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("stats")
    @ExcludeMissing
    fun _stats(): JsonField<BetaManagedAgentsSessionThreadStats> = stats

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status")
    @ExcludeMissing
    fun _status(): JsonField<BetaManagedAgentsSessionThreadStatus> = status

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [usage].
     *
     * Unlike [usage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("usage")
    @ExcludeMissing
    fun _usage(): JsonField<BetaManagedAgentsSessionThreadUsage> = usage

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
         * [BetaManagedAgentsSessionThread].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agent()
         * .archivedAt()
         * .createdAt()
         * .parentThreadId()
         * .sessionId()
         * .stats()
         * .status()
         * .type()
         * .updatedAt()
         * .usage()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionThread]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var agent: JsonField<BetaManagedAgentsSessionThreadAgent>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var parentThreadId: JsonField<String>? = null
        private var sessionId: JsonField<String>? = null
        private var stats: JsonField<BetaManagedAgentsSessionThreadStats>? = null
        private var status: JsonField<BetaManagedAgentsSessionThreadStatus>? = null
        private var type: JsonField<Type>? = null
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var usage: JsonField<BetaManagedAgentsSessionThreadUsage>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsSessionThread: BetaManagedAgentsSessionThread) = apply {
            id = betaManagedAgentsSessionThread.id
            agent = betaManagedAgentsSessionThread.agent
            archivedAt = betaManagedAgentsSessionThread.archivedAt
            createdAt = betaManagedAgentsSessionThread.createdAt
            parentThreadId = betaManagedAgentsSessionThread.parentThreadId
            sessionId = betaManagedAgentsSessionThread.sessionId
            stats = betaManagedAgentsSessionThread.stats
            status = betaManagedAgentsSessionThread.status
            type = betaManagedAgentsSessionThread.type
            updatedAt = betaManagedAgentsSessionThread.updatedAt
            usage = betaManagedAgentsSessionThread.usage
            additionalProperties =
                betaManagedAgentsSessionThread.additionalProperties.toMutableMap()
        }

        /** Unique identifier for this thread. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * Resolved `agent` definition for a single `session_thread`. Snapshot of the agent at
         * thread creation time. The multiagent roster is not repeated here; read it from
         * `Session.agent`.
         */
        fun agent(agent: BetaManagedAgentsSessionThreadAgent) = agent(JsonField.of(agent))

        /**
         * Sets [Builder.agent] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agent] with a well-typed
         * [BetaManagedAgentsSessionThreadAgent] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun agent(agent: JsonField<BetaManagedAgentsSessionThreadAgent>) = apply {
            this.agent = agent
        }

        /** A timestamp in RFC 3339 format */
        fun archivedAt(archivedAt: OffsetDateTime?) = archivedAt(JsonField.ofNullable(archivedAt))

        /** Alias for calling [Builder.archivedAt] with `archivedAt.orElse(null)`. */
        fun archivedAt(archivedAt: Optional<OffsetDateTime>) = archivedAt(archivedAt.getOrNull())

        /**
         * Sets [Builder.archivedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.archivedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun archivedAt(archivedAt: JsonField<OffsetDateTime>) = apply {
            this.archivedAt = archivedAt
        }

        /** A timestamp in RFC 3339 format */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** Parent thread that spawned this thread. Null for the primary thread. */
        fun parentThreadId(parentThreadId: String?) =
            parentThreadId(JsonField.ofNullable(parentThreadId))

        /** Alias for calling [Builder.parentThreadId] with `parentThreadId.orElse(null)`. */
        fun parentThreadId(parentThreadId: Optional<String>) =
            parentThreadId(parentThreadId.getOrNull())

        /**
         * Sets [Builder.parentThreadId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.parentThreadId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun parentThreadId(parentThreadId: JsonField<String>) = apply {
            this.parentThreadId = parentThreadId
        }

        /** The session this thread belongs to. */
        fun sessionId(sessionId: String) = sessionId(JsonField.of(sessionId))

        /**
         * Sets [Builder.sessionId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sessionId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun sessionId(sessionId: JsonField<String>) = apply { this.sessionId = sessionId }

        /** Timing statistics for a session thread. */
        fun stats(stats: BetaManagedAgentsSessionThreadStats?) = stats(JsonField.ofNullable(stats))

        /** Alias for calling [Builder.stats] with `stats.orElse(null)`. */
        fun stats(stats: Optional<BetaManagedAgentsSessionThreadStats>) = stats(stats.getOrNull())

        /**
         * Sets [Builder.stats] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stats] with a well-typed
         * [BetaManagedAgentsSessionThreadStats] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun stats(stats: JsonField<BetaManagedAgentsSessionThreadStats>) = apply {
            this.stats = stats
        }

        /** SessionThreadStatus enum */
        fun status(status: BetaManagedAgentsSessionThreadStatus) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed
         * [BetaManagedAgentsSessionThreadStatus] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<BetaManagedAgentsSessionThreadStatus>) = apply {
            this.status = status
        }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** A timestamp in RFC 3339 format */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /** Cumulative token usage for a session thread across all turns. */
        fun usage(usage: BetaManagedAgentsSessionThreadUsage?) = usage(JsonField.ofNullable(usage))

        /** Alias for calling [Builder.usage] with `usage.orElse(null)`. */
        fun usage(usage: Optional<BetaManagedAgentsSessionThreadUsage>) = usage(usage.getOrNull())

        /**
         * Sets [Builder.usage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.usage] with a well-typed
         * [BetaManagedAgentsSessionThreadUsage] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun usage(usage: JsonField<BetaManagedAgentsSessionThreadUsage>) = apply {
            this.usage = usage
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
         * Returns an immutable instance of [BetaManagedAgentsSessionThread].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agent()
         * .archivedAt()
         * .createdAt()
         * .parentThreadId()
         * .sessionId()
         * .stats()
         * .status()
         * .type()
         * .updatedAt()
         * .usage()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSessionThread =
            BetaManagedAgentsSessionThread(
                checkRequired("id", id),
                checkRequired("agent", agent),
                checkRequired("archivedAt", archivedAt),
                checkRequired("createdAt", createdAt),
                checkRequired("parentThreadId", parentThreadId),
                checkRequired("sessionId", sessionId),
                checkRequired("stats", stats),
                checkRequired("status", status),
                checkRequired("type", type),
                checkRequired("updatedAt", updatedAt),
                checkRequired("usage", usage),
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
    fun validate(): BetaManagedAgentsSessionThread = apply {
        if (validated) {
            return@apply
        }

        id()
        agent().validate()
        archivedAt()
        createdAt()
        parentThreadId()
        sessionId()
        stats().ifPresent { it.validate() }
        status().validate()
        type().validate()
        updatedAt()
        usage().ifPresent { it.validate() }
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
            (agent.asKnown().getOrNull()?.validity() ?: 0) +
            (if (archivedAt.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (parentThreadId.asKnown().isPresent) 1 else 0) +
            (if (sessionId.asKnown().isPresent) 1 else 0) +
            (stats.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (usage.asKnown().getOrNull()?.validity() ?: 0)

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

            @JvmField val SESSION_THREAD = of("session_thread")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SESSION_THREAD
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
            SESSION_THREAD,
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
                SESSION_THREAD -> Value.SESSION_THREAD
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
                SESSION_THREAD -> Known.SESSION_THREAD
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

        return other is BetaManagedAgentsSessionThread &&
            id == other.id &&
            agent == other.agent &&
            archivedAt == other.archivedAt &&
            createdAt == other.createdAt &&
            parentThreadId == other.parentThreadId &&
            sessionId == other.sessionId &&
            stats == other.stats &&
            status == other.status &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            usage == other.usage &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            agent,
            archivedAt,
            createdAt,
            parentThreadId,
            sessionId,
            stats,
            status,
            type,
            updatedAt,
            usage,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionThread{id=$id, agent=$agent, archivedAt=$archivedAt, createdAt=$createdAt, parentThreadId=$parentThreadId, sessionId=$sessionId, stats=$stats, status=$status, type=$type, updatedAt=$updatedAt, usage=$usage, additionalProperties=$additionalProperties}"
}
