// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

@NoAutoDetect
class BetaInputJsonDelta
@JsonCreator
private constructor(
    @JsonProperty("partial_json")
    @ExcludeMissing
    private val partialJson: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

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

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaInputJsonDelta = apply {
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

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaInputJsonDelta].
         *
         * The following fields are required:
         * ```java
         * .partialJson()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaInputJsonDelta]. */
    class Builder internal constructor() {

        private var partialJson: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("input_json_delta")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaInputJsonDelta: BetaInputJsonDelta) = apply {
            partialJson = betaInputJsonDelta.partialJson
            type = betaInputJsonDelta.type
            additionalProperties = betaInputJsonDelta.additionalProperties.toMutableMap()
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

        fun build(): BetaInputJsonDelta =
            BetaInputJsonDelta(
                checkRequired("partialJson", partialJson),
                type,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaInputJsonDelta && partialJson == other.partialJson && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(partialJson, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaInputJsonDelta{partialJson=$partialJson, type=$type, additionalProperties=$additionalProperties}"
}
