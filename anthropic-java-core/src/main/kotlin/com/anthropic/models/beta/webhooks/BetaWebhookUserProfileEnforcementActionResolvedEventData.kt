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
import java.util.Collections
import java.util.Objects

class BetaWebhookUserProfileEnforcementActionResolvedEventData
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val enforcementActionId: JsonField<String>,
    private val organizationId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("enforcement_action_id")
        @ExcludeMissing
        enforcementActionId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("organization_id")
        @ExcludeMissing
        organizationId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, enforcementActionId, organizationId, type, mutableMapOf())

    /**
     * ID of the user profile the enforcement action is on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * ID (`enac_...`) of the enforcement action that was opened or resolved.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun enforcementActionId(): String = enforcementActionId.getRequired("enforcement_action_id")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun organizationId(): String = organizationId.getRequired("organization_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("user_profile.enforcement_action_resolved")
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
     * Returns the raw JSON value of [enforcementActionId].
     *
     * Unlike [enforcementActionId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("enforcement_action_id")
    @ExcludeMissing
    fun _enforcementActionId(): JsonField<String> = enforcementActionId

    /**
     * Returns the raw JSON value of [organizationId].
     *
     * Unlike [organizationId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("organization_id")
    @ExcludeMissing
    fun _organizationId(): JsonField<String> = organizationId

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
         * [BetaWebhookUserProfileEnforcementActionResolvedEventData].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .enforcementActionId()
         * .organizationId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaWebhookUserProfileEnforcementActionResolvedEventData]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var enforcementActionId: JsonField<String>? = null
        private var organizationId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("user_profile.enforcement_action_resolved")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaWebhookUserProfileEnforcementActionResolvedEventData:
                BetaWebhookUserProfileEnforcementActionResolvedEventData
        ) = apply {
            id = betaWebhookUserProfileEnforcementActionResolvedEventData.id
            enforcementActionId =
                betaWebhookUserProfileEnforcementActionResolvedEventData.enforcementActionId
            organizationId = betaWebhookUserProfileEnforcementActionResolvedEventData.organizationId
            type = betaWebhookUserProfileEnforcementActionResolvedEventData.type
            additionalProperties =
                betaWebhookUserProfileEnforcementActionResolvedEventData.additionalProperties
                    .toMutableMap()
        }

        /** ID of the user profile the enforcement action is on. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** ID (`enac_...`) of the enforcement action that was opened or resolved. */
        fun enforcementActionId(enforcementActionId: String) =
            enforcementActionId(JsonField.of(enforcementActionId))

        /**
         * Sets [Builder.enforcementActionId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enforcementActionId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun enforcementActionId(enforcementActionId: JsonField<String>) = apply {
            this.enforcementActionId = enforcementActionId
        }

        fun organizationId(organizationId: String) = organizationId(JsonField.of(organizationId))

        /**
         * Sets [Builder.organizationId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.organizationId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun organizationId(organizationId: JsonField<String>) = apply {
            this.organizationId = organizationId
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("user_profile.enforcement_action_resolved")
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
         * Returns an immutable instance of
         * [BetaWebhookUserProfileEnforcementActionResolvedEventData].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .enforcementActionId()
         * .organizationId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaWebhookUserProfileEnforcementActionResolvedEventData =
            BetaWebhookUserProfileEnforcementActionResolvedEventData(
                checkRequired("id", id),
                checkRequired("enforcementActionId", enforcementActionId),
                checkRequired("organizationId", organizationId),
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
    fun validate(): BetaWebhookUserProfileEnforcementActionResolvedEventData = apply {
        if (validated) {
            return@apply
        }

        id()
        enforcementActionId()
        organizationId()
        _type().let {
            if (it != JsonValue.from("user_profile.enforcement_action_resolved")) {
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
            (if (enforcementActionId.asKnown().isPresent) 1 else 0) +
            (if (organizationId.asKnown().isPresent) 1 else 0) +
            type.let {
                if (it == JsonValue.from("user_profile.enforcement_action_resolved")) 1 else 0
            }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWebhookUserProfileEnforcementActionResolvedEventData &&
            id == other.id &&
            enforcementActionId == other.enforcementActionId &&
            organizationId == other.organizationId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, enforcementActionId, organizationId, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaWebhookUserProfileEnforcementActionResolvedEventData{id=$id, enforcementActionId=$enforcementActionId, organizationId=$organizationId, type=$type, additionalProperties=$additionalProperties}"
}
