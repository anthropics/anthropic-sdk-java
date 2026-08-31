// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
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
 * The browser toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the browser
 * tool family. The model is served the family's tool with any members disabled via ``configs``
 * removed from its schema.
 */
class BetaBrowserToolset20260801
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val configs: JsonField<BetaBrowserToolsetConfigs>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("configs")
        @ExcludeMissing
        configs: JsonField<BetaBrowserToolsetConfigs> = JsonMissing.of(),
    ) : this(type, cacheControl, configs, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("browser_toolset_20260801")
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
     * Per-member configuration for ``browser_toolset_20260801``: one optional field per member
     * tool, keyed by the member name — the same name the member's ``tool_use`` blocks carry. Every
     * member is an accepted key, and a member's defaults apply wherever its key is absent. Unknown
     * keys are rejected: the field set is this toolset version's complete member set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun configs(): Optional<BetaBrowserToolsetConfigs> = configs.getOptional("configs")

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [configs].
     *
     * Unlike [configs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("configs")
    @ExcludeMissing
    fun _configs(): JsonField<BetaBrowserToolsetConfigs> = configs

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
         * Returns a mutable builder for constructing an instance of [BetaBrowserToolset20260801].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaBrowserToolset20260801]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("browser_toolset_20260801")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var configs: JsonField<BetaBrowserToolsetConfigs> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaBrowserToolset20260801: BetaBrowserToolset20260801) = apply {
            type = betaBrowserToolset20260801.type
            cacheControl = betaBrowserToolset20260801.cacheControl
            configs = betaBrowserToolset20260801.configs
            additionalProperties = betaBrowserToolset20260801.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("browser_toolset_20260801")
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

        /**
         * Per-member configuration for ``browser_toolset_20260801``: one optional field per member
         * tool, keyed by the member name — the same name the member's ``tool_use`` blocks carry.
         * Every member is an accepted key, and a member's defaults apply wherever its key is
         * absent. Unknown keys are rejected: the field set is this toolset version's complete
         * member set.
         */
        fun configs(configs: BetaBrowserToolsetConfigs?) = configs(JsonField.ofNullable(configs))

        /** Alias for calling [Builder.configs] with `configs.orElse(null)`. */
        fun configs(configs: Optional<BetaBrowserToolsetConfigs>) = configs(configs.getOrNull())

        /**
         * Sets [Builder.configs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.configs] with a well-typed [BetaBrowserToolsetConfigs]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun configs(configs: JsonField<BetaBrowserToolsetConfigs>) = apply {
            this.configs = configs
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
         * Returns an immutable instance of [BetaBrowserToolset20260801].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaBrowserToolset20260801 =
            BetaBrowserToolset20260801(
                type,
                cacheControl,
                configs,
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
    fun validate(): BetaBrowserToolset20260801 = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("browser_toolset_20260801")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        configs().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("browser_toolset_20260801")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (configs.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBrowserToolset20260801 &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            configs == other.configs &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, cacheControl, configs, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBrowserToolset20260801{type=$type, cacheControl=$cacheControl, configs=$configs, additionalProperties=$additionalProperties}"
}
