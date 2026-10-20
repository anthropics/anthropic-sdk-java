package com.anthropic.models.beta.organization.webhooks

import com.anthropic.core.Enum
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
import kotlin.jvm.optionals.getOrNull

/** A new organization webhook endpoint, with its signing secret. */
class BetaNewOrganizationWebhookEndpoint
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val description: JsonField<String>,
    private val disabledReason: JsonField<String>,
    private val enabledEvents: JsonField<List<EnabledEvent>>,
    private val name: JsonField<String>,
    private val signingSecret: JsonField<String>,
    private val state: JsonField<BetaOrganizationWebhookEndpointState>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val url: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("disabled_reason")
        @ExcludeMissing
        disabledReason: JsonField<String> = JsonMissing.of(),
        @JsonProperty("enabled_events")
        @ExcludeMissing
        enabledEvents: JsonField<List<EnabledEvent>> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("signing_secret")
        @ExcludeMissing
        signingSecret: JsonField<String> = JsonMissing.of(),
        @JsonProperty("state")
        @ExcludeMissing
        state: JsonField<BetaOrganizationWebhookEndpointState> = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        createdAt,
        description,
        disabledReason,
        enabledEvents,
        name,
        signingSecret,
        state,
        updatedAt,
        url,
        mutableMapOf(),
    )

    /**
     * ID of the webhook endpoint.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * When the endpoint was created, in RFC 3339 format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Notes about the endpoint. An empty string when none were given.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun description(): String = description.getRequired("description")

    /**
     * Why Anthropic disabled the endpoint automatically. An empty string when the endpoint is
     * enabled, or when you disabled it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun disabledReason(): String = disabledReason.getRequired("disabled_reason")

    /**
     * The event types delivered to the endpoint.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun enabledEvents(): List<EnabledEvent> = enabledEvents.getRequired("enabled_events")

    /**
     * A short label for the endpoint. An empty string when none was given.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * The secret Anthropic signs deliveries to the endpoint with (prefix `whsec_`). No other
     * response returns it, so store it now. To replace it, regenerate the signing secret.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun signingSecret(): String = signingSecret.getRequired("signing_secret")

    /**
     * Whether deliveries are sent to the endpoint.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun state(): BetaOrganizationWebhookEndpointState = state.getRequired("state")

    /**
     * When the endpoint was last changed, in RFC 3339 format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * The HTTPS URL that deliveries are sent to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun url(): String = url.getRequired("url")

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
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    /**
     * Returns the raw JSON value of [disabledReason].
     *
     * Unlike [disabledReason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("disabled_reason")
    @ExcludeMissing
    fun _disabledReason(): JsonField<String> = disabledReason

    /**
     * Returns the raw JSON value of [enabledEvents].
     *
     * Unlike [enabledEvents], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("enabled_events")
    @ExcludeMissing
    fun _enabledEvents(): JsonField<List<EnabledEvent>> = enabledEvents

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [signingSecret].
     *
     * Unlike [signingSecret], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("signing_secret")
    @ExcludeMissing
    fun _signingSecret(): JsonField<String> = signingSecret

    /**
     * Returns the raw JSON value of [state].
     *
     * Unlike [state], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("state")
    @ExcludeMissing
    fun _state(): JsonField<BetaOrganizationWebhookEndpointState> = state

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [url].
     *
     * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

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
         * Returns a mutable builder for constructing an instance of
         * [BetaNewOrganizationWebhookEndpoint].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .description()
         * .disabledReason()
         * .enabledEvents()
         * .name()
         * .signingSecret()
         * .state()
         * .updatedAt()
         * .url()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaNewOrganizationWebhookEndpoint]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var description: JsonField<String>? = null
        private var disabledReason: JsonField<String>? = null
        private var enabledEvents: JsonField<MutableList<EnabledEvent>>? = null
        private var name: JsonField<String>? = null
        private var signingSecret: JsonField<String>? = null
        private var state: JsonField<BetaOrganizationWebhookEndpointState>? = null
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var url: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaNewOrganizationWebhookEndpoint: BetaNewOrganizationWebhookEndpoint) =
            apply {
                id = betaNewOrganizationWebhookEndpoint.id
                createdAt = betaNewOrganizationWebhookEndpoint.createdAt
                description = betaNewOrganizationWebhookEndpoint.description
                disabledReason = betaNewOrganizationWebhookEndpoint.disabledReason
                enabledEvents =
                    betaNewOrganizationWebhookEndpoint.enabledEvents
                        .map { it.toMutableList() }
                        .takeUnless { it.isMissing() }
                name = betaNewOrganizationWebhookEndpoint.name
                signingSecret = betaNewOrganizationWebhookEndpoint.signingSecret
                state = betaNewOrganizationWebhookEndpoint.state
                updatedAt = betaNewOrganizationWebhookEndpoint.updatedAt
                url = betaNewOrganizationWebhookEndpoint.url
                additionalProperties =
                    betaNewOrganizationWebhookEndpoint.additionalProperties.toMutableMap()
            }

        /** ID of the webhook endpoint. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** When the endpoint was created, in RFC 3339 format. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** Notes about the endpoint. An empty string when none were given. */
        fun description(description: String) = description(JsonField.of(description))

        /**
         * Sets [Builder.description] to an arbitrary JSON value.
         *
         * You should usually call [Builder.description] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun description(description: JsonField<String>) = apply { this.description = description }

        /**
         * Why Anthropic disabled the endpoint automatically. An empty string when the endpoint is
         * enabled, or when you disabled it.
         */
        fun disabledReason(disabledReason: String) = disabledReason(JsonField.of(disabledReason))

        /**
         * Sets [Builder.disabledReason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.disabledReason] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun disabledReason(disabledReason: JsonField<String>) = apply {
            this.disabledReason = disabledReason
        }

        /** The event types delivered to the endpoint. */
        fun enabledEvents(enabledEvents: List<EnabledEvent>) =
            enabledEvents(JsonField.of(enabledEvents))

        /**
         * Sets [Builder.enabledEvents] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enabledEvents] with a well-typed `List<EnabledEvent>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun enabledEvents(enabledEvents: JsonField<List<EnabledEvent>>) = apply {
            this.enabledEvents = enabledEvents.map { it.toMutableList() }
        }

        /**
         * Adds a single [EnabledEvent] to [enabledEvents].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addEnabledEvent(enabledEvent: EnabledEvent) = apply {
            enabledEvents =
                (enabledEvents ?: JsonField.of(mutableListOf())).also {
                    checkKnown("enabledEvents", it).add(enabledEvent)
                }
        }

        /** A short label for the endpoint. An empty string when none was given. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /**
         * The secret Anthropic signs deliveries to the endpoint with (prefix `whsec_`). No other
         * response returns it, so store it now. To replace it, regenerate the signing secret.
         */
        fun signingSecret(signingSecret: String) = signingSecret(JsonField.of(signingSecret))

        /**
         * Sets [Builder.signingSecret] to an arbitrary JSON value.
         *
         * You should usually call [Builder.signingSecret] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun signingSecret(signingSecret: JsonField<String>) = apply {
            this.signingSecret = signingSecret
        }

        /** Whether deliveries are sent to the endpoint. */
        fun state(state: BetaOrganizationWebhookEndpointState) = state(JsonField.of(state))

        /**
         * Sets [Builder.state] to an arbitrary JSON value.
         *
         * You should usually call [Builder.state] with a well-typed
         * [BetaOrganizationWebhookEndpointState] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun state(state: JsonField<BetaOrganizationWebhookEndpointState>) = apply {
            this.state = state
        }

        /** When the endpoint was last changed, in RFC 3339 format. */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /** The HTTPS URL that deliveries are sent to. */
        fun url(url: String) = url(JsonField.of(url))

        /**
         * Sets [Builder.url] to an arbitrary JSON value.
         *
         * You should usually call [Builder.url] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun url(url: JsonField<String>) = apply { this.url = url }

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
         * Returns an immutable instance of [BetaNewOrganizationWebhookEndpoint].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .description()
         * .disabledReason()
         * .enabledEvents()
         * .name()
         * .signingSecret()
         * .state()
         * .updatedAt()
         * .url()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaNewOrganizationWebhookEndpoint =
            BetaNewOrganizationWebhookEndpoint(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("description", description),
                checkRequired("disabledReason", disabledReason),
                checkRequired("enabledEvents", enabledEvents).map { it.toImmutable() },
                checkRequired("name", name),
                checkRequired("signingSecret", signingSecret),
                checkRequired("state", state),
                checkRequired("updatedAt", updatedAt),
                checkRequired("url", url),
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
    fun validate(): BetaNewOrganizationWebhookEndpoint = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        description()
        disabledReason()
        enabledEvents().forEach { it.validate() }
        name()
        signingSecret()
        state().validate()
        updatedAt()
        url()
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
            (if (description.asKnown().isPresent) 1 else 0) +
            (if (disabledReason.asKnown().isPresent) 1 else 0) +
            (enabledEvents.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (if (signingSecret.asKnown().isPresent) 1 else 0) +
            (state.asKnown().getOrNull()?.validity() ?: 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (url.asKnown().isPresent) 1 else 0)

    class EnabledEvent private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField
            val USER_PROFILE_ENFORCEMENT_ACTION_OPENED =
                EnabledEvent(JsonField.of("user_profile.enforcement_action_opened"))

            @JvmField
            val USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED =
                EnabledEvent(JsonField.of("user_profile.enforcement_action_resolved"))

            @JvmStatic
            fun of(value: String): EnabledEvent =
                // Intern known values so `==` works
                when (value) {
                    "user_profile.enforcement_action_opened" ->
                        USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                    "user_profile.enforcement_action_resolved" ->
                        USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED
                    else -> EnabledEvent(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): EnabledEvent =
                value.asString().getOrNull()?.let { of(it) } ?: EnabledEvent(value)
        }

        /** An enum containing [EnabledEvent]'s known values. */
        enum class Known {
            USER_PROFILE_ENFORCEMENT_ACTION_OPENED,
            USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED,
        }

        /**
         * An enum containing [EnabledEvent]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [EnabledEvent] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            USER_PROFILE_ENFORCEMENT_ACTION_OPENED,
            USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED,
            /**
             * An enum member indicating that [EnabledEvent] was instantiated with an unknown value.
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
                USER_PROFILE_ENFORCEMENT_ACTION_OPENED ->
                    Value.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED ->
                    Value.USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED
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
                USER_PROFILE_ENFORCEMENT_ACTION_OPENED ->
                    Known.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED ->
                    Known.USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED
                else -> throw AnthropicInvalidDataException("Unknown EnabledEvent: $value")
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
        fun validate(): EnabledEvent = apply {
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

            return other is EnabledEvent && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaNewOrganizationWebhookEndpoint &&
            id == other.id &&
            createdAt == other.createdAt &&
            description == other.description &&
            disabledReason == other.disabledReason &&
            enabledEvents == other.enabledEvents &&
            name == other.name &&
            signingSecret == other.signingSecret &&
            state == other.state &&
            updatedAt == other.updatedAt &&
            url == other.url &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            description,
            disabledReason,
            enabledEvents,
            name,
            signingSecret,
            state,
            updatedAt,
            url,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaNewOrganizationWebhookEndpoint{id=$id, createdAt=$createdAt, description=$description, disabledReason=$disabledReason, enabledEvents=$enabledEvents, name=$name, signingSecret=$signingSecret, state=$state, updatedAt=$updatedAt, url=$url, additionalProperties=$additionalProperties}"
}
