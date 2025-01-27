// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

@NoAutoDetect
class BetaMessageBatchRequestCounts
@JsonCreator
private constructor(
    @JsonProperty("canceled")
    @ExcludeMissing
    private val canceled: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("errored")
    @ExcludeMissing
    private val errored: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("expired")
    @ExcludeMissing
    private val expired: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("processing")
    @ExcludeMissing
    private val processing: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("succeeded")
    @ExcludeMissing
    private val succeeded: JsonField<Long> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /**
     * Number of requests in the Message Batch that have been canceled.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    fun canceled(): Long = canceled.getRequired("canceled")

    /**
     * Number of requests in the Message Batch that encountered an error.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    fun errored(): Long = errored.getRequired("errored")

    /**
     * Number of requests in the Message Batch that have expired.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    fun expired(): Long = expired.getRequired("expired")

    /** Number of requests in the Message Batch that are processing. */
    fun processing(): Long = processing.getRequired("processing")

    /**
     * Number of requests in the Message Batch that have completed successfully.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    fun succeeded(): Long = succeeded.getRequired("succeeded")

    /**
     * Number of requests in the Message Batch that have been canceled.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    @JsonProperty("canceled") @ExcludeMissing fun _canceled(): JsonField<Long> = canceled

    /**
     * Number of requests in the Message Batch that encountered an error.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    @JsonProperty("errored") @ExcludeMissing fun _errored(): JsonField<Long> = errored

    /**
     * Number of requests in the Message Batch that have expired.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    @JsonProperty("expired") @ExcludeMissing fun _expired(): JsonField<Long> = expired

    /** Number of requests in the Message Batch that are processing. */
    @JsonProperty("processing") @ExcludeMissing fun _processing(): JsonField<Long> = processing

    /**
     * Number of requests in the Message Batch that have completed successfully.
     *
     * This is zero until processing of the entire Message Batch has ended.
     */
    @JsonProperty("succeeded") @ExcludeMissing fun _succeeded(): JsonField<Long> = succeeded

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaMessageBatchRequestCounts = apply {
        if (validated) {
            return@apply
        }

        canceled()
        errored()
        expired()
        processing()
        succeeded()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMessageBatchRequestCounts]. */
    class Builder internal constructor() {

        private var canceled: JsonField<Long>? = null
        private var errored: JsonField<Long>? = null
        private var expired: JsonField<Long>? = null
        private var processing: JsonField<Long>? = null
        private var succeeded: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageBatchRequestCounts: BetaMessageBatchRequestCounts) = apply {
            canceled = betaMessageBatchRequestCounts.canceled
            errored = betaMessageBatchRequestCounts.errored
            expired = betaMessageBatchRequestCounts.expired
            processing = betaMessageBatchRequestCounts.processing
            succeeded = betaMessageBatchRequestCounts.succeeded
            additionalProperties = betaMessageBatchRequestCounts.additionalProperties.toMutableMap()
        }

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
        fun canceled(canceled: JsonField<Long>) = apply { this.canceled = canceled }

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
        fun errored(errored: JsonField<Long>) = apply { this.errored = errored }

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
        fun expired(expired: JsonField<Long>) = apply { this.expired = expired }

        /** Number of requests in the Message Batch that are processing. */
        fun processing(processing: Long) = processing(JsonField.of(processing))

        /** Number of requests in the Message Batch that are processing. */
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
        fun succeeded(succeeded: JsonField<Long>) = apply { this.succeeded = succeeded }

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

        fun build(): BetaMessageBatchRequestCounts =
            BetaMessageBatchRequestCounts(
                checkRequired("canceled", canceled),
                checkRequired("errored", errored),
                checkRequired("expired", expired),
                checkRequired("processing", processing),
                checkRequired("succeeded", succeeded),
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaMessageBatchRequestCounts && canceled == other.canceled && errored == other.errored && expired == other.expired && processing == other.processing && succeeded == other.succeeded && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(canceled, errored, expired, processing, succeeded, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageBatchRequestCounts{canceled=$canceled, errored=$errored, expired=$expired, processing=$processing, succeeded=$succeeded, additionalProperties=$additionalProperties}"
}
