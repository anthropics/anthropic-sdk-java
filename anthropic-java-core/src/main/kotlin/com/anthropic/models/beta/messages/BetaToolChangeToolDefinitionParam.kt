package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.toolFromClass
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * A tool defined by value: `definition` is a `tools` entry (any kind `tools` accepts, an MCP
 * toolset included). An `mcp_toolset` given here also requires the `mcp-client-2026-09-15` beta.
 */
class BetaToolChangeToolDefinitionParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val definition: JsonField<BetaToolUnion>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("definition")
        @ExcludeMissing
        definition: JsonField<BetaToolUnion> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(definition, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun definition(): BetaToolUnion = definition.getRequired("definition")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("tool_definition")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [definition].
     *
     * Unlike [definition], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("definition")
    @ExcludeMissing
    fun _definition(): JsonField<BetaToolUnion> = definition

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
         * [BetaToolChangeToolDefinitionParam].
         *
         * The following fields are required:
         * ```java
         * .definition()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaToolChangeToolDefinitionParam] with the required
         * [definition] set to the given value.
         */
        @JvmStatic fun of(definition: BetaToolUnion) = builder().definition(definition).build()
    }

    /** A builder for [BetaToolChangeToolDefinitionParam]. */
    class Builder internal constructor() {

        private var definition: JsonField<BetaToolUnion>? = null
        private var type: JsonValue = JsonValue.from("tool_definition")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolChangeToolDefinitionParam: BetaToolChangeToolDefinitionParam) =
            apply {
                definition = betaToolChangeToolDefinitionParam.definition
                type = betaToolChangeToolDefinitionParam.type
                additionalProperties =
                    betaToolChangeToolDefinitionParam.additionalProperties.toMutableMap()
            }

        fun definition(definition: BetaToolUnion) = definition(JsonField.of(definition))

        /**
         * Sets [Builder.definition] to an arbitrary JSON value.
         *
         * You should usually call [Builder.definition] with a well-typed [BetaToolUnion] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun definition(definition: JsonField<BetaToolUnion>) = apply {
            this.definition = definition
        }

        /** Alias for calling [Builder.definition] with `definition.toParam()`. */
        fun definition(definition: BetaResponseToolUnion) = definition(definition.toParam())

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofBetaResponseTool(betaResponseTool)`.
         */
        fun definition(betaResponseTool: BetaResponseTool) =
            definition(BetaResponseToolUnion.ofBetaResponseTool(betaResponseTool))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofToolBash20241022(toolBash20241022)`.
         */
        fun definition(toolBash20241022: BetaToolBash20241022) =
            definition(BetaResponseToolUnion.ofToolBash20241022(toolBash20241022))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofToolBash20250124(toolBash20250124)`.
         */
        fun definition(toolBash20250124: BetaToolBash20250124) =
            definition(BetaResponseToolUnion.ofToolBash20250124(toolBash20250124))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)`.
         */
        fun definition(codeExecutionTool20250522: BetaCodeExecutionTool20250522) =
            definition(BetaResponseToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)`.
         */
        fun definition(codeExecutionTool20250825: BetaCodeExecutionTool20250825) =
            definition(BetaResponseToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)`.
         */
        fun definition(codeExecutionTool20260120: BetaCodeExecutionTool20260120) =
            definition(BetaResponseToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20260521(codeExecutionTool20260521)`.
         */
        fun definition(codeExecutionTool20260521: BetaCodeExecutionTool20260521) =
            definition(BetaResponseToolUnion.ofCodeExecutionTool20260521(codeExecutionTool20260521))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofBrowserToolset20260801(browserToolset20260801)`.
         */
        fun definition(browserToolset20260801: BetaBrowserToolset20260801) =
            definition(BetaResponseToolUnion.ofBrowserToolset20260801(browserToolset20260801))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofToolComputerUse20241022(toolComputerUse20241022)`.
         */
        fun definition(toolComputerUse20241022: BetaToolComputerUse20241022) =
            definition(BetaResponseToolUnion.ofToolComputerUse20241022(toolComputerUse20241022))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofMemoryTool20250818(memoryTool20250818)`.
         */
        fun definition(memoryTool20250818: BetaMemoryTool20250818) =
            definition(BetaResponseToolUnion.ofMemoryTool20250818(memoryTool20250818))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofToolComputerUse20250124(toolComputerUse20250124)`.
         */
        fun definition(toolComputerUse20250124: BetaToolComputerUse20250124) =
            definition(BetaResponseToolUnion.ofToolComputerUse20250124(toolComputerUse20250124))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofToolTextEditor20241022(toolTextEditor20241022)`.
         */
        fun definition(toolTextEditor20241022: BetaToolTextEditor20241022) =
            definition(BetaResponseToolUnion.ofToolTextEditor20241022(toolTextEditor20241022))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofToolComputerUse20251124(toolComputerUse20251124)`.
         */
        fun definition(toolComputerUse20251124: BetaToolComputerUse20251124) =
            definition(BetaResponseToolUnion.ofToolComputerUse20251124(toolComputerUse20251124))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofComputerToolset20260801(computerToolset20260801)`.
         */
        fun definition(computerToolset20260801: BetaComputerToolset20260801) =
            definition(BetaResponseToolUnion.ofComputerToolset20260801(computerToolset20260801))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofToolTextEditor20250124(toolTextEditor20250124)`.
         */
        fun definition(toolTextEditor20250124: BetaToolTextEditor20250124) =
            definition(BetaResponseToolUnion.ofToolTextEditor20250124(toolTextEditor20250124))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofToolTextEditor20250429(toolTextEditor20250429)`.
         */
        fun definition(toolTextEditor20250429: BetaToolTextEditor20250429) =
            definition(BetaResponseToolUnion.ofToolTextEditor20250429(toolTextEditor20250429))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofToolTextEditor20250728(toolTextEditor20250728)`.
         */
        fun definition(toolTextEditor20250728: BetaToolTextEditor20250728) =
            definition(BetaResponseToolUnion.ofToolTextEditor20250728(toolTextEditor20250728))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofWebSearchTool20250305(webSearchTool20250305)`.
         */
        fun definition(webSearchTool20250305: BetaWebSearchTool20250305) =
            definition(BetaResponseToolUnion.ofWebSearchTool20250305(webSearchTool20250305))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofWebFetchTool20250910(webFetchTool20250910)`.
         */
        fun definition(webFetchTool20250910: BetaWebFetchTool20250910) =
            definition(BetaResponseToolUnion.ofWebFetchTool20250910(webFetchTool20250910))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofWebSearchTool20260209(webSearchTool20260209)`.
         */
        fun definition(webSearchTool20260209: BetaWebSearchTool20260209) =
            definition(BetaResponseToolUnion.ofWebSearchTool20260209(webSearchTool20260209))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofWebFetchTool20260209(webFetchTool20260209)`.
         */
        fun definition(webFetchTool20260209: BetaWebFetchTool20260209) =
            definition(BetaResponseToolUnion.ofWebFetchTool20260209(webFetchTool20260209))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofWebFetchTool20260309(webFetchTool20260309)`.
         */
        fun definition(webFetchTool20260309: BetaWebFetchTool20260309) =
            definition(BetaResponseToolUnion.ofWebFetchTool20260309(webFetchTool20260309))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofWebSearchTool20260318(webSearchTool20260318)`.
         */
        fun definition(webSearchTool20260318: BetaWebSearchTool20260318) =
            definition(BetaResponseToolUnion.ofWebSearchTool20260318(webSearchTool20260318))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofWebFetchTool20260318(webFetchTool20260318)`.
         */
        fun definition(webFetchTool20260318: BetaWebFetchTool20260318) =
            definition(BetaResponseToolUnion.ofWebFetchTool20260318(webFetchTool20260318))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofAdvisorTool20260301(advisorTool20260301)`.
         */
        fun definition(advisorTool20260301: BetaAdvisorTool20260301) =
            definition(BetaResponseToolUnion.ofAdvisorTool20260301(advisorTool20260301))

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofToolSearchToolBm25_20251119(toolSearchToolBm25_20251119)`.
         */
        fun definition(toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119) =
            definition(
                BetaResponseToolUnion.ofToolSearchToolBm25_20251119(toolSearchToolBm25_20251119)
            )

        /**
         * Alias for calling [definition] with
         * `BetaResponseToolUnion.ofToolSearchToolRegex20251119(toolSearchToolRegex20251119)`.
         */
        fun definition(toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119) =
            definition(
                BetaResponseToolUnion.ofToolSearchToolRegex20251119(toolSearchToolRegex20251119)
            )

        /** Alias for calling [definition] with `BetaResponseToolUnion.ofMcpToolset(mcpToolset)`. */
        fun definition(mcpToolset: BetaMcpToolset) =
            definition(BetaResponseToolUnion.ofMcpToolset(mcpToolset))

        /** Alias for calling [definition] with `BetaToolUnion.ofBetaTool(betaTool)`. */
        fun definition(betaTool: BetaTool) = definition(BetaToolUnion.ofBetaTool(betaTool))

        /**
         * Sets [definition] to a [BetaTool] where the JSON schema describing the tool's parameters
         * is derived from the fields of a given class. Local validation of that JSON schema can be
         * performed to check if the schema is likely to pass remote validation by the AI model. By
         * default, local validation is enabled; disable it by setting [localValidation] to
         * [JsonSchemaLocalValidation.NO].
         *
         * @see MessageCreateParams.Builder.addTool
         */
        @JvmOverloads
        fun definition(
            toolParametersType: Class<*>,
            localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
        ) = definition(toolFromClass(toolParametersType, localValidation))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("tool_definition")
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
         * Returns an immutable instance of [BetaToolChangeToolDefinitionParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .definition()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaToolChangeToolDefinitionParam =
            BetaToolChangeToolDefinitionParam(
                checkRequired("definition", definition),
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
    fun validate(): BetaToolChangeToolDefinitionParam = apply {
        if (validated) {
            return@apply
        }

        definition().validate()
        _type().let {
            if (it != JsonValue.from("tool_definition")) {
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
        (definition.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("tool_definition")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaToolChangeToolDefinitionParam &&
            definition == other.definition &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(definition, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolChangeToolDefinitionParam{definition=$definition, type=$type, additionalProperties=$additionalProperties}"
}
