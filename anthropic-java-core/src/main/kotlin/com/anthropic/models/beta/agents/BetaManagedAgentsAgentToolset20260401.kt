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
import kotlin.jvm.optionals.getOrNull

class BetaManagedAgentsAgentToolset20260401
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val configs: JsonField<List<BetaManagedAgentsAgentToolConfig>>,
    private val defaultConfig: JsonField<BetaManagedAgentsAgentToolsetDefaultConfig>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("configs")
        @ExcludeMissing
        configs: JsonField<List<BetaManagedAgentsAgentToolConfig>> = JsonMissing.of(),
        @JsonProperty("default_config")
        @ExcludeMissing
        defaultConfig: JsonField<BetaManagedAgentsAgentToolsetDefaultConfig> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(configs, defaultConfig, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun configs(): List<BetaManagedAgentsAgentToolConfig> = configs.getRequired("configs")

    /**
     * Resolved default configuration for agent tools.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun defaultConfig(): BetaManagedAgentsAgentToolsetDefaultConfig =
        defaultConfig.getRequired("default_config")

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
     * Returns the raw JSON value of [configs].
     *
     * Unlike [configs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("configs")
    @ExcludeMissing
    fun _configs(): JsonField<List<BetaManagedAgentsAgentToolConfig>> = configs

    /**
     * Returns the raw JSON value of [defaultConfig].
     *
     * Unlike [defaultConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("default_config")
    @ExcludeMissing
    fun _defaultConfig(): JsonField<BetaManagedAgentsAgentToolsetDefaultConfig> = defaultConfig

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
         * [BetaManagedAgentsAgentToolset20260401].
         *
         * The following fields are required:
         * ```java
         * .configs()
         * .defaultConfig()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentToolset20260401]. */
    class Builder internal constructor() {

        private var configs: JsonField<MutableList<BetaManagedAgentsAgentToolConfig>>? = null
        private var defaultConfig: JsonField<BetaManagedAgentsAgentToolsetDefaultConfig>? = null
        private var type: JsonValue = JsonValue.from("agent_toolset_20260401")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentToolset20260401: BetaManagedAgentsAgentToolset20260401
        ) = apply {
            configs =
                betaManagedAgentsAgentToolset20260401.configs
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            defaultConfig = betaManagedAgentsAgentToolset20260401.defaultConfig
            type = betaManagedAgentsAgentToolset20260401.type
            additionalProperties =
                betaManagedAgentsAgentToolset20260401.additionalProperties.toMutableMap()
        }

        fun configs(configs: List<BetaManagedAgentsAgentToolConfig>) =
            configs(JsonField.of(configs))

        /**
         * Sets [Builder.configs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.configs] with a well-typed
         * `List<BetaManagedAgentsAgentToolConfig>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun configs(configs: JsonField<List<BetaManagedAgentsAgentToolConfig>>) = apply {
            this.configs = configs.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaManagedAgentsAgentToolConfig] to [configs].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addConfig(config: BetaManagedAgentsAgentToolConfig) = apply {
            configs =
                (configs ?: JsonField.of(mutableListOf())).also {
                    checkKnown("configs", it).add(config)
                }
        }

        /** Alias for calling [addConfig] with `BetaManagedAgentsAgentToolConfig.ofBash(bash)`. */
        fun addConfig(bash: BetaManagedAgentsBashToolConfig) =
            addConfig(BetaManagedAgentsAgentToolConfig.ofBash(bash))

        /** Alias for calling [addConfig] with `BetaManagedAgentsAgentToolConfig.ofEdit(edit)`. */
        fun addConfig(edit: BetaManagedAgentsEditToolConfig) =
            addConfig(BetaManagedAgentsAgentToolConfig.ofEdit(edit))

        /** Alias for calling [addConfig] with `BetaManagedAgentsAgentToolConfig.ofRead(read)`. */
        fun addConfig(read: BetaManagedAgentsReadToolConfig) =
            addConfig(BetaManagedAgentsAgentToolConfig.ofRead(read))

        /** Alias for calling [addConfig] with `BetaManagedAgentsAgentToolConfig.ofWrite(write)`. */
        fun addConfig(write: BetaManagedAgentsWriteToolConfig) =
            addConfig(BetaManagedAgentsAgentToolConfig.ofWrite(write))

        /** Alias for calling [addConfig] with `BetaManagedAgentsAgentToolConfig.ofGlob(glob)`. */
        fun addConfig(glob: BetaManagedAgentsGlobToolConfig) =
            addConfig(BetaManagedAgentsAgentToolConfig.ofGlob(glob))

        /** Alias for calling [addConfig] with `BetaManagedAgentsAgentToolConfig.ofGrep(grep)`. */
        fun addConfig(grep: BetaManagedAgentsGrepToolConfig) =
            addConfig(BetaManagedAgentsAgentToolConfig.ofGrep(grep))

        /**
         * Alias for calling [addConfig] with
         * `BetaManagedAgentsAgentToolConfig.ofWebFetch(webFetch)`.
         */
        fun addConfig(webFetch: BetaManagedAgentsWebFetchToolConfig) =
            addConfig(BetaManagedAgentsAgentToolConfig.ofWebFetch(webFetch))

        /**
         * Alias for calling [addConfig] with
         * `BetaManagedAgentsAgentToolConfig.ofWebSearch(webSearch)`.
         */
        fun addConfig(webSearch: BetaManagedAgentsWebSearchToolConfig) =
            addConfig(BetaManagedAgentsAgentToolConfig.ofWebSearch(webSearch))

        /** Resolved default configuration for agent tools. */
        fun defaultConfig(defaultConfig: BetaManagedAgentsAgentToolsetDefaultConfig) =
            defaultConfig(JsonField.of(defaultConfig))

        /**
         * Sets [Builder.defaultConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.defaultConfig] with a well-typed
         * [BetaManagedAgentsAgentToolsetDefaultConfig] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun defaultConfig(defaultConfig: JsonField<BetaManagedAgentsAgentToolsetDefaultConfig>) =
            apply {
                this.defaultConfig = defaultConfig
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
         * Returns an immutable instance of [BetaManagedAgentsAgentToolset20260401].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .configs()
         * .defaultConfig()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentToolset20260401 =
            BetaManagedAgentsAgentToolset20260401(
                checkRequired("configs", configs).map { it.toImmutable() },
                checkRequired("defaultConfig", defaultConfig),
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
    fun validate(): BetaManagedAgentsAgentToolset20260401 = apply {
        if (validated) {
            return@apply
        }

        configs().forEach { it.validate() }
        defaultConfig().validate()
        _type().let {
            if (it != JsonValue.from("agent_toolset_20260401")) {
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
        (configs.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (defaultConfig.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("agent_toolset_20260401")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentToolset20260401 &&
            configs == other.configs &&
            defaultConfig == other.defaultConfig &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(configs, defaultConfig, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentToolset20260401{configs=$configs, defaultConfig=$defaultConfig, type=$type, additionalProperties=$additionalProperties}"
}
