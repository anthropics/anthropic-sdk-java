// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.users

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.organization.BetaOrganizationRole
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

class BetaOrganizationUser
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val addedAt: JsonField<OffsetDateTime>,
    private val email: JsonField<String>,
    private val name: JsonField<String>,
    private val role: JsonField<BetaOrganizationRole>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("added_at")
        @ExcludeMissing
        addedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("email") @ExcludeMissing email: JsonField<String> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("role")
        @ExcludeMissing
        role: JsonField<BetaOrganizationRole> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, addedAt, email, name, role, type, mutableMapOf())

    /**
     * ID of the User.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * RFC 3339 datetime string indicating when the User joined the Organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun addedAt(): OffsetDateTime = addedAt.getRequired("added_at")

    /**
     * Email of the User.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun email(): String = email.getRequired("email")

    /**
     * Name of the User.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * Organization role of the User.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun role(): BetaOrganizationRole = role.getRequired("role")

    /**
     * Object type.
     *
     * For Users, this is always `"user"`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("user")
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
     * Returns the raw JSON value of [addedAt].
     *
     * Unlike [addedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("added_at") @ExcludeMissing fun _addedAt(): JsonField<OffsetDateTime> = addedAt

    /**
     * Returns the raw JSON value of [email].
     *
     * Unlike [email], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("email") @ExcludeMissing fun _email(): JsonField<String> = email

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [role].
     *
     * Unlike [role], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("role") @ExcludeMissing fun _role(): JsonField<BetaOrganizationRole> = role

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
         * Returns a mutable builder for constructing an instance of [BetaOrganizationUser].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .addedAt()
         * .email()
         * .name()
         * .role()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaOrganizationUser]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var addedAt: JsonField<OffsetDateTime>? = null
        private var email: JsonField<String>? = null
        private var name: JsonField<String>? = null
        private var role: JsonField<BetaOrganizationRole>? = null
        private var type: JsonValue = JsonValue.from("user")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaOrganizationUser: BetaOrganizationUser) = apply {
            id = betaOrganizationUser.id
            addedAt = betaOrganizationUser.addedAt
            email = betaOrganizationUser.email
            name = betaOrganizationUser.name
            role = betaOrganizationUser.role
            type = betaOrganizationUser.type
            additionalProperties = betaOrganizationUser.additionalProperties.toMutableMap()
        }

        /** ID of the User. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** RFC 3339 datetime string indicating when the User joined the Organization. */
        fun addedAt(addedAt: OffsetDateTime) = addedAt(JsonField.of(addedAt))

        /**
         * Sets [Builder.addedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.addedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun addedAt(addedAt: JsonField<OffsetDateTime>) = apply { this.addedAt = addedAt }

        /** Email of the User. */
        fun email(email: String) = email(JsonField.of(email))

        /**
         * Sets [Builder.email] to an arbitrary JSON value.
         *
         * You should usually call [Builder.email] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun email(email: JsonField<String>) = apply { this.email = email }

        /** Name of the User. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("user")
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
         * Returns an immutable instance of [BetaOrganizationUser].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .addedAt()
         * .email()
         * .name()
         * .role()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaOrganizationUser =
            BetaOrganizationUser(
                checkRequired("id", id),
                checkRequired("addedAt", addedAt),
                checkRequired("email", email),
                checkRequired("name", name),
                checkRequired("role", role),
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
    fun validate(): BetaOrganizationUser = apply {
        if (validated) {
            return@apply
        }

        id()
        addedAt()
        email()
        name()
        role().validate()
        _type().let {
            if (it != JsonValue.from("user")) {
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
            (if (addedAt.asKnown().isPresent) 1 else 0) +
            (if (email.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (role.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("user")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaOrganizationUser &&
            id == other.id &&
            addedAt == other.addedAt &&
            email == other.email &&
            name == other.name &&
            role == other.role &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, addedAt, email, name, role, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaOrganizationUser{id=$id, addedAt=$addedAt, email=$email, name=$name, role=$role, type=$type, additionalProperties=$additionalProperties}"
}
