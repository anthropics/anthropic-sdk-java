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

/** User-configurable total token budget across contexts. */
class BetaTokenTaskBudget
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val total: JsonField<Long>,
    private val type: JsonValue,
    private val remaining: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("total") @ExcludeMissing total: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("remaining") @ExcludeMissing remaining: JsonField<Long> = JsonMissing.of(),
    ) : this(total, type, remaining, mutableMapOf())

    /**
     * Total token budget across all contexts in the session.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun total(): Long = total.getRequired("total")

    /**
     * The budget type. Currently only 'tokens' is supported.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("tokens")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Remaining tokens in the budget. Use this to track usage across contexts when implementing
     * compaction client-side. Defaults to total if not provided.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun remaining(): Optional<Long> = remaining.getOptional("remaining")

    /**
     * Returns the raw JSON value of [total].
     *
     * Unlike [total], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("total") @ExcludeMissing fun _total(): JsonField<Long> = total

    /**
     * Returns the raw JSON value of [remaining].
     *
     * Unlike [remaining], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("remaining") @ExcludeMissing fun _remaining(): JsonField<Long> = remaining

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
         * Returns a mutable builder for constructing an instance of [BetaTokenTaskBudget].
         *
         * The following fields are required:
         * ```java
         * .total()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTokenTaskBudget]. */
    class Builder internal constructor() {

        private var total: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("tokens")
        private var remaining: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaTokenTaskBudget: BetaTokenTaskBudget) = apply {
            total = betaTokenTaskBudget.total
            type = betaTokenTaskBudget.type
            remaining = betaTokenTaskBudget.remaining
            additionalProperties = betaTokenTaskBudget.additionalProperties.toMutableMap()
        }

        /** Total token budget across all contexts in the session. */
        fun total(total: Long) = total(JsonField.of(total))

        /**
         * Sets [Builder.total] to an arbitrary JSON value.
         *
         * You should usually call [Builder.total] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun total(total: JsonField<Long>) = apply { this.total = total }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("tokens")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Remaining tokens in the budget. Use this to track usage across contexts when implementing
         * compaction client-side. Defaults to total if not provided.
         */
        fun remaining(remaining: Long?) = remaining(JsonField.ofNullable(remaining))

        /**
         * Alias for [Builder.remaining].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun remaining(remaining: Long) = remaining(remaining as Long?)

        /** Alias for calling [Builder.remaining] with `remaining.orElse(null)`. */
        fun remaining(remaining: Optional<Long>) = remaining(remaining.getOrNull())

        /**
         * Sets [Builder.remaining] to an arbitrary JSON value.
         *
         * You should usually call [Builder.remaining] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun remaining(remaining: JsonField<Long>) = apply { this.remaining = remaining }

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
         * Returns an immutable instance of [BetaTokenTaskBudget].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .total()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaTokenTaskBudget =
            BetaTokenTaskBudget(
                checkRequired("total", total),
                type,
                remaining,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaTokenTaskBudget = apply {
        if (validated) {
            return@apply
        }

        total()
        _type().let {
            if (it != JsonValue.from("tokens")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        remaining()
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
        (if (total.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("tokens")) 1 else 0 } +
            (if (remaining.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTokenTaskBudget &&
            total == other.total &&
            type == other.type &&
            remaining == other.remaining &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(total, type, remaining, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTokenTaskBudget{total=$total, type=$type, remaining=$remaining, additionalProperties=$additionalProperties}"
}
