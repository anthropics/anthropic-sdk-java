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
import kotlin.jvm.optionals.getOrNull

class BetaManagedAgentsAgentToolset20260401
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val configs: JsonField<List<BetaManagedAgentsAgentToolConfig>>,
    private val defaultConfig: JsonField<BetaManagedAgentsAgentToolsetDefaultConfig>,
    private val type: JsonField<Type>,
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
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
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
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

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

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentToolset20260401]. */
    class Builder internal constructor() {

        private var configs: JsonField<MutableList<BetaManagedAgentsAgentToolConfig>>? = null
        private var defaultConfig: JsonField<BetaManagedAgentsAgentToolsetDefaultConfig>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentToolset20260401: BetaManagedAgentsAgentToolset20260401
        ) = apply {
            configs = betaManagedAgentsAgentToolset20260401.configs.map { it.toMutableList() }
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

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

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
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentToolset20260401 =
            BetaManagedAgentsAgentToolset20260401(
                checkRequired("configs", configs).map { it.toImmutable() },
                checkRequired("defaultConfig", defaultConfig),
                checkRequired("type", type),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaManagedAgentsAgentToolset20260401 = apply {
        if (validated) {
            return@apply
        }

        configs().forEach { it.validate() }
        defaultConfig().validate()
        type().validate()
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
            (type.asKnown().getOrNull()?.validity() ?: 0)

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

            @JvmField val AGENT_TOOLSET_20260401 = of("agent_toolset_20260401")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            AGENT_TOOLSET_20260401
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
            AGENT_TOOLSET_20260401,
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
                AGENT_TOOLSET_20260401 -> Value.AGENT_TOOLSET_20260401
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
                AGENT_TOOLSET_20260401 -> Known.AGENT_TOOLSET_20260401
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
