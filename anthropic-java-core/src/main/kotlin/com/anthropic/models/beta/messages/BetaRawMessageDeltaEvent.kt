package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.core.toImmutable
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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaRawMessageDeltaEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val contextManagement: JsonField<BetaContextManagementResponse>,
    private val delta: JsonField<Delta>,
    private val type: JsonValue,
    private val usage: JsonField<BetaMessageDeltaUsage>,
    private val inputTransformations: JsonField<List<InputTransformation>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("context_management")
        @ExcludeMissing
        contextManagement: JsonField<BetaContextManagementResponse> = JsonMissing.of(),
        @JsonProperty("delta") @ExcludeMissing delta: JsonField<Delta> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("usage")
        @ExcludeMissing
        usage: JsonField<BetaMessageDeltaUsage> = JsonMissing.of(),
        @JsonProperty("input_transformations")
        @ExcludeMissing
        inputTransformations: JsonField<List<InputTransformation>> = JsonMissing.of(),
    ) : this(contextManagement, delta, type, usage, inputTransformations, mutableMapOf())

    /**
     * Information about context management strategies applied during the request
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun contextManagement(): Optional<BetaContextManagementResponse> =
        contextManagement.getOptional("context_management")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun delta(): Delta = delta.getRequired("delta")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("message_delta")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Billing and rate-limit usage.
     *
     * Anthropic's API bills and rate-limits by token counts, as tokens represent the underlying
     * cost to our systems.
     *
     * Under the hood, the API transforms requests into a format suitable for the model. The model's
     * output then goes through a parsing stage before becoming an API response. As a result, the
     * token counts in `usage` will not match one-to-one with the exact visible content of an API
     * request or response.
     *
     * For example, `output_tokens` will be non-zero, even for an empty string response from Claude.
     *
     * Total input tokens in a request is the summation of `input_tokens`,
     * `cache_creation_input_tokens`, and `cache_read_input_tokens`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun usage(): BetaMessageDeltaUsage = usage.getRequired("usage")

    /**
     * Changes the API made to the request's input before showing it to the model, and blocks that
     * failed a binding check but were left unchanged: one entry per block, in request order. Two
     * entry types today. `thinking_dropped` — a `thinking`, `redacted_thinking` or `connector_text`
     * block from the request's `messages` that was removed from the prompt instead of being shown
     * to the model because it failed a binding check. `thinking_mismatch_allowed` — a `thinking` or
     * `redacted_thinking` block that failed the conversation check (the conversation before it
     * differs from the one it was created in, or it carries no record of one on a model that
     * requires it) and was shown to the model all the same, because that check is not enforced for
     * this request. More entry types may be added over time; ignore types you do not recognize.
     *
     * Requires `anthropic-beta: thinking-binding-controls-2026-08-01`. Present on every such
     * response from a model that supports extended thinking, as `[]` when there is no entry to
     * report; without the beta, blocks are removed or left in place all the same but nothing is
     * reported. Removed blocks contribute nothing to `usage.input_tokens`; blocks left in place
     * count as sent. When streaming, the array is final in `message_start`; the final
     * `message_delta` event carries it only when a server-side model fallback happened mid-stream,
     * in which case it holds the serving model's entries and replaces the one in `message_start`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun inputTransformations(): Optional<List<InputTransformation>> =
        inputTransformations.getOptional("input_transformations")

    /**
     * Returns the raw JSON value of [contextManagement].
     *
     * Unlike [contextManagement], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("context_management")
    @ExcludeMissing
    fun _contextManagement(): JsonField<BetaContextManagementResponse> = contextManagement

    /**
     * Returns the raw JSON value of [delta].
     *
     * Unlike [delta], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("delta") @ExcludeMissing fun _delta(): JsonField<Delta> = delta

    /**
     * Returns the raw JSON value of [usage].
     *
     * Unlike [usage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("usage") @ExcludeMissing fun _usage(): JsonField<BetaMessageDeltaUsage> = usage

    /**
     * Returns the raw JSON value of [inputTransformations].
     *
     * Unlike [inputTransformations], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("input_transformations")
    @ExcludeMissing
    fun _inputTransformations(): JsonField<List<InputTransformation>> = inputTransformations

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
         * Returns a mutable builder for constructing an instance of [BetaRawMessageDeltaEvent].
         *
         * The following fields are required:
         * ```java
         * .contextManagement()
         * .delta()
         * .usage()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRawMessageDeltaEvent]. */
    class Builder internal constructor() {

        private var contextManagement: JsonField<BetaContextManagementResponse>? = null
        private var delta: JsonField<Delta>? = null
        private var type: JsonValue = JsonValue.from("message_delta")
        private var usage: JsonField<BetaMessageDeltaUsage>? = null
        private var inputTransformations: JsonField<MutableList<InputTransformation>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRawMessageDeltaEvent: BetaRawMessageDeltaEvent) = apply {
            contextManagement = betaRawMessageDeltaEvent.contextManagement
            delta = betaRawMessageDeltaEvent.delta
            type = betaRawMessageDeltaEvent.type
            usage = betaRawMessageDeltaEvent.usage
            inputTransformations =
                betaRawMessageDeltaEvent.inputTransformations
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties = betaRawMessageDeltaEvent.additionalProperties.toMutableMap()
        }

        /** Information about context management strategies applied during the request */
        fun contextManagement(contextManagement: BetaContextManagementResponse?) =
            contextManagement(JsonField.ofNullable(contextManagement))

        /** Alias for calling [Builder.contextManagement] with `contextManagement.orElse(null)`. */
        fun contextManagement(contextManagement: Optional<BetaContextManagementResponse>) =
            contextManagement(contextManagement.getOrNull())

        /**
         * Sets [Builder.contextManagement] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contextManagement] with a well-typed
         * [BetaContextManagementResponse] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun contextManagement(contextManagement: JsonField<BetaContextManagementResponse>) = apply {
            this.contextManagement = contextManagement
        }

        fun delta(delta: Delta) = delta(JsonField.of(delta))

        /**
         * Sets [Builder.delta] to an arbitrary JSON value.
         *
         * You should usually call [Builder.delta] with a well-typed [Delta] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun delta(delta: JsonField<Delta>) = apply { this.delta = delta }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("message_delta")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Billing and rate-limit usage.
         *
         * Anthropic's API bills and rate-limits by token counts, as tokens represent the underlying
         * cost to our systems.
         *
         * Under the hood, the API transforms requests into a format suitable for the model. The
         * model's output then goes through a parsing stage before becoming an API response. As a
         * result, the token counts in `usage` will not match one-to-one with the exact visible
         * content of an API request or response.
         *
         * For example, `output_tokens` will be non-zero, even for an empty string response from
         * Claude.
         *
         * Total input tokens in a request is the summation of `input_tokens`,
         * `cache_creation_input_tokens`, and `cache_read_input_tokens`.
         */
        fun usage(usage: BetaMessageDeltaUsage) = usage(JsonField.of(usage))

        /**
         * Sets [Builder.usage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.usage] with a well-typed [BetaMessageDeltaUsage] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun usage(usage: JsonField<BetaMessageDeltaUsage>) = apply { this.usage = usage }

        /**
         * Changes the API made to the request's input before showing it to the model, and blocks
         * that failed a binding check but were left unchanged: one entry per block, in request
         * order. Two entry types today. `thinking_dropped` — a `thinking`, `redacted_thinking` or
         * `connector_text` block from the request's `messages` that was removed from the prompt
         * instead of being shown to the model because it failed a binding check.
         * `thinking_mismatch_allowed` — a `thinking` or `redacted_thinking` block that failed the
         * conversation check (the conversation before it differs from the one it was created in, or
         * it carries no record of one on a model that requires it) and was shown to the model all
         * the same, because that check is not enforced for this request. More entry types may be
         * added over time; ignore types you do not recognize.
         *
         * Requires `anthropic-beta: thinking-binding-controls-2026-08-01`. Present on every such
         * response from a model that supports extended thinking, as `[]` when there is no entry to
         * report; without the beta, blocks are removed or left in place all the same but nothing is
         * reported. Removed blocks contribute nothing to `usage.input_tokens`; blocks left in place
         * count as sent. When streaming, the array is final in `message_start`; the final
         * `message_delta` event carries it only when a server-side model fallback happened
         * mid-stream, in which case it holds the serving model's entries and replaces the one in
         * `message_start`.
         */
        fun inputTransformations(inputTransformations: List<InputTransformation>?) =
            inputTransformations(JsonField.ofNullable(inputTransformations))

        /**
         * Alias for calling [Builder.inputTransformations] with
         * `inputTransformations.orElse(null)`.
         */
        fun inputTransformations(inputTransformations: Optional<List<InputTransformation>>) =
            inputTransformations(inputTransformations.getOrNull())

        /**
         * Sets [Builder.inputTransformations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputTransformations] with a well-typed
         * `List<InputTransformation>` value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun inputTransformations(inputTransformations: JsonField<List<InputTransformation>>) =
            apply {
                this.inputTransformations = inputTransformations.map { it.toMutableList() }
            }

        /**
         * Adds a single [InputTransformation] to [inputTransformations].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addInputTransformation(inputTransformation: InputTransformation) = apply {
            inputTransformations =
                (inputTransformations ?: JsonField.of(mutableListOf())).also {
                    checkKnown("inputTransformations", it).add(inputTransformation)
                }
        }

        /**
         * Alias for calling [addInputTransformation] with
         * `InputTransformation.ofThinkingDropped(thinkingDropped)`.
         */
        fun addInputTransformation(thinkingDropped: BetaThinkingDroppedInputTransformation) =
            addInputTransformation(InputTransformation.ofThinkingDropped(thinkingDropped))

        /**
         * Alias for calling [addInputTransformation] with
         * `InputTransformation.ofThinkingMismatchAllowed(thinkingMismatchAllowed)`.
         */
        fun addInputTransformation(
            thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
        ) =
            addInputTransformation(
                InputTransformation.ofThinkingMismatchAllowed(thinkingMismatchAllowed)
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
         * Returns an immutable instance of [BetaRawMessageDeltaEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .contextManagement()
         * .delta()
         * .usage()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaRawMessageDeltaEvent =
            BetaRawMessageDeltaEvent(
                checkRequired("contextManagement", contextManagement),
                checkRequired("delta", delta),
                type,
                checkRequired("usage", usage),
                (inputTransformations ?: JsonMissing.of()).map { it.toImmutable() },
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
    fun validate(): BetaRawMessageDeltaEvent = apply {
        if (validated) {
            return@apply
        }

        contextManagement().ifPresent { it.validate() }
        delta().validate()
        _type().let {
            if (it != JsonValue.from("message_delta")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        usage().validate()
        inputTransformations().ifPresent { it.forEach { it.validate() } }
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
        (contextManagement.asKnown().getOrNull()?.validity() ?: 0) +
            (delta.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("message_delta")) 1 else 0 } +
            (usage.asKnown().getOrNull()?.validity() ?: 0) +
            (inputTransformations.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    class Delta
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val container: JsonField<BetaContainer>,
        private val stopDetails: JsonField<BetaRefusalStopDetails>,
        private val stopReason: JsonField<BetaStopReason>,
        private val stopSequence: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("container")
            @ExcludeMissing
            container: JsonField<BetaContainer> = JsonMissing.of(),
            @JsonProperty("stop_details")
            @ExcludeMissing
            stopDetails: JsonField<BetaRefusalStopDetails> = JsonMissing.of(),
            @JsonProperty("stop_reason")
            @ExcludeMissing
            stopReason: JsonField<BetaStopReason> = JsonMissing.of(),
            @JsonProperty("stop_sequence")
            @ExcludeMissing
            stopSequence: JsonField<String> = JsonMissing.of(),
        ) : this(container, stopDetails, stopReason, stopSequence, mutableMapOf())

        /**
         * Information about the container used in the request (for the code execution tool)
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun container(): Optional<BetaContainer> = container.getOptional("container")

        /**
         * Structured information about a refusal.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun stopDetails(): Optional<BetaRefusalStopDetails> =
            stopDetails.getOptional("stop_details")

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun stopReason(): Optional<BetaStopReason> = stopReason.getOptional("stop_reason")

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun stopSequence(): Optional<String> = stopSequence.getOptional("stop_sequence")

        /**
         * Returns the raw JSON value of [container].
         *
         * Unlike [container], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("container")
        @ExcludeMissing
        fun _container(): JsonField<BetaContainer> = container

        /**
         * Returns the raw JSON value of [stopDetails].
         *
         * Unlike [stopDetails], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("stop_details")
        @ExcludeMissing
        fun _stopDetails(): JsonField<BetaRefusalStopDetails> = stopDetails

        /**
         * Returns the raw JSON value of [stopReason].
         *
         * Unlike [stopReason], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("stop_reason")
        @ExcludeMissing
        fun _stopReason(): JsonField<BetaStopReason> = stopReason

        /**
         * Returns the raw JSON value of [stopSequence].
         *
         * Unlike [stopSequence], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("stop_sequence")
        @ExcludeMissing
        fun _stopSequence(): JsonField<String> = stopSequence

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
             * Returns a mutable builder for constructing an instance of [Delta].
             *
             * The following fields are required:
             * ```java
             * .container()
             * .stopDetails()
             * .stopReason()
             * .stopSequence()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Delta]. */
        class Builder internal constructor() {

            private var container: JsonField<BetaContainer>? = null
            private var stopDetails: JsonField<BetaRefusalStopDetails>? = null
            private var stopReason: JsonField<BetaStopReason>? = null
            private var stopSequence: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(delta: Delta) = apply {
                container = delta.container
                stopDetails = delta.stopDetails
                stopReason = delta.stopReason
                stopSequence = delta.stopSequence
                additionalProperties = delta.additionalProperties.toMutableMap()
            }

            /** Information about the container used in the request (for the code execution tool) */
            fun container(container: BetaContainer?) = container(JsonField.ofNullable(container))

            /** Alias for calling [Builder.container] with `container.orElse(null)`. */
            fun container(container: Optional<BetaContainer>) = container(container.getOrNull())

            /**
             * Sets [Builder.container] to an arbitrary JSON value.
             *
             * You should usually call [Builder.container] with a well-typed [BetaContainer] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun container(container: JsonField<BetaContainer>) = apply {
                this.container = container
            }

            /** Structured information about a refusal. */
            fun stopDetails(stopDetails: BetaRefusalStopDetails?) =
                stopDetails(JsonField.ofNullable(stopDetails))

            /** Alias for calling [Builder.stopDetails] with `stopDetails.orElse(null)`. */
            fun stopDetails(stopDetails: Optional<BetaRefusalStopDetails>) =
                stopDetails(stopDetails.getOrNull())

            /**
             * Sets [Builder.stopDetails] to an arbitrary JSON value.
             *
             * You should usually call [Builder.stopDetails] with a well-typed
             * [BetaRefusalStopDetails] value instead. This method is primarily for setting the
             * field to an undocumented or not yet supported value.
             */
            fun stopDetails(stopDetails: JsonField<BetaRefusalStopDetails>) = apply {
                this.stopDetails = stopDetails
            }

            fun stopReason(stopReason: BetaStopReason?) =
                stopReason(JsonField.ofNullable(stopReason))

            /** Alias for calling [Builder.stopReason] with `stopReason.orElse(null)`. */
            fun stopReason(stopReason: Optional<BetaStopReason>) =
                stopReason(stopReason.getOrNull())

            /**
             * Sets [Builder.stopReason] to an arbitrary JSON value.
             *
             * You should usually call [Builder.stopReason] with a well-typed [BetaStopReason] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun stopReason(stopReason: JsonField<BetaStopReason>) = apply {
                this.stopReason = stopReason
            }

            fun stopSequence(stopSequence: String?) =
                stopSequence(JsonField.ofNullable(stopSequence))

            /** Alias for calling [Builder.stopSequence] with `stopSequence.orElse(null)`. */
            fun stopSequence(stopSequence: Optional<String>) =
                stopSequence(stopSequence.getOrNull())

            /**
             * Sets [Builder.stopSequence] to an arbitrary JSON value.
             *
             * You should usually call [Builder.stopSequence] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun stopSequence(stopSequence: JsonField<String>) = apply {
                this.stopSequence = stopSequence
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
             * Returns an immutable instance of [Delta].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .container()
             * .stopDetails()
             * .stopReason()
             * .stopSequence()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Delta =
                Delta(
                    checkRequired("container", container),
                    checkRequired("stopDetails", stopDetails),
                    checkRequired("stopReason", stopReason),
                    checkRequired("stopSequence", stopSequence),
                    additionalProperties.toMutableMap(),
                )
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
        fun validate(): Delta = apply {
            if (validated) {
                return@apply
            }

            container().ifPresent { it.validate() }
            stopDetails().ifPresent { it.validate() }
            stopReason().ifPresent { it.validate() }
            stopSequence()
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
            (container.asKnown().getOrNull()?.validity() ?: 0) +
                (stopDetails.asKnown().getOrNull()?.validity() ?: 0) +
                (stopReason.asKnown().getOrNull()?.validity() ?: 0) +
                (if (stopSequence.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Delta &&
                container == other.container &&
                stopDetails == other.stopDetails &&
                stopReason == other.stopReason &&
                stopSequence == other.stopSequence &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(container, stopDetails, stopReason, stopSequence, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Delta{container=$container, stopDetails=$stopDetails, stopReason=$stopReason, stopSequence=$stopSequence, additionalProperties=$additionalProperties}"
    }

    @JsonDeserialize(using = InputTransformation.Deserializer::class)
    @JsonSerialize(using = InputTransformation.Serializer::class)
    class InputTransformation
    private constructor(
        private val thinkingDropped: BetaThinkingDroppedInputTransformation? = null,
        private val thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitThinkingDropped(
                        thinkingDropped: BetaThinkingDroppedInputTransformation
                    ): Type = Type.THINKING_DROPPED

                    override fun visitThinkingMismatchAllowed(
                        thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
                    ): Type = Type.THINKING_MISMATCH_ALLOWED

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun path(): String =
            accept(
                object : Visitor<String> {
                    override fun visitThinkingDropped(
                        thinkingDropped: BetaThinkingDroppedInputTransformation
                    ): String = thinkingDropped.path()

                    override fun visitThinkingMismatchAllowed(
                        thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
                    ): String = thinkingMismatchAllowed.path()

                    override fun unknown(json: JsonValue?): String =
                        json.getProperty<String>("path").getRequired("path")
                }
            )

        fun thinkingDropped(): Optional<BetaThinkingDroppedInputTransformation> =
            Optional.ofNullable(thinkingDropped)

        fun thinkingMismatchAllowed(): Optional<BetaThinkingMismatchAllowedInputTransformation> =
            Optional.ofNullable(thinkingMismatchAllowed)

        fun isThinkingDropped(): Boolean = thinkingDropped != null

        fun isThinkingMismatchAllowed(): Boolean = thinkingMismatchAllowed != null

        fun asThinkingDropped(): BetaThinkingDroppedInputTransformation =
            thinkingDropped.getOrThrow("thinkingDropped")

        fun asThinkingMismatchAllowed(): BetaThinkingMismatchAllowedInputTransformation =
            thinkingMismatchAllowed.getOrThrow("thinkingMismatchAllowed")

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
         * Optional<String> result = inputTransformation.accept(new InputTransformation.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitThinkingDropped(BetaThinkingDroppedInputTransformation thinkingDropped) {
         *         return Optional.of(thinkingDropped.toString());
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
                thinkingDropped != null -> visitor.visitThinkingDropped(thinkingDropped)
                thinkingMismatchAllowed != null ->
                    visitor.visitThinkingMismatchAllowed(thinkingMismatchAllowed)
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
        fun validate(): InputTransformation = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitThinkingDropped(
                        thinkingDropped: BetaThinkingDroppedInputTransformation
                    ) {
                        thinkingDropped.validate()
                    }

                    override fun visitThinkingMismatchAllowed(
                        thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
                    ) {
                        thinkingMismatchAllowed.validate()
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
                    override fun visitThinkingDropped(
                        thinkingDropped: BetaThinkingDroppedInputTransformation
                    ) = thinkingDropped.validity()

                    override fun visitThinkingMismatchAllowed(
                        thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
                    ) = thinkingMismatchAllowed.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is InputTransformation &&
                thinkingDropped == other.thinkingDropped &&
                thinkingMismatchAllowed == other.thinkingMismatchAllowed
        }

        override fun hashCode(): Int = Objects.hash(thinkingDropped, thinkingMismatchAllowed)

        override fun toString(): String =
            when {
                thinkingDropped != null -> "InputTransformation{thinkingDropped=$thinkingDropped}"
                thinkingMismatchAllowed != null ->
                    "InputTransformation{thinkingMismatchAllowed=$thinkingMismatchAllowed}"
                _json != null -> "InputTransformation{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid InputTransformation")
            }

        companion object {

            @JvmStatic
            fun ofThinkingDropped(thinkingDropped: BetaThinkingDroppedInputTransformation) =
                InputTransformation(thinkingDropped = thinkingDropped)

            @JvmStatic
            fun ofThinkingMismatchAllowed(
                thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
            ) = InputTransformation(thinkingMismatchAllowed = thinkingMismatchAllowed)
        }

        /**
         * An interface that defines how to map each variant of [InputTransformation] to a value of
         * type [T].
         */
        interface Visitor<out T> {

            fun visitThinkingDropped(thinkingDropped: BetaThinkingDroppedInputTransformation): T

            fun visitThinkingMismatchAllowed(
                thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
            ): T

            /**
             * Maps an unknown variant of [InputTransformation] to a value of type [T].
             *
             * An instance of [InputTransformation] can contain an unknown variant if it was
             * deserialized from data that doesn't match any known variant. For example, if the SDK
             * is on an older version than the API, then the API may respond with new variants that
             * the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown InputTransformation: $json")
            }
        }

        internal class Deserializer :
            BaseDeserializer<InputTransformation>(InputTransformation::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): InputTransformation {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "thinking_dropped" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaThinkingDroppedInputTransformation>(),
                            )
                            ?.let { InputTransformation(thinkingDropped = it, _json = json) }
                            ?: InputTransformation(_json = json)
                    }
                    "thinking_mismatch_allowed" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaThinkingMismatchAllowedInputTransformation>(),
                            )
                            ?.let {
                                InputTransformation(thinkingMismatchAllowed = it, _json = json)
                            } ?: InputTransformation(_json = json)
                    }
                }

                return InputTransformation(_json = json)
            }
        }

        internal class Serializer :
            BaseSerializer<InputTransformation>(InputTransformation::class) {

            override fun serialize(
                value: InputTransformation,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.thinkingDropped != null -> generator.writeObject(value.thinkingDropped)
                    value.thinkingMismatchAllowed != null ->
                        generator.writeObject(value.thinkingMismatchAllowed)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid InputTransformation")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val THINKING_DROPPED = of("thinking_dropped")

                @JvmField val THINKING_MISMATCH_ALLOWED = of("thinking_mismatch_allowed")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                THINKING_DROPPED,
                THINKING_MISMATCH_ALLOWED,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                THINKING_DROPPED,
                THINKING_MISMATCH_ALLOWED,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    THINKING_DROPPED -> Value.THINKING_DROPPED
                    THINKING_MISMATCH_ALLOWED -> Value.THINKING_MISMATCH_ALLOWED
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    THINKING_DROPPED -> Known.THINKING_DROPPED
                    THINKING_MISMATCH_ALLOWED -> Known.THINKING_MISMATCH_ALLOWED
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
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
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
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
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRawMessageDeltaEvent &&
            contextManagement == other.contextManagement &&
            delta == other.delta &&
            type == other.type &&
            usage == other.usage &&
            inputTransformations == other.inputTransformations &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            contextManagement,
            delta,
            type,
            usage,
            inputTransformations,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRawMessageDeltaEvent{contextManagement=$contextManagement, delta=$delta, type=$type, usage=$usage, inputTransformations=$inputTransformations, additionalProperties=$additionalProperties}"
}
