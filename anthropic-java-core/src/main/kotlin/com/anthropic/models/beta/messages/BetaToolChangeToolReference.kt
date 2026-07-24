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

/**
 * Reference to a single tool the caller declared directly in ``tools[]``. Does not accept the
 * composed ``{server}_{name}`` form the server assigns to MCP-resolved tools — use
 * ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
 */
class BetaToolChangeToolReference
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val name: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(name, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

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
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

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
         * Returns a mutable builder for constructing an instance of [BetaToolChangeToolReference].
         *
         * The following fields are required:
         * ```java
         * .name()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaToolChangeToolReference]. */
    class Builder internal constructor() {

        private var name: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("tool_reference")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolChangeToolReference: BetaToolChangeToolReference) = apply {
            name = betaToolChangeToolReference.name
            type = betaToolChangeToolReference.type
            additionalProperties = betaToolChangeToolReference.additionalProperties.toMutableMap()
        }

        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

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
         * Returns an immutable instance of [BetaToolChangeToolReference].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .name()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaToolChangeToolReference =
            BetaToolChangeToolReference(
                checkRequired("name", name),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaToolChangeToolReference = apply {
        if (validated) {
            return@apply
        }

        name()
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
        (if (name.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("tool_reference")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaToolChangeToolReference &&
            name == other.name &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(name, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolChangeToolReference{name=$name, type=$type, additionalProperties=$additionalProperties}"
}
