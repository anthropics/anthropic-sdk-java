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

class InputJsonDelta
private constructor(
    private val partialJson: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("partial_json")
        @ExcludeMissing
        partialJson: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(partialJson, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun partialJson(): String = partialJson.getRequired("partial_json")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("input_json_delta")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [partialJson].
     *
     * Unlike [partialJson], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("partial_json")
    @ExcludeMissing
    fun _partialJson(): JsonField<String> = partialJson

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
         * Returns a mutable builder for constructing an instance of [InputJsonDelta].
         *
         * The following fields are required:
         * ```java
         * .partialJson()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [InputJsonDelta]. */
    class Builder internal constructor() {

        private var partialJson: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("input_json_delta")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(inputJsonDelta: InputJsonDelta) = apply {
            partialJson = inputJsonDelta.partialJson
            type = inputJsonDelta.type
            additionalProperties = inputJsonDelta.additionalProperties.toMutableMap()
        }

        fun partialJson(partialJson: String) = partialJson(JsonField.of(partialJson))

        /**
         * Sets [Builder.partialJson] to an arbitrary JSON value.
         *
         * You should usually call [Builder.partialJson] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun partialJson(partialJson: JsonField<String>) = apply { this.partialJson = partialJson }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("input_json_delta")
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
         * Returns an immutable instance of [InputJsonDelta].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .partialJson()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): InputJsonDelta =
            InputJsonDelta(
                checkRequired("partialJson", partialJson),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): InputJsonDelta = apply {
        if (validated) {
            return@apply
        }

        partialJson()
        _type().let {
            if (it != JsonValue.from("input_json_delta")) {
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
        (if (partialJson.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("input_json_delta")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is InputJsonDelta && partialJson == other.partialJson && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(partialJson, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "InputJsonDelta{partialJson=$partialJson, type=$type, additionalProperties=$additionalProperties}"
}
