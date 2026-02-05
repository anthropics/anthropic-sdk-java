// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.Enum
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
    private val effort: JsonField<Effort>,
    private val format: JsonField<JsonOutputFormat>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("effort") @ExcludeMissing effort: JsonField<Effort> = JsonMissing.of(),
        @JsonProperty("format")
        @ExcludeMissing
        format: JsonField<JsonOutputFormat> = JsonMissing.of(),
    ) : this(effort, format, mutableMapOf())

    /**
     * All possible effort levels.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun effort(): Optional<Effort> = effort.getOptional("effort")

    /**
     * A schema to specify Claude's output format in responses. See
     * [structured outputs](https://platform.claude.com/docs/en/build-with-claude/structured-outputs)
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun format(): Optional<JsonOutputFormat> = format.getOptional("format")

    /**
     * Returns the raw JSON value of [effort].
     *
     * Unlike [effort], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("effort") @ExcludeMissing fun _effort(): JsonField<Effort> = effort

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

        private var effort: JsonField<Effort> = JsonMissing.of()
        private var format: JsonField<JsonOutputFormat> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(outputConfig: OutputConfig) = apply {
            effort = outputConfig.effort
            format = outputConfig.format
            additionalProperties = outputConfig.additionalProperties.toMutableMap()
        }

        /** All possible effort levels. */
        fun effort(effort: Effort?) = effort(JsonField.ofNullable(effort))

        /** Alias for calling [Builder.effort] with `effort.orElse(null)`. */
        fun effort(effort: Optional<Effort>) = effort(effort.getOrNull())

        /**
         * Sets [Builder.effort] to an arbitrary JSON value.
         *
         * You should usually call [Builder.effort] with a well-typed [Effort] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun effort(effort: JsonField<Effort>) = apply { this.effort = effort }

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
        fun build(): OutputConfig =
            OutputConfig(effort, format, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): OutputConfig = apply {
        if (validated) {
            return@apply
        }

        effort().ifPresent { it.validate() }
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
    @JvmSynthetic
    internal fun validity(): Int =
        (effort.asKnown().getOrNull()?.validity() ?: 0) +
            (format.asKnown().getOrNull()?.validity() ?: 0)

    /** All possible effort levels. */
    class Effort @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val LOW = of("low")

            @JvmField val MEDIUM = of("medium")

            @JvmField val HIGH = of("high")

            @JvmField val MAX = of("max")

            @JvmStatic fun of(value: String) = Effort(JsonField.of(value))
        }

        /** An enum containing [Effort]'s known values. */
        enum class Known {
            LOW,
            MEDIUM,
            HIGH,
            MAX,
        }

        /**
         * An enum containing [Effort]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Effort] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            LOW,
            MEDIUM,
            HIGH,
            MAX,
            /** An enum member indicating that [Effort] was instantiated with an unknown value. */
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
                LOW -> Value.LOW
                MEDIUM -> Value.MEDIUM
                HIGH -> Value.HIGH
                MAX -> Value.MAX
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
                LOW -> Known.LOW
                MEDIUM -> Known.MEDIUM
                HIGH -> Known.HIGH
                MAX -> Known.MAX
                else -> throw AnthropicInvalidDataException("Unknown Effort: $value")
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

        fun validate(): Effort = apply {
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

            return other is Effort && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is OutputConfig &&
            effort == other.effort &&
            format == other.format &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(effort, format, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "OutputConfig{effort=$effort, format=$format, additionalProperties=$additionalProperties}"
}
