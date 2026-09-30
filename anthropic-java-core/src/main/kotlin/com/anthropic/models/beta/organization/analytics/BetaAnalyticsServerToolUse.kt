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

class BetaAnalyticsServerToolUse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val webSearchRequests: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("web_search_requests")
        @ExcludeMissing
        webSearchRequests: JsonField<Long> = JsonMissing.of()
    ) : this(webSearchRequests, mutableMapOf())

    /**
     * The number of web search requests made.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun webSearchRequests(): Long = webSearchRequests.getRequired("web_search_requests")

    /**
     * Returns the raw JSON value of [webSearchRequests].
     *
     * Unlike [webSearchRequests], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("web_search_requests")
    @ExcludeMissing
    fun _webSearchRequests(): JsonField<Long> = webSearchRequests

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsServerToolUse].
         *
         * The following fields are required:
         * ```java
         * .webSearchRequests()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaAnalyticsServerToolUse] with the required
         * [webSearchRequests] set to the given value.
         */
        @JvmStatic
        fun of(webSearchRequests: Long) = builder().webSearchRequests(webSearchRequests).build()
    }

    /** A builder for [BetaAnalyticsServerToolUse]. */
    class Builder internal constructor() {

        private var webSearchRequests: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsServerToolUse: BetaAnalyticsServerToolUse) = apply {
            webSearchRequests = betaAnalyticsServerToolUse.webSearchRequests
            additionalProperties = betaAnalyticsServerToolUse.additionalProperties.toMutableMap()
        }

        /** The number of web search requests made. */
        fun webSearchRequests(webSearchRequests: Long) =
            webSearchRequests(JsonField.of(webSearchRequests))

        /**
         * Sets [Builder.webSearchRequests] to an arbitrary JSON value.
         *
         * You should usually call [Builder.webSearchRequests] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun webSearchRequests(webSearchRequests: JsonField<Long>) = apply {
            this.webSearchRequests = webSearchRequests
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
         * Returns an immutable instance of [BetaAnalyticsServerToolUse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .webSearchRequests()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsServerToolUse =
            BetaAnalyticsServerToolUse(
                checkRequired("webSearchRequests", webSearchRequests),
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
    fun validate(): BetaAnalyticsServerToolUse = apply {
        if (validated) {
            return@apply
        }

        webSearchRequests()
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
    internal fun validity(): Int = (if (webSearchRequests.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsServerToolUse &&
            webSearchRequests == other.webSearchRequests &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(webSearchRequests, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsServerToolUse{webSearchRequests=$webSearchRequests, additionalProperties=$additionalProperties}"
}
