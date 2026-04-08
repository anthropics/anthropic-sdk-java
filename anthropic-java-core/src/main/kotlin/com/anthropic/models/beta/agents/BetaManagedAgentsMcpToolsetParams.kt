// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.Enum
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
    private val type: JsonField<Type>,
    private val configs: JsonField<List<BetaManagedAgentsMcpToolConfigParams>>,
    private val defaultConfig: JsonField<BetaManagedAgentsMcpToolsetDefaultConfigParams>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("mcp_server_name")
        @ExcludeMissing
        mcpServerName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
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
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Per-tool configuration overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun configs(): Optional<List<BetaManagedAgentsMcpToolConfigParams>> =
        configs.getOptional("configs")

    /**
     * Default configuration for all tools from an MCP server.
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
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMcpToolsetParams]. */
    class Builder internal constructor() {

        private var mcpServerName: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var configs: JsonField<MutableList<BetaManagedAgentsMcpToolConfigParams>>? = null
        private var defaultConfig: JsonField<BetaManagedAgentsMcpToolsetDefaultConfigParams> =
            JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsMcpToolsetParams: BetaManagedAgentsMcpToolsetParams) =
            apply {
                mcpServerName = betaManagedAgentsMcpToolsetParams.mcpServerName
                type = betaManagedAgentsMcpToolsetParams.type
                configs = betaManagedAgentsMcpToolsetParams.configs.map { it.toMutableList() }
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

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

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

        /** Default configuration for all tools from an MCP server. */
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
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMcpToolsetParams =
            BetaManagedAgentsMcpToolsetParams(
                checkRequired("mcpServerName", mcpServerName),
                checkRequired("type", type),
                (configs ?: JsonMissing.of()).map { it.toImmutable() },
                defaultConfig,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaManagedAgentsMcpToolsetParams = apply {
        if (validated) {
            return@apply
        }

        mcpServerName()
        type().validate()
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
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (configs.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (defaultConfig.asKnown().getOrNull()?.validity() ?: 0)

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val MCP_TOOLSET = of("mcp_toolset")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            MCP_TOOLSET
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            MCP_TOOLSET,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                MCP_TOOLSET -> Value.MCP_TOOLSET
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                MCP_TOOLSET -> Known.MCP_TOOLSET
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

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
