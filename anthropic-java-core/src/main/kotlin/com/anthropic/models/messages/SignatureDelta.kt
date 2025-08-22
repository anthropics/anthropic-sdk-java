// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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

class SignatureDelta
private constructor(
    private val signature: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("signature") @ExcludeMissing signature: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(signature, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun signature(): String = signature.getRequired("signature")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("signature_delta")
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
         * Returns a mutable builder for constructing an instance of [SignatureDelta].
         *
         * The following fields are required:
         * ```java
         * .signature()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SignatureDelta]. */
    class Builder internal constructor() {

        private var signature: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("signature_delta")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(signatureDelta: SignatureDelta) = apply {
            signature = signatureDelta.signature
            type = signatureDelta.type
            additionalProperties = signatureDelta.additionalProperties.toMutableMap()
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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("signature_delta")
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
         * Returns an immutable instance of [SignatureDelta].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .signature()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SignatureDelta =
            SignatureDelta(
                checkRequired("signature", signature),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): SignatureDelta = apply {
        if (validated) {
            return@apply
        }

        signature()
        _type().let {
            if (it != JsonValue.from("signature_delta")) {
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
            type.let { if (it == JsonValue.from("signature_delta")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SignatureDelta &&
            signature == other.signature &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(signature, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SignatureDelta{signature=$signature, type=$type, additionalProperties=$additionalProperties}"
}
