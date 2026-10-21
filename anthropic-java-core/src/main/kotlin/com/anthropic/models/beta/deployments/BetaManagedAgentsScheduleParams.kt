package com.anthropic.models.beta.deployments

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

/** A recurring schedule. Discriminated union — only cron is supported currently. */
class BetaManagedAgentsScheduleParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val expression: JsonField<String>,
    private val timezone: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("expression")
        @ExcludeMissing
        expression: JsonField<String> = JsonMissing.of(),
        @JsonProperty("timezone") @ExcludeMissing timezone: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(expression, timezone, type, mutableMapOf())

    /**
     * 5-field POSIX cron expression: minute hour day-of-month month day-of-week (e.g., "0 9 * *
     * 1-5" for weekdays at 9am). Day-of-week is 0-7 where 0 and 7 both mean Sunday. Extended cron
     * syntax - seconds or year fields, and the special characters L, W, #, and ? - is not
     * supported, nor are predefined shortcuts (@daily).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun expression(): String = expression.getRequired("expression")

    /**
     * Required. IANA timezone identifier (e.g., "America/Los_Angeles", "UTC"). Validated against
     * the IANA timezone database.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun timezone(): String = timezone.getRequired("timezone")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("cron")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [expression].
     *
     * Unlike [expression], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expression") @ExcludeMissing fun _expression(): JsonField<String> = expression

    /**
     * Returns the raw JSON value of [timezone].
     *
     * Unlike [timezone], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("timezone") @ExcludeMissing fun _timezone(): JsonField<String> = timezone

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
         * [BetaManagedAgentsScheduleParams].
         *
         * The following fields are required:
         * ```java
         * .expression()
         * .timezone()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsScheduleParams]. */
    class Builder internal constructor() {

        private var expression: JsonField<String>? = null
        private var timezone: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("cron")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsScheduleParams: BetaManagedAgentsScheduleParams) =
            apply {
                expression = betaManagedAgentsScheduleParams.expression
                timezone = betaManagedAgentsScheduleParams.timezone
                type = betaManagedAgentsScheduleParams.type
                additionalProperties =
                    betaManagedAgentsScheduleParams.additionalProperties.toMutableMap()
            }

        /**
         * 5-field POSIX cron expression: minute hour day-of-month month day-of-week (e.g., "0 9 * *
         * 1-5" for weekdays at 9am). Day-of-week is 0-7 where 0 and 7 both mean Sunday. Extended
         * cron syntax - seconds or year fields, and the special characters L, W, #, and ? - is not
         * supported, nor are predefined shortcuts (@daily).
         */
        fun expression(expression: String) = expression(JsonField.of(expression))

        /**
         * Sets [Builder.expression] to an arbitrary JSON value.
         *
         * You should usually call [Builder.expression] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun expression(expression: JsonField<String>) = apply { this.expression = expression }

        /**
         * Required. IANA timezone identifier (e.g., "America/Los_Angeles", "UTC"). Validated
         * against the IANA timezone database.
         */
        fun timezone(timezone: String) = timezone(JsonField.of(timezone))

        /**
         * Sets [Builder.timezone] to an arbitrary JSON value.
         *
         * You should usually call [Builder.timezone] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun timezone(timezone: JsonField<String>) = apply { this.timezone = timezone }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("cron")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaManagedAgentsScheduleParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .expression()
         * .timezone()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsScheduleParams =
            BetaManagedAgentsScheduleParams(
                checkRequired("expression", expression),
                checkRequired("timezone", timezone),
                type,
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
    fun validate(): BetaManagedAgentsScheduleParams = apply {
        if (validated) {
            return@apply
        }

        expression()
        timezone()
        _type().let {
            if (it != JsonValue.from("cron")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (if (expression.asKnown().isPresent) 1 else 0) +
            (if (timezone.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("cron")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsScheduleParams &&
            expression == other.expression &&
            timezone == other.timezone &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(expression, timezone, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsScheduleParams{expression=$expression, timezone=$timezone, type=$type, additionalProperties=$additionalProperties}"
}
