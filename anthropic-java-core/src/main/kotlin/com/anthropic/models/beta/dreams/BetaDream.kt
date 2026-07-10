// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

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

/**
 * An asynchronous memory-consolidation job that reads a memory store plus a set of session
 * transcripts and writes consolidated memories into a new output memory store. The Dreams API is in
 * research preview: the request and response shapes are volatile and may change without the
 * deprecation period that applies to generally-available endpoints.
 */
class BetaDream
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val endedAt: JsonField<OffsetDateTime>,
    private val error: JsonField<BetaDreamError>,
    private val inputs: JsonField<List<BetaDreamInput>>,
    private val instructions: JsonField<String>,
    private val model: JsonField<BetaDreamModelConfig>,
    private val outputs: JsonField<List<BetaDreamOutput>>,
    private val sessionId: JsonField<String>,
    private val status: JsonField<BetaDreamStatus>,
    private val type: JsonField<Type>,
    private val usage: JsonField<BetaDreamUsage>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("archived_at")
        @ExcludeMissing
        archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("ended_at")
        @ExcludeMissing
        endedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("error") @ExcludeMissing error: JsonField<BetaDreamError> = JsonMissing.of(),
        @JsonProperty("inputs")
        @ExcludeMissing
        inputs: JsonField<List<BetaDreamInput>> = JsonMissing.of(),
        @JsonProperty("instructions")
        @ExcludeMissing
        instructions: JsonField<String> = JsonMissing.of(),
        @JsonProperty("model")
        @ExcludeMissing
        model: JsonField<BetaDreamModelConfig> = JsonMissing.of(),
        @JsonProperty("outputs")
        @ExcludeMissing
        outputs: JsonField<List<BetaDreamOutput>> = JsonMissing.of(),
        @JsonProperty("session_id") @ExcludeMissing sessionId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("status")
        @ExcludeMissing
        status: JsonField<BetaDreamStatus> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("usage") @ExcludeMissing usage: JsonField<BetaDreamUsage> = JsonMissing.of(),
    ) : this(
        id,
        archivedAt,
        createdAt,
        endedAt,
        error,
        inputs,
        instructions,
        model,
        outputs,
        sessionId,
        status,
        type,
        usage,
        mutableMapOf(),
    )

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedAt(): Optional<OffsetDateTime> = archivedAt.getOptional("archived_at")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun endedAt(): Optional<OffsetDateTime> = endedAt.getOptional("ended_at")

    /**
     * Failure detail for a Dream whose `status` is `failed`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun error(): Optional<BetaDreamError> = error.getOptional("error")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun inputs(): List<BetaDreamInput> = inputs.getRequired("inputs")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun instructions(): Optional<String> = instructions.getOptional("instructions")

    /**
     * Model identifier and configuration applied to every pipeline stage. Same wire shape as the
     * Agents API ModelConfig.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun model(): BetaDreamModelConfig = model.getRequired("model")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outputs(): List<BetaDreamOutput> = outputs.getRequired("outputs")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun sessionId(): Optional<String> = sessionId.getOptional("session_id")

    /**
     * Lifecycle status of a Dream.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): BetaDreamStatus = status.getRequired("status")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Cumulative token usage for the dream across every pipeline stage.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun usage(): BetaDreamUsage = usage.getRequired("usage")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [archivedAt].
     *
     * Unlike [archivedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("archived_at")
    @ExcludeMissing
    fun _archivedAt(): JsonField<OffsetDateTime> = archivedAt

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [endedAt].
     *
     * Unlike [endedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ended_at") @ExcludeMissing fun _endedAt(): JsonField<OffsetDateTime> = endedAt

    /**
     * Returns the raw JSON value of [error].
     *
     * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<BetaDreamError> = error

    /**
     * Returns the raw JSON value of [inputs].
     *
     * Unlike [inputs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("inputs") @ExcludeMissing fun _inputs(): JsonField<List<BetaDreamInput>> = inputs

    /**
     * Returns the raw JSON value of [instructions].
     *
     * Unlike [instructions], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("instructions")
    @ExcludeMissing
    fun _instructions(): JsonField<String> = instructions

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<BetaDreamModelConfig> = model

    /**
     * Returns the raw JSON value of [outputs].
     *
     * Unlike [outputs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("outputs")
    @ExcludeMissing
    fun _outputs(): JsonField<List<BetaDreamOutput>> = outputs

    /**
     * Returns the raw JSON value of [sessionId].
     *
     * Unlike [sessionId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("session_id") @ExcludeMissing fun _sessionId(): JsonField<String> = sessionId

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<BetaDreamStatus> = status

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
    @JsonProperty("usage") @ExcludeMissing fun _usage(): JsonField<BetaDreamUsage> = usage

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
         * Returns a mutable builder for constructing an instance of [BetaDream].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .createdAt()
         * .endedAt()
         * .error()
         * .inputs()
         * .instructions()
         * .model()
         * .outputs()
         * .sessionId()
         * .status()
         * .type()
         * .usage()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaDream]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var endedAt: JsonField<OffsetDateTime>? = null
        private var error: JsonField<BetaDreamError>? = null
        private var inputs: JsonField<MutableList<BetaDreamInput>>? = null
        private var instructions: JsonField<String>? = null
        private var model: JsonField<BetaDreamModelConfig>? = null
        private var outputs: JsonField<MutableList<BetaDreamOutput>>? = null
        private var sessionId: JsonField<String>? = null
        private var status: JsonField<BetaDreamStatus>? = null
        private var type: JsonField<Type>? = null
        private var usage: JsonField<BetaDreamUsage>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaDream: BetaDream) = apply {
            id = betaDream.id
            archivedAt = betaDream.archivedAt
            createdAt = betaDream.createdAt
            endedAt = betaDream.endedAt
            error = betaDream.error
            inputs = betaDream.inputs.map { it.toMutableList() }.takeUnless { it.isMissing() }
            instructions = betaDream.instructions
            model = betaDream.model
            outputs = betaDream.outputs.map { it.toMutableList() }.takeUnless { it.isMissing() }
            sessionId = betaDream.sessionId
            status = betaDream.status
            type = betaDream.type
            usage = betaDream.usage
            additionalProperties = betaDream.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** A timestamp in RFC 3339 format */
        fun archivedAt(archivedAt: OffsetDateTime?) = archivedAt(JsonField.ofNullable(archivedAt))

        /** Alias for calling [Builder.archivedAt] with `archivedAt.orElse(null)`. */
        fun archivedAt(archivedAt: Optional<OffsetDateTime>) = archivedAt(archivedAt.getOrNull())

        /**
         * Sets [Builder.archivedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.archivedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun archivedAt(archivedAt: JsonField<OffsetDateTime>) = apply {
            this.archivedAt = archivedAt
        }

        /** A timestamp in RFC 3339 format */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** A timestamp in RFC 3339 format */
        fun endedAt(endedAt: OffsetDateTime?) = endedAt(JsonField.ofNullable(endedAt))

        /** Alias for calling [Builder.endedAt] with `endedAt.orElse(null)`. */
        fun endedAt(endedAt: Optional<OffsetDateTime>) = endedAt(endedAt.getOrNull())

        /**
         * Sets [Builder.endedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun endedAt(endedAt: JsonField<OffsetDateTime>) = apply { this.endedAt = endedAt }

        /** Failure detail for a Dream whose `status` is `failed`. */
        fun error(error: BetaDreamError?) = error(JsonField.ofNullable(error))

        /** Alias for calling [Builder.error] with `error.orElse(null)`. */
        fun error(error: Optional<BetaDreamError>) = error(error.getOrNull())

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed [BetaDreamError] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun error(error: JsonField<BetaDreamError>) = apply { this.error = error }

        fun inputs(inputs: List<BetaDreamInput>) = inputs(JsonField.of(inputs))

        /**
         * Sets [Builder.inputs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputs] with a well-typed `List<BetaDreamInput>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun inputs(inputs: JsonField<List<BetaDreamInput>>) = apply {
            this.inputs = inputs.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaDreamInput] to [inputs].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addInput(input: BetaDreamInput) = apply {
            inputs =
                (inputs ?: JsonField.of(mutableListOf())).also {
                    checkKnown("inputs", it).add(input)
                }
        }

        /** Alias for calling [addInput] with `BetaDreamInput.ofMemoryStore(memoryStore)`. */
        fun addInput(memoryStore: BetaDreamMemoryStoreInput) =
            addInput(BetaDreamInput.ofMemoryStore(memoryStore))

        /**
         * Alias for calling [addInput] with the following:
         * ```java
         * BetaDreamMemoryStoreInput.builder()
         *     .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
         *     .memoryStoreId(memoryStoreId)
         *     .build()
         * ```
         */
        fun addMemoryStoreInput(memoryStoreId: String) =
            addInput(
                BetaDreamMemoryStoreInput.builder()
                    .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
                    .memoryStoreId(memoryStoreId)
                    .build()
            )

        /** Alias for calling [addInput] with `BetaDreamInput.ofSessions(sessions)`. */
        fun addInput(sessions: BetaDreamSessionsInput) =
            addInput(BetaDreamInput.ofSessions(sessions))

        /**
         * Alias for calling [addInput] with the following:
         * ```java
         * BetaDreamSessionsInput.builder()
         *     .type(BetaDreamSessionsInput.Type.SESSIONS)
         *     .sessionIds(sessionIds)
         *     .build()
         * ```
         */
        fun addSessionsInput(sessionIds: List<String>) =
            addInput(
                BetaDreamSessionsInput.builder()
                    .type(BetaDreamSessionsInput.Type.SESSIONS)
                    .sessionIds(sessionIds)
                    .build()
            )

        fun instructions(instructions: String?) = instructions(JsonField.ofNullable(instructions))

        /** Alias for calling [Builder.instructions] with `instructions.orElse(null)`. */
        fun instructions(instructions: Optional<String>) = instructions(instructions.getOrNull())

        /**
         * Sets [Builder.instructions] to an arbitrary JSON value.
         *
         * You should usually call [Builder.instructions] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun instructions(instructions: JsonField<String>) = apply {
            this.instructions = instructions
        }

        /**
         * Model identifier and configuration applied to every pipeline stage. Same wire shape as
         * the Agents API ModelConfig.
         */
        fun model(model: BetaDreamModelConfig) = model(JsonField.of(model))

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [BetaDreamModelConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun model(model: JsonField<BetaDreamModelConfig>) = apply { this.model = model }

        fun outputs(outputs: List<BetaDreamOutput>) = outputs(JsonField.of(outputs))

        /**
         * Sets [Builder.outputs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputs] with a well-typed `List<BetaDreamOutput>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun outputs(outputs: JsonField<List<BetaDreamOutput>>) = apply {
            this.outputs = outputs.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaDreamOutput] to [outputs].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addOutput(output: BetaDreamOutput) = apply {
            outputs =
                (outputs ?: JsonField.of(mutableListOf())).also {
                    checkKnown("outputs", it).add(output)
                }
        }

        /**
         * Alias for calling [addOutput] with the following:
         * ```java
         * BetaDreamOutput.builder()
         *     .type(BetaDreamOutput.Type.MEMORY_STORE)
         *     .memoryStoreId(memoryStoreId)
         *     .build()
         * ```
         */
        fun addMemoryStoreOutput(memoryStoreId: String) =
            addOutput(
                BetaDreamOutput.builder()
                    .type(BetaDreamOutput.Type.MEMORY_STORE)
                    .memoryStoreId(memoryStoreId)
                    .build()
            )

        fun sessionId(sessionId: String?) = sessionId(JsonField.ofNullable(sessionId))

        /** Alias for calling [Builder.sessionId] with `sessionId.orElse(null)`. */
        fun sessionId(sessionId: Optional<String>) = sessionId(sessionId.getOrNull())

        /**
         * Sets [Builder.sessionId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sessionId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun sessionId(sessionId: JsonField<String>) = apply { this.sessionId = sessionId }

        /** Lifecycle status of a Dream. */
        fun status(status: BetaDreamStatus) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [BetaDreamStatus] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun status(status: JsonField<BetaDreamStatus>) = apply { this.status = status }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** Cumulative token usage for the dream across every pipeline stage. */
        fun usage(usage: BetaDreamUsage) = usage(JsonField.of(usage))

        /**
         * Sets [Builder.usage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.usage] with a well-typed [BetaDreamUsage] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun usage(usage: JsonField<BetaDreamUsage>) = apply { this.usage = usage }

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
         * Returns an immutable instance of [BetaDream].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .createdAt()
         * .endedAt()
         * .error()
         * .inputs()
         * .instructions()
         * .model()
         * .outputs()
         * .sessionId()
         * .status()
         * .type()
         * .usage()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaDream =
            BetaDream(
                checkRequired("id", id),
                checkRequired("archivedAt", archivedAt),
                checkRequired("createdAt", createdAt),
                checkRequired("endedAt", endedAt),
                checkRequired("error", error),
                checkRequired("inputs", inputs).map { it.toImmutable() },
                checkRequired("instructions", instructions),
                checkRequired("model", model),
                checkRequired("outputs", outputs).map { it.toImmutable() },
                checkRequired("sessionId", sessionId),
                checkRequired("status", status),
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
    fun validate(): BetaDream = apply {
        if (validated) {
            return@apply
        }

        id()
        archivedAt()
        createdAt()
        endedAt()
        error().ifPresent { it.validate() }
        inputs().forEach { it.validate() }
        instructions()
        model().validate()
        outputs().forEach { it.validate() }
        sessionId()
        status().validate()
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
            (if (archivedAt.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (endedAt.asKnown().isPresent) 1 else 0) +
            (error.asKnown().getOrNull()?.validity() ?: 0) +
            (inputs.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (instructions.asKnown().isPresent) 1 else 0) +
            (model.asKnown().getOrNull()?.validity() ?: 0) +
            (outputs.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (sessionId.asKnown().isPresent) 1 else 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
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

            @JvmField val DREAM = of("dream")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            DREAM
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
            DREAM,
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
                DREAM -> Value.DREAM
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
                DREAM -> Known.DREAM
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

        return other is BetaDream &&
            id == other.id &&
            archivedAt == other.archivedAt &&
            createdAt == other.createdAt &&
            endedAt == other.endedAt &&
            error == other.error &&
            inputs == other.inputs &&
            instructions == other.instructions &&
            model == other.model &&
            outputs == other.outputs &&
            sessionId == other.sessionId &&
            status == other.status &&
            type == other.type &&
            usage == other.usage &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            archivedAt,
            createdAt,
            endedAt,
            error,
            inputs,
            instructions,
            model,
            outputs,
            sessionId,
            status,
            type,
            usage,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaDream{id=$id, archivedAt=$archivedAt, createdAt=$createdAt, endedAt=$endedAt, error=$error, inputs=$inputs, instructions=$instructions, model=$model, outputs=$outputs, sessionId=$sessionId, status=$status, type=$type, usage=$usage, additionalProperties=$additionalProperties}"
}
