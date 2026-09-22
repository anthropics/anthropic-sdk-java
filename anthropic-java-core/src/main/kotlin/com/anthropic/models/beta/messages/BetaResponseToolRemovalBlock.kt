package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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
 * An entry of a `compaction` block's `tool_changes`: a tool of the request's `tools` (or an MCP
 * tool or toolset) that the compacted range withdrew. Send it back unchanged.
 */
class BetaResponseToolRemovalBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val tool: JsonField<Tool>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("tool") @ExcludeMissing tool: JsonField<Tool> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(tool, type, mutableMapOf())

    fun toParam(): BetaRequestToolRemovalBlock =
        BetaRequestToolRemovalBlock.builder()
            .tool(
                _tool().map {
                    it.accept(
                        object :
                            BetaResponseToolRemovalBlock.Tool.Visitor<
                                BetaRequestToolRemovalBlock.Tool
                            > {
                            override fun visitReference(
                                reference: BetaResponseToolChangeToolReference
                            ): BetaRequestToolRemovalBlock.Tool =
                                BetaRequestToolRemovalBlock.Tool.ofReference(reference.toParam())

                            override fun visitMcpToolReference(
                                mcpToolReference: BetaResponseToolChangeMcpToolReference
                            ): BetaRequestToolRemovalBlock.Tool =
                                BetaRequestToolRemovalBlock.Tool.ofMcpToolReference(
                                    mcpToolReference.toParam()
                                )

                            override fun visitMcpToolsetReference(
                                mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference
                            ): BetaRequestToolRemovalBlock.Tool =
                                BetaRequestToolRemovalBlock.Tool.ofMcpToolsetReference(
                                    mcpToolsetReference.toParam()
                                )
                        }
                    )
                }
            )
            .build()

    /**
     * A reference to the withdrawn `tools` entry, MCP tool or MCP toolset.
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
     * Returns the raw JSON value of [tool].
     *
     * Unlike [tool], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool") @ExcludeMissing fun _tool(): JsonField<Tool> = tool

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
         * Returns a mutable builder for constructing an instance of [BetaResponseToolRemovalBlock].
         *
         * The following fields are required:
         * ```java
         * .tool()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaResponseToolRemovalBlock] with the required [tool]
         * set to the given value.
         */
        @JvmStatic fun of(tool: Tool) = builder().tool(tool).build()
    }

    /** A builder for [BetaResponseToolRemovalBlock]. */
    class Builder internal constructor() {

        private var tool: JsonField<Tool>? = null
        private var type: JsonValue = JsonValue.from("tool_removal")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaResponseToolRemovalBlock: BetaResponseToolRemovalBlock) = apply {
            tool = betaResponseToolRemovalBlock.tool
            type = betaResponseToolRemovalBlock.type
            additionalProperties = betaResponseToolRemovalBlock.additionalProperties.toMutableMap()
        }

        /** A reference to the withdrawn `tools` entry, MCP tool or MCP toolset. */
        fun tool(tool: Tool) = tool(JsonField.of(tool))

        /**
         * Sets [Builder.tool] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tool] with a well-typed [Tool] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun tool(tool: JsonField<Tool>) = apply { this.tool = tool }

        /** Alias for calling [tool] with `Tool.ofReference(reference)`. */
        fun tool(reference: BetaResponseToolChangeToolReference) = tool(Tool.ofReference(reference))

        /**
         * Alias for calling [tool] with the following:
         * ```java
         * BetaResponseToolChangeToolReference.builder()
         *     .name(name)
         *     .build()
         * ```
         */
        fun referenceTool(name: String) =
            tool(BetaResponseToolChangeToolReference.builder().name(name).build())

        /** Alias for calling [tool] with `Tool.ofMcpToolReference(mcpToolReference)`. */
        fun tool(mcpToolReference: BetaResponseToolChangeMcpToolReference) =
            tool(Tool.ofMcpToolReference(mcpToolReference))

        /** Alias for calling [tool] with `Tool.ofMcpToolsetReference(mcpToolsetReference)`. */
        fun tool(mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference) =
            tool(Tool.ofMcpToolsetReference(mcpToolsetReference))

        /**
         * Alias for calling [tool] with the following:
         * ```java
         * BetaResponseToolChangeMcpToolsetReference.builder()
         *     .serverName(serverName)
         *     .build()
         * ```
         */
        fun mcpToolsetReferenceTool(serverName: String) =
            tool(BetaResponseToolChangeMcpToolsetReference.builder().serverName(serverName).build())

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
         * Returns an immutable instance of [BetaResponseToolRemovalBlock].
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
        fun build(): BetaResponseToolRemovalBlock =
            BetaResponseToolRemovalBlock(
                checkRequired("tool", tool),
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
    fun validate(): BetaResponseToolRemovalBlock = apply {
        if (validated) {
            return@apply
        }

        tool().validate()
        _type().let {
            if (it != JsonValue.from("tool_removal")) {
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
        (tool.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("tool_removal")) 1 else 0 }

    /** A reference to the withdrawn `tools` entry, MCP tool or MCP toolset. */
    @JsonDeserialize(using = Tool.Deserializer::class)
    @JsonSerialize(using = Tool.Serializer::class)
    class Tool
    private constructor(
        private val reference: BetaResponseToolChangeToolReference? = null,
        private val mcpToolReference: BetaResponseToolChangeMcpToolReference? = null,
        private val mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitReference(
                        reference: BetaResponseToolChangeToolReference
                    ): Type = Type.TOOL_REFERENCE

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaResponseToolChangeMcpToolReference
                    ): Type = Type.MCP_TOOL_REFERENCE

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference
                    ): Type = Type.MCP_TOOLSET_REFERENCE

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun name(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitReference(
                        reference: BetaResponseToolChangeToolReference
                    ): Optional<String> = Optional.of(reference.name())

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaResponseToolChangeMcpToolReference
                    ): Optional<String> = Optional.of(mcpToolReference.name())

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference
                    ): Optional<String> = Optional.empty()

                    override fun unknown(json: JsonValue?): Optional<String> =
                        json.getProperty<String>("name").asKnown()
                }
            )

        fun serverName(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitReference(
                        reference: BetaResponseToolChangeToolReference
                    ): Optional<String> = Optional.empty()

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaResponseToolChangeMcpToolReference
                    ): Optional<String> = Optional.of(mcpToolReference.serverName())

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference
                    ): Optional<String> = Optional.of(mcpToolsetReference.serverName())

                    override fun unknown(json: JsonValue?): Optional<String> =
                        json.getProperty<String>("server_name").asKnown()
                }
            )

        /**
         * Reference to a single tool, by the name the model uses to call it, as a ``compaction``
         * block's ``tool_changes`` entry reports it: a tool declared in ``tools`` or defined by an
         * earlier ``tool_addition`` block. Send it back unchanged with the block.
         */
        fun reference(): Optional<BetaResponseToolChangeToolReference> =
            Optional.ofNullable(reference)

        /**
         * Reference to a single MCP tool, by its server and its name on that server, as a
         * ``compaction`` block's ``tool_changes`` entry reports it. Send it back unchanged with the
         * block.
         */
        fun mcpToolReference(): Optional<BetaResponseToolChangeMcpToolReference> =
            Optional.ofNullable(mcpToolReference)

        /**
         * Reference to every tool in the named MCP server's toolset, as a ``compaction`` block's
         * ``tool_changes`` entry reports it. Send it back unchanged with the block.
         */
        fun mcpToolsetReference(): Optional<BetaResponseToolChangeMcpToolsetReference> =
            Optional.ofNullable(mcpToolsetReference)

        fun isReference(): Boolean = reference != null

        fun isMcpToolReference(): Boolean = mcpToolReference != null

        fun isMcpToolsetReference(): Boolean = mcpToolsetReference != null

        /**
         * Reference to a single tool, by the name the model uses to call it, as a ``compaction``
         * block's ``tool_changes`` entry reports it: a tool declared in ``tools`` or defined by an
         * earlier ``tool_addition`` block. Send it back unchanged with the block.
         */
        fun asReference(): BetaResponseToolChangeToolReference = reference.getOrThrow("reference")

        /**
         * Reference to a single MCP tool, by its server and its name on that server, as a
         * ``compaction`` block's ``tool_changes`` entry reports it. Send it back unchanged with the
         * block.
         */
        fun asMcpToolReference(): BetaResponseToolChangeMcpToolReference =
            mcpToolReference.getOrThrow("mcpToolReference")

        /**
         * Reference to every tool in the named MCP server's toolset, as a ``compaction`` block's
         * ``tool_changes`` entry reports it. Send it back unchanged with the block.
         */
        fun asMcpToolsetReference(): BetaResponseToolChangeMcpToolsetReference =
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
         *     public Optional<String> visitReference(BetaResponseToolChangeToolReference reference) {
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
                    override fun visitReference(reference: BetaResponseToolChangeToolReference) {
                        reference.validate()
                    }

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaResponseToolChangeMcpToolReference
                    ) {
                        mcpToolReference.validate()
                    }

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference
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
                    override fun visitReference(reference: BetaResponseToolChangeToolReference) =
                        reference.validity()

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaResponseToolChangeMcpToolReference
                    ) = mcpToolReference.validity()

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference
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
             * Reference to a single tool, by the name the model uses to call it, as a
             * ``compaction`` block's ``tool_changes`` entry reports it: a tool declared in
             * ``tools`` or defined by an earlier ``tool_addition`` block. Send it back unchanged
             * with the block.
             */
            @JvmStatic
            fun ofReference(reference: BetaResponseToolChangeToolReference) =
                Tool(reference = reference)

            /**
             * Returns an immutable instance of [Tool] whose [ofReference] variant is built from the
             * given required [name].
             */
            @JvmStatic
            fun ofReference(name: String) =
                ofReference(BetaResponseToolChangeToolReference.of(name))

            /**
             * Reference to a single MCP tool, by its server and its name on that server, as a
             * ``compaction`` block's ``tool_changes`` entry reports it. Send it back unchanged with
             * the block.
             */
            @JvmStatic
            fun ofMcpToolReference(mcpToolReference: BetaResponseToolChangeMcpToolReference) =
                Tool(mcpToolReference = mcpToolReference)

            /**
             * Reference to every tool in the named MCP server's toolset, as a ``compaction``
             * block's ``tool_changes`` entry reports it. Send it back unchanged with the block.
             */
            @JvmStatic
            fun ofMcpToolsetReference(
                mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference
            ) = Tool(mcpToolsetReference = mcpToolsetReference)

            /**
             * Returns an immutable instance of [Tool] whose [ofMcpToolsetReference] variant is
             * built from the given required [serverName].
             */
            @JvmStatic
            fun ofMcpToolsetReference(serverName: String) =
                ofMcpToolsetReference(BetaResponseToolChangeMcpToolsetReference.of(serverName))
        }

        /** An interface that defines how to map each variant of [Tool] to a value of type [T]. */
        interface Visitor<out T> {

            /**
             * Reference to a single tool, by the name the model uses to call it, as a
             * ``compaction`` block's ``tool_changes`` entry reports it: a tool declared in
             * ``tools`` or defined by an earlier ``tool_addition`` block. Send it back unchanged
             * with the block.
             */
            fun visitReference(reference: BetaResponseToolChangeToolReference): T

            /**
             * Reference to a single MCP tool, by its server and its name on that server, as a
             * ``compaction`` block's ``tool_changes`` entry reports it. Send it back unchanged with
             * the block.
             */
            fun visitMcpToolReference(mcpToolReference: BetaResponseToolChangeMcpToolReference): T

            /**
             * Reference to every tool in the named MCP server's toolset, as a ``compaction``
             * block's ``tool_changes`` entry reports it. Send it back unchanged with the block.
             */
            fun visitMcpToolsetReference(
                mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference
            ): T

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
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaResponseToolChangeToolReference>(),
                            )
                            ?.let { Tool(reference = it, _json = json) } ?: Tool(_json = json)
                    }
                    "mcp_tool_reference" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaResponseToolChangeMcpToolReference>(),
                            )
                            ?.let { Tool(mcpToolReference = it, _json = json) }
                            ?: Tool(_json = json)
                    }
                    "mcp_toolset_reference" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaResponseToolChangeMcpToolsetReference>(),
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

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val TOOL_REFERENCE = of("tool_reference")

                @JvmField val MCP_TOOL_REFERENCE = of("mcp_tool_reference")

                @JvmField val MCP_TOOLSET_REFERENCE = of("mcp_toolset_reference")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                TOOL_REFERENCE,
                MCP_TOOL_REFERENCE,
                MCP_TOOLSET_REFERENCE,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                TOOL_REFERENCE,
                MCP_TOOL_REFERENCE,
                MCP_TOOLSET_REFERENCE,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    TOOL_REFERENCE -> Value.TOOL_REFERENCE
                    MCP_TOOL_REFERENCE -> Value.MCP_TOOL_REFERENCE
                    MCP_TOOLSET_REFERENCE -> Value.MCP_TOOLSET_REFERENCE
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    TOOL_REFERENCE -> Known.TOOL_REFERENCE
                    MCP_TOOL_REFERENCE -> Known.MCP_TOOL_REFERENCE
                    MCP_TOOLSET_REFERENCE -> Known.MCP_TOOLSET_REFERENCE
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
                if (validated) {
                    return@apply
                }

                known()
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
            @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaResponseToolRemovalBlock &&
            tool == other.tool &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(tool, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaResponseToolRemovalBlock{tool=$tool, type=$type, additionalProperties=$additionalProperties}"
}
