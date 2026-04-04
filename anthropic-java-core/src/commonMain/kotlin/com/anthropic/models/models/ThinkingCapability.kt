// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

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
import com.anthropic.core.contentHash

/** Thinking capability details. */
class ThinkingCapability
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val supported: JsonField<Boolean>,
    private val types: JsonField<ThinkingTypes>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("supported") @ExcludeMissing supported: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("types") @ExcludeMissing types: JsonField<ThinkingTypes> = JsonMissing.of(),
    ) : this(supported, types, mutableMapOf())

    /**
     * Whether this capability is supported by the model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun supported(): Boolean = supported.getRequired("supported")

    /**
     * Supported thinking type configurations.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun types(): ThinkingTypes = types.getRequired("types")

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
    @JsonProperty("types") @ExcludeMissing fun _types(): JsonField<ThinkingTypes> = types

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
         * Returns a mutable builder for constructing an instance of [ThinkingCapability].
         *
         * The following fields are required:
         * ```java
         * .supported()
         * .types()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ThinkingCapability]. */
    class Builder internal constructor() {

        private var supported: JsonField<Boolean>? = null
        private var types: JsonField<ThinkingTypes>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(thinkingCapability: ThinkingCapability) = apply {
            supported = thinkingCapability.supported
            types = thinkingCapability.types
            additionalProperties = thinkingCapability.additionalProperties.toMutableMap()
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
        fun types(types: ThinkingTypes) = types(JsonField.of(types))

        /**
         * Sets [Builder.types] to an arbitrary JSON value.
         *
         * You should usually call [Builder.types] with a well-typed [ThinkingTypes] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun types(types: JsonField<ThinkingTypes>) = apply { this.types = types }

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
         * Returns an immutable instance of [ThinkingCapability].
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
        fun build(): ThinkingCapability =
            ThinkingCapability(
                checkRequired("supported", supported),
                checkRequired("types", types),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ThinkingCapability = apply {
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
        } catch (e: AnthropicInvalidDataException) {
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

        return other is ThinkingCapability &&
            supported == other.supported &&
            types == other.types &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { contentHash(supported, types, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ThinkingCapability{supported=$supported, types=$types, additionalProperties=$additionalProperties}"
}
