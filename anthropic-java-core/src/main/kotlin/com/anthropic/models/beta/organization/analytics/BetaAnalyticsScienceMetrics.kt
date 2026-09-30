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

/** Claude Science activity metrics for a single user on a given day. */
class BetaAnalyticsScienceMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val delegationCount: JsonField<Long>,
    private val distinctSessionCount: JsonField<Long>,
    private val messageCount: JsonField<Long>,
    private val remoteComputeJobCount: JsonField<Long>,
    private val skillsUsedCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("delegation_count")
        @ExcludeMissing
        delegationCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("distinct_session_count")
        @ExcludeMissing
        distinctSessionCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("message_count")
        @ExcludeMissing
        messageCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("remote_compute_job_count")
        @ExcludeMissing
        remoteComputeJobCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("skills_used_count")
        @ExcludeMissing
        skillsUsedCount: JsonField<Long> = JsonMissing.of(),
    ) : this(
        delegationCount,
        distinctSessionCount,
        messageCount,
        remoteComputeJobCount,
        skillsUsedCount,
        mutableMapOf(),
    )

    /**
     * Number of delegations (handoffs to a specialized agent) in Claude Science sessions
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun delegationCount(): Long = delegationCount.getRequired("delegation_count")

    /**
     * Number of distinct Claude Science sessions. Approximate (HLL, typical error <2%) in
     * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSessionCount(): Optional<Long> =
        distinctSessionCount.getOptional("distinct_session_count")

    /**
     * Number of messages sent in Claude Science sessions
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun messageCount(): Long = messageCount.getRequired("message_count")

    /**
     * Number of remote compute jobs launched from Claude Science sessions
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun remoteComputeJobCount(): Long =
        remoteComputeJobCount.getRequired("remote_compute_job_count")

    /**
     * Total number of skill invocations in Claude Science sessions
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun skillsUsedCount(): Long = skillsUsedCount.getRequired("skills_used_count")

    /**
     * Returns the raw JSON value of [delegationCount].
     *
     * Unlike [delegationCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("delegation_count")
    @ExcludeMissing
    fun _delegationCount(): JsonField<Long> = delegationCount

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

    /**
     * Returns the raw JSON value of [remoteComputeJobCount].
     *
     * Unlike [remoteComputeJobCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("remote_compute_job_count")
    @ExcludeMissing
    fun _remoteComputeJobCount(): JsonField<Long> = remoteComputeJobCount

    /**
     * Returns the raw JSON value of [skillsUsedCount].
     *
     * Unlike [skillsUsedCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("skills_used_count")
    @ExcludeMissing
    fun _skillsUsedCount(): JsonField<Long> = skillsUsedCount

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsScienceMetrics].
         *
         * The following fields are required:
         * ```java
         * .delegationCount()
         * .distinctSessionCount()
         * .messageCount()
         * .remoteComputeJobCount()
         * .skillsUsedCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsScienceMetrics]. */
    class Builder internal constructor() {

        private var delegationCount: JsonField<Long>? = null
        private var distinctSessionCount: JsonField<Long>? = null
        private var messageCount: JsonField<Long>? = null
        private var remoteComputeJobCount: JsonField<Long>? = null
        private var skillsUsedCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsScienceMetrics: BetaAnalyticsScienceMetrics) = apply {
            delegationCount = betaAnalyticsScienceMetrics.delegationCount
            distinctSessionCount = betaAnalyticsScienceMetrics.distinctSessionCount
            messageCount = betaAnalyticsScienceMetrics.messageCount
            remoteComputeJobCount = betaAnalyticsScienceMetrics.remoteComputeJobCount
            skillsUsedCount = betaAnalyticsScienceMetrics.skillsUsedCount
            additionalProperties = betaAnalyticsScienceMetrics.additionalProperties.toMutableMap()
        }

        /** Number of delegations (handoffs to a specialized agent) in Claude Science sessions */
        fun delegationCount(delegationCount: Long) = delegationCount(JsonField.of(delegationCount))

        /**
         * Sets [Builder.delegationCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.delegationCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun delegationCount(delegationCount: JsonField<Long>) = apply {
            this.delegationCount = delegationCount
        }

        /**
         * Number of distinct Claude Science sessions. Approximate (HLL, typical error <2%) in
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

        /** Number of messages sent in Claude Science sessions */
        fun messageCount(messageCount: Long) = messageCount(JsonField.of(messageCount))

        /**
         * Sets [Builder.messageCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.messageCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun messageCount(messageCount: JsonField<Long>) = apply { this.messageCount = messageCount }

        /** Number of remote compute jobs launched from Claude Science sessions */
        fun remoteComputeJobCount(remoteComputeJobCount: Long) =
            remoteComputeJobCount(JsonField.of(remoteComputeJobCount))

        /**
         * Sets [Builder.remoteComputeJobCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.remoteComputeJobCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun remoteComputeJobCount(remoteComputeJobCount: JsonField<Long>) = apply {
            this.remoteComputeJobCount = remoteComputeJobCount
        }

        /** Total number of skill invocations in Claude Science sessions */
        fun skillsUsedCount(skillsUsedCount: Long) = skillsUsedCount(JsonField.of(skillsUsedCount))

        /**
         * Sets [Builder.skillsUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.skillsUsedCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun skillsUsedCount(skillsUsedCount: JsonField<Long>) = apply {
            this.skillsUsedCount = skillsUsedCount
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
         * Returns an immutable instance of [BetaAnalyticsScienceMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .delegationCount()
         * .distinctSessionCount()
         * .messageCount()
         * .remoteComputeJobCount()
         * .skillsUsedCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsScienceMetrics =
            BetaAnalyticsScienceMetrics(
                checkRequired("delegationCount", delegationCount),
                checkRequired("distinctSessionCount", distinctSessionCount),
                checkRequired("messageCount", messageCount),
                checkRequired("remoteComputeJobCount", remoteComputeJobCount),
                checkRequired("skillsUsedCount", skillsUsedCount),
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
    fun validate(): BetaAnalyticsScienceMetrics = apply {
        if (validated) {
            return@apply
        }

        delegationCount()
        distinctSessionCount()
        messageCount()
        remoteComputeJobCount()
        skillsUsedCount()
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
        (if (delegationCount.asKnown().isPresent) 1 else 0) +
            (if (distinctSessionCount.asKnown().isPresent) 1 else 0) +
            (if (messageCount.asKnown().isPresent) 1 else 0) +
            (if (remoteComputeJobCount.asKnown().isPresent) 1 else 0) +
            (if (skillsUsedCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsScienceMetrics &&
            delegationCount == other.delegationCount &&
            distinctSessionCount == other.distinctSessionCount &&
            messageCount == other.messageCount &&
            remoteComputeJobCount == other.remoteComputeJobCount &&
            skillsUsedCount == other.skillsUsedCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            delegationCount,
            distinctSessionCount,
            messageCount,
            remoteComputeJobCount,
            skillsUsedCount,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsScienceMetrics{delegationCount=$delegationCount, distinctSessionCount=$distinctSessionCount, messageCount=$messageCount, remoteComputeJobCount=$remoteComputeJobCount, skillsUsedCount=$skillsUsedCount, additionalProperties=$additionalProperties}"
}
