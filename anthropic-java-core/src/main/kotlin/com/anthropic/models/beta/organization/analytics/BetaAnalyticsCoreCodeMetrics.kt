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

/** Core Claude Code activity metrics for a single user on a given day. */
class BetaAnalyticsCoreCodeMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val artifactsCreatedCount: JsonField<Long>,
    private val commitCount: JsonField<Long>,
    private val distinctSessionCount: JsonField<Long>,
    private val linesOfCode: JsonField<BetaAnalyticsLinesOfCode>,
    private val pullRequestCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("artifacts_created_count")
        @ExcludeMissing
        artifactsCreatedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("commit_count")
        @ExcludeMissing
        commitCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("distinct_session_count")
        @ExcludeMissing
        distinctSessionCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("lines_of_code")
        @ExcludeMissing
        linesOfCode: JsonField<BetaAnalyticsLinesOfCode> = JsonMissing.of(),
        @JsonProperty("pull_request_count")
        @ExcludeMissing
        pullRequestCount: JsonField<Long> = JsonMissing.of(),
    ) : this(
        artifactsCreatedCount,
        commitCount,
        distinctSessionCount,
        linesOfCode,
        pullRequestCount,
        mutableMapOf(),
    )

    /**
     * Number of artifacts created in Claude Code sessions: an artifact counts once, on the day a
     * session first saves it. Counted from 2026-08-17; 0 on earlier days. Exact in date-range mode:
     * a creation belongs to exactly one day, so the per-day counts never overlap and their sum over
     * the window is the exact count of distinct creations in it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun artifactsCreatedCount(): Long = artifactsCreatedCount.getRequired("artifacts_created_count")

    /**
     * Number of commits made via Claude Code
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun commitCount(): Long = commitCount.getRequired("commit_count")

    /**
     * Number of distinct Claude Code sessions. On aggregated rows and in date-range mode: summed
     * per-day distinct counts. A session essentially never spans a UTC day, so the sum is in
     * practice the true distinct count.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSessionCount(): Optional<Long> =
        distinctSessionCount.getOptional("distinct_session_count")

    /**
     * Lines of code added and removed via Claude Code.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun linesOfCode(): BetaAnalyticsLinesOfCode = linesOfCode.getRequired("lines_of_code")

    /**
     * Number of pull requests created via Claude Code
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pullRequestCount(): Long = pullRequestCount.getRequired("pull_request_count")

    /**
     * Returns the raw JSON value of [artifactsCreatedCount].
     *
     * Unlike [artifactsCreatedCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("artifacts_created_count")
    @ExcludeMissing
    fun _artifactsCreatedCount(): JsonField<Long> = artifactsCreatedCount

    /**
     * Returns the raw JSON value of [commitCount].
     *
     * Unlike [commitCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("commit_count") @ExcludeMissing fun _commitCount(): JsonField<Long> = commitCount

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
     * Returns the raw JSON value of [linesOfCode].
     *
     * Unlike [linesOfCode], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("lines_of_code")
    @ExcludeMissing
    fun _linesOfCode(): JsonField<BetaAnalyticsLinesOfCode> = linesOfCode

    /**
     * Returns the raw JSON value of [pullRequestCount].
     *
     * Unlike [pullRequestCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("pull_request_count")
    @ExcludeMissing
    fun _pullRequestCount(): JsonField<Long> = pullRequestCount

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsCoreCodeMetrics].
         *
         * The following fields are required:
         * ```java
         * .artifactsCreatedCount()
         * .commitCount()
         * .distinctSessionCount()
         * .linesOfCode()
         * .pullRequestCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsCoreCodeMetrics]. */
    class Builder internal constructor() {

        private var artifactsCreatedCount: JsonField<Long>? = null
        private var commitCount: JsonField<Long>? = null
        private var distinctSessionCount: JsonField<Long>? = null
        private var linesOfCode: JsonField<BetaAnalyticsLinesOfCode>? = null
        private var pullRequestCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsCoreCodeMetrics: BetaAnalyticsCoreCodeMetrics) = apply {
            artifactsCreatedCount = betaAnalyticsCoreCodeMetrics.artifactsCreatedCount
            commitCount = betaAnalyticsCoreCodeMetrics.commitCount
            distinctSessionCount = betaAnalyticsCoreCodeMetrics.distinctSessionCount
            linesOfCode = betaAnalyticsCoreCodeMetrics.linesOfCode
            pullRequestCount = betaAnalyticsCoreCodeMetrics.pullRequestCount
            additionalProperties = betaAnalyticsCoreCodeMetrics.additionalProperties.toMutableMap()
        }

        /**
         * Number of artifacts created in Claude Code sessions: an artifact counts once, on the day
         * a session first saves it. Counted from 2026-08-17; 0 on earlier days. Exact in date-range
         * mode: a creation belongs to exactly one day, so the per-day counts never overlap and
         * their sum over the window is the exact count of distinct creations in it.
         */
        fun artifactsCreatedCount(artifactsCreatedCount: Long) =
            artifactsCreatedCount(JsonField.of(artifactsCreatedCount))

        /**
         * Sets [Builder.artifactsCreatedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.artifactsCreatedCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun artifactsCreatedCount(artifactsCreatedCount: JsonField<Long>) = apply {
            this.artifactsCreatedCount = artifactsCreatedCount
        }

        /** Number of commits made via Claude Code */
        fun commitCount(commitCount: Long) = commitCount(JsonField.of(commitCount))

        /**
         * Sets [Builder.commitCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.commitCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun commitCount(commitCount: JsonField<Long>) = apply { this.commitCount = commitCount }

        /**
         * Number of distinct Claude Code sessions. On aggregated rows and in date-range mode:
         * summed per-day distinct counts. A session essentially never spans a UTC day, so the sum
         * is in practice the true distinct count.
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

        /** Lines of code added and removed via Claude Code. */
        fun linesOfCode(linesOfCode: BetaAnalyticsLinesOfCode) =
            linesOfCode(JsonField.of(linesOfCode))

        /**
         * Sets [Builder.linesOfCode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.linesOfCode] with a well-typed
         * [BetaAnalyticsLinesOfCode] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun linesOfCode(linesOfCode: JsonField<BetaAnalyticsLinesOfCode>) = apply {
            this.linesOfCode = linesOfCode
        }

        /** Number of pull requests created via Claude Code */
        fun pullRequestCount(pullRequestCount: Long) =
            pullRequestCount(JsonField.of(pullRequestCount))

        /**
         * Sets [Builder.pullRequestCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pullRequestCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun pullRequestCount(pullRequestCount: JsonField<Long>) = apply {
            this.pullRequestCount = pullRequestCount
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
         * Returns an immutable instance of [BetaAnalyticsCoreCodeMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .artifactsCreatedCount()
         * .commitCount()
         * .distinctSessionCount()
         * .linesOfCode()
         * .pullRequestCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsCoreCodeMetrics =
            BetaAnalyticsCoreCodeMetrics(
                checkRequired("artifactsCreatedCount", artifactsCreatedCount),
                checkRequired("commitCount", commitCount),
                checkRequired("distinctSessionCount", distinctSessionCount),
                checkRequired("linesOfCode", linesOfCode),
                checkRequired("pullRequestCount", pullRequestCount),
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
    fun validate(): BetaAnalyticsCoreCodeMetrics = apply {
        if (validated) {
            return@apply
        }

        artifactsCreatedCount()
        commitCount()
        distinctSessionCount()
        linesOfCode().validate()
        pullRequestCount()
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
        (if (artifactsCreatedCount.asKnown().isPresent) 1 else 0) +
            (if (commitCount.asKnown().isPresent) 1 else 0) +
            (if (distinctSessionCount.asKnown().isPresent) 1 else 0) +
            (linesOfCode.asKnown().getOrNull()?.validity() ?: 0) +
            (if (pullRequestCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsCoreCodeMetrics &&
            artifactsCreatedCount == other.artifactsCreatedCount &&
            commitCount == other.commitCount &&
            distinctSessionCount == other.distinctSessionCount &&
            linesOfCode == other.linesOfCode &&
            pullRequestCount == other.pullRequestCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            artifactsCreatedCount,
            commitCount,
            distinctSessionCount,
            linesOfCode,
            pullRequestCount,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsCoreCodeMetrics{artifactsCreatedCount=$artifactsCreatedCount, commitCount=$commitCount, distinctSessionCount=$distinctSessionCount, linesOfCode=$linesOfCode, pullRequestCount=$pullRequestCount, additionalProperties=$additionalProperties}"
}
