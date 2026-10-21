package com.anthropic.models.beta.agents

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Configuration for tools from an MCP server defined in `mcp_servers`. */
class BetaManagedAgentsMcpToolsetParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val mcpServerName: JsonField<String>,
    private val type: JsonValue,
    private val configs: JsonField<List<BetaManagedAgentsMcpToolConfigParams>>,
    private val defaultConfig: JsonField<BetaManagedAgentsMcpToolsetDefaultConfigParams>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("mcp_server_name")
        @ExcludeMissing
        mcpServerName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("configs")
        @ExcludeMissing
        configs: JsonField<List<BetaManagedAgentsMcpToolConfigParams>> = JsonMissing.of(),
        @JsonProperty("default_config")
        @ExcludeMissing
        defaultConfig: JsonField<BetaManagedAgentsMcpToolsetDefaultConfigParams> = JsonMissing.of(),
    ) : this(mcpServerName, type, configs, defaultConfig, mutableMapOf())

    /**
     * Name of the MCP server. Must match a server name from the mcp_servers array. 1-255
     * characters.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun mcpServerName(): String = mcpServerName.getRequired("mcp_server_name")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("mcp_toolset")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Per-tool configuration overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun configs(): Optional<List<BetaManagedAgentsMcpToolConfigParams>> =
        configs.getOptional("configs")

    /**
     * Default configuration for all tools from this server.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun defaultConfig(): Optional<BetaManagedAgentsMcpToolsetDefaultConfigParams> =
        defaultConfig.getOptional("default_config")

    /**
     * Returns the raw JSON value of [mcpServerName].
     *
     * Unlike [mcpServerName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mcp_server_name")
    @ExcludeMissing
    fun _mcpServerName(): JsonField<String> = mcpServerName

    /**
     * Returns the raw JSON value of [configs].
     *
     * Unlike [configs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("configs")
    @ExcludeMissing
    fun _configs(): JsonField<List<BetaManagedAgentsMcpToolConfigParams>> = configs

    /**
     * Returns the raw JSON value of [defaultConfig].
     *
     * Unlike [defaultConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("default_config")
    @ExcludeMissing
    fun _defaultConfig(): JsonField<BetaManagedAgentsMcpToolsetDefaultConfigParams> = defaultConfig

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
         * [BetaManagedAgentsMcpToolsetParams].
         *
         * The following fields are required:
         * ```java
         * .mcpServerName()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsMcpToolsetParams] with the required
         * [mcpServerName] set to the given value.
         */
        @JvmStatic fun of(mcpServerName: String) = builder().mcpServerName(mcpServerName).build()
    }

    /** A builder for [BetaManagedAgentsMcpToolsetParams]. */
    class Builder internal constructor() {

        private var mcpServerName: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("mcp_toolset")
        private var configs: JsonField<MutableList<BetaManagedAgentsMcpToolConfigParams>>? = null
        private var defaultConfig: JsonField<BetaManagedAgentsMcpToolsetDefaultConfigParams> =
            JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsMcpToolsetParams: BetaManagedAgentsMcpToolsetParams) =
            apply {
                mcpServerName = betaManagedAgentsMcpToolsetParams.mcpServerName
                type = betaManagedAgentsMcpToolsetParams.type
                configs =
                    betaManagedAgentsMcpToolsetParams.configs
                        .map { it.toMutableList() }
                        .takeUnless { it.isMissing() }
                defaultConfig = betaManagedAgentsMcpToolsetParams.defaultConfig
                additionalProperties =
                    betaManagedAgentsMcpToolsetParams.additionalProperties.toMutableMap()
            }

        /**
         * Name of the MCP server. Must match a server name from the mcp_servers array. 1-255
         * characters.
         */
        fun mcpServerName(mcpServerName: String) = mcpServerName(JsonField.of(mcpServerName))

        /**
         * Sets [Builder.mcpServerName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mcpServerName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mcpServerName(mcpServerName: JsonField<String>) = apply {
            this.mcpServerName = mcpServerName
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("mcp_toolset")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Per-tool configuration overrides. */
        fun configs(configs: List<BetaManagedAgentsMcpToolConfigParams>) =
            configs(JsonField.of(configs))

        /**
         * Sets [Builder.configs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.configs] with a well-typed
         * `List<BetaManagedAgentsMcpToolConfigParams>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun configs(configs: JsonField<List<BetaManagedAgentsMcpToolConfigParams>>) = apply {
            this.configs = configs.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaManagedAgentsMcpToolConfigParams] to [configs].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addConfig(config: BetaManagedAgentsMcpToolConfigParams) = apply {
            configs =
                (configs ?: JsonField.of(mutableListOf())).also {
                    checkKnown("configs", it).add(config)
                }
        }

        /** Default configuration for all tools from this server. */
        fun defaultConfig(defaultConfig: BetaManagedAgentsMcpToolsetDefaultConfigParams?) =
            defaultConfig(JsonField.ofNullable(defaultConfig))

        /** Alias for calling [Builder.defaultConfig] with `defaultConfig.orElse(null)`. */
        fun defaultConfig(defaultConfig: Optional<BetaManagedAgentsMcpToolsetDefaultConfigParams>) =
            defaultConfig(defaultConfig.getOrNull())

        /**
         * Sets [Builder.defaultConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.defaultConfig] with a well-typed
         * [BetaManagedAgentsMcpToolsetDefaultConfigParams] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun defaultConfig(
            defaultConfig: JsonField<BetaManagedAgentsMcpToolsetDefaultConfigParams>
        ) = apply { this.defaultConfig = defaultConfig }

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
         * Returns an immutable instance of [BetaManagedAgentsMcpToolsetParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .mcpServerName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMcpToolsetParams =
            BetaManagedAgentsMcpToolsetParams(
                checkRequired("mcpServerName", mcpServerName),
                type,
                (configs ?: JsonMissing.of()).map { it.toImmutable() },
                defaultConfig,
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
    fun validate(): BetaManagedAgentsMcpToolsetParams = apply {
        if (validated) {
            return@apply
        }

        mcpServerName()
        _type().let {
            if (it != JsonValue.from("mcp_toolset")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        configs().ifPresent { it.forEach { it.validate() } }
        defaultConfig().ifPresent { it.validate() }
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
        (if (mcpServerName.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("mcp_toolset")) 1 else 0 } +
            (configs.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (defaultConfig.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMcpToolsetParams &&
            mcpServerName == other.mcpServerName &&
            type == other.type &&
            configs == other.configs &&
            defaultConfig == other.defaultConfig &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(mcpServerName, type, configs, defaultConfig, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMcpToolsetParams{mcpServerName=$mcpServerName, type=$type, configs=$configs, defaultConfig=$defaultConfig, additionalProperties=$additionalProperties}"
}
