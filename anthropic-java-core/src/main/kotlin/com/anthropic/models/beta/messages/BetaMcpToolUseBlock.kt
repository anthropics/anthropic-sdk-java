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

class BetaMcpToolUseBlock
private constructor(
    private val id: JsonField<String>,
    private val input: JsonValue,
    private val name: JsonField<String>,
    private val serverName: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("input") @ExcludeMissing input: JsonValue = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("server_name")
        @ExcludeMissing
        serverName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, input, name, serverName, type, mutableMapOf())

    fun toParam(): BetaMcpToolUseBlockParam =
        BetaMcpToolUseBlockParam.builder()
            .id(_id())
            .input(_input())
            .name(_name())
            .serverName(_serverName())
            .build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    @JsonProperty("input") @ExcludeMissing fun _input(): JsonValue = input

    /**
     * The name of the MCP tool
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * The name of the MCP server
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun serverName(): String = serverName.getRequired("server_name")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("mcp_tool_use")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

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
         * Returns a mutable builder for constructing an instance of [BetaMcpToolUseBlock].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .input()
         * .name()
         * .serverName()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMcpToolUseBlock]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var input: JsonValue? = null
        private var name: JsonField<String>? = null
        private var serverName: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("mcp_tool_use")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMcpToolUseBlock: BetaMcpToolUseBlock) = apply {
            id = betaMcpToolUseBlock.id
            input = betaMcpToolUseBlock.input
            name = betaMcpToolUseBlock.name
            serverName = betaMcpToolUseBlock.serverName
            type = betaMcpToolUseBlock.type
            additionalProperties = betaMcpToolUseBlock.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        fun input(input: JsonValue) = apply { this.input = input }

        /** The name of the MCP tool */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** The name of the MCP server */
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
         * JsonValue.from("mcp_tool_use")
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
         * Returns an immutable instance of [BetaMcpToolUseBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .input()
         * .name()
         * .serverName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaMcpToolUseBlock =
            BetaMcpToolUseBlock(
                checkRequired("id", id),
                checkRequired("input", input),
                checkRequired("name", name),
                checkRequired("serverName", serverName),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaMcpToolUseBlock = apply {
        if (validated) {
            return@apply
        }

        id()
        name()
        serverName()
        _type().let {
            if (it != JsonValue.from("mcp_tool_use")) {
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (if (serverName.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("mcp_tool_use")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaMcpToolUseBlock && id == other.id && input == other.input && name == other.name && serverName == other.serverName && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(id, input, name, serverName, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMcpToolUseBlock{id=$id, input=$input, name=$name, serverName=$serverName, type=$type, additionalProperties=$additionalProperties}"
}
