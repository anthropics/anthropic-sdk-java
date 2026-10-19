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

class BetaWorkspaceRateLimitUpdateValue
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonField<BetaWorkspaceRateLimitType>,
    private val value: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type")
        @ExcludeMissing
        type: JsonField<BetaWorkspaceRateLimitType> = JsonMissing.of(),
        @JsonProperty("value") @ExcludeMissing value: JsonField<Long> = JsonMissing.of(),
    ) : this(type, value, mutableMapOf())

    /**
     * Rate limiter type.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): BetaWorkspaceRateLimitType = type.getRequired("type")

    /**
     * New limit value. Set to null to clear the workspace override and fall back to the
     * organization limit.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun value(): Optional<Long> = value.getOptional("value")

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<BetaWorkspaceRateLimitType> = type

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
         * Returns a mutable builder for constructing an instance of
         * [BetaWorkspaceRateLimitUpdateValue].
         *
         * The following fields are required:
         * ```java
         * .type()
         * .value()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaWorkspaceRateLimitUpdateValue]. */
    class Builder internal constructor() {

        private var type: JsonField<BetaWorkspaceRateLimitType>? = null
        private var value: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaWorkspaceRateLimitUpdateValue: BetaWorkspaceRateLimitUpdateValue) =
            apply {
                type = betaWorkspaceRateLimitUpdateValue.type
                value = betaWorkspaceRateLimitUpdateValue.value
                additionalProperties =
                    betaWorkspaceRateLimitUpdateValue.additionalProperties.toMutableMap()
            }

        /** Rate limiter type. */
        fun type(type: BetaWorkspaceRateLimitType) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [BetaWorkspaceRateLimitType]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun type(type: JsonField<BetaWorkspaceRateLimitType>) = apply { this.type = type }

        /**
         * New limit value. Set to null to clear the workspace override and fall back to the
         * organization limit.
         */
        fun value(value: Long?) = value(JsonField.ofNullable(value))

        /**
         * Alias for [Builder.value].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun value(value: Long) = value(value as Long?)

        /** Alias for calling [Builder.value] with `value.orElse(null)`. */
        fun value(value: Optional<Long>) = value(value.getOrNull())

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
         * Returns an immutable instance of [BetaWorkspaceRateLimitUpdateValue].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .type()
         * .value()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaWorkspaceRateLimitUpdateValue =
            BetaWorkspaceRateLimitUpdateValue(
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
    fun validate(): BetaWorkspaceRateLimitUpdateValue = apply {
        if (validated) {
            return@apply
        }

        type().validate()
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
        (type.asKnown().getOrNull()?.validity() ?: 0) + (if (value.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWorkspaceRateLimitUpdateValue &&
            type == other.type &&
            value == other.value &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, value, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaWorkspaceRateLimitUpdateValue{type=$type, value=$value, additionalProperties=$additionalProperties}"
}
