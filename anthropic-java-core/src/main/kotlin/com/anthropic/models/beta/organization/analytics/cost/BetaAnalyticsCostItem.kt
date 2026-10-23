package com.anthropic.models.beta.organization.analytics.cost

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

class BetaAnalyticsCostItem
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cost: JsonField<BetaAnalyticsCostMetrics>,
    private val endingAt: JsonField<OffsetDateTime>,
    private val group: JsonField<BetaAnalyticsCostGroup>,
    private val startingAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cost")
        @ExcludeMissing
        cost: JsonField<BetaAnalyticsCostMetrics> = JsonMissing.of(),
        @JsonProperty("ending_at")
        @ExcludeMissing
        endingAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("group")
        @ExcludeMissing
        group: JsonField<BetaAnalyticsCostGroup> = JsonMissing.of(),
        @JsonProperty("starting_at")
        @ExcludeMissing
        startingAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(cost, endingAt, group, startingAt, mutableMapOf())

    /**
     * Cost for this row.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun cost(): BetaAnalyticsCostMetrics = cost.getRequired("cost")

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
     * The dimension values this row's cost is broken out by.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun group(): BetaAnalyticsCostGroup = group.getRequired("group")

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
     * Returns the raw JSON value of [cost].
     *
     * Unlike [cost], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cost") @ExcludeMissing fun _cost(): JsonField<BetaAnalyticsCostMetrics> = cost

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
    @JsonProperty("group") @ExcludeMissing fun _group(): JsonField<BetaAnalyticsCostGroup> = group

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsCostItem].
         *
         * The following fields are required:
         * ```java
         * .cost()
         * .endingAt()
         * .group()
         * .startingAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsCostItem]. */
    class Builder internal constructor() {

        private var cost: JsonField<BetaAnalyticsCostMetrics>? = null
        private var endingAt: JsonField<OffsetDateTime>? = null
        private var group: JsonField<BetaAnalyticsCostGroup>? = null
        private var startingAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsCostItem: BetaAnalyticsCostItem) = apply {
            cost = betaAnalyticsCostItem.cost
            endingAt = betaAnalyticsCostItem.endingAt
            group = betaAnalyticsCostItem.group
            startingAt = betaAnalyticsCostItem.startingAt
            additionalProperties = betaAnalyticsCostItem.additionalProperties.toMutableMap()
        }

        /** Cost for this row. */
        fun cost(cost: BetaAnalyticsCostMetrics) = cost(JsonField.of(cost))

        /**
         * Sets [Builder.cost] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cost] with a well-typed [BetaAnalyticsCostMetrics] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun cost(cost: JsonField<BetaAnalyticsCostMetrics>) = apply { this.cost = cost }

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

        /** The dimension values this row's cost is broken out by. */
        fun group(group: BetaAnalyticsCostGroup) = group(JsonField.of(group))

        /**
         * Sets [Builder.group] to an arbitrary JSON value.
         *
         * You should usually call [Builder.group] with a well-typed [BetaAnalyticsCostGroup] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun group(group: JsonField<BetaAnalyticsCostGroup>) = apply { this.group = group }

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
         * Returns an immutable instance of [BetaAnalyticsCostItem].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .cost()
         * .endingAt()
         * .group()
         * .startingAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsCostItem =
            BetaAnalyticsCostItem(
                checkRequired("cost", cost),
                checkRequired("endingAt", endingAt),
                checkRequired("group", group),
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
    fun validate(): BetaAnalyticsCostItem = apply {
        if (validated) {
            return@apply
        }

        cost().validate()
        endingAt()
        group().validate()
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
        (cost.asKnown().getOrNull()?.validity() ?: 0) +
            (if (endingAt.asKnown().isPresent) 1 else 0) +
            (group.asKnown().getOrNull()?.validity() ?: 0) +
            (if (startingAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsCostItem &&
            cost == other.cost &&
            endingAt == other.endingAt &&
            group == other.group &&
            startingAt == other.startingAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(cost, endingAt, group, startingAt, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsCostItem{cost=$cost, endingAt=$endingAt, group=$group, startingAt=$startingAt, additionalProperties=$additionalProperties}"
}
