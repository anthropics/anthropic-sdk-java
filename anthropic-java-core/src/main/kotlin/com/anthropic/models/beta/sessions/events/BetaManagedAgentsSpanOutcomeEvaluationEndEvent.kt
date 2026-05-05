// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * Emitted when an outcome evaluation cycle completes. Carries the verdict and aggregate token
 * usage. A verdict of `needs_revision` means another evaluation cycle follows; `satisfied`,
 * `max_iterations_reached`, `failed`, or `interrupted` are terminal — no further evaluation cycles
 * follow.
 */
class BetaManagedAgentsSpanOutcomeEvaluationEndEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val explanation: JsonField<String>,
    private val iteration: JsonField<Int>,
    private val outcomeEvaluationStartId: JsonField<String>,
    private val outcomeId: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val result: JsonField<String>,
    private val type: JsonField<Type>,
    private val usage: JsonField<BetaManagedAgentsSpanModelUsage>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("explanation")
        @ExcludeMissing
        explanation: JsonField<String> = JsonMissing.of(),
        @JsonProperty("iteration") @ExcludeMissing iteration: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("outcome_evaluation_start_id")
        @ExcludeMissing
        outcomeEvaluationStartId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("outcome_id") @ExcludeMissing outcomeId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("result") @ExcludeMissing result: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("usage")
        @ExcludeMissing
        usage: JsonField<BetaManagedAgentsSpanModelUsage> = JsonMissing.of(),
    ) : this(
        id,
        explanation,
        iteration,
        outcomeEvaluationStartId,
        outcomeId,
        processedAt,
        result,
        type,
        usage,
        mutableMapOf(),
    )

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Human-readable explanation of the verdict. For `needs_revision`, describes which criteria
     * failed and why.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun explanation(): String = explanation.getRequired("explanation")

    /**
     * 0-indexed revision cycle, matching the corresponding `span.outcome_evaluation_start`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun iteration(): Int = iteration.getRequired("iteration")

    /**
     * The id of the corresponding `span.outcome_evaluation_start` event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outcomeEvaluationStartId(): String =
        outcomeEvaluationStartId.getRequired("outcome_evaluation_start_id")

    /**
     * The `outc_` ID of the outcome being evaluated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outcomeId(): String = outcomeId.getRequired("outcome_id")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processedAt(): OffsetDateTime = processedAt.getRequired("processed_at")

    /**
     * Evaluation verdict. 'satisfied': criteria met, session goes idle. 'needs_revision': criteria
     * not met, another revision cycle follows. 'max_iterations_reached': evaluation budget
     * exhausted with criteria still unmet — one final acknowledgment turn follows before the
     * session goes idle, but no further evaluation runs. 'failed': grader determined the rubric
     * does not apply to the deliverables. 'interrupted': user sent an interrupt while evaluation
     * was in progress.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun result(): String = result.getRequired("result")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Token usage for a single model request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun usage(): BetaManagedAgentsSpanModelUsage = usage.getRequired("usage")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [explanation].
     *
     * Unlike [explanation], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("explanation") @ExcludeMissing fun _explanation(): JsonField<String> = explanation

    /**
     * Returns the raw JSON value of [iteration].
     *
     * Unlike [iteration], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("iteration") @ExcludeMissing fun _iteration(): JsonField<Int> = iteration

    /**
     * Returns the raw JSON value of [outcomeEvaluationStartId].
     *
     * Unlike [outcomeEvaluationStartId], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("outcome_evaluation_start_id")
    @ExcludeMissing
    fun _outcomeEvaluationStartId(): JsonField<String> = outcomeEvaluationStartId

    /**
     * Returns the raw JSON value of [outcomeId].
     *
     * Unlike [outcomeId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("outcome_id") @ExcludeMissing fun _outcomeId(): JsonField<String> = outcomeId

    /**
     * Returns the raw JSON value of [processedAt].
     *
     * Unlike [processedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("processed_at")
    @ExcludeMissing
    fun _processedAt(): JsonField<OffsetDateTime> = processedAt

    /**
     * Returns the raw JSON value of [result].
     *
     * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("result") @ExcludeMissing fun _result(): JsonField<String> = result

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
    fun _usage(): JsonField<BetaManagedAgentsSpanModelUsage> = usage

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
         * [BetaManagedAgentsSpanOutcomeEvaluationEndEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .explanation()
         * .iteration()
         * .outcomeEvaluationStartId()
         * .outcomeId()
         * .processedAt()
         * .result()
         * .type()
         * .usage()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSpanOutcomeEvaluationEndEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var explanation: JsonField<String>? = null
        private var iteration: JsonField<Int>? = null
        private var outcomeEvaluationStartId: JsonField<String>? = null
        private var outcomeId: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var result: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var usage: JsonField<BetaManagedAgentsSpanModelUsage>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSpanOutcomeEvaluationEndEvent:
                BetaManagedAgentsSpanOutcomeEvaluationEndEvent
        ) = apply {
            id = betaManagedAgentsSpanOutcomeEvaluationEndEvent.id
            explanation = betaManagedAgentsSpanOutcomeEvaluationEndEvent.explanation
            iteration = betaManagedAgentsSpanOutcomeEvaluationEndEvent.iteration
            outcomeEvaluationStartId =
                betaManagedAgentsSpanOutcomeEvaluationEndEvent.outcomeEvaluationStartId
            outcomeId = betaManagedAgentsSpanOutcomeEvaluationEndEvent.outcomeId
            processedAt = betaManagedAgentsSpanOutcomeEvaluationEndEvent.processedAt
            result = betaManagedAgentsSpanOutcomeEvaluationEndEvent.result
            type = betaManagedAgentsSpanOutcomeEvaluationEndEvent.type
            usage = betaManagedAgentsSpanOutcomeEvaluationEndEvent.usage
            additionalProperties =
                betaManagedAgentsSpanOutcomeEvaluationEndEvent.additionalProperties.toMutableMap()
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

        /**
         * Human-readable explanation of the verdict. For `needs_revision`, describes which criteria
         * failed and why.
         */
        fun explanation(explanation: String) = explanation(JsonField.of(explanation))

        /**
         * Sets [Builder.explanation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.explanation] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun explanation(explanation: JsonField<String>) = apply { this.explanation = explanation }

        /** 0-indexed revision cycle, matching the corresponding `span.outcome_evaluation_start`. */
        fun iteration(iteration: Int) = iteration(JsonField.of(iteration))

        /**
         * Sets [Builder.iteration] to an arbitrary JSON value.
         *
         * You should usually call [Builder.iteration] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun iteration(iteration: JsonField<Int>) = apply { this.iteration = iteration }

        /** The id of the corresponding `span.outcome_evaluation_start` event. */
        fun outcomeEvaluationStartId(outcomeEvaluationStartId: String) =
            outcomeEvaluationStartId(JsonField.of(outcomeEvaluationStartId))

        /**
         * Sets [Builder.outcomeEvaluationStartId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outcomeEvaluationStartId] with a well-typed [String]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun outcomeEvaluationStartId(outcomeEvaluationStartId: JsonField<String>) = apply {
            this.outcomeEvaluationStartId = outcomeEvaluationStartId
        }

        /** The `outc_` ID of the outcome being evaluated. */
        fun outcomeId(outcomeId: String) = outcomeId(JsonField.of(outcomeId))

        /**
         * Sets [Builder.outcomeId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outcomeId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun outcomeId(outcomeId: JsonField<String>) = apply { this.outcomeId = outcomeId }

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

        /**
         * Evaluation verdict. 'satisfied': criteria met, session goes idle. 'needs_revision':
         * criteria not met, another revision cycle follows. 'max_iterations_reached': evaluation
         * budget exhausted with criteria still unmet — one final acknowledgment turn follows before
         * the session goes idle, but no further evaluation runs. 'failed': grader determined the
         * rubric does not apply to the deliverables. 'interrupted': user sent an interrupt while
         * evaluation was in progress.
         */
        fun result(result: String) = result(JsonField.of(result))

        /**
         * Sets [Builder.result] to an arbitrary JSON value.
         *
         * You should usually call [Builder.result] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun result(result: JsonField<String>) = apply { this.result = result }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** Token usage for a single model request. */
        fun usage(usage: BetaManagedAgentsSpanModelUsage) = usage(JsonField.of(usage))

        /**
         * Sets [Builder.usage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.usage] with a well-typed
         * [BetaManagedAgentsSpanModelUsage] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun usage(usage: JsonField<BetaManagedAgentsSpanModelUsage>) = apply { this.usage = usage }

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
         * Returns an immutable instance of [BetaManagedAgentsSpanOutcomeEvaluationEndEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .explanation()
         * .iteration()
         * .outcomeEvaluationStartId()
         * .outcomeId()
         * .processedAt()
         * .result()
         * .type()
         * .usage()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSpanOutcomeEvaluationEndEvent =
            BetaManagedAgentsSpanOutcomeEvaluationEndEvent(
                checkRequired("id", id),
                checkRequired("explanation", explanation),
                checkRequired("iteration", iteration),
                checkRequired("outcomeEvaluationStartId", outcomeEvaluationStartId),
                checkRequired("outcomeId", outcomeId),
                checkRequired("processedAt", processedAt),
                checkRequired("result", result),
                checkRequired("type", type),
                checkRequired("usage", usage),
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
    fun validate(): BetaManagedAgentsSpanOutcomeEvaluationEndEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        explanation()
        iteration()
        outcomeEvaluationStartId()
        outcomeId()
        processedAt()
        result()
        type().validate()
        usage().validate()
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
            (if (explanation.asKnown().isPresent) 1 else 0) +
            (if (iteration.asKnown().isPresent) 1 else 0) +
            (if (outcomeEvaluationStartId.asKnown().isPresent) 1 else 0) +
            (if (outcomeId.asKnown().isPresent) 1 else 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            (if (result.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (usage.asKnown().getOrNull()?.validity() ?: 0)

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

            @JvmField val SPAN_OUTCOME_EVALUATION_END = of("span.outcome_evaluation_end")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SPAN_OUTCOME_EVALUATION_END
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
            SPAN_OUTCOME_EVALUATION_END,
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
                SPAN_OUTCOME_EVALUATION_END -> Value.SPAN_OUTCOME_EVALUATION_END
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
                SPAN_OUTCOME_EVALUATION_END -> Known.SPAN_OUTCOME_EVALUATION_END
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

        return other is BetaManagedAgentsSpanOutcomeEvaluationEndEvent &&
            id == other.id &&
            explanation == other.explanation &&
            iteration == other.iteration &&
            outcomeEvaluationStartId == other.outcomeEvaluationStartId &&
            outcomeId == other.outcomeId &&
            processedAt == other.processedAt &&
            result == other.result &&
            type == other.type &&
            usage == other.usage &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            explanation,
            iteration,
            outcomeEvaluationStartId,
            outcomeId,
            processedAt,
            result,
            type,
            usage,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSpanOutcomeEvaluationEndEvent{id=$id, explanation=$explanation, iteration=$iteration, outcomeEvaluationStartId=$outcomeEvaluationStartId, outcomeId=$outcomeId, processedAt=$processedAt, result=$result, type=$type, usage=$usage, additionalProperties=$additionalProperties}"
}
