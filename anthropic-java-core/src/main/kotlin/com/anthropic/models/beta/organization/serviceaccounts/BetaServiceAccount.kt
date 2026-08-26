// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.serviceaccounts

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
 * Named non-human identity within the caller's organization.
 *
 * A service account is a pure identity: name + org. Authorization lives on whatever references it
 * (federation rules).
 */
class BetaServiceAccount
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val archivedByActorId: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val createdByActorId: JsonField<String>,
    private val description: JsonField<String>,
    private val name: JsonField<String>,
    private val organizationRole: JsonField<OrganizationRole>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val updatedByActorId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("archived_at")
        @ExcludeMissing
        archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("archived_by_actor_id")
        @ExcludeMissing
        archivedByActorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_by_actor_id")
        @ExcludeMissing
        createdByActorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("organization_role")
        @ExcludeMissing
        organizationRole: JsonField<OrganizationRole> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("updated_by_actor_id")
        @ExcludeMissing
        updatedByActorId: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        archivedAt,
        archivedByActorId,
        createdAt,
        createdByActorId,
        description,
        name,
        organizationRole,
        type,
        updatedAt,
        updatedByActorId,
        mutableMapOf(),
    )

    /**
     * Tagged ID of the service account.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * If set, this service account is archived.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedAt(): Optional<OffsetDateTime> = archivedAt.getOptional("archived_at")

    /**
     * Tagged ID (`user_`/`svac_`) of the actor that archived this service account.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedByActorId(): Optional<String> =
        archivedByActorId.getOptional("archived_by_actor_id")

    /**
     * When this service account was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Tagged ID (`user_`/`svac_`) of the actor that created this service account.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun createdByActorId(): Optional<String> = createdByActorId.getOptional("created_by_actor_id")

    /**
     * Optional free-text description.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun description(): Optional<String> = description.getOptional("description")

    /**
     * Admin-chosen slug identifier.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * Org-level role. A federation rule may only be created or retargeted to grant `org:admin`
     * scope when this is `admin`. A rule granting `org:admin` whose target is later demoted to
     * `developer` is rejected at token exchange. Rules granting `org:admin` are managed in the
     * Console.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun organizationRole(): OrganizationRole = organizationRole.getRequired("organization_role")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("service_account")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * When this service account was last updated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * Tagged ID (`user_`/`svac_`) of the actor that last updated this service account.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun updatedByActorId(): Optional<String> = updatedByActorId.getOptional("updated_by_actor_id")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [archivedAt].
     *
     * Unlike [archivedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("archived_at")
    @ExcludeMissing
    fun _archivedAt(): JsonField<OffsetDateTime> = archivedAt

    /**
     * Returns the raw JSON value of [archivedByActorId].
     *
     * Unlike [archivedByActorId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("archived_by_actor_id")
    @ExcludeMissing
    fun _archivedByActorId(): JsonField<String> = archivedByActorId

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [createdByActorId].
     *
     * Unlike [createdByActorId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("created_by_actor_id")
    @ExcludeMissing
    fun _createdByActorId(): JsonField<String> = createdByActorId

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [organizationRole].
     *
     * Unlike [organizationRole], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("organization_role")
    @ExcludeMissing
    fun _organizationRole(): JsonField<OrganizationRole> = organizationRole

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [updatedByActorId].
     *
     * Unlike [updatedByActorId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("updated_by_actor_id")
    @ExcludeMissing
    fun _updatedByActorId(): JsonField<String> = updatedByActorId

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
         * Returns a mutable builder for constructing an instance of [BetaServiceAccount].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .archivedByActorId()
         * .createdAt()
         * .createdByActorId()
         * .description()
         * .name()
         * .organizationRole()
         * .updatedAt()
         * .updatedByActorId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaServiceAccount]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var archivedByActorId: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var createdByActorId: JsonField<String>? = null
        private var description: JsonField<String>? = null
        private var name: JsonField<String>? = null
        private var organizationRole: JsonField<OrganizationRole>? = null
        private var type: JsonValue = JsonValue.from("service_account")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var updatedByActorId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaServiceAccount: BetaServiceAccount) = apply {
            id = betaServiceAccount.id
            archivedAt = betaServiceAccount.archivedAt
            archivedByActorId = betaServiceAccount.archivedByActorId
            createdAt = betaServiceAccount.createdAt
            createdByActorId = betaServiceAccount.createdByActorId
            description = betaServiceAccount.description
            name = betaServiceAccount.name
            organizationRole = betaServiceAccount.organizationRole
            type = betaServiceAccount.type
            updatedAt = betaServiceAccount.updatedAt
            updatedByActorId = betaServiceAccount.updatedByActorId
            additionalProperties = betaServiceAccount.additionalProperties.toMutableMap()
        }

        /** Tagged ID of the service account. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** If set, this service account is archived. */
        fun archivedAt(archivedAt: OffsetDateTime?) = archivedAt(JsonField.ofNullable(archivedAt))

        /** Alias for calling [Builder.archivedAt] with `archivedAt.orElse(null)`. */
        fun archivedAt(archivedAt: Optional<OffsetDateTime>) = archivedAt(archivedAt.getOrNull())

        /**
         * Sets [Builder.archivedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.archivedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun archivedAt(archivedAt: JsonField<OffsetDateTime>) = apply {
            this.archivedAt = archivedAt
        }

        /** Tagged ID (`user_`/`svac_`) of the actor that archived this service account. */
        fun archivedByActorId(archivedByActorId: String?) =
            archivedByActorId(JsonField.ofNullable(archivedByActorId))

        /** Alias for calling [Builder.archivedByActorId] with `archivedByActorId.orElse(null)`. */
        fun archivedByActorId(archivedByActorId: Optional<String>) =
            archivedByActorId(archivedByActorId.getOrNull())

        /**
         * Sets [Builder.archivedByActorId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.archivedByActorId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun archivedByActorId(archivedByActorId: JsonField<String>) = apply {
            this.archivedByActorId = archivedByActorId
        }

        /** When this service account was created. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** Tagged ID (`user_`/`svac_`) of the actor that created this service account. */
        fun createdByActorId(createdByActorId: String?) =
            createdByActorId(JsonField.ofNullable(createdByActorId))

        /** Alias for calling [Builder.createdByActorId] with `createdByActorId.orElse(null)`. */
        fun createdByActorId(createdByActorId: Optional<String>) =
            createdByActorId(createdByActorId.getOrNull())

        /**
         * Sets [Builder.createdByActorId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdByActorId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdByActorId(createdByActorId: JsonField<String>) = apply {
            this.createdByActorId = createdByActorId
        }

        /** Optional free-text description. */
        fun description(description: String?) = description(JsonField.ofNullable(description))

        /** Alias for calling [Builder.description] with `description.orElse(null)`. */
        fun description(description: Optional<String>) = description(description.getOrNull())

        /**
         * Sets [Builder.description] to an arbitrary JSON value.
         *
         * You should usually call [Builder.description] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun description(description: JsonField<String>) = apply { this.description = description }

        /** Admin-chosen slug identifier. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /**
         * Org-level role. A federation rule may only be created or retargeted to grant `org:admin`
         * scope when this is `admin`. A rule granting `org:admin` whose target is later demoted to
         * `developer` is rejected at token exchange. Rules granting `org:admin` are managed in the
         * Console.
         */
        fun organizationRole(organizationRole: OrganizationRole) =
            organizationRole(JsonField.of(organizationRole))

        /**
         * Sets [Builder.organizationRole] to an arbitrary JSON value.
         *
         * You should usually call [Builder.organizationRole] with a well-typed [OrganizationRole]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun organizationRole(organizationRole: JsonField<OrganizationRole>) = apply {
            this.organizationRole = organizationRole
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("service_account")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** When this service account was last updated. */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /** Tagged ID (`user_`/`svac_`) of the actor that last updated this service account. */
        fun updatedByActorId(updatedByActorId: String?) =
            updatedByActorId(JsonField.ofNullable(updatedByActorId))

        /** Alias for calling [Builder.updatedByActorId] with `updatedByActorId.orElse(null)`. */
        fun updatedByActorId(updatedByActorId: Optional<String>) =
            updatedByActorId(updatedByActorId.getOrNull())

        /**
         * Sets [Builder.updatedByActorId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedByActorId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedByActorId(updatedByActorId: JsonField<String>) = apply {
            this.updatedByActorId = updatedByActorId
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
         * Returns an immutable instance of [BetaServiceAccount].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .archivedByActorId()
         * .createdAt()
         * .createdByActorId()
         * .description()
         * .name()
         * .organizationRole()
         * .updatedAt()
         * .updatedByActorId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaServiceAccount =
            BetaServiceAccount(
                checkRequired("id", id),
                checkRequired("archivedAt", archivedAt),
                checkRequired("archivedByActorId", archivedByActorId),
                checkRequired("createdAt", createdAt),
                checkRequired("createdByActorId", createdByActorId),
                checkRequired("description", description),
                checkRequired("name", name),
                checkRequired("organizationRole", organizationRole),
                type,
                checkRequired("updatedAt", updatedAt),
                checkRequired("updatedByActorId", updatedByActorId),
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
    fun validate(): BetaServiceAccount = apply {
        if (validated) {
            return@apply
        }

        id()
        archivedAt()
        archivedByActorId()
        createdAt()
        createdByActorId()
        description()
        name()
        organizationRole().validate()
        _type().let {
            if (it != JsonValue.from("service_account")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
        updatedByActorId()
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
            (if (archivedAt.asKnown().isPresent) 1 else 0) +
            (if (archivedByActorId.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (createdByActorId.asKnown().isPresent) 1 else 0) +
            (if (description.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (organizationRole.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("service_account")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (updatedByActorId.asKnown().isPresent) 1 else 0)

    /**
     * Org-level role. A federation rule may only be created or retargeted to grant `org:admin`
     * scope when this is `admin`. A rule granting `org:admin` whose target is later demoted to
     * `developer` is rejected at token exchange. Rules granting `org:admin` are managed in the
     * Console.
     */
    class OrganizationRole @JsonCreator private constructor(private val value: JsonField<String>) :
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

            @JvmField val ADMIN = of("admin")

            @JvmField val DEVELOPER = of("developer")

            @JvmStatic fun of(value: String) = OrganizationRole(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): OrganizationRole =
                value.asString().getOrNull()?.let { of(it) } ?: OrganizationRole(value)
        }

        /** An enum containing [OrganizationRole]'s known values. */
        enum class Known {
            ADMIN,
            DEVELOPER,
        }

        /**
         * An enum containing [OrganizationRole]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [OrganizationRole] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ADMIN,
            DEVELOPER,
            /**
             * An enum member indicating that [OrganizationRole] was instantiated with an unknown
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
                ADMIN -> Value.ADMIN
                DEVELOPER -> Value.DEVELOPER
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
                ADMIN -> Known.ADMIN
                DEVELOPER -> Known.DEVELOPER
                else -> throw AnthropicInvalidDataException("Unknown OrganizationRole: $value")
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
        fun validate(): OrganizationRole = apply {
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

            return other is OrganizationRole && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaServiceAccount &&
            id == other.id &&
            archivedAt == other.archivedAt &&
            archivedByActorId == other.archivedByActorId &&
            createdAt == other.createdAt &&
            createdByActorId == other.createdByActorId &&
            description == other.description &&
            name == other.name &&
            organizationRole == other.organizationRole &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            updatedByActorId == other.updatedByActorId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            archivedAt,
            archivedByActorId,
            createdAt,
            createdByActorId,
            description,
            name,
            organizationRole,
            type,
            updatedAt,
            updatedByActorId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaServiceAccount{id=$id, archivedAt=$archivedAt, archivedByActorId=$archivedByActorId, createdAt=$createdAt, createdByActorId=$createdByActorId, description=$description, name=$name, organizationRole=$organizationRole, type=$type, updatedAt=$updatedAt, updatedByActorId=$updatedByActorId, additionalProperties=$additionalProperties}"
}
