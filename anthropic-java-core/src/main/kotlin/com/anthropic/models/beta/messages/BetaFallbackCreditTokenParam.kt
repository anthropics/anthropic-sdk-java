// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
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
 * Object form of ``fallback_credit_token``: the token plus a redemption mode.
 *
 * Requires ``anthropic-beta: fallback-credit-2026-07-01``; without that header the field accepts
 * the bare string only. The bare string and the mode-less object are equivalent (both select
 * ``strict``), so wrapping an existing token changes nothing by itself.
 */
class BetaFallbackCreditTokenParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val token: JsonField<String>,
    private val mode: JsonField<Mode>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("token") @ExcludeMissing token: JsonField<String> = JsonMissing.of(),
        @JsonProperty("mode") @ExcludeMissing mode: JsonField<Mode> = JsonMissing.of(),
    ) : this(token, mode, mutableMapOf())

    /**
     * The opaque `fallback_credit_token` from a prior refusal's `stop_details` — the same string
     * the bare-string form carries.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun token(): String = token.getRequired("token")

    /**
     * How a failing token affects the retry. `strict` (the default, and the bare-string behavior):
     * a failing redemption is a 400 and the retry is not served. `best_effort`: the retry is served
     * either way — a token-layer failure no longer rejects the request; the retry proceeds at
     * normal price and the outcome is reported on the response's `usage.fallback_credit`. Two
     * failures stay hard in both modes: a malformed token, and combining `fallback_credit_token`
     * with `fallbacks`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mode(): Optional<Mode> = mode.getOptional("mode")

    /**
     * Returns the raw JSON value of [token].
     *
     * Unlike [token], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("token") @ExcludeMissing fun _token(): JsonField<String> = token

    /**
     * Returns the raw JSON value of [mode].
     *
     * Unlike [mode], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mode") @ExcludeMissing fun _mode(): JsonField<Mode> = mode

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
         * Returns a mutable builder for constructing an instance of [BetaFallbackCreditTokenParam].
         *
         * The following fields are required:
         * ```java
         * .token()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaFallbackCreditTokenParam] with the required [token]
         * set to the given value.
         */
        @JvmStatic fun of(token: String) = builder().token(token).build()
    }

    /** A builder for [BetaFallbackCreditTokenParam]. */
    class Builder internal constructor() {

        private var token: JsonField<String>? = null
        private var mode: JsonField<Mode> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaFallbackCreditTokenParam: BetaFallbackCreditTokenParam) = apply {
            token = betaFallbackCreditTokenParam.token
            mode = betaFallbackCreditTokenParam.mode
            additionalProperties = betaFallbackCreditTokenParam.additionalProperties.toMutableMap()
        }

        /**
         * The opaque `fallback_credit_token` from a prior refusal's `stop_details` — the same
         * string the bare-string form carries.
         */
        fun token(token: String) = token(JsonField.of(token))

        /**
         * Sets [Builder.token] to an arbitrary JSON value.
         *
         * You should usually call [Builder.token] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun token(token: JsonField<String>) = apply { this.token = token }

        /**
         * How a failing token affects the retry. `strict` (the default, and the bare-string
         * behavior): a failing redemption is a 400 and the retry is not served. `best_effort`: the
         * retry is served either way — a token-layer failure no longer rejects the request; the
         * retry proceeds at normal price and the outcome is reported on the response's
         * `usage.fallback_credit`. Two failures stay hard in both modes: a malformed token, and
         * combining `fallback_credit_token` with `fallbacks`.
         */
        fun mode(mode: Mode) = mode(JsonField.of(mode))

        /**
         * Sets [Builder.mode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mode] with a well-typed [Mode] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun mode(mode: JsonField<Mode>) = apply { this.mode = mode }

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
         * Returns an immutable instance of [BetaFallbackCreditTokenParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .token()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaFallbackCreditTokenParam =
            BetaFallbackCreditTokenParam(
                checkRequired("token", token),
                mode,
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
    fun validate(): BetaFallbackCreditTokenParam = apply {
        if (validated) {
            return@apply
        }

        token()
        mode().ifPresent { it.validate() }
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
        (if (token.asKnown().isPresent) 1 else 0) + (mode.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * How a failing token affects the retry. `strict` (the default, and the bare-string behavior):
     * a failing redemption is a 400 and the retry is not served. `best_effort`: the retry is served
     * either way — a token-layer failure no longer rejects the request; the retry proceeds at
     * normal price and the outcome is reported on the response's `usage.fallback_credit`. Two
     * failures stay hard in both modes: a malformed token, and combining `fallback_credit_token`
     * with `fallbacks`.
     */
    class Mode @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val STRICT = of("strict")

            @JvmField val BEST_EFFORT = of("best_effort")

            @JvmStatic fun of(value: String) = Mode(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Mode =
                value.asString().getOrNull()?.let { of(it) } ?: Mode(value)
        }

        /** An enum containing [Mode]'s known values. */
        enum class Known {
            STRICT,
            BEST_EFFORT,
        }

        /**
         * An enum containing [Mode]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Mode] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            STRICT,
            BEST_EFFORT,
            /** An enum member indicating that [Mode] was instantiated with an unknown value. */
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
                STRICT -> Value.STRICT
                BEST_EFFORT -> Value.BEST_EFFORT
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
                STRICT -> Known.STRICT
                BEST_EFFORT -> Known.BEST_EFFORT
                else -> throw AnthropicInvalidDataException("Unknown Mode: $value")
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

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Mode = apply {
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

            return other is Mode && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaFallbackCreditTokenParam &&
            token == other.token &&
            mode == other.mode &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(token, mode, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaFallbackCreditTokenParam{token=$token, mode=$mode, additionalProperties=$additionalProperties}"
}
