package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
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
import kotlin.jvm.optionals.getOrNull

class BetaAnalyticsCostReportTimeBucket
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val endingAt: JsonField<OffsetDateTime>,
    private val results: JsonField<List<BetaAnalyticsCostBucketedResult>>,
    private val startingAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("ending_at")
        @ExcludeMissing
        endingAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("results")
        @ExcludeMissing
        results: JsonField<List<BetaAnalyticsCostBucketedResult>> = JsonMissing.of(),
        @JsonProperty("starting_at")
        @ExcludeMissing
        startingAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(endingAt, results, startingAt, mutableMapOf())

    /**
     * End of the time bucket (exclusive) in RFC 3339 format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun endingAt(): OffsetDateTime = endingAt.getRequired("ending_at")

    /**
     * Rows for this time bucket. Empty when the bucket has no data; otherwise a single combined row
     * when `group_by[]` is omitted, or one row per group (subject to the per-bucket group cap
     * described on the `group_by[]` parameter).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun results(): List<BetaAnalyticsCostBucketedResult> = results.getRequired("results")

    /**
     * Start of the time bucket (inclusive) in RFC 3339 format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun startingAt(): OffsetDateTime = startingAt.getRequired("starting_at")

    /**
     * Returns the raw JSON value of [endingAt].
     *
     * Unlike [endingAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ending_at") @ExcludeMissing fun _endingAt(): JsonField<OffsetDateTime> = endingAt

    /**
     * Returns the raw JSON value of [results].
     *
     * Unlike [results], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("results")
    @ExcludeMissing
    fun _results(): JsonField<List<BetaAnalyticsCostBucketedResult>> = results

    /**
     * Returns the raw JSON value of [startingAt].
     *
     * Unlike [startingAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("starting_at")
    @ExcludeMissing
    fun _startingAt(): JsonField<OffsetDateTime> = startingAt

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
         * [BetaAnalyticsCostReportTimeBucket].
         *
         * The following fields are required:
         * ```java
         * .endingAt()
         * .results()
         * .startingAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsCostReportTimeBucket]. */
    class Builder internal constructor() {

        private var endingAt: JsonField<OffsetDateTime>? = null
        private var results: JsonField<MutableList<BetaAnalyticsCostBucketedResult>>? = null
        private var startingAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsCostReportTimeBucket: BetaAnalyticsCostReportTimeBucket) =
            apply {
                endingAt = betaAnalyticsCostReportTimeBucket.endingAt
                results =
                    betaAnalyticsCostReportTimeBucket.results
                        .map { it.toMutableList() }
                        .takeUnless { it.isMissing() }
                startingAt = betaAnalyticsCostReportTimeBucket.startingAt
                additionalProperties =
                    betaAnalyticsCostReportTimeBucket.additionalProperties.toMutableMap()
            }

        /** End of the time bucket (exclusive) in RFC 3339 format. */
        fun endingAt(endingAt: OffsetDateTime) = endingAt(JsonField.of(endingAt))

        /**
         * Sets [Builder.endingAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endingAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun endingAt(endingAt: JsonField<OffsetDateTime>) = apply { this.endingAt = endingAt }

        /**
         * Rows for this time bucket. Empty when the bucket has no data; otherwise a single combined
         * row when `group_by[]` is omitted, or one row per group (subject to the per-bucket group
         * cap described on the `group_by[]` parameter).
         */
        fun results(results: List<BetaAnalyticsCostBucketedResult>) = results(JsonField.of(results))

        /**
         * Sets [Builder.results] to an arbitrary JSON value.
         *
         * You should usually call [Builder.results] with a well-typed
         * `List<BetaAnalyticsCostBucketedResult>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun results(results: JsonField<List<BetaAnalyticsCostBucketedResult>>) = apply {
            this.results = results.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaAnalyticsCostBucketedResult] to [results].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addResult(result: BetaAnalyticsCostBucketedResult) = apply {
            results =
                (results ?: JsonField.of(mutableListOf())).also {
                    checkKnown("results", it).add(result)
                }
        }

        /** Start of the time bucket (inclusive) in RFC 3339 format. */
        fun startingAt(startingAt: OffsetDateTime) = startingAt(JsonField.of(startingAt))

        /**
         * Sets [Builder.startingAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.startingAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun startingAt(startingAt: JsonField<OffsetDateTime>) = apply {
            this.startingAt = startingAt
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
         * Returns an immutable instance of [BetaAnalyticsCostReportTimeBucket].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .endingAt()
         * .results()
         * .startingAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsCostReportTimeBucket =
            BetaAnalyticsCostReportTimeBucket(
                checkRequired("endingAt", endingAt),
                checkRequired("results", results).map { it.toImmutable() },
                checkRequired("startingAt", startingAt),
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
    fun validate(): BetaAnalyticsCostReportTimeBucket = apply {
        if (validated) {
            return@apply
        }

        endingAt()
        results().forEach { it.validate() }
        startingAt()
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
        (if (endingAt.asKnown().isPresent) 1 else 0) +
            (results.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (startingAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsCostReportTimeBucket &&
            endingAt == other.endingAt &&
            results == other.results &&
            startingAt == other.startingAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(endingAt, results, startingAt, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsCostReportTimeBucket{endingAt=$endingAt, results=$results, startingAt=$startingAt, additionalProperties=$additionalProperties}"
}
