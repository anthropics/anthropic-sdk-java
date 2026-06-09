// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** 5-field POSIX cron schedule with computed runtime timestamps. */
class BetaManagedAgentsSchedule
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val expression: JsonField<String>,
    private val timezone: JsonField<String>,
    private val type: JsonField<Type>,
    private val lastRunAt: JsonField<OffsetDateTime>,
    private val upcomingRunsAt: JsonField<List<OffsetDateTime>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("expression")
        @ExcludeMissing
        expression: JsonField<String> = JsonMissing.of(),
        @JsonProperty("timezone") @ExcludeMissing timezone: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("last_run_at")
        @ExcludeMissing
        lastRunAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("upcoming_runs_at")
        @ExcludeMissing
        upcomingRunsAt: JsonField<List<OffsetDateTime>> = JsonMissing.of(),
    ) : this(expression, timezone, type, lastRunAt, upcomingRunsAt, mutableMapOf())

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
     * IANA timezone identifier (e.g., "America/Los_Angeles", "UTC").
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun timezone(): String = timezone.getRequired("timezone")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun lastRunAt(): Optional<OffsetDateTime> = lastRunAt.getOptional("last_run_at")

    /**
     * Up to 5 timestamps of upcoming cron occurrences. Non-empty for active and paused deployments
     * (reflects what the schedule would do if unpaused); empty once the deployment is archived
     * (`archived_at` set). Each fire is offset by a small per-schedule jitter, so a run will
     * actually start at or shortly after its listed time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun upcomingRunsAt(): Optional<List<OffsetDateTime>> =
        upcomingRunsAt.getOptional("upcoming_runs_at")

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

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [lastRunAt].
     *
     * Unlike [lastRunAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("last_run_at")
    @ExcludeMissing
    fun _lastRunAt(): JsonField<OffsetDateTime> = lastRunAt

    /**
     * Returns the raw JSON value of [upcomingRunsAt].
     *
     * Unlike [upcomingRunsAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("upcoming_runs_at")
    @ExcludeMissing
    fun _upcomingRunsAt(): JsonField<List<OffsetDateTime>> = upcomingRunsAt

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
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsSchedule].
         *
         * The following fields are required:
         * ```java
         * .expression()
         * .timezone()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSchedule]. */
    class Builder internal constructor() {

        private var expression: JsonField<String>? = null
        private var timezone: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var lastRunAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var upcomingRunsAt: JsonField<MutableList<OffsetDateTime>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsSchedule: BetaManagedAgentsSchedule) = apply {
            expression = betaManagedAgentsSchedule.expression
            timezone = betaManagedAgentsSchedule.timezone
            type = betaManagedAgentsSchedule.type
            lastRunAt = betaManagedAgentsSchedule.lastRunAt
            upcomingRunsAt =
                betaManagedAgentsSchedule.upcomingRunsAt
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties = betaManagedAgentsSchedule.additionalProperties.toMutableMap()
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

        /** IANA timezone identifier (e.g., "America/Los_Angeles", "UTC"). */
        fun timezone(timezone: String) = timezone(JsonField.of(timezone))

        /**
         * Sets [Builder.timezone] to an arbitrary JSON value.
         *
         * You should usually call [Builder.timezone] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun timezone(timezone: JsonField<String>) = apply { this.timezone = timezone }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** A timestamp in RFC 3339 format */
        fun lastRunAt(lastRunAt: OffsetDateTime?) = lastRunAt(JsonField.ofNullable(lastRunAt))

        /** Alias for calling [Builder.lastRunAt] with `lastRunAt.orElse(null)`. */
        fun lastRunAt(lastRunAt: Optional<OffsetDateTime>) = lastRunAt(lastRunAt.getOrNull())

        /**
         * Sets [Builder.lastRunAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastRunAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun lastRunAt(lastRunAt: JsonField<OffsetDateTime>) = apply { this.lastRunAt = lastRunAt }

        /**
         * Up to 5 timestamps of upcoming cron occurrences. Non-empty for active and paused
         * deployments (reflects what the schedule would do if unpaused); empty once the deployment
         * is archived (`archived_at` set). Each fire is offset by a small per-schedule jitter, so a
         * run will actually start at or shortly after its listed time.
         */
        fun upcomingRunsAt(upcomingRunsAt: List<OffsetDateTime>) =
            upcomingRunsAt(JsonField.of(upcomingRunsAt))

        /**
         * Sets [Builder.upcomingRunsAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.upcomingRunsAt] with a well-typed `List<OffsetDateTime>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun upcomingRunsAt(upcomingRunsAt: JsonField<List<OffsetDateTime>>) = apply {
            this.upcomingRunsAt = upcomingRunsAt.map { it.toMutableList() }
        }

        /**
         * Adds a single [OffsetDateTime] to [Builder.upcomingRunsAt].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addUpcomingRunsAt(upcomingRunsAt: OffsetDateTime) = apply {
            this.upcomingRunsAt =
                (this.upcomingRunsAt ?: JsonField.of(mutableListOf())).also {
                    checkKnown("upcomingRunsAt", it).add(upcomingRunsAt)
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
         * Returns an immutable instance of [BetaManagedAgentsSchedule].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .expression()
         * .timezone()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSchedule =
            BetaManagedAgentsSchedule(
                checkRequired("expression", expression),
                checkRequired("timezone", timezone),
                checkRequired("type", type),
                lastRunAt,
                (upcomingRunsAt ?: JsonMissing.of()).map { it.toImmutable() },
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
    fun validate(): BetaManagedAgentsSchedule = apply {
        if (validated) {
            return@apply
        }

        expression()
        timezone()
        type().validate()
        lastRunAt()
        upcomingRunsAt()
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
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (lastRunAt.asKnown().isPresent) 1 else 0) +
            (upcomingRunsAt.asKnown().getOrNull()?.size ?: 0)

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val CRON = of("cron")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            CRON
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            CRON,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
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
                CRON -> Value.CRON
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
                CRON -> Known.CRON
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
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
        fun validate(): Type = apply {
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

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSchedule &&
            expression == other.expression &&
            timezone == other.timezone &&
            type == other.type &&
            lastRunAt == other.lastRunAt &&
            upcomingRunsAt == other.upcomingRunsAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(expression, timezone, type, lastRunAt, upcomingRunsAt, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSchedule{expression=$expression, timezone=$timezone, type=$type, lastRunAt=$lastRunAt, upcomingRunsAt=$upcomingRunsAt, additionalProperties=$additionalProperties}"
}
