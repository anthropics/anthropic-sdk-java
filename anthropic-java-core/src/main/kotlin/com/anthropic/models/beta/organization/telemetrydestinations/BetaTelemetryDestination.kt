package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
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

/**
 * An OpenTelemetry destination: an OTLP/HTTP endpoint, the request headers sent with every delivery
 * to it, what the exported spans contain, and which workspaces it covers. A destination belongs to
 * an organization. A workspace's spans go to every enabled destination that covers it.
 */
class BetaTelemetryDestination
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val deliveryHealth: JsonField<BetaTelemetryDestinationDeliveryHealth>,
    private val displayName: JsonField<String>,
    private val endpointUrl: JsonField<String>,
    private val headerNames: JsonField<List<String>>,
    private val state: JsonField<BetaTelemetryDestinationState>,
    private val traces: JsonField<BetaTelemetryDestinationTraces>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val workspaces: JsonField<BetaTelemetryDestinationWorkspaces>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("delivery_health")
        @ExcludeMissing
        deliveryHealth: JsonField<BetaTelemetryDestinationDeliveryHealth> = JsonMissing.of(),
        @JsonProperty("display_name")
        @ExcludeMissing
        displayName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("endpoint_url")
        @ExcludeMissing
        endpointUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("header_names")
        @ExcludeMissing
        headerNames: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("state")
        @ExcludeMissing
        state: JsonField<BetaTelemetryDestinationState> = JsonMissing.of(),
        @JsonProperty("traces")
        @ExcludeMissing
        traces: JsonField<BetaTelemetryDestinationTraces> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("workspaces")
        @ExcludeMissing
        workspaces: JsonField<BetaTelemetryDestinationWorkspaces> = JsonMissing.of(),
    ) : this(
        id,
        createdAt,
        deliveryHealth,
        displayName,
        endpointUrl,
        headerNames,
        state,
        traces,
        type,
        updatedAt,
        workspaces,
        mutableMapOf(),
    )

    /**
     * The unique ID of the destination (`tdst_...`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Time the destination was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * The most recent delivery results for this destination.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun deliveryHealth(): BetaTelemetryDestinationDeliveryHealth =
        deliveryHealth.getRequired("delivery_health")

    /**
     * Human-readable name for the destination. Null when none was set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayName(): Optional<String> = displayName.getOptional("display_name")

    /**
     * The base URL of the destination's OTLP/HTTP endpoint, exactly as registered.
     *
     * The API appends the per-signal path, such as `/v1/traces`, on delivery, unless
     * `traces.endpoint_url` is set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun endpointUrl(): String = endpointUrl.getRequired("endpoint_url")

    /**
     * Names of the registered request headers. Header values are write-only and are never returned.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun headerNames(): List<String> = headerNames.getRequired("header_names")

    /**
     * Whether the destination receives deliveries.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun state(): BetaTelemetryDestinationState = state.getRequired("state")

    /**
     * The destination's settings for the traces it receives.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun traces(): BetaTelemetryDestinationTraces = traces.getRequired("traces")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("telemetry_destination")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Time of the last change to the destination's configuration or state. Changes to
     * `delivery_health` don't change it.
     *
     * An update that sets a header value always changes it, even when the value is the same as
     * before. Adding or removing a single workspace on the destination's list doesn't change it. An
     * update that changes the list does.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * Which of the organization's workspaces the destination covers.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaces(): BetaTelemetryDestinationWorkspaces = workspaces.getRequired("workspaces")

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
     * Returns the raw JSON value of [deliveryHealth].
     *
     * Unlike [deliveryHealth], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("delivery_health")
    @ExcludeMissing
    fun _deliveryHealth(): JsonField<BetaTelemetryDestinationDeliveryHealth> = deliveryHealth

    /**
     * Returns the raw JSON value of [displayName].
     *
     * Unlike [displayName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display_name")
    @ExcludeMissing
    fun _displayName(): JsonField<String> = displayName

    /**
     * Returns the raw JSON value of [endpointUrl].
     *
     * Unlike [endpointUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("endpoint_url")
    @ExcludeMissing
    fun _endpointUrl(): JsonField<String> = endpointUrl

    /**
     * Returns the raw JSON value of [headerNames].
     *
     * Unlike [headerNames], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("header_names")
    @ExcludeMissing
    fun _headerNames(): JsonField<List<String>> = headerNames

    /**
     * Returns the raw JSON value of [state].
     *
     * Unlike [state], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("state")
    @ExcludeMissing
    fun _state(): JsonField<BetaTelemetryDestinationState> = state

    /**
     * Returns the raw JSON value of [traces].
     *
     * Unlike [traces], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("traces")
    @ExcludeMissing
    fun _traces(): JsonField<BetaTelemetryDestinationTraces> = traces

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [workspaces].
     *
     * Unlike [workspaces], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspaces")
    @ExcludeMissing
    fun _workspaces(): JsonField<BetaTelemetryDestinationWorkspaces> = workspaces

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
         * Returns a mutable builder for constructing an instance of [BetaTelemetryDestination].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .deliveryHealth()
         * .displayName()
         * .endpointUrl()
         * .headerNames()
         * .state()
         * .traces()
         * .updatedAt()
         * .workspaces()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTelemetryDestination]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var deliveryHealth: JsonField<BetaTelemetryDestinationDeliveryHealth>? = null
        private var displayName: JsonField<String>? = null
        private var endpointUrl: JsonField<String>? = null
        private var headerNames: JsonField<MutableList<String>>? = null
        private var state: JsonField<BetaTelemetryDestinationState>? = null
        private var traces: JsonField<BetaTelemetryDestinationTraces>? = null
        private var type: JsonValue = JsonValue.from("telemetry_destination")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var workspaces: JsonField<BetaTelemetryDestinationWorkspaces>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaTelemetryDestination: BetaTelemetryDestination) = apply {
            id = betaTelemetryDestination.id
            createdAt = betaTelemetryDestination.createdAt
            deliveryHealth = betaTelemetryDestination.deliveryHealth
            displayName = betaTelemetryDestination.displayName
            endpointUrl = betaTelemetryDestination.endpointUrl
            headerNames =
                betaTelemetryDestination.headerNames
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            state = betaTelemetryDestination.state
            traces = betaTelemetryDestination.traces
            type = betaTelemetryDestination.type
            updatedAt = betaTelemetryDestination.updatedAt
            workspaces = betaTelemetryDestination.workspaces
            additionalProperties = betaTelemetryDestination.additionalProperties.toMutableMap()
        }

        /** The unique ID of the destination (`tdst_...`). */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Time the destination was created. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** The most recent delivery results for this destination. */
        fun deliveryHealth(deliveryHealth: BetaTelemetryDestinationDeliveryHealth) =
            deliveryHealth(JsonField.of(deliveryHealth))

        /**
         * Sets [Builder.deliveryHealth] to an arbitrary JSON value.
         *
         * You should usually call [Builder.deliveryHealth] with a well-typed
         * [BetaTelemetryDestinationDeliveryHealth] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun deliveryHealth(deliveryHealth: JsonField<BetaTelemetryDestinationDeliveryHealth>) =
            apply {
                this.deliveryHealth = deliveryHealth
            }

        /** Human-readable name for the destination. Null when none was set. */
        fun displayName(displayName: String?) = displayName(JsonField.ofNullable(displayName))

        /** Alias for calling [Builder.displayName] with `displayName.orElse(null)`. */
        fun displayName(displayName: Optional<String>) = displayName(displayName.getOrNull())

        /**
         * Sets [Builder.displayName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayName(displayName: JsonField<String>) = apply { this.displayName = displayName }

        /**
         * The base URL of the destination's OTLP/HTTP endpoint, exactly as registered.
         *
         * The API appends the per-signal path, such as `/v1/traces`, on delivery, unless
         * `traces.endpoint_url` is set.
         */
        fun endpointUrl(endpointUrl: String) = endpointUrl(JsonField.of(endpointUrl))

        /**
         * Sets [Builder.endpointUrl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endpointUrl] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun endpointUrl(endpointUrl: JsonField<String>) = apply { this.endpointUrl = endpointUrl }

        /**
         * Names of the registered request headers. Header values are write-only and are never
         * returned.
         */
        fun headerNames(headerNames: List<String>) = headerNames(JsonField.of(headerNames))

        /**
         * Sets [Builder.headerNames] to an arbitrary JSON value.
         *
         * You should usually call [Builder.headerNames] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun headerNames(headerNames: JsonField<List<String>>) = apply {
            this.headerNames = headerNames.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [headerNames].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addHeaderName(headerName: String) = apply {
            headerNames =
                (headerNames ?: JsonField.of(mutableListOf())).also {
                    checkKnown("headerNames", it).add(headerName)
                }
        }

        /** Whether the destination receives deliveries. */
        fun state(state: BetaTelemetryDestinationState) = state(JsonField.of(state))

        /**
         * Sets [Builder.state] to an arbitrary JSON value.
         *
         * You should usually call [Builder.state] with a well-typed [BetaTelemetryDestinationState]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun state(state: JsonField<BetaTelemetryDestinationState>) = apply { this.state = state }

        /** Alias for calling [state] with `BetaTelemetryDestinationState.ofEnabled(enabled)`. */
        fun state(enabled: BetaTelemetryDestinationStateEnabled) =
            state(BetaTelemetryDestinationState.ofEnabled(enabled))

        /** Alias for calling [state] with `BetaTelemetryDestinationState.ofDisabled(disabled)`. */
        fun state(disabled: BetaTelemetryDestinationStateDisabled) =
            state(BetaTelemetryDestinationState.ofDisabled(disabled))

        /**
         * Alias for calling [state] with the following:
         * ```java
         * BetaTelemetryDestinationStateDisabled.builder()
         *     .reason(reason)
         *     .build()
         * ```
         */
        fun disabledState(reason: BetaTelemetryDestinationDisabledReason) =
            state(BetaTelemetryDestinationStateDisabled.builder().reason(reason).build())

        /**
         * Alias for calling [disabledState] with
         * `BetaTelemetryDestinationDisabledReason.ofCaller(caller)`.
         */
        fun disabledState(caller: BetaTelemetryDestinationDisabledReasonCaller) =
            disabledState(BetaTelemetryDestinationDisabledReason.ofCaller(caller))

        /**
         * Alias for calling [disabledState] with
         * `BetaTelemetryDestinationDisabledReason.ofSustainedFailure(sustainedFailure)`.
         */
        fun disabledState(
            sustainedFailure: BetaTelemetryDestinationDisabledReasonSustainedFailure
        ) =
            disabledState(
                BetaTelemetryDestinationDisabledReason.ofSustainedFailure(sustainedFailure)
            )

        /**
         * Alias for calling [disabledState] with
         * `BetaTelemetryDestinationDisabledReason.ofInvalidAddress(invalidAddress)`.
         */
        fun disabledState(invalidAddress: BetaTelemetryDestinationDisabledReasonInvalidAddress) =
            disabledState(BetaTelemetryDestinationDisabledReason.ofInvalidAddress(invalidAddress))

        /**
         * Alias for calling [disabledState] with
         * `BetaTelemetryDestinationDisabledReason.ofRedirect(redirect)`.
         */
        fun disabledState(redirect: BetaTelemetryDestinationDisabledReasonRedirect) =
            disabledState(BetaTelemetryDestinationDisabledReason.ofRedirect(redirect))

        /** The destination's settings for the traces it receives. */
        fun traces(traces: BetaTelemetryDestinationTraces) = traces(JsonField.of(traces))

        /**
         * Sets [Builder.traces] to an arbitrary JSON value.
         *
         * You should usually call [Builder.traces] with a well-typed
         * [BetaTelemetryDestinationTraces] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun traces(traces: JsonField<BetaTelemetryDestinationTraces>) = apply {
            this.traces = traces
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("telemetry_destination")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Time of the last change to the destination's configuration or state. Changes to
         * `delivery_health` don't change it.
         *
         * An update that sets a header value always changes it, even when the value is the same as
         * before. Adding or removing a single workspace on the destination's list doesn't change
         * it. An update that changes the list does.
         */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /** Which of the organization's workspaces the destination covers. */
        fun workspaces(workspaces: BetaTelemetryDestinationWorkspaces) =
            workspaces(JsonField.of(workspaces))

        /**
         * Sets [Builder.workspaces] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaces] with a well-typed
         * [BetaTelemetryDestinationWorkspaces] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun workspaces(workspaces: JsonField<BetaTelemetryDestinationWorkspaces>) = apply {
            this.workspaces = workspaces
        }

        /** Alias for calling [workspaces] with `BetaTelemetryDestinationWorkspaces.ofAll(all)`. */
        fun workspaces(all: BetaTelemetryDestinationWorkspacesAll) =
            workspaces(BetaTelemetryDestinationWorkspaces.ofAll(all))

        /**
         * Alias for calling [workspaces] with
         * `BetaTelemetryDestinationWorkspaces.ofInclude(include)`.
         */
        fun workspaces(include: BetaTelemetryDestinationWorkspacesInclude) =
            workspaces(BetaTelemetryDestinationWorkspaces.ofInclude(include))

        /**
         * Alias for calling [workspaces] with
         * `BetaTelemetryDestinationWorkspaces.ofExclude(exclude)`.
         */
        fun workspaces(exclude: BetaTelemetryDestinationWorkspacesExclude) =
            workspaces(BetaTelemetryDestinationWorkspaces.ofExclude(exclude))

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
         * Returns an immutable instance of [BetaTelemetryDestination].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .deliveryHealth()
         * .displayName()
         * .endpointUrl()
         * .headerNames()
         * .state()
         * .traces()
         * .updatedAt()
         * .workspaces()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaTelemetryDestination =
            BetaTelemetryDestination(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("deliveryHealth", deliveryHealth),
                checkRequired("displayName", displayName),
                checkRequired("endpointUrl", endpointUrl),
                checkRequired("headerNames", headerNames).map { it.toImmutable() },
                checkRequired("state", state),
                checkRequired("traces", traces),
                type,
                checkRequired("updatedAt", updatedAt),
                checkRequired("workspaces", workspaces),
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
    fun validate(): BetaTelemetryDestination = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        deliveryHealth().validate()
        displayName()
        endpointUrl()
        headerNames()
        state().validate()
        traces().validate()
        _type().let {
            if (it != JsonValue.from("telemetry_destination")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
        workspaces().validate()
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
            (deliveryHealth.asKnown().getOrNull()?.validity() ?: 0) +
            (if (displayName.asKnown().isPresent) 1 else 0) +
            (if (endpointUrl.asKnown().isPresent) 1 else 0) +
            (headerNames.asKnown().getOrNull()?.size ?: 0) +
            (state.asKnown().getOrNull()?.validity() ?: 0) +
            (traces.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("telemetry_destination")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (workspaces.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTelemetryDestination &&
            id == other.id &&
            createdAt == other.createdAt &&
            deliveryHealth == other.deliveryHealth &&
            displayName == other.displayName &&
            endpointUrl == other.endpointUrl &&
            headerNames == other.headerNames &&
            state == other.state &&
            traces == other.traces &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            workspaces == other.workspaces &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            deliveryHealth,
            displayName,
            endpointUrl,
            headerNames,
            state,
            traces,
            type,
            updatedAt,
            workspaces,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTelemetryDestination{id=$id, createdAt=$createdAt, deliveryHealth=$deliveryHealth, displayName=$displayName, endpointUrl=$endpointUrl, headerNames=$headerNames, state=$state, traces=$traces, type=$type, updatedAt=$updatedAt, workspaces=$workspaces, additionalProperties=$additionalProperties}"
}
