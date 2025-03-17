// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

@NoAutoDetect
class MessageBatchRequestCounts
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
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun canceled(): Long = canceled.getRequired("canceled")

    /**
     * Number of requests in the Message Batch that encountered an error.
     *
     * This is zero until processing of the entire Message Batch has ended.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun errored(): Long = errored.getRequired("errored")

    /**
     * Number of requests in the Message Batch that have expired.
     *
     * This is zero until processing of the entire Message Batch has ended.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun expired(): Long = expired.getRequired("expired")

    /**
     * Number of requests in the Message Batch that are processing.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processing(): Long = processing.getRequired("processing")

    /**
     * Number of requests in the Message Batch that have completed successfully.
     *
     * This is zero until processing of the entire Message Batch has ended.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun succeeded(): Long = succeeded.getRequired("succeeded")

    /**
     * Returns the raw JSON value of [canceled].
     *
     * Unlike [canceled], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("canceled") @ExcludeMissing fun _canceled(): JsonField<Long> = canceled

    /**
     * Returns the raw JSON value of [errored].
     *
     * Unlike [errored], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("errored") @ExcludeMissing fun _errored(): JsonField<Long> = errored

    /**
     * Returns the raw JSON value of [expired].
     *
     * Unlike [expired], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expired") @ExcludeMissing fun _expired(): JsonField<Long> = expired

    /**
     * Returns the raw JSON value of [processing].
     *
     * Unlike [processing], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("processing") @ExcludeMissing fun _processing(): JsonField<Long> = processing

    /**
     * Returns the raw JSON value of [succeeded].
     *
     * Unlike [succeeded], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("succeeded") @ExcludeMissing fun _succeeded(): JsonField<Long> = succeeded

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): MessageBatchRequestCounts = apply {
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

        /**
         * Returns a mutable builder for constructing an instance of [MessageBatchRequestCounts].
         *
         * The following fields are required:
         * ```java
         * .canceled()
         * .errored()
         * .expired()
         * .processing()
         * .succeeded()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MessageBatchRequestCounts]. */
    class Builder internal constructor() {

        private var canceled: JsonField<Long>? = null
        private var errored: JsonField<Long>? = null
        private var expired: JsonField<Long>? = null
        private var processing: JsonField<Long>? = null
        private var succeeded: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(messageBatchRequestCounts: MessageBatchRequestCounts) = apply {
            canceled = messageBatchRequestCounts.canceled
            errored = messageBatchRequestCounts.errored
            expired = messageBatchRequestCounts.expired
            processing = messageBatchRequestCounts.processing
            succeeded = messageBatchRequestCounts.succeeded
            additionalProperties = messageBatchRequestCounts.additionalProperties.toMutableMap()
        }

        /**
         * Number of requests in the Message Batch that have been canceled.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        fun canceled(canceled: Long) = canceled(JsonField.of(canceled))

        /**
         * Sets [Builder.canceled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.canceled] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun canceled(canceled: JsonField<Long>) = apply { this.canceled = canceled }

        /**
         * Number of requests in the Message Batch that encountered an error.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        fun errored(errored: Long) = errored(JsonField.of(errored))

        /**
         * Sets [Builder.errored] to an arbitrary JSON value.
         *
         * You should usually call [Builder.errored] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun errored(errored: JsonField<Long>) = apply { this.errored = errored }

        /**
         * Number of requests in the Message Batch that have expired.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        fun expired(expired: Long) = expired(JsonField.of(expired))

        /**
         * Sets [Builder.expired] to an arbitrary JSON value.
         *
         * You should usually call [Builder.expired] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun expired(expired: JsonField<Long>) = apply { this.expired = expired }

        /** Number of requests in the Message Batch that are processing. */
        fun processing(processing: Long) = processing(JsonField.of(processing))

        /**
         * Sets [Builder.processing] to an arbitrary JSON value.
         *
         * You should usually call [Builder.processing] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun processing(processing: JsonField<Long>) = apply { this.processing = processing }

        /**
         * Number of requests in the Message Batch that have completed successfully.
         *
         * This is zero until processing of the entire Message Batch has ended.
         */
        fun succeeded(succeeded: Long) = succeeded(JsonField.of(succeeded))

        /**
         * Sets [Builder.succeeded] to an arbitrary JSON value.
         *
         * You should usually call [Builder.succeeded] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
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

        /**
         * Returns an immutable instance of [MessageBatchRequestCounts].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .canceled()
         * .errored()
         * .expired()
         * .processing()
         * .succeeded()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): MessageBatchRequestCounts =
            MessageBatchRequestCounts(
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

        return /* spotless:off */ other is MessageBatchRequestCounts && canceled == other.canceled && errored == other.errored && expired == other.expired && processing == other.processing && succeeded == other.succeeded && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(canceled, errored, expired, processing, succeeded, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "MessageBatchRequestCounts{canceled=$canceled, errored=$errored, expired=$expired, processing=$processing, succeeded=$succeeded, additionalProperties=$additionalProperties}"
}
