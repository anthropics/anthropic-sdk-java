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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaMessageTokensCount
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val contextManagement: JsonField<BetaCountTokensContextManagementResponse>,
    private val inputTokens: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("context_management")
        @ExcludeMissing
        contextManagement: JsonField<BetaCountTokensContextManagementResponse> = JsonMissing.of(),
        @JsonProperty("input_tokens")
        @ExcludeMissing
        inputTokens: JsonField<Long> = JsonMissing.of(),
    ) : this(contextManagement, inputTokens, mutableMapOf())

    /**
     * Information about context management applied to the message.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun contextManagement(): Optional<BetaCountTokensContextManagementResponse> =
        contextManagement.getOptional("context_management")

    /**
     * The total number of tokens across the provided list of messages, system prompt, and tools.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun inputTokens(): Long = inputTokens.getRequired("input_tokens")

    /**
     * Returns the raw JSON value of [contextManagement].
     *
     * Unlike [contextManagement], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("context_management")
    @ExcludeMissing
    fun _contextManagement(): JsonField<BetaCountTokensContextManagementResponse> =
        contextManagement

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
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaMessageTokensCount].
         *
         * The following fields are required:
         * ```java
         * .contextManagement()
         * .inputTokens()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMessageTokensCount]. */
    class Builder internal constructor() {

        private var contextManagement: JsonField<BetaCountTokensContextManagementResponse>? = null
        private var inputTokens: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageTokensCount: BetaMessageTokensCount) = apply {
            contextManagement = betaMessageTokensCount.contextManagement
            inputTokens = betaMessageTokensCount.inputTokens
            additionalProperties = betaMessageTokensCount.additionalProperties.toMutableMap()
        }

        /** Information about context management applied to the message. */
        fun contextManagement(contextManagement: BetaCountTokensContextManagementResponse?) =
            contextManagement(JsonField.ofNullable(contextManagement))

        /** Alias for calling [Builder.contextManagement] with `contextManagement.orElse(null)`. */
        fun contextManagement(
            contextManagement: Optional<BetaCountTokensContextManagementResponse>
        ) = contextManagement(contextManagement.getOrNull())

        /**
         * Sets [Builder.contextManagement] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contextManagement] with a well-typed
         * [BetaCountTokensContextManagementResponse] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun contextManagement(
            contextManagement: JsonField<BetaCountTokensContextManagementResponse>
        ) = apply { this.contextManagement = contextManagement }

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
         * Returns an immutable instance of [BetaMessageTokensCount].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .contextManagement()
         * .inputTokens()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaMessageTokensCount =
            BetaMessageTokensCount(
                checkRequired("contextManagement", contextManagement),
                checkRequired("inputTokens", inputTokens),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaMessageTokensCount = apply {
        if (validated) {
            return@apply
        }

        contextManagement().ifPresent { it.validate() }
        inputTokens()
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
        (contextManagement.asKnown().getOrNull()?.validity() ?: 0) +
            (if (inputTokens.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMessageTokensCount &&
            contextManagement == other.contextManagement &&
            inputTokens == other.inputTokens &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(contextManagement, inputTokens, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageTokensCount{contextManagement=$contextManagement, inputTokens=$inputTokens, additionalProperties=$additionalProperties}"
}
