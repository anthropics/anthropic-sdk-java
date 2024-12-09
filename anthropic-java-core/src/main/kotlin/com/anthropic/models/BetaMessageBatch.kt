// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.time.OffsetDateTime
import java.util.Objects
import java.util.Optional

@JsonDeserialize(builder = BetaMessageBatch.Builder::class)
@NoAutoDetect
class BetaMessageBatch
private constructor(
    private val id: JsonField<String>,
    private val type: JsonField<Type>,
    private val processingStatus: JsonField<ProcessingStatus>,
    private val requestCounts: JsonField<BetaMessageBatchRequestCounts>,
    private val endedAt: JsonField<OffsetDateTime>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val expiresAt: JsonField<OffsetDateTime>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val cancelInitiatedAt: JsonField<OffsetDateTime>,
    private val resultsUrl: JsonField<String>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    /**
     * Unique object identifier.
     *
     * The format and length of IDs may change over time.
     */
    fun id(): String = id.getRequired("id")

    /**
     * Object type.
     *
     * For Message Batches, this is always `"message_batch"`.
     */
    fun type(): Type = type.getRequired("type")

    /** Processing status of the Message Batch. */
    fun processingStatus(): ProcessingStatus = processingStatus.getRequired("processing_status")

    /**
     * Tallies requests within the Message Batch, categorized by their status.
     *
     * Requests start as `processing` and move to one of the other statuses only once processing of
     * the entire batch ends. The sum of all values always matches the total number of requests in
     * the batch.
     */
    fun requestCounts(): BetaMessageBatchRequestCounts = requestCounts.getRequired("request_counts")

    /**
     * RFC 3339 datetime string representing the time at which processing for the Message Batch
     * ended. Specified only once processing ends.
     *
     * Processing ends when every request in a Message Batch has either succeeded, errored,
     * canceled, or expired.
     */
    fun endedAt(): Optional<OffsetDateTime> = Optional.ofNullable(endedAt.getNullable("ended_at"))

    /** RFC 3339 datetime string representing the time at which the Message Batch was created. */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * RFC 3339 datetime string representing the time at which the Message Batch will expire and end
     * processing, which is 24 hours after creation.
     */
    fun expiresAt(): OffsetDateTime = expiresAt.getRequired("expires_at")

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

    /**
     * URL to a `.jsonl` file containing the results of the Message Batch requests. Specified only
     * once processing ends.
     *
     * Results in the file are not guaranteed to be in the same order as requests. Use the
     * `custom_id` field to match results to requests.
     */
    fun resultsUrl(): Optional<String> = Optional.ofNullable(resultsUrl.getNullable("results_url"))

    /**
     * Unique object identifier.
     *
     * The format and length of IDs may change over time.
     */
    @JsonProperty("id") @ExcludeMissing fun _id() = id

    /**
     * Object type.
     *
     * For Message Batches, this is always `"message_batch"`.
     */
    @JsonProperty("type") @ExcludeMissing fun _type() = type

    /** Processing status of the Message Batch. */
    @JsonProperty("processing_status") @ExcludeMissing fun _processingStatus() = processingStatus

    /**
     * Tallies requests within the Message Batch, categorized by their status.
     *
     * Requests start as `processing` and move to one of the other statuses only once processing of
     * the entire batch ends. The sum of all values always matches the total number of requests in
     * the batch.
     */
    @JsonProperty("request_counts") @ExcludeMissing fun _requestCounts() = requestCounts

    /**
     * RFC 3339 datetime string representing the time at which processing for the Message Batch
     * ended. Specified only once processing ends.
     *
     * Processing ends when every request in a Message Batch has either succeeded, errored,
     * canceled, or expired.
     */
    @JsonProperty("ended_at") @ExcludeMissing fun _endedAt() = endedAt

    /** RFC 3339 datetime string representing the time at which the Message Batch was created. */
    @JsonProperty("created_at") @ExcludeMissing fun _createdAt() = createdAt

    /**
     * RFC 3339 datetime string representing the time at which the Message Batch will expire and end
     * processing, which is 24 hours after creation.
     */
    @JsonProperty("expires_at") @ExcludeMissing fun _expiresAt() = expiresAt

    /**
     * RFC 3339 datetime string representing the time at which the Message Batch was archived and
     * its results became unavailable.
     */
    @JsonProperty("archived_at") @ExcludeMissing fun _archivedAt() = archivedAt

    /**
     * RFC 3339 datetime string representing the time at which cancellation was initiated for the
     * Message Batch. Specified only if cancellation was initiated.
     */
    @JsonProperty("cancel_initiated_at")
    @ExcludeMissing
    fun _cancelInitiatedAt() = cancelInitiatedAt

    /**
     * URL to a `.jsonl` file containing the results of the Message Batch requests. Specified only
     * once processing ends.
     *
     * Results in the file are not guaranteed to be in the same order as requests. Use the
     * `custom_id` field to match results to requests.
     */
    @JsonProperty("results_url") @ExcludeMissing fun _resultsUrl() = resultsUrl

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): BetaMessageBatch = apply {
        if (!validated) {
            id()
            type()
            processingStatus()
            requestCounts().validate()
            endedAt()
            createdAt()
            expiresAt()
            archivedAt()
            cancelInitiatedAt()
            resultsUrl()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var id: JsonField<String> = JsonMissing.of()
        private var type: JsonField<Type> = JsonMissing.of()
        private var processingStatus: JsonField<ProcessingStatus> = JsonMissing.of()
        private var requestCounts: JsonField<BetaMessageBatchRequestCounts> = JsonMissing.of()
        private var endedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var createdAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var expiresAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var archivedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var cancelInitiatedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var resultsUrl: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageBatch: BetaMessageBatch) = apply {
            this.id = betaMessageBatch.id
            this.type = betaMessageBatch.type
            this.processingStatus = betaMessageBatch.processingStatus
            this.requestCounts = betaMessageBatch.requestCounts
            this.endedAt = betaMessageBatch.endedAt
            this.createdAt = betaMessageBatch.createdAt
            this.expiresAt = betaMessageBatch.expiresAt
            this.archivedAt = betaMessageBatch.archivedAt
            this.cancelInitiatedAt = betaMessageBatch.cancelInitiatedAt
            this.resultsUrl = betaMessageBatch.resultsUrl
            additionalProperties(betaMessageBatch.additionalProperties)
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
        @JsonProperty("id") @ExcludeMissing fun id(id: JsonField<String>) = apply { this.id = id }

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
        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** Processing status of the Message Batch. */
        fun processingStatus(processingStatus: ProcessingStatus) =
            processingStatus(JsonField.of(processingStatus))

        /** Processing status of the Message Batch. */
        @JsonProperty("processing_status")
        @ExcludeMissing
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
        fun requestCounts(requestCounts: BetaMessageBatchRequestCounts) =
            requestCounts(JsonField.of(requestCounts))

        /**
         * Tallies requests within the Message Batch, categorized by their status.
         *
         * Requests start as `processing` and move to one of the other statuses only once processing
         * of the entire batch ends. The sum of all values always matches the total number of
         * requests in the batch.
         */
        @JsonProperty("request_counts")
        @ExcludeMissing
        fun requestCounts(requestCounts: JsonField<BetaMessageBatchRequestCounts>) = apply {
            this.requestCounts = requestCounts
        }

        /**
         * RFC 3339 datetime string representing the time at which processing for the Message Batch
         * ended. Specified only once processing ends.
         *
         * Processing ends when every request in a Message Batch has either succeeded, errored,
         * canceled, or expired.
         */
        fun endedAt(endedAt: OffsetDateTime) = endedAt(JsonField.of(endedAt))

        /**
         * RFC 3339 datetime string representing the time at which processing for the Message Batch
         * ended. Specified only once processing ends.
         *
         * Processing ends when every request in a Message Batch has either succeeded, errored,
         * canceled, or expired.
         */
        @JsonProperty("ended_at")
        @ExcludeMissing
        fun endedAt(endedAt: JsonField<OffsetDateTime>) = apply { this.endedAt = endedAt }

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch was created.
         */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch was created.
         */
        @JsonProperty("created_at")
        @ExcludeMissing
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch will expire and
         * end processing, which is 24 hours after creation.
         */
        fun expiresAt(expiresAt: OffsetDateTime) = expiresAt(JsonField.of(expiresAt))

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch will expire and
         * end processing, which is 24 hours after creation.
         */
        @JsonProperty("expires_at")
        @ExcludeMissing
        fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply { this.expiresAt = expiresAt }

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch was archived
         * and its results became unavailable.
         */
        fun archivedAt(archivedAt: OffsetDateTime) = archivedAt(JsonField.of(archivedAt))

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch was archived
         * and its results became unavailable.
         */
        @JsonProperty("archived_at")
        @ExcludeMissing
        fun archivedAt(archivedAt: JsonField<OffsetDateTime>) = apply {
            this.archivedAt = archivedAt
        }

        /**
         * RFC 3339 datetime string representing the time at which cancellation was initiated for
         * the Message Batch. Specified only if cancellation was initiated.
         */
        fun cancelInitiatedAt(cancelInitiatedAt: OffsetDateTime) =
            cancelInitiatedAt(JsonField.of(cancelInitiatedAt))

        /**
         * RFC 3339 datetime string representing the time at which cancellation was initiated for
         * the Message Batch. Specified only if cancellation was initiated.
         */
        @JsonProperty("cancel_initiated_at")
        @ExcludeMissing
        fun cancelInitiatedAt(cancelInitiatedAt: JsonField<OffsetDateTime>) = apply {
            this.cancelInitiatedAt = cancelInitiatedAt
        }

        /**
         * URL to a `.jsonl` file containing the results of the Message Batch requests. Specified
         * only once processing ends.
         *
         * Results in the file are not guaranteed to be in the same order as requests. Use the
         * `custom_id` field to match results to requests.
         */
        fun resultsUrl(resultsUrl: String) = resultsUrl(JsonField.of(resultsUrl))

        /**
         * URL to a `.jsonl` file containing the results of the Message Batch requests. Specified
         * only once processing ends.
         *
         * Results in the file are not guaranteed to be in the same order as requests. Use the
         * `custom_id` field to match results to requests.
         */
        @JsonProperty("results_url")
        @ExcludeMissing
        fun resultsUrl(resultsUrl: JsonField<String>) = apply { this.resultsUrl = resultsUrl }

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

        fun build(): BetaMessageBatch =
            BetaMessageBatch(
                id,
                type,
                processingStatus,
                requestCounts,
                endedAt,
                createdAt,
                expiresAt,
                archivedAt,
                cancelInitiatedAt,
                resultsUrl,
                additionalProperties.toImmutable(),
            )
    }

    class ProcessingStatus
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is ProcessingStatus && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()

        companion object {

            @JvmField val IN_PROGRESS = ProcessingStatus(JsonField.of("in_progress"))

            @JvmField val CANCELING = ProcessingStatus(JsonField.of("canceling"))

            @JvmField val ENDED = ProcessingStatus(JsonField.of("ended"))

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
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()

        companion object {

            @JvmField val MESSAGE_BATCH = Type(JsonField.of("message_batch"))

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
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaMessageBatch && id == other.id && type == other.type && processingStatus == other.processingStatus && requestCounts == other.requestCounts && endedAt == other.endedAt && createdAt == other.createdAt && expiresAt == other.expiresAt && archivedAt == other.archivedAt && cancelInitiatedAt == other.cancelInitiatedAt && resultsUrl == other.resultsUrl && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(id, type, processingStatus, requestCounts, endedAt, createdAt, expiresAt, archivedAt, cancelInitiatedAt, resultsUrl, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageBatch{id=$id, type=$type, processingStatus=$processingStatus, requestCounts=$requestCounts, endedAt=$endedAt, createdAt=$createdAt, expiresAt=$expiresAt, archivedAt=$archivedAt, cancelInitiatedAt=$cancelInitiatedAt, resultsUrl=$resultsUrl, additionalProperties=$additionalProperties}"
}
