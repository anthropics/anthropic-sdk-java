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

class BetaToolTextEditor20241022
private constructor(
    private val name: JsonValue,
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
    ) : this(name, type, cacheControl, mutableMapOf())

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("str_replace_editor")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("text_editor_20241022")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        cacheControl.getOptional("cache_control")

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

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
         * Returns a mutable builder for constructing an instance of [BetaToolTextEditor20241022].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaToolTextEditor20241022]. */
    class Builder internal constructor() {

        private var name: JsonValue = JsonValue.from("str_replace_editor")
        private var type: JsonValue = JsonValue.from("text_editor_20241022")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolTextEditor20241022: BetaToolTextEditor20241022) = apply {
            name = betaToolTextEditor20241022.name
            type = betaToolTextEditor20241022.type
            cacheControl = betaToolTextEditor20241022.cacheControl
            additionalProperties = betaToolTextEditor20241022.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("str_replace_editor")
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
         * JsonValue.from("text_editor_20241022")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaToolTextEditor20241022].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaToolTextEditor20241022 =
            BetaToolTextEditor20241022(
                name,
                type,
                cacheControl,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaToolTextEditor20241022 = apply {
        if (validated) {
            return@apply
        }

        _name().let {
            if (it != JsonValue.from("str_replace_editor")) {
                throw AnthropicInvalidDataException("'name' is invalid, received $it")
            }
        }
        _type().let {
            if (it != JsonValue.from("text_editor_20241022")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
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
        name.let { if (it == JsonValue.from("str_replace_editor")) 1 else 0 } +
            type.let { if (it == JsonValue.from("text_editor_20241022")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaToolTextEditor20241022 && name == other.name && type == other.type && cacheControl == other.cacheControl && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(name, type, cacheControl, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolTextEditor20241022{name=$name, type=$type, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
}
