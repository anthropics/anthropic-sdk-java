// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.ErrorResponse
import com.anthropic.models.messages.Message
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * This is a single line in the response `.jsonl` file and does not represent the response as a
 * whole.
 */
class MessageBatchIndividualResponse
private constructor(
    private val customId: JsonField<String>,
    private val result: JsonField<MessageBatchResult>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("custom_id") @ExcludeMissing customId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("result")
        @ExcludeMissing
        result: JsonField<MessageBatchResult> = JsonMissing.of(),
    ) : this(customId, result, mutableMapOf())

    /**
     * Developer-provided ID created for each request in a Message Batch. Useful for matching
     * results to requests, as results may be given out of request order.
     *
     * Must be unique for each request within the Message Batch.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun customId(): String = customId.getRequired("custom_id")

    /**
     * Processing result for this request.
     *
     * Contains a Message output if processing was successful, an error response if processing
     * failed, or the reason why processing was not attempted, such as cancellation or expiration.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun result(): MessageBatchResult = result.getRequired("result")

    /**
     * Returns the raw JSON value of [customId].
     *
     * Unlike [customId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("custom_id") @ExcludeMissing fun _customId(): JsonField<String> = customId

    /**
     * Returns the raw JSON value of [result].
     *
     * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("result") @ExcludeMissing fun _result(): JsonField<MessageBatchResult> = result

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
         * [MessageBatchIndividualResponse].
         *
         * The following fields are required:
         * ```java
         * .customId()
         * .result()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MessageBatchIndividualResponse]. */
    class Builder internal constructor() {

        private var customId: JsonField<String>? = null
        private var result: JsonField<MessageBatchResult>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(messageBatchIndividualResponse: MessageBatchIndividualResponse) = apply {
            customId = messageBatchIndividualResponse.customId
            result = messageBatchIndividualResponse.result
            additionalProperties =
                messageBatchIndividualResponse.additionalProperties.toMutableMap()
        }

        /**
         * Developer-provided ID created for each request in a Message Batch. Useful for matching
         * results to requests, as results may be given out of request order.
         *
         * Must be unique for each request within the Message Batch.
         */
        fun customId(customId: String) = customId(JsonField.of(customId))

        /**
         * Sets [Builder.customId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.customId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun customId(customId: JsonField<String>) = apply { this.customId = customId }

        /**
         * Processing result for this request.
         *
         * Contains a Message output if processing was successful, an error response if processing
         * failed, or the reason why processing was not attempted, such as cancellation or
         * expiration.
         */
        fun result(result: MessageBatchResult) = result(JsonField.of(result))

        /**
         * Sets [Builder.result] to an arbitrary JSON value.
         *
         * You should usually call [Builder.result] with a well-typed [MessageBatchResult] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun result(result: JsonField<MessageBatchResult>) = apply { this.result = result }

        /** Alias for calling [result] with `MessageBatchResult.ofSucceeded(succeeded)`. */
        fun result(succeeded: MessageBatchSucceededResult) =
            result(MessageBatchResult.ofSucceeded(succeeded))

        /**
         * Alias for calling [result] with the following:
         * ```java
         * MessageBatchSucceededResult.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun succeededResult(message: Message) =
            result(MessageBatchSucceededResult.builder().message(message).build())

        /** Alias for calling [result] with `MessageBatchResult.ofErrored(errored)`. */
        fun result(errored: MessageBatchErroredResult) =
            result(MessageBatchResult.ofErrored(errored))

        /**
         * Alias for calling [result] with the following:
         * ```java
         * MessageBatchErroredResult.builder()
         *     .error(error)
         *     .build()
         * ```
         */
        fun erroredResult(error: ErrorResponse) =
            result(MessageBatchErroredResult.builder().error(error).build())

        /** Alias for calling [result] with `MessageBatchResult.ofCanceled(canceled)`. */
        fun result(canceled: MessageBatchCanceledResult) =
            result(MessageBatchResult.ofCanceled(canceled))

        /** Alias for calling [result] with `MessageBatchResult.ofExpired(expired)`. */
        fun result(expired: MessageBatchExpiredResult) =
            result(MessageBatchResult.ofExpired(expired))

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
         * Returns an immutable instance of [MessageBatchIndividualResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .customId()
         * .result()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): MessageBatchIndividualResponse =
            MessageBatchIndividualResponse(
                checkRequired("customId", customId),
                checkRequired("result", result),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): MessageBatchIndividualResponse = apply {
        if (validated) {
            return@apply
        }

        customId()
        result().validate()
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
        (if (customId.asKnown().isPresent) 1 else 0) +
            (result.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MessageBatchIndividualResponse &&
            customId == other.customId &&
            result == other.result &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(customId, result, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "MessageBatchIndividualResponse{customId=$customId, result=$result, additionalProperties=$additionalProperties}"
}
