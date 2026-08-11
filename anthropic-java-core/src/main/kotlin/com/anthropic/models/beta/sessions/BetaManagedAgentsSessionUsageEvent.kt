// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.BetaMonetaryAmount
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSessionUsageSnapshot
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Periodic snapshot of the session's cumulative usage and tracked list cost. */
class BetaManagedAgentsSessionUsageEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val type: JsonField<Type>,
    private val usage: JsonField<BetaManagedAgentsSessionUsageSnapshot>,
    private val budget: JsonField<BetaManagedAgentsBudgetLimit>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("usage")
        @ExcludeMissing
        usage: JsonField<BetaManagedAgentsSessionUsageSnapshot> = JsonMissing.of(),
        @JsonProperty("budget")
        @ExcludeMissing
        budget: JsonField<BetaManagedAgentsBudgetLimit> = JsonMissing.of(),
    ) : this(id, processedAt, type, usage, budget, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processedAt(): OffsetDateTime = processedAt.getRequired("processed_at")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Point-in-time snapshot of a session's cumulative usage.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun usage(): BetaManagedAgentsSessionUsageSnapshot = usage.getRequired("usage")

    /**
     * A hard spend ceiling. The session stops issuing new model requests once the tracked list cost
     * reaches `max_list_cost`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun budget(): Optional<BetaManagedAgentsBudgetLimit> = budget.getOptional("budget")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [processedAt].
     *
     * Unlike [processedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("processed_at")
    @ExcludeMissing
    fun _processedAt(): JsonField<OffsetDateTime> = processedAt

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [usage].
     *
     * Unlike [usage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("usage")
    @ExcludeMissing
    fun _usage(): JsonField<BetaManagedAgentsSessionUsageSnapshot> = usage

    /**
     * Returns the raw JSON value of [budget].
     *
     * Unlike [budget], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("budget")
    @ExcludeMissing
    fun _budget(): JsonField<BetaManagedAgentsBudgetLimit> = budget

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
         * [BetaManagedAgentsSessionUsageEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .processedAt()
         * .type()
         * .usage()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionUsageEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonField<Type>? = null
        private var usage: JsonField<BetaManagedAgentsSessionUsageSnapshot>? = null
        private var budget: JsonField<BetaManagedAgentsBudgetLimit> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsSessionUsageEvent: BetaManagedAgentsSessionUsageEvent) =
            apply {
                id = betaManagedAgentsSessionUsageEvent.id
                processedAt = betaManagedAgentsSessionUsageEvent.processedAt
                type = betaManagedAgentsSessionUsageEvent.type
                usage = betaManagedAgentsSessionUsageEvent.usage
                budget = betaManagedAgentsSessionUsageEvent.budget
                additionalProperties =
                    betaManagedAgentsSessionUsageEvent.additionalProperties.toMutableMap()
            }

        /** Unique identifier for this event. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** A timestamp in RFC 3339 format */
        fun processedAt(processedAt: OffsetDateTime) = processedAt(JsonField.of(processedAt))

        /**
         * Sets [Builder.processedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.processedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun processedAt(processedAt: JsonField<OffsetDateTime>) = apply {
            this.processedAt = processedAt
        }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** Point-in-time snapshot of a session's cumulative usage. */
        fun usage(usage: BetaManagedAgentsSessionUsageSnapshot) = usage(JsonField.of(usage))

        /**
         * Sets [Builder.usage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.usage] with a well-typed
         * [BetaManagedAgentsSessionUsageSnapshot] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun usage(usage: JsonField<BetaManagedAgentsSessionUsageSnapshot>) = apply {
            this.usage = usage
        }

        /**
         * A hard spend ceiling. The session stops issuing new model requests once the tracked list
         * cost reaches `max_list_cost`.
         */
        fun budget(budget: BetaManagedAgentsBudgetLimit?) = budget(JsonField.ofNullable(budget))

        /** Alias for calling [Builder.budget] with `budget.orElse(null)`. */
        fun budget(budget: Optional<BetaManagedAgentsBudgetLimit>) = budget(budget.getOrNull())

        /**
         * Sets [Builder.budget] to an arbitrary JSON value.
         *
         * You should usually call [Builder.budget] with a well-typed [BetaManagedAgentsBudgetLimit]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun budget(budget: JsonField<BetaManagedAgentsBudgetLimit>) = apply { this.budget = budget }

        /**
         * Alias for calling [budget] with the following:
         * ```java
         * BetaManagedAgentsBudgetLimit.builder()
         *     .type(BetaManagedAgentsBudgetLimit.Type.LIMIT)
         *     .maxListCost(maxListCost)
         *     .build()
         * ```
         */
        fun limitBudget(maxListCost: BetaMonetaryAmount) =
            budget(
                BetaManagedAgentsBudgetLimit.builder()
                    .type(BetaManagedAgentsBudgetLimit.Type.LIMIT)
                    .maxListCost(maxListCost)
                    .build()
            )

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
         * Returns an immutable instance of [BetaManagedAgentsSessionUsageEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .processedAt()
         * .type()
         * .usage()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSessionUsageEvent =
            BetaManagedAgentsSessionUsageEvent(
                checkRequired("id", id),
                checkRequired("processedAt", processedAt),
                checkRequired("type", type),
                checkRequired("usage", usage),
                budget,
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
    fun validate(): BetaManagedAgentsSessionUsageEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        processedAt()
        type().validate()
        usage().validate()
        budget().ifPresent { it.validate() }
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (usage.asKnown().getOrNull()?.validity() ?: 0) +
            (budget.asKnown().getOrNull()?.validity() ?: 0)

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

            @JvmField val SESSION_USAGE = of("session.usage")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SESSION_USAGE
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
            SESSION_USAGE,
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
                SESSION_USAGE -> Value.SESSION_USAGE
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
                SESSION_USAGE -> Known.SESSION_USAGE
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

        return other is BetaManagedAgentsSessionUsageEvent &&
            id == other.id &&
            processedAt == other.processedAt &&
            type == other.type &&
            usage == other.usage &&
            budget == other.budget &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, processedAt, type, usage, budget, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionUsageEvent{id=$id, processedAt=$processedAt, type=$type, usage=$usage, budget=$budget, additionalProperties=$additionalProperties}"
}
