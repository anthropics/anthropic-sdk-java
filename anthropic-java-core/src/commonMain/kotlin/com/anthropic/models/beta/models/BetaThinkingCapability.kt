// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

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

/** Thinking capability details. */
class BetaThinkingCapability
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val supported: JsonField<Boolean>,
    private val types: JsonField<BetaThinkingTypes>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("supported") @ExcludeMissing supported: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("types")
        @ExcludeMissing
        types: JsonField<BetaThinkingTypes> = JsonMissing.of(),
    ) : this(supported, types, mutableMapOf())

    /**
     * Whether this capability is supported by the model.
     *
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun supported(): Boolean = supported.getRequired("supported")

    /**
     * Supported thinking type configurations.
     *
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun types(): BetaThinkingTypes = types.getRequired("types")

    /**
     * Returns the raw JSON value of [supported].
     *
     * Unlike [supported], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("supported") @ExcludeMissing fun _supported(): JsonField<Boolean> = supported

    /**
     * Returns the raw JSON value of [types].
     *
     * Unlike [types], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("types") @ExcludeMissing fun _types(): JsonField<BetaThinkingTypes> = types

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
         * Returns a mutable builder for constructing an instance of [BetaThinkingCapability].
         *
         * The following fields are required:
         * ```java
         * .supported()
         * .types()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaThinkingCapability]. */
    class Builder internal constructor() {

        private var supported: JsonField<Boolean>? = null
        private var types: JsonField<BetaThinkingTypes>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(betaThinkingCapability: BetaThinkingCapability) = apply {
            supported = betaThinkingCapability.supported
            types = betaThinkingCapability.types
            additionalProperties = betaThinkingCapability.additionalProperties.toMutableMap()
        }

        /** Whether this capability is supported by the model. */
        fun supported(supported: Boolean) = supported(JsonField.of(supported))

        /**
         * Sets [Builder.supported] to an arbitrary JSON value.
         *
         * You should usually call [Builder.supported] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun supported(supported: JsonField<Boolean>) = apply { this.supported = supported }

        /** Supported thinking type configurations. */
        fun types(types: BetaThinkingTypes) = types(JsonField.of(types))

        /**
         * Sets [Builder.types] to an arbitrary JSON value.
         *
         * You should usually call [Builder.types] with a well-typed [BetaThinkingTypes] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun types(types: JsonField<BetaThinkingTypes>) = apply { this.types = types }

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
         * Returns an immutable instance of [BetaThinkingCapability].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .supported()
         * .types()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaThinkingCapability =
            BetaThinkingCapability(
                checkRequired("supported", supported),
                checkRequired("types", types),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaThinkingCapability = apply {
        if (validated) {
            return@apply
        }

        supported()
        types().validate()
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
    internal fun validity(): Int =
        (if (supported.asKnown() != null) 1 else 0) +
            (types.asKnown()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaThinkingCapability &&
            supported == other.supported &&
            types == other.types &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { contentHash(supported, types, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaThinkingCapability{supported=$supported, types=$types, additionalProperties=$additionalProperties}"
}
