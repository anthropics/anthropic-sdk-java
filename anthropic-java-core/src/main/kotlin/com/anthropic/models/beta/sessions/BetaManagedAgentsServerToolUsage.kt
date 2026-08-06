// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional

/** Cumulative count of server-executed tool invocations, broken down by tool. */
class BetaManagedAgentsServerToolUsage
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val webFetchRequests: JsonField<Int>,
    private val webSearchRequests: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("web_fetch_requests")
        @ExcludeMissing
        webFetchRequests: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("web_search_requests")
        @ExcludeMissing
        webSearchRequests: JsonField<Int> = JsonMissing.of(),
    ) : this(webFetchRequests, webSearchRequests, mutableMapOf())

    /**
     * Number of server-executed web fetch requests.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun webFetchRequests(): Optional<Int> = webFetchRequests.getOptional("web_fetch_requests")

    /**
     * Number of server-executed web search requests.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun webSearchRequests(): Optional<Int> = webSearchRequests.getOptional("web_search_requests")

    /**
     * Returns the raw JSON value of [webFetchRequests].
     *
     * Unlike [webFetchRequests], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("web_fetch_requests")
    @ExcludeMissing
    fun _webFetchRequests(): JsonField<Int> = webFetchRequests

    /**
     * Returns the raw JSON value of [webSearchRequests].
     *
     * Unlike [webSearchRequests], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("web_search_requests")
    @ExcludeMissing
    fun _webSearchRequests(): JsonField<Int> = webSearchRequests

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
         * [BetaManagedAgentsServerToolUsage].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsServerToolUsage]. */
    class Builder internal constructor() {

        private var webFetchRequests: JsonField<Int> = JsonMissing.of()
        private var webSearchRequests: JsonField<Int> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsServerToolUsage: BetaManagedAgentsServerToolUsage) =
            apply {
                webFetchRequests = betaManagedAgentsServerToolUsage.webFetchRequests
                webSearchRequests = betaManagedAgentsServerToolUsage.webSearchRequests
                additionalProperties =
                    betaManagedAgentsServerToolUsage.additionalProperties.toMutableMap()
            }

        /** Number of server-executed web fetch requests. */
        fun webFetchRequests(webFetchRequests: Int) =
            webFetchRequests(JsonField.of(webFetchRequests))

        /**
         * Sets [Builder.webFetchRequests] to an arbitrary JSON value.
         *
         * You should usually call [Builder.webFetchRequests] with a well-typed [Int] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun webFetchRequests(webFetchRequests: JsonField<Int>) = apply {
            this.webFetchRequests = webFetchRequests
        }

        /** Number of server-executed web search requests. */
        fun webSearchRequests(webSearchRequests: Int) =
            webSearchRequests(JsonField.of(webSearchRequests))

        /**
         * Sets [Builder.webSearchRequests] to an arbitrary JSON value.
         *
         * You should usually call [Builder.webSearchRequests] with a well-typed [Int] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun webSearchRequests(webSearchRequests: JsonField<Int>) = apply {
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
         * Returns an immutable instance of [BetaManagedAgentsServerToolUsage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsServerToolUsage =
            BetaManagedAgentsServerToolUsage(
                webFetchRequests,
                webSearchRequests,
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
    fun validate(): BetaManagedAgentsServerToolUsage = apply {
        if (validated) {
            return@apply
        }

        webFetchRequests()
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
    internal fun validity(): Int =
        (if (webFetchRequests.asKnown().isPresent) 1 else 0) +
            (if (webSearchRequests.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsServerToolUsage &&
            webFetchRequests == other.webFetchRequests &&
            webSearchRequests == other.webSearchRequests &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(webFetchRequests, webSearchRequests, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsServerToolUsage{webFetchRequests=$webFetchRequests, webSearchRequests=$webSearchRequests, additionalProperties=$additionalProperties}"
}
