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

class OutputConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val format: JsonField<JsonOutputFormat>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("format")
        @ExcludeMissing
        format: JsonField<JsonOutputFormat> = JsonMissing.of()
    ) : this(format, mutableMapOf())

    /**
     * A schema to specify Claude's output format in responses. See
     * [structured outputs](https://platform.claude.com/docs/en/build-with-claude/structured-outputs)
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun format(): Optional<JsonOutputFormat> = format.getOptional("format")

    /**
     * Returns the raw JSON value of [format].
     *
     * Unlike [format], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("format") @ExcludeMissing fun _format(): JsonField<JsonOutputFormat> = format

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

        /** Returns a mutable builder for constructing an instance of [OutputConfig]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [OutputConfig]. */
    class Builder internal constructor() {

        private var format: JsonField<JsonOutputFormat> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(outputConfig: OutputConfig) = apply {
            format = outputConfig.format
            additionalProperties = outputConfig.additionalProperties.toMutableMap()
        }

        /**
         * A schema to specify Claude's output format in responses. See
         * [structured outputs](https://platform.claude.com/docs/en/build-with-claude/structured-outputs)
         */
        fun format(format: JsonOutputFormat?) = format(JsonField.ofNullable(format))

        /** Alias for calling [Builder.format] with `format.orElse(null)`. */
        fun format(format: Optional<JsonOutputFormat>) = format(format.getOrNull())

        /**
         * Sets [Builder.format] to an arbitrary JSON value.
         *
         * You should usually call [Builder.format] with a well-typed [JsonOutputFormat] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun format(format: JsonField<JsonOutputFormat>) = apply { this.format = format }

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
         * Returns an immutable instance of [OutputConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): OutputConfig = OutputConfig(format, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): OutputConfig = apply {
        if (validated) {
            return@apply
        }

        format().ifPresent { it.validate() }
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
    @JvmSynthetic internal fun validity(): Int = (format.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is OutputConfig &&
            format == other.format &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(format, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "OutputConfig{format=$format, additionalProperties=$additionalProperties}"
}
