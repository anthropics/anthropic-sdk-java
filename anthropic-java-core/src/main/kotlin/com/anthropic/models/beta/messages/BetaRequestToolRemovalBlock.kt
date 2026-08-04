// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Mid-conversation directive to withdraw a tool.
 *
 * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is no longer
 * offered to the model from this point in the conversation onward.
 */
class BetaRequestToolRemovalBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val tool: JsonField<Tool>,
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("tool") @ExcludeMissing tool: JsonField<Tool> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
    ) : this(tool, type, cacheControl, mutableMapOf())

    /**
     * Reference to a single tool the caller declared directly in ``tools[]``. Does not accept the
     * composed ``{server}_{name}`` form the server assigns to MCP-resolved tools — use
     * ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tool(): Tool = tool.getRequired("tool")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("tool_removal")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        cacheControl.getOptional("cache_control")

    /**
     * Returns the raw JSON value of [tool].
     *
     * Unlike [tool], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool") @ExcludeMissing fun _tool(): JsonField<Tool> = tool

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

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
         * Returns a mutable builder for constructing an instance of [BetaRequestToolRemovalBlock].
         *
         * The following fields are required:
         * ```java
         * .tool()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRequestToolRemovalBlock]. */
    class Builder internal constructor() {

        private var tool: JsonField<Tool>? = null
        private var type: JsonValue = JsonValue.from("tool_removal")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRequestToolRemovalBlock: BetaRequestToolRemovalBlock) = apply {
            tool = betaRequestToolRemovalBlock.tool
            type = betaRequestToolRemovalBlock.type
            cacheControl = betaRequestToolRemovalBlock.cacheControl
            additionalProperties = betaRequestToolRemovalBlock.additionalProperties.toMutableMap()
        }

        /**
         * Reference to a single tool the caller declared directly in ``tools[]``. Does not accept
         * the composed ``{server}_{name}`` form the server assigns to MCP-resolved tools — use
         * ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
         */
        fun tool(tool: Tool) = tool(JsonField.of(tool))

        /**
         * Sets [Builder.tool] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tool] with a well-typed [Tool] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun tool(tool: JsonField<Tool>) = apply { this.tool = tool }

        /** Alias for calling [tool] with `Tool.ofReference(reference)`. */
        fun tool(reference: BetaToolChangeToolReference) = tool(Tool.ofReference(reference))

        /**
         * Alias for calling [tool] with the following:
         * ```java
         * BetaToolChangeToolReference.builder()
         *     .name(name)
         *     .build()
         * ```
         */
        fun referenceTool(name: String) =
            tool(BetaToolChangeToolReference.builder().name(name).build())

        /** Alias for calling [tool] with `Tool.ofMcpToolReference(mcpToolReference)`. */
        fun tool(mcpToolReference: BetaToolChangeMcpToolReference) =
            tool(Tool.ofMcpToolReference(mcpToolReference))

        /** Alias for calling [tool] with `Tool.ofMcpToolsetReference(mcpToolsetReference)`. */
        fun tool(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
            tool(Tool.ofMcpToolsetReference(mcpToolsetReference))

        /**
         * Alias for calling [tool] with the following:
         * ```java
         * BetaToolChangeMcpToolsetReference.builder()
         *     .serverName(serverName)
         *     .build()
         * ```
         */
        fun mcpToolsetReferenceTool(serverName: String) =
            tool(BetaToolChangeMcpToolsetReference.builder().serverName(serverName).build())

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("tool_removal")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed
         * [BetaCacheControlEphemeral] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
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
         * Returns an immutable instance of [BetaRequestToolRemovalBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .tool()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaRequestToolRemovalBlock =
            BetaRequestToolRemovalBlock(
                checkRequired("tool", tool),
                type,
                cacheControl,
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
    fun validate(): BetaRequestToolRemovalBlock = apply {
        if (validated) {
            return@apply
        }

        tool().validate()
        _type().let {
            if (it != JsonValue.from("tool_removal")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
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
        (tool.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("tool_removal")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * Reference to a single tool the caller declared directly in ``tools[]``. Does not accept the
     * composed ``{server}_{name}`` form the server assigns to MCP-resolved tools — use
     * ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
     */
    @JsonDeserialize(using = Tool.Deserializer::class)
    @JsonSerialize(using = Tool.Serializer::class)
    class Tool
    private constructor(
        private val reference: BetaToolChangeToolReference? = null,
        private val mcpToolReference: BetaToolChangeMcpToolReference? = null,
        private val mcpToolsetReference: BetaToolChangeMcpToolsetReference? = null,
        private val _json: JsonValue? = null,
    ) {

        fun name(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitReference(
                        reference: BetaToolChangeToolReference
                    ): Optional<String> = Optional.of(reference.name())

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaToolChangeMcpToolReference
                    ): Optional<String> = Optional.of(mcpToolReference.name())

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaToolChangeMcpToolsetReference
                    ): Optional<String> = Optional.empty()
                }
            )

        fun serverName(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitReference(
                        reference: BetaToolChangeToolReference
                    ): Optional<String> = Optional.empty()

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaToolChangeMcpToolReference
                    ): Optional<String> = Optional.of(mcpToolReference.serverName())

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaToolChangeMcpToolsetReference
                    ): Optional<String> = Optional.of(mcpToolsetReference.serverName())
                }
            )

        /**
         * Reference to a single tool the caller declared directly in ``tools[]``. Does not accept
         * the composed ``{server}_{name}`` form the server assigns to MCP-resolved tools — use
         * ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
         */
        fun reference(): Optional<BetaToolChangeToolReference> = Optional.ofNullable(reference)

        /**
         * Reference to a single MCP tool by its server and remote name — the same
         * ``server_name``/``name`` pair ``mcp_tool_use`` carries.
         */
        fun mcpToolReference(): Optional<BetaToolChangeMcpToolReference> =
            Optional.ofNullable(mcpToolReference)

        /** Reference to every tool in the named MCP server's toolset. */
        fun mcpToolsetReference(): Optional<BetaToolChangeMcpToolsetReference> =
            Optional.ofNullable(mcpToolsetReference)

        fun isReference(): Boolean = reference != null

        fun isMcpToolReference(): Boolean = mcpToolReference != null

        fun isMcpToolsetReference(): Boolean = mcpToolsetReference != null

        /**
         * Reference to a single tool the caller declared directly in ``tools[]``. Does not accept
         * the composed ``{server}_{name}`` form the server assigns to MCP-resolved tools — use
         * ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
         */
        fun asReference(): BetaToolChangeToolReference = reference.getOrThrow("reference")

        /**
         * Reference to a single MCP tool by its server and remote name — the same
         * ``server_name``/``name`` pair ``mcp_tool_use`` carries.
         */
        fun asMcpToolReference(): BetaToolChangeMcpToolReference =
            mcpToolReference.getOrThrow("mcpToolReference")

        /** Reference to every tool in the named MCP server's toolset. */
        fun asMcpToolsetReference(): BetaToolChangeMcpToolsetReference =
            mcpToolsetReference.getOrThrow("mcpToolsetReference")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = tool.accept(new Tool.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitReference(BetaToolChangeToolReference reference) {
         *         return Optional.of(reference.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                reference != null -> visitor.visitReference(reference)
                mcpToolReference != null -> visitor.visitMcpToolReference(mcpToolReference)
                mcpToolsetReference != null -> visitor.visitMcpToolsetReference(mcpToolsetReference)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Tool = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitReference(reference: BetaToolChangeToolReference) {
                        reference.validate()
                    }

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaToolChangeMcpToolReference
                    ) {
                        mcpToolReference.validate()
                    }

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaToolChangeMcpToolsetReference
                    ) {
                        mcpToolsetReference.validate()
                    }
                }
            )
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitReference(reference: BetaToolChangeToolReference) =
                        reference.validity()

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaToolChangeMcpToolReference
                    ) = mcpToolReference.validity()

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaToolChangeMcpToolsetReference
                    ) = mcpToolsetReference.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Tool &&
                reference == other.reference &&
                mcpToolReference == other.mcpToolReference &&
                mcpToolsetReference == other.mcpToolsetReference
        }

        override fun hashCode(): Int =
            Objects.hash(reference, mcpToolReference, mcpToolsetReference)

        override fun toString(): String =
            when {
                reference != null -> "Tool{reference=$reference}"
                mcpToolReference != null -> "Tool{mcpToolReference=$mcpToolReference}"
                mcpToolsetReference != null -> "Tool{mcpToolsetReference=$mcpToolsetReference}"
                _json != null -> "Tool{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Tool")
            }

        companion object {

            /**
             * Reference to a single tool the caller declared directly in ``tools[]``. Does not
             * accept the composed ``{server}_{name}`` form the server assigns to MCP-resolved tools
             * — use ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
             */
            @JvmStatic
            fun ofReference(reference: BetaToolChangeToolReference) = Tool(reference = reference)

            /**
             * Reference to a single MCP tool by its server and remote name — the same
             * ``server_name``/``name`` pair ``mcp_tool_use`` carries.
             */
            @JvmStatic
            fun ofMcpToolReference(mcpToolReference: BetaToolChangeMcpToolReference) =
                Tool(mcpToolReference = mcpToolReference)

            /** Reference to every tool in the named MCP server's toolset. */
            @JvmStatic
            fun ofMcpToolsetReference(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
                Tool(mcpToolsetReference = mcpToolsetReference)
        }

        /** An interface that defines how to map each variant of [Tool] to a value of type [T]. */
        interface Visitor<out T> {

            /**
             * Reference to a single tool the caller declared directly in ``tools[]``. Does not
             * accept the composed ``{server}_{name}`` form the server assigns to MCP-resolved tools
             * — use ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
             */
            fun visitReference(reference: BetaToolChangeToolReference): T

            /**
             * Reference to a single MCP tool by its server and remote name — the same
             * ``server_name``/``name`` pair ``mcp_tool_use`` carries.
             */
            fun visitMcpToolReference(mcpToolReference: BetaToolChangeMcpToolReference): T

            /** Reference to every tool in the named MCP server's toolset. */
            fun visitMcpToolsetReference(mcpToolsetReference: BetaToolChangeMcpToolsetReference): T

            /**
             * Maps an unknown variant of [Tool] to a value of type [T].
             *
             * An instance of [Tool] can contain an unknown variant if it was deserialized from data
             * that doesn't match any known variant. For example, if the SDK is on an older version
             * than the API, then the API may respond with new variants that the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Tool: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Tool>(Tool::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Tool {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "tool_reference" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaToolChangeToolReference>())
                            ?.let { Tool(reference = it, _json = json) } ?: Tool(_json = json)
                    }
                    "mcp_tool_reference" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaToolChangeMcpToolReference>(),
                            )
                            ?.let { Tool(mcpToolReference = it, _json = json) }
                            ?: Tool(_json = json)
                    }
                    "mcp_toolset_reference" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaToolChangeMcpToolsetReference>(),
                            )
                            ?.let { Tool(mcpToolsetReference = it, _json = json) }
                            ?: Tool(_json = json)
                    }
                }

                return Tool(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Tool>(Tool::class) {

            override fun serialize(
                value: Tool,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.reference != null -> generator.writeObject(value.reference)
                    value.mcpToolReference != null -> generator.writeObject(value.mcpToolReference)
                    value.mcpToolsetReference != null ->
                        generator.writeObject(value.mcpToolsetReference)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Tool")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRequestToolRemovalBlock &&
            tool == other.tool &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(tool, type, cacheControl, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRequestToolRemovalBlock{tool=$tool, type=$type, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
}
