package com.anthropic.models.beta.organization.analytics.usage

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
import kotlin.jvm.optionals.getOrNull

class BetaAnalyticsUsageItem
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val endingAt: JsonField<OffsetDateTime>,
    private val group: JsonField<BetaAnalyticsUsageGroup>,
    private val startingAt: JsonField<OffsetDateTime>,
    private val usage: JsonField<BetaAnalyticsUsageMetrics>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("ending_at")
        @ExcludeMissing
        endingAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("group")
        @ExcludeMissing
        group: JsonField<BetaAnalyticsUsageGroup> = JsonMissing.of(),
        @JsonProperty("starting_at")
        @ExcludeMissing
        startingAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("usage")
        @ExcludeMissing
        usage: JsonField<BetaAnalyticsUsageMetrics> = JsonMissing.of(),
    ) : this(endingAt, group, startingAt, usage, mutableMapOf())

    /**
     * End of the period this row covers (exclusive), as an RFC 3339 UTC timestamp: the end of the
     * row's time bucket when a time-bucket width is set; otherwise the requested `ending_at` (or,
     * when omitted, the earlier of now and `starting_at` plus 31 days) rounded down to the hour,
     * because without a time bucket the report covers whole hours.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun endingAt(): OffsetDateTime = endingAt.getRequired("ending_at")

    /**
     * The dimension values this row's usage is broken out by.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun group(): BetaAnalyticsUsageGroup = group.getRequired("group")

    /**
     * Start of the period this row covers (inclusive), as an RFC 3339 UTC timestamp: the start of
     * the row's time bucket when a time-bucket width is set, otherwise the requested `starting_at`
     * rounded down to the hour.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun startingAt(): OffsetDateTime = startingAt.getRequired("starting_at")

    /**
     * Token usage for this row.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun usage(): BetaAnalyticsUsageMetrics = usage.getRequired("usage")

    /**
     * Returns the raw JSON value of [endingAt].
     *
     * Unlike [endingAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ending_at") @ExcludeMissing fun _endingAt(): JsonField<OffsetDateTime> = endingAt

    /**
     * Returns the raw JSON value of [group].
     *
     * Unlike [group], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("group") @ExcludeMissing fun _group(): JsonField<BetaAnalyticsUsageGroup> = group

    /**
     * Returns the raw JSON value of [startingAt].
     *
     * Unlike [startingAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("starting_at")
    @ExcludeMissing
    fun _startingAt(): JsonField<OffsetDateTime> = startingAt

    /**
     * Returns the raw JSON value of [usage].
     *
     * Unlike [usage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("usage")
    @ExcludeMissing
    fun _usage(): JsonField<BetaAnalyticsUsageMetrics> = usage

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsUsageItem].
         *
         * The following fields are required:
         * ```java
         * .endingAt()
         * .group()
         * .startingAt()
         * .usage()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsUsageItem]. */
    class Builder internal constructor() {

        private var endingAt: JsonField<OffsetDateTime>? = null
        private var group: JsonField<BetaAnalyticsUsageGroup>? = null
        private var startingAt: JsonField<OffsetDateTime>? = null
        private var usage: JsonField<BetaAnalyticsUsageMetrics>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsUsageItem: BetaAnalyticsUsageItem) = apply {
            endingAt = betaAnalyticsUsageItem.endingAt
            group = betaAnalyticsUsageItem.group
            startingAt = betaAnalyticsUsageItem.startingAt
            usage = betaAnalyticsUsageItem.usage
            additionalProperties = betaAnalyticsUsageItem.additionalProperties.toMutableMap()
        }

        /**
         * End of the period this row covers (exclusive), as an RFC 3339 UTC timestamp: the end of
         * the row's time bucket when a time-bucket width is set; otherwise the requested
         * `ending_at` (or, when omitted, the earlier of now and `starting_at` plus 31 days) rounded
         * down to the hour, because without a time bucket the report covers whole hours.
         */
        fun endingAt(endingAt: OffsetDateTime) = endingAt(JsonField.of(endingAt))

        /**
         * Sets [Builder.endingAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endingAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun endingAt(endingAt: JsonField<OffsetDateTime>) = apply { this.endingAt = endingAt }

        /** The dimension values this row's usage is broken out by. */
        fun group(group: BetaAnalyticsUsageGroup) = group(JsonField.of(group))

        /**
         * Sets [Builder.group] to an arbitrary JSON value.
         *
         * You should usually call [Builder.group] with a well-typed [BetaAnalyticsUsageGroup] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun group(group: JsonField<BetaAnalyticsUsageGroup>) = apply { this.group = group }

        /**
         * Start of the period this row covers (inclusive), as an RFC 3339 UTC timestamp: the start
         * of the row's time bucket when a time-bucket width is set, otherwise the requested
         * `starting_at` rounded down to the hour.
         */
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

        /** Token usage for this row. */
        fun usage(usage: BetaAnalyticsUsageMetrics) = usage(JsonField.of(usage))

        /**
         * Sets [Builder.usage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.usage] with a well-typed [BetaAnalyticsUsageMetrics]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun usage(usage: JsonField<BetaAnalyticsUsageMetrics>) = apply { this.usage = usage }

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
         * Returns an immutable instance of [BetaAnalyticsUsageItem].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .endingAt()
         * .group()
         * .startingAt()
         * .usage()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsUsageItem =
            BetaAnalyticsUsageItem(
                checkRequired("endingAt", endingAt),
                checkRequired("group", group),
                checkRequired("startingAt", startingAt),
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
    fun validate(): BetaAnalyticsUsageItem = apply {
        if (validated) {
            return@apply
        }

        endingAt()
        group().validate()
        startingAt()
        usage().validate()
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
            (group.asKnown().getOrNull()?.validity() ?: 0) +
            (if (startingAt.asKnown().isPresent) 1 else 0) +
            (usage.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsUsageItem &&
            endingAt == other.endingAt &&
            group == other.group &&
            startingAt == other.startingAt &&
            usage == other.usage &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(endingAt, group, startingAt, usage, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsUsageItem{endingAt=$endingAt, group=$group, startingAt=$startingAt, usage=$usage, additionalProperties=$additionalProperties}"
}
