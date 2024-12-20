// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

@NoAutoDetect
class BetaMessageBatchIndividualResponse
@JsonCreator
private constructor(
    @JsonProperty("custom_id")
    @ExcludeMissing
    private val customId: JsonField<String> = JsonMissing.of(),
    @JsonProperty("result")
    @ExcludeMissing
    private val result: JsonField<BetaMessageBatchResult> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

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
    fun result(): BetaMessageBatchResult = result.getRequired("result")

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

    private var validated: Boolean = false

    fun validate(): BetaMessageBatchIndividualResponse = apply {
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
        private var result: JsonField<BetaMessageBatchResult> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageBatchIndividualResponse: BetaMessageBatchIndividualResponse) =
            apply {
                customId = betaMessageBatchIndividualResponse.customId
                result = betaMessageBatchIndividualResponse.result
                additionalProperties =
                    betaMessageBatchIndividualResponse.additionalProperties.toMutableMap()
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
        fun customId(customId: JsonField<String>) = apply { this.customId = customId }

        /**
         * Processing result for this request.
         *
         * Contains a Message output if processing was successful, an error response if processing
         * failed, or the reason why processing was not attempted, such as cancellation or
         * expiration.
         */
        fun result(result: BetaMessageBatchResult) = result(JsonField.of(result))

        /**
         * Processing result for this request.
         *
         * Contains a Message output if processing was successful, an error response if processing
         * failed, or the reason why processing was not attempted, such as cancellation or
         * expiration.
         */
        fun result(result: JsonField<BetaMessageBatchResult>) = apply { this.result = result }

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

        fun build(): BetaMessageBatchIndividualResponse =
            BetaMessageBatchIndividualResponse(
                customId,
                result,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaMessageBatchIndividualResponse && customId == other.customId && result == other.result && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(customId, result, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageBatchIndividualResponse{customId=$customId, result=$result, additionalProperties=$additionalProperties}"
}
