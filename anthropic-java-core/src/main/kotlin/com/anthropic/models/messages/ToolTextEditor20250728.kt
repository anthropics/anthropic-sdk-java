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

class ToolTextEditor20250728
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val name: JsonValue,
    private val type: JsonValue,
    private val cacheControl: JsonField<CacheControlEphemeral>,
    private val maxCharacters: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("max_characters")
        @ExcludeMissing
        maxCharacters: JsonField<Long> = JsonMissing.of(),
    ) : this(name, type, cacheControl, maxCharacters, mutableMapOf())

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in `tool_use` blocks.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("str_replace_based_edit_tool")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("text_editor_20250728")
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
     * Maximum number of characters to display when viewing a file. If not specified, defaults to
     * displaying the full file.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxCharacters(): Optional<Long> = maxCharacters.getOptional("max_characters")

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [maxCharacters].
     *
     * Unlike [maxCharacters], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max_characters")
    @ExcludeMissing
    fun _maxCharacters(): JsonField<Long> = maxCharacters

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

        /** Returns a mutable builder for constructing an instance of [ToolTextEditor20250728]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ToolTextEditor20250728]. */
    class Builder internal constructor() {

        private var name: JsonValue = JsonValue.from("str_replace_based_edit_tool")
        private var type: JsonValue = JsonValue.from("text_editor_20250728")
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var maxCharacters: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(toolTextEditor20250728: ToolTextEditor20250728) = apply {
            name = toolTextEditor20250728.name
            type = toolTextEditor20250728.type
            cacheControl = toolTextEditor20250728.cacheControl
            maxCharacters = toolTextEditor20250728.maxCharacters
            additionalProperties = toolTextEditor20250728.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("str_replace_based_edit_tool")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun name(name: JsonValue) = apply { this.name = name }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("text_editor_20250728")
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
         * Maximum number of characters to display when viewing a file. If not specified, defaults
         * to displaying the full file.
         */
        fun maxCharacters(maxCharacters: Long?) = maxCharacters(JsonField.ofNullable(maxCharacters))

        /**
         * Alias for [Builder.maxCharacters].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxCharacters(maxCharacters: Long) = maxCharacters(maxCharacters as Long?)

        /** Alias for calling [Builder.maxCharacters] with `maxCharacters.orElse(null)`. */
        fun maxCharacters(maxCharacters: Optional<Long>) = maxCharacters(maxCharacters.getOrNull())

        /**
         * Sets [Builder.maxCharacters] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxCharacters] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun maxCharacters(maxCharacters: JsonField<Long>) = apply {
            this.maxCharacters = maxCharacters
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
         * Returns an immutable instance of [ToolTextEditor20250728].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ToolTextEditor20250728 =
            ToolTextEditor20250728(
                name,
                type,
                cacheControl,
                maxCharacters,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ToolTextEditor20250728 = apply {
        if (validated) {
            return@apply
        }

        _name().let {
            if (it != JsonValue.from("str_replace_based_edit_tool")) {
                throw AnthropicInvalidDataException("'name' is invalid, received $it")
            }
        }
        _type().let {
            if (it != JsonValue.from("text_editor_20250728")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        maxCharacters()
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
        name.let { if (it == JsonValue.from("str_replace_based_edit_tool")) 1 else 0 } +
            type.let { if (it == JsonValue.from("text_editor_20250728")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (if (maxCharacters.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ToolTextEditor20250728 &&
            name == other.name &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            maxCharacters == other.maxCharacters &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(name, type, cacheControl, maxCharacters, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ToolTextEditor20250728{name=$name, type=$type, cacheControl=$cacheControl, maxCharacters=$maxCharacters, additionalProperties=$additionalProperties}"
}
