// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.ratelimits

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

class BetaWorkspaceRateLimitValue
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val orgLimit: JsonField<Long>,
    private val type: JsonField<String>,
    private val value: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("org_limit") @ExcludeMissing orgLimit: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<String> = JsonMissing.of(),
        @JsonProperty("value") @ExcludeMissing value: JsonField<Long> = JsonMissing.of(),
    ) : this(orgLimit, type, value, mutableMapOf())

    /**
     * The organization-level value for the same limiter type, for reference. `null` when the
     * organization has no limit configured for this limiter type.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun orgLimit(): Optional<Long> = orgLimit.getOptional("org_limit")

    /**
     * The limiter type (for example, `requests_per_minute` or `input_tokens_per_minute`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): String = type.getRequired("type")

    /**
     * The workspace-level override value for this limiter type.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun value(): Long = value.getRequired("value")

    /**
     * Returns the raw JSON value of [orgLimit].
     *
     * Unlike [orgLimit], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("org_limit") @ExcludeMissing fun _orgLimit(): JsonField<Long> = orgLimit

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

    /**
     * Returns the raw JSON value of [value].
     *
     * Unlike [value], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<Long> = value

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
         * Returns a mutable builder for constructing an instance of [BetaWorkspaceRateLimitValue].
         *
         * The following fields are required:
         * ```java
         * .orgLimit()
         * .type()
         * .value()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaWorkspaceRateLimitValue]. */
    class Builder internal constructor() {

        private var orgLimit: JsonField<Long>? = null
        private var type: JsonField<String>? = null
        private var value: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaWorkspaceRateLimitValue: BetaWorkspaceRateLimitValue) = apply {
            orgLimit = betaWorkspaceRateLimitValue.orgLimit
            type = betaWorkspaceRateLimitValue.type
            value = betaWorkspaceRateLimitValue.value
            additionalProperties = betaWorkspaceRateLimitValue.additionalProperties.toMutableMap()
        }

        /**
         * The organization-level value for the same limiter type, for reference. `null` when the
         * organization has no limit configured for this limiter type.
         */
        fun orgLimit(orgLimit: Long?) = orgLimit(JsonField.ofNullable(orgLimit))

        /**
         * Alias for [Builder.orgLimit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun orgLimit(orgLimit: Long) = orgLimit(orgLimit as Long?)

        /** Alias for calling [Builder.orgLimit] with `orgLimit.orElse(null)`. */
        fun orgLimit(orgLimit: Optional<Long>) = orgLimit(orgLimit.getOrNull())

        /**
         * Sets [Builder.orgLimit] to an arbitrary JSON value.
         *
         * You should usually call [Builder.orgLimit] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun orgLimit(orgLimit: JsonField<Long>) = apply { this.orgLimit = orgLimit }

        /** The limiter type (for example, `requests_per_minute` or `input_tokens_per_minute`). */
        fun type(type: String) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<String>) = apply { this.type = type }

        /** The workspace-level override value for this limiter type. */
        fun value(value: Long) = value(JsonField.of(value))

        /**
         * Sets [Builder.value] to an arbitrary JSON value.
         *
         * You should usually call [Builder.value] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun value(value: JsonField<Long>) = apply { this.value = value }

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
         * Returns an immutable instance of [BetaWorkspaceRateLimitValue].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .orgLimit()
         * .type()
         * .value()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaWorkspaceRateLimitValue =
            BetaWorkspaceRateLimitValue(
                checkRequired("orgLimit", orgLimit),
                checkRequired("type", type),
                checkRequired("value", value),
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
    fun validate(): BetaWorkspaceRateLimitValue = apply {
        if (validated) {
            return@apply
        }

        orgLimit()
        type()
        value()
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
        (if (orgLimit.asKnown().isPresent) 1 else 0) +
            (if (type.asKnown().isPresent) 1 else 0) +
            (if (value.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWorkspaceRateLimitValue &&
            orgLimit == other.orgLimit &&
            type == other.type &&
            value == other.value &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(orgLimit, type, value, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaWorkspaceRateLimitValue{orgLimit=$orgLimit, type=$type, value=$value, additionalProperties=$additionalProperties}"
}
