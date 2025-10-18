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

class BetaClearToolUses20250919EditResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val clearedInputTokens: JsonField<Long>,
    private val clearedToolUses: JsonField<Long>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cleared_input_tokens")
        @ExcludeMissing
        clearedInputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("cleared_tool_uses")
        @ExcludeMissing
        clearedToolUses: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(clearedInputTokens, clearedToolUses, type, mutableMapOf())

    /**
     * Number of input tokens cleared by this edit.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun clearedInputTokens(): Long = clearedInputTokens.getRequired("cleared_input_tokens")

    /**
     * Number of tool uses that were cleared.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun clearedToolUses(): Long = clearedToolUses.getRequired("cleared_tool_uses")

    /**
     * The type of context management edit applied.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("clear_tool_uses_20250919")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [clearedInputTokens].
     *
     * Unlike [clearedInputTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("cleared_input_tokens")
    @ExcludeMissing
    fun _clearedInputTokens(): JsonField<Long> = clearedInputTokens

    /**
     * Returns the raw JSON value of [clearedToolUses].
     *
     * Unlike [clearedToolUses], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cleared_tool_uses")
    @ExcludeMissing
    fun _clearedToolUses(): JsonField<Long> = clearedToolUses

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
         * [BetaClearToolUses20250919EditResponse].
         *
         * The following fields are required:
         * ```java
         * .clearedInputTokens()
         * .clearedToolUses()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaClearToolUses20250919EditResponse]. */
    class Builder internal constructor() {

        private var clearedInputTokens: JsonField<Long>? = null
        private var clearedToolUses: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("clear_tool_uses_20250919")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaClearToolUses20250919EditResponse: BetaClearToolUses20250919EditResponse
        ) = apply {
            clearedInputTokens = betaClearToolUses20250919EditResponse.clearedInputTokens
            clearedToolUses = betaClearToolUses20250919EditResponse.clearedToolUses
            type = betaClearToolUses20250919EditResponse.type
            additionalProperties =
                betaClearToolUses20250919EditResponse.additionalProperties.toMutableMap()
        }

        /** Number of input tokens cleared by this edit. */
        fun clearedInputTokens(clearedInputTokens: Long) =
            clearedInputTokens(JsonField.of(clearedInputTokens))

        /**
         * Sets [Builder.clearedInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clearedInputTokens] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun clearedInputTokens(clearedInputTokens: JsonField<Long>) = apply {
            this.clearedInputTokens = clearedInputTokens
        }

        /** Number of tool uses that were cleared. */
        fun clearedToolUses(clearedToolUses: Long) = clearedToolUses(JsonField.of(clearedToolUses))

        /**
         * Sets [Builder.clearedToolUses] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clearedToolUses] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun clearedToolUses(clearedToolUses: JsonField<Long>) = apply {
            this.clearedToolUses = clearedToolUses
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("clear_tool_uses_20250919")
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
         * Returns an immutable instance of [BetaClearToolUses20250919EditResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .clearedInputTokens()
         * .clearedToolUses()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaClearToolUses20250919EditResponse =
            BetaClearToolUses20250919EditResponse(
                checkRequired("clearedInputTokens", clearedInputTokens),
                checkRequired("clearedToolUses", clearedToolUses),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaClearToolUses20250919EditResponse = apply {
        if (validated) {
            return@apply
        }

        clearedInputTokens()
        clearedToolUses()
        _type().let {
            if (it != JsonValue.from("clear_tool_uses_20250919")) {
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
        (if (clearedInputTokens.asKnown().isPresent) 1 else 0) +
            (if (clearedToolUses.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("clear_tool_uses_20250919")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClearToolUses20250919EditResponse &&
            clearedInputTokens == other.clearedInputTokens &&
            clearedToolUses == other.clearedToolUses &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(clearedInputTokens, clearedToolUses, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaClearToolUses20250919EditResponse{clearedInputTokens=$clearedInputTokens, clearedToolUses=$clearedToolUses, type=$type, additionalProperties=$additionalProperties}"
}
