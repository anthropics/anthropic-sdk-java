// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaApiKey
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val createdBy: JsonField<BetaApiKeyCreatedBy>,
    private val expiresAt: JsonField<OffsetDateTime>,
    private val name: JsonField<String>,
    private val partialKeyHint: JsonField<String>,
    private val principal: JsonField<Principal>,
    private val scope: JsonField<Scope>,
    private val status: JsonField<Status>,
    private val type: JsonValue,
    private val workspaceId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_by")
        @ExcludeMissing
        createdBy: JsonField<BetaApiKeyCreatedBy> = JsonMissing.of(),
        @JsonProperty("expires_at")
        @ExcludeMissing
        expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("partial_key_hint")
        @ExcludeMissing
        partialKeyHint: JsonField<String> = JsonMissing.of(),
        @JsonProperty("principal")
        @ExcludeMissing
        principal: JsonField<Principal> = JsonMissing.of(),
        @JsonProperty("scope") @ExcludeMissing scope: JsonField<Scope> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workspace_id")
        @ExcludeMissing
        workspaceId: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        createdAt,
        createdBy,
        expiresAt,
        name,
        partialKeyHint,
        principal,
        scope,
        status,
        type,
        workspaceId,
        mutableMapOf(),
    )

    /**
     * ID of the API key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * RFC 3339 datetime string indicating when the API Key was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * The ID and type of the actor that created the API key, or `null` when the creator is not
     * recorded (legacy, workload-identity-federated, or system-created keys).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun createdBy(): Optional<BetaApiKeyCreatedBy> = createdBy.getOptional("created_by")

    /**
     * RFC 3339 datetime string indicating when the API Key expires, or `null` if it never expires.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun expiresAt(): Optional<OffsetDateTime> = expiresAt.getOptional("expires_at")

    /**
     * Name of the API key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * Partially redacted hint for the API key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun partialKeyHint(): Optional<String> = partialKeyHint.getOptional("partial_key_hint")

    /**
     * The principal the API key acts as (a User or a Service Account), or `null` if the API key is
     * not bound to a principal.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun principal(): Optional<Principal> = principal.getOptional("principal")

    /**
     * Where the API key belongs: its Workspace (`{"type": "workspace", "workspace_id":
     * "wrkspc_..."}`, with the Workspace's real ID even when it is the organization's default
     * Workspace), or the organization (`{"type": "organization"}`) for a principal-bound API key
     * that has no Workspace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun scope(): Scope = scope.getRequired("scope")

    /**
     * Status of the API key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): Status = status.getRequired("status")

    /**
     * Object type.
     *
     * For API Keys, this is always `"api_key"`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("api_key")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Deprecated: use `scope` instead. ID of the Workspace associated with the API key, or `null`
     * if the API key belongs to the default Workspace. Also `null` for a principal-bound API key
     * that has no Workspace; `scope` tells the two apart.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    @Deprecated(
        "Use `scope` instead. `workspace_id` is `null` both for an API key in the default Workspace and for a principal-bound API key that has no Workspace."
    )
    fun workspaceId(): Optional<String> = workspaceId.getOptional("workspace_id")

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
     * Returns the raw JSON value of [createdBy].
     *
     * Unlike [createdBy], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_by")
    @ExcludeMissing
    fun _createdBy(): JsonField<BetaApiKeyCreatedBy> = createdBy

    /**
     * Returns the raw JSON value of [expiresAt].
     *
     * Unlike [expiresAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expires_at")
    @ExcludeMissing
    fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [partialKeyHint].
     *
     * Unlike [partialKeyHint], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("partial_key_hint")
    @ExcludeMissing
    fun _partialKeyHint(): JsonField<String> = partialKeyHint

    /**
     * Returns the raw JSON value of [principal].
     *
     * Unlike [principal], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("principal") @ExcludeMissing fun _principal(): JsonField<Principal> = principal

    /**
     * Returns the raw JSON value of [scope].
     *
     * Unlike [scope], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scope") @ExcludeMissing fun _scope(): JsonField<Scope> = scope

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

    /**
     * Returns the raw JSON value of [workspaceId].
     *
     * Unlike [workspaceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @Deprecated(
        "Use `scope` instead. `workspace_id` is `null` both for an API key in the default Workspace and for a principal-bound API key that has no Workspace."
    )
    @JsonProperty("workspace_id")
    @ExcludeMissing
    fun _workspaceId(): JsonField<String> = workspaceId

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
         * Returns a mutable builder for constructing an instance of [BetaApiKey].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .createdBy()
         * .expiresAt()
         * .name()
         * .partialKeyHint()
         * .principal()
         * .scope()
         * .status()
         * .workspaceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaApiKey]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var createdBy: JsonField<BetaApiKeyCreatedBy>? = null
        private var expiresAt: JsonField<OffsetDateTime>? = null
        private var name: JsonField<String>? = null
        private var partialKeyHint: JsonField<String>? = null
        private var principal: JsonField<Principal>? = null
        private var scope: JsonField<Scope>? = null
        private var status: JsonField<Status>? = null
        private var type: JsonValue = JsonValue.from("api_key")
        private var workspaceId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaApiKey: BetaApiKey) = apply {
            id = betaApiKey.id
            createdAt = betaApiKey.createdAt
            createdBy = betaApiKey.createdBy
            expiresAt = betaApiKey.expiresAt
            name = betaApiKey.name
            partialKeyHint = betaApiKey.partialKeyHint
            principal = betaApiKey.principal
            scope = betaApiKey.scope
            status = betaApiKey.status
            type = betaApiKey.type
            workspaceId = betaApiKey.workspaceId
            additionalProperties = betaApiKey.additionalProperties.toMutableMap()
        }

        /** ID of the API key. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** RFC 3339 datetime string indicating when the API Key was created. */
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
         * The ID and type of the actor that created the API key, or `null` when the creator is not
         * recorded (legacy, workload-identity-federated, or system-created keys).
         */
        fun createdBy(createdBy: BetaApiKeyCreatedBy?) = createdBy(JsonField.ofNullable(createdBy))

        /** Alias for calling [Builder.createdBy] with `createdBy.orElse(null)`. */
        fun createdBy(createdBy: Optional<BetaApiKeyCreatedBy>) = createdBy(createdBy.getOrNull())

        /**
         * Sets [Builder.createdBy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdBy] with a well-typed [BetaApiKeyCreatedBy] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdBy(createdBy: JsonField<BetaApiKeyCreatedBy>) = apply {
            this.createdBy = createdBy
        }

        /**
         * RFC 3339 datetime string indicating when the API Key expires, or `null` if it never
         * expires.
         */
        fun expiresAt(expiresAt: OffsetDateTime?) = expiresAt(JsonField.ofNullable(expiresAt))

        /** Alias for calling [Builder.expiresAt] with `expiresAt.orElse(null)`. */
        fun expiresAt(expiresAt: Optional<OffsetDateTime>) = expiresAt(expiresAt.getOrNull())

        /**
         * Sets [Builder.expiresAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.expiresAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply { this.expiresAt = expiresAt }

        /** Name of the API key. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** Partially redacted hint for the API key. */
        fun partialKeyHint(partialKeyHint: String?) =
            partialKeyHint(JsonField.ofNullable(partialKeyHint))

        /** Alias for calling [Builder.partialKeyHint] with `partialKeyHint.orElse(null)`. */
        fun partialKeyHint(partialKeyHint: Optional<String>) =
            partialKeyHint(partialKeyHint.getOrNull())

        /**
         * Sets [Builder.partialKeyHint] to an arbitrary JSON value.
         *
         * You should usually call [Builder.partialKeyHint] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun partialKeyHint(partialKeyHint: JsonField<String>) = apply {
            this.partialKeyHint = partialKeyHint
        }

        /**
         * The principal the API key acts as (a User or a Service Account), or `null` if the API key
         * is not bound to a principal.
         */
        fun principal(principal: Principal?) = principal(JsonField.ofNullable(principal))

        /** Alias for calling [Builder.principal] with `principal.orElse(null)`. */
        fun principal(principal: Optional<Principal>) = principal(principal.getOrNull())

        /**
         * Sets [Builder.principal] to an arbitrary JSON value.
         *
         * You should usually call [Builder.principal] with a well-typed [Principal] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun principal(principal: JsonField<Principal>) = apply { this.principal = principal }

        /** Alias for calling [principal] with `Principal.ofUserActor(userActor)`. */
        fun principal(userActor: BetaApiKeyUserActor) = principal(Principal.ofUserActor(userActor))

        /**
         * Alias for calling [principal] with the following:
         * ```java
         * BetaApiKeyUserActor.builder()
         *     .userId(userId)
         *     .build()
         * ```
         */
        fun userActorPrincipal(userId: String) =
            principal(BetaApiKeyUserActor.builder().userId(userId).build())

        /**
         * Alias for calling [principal] with
         * `Principal.ofServiceAccountActor(serviceAccountActor)`.
         */
        fun principal(serviceAccountActor: BetaApiKeyServiceAccountActor) =
            principal(Principal.ofServiceAccountActor(serviceAccountActor))

        /**
         * Alias for calling [principal] with the following:
         * ```java
         * BetaApiKeyServiceAccountActor.builder()
         *     .serviceAccountId(serviceAccountId)
         *     .build()
         * ```
         */
        fun serviceAccountActorPrincipal(serviceAccountId: String) =
            principal(
                BetaApiKeyServiceAccountActor.builder().serviceAccountId(serviceAccountId).build()
            )

        /**
         * Where the API key belongs: its Workspace (`{"type": "workspace", "workspace_id":
         * "wrkspc_..."}`, with the Workspace's real ID even when it is the organization's default
         * Workspace), or the organization (`{"type": "organization"}`) for a principal-bound API
         * key that has no Workspace.
         */
        fun scope(scope: Scope) = scope(JsonField.of(scope))

        /**
         * Sets [Builder.scope] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scope] with a well-typed [Scope] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun scope(scope: JsonField<Scope>) = apply { this.scope = scope }

        /** Alias for calling [scope] with `Scope.ofOrganization(organization)`. */
        fun scope(organization: BetaApiKeyOrganizationScope) =
            scope(Scope.ofOrganization(organization))

        /** Alias for calling [scope] with `Scope.ofWorkspace(workspace)`. */
        fun scope(workspace: BetaApiKeyWorkspaceScope) = scope(Scope.ofWorkspace(workspace))

        /**
         * Alias for calling [scope] with the following:
         * ```java
         * BetaApiKeyWorkspaceScope.builder()
         *     .workspaceId(workspaceId)
         *     .build()
         * ```
         */
        fun workspaceScope(workspaceId: String) =
            scope(BetaApiKeyWorkspaceScope.builder().workspaceId(workspaceId).build())

        /** Status of the API key. */
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
         * JsonValue.from("api_key")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Deprecated: use `scope` instead. ID of the Workspace associated with the API key, or
         * `null` if the API key belongs to the default Workspace. Also `null` for a principal-bound
         * API key that has no Workspace; `scope` tells the two apart.
         */
        @Deprecated(
            "Use `scope` instead. `workspace_id` is `null` both for an API key in the default Workspace and for a principal-bound API key that has no Workspace."
        )
        fun workspaceId(workspaceId: String?) = workspaceId(JsonField.ofNullable(workspaceId))

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        @Deprecated(
            "Use `scope` instead. `workspace_id` is `null` both for an API key in the default Workspace and for a principal-bound API key that has no Workspace."
        )
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

        /**
         * Sets [Builder.workspaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        @Deprecated(
            "Use `scope` instead. `workspace_id` is `null` both for an API key in the default Workspace and for a principal-bound API key that has no Workspace."
        )
        fun workspaceId(workspaceId: JsonField<String>) = apply { this.workspaceId = workspaceId }

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
         * Returns an immutable instance of [BetaApiKey].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .createdBy()
         * .expiresAt()
         * .name()
         * .partialKeyHint()
         * .principal()
         * .scope()
         * .status()
         * .workspaceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaApiKey =
            BetaApiKey(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("createdBy", createdBy),
                checkRequired("expiresAt", expiresAt),
                checkRequired("name", name),
                checkRequired("partialKeyHint", partialKeyHint),
                checkRequired("principal", principal),
                checkRequired("scope", scope),
                checkRequired("status", status),
                type,
                checkRequired("workspaceId", workspaceId),
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
    fun validate(): BetaApiKey = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        createdBy().ifPresent { it.validate() }
        expiresAt()
        name()
        partialKeyHint()
        principal().ifPresent { it.validate() }
        scope().validate()
        status().validate()
        _type().let {
            if (it != JsonValue.from("api_key")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        workspaceId()
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
            (createdBy.asKnown().getOrNull()?.validity() ?: 0) +
            (if (expiresAt.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (if (partialKeyHint.asKnown().isPresent) 1 else 0) +
            (principal.asKnown().getOrNull()?.validity() ?: 0) +
            (scope.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("api_key")) 1 else 0 } +
            (if (workspaceId.asKnown().isPresent) 1 else 0)

    /**
     * The principal the API key acts as (a User or a Service Account), or `null` if the API key is
     * not bound to a principal.
     */
    @JsonDeserialize(using = Principal.Deserializer::class)
    @JsonSerialize(using = Principal.Serializer::class)
    class Principal
    private constructor(
        private val userActor: BetaApiKeyUserActor? = null,
        private val serviceAccountActor: BetaApiKeyServiceAccountActor? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitUserActor(userActor: BetaApiKeyUserActor): Type =
                        Type.USER_ACTOR

                    override fun visitServiceAccountActor(
                        serviceAccountActor: BetaApiKeyServiceAccountActor
                    ): Type = Type.SERVICE_ACCOUNT_ACTOR

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun userActor(): Optional<BetaApiKeyUserActor> = Optional.ofNullable(userActor)

        fun serviceAccountActor(): Optional<BetaApiKeyServiceAccountActor> =
            Optional.ofNullable(serviceAccountActor)

        fun isUserActor(): Boolean = userActor != null

        fun isServiceAccountActor(): Boolean = serviceAccountActor != null

        fun asUserActor(): BetaApiKeyUserActor = userActor.getOrThrow("userActor")

        fun asServiceAccountActor(): BetaApiKeyServiceAccountActor =
            serviceAccountActor.getOrThrow("serviceAccountActor")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = principal.accept(new Principal.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitUserActor(BetaApiKeyUserActor userActor) {
         *         return Optional.of(userActor.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                userActor != null -> visitor.visitUserActor(userActor)
                serviceAccountActor != null -> visitor.visitServiceAccountActor(serviceAccountActor)
                else -> visitor.unknown(_json)
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
        fun validate(): Principal = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitUserActor(userActor: BetaApiKeyUserActor) {
                        userActor.validate()
                    }

                    override fun visitServiceAccountActor(
                        serviceAccountActor: BetaApiKeyServiceAccountActor
                    ) {
                        serviceAccountActor.validate()
                    }
                }
            )
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
            accept(
                object : Visitor<Int> {
                    override fun visitUserActor(userActor: BetaApiKeyUserActor) =
                        userActor.validity()

                    override fun visitServiceAccountActor(
                        serviceAccountActor: BetaApiKeyServiceAccountActor
                    ) = serviceAccountActor.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Principal &&
                userActor == other.userActor &&
                serviceAccountActor == other.serviceAccountActor
        }

        override fun hashCode(): Int = Objects.hash(userActor, serviceAccountActor)

        override fun toString(): String =
            when {
                userActor != null -> "Principal{userActor=$userActor}"
                serviceAccountActor != null -> "Principal{serviceAccountActor=$serviceAccountActor}"
                _json != null -> "Principal{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Principal")
            }

        companion object {

            @JvmStatic
            fun ofUserActor(userActor: BetaApiKeyUserActor) = Principal(userActor = userActor)

            /**
             * Returns an immutable instance of [Principal] whose [ofUserActor] variant is built
             * from the given required [userId].
             */
            @JvmStatic fun ofUserActor(userId: String) = ofUserActor(BetaApiKeyUserActor.of(userId))

            @JvmStatic
            fun ofServiceAccountActor(serviceAccountActor: BetaApiKeyServiceAccountActor) =
                Principal(serviceAccountActor = serviceAccountActor)

            /**
             * Returns an immutable instance of [Principal] whose [ofServiceAccountActor] variant is
             * built from the given required [serviceAccountId].
             */
            @JvmStatic
            fun ofServiceAccountActor(serviceAccountId: String) =
                ofServiceAccountActor(BetaApiKeyServiceAccountActor.of(serviceAccountId))
        }

        /**
         * An interface that defines how to map each variant of [Principal] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitUserActor(userActor: BetaApiKeyUserActor): T

            fun visitServiceAccountActor(serviceAccountActor: BetaApiKeyServiceAccountActor): T

            /**
             * Maps an unknown variant of [Principal] to a value of type [T].
             *
             * An instance of [Principal] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Principal: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Principal>(Principal::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Principal {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "user_actor" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaApiKeyUserActor>())?.let {
                            Principal(userActor = it, _json = json)
                        } ?: Principal(_json = json)
                    }
                    "service_account_actor" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaApiKeyServiceAccountActor>())
                            ?.let { Principal(serviceAccountActor = it, _json = json) }
                            ?: Principal(_json = json)
                    }
                }

                return Principal(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Principal>(Principal::class) {

            override fun serialize(
                value: Principal,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.userActor != null -> generator.writeObject(value.userActor)
                    value.serviceAccountActor != null ->
                        generator.writeObject(value.serviceAccountActor)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Principal")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val USER_ACTOR = of("user_actor")

                @JvmField val SERVICE_ACCOUNT_ACTOR = of("service_account_actor")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                USER_ACTOR,
                SERVICE_ACCOUNT_ACTOR,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                USER_ACTOR,
                SERVICE_ACCOUNT_ACTOR,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    USER_ACTOR -> Value.USER_ACTOR
                    SERVICE_ACCOUNT_ACTOR -> Value.SERVICE_ACCOUNT_ACTOR
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    USER_ACTOR -> Known.USER_ACTOR
                    SERVICE_ACCOUNT_ACTOR -> Known.SERVICE_ACCOUNT_ACTOR
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
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
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
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

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    /**
     * Where the API key belongs: its Workspace (`{"type": "workspace", "workspace_id":
     * "wrkspc_..."}`, with the Workspace's real ID even when it is the organization's default
     * Workspace), or the organization (`{"type": "organization"}`) for a principal-bound API key
     * that has no Workspace.
     */
    @JsonDeserialize(using = Scope.Deserializer::class)
    @JsonSerialize(using = Scope.Serializer::class)
    class Scope
    private constructor(
        private val organization: BetaApiKeyOrganizationScope? = null,
        private val workspace: BetaApiKeyWorkspaceScope? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitOrganization(
                        organization: BetaApiKeyOrganizationScope
                    ): Type = Type.ORGANIZATION

                    override fun visitWorkspace(workspace: BetaApiKeyWorkspaceScope): Type =
                        Type.WORKSPACE

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun organization(): Optional<BetaApiKeyOrganizationScope> =
            Optional.ofNullable(organization)

        fun workspace(): Optional<BetaApiKeyWorkspaceScope> = Optional.ofNullable(workspace)

        fun isOrganization(): Boolean = organization != null

        fun isWorkspace(): Boolean = workspace != null

        fun asOrganization(): BetaApiKeyOrganizationScope = organization.getOrThrow("organization")

        fun asWorkspace(): BetaApiKeyWorkspaceScope = workspace.getOrThrow("workspace")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = scope.accept(new Scope.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitOrganization(BetaApiKeyOrganizationScope organization) {
         *         return Optional.of(organization.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                organization != null -> visitor.visitOrganization(organization)
                workspace != null -> visitor.visitWorkspace(workspace)
                else -> visitor.unknown(_json)
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
        fun validate(): Scope = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitOrganization(organization: BetaApiKeyOrganizationScope) {
                        organization.validate()
                    }

                    override fun visitWorkspace(workspace: BetaApiKeyWorkspaceScope) {
                        workspace.validate()
                    }
                }
            )
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
            accept(
                object : Visitor<Int> {
                    override fun visitOrganization(organization: BetaApiKeyOrganizationScope) =
                        organization.validity()

                    override fun visitWorkspace(workspace: BetaApiKeyWorkspaceScope) =
                        workspace.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Scope &&
                organization == other.organization &&
                workspace == other.workspace
        }

        override fun hashCode(): Int = Objects.hash(organization, workspace)

        override fun toString(): String =
            when {
                organization != null -> "Scope{organization=$organization}"
                workspace != null -> "Scope{workspace=$workspace}"
                _json != null -> "Scope{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Scope")
            }

        companion object {

            @JvmStatic
            fun ofOrganization(organization: BetaApiKeyOrganizationScope) =
                Scope(organization = organization)

            @JvmStatic
            fun ofWorkspace(workspace: BetaApiKeyWorkspaceScope) = Scope(workspace = workspace)

            /**
             * Returns an immutable instance of [Scope] whose [ofWorkspace] variant is built from
             * the given required [workspaceId].
             */
            @JvmStatic
            fun ofWorkspace(workspaceId: String) =
                ofWorkspace(BetaApiKeyWorkspaceScope.of(workspaceId))
        }

        /** An interface that defines how to map each variant of [Scope] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitOrganization(organization: BetaApiKeyOrganizationScope): T

            fun visitWorkspace(workspace: BetaApiKeyWorkspaceScope): T

            /**
             * Maps an unknown variant of [Scope] to a value of type [T].
             *
             * An instance of [Scope] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Scope: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Scope>(Scope::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Scope {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "organization" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaApiKeyOrganizationScope>())
                            ?.let { Scope(organization = it, _json = json) } ?: Scope(_json = json)
                    }
                    "workspace" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaApiKeyWorkspaceScope>())
                            ?.let { Scope(workspace = it, _json = json) } ?: Scope(_json = json)
                    }
                }

                return Scope(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Scope>(Scope::class) {

            override fun serialize(
                value: Scope,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.organization != null -> generator.writeObject(value.organization)
                    value.workspace != null -> generator.writeObject(value.workspace)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Scope")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val ORGANIZATION = of("organization")

                @JvmField val WORKSPACE = of("workspace")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                ORGANIZATION,
                WORKSPACE,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                ORGANIZATION,
                WORKSPACE,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    ORGANIZATION -> Value.ORGANIZATION
                    WORKSPACE -> Value.WORKSPACE
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    ORGANIZATION -> Known.ORGANIZATION
                    WORKSPACE -> Known.WORKSPACE
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
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
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
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

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    /** Status of the API key. */
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

            @JvmField val ACTIVE = of("active")

            @JvmField val ARCHIVED = of("archived")

            @JvmField val EXPIRED = of("expired")

            @JvmField val INACTIVE = of("inactive")

            @JvmStatic fun of(value: String) = Status(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Status =
                value.asString().getOrNull()?.let { of(it) } ?: Status(value)
        }

        /** An enum containing [Status]'s known values. */
        enum class Known {
            ACTIVE,
            ARCHIVED,
            EXPIRED,
            INACTIVE,
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
            ACTIVE,
            ARCHIVED,
            EXPIRED,
            INACTIVE,
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
                ACTIVE -> Value.ACTIVE
                ARCHIVED -> Value.ARCHIVED
                EXPIRED -> Value.EXPIRED
                INACTIVE -> Value.INACTIVE
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
                ARCHIVED -> Known.ARCHIVED
                EXPIRED -> Known.EXPIRED
                INACTIVE -> Known.INACTIVE
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

        return other is BetaApiKey &&
            id == other.id &&
            createdAt == other.createdAt &&
            createdBy == other.createdBy &&
            expiresAt == other.expiresAt &&
            name == other.name &&
            partialKeyHint == other.partialKeyHint &&
            principal == other.principal &&
            scope == other.scope &&
            status == other.status &&
            type == other.type &&
            workspaceId == other.workspaceId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            createdBy,
            expiresAt,
            name,
            partialKeyHint,
            principal,
            scope,
            status,
            type,
            workspaceId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaApiKey{id=$id, createdAt=$createdAt, createdBy=$createdBy, expiresAt=$expiresAt, name=$name, partialKeyHint=$partialKeyHint, principal=$principal, scope=$scope, status=$status, type=$type, workspaceId=$workspaceId, additionalProperties=$additionalProperties}"
}
