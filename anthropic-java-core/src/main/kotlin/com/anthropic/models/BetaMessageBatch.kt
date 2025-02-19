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
class BetaMessageBatch
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
    private val requestCounts: JsonField<BetaMessageBatchRequestCounts> = JsonMissing.of(),
    @JsonProperty("results_url")
    @ExcludeMissing
    private val resultsUrl: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
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
    fun requestCounts(): BetaMessageBatchRequestCounts = requestCounts.getRequired("request_counts")

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
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

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
    fun _requestCounts(): JsonField<BetaMessageBatchRequestCounts> = requestCounts

    /**
     * URL to a `.jsonl` file containing the results of the Message Batch requests. Specified only
     * once processing ends.
     *
     * Results in the file are not guaranteed to be in the same order as requests. Use the
     * `custom_id` field to match results to requests.
     */
    @JsonProperty("results_url") @ExcludeMissing fun _resultsUrl(): JsonField<String> = resultsUrl

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaMessageBatch = apply {
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
        _type().let {
            if (it != JsonValue.from("message_batch")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMessageBatch]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var cancelInitiatedAt: JsonField<OffsetDateTime>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var endedAt: JsonField<OffsetDateTime>? = null
        private var expiresAt: JsonField<OffsetDateTime>? = null
        private var processingStatus: JsonField<ProcessingStatus>? = null
        private var requestCounts: JsonField<BetaMessageBatchRequestCounts>? = null
        private var resultsUrl: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("message_batch")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageBatch: BetaMessageBatch) = apply {
            id = betaMessageBatch.id
            archivedAt = betaMessageBatch.archivedAt
            cancelInitiatedAt = betaMessageBatch.cancelInitiatedAt
            createdAt = betaMessageBatch.createdAt
            endedAt = betaMessageBatch.endedAt
            expiresAt = betaMessageBatch.expiresAt
            processingStatus = betaMessageBatch.processingStatus
            requestCounts = betaMessageBatch.requestCounts
            resultsUrl = betaMessageBatch.resultsUrl
            type = betaMessageBatch.type
            additionalProperties = betaMessageBatch.additionalProperties.toMutableMap()
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
        fun requestCounts(requestCounts: BetaMessageBatchRequestCounts) =
            requestCounts(JsonField.of(requestCounts))

        /**
         * Tallies requests within the Message Batch, categorized by their status.
         *
         * Requests start as `processing` and move to one of the other statuses only once processing
         * of the entire batch ends. The sum of all values always matches the total number of
         * requests in the batch.
         */
        fun requestCounts(requestCounts: JsonField<BetaMessageBatchRequestCounts>) = apply {
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

        fun build(): BetaMessageBatch =
            BetaMessageBatch(
                checkRequired("id", id),
                checkRequired("archivedAt", archivedAt),
                checkRequired("cancelInitiatedAt", cancelInitiatedAt),
                checkRequired("createdAt", createdAt),
                checkRequired("endedAt", endedAt),
                checkRequired("expiresAt", expiresAt),
                checkRequired("processingStatus", processingStatus),
                checkRequired("requestCounts", requestCounts),
                checkRequired("resultsUrl", resultsUrl),
                type,
                additionalProperties.toImmutable(),
            )
    }

    /** Processing status of the Message Batch. */
    class ProcessingStatus @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val IN_PROGRESS = of("in_progress")

            @JvmField val CANCELING = of("canceling")

            @JvmField val ENDED = of("ended")

            @JvmStatic fun of(value: String) = ProcessingStatus(JsonField.of(value))
        }

        /** An enum containing [ProcessingStatus]'s known values. */
        enum class Known {
            IN_PROGRESS,
            CANCELING,
            ENDED,
        }

        /**
         * An enum containing [ProcessingStatus]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ProcessingStatus] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            IN_PROGRESS,
            CANCELING,
            ENDED,
            /**
             * An enum member indicating that [ProcessingStatus] was instantiated with an unknown
             * value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                IN_PROGRESS -> Value.IN_PROGRESS
                CANCELING -> Value.CANCELING
                ENDED -> Value.ENDED
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                IN_PROGRESS -> Known.IN_PROGRESS
                CANCELING -> Known.CANCELING
                ENDED -> Known.ENDED
                else -> throw AnthropicInvalidDataException("Unknown ProcessingStatus: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is ProcessingStatus && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaMessageBatch && id == other.id && archivedAt == other.archivedAt && cancelInitiatedAt == other.cancelInitiatedAt && createdAt == other.createdAt && endedAt == other.endedAt && expiresAt == other.expiresAt && processingStatus == other.processingStatus && requestCounts == other.requestCounts && resultsUrl == other.resultsUrl && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(id, archivedAt, cancelInitiatedAt, createdAt, endedAt, expiresAt, processingStatus, requestCounts, resultsUrl, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageBatch{id=$id, archivedAt=$archivedAt, cancelInitiatedAt=$cancelInitiatedAt, createdAt=$createdAt, endedAt=$endedAt, expiresAt=$expiresAt, processingStatus=$processingStatus, requestCounts=$requestCounts, resultsUrl=$resultsUrl, type=$type, additionalProperties=$additionalProperties}"
}
