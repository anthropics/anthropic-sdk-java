// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** No reprice was applied; ``reason`` says why. */
class BetaFallbackCreditNotApplied
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val reason: JsonField<Reason>,
    private val type: JsonValue,
    private val removeToRedeem: JsonField<List<String>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("reason") @ExcludeMissing reason: JsonField<Reason> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("remove_to_redeem")
        @ExcludeMissing
        removeToRedeem: JsonField<List<String>> = JsonMissing.of(),
    ) : this(reason, type, removeToRedeem, mutableMapOf())

    /**
     * Why the reprice was not applied.
     *
     * A closed enum; additions to the redemption-check vocabulary arrive as deliberate schema
     * updates.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun reason(): Reason = reason.getRequired("reason")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("not_applied")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Request fields to remove before retrying, so the retry can redeem this token.
     *
     * Present exactly when `reason` is `variant_fields_present` — never null, never an empty array;
     * absent otherwise. Fields are named only from your own request, and only after the sealed
     * variant hash matched. A served best-effort retry has already been billed at normal price;
     * nothing redeems retroactively, but a corrected re-send inside the token's five-minute window
     * can still redeem.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun removeToRedeem(): Optional<List<String>> = removeToRedeem.getOptional("remove_to_redeem")

    /**
     * Returns the raw JSON value of [reason].
     *
     * Unlike [reason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("reason") @ExcludeMissing fun _reason(): JsonField<Reason> = reason

    /**
     * Returns the raw JSON value of [removeToRedeem].
     *
     * Unlike [removeToRedeem], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("remove_to_redeem")
    @ExcludeMissing
    fun _removeToRedeem(): JsonField<List<String>> = removeToRedeem

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
         * Returns a mutable builder for constructing an instance of [BetaFallbackCreditNotApplied].
         *
         * The following fields are required:
         * ```java
         * .reason()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaFallbackCreditNotApplied]. */
    class Builder internal constructor() {

        private var reason: JsonField<Reason>? = null
        private var type: JsonValue = JsonValue.from("not_applied")
        private var removeToRedeem: JsonField<MutableList<String>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaFallbackCreditNotApplied: BetaFallbackCreditNotApplied) = apply {
            reason = betaFallbackCreditNotApplied.reason
            type = betaFallbackCreditNotApplied.type
            removeToRedeem =
                betaFallbackCreditNotApplied.removeToRedeem
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties = betaFallbackCreditNotApplied.additionalProperties.toMutableMap()
        }

        /**
         * Why the reprice was not applied.
         *
         * A closed enum; additions to the redemption-check vocabulary arrive as deliberate schema
         * updates.
         */
        fun reason(reason: Reason) = reason(JsonField.of(reason))

        /**
         * Sets [Builder.reason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.reason] with a well-typed [Reason] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun reason(reason: JsonField<Reason>) = apply { this.reason = reason }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("not_applied")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Request fields to remove before retrying, so the retry can redeem this token.
         *
         * Present exactly when `reason` is `variant_fields_present` — never null, never an empty
         * array; absent otherwise. Fields are named only from your own request, and only after the
         * sealed variant hash matched. A served best-effort retry has already been billed at normal
         * price; nothing redeems retroactively, but a corrected re-send inside the token's
         * five-minute window can still redeem.
         */
        fun removeToRedeem(removeToRedeem: List<String>?) =
            removeToRedeem(JsonField.ofNullable(removeToRedeem))

        /** Alias for calling [Builder.removeToRedeem] with `removeToRedeem.orElse(null)`. */
        fun removeToRedeem(removeToRedeem: Optional<List<String>>) =
            removeToRedeem(removeToRedeem.getOrNull())

        /**
         * Sets [Builder.removeToRedeem] to an arbitrary JSON value.
         *
         * You should usually call [Builder.removeToRedeem] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun removeToRedeem(removeToRedeem: JsonField<List<String>>) = apply {
            this.removeToRedeem = removeToRedeem.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [Builder.removeToRedeem].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRemoveToRedeem(removeToRedeem: String) = apply {
            this.removeToRedeem =
                (this.removeToRedeem ?: JsonField.of(mutableListOf())).also {
                    checkKnown("removeToRedeem", it).add(removeToRedeem)
                }
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
         * Returns an immutable instance of [BetaFallbackCreditNotApplied].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .reason()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaFallbackCreditNotApplied =
            BetaFallbackCreditNotApplied(
                checkRequired("reason", reason),
                type,
                (removeToRedeem ?: JsonMissing.of()).map { it.toImmutable() },
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
    fun validate(): BetaFallbackCreditNotApplied = apply {
        if (validated) {
            return@apply
        }

        reason().validate()
        _type().let {
            if (it != JsonValue.from("not_applied")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        removeToRedeem()
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
        (reason.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("not_applied")) 1 else 0 } +
            (removeToRedeem.asKnown().getOrNull()?.size ?: 0)

    /**
     * Why the reprice was not applied.
     *
     * A closed enum; additions to the redemption-check vocabulary arrive as deliberate schema
     * updates.
     */
    class Reason @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val BODY_MISMATCH = of("body_mismatch")

            @JvmField val CONTINUATION_EXCLUDED = of("continuation_excluded")

            @JvmField val CONTINUATION_ONLY = of("continuation_only")

            @JvmField val EXPIRED = of("expired")

            @JvmField val INVALID_TARGET_MODEL = of("invalid_target_model")

            @JvmField val NOT_ENABLED = of("not_enabled")

            @JvmField val REPRICE_UNAVAILABLE = of("reprice_unavailable")

            @JvmField val TEMPORARILY_UNAVAILABLE = of("temporarily_unavailable")

            @JvmField val VARIANT_FIELDS_PRESENT = of("variant_fields_present")

            @JvmField val WRONG_ORGANIZATION = of("wrong_organization")

            @JvmField val WRONG_PLATFORM = of("wrong_platform")

            @JvmField val WRONG_WORKSPACE = of("wrong_workspace")

            @JvmStatic fun of(value: String) = Reason(JsonField.of(value))
        }

        /** An enum containing [Reason]'s known values. */
        enum class Known {
            BODY_MISMATCH,
            CONTINUATION_EXCLUDED,
            CONTINUATION_ONLY,
            EXPIRED,
            INVALID_TARGET_MODEL,
            NOT_ENABLED,
            REPRICE_UNAVAILABLE,
            TEMPORARILY_UNAVAILABLE,
            VARIANT_FIELDS_PRESENT,
            WRONG_ORGANIZATION,
            WRONG_PLATFORM,
            WRONG_WORKSPACE,
        }

        /**
         * An enum containing [Reason]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Reason] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            BODY_MISMATCH,
            CONTINUATION_EXCLUDED,
            CONTINUATION_ONLY,
            EXPIRED,
            INVALID_TARGET_MODEL,
            NOT_ENABLED,
            REPRICE_UNAVAILABLE,
            TEMPORARILY_UNAVAILABLE,
            VARIANT_FIELDS_PRESENT,
            WRONG_ORGANIZATION,
            WRONG_PLATFORM,
            WRONG_WORKSPACE,
            /** An enum member indicating that [Reason] was instantiated with an unknown value. */
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
                BODY_MISMATCH -> Value.BODY_MISMATCH
                CONTINUATION_EXCLUDED -> Value.CONTINUATION_EXCLUDED
                CONTINUATION_ONLY -> Value.CONTINUATION_ONLY
                EXPIRED -> Value.EXPIRED
                INVALID_TARGET_MODEL -> Value.INVALID_TARGET_MODEL
                NOT_ENABLED -> Value.NOT_ENABLED
                REPRICE_UNAVAILABLE -> Value.REPRICE_UNAVAILABLE
                TEMPORARILY_UNAVAILABLE -> Value.TEMPORARILY_UNAVAILABLE
                VARIANT_FIELDS_PRESENT -> Value.VARIANT_FIELDS_PRESENT
                WRONG_ORGANIZATION -> Value.WRONG_ORGANIZATION
                WRONG_PLATFORM -> Value.WRONG_PLATFORM
                WRONG_WORKSPACE -> Value.WRONG_WORKSPACE
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
                BODY_MISMATCH -> Known.BODY_MISMATCH
                CONTINUATION_EXCLUDED -> Known.CONTINUATION_EXCLUDED
                CONTINUATION_ONLY -> Known.CONTINUATION_ONLY
                EXPIRED -> Known.EXPIRED
                INVALID_TARGET_MODEL -> Known.INVALID_TARGET_MODEL
                NOT_ENABLED -> Known.NOT_ENABLED
                REPRICE_UNAVAILABLE -> Known.REPRICE_UNAVAILABLE
                TEMPORARILY_UNAVAILABLE -> Known.TEMPORARILY_UNAVAILABLE
                VARIANT_FIELDS_PRESENT -> Known.VARIANT_FIELDS_PRESENT
                WRONG_ORGANIZATION -> Known.WRONG_ORGANIZATION
                WRONG_PLATFORM -> Known.WRONG_PLATFORM
                WRONG_WORKSPACE -> Known.WRONG_WORKSPACE
                else -> throw AnthropicInvalidDataException("Unknown Reason: $value")
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
        fun validate(): Reason = apply {
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

            return other is Reason && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaFallbackCreditNotApplied &&
            reason == other.reason &&
            type == other.type &&
            removeToRedeem == other.removeToRedeem &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(reason, type, removeToRedeem, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaFallbackCreditNotApplied{reason=$reason, type=$type, removeToRedeem=$removeToRedeem, additionalProperties=$additionalProperties}"
}
