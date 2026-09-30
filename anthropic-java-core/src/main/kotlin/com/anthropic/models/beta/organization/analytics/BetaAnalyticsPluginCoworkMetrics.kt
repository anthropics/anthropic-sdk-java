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

/** Cowork activity metrics for a single plugin on a given day. */
class BetaAnalyticsPluginCoworkMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val distinctSessionPluginUsedCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("distinct_session_plugin_used_count")
        @ExcludeMissing
        distinctSessionPluginUsedCount: JsonField<Long> = JsonMissing.of()
    ) : this(distinctSessionPluginUsedCount, mutableMapOf())

    /**
     * Number of distinct Cowork sessions in which the plugin was invoked. Null on aggregated rows
     * where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSessionPluginUsedCount(): Optional<Long> =
        distinctSessionPluginUsedCount.getOptional("distinct_session_plugin_used_count")

    /**
     * Returns the raw JSON value of [distinctSessionPluginUsedCount].
     *
     * Unlike [distinctSessionPluginUsedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_session_plugin_used_count")
    @ExcludeMissing
    fun _distinctSessionPluginUsedCount(): JsonField<Long> = distinctSessionPluginUsedCount

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
         * [BetaAnalyticsPluginCoworkMetrics].
         *
         * The following fields are required:
         * ```java
         * .distinctSessionPluginUsedCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaAnalyticsPluginCoworkMetrics] with the required
         * [distinctSessionPluginUsedCount] set to the given value.
         */
        @JvmStatic
        fun of(distinctSessionPluginUsedCount: Long?) =
            builder().distinctSessionPluginUsedCount(distinctSessionPluginUsedCount).build()

        /**
         * Alias for [of].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        @JvmStatic
        fun of(distinctSessionPluginUsedCount: Long) = of(distinctSessionPluginUsedCount as Long?)

        /** Alias for calling [of] with `distinctSessionPluginUsedCount.orElse(null)`. */
        @JvmStatic
        fun of(distinctSessionPluginUsedCount: Optional<Long>) =
            of(distinctSessionPluginUsedCount.getOrNull())
    }

    /** A builder for [BetaAnalyticsPluginCoworkMetrics]. */
    class Builder internal constructor() {

        private var distinctSessionPluginUsedCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsPluginCoworkMetrics: BetaAnalyticsPluginCoworkMetrics) =
            apply {
                distinctSessionPluginUsedCount =
                    betaAnalyticsPluginCoworkMetrics.distinctSessionPluginUsedCount
                additionalProperties =
                    betaAnalyticsPluginCoworkMetrics.additionalProperties.toMutableMap()
            }

        /**
         * Number of distinct Cowork sessions in which the plugin was invoked. Null on aggregated
         * rows where a distinct count cannot be computed.
         */
        fun distinctSessionPluginUsedCount(distinctSessionPluginUsedCount: Long?) =
            distinctSessionPluginUsedCount(JsonField.ofNullable(distinctSessionPluginUsedCount))

        /**
         * Alias for [Builder.distinctSessionPluginUsedCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun distinctSessionPluginUsedCount(distinctSessionPluginUsedCount: Long) =
            distinctSessionPluginUsedCount(distinctSessionPluginUsedCount as Long?)

        /**
         * Alias for calling [Builder.distinctSessionPluginUsedCount] with
         * `distinctSessionPluginUsedCount.orElse(null)`.
         */
        fun distinctSessionPluginUsedCount(distinctSessionPluginUsedCount: Optional<Long>) =
            distinctSessionPluginUsedCount(distinctSessionPluginUsedCount.getOrNull())

        /**
         * Sets [Builder.distinctSessionPluginUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctSessionPluginUsedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctSessionPluginUsedCount(distinctSessionPluginUsedCount: JsonField<Long>) =
            apply {
                this.distinctSessionPluginUsedCount = distinctSessionPluginUsedCount
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
         * Returns an immutable instance of [BetaAnalyticsPluginCoworkMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .distinctSessionPluginUsedCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsPluginCoworkMetrics =
            BetaAnalyticsPluginCoworkMetrics(
                checkRequired("distinctSessionPluginUsedCount", distinctSessionPluginUsedCount),
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
    fun validate(): BetaAnalyticsPluginCoworkMetrics = apply {
        if (validated) {
            return@apply
        }

        distinctSessionPluginUsedCount()
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
        (if (distinctSessionPluginUsedCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsPluginCoworkMetrics &&
            distinctSessionPluginUsedCount == other.distinctSessionPluginUsedCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(distinctSessionPluginUsedCount, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsPluginCoworkMetrics{distinctSessionPluginUsedCount=$distinctSessionPluginUsedCount, additionalProperties=$additionalProperties}"
}
