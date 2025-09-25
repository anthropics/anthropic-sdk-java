// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaCacheControlEphemeral
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val ttl: JsonField<Ttl>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("ttl") @ExcludeMissing ttl: JsonField<Ttl> = JsonMissing.of(),
    ) : this(type, ttl, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("ephemeral")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The time-to-live for the cache control breakpoint.
     *
     * This may be one the following values:
     * - `5m`: 5 minutes
     * - `1h`: 1 hour
     *
     * Defaults to `5m`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun ttl(): Optional<Ttl> = ttl.getOptional("ttl")

    /**
     * Returns the raw JSON value of [ttl].
     *
     * Unlike [ttl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ttl") @ExcludeMissing fun _ttl(): JsonField<Ttl> = ttl

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
         * Returns a mutable builder for constructing an instance of [BetaCacheControlEphemeral].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCacheControlEphemeral]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("ephemeral")
        private var ttl: JsonField<Ttl> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCacheControlEphemeral: BetaCacheControlEphemeral) = apply {
            type = betaCacheControlEphemeral.type
            ttl = betaCacheControlEphemeral.ttl
            additionalProperties = betaCacheControlEphemeral.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("ephemeral")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * The time-to-live for the cache control breakpoint.
         *
         * This may be one the following values:
         * - `5m`: 5 minutes
         * - `1h`: 1 hour
         *
         * Defaults to `5m`.
         */
        fun ttl(ttl: Ttl) = ttl(JsonField.of(ttl))

        /**
         * Sets [Builder.ttl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.ttl] with a well-typed [Ttl] value instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun ttl(ttl: JsonField<Ttl>) = apply { this.ttl = ttl }

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
         * Returns an immutable instance of [BetaCacheControlEphemeral].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaCacheControlEphemeral =
            BetaCacheControlEphemeral(type, ttl, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): BetaCacheControlEphemeral = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("ephemeral")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        ttl().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("ephemeral")) 1 else 0 } +
            (ttl.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * The time-to-live for the cache control breakpoint.
     *
     * This may be one the following values:
     * - `5m`: 5 minutes
     * - `1h`: 1 hour
     *
     * Defaults to `5m`.
     */
    class Ttl @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val TTL_5M = of("5m")

            @JvmField val TTL_1H = of("1h")

            @JvmStatic fun of(value: String) = Ttl(JsonField.of(value))
        }

        /** An enum containing [Ttl]'s known values. */
        enum class Known {
            TTL_5M,
            TTL_1H,
        }

        /**
         * An enum containing [Ttl]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Ttl] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            TTL_5M,
            TTL_1H,
            /** An enum member indicating that [Ttl] was instantiated with an unknown value. */
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
                TTL_5M -> Value.TTL_5M
                TTL_1H -> Value.TTL_1H
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
                TTL_5M -> Known.TTL_5M
                TTL_1H -> Known.TTL_1H
                else -> throw AnthropicInvalidDataException("Unknown Ttl: $value")
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

        fun validate(): Ttl = apply {
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

            return other is Ttl && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaCacheControlEphemeral &&
            type == other.type &&
            ttl == other.ttl &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, ttl, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCacheControlEphemeral{type=$type, ttl=$ttl, additionalProperties=$additionalProperties}"
}
