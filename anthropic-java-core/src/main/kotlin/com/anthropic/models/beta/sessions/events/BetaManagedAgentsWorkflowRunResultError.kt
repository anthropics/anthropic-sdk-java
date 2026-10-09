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
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/** The run failed or reached its time limit. */
class BetaManagedAgentsWorkflowRunResultError
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val error: JsonField<BetaManagedAgentsWorkflowRunError>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error")
        @ExcludeMissing
        error: JsonField<BetaManagedAgentsWorkflowRunError> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(error, type, mutableMapOf())

    /**
     * Why the run did not finish.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun error(): BetaManagedAgentsWorkflowRunError = error.getRequired("error")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("error")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [error].
     *
     * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error")
    @ExcludeMissing
    fun _error(): JsonField<BetaManagedAgentsWorkflowRunError> = error

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
         * [BetaManagedAgentsWorkflowRunResultError].
         *
         * The following fields are required:
         * ```java
         * .error()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunResultError] with the
         * required [error] set to the given value.
         */
        @JvmStatic fun of(error: BetaManagedAgentsWorkflowRunError) = builder().error(error).build()

        /** Alias for calling [of] with `BetaManagedAgentsWorkflowRunError.ofTimeout(timeout)`. */
        @JvmStatic
        fun of(timeout: BetaManagedAgentsTimeoutWorkflowRunError) =
            of(BetaManagedAgentsWorkflowRunError.ofTimeout(timeout))

        /** Alias for calling [of] with `BetaManagedAgentsWorkflowRunError.ofProgram(program)`. */
        @JvmStatic
        fun of(program: BetaManagedAgentsProgramWorkflowRunError) =
            of(BetaManagedAgentsWorkflowRunError.ofProgram(program))

        /** Alias for calling [of] with `BetaManagedAgentsWorkflowRunError.ofUnknown(unknown)`. */
        @JvmStatic
        fun of(unknown: BetaManagedAgentsUnknownWorkflowRunError) =
            of(BetaManagedAgentsWorkflowRunError.ofUnknown(unknown))

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsWorkflowRunError.ofThreadLimit(threadLimit)`.
         */
        @JvmStatic
        fun of(threadLimit: BetaManagedAgentsThreadLimitWorkflowRunError) =
            of(BetaManagedAgentsWorkflowRunError.ofThreadLimit(threadLimit))

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(maxWorkflowRuns)`.
         */
        @JvmStatic
        fun of(maxWorkflowRuns: BetaManagedAgentsMaxWorkflowRunsWorkflowRunError) =
            of(BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(maxWorkflowRuns))
    }

    /** A builder for [BetaManagedAgentsWorkflowRunResultError]. */
    class Builder internal constructor() {

        private var error: JsonField<BetaManagedAgentsWorkflowRunError>? = null
        private var type: JsonValue = JsonValue.from("error")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsWorkflowRunResultError: BetaManagedAgentsWorkflowRunResultError
        ) = apply {
            error = betaManagedAgentsWorkflowRunResultError.error
            type = betaManagedAgentsWorkflowRunResultError.type
            additionalProperties =
                betaManagedAgentsWorkflowRunResultError.additionalProperties.toMutableMap()
        }

        /** Why the run did not finish. */
        fun error(error: BetaManagedAgentsWorkflowRunError) = error(JsonField.of(error))

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed
         * [BetaManagedAgentsWorkflowRunError] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun error(error: JsonField<BetaManagedAgentsWorkflowRunError>) = apply {
            this.error = error
        }

        /**
         * Alias for calling [error] with `BetaManagedAgentsWorkflowRunError.ofTimeout(timeout)`.
         */
        fun error(timeout: BetaManagedAgentsTimeoutWorkflowRunError) =
            error(BetaManagedAgentsWorkflowRunError.ofTimeout(timeout))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsTimeoutWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun timeoutError(message: String) =
            error(BetaManagedAgentsTimeoutWorkflowRunError.builder().message(message).build())

        /**
         * Alias for calling [error] with `BetaManagedAgentsWorkflowRunError.ofProgram(program)`.
         */
        fun error(program: BetaManagedAgentsProgramWorkflowRunError) =
            error(BetaManagedAgentsWorkflowRunError.ofProgram(program))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsProgramWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun programError(message: String) =
            error(BetaManagedAgentsProgramWorkflowRunError.builder().message(message).build())

        /**
         * Alias for calling [error] with `BetaManagedAgentsWorkflowRunError.ofUnknown(unknown)`.
         */
        fun error(unknown: BetaManagedAgentsUnknownWorkflowRunError) =
            error(BetaManagedAgentsWorkflowRunError.ofUnknown(unknown))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsUnknownWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun unknownError(message: String) =
            error(BetaManagedAgentsUnknownWorkflowRunError.builder().message(message).build())

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsWorkflowRunError.ofThreadLimit(threadLimit)`.
         */
        fun error(threadLimit: BetaManagedAgentsThreadLimitWorkflowRunError) =
            error(BetaManagedAgentsWorkflowRunError.ofThreadLimit(threadLimit))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsThreadLimitWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun threadLimitError(message: String) =
            error(BetaManagedAgentsThreadLimitWorkflowRunError.builder().message(message).build())

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(maxWorkflowRuns)`.
         */
        fun error(maxWorkflowRuns: BetaManagedAgentsMaxWorkflowRunsWorkflowRunError) =
            error(BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(maxWorkflowRuns))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsMaxWorkflowRunsWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun maxWorkflowRunsError(message: String) =
            error(
                BetaManagedAgentsMaxWorkflowRunsWorkflowRunError.builder().message(message).build()
            )

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("error")
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
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunResultError].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .error()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsWorkflowRunResultError =
            BetaManagedAgentsWorkflowRunResultError(
                checkRequired("error", error),
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
    fun validate(): BetaManagedAgentsWorkflowRunResultError = apply {
        if (validated) {
            return@apply
        }

        error().validate()
        _type().let {
            if (it != JsonValue.from("error")) {
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
        (error.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("error")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWorkflowRunResultError &&
            error == other.error &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(error, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsWorkflowRunResultError{error=$error, type=$type, additionalProperties=$additionalProperties}"
}
