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

/** Declares which organization is billed for the request. */
class BetaBillingPolicy
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val billTo: JsonField<BetaBillTo>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("bill_to") @ExcludeMissing billTo: JsonField<BetaBillTo> = JsonMissing.of(),
    ) : this(type, billTo, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("billing_policy")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Who is billed. `self`, the default, bills your organization, as if `billing_policy` were
     * omitted. `user_profile` bills the customer behind the user profile the request names; if that
     * customer has not linked an Anthropic organization to the profile, or has not allowed your
     * organization to bill it, the request is rejected rather than billed to you.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun billTo(): Optional<BetaBillTo> = billTo.getOptional("bill_to")

    /**
     * Returns the raw JSON value of [billTo].
     *
     * Unlike [billTo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("bill_to") @ExcludeMissing fun _billTo(): JsonField<BetaBillTo> = billTo

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

        /** Returns a mutable builder for constructing an instance of [BetaBillingPolicy]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaBillingPolicy]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("billing_policy")
        private var billTo: JsonField<BetaBillTo> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaBillingPolicy: BetaBillingPolicy) = apply {
            type = betaBillingPolicy.type
            billTo = betaBillingPolicy.billTo
            additionalProperties = betaBillingPolicy.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("billing_policy")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Who is billed. `self`, the default, bills your organization, as if `billing_policy` were
         * omitted. `user_profile` bills the customer behind the user profile the request names; if
         * that customer has not linked an Anthropic organization to the profile, or has not allowed
         * your organization to bill it, the request is rejected rather than billed to you.
         */
        fun billTo(billTo: BetaBillTo) = billTo(JsonField.of(billTo))

        /**
         * Sets [Builder.billTo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.billTo] with a well-typed [BetaBillTo] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun billTo(billTo: JsonField<BetaBillTo>) = apply { this.billTo = billTo }

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
         * Returns an immutable instance of [BetaBillingPolicy].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaBillingPolicy =
            BetaBillingPolicy(type, billTo, additionalProperties.toMutableMap())
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
    fun validate(): BetaBillingPolicy = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("billing_policy")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        billTo().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("billing_policy")) 1 else 0 } +
            (billTo.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBillingPolicy &&
            type == other.type &&
            billTo == other.billTo &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, billTo, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBillingPolicy{type=$type, billTo=$billTo, additionalProperties=$additionalProperties}"
}
