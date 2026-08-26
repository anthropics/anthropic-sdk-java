// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.invites

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Invite a user to join the organization by email.
 *
 * On plans that draw members from a finite pool of purchased seats, the invite automatically
 * consumes a seat from the lowest tier with availability; there is no seat-tier parameter. When no
 * seat is free the request fails with a 400 error rather than purchasing a seat.
 */
class InviteCreateParams
private constructor(
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * Email of the User.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun email(): String = body.email()

    /**
     * Role for the invited User.
     *
     * The accepted values depend on the organization type. Console and API organizations accept
     * `user`, `developer`, `billing`, and `claude_code_user`; `admin` cannot be assigned through
     * the API. Claude Enterprise organizations accept `user` and `managed`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun role(): Role = body.role()

    /**
     * RBAC group IDs to assign to the User when the Invite is accepted. A non-empty array is
     * accepted only for a Claude Enterprise organization with RBAC groups, and requires the key to
     * carry the `write:rbac_groups` scope.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun rbacGroupIds(): Optional<List<String>> = body.rbacGroupIds()

    /**
     * Returns the raw JSON value of [email].
     *
     * Unlike [email], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _email(): JsonField<String> = body._email()

    /**
     * Returns the raw JSON value of [role].
     *
     * Unlike [role], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _role(): JsonField<Role> = body._role()

    /**
     * Returns the raw JSON value of [rbacGroupIds].
     *
     * Unlike [rbacGroupIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _rbacGroupIds(): JsonField<List<String>> = body._rbacGroupIds()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [InviteCreateParams].
         *
         * The following fields are required:
         * ```java
         * .email()
         * .role()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [InviteCreateParams]. */
    class Builder internal constructor() {

        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(inviteCreateParams: InviteCreateParams) = apply {
            body = inviteCreateParams.body.toBuilder()
            additionalHeaders = inviteCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = inviteCreateParams.additionalQueryParams.toBuilder()
        }

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [email]
         * - [role]
         * - [rbacGroupIds]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** Email of the User. */
        fun email(email: String) = apply { body.email(email) }

        /**
         * Sets [Builder.email] to an arbitrary JSON value.
         *
         * You should usually call [Builder.email] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun email(email: JsonField<String>) = apply { body.email(email) }

        /**
         * Role for the invited User.
         *
         * The accepted values depend on the organization type. Console and API organizations accept
         * `user`, `developer`, `billing`, and `claude_code_user`; `admin` cannot be assigned
         * through the API. Claude Enterprise organizations accept `user` and `managed`.
         */
        fun role(role: Role) = apply { body.role(role) }

        /**
         * Sets [Builder.role] to an arbitrary JSON value.
         *
         * You should usually call [Builder.role] with a well-typed [Role] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun role(role: JsonField<Role>) = apply { body.role(role) }

        /**
         * RBAC group IDs to assign to the User when the Invite is accepted. A non-empty array is
         * accepted only for a Claude Enterprise organization with RBAC groups, and requires the key
         * to carry the `write:rbac_groups` scope.
         */
        fun rbacGroupIds(rbacGroupIds: List<String>) = apply { body.rbacGroupIds(rbacGroupIds) }

        /**
         * Sets [Builder.rbacGroupIds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupIds] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun rbacGroupIds(rbacGroupIds: JsonField<List<String>>) = apply {
            body.rbacGroupIds(rbacGroupIds)
        }

        /**
         * Adds a single [String] to [rbacGroupIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRbacGroupId(rbacGroupId: String) = apply { body.addRbacGroupId(rbacGroupId) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [InviteCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .email()
         * .role()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): InviteCreateParams =
            InviteCreateParams(
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val email: JsonField<String>,
        private val role: JsonField<Role>,
        private val rbacGroupIds: JsonField<List<String>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("email") @ExcludeMissing email: JsonField<String> = JsonMissing.of(),
            @JsonProperty("role") @ExcludeMissing role: JsonField<Role> = JsonMissing.of(),
            @JsonProperty("rbac_group_ids")
            @ExcludeMissing
            rbacGroupIds: JsonField<List<String>> = JsonMissing.of(),
        ) : this(email, role, rbacGroupIds, mutableMapOf())

        /**
         * Email of the User.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun email(): String = email.getRequired("email")

        /**
         * Role for the invited User.
         *
         * The accepted values depend on the organization type. Console and API organizations accept
         * `user`, `developer`, `billing`, and `claude_code_user`; `admin` cannot be assigned
         * through the API. Claude Enterprise organizations accept `user` and `managed`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun role(): Role = role.getRequired("role")

        /**
         * RBAC group IDs to assign to the User when the Invite is accepted. A non-empty array is
         * accepted only for a Claude Enterprise organization with RBAC groups, and requires the key
         * to carry the `write:rbac_groups` scope.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun rbacGroupIds(): Optional<List<String>> = rbacGroupIds.getOptional("rbac_group_ids")

        /**
         * Returns the raw JSON value of [email].
         *
         * Unlike [email], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("email") @ExcludeMissing fun _email(): JsonField<String> = email

        /**
         * Returns the raw JSON value of [role].
         *
         * Unlike [role], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("role") @ExcludeMissing fun _role(): JsonField<Role> = role

        /**
         * Returns the raw JSON value of [rbacGroupIds].
         *
         * Unlike [rbacGroupIds], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("rbac_group_ids")
        @ExcludeMissing
        fun _rbacGroupIds(): JsonField<List<String>> = rbacGroupIds

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
             * Returns a mutable builder for constructing an instance of [Body].
             *
             * The following fields are required:
             * ```java
             * .email()
             * .role()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var email: JsonField<String>? = null
            private var role: JsonField<Role>? = null
            private var rbacGroupIds: JsonField<MutableList<String>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                email = body.email
                role = body.role
                rbacGroupIds =
                    body.rbacGroupIds.map { it.toMutableList() }.takeUnless { it.isMissing() }
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /** Email of the User. */
            fun email(email: String) = email(JsonField.of(email))

            /**
             * Sets [Builder.email] to an arbitrary JSON value.
             *
             * You should usually call [Builder.email] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun email(email: JsonField<String>) = apply { this.email = email }

            /**
             * Role for the invited User.
             *
             * The accepted values depend on the organization type. Console and API organizations
             * accept `user`, `developer`, `billing`, and `claude_code_user`; `admin` cannot be
             * assigned through the API. Claude Enterprise organizations accept `user` and
             * `managed`.
             */
            fun role(role: Role) = role(JsonField.of(role))

            /**
             * Sets [Builder.role] to an arbitrary JSON value.
             *
             * You should usually call [Builder.role] with a well-typed [Role] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun role(role: JsonField<Role>) = apply { this.role = role }

            /**
             * RBAC group IDs to assign to the User when the Invite is accepted. A non-empty array
             * is accepted only for a Claude Enterprise organization with RBAC groups, and requires
             * the key to carry the `write:rbac_groups` scope.
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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .email()
             * .role()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("email", email),
                    checkRequired("role", role),
                    (rbacGroupIds ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
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
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            email()
            role().validate()
            rbacGroupIds()
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
            (if (email.asKnown().isPresent) 1 else 0) +
                (role.asKnown().getOrNull()?.validity() ?: 0) +
                (rbacGroupIds.asKnown().getOrNull()?.size ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                email == other.email &&
                role == other.role &&
                rbacGroupIds == other.rbacGroupIds &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(email, role, rbacGroupIds, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{email=$email, role=$role, rbacGroupIds=$rbacGroupIds, additionalProperties=$additionalProperties}"
    }

    /**
     * Role for the invited User.
     *
     * The accepted values depend on the organization type. Console and API organizations accept
     * `user`, `developer`, `billing`, and `claude_code_user`; `admin` cannot be assigned through
     * the API. Claude Enterprise organizations accept `user` and `managed`.
     */
    class Role @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val BILLING = of("billing")

            @JvmField val CLAUDE_CODE_USER = of("claude_code_user")

            @JvmField val DEVELOPER = of("developer")

            @JvmField val MANAGED = of("managed")

            @JvmField val USER = of("user")

            @JvmStatic fun of(value: String) = Role(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Role =
                value.asString().getOrNull()?.let { of(it) } ?: Role(value)
        }

        /** An enum containing [Role]'s known values. */
        enum class Known {
            BILLING,
            CLAUDE_CODE_USER,
            DEVELOPER,
            MANAGED,
            USER,
        }

        /**
         * An enum containing [Role]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Role] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            BILLING,
            CLAUDE_CODE_USER,
            DEVELOPER,
            MANAGED,
            USER,
            /** An enum member indicating that [Role] was instantiated with an unknown value. */
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
                BILLING -> Value.BILLING
                CLAUDE_CODE_USER -> Value.CLAUDE_CODE_USER
                DEVELOPER -> Value.DEVELOPER
                MANAGED -> Value.MANAGED
                USER -> Value.USER
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
                BILLING -> Known.BILLING
                CLAUDE_CODE_USER -> Known.CLAUDE_CODE_USER
                DEVELOPER -> Known.DEVELOPER
                MANAGED -> Known.MANAGED
                USER -> Known.USER
                else -> throw AnthropicInvalidDataException("Unknown Role: $value")
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
        fun validate(): Role = apply {
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

            return other is Role && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is InviteCreateParams &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int = Objects.hash(body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "InviteCreateParams{body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
