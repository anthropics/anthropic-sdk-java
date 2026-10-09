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
 * A connector's use in chat conversations recorded while members had Chat and Cowork unified turned
 * on.
 */
class BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val distinctConversationConnectorUsedCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("distinct_conversation_connector_used_count")
        @ExcludeMissing
        distinctConversationConnectorUsedCount: JsonField<Long> = JsonMissing.of()
    ) : this(distinctConversationConnectorUsedCount, mutableMapOf())

    /**
     * Same measure as `chat_metrics.distinct_conversation_connector_used_count`, for activity
     * recorded while members had Chat and Cowork unified turned on. Approximate (HLL, typical error
     * <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctConversationConnectorUsedCount(): Optional<Long> =
        distinctConversationConnectorUsedCount.getOptional(
            "distinct_conversation_connector_used_count"
        )

    /**
     * Returns the raw JSON value of [distinctConversationConnectorUsedCount].
     *
     * Unlike [distinctConversationConnectorUsedCount], this method doesn't throw if the JSON field
     * has an unexpected type.
     */
    @JsonProperty("distinct_conversation_connector_used_count")
    @ExcludeMissing
    fun _distinctConversationConnectorUsedCount(): JsonField<Long> =
        distinctConversationConnectorUsedCount

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
         * [BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics].
         *
         * The following fields are required:
         * ```java
         * .distinctConversationConnectorUsedCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics]
         * with the required [distinctConversationConnectorUsedCount] set to the given value.
         */
        @JvmStatic
        fun of(distinctConversationConnectorUsedCount: Long?) =
            builder()
                .distinctConversationConnectorUsedCount(distinctConversationConnectorUsedCount)
                .build()

        /**
         * Alias for [of].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        @JvmStatic
        fun of(distinctConversationConnectorUsedCount: Long) =
            of(distinctConversationConnectorUsedCount as Long?)

        /** Alias for calling [of] with `distinctConversationConnectorUsedCount.orElse(null)`. */
        @JvmStatic
        fun of(distinctConversationConnectorUsedCount: Optional<Long>) =
            of(distinctConversationConnectorUsedCount.getOrNull())
    }

    /** A builder for [BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics]. */
    class Builder internal constructor() {

        private var distinctConversationConnectorUsedCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaAnalyticsConnectorChatCoworkUnifiedChatMetrics:
                BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics
        ) = apply {
            distinctConversationConnectorUsedCount =
                betaAnalyticsConnectorChatCoworkUnifiedChatMetrics
                    .distinctConversationConnectorUsedCount
            additionalProperties =
                betaAnalyticsConnectorChatCoworkUnifiedChatMetrics.additionalProperties
                    .toMutableMap()
        }

        /**
         * Same measure as `chat_metrics.distinct_conversation_connector_used_count`, for activity
         * recorded while members had Chat and Cowork unified turned on. Approximate (HLL, typical
         * error <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
         * computed.
         */
        fun distinctConversationConnectorUsedCount(distinctConversationConnectorUsedCount: Long?) =
            distinctConversationConnectorUsedCount(
                JsonField.ofNullable(distinctConversationConnectorUsedCount)
            )

        /**
         * Alias for [Builder.distinctConversationConnectorUsedCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun distinctConversationConnectorUsedCount(distinctConversationConnectorUsedCount: Long) =
            distinctConversationConnectorUsedCount(distinctConversationConnectorUsedCount as Long?)

        /**
         * Alias for calling [Builder.distinctConversationConnectorUsedCount] with
         * `distinctConversationConnectorUsedCount.orElse(null)`.
         */
        fun distinctConversationConnectorUsedCount(
            distinctConversationConnectorUsedCount: Optional<Long>
        ) =
            distinctConversationConnectorUsedCount(
                distinctConversationConnectorUsedCount.getOrNull()
            )

        /**
         * Sets [Builder.distinctConversationConnectorUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctConversationConnectorUsedCount] with a
         * well-typed [Long] value instead. This method is primarily for setting the field to an
         * undocumented or not yet supported value.
         */
        fun distinctConversationConnectorUsedCount(
            distinctConversationConnectorUsedCount: JsonField<Long>
        ) = apply {
            this.distinctConversationConnectorUsedCount = distinctConversationConnectorUsedCount
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
         * Returns an immutable instance of [BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .distinctConversationConnectorUsedCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics =
            BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics(
                checkRequired(
                    "distinctConversationConnectorUsedCount",
                    distinctConversationConnectorUsedCount,
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
    fun validate(): BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics = apply {
        if (validated) {
            return@apply
        }

        distinctConversationConnectorUsedCount()
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
        (if (distinctConversationConnectorUsedCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics &&
            distinctConversationConnectorUsedCount ==
                other.distinctConversationConnectorUsedCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(distinctConversationConnectorUsedCount, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics{distinctConversationConnectorUsedCount=$distinctConversationConnectorUsedCount, additionalProperties=$additionalProperties}"
}
