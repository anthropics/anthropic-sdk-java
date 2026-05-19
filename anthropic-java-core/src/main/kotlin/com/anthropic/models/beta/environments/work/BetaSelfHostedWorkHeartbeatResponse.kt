// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

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
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/** Response after recording a heartbeat for a work item. */
class BetaSelfHostedWorkHeartbeatResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val lastHeartbeat: JsonField<String>,
    private val leaseExtended: JsonField<Boolean>,
    private val state: JsonField<State>,
    private val ttlSeconds: JsonField<Long>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("last_heartbeat")
        @ExcludeMissing
        lastHeartbeat: JsonField<String> = JsonMissing.of(),
        @JsonProperty("lease_extended")
        @ExcludeMissing
        leaseExtended: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("state") @ExcludeMissing state: JsonField<State> = JsonMissing.of(),
        @JsonProperty("ttl_seconds") @ExcludeMissing ttlSeconds: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(lastHeartbeat, leaseExtended, state, ttlSeconds, type, mutableMapOf())

    /**
     * RFC 3339 timestamp of the actual heartbeat from DB
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun lastHeartbeat(): String = lastHeartbeat.getRequired("last_heartbeat")

    /**
     * Whether the heartbeat succeeded in extending the lease
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun leaseExtended(): Boolean = leaseExtended.getRequired("lease_extended")

    /**
     * Current state of the work item (active/stopping/stopped)
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun state(): State = state.getRequired("state")

    /**
     * Effective TTL applied to the lease
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun ttlSeconds(): Long = ttlSeconds.getRequired("ttl_seconds")

    /**
     * The type of response
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("work_heartbeat")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [lastHeartbeat].
     *
     * Unlike [lastHeartbeat], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("last_heartbeat")
    @ExcludeMissing
    fun _lastHeartbeat(): JsonField<String> = lastHeartbeat

    /**
     * Returns the raw JSON value of [leaseExtended].
     *
     * Unlike [leaseExtended], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("lease_extended")
    @ExcludeMissing
    fun _leaseExtended(): JsonField<Boolean> = leaseExtended

    /**
     * Returns the raw JSON value of [state].
     *
     * Unlike [state], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("state") @ExcludeMissing fun _state(): JsonField<State> = state

    /**
     * Returns the raw JSON value of [ttlSeconds].
     *
     * Unlike [ttlSeconds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ttl_seconds") @ExcludeMissing fun _ttlSeconds(): JsonField<Long> = ttlSeconds

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
         * [BetaSelfHostedWorkHeartbeatResponse].
         *
         * The following fields are required:
         * ```java
         * .lastHeartbeat()
         * .leaseExtended()
         * .state()
         * .ttlSeconds()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaSelfHostedWorkHeartbeatResponse]. */
    class Builder internal constructor() {

        private var lastHeartbeat: JsonField<String>? = null
        private var leaseExtended: JsonField<Boolean>? = null
        private var state: JsonField<State>? = null
        private var ttlSeconds: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("work_heartbeat")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaSelfHostedWorkHeartbeatResponse: BetaSelfHostedWorkHeartbeatResponse
        ) = apply {
            lastHeartbeat = betaSelfHostedWorkHeartbeatResponse.lastHeartbeat
            leaseExtended = betaSelfHostedWorkHeartbeatResponse.leaseExtended
            state = betaSelfHostedWorkHeartbeatResponse.state
            ttlSeconds = betaSelfHostedWorkHeartbeatResponse.ttlSeconds
            type = betaSelfHostedWorkHeartbeatResponse.type
            additionalProperties =
                betaSelfHostedWorkHeartbeatResponse.additionalProperties.toMutableMap()
        }

        /** RFC 3339 timestamp of the actual heartbeat from DB */
        fun lastHeartbeat(lastHeartbeat: String) = lastHeartbeat(JsonField.of(lastHeartbeat))

        /**
         * Sets [Builder.lastHeartbeat] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastHeartbeat] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun lastHeartbeat(lastHeartbeat: JsonField<String>) = apply {
            this.lastHeartbeat = lastHeartbeat
        }

        /** Whether the heartbeat succeeded in extending the lease */
        fun leaseExtended(leaseExtended: Boolean) = leaseExtended(JsonField.of(leaseExtended))

        /**
         * Sets [Builder.leaseExtended] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leaseExtended] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun leaseExtended(leaseExtended: JsonField<Boolean>) = apply {
            this.leaseExtended = leaseExtended
        }

        /** Current state of the work item (active/stopping/stopped) */
        fun state(state: State) = state(JsonField.of(state))

        /**
         * Sets [Builder.state] to an arbitrary JSON value.
         *
         * You should usually call [Builder.state] with a well-typed [State] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun state(state: JsonField<State>) = apply { this.state = state }

        /** Effective TTL applied to the lease */
        fun ttlSeconds(ttlSeconds: Long) = ttlSeconds(JsonField.of(ttlSeconds))

        /**
         * Sets [Builder.ttlSeconds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.ttlSeconds] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun ttlSeconds(ttlSeconds: JsonField<Long>) = apply { this.ttlSeconds = ttlSeconds }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("work_heartbeat")
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
         * Returns an immutable instance of [BetaSelfHostedWorkHeartbeatResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .lastHeartbeat()
         * .leaseExtended()
         * .state()
         * .ttlSeconds()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaSelfHostedWorkHeartbeatResponse =
            BetaSelfHostedWorkHeartbeatResponse(
                checkRequired("lastHeartbeat", lastHeartbeat),
                checkRequired("leaseExtended", leaseExtended),
                checkRequired("state", state),
                checkRequired("ttlSeconds", ttlSeconds),
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
    fun validate(): BetaSelfHostedWorkHeartbeatResponse = apply {
        if (validated) {
            return@apply
        }

        lastHeartbeat()
        leaseExtended()
        state().validate()
        ttlSeconds()
        _type().let {
            if (it != JsonValue.from("work_heartbeat")) {
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
        (if (lastHeartbeat.asKnown().isPresent) 1 else 0) +
            (if (leaseExtended.asKnown().isPresent) 1 else 0) +
            (state.asKnown().getOrNull()?.validity() ?: 0) +
            (if (ttlSeconds.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("work_heartbeat")) 1 else 0 }

    /** Current state of the work item (active/stopping/stopped) */
    class State @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val QUEUED = of("queued")

            @JvmField val STARTING = of("starting")

            @JvmField val ACTIVE = of("active")

            @JvmField val STOPPING = of("stopping")

            @JvmField val STOPPED = of("stopped")

            @JvmStatic fun of(value: String) = State(JsonField.of(value))
        }

        /** An enum containing [State]'s known values. */
        enum class Known {
            QUEUED,
            STARTING,
            ACTIVE,
            STOPPING,
            STOPPED,
        }

        /**
         * An enum containing [State]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [State] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            QUEUED,
            STARTING,
            ACTIVE,
            STOPPING,
            STOPPED,
            /** An enum member indicating that [State] was instantiated with an unknown value. */
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
                QUEUED -> Value.QUEUED
                STARTING -> Value.STARTING
                ACTIVE -> Value.ACTIVE
                STOPPING -> Value.STOPPING
                STOPPED -> Value.STOPPED
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
                QUEUED -> Known.QUEUED
                STARTING -> Known.STARTING
                ACTIVE -> Known.ACTIVE
                STOPPING -> Known.STOPPING
                STOPPED -> Known.STOPPED
                else -> throw AnthropicInvalidDataException("Unknown State: $value")
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
        fun validate(): State = apply {
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

            return other is State && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaSelfHostedWorkHeartbeatResponse &&
            lastHeartbeat == other.lastHeartbeat &&
            leaseExtended == other.leaseExtended &&
            state == other.state &&
            ttlSeconds == other.ttlSeconds &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(lastHeartbeat, leaseExtended, state, ttlSeconds, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSelfHostedWorkHeartbeatResponse{lastHeartbeat=$lastHeartbeat, leaseExtended=$leaseExtended, state=$state, ttlSeconds=$ttlSeconds, type=$type, additionalProperties=$additionalProperties}"
}
