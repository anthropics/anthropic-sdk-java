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

class BetaToolReferenceBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val toolName: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("tool_name") @ExcludeMissing toolName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(toolName, type, mutableMapOf())

    fun toParam(): BetaToolReferenceBlockParam =
        BetaToolReferenceBlockParam.builder().toolName(_toolName()).build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun toolName(): String = toolName.getRequired("tool_name")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("tool_reference")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [toolName].
     *
     * Unlike [toolName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool_name") @ExcludeMissing fun _toolName(): JsonField<String> = toolName

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
         * Returns a mutable builder for constructing an instance of [BetaToolReferenceBlock].
         *
         * The following fields are required:
         * ```java
         * .toolName()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaToolReferenceBlock]. */
    class Builder internal constructor() {

        private var toolName: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("tool_reference")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolReferenceBlock: BetaToolReferenceBlock) = apply {
            toolName = betaToolReferenceBlock.toolName
            type = betaToolReferenceBlock.type
            additionalProperties = betaToolReferenceBlock.additionalProperties.toMutableMap()
        }

        fun toolName(toolName: String) = toolName(JsonField.of(toolName))

        /**
         * Sets [Builder.toolName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolName] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun toolName(toolName: JsonField<String>) = apply { this.toolName = toolName }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("tool_reference")
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
         * Returns an immutable instance of [BetaToolReferenceBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .toolName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaToolReferenceBlock =
            BetaToolReferenceBlock(
                checkRequired("toolName", toolName),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaToolReferenceBlock = apply {
        if (validated) {
            return@apply
        }

        toolName()
        _type().let {
            if (it != JsonValue.from("tool_reference")) {
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
        (if (toolName.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("tool_reference")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaToolReferenceBlock &&
            toolName == other.toolName &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(toolName, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolReferenceBlock{toolName=$toolName, type=$type, additionalProperties=$additionalProperties}"
}
