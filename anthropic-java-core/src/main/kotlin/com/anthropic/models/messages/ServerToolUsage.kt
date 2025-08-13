// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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

class ServerToolUsage
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
     * The number of web search tool requests.
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
         * Returns a mutable builder for constructing an instance of [ServerToolUsage].
         *
         * The following fields are required:
         * ```java
         * .webSearchRequests()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ServerToolUsage]. */
    class Builder internal constructor() {

        private var webSearchRequests: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(serverToolUsage: ServerToolUsage) = apply {
            webSearchRequests = serverToolUsage.webSearchRequests
            additionalProperties = serverToolUsage.additionalProperties.toMutableMap()
        }

        /** The number of web search tool requests. */
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
         * Returns an immutable instance of [ServerToolUsage].
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
        fun build(): ServerToolUsage =
            ServerToolUsage(
                checkRequired("webSearchRequests", webSearchRequests),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ServerToolUsage = apply {
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

        return other is ServerToolUsage &&
            webSearchRequests == other.webSearchRequests &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(webSearchRequests, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ServerToolUsage{webSearchRequests=$webSearchRequests, additionalProperties=$additionalProperties}"
}
