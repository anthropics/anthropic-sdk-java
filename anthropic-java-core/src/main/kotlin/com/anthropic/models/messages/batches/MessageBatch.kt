// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class MessageBatch
private constructor(
    private val id: JsonField<String>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val cancelInitiatedAt: JsonField<OffsetDateTime>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val endedAt: JsonField<OffsetDateTime>,
    private val expiresAt: JsonField<OffsetDateTime>,
    private val processingStatus: JsonField<ProcessingStatus>,
    private val requestCounts: JsonField<MessageBatchRequestCounts>,
    private val resultsUrl: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("archived_at")
        @ExcludeMissing
        archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("cancel_initiated_at")
        @ExcludeMissing
        cancelInitiatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("ended_at")
        @ExcludeMissing
        endedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("expires_at")
        @ExcludeMissing
        expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("processing_status")
        @ExcludeMissing
        processingStatus: JsonField<ProcessingStatus> = JsonMissing.of(),
        @JsonProperty("request_counts")
        @ExcludeMissing
        requestCounts: JsonField<MessageBatchRequestCounts> = JsonMissing.of(),
        @JsonProperty("results_url")
        @ExcludeMissing
        resultsUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        id,
        archivedAt,
        cancelInitiatedAt,
        createdAt,
        endedAt,
        expiresAt,
        processingStatus,
        requestCounts,
        resultsUrl,
        type,
        mutableMapOf(),
    )

    /**
     * Unique object identifier.
     *
     * The format and length of IDs may change over time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * RFC 3339 datetime string representing the time at which the Message Batch was archived and
     * its results became unavailable.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedAt(): Optional<OffsetDateTime> =
        Optional.ofNullable(archivedAt.getNullable("archived_at"))

    /**
     * RFC 3339 datetime string representing the time at which cancellation was initiated for the
     * Message Batch. Specified only if cancellation was initiated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cancelInitiatedAt(): Optional<OffsetDateTime> =
        Optional.ofNullable(cancelInitiatedAt.getNullable("cancel_initiated_at"))

    /**
     * RFC 3339 datetime string representing the time at which the Message Batch was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * RFC 3339 datetime string representing the time at which processing for the Message Batch
     * ended. Specified only once processing ends.
     *
     * Processing ends when every request in a Message Batch has either succeeded, errored,
     * canceled, or expired.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun endedAt(): Optional<OffsetDateTime> = Optional.ofNullable(endedAt.getNullable("ended_at"))

    /**
     * RFC 3339 datetime string representing the time at which the Message Batch will expire and end
     * processing, which is 24 hours after creation.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun expiresAt(): OffsetDateTime = expiresAt.getRequired("expires_at")

    /**
     * Processing status of the Message Batch.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processingStatus(): ProcessingStatus = processingStatus.getRequired("processing_status")

    /**
     * Tallies requests within the Message Batch, categorized by their status.
     *
     * Requests start as `processing` and move to one of the other statuses only once processing of
     * the entire batch ends. The sum of all values always matches the total number of requests in
     * the batch.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun requestCounts(): MessageBatchRequestCounts = requestCounts.getRequired("request_counts")

    /**
     * URL to a `.jsonl` file containing the results of the Message Batch requests. Specified only
     * once processing ends.
     *
     * Results in the file are not guaranteed to be in the same order as requests. Use the
     * `custom_id` field to match results to requests.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun resultsUrl(): Optional<String> = Optional.ofNullable(resultsUrl.getNullable("results_url"))

    /**
     * Object type.
     *
     * For Message Batches, this is always `"message_batch"`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("message_batch")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [archivedAt].
     *
     * Unlike [archivedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("archived_at")
    @ExcludeMissing
    fun _archivedAt(): JsonField<OffsetDateTime> = archivedAt

    /**
     * Returns the raw JSON value of [cancelInitiatedAt].
     *
     * Unlike [cancelInitiatedAt], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("cancel_initiated_at")
    @ExcludeMissing
    fun _cancelInitiatedAt(): JsonField<OffsetDateTime> = cancelInitiatedAt

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [endedAt].
     *
     * Unlike [endedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ended_at") @ExcludeMissing fun _endedAt(): JsonField<OffsetDateTime> = endedAt

    /**
     * Returns the raw JSON value of [expiresAt].
     *
     * Unlike [expiresAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expires_at")
    @ExcludeMissing
    fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

    /**
     * Returns the raw JSON value of [processingStatus].
     *
     * Unlike [processingStatus], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("processing_status")
    @ExcludeMissing
    fun _processingStatus(): JsonField<ProcessingStatus> = processingStatus

    /**
     * Returns the raw JSON value of [requestCounts].
     *
     * Unlike [requestCounts], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("request_counts")
    @ExcludeMissing
    fun _requestCounts(): JsonField<MessageBatchRequestCounts> = requestCounts

    /**
     * Returns the raw JSON value of [resultsUrl].
     *
     * Unlike [resultsUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("results_url") @ExcludeMissing fun _resultsUrl(): JsonField<String> = resultsUrl

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
         * Returns a mutable builder for constructing an instance of [MessageBatch].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .cancelInitiatedAt()
         * .createdAt()
         * .endedAt()
         * .expiresAt()
         * .processingStatus()
         * .requestCounts()
         * .resultsUrl()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MessageBatch]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var cancelInitiatedAt: JsonField<OffsetDateTime>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var endedAt: JsonField<OffsetDateTime>? = null
        private var expiresAt: JsonField<OffsetDateTime>? = null
        private var processingStatus: JsonField<ProcessingStatus>? = null
        private var requestCounts: JsonField<MessageBatchRequestCounts>? = null
        private var resultsUrl: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("message_batch")
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
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch was archived
         * and its results became unavailable.
         */
        fun archivedAt(archivedAt: OffsetDateTime?) = archivedAt(JsonField.ofNullable(archivedAt))

        /** Alias for calling [Builder.archivedAt] with `archivedAt.orElse(null)`. */
        fun archivedAt(archivedAt: Optional<OffsetDateTime>) = archivedAt(archivedAt.getOrNull())

        /**
         * Sets [Builder.archivedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.archivedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
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

        /** Alias for calling [Builder.cancelInitiatedAt] with `cancelInitiatedAt.orElse(null)`. */
        fun cancelInitiatedAt(cancelInitiatedAt: Optional<OffsetDateTime>) =
            cancelInitiatedAt(cancelInitiatedAt.getOrNull())

        /**
         * Sets [Builder.cancelInitiatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cancelInitiatedAt] with a well-typed [OffsetDateTime]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cancelInitiatedAt(cancelInitiatedAt: JsonField<OffsetDateTime>) = apply {
            this.cancelInitiatedAt = cancelInitiatedAt
        }

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch was created.
         */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
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

        /** Alias for calling [Builder.endedAt] with `endedAt.orElse(null)`. */
        fun endedAt(endedAt: Optional<OffsetDateTime>) = endedAt(endedAt.getOrNull())

        /**
         * Sets [Builder.endedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun endedAt(endedAt: JsonField<OffsetDateTime>) = apply { this.endedAt = endedAt }

        /**
         * RFC 3339 datetime string representing the time at which the Message Batch will expire and
         * end processing, which is 24 hours after creation.
         */
        fun expiresAt(expiresAt: OffsetDateTime) = expiresAt(JsonField.of(expiresAt))

        /**
         * Sets [Builder.expiresAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.expiresAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply { this.expiresAt = expiresAt }

        /** Processing status of the Message Batch. */
        fun processingStatus(processingStatus: ProcessingStatus) =
            processingStatus(JsonField.of(processingStatus))

        /**
         * Sets [Builder.processingStatus] to an arbitrary JSON value.
         *
         * You should usually call [Builder.processingStatus] with a well-typed [ProcessingStatus]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
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
         * Sets [Builder.requestCounts] to an arbitrary JSON value.
         *
         * You should usually call [Builder.requestCounts] with a well-typed
         * [MessageBatchRequestCounts] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
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

        /** Alias for calling [Builder.resultsUrl] with `resultsUrl.orElse(null)`. */
        fun resultsUrl(resultsUrl: Optional<String>) = resultsUrl(resultsUrl.getOrNull())

        /**
         * Sets [Builder.resultsUrl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.resultsUrl] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun resultsUrl(resultsUrl: JsonField<String>) = apply { this.resultsUrl = resultsUrl }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("message_batch")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
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

        /**
         * Returns an immutable instance of [MessageBatch].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .cancelInitiatedAt()
         * .createdAt()
         * .endedAt()
         * .expiresAt()
         * .processingStatus()
         * .requestCounts()
         * .resultsUrl()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
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
                type,
                additionalProperties.toMutableMap(),
            )
    }

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
        _type().let {
            if (it != JsonValue.from("message_batch")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
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

        return /* spotless:off */ other is MessageBatch && id == other.id && archivedAt == other.archivedAt && cancelInitiatedAt == other.cancelInitiatedAt && createdAt == other.createdAt && endedAt == other.endedAt && expiresAt == other.expiresAt && processingStatus == other.processingStatus && requestCounts == other.requestCounts && resultsUrl == other.resultsUrl && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(id, archivedAt, cancelInitiatedAt, createdAt, endedAt, expiresAt, processingStatus, requestCounts, resultsUrl, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "MessageBatch{id=$id, archivedAt=$archivedAt, cancelInitiatedAt=$cancelInitiatedAt, createdAt=$createdAt, endedAt=$endedAt, expiresAt=$expiresAt, processingStatus=$processingStatus, requestCounts=$requestCounts, resultsUrl=$resultsUrl, type=$type, additionalProperties=$additionalProperties}"
}
