package com.anthropic.models.organization.federation.rules

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
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
 * Authorization rule binding an external OIDC identity to Anthropic.
 *
 * Evaluates the match conditions and mints an OAuth access token for the resolved target, scoped to
 * a single workspace where the rule is enabled (chosen by the caller at exchange time when the rule
 * is enabled for more than one). For rules enabled via `workspace_ids` or
 * `applies_to_all_workspaces`, the target service account must be a member of that workspace (it is
 * implicitly a member of the default workspace); rules carrying only the legacy `workspace_id`
 * binding do not enforce this.
 */
class FederationRule
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val appliesToAllWorkspaces: JsonField<Boolean>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val archivedByActorId: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val createdByActorId: JsonField<String>,
    private val description: JsonField<String>,
    private val issuerId: JsonField<String>,
    private val issuerName: JsonField<String>,
    private val match: JsonField<FederationRuleMatch>,
    private val name: JsonField<String>,
    private val oauthScope: JsonField<String>,
    private val target: JsonField<ServiceAccountTarget>,
    private val tokenLifetimeSeconds: JsonField<Long>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val updatedByActorId: JsonField<String>,
    private val workspaceIds: JsonField<List<String>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("applies_to_all_workspaces")
        @ExcludeMissing
        appliesToAllWorkspaces: JsonField<Boolean> = JsonMissing.of(),
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
        @JsonProperty("issuer_id") @ExcludeMissing issuerId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("issuer_name")
        @ExcludeMissing
        issuerName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("match")
        @ExcludeMissing
        match: JsonField<FederationRuleMatch> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("oauth_scope")
        @ExcludeMissing
        oauthScope: JsonField<String> = JsonMissing.of(),
        @JsonProperty("target")
        @ExcludeMissing
        target: JsonField<ServiceAccountTarget> = JsonMissing.of(),
        @JsonProperty("token_lifetime_seconds")
        @ExcludeMissing
        tokenLifetimeSeconds: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("updated_by_actor_id")
        @ExcludeMissing
        updatedByActorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("workspace_ids")
        @ExcludeMissing
        workspaceIds: JsonField<List<String>> = JsonMissing.of(),
    ) : this(
        id,
        appliesToAllWorkspaces,
        archivedAt,
        archivedByActorId,
        createdAt,
        createdByActorId,
        description,
        issuerId,
        issuerName,
        match,
        name,
        oauthScope,
        target,
        tokenLifetimeSeconds,
        type,
        updatedAt,
        updatedByActorId,
        workspaceIds,
        mutableMapOf(),
    )

    /**
     * Tagged ID of the federation rule.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * When true, this rule is enabled for every workspace in the org (including ones created after
     * the rule). `workspace_ids` is ignored at exchange time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun appliesToAllWorkspaces(): Boolean =
        appliesToAllWorkspaces.getRequired("applies_to_all_workspaces")

    /**
     * If set, this rule is archived and rejects token exchange.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedAt(): Optional<OffsetDateTime> = archivedAt.getOptional("archived_at")

    /**
     * Tagged ID (`user_`/`svac_`) of the actor that archived this rule.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedByActorId(): Optional<String> =
        archivedByActorId.getOptional("archived_by_actor_id")

    /**
     * When this rule was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Tagged ID (`user_`/`svac_`) of the actor that created this rule.
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
     * Tagged ID of the issuer whose tokens this rule accepts.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun issuerId(): String = issuerId.getRequired("issuer_id")

    /**
     * Issuer's display name at read time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun issuerName(): Optional<String> = issuerName.getOptional("issuer_name")

    /**
     * Conditions the verified JWT must satisfy for this rule to apply. All populated matcher fields
     * must pass.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun match(): FederationRuleMatch = match.getRequired("match")

    /**
     * Admin-chosen slug identifier.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * Space-separated OAuth scopes granted on the minted token.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun oauthScope(): String = oauthScope.getRequired("oauth_scope")

    /**
     * What this rule targets. Check `type` before reading the other fields. Tokens minted via a
     * rule whose target `type` is `service_account` act as that service account.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun target(): ServiceAccountTarget = target.getRequired("target")

    /**
     * Lifetime in seconds of access tokens minted via this rule. Minted tokens are capped at
     * `max(60, min(this value, 2 × remaining assertion validity))` seconds.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tokenLifetimeSeconds(): Long = tokenLifetimeSeconds.getRequired("token_lifetime_seconds")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("federation_rule")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * When this rule was last updated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * Tagged ID (`user_`/`svac_`) of the actor that last updated this rule.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun updatedByActorId(): Optional<String> = updatedByActorId.getOptional("updated_by_actor_id")

    /**
     * Tagged IDs of the workspaces this rule is enabled for. May be empty for older rules that only
     * carry the legacy `workspace_id` binding. Ignored at exchange time when
     * `applies_to_all_workspaces` is true (the list may still be non-empty).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceIds(): List<String> = workspaceIds.getRequired("workspace_ids")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [appliesToAllWorkspaces].
     *
     * Unlike [appliesToAllWorkspaces], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("applies_to_all_workspaces")
    @ExcludeMissing
    fun _appliesToAllWorkspaces(): JsonField<Boolean> = appliesToAllWorkspaces

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
     * Returns the raw JSON value of [issuerId].
     *
     * Unlike [issuerId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("issuer_id") @ExcludeMissing fun _issuerId(): JsonField<String> = issuerId

    /**
     * Returns the raw JSON value of [issuerName].
     *
     * Unlike [issuerName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("issuer_name") @ExcludeMissing fun _issuerName(): JsonField<String> = issuerName

    /**
     * Returns the raw JSON value of [match].
     *
     * Unlike [match], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("match") @ExcludeMissing fun _match(): JsonField<FederationRuleMatch> = match

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [oauthScope].
     *
     * Unlike [oauthScope], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("oauth_scope") @ExcludeMissing fun _oauthScope(): JsonField<String> = oauthScope

    /**
     * Returns the raw JSON value of [target].
     *
     * Unlike [target], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<ServiceAccountTarget> = target

    /**
     * Returns the raw JSON value of [tokenLifetimeSeconds].
     *
     * Unlike [tokenLifetimeSeconds], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("token_lifetime_seconds")
    @ExcludeMissing
    fun _tokenLifetimeSeconds(): JsonField<Long> = tokenLifetimeSeconds

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

    /**
     * Returns the raw JSON value of [workspaceIds].
     *
     * Unlike [workspaceIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_ids")
    @ExcludeMissing
    fun _workspaceIds(): JsonField<List<String>> = workspaceIds

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
         * Returns a mutable builder for constructing an instance of [FederationRule].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .appliesToAllWorkspaces()
         * .archivedAt()
         * .archivedByActorId()
         * .createdAt()
         * .createdByActorId()
         * .description()
         * .issuerId()
         * .issuerName()
         * .match()
         * .name()
         * .oauthScope()
         * .target()
         * .tokenLifetimeSeconds()
         * .updatedAt()
         * .updatedByActorId()
         * .workspaceIds()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [FederationRule]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var appliesToAllWorkspaces: JsonField<Boolean>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var archivedByActorId: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var createdByActorId: JsonField<String>? = null
        private var description: JsonField<String>? = null
        private var issuerId: JsonField<String>? = null
        private var issuerName: JsonField<String>? = null
        private var match: JsonField<FederationRuleMatch>? = null
        private var name: JsonField<String>? = null
        private var oauthScope: JsonField<String>? = null
        private var target: JsonField<ServiceAccountTarget>? = null
        private var tokenLifetimeSeconds: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("federation_rule")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var updatedByActorId: JsonField<String>? = null
        private var workspaceIds: JsonField<MutableList<String>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(federationRule: FederationRule) = apply {
            id = federationRule.id
            appliesToAllWorkspaces = federationRule.appliesToAllWorkspaces
            archivedAt = federationRule.archivedAt
            archivedByActorId = federationRule.archivedByActorId
            createdAt = federationRule.createdAt
            createdByActorId = federationRule.createdByActorId
            description = federationRule.description
            issuerId = federationRule.issuerId
            issuerName = federationRule.issuerName
            match = federationRule.match
            name = federationRule.name
            oauthScope = federationRule.oauthScope
            target = federationRule.target
            tokenLifetimeSeconds = federationRule.tokenLifetimeSeconds
            type = federationRule.type
            updatedAt = federationRule.updatedAt
            updatedByActorId = federationRule.updatedByActorId
            workspaceIds =
                federationRule.workspaceIds.map { it.toMutableList() }.takeUnless { it.isMissing() }
            additionalProperties = federationRule.additionalProperties.toMutableMap()
        }

        /** Tagged ID of the federation rule. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * When true, this rule is enabled for every workspace in the org (including ones created
         * after the rule). `workspace_ids` is ignored at exchange time.
         */
        fun appliesToAllWorkspaces(appliesToAllWorkspaces: Boolean) =
            appliesToAllWorkspaces(JsonField.of(appliesToAllWorkspaces))

        /**
         * Sets [Builder.appliesToAllWorkspaces] to an arbitrary JSON value.
         *
         * You should usually call [Builder.appliesToAllWorkspaces] with a well-typed [Boolean]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun appliesToAllWorkspaces(appliesToAllWorkspaces: JsonField<Boolean>) = apply {
            this.appliesToAllWorkspaces = appliesToAllWorkspaces
        }

        /** If set, this rule is archived and rejects token exchange. */
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

        /** Tagged ID (`user_`/`svac_`) of the actor that archived this rule. */
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

        /** When this rule was created. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** Tagged ID (`user_`/`svac_`) of the actor that created this rule. */
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

        /** Tagged ID of the issuer whose tokens this rule accepts. */
        fun issuerId(issuerId: String) = issuerId(JsonField.of(issuerId))

        /**
         * Sets [Builder.issuerId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.issuerId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun issuerId(issuerId: JsonField<String>) = apply { this.issuerId = issuerId }

        /** Issuer's display name at read time. */
        fun issuerName(issuerName: String?) = issuerName(JsonField.ofNullable(issuerName))

        /** Alias for calling [Builder.issuerName] with `issuerName.orElse(null)`. */
        fun issuerName(issuerName: Optional<String>) = issuerName(issuerName.getOrNull())

        /**
         * Sets [Builder.issuerName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.issuerName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun issuerName(issuerName: JsonField<String>) = apply { this.issuerName = issuerName }

        /**
         * Conditions the verified JWT must satisfy for this rule to apply. All populated matcher
         * fields must pass.
         */
        fun match(match: FederationRuleMatch) = match(JsonField.of(match))

        /**
         * Sets [Builder.match] to an arbitrary JSON value.
         *
         * You should usually call [Builder.match] with a well-typed [FederationRuleMatch] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun match(match: JsonField<FederationRuleMatch>) = apply { this.match = match }

        /** Admin-chosen slug identifier. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** Space-separated OAuth scopes granted on the minted token. */
        fun oauthScope(oauthScope: String) = oauthScope(JsonField.of(oauthScope))

        /**
         * Sets [Builder.oauthScope] to an arbitrary JSON value.
         *
         * You should usually call [Builder.oauthScope] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun oauthScope(oauthScope: JsonField<String>) = apply { this.oauthScope = oauthScope }

        /**
         * What this rule targets. Check `type` before reading the other fields. Tokens minted via a
         * rule whose target `type` is `service_account` act as that service account.
         */
        fun target(target: ServiceAccountTarget) = target(JsonField.of(target))

        /**
         * Sets [Builder.target] to an arbitrary JSON value.
         *
         * You should usually call [Builder.target] with a well-typed [ServiceAccountTarget] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun target(target: JsonField<ServiceAccountTarget>) = apply { this.target = target }

        /**
         * Lifetime in seconds of access tokens minted via this rule. Minted tokens are capped at
         * `max(60, min(this value, 2 × remaining assertion validity))` seconds.
         */
        fun tokenLifetimeSeconds(tokenLifetimeSeconds: Long) =
            tokenLifetimeSeconds(JsonField.of(tokenLifetimeSeconds))

        /**
         * Sets [Builder.tokenLifetimeSeconds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tokenLifetimeSeconds] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun tokenLifetimeSeconds(tokenLifetimeSeconds: JsonField<Long>) = apply {
            this.tokenLifetimeSeconds = tokenLifetimeSeconds
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("federation_rule")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** When this rule was last updated. */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /** Tagged ID (`user_`/`svac_`) of the actor that last updated this rule. */
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

        /**
         * Tagged IDs of the workspaces this rule is enabled for. May be empty for older rules that
         * only carry the legacy `workspace_id` binding. Ignored at exchange time when
         * `applies_to_all_workspaces` is true (the list may still be non-empty).
         */
        fun workspaceIds(workspaceIds: List<String>) = workspaceIds(JsonField.of(workspaceIds))

        /**
         * Sets [Builder.workspaceIds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceIds] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun workspaceIds(workspaceIds: JsonField<List<String>>) = apply {
            this.workspaceIds = workspaceIds.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [workspaceIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addWorkspaceId(workspaceId: String) = apply {
            workspaceIds =
                (workspaceIds ?: JsonField.of(mutableListOf())).also {
                    checkKnown("workspaceIds", it).add(workspaceId)
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
         * Returns an immutable instance of [FederationRule].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .appliesToAllWorkspaces()
         * .archivedAt()
         * .archivedByActorId()
         * .createdAt()
         * .createdByActorId()
         * .description()
         * .issuerId()
         * .issuerName()
         * .match()
         * .name()
         * .oauthScope()
         * .target()
         * .tokenLifetimeSeconds()
         * .updatedAt()
         * .updatedByActorId()
         * .workspaceIds()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FederationRule =
            FederationRule(
                checkRequired("id", id),
                checkRequired("appliesToAllWorkspaces", appliesToAllWorkspaces),
                checkRequired("archivedAt", archivedAt),
                checkRequired("archivedByActorId", archivedByActorId),
                checkRequired("createdAt", createdAt),
                checkRequired("createdByActorId", createdByActorId),
                checkRequired("description", description),
                checkRequired("issuerId", issuerId),
                checkRequired("issuerName", issuerName),
                checkRequired("match", match),
                checkRequired("name", name),
                checkRequired("oauthScope", oauthScope),
                checkRequired("target", target),
                checkRequired("tokenLifetimeSeconds", tokenLifetimeSeconds),
                type,
                checkRequired("updatedAt", updatedAt),
                checkRequired("updatedByActorId", updatedByActorId),
                checkRequired("workspaceIds", workspaceIds).map { it.toImmutable() },
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
    fun validate(): FederationRule = apply {
        if (validated) {
            return@apply
        }

        id()
        appliesToAllWorkspaces()
        archivedAt()
        archivedByActorId()
        createdAt()
        createdByActorId()
        description()
        issuerId()
        issuerName()
        match().validate()
        name()
        oauthScope()
        target().validate()
        tokenLifetimeSeconds()
        _type().let {
            if (it != JsonValue.from("federation_rule")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
        updatedByActorId()
        workspaceIds()
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
            (if (appliesToAllWorkspaces.asKnown().isPresent) 1 else 0) +
            (if (archivedAt.asKnown().isPresent) 1 else 0) +
            (if (archivedByActorId.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (createdByActorId.asKnown().isPresent) 1 else 0) +
            (if (description.asKnown().isPresent) 1 else 0) +
            (if (issuerId.asKnown().isPresent) 1 else 0) +
            (if (issuerName.asKnown().isPresent) 1 else 0) +
            (match.asKnown().getOrNull()?.validity() ?: 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (if (oauthScope.asKnown().isPresent) 1 else 0) +
            (target.asKnown().getOrNull()?.validity() ?: 0) +
            (if (tokenLifetimeSeconds.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("federation_rule")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (updatedByActorId.asKnown().isPresent) 1 else 0) +
            (workspaceIds.asKnown().getOrNull()?.size ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FederationRule &&
            id == other.id &&
            appliesToAllWorkspaces == other.appliesToAllWorkspaces &&
            archivedAt == other.archivedAt &&
            archivedByActorId == other.archivedByActorId &&
            createdAt == other.createdAt &&
            createdByActorId == other.createdByActorId &&
            description == other.description &&
            issuerId == other.issuerId &&
            issuerName == other.issuerName &&
            match == other.match &&
            name == other.name &&
            oauthScope == other.oauthScope &&
            target == other.target &&
            tokenLifetimeSeconds == other.tokenLifetimeSeconds &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            updatedByActorId == other.updatedByActorId &&
            workspaceIds == other.workspaceIds &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            appliesToAllWorkspaces,
            archivedAt,
            archivedByActorId,
            createdAt,
            createdByActorId,
            description,
            issuerId,
            issuerName,
            match,
            name,
            oauthScope,
            target,
            tokenLifetimeSeconds,
            type,
            updatedAt,
            updatedByActorId,
            workspaceIds,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FederationRule{id=$id, appliesToAllWorkspaces=$appliesToAllWorkspaces, archivedAt=$archivedAt, archivedByActorId=$archivedByActorId, createdAt=$createdAt, createdByActorId=$createdByActorId, description=$description, issuerId=$issuerId, issuerName=$issuerName, match=$match, name=$name, oauthScope=$oauthScope, target=$target, tokenLifetimeSeconds=$tokenLifetimeSeconds, type=$type, updatedAt=$updatedAt, updatedByActorId=$updatedByActorId, workspaceIds=$workspaceIds, additionalProperties=$additionalProperties}"
}
