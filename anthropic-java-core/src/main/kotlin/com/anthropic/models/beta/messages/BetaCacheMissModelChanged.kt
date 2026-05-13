// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaCacheMissModelChanged
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cacheMissedInputTokens: JsonField<Long>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cache_missed_input_tokens")
        @ExcludeMissing
        cacheMissedInputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(cacheMissedInputTokens, type, mutableMapOf())

    /**
     * Approximate number of input tokens that would have been read from cache had the prefix
     * matched the previous request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun cacheMissedInputTokens(): Long =
        cacheMissedInputTokens.getRequired("cache_missed_input_tokens")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("model_changed")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [cacheMissedInputTokens].
     *
     * Unlike [cacheMissedInputTokens], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("cache_missed_input_tokens")
    @ExcludeMissing
    fun _cacheMissedInputTokens(): JsonField<Long> = cacheMissedInputTokens

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
         * Returns a mutable builder for constructing an instance of [BetaCacheMissModelChanged].
         *
         * The following fields are required:
         * ```java
         * .cacheMissedInputTokens()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCacheMissModelChanged]. */
    class Builder internal constructor() {

        private var cacheMissedInputTokens: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("model_changed")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCacheMissModelChanged: BetaCacheMissModelChanged) = apply {
            cacheMissedInputTokens = betaCacheMissModelChanged.cacheMissedInputTokens
            type = betaCacheMissModelChanged.type
            additionalProperties = betaCacheMissModelChanged.additionalProperties.toMutableMap()
        }

        /**
         * Approximate number of input tokens that would have been read from cache had the prefix
         * matched the previous request.
         */
        fun cacheMissedInputTokens(cacheMissedInputTokens: Long) =
            cacheMissedInputTokens(JsonField.of(cacheMissedInputTokens))

        /**
         * Sets [Builder.cacheMissedInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheMissedInputTokens] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun cacheMissedInputTokens(cacheMissedInputTokens: JsonField<Long>) = apply {
            this.cacheMissedInputTokens = cacheMissedInputTokens
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("model_changed")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaCacheMissModelChanged].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .cacheMissedInputTokens()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaCacheMissModelChanged =
            BetaCacheMissModelChanged(
                checkRequired("cacheMissedInputTokens", cacheMissedInputTokens),
                type,
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
    fun validate(): BetaCacheMissModelChanged = apply {
        if (validated) {
            return@apply
        }

        cacheMissedInputTokens()
        _type().let {
            if (it != JsonValue.from("model_changed")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (if (cacheMissedInputTokens.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("model_changed")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaCacheMissModelChanged &&
            cacheMissedInputTokens == other.cacheMissedInputTokens &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(cacheMissedInputTokens, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCacheMissModelChanged{cacheMissedInputTokens=$cacheMissedInputTokens, type=$type, additionalProperties=$additionalProperties}"
}
