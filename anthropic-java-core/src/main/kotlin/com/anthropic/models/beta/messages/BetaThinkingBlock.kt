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

class BetaThinkingBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val signature: JsonField<String>,
    private val thinking: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("signature") @ExcludeMissing signature: JsonField<String> = JsonMissing.of(),
        @JsonProperty("thinking") @ExcludeMissing thinking: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(signature, thinking, type, mutableMapOf())

    fun toParam(): BetaThinkingBlockParam =
        BetaThinkingBlockParam.builder().signature(_signature()).thinking(_thinking()).build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun signature(): String = signature.getRequired("signature")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun thinking(): String = thinking.getRequired("thinking")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("thinking")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [signature].
     *
     * Unlike [signature], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("signature") @ExcludeMissing fun _signature(): JsonField<String> = signature

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
         * Returns a mutable builder for constructing an instance of [BetaThinkingBlock].
         *
         * The following fields are required:
         * ```java
         * .signature()
         * .thinking()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaThinkingBlock]. */
    class Builder internal constructor() {

        private var signature: JsonField<String>? = null
        private var thinking: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("thinking")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaThinkingBlock: BetaThinkingBlock) = apply {
            signature = betaThinkingBlock.signature
            thinking = betaThinkingBlock.thinking
            type = betaThinkingBlock.type
            additionalProperties = betaThinkingBlock.additionalProperties.toMutableMap()
        }

        fun signature(signature: String) = signature(JsonField.of(signature))

        /**
         * Sets [Builder.signature] to an arbitrary JSON value.
         *
         * You should usually call [Builder.signature] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun signature(signature: JsonField<String>) = apply { this.signature = signature }

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
         * JsonValue.from("thinking")
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
         * Returns an immutable instance of [BetaThinkingBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .signature()
         * .thinking()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaThinkingBlock =
            BetaThinkingBlock(
                checkRequired("signature", signature),
                checkRequired("thinking", thinking),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaThinkingBlock = apply {
        if (validated) {
            return@apply
        }

        signature()
        thinking()
        _type().let {
            if (it != JsonValue.from("thinking")) {
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
        (if (signature.asKnown().isPresent) 1 else 0) +
            (if (thinking.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("thinking")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaThinkingBlock &&
            signature == other.signature &&
            thinking == other.thinking &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(signature, thinking, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaThinkingBlock{signature=$signature, thinking=$thinking, type=$type, additionalProperties=$additionalProperties}"
}
