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
 * A skill's use in chat conversations recorded while members had Chat and Cowork unified turned on.
 */
class BetaAnalyticsSkillChatCoworkUnifiedChatMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val distinctConversationSkillUsedCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("distinct_conversation_skill_used_count")
        @ExcludeMissing
        distinctConversationSkillUsedCount: JsonField<Long> = JsonMissing.of()
    ) : this(distinctConversationSkillUsedCount, mutableMapOf())

    /**
     * Same measure as `chat_metrics.distinct_conversation_skill_used_count`, for activity recorded
     * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%) in
     * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctConversationSkillUsedCount(): Optional<Long> =
        distinctConversationSkillUsedCount.getOptional("distinct_conversation_skill_used_count")

    /**
     * Returns the raw JSON value of [distinctConversationSkillUsedCount].
     *
     * Unlike [distinctConversationSkillUsedCount], this method doesn't throw if the JSON field has
     * an unexpected type.
     */
    @JsonProperty("distinct_conversation_skill_used_count")
    @ExcludeMissing
    fun _distinctConversationSkillUsedCount(): JsonField<Long> = distinctConversationSkillUsedCount

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
         * [BetaAnalyticsSkillChatCoworkUnifiedChatMetrics].
         *
         * The following fields are required:
         * ```java
         * .distinctConversationSkillUsedCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaAnalyticsSkillChatCoworkUnifiedChatMetrics] with
         * the required [distinctConversationSkillUsedCount] set to the given value.
         */
        @JvmStatic
        fun of(distinctConversationSkillUsedCount: Long?) =
            builder().distinctConversationSkillUsedCount(distinctConversationSkillUsedCount).build()

        /**
         * Alias for [of].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        @JvmStatic
        fun of(distinctConversationSkillUsedCount: Long) =
            of(distinctConversationSkillUsedCount as Long?)

        /** Alias for calling [of] with `distinctConversationSkillUsedCount.orElse(null)`. */
        @JvmStatic
        fun of(distinctConversationSkillUsedCount: Optional<Long>) =
            of(distinctConversationSkillUsedCount.getOrNull())
    }

    /** A builder for [BetaAnalyticsSkillChatCoworkUnifiedChatMetrics]. */
    class Builder internal constructor() {

        private var distinctConversationSkillUsedCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaAnalyticsSkillChatCoworkUnifiedChatMetrics:
                BetaAnalyticsSkillChatCoworkUnifiedChatMetrics
        ) = apply {
            distinctConversationSkillUsedCount =
                betaAnalyticsSkillChatCoworkUnifiedChatMetrics.distinctConversationSkillUsedCount
            additionalProperties =
                betaAnalyticsSkillChatCoworkUnifiedChatMetrics.additionalProperties.toMutableMap()
        }

        /**
         * Same measure as `chat_metrics.distinct_conversation_skill_used_count`, for activity
         * recorded while members had Chat and Cowork unified turned on. Approximate (HLL, typical
         * error <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
         * computed.
         */
        fun distinctConversationSkillUsedCount(distinctConversationSkillUsedCount: Long?) =
            distinctConversationSkillUsedCount(
                JsonField.ofNullable(distinctConversationSkillUsedCount)
            )

        /**
         * Alias for [Builder.distinctConversationSkillUsedCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun distinctConversationSkillUsedCount(distinctConversationSkillUsedCount: Long) =
            distinctConversationSkillUsedCount(distinctConversationSkillUsedCount as Long?)

        /**
         * Alias for calling [Builder.distinctConversationSkillUsedCount] with
         * `distinctConversationSkillUsedCount.orElse(null)`.
         */
        fun distinctConversationSkillUsedCount(distinctConversationSkillUsedCount: Optional<Long>) =
            distinctConversationSkillUsedCount(distinctConversationSkillUsedCount.getOrNull())

        /**
         * Sets [Builder.distinctConversationSkillUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctConversationSkillUsedCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun distinctConversationSkillUsedCount(
            distinctConversationSkillUsedCount: JsonField<Long>
        ) = apply { this.distinctConversationSkillUsedCount = distinctConversationSkillUsedCount }

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
         * Returns an immutable instance of [BetaAnalyticsSkillChatCoworkUnifiedChatMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .distinctConversationSkillUsedCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsSkillChatCoworkUnifiedChatMetrics =
            BetaAnalyticsSkillChatCoworkUnifiedChatMetrics(
                checkRequired(
                    "distinctConversationSkillUsedCount",
                    distinctConversationSkillUsedCount,
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
    fun validate(): BetaAnalyticsSkillChatCoworkUnifiedChatMetrics = apply {
        if (validated) {
            return@apply
        }

        distinctConversationSkillUsedCount()
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
        (if (distinctConversationSkillUsedCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsSkillChatCoworkUnifiedChatMetrics &&
            distinctConversationSkillUsedCount == other.distinctConversationSkillUsedCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(distinctConversationSkillUsedCount, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsSkillChatCoworkUnifiedChatMetrics{distinctConversationSkillUsedCount=$distinctConversationSkillUsedCount, additionalProperties=$additionalProperties}"
}
