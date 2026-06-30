// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Work resource representing a unit of work in a self-hosted environment.
 *
 * Work items are queued when sessions are created or when long-dormant sessions receive new
 * messages. The environment worker polls for work to execute in a self-hosted sandbox.
 */
class BetaSelfHostedWork
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val acknowledgedAt: JsonField<String>,
    private val createdAt: JsonField<String>,
    private val data: JsonField<BetaSessionWorkData>,
    private val environmentId: JsonField<String>,
    private val latestHeartbeatAt: JsonField<String>,
    private val metadata: JsonField<Metadata>,
    private val secret: JsonField<String>,
    private val startedAt: JsonField<String>,
    private val state: JsonField<State>,
    private val stopRequestedAt: JsonField<String>,
    private val stoppedAt: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("acknowledged_at")
        @ExcludeMissing
        acknowledgedAt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at") @ExcludeMissing createdAt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("data")
        @ExcludeMissing
        data: JsonField<BetaSessionWorkData> = JsonMissing.of(),
        @JsonProperty("environment_id")
        @ExcludeMissing
        environmentId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("latest_heartbeat_at")
        @ExcludeMissing
        latestHeartbeatAt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("metadata") @ExcludeMissing metadata: JsonField<Metadata> = JsonMissing.of(),
        @JsonProperty("secret") @ExcludeMissing secret: JsonField<String> = JsonMissing.of(),
        @JsonProperty("started_at") @ExcludeMissing startedAt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("state") @ExcludeMissing state: JsonField<State> = JsonMissing.of(),
        @JsonProperty("stop_requested_at")
        @ExcludeMissing
        stopRequestedAt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("stopped_at") @ExcludeMissing stoppedAt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        id,
        acknowledgedAt,
        createdAt,
        data,
        environmentId,
        latestHeartbeatAt,
        metadata,
        secret,
        startedAt,
        state,
        stopRequestedAt,
        stoppedAt,
        type,
        mutableMapOf(),
    )

    /**
     * Work identifier (e.g., 'work_...')
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * RFC 3339 timestamp when the work item was acknowledged and assigned to a self-hosted sandbox
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun acknowledgedAt(): Optional<String> = acknowledgedAt.getOptional("acknowledged_at")

    /**
     * RFC 3339 timestamp when work was created
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): String = createdAt.getRequired("created_at")

    /**
     * The actual work to be performed
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun data(): BetaSessionWorkData = data.getRequired("data")

    /**
     * Environment identifier this work belongs to (e.g., `env_...`)
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun environmentId(): String = environmentId.getRequired("environment_id")

    /**
     * RFC 3339 timestamp of the most recent heartbeat
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun latestHeartbeatAt(): Optional<String> = latestHeartbeatAt.getOptional("latest_heartbeat_at")

    /**
     * User-provided metadata key-value pairs associated with this work item
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun metadata(): Metadata = metadata.getRequired("metadata")

    /**
     * Credential payload used by the environment worker to execute this work item. May be populated
     * when polling for work; null on all other retrieval paths.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun secret(): Optional<String> = secret.getOptional("secret")

    /**
     * RFC 3339 timestamp when work execution started
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun startedAt(): Optional<String> = startedAt.getOptional("started_at")

    /**
     * Current state of the work item
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun state(): State = state.getRequired("state")

    /**
     * RFC 3339 timestamp when stop was requested
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun stopRequestedAt(): Optional<String> = stopRequestedAt.getOptional("stop_requested_at")

    /**
     * RFC 3339 timestamp when work execution stopped
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun stoppedAt(): Optional<String> = stoppedAt.getOptional("stopped_at")

    /**
     * The type of object (always 'work')
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("work")
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
     * Returns the raw JSON value of [acknowledgedAt].
     *
     * Unlike [acknowledgedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("acknowledged_at")
    @ExcludeMissing
    fun _acknowledgedAt(): JsonField<String> = acknowledgedAt

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at") @ExcludeMissing fun _createdAt(): JsonField<String> = createdAt

    /**
     * Returns the raw JSON value of [data].
     *
     * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<BetaSessionWorkData> = data

    /**
     * Returns the raw JSON value of [environmentId].
     *
     * Unlike [environmentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("environment_id")
    @ExcludeMissing
    fun _environmentId(): JsonField<String> = environmentId

    /**
     * Returns the raw JSON value of [latestHeartbeatAt].
     *
     * Unlike [latestHeartbeatAt], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("latest_heartbeat_at")
    @ExcludeMissing
    fun _latestHeartbeatAt(): JsonField<String> = latestHeartbeatAt

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

    /**
     * Returns the raw JSON value of [secret].
     *
     * Unlike [secret], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("secret") @ExcludeMissing fun _secret(): JsonField<String> = secret

    /**
     * Returns the raw JSON value of [startedAt].
     *
     * Unlike [startedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("started_at") @ExcludeMissing fun _startedAt(): JsonField<String> = startedAt

    /**
     * Returns the raw JSON value of [state].
     *
     * Unlike [state], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("state") @ExcludeMissing fun _state(): JsonField<State> = state

    /**
     * Returns the raw JSON value of [stopRequestedAt].
     *
     * Unlike [stopRequestedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("stop_requested_at")
    @ExcludeMissing
    fun _stopRequestedAt(): JsonField<String> = stopRequestedAt

    /**
     * Returns the raw JSON value of [stoppedAt].
     *
     * Unlike [stoppedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("stopped_at") @ExcludeMissing fun _stoppedAt(): JsonField<String> = stoppedAt

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
         * Returns a mutable builder for constructing an instance of [BetaSelfHostedWork].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .acknowledgedAt()
         * .createdAt()
         * .data()
         * .environmentId()
         * .latestHeartbeatAt()
         * .metadata()
         * .secret()
         * .startedAt()
         * .state()
         * .stopRequestedAt()
         * .stoppedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaSelfHostedWork]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var acknowledgedAt: JsonField<String>? = null
        private var createdAt: JsonField<String>? = null
        private var data: JsonField<BetaSessionWorkData>? = null
        private var environmentId: JsonField<String>? = null
        private var latestHeartbeatAt: JsonField<String>? = null
        private var metadata: JsonField<Metadata>? = null
        private var secret: JsonField<String>? = null
        private var startedAt: JsonField<String>? = null
        private var state: JsonField<State>? = null
        private var stopRequestedAt: JsonField<String>? = null
        private var stoppedAt: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("work")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaSelfHostedWork: BetaSelfHostedWork) = apply {
            id = betaSelfHostedWork.id
            acknowledgedAt = betaSelfHostedWork.acknowledgedAt
            createdAt = betaSelfHostedWork.createdAt
            data = betaSelfHostedWork.data
            environmentId = betaSelfHostedWork.environmentId
            latestHeartbeatAt = betaSelfHostedWork.latestHeartbeatAt
            metadata = betaSelfHostedWork.metadata
            secret = betaSelfHostedWork.secret
            startedAt = betaSelfHostedWork.startedAt
            state = betaSelfHostedWork.state
            stopRequestedAt = betaSelfHostedWork.stopRequestedAt
            stoppedAt = betaSelfHostedWork.stoppedAt
            type = betaSelfHostedWork.type
            additionalProperties = betaSelfHostedWork.additionalProperties.toMutableMap()
        }

        /** Work identifier (e.g., 'work_...') */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * RFC 3339 timestamp when the work item was acknowledged and assigned to a self-hosted
         * sandbox
         */
        fun acknowledgedAt(acknowledgedAt: String?) =
            acknowledgedAt(JsonField.ofNullable(acknowledgedAt))

        /** Alias for calling [Builder.acknowledgedAt] with `acknowledgedAt.orElse(null)`. */
        fun acknowledgedAt(acknowledgedAt: Optional<String>) =
            acknowledgedAt(acknowledgedAt.getOrNull())

        /**
         * Sets [Builder.acknowledgedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.acknowledgedAt] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun acknowledgedAt(acknowledgedAt: JsonField<String>) = apply {
            this.acknowledgedAt = acknowledgedAt
        }

        /** RFC 3339 timestamp when work was created */
        fun createdAt(createdAt: String) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun createdAt(createdAt: JsonField<String>) = apply { this.createdAt = createdAt }

        /** The actual work to be performed */
        fun data(data: BetaSessionWorkData) = data(JsonField.of(data))

        /**
         * Sets [Builder.data] to an arbitrary JSON value.
         *
         * You should usually call [Builder.data] with a well-typed [BetaSessionWorkData] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun data(data: JsonField<BetaSessionWorkData>) = apply { this.data = data }

        /** Environment identifier this work belongs to (e.g., `env_...`) */
        fun environmentId(environmentId: String) = environmentId(JsonField.of(environmentId))

        /**
         * Sets [Builder.environmentId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.environmentId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun environmentId(environmentId: JsonField<String>) = apply {
            this.environmentId = environmentId
        }

        /** RFC 3339 timestamp of the most recent heartbeat */
        fun latestHeartbeatAt(latestHeartbeatAt: String?) =
            latestHeartbeatAt(JsonField.ofNullable(latestHeartbeatAt))

        /** Alias for calling [Builder.latestHeartbeatAt] with `latestHeartbeatAt.orElse(null)`. */
        fun latestHeartbeatAt(latestHeartbeatAt: Optional<String>) =
            latestHeartbeatAt(latestHeartbeatAt.getOrNull())

        /**
         * Sets [Builder.latestHeartbeatAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.latestHeartbeatAt] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun latestHeartbeatAt(latestHeartbeatAt: JsonField<String>) = apply {
            this.latestHeartbeatAt = latestHeartbeatAt
        }

        /** User-provided metadata key-value pairs associated with this work item */
        fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [Metadata] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

        /**
         * Credential payload used by the environment worker to execute this work item. May be
         * populated when polling for work; null on all other retrieval paths.
         */
        fun secret(secret: String?) = secret(JsonField.ofNullable(secret))

        /** Alias for calling [Builder.secret] with `secret.orElse(null)`. */
        fun secret(secret: Optional<String>) = secret(secret.getOrNull())

        /**
         * Sets [Builder.secret] to an arbitrary JSON value.
         *
         * You should usually call [Builder.secret] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun secret(secret: JsonField<String>) = apply { this.secret = secret }

        /** RFC 3339 timestamp when work execution started */
        fun startedAt(startedAt: String?) = startedAt(JsonField.ofNullable(startedAt))

        /** Alias for calling [Builder.startedAt] with `startedAt.orElse(null)`. */
        fun startedAt(startedAt: Optional<String>) = startedAt(startedAt.getOrNull())

        /**
         * Sets [Builder.startedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.startedAt] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun startedAt(startedAt: JsonField<String>) = apply { this.startedAt = startedAt }

        /** Current state of the work item */
        fun state(state: State) = state(JsonField.of(state))

        /**
         * Sets [Builder.state] to an arbitrary JSON value.
         *
         * You should usually call [Builder.state] with a well-typed [State] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun state(state: JsonField<State>) = apply { this.state = state }

        /** RFC 3339 timestamp when stop was requested */
        fun stopRequestedAt(stopRequestedAt: String?) =
            stopRequestedAt(JsonField.ofNullable(stopRequestedAt))

        /** Alias for calling [Builder.stopRequestedAt] with `stopRequestedAt.orElse(null)`. */
        fun stopRequestedAt(stopRequestedAt: Optional<String>) =
            stopRequestedAt(stopRequestedAt.getOrNull())

        /**
         * Sets [Builder.stopRequestedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stopRequestedAt] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun stopRequestedAt(stopRequestedAt: JsonField<String>) = apply {
            this.stopRequestedAt = stopRequestedAt
        }

        /** RFC 3339 timestamp when work execution stopped */
        fun stoppedAt(stoppedAt: String?) = stoppedAt(JsonField.ofNullable(stoppedAt))

        /** Alias for calling [Builder.stoppedAt] with `stoppedAt.orElse(null)`. */
        fun stoppedAt(stoppedAt: Optional<String>) = stoppedAt(stoppedAt.getOrNull())

        /**
         * Sets [Builder.stoppedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stoppedAt] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun stoppedAt(stoppedAt: JsonField<String>) = apply { this.stoppedAt = stoppedAt }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("work")
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
         * Returns an immutable instance of [BetaSelfHostedWork].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .acknowledgedAt()
         * .createdAt()
         * .data()
         * .environmentId()
         * .latestHeartbeatAt()
         * .metadata()
         * .secret()
         * .startedAt()
         * .state()
         * .stopRequestedAt()
         * .stoppedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaSelfHostedWork =
            BetaSelfHostedWork(
                checkRequired("id", id),
                checkRequired("acknowledgedAt", acknowledgedAt),
                checkRequired("createdAt", createdAt),
                checkRequired("data", data),
                checkRequired("environmentId", environmentId),
                checkRequired("latestHeartbeatAt", latestHeartbeatAt),
                checkRequired("metadata", metadata),
                checkRequired("secret", secret),
                checkRequired("startedAt", startedAt),
                checkRequired("state", state),
                checkRequired("stopRequestedAt", stopRequestedAt),
                checkRequired("stoppedAt", stoppedAt),
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
    fun validate(): BetaSelfHostedWork = apply {
        if (validated) {
            return@apply
        }

        id()
        acknowledgedAt()
        createdAt()
        data().validate()
        environmentId()
        latestHeartbeatAt()
        metadata().validate()
        secret()
        startedAt()
        state().validate()
        stopRequestedAt()
        stoppedAt()
        _type().let {
            if (it != JsonValue.from("work")) {
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
            (if (acknowledgedAt.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (data.asKnown().getOrNull()?.validity() ?: 0) +
            (if (environmentId.asKnown().isPresent) 1 else 0) +
            (if (latestHeartbeatAt.asKnown().isPresent) 1 else 0) +
            (metadata.asKnown().getOrNull()?.validity() ?: 0) +
            (if (secret.asKnown().isPresent) 1 else 0) +
            (if (startedAt.asKnown().isPresent) 1 else 0) +
            (state.asKnown().getOrNull()?.validity() ?: 0) +
            (if (stopRequestedAt.asKnown().isPresent) 1 else 0) +
            (if (stoppedAt.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("work")) 1 else 0 }

    /** User-provided metadata key-value pairs associated with this work item */
    class Metadata
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Metadata]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Metadata]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(metadata: Metadata) = apply {
                additionalProperties = metadata.additionalProperties.toMutableMap()
            }

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
             * Returns an immutable instance of [Metadata].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Metadata = Metadata(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Metadata = apply {
            if (validated) {
                return@apply
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Metadata && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Metadata{additionalProperties=$additionalProperties}"
    }

    /** Current state of the work item */
    class State @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val QUEUED = of("queued")

            @JvmField val STARTING = of("starting")

            @JvmField val ACTIVE = of("active")

            @JvmField val STOPPING = of("stopping")

            @JvmField val STOPPED = of("stopped")

            @JvmStatic fun of(value: String) = State(JsonField.of(value))
        }

        /** An enum containing [State]'s known values. */
        enum class Known {
            QUEUED,
            STARTING,
            ACTIVE,
            STOPPING,
            STOPPED,
        }

        /**
         * An enum containing [State]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [State] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            QUEUED,
            STARTING,
            ACTIVE,
            STOPPING,
            STOPPED,
            /** An enum member indicating that [State] was instantiated with an unknown value. */
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
                QUEUED -> Value.QUEUED
                STARTING -> Value.STARTING
                ACTIVE -> Value.ACTIVE
                STOPPING -> Value.STOPPING
                STOPPED -> Value.STOPPED
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
                QUEUED -> Known.QUEUED
                STARTING -> Known.STARTING
                ACTIVE -> Known.ACTIVE
                STOPPING -> Known.STOPPING
                STOPPED -> Known.STOPPED
                else -> throw AnthropicInvalidDataException("Unknown State: $value")
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

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): State = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is State && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaSelfHostedWork &&
            id == other.id &&
            acknowledgedAt == other.acknowledgedAt &&
            createdAt == other.createdAt &&
            data == other.data &&
            environmentId == other.environmentId &&
            latestHeartbeatAt == other.latestHeartbeatAt &&
            metadata == other.metadata &&
            secret == other.secret &&
            startedAt == other.startedAt &&
            state == other.state &&
            stopRequestedAt == other.stopRequestedAt &&
            stoppedAt == other.stoppedAt &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            acknowledgedAt,
            createdAt,
            data,
            environmentId,
            latestHeartbeatAt,
            metadata,
            secret,
            startedAt,
            state,
            stopRequestedAt,
            stoppedAt,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSelfHostedWork{id=$id, acknowledgedAt=$acknowledgedAt, createdAt=$createdAt, data=$data, environmentId=$environmentId, latestHeartbeatAt=$latestHeartbeatAt, metadata=$metadata, secret=$secret, startedAt=$startedAt, state=$state, stopRequestedAt=$stopRequestedAt, stoppedAt=$stoppedAt, type=$type, additionalProperties=$additionalProperties}"
}
