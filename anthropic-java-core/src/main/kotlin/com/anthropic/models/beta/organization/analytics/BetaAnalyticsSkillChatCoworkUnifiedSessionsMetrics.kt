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

/**
 * A skill's use in Cowork sessions recorded while members had Chat and Cowork unified turned on.
 */
class BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val distinctSessionSkillUsedCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("distinct_session_skill_used_count")
        @ExcludeMissing
        distinctSessionSkillUsedCount: JsonField<Long> = JsonMissing.of()
    ) : this(distinctSessionSkillUsedCount, mutableMapOf())

    /**
     * Same measure as `cowork_metrics.distinct_session_skill_used_count`, for activity recorded
     * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%) in
     * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSessionSkillUsedCount(): Optional<Long> =
        distinctSessionSkillUsedCount.getOptional("distinct_session_skill_used_count")

    /**
     * Returns the raw JSON value of [distinctSessionSkillUsedCount].
     *
     * Unlike [distinctSessionSkillUsedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_session_skill_used_count")
    @ExcludeMissing
    fun _distinctSessionSkillUsedCount(): JsonField<Long> = distinctSessionSkillUsedCount

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
         * [BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics].
         *
         * The following fields are required:
         * ```java
         * .distinctSessionSkillUsedCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics]
         * with the required [distinctSessionSkillUsedCount] set to the given value.
         */
        @JvmStatic
        fun of(distinctSessionSkillUsedCount: Long?) =
            builder().distinctSessionSkillUsedCount(distinctSessionSkillUsedCount).build()

        /**
         * Alias for [of].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        @JvmStatic
        fun of(distinctSessionSkillUsedCount: Long) = of(distinctSessionSkillUsedCount as Long?)

        /** Alias for calling [of] with `distinctSessionSkillUsedCount.orElse(null)`. */
        @JvmStatic
        fun of(distinctSessionSkillUsedCount: Optional<Long>) =
            of(distinctSessionSkillUsedCount.getOrNull())
    }

    /** A builder for [BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics]. */
    class Builder internal constructor() {

        private var distinctSessionSkillUsedCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaAnalyticsSkillChatCoworkUnifiedSessionsMetrics:
                BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics
        ) = apply {
            distinctSessionSkillUsedCount =
                betaAnalyticsSkillChatCoworkUnifiedSessionsMetrics.distinctSessionSkillUsedCount
            additionalProperties =
                betaAnalyticsSkillChatCoworkUnifiedSessionsMetrics.additionalProperties
                    .toMutableMap()
        }

        /**
         * Same measure as `cowork_metrics.distinct_session_skill_used_count`, for activity recorded
         * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%)
         * in date-range mode. Null on aggregated rows where a distinct count cannot be computed.
         */
        fun distinctSessionSkillUsedCount(distinctSessionSkillUsedCount: Long?) =
            distinctSessionSkillUsedCount(JsonField.ofNullable(distinctSessionSkillUsedCount))

        /**
         * Alias for [Builder.distinctSessionSkillUsedCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun distinctSessionSkillUsedCount(distinctSessionSkillUsedCount: Long) =
            distinctSessionSkillUsedCount(distinctSessionSkillUsedCount as Long?)

        /**
         * Alias for calling [Builder.distinctSessionSkillUsedCount] with
         * `distinctSessionSkillUsedCount.orElse(null)`.
         */
        fun distinctSessionSkillUsedCount(distinctSessionSkillUsedCount: Optional<Long>) =
            distinctSessionSkillUsedCount(distinctSessionSkillUsedCount.getOrNull())

        /**
         * Sets [Builder.distinctSessionSkillUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctSessionSkillUsedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctSessionSkillUsedCount(distinctSessionSkillUsedCount: JsonField<Long>) = apply {
            this.distinctSessionSkillUsedCount = distinctSessionSkillUsedCount
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
         * Returns an immutable instance of [BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .distinctSessionSkillUsedCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics =
            BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics(
                checkRequired("distinctSessionSkillUsedCount", distinctSessionSkillUsedCount),
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
    fun validate(): BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics = apply {
        if (validated) {
            return@apply
        }

        distinctSessionSkillUsedCount()
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
    internal fun validity(): Int = (if (distinctSessionSkillUsedCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics &&
            distinctSessionSkillUsedCount == other.distinctSessionSkillUsedCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(distinctSessionSkillUsedCount, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics{distinctSessionSkillUsedCount=$distinctSessionSkillUsedCount, additionalProperties=$additionalProperties}"
}
