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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Emitted when a model request completes. */
class BetaManagedAgentsSpanModelRequestEndEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val isError: JsonField<Boolean>,
    private val modelRequestStartId: JsonField<String>,
    private val modelUsage: JsonField<BetaManagedAgentsSpanModelUsage>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("is_error") @ExcludeMissing isError: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("model_request_start_id")
        @ExcludeMissing
        modelRequestStartId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("model_usage")
        @ExcludeMissing
        modelUsage: JsonField<BetaManagedAgentsSpanModelUsage> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(id, isError, modelRequestStartId, modelUsage, processedAt, type, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Whether the model request resulted in an error.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun isError(): Optional<Boolean> = isError.getOptional("is_error")

    /**
     * The id of the corresponding `span.model_request_start` event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun modelRequestStartId(): String = modelRequestStartId.getRequired("model_request_start_id")

    /**
     * Token usage for a single model request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun modelUsage(): BetaManagedAgentsSpanModelUsage = modelUsage.getRequired("model_usage")

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
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [isError].
     *
     * Unlike [isError], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("is_error") @ExcludeMissing fun _isError(): JsonField<Boolean> = isError

    /**
     * Returns the raw JSON value of [modelRequestStartId].
     *
     * Unlike [modelRequestStartId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("model_request_start_id")
    @ExcludeMissing
    fun _modelRequestStartId(): JsonField<String> = modelRequestStartId

    /**
     * Returns the raw JSON value of [modelUsage].
     *
     * Unlike [modelUsage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model_usage")
    @ExcludeMissing
    fun _modelUsage(): JsonField<BetaManagedAgentsSpanModelUsage> = modelUsage

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
         * [BetaManagedAgentsSpanModelRequestEndEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .isError()
         * .modelRequestStartId()
         * .modelUsage()
         * .processedAt()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSpanModelRequestEndEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var isError: JsonField<Boolean>? = null
        private var modelRequestStartId: JsonField<String>? = null
        private var modelUsage: JsonField<BetaManagedAgentsSpanModelUsage>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSpanModelRequestEndEvent: BetaManagedAgentsSpanModelRequestEndEvent
        ) = apply {
            id = betaManagedAgentsSpanModelRequestEndEvent.id
            isError = betaManagedAgentsSpanModelRequestEndEvent.isError
            modelRequestStartId = betaManagedAgentsSpanModelRequestEndEvent.modelRequestStartId
            modelUsage = betaManagedAgentsSpanModelRequestEndEvent.modelUsage
            processedAt = betaManagedAgentsSpanModelRequestEndEvent.processedAt
            type = betaManagedAgentsSpanModelRequestEndEvent.type
            additionalProperties =
                betaManagedAgentsSpanModelRequestEndEvent.additionalProperties.toMutableMap()
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

        /** Whether the model request resulted in an error. */
        fun isError(isError: Boolean?) = isError(JsonField.ofNullable(isError))

        /**
         * Alias for [Builder.isError].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun isError(isError: Boolean) = isError(isError as Boolean?)

        /** Alias for calling [Builder.isError] with `isError.orElse(null)`. */
        fun isError(isError: Optional<Boolean>) = isError(isError.getOrNull())

        /**
         * Sets [Builder.isError] to an arbitrary JSON value.
         *
         * You should usually call [Builder.isError] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun isError(isError: JsonField<Boolean>) = apply { this.isError = isError }

        /** The id of the corresponding `span.model_request_start` event. */
        fun modelRequestStartId(modelRequestStartId: String) =
            modelRequestStartId(JsonField.of(modelRequestStartId))

        /**
         * Sets [Builder.modelRequestStartId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.modelRequestStartId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun modelRequestStartId(modelRequestStartId: JsonField<String>) = apply {
            this.modelRequestStartId = modelRequestStartId
        }

        /** Token usage for a single model request. */
        fun modelUsage(modelUsage: BetaManagedAgentsSpanModelUsage) =
            modelUsage(JsonField.of(modelUsage))

        /**
         * Sets [Builder.modelUsage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.modelUsage] with a well-typed
         * [BetaManagedAgentsSpanModelUsage] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun modelUsage(modelUsage: JsonField<BetaManagedAgentsSpanModelUsage>) = apply {
            this.modelUsage = modelUsage
        }

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
         * Returns an immutable instance of [BetaManagedAgentsSpanModelRequestEndEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .isError()
         * .modelRequestStartId()
         * .modelUsage()
         * .processedAt()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSpanModelRequestEndEvent =
            BetaManagedAgentsSpanModelRequestEndEvent(
                checkRequired("id", id),
                checkRequired("isError", isError),
                checkRequired("modelRequestStartId", modelRequestStartId),
                checkRequired("modelUsage", modelUsage),
                checkRequired("processedAt", processedAt),
                checkRequired("type", type),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaManagedAgentsSpanModelRequestEndEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        isError()
        modelRequestStartId()
        modelUsage().validate()
        processedAt()
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
            (if (isError.asKnown().isPresent) 1 else 0) +
            (if (modelRequestStartId.asKnown().isPresent) 1 else 0) +
            (modelUsage.asKnown().getOrNull()?.validity() ?: 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0)

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

            @JvmField val SPAN_MODEL_REQUEST_END = of("span.model_request_end")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SPAN_MODEL_REQUEST_END
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
            SPAN_MODEL_REQUEST_END,
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
                SPAN_MODEL_REQUEST_END -> Value.SPAN_MODEL_REQUEST_END
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
                SPAN_MODEL_REQUEST_END -> Known.SPAN_MODEL_REQUEST_END
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

        return other is BetaManagedAgentsSpanModelRequestEndEvent &&
            id == other.id &&
            isError == other.isError &&
            modelRequestStartId == other.modelRequestStartId &&
            modelUsage == other.modelUsage &&
            processedAt == other.processedAt &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            isError,
            modelRequestStartId,
            modelUsage,
            processedAt,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSpanModelRequestEndEvent{id=$id, isError=$isError, modelRequestStartId=$modelRequestStartId, modelUsage=$modelUsage, processedAt=$processedAt, type=$type, additionalProperties=$additionalProperties}"
}
