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

class BetaServerToolCaller20260120
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val toolId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("tool_id") @ExcludeMissing toolId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(toolId, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun toolId(): String = toolId.getRequired("tool_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("code_execution_20260120")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [toolId].
     *
     * Unlike [toolId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool_id") @ExcludeMissing fun _toolId(): JsonField<String> = toolId

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
         * Returns a mutable builder for constructing an instance of [BetaServerToolCaller20260120].
         *
         * The following fields are required:
         * ```java
         * .toolId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaServerToolCaller20260120]. */
    class Builder internal constructor() {

        private var toolId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("code_execution_20260120")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaServerToolCaller20260120: BetaServerToolCaller20260120) = apply {
            toolId = betaServerToolCaller20260120.toolId
            type = betaServerToolCaller20260120.type
            additionalProperties = betaServerToolCaller20260120.additionalProperties.toMutableMap()
        }

        fun toolId(toolId: String) = toolId(JsonField.of(toolId))

        /**
         * Sets [Builder.toolId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun toolId(toolId: JsonField<String>) = apply { this.toolId = toolId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("code_execution_20260120")
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
         * Returns an immutable instance of [BetaServerToolCaller20260120].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .toolId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaServerToolCaller20260120 =
            BetaServerToolCaller20260120(
                checkRequired("toolId", toolId),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaServerToolCaller20260120 = apply {
        if (validated) {
            return@apply
        }

        toolId()
        _type().let {
            if (it != JsonValue.from("code_execution_20260120")) {
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
        (if (toolId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("code_execution_20260120")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaServerToolCaller20260120 &&
            toolId == other.toolId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(toolId, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaServerToolCaller20260120{toolId=$toolId, type=$type, additionalProperties=$additionalProperties}"
}
