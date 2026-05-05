// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

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
import kotlin.jvm.optionals.getOrNull

class BetaWebhookEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val data: JsonField<BetaWebhookEventData>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("data")
        @ExcludeMissing
        data: JsonField<BetaWebhookEventData> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, createdAt, data, type, mutableMapOf())

    /**
     * Unique event identifier for idempotency.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * RFC 3339 timestamp when the event occurred.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun data(): BetaWebhookEventData = data.getRequired("data")

    /**
     * Object type. Always `event` for webhook payloads.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("event")
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
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [data].
     *
     * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<BetaWebhookEventData> = data

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
         * Returns a mutable builder for constructing an instance of [BetaWebhookEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .data()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaWebhookEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var data: JsonField<BetaWebhookEventData>? = null
        private var type: JsonValue = JsonValue.from("event")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaWebhookEvent: BetaWebhookEvent) = apply {
            id = betaWebhookEvent.id
            createdAt = betaWebhookEvent.createdAt
            data = betaWebhookEvent.data
            type = betaWebhookEvent.type
            additionalProperties = betaWebhookEvent.additionalProperties.toMutableMap()
        }

        /** Unique event identifier for idempotency. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** RFC 3339 timestamp when the event occurred. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        fun data(data: BetaWebhookEventData) = data(JsonField.of(data))

        /**
         * Sets [Builder.data] to an arbitrary JSON value.
         *
         * You should usually call [Builder.data] with a well-typed [BetaWebhookEventData] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun data(data: JsonField<BetaWebhookEventData>) = apply { this.data = data }

        /**
         * Alias for calling [data] with `BetaWebhookEventData.ofSessionCreated(sessionCreated)`.
         */
        fun data(sessionCreated: BetaWebhookSessionCreatedEventData) =
            data(BetaWebhookEventData.ofSessionCreated(sessionCreated))

        /**
         * Alias for calling [data] with `BetaWebhookEventData.ofSessionPending(sessionPending)`.
         */
        fun data(sessionPending: BetaWebhookSessionPendingEventData) =
            data(BetaWebhookEventData.ofSessionPending(sessionPending))

        /**
         * Alias for calling [data] with `BetaWebhookEventData.ofSessionRunning(sessionRunning)`.
         */
        fun data(sessionRunning: BetaWebhookSessionRunningEventData) =
            data(BetaWebhookEventData.ofSessionRunning(sessionRunning))

        /** Alias for calling [data] with `BetaWebhookEventData.ofSessionIdled(sessionIdled)`. */
        fun data(sessionIdled: BetaWebhookSessionIdledEventData) =
            data(BetaWebhookEventData.ofSessionIdled(sessionIdled))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofSessionRequiresAction(sessionRequiresAction)`.
         */
        fun data(sessionRequiresAction: BetaWebhookSessionRequiresActionEventData) =
            data(BetaWebhookEventData.ofSessionRequiresAction(sessionRequiresAction))

        /**
         * Alias for calling [data] with `BetaWebhookEventData.ofSessionArchived(sessionArchived)`.
         */
        fun data(sessionArchived: BetaWebhookSessionArchivedEventData) =
            data(BetaWebhookEventData.ofSessionArchived(sessionArchived))

        /**
         * Alias for calling [data] with `BetaWebhookEventData.ofSessionDeleted(sessionDeleted)`.
         */
        fun data(sessionDeleted: BetaWebhookSessionDeletedEventData) =
            data(BetaWebhookEventData.ofSessionDeleted(sessionDeleted))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofSessionStatusScheduled(sessionStatusScheduled)`.
         */
        fun data(sessionStatusScheduled: BetaWebhookSessionStatusScheduledEventData) =
            data(BetaWebhookEventData.ofSessionStatusScheduled(sessionStatusScheduled))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofSessionStatusRunStarted(sessionStatusRunStarted)`.
         */
        fun data(sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData) =
            data(BetaWebhookEventData.ofSessionStatusRunStarted(sessionStatusRunStarted))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofSessionStatusIdled(sessionStatusIdled)`.
         */
        fun data(sessionStatusIdled: BetaWebhookSessionStatusIdledEventData) =
            data(BetaWebhookEventData.ofSessionStatusIdled(sessionStatusIdled))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofSessionStatusTerminated(sessionStatusTerminated)`.
         */
        fun data(sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData) =
            data(BetaWebhookEventData.ofSessionStatusTerminated(sessionStatusTerminated))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofSessionThreadCreated(sessionThreadCreated)`.
         */
        fun data(sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData) =
            data(BetaWebhookEventData.ofSessionThreadCreated(sessionThreadCreated))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofSessionThreadIdled(sessionThreadIdled)`.
         */
        fun data(sessionThreadIdled: BetaWebhookSessionThreadIdledEventData) =
            data(BetaWebhookEventData.ofSessionThreadIdled(sessionThreadIdled))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofSessionThreadTerminated(sessionThreadTerminated)`.
         */
        fun data(sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData) =
            data(BetaWebhookEventData.ofSessionThreadTerminated(sessionThreadTerminated))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofSessionOutcomeEvaluationEnded(sessionOutcomeEvaluationEnded)`.
         */
        fun data(sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData) =
            data(
                BetaWebhookEventData.ofSessionOutcomeEvaluationEnded(sessionOutcomeEvaluationEnded)
            )

        /** Alias for calling [data] with `BetaWebhookEventData.ofVaultCreated(vaultCreated)`. */
        fun data(vaultCreated: BetaWebhookVaultCreatedEventData) =
            data(BetaWebhookEventData.ofVaultCreated(vaultCreated))

        /** Alias for calling [data] with `BetaWebhookEventData.ofVaultArchived(vaultArchived)`. */
        fun data(vaultArchived: BetaWebhookVaultArchivedEventData) =
            data(BetaWebhookEventData.ofVaultArchived(vaultArchived))

        /** Alias for calling [data] with `BetaWebhookEventData.ofVaultDeleted(vaultDeleted)`. */
        fun data(vaultDeleted: BetaWebhookVaultDeletedEventData) =
            data(BetaWebhookEventData.ofVaultDeleted(vaultDeleted))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofVaultCredentialCreated(vaultCredentialCreated)`.
         */
        fun data(vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData) =
            data(BetaWebhookEventData.ofVaultCredentialCreated(vaultCredentialCreated))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofVaultCredentialArchived(vaultCredentialArchived)`.
         */
        fun data(vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData) =
            data(BetaWebhookEventData.ofVaultCredentialArchived(vaultCredentialArchived))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofVaultCredentialDeleted(vaultCredentialDeleted)`.
         */
        fun data(vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData) =
            data(BetaWebhookEventData.ofVaultCredentialDeleted(vaultCredentialDeleted))

        /**
         * Alias for calling [data] with
         * `BetaWebhookEventData.ofVaultCredentialRefreshFailed(vaultCredentialRefreshFailed)`.
         */
        fun data(vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData) =
            data(BetaWebhookEventData.ofVaultCredentialRefreshFailed(vaultCredentialRefreshFailed))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("event")
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
         * Returns an immutable instance of [BetaWebhookEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .data()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaWebhookEvent =
            BetaWebhookEvent(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("data", data),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaWebhookEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        data().validate()
        _type().let {
            if (it != JsonValue.from("event")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (data.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("event")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWebhookEvent &&
            id == other.id &&
            createdAt == other.createdAt &&
            data == other.data &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, createdAt, data, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaWebhookEvent{id=$id, createdAt=$createdAt, data=$data, type=$type, additionalProperties=$additionalProperties}"
}
