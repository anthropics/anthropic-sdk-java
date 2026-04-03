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

/** Indicates whether a capability is supported. */
class CapabilitySupport
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val supported: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("supported") @ExcludeMissing supported: JsonField<Boolean> = JsonMissing.of()
    ) : this(supported, mutableMapOf())

    /**
     * Whether this capability is supported by the model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun supported(): Boolean = supported.getRequired("supported")

    /**
     * Returns the raw JSON value of [supported].
     *
     * Unlike [supported], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("supported") @ExcludeMissing fun _supported(): JsonField<Boolean> = supported

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
         * Returns a mutable builder for constructing an instance of [CapabilitySupport].
         *
         * The following fields are required:
         * ```java
         * .supported()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [CapabilitySupport]. */
    class Builder internal constructor() {

        private var supported: JsonField<Boolean>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(capabilitySupport: CapabilitySupport) = apply {
            supported = capabilitySupport.supported
            additionalProperties = capabilitySupport.additionalProperties.toMutableMap()
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
         * Returns an immutable instance of [CapabilitySupport].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .supported()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): CapabilitySupport =
            CapabilitySupport(
                checkRequired("supported", supported),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): CapabilitySupport = apply {
        if (validated) {
            return@apply
        }

        supported()
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
    @JvmSynthetic internal fun validity(): Int = (if (supported.asKnown() != null) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CapabilitySupport &&
            supported == other.supported &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { contentHash(supported, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CapabilitySupport{supported=$supported, additionalProperties=$additionalProperties}"
}
