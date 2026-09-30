package com.anthropic.models.beta.organization.analytics

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

/** Claude Design activity metrics for a single user on a given day. */
class BetaAnalyticsDesignMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val distinctProjectsCreatedCount: JsonField<Long>,
    private val distinctProjectsUsedCount: JsonField<Long>,
    private val distinctSessionCount: JsonField<Long>,
    private val messageCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("distinct_projects_created_count")
        @ExcludeMissing
        distinctProjectsCreatedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("distinct_projects_used_count")
        @ExcludeMissing
        distinctProjectsUsedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("distinct_session_count")
        @ExcludeMissing
        distinctSessionCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("message_count")
        @ExcludeMissing
        messageCount: JsonField<Long> = JsonMissing.of(),
    ) : this(
        distinctProjectsCreatedCount,
        distinctProjectsUsedCount,
        distinctSessionCount,
        messageCount,
        mutableMapOf(),
    )

    /**
     * Number of distinct Claude Design projects created. Exact in date-range mode: a creation
     * belongs to exactly one day, so the per-day counts never overlap and their sum over the window
     * is the exact count of distinct creations in it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun distinctProjectsCreatedCount(): Long =
        distinctProjectsCreatedCount.getRequired("distinct_projects_created_count")

    /**
     * Number of distinct Claude Design projects the user worked in. Approximate (HLL, typical error
     * <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctProjectsUsedCount(): Optional<Long> =
        distinctProjectsUsedCount.getOptional("distinct_projects_used_count")

    /**
     * Number of distinct Claude Design sessions. Approximate (HLL, typical error <2%) in date-range
     * mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSessionCount(): Optional<Long> =
        distinctSessionCount.getOptional("distinct_session_count")

    /**
     * Number of messages sent in Claude Design sessions
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun messageCount(): Long = messageCount.getRequired("message_count")

    /**
     * Returns the raw JSON value of [distinctProjectsCreatedCount].
     *
     * Unlike [distinctProjectsCreatedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_projects_created_count")
    @ExcludeMissing
    fun _distinctProjectsCreatedCount(): JsonField<Long> = distinctProjectsCreatedCount

    /**
     * Returns the raw JSON value of [distinctProjectsUsedCount].
     *
     * Unlike [distinctProjectsUsedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_projects_used_count")
    @ExcludeMissing
    fun _distinctProjectsUsedCount(): JsonField<Long> = distinctProjectsUsedCount

    /**
     * Returns the raw JSON value of [distinctSessionCount].
     *
     * Unlike [distinctSessionCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("distinct_session_count")
    @ExcludeMissing
    fun _distinctSessionCount(): JsonField<Long> = distinctSessionCount

    /**
     * Returns the raw JSON value of [messageCount].
     *
     * Unlike [messageCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("message_count")
    @ExcludeMissing
    fun _messageCount(): JsonField<Long> = messageCount

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsDesignMetrics].
         *
         * The following fields are required:
         * ```java
         * .distinctProjectsCreatedCount()
         * .distinctProjectsUsedCount()
         * .distinctSessionCount()
         * .messageCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsDesignMetrics]. */
    class Builder internal constructor() {

        private var distinctProjectsCreatedCount: JsonField<Long>? = null
        private var distinctProjectsUsedCount: JsonField<Long>? = null
        private var distinctSessionCount: JsonField<Long>? = null
        private var messageCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsDesignMetrics: BetaAnalyticsDesignMetrics) = apply {
            distinctProjectsCreatedCount = betaAnalyticsDesignMetrics.distinctProjectsCreatedCount
            distinctProjectsUsedCount = betaAnalyticsDesignMetrics.distinctProjectsUsedCount
            distinctSessionCount = betaAnalyticsDesignMetrics.distinctSessionCount
            messageCount = betaAnalyticsDesignMetrics.messageCount
            additionalProperties = betaAnalyticsDesignMetrics.additionalProperties.toMutableMap()
        }

        /**
         * Number of distinct Claude Design projects created. Exact in date-range mode: a creation
         * belongs to exactly one day, so the per-day counts never overlap and their sum over the
         * window is the exact count of distinct creations in it.
         */
        fun distinctProjectsCreatedCount(distinctProjectsCreatedCount: Long) =
            distinctProjectsCreatedCount(JsonField.of(distinctProjectsCreatedCount))

        /**
         * Sets [Builder.distinctProjectsCreatedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctProjectsCreatedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctProjectsCreatedCount(distinctProjectsCreatedCount: JsonField<Long>) = apply {
            this.distinctProjectsCreatedCount = distinctProjectsCreatedCount
        }

        /**
         * Number of distinct Claude Design projects the user worked in. Approximate (HLL, typical
         * error <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
         * computed.
         */
        fun distinctProjectsUsedCount(distinctProjectsUsedCount: Long?) =
            distinctProjectsUsedCount(JsonField.ofNullable(distinctProjectsUsedCount))

        /**
         * Alias for [Builder.distinctProjectsUsedCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun distinctProjectsUsedCount(distinctProjectsUsedCount: Long) =
            distinctProjectsUsedCount(distinctProjectsUsedCount as Long?)

        /**
         * Alias for calling [Builder.distinctProjectsUsedCount] with
         * `distinctProjectsUsedCount.orElse(null)`.
         */
        fun distinctProjectsUsedCount(distinctProjectsUsedCount: Optional<Long>) =
            distinctProjectsUsedCount(distinctProjectsUsedCount.getOrNull())

        /**
         * Sets [Builder.distinctProjectsUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctProjectsUsedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctProjectsUsedCount(distinctProjectsUsedCount: JsonField<Long>) = apply {
            this.distinctProjectsUsedCount = distinctProjectsUsedCount
        }

        /**
         * Number of distinct Claude Design sessions. Approximate (HLL, typical error <2%) in
         * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
         */
        fun distinctSessionCount(distinctSessionCount: Long?) =
            distinctSessionCount(JsonField.ofNullable(distinctSessionCount))

        /**
         * Alias for [Builder.distinctSessionCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun distinctSessionCount(distinctSessionCount: Long) =
            distinctSessionCount(distinctSessionCount as Long?)

        /**
         * Alias for calling [Builder.distinctSessionCount] with
         * `distinctSessionCount.orElse(null)`.
         */
        fun distinctSessionCount(distinctSessionCount: Optional<Long>) =
            distinctSessionCount(distinctSessionCount.getOrNull())

        /**
         * Sets [Builder.distinctSessionCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctSessionCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun distinctSessionCount(distinctSessionCount: JsonField<Long>) = apply {
            this.distinctSessionCount = distinctSessionCount
        }

        /** Number of messages sent in Claude Design sessions */
        fun messageCount(messageCount: Long) = messageCount(JsonField.of(messageCount))

        /**
         * Sets [Builder.messageCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.messageCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun messageCount(messageCount: JsonField<Long>) = apply { this.messageCount = messageCount }

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
         * Returns an immutable instance of [BetaAnalyticsDesignMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .distinctProjectsCreatedCount()
         * .distinctProjectsUsedCount()
         * .distinctSessionCount()
         * .messageCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsDesignMetrics =
            BetaAnalyticsDesignMetrics(
                checkRequired("distinctProjectsCreatedCount", distinctProjectsCreatedCount),
                checkRequired("distinctProjectsUsedCount", distinctProjectsUsedCount),
                checkRequired("distinctSessionCount", distinctSessionCount),
                checkRequired("messageCount", messageCount),
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
    fun validate(): BetaAnalyticsDesignMetrics = apply {
        if (validated) {
            return@apply
        }

        distinctProjectsCreatedCount()
        distinctProjectsUsedCount()
        distinctSessionCount()
        messageCount()
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
        (if (distinctProjectsCreatedCount.asKnown().isPresent) 1 else 0) +
            (if (distinctProjectsUsedCount.asKnown().isPresent) 1 else 0) +
            (if (distinctSessionCount.asKnown().isPresent) 1 else 0) +
            (if (messageCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsDesignMetrics &&
            distinctProjectsCreatedCount == other.distinctProjectsCreatedCount &&
            distinctProjectsUsedCount == other.distinctProjectsUsedCount &&
            distinctSessionCount == other.distinctSessionCount &&
            messageCount == other.messageCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            distinctProjectsCreatedCount,
            distinctProjectsUsedCount,
            distinctSessionCount,
            messageCount,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsDesignMetrics{distinctProjectsCreatedCount=$distinctProjectsCreatedCount, distinctProjectsUsedCount=$distinctProjectsUsedCount, distinctSessionCount=$distinctSessionCount, messageCount=$messageCount, additionalProperties=$additionalProperties}"
}
