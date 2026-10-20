package com.anthropic.models.userprofiles

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
class UserProfile
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val accessType: JsonField<UserProfileAccessType>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val externalUserDetails: JsonField<UserProfileExternalUserDetails>,
    private val metadata: JsonField<Metadata>,
    private val name: JsonField<String>,
    private val programs: JsonField<UserProfilePrograms>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("access_type")
        @ExcludeMissing
        accessType: JsonField<UserProfileAccessType> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("external_user_details")
        @ExcludeMissing
        externalUserDetails: JsonField<UserProfileExternalUserDetails> = JsonMissing.of(),
        @JsonProperty("metadata") @ExcludeMissing metadata: JsonField<Metadata> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("programs")
        @ExcludeMissing
        programs: JsonField<UserProfilePrograms> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(
        id,
        accessType,
        createdAt,
        externalUserDetails,
        metadata,
        name,
        programs,
        type,
        updatedAt,
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
     * How the platform uses the API for this entity: `application` (default) or `passthrough`.
     * Absent under the legacy `user-profiles-2026-03-24` beta header.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun accessType(): UserProfileAccessType = accessType.getRequired("access_type")

    /**
     * When this user profile was created, in RFC 3339 format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Details about the entity this profile represents, as the platform states them; not verified
     * by Anthropic. Every field is present, `null` until the platform supplies a value. Absent
     * under the legacy `user-profiles-2026-03-24` and `user-profiles-2026-08-18` beta headers,
     * which serve `reference_id` as the top-level `external_id` and, under
     * `user-profiles-2026-08-18`, `onboarded_at` as `external_user_onboarded_at`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun externalUserDetails(): UserProfileExternalUserDetails =
        externalUserDetails.getRequired("external_user_details")

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
     * The profile's standing in each Anthropic program, such as the Cyber Verification Program.
     * Present when the request sends no user-profiles beta header; absent under the
     * `user-profiles-2026-09-04` and earlier beta headers, which serve `trust_grants` instead.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun programs(): UserProfilePrograms = programs.getRequired("programs")

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
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [accessType].
     *
     * Unlike [accessType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("access_type")
    @ExcludeMissing
    fun _accessType(): JsonField<UserProfileAccessType> = accessType

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [externalUserDetails].
     *
     * Unlike [externalUserDetails], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("external_user_details")
    @ExcludeMissing
    fun _externalUserDetails(): JsonField<UserProfileExternalUserDetails> = externalUserDetails

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
     * Returns the raw JSON value of [programs].
     *
     * Unlike [programs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("programs")
    @ExcludeMissing
    fun _programs(): JsonField<UserProfilePrograms> = programs

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

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
         * Returns a mutable builder for constructing an instance of [UserProfile].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .accessType()
         * .createdAt()
         * .externalUserDetails()
         * .metadata()
         * .name()
         * .programs()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserProfile]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var accessType: JsonField<UserProfileAccessType>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var externalUserDetails: JsonField<UserProfileExternalUserDetails>? = null
        private var metadata: JsonField<Metadata>? = null
        private var name: JsonField<String>? = null
        private var programs: JsonField<UserProfilePrograms>? = null
        private var type: JsonValue = JsonValue.from("user_profile")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(userProfile: UserProfile) = apply {
            id = userProfile.id
            accessType = userProfile.accessType
            createdAt = userProfile.createdAt
            externalUserDetails = userProfile.externalUserDetails
            metadata = userProfile.metadata
            name = userProfile.name
            programs = userProfile.programs
            type = userProfile.type
            updatedAt = userProfile.updatedAt
            additionalProperties = userProfile.additionalProperties.toMutableMap()
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

        /**
         * How the platform uses the API for this entity: `application` (default) or `passthrough`.
         * Absent under the legacy `user-profiles-2026-03-24` beta header.
         */
        fun accessType(accessType: UserProfileAccessType) = accessType(JsonField.of(accessType))

        /**
         * Sets [Builder.accessType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.accessType] with a well-typed [UserProfileAccessType]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun accessType(accessType: JsonField<UserProfileAccessType>) = apply {
            this.accessType = accessType
        }

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
         * Details about the entity this profile represents, as the platform states them; not
         * verified by Anthropic. Every field is present, `null` until the platform supplies a
         * value. Absent under the legacy `user-profiles-2026-03-24` and `user-profiles-2026-08-18`
         * beta headers, which serve `reference_id` as the top-level `external_id` and, under
         * `user-profiles-2026-08-18`, `onboarded_at` as `external_user_onboarded_at`.
         */
        fun externalUserDetails(externalUserDetails: UserProfileExternalUserDetails) =
            externalUserDetails(JsonField.of(externalUserDetails))

        /**
         * Sets [Builder.externalUserDetails] to an arbitrary JSON value.
         *
         * You should usually call [Builder.externalUserDetails] with a well-typed
         * [UserProfileExternalUserDetails] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun externalUserDetails(externalUserDetails: JsonField<UserProfileExternalUserDetails>) =
            apply {
                this.externalUserDetails = externalUserDetails
            }

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
         * The profile's standing in each Anthropic program, such as the Cyber Verification Program.
         * Present when the request sends no user-profiles beta header; absent under the
         * `user-profiles-2026-09-04` and earlier beta headers, which serve `trust_grants` instead.
         */
        fun programs(programs: UserProfilePrograms) = programs(JsonField.of(programs))

        /**
         * Sets [Builder.programs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.programs] with a well-typed [UserProfilePrograms] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun programs(programs: JsonField<UserProfilePrograms>) = apply { this.programs = programs }

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
         * Returns an immutable instance of [UserProfile].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .accessType()
         * .createdAt()
         * .externalUserDetails()
         * .metadata()
         * .name()
         * .programs()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UserProfile =
            UserProfile(
                checkRequired("id", id),
                checkRequired("accessType", accessType),
                checkRequired("createdAt", createdAt),
                checkRequired("externalUserDetails", externalUserDetails),
                checkRequired("metadata", metadata),
                checkRequired("name", name),
                checkRequired("programs", programs),
                type,
                checkRequired("updatedAt", updatedAt),
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
    fun validate(): UserProfile = apply {
        if (validated) {
            return@apply
        }

        id()
        accessType().validate()
        createdAt()
        externalUserDetails().validate()
        metadata().validate()
        name()
        programs().validate()
        _type().let {
            if (it != JsonValue.from("user_profile")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
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
            (accessType.asKnown().getOrNull()?.validity() ?: 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (externalUserDetails.asKnown().getOrNull()?.validity() ?: 0) +
            (metadata.asKnown().getOrNull()?.validity() ?: 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (programs.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("user_profile")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0)

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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfile &&
            id == other.id &&
            accessType == other.accessType &&
            createdAt == other.createdAt &&
            externalUserDetails == other.externalUserDetails &&
            metadata == other.metadata &&
            name == other.name &&
            programs == other.programs &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            accessType,
            createdAt,
            externalUserDetails,
            metadata,
            name,
            programs,
            type,
            updatedAt,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UserProfile{id=$id, accessType=$accessType, createdAt=$createdAt, externalUserDetails=$externalUserDetails, metadata=$metadata, name=$name, programs=$programs, type=$type, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
