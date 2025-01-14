// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
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
import java.time.OffsetDateTime
import java.util.Objects
import java.util.Optional

@NoAutoDetect
class MessageBatch
@JsonCreator
private constructor(
    @JsonProperty("id") @ExcludeMissing private val id: JsonField<String> = JsonMissing.of(),
    @JsonProperty("archived_at")
    @ExcludeMissing
    private val archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    @JsonProperty("cancel_initiated_at")
    @ExcludeMissing
    private val cancelInitiatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    @JsonProperty("created_at")
    @ExcludeMissing
    private val createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    @JsonProperty("ended_at")
    @ExcludeMissing
    private val endedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    @JsonProperty("expires_at")
    @ExcludeMissing
    private val expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    @JsonProperty("processing_status")
    @ExcludeMissing
    private val processingStatus: JsonField<ProcessingStatus> = JsonMissing.of(),
    @JsonProperty("request_counts")
    @ExcludeMissing
    private val requestCounts: JsonField<MessageBatchRequestCounts> = JsonMissing.of(),
    @JsonProperty("results_url")
    @ExcludeMissing
    private val resultsUrl: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /**
     * Unique object identifier.
     *
     * The format and length of IDs may change over time.
     */
    fun id(): String = id.getRequired("id")

    /**
     * RFC 3339 datetime string representing the time at which the Message Batch was archived and
     * its results became unavailable.
     */
    fun archivedAt(): Optional<OffsetDateTime> =
        Optional.ofNullable(archivedAt.getNullable("archived_at"))

    /**
     * RFC 3339 datetime string representing the time at which cancellation was initiated for the
     * Message Batch. Specified only if cancellation was initiated.
     */
    fun cancelInitiatedAt(): Optional<OffsetDateTime> =
        Optional.ofNullable(cancelInitiatedAt.getNullable("cancel_initiated_at"))

    /** RFC 3339 datetime string representing the time at which the Message Batch was created. */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * RFC 3339 datetime string representing the time at which processing for the Message Batch
     * ended. Specified only once processing ends.
     *
     * Processing ends when every request in a Message Batch has either succeeded, errored,
     * canceled, or expired.
     */
    fun endedAt(): Optional<OffsetDateTime> = Optional.ofNullable(endedAt.getNullable("ended_at"))

    /**
     * RFC 3339 datetime string representing the time at which the Message Batch will expire and end
     * processing, which is 24 hours after creation.
     */
    fun expiresAt(): OffsetDateTime = expiresAt.getRequired("expires_at")

    /** Processing status of the Message Batch. */
    fun processingStatus(): ProcessingStatus = processingStatus.getRequired("processing_status")

    /**
     * Tallies requests within the Message Batch, categorized by their status.
     *
     * Requests start as `processing` and move to one of the other statuses only once processing of
     * the entire batch ends. The sum of all values always matches the total number of requests in
     * the batch.
     */
    fun requestCounts(): MessageBatchRequestCounts = requestCounts.getRequired("request_counts")

    /**
     * URL to a `.jsonl` file containing the results of the Message Batch requests. Specified only
     * once processing ends.
     *
     * Results in the file are not guaranteed to be in the same order as requests. Use the
     * `custom_id` field to match results to requests.
     */
    fun resultsUrl(): Optional<String> = Optional.ofNullable(resultsUrl.getNullable("results_url"))

    /**
     * Object type.
     *
     * For Message Batches, this is always `"message_batch"`.
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Unique object identifier.
     *
     * The format and length of IDs may change over time.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * RFC 3339 datetime string representing the time at which the Message Batch was archived and
     * its results became unavailable.
     */
    @JsonProperty("archived_at")
    @ExcludeMissing
    fun _archivedAt(): JsonField<OffsetDateTime> = archivedAt

    /**
     * RFC 3339 datetime string representing the time at which cancellation was initiated for the
     * Message Batch. Specified only if cancellation was initiated.
     */
    @JsonProperty("cancel_initiated_at")
    @ExcludeMissing
    fun _cancelInitiatedAt(): JsonField<OffsetDateTime> = cancelInitiatedAt

    /** RFC 3339 datetime string representing the time at which the Message Batch was created. */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * RFC 3339 datetime string representing the time at which processing for the Message Batch
     * ended. Specified only once processing ends.
     *
     * Processing ends when every request in a Message Batch has either succeeded, errored,
     * canceled, or expired.
     */
    @JsonProperty("ended_at") @ExcludeMissing fun _endedAt(): JsonField<OffsetDateTime> = endedAt

    /**
     * RFC 3339 datetime string representing the time at which the Message Batch will expire and end
     * processing, which is 24 hours after creation.
     */
    @JsonProperty("expires_at")
    @ExcludeMissing
    fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

    /** Processing status of the Message Batch. */
    @JsonProperty("processing_status")
    @ExcludeMissing
    fun _processingStatus(): JsonField<ProcessingStatus> = processingStatus

    /**
     * Tallies requests within the Message Batch, categorized by their status.
     *
     * Requests start as `processing` and move to one of the other statuses only once processing of
     * the entire batch ends. The sum of all values always matches the total number of requests in
     * the batch.
     */
    @JsonProperty("request_counts")
    @ExcludeMissing
    fun _requestCounts(): JsonField<MessageBatchRequestCounts> = requestCounts

    /**
     * URL to a `.jsonl` file containing the results of the Message Batch requests. Specified only
     * once processing ends.
     *
     * Results in the file are not guaranteed to be in the same order as requests. Use the
     * `custom_id` field to match results to requests.
     */
    @JsonProperty("results_url") @ExcludeMissing fun _resultsUrl(): JsonField<String> = resultsUrl

    /**
     * Object type.
     *
     * For Message Batches, this is always `"message_batch"`.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): MessageBatch = apply {
        if (validated) {
            return@apply
        }

        id()
        archivedAt()
        cancelInitiatedAt()
        createdAt()
        endedAt()
        expiresAt()
        processingStatus()
        requestCounts().validate()
        resultsUrl()
        type()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var id: JsonField<String>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var cancelInitiatedAt: JsonField<OffsetDateTime>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var endedAt: JsonField<OffsetDateTime>? = null
        private var expiresAt: JsonField<OffsetDateTime>? = null
        private var processingStatus: JsonField<ProcessingStatus>? = null
        private var requestCounts: JsonField<MessageBatchRequestCounts>? = null
        private var resultsUrl: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(messageBatch: MessageBatch) = apply {
            id = messageBatch.id
            archivedAt = messageBatch.archivedAt
            cancelInitiatedAt = messageBatch.cancelInitiatedAt
            createdAt = messageBatch.createdAt
            endedAt = messageBatch.endedAt
            expiresAt = messageBatch.expiresAt
            processingStatus = messageBatch.processingStatus
            requestCounts = messageBatch.requestCounts
            resultsUrl = messageBatch.resultsUrl
            type = messageBatch.type
            additionalProperties = messageBatch.additionalProperties.toMutableMap()
        }

        /**
         * Unique object identifier.
         *
         * The format and length of IDs may change over time.
         */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Unique object identifier.
         *
         * The format and length of IDs may change over time.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch was archived
         * and its results became unavailable.
         */
        fun archivedAt(archivedAt: OffsetDateTime?) = archivedAt(JsonField.ofNullable(archivedAt))

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch was archived
         * and its results became unavailable.
         */
        fun archivedAt(archivedAt: Optional<OffsetDateTime>) = archivedAt(archivedAt.orElse(null))

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch was archived
         * and its results became unavailable.
         */
        fun archivedAt(archivedAt: JsonField<OffsetDateTime>) = apply {
            this.archivedAt = archivedAt
        }

        /**
         * RFC 3339 datetime string representing the time at which cancellation was initiated for
         * the Message Batch. Specified only if cancellation was initiated.
         */
        fun cancelInitiatedAt(cancelInitiatedAt: OffsetDateTime?) =
            cancelInitiatedAt(JsonField.ofNullable(cancelInitiatedAt))

        /**
         * RFC 3339 datetime string representing the time at which cancellation was initiated for
         * the Message Batch. Specified only if cancellation was initiated.
         */
        fun cancelInitiatedAt(cancelInitiatedAt: Optional<OffsetDateTime>) =
            cancelInitiatedAt(cancelInitiatedAt.orElse(null))

        /**
         * RFC 3339 datetime string representing the time at which cancellation was initiated for
         * the Message Batch. Specified only if cancellation was initiated.
         */
        fun cancelInitiatedAt(cancelInitiatedAt: JsonField<OffsetDateTime>) = apply {
            this.cancelInitiatedAt = cancelInitiatedAt
        }

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch was created.
         */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch was created.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /**
         * RFC 3339 datetime string representing the time at which processing for the Message Batch
         * ended. Specified only once processing ends.
         *
         * Processing ends when every request in a Message Batch has either succeeded, errored,
         * canceled, or expired.
         */
        fun endedAt(endedAt: OffsetDateTime?) = endedAt(JsonField.ofNullable(endedAt))

        /**
         * RFC 3339 datetime string representing the time at which processing for the Message Batch
         * ended. Specified only once processing ends.
         *
         * Processing ends when every request in a Message Batch has either succeeded, errored,
         * canceled, or expired.
         */
        fun endedAt(endedAt: Optional<OffsetDateTime>) = endedAt(endedAt.orElse(null))

        /**
         * RFC 3339 datetime string representing the time at which processing for the Message Batch
         * ended. Specified only once processing ends.
         *
         * Processing ends when every request in a Message Batch has either succeeded, errored,
         * canceled, or expired.
         */
        fun endedAt(endedAt: JsonField<OffsetDateTime>) = apply { this.endedAt = endedAt }

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch will expire and
         * end processing, which is 24 hours after creation.
         */
        fun expiresAt(expiresAt: OffsetDateTime) = expiresAt(JsonField.of(expiresAt))

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch will expire and
         * end processing, which is 24 hours after creation.
         */
        fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply { this.expiresAt = expiresAt }

        /** Processing status of the Message Batch. */
        fun processingStatus(processingStatus: ProcessingStatus) =
            processingStatus(JsonField.of(processingStatus))

        /** Processing status of the Message Batch. */
        fun processingStatus(processingStatus: JsonField<ProcessingStatus>) = apply {
            this.processingStatus = processingStatus
        }

        /**
         * Tallies requests within the Message Batch, categorized by their status.
         *
         * Requests start as `processing` and move to one of the other statuses only once processing
         * of the entire batch ends. The sum of all values always matches the total number of
         * requests in the batch.
         */
        fun requestCounts(requestCounts: MessageBatchRequestCounts) =
            requestCounts(JsonField.of(requestCounts))

        /**
         * Tallies requests within the Message Batch, categorized by their status.
         *
         * Requests start as `processing` and move to one of the other statuses only once processing
         * of the entire batch ends. The sum of all values always matches the total number of
         * requests in the batch.
         */
        fun requestCounts(requestCounts: JsonField<MessageBatchRequestCounts>) = apply {
            this.requestCounts = requestCounts
        }

        /**
         * URL to a `.jsonl` file containing the results of the Message Batch requests. Specified
         * only once processing ends.
         *
         * Results in the file are not guaranteed to be in the same order as requests. Use the
         * `custom_id` field to match results to requests.
         */
        fun resultsUrl(resultsUrl: String?) = resultsUrl(JsonField.ofNullable(resultsUrl))

        /**
         * URL to a `.jsonl` file containing the results of the Message Batch requests. Specified
         * only once processing ends.
         *
         * Results in the file are not guaranteed to be in the same order as requests. Use the
         * `custom_id` field to match results to requests.
         */
        fun resultsUrl(resultsUrl: Optional<String>) = resultsUrl(resultsUrl.orElse(null))

        /**
         * URL to a `.jsonl` file containing the results of the Message Batch requests. Specified
         * only once processing ends.
         *
         * Results in the file are not guaranteed to be in the same order as requests. Use the
         * `custom_id` field to match results to requests.
         */
        fun resultsUrl(resultsUrl: JsonField<String>) = apply { this.resultsUrl = resultsUrl }

        /**
         * Object type.
         *
         * For Message Batches, this is always `"message_batch"`.
         */
        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Object type.
         *
         * For Message Batches, this is always `"message_batch"`.
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

        fun build(): MessageBatch =
            MessageBatch(
                checkRequired("id", id),
                checkRequired("archivedAt", archivedAt),
                checkRequired("cancelInitiatedAt", cancelInitiatedAt),
                checkRequired("createdAt", createdAt),
                checkRequired("endedAt", endedAt),
                checkRequired("expiresAt", expiresAt),
                checkRequired("processingStatus", processingStatus),
                checkRequired("requestCounts", requestCounts),
                checkRequired("resultsUrl", resultsUrl),
                checkRequired("type", type),
                additionalProperties.toImmutable(),
            )
    }

    class ProcessingStatus
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val IN_PROGRESS = of("in_progress")

            @JvmField val CANCELING = of("canceling")

            @JvmField val ENDED = of("ended")

            @JvmStatic fun of(value: String) = ProcessingStatus(JsonField.of(value))
        }

        enum class Known {
            IN_PROGRESS,
            CANCELING,
            ENDED,
        }

        enum class Value {
            IN_PROGRESS,
            CANCELING,
            ENDED,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                IN_PROGRESS -> Value.IN_PROGRESS
                CANCELING -> Value.CANCELING
                ENDED -> Value.ENDED
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                IN_PROGRESS -> Known.IN_PROGRESS
                CANCELING -> Known.CANCELING
                ENDED -> Known.ENDED
                else -> throw AnthropicInvalidDataException("Unknown ProcessingStatus: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is ProcessingStatus && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val MESSAGE_BATCH = of("message_batch")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            MESSAGE_BATCH,
        }

        enum class Value {
            MESSAGE_BATCH,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                MESSAGE_BATCH -> Value.MESSAGE_BATCH
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                MESSAGE_BATCH -> Known.MESSAGE_BATCH
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is MessageBatch && id == other.id && archivedAt == other.archivedAt && cancelInitiatedAt == other.cancelInitiatedAt && createdAt == other.createdAt && endedAt == other.endedAt && expiresAt == other.expiresAt && processingStatus == other.processingStatus && requestCounts == other.requestCounts && resultsUrl == other.resultsUrl && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(id, archivedAt, cancelInitiatedAt, createdAt, endedAt, expiresAt, processingStatus, requestCounts, resultsUrl, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "MessageBatch{id=$id, archivedAt=$archivedAt, cancelInitiatedAt=$cancelInitiatedAt, createdAt=$createdAt, endedAt=$endedAt, expiresAt=$expiresAt, processingStatus=$processingStatus, requestCounts=$requestCounts, resultsUrl=$resultsUrl, type=$type, additionalProperties=$additionalProperties}"
}
