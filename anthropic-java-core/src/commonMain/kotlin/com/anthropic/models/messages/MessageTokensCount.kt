// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.ExcludeMissing
import kotlinx.kmp.util.core.JsonField
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.errors.ApiInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.kmp.util.core.contentHash

class MessageTokensCount
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val inputTokens: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("input_tokens")
        @ExcludeMissing
        inputTokens: JsonField<Long> = JsonMissing.of()
    ) : this(inputTokens, mutableMapOf())

    /**
     * The total number of tokens across the provided list of messages, system prompt, and tools.
     *
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun inputTokens(): Long = inputTokens.getRequired("input_tokens")

    /**
     * Returns the raw JSON value of [inputTokens].
     *
     * Unlike [inputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input_tokens") @ExcludeMissing fun _inputTokens(): JsonField<Long> = inputTokens

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        additionalProperties.toMap()

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [MessageTokensCount].
         *
         * The following fields are required:
         * ```java
         * .inputTokens()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MessageTokensCount]. */
    class Builder internal constructor() {

        private var inputTokens: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(messageTokensCount: MessageTokensCount) = apply {
            inputTokens = messageTokensCount.inputTokens
            additionalProperties = messageTokensCount.additionalProperties.toMutableMap()
        }

        /**
         * The total number of tokens across the provided list of messages, system prompt, and
         * tools.
         */
        fun inputTokens(inputTokens: Long) = inputTokens(JsonField.of(inputTokens))

        /**
         * Sets [Builder.inputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputTokens] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun inputTokens(inputTokens: JsonField<Long>) = apply { this.inputTokens = inputTokens }

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
         * Returns an immutable instance of [MessageTokensCount].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .inputTokens()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): MessageTokensCount =
            MessageTokensCount(
                checkRequired("inputTokens", inputTokens),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): MessageTokensCount = apply {
        if (validated) {
            return@apply
        }

        inputTokens()
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: ApiInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic internal fun validity(): Int = (if (inputTokens.asKnown() != null) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MessageTokensCount &&
            inputTokens == other.inputTokens &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { contentHash(inputTokens, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "MessageTokensCount{inputTokens=$inputTokens, additionalProperties=$additionalProperties}"
}
