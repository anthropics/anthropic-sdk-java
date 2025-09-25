// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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

/** The model will automatically decide whether to use tools. */
class ToolChoiceAuto
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val disableParallelToolUse: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("disable_parallel_tool_use")
        @ExcludeMissing
        disableParallelToolUse: JsonField<Boolean> = JsonMissing.of(),
    ) : this(type, disableParallelToolUse, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("auto")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Whether to disable parallel tool use.
     *
     * Defaults to `false`. If set to `true`, the model will output at most one tool use.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun disableParallelToolUse(): Optional<Boolean> =
        disableParallelToolUse.getOptional("disable_parallel_tool_use")

    /**
     * Returns the raw JSON value of [disableParallelToolUse].
     *
     * Unlike [disableParallelToolUse], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("disable_parallel_tool_use")
    @ExcludeMissing
    fun _disableParallelToolUse(): JsonField<Boolean> = disableParallelToolUse

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

        /** Returns a mutable builder for constructing an instance of [ToolChoiceAuto]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ToolChoiceAuto]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("auto")
        private var disableParallelToolUse: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(toolChoiceAuto: ToolChoiceAuto) = apply {
            type = toolChoiceAuto.type
            disableParallelToolUse = toolChoiceAuto.disableParallelToolUse
            additionalProperties = toolChoiceAuto.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("auto")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Whether to disable parallel tool use.
         *
         * Defaults to `false`. If set to `true`, the model will output at most one tool use.
         */
        fun disableParallelToolUse(disableParallelToolUse: Boolean) =
            disableParallelToolUse(JsonField.of(disableParallelToolUse))

        /**
         * Sets [Builder.disableParallelToolUse] to an arbitrary JSON value.
         *
         * You should usually call [Builder.disableParallelToolUse] with a well-typed [Boolean]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
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

        /**
         * Returns an immutable instance of [ToolChoiceAuto].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ToolChoiceAuto =
            ToolChoiceAuto(type, disableParallelToolUse, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): ToolChoiceAuto = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("auto")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        disableParallelToolUse()
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
        type.let { if (it == JsonValue.from("auto")) 1 else 0 } +
            (if (disableParallelToolUse.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ToolChoiceAuto &&
            type == other.type &&
            disableParallelToolUse == other.disableParallelToolUse &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, disableParallelToolUse, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ToolChoiceAuto{type=$type, disableParallelToolUse=$disableParallelToolUse, additionalProperties=$additionalProperties}"
}
