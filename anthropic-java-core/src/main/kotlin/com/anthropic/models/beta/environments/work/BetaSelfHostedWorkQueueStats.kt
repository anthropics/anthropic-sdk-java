// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Statistics about the work queue for an environment.
 *
 * Uses Redis Stream consumer group metrics for O(1) queries.
 */
class BetaSelfHostedWorkQueueStats
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val depth: JsonField<Long>,
    private val oldestQueuedAt: JsonField<String>,
    private val pending: JsonField<Long>,
    private val type: JsonValue,
    private val workersPolling: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("depth") @ExcludeMissing depth: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("oldest_queued_at")
        @ExcludeMissing
        oldestQueuedAt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("pending") @ExcludeMissing pending: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workers_polling")
        @ExcludeMissing
        workersPolling: JsonField<Long> = JsonMissing.of(),
    ) : this(depth, oldestQueuedAt, pending, type, workersPolling, mutableMapOf())

    /**
     * Number of work items waiting to be picked up (lag from consumer group)
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun depth(): Long = depth.getRequired("depth")

    /**
     * RFC 3339 timestamp of oldest item in the work stream (includes both queued and pending
     * items), null if stream empty
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun oldestQueuedAt(): Optional<String> = oldestQueuedAt.getOptional("oldest_queued_at")

    /**
     * Number of work items being processed (polled but not acknowledged)
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pending(): Long = pending.getRequired("pending")

    /**
     * The type of object
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("work_queue_stats")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Number of workers that have polled for work in the last 30 seconds. Requires worker_id to be
     * sent with poll requests.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun workersPolling(): Optional<Long> = workersPolling.getOptional("workers_polling")

    /**
     * Returns the raw JSON value of [depth].
     *
     * Unlike [depth], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("depth") @ExcludeMissing fun _depth(): JsonField<Long> = depth

    /**
     * Returns the raw JSON value of [oldestQueuedAt].
     *
     * Unlike [oldestQueuedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("oldest_queued_at")
    @ExcludeMissing
    fun _oldestQueuedAt(): JsonField<String> = oldestQueuedAt

    /**
     * Returns the raw JSON value of [pending].
     *
     * Unlike [pending], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("pending") @ExcludeMissing fun _pending(): JsonField<Long> = pending

    /**
     * Returns the raw JSON value of [workersPolling].
     *
     * Unlike [workersPolling], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workers_polling")
    @ExcludeMissing
    fun _workersPolling(): JsonField<Long> = workersPolling

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
         * Returns a mutable builder for constructing an instance of [BetaSelfHostedWorkQueueStats].
         *
         * The following fields are required:
         * ```java
         * .depth()
         * .oldestQueuedAt()
         * .pending()
         * .workersPolling()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaSelfHostedWorkQueueStats]. */
    class Builder internal constructor() {

        private var depth: JsonField<Long>? = null
        private var oldestQueuedAt: JsonField<String>? = null
        private var pending: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("work_queue_stats")
        private var workersPolling: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaSelfHostedWorkQueueStats: BetaSelfHostedWorkQueueStats) = apply {
            depth = betaSelfHostedWorkQueueStats.depth
            oldestQueuedAt = betaSelfHostedWorkQueueStats.oldestQueuedAt
            pending = betaSelfHostedWorkQueueStats.pending
            type = betaSelfHostedWorkQueueStats.type
            workersPolling = betaSelfHostedWorkQueueStats.workersPolling
            additionalProperties = betaSelfHostedWorkQueueStats.additionalProperties.toMutableMap()
        }

        /** Number of work items waiting to be picked up (lag from consumer group) */
        fun depth(depth: Long) = depth(JsonField.of(depth))

        /**
         * Sets [Builder.depth] to an arbitrary JSON value.
         *
         * You should usually call [Builder.depth] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun depth(depth: JsonField<Long>) = apply { this.depth = depth }

        /**
         * RFC 3339 timestamp of oldest item in the work stream (includes both queued and pending
         * items), null if stream empty
         */
        fun oldestQueuedAt(oldestQueuedAt: String?) =
            oldestQueuedAt(JsonField.ofNullable(oldestQueuedAt))

        /** Alias for calling [Builder.oldestQueuedAt] with `oldestQueuedAt.orElse(null)`. */
        fun oldestQueuedAt(oldestQueuedAt: Optional<String>) =
            oldestQueuedAt(oldestQueuedAt.getOrNull())

        /**
         * Sets [Builder.oldestQueuedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.oldestQueuedAt] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun oldestQueuedAt(oldestQueuedAt: JsonField<String>) = apply {
            this.oldestQueuedAt = oldestQueuedAt
        }

        /** Number of work items being processed (polled but not acknowledged) */
        fun pending(pending: Long) = pending(JsonField.of(pending))

        /**
         * Sets [Builder.pending] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pending] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun pending(pending: JsonField<Long>) = apply { this.pending = pending }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("work_queue_stats")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Number of workers that have polled for work in the last 30 seconds. Requires worker_id to
         * be sent with poll requests.
         */
        fun workersPolling(workersPolling: Long?) =
            workersPolling(JsonField.ofNullable(workersPolling))

        /**
         * Alias for [Builder.workersPolling].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun workersPolling(workersPolling: Long) = workersPolling(workersPolling as Long?)

        /** Alias for calling [Builder.workersPolling] with `workersPolling.orElse(null)`. */
        fun workersPolling(workersPolling: Optional<Long>) =
            workersPolling(workersPolling.getOrNull())

        /**
         * Sets [Builder.workersPolling] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workersPolling] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workersPolling(workersPolling: JsonField<Long>) = apply {
            this.workersPolling = workersPolling
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
         * Returns an immutable instance of [BetaSelfHostedWorkQueueStats].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .depth()
         * .oldestQueuedAt()
         * .pending()
         * .workersPolling()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaSelfHostedWorkQueueStats =
            BetaSelfHostedWorkQueueStats(
                checkRequired("depth", depth),
                checkRequired("oldestQueuedAt", oldestQueuedAt),
                checkRequired("pending", pending),
                type,
                checkRequired("workersPolling", workersPolling),
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
    fun validate(): BetaSelfHostedWorkQueueStats = apply {
        if (validated) {
            return@apply
        }

        depth()
        oldestQueuedAt()
        pending()
        _type().let {
            if (it != JsonValue.from("work_queue_stats")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        workersPolling()
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
        (if (depth.asKnown().isPresent) 1 else 0) +
            (if (oldestQueuedAt.asKnown().isPresent) 1 else 0) +
            (if (pending.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("work_queue_stats")) 1 else 0 } +
            (if (workersPolling.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaSelfHostedWorkQueueStats &&
            depth == other.depth &&
            oldestQueuedAt == other.oldestQueuedAt &&
            pending == other.pending &&
            type == other.type &&
            workersPolling == other.workersPolling &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(depth, oldestQueuedAt, pending, type, workersPolling, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSelfHostedWorkQueueStats{depth=$depth, oldestQueuedAt=$oldestQueuedAt, pending=$pending, type=$type, workersPolling=$workersPolling, additionalProperties=$additionalProperties}"
}
