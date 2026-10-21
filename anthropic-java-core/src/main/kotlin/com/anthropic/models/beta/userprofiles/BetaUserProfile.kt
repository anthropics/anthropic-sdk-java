package com.anthropic.models.beta.userprofiles

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A record of an entity that the platform serves through the API, such as an end-user of the
 * platform's product or a company that the platform resells Claude access to.
 *
 * A Messages, Message Batches or token counting request can send a profile's `id` in the
 * `anthropic-user-profile-id` header to attribute the request to that entity.
 */
class BetaUserProfile
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val metadata: JsonField<Metadata>,
    private val name: JsonField<String>,
    private val trustGrants: JsonField<TrustGrants>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val accessType: JsonField<AccessType>,
    private val externalId: JsonField<String>,
    private val externalUserDetails: JsonField<BetaUserProfileExternalUserDetails>,
    private val externalUserOnboardedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("metadata") @ExcludeMissing metadata: JsonField<Metadata> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("trust_grants")
        @ExcludeMissing
        trustGrants: JsonField<TrustGrants> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("access_type")
        @ExcludeMissing
        accessType: JsonField<AccessType> = JsonMissing.of(),
        @JsonProperty("external_id")
        @ExcludeMissing
        externalId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("external_user_details")
        @ExcludeMissing
        externalUserDetails: JsonField<BetaUserProfileExternalUserDetails> = JsonMissing.of(),
        @JsonProperty("external_user_onboarded_at")
        @ExcludeMissing
        externalUserOnboardedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(
        id,
        createdAt,
        metadata,
        name,
        trustGrants,
        type,
        updatedAt,
        accessType,
        externalId,
        externalUserDetails,
        externalUserOnboardedAt,
        mutableMapOf(),
    )

    /**
     * Unique identifier for this user profile, prefixed `uprof_`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * When this user profile was created, in RFC 3339 format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Arbitrary key-value metadata. Maximum 16 pairs, keys up to 64 chars, values up to 512 chars.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun metadata(): Metadata = metadata.getRequired("metadata")

    /**
     * Real-world name of the entity this profile represents (company or individual). For a company
     * the platform resells Claude access to (`access_type` `passthrough`) this is that company's
     * name. `null` until the platform supplies one.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun name(): Optional<String> = name.getOptional("name")

    /**
     * Trust grants for this profile, keyed by grant name. Key omitted when no grant is active or in
     * flight.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun trustGrants(): TrustGrants = trustGrants.getRequired("trust_grants")

    /**
     * Object type. Always `user_profile`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("user_profile")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * When this user profile was last modified, in RFC 3339 format. It normally also updates
     * shortly after Anthropic gives the profile an approval or withdraws one. Other changes to the
     * statuses that Anthropic reports do not always update it, so read the status itself rather
     * than watching this timestamp.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * How the platform uses the API for this entity: `application` (default) or `passthrough`.
     * Absent under the legacy `user-profiles-2026-03-24` beta header.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun accessType(): Optional<AccessType> = accessType.getOptional("access_type")

    /**
     * Platform's own identifier for this user. Not enforced unique. Legacy field, present only
     * under the `user-profiles-2026-03-24` and `user-profiles-2026-08-18` beta headers; without one
     * of them, the value is `external_user_details.reference_id`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun externalId(): Optional<String> = externalId.getOptional("external_id")

    /**
     * Details about the entity this profile represents, as the platform states them; not verified
     * by Anthropic. Every field is present, `null` until the platform supplies a value. Absent
     * under the legacy `user-profiles-2026-03-24` and `user-profiles-2026-08-18` beta headers,
     * which serve `reference_id` as the top-level `external_id` and, under
     * `user-profiles-2026-08-18`, `onboarded_at` as `external_user_onboarded_at`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun externalUserDetails(): Optional<BetaUserProfileExternalUserDetails> =
        externalUserDetails.getOptional("external_user_details")

    /**
     * When the entity this profile represents opened its account with the platform, as stated by
     * the platform, in RFC 3339 format (UTC). `null` until the platform supplies one. Legacy field,
     * present only under the `user-profiles-2026-08-18` beta header; without a legacy header, the
     * value is `external_user_details.onboarded_at`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun externalUserOnboardedAt(): Optional<OffsetDateTime> =
        externalUserOnboardedAt.getOptional("external_user_onboarded_at")

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
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [trustGrants].
     *
     * Unlike [trustGrants], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("trust_grants")
    @ExcludeMissing
    fun _trustGrants(): JsonField<TrustGrants> = trustGrants

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [accessType].
     *
     * Unlike [accessType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("access_type")
    @ExcludeMissing
    fun _accessType(): JsonField<AccessType> = accessType

    /**
     * Returns the raw JSON value of [externalId].
     *
     * Unlike [externalId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("external_id") @ExcludeMissing fun _externalId(): JsonField<String> = externalId

    /**
     * Returns the raw JSON value of [externalUserDetails].
     *
     * Unlike [externalUserDetails], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("external_user_details")
    @ExcludeMissing
    fun _externalUserDetails(): JsonField<BetaUserProfileExternalUserDetails> = externalUserDetails

    /**
     * Returns the raw JSON value of [externalUserOnboardedAt].
     *
     * Unlike [externalUserOnboardedAt], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("external_user_onboarded_at")
    @ExcludeMissing
    fun _externalUserOnboardedAt(): JsonField<OffsetDateTime> = externalUserOnboardedAt

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
         * Returns a mutable builder for constructing an instance of [BetaUserProfile].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .metadata()
         * .name()
         * .trustGrants()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaUserProfile]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var metadata: JsonField<Metadata>? = null
        private var name: JsonField<String>? = null
        private var trustGrants: JsonField<TrustGrants>? = null
        private var type: JsonValue = JsonValue.from("user_profile")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var accessType: JsonField<AccessType> = JsonMissing.of()
        private var externalId: JsonField<String> = JsonMissing.of()
        private var externalUserDetails: JsonField<BetaUserProfileExternalUserDetails> =
            JsonMissing.of()
        private var externalUserOnboardedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaUserProfile: BetaUserProfile) = apply {
            id = betaUserProfile.id
            createdAt = betaUserProfile.createdAt
            metadata = betaUserProfile.metadata
            name = betaUserProfile.name
            trustGrants = betaUserProfile.trustGrants
            type = betaUserProfile.type
            updatedAt = betaUserProfile.updatedAt
            accessType = betaUserProfile.accessType
            externalId = betaUserProfile.externalId
            externalUserDetails = betaUserProfile.externalUserDetails
            externalUserOnboardedAt = betaUserProfile.externalUserOnboardedAt
            additionalProperties = betaUserProfile.additionalProperties.toMutableMap()
        }

        /** Unique identifier for this user profile, prefixed `uprof_`. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** When this user profile was created, in RFC 3339 format. */
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
         * Arbitrary key-value metadata. Maximum 16 pairs, keys up to 64 chars, values up to 512
         * chars.
         */
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
         * Real-world name of the entity this profile represents (company or individual). For a
         * company the platform resells Claude access to (`access_type` `passthrough`) this is that
         * company's name. `null` until the platform supplies one.
         */
        fun name(name: String?) = name(JsonField.ofNullable(name))

        /** Alias for calling [Builder.name] with `name.orElse(null)`. */
        fun name(name: Optional<String>) = name(name.getOrNull())

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /**
         * Trust grants for this profile, keyed by grant name. Key omitted when no grant is active
         * or in flight.
         */
        fun trustGrants(trustGrants: TrustGrants) = trustGrants(JsonField.of(trustGrants))

        /**
         * Sets [Builder.trustGrants] to an arbitrary JSON value.
         *
         * You should usually call [Builder.trustGrants] with a well-typed [TrustGrants] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun trustGrants(trustGrants: JsonField<TrustGrants>) = apply {
            this.trustGrants = trustGrants
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("user_profile")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * When this user profile was last modified, in RFC 3339 format. It normally also updates
         * shortly after Anthropic gives the profile an approval or withdraws one. Other changes to
         * the statuses that Anthropic reports do not always update it, so read the status itself
         * rather than watching this timestamp.
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

        /**
         * How the platform uses the API for this entity: `application` (default) or `passthrough`.
         * Absent under the legacy `user-profiles-2026-03-24` beta header.
         */
        fun accessType(accessType: AccessType) = accessType(JsonField.of(accessType))

        /**
         * Sets [Builder.accessType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.accessType] with a well-typed [AccessType] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun accessType(accessType: JsonField<AccessType>) = apply { this.accessType = accessType }

        /**
         * Platform's own identifier for this user. Not enforced unique. Legacy field, present only
         * under the `user-profiles-2026-03-24` and `user-profiles-2026-08-18` beta headers; without
         * one of them, the value is `external_user_details.reference_id`.
         */
        fun externalId(externalId: String?) = externalId(JsonField.ofNullable(externalId))

        /** Alias for calling [Builder.externalId] with `externalId.orElse(null)`. */
        fun externalId(externalId: Optional<String>) = externalId(externalId.getOrNull())

        /**
         * Sets [Builder.externalId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.externalId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun externalId(externalId: JsonField<String>) = apply { this.externalId = externalId }

        /**
         * Details about the entity this profile represents, as the platform states them; not
         * verified by Anthropic. Every field is present, `null` until the platform supplies a
         * value. Absent under the legacy `user-profiles-2026-03-24` and `user-profiles-2026-08-18`
         * beta headers, which serve `reference_id` as the top-level `external_id` and, under
         * `user-profiles-2026-08-18`, `onboarded_at` as `external_user_onboarded_at`.
         */
        fun externalUserDetails(externalUserDetails: BetaUserProfileExternalUserDetails) =
            externalUserDetails(JsonField.of(externalUserDetails))

        /**
         * Sets [Builder.externalUserDetails] to an arbitrary JSON value.
         *
         * You should usually call [Builder.externalUserDetails] with a well-typed
         * [BetaUserProfileExternalUserDetails] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun externalUserDetails(
            externalUserDetails: JsonField<BetaUserProfileExternalUserDetails>
        ) = apply { this.externalUserDetails = externalUserDetails }

        /**
         * When the entity this profile represents opened its account with the platform, as stated
         * by the platform, in RFC 3339 format (UTC). `null` until the platform supplies one. Legacy
         * field, present only under the `user-profiles-2026-08-18` beta header; without a legacy
         * header, the value is `external_user_details.onboarded_at`.
         */
        fun externalUserOnboardedAt(externalUserOnboardedAt: OffsetDateTime?) =
            externalUserOnboardedAt(JsonField.ofNullable(externalUserOnboardedAt))

        /**
         * Alias for calling [Builder.externalUserOnboardedAt] with
         * `externalUserOnboardedAt.orElse(null)`.
         */
        fun externalUserOnboardedAt(externalUserOnboardedAt: Optional<OffsetDateTime>) =
            externalUserOnboardedAt(externalUserOnboardedAt.getOrNull())

        /**
         * Sets [Builder.externalUserOnboardedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.externalUserOnboardedAt] with a well-typed
         * [OffsetDateTime] value instead. This method is primarily for setting the field to an
         * undocumented or not yet supported value.
         */
        fun externalUserOnboardedAt(externalUserOnboardedAt: JsonField<OffsetDateTime>) = apply {
            this.externalUserOnboardedAt = externalUserOnboardedAt
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
         * Returns an immutable instance of [BetaUserProfile].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .metadata()
         * .name()
         * .trustGrants()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaUserProfile =
            BetaUserProfile(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("metadata", metadata),
                checkRequired("name", name),
                checkRequired("trustGrants", trustGrants),
                type,
                checkRequired("updatedAt", updatedAt),
                accessType,
                externalId,
                externalUserDetails,
                externalUserOnboardedAt,
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
    fun validate(): BetaUserProfile = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        metadata().validate()
        name()
        trustGrants().validate()
        _type().let {
            if (it != JsonValue.from("user_profile")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
        accessType().ifPresent { it.validate() }
        externalId()
        externalUserDetails().ifPresent { it.validate() }
        externalUserOnboardedAt()
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
            (metadata.asKnown().getOrNull()?.validity() ?: 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (trustGrants.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("user_profile")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (accessType.asKnown().getOrNull()?.validity() ?: 0) +
            (if (externalId.asKnown().isPresent) 1 else 0) +
            (externalUserDetails.asKnown().getOrNull()?.validity() ?: 0) +
            (if (externalUserOnboardedAt.asKnown().isPresent) 1 else 0)

    /**
     * Arbitrary key-value metadata. Maximum 16 pairs, keys up to 64 chars, values up to 512 chars.
     */
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

    /**
     * Trust grants for this profile, keyed by grant name. Key omitted when no grant is active or in
     * flight.
     */
    class TrustGrants
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

            /** Returns a mutable builder for constructing an instance of [TrustGrants]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [TrustGrants]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(trustGrants: TrustGrants) = apply {
                additionalProperties = trustGrants.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [TrustGrants].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): TrustGrants = TrustGrants(additionalProperties.toImmutable())
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
        fun validate(): TrustGrants = apply {
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

            return other is TrustGrants && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "TrustGrants{additionalProperties=$additionalProperties}"
    }

    /**
     * How the platform uses the API for this entity: `application` (default) or `passthrough`.
     * Absent under the legacy `user-profiles-2026-03-24` beta header.
     */
    class AccessType private constructor(private val value: JsonField<String>) : Enum {

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

            /**
             * The user profile represents an individual end-user of a product that the platform
             * builds on the API. New profiles get this value by default.
             */
            @JvmField val APPLICATION = AccessType(JsonField.of("application"))

            /** The user profile represents a company that the platform resells Claude access to. */
            @JvmField val PASSTHROUGH = AccessType(JsonField.of("passthrough"))

            @JvmStatic
            fun of(value: String): AccessType =
                // Intern known values so `==` works
                when (value) {
                    "application" -> APPLICATION
                    "passthrough" -> PASSTHROUGH
                    else -> AccessType(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): AccessType =
                value.asString().getOrNull()?.let { of(it) } ?: AccessType(value)
        }

        /** An enum containing [AccessType]'s known values. */
        enum class Known {
            /**
             * The user profile represents an individual end-user of a product that the platform
             * builds on the API. New profiles get this value by default.
             */
            APPLICATION,
            /** The user profile represents a company that the platform resells Claude access to. */
            PASSTHROUGH,
        }

        /**
         * An enum containing [AccessType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [AccessType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            /**
             * The user profile represents an individual end-user of a product that the platform
             * builds on the API. New profiles get this value by default.
             */
            APPLICATION,
            /** The user profile represents a company that the platform resells Claude access to. */
            PASSTHROUGH,
            /**
             * An enum member indicating that [AccessType] was instantiated with an unknown value.
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
                APPLICATION -> Value.APPLICATION
                PASSTHROUGH -> Value.PASSTHROUGH
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
                APPLICATION -> Known.APPLICATION
                PASSTHROUGH -> Known.PASSTHROUGH
                else -> throw AnthropicInvalidDataException("Unknown AccessType: $value")
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
        fun validate(): AccessType = apply {
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

            return other is AccessType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaUserProfile &&
            id == other.id &&
            createdAt == other.createdAt &&
            metadata == other.metadata &&
            name == other.name &&
            trustGrants == other.trustGrants &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            accessType == other.accessType &&
            externalId == other.externalId &&
            externalUserDetails == other.externalUserDetails &&
            externalUserOnboardedAt == other.externalUserOnboardedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            metadata,
            name,
            trustGrants,
            type,
            updatedAt,
            accessType,
            externalId,
            externalUserDetails,
            externalUserOnboardedAt,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaUserProfile{id=$id, createdAt=$createdAt, metadata=$metadata, name=$name, trustGrants=$trustGrants, type=$type, updatedAt=$updatedAt, accessType=$accessType, externalId=$externalId, externalUserDetails=$externalUserDetails, externalUserOnboardedAt=$externalUserOnboardedAt, additionalProperties=$additionalProperties}"
}
