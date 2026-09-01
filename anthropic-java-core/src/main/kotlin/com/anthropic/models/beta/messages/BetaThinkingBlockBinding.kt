// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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
import kotlin.jvm.optionals.getOrNull

/**
 * Controls for block binding: what happens when a thinking block this request sends back fails the
 * conversation check. Every field is optional; an empty object means every default.
 */
class BetaThinkingBlockBinding
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val prefixMismatchBehavior: JsonField<BetaThinkingPrefixMismatchBehavior>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("prefix_mismatch_behavior")
        @ExcludeMissing
        prefixMismatchBehavior: JsonField<BetaThinkingPrefixMismatchBehavior> = JsonMissing.of()
    ) : this(prefixMismatchBehavior, mutableMapOf())

    /**
     * What happens when a thinking block in `messages` fails the conversation check: it was created
     * in a different conversation, or the messages before it have changed since. `"error"` (the
     * default) fails the request with a 400 error. `"drop_block"` removes the failing blocks and
     * the request proceeds; the model no longer sees the dropped reasoning.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun prefixMismatchBehavior(): Optional<BetaThinkingPrefixMismatchBehavior> =
        prefixMismatchBehavior.getOptional("prefix_mismatch_behavior")

    /**
     * Returns the raw JSON value of [prefixMismatchBehavior].
     *
     * Unlike [prefixMismatchBehavior], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("prefix_mismatch_behavior")
    @ExcludeMissing
    fun _prefixMismatchBehavior(): JsonField<BetaThinkingPrefixMismatchBehavior> =
        prefixMismatchBehavior

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

        /** Returns a mutable builder for constructing an instance of [BetaThinkingBlockBinding]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaThinkingBlockBinding]. */
    class Builder internal constructor() {

        private var prefixMismatchBehavior: JsonField<BetaThinkingPrefixMismatchBehavior> =
            JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaThinkingBlockBinding: BetaThinkingBlockBinding) = apply {
            prefixMismatchBehavior = betaThinkingBlockBinding.prefixMismatchBehavior
            additionalProperties = betaThinkingBlockBinding.additionalProperties.toMutableMap()
        }

        /**
         * What happens when a thinking block in `messages` fails the conversation check: it was
         * created in a different conversation, or the messages before it have changed since.
         * `"error"` (the default) fails the request with a 400 error. `"drop_block"` removes the
         * failing blocks and the request proceeds; the model no longer sees the dropped reasoning.
         */
        fun prefixMismatchBehavior(prefixMismatchBehavior: BetaThinkingPrefixMismatchBehavior?) =
            prefixMismatchBehavior(JsonField.ofNullable(prefixMismatchBehavior))

        /**
         * Alias for calling [Builder.prefixMismatchBehavior] with
         * `prefixMismatchBehavior.orElse(null)`.
         */
        fun prefixMismatchBehavior(
            prefixMismatchBehavior: Optional<BetaThinkingPrefixMismatchBehavior>
        ) = prefixMismatchBehavior(prefixMismatchBehavior.getOrNull())

        /**
         * Sets [Builder.prefixMismatchBehavior] to an arbitrary JSON value.
         *
         * You should usually call [Builder.prefixMismatchBehavior] with a well-typed
         * [BetaThinkingPrefixMismatchBehavior] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun prefixMismatchBehavior(
            prefixMismatchBehavior: JsonField<BetaThinkingPrefixMismatchBehavior>
        ) = apply { this.prefixMismatchBehavior = prefixMismatchBehavior }

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
         * Returns an immutable instance of [BetaThinkingBlockBinding].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaThinkingBlockBinding =
            BetaThinkingBlockBinding(prefixMismatchBehavior, additionalProperties.toMutableMap())
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
    fun validate(): BetaThinkingBlockBinding = apply {
        if (validated) {
            return@apply
        }

        prefixMismatchBehavior().ifPresent { it.validate() }
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
    internal fun validity(): Int = (prefixMismatchBehavior.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaThinkingBlockBinding &&
            prefixMismatchBehavior == other.prefixMismatchBehavior &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(prefixMismatchBehavior, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaThinkingBlockBinding{prefixMismatchBehavior=$prefixMismatchBehavior, additionalProperties=$additionalProperties}"
}
