// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects
import java.util.Optional

/** The model will use any available tools. */
@NoAutoDetect
class BetaToolChoiceAny
@JsonCreator
private constructor(
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonProperty("disable_parallel_tool_use")
    @ExcludeMissing
    private val disableParallelToolUse: JsonField<Boolean> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Whether to disable parallel tool use.
     *
     * Defaults to `false`. If set to `true`, the model will output exactly one tool use.
     */
    fun disableParallelToolUse(): Optional<Boolean> =
        Optional.ofNullable(disableParallelToolUse.getNullable("disable_parallel_tool_use"))

    /**
     * Whether to disable parallel tool use.
     *
     * Defaults to `false`. If set to `true`, the model will output exactly one tool use.
     */
    @JsonProperty("disable_parallel_tool_use")
    @ExcludeMissing
    fun _disableParallelToolUse(): JsonField<Boolean> = disableParallelToolUse

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaToolChoiceAny = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("any")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        disableParallelToolUse()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaToolChoiceAny]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("any")
        private var disableParallelToolUse: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolChoiceAny: BetaToolChoiceAny) = apply {
            type = betaToolChoiceAny.type
            disableParallelToolUse = betaToolChoiceAny.disableParallelToolUse
            additionalProperties = betaToolChoiceAny.additionalProperties.toMutableMap()
        }

        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Whether to disable parallel tool use.
         *
         * Defaults to `false`. If set to `true`, the model will output exactly one tool use.
         */
        fun disableParallelToolUse(disableParallelToolUse: Boolean) =
            disableParallelToolUse(JsonField.of(disableParallelToolUse))

        /**
         * Whether to disable parallel tool use.
         *
         * Defaults to `false`. If set to `true`, the model will output exactly one tool use.
         */
        fun disableParallelToolUse(disableParallelToolUse: JsonField<Boolean>) = apply {
            this.disableParallelToolUse = disableParallelToolUse
        }

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

        fun build(): BetaToolChoiceAny =
            BetaToolChoiceAny(type, disableParallelToolUse, additionalProperties.toImmutable())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaToolChoiceAny && type == other.type && disableParallelToolUse == other.disableParallelToolUse && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, disableParallelToolUse, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolChoiceAny{type=$type, disableParallelToolUse=$disableParallelToolUse, additionalProperties=$additionalProperties}"
}
