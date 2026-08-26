// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.serviceaccounts

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.organization.workspaces.BetaWorkspaceRole
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaServiceAccountWorkspaceMember
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val createdByActorId: JsonField<String>,
    private val implicit: JsonField<Boolean>,
    private val serviceAccountId: JsonField<String>,
    private val type: JsonValue,
    private val workspaceId: JsonField<String>,
    private val workspaceRole: JsonField<BetaWorkspaceRole>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("created_by_actor_id")
        @ExcludeMissing
        createdByActorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("implicit") @ExcludeMissing implicit: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("service_account_id")
        @ExcludeMissing
        serviceAccountId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workspace_id")
        @ExcludeMissing
        workspaceId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("workspace_role")
        @ExcludeMissing
        workspaceRole: JsonField<BetaWorkspaceRole> = JsonMissing.of(),
    ) : this(
        createdByActorId,
        implicit,
        serviceAccountId,
        type,
        workspaceId,
        workspaceRole,
        mutableMapOf(),
    )

    /**
     * Tagged ID (`user_...`/`svac_...`) of the actor who created this membership.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun createdByActorId(): Optional<String> = createdByActorId.getOptional("created_by_actor_id")

    /**
     * True when this is the implicit default-workspace membership every service account has when no
     * explicit membership exists. Implicit memberships have role `workspace_user` and cannot be
     * removed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun implicit(): Optional<Boolean> = implicit.getOptional("implicit")

    /**
     * Tagged service account ID (`svac_...`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun serviceAccountId(): String = serviceAccountId.getRequired("service_account_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("service_account_workspace_member")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Tagged workspace ID (`wrkspc_...`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceId(): String = workspaceId.getRequired("workspace_id")

    /**
     * Role of the service account in this workspace. Service accounts cannot hold the
     * `workspace_billing` role.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceRole(): BetaWorkspaceRole = workspaceRole.getRequired("workspace_role")

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
     * Returns the raw JSON value of [implicit].
     *
     * Unlike [implicit], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("implicit") @ExcludeMissing fun _implicit(): JsonField<Boolean> = implicit

    /**
     * Returns the raw JSON value of [serviceAccountId].
     *
     * Unlike [serviceAccountId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("service_account_id")
    @ExcludeMissing
    fun _serviceAccountId(): JsonField<String> = serviceAccountId

    /**
     * Returns the raw JSON value of [workspaceId].
     *
     * Unlike [workspaceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_id")
    @ExcludeMissing
    fun _workspaceId(): JsonField<String> = workspaceId

    /**
     * Returns the raw JSON value of [workspaceRole].
     *
     * Unlike [workspaceRole], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_role")
    @ExcludeMissing
    fun _workspaceRole(): JsonField<BetaWorkspaceRole> = workspaceRole

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
         * [BetaServiceAccountWorkspaceMember].
         *
         * The following fields are required:
         * ```java
         * .createdByActorId()
         * .implicit()
         * .serviceAccountId()
         * .workspaceId()
         * .workspaceRole()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaServiceAccountWorkspaceMember]. */
    class Builder internal constructor() {

        private var createdByActorId: JsonField<String>? = null
        private var implicit: JsonField<Boolean>? = null
        private var serviceAccountId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("service_account_workspace_member")
        private var workspaceId: JsonField<String>? = null
        private var workspaceRole: JsonField<BetaWorkspaceRole>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaServiceAccountWorkspaceMember: BetaServiceAccountWorkspaceMember) =
            apply {
                createdByActorId = betaServiceAccountWorkspaceMember.createdByActorId
                implicit = betaServiceAccountWorkspaceMember.implicit
                serviceAccountId = betaServiceAccountWorkspaceMember.serviceAccountId
                type = betaServiceAccountWorkspaceMember.type
                workspaceId = betaServiceAccountWorkspaceMember.workspaceId
                workspaceRole = betaServiceAccountWorkspaceMember.workspaceRole
                additionalProperties =
                    betaServiceAccountWorkspaceMember.additionalProperties.toMutableMap()
            }

        /** Tagged ID (`user_...`/`svac_...`) of the actor who created this membership. */
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

        /**
         * True when this is the implicit default-workspace membership every service account has
         * when no explicit membership exists. Implicit memberships have role `workspace_user` and
         * cannot be removed.
         */
        fun implicit(implicit: Boolean?) = implicit(JsonField.ofNullable(implicit))

        /**
         * Alias for [Builder.implicit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun implicit(implicit: Boolean) = implicit(implicit as Boolean?)

        /** Alias for calling [Builder.implicit] with `implicit.orElse(null)`. */
        fun implicit(implicit: Optional<Boolean>) = implicit(implicit.getOrNull())

        /**
         * Sets [Builder.implicit] to an arbitrary JSON value.
         *
         * You should usually call [Builder.implicit] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun implicit(implicit: JsonField<Boolean>) = apply { this.implicit = implicit }

        /** Tagged service account ID (`svac_...`). */
        fun serviceAccountId(serviceAccountId: String) =
            serviceAccountId(JsonField.of(serviceAccountId))

        /**
         * Sets [Builder.serviceAccountId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serviceAccountId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun serviceAccountId(serviceAccountId: JsonField<String>) = apply {
            this.serviceAccountId = serviceAccountId
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("service_account_workspace_member")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Tagged workspace ID (`wrkspc_...`). */
        fun workspaceId(workspaceId: String) = workspaceId(JsonField.of(workspaceId))

        /**
         * Sets [Builder.workspaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workspaceId(workspaceId: JsonField<String>) = apply { this.workspaceId = workspaceId }

        /**
         * Role of the service account in this workspace. Service accounts cannot hold the
         * `workspace_billing` role.
         */
        fun workspaceRole(workspaceRole: BetaWorkspaceRole) =
            workspaceRole(JsonField.of(workspaceRole))

        /**
         * Sets [Builder.workspaceRole] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceRole] with a well-typed [BetaWorkspaceRole]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun workspaceRole(workspaceRole: JsonField<BetaWorkspaceRole>) = apply {
            this.workspaceRole = workspaceRole
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
         * Returns an immutable instance of [BetaServiceAccountWorkspaceMember].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .createdByActorId()
         * .implicit()
         * .serviceAccountId()
         * .workspaceId()
         * .workspaceRole()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaServiceAccountWorkspaceMember =
            BetaServiceAccountWorkspaceMember(
                checkRequired("createdByActorId", createdByActorId),
                checkRequired("implicit", implicit),
                checkRequired("serviceAccountId", serviceAccountId),
                type,
                checkRequired("workspaceId", workspaceId),
                checkRequired("workspaceRole", workspaceRole),
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
    fun validate(): BetaServiceAccountWorkspaceMember = apply {
        if (validated) {
            return@apply
        }

        createdByActorId()
        implicit()
        serviceAccountId()
        _type().let {
            if (it != JsonValue.from("service_account_workspace_member")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        workspaceId()
        workspaceRole().validate()
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
        (if (createdByActorId.asKnown().isPresent) 1 else 0) +
            (if (implicit.asKnown().isPresent) 1 else 0) +
            (if (serviceAccountId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("service_account_workspace_member")) 1 else 0 } +
            (if (workspaceId.asKnown().isPresent) 1 else 0) +
            (workspaceRole.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaServiceAccountWorkspaceMember &&
            createdByActorId == other.createdByActorId &&
            implicit == other.implicit &&
            serviceAccountId == other.serviceAccountId &&
            type == other.type &&
            workspaceId == other.workspaceId &&
            workspaceRole == other.workspaceRole &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            createdByActorId,
            implicit,
            serviceAccountId,
            type,
            workspaceId,
            workspaceRole,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaServiceAccountWorkspaceMember{createdByActorId=$createdByActorId, implicit=$implicit, serviceAccountId=$serviceAccountId, type=$type, workspaceId=$workspaceId, workspaceRole=$workspaceRole, additionalProperties=$additionalProperties}"
}
