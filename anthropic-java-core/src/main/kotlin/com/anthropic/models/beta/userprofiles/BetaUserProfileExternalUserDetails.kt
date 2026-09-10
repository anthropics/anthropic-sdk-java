package com.anthropic.models.beta.userprofiles

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

/**
 * Details about the entity this profile represents, as the platform states them. Anthropic does not
 * verify them. Every field is present, `null` until the platform supplies a value.
 */
class BetaUserProfileExternalUserDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val accountStatus: JsonField<AccountStatus>,
    private val country: JsonField<String>,
    private val emailHash: JsonField<String>,
    private val entityType: JsonField<EntityType>,
    private val nameHash: JsonField<String>,
    private val onboardedAt: JsonField<OffsetDateTime>,
    private val referenceId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("account_status")
        @ExcludeMissing
        accountStatus: JsonField<AccountStatus> = JsonMissing.of(),
        @JsonProperty("country") @ExcludeMissing country: JsonField<String> = JsonMissing.of(),
        @JsonProperty("email_hash") @ExcludeMissing emailHash: JsonField<String> = JsonMissing.of(),
        @JsonProperty("entity_type")
        @ExcludeMissing
        entityType: JsonField<EntityType> = JsonMissing.of(),
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
     * The status of the entity's account on the platform, as the platform states it: `active`;
     * `suspended`, when the platform has restricted the account and may restore it; or `blocked`,
     * when the platform has barred it. It records the platform's decision only; the statuses in
     * `trust_grants` are Anthropic's and do not follow it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun accountStatus(): Optional<AccountStatus> = accountStatus.getOptional("account_status")

    /**
     * The country the platform associates with the entity, as an ISO 3166-1 alpha-2 code. `null`
     * until the platform supplies one.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun country(): Optional<String> = country.getOptional("country")

    /**
     * The platform-computed hash of the entity's email address. `null` until the platform supplies
     * one.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun emailHash(): Optional<String> = emailHash.getOptional("email_hash")

    /**
     * What kind of entity the profile represents, as the platform states it: `individual`,
     * `business`, `non_profit` or `government`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun entityType(): Optional<EntityType> = entityType.getOptional("entity_type")

    /**
     * The platform-computed hash of the entity's name. `null` until the platform supplies one.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun nameHash(): Optional<String> = nameHash.getOptional("name_hash")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun onboardedAt(): Optional<OffsetDateTime> = onboardedAt.getOptional("onboarded_at")

    /**
     * The platform's own reference for the entity. `null` until the platform supplies one.
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
    fun _accountStatus(): JsonField<AccountStatus> = accountStatus

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
    fun _entityType(): JsonField<EntityType> = entityType

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
         * [BetaUserProfileExternalUserDetails].
         *
         * The following fields are required:
         * ```java
         * .accountStatus()
         * .country()
         * .emailHash()
         * .entityType()
         * .nameHash()
         * .onboardedAt()
         * .referenceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaUserProfileExternalUserDetails]. */
    class Builder internal constructor() {

        private var accountStatus: JsonField<AccountStatus>? = null
        private var country: JsonField<String>? = null
        private var emailHash: JsonField<String>? = null
        private var entityType: JsonField<EntityType>? = null
        private var nameHash: JsonField<String>? = null
        private var onboardedAt: JsonField<OffsetDateTime>? = null
        private var referenceId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaUserProfileExternalUserDetails: BetaUserProfileExternalUserDetails) =
            apply {
                accountStatus = betaUserProfileExternalUserDetails.accountStatus
                country = betaUserProfileExternalUserDetails.country
                emailHash = betaUserProfileExternalUserDetails.emailHash
                entityType = betaUserProfileExternalUserDetails.entityType
                nameHash = betaUserProfileExternalUserDetails.nameHash
                onboardedAt = betaUserProfileExternalUserDetails.onboardedAt
                referenceId = betaUserProfileExternalUserDetails.referenceId
                additionalProperties =
                    betaUserProfileExternalUserDetails.additionalProperties.toMutableMap()
            }

        /**
         * The status of the entity's account on the platform, as the platform states it: `active`;
         * `suspended`, when the platform has restricted the account and may restore it; or
         * `blocked`, when the platform has barred it. It records the platform's decision only; the
         * statuses in `trust_grants` are Anthropic's and do not follow it.
         */
        fun accountStatus(accountStatus: AccountStatus?) =
            accountStatus(JsonField.ofNullable(accountStatus))

        /** Alias for calling [Builder.accountStatus] with `accountStatus.orElse(null)`. */
        fun accountStatus(accountStatus: Optional<AccountStatus>) =
            accountStatus(accountStatus.getOrNull())

        /**
         * Sets [Builder.accountStatus] to an arbitrary JSON value.
         *
         * You should usually call [Builder.accountStatus] with a well-typed [AccountStatus] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun accountStatus(accountStatus: JsonField<AccountStatus>) = apply {
            this.accountStatus = accountStatus
        }

        /**
         * The country the platform associates with the entity, as an ISO 3166-1 alpha-2 code.
         * `null` until the platform supplies one.
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
         * The platform-computed hash of the entity's email address. `null` until the platform
         * supplies one.
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
         * What kind of entity the profile represents, as the platform states it: `individual`,
         * `business`, `non_profit` or `government`.
         */
        fun entityType(entityType: EntityType?) = entityType(JsonField.ofNullable(entityType))

        /** Alias for calling [Builder.entityType] with `entityType.orElse(null)`. */
        fun entityType(entityType: Optional<EntityType>) = entityType(entityType.getOrNull())

        /**
         * Sets [Builder.entityType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.entityType] with a well-typed [EntityType] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun entityType(entityType: JsonField<EntityType>) = apply { this.entityType = entityType }

        /**
         * The platform-computed hash of the entity's name. `null` until the platform supplies one.
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

        /** A timestamp in RFC 3339 format */
        fun onboardedAt(onboardedAt: OffsetDateTime?) =
            onboardedAt(JsonField.ofNullable(onboardedAt))

        /** Alias for calling [Builder.onboardedAt] with `onboardedAt.orElse(null)`. */
        fun onboardedAt(onboardedAt: Optional<OffsetDateTime>) =
            onboardedAt(onboardedAt.getOrNull())

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

        /** The platform's own reference for the entity. `null` until the platform supplies one. */
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
         * Returns an immutable instance of [BetaUserProfileExternalUserDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .accountStatus()
         * .country()
         * .emailHash()
         * .entityType()
         * .nameHash()
         * .onboardedAt()
         * .referenceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaUserProfileExternalUserDetails =
            BetaUserProfileExternalUserDetails(
                checkRequired("accountStatus", accountStatus),
                checkRequired("country", country),
                checkRequired("emailHash", emailHash),
                checkRequired("entityType", entityType),
                checkRequired("nameHash", nameHash),
                checkRequired("onboardedAt", onboardedAt),
                checkRequired("referenceId", referenceId),
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
    fun validate(): BetaUserProfileExternalUserDetails = apply {
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

    /**
     * The status of the entity's account on the platform, as the platform states it: `active`;
     * `suspended`, when the platform has restricted the account and may restore it; or `blocked`,
     * when the platform has barred it. It records the platform's decision only; the statuses in
     * `trust_grants` are Anthropic's and do not follow it.
     */
    class AccountStatus @JsonCreator private constructor(private val value: JsonField<String>) :
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

            @JvmField val ACTIVE = of("active")

            @JvmField val SUSPENDED = of("suspended")

            @JvmField val BLOCKED = of("blocked")

            @JvmStatic fun of(value: String) = AccountStatus(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): AccountStatus =
                value.asString().getOrNull()?.let { of(it) } ?: AccountStatus(value)
        }

        /** An enum containing [AccountStatus]'s known values. */
        enum class Known {
            ACTIVE,
            SUSPENDED,
            BLOCKED,
        }

        /**
         * An enum containing [AccountStatus]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [AccountStatus] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ACTIVE,
            SUSPENDED,
            BLOCKED,
            /**
             * An enum member indicating that [AccountStatus] was instantiated with an unknown
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
                ACTIVE -> Value.ACTIVE
                SUSPENDED -> Value.SUSPENDED
                BLOCKED -> Value.BLOCKED
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
                ACTIVE -> Known.ACTIVE
                SUSPENDED -> Known.SUSPENDED
                BLOCKED -> Known.BLOCKED
                else -> throw AnthropicInvalidDataException("Unknown AccountStatus: $value")
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
        fun validate(): AccountStatus = apply {
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

            return other is AccountStatus && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /**
     * What kind of entity the profile represents, as the platform states it: `individual`,
     * `business`, `non_profit` or `government`.
     */
    class EntityType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val INDIVIDUAL = of("individual")

            @JvmField val BUSINESS = of("business")

            @JvmField val NON_PROFIT = of("non_profit")

            @JvmField val GOVERNMENT = of("government")

            @JvmStatic fun of(value: String) = EntityType(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): EntityType =
                value.asString().getOrNull()?.let { of(it) } ?: EntityType(value)
        }

        /** An enum containing [EntityType]'s known values. */
        enum class Known {
            INDIVIDUAL,
            BUSINESS,
            NON_PROFIT,
            GOVERNMENT,
        }

        /**
         * An enum containing [EntityType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [EntityType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            INDIVIDUAL,
            BUSINESS,
            NON_PROFIT,
            GOVERNMENT,
            /**
             * An enum member indicating that [EntityType] was instantiated with an unknown value.
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
                INDIVIDUAL -> Value.INDIVIDUAL
                BUSINESS -> Value.BUSINESS
                NON_PROFIT -> Value.NON_PROFIT
                GOVERNMENT -> Value.GOVERNMENT
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
                INDIVIDUAL -> Known.INDIVIDUAL
                BUSINESS -> Known.BUSINESS
                NON_PROFIT -> Known.NON_PROFIT
                GOVERNMENT -> Known.GOVERNMENT
                else -> throw AnthropicInvalidDataException("Unknown EntityType: $value")
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
        fun validate(): EntityType = apply {
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

            return other is EntityType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaUserProfileExternalUserDetails &&
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
        "BetaUserProfileExternalUserDetails{accountStatus=$accountStatus, country=$country, emailHash=$emailHash, entityType=$entityType, nameHash=$nameHash, onboardedAt=$onboardedAt, referenceId=$referenceId, additionalProperties=$additionalProperties}"
}
