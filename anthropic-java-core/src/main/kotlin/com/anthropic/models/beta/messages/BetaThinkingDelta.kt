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

class BetaThinkingDelta
private constructor(
    private val thinking: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("thinking") @ExcludeMissing thinking: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(thinking, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun thinking(): String = thinking.getRequired("thinking")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("thinking_delta")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [thinking].
     *
     * Unlike [thinking], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("thinking") @ExcludeMissing fun _thinking(): JsonField<String> = thinking

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
         * Returns a mutable builder for constructing an instance of [BetaThinkingDelta].
         *
         * The following fields are required:
         * ```java
         * .thinking()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaThinkingDelta]. */
    class Builder internal constructor() {

        private var thinking: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("thinking_delta")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaThinkingDelta: BetaThinkingDelta) = apply {
            thinking = betaThinkingDelta.thinking
            type = betaThinkingDelta.type
            additionalProperties = betaThinkingDelta.additionalProperties.toMutableMap()
        }

        fun thinking(thinking: String) = thinking(JsonField.of(thinking))

        /**
         * Sets [Builder.thinking] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinking] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun thinking(thinking: JsonField<String>) = apply { this.thinking = thinking }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("thinking_delta")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAlladditional_properties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAlladditional_properties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAlladditional_properties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaThinkingDelta].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .thinking()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaThinkingDelta =
            BetaThinkingDelta(
                checkRequired("thinking", thinking),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaThinkingDelta = apply {
        if (validated) {
            return@apply
        }

        thinking()
        _type().let {
            if (it != JsonValue.from("thinking_delta")) {
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
        (if (thinking.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("thinking_delta")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaThinkingDelta &&
            thinking == other.thinking &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(thinking, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaThinkingDelta{thinking=$thinking, type=$type, additionalProperties=$additionalProperties}"
}
