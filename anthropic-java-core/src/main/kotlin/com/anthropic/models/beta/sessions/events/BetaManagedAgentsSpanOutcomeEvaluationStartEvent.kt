package com.anthropic.models.beta.sessions.events

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

/** Emitted when an outcome evaluation cycle begins. */
class BetaManagedAgentsSpanOutcomeEvaluationStartEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val iteration: JsonField<Int>,
    private val outcomeId: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("iteration") @ExcludeMissing iteration: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("outcome_id") @ExcludeMissing outcomeId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, iteration, outcomeId, processedAt, type, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * 0-indexed revision cycle. 0 is the first evaluation; 1 is the re-evaluation after the first
     * revision; etc.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun iteration(): Int = iteration.getRequired("iteration")

    /**
     * The `outc_` ID of the outcome being evaluated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outcomeId(): String = outcomeId.getRequired("outcome_id")

    /**
     * Timestamp when outcome evaluation started.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processedAt(): OffsetDateTime = processedAt.getRequired("processed_at")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("span.outcome_evaluation_start")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [iteration].
     *
     * Unlike [iteration], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("iteration") @ExcludeMissing fun _iteration(): JsonField<Int> = iteration

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
         * [BetaManagedAgentsSpanOutcomeEvaluationStartEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .iteration()
         * .outcomeId()
         * .processedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSpanOutcomeEvaluationStartEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var iteration: JsonField<Int>? = null
        private var outcomeId: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonValue = JsonValue.from("span.outcome_evaluation_start")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSpanOutcomeEvaluationStartEvent:
                BetaManagedAgentsSpanOutcomeEvaluationStartEvent
        ) = apply {
            id = betaManagedAgentsSpanOutcomeEvaluationStartEvent.id
            iteration = betaManagedAgentsSpanOutcomeEvaluationStartEvent.iteration
            outcomeId = betaManagedAgentsSpanOutcomeEvaluationStartEvent.outcomeId
            processedAt = betaManagedAgentsSpanOutcomeEvaluationStartEvent.processedAt
            type = betaManagedAgentsSpanOutcomeEvaluationStartEvent.type
            additionalProperties =
                betaManagedAgentsSpanOutcomeEvaluationStartEvent.additionalProperties.toMutableMap()
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
         * 0-indexed revision cycle. 0 is the first evaluation; 1 is the re-evaluation after the
         * first revision; etc.
         */
        fun iteration(iteration: Int) = iteration(JsonField.of(iteration))

        /**
         * Sets [Builder.iteration] to an arbitrary JSON value.
         *
         * You should usually call [Builder.iteration] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun iteration(iteration: JsonField<Int>) = apply { this.iteration = iteration }

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

        /** Timestamp when outcome evaluation started. */
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
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("span.outcome_evaluation_start")
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
         * Returns an immutable instance of [BetaManagedAgentsSpanOutcomeEvaluationStartEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .iteration()
         * .outcomeId()
         * .processedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSpanOutcomeEvaluationStartEvent =
            BetaManagedAgentsSpanOutcomeEvaluationStartEvent(
                checkRequired("id", id),
                checkRequired("iteration", iteration),
                checkRequired("outcomeId", outcomeId),
                checkRequired("processedAt", processedAt),
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
    fun validate(): BetaManagedAgentsSpanOutcomeEvaluationStartEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        iteration()
        outcomeId()
        processedAt()
        _type().let {
            if (it != JsonValue.from("span.outcome_evaluation_start")) {
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (iteration.asKnown().isPresent) 1 else 0) +
            (if (outcomeId.asKnown().isPresent) 1 else 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("span.outcome_evaluation_start")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSpanOutcomeEvaluationStartEvent &&
            id == other.id &&
            iteration == other.iteration &&
            outcomeId == other.outcomeId &&
            processedAt == other.processedAt &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, iteration, outcomeId, processedAt, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSpanOutcomeEvaluationStartEvent{id=$id, iteration=$iteration, outcomeId=$outcomeId, processedAt=$processedAt, type=$type, additionalProperties=$additionalProperties}"
}
