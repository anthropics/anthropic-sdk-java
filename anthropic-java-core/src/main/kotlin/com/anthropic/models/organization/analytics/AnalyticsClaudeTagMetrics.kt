package com.anthropic.models.organization.analytics

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

/** Claude Tag activity metrics for a single linked user on a given day. */
class AnalyticsClaudeTagMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val activeDayCount: JsonField<Long>,
    private val activeMessageCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("active_day_count")
        @ExcludeMissing
        activeDayCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("active_message_count")
        @ExcludeMissing
        activeMessageCount: JsonField<Long> = JsonMissing.of(),
    ) : this(activeDayCount, activeMessageCount, mutableMapOf())

    /**
     * 1 on a day the user was active in Claude Tag in Slack, otherwise 0. Summed over the window in
     * date-range mode, so it is the number of active days; on grouped rows, the total of each
     * member's active days. Null when Claude Tag data cannot be read for the request. Otherwise, 0
     * on a row filtered by project, and null on any other row when no covered day was measured.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun activeDayCount(): Optional<Long> = activeDayCount.getOptional("active_day_count")

    /**
     * Number of the user's Slack messages that made them active: @-mentions, direct messages, group
     * direct message asks, and thread posts Claude answered. Other messages are not counted. Null
     * when Claude Tag data cannot be read for the request. Otherwise, 0 on a row filtered by
     * project, and null on any other row when no covered day was measured.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun activeMessageCount(): Optional<Long> =
        activeMessageCount.getOptional("active_message_count")

    /**
     * Returns the raw JSON value of [activeDayCount].
     *
     * Unlike [activeDayCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("active_day_count")
    @ExcludeMissing
    fun _activeDayCount(): JsonField<Long> = activeDayCount

    /**
     * Returns the raw JSON value of [activeMessageCount].
     *
     * Unlike [activeMessageCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("active_message_count")
    @ExcludeMissing
    fun _activeMessageCount(): JsonField<Long> = activeMessageCount

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
         * Returns a mutable builder for constructing an instance of [AnalyticsClaudeTagMetrics].
         *
         * The following fields are required:
         * ```java
         * .activeDayCount()
         * .activeMessageCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [AnalyticsClaudeTagMetrics]. */
    class Builder internal constructor() {

        private var activeDayCount: JsonField<Long>? = null
        private var activeMessageCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(analyticsClaudeTagMetrics: AnalyticsClaudeTagMetrics) = apply {
            activeDayCount = analyticsClaudeTagMetrics.activeDayCount
            activeMessageCount = analyticsClaudeTagMetrics.activeMessageCount
            additionalProperties = analyticsClaudeTagMetrics.additionalProperties.toMutableMap()
        }

        /**
         * 1 on a day the user was active in Claude Tag in Slack, otherwise 0. Summed over the
         * window in date-range mode, so it is the number of active days; on grouped rows, the total
         * of each member's active days. Null when Claude Tag data cannot be read for the request.
         * Otherwise, 0 on a row filtered by project, and null on any other row when no covered day
         * was measured.
         */
        fun activeDayCount(activeDayCount: Long?) =
            activeDayCount(JsonField.ofNullable(activeDayCount))

        /**
         * Alias for [Builder.activeDayCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun activeDayCount(activeDayCount: Long) = activeDayCount(activeDayCount as Long?)

        /** Alias for calling [Builder.activeDayCount] with `activeDayCount.orElse(null)`. */
        fun activeDayCount(activeDayCount: Optional<Long>) =
            activeDayCount(activeDayCount.getOrNull())

        /**
         * Sets [Builder.activeDayCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.activeDayCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun activeDayCount(activeDayCount: JsonField<Long>) = apply {
            this.activeDayCount = activeDayCount
        }

        /**
         * Number of the user's Slack messages that made them active: @-mentions, direct messages,
         * group direct message asks, and thread posts Claude answered. Other messages are not
         * counted. Null when Claude Tag data cannot be read for the request. Otherwise, 0 on a row
         * filtered by project, and null on any other row when no covered day was measured.
         */
        fun activeMessageCount(activeMessageCount: Long?) =
            activeMessageCount(JsonField.ofNullable(activeMessageCount))

        /**
         * Alias for [Builder.activeMessageCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun activeMessageCount(activeMessageCount: Long) =
            activeMessageCount(activeMessageCount as Long?)

        /**
         * Alias for calling [Builder.activeMessageCount] with `activeMessageCount.orElse(null)`.
         */
        fun activeMessageCount(activeMessageCount: Optional<Long>) =
            activeMessageCount(activeMessageCount.getOrNull())

        /**
         * Sets [Builder.activeMessageCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.activeMessageCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun activeMessageCount(activeMessageCount: JsonField<Long>) = apply {
            this.activeMessageCount = activeMessageCount
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
         * Returns an immutable instance of [AnalyticsClaudeTagMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .activeDayCount()
         * .activeMessageCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): AnalyticsClaudeTagMetrics =
            AnalyticsClaudeTagMetrics(
                checkRequired("activeDayCount", activeDayCount),
                checkRequired("activeMessageCount", activeMessageCount),
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
    fun validate(): AnalyticsClaudeTagMetrics = apply {
        if (validated) {
            return@apply
        }

        activeDayCount()
        activeMessageCount()
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
        (if (activeDayCount.asKnown().isPresent) 1 else 0) +
            (if (activeMessageCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is AnalyticsClaudeTagMetrics &&
            activeDayCount == other.activeDayCount &&
            activeMessageCount == other.activeMessageCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(activeDayCount, activeMessageCount, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "AnalyticsClaudeTagMetrics{activeDayCount=$activeDayCount, activeMessageCount=$activeMessageCount, additionalProperties=$additionalProperties}"
}
