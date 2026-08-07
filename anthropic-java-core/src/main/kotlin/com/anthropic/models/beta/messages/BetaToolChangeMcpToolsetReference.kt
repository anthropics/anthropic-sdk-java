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

/** Reference to every tool in the named MCP server's toolset. */
class BetaToolChangeMcpToolsetReference
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val serverName: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("server_name")
        @ExcludeMissing
        serverName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(serverName, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun serverName(): String = serverName.getRequired("server_name")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("mcp_toolset_reference")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [serverName].
     *
     * Unlike [serverName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("server_name") @ExcludeMissing fun _serverName(): JsonField<String> = serverName

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
         * Returns a mutable builder for constructing an instance of
         * [BetaToolChangeMcpToolsetReference].
         *
         * The following fields are required:
         * ```java
         * .serverName()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaToolChangeMcpToolsetReference] with the required
         * [serverName] set to the given value.
         */
        @JvmStatic fun of(serverName: String) = builder().serverName(serverName).build()
    }

    /** A builder for [BetaToolChangeMcpToolsetReference]. */
    class Builder internal constructor() {

        private var serverName: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("mcp_toolset_reference")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolChangeMcpToolsetReference: BetaToolChangeMcpToolsetReference) =
            apply {
                serverName = betaToolChangeMcpToolsetReference.serverName
                type = betaToolChangeMcpToolsetReference.type
                additionalProperties =
                    betaToolChangeMcpToolsetReference.additionalProperties.toMutableMap()
            }

        fun serverName(serverName: String) = serverName(JsonField.of(serverName))

        /**
         * Sets [Builder.serverName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serverName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun serverName(serverName: JsonField<String>) = apply { this.serverName = serverName }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("mcp_toolset_reference")
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
         * Returns an immutable instance of [BetaToolChangeMcpToolsetReference].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .serverName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaToolChangeMcpToolsetReference =
            BetaToolChangeMcpToolsetReference(
                checkRequired("serverName", serverName),
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
    fun validate(): BetaToolChangeMcpToolsetReference = apply {
        if (validated) {
            return@apply
        }

        serverName()
        _type().let {
            if (it != JsonValue.from("mcp_toolset_reference")) {
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
        (if (serverName.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("mcp_toolset_reference")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaToolChangeMcpToolsetReference &&
            serverName == other.serverName &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(serverName, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolChangeMcpToolsetReference{serverName=$serverName, type=$type, additionalProperties=$additionalProperties}"
}
