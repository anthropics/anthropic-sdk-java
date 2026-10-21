package com.anthropic.models.beta.agents

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
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

/**
 * Configuration for built-in agent tools. Use this to enable or disable groups of tools available
 * to the agent.
 */
class BetaManagedAgentsAgentToolset20260401Params
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val configs: JsonField<List<BetaManagedAgentsAgentToolConfigParams>>,
    private val defaultConfig: JsonField<BetaManagedAgentsAgentToolsetDefaultConfigParams>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("configs")
        @ExcludeMissing
        configs: JsonField<List<BetaManagedAgentsAgentToolConfigParams>> = JsonMissing.of(),
        @JsonProperty("default_config")
        @ExcludeMissing
        defaultConfig: JsonField<BetaManagedAgentsAgentToolsetDefaultConfigParams> =
            JsonMissing.of(),
    ) : this(type, configs, defaultConfig, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("agent_toolset_20260401")
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
    fun configs(): Optional<List<BetaManagedAgentsAgentToolConfigParams>> =
        configs.getOptional("configs")

    /**
     * Default configuration applied to all tools in this set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun defaultConfig(): Optional<BetaManagedAgentsAgentToolsetDefaultConfigParams> =
        defaultConfig.getOptional("default_config")

    /**
     * Returns the raw JSON value of [configs].
     *
     * Unlike [configs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("configs")
    @ExcludeMissing
    fun _configs(): JsonField<List<BetaManagedAgentsAgentToolConfigParams>> = configs

    /**
     * Returns the raw JSON value of [defaultConfig].
     *
     * Unlike [defaultConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("default_config")
    @ExcludeMissing
    fun _defaultConfig(): JsonField<BetaManagedAgentsAgentToolsetDefaultConfigParams> =
        defaultConfig

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
         * [BetaManagedAgentsAgentToolset20260401Params].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentToolset20260401Params]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("agent_toolset_20260401")
        private var configs: JsonField<MutableList<BetaManagedAgentsAgentToolConfigParams>>? = null
        private var defaultConfig: JsonField<BetaManagedAgentsAgentToolsetDefaultConfigParams> =
            JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentToolset20260401Params: BetaManagedAgentsAgentToolset20260401Params
        ) = apply {
            type = betaManagedAgentsAgentToolset20260401Params.type
            configs =
                betaManagedAgentsAgentToolset20260401Params.configs
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            defaultConfig = betaManagedAgentsAgentToolset20260401Params.defaultConfig
            additionalProperties =
                betaManagedAgentsAgentToolset20260401Params.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("agent_toolset_20260401")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Per-tool configuration overrides. */
        fun configs(configs: List<BetaManagedAgentsAgentToolConfigParams>) =
            configs(JsonField.of(configs))

        /**
         * Sets [Builder.configs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.configs] with a well-typed
         * `List<BetaManagedAgentsAgentToolConfigParams>` value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun configs(configs: JsonField<List<BetaManagedAgentsAgentToolConfigParams>>) = apply {
            this.configs = configs.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaManagedAgentsAgentToolConfigParams] to [configs].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addConfig(config: BetaManagedAgentsAgentToolConfigParams) = apply {
            configs =
                (configs ?: JsonField.of(mutableListOf())).also {
                    checkKnown("configs", it).add(config)
                }
        }

        /**
         * Alias for calling [addConfig] with `BetaManagedAgentsAgentToolConfigParams.ofBash(bash)`.
         */
        fun addConfig(bash: BetaManagedAgentsBashToolConfigParams) =
            addConfig(BetaManagedAgentsAgentToolConfigParams.ofBash(bash))

        /**
         * Alias for calling [addConfig] with `BetaManagedAgentsAgentToolConfigParams.ofEdit(edit)`.
         */
        fun addConfig(edit: BetaManagedAgentsEditToolConfigParams) =
            addConfig(BetaManagedAgentsAgentToolConfigParams.ofEdit(edit))

        /**
         * Alias for calling [addConfig] with `BetaManagedAgentsAgentToolConfigParams.ofRead(read)`.
         */
        fun addConfig(read: BetaManagedAgentsReadToolConfigParams) =
            addConfig(BetaManagedAgentsAgentToolConfigParams.ofRead(read))

        /**
         * Alias for calling [addConfig] with
         * `BetaManagedAgentsAgentToolConfigParams.ofWrite(write)`.
         */
        fun addConfig(write: BetaManagedAgentsWriteToolConfigParams) =
            addConfig(BetaManagedAgentsAgentToolConfigParams.ofWrite(write))

        /**
         * Alias for calling [addConfig] with `BetaManagedAgentsAgentToolConfigParams.ofGlob(glob)`.
         */
        fun addConfig(glob: BetaManagedAgentsGlobToolConfigParams) =
            addConfig(BetaManagedAgentsAgentToolConfigParams.ofGlob(glob))

        /**
         * Alias for calling [addConfig] with `BetaManagedAgentsAgentToolConfigParams.ofGrep(grep)`.
         */
        fun addConfig(grep: BetaManagedAgentsGrepToolConfigParams) =
            addConfig(BetaManagedAgentsAgentToolConfigParams.ofGrep(grep))

        /**
         * Alias for calling [addConfig] with
         * `BetaManagedAgentsAgentToolConfigParams.ofWebFetch(webFetch)`.
         */
        fun addConfig(webFetch: BetaManagedAgentsWebFetchToolConfigParams) =
            addConfig(BetaManagedAgentsAgentToolConfigParams.ofWebFetch(webFetch))

        /**
         * Alias for calling [addConfig] with
         * `BetaManagedAgentsAgentToolConfigParams.ofWebSearch(webSearch)`.
         */
        fun addConfig(webSearch: BetaManagedAgentsWebSearchToolConfigParams) =
            addConfig(BetaManagedAgentsAgentToolConfigParams.ofWebSearch(webSearch))

        /** Default configuration applied to all tools in this set. */
        fun defaultConfig(defaultConfig: BetaManagedAgentsAgentToolsetDefaultConfigParams?) =
            defaultConfig(JsonField.ofNullable(defaultConfig))

        /** Alias for calling [Builder.defaultConfig] with `defaultConfig.orElse(null)`. */
        fun defaultConfig(
            defaultConfig: Optional<BetaManagedAgentsAgentToolsetDefaultConfigParams>
        ) = defaultConfig(defaultConfig.getOrNull())

        /**
         * Sets [Builder.defaultConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.defaultConfig] with a well-typed
         * [BetaManagedAgentsAgentToolsetDefaultConfigParams] value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun defaultConfig(
            defaultConfig: JsonField<BetaManagedAgentsAgentToolsetDefaultConfigParams>
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
         * Returns an immutable instance of [BetaManagedAgentsAgentToolset20260401Params].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsAgentToolset20260401Params =
            BetaManagedAgentsAgentToolset20260401Params(
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
    fun validate(): BetaManagedAgentsAgentToolset20260401Params = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("agent_toolset_20260401")) {
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
        type.let { if (it == JsonValue.from("agent_toolset_20260401")) 1 else 0 } +
            (configs.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (defaultConfig.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentToolset20260401Params &&
            type == other.type &&
            configs == other.configs &&
            defaultConfig == other.defaultConfig &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, configs, defaultConfig, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentToolset20260401Params{type=$type, configs=$configs, defaultConfig=$defaultConfig, additionalProperties=$additionalProperties}"
}
