// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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
 * The computer toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the
 * computer tool family. The model is served the family's tool with any members disabled via
 * ``configs`` removed from its schema. Every member is enabled by default, zoom included. The
 * single-tool options ``display_number`` and ``enable_zoom`` are not fields of a toolset entry — it
 * carries only ``type``, ``configs``, and ``cache_control``; zoom is controlled via
 * ``configs.zoom.enabled``.
 */
class ComputerToolset20260801
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val cacheControl: JsonField<CacheControlEphemeral>,
    private val configs: JsonField<ComputerToolsetConfigs>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("configs")
        @ExcludeMissing
        configs: JsonField<ComputerToolsetConfigs> = JsonMissing.of(),
    ) : this(type, cacheControl, configs, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("computer_toolset_20260801")
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
    fun cacheControl(): Optional<CacheControlEphemeral> = cacheControl.getOptional("cache_control")

    /**
     * Per-member configuration for ``computer_toolset_20260801``: one optional field per member
     * tool, keyed by the member name — the same name the member's ``tool_use`` blocks carry. Every
     * member is an accepted key, and a member's defaults apply wherever its key is absent. Unknown
     * keys are rejected: the field set is this toolset version's complete member set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun configs(): Optional<ComputerToolsetConfigs> = configs.getOptional("configs")

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [configs].
     *
     * Unlike [configs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("configs")
    @ExcludeMissing
    fun _configs(): JsonField<ComputerToolsetConfigs> = configs

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

        /** Returns a mutable builder for constructing an instance of [ComputerToolset20260801]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ComputerToolset20260801]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("computer_toolset_20260801")
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var configs: JsonField<ComputerToolsetConfigs> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(computerToolset20260801: ComputerToolset20260801) = apply {
            type = computerToolset20260801.type
            cacheControl = computerToolset20260801.cacheControl
            configs = computerToolset20260801.configs
            additionalProperties = computerToolset20260801.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("computer_toolset_20260801")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: CacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<CacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed [CacheControlEphemeral]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        /**
         * Per-member configuration for ``computer_toolset_20260801``: one optional field per member
         * tool, keyed by the member name — the same name the member's ``tool_use`` blocks carry.
         * Every member is an accepted key, and a member's defaults apply wherever its key is
         * absent. Unknown keys are rejected: the field set is this toolset version's complete
         * member set.
         */
        fun configs(configs: ComputerToolsetConfigs?) = configs(JsonField.ofNullable(configs))

        /** Alias for calling [Builder.configs] with `configs.orElse(null)`. */
        fun configs(configs: Optional<ComputerToolsetConfigs>) = configs(configs.getOrNull())

        /**
         * Sets [Builder.configs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.configs] with a well-typed [ComputerToolsetConfigs]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun configs(configs: JsonField<ComputerToolsetConfigs>) = apply { this.configs = configs }

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
         * Returns an immutable instance of [ComputerToolset20260801].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ComputerToolset20260801 =
            ComputerToolset20260801(
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
    fun validate(): ComputerToolset20260801 = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("computer_toolset_20260801")) {
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
        type.let { if (it == JsonValue.from("computer_toolset_20260801")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (configs.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ComputerToolset20260801 &&
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
        "ComputerToolset20260801{type=$type, cacheControl=$cacheControl, configs=$configs, additionalProperties=$additionalProperties}"
}
