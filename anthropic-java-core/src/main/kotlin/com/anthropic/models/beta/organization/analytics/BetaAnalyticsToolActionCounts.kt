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

/** Accepted/rejected counts for a single Claude Code tool type. */
class BetaAnalyticsToolActionCounts
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val acceptedCount: JsonField<Long>,
    private val rejectedCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("accepted_count")
        @ExcludeMissing
        acceptedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("rejected_count")
        @ExcludeMissing
        rejectedCount: JsonField<Long> = JsonMissing.of(),
    ) : this(acceptedCount, rejectedCount, mutableMapOf())

    /**
     * Number of tool proposals accepted
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun acceptedCount(): Long = acceptedCount.getRequired("accepted_count")

    /**
     * Number of tool proposals rejected
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun rejectedCount(): Long = rejectedCount.getRequired("rejected_count")

    /**
     * Returns the raw JSON value of [acceptedCount].
     *
     * Unlike [acceptedCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("accepted_count")
    @ExcludeMissing
    fun _acceptedCount(): JsonField<Long> = acceptedCount

    /**
     * Returns the raw JSON value of [rejectedCount].
     *
     * Unlike [rejectedCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rejected_count")
    @ExcludeMissing
    fun _rejectedCount(): JsonField<Long> = rejectedCount

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
         * [BetaAnalyticsToolActionCounts].
         *
         * The following fields are required:
         * ```java
         * .acceptedCount()
         * .rejectedCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsToolActionCounts]. */
    class Builder internal constructor() {

        private var acceptedCount: JsonField<Long>? = null
        private var rejectedCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsToolActionCounts: BetaAnalyticsToolActionCounts) = apply {
            acceptedCount = betaAnalyticsToolActionCounts.acceptedCount
            rejectedCount = betaAnalyticsToolActionCounts.rejectedCount
            additionalProperties = betaAnalyticsToolActionCounts.additionalProperties.toMutableMap()
        }

        /** Number of tool proposals accepted */
        fun acceptedCount(acceptedCount: Long) = acceptedCount(JsonField.of(acceptedCount))

        /**
         * Sets [Builder.acceptedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.acceptedCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun acceptedCount(acceptedCount: JsonField<Long>) = apply {
            this.acceptedCount = acceptedCount
        }

        /** Number of tool proposals rejected */
        fun rejectedCount(rejectedCount: Long) = rejectedCount(JsonField.of(rejectedCount))

        /**
         * Sets [Builder.rejectedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rejectedCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun rejectedCount(rejectedCount: JsonField<Long>) = apply {
            this.rejectedCount = rejectedCount
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
         * Returns an immutable instance of [BetaAnalyticsToolActionCounts].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .acceptedCount()
         * .rejectedCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsToolActionCounts =
            BetaAnalyticsToolActionCounts(
                checkRequired("acceptedCount", acceptedCount),
                checkRequired("rejectedCount", rejectedCount),
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
    fun validate(): BetaAnalyticsToolActionCounts = apply {
        if (validated) {
            return@apply
        }

        acceptedCount()
        rejectedCount()
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
        (if (acceptedCount.asKnown().isPresent) 1 else 0) +
            (if (rejectedCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsToolActionCounts &&
            acceptedCount == other.acceptedCount &&
            rejectedCount == other.rejectedCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(acceptedCount, rejectedCount, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsToolActionCounts{acceptedCount=$acceptedCount, rejectedCount=$rejectedCount, additionalProperties=$additionalProperties}"
}
