package com.anthropic.models.userprofiles

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
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

class UserProfileExternalUserDetailsParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val accountStatus: JsonField<UserProfileExternalUserAccountStatus>,
    private val country: JsonField<String>,
    private val emailHash: JsonField<String>,
    private val entityType: JsonField<UserProfileExternalUserEntityType>,
    private val nameHash: JsonField<String>,
    private val onboardedAt: JsonField<OffsetDateTime>,
    private val referenceId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("account_status")
        @ExcludeMissing
        accountStatus: JsonField<UserProfileExternalUserAccountStatus> = JsonMissing.of(),
        @JsonProperty("country") @ExcludeMissing country: JsonField<String> = JsonMissing.of(),
        @JsonProperty("email_hash") @ExcludeMissing emailHash: JsonField<String> = JsonMissing.of(),
        @JsonProperty("entity_type")
        @ExcludeMissing
        entityType: JsonField<UserProfileExternalUserEntityType> = JsonMissing.of(),
        @JsonProperty("name_hash") @ExcludeMissing nameHash: JsonField<String> = JsonMissing.of(),
        @JsonProperty("onboarded_at")
        @ExcludeMissing
        onboardedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("reference_id")
        @ExcludeMissing
        referenceId: JsonField<String> = JsonMissing.of(),
    ) : this(
        accountStatus,
        country,
        emailHash,
        entityType,
        nameHash,
        onboardedAt,
        referenceId,
        mutableMapOf(),
    )

    /**
     * The status of the entity's account on the platform: `active`, `suspended` or `blocked`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun accountStatus(): Optional<UserProfileExternalUserAccountStatus> =
        accountStatus.getOptional("account_status")

    /**
     * The country of the entity (not of the platform), as the platform determines it: an ISO 3166-1
     * alpha-2 code in upper case, for example `US`. Only the form, two uppercase ASCII letters, is
     * checked.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun country(): Optional<String> = country.getOptional("country")

    /**
     * A hash of the entity's email address, computed by the platform. Anthropic treats it as an
     * opaque string and does not prescribe the hash function. 1 to 255 characters.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun emailHash(): Optional<String> = emailHash.getOptional("email_hash")

    /**
     * What kind of entity the profile represents: `individual`, `business`, `non_profit` or
     * `government`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun entityType(): Optional<UserProfileExternalUserEntityType> =
        entityType.getOptional("entity_type")

    /**
     * A hash of the entity's name, computed by the platform. Anthropic treats it as an opaque
     * string and does not prescribe the hash function. 1 to 255 characters.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun nameHash(): Optional<String> = nameHash.getOptional("name_hash")

    /**
     * When the entity opened its account with the platform, in RFC 3339 format: for an
     * `application` profile, when the end-user signed up; for a `passthrough` profile, when the
     * company became the platform's customer. Must be a complete timestamp no more than 1 minute in
     * the future.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun onboardedAt(): Optional<OffsetDateTime> = onboardedAt.getOptional("onboarded_at")

    /**
     * The platform's own reference for the entity, for example the key of the end-user's row in the
     * platform's database. Not interpreted by Anthropic and not enforced unique. 1 to 255
     * characters.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun referenceId(): Optional<String> = referenceId.getOptional("reference_id")

    /**
     * Returns the raw JSON value of [accountStatus].
     *
     * Unlike [accountStatus], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("account_status")
    @ExcludeMissing
    fun _accountStatus(): JsonField<UserProfileExternalUserAccountStatus> = accountStatus

    /**
     * Returns the raw JSON value of [country].
     *
     * Unlike [country], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("country") @ExcludeMissing fun _country(): JsonField<String> = country

    /**
     * Returns the raw JSON value of [emailHash].
     *
     * Unlike [emailHash], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("email_hash") @ExcludeMissing fun _emailHash(): JsonField<String> = emailHash

    /**
     * Returns the raw JSON value of [entityType].
     *
     * Unlike [entityType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("entity_type")
    @ExcludeMissing
    fun _entityType(): JsonField<UserProfileExternalUserEntityType> = entityType

    /**
     * Returns the raw JSON value of [nameHash].
     *
     * Unlike [nameHash], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name_hash") @ExcludeMissing fun _nameHash(): JsonField<String> = nameHash

    /**
     * Returns the raw JSON value of [onboardedAt].
     *
     * Unlike [onboardedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("onboarded_at")
    @ExcludeMissing
    fun _onboardedAt(): JsonField<OffsetDateTime> = onboardedAt

    /**
     * Returns the raw JSON value of [referenceId].
     *
     * Unlike [referenceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("reference_id")
    @ExcludeMissing
    fun _referenceId(): JsonField<String> = referenceId

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
         * [UserProfileExternalUserDetailsParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserProfileExternalUserDetailsParams]. */
    class Builder internal constructor() {

        private var accountStatus: JsonField<UserProfileExternalUserAccountStatus> =
            JsonMissing.of()
        private var country: JsonField<String> = JsonMissing.of()
        private var emailHash: JsonField<String> = JsonMissing.of()
        private var entityType: JsonField<UserProfileExternalUserEntityType> = JsonMissing.of()
        private var nameHash: JsonField<String> = JsonMissing.of()
        private var onboardedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var referenceId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            userProfileExternalUserDetailsParams: UserProfileExternalUserDetailsParams
        ) = apply {
            accountStatus = userProfileExternalUserDetailsParams.accountStatus
            country = userProfileExternalUserDetailsParams.country
            emailHash = userProfileExternalUserDetailsParams.emailHash
            entityType = userProfileExternalUserDetailsParams.entityType
            nameHash = userProfileExternalUserDetailsParams.nameHash
            onboardedAt = userProfileExternalUserDetailsParams.onboardedAt
            referenceId = userProfileExternalUserDetailsParams.referenceId
            additionalProperties =
                userProfileExternalUserDetailsParams.additionalProperties.toMutableMap()
        }

        /**
         * The status of the entity's account on the platform: `active`, `suspended` or `blocked`.
         */
        fun accountStatus(accountStatus: UserProfileExternalUserAccountStatus?) =
            accountStatus(JsonField.ofNullable(accountStatus))

        /** Alias for calling [Builder.accountStatus] with `accountStatus.orElse(null)`. */
        fun accountStatus(accountStatus: Optional<UserProfileExternalUserAccountStatus>) =
            accountStatus(accountStatus.getOrNull())

        /**
         * Sets [Builder.accountStatus] to an arbitrary JSON value.
         *
         * You should usually call [Builder.accountStatus] with a well-typed
         * [UserProfileExternalUserAccountStatus] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun accountStatus(accountStatus: JsonField<UserProfileExternalUserAccountStatus>) = apply {
            this.accountStatus = accountStatus
        }

        /**
         * The country of the entity (not of the platform), as the platform determines it: an ISO
         * 3166-1 alpha-2 code in upper case, for example `US`. Only the form, two uppercase ASCII
         * letters, is checked.
         */
        fun country(country: String?) = country(JsonField.ofNullable(country))

        /** Alias for calling [Builder.country] with `country.orElse(null)`. */
        fun country(country: Optional<String>) = country(country.getOrNull())

        /**
         * Sets [Builder.country] to an arbitrary JSON value.
         *
         * You should usually call [Builder.country] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun country(country: JsonField<String>) = apply { this.country = country }

        /**
         * A hash of the entity's email address, computed by the platform. Anthropic treats it as an
         * opaque string and does not prescribe the hash function. 1 to 255 characters.
         */
        fun emailHash(emailHash: String?) = emailHash(JsonField.ofNullable(emailHash))

        /** Alias for calling [Builder.emailHash] with `emailHash.orElse(null)`. */
        fun emailHash(emailHash: Optional<String>) = emailHash(emailHash.getOrNull())

        /**
         * Sets [Builder.emailHash] to an arbitrary JSON value.
         *
         * You should usually call [Builder.emailHash] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun emailHash(emailHash: JsonField<String>) = apply { this.emailHash = emailHash }

        /**
         * What kind of entity the profile represents: `individual`, `business`, `non_profit` or
         * `government`.
         */
        fun entityType(entityType: UserProfileExternalUserEntityType?) =
            entityType(JsonField.ofNullable(entityType))

        /** Alias for calling [Builder.entityType] with `entityType.orElse(null)`. */
        fun entityType(entityType: Optional<UserProfileExternalUserEntityType>) =
            entityType(entityType.getOrNull())

        /**
         * Sets [Builder.entityType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.entityType] with a well-typed
         * [UserProfileExternalUserEntityType] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun entityType(entityType: JsonField<UserProfileExternalUserEntityType>) = apply {
            this.entityType = entityType
        }

        /**
         * A hash of the entity's name, computed by the platform. Anthropic treats it as an opaque
         * string and does not prescribe the hash function. 1 to 255 characters.
         */
        fun nameHash(nameHash: String?) = nameHash(JsonField.ofNullable(nameHash))

        /** Alias for calling [Builder.nameHash] with `nameHash.orElse(null)`. */
        fun nameHash(nameHash: Optional<String>) = nameHash(nameHash.getOrNull())

        /**
         * Sets [Builder.nameHash] to an arbitrary JSON value.
         *
         * You should usually call [Builder.nameHash] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun nameHash(nameHash: JsonField<String>) = apply { this.nameHash = nameHash }

        /**
         * When the entity opened its account with the platform, in RFC 3339 format: for an
         * `application` profile, when the end-user signed up; for a `passthrough` profile, when the
         * company became the platform's customer. Must be a complete timestamp no more than 1
         * minute in the future.
         */
        fun onboardedAt(onboardedAt: OffsetDateTime) = onboardedAt(JsonField.of(onboardedAt))

        /**
         * Sets [Builder.onboardedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.onboardedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun onboardedAt(onboardedAt: JsonField<OffsetDateTime>) = apply {
            this.onboardedAt = onboardedAt
        }

        /**
         * The platform's own reference for the entity, for example the key of the end-user's row in
         * the platform's database. Not interpreted by Anthropic and not enforced unique. 1 to 255
         * characters.
         */
        fun referenceId(referenceId: String?) = referenceId(JsonField.ofNullable(referenceId))

        /** Alias for calling [Builder.referenceId] with `referenceId.orElse(null)`. */
        fun referenceId(referenceId: Optional<String>) = referenceId(referenceId.getOrNull())

        /**
         * Sets [Builder.referenceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.referenceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun referenceId(referenceId: JsonField<String>) = apply { this.referenceId = referenceId }

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
         * Returns an immutable instance of [UserProfileExternalUserDetailsParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): UserProfileExternalUserDetailsParams =
            UserProfileExternalUserDetailsParams(
                accountStatus,
                country,
                emailHash,
                entityType,
                nameHash,
                onboardedAt,
                referenceId,
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
    fun validate(): UserProfileExternalUserDetailsParams = apply {
        if (validated) {
            return@apply
        }

        accountStatus().ifPresent { it.validate() }
        country()
        emailHash()
        entityType().ifPresent { it.validate() }
        nameHash()
        onboardedAt()
        referenceId()
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
        (accountStatus.asKnown().getOrNull()?.validity() ?: 0) +
            (if (country.asKnown().isPresent) 1 else 0) +
            (if (emailHash.asKnown().isPresent) 1 else 0) +
            (entityType.asKnown().getOrNull()?.validity() ?: 0) +
            (if (nameHash.asKnown().isPresent) 1 else 0) +
            (if (onboardedAt.asKnown().isPresent) 1 else 0) +
            (if (referenceId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfileExternalUserDetailsParams &&
            accountStatus == other.accountStatus &&
            country == other.country &&
            emailHash == other.emailHash &&
            entityType == other.entityType &&
            nameHash == other.nameHash &&
            onboardedAt == other.onboardedAt &&
            referenceId == other.referenceId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            accountStatus,
            country,
            emailHash,
            entityType,
            nameHash,
            onboardedAt,
            referenceId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UserProfileExternalUserDetailsParams{accountStatus=$accountStatus, country=$country, emailHash=$emailHash, entityType=$entityType, nameHash=$nameHash, onboardedAt=$onboardedAt, referenceId=$referenceId, additionalProperties=$additionalProperties}"
}
