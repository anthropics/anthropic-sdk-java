package com.anthropic.models.beta.sessions

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Evaluation state for a single outcome defined via a `define_outcome` event. */
class BetaManagedAgentsOutcomeEvaluationResource
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val completedAt: JsonField<OffsetDateTime>,
    private val description: JsonField<String>,
    private val explanation: JsonField<String>,
    private val iteration: JsonField<Int>,
    private val outcomeId: JsonField<String>,
    private val result: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("completed_at")
        @ExcludeMissing
        completedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("explanation")
        @ExcludeMissing
        explanation: JsonField<String> = JsonMissing.of(),
        @JsonProperty("iteration") @ExcludeMissing iteration: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("outcome_id") @ExcludeMissing outcomeId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("result") @ExcludeMissing result: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        completedAt,
        description,
        explanation,
        iteration,
        outcomeId,
        result,
        type,
        mutableMapOf(),
    )

    /**
     * When the outcome reached a terminal result. Null while `pending`/`running`/`evaluating`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun completedAt(): Optional<OffsetDateTime> = completedAt.getOptional("completed_at")

    /**
     * What the agent should produce.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun description(): String = description.getRequired("description")

    /**
     * Grader's verdict text from the most recent evaluation. For `satisfied`, explains why criteria
     * are met; for `needs_revision` (intermediate), what's missing; for `failed`, why
     * unrecoverable.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun explanation(): Optional<String> = explanation.getOptional("explanation")

    /**
     * 0-indexed revision cycle the outcome is currently on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun iteration(): Int = iteration.getRequired("iteration")

    /**
     * Server-generated outc_ ID for this outcome.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outcomeId(): String = outcomeId.getRequired("outcome_id")

    /**
     * Current evaluation state. `pending` before the agent begins work; `running` while producing
     * or revising; `evaluating` while the grader scores;
     * `satisfied`/`max_iterations_reached`/`failed`/`interrupted` are terminal.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun result(): String = result.getRequired("result")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("outcome_evaluation")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [completedAt].
     *
     * Unlike [completedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("completed_at")
    @ExcludeMissing
    fun _completedAt(): JsonField<OffsetDateTime> = completedAt

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

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
     * Returns the raw JSON value of [outcomeId].
     *
     * Unlike [outcomeId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("outcome_id") @ExcludeMissing fun _outcomeId(): JsonField<String> = outcomeId

    /**
     * Returns the raw JSON value of [result].
     *
     * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("result") @ExcludeMissing fun _result(): JsonField<String> = result

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
         * [BetaManagedAgentsOutcomeEvaluationResource].
         *
         * The following fields are required:
         * ```java
         * .completedAt()
         * .description()
         * .explanation()
         * .iteration()
         * .outcomeId()
         * .result()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsOutcomeEvaluationResource]. */
    class Builder internal constructor() {

        private var completedAt: JsonField<OffsetDateTime>? = null
        private var description: JsonField<String>? = null
        private var explanation: JsonField<String>? = null
        private var iteration: JsonField<Int>? = null
        private var outcomeId: JsonField<String>? = null
        private var result: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("outcome_evaluation")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsOutcomeEvaluationResource: BetaManagedAgentsOutcomeEvaluationResource
        ) = apply {
            completedAt = betaManagedAgentsOutcomeEvaluationResource.completedAt
            description = betaManagedAgentsOutcomeEvaluationResource.description
            explanation = betaManagedAgentsOutcomeEvaluationResource.explanation
            iteration = betaManagedAgentsOutcomeEvaluationResource.iteration
            outcomeId = betaManagedAgentsOutcomeEvaluationResource.outcomeId
            result = betaManagedAgentsOutcomeEvaluationResource.result
            type = betaManagedAgentsOutcomeEvaluationResource.type
            additionalProperties =
                betaManagedAgentsOutcomeEvaluationResource.additionalProperties.toMutableMap()
        }

        /**
         * When the outcome reached a terminal result. Null while `pending`/`running`/`evaluating`.
         */
        fun completedAt(completedAt: OffsetDateTime?) =
            completedAt(JsonField.ofNullable(completedAt))

        /** Alias for calling [Builder.completedAt] with `completedAt.orElse(null)`. */
        fun completedAt(completedAt: Optional<OffsetDateTime>) =
            completedAt(completedAt.getOrNull())

        /**
         * Sets [Builder.completedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.completedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun completedAt(completedAt: JsonField<OffsetDateTime>) = apply {
            this.completedAt = completedAt
        }

        /** What the agent should produce. */
        fun description(description: String) = description(JsonField.of(description))

        /**
         * Sets [Builder.description] to an arbitrary JSON value.
         *
         * You should usually call [Builder.description] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun description(description: JsonField<String>) = apply { this.description = description }

        /**
         * Grader's verdict text from the most recent evaluation. For `satisfied`, explains why
         * criteria are met; for `needs_revision` (intermediate), what's missing; for `failed`, why
         * unrecoverable.
         */
        fun explanation(explanation: String?) = explanation(JsonField.ofNullable(explanation))

        /** Alias for calling [Builder.explanation] with `explanation.orElse(null)`. */
        fun explanation(explanation: Optional<String>) = explanation(explanation.getOrNull())

        /**
         * Sets [Builder.explanation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.explanation] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun explanation(explanation: JsonField<String>) = apply { this.explanation = explanation }

        /** 0-indexed revision cycle the outcome is currently on. */
        fun iteration(iteration: Int) = iteration(JsonField.of(iteration))

        /**
         * Sets [Builder.iteration] to an arbitrary JSON value.
         *
         * You should usually call [Builder.iteration] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun iteration(iteration: JsonField<Int>) = apply { this.iteration = iteration }

        /** Server-generated outc_ ID for this outcome. */
        fun outcomeId(outcomeId: String) = outcomeId(JsonField.of(outcomeId))

        /**
         * Sets [Builder.outcomeId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outcomeId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun outcomeId(outcomeId: JsonField<String>) = apply { this.outcomeId = outcomeId }

        /**
         * Current evaluation state. `pending` before the agent begins work; `running` while
         * producing or revising; `evaluating` while the grader scores;
         * `satisfied`/`max_iterations_reached`/`failed`/`interrupted` are terminal.
         */
        fun result(result: String) = result(JsonField.of(result))

        /**
         * Sets [Builder.result] to an arbitrary JSON value.
         *
         * You should usually call [Builder.result] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun result(result: JsonField<String>) = apply { this.result = result }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("outcome_evaluation")
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
         * Returns an immutable instance of [BetaManagedAgentsOutcomeEvaluationResource].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .completedAt()
         * .description()
         * .explanation()
         * .iteration()
         * .outcomeId()
         * .result()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsOutcomeEvaluationResource =
            BetaManagedAgentsOutcomeEvaluationResource(
                checkRequired("completedAt", completedAt),
                checkRequired("description", description),
                checkRequired("explanation", explanation),
                checkRequired("iteration", iteration),
                checkRequired("outcomeId", outcomeId),
                checkRequired("result", result),
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
    fun validate(): BetaManagedAgentsOutcomeEvaluationResource = apply {
        if (validated) {
            return@apply
        }

        completedAt()
        description()
        explanation()
        iteration()
        outcomeId()
        result()
        _type().let {
            if (it != JsonValue.from("outcome_evaluation")) {
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
        (if (completedAt.asKnown().isPresent) 1 else 0) +
            (if (description.asKnown().isPresent) 1 else 0) +
            (if (explanation.asKnown().isPresent) 1 else 0) +
            (if (iteration.asKnown().isPresent) 1 else 0) +
            (if (outcomeId.asKnown().isPresent) 1 else 0) +
            (if (result.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("outcome_evaluation")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsOutcomeEvaluationResource &&
            completedAt == other.completedAt &&
            description == other.description &&
            explanation == other.explanation &&
            iteration == other.iteration &&
            outcomeId == other.outcomeId &&
            result == other.result &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            completedAt,
            description,
            explanation,
            iteration,
            outcomeId,
            result,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsOutcomeEvaluationResource{completedAt=$completedAt, description=$description, explanation=$explanation, iteration=$iteration, outcomeId=$outcomeId, result=$result, type=$type, additionalProperties=$additionalProperties}"
}
