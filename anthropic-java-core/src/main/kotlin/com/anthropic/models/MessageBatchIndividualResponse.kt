// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.toImmutable
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.Objects

@JsonDeserialize(builder = MessageBatchIndividualResponse.Builder::class)
@NoAutoDetect
class MessageBatchIndividualResponse
private constructor(
    private val customId: JsonField<String>,
    private val result: JsonField<MessageBatchResult>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    /**
     * Developer-provided ID created for each request in a Message Batch. Useful for matching
     * results to requests, as results may be given out of request order.
     *
     * Must be unique for each request within the Message Batch.
     */
    fun customId(): String = customId.getRequired("custom_id")

    /**
     * Processing result for this request.
     *
     * Contains a Message output if processing was successful, an error response if processing
     * failed, or the reason why processing was not attempted, such as cancellation or expiration.
     */
    fun result(): MessageBatchResult = result.getRequired("result")

    /**
     * Developer-provided ID created for each request in a Message Batch. Useful for matching
     * results to requests, as results may be given out of request order.
     *
     * Must be unique for each request within the Message Batch.
     */
    @JsonProperty("custom_id") @ExcludeMissing fun _customId() = customId

    /**
     * Processing result for this request.
     *
     * Contains a Message output if processing was successful, an error response if processing
     * failed, or the reason why processing was not attempted, such as cancellation or expiration.
     */
    @JsonProperty("result") @ExcludeMissing fun _result() = result

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): MessageBatchIndividualResponse = apply {
        if (!validated) {
            customId()
            result()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var customId: JsonField<String> = JsonMissing.of()
        private var result: JsonField<MessageBatchResult> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(messageBatchIndividualResponse: MessageBatchIndividualResponse) = apply {
            this.customId = messageBatchIndividualResponse.customId
            this.result = messageBatchIndividualResponse.result
            additionalProperties(messageBatchIndividualResponse.additionalProperties)
        }

        /**
         * Developer-provided ID created for each request in a Message Batch. Useful for matching
         * results to requests, as results may be given out of request order.
         *
         * Must be unique for each request within the Message Batch.
         */
        fun customId(customId: String) = customId(JsonField.of(customId))

        /**
         * Developer-provided ID created for each request in a Message Batch. Useful for matching
         * results to requests, as results may be given out of request order.
         *
         * Must be unique for each request within the Message Batch.
         */
        @JsonProperty("custom_id")
        @ExcludeMissing
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
         * Processing result for this request.
         *
         * Contains a Message output if processing was successful, an error response if processing
         * failed, or the reason why processing was not attempted, such as cancellation or
         * expiration.
         */
        @JsonProperty("result")
        @ExcludeMissing
        fun result(result: JsonField<MessageBatchResult>) = apply { this.result = result }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            this.additionalProperties.putAll(additionalProperties)
        }

        @JsonAnySetter
        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            this.additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun build(): MessageBatchIndividualResponse =
            MessageBatchIndividualResponse(
                customId,
                result,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is MessageBatchIndividualResponse && customId == other.customId && result == other.result && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(customId, result, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "MessageBatchIndividualResponse{customId=$customId, result=$result, additionalProperties=$additionalProperties}"
}
