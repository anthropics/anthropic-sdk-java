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

@JsonDeserialize(builder = BetaMessageBatchRequestCounts.Builder::class)
@NoAutoDetect
class BetaMessageBatchRequestCounts
private constructor(
    private val processing: JsonField<Long>,
    private val succeeded: JsonField<Long>,
    private val errored: JsonField<Long>,
    private val canceled: JsonField<Long>,
    private val expired: JsonField<Long>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    /** Number of requests in the Message Batch that are processing. */
    fun processing(): Long = processing.getRequired("processing")

    /**
     * Number of requests in the Message Batch that have completed successfully.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    fun succeeded(): Long = succeeded.getRequired("succeeded")

    /**
     * Number of requests in the Message Batch that encountered an error.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    fun errored(): Long = errored.getRequired("errored")

    /**
     * Number of requests in the Message Batch that have been canceled.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    fun canceled(): Long = canceled.getRequired("canceled")

    /**
     * Number of requests in the Message Batch that have expired.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    fun expired(): Long = expired.getRequired("expired")

    /** Number of requests in the Message Batch that are processing. */
    @JsonProperty("processing") @ExcludeMissing fun _processing() = processing

    /**
     * Number of requests in the Message Batch that have completed successfully.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    @JsonProperty("succeeded") @ExcludeMissing fun _succeeded() = succeeded

    /**
     * Number of requests in the Message Batch that encountered an error.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    @JsonProperty("errored") @ExcludeMissing fun _errored() = errored

    /**
     * Number of requests in the Message Batch that have been canceled.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    @JsonProperty("canceled") @ExcludeMissing fun _canceled() = canceled

    /**
     * Number of requests in the Message Batch that have expired.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    @JsonProperty("expired") @ExcludeMissing fun _expired() = expired

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): BetaMessageBatchRequestCounts = apply {
        if (!validated) {
            processing()
            succeeded()
            errored()
            canceled()
            expired()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var processing: JsonField<Long> = JsonMissing.of()
        private var succeeded: JsonField<Long> = JsonMissing.of()
        private var errored: JsonField<Long> = JsonMissing.of()
        private var canceled: JsonField<Long> = JsonMissing.of()
        private var expired: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageBatchRequestCounts: BetaMessageBatchRequestCounts) = apply {
            this.processing = betaMessageBatchRequestCounts.processing
            this.succeeded = betaMessageBatchRequestCounts.succeeded
            this.errored = betaMessageBatchRequestCounts.errored
            this.canceled = betaMessageBatchRequestCounts.canceled
            this.expired = betaMessageBatchRequestCounts.expired
            additionalProperties(betaMessageBatchRequestCounts.additionalProperties)
        }

        /** Number of requests in the Message Batch that are processing. */
        fun processing(processing: Long) = processing(JsonField.of(processing))

        /** Number of requests in the Message Batch that are processing. */
        @JsonProperty("processing")
        @ExcludeMissing
        fun processing(processing: JsonField<Long>) = apply { this.processing = processing }

        /**
         * Number of requests in the Message Batch that have completed successfully.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        fun succeeded(succeeded: Long) = succeeded(JsonField.of(succeeded))

        /**
         * Number of requests in the Message Batch that have completed successfully.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        @JsonProperty("succeeded")
        @ExcludeMissing
        fun succeeded(succeeded: JsonField<Long>) = apply { this.succeeded = succeeded }

        /**
         * Number of requests in the Message Batch that encountered an error.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        fun errored(errored: Long) = errored(JsonField.of(errored))

        /**
         * Number of requests in the Message Batch that encountered an error.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        @JsonProperty("errored")
        @ExcludeMissing
        fun errored(errored: JsonField<Long>) = apply { this.errored = errored }

        /**
         * Number of requests in the Message Batch that have been canceled.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        fun canceled(canceled: Long) = canceled(JsonField.of(canceled))

        /**
         * Number of requests in the Message Batch that have been canceled.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        @JsonProperty("canceled")
        @ExcludeMissing
        fun canceled(canceled: JsonField<Long>) = apply { this.canceled = canceled }

        /**
         * Number of requests in the Message Batch that have expired.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        fun expired(expired: Long) = expired(JsonField.of(expired))

        /**
         * Number of requests in the Message Batch that have expired.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        @JsonProperty("expired")
        @ExcludeMissing
        fun expired(expired: JsonField<Long>) = apply { this.expired = expired }

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

        fun build(): BetaMessageBatchRequestCounts =
            BetaMessageBatchRequestCounts(
                processing,
                succeeded,
                errored,
                canceled,
                expired,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaMessageBatchRequestCounts && processing == other.processing && succeeded == other.succeeded && errored == other.errored && canceled == other.canceled && expired == other.expired && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(processing, succeeded, errored, canceled, expired, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageBatchRequestCounts{processing=$processing, succeeded=$succeeded, errored=$errored, canceled=$canceled, expired=$expired, additionalProperties=$additionalProperties}"
}
