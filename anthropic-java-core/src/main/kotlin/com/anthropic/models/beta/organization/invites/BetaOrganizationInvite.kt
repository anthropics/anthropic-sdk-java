// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.invites

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.organization.BetaOrganizationRole
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaOrganizationInvite
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val acceptedAt: JsonField<OffsetDateTime>,
    private val email: JsonField<String>,
    private val expiresAt: JsonField<OffsetDateTime>,
    private val invitedAt: JsonField<OffsetDateTime>,
    private val rbacGroupIds: JsonField<List<String>>,
    private val role: JsonField<BetaOrganizationRole>,
    private val status: JsonField<Status>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("accepted_at")
        @ExcludeMissing
        acceptedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("email") @ExcludeMissing email: JsonField<String> = JsonMissing.of(),
        @JsonProperty("expires_at")
        @ExcludeMissing
        expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("invited_at")
        @ExcludeMissing
        invitedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("rbac_group_ids")
        @ExcludeMissing
        rbacGroupIds: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("role")
        @ExcludeMissing
        role: JsonField<BetaOrganizationRole> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        id,
        acceptedAt,
        email,
        expiresAt,
        invitedAt,
        rbacGroupIds,
        role,
        status,
        type,
        mutableMapOf(),
    )

    /**
     * ID of the Invite.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * RFC 3339 datetime string indicating when the Invite was accepted, or null.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun acceptedAt(): Optional<OffsetDateTime> = acceptedAt.getOptional("accepted_at")

    /**
     * Email of the User being invited.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun email(): String = email.getRequired("email")

    /**
     * RFC 3339 datetime string indicating when the Invite expires.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun expiresAt(): OffsetDateTime = expiresAt.getRequired("expires_at")

    /**
     * RFC 3339 datetime string indicating when the Invite was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun invitedAt(): OffsetDateTime = invitedAt.getRequired("invited_at")

    /**
     * RBAC group IDs recorded on the Invite (Claude Enterprise organizations), to be assigned to
     * the User when the Invite is accepted. `[]` when none.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun rbacGroupIds(): List<String> = rbacGroupIds.getRequired("rbac_group_ids")

    /**
     * Organization role of the User.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun role(): BetaOrganizationRole = role.getRequired("role")

    /**
     * Status of the Invite.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): Status = status.getRequired("status")

    /**
     * Object type.
     *
     * For Invites, this is always `"invite"`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("invite")
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
     * Returns the raw JSON value of [acceptedAt].
     *
     * Unlike [acceptedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("accepted_at")
    @ExcludeMissing
    fun _acceptedAt(): JsonField<OffsetDateTime> = acceptedAt

    /**
     * Returns the raw JSON value of [email].
     *
     * Unlike [email], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("email") @ExcludeMissing fun _email(): JsonField<String> = email

    /**
     * Returns the raw JSON value of [expiresAt].
     *
     * Unlike [expiresAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expires_at")
    @ExcludeMissing
    fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

    /**
     * Returns the raw JSON value of [invitedAt].
     *
     * Unlike [invitedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("invited_at")
    @ExcludeMissing
    fun _invitedAt(): JsonField<OffsetDateTime> = invitedAt

    /**
     * Returns the raw JSON value of [rbacGroupIds].
     *
     * Unlike [rbacGroupIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rbac_group_ids")
    @ExcludeMissing
    fun _rbacGroupIds(): JsonField<List<String>> = rbacGroupIds

    /**
     * Returns the raw JSON value of [role].
     *
     * Unlike [role], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("role") @ExcludeMissing fun _role(): JsonField<BetaOrganizationRole> = role

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

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
         * Returns a mutable builder for constructing an instance of [BetaOrganizationInvite].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .acceptedAt()
         * .email()
         * .expiresAt()
         * .invitedAt()
         * .rbacGroupIds()
         * .role()
         * .status()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaOrganizationInvite]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var acceptedAt: JsonField<OffsetDateTime>? = null
        private var email: JsonField<String>? = null
        private var expiresAt: JsonField<OffsetDateTime>? = null
        private var invitedAt: JsonField<OffsetDateTime>? = null
        private var rbacGroupIds: JsonField<MutableList<String>>? = null
        private var role: JsonField<BetaOrganizationRole>? = null
        private var status: JsonField<Status>? = null
        private var type: JsonValue = JsonValue.from("invite")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaOrganizationInvite: BetaOrganizationInvite) = apply {
            id = betaOrganizationInvite.id
            acceptedAt = betaOrganizationInvite.acceptedAt
            email = betaOrganizationInvite.email
            expiresAt = betaOrganizationInvite.expiresAt
            invitedAt = betaOrganizationInvite.invitedAt
            rbacGroupIds =
                betaOrganizationInvite.rbacGroupIds
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            role = betaOrganizationInvite.role
            status = betaOrganizationInvite.status
            type = betaOrganizationInvite.type
            additionalProperties = betaOrganizationInvite.additionalProperties.toMutableMap()
        }

        /** ID of the Invite. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** RFC 3339 datetime string indicating when the Invite was accepted, or null. */
        fun acceptedAt(acceptedAt: OffsetDateTime?) = acceptedAt(JsonField.ofNullable(acceptedAt))

        /** Alias for calling [Builder.acceptedAt] with `acceptedAt.orElse(null)`. */
        fun acceptedAt(acceptedAt: Optional<OffsetDateTime>) = acceptedAt(acceptedAt.getOrNull())

        /**
         * Sets [Builder.acceptedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.acceptedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun acceptedAt(acceptedAt: JsonField<OffsetDateTime>) = apply {
            this.acceptedAt = acceptedAt
        }

        /** Email of the User being invited. */
        fun email(email: String) = email(JsonField.of(email))

        /**
         * Sets [Builder.email] to an arbitrary JSON value.
         *
         * You should usually call [Builder.email] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun email(email: JsonField<String>) = apply { this.email = email }

        /** RFC 3339 datetime string indicating when the Invite expires. */
        fun expiresAt(expiresAt: OffsetDateTime) = expiresAt(JsonField.of(expiresAt))

        /**
         * Sets [Builder.expiresAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.expiresAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply { this.expiresAt = expiresAt }

        /** RFC 3339 datetime string indicating when the Invite was created. */
        fun invitedAt(invitedAt: OffsetDateTime) = invitedAt(JsonField.of(invitedAt))

        /**
         * Sets [Builder.invitedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.invitedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun invitedAt(invitedAt: JsonField<OffsetDateTime>) = apply { this.invitedAt = invitedAt }

        /**
         * RBAC group IDs recorded on the Invite (Claude Enterprise organizations), to be assigned
         * to the User when the Invite is accepted. `[]` when none.
         */
        fun rbacGroupIds(rbacGroupIds: List<String>) = rbacGroupIds(JsonField.of(rbacGroupIds))

        /**
         * Sets [Builder.rbacGroupIds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupIds] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun rbacGroupIds(rbacGroupIds: JsonField<List<String>>) = apply {
            this.rbacGroupIds = rbacGroupIds.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [rbacGroupIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRbacGroupId(rbacGroupId: String) = apply {
            rbacGroupIds =
                (rbacGroupIds ?: JsonField.of(mutableListOf())).also {
                    checkKnown("rbacGroupIds", it).add(rbacGroupId)
                }
        }

        /** Organization role of the User. */
        fun role(role: BetaOrganizationRole) = role(JsonField.of(role))

        /**
         * Sets [Builder.role] to an arbitrary JSON value.
         *
         * You should usually call [Builder.role] with a well-typed [BetaOrganizationRole] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun role(role: JsonField<BetaOrganizationRole>) = apply { this.role = role }

        /** Status of the Invite. */
        fun status(status: Status) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [Status] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<Status>) = apply { this.status = status }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("invite")
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
         * Returns an immutable instance of [BetaOrganizationInvite].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .acceptedAt()
         * .email()
         * .expiresAt()
         * .invitedAt()
         * .rbacGroupIds()
         * .role()
         * .status()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaOrganizationInvite =
            BetaOrganizationInvite(
                checkRequired("id", id),
                checkRequired("acceptedAt", acceptedAt),
                checkRequired("email", email),
                checkRequired("expiresAt", expiresAt),
                checkRequired("invitedAt", invitedAt),
                checkRequired("rbacGroupIds", rbacGroupIds).map { it.toImmutable() },
                checkRequired("role", role),
                checkRequired("status", status),
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
    fun validate(): BetaOrganizationInvite = apply {
        if (validated) {
            return@apply
        }

        id()
        acceptedAt()
        email()
        expiresAt()
        invitedAt()
        rbacGroupIds()
        role().validate()
        status().validate()
        _type().let {
            if (it != JsonValue.from("invite")) {
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
            (if (acceptedAt.asKnown().isPresent) 1 else 0) +
            (if (email.asKnown().isPresent) 1 else 0) +
            (if (expiresAt.asKnown().isPresent) 1 else 0) +
            (if (invitedAt.asKnown().isPresent) 1 else 0) +
            (rbacGroupIds.asKnown().getOrNull()?.size ?: 0) +
            (role.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("invite")) 1 else 0 }

    /** Status of the Invite. */
    class Status @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val ACCEPTED = of("accepted")

            @JvmField val DELETED = of("deleted")

            @JvmField val EXPIRED = of("expired")

            @JvmField val PENDING = of("pending")

            @JvmStatic fun of(value: String) = Status(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Status =
                value.asString().getOrNull()?.let { of(it) } ?: Status(value)
        }

        /** An enum containing [Status]'s known values. */
        enum class Known {
            ACCEPTED,
            DELETED,
            EXPIRED,
            PENDING,
        }

        /**
         * An enum containing [Status]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Status] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ACCEPTED,
            DELETED,
            EXPIRED,
            PENDING,
            /** An enum member indicating that [Status] was instantiated with an unknown value. */
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
                ACCEPTED -> Value.ACCEPTED
                DELETED -> Value.DELETED
                EXPIRED -> Value.EXPIRED
                PENDING -> Value.PENDING
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
                ACCEPTED -> Known.ACCEPTED
                DELETED -> Known.DELETED
                EXPIRED -> Known.EXPIRED
                PENDING -> Known.PENDING
                else -> throw AnthropicInvalidDataException("Unknown Status: $value")
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
        fun validate(): Status = apply {
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

            return other is Status && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaOrganizationInvite &&
            id == other.id &&
            acceptedAt == other.acceptedAt &&
            email == other.email &&
            expiresAt == other.expiresAt &&
            invitedAt == other.invitedAt &&
            rbacGroupIds == other.rbacGroupIds &&
            role == other.role &&
            status == other.status &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            acceptedAt,
            email,
            expiresAt,
            invitedAt,
            rbacGroupIds,
            role,
            status,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaOrganizationInvite{id=$id, acceptedAt=$acceptedAt, email=$email, expiresAt=$expiresAt, invitedAt=$invitedAt, rbacGroupIds=$rbacGroupIds, role=$role, status=$status, type=$type, additionalProperties=$additionalProperties}"
}
