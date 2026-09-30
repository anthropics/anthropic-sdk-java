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

/** Lines of code added and removed via Claude Code. */
class BetaAnalyticsLinesOfCode
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val addedCount: JsonField<Long>,
    private val removedCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("added_count") @ExcludeMissing addedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("removed_count")
        @ExcludeMissing
        removedCount: JsonField<Long> = JsonMissing.of(),
    ) : this(addedCount, removedCount, mutableMapOf())

    /**
     * Lines of code added
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun addedCount(): Long = addedCount.getRequired("added_count")

    /**
     * Lines of code removed
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun removedCount(): Long = removedCount.getRequired("removed_count")

    /**
     * Returns the raw JSON value of [addedCount].
     *
     * Unlike [addedCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("added_count") @ExcludeMissing fun _addedCount(): JsonField<Long> = addedCount

    /**
     * Returns the raw JSON value of [removedCount].
     *
     * Unlike [removedCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("removed_count")
    @ExcludeMissing
    fun _removedCount(): JsonField<Long> = removedCount

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsLinesOfCode].
         *
         * The following fields are required:
         * ```java
         * .addedCount()
         * .removedCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsLinesOfCode]. */
    class Builder internal constructor() {

        private var addedCount: JsonField<Long>? = null
        private var removedCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsLinesOfCode: BetaAnalyticsLinesOfCode) = apply {
            addedCount = betaAnalyticsLinesOfCode.addedCount
            removedCount = betaAnalyticsLinesOfCode.removedCount
            additionalProperties = betaAnalyticsLinesOfCode.additionalProperties.toMutableMap()
        }

        /** Lines of code added */
        fun addedCount(addedCount: Long) = addedCount(JsonField.of(addedCount))

        /**
         * Sets [Builder.addedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.addedCount] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun addedCount(addedCount: JsonField<Long>) = apply { this.addedCount = addedCount }

        /** Lines of code removed */
        fun removedCount(removedCount: Long) = removedCount(JsonField.of(removedCount))

        /**
         * Sets [Builder.removedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.removedCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun removedCount(removedCount: JsonField<Long>) = apply { this.removedCount = removedCount }

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
         * Returns an immutable instance of [BetaAnalyticsLinesOfCode].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .addedCount()
         * .removedCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsLinesOfCode =
            BetaAnalyticsLinesOfCode(
                checkRequired("addedCount", addedCount),
                checkRequired("removedCount", removedCount),
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
    fun validate(): BetaAnalyticsLinesOfCode = apply {
        if (validated) {
            return@apply
        }

        addedCount()
        removedCount()
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
        (if (addedCount.asKnown().isPresent) 1 else 0) +
            (if (removedCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsLinesOfCode &&
            addedCount == other.addedCount &&
            removedCount == other.removedCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(addedCount, removedCount, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsLinesOfCode{addedCount=$addedCount, removedCount=$removedCount, additionalProperties=$additionalProperties}"
}
