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

/** Office Agent activity metrics for a single user on a given day within one Office product. */
class BetaAnalyticsOfficeProductMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val connectorsUsedCount: JsonField<Long>,
    private val distinctConnectorsUsedCount: JsonField<Long>,
    private val distinctSessionCount: JsonField<Long>,
    private val distinctSkillsUsedCount: JsonField<Long>,
    private val messageCount: JsonField<Long>,
    private val skillsUsedCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("connectors_used_count")
        @ExcludeMissing
        connectorsUsedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("distinct_connectors_used_count")
        @ExcludeMissing
        distinctConnectorsUsedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("distinct_session_count")
        @ExcludeMissing
        distinctSessionCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("distinct_skills_used_count")
        @ExcludeMissing
        distinctSkillsUsedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("message_count")
        @ExcludeMissing
        messageCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("skills_used_count")
        @ExcludeMissing
        skillsUsedCount: JsonField<Long> = JsonMissing.of(),
    ) : this(
        connectorsUsedCount,
        distinctConnectorsUsedCount,
        distinctSessionCount,
        distinctSkillsUsedCount,
        messageCount,
        skillsUsedCount,
        mutableMapOf(),
    )

    /**
     * Number of MCP connector invocations
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun connectorsUsedCount(): Long = connectorsUsedCount.getRequired("connectors_used_count")

    /**
     * Number of distinct MCP connectors used. Approximate (HLL, typical error <2%) in date-range
     * mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctConnectorsUsedCount(): Optional<Long> =
        distinctConnectorsUsedCount.getOptional("distinct_connectors_used_count")

    /**
     * Number of distinct Office Agent sessions. Approximate (HLL, typical error <2%) in date-range
     * mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSessionCount(): Optional<Long> =
        distinctSessionCount.getOptional("distinct_session_count")

    /**
     * Number of distinct skills used. Approximate (HLL, typical error <2%) in date-range mode. Null
     * on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSkillsUsedCount(): Optional<Long> =
        distinctSkillsUsedCount.getOptional("distinct_skills_used_count")

    /**
     * Number of messages sent
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun messageCount(): Long = messageCount.getRequired("message_count")

    /**
     * Number of skill invocations
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun skillsUsedCount(): Long = skillsUsedCount.getRequired("skills_used_count")

    /**
     * Returns the raw JSON value of [connectorsUsedCount].
     *
     * Unlike [connectorsUsedCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("connectors_used_count")
    @ExcludeMissing
    fun _connectorsUsedCount(): JsonField<Long> = connectorsUsedCount

    /**
     * Returns the raw JSON value of [distinctConnectorsUsedCount].
     *
     * Unlike [distinctConnectorsUsedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_connectors_used_count")
    @ExcludeMissing
    fun _distinctConnectorsUsedCount(): JsonField<Long> = distinctConnectorsUsedCount

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
     * Returns the raw JSON value of [distinctSkillsUsedCount].
     *
     * Unlike [distinctSkillsUsedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_skills_used_count")
    @ExcludeMissing
    fun _distinctSkillsUsedCount(): JsonField<Long> = distinctSkillsUsedCount

    /**
     * Returns the raw JSON value of [messageCount].
     *
     * Unlike [messageCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("message_count")
    @ExcludeMissing
    fun _messageCount(): JsonField<Long> = messageCount

    /**
     * Returns the raw JSON value of [skillsUsedCount].
     *
     * Unlike [skillsUsedCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("skills_used_count")
    @ExcludeMissing
    fun _skillsUsedCount(): JsonField<Long> = skillsUsedCount

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
         * [BetaAnalyticsOfficeProductMetrics].
         *
         * The following fields are required:
         * ```java
         * .connectorsUsedCount()
         * .distinctConnectorsUsedCount()
         * .distinctSessionCount()
         * .distinctSkillsUsedCount()
         * .messageCount()
         * .skillsUsedCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsOfficeProductMetrics]. */
    class Builder internal constructor() {

        private var connectorsUsedCount: JsonField<Long>? = null
        private var distinctConnectorsUsedCount: JsonField<Long>? = null
        private var distinctSessionCount: JsonField<Long>? = null
        private var distinctSkillsUsedCount: JsonField<Long>? = null
        private var messageCount: JsonField<Long>? = null
        private var skillsUsedCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsOfficeProductMetrics: BetaAnalyticsOfficeProductMetrics) =
            apply {
                connectorsUsedCount = betaAnalyticsOfficeProductMetrics.connectorsUsedCount
                distinctConnectorsUsedCount =
                    betaAnalyticsOfficeProductMetrics.distinctConnectorsUsedCount
                distinctSessionCount = betaAnalyticsOfficeProductMetrics.distinctSessionCount
                distinctSkillsUsedCount = betaAnalyticsOfficeProductMetrics.distinctSkillsUsedCount
                messageCount = betaAnalyticsOfficeProductMetrics.messageCount
                skillsUsedCount = betaAnalyticsOfficeProductMetrics.skillsUsedCount
                additionalProperties =
                    betaAnalyticsOfficeProductMetrics.additionalProperties.toMutableMap()
            }

        /** Number of MCP connector invocations */
        fun connectorsUsedCount(connectorsUsedCount: Long) =
            connectorsUsedCount(JsonField.of(connectorsUsedCount))

        /**
         * Sets [Builder.connectorsUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.connectorsUsedCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun connectorsUsedCount(connectorsUsedCount: JsonField<Long>) = apply {
            this.connectorsUsedCount = connectorsUsedCount
        }

        /**
         * Number of distinct MCP connectors used. Approximate (HLL, typical error <2%) in
         * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
         */
        fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: Long?) =
            distinctConnectorsUsedCount(JsonField.ofNullable(distinctConnectorsUsedCount))

        /**
         * Alias for [Builder.distinctConnectorsUsedCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: Long) =
            distinctConnectorsUsedCount(distinctConnectorsUsedCount as Long?)

        /**
         * Alias for calling [Builder.distinctConnectorsUsedCount] with
         * `distinctConnectorsUsedCount.orElse(null)`.
         */
        fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: Optional<Long>) =
            distinctConnectorsUsedCount(distinctConnectorsUsedCount.getOrNull())

        /**
         * Sets [Builder.distinctConnectorsUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctConnectorsUsedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: JsonField<Long>) = apply {
            this.distinctConnectorsUsedCount = distinctConnectorsUsedCount
        }

        /**
         * Number of distinct Office Agent sessions. Approximate (HLL, typical error <2%) in
         * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
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

        /**
         * Number of distinct skills used. Approximate (HLL, typical error <2%) in date-range mode.
         * Null on aggregated rows where a distinct count cannot be computed.
         */
        fun distinctSkillsUsedCount(distinctSkillsUsedCount: Long?) =
            distinctSkillsUsedCount(JsonField.ofNullable(distinctSkillsUsedCount))

        /**
         * Alias for [Builder.distinctSkillsUsedCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun distinctSkillsUsedCount(distinctSkillsUsedCount: Long) =
            distinctSkillsUsedCount(distinctSkillsUsedCount as Long?)

        /**
         * Alias for calling [Builder.distinctSkillsUsedCount] with
         * `distinctSkillsUsedCount.orElse(null)`.
         */
        fun distinctSkillsUsedCount(distinctSkillsUsedCount: Optional<Long>) =
            distinctSkillsUsedCount(distinctSkillsUsedCount.getOrNull())

        /**
         * Sets [Builder.distinctSkillsUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctSkillsUsedCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun distinctSkillsUsedCount(distinctSkillsUsedCount: JsonField<Long>) = apply {
            this.distinctSkillsUsedCount = distinctSkillsUsedCount
        }

        /** Number of messages sent */
        fun messageCount(messageCount: Long) = messageCount(JsonField.of(messageCount))

        /**
         * Sets [Builder.messageCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.messageCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun messageCount(messageCount: JsonField<Long>) = apply { this.messageCount = messageCount }

        /** Number of skill invocations */
        fun skillsUsedCount(skillsUsedCount: Long) = skillsUsedCount(JsonField.of(skillsUsedCount))

        /**
         * Sets [Builder.skillsUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.skillsUsedCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun skillsUsedCount(skillsUsedCount: JsonField<Long>) = apply {
            this.skillsUsedCount = skillsUsedCount
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
         * Returns an immutable instance of [BetaAnalyticsOfficeProductMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .connectorsUsedCount()
         * .distinctConnectorsUsedCount()
         * .distinctSessionCount()
         * .distinctSkillsUsedCount()
         * .messageCount()
         * .skillsUsedCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsOfficeProductMetrics =
            BetaAnalyticsOfficeProductMetrics(
                checkRequired("connectorsUsedCount", connectorsUsedCount),
                checkRequired("distinctConnectorsUsedCount", distinctConnectorsUsedCount),
                checkRequired("distinctSessionCount", distinctSessionCount),
                checkRequired("distinctSkillsUsedCount", distinctSkillsUsedCount),
                checkRequired("messageCount", messageCount),
                checkRequired("skillsUsedCount", skillsUsedCount),
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
    fun validate(): BetaAnalyticsOfficeProductMetrics = apply {
        if (validated) {
            return@apply
        }

        connectorsUsedCount()
        distinctConnectorsUsedCount()
        distinctSessionCount()
        distinctSkillsUsedCount()
        messageCount()
        skillsUsedCount()
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
        (if (connectorsUsedCount.asKnown().isPresent) 1 else 0) +
            (if (distinctConnectorsUsedCount.asKnown().isPresent) 1 else 0) +
            (if (distinctSessionCount.asKnown().isPresent) 1 else 0) +
            (if (distinctSkillsUsedCount.asKnown().isPresent) 1 else 0) +
            (if (messageCount.asKnown().isPresent) 1 else 0) +
            (if (skillsUsedCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsOfficeProductMetrics &&
            connectorsUsedCount == other.connectorsUsedCount &&
            distinctConnectorsUsedCount == other.distinctConnectorsUsedCount &&
            distinctSessionCount == other.distinctSessionCount &&
            distinctSkillsUsedCount == other.distinctSkillsUsedCount &&
            messageCount == other.messageCount &&
            skillsUsedCount == other.skillsUsedCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            connectorsUsedCount,
            distinctConnectorsUsedCount,
            distinctSessionCount,
            distinctSkillsUsedCount,
            messageCount,
            skillsUsedCount,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsOfficeProductMetrics{connectorsUsedCount=$connectorsUsedCount, distinctConnectorsUsedCount=$distinctConnectorsUsedCount, distinctSessionCount=$distinctSessionCount, distinctSkillsUsedCount=$distinctSkillsUsedCount, messageCount=$messageCount, skillsUsedCount=$skillsUsedCount, additionalProperties=$additionalProperties}"
}
