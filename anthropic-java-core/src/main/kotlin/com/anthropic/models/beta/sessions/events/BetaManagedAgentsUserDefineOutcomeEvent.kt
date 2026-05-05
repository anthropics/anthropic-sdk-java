// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Echo of a `user.define_outcome` input event. Carries the server-generated `outcome_id` that
 * subsequent `span.outcome_evaluation_*` events reference.
 */
class BetaManagedAgentsUserDefineOutcomeEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val description: JsonField<String>,
    private val maxIterations: JsonField<Int>,
    private val outcomeId: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val rubric: JsonField<Rubric>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("max_iterations")
        @ExcludeMissing
        maxIterations: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("outcome_id") @ExcludeMissing outcomeId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("rubric") @ExcludeMissing rubric: JsonField<Rubric> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(id, description, maxIterations, outcomeId, processedAt, rubric, type, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * What the agent should produce. Copied from the input event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun description(): String = description.getRequired("description")

    /**
     * Evaluate-then-revise cycles before giving up. Default 3, max 20.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxIterations(): Optional<Int> = maxIterations.getOptional("max_iterations")

    /**
     * Server-generated `outc_` ID for this outcome. Referenced by `span.outcome_evaluation_*`
     * events and the session's `outcome_evaluations` list.
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
     * Rubric for grading the quality of an outcome.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun rubric(): Rubric = rubric.getRequired("rubric")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    /**
     * Returns the raw JSON value of [maxIterations].
     *
     * Unlike [maxIterations], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max_iterations")
    @ExcludeMissing
    fun _maxIterations(): JsonField<Int> = maxIterations

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
     * Returns the raw JSON value of [rubric].
     *
     * Unlike [rubric], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rubric") @ExcludeMissing fun _rubric(): JsonField<Rubric> = rubric

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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
         * [BetaManagedAgentsUserDefineOutcomeEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .description()
         * .maxIterations()
         * .outcomeId()
         * .processedAt()
         * .rubric()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsUserDefineOutcomeEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var description: JsonField<String>? = null
        private var maxIterations: JsonField<Int>? = null
        private var outcomeId: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var rubric: JsonField<Rubric>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsUserDefineOutcomeEvent: BetaManagedAgentsUserDefineOutcomeEvent
        ) = apply {
            id = betaManagedAgentsUserDefineOutcomeEvent.id
            description = betaManagedAgentsUserDefineOutcomeEvent.description
            maxIterations = betaManagedAgentsUserDefineOutcomeEvent.maxIterations
            outcomeId = betaManagedAgentsUserDefineOutcomeEvent.outcomeId
            processedAt = betaManagedAgentsUserDefineOutcomeEvent.processedAt
            rubric = betaManagedAgentsUserDefineOutcomeEvent.rubric
            type = betaManagedAgentsUserDefineOutcomeEvent.type
            additionalProperties =
                betaManagedAgentsUserDefineOutcomeEvent.additionalProperties.toMutableMap()
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

        /** What the agent should produce. Copied from the input event. */
        fun description(description: String) = description(JsonField.of(description))

        /**
         * Sets [Builder.description] to an arbitrary JSON value.
         *
         * You should usually call [Builder.description] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun description(description: JsonField<String>) = apply { this.description = description }

        /** Evaluate-then-revise cycles before giving up. Default 3, max 20. */
        fun maxIterations(maxIterations: Int?) = maxIterations(JsonField.ofNullable(maxIterations))

        /**
         * Alias for [Builder.maxIterations].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxIterations(maxIterations: Int) = maxIterations(maxIterations as Int?)

        /** Alias for calling [Builder.maxIterations] with `maxIterations.orElse(null)`. */
        fun maxIterations(maxIterations: Optional<Int>) = maxIterations(maxIterations.getOrNull())

        /**
         * Sets [Builder.maxIterations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxIterations] with a well-typed [Int] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun maxIterations(maxIterations: JsonField<Int>) = apply {
            this.maxIterations = maxIterations
        }

        /**
         * Server-generated `outc_` ID for this outcome. Referenced by `span.outcome_evaluation_*`
         * events and the session's `outcome_evaluations` list.
         */
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

        /** Rubric for grading the quality of an outcome. */
        fun rubric(rubric: Rubric) = rubric(JsonField.of(rubric))

        /**
         * Sets [Builder.rubric] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rubric] with a well-typed [Rubric] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun rubric(rubric: JsonField<Rubric>) = apply { this.rubric = rubric }

        /** Alias for calling [rubric] with `Rubric.ofFile(file)`. */
        fun rubric(file: BetaManagedAgentsFileRubric) = rubric(Rubric.ofFile(file))

        /**
         * Alias for calling [rubric] with the following:
         * ```java
         * BetaManagedAgentsFileRubric.builder()
         *     .type(BetaManagedAgentsFileRubric.Type.FILE)
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun fileRubric(fileId: String) =
            rubric(
                BetaManagedAgentsFileRubric.builder()
                    .type(BetaManagedAgentsFileRubric.Type.FILE)
                    .fileId(fileId)
                    .build()
            )

        /** Alias for calling [rubric] with `Rubric.ofText(text)`. */
        fun rubric(text: BetaManagedAgentsTextRubric) = rubric(Rubric.ofText(text))

        /**
         * Alias for calling [rubric] with the following:
         * ```java
         * BetaManagedAgentsTextRubric.builder()
         *     .type(BetaManagedAgentsTextRubric.Type.TEXT)
         *     .content(content)
         *     .build()
         * ```
         */
        fun textRubric(content: String) =
            rubric(
                BetaManagedAgentsTextRubric.builder()
                    .type(BetaManagedAgentsTextRubric.Type.TEXT)
                    .content(content)
                    .build()
            )

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaManagedAgentsUserDefineOutcomeEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .description()
         * .maxIterations()
         * .outcomeId()
         * .processedAt()
         * .rubric()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsUserDefineOutcomeEvent =
            BetaManagedAgentsUserDefineOutcomeEvent(
                checkRequired("id", id),
                checkRequired("description", description),
                checkRequired("maxIterations", maxIterations),
                checkRequired("outcomeId", outcomeId),
                checkRequired("processedAt", processedAt),
                checkRequired("rubric", rubric),
                checkRequired("type", type),
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
    fun validate(): BetaManagedAgentsUserDefineOutcomeEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        description()
        maxIterations()
        outcomeId()
        processedAt()
        rubric().validate()
        type().validate()
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
            (if (description.asKnown().isPresent) 1 else 0) +
            (if (maxIterations.asKnown().isPresent) 1 else 0) +
            (if (outcomeId.asKnown().isPresent) 1 else 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            (rubric.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0)

    /** Rubric for grading the quality of an outcome. */
    @JsonDeserialize(using = Rubric.Deserializer::class)
    @JsonSerialize(using = Rubric.Serializer::class)
    class Rubric
    private constructor(
        private val file: BetaManagedAgentsFileRubric? = null,
        private val text: BetaManagedAgentsTextRubric? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Rubric referenced by a file uploaded via the Files API. */
        fun file(): Optional<BetaManagedAgentsFileRubric> = Optional.ofNullable(file)

        /** Rubric content provided inline as text. */
        fun text(): Optional<BetaManagedAgentsTextRubric> = Optional.ofNullable(text)

        fun isFile(): Boolean = file != null

        fun isText(): Boolean = text != null

        /** Rubric referenced by a file uploaded via the Files API. */
        fun asFile(): BetaManagedAgentsFileRubric = file.getOrThrow("file")

        /** Rubric content provided inline as text. */
        fun asText(): BetaManagedAgentsTextRubric = text.getOrThrow("text")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = rubric.accept(new Rubric.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitFile(BetaManagedAgentsFileRubric file) {
         *         return Optional.of(file.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                file != null -> visitor.visitFile(file)
                text != null -> visitor.visitText(text)
                else -> visitor.unknown(_json)
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
        fun validate(): Rubric = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitFile(file: BetaManagedAgentsFileRubric) {
                        file.validate()
                    }

                    override fun visitText(text: BetaManagedAgentsTextRubric) {
                        text.validate()
                    }
                }
            )
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
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitFile(file: BetaManagedAgentsFileRubric) = file.validity()

                    override fun visitText(text: BetaManagedAgentsTextRubric) = text.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Rubric && file == other.file && text == other.text
        }

        override fun hashCode(): Int = Objects.hash(file, text)

        override fun toString(): String =
            when {
                file != null -> "Rubric{file=$file}"
                text != null -> "Rubric{text=$text}"
                _json != null -> "Rubric{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Rubric")
            }

        companion object {

            /** Rubric referenced by a file uploaded via the Files API. */
            @JvmStatic fun ofFile(file: BetaManagedAgentsFileRubric) = Rubric(file = file)

            /** Rubric content provided inline as text. */
            @JvmStatic fun ofText(text: BetaManagedAgentsTextRubric) = Rubric(text = text)
        }

        /** An interface that defines how to map each variant of [Rubric] to a value of type [T]. */
        interface Visitor<out T> {

            /** Rubric referenced by a file uploaded via the Files API. */
            fun visitFile(file: BetaManagedAgentsFileRubric): T

            /** Rubric content provided inline as text. */
            fun visitText(text: BetaManagedAgentsTextRubric): T

            /**
             * Maps an unknown variant of [Rubric] to a value of type [T].
             *
             * An instance of [Rubric] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Rubric: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Rubric>(Rubric::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Rubric {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "file" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsFileRubric>())
                            ?.let { Rubric(file = it, _json = json) } ?: Rubric(_json = json)
                    }
                    "text" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsTextRubric>())
                            ?.let { Rubric(text = it, _json = json) } ?: Rubric(_json = json)
                    }
                }

                return Rubric(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Rubric>(Rubric::class) {

            override fun serialize(
                value: Rubric,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.file != null -> generator.writeObject(value.file)
                    value.text != null -> generator.writeObject(value.text)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Rubric")
                }
            }
        }
    }

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

            @JvmField val USER_DEFINE_OUTCOME = of("user.define_outcome")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            USER_DEFINE_OUTCOME
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
            USER_DEFINE_OUTCOME,
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
                USER_DEFINE_OUTCOME -> Value.USER_DEFINE_OUTCOME
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
                USER_DEFINE_OUTCOME -> Known.USER_DEFINE_OUTCOME
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

        return other is BetaManagedAgentsUserDefineOutcomeEvent &&
            id == other.id &&
            description == other.description &&
            maxIterations == other.maxIterations &&
            outcomeId == other.outcomeId &&
            processedAt == other.processedAt &&
            rubric == other.rubric &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            description,
            maxIterations,
            outcomeId,
            processedAt,
            rubric,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsUserDefineOutcomeEvent{id=$id, description=$description, maxIterations=$maxIterations, outcomeId=$outcomeId, processedAt=$processedAt, rubric=$rubric, type=$type, additionalProperties=$additionalProperties}"
}
