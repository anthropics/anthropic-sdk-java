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
import kotlin.jvm.optionals.getOrNull

/**
 * Marks the point in `content` where one model's output gives way to the next.
 *
 * One block appears per hop where a preceding model actually ran this turn and declined. A turn
 * routed directly by the sticky decision has no such boundary and carries no block — the signal for
 * whether a fallback model served the response is the presence of a `fallback_message` entry in
 * `usage.iterations`, not this block.
 *
 * The block is treated like a server-tool content block for streaming: it arrives via the standard
 * `content_block_start` / `content_block_stop` pair and carries no deltas.
 */
class BetaFallbackBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val from: JsonField<BetaFallbackInfo>,
    private val to: JsonField<BetaFallbackInfo>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("from") @ExcludeMissing from: JsonField<BetaFallbackInfo> = JsonMissing.of(),
        @JsonProperty("to") @ExcludeMissing to: JsonField<BetaFallbackInfo> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(from, to, type, mutableMapOf())

    fun toParam(): BetaFallbackBlockParam =
        BetaFallbackBlockParam.builder()
            .from(_from().map { it.toParam() })
            .to(_to().map { it.toParam() })
            .build()

    /**
     * The model whose output ends at this point — the model that declined at this hop. When the
     * declining hop is the requested model, its `model` echoes the top-level `model` string the
     * caller sent (alias or canonical); when the declining hop is a fallback model, its `model` is
     * that model's canonical id.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun from(): BetaFallbackInfo = from.getRequired("from")

    /**
     * The fallback model producing the content that follows this block. Its `model` is always the
     * canonical id.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun to(): BetaFallbackInfo = to.getRequired("to")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("fallback")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [from].
     *
     * Unlike [from], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("from") @ExcludeMissing fun _from(): JsonField<BetaFallbackInfo> = from

    /**
     * Returns the raw JSON value of [to].
     *
     * Unlike [to], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("to") @ExcludeMissing fun _to(): JsonField<BetaFallbackInfo> = to

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
         * Returns a mutable builder for constructing an instance of [BetaFallbackBlock].
         *
         * The following fields are required:
         * ```java
         * .from()
         * .to()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaFallbackBlock]. */
    class Builder internal constructor() {

        private var from: JsonField<BetaFallbackInfo>? = null
        private var to: JsonField<BetaFallbackInfo>? = null
        private var type: JsonValue = JsonValue.from("fallback")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaFallbackBlock: BetaFallbackBlock) = apply {
            from = betaFallbackBlock.from
            to = betaFallbackBlock.to
            type = betaFallbackBlock.type
            additionalProperties = betaFallbackBlock.additionalProperties.toMutableMap()
        }

        /**
         * The model whose output ends at this point — the model that declined at this hop. When the
         * declining hop is the requested model, its `model` echoes the top-level `model` string the
         * caller sent (alias or canonical); when the declining hop is a fallback model, its `model`
         * is that model's canonical id.
         */
        fun from(from: BetaFallbackInfo) = from(JsonField.of(from))

        /**
         * Sets [Builder.from] to an arbitrary JSON value.
         *
         * You should usually call [Builder.from] with a well-typed [BetaFallbackInfo] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun from(from: JsonField<BetaFallbackInfo>) = apply { this.from = from }

        /**
         * The fallback model producing the content that follows this block. Its `model` is always
         * the canonical id.
         */
        fun to(to: BetaFallbackInfo) = to(JsonField.of(to))

        /**
         * Sets [Builder.to] to an arbitrary JSON value.
         *
         * You should usually call [Builder.to] with a well-typed [BetaFallbackInfo] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun to(to: JsonField<BetaFallbackInfo>) = apply { this.to = to }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("fallback")
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
         * Returns an immutable instance of [BetaFallbackBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .from()
         * .to()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaFallbackBlock =
            BetaFallbackBlock(
                checkRequired("from", from),
                checkRequired("to", to),
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
    fun validate(): BetaFallbackBlock = apply {
        if (validated) {
            return@apply
        }

        from().validate()
        to().validate()
        _type().let {
            if (it != JsonValue.from("fallback")) {
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
        (from.asKnown().getOrNull()?.validity() ?: 0) +
            (to.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("fallback")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaFallbackBlock &&
            from == other.from &&
            to == other.to &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(from, to, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaFallbackBlock{from=$from, to=$to, type=$type, additionalProperties=$additionalProperties}"
}
