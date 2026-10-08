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
 * A connector's use in Cowork sessions recorded while members had Chat and Cowork unified turned
 * on.
 */
class BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val distinctSessionConnectorUsedCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("distinct_session_connector_used_count")
        @ExcludeMissing
        distinctSessionConnectorUsedCount: JsonField<Long> = JsonMissing.of()
    ) : this(distinctSessionConnectorUsedCount, mutableMapOf())

    /**
     * Same measure as `cowork_metrics.distinct_session_connector_used_count`, for activity recorded
     * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%) in
     * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSessionConnectorUsedCount(): Optional<Long> =
        distinctSessionConnectorUsedCount.getOptional("distinct_session_connector_used_count")

    /**
     * Returns the raw JSON value of [distinctSessionConnectorUsedCount].
     *
     * Unlike [distinctSessionConnectorUsedCount], this method doesn't throw if the JSON field has
     * an unexpected type.
     */
    @JsonProperty("distinct_session_connector_used_count")
    @ExcludeMissing
    fun _distinctSessionConnectorUsedCount(): JsonField<Long> = distinctSessionConnectorUsedCount

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
         * [BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics].
         *
         * The following fields are required:
         * ```java
         * .distinctSessionConnectorUsedCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics]
         * with the required [distinctSessionConnectorUsedCount] set to the given value.
         */
        @JvmStatic
        fun of(distinctSessionConnectorUsedCount: Long?) =
            builder().distinctSessionConnectorUsedCount(distinctSessionConnectorUsedCount).build()

        /**
         * Alias for [of].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        @JvmStatic
        fun of(distinctSessionConnectorUsedCount: Long) =
            of(distinctSessionConnectorUsedCount as Long?)

        /** Alias for calling [of] with `distinctSessionConnectorUsedCount.orElse(null)`. */
        @JvmStatic
        fun of(distinctSessionConnectorUsedCount: Optional<Long>) =
            of(distinctSessionConnectorUsedCount.getOrNull())
    }

    /** A builder for [BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics]. */
    class Builder internal constructor() {

        private var distinctSessionConnectorUsedCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics:
                BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics
        ) = apply {
            distinctSessionConnectorUsedCount =
                betaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics
                    .distinctSessionConnectorUsedCount
            additionalProperties =
                betaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics.additionalProperties
                    .toMutableMap()
        }

        /**
         * Same measure as `cowork_metrics.distinct_session_connector_used_count`, for activity
         * recorded while members had Chat and Cowork unified turned on. Approximate (HLL, typical
         * error <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
         * computed.
         */
        fun distinctSessionConnectorUsedCount(distinctSessionConnectorUsedCount: Long?) =
            distinctSessionConnectorUsedCount(
                JsonField.ofNullable(distinctSessionConnectorUsedCount)
            )

        /**
         * Alias for [Builder.distinctSessionConnectorUsedCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun distinctSessionConnectorUsedCount(distinctSessionConnectorUsedCount: Long) =
            distinctSessionConnectorUsedCount(distinctSessionConnectorUsedCount as Long?)

        /**
         * Alias for calling [Builder.distinctSessionConnectorUsedCount] with
         * `distinctSessionConnectorUsedCount.orElse(null)`.
         */
        fun distinctSessionConnectorUsedCount(distinctSessionConnectorUsedCount: Optional<Long>) =
            distinctSessionConnectorUsedCount(distinctSessionConnectorUsedCount.getOrNull())

        /**
         * Sets [Builder.distinctSessionConnectorUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctSessionConnectorUsedCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun distinctSessionConnectorUsedCount(distinctSessionConnectorUsedCount: JsonField<Long>) =
            apply {
                this.distinctSessionConnectorUsedCount = distinctSessionConnectorUsedCount
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
         * Returns an immutable instance of
         * [BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .distinctSessionConnectorUsedCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics =
            BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics(
                checkRequired(
                    "distinctSessionConnectorUsedCount",
                    distinctSessionConnectorUsedCount,
                ),
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
    fun validate(): BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics = apply {
        if (validated) {
            return@apply
        }

        distinctSessionConnectorUsedCount()
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
        (if (distinctSessionConnectorUsedCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics &&
            distinctSessionConnectorUsedCount == other.distinctSessionConnectorUsedCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(distinctSessionConnectorUsedCount, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics{distinctSessionConnectorUsedCount=$distinctSessionConnectorUsedCount, additionalProperties=$additionalProperties}"
}
