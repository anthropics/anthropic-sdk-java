// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
 * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
 * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
 *
 * Partially update a federation rule.
 *
 * `issuer_id` is immutable. `match` and `target` are replaced as whole objects when set. Referenced
 * service accounts and workspaces must exist in your organization; invalid references are rejected
 * with a 400 error. Archived rules cannot be updated; this returns 400. Create a new rule instead.
 * Rules on well-known shared issuers (GitHub Actions, GitLab, Buildkite, Terraform Cloud, Google)
 * must constrain tenant identity via an identity-bearing claim, a tenant-pinning subject prefix
 * (such as `repo:YOUR_ORG/...`), or a CEL condition referencing one of those identity claims (e.g.
 * `claims.repository_owner`). On these issuers the requirement is re-checked on every update; if an
 * existing rule's stored match does not yet constrain tenant identity, any update (even a rename or
 * description change) must also supply a conforming `match` in the same request. OAuth callers may
 * only manage rules whose `oauth_scope` is `workspace:developer` or `workspace:inference`; other
 * scopes require a Console session.
 */
class RuleUpdateParams
private constructor(
    private val federationRuleId: String?,
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** ID of the federation rule to update. */
    fun federationRuleId(): Optional<String> = Optional.ofNullable(federationRuleId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * When true, enables this rule for every workspace in the org (including workspaces created
     * later). Setting `false` is rejected with 400 if no workspace would remain enabled; a rule
     * with only a legacy `workspace_id` binding continues to mint.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun appliesToAllWorkspaces(): Optional<Boolean> = body.appliesToAllWorkspaces()

    /**
     * Replaces the CEL expressions `{name: expr}` extracting named values from claims. Send null to
     * clear them. Not yet supported; any non-empty value is rejected with 400.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun attributes(): Optional<Attributes> = body.attributes()

    /**
     * Replaces the description. Omit to leave unchanged; send `null` to clear (the field is stored
     * as an empty string).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun description(): Optional<String> = body.description()

    /**
     * Does the incoming JWT qualify?
     *
     * All populated fields must pass; omitted fields are skipped. At least one of `subject_prefix`
     * (other than a wildcard-only value like `*`), `claims`, or `condition` is required; `audience`
     * alone is not sufficient.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun match(): Optional<BetaFederationRuleMatch> = body.match()

    /**
     * Replaces the slug identifier (lowercase, digits, hyphens). Unique within the organization; a
     * duplicate name returns 409.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun name(): Optional<String> = body.name()

    /**
     * Replaces the space-separated OAuth scopes granted on minted tokens. OAuth callers may only
     * set `workspace:developer` or `workspace:inference`; other scopes (such as `org:admin`)
     * require a Console session.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun oauthScope(): Optional<String> = body.oauthScope()

    /**
     * Bind to a fixed service account by ID.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun target(): Optional<BetaServiceAccountTarget> = body.target()

    /**
     * Replaces the lifetime in seconds for access tokens minted via this rule (60-86400). Minted
     * tokens are capped at `max(60, min(this value, 2 × remaining assertion validity))` seconds.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tokenLifetimeSeconds(): Optional<Long> = body.tokenLifetimeSeconds()

    /**
     * Replaces the existing single workspace enablement (the previous one is removed). Rejected
     * with 400 if the rule is enabled for more than one workspace; use the
     * `/federation_rules/{federation_rule_id}/workspaces` sub-resource instead.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun workspaceId(): Optional<String> = body.workspaceId()

    /**
     * Returns the raw JSON value of [appliesToAllWorkspaces].
     *
     * Unlike [appliesToAllWorkspaces], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    fun _appliesToAllWorkspaces(): JsonField<Boolean> = body._appliesToAllWorkspaces()

    /**
     * Returns the raw JSON value of [attributes].
     *
     * Unlike [attributes], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _attributes(): JsonField<Attributes> = body._attributes()

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _description(): JsonField<String> = body._description()

    /**
     * Returns the raw JSON value of [match].
     *
     * Unlike [match], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _match(): JsonField<BetaFederationRuleMatch> = body._match()

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _name(): JsonField<String> = body._name()

    /**
     * Returns the raw JSON value of [oauthScope].
     *
     * Unlike [oauthScope], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _oauthScope(): JsonField<String> = body._oauthScope()

    /**
     * Returns the raw JSON value of [target].
     *
     * Unlike [target], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _target(): JsonField<BetaServiceAccountTarget> = body._target()

    /**
     * Returns the raw JSON value of [tokenLifetimeSeconds].
     *
     * Unlike [tokenLifetimeSeconds], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _tokenLifetimeSeconds(): JsonField<Long> = body._tokenLifetimeSeconds()

    /**
     * Returns the raw JSON value of [workspaceId].
     *
     * Unlike [workspaceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _workspaceId(): JsonField<String> = body._workspaceId()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): RuleUpdateParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [RuleUpdateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [RuleUpdateParams]. */
    class Builder internal constructor() {

        private var federationRuleId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(ruleUpdateParams: RuleUpdateParams) = apply {
            federationRuleId = ruleUpdateParams.federationRuleId
            betas = ruleUpdateParams.betas?.toMutableList()
            body = ruleUpdateParams.body.toBuilder()
            additionalHeaders = ruleUpdateParams.additionalHeaders.toBuilder()
            additionalQueryParams = ruleUpdateParams.additionalQueryParams.toBuilder()
        }

        /** ID of the federation rule to update. */
        fun federationRuleId(federationRuleId: String?) = apply {
            this.federationRuleId = federationRuleId
        }

        /** Alias for calling [Builder.federationRuleId] with `federationRuleId.orElse(null)`. */
        fun federationRuleId(federationRuleId: Optional<String>) =
            federationRuleId(federationRuleId.getOrNull())

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [appliesToAllWorkspaces]
         * - [attributes]
         * - [description]
         * - [match]
         * - [name]
         * - etc.
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * When true, enables this rule for every workspace in the org (including workspaces created
         * later). Setting `false` is rejected with 400 if no workspace would remain enabled; a rule
         * with only a legacy `workspace_id` binding continues to mint.
         */
        fun appliesToAllWorkspaces(appliesToAllWorkspaces: Boolean?) = apply {
            body.appliesToAllWorkspaces(appliesToAllWorkspaces)
        }

        /**
         * Alias for [Builder.appliesToAllWorkspaces].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun appliesToAllWorkspaces(appliesToAllWorkspaces: Boolean) =
            appliesToAllWorkspaces(appliesToAllWorkspaces as Boolean?)

        /**
         * Alias for calling [Builder.appliesToAllWorkspaces] with
         * `appliesToAllWorkspaces.orElse(null)`.
         */
        fun appliesToAllWorkspaces(appliesToAllWorkspaces: Optional<Boolean>) =
            appliesToAllWorkspaces(appliesToAllWorkspaces.getOrNull())

        /**
         * Sets [Builder.appliesToAllWorkspaces] to an arbitrary JSON value.
         *
         * You should usually call [Builder.appliesToAllWorkspaces] with a well-typed [Boolean]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun appliesToAllWorkspaces(appliesToAllWorkspaces: JsonField<Boolean>) = apply {
            body.appliesToAllWorkspaces(appliesToAllWorkspaces)
        }

        /**
         * Replaces the CEL expressions `{name: expr}` extracting named values from claims. Send
         * null to clear them. Not yet supported; any non-empty value is rejected with 400.
         */
        fun attributes(attributes: Attributes?) = apply { body.attributes(attributes) }

        /** Alias for calling [Builder.attributes] with `attributes.orElse(null)`. */
        fun attributes(attributes: Optional<Attributes>) = attributes(attributes.getOrNull())

        /**
         * Sets [Builder.attributes] to an arbitrary JSON value.
         *
         * You should usually call [Builder.attributes] with a well-typed [Attributes] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun attributes(attributes: JsonField<Attributes>) = apply { body.attributes(attributes) }

        /**
         * Replaces the description. Omit to leave unchanged; send `null` to clear (the field is
         * stored as an empty string).
         */
        fun description(description: String?) = apply { body.description(description) }

        /** Alias for calling [Builder.description] with `description.orElse(null)`. */
        fun description(description: Optional<String>) = description(description.getOrNull())

        /**
         * Sets [Builder.description] to an arbitrary JSON value.
         *
         * You should usually call [Builder.description] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun description(description: JsonField<String>) = apply { body.description(description) }

        /**
         * Does the incoming JWT qualify?
         *
         * All populated fields must pass; omitted fields are skipped. At least one of
         * `subject_prefix` (other than a wildcard-only value like `*`), `claims`, or `condition` is
         * required; `audience` alone is not sufficient.
         */
        fun match(match: BetaFederationRuleMatch?) = apply { body.match(match) }

        /** Alias for calling [Builder.match] with `match.orElse(null)`. */
        fun match(match: Optional<BetaFederationRuleMatch>) = match(match.getOrNull())

        /**
         * Sets [Builder.match] to an arbitrary JSON value.
         *
         * You should usually call [Builder.match] with a well-typed [BetaFederationRuleMatch] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun match(match: JsonField<BetaFederationRuleMatch>) = apply { body.match(match) }

        /**
         * Replaces the slug identifier (lowercase, digits, hyphens). Unique within the
         * organization; a duplicate name returns 409.
         */
        fun name(name: String?) = apply { body.name(name) }

        /** Alias for calling [Builder.name] with `name.orElse(null)`. */
        fun name(name: Optional<String>) = name(name.getOrNull())

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { body.name(name) }

        /**
         * Replaces the space-separated OAuth scopes granted on minted tokens. OAuth callers may
         * only set `workspace:developer` or `workspace:inference`; other scopes (such as
         * `org:admin`) require a Console session.
         */
        fun oauthScope(oauthScope: String?) = apply { body.oauthScope(oauthScope) }

        /** Alias for calling [Builder.oauthScope] with `oauthScope.orElse(null)`. */
        fun oauthScope(oauthScope: Optional<String>) = oauthScope(oauthScope.getOrNull())

        /**
         * Sets [Builder.oauthScope] to an arbitrary JSON value.
         *
         * You should usually call [Builder.oauthScope] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun oauthScope(oauthScope: JsonField<String>) = apply { body.oauthScope(oauthScope) }

        /** Bind to a fixed service account by ID. */
        fun target(target: BetaServiceAccountTarget?) = apply { body.target(target) }

        /** Alias for calling [Builder.target] with `target.orElse(null)`. */
        fun target(target: Optional<BetaServiceAccountTarget>) = target(target.getOrNull())

        /**
         * Sets [Builder.target] to an arbitrary JSON value.
         *
         * You should usually call [Builder.target] with a well-typed [BetaServiceAccountTarget]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun target(target: JsonField<BetaServiceAccountTarget>) = apply { body.target(target) }

        /**
         * Replaces the lifetime in seconds for access tokens minted via this rule (60-86400).
         * Minted tokens are capped at `max(60, min(this value, 2 × remaining assertion validity))`
         * seconds.
         */
        fun tokenLifetimeSeconds(tokenLifetimeSeconds: Long?) = apply {
            body.tokenLifetimeSeconds(tokenLifetimeSeconds)
        }

        /**
         * Alias for [Builder.tokenLifetimeSeconds].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun tokenLifetimeSeconds(tokenLifetimeSeconds: Long) =
            tokenLifetimeSeconds(tokenLifetimeSeconds as Long?)

        /**
         * Alias for calling [Builder.tokenLifetimeSeconds] with
         * `tokenLifetimeSeconds.orElse(null)`.
         */
        fun tokenLifetimeSeconds(tokenLifetimeSeconds: Optional<Long>) =
            tokenLifetimeSeconds(tokenLifetimeSeconds.getOrNull())

        /**
         * Sets [Builder.tokenLifetimeSeconds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tokenLifetimeSeconds] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun tokenLifetimeSeconds(tokenLifetimeSeconds: JsonField<Long>) = apply {
            body.tokenLifetimeSeconds(tokenLifetimeSeconds)
        }

        /**
         * Replaces the existing single workspace enablement (the previous one is removed). Rejected
         * with 400 if the rule is enabled for more than one workspace; use the
         * `/federation_rules/{federation_rule_id}/workspaces` sub-resource instead.
         */
        fun workspaceId(workspaceId: String?) = apply { body.workspaceId(workspaceId) }

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

        /**
         * Sets [Builder.workspaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workspaceId(workspaceId: JsonField<String>) = apply { body.workspaceId(workspaceId) }

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
         * Returns an immutable instance of [RuleUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): RuleUpdateParams =
            RuleUpdateParams(
                federationRuleId,
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> federationRuleId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    /**
     * Partial update. Set fields are applied; omitted fields are unchanged.
     *
     * `issuer_id` is immutable. `match` and `target` are replaced as whole objects when set.
     * Explicit `null` clears `description` and `attributes`; other fields can only be replaced, not
     * cleared.
     */
    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val appliesToAllWorkspaces: JsonField<Boolean>,
        private val attributes: JsonField<Attributes>,
        private val description: JsonField<String>,
        private val match: JsonField<BetaFederationRuleMatch>,
        private val name: JsonField<String>,
        private val oauthScope: JsonField<String>,
        private val target: JsonField<BetaServiceAccountTarget>,
        private val tokenLifetimeSeconds: JsonField<Long>,
        private val workspaceId: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("applies_to_all_workspaces")
            @ExcludeMissing
            appliesToAllWorkspaces: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("attributes")
            @ExcludeMissing
            attributes: JsonField<Attributes> = JsonMissing.of(),
            @JsonProperty("description")
            @ExcludeMissing
            description: JsonField<String> = JsonMissing.of(),
            @JsonProperty("match")
            @ExcludeMissing
            match: JsonField<BetaFederationRuleMatch> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("oauth_scope")
            @ExcludeMissing
            oauthScope: JsonField<String> = JsonMissing.of(),
            @JsonProperty("target")
            @ExcludeMissing
            target: JsonField<BetaServiceAccountTarget> = JsonMissing.of(),
            @JsonProperty("token_lifetime_seconds")
            @ExcludeMissing
            tokenLifetimeSeconds: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("workspace_id")
            @ExcludeMissing
            workspaceId: JsonField<String> = JsonMissing.of(),
        ) : this(
            appliesToAllWorkspaces,
            attributes,
            description,
            match,
            name,
            oauthScope,
            target,
            tokenLifetimeSeconds,
            workspaceId,
            mutableMapOf(),
        )

        /**
         * When true, enables this rule for every workspace in the org (including workspaces created
         * later). Setting `false` is rejected with 400 if no workspace would remain enabled; a rule
         * with only a legacy `workspace_id` binding continues to mint.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun appliesToAllWorkspaces(): Optional<Boolean> =
            appliesToAllWorkspaces.getOptional("applies_to_all_workspaces")

        /**
         * Replaces the CEL expressions `{name: expr}` extracting named values from claims. Send
         * null to clear them. Not yet supported; any non-empty value is rejected with 400.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun attributes(): Optional<Attributes> = attributes.getOptional("attributes")

        /**
         * Replaces the description. Omit to leave unchanged; send `null` to clear (the field is
         * stored as an empty string).
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun description(): Optional<String> = description.getOptional("description")

        /**
         * Does the incoming JWT qualify?
         *
         * All populated fields must pass; omitted fields are skipped. At least one of
         * `subject_prefix` (other than a wildcard-only value like `*`), `claims`, or `condition` is
         * required; `audience` alone is not sufficient.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun match(): Optional<BetaFederationRuleMatch> = match.getOptional("match")

        /**
         * Replaces the slug identifier (lowercase, digits, hyphens). Unique within the
         * organization; a duplicate name returns 409.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun name(): Optional<String> = name.getOptional("name")

        /**
         * Replaces the space-separated OAuth scopes granted on minted tokens. OAuth callers may
         * only set `workspace:developer` or `workspace:inference`; other scopes (such as
         * `org:admin`) require a Console session.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun oauthScope(): Optional<String> = oauthScope.getOptional("oauth_scope")

        /**
         * Bind to a fixed service account by ID.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun target(): Optional<BetaServiceAccountTarget> = target.getOptional("target")

        /**
         * Replaces the lifetime in seconds for access tokens minted via this rule (60-86400).
         * Minted tokens are capped at `max(60, min(this value, 2 × remaining assertion validity))`
         * seconds.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun tokenLifetimeSeconds(): Optional<Long> =
            tokenLifetimeSeconds.getOptional("token_lifetime_seconds")

        /**
         * Replaces the existing single workspace enablement (the previous one is removed). Rejected
         * with 400 if the rule is enabled for more than one workspace; use the
         * `/federation_rules/{federation_rule_id}/workspaces` sub-resource instead.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun workspaceId(): Optional<String> = workspaceId.getOptional("workspace_id")

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
         * Returns the raw JSON value of [attributes].
         *
         * Unlike [attributes], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("attributes")
        @ExcludeMissing
        fun _attributes(): JsonField<Attributes> = attributes

        /**
         * Returns the raw JSON value of [description].
         *
         * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("description")
        @ExcludeMissing
        fun _description(): JsonField<String> = description

        /**
         * Returns the raw JSON value of [match].
         *
         * Unlike [match], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("match")
        @ExcludeMissing
        fun _match(): JsonField<BetaFederationRuleMatch> = match

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
        @JsonProperty("oauth_scope")
        @ExcludeMissing
        fun _oauthScope(): JsonField<String> = oauthScope

        /**
         * Returns the raw JSON value of [target].
         *
         * Unlike [target], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("target")
        @ExcludeMissing
        fun _target(): JsonField<BetaServiceAccountTarget> = target

        /**
         * Returns the raw JSON value of [tokenLifetimeSeconds].
         *
         * Unlike [tokenLifetimeSeconds], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("token_lifetime_seconds")
        @ExcludeMissing
        fun _tokenLifetimeSeconds(): JsonField<Long> = tokenLifetimeSeconds

        /**
         * Returns the raw JSON value of [workspaceId].
         *
         * Unlike [workspaceId], this method doesn't throw if the JSON field has an unexpected type.
         */
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

            /** Returns a mutable builder for constructing an instance of [Body]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var appliesToAllWorkspaces: JsonField<Boolean> = JsonMissing.of()
            private var attributes: JsonField<Attributes> = JsonMissing.of()
            private var description: JsonField<String> = JsonMissing.of()
            private var match: JsonField<BetaFederationRuleMatch> = JsonMissing.of()
            private var name: JsonField<String> = JsonMissing.of()
            private var oauthScope: JsonField<String> = JsonMissing.of()
            private var target: JsonField<BetaServiceAccountTarget> = JsonMissing.of()
            private var tokenLifetimeSeconds: JsonField<Long> = JsonMissing.of()
            private var workspaceId: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                appliesToAllWorkspaces = body.appliesToAllWorkspaces
                attributes = body.attributes
                description = body.description
                match = body.match
                name = body.name
                oauthScope = body.oauthScope
                target = body.target
                tokenLifetimeSeconds = body.tokenLifetimeSeconds
                workspaceId = body.workspaceId
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * When true, enables this rule for every workspace in the org (including workspaces
             * created later). Setting `false` is rejected with 400 if no workspace would remain
             * enabled; a rule with only a legacy `workspace_id` binding continues to mint.
             */
            fun appliesToAllWorkspaces(appliesToAllWorkspaces: Boolean?) =
                appliesToAllWorkspaces(JsonField.ofNullable(appliesToAllWorkspaces))

            /**
             * Alias for [Builder.appliesToAllWorkspaces].
             *
             * This unboxed primitive overload exists for backwards compatibility.
             */
            fun appliesToAllWorkspaces(appliesToAllWorkspaces: Boolean) =
                appliesToAllWorkspaces(appliesToAllWorkspaces as Boolean?)

            /**
             * Alias for calling [Builder.appliesToAllWorkspaces] with
             * `appliesToAllWorkspaces.orElse(null)`.
             */
            fun appliesToAllWorkspaces(appliesToAllWorkspaces: Optional<Boolean>) =
                appliesToAllWorkspaces(appliesToAllWorkspaces.getOrNull())

            /**
             * Sets [Builder.appliesToAllWorkspaces] to an arbitrary JSON value.
             *
             * You should usually call [Builder.appliesToAllWorkspaces] with a well-typed [Boolean]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun appliesToAllWorkspaces(appliesToAllWorkspaces: JsonField<Boolean>) = apply {
                this.appliesToAllWorkspaces = appliesToAllWorkspaces
            }

            /**
             * Replaces the CEL expressions `{name: expr}` extracting named values from claims. Send
             * null to clear them. Not yet supported; any non-empty value is rejected with 400.
             */
            fun attributes(attributes: Attributes?) = attributes(JsonField.ofNullable(attributes))

            /** Alias for calling [Builder.attributes] with `attributes.orElse(null)`. */
            fun attributes(attributes: Optional<Attributes>) = attributes(attributes.getOrNull())

            /**
             * Sets [Builder.attributes] to an arbitrary JSON value.
             *
             * You should usually call [Builder.attributes] with a well-typed [Attributes] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun attributes(attributes: JsonField<Attributes>) = apply {
                this.attributes = attributes
            }

            /**
             * Replaces the description. Omit to leave unchanged; send `null` to clear (the field is
             * stored as an empty string).
             */
            fun description(description: String?) = description(JsonField.ofNullable(description))

            /** Alias for calling [Builder.description] with `description.orElse(null)`. */
            fun description(description: Optional<String>) = description(description.getOrNull())

            /**
             * Sets [Builder.description] to an arbitrary JSON value.
             *
             * You should usually call [Builder.description] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun description(description: JsonField<String>) = apply {
                this.description = description
            }

            /**
             * Does the incoming JWT qualify?
             *
             * All populated fields must pass; omitted fields are skipped. At least one of
             * `subject_prefix` (other than a wildcard-only value like `*`), `claims`, or
             * `condition` is required; `audience` alone is not sufficient.
             */
            fun match(match: BetaFederationRuleMatch?) = match(JsonField.ofNullable(match))

            /** Alias for calling [Builder.match] with `match.orElse(null)`. */
            fun match(match: Optional<BetaFederationRuleMatch>) = match(match.getOrNull())

            /**
             * Sets [Builder.match] to an arbitrary JSON value.
             *
             * You should usually call [Builder.match] with a well-typed [BetaFederationRuleMatch]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun match(match: JsonField<BetaFederationRuleMatch>) = apply { this.match = match }

            /**
             * Replaces the slug identifier (lowercase, digits, hyphens). Unique within the
             * organization; a duplicate name returns 409.
             */
            fun name(name: String?) = name(JsonField.ofNullable(name))

            /** Alias for calling [Builder.name] with `name.orElse(null)`. */
            fun name(name: Optional<String>) = name(name.getOrNull())

            /**
             * Sets [Builder.name] to an arbitrary JSON value.
             *
             * You should usually call [Builder.name] with a well-typed [String] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun name(name: JsonField<String>) = apply { this.name = name }

            /**
             * Replaces the space-separated OAuth scopes granted on minted tokens. OAuth callers may
             * only set `workspace:developer` or `workspace:inference`; other scopes (such as
             * `org:admin`) require a Console session.
             */
            fun oauthScope(oauthScope: String?) = oauthScope(JsonField.ofNullable(oauthScope))

            /** Alias for calling [Builder.oauthScope] with `oauthScope.orElse(null)`. */
            fun oauthScope(oauthScope: Optional<String>) = oauthScope(oauthScope.getOrNull())

            /**
             * Sets [Builder.oauthScope] to an arbitrary JSON value.
             *
             * You should usually call [Builder.oauthScope] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun oauthScope(oauthScope: JsonField<String>) = apply { this.oauthScope = oauthScope }

            /** Bind to a fixed service account by ID. */
            fun target(target: BetaServiceAccountTarget?) = target(JsonField.ofNullable(target))

            /** Alias for calling [Builder.target] with `target.orElse(null)`. */
            fun target(target: Optional<BetaServiceAccountTarget>) = target(target.getOrNull())

            /**
             * Sets [Builder.target] to an arbitrary JSON value.
             *
             * You should usually call [Builder.target] with a well-typed [BetaServiceAccountTarget]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun target(target: JsonField<BetaServiceAccountTarget>) = apply { this.target = target }

            /**
             * Replaces the lifetime in seconds for access tokens minted via this rule (60-86400).
             * Minted tokens are capped at `max(60, min(this value, 2 × remaining assertion
             * validity))` seconds.
             */
            fun tokenLifetimeSeconds(tokenLifetimeSeconds: Long?) =
                tokenLifetimeSeconds(JsonField.ofNullable(tokenLifetimeSeconds))

            /**
             * Alias for [Builder.tokenLifetimeSeconds].
             *
             * This unboxed primitive overload exists for backwards compatibility.
             */
            fun tokenLifetimeSeconds(tokenLifetimeSeconds: Long) =
                tokenLifetimeSeconds(tokenLifetimeSeconds as Long?)

            /**
             * Alias for calling [Builder.tokenLifetimeSeconds] with
             * `tokenLifetimeSeconds.orElse(null)`.
             */
            fun tokenLifetimeSeconds(tokenLifetimeSeconds: Optional<Long>) =
                tokenLifetimeSeconds(tokenLifetimeSeconds.getOrNull())

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
             * Replaces the existing single workspace enablement (the previous one is removed).
             * Rejected with 400 if the rule is enabled for more than one workspace; use the
             * `/federation_rules/{federation_rule_id}/workspaces` sub-resource instead.
             */
            fun workspaceId(workspaceId: String?) = workspaceId(JsonField.ofNullable(workspaceId))

            /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
            fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

            /**
             * Sets [Builder.workspaceId] to an arbitrary JSON value.
             *
             * You should usually call [Builder.workspaceId] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun workspaceId(workspaceId: JsonField<String>) = apply {
                this.workspaceId = workspaceId
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
             */
            fun build(): Body =
                Body(
                    appliesToAllWorkspaces,
                    attributes,
                    description,
                    match,
                    name,
                    oauthScope,
                    target,
                    tokenLifetimeSeconds,
                    workspaceId,
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

            appliesToAllWorkspaces()
            attributes().ifPresent { it.validate() }
            description()
            match().ifPresent { it.validate() }
            name()
            oauthScope()
            target().ifPresent { it.validate() }
            tokenLifetimeSeconds()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            (if (appliesToAllWorkspaces.asKnown().isPresent) 1 else 0) +
                (attributes.asKnown().getOrNull()?.validity() ?: 0) +
                (if (description.asKnown().isPresent) 1 else 0) +
                (match.asKnown().getOrNull()?.validity() ?: 0) +
                (if (name.asKnown().isPresent) 1 else 0) +
                (if (oauthScope.asKnown().isPresent) 1 else 0) +
                (target.asKnown().getOrNull()?.validity() ?: 0) +
                (if (tokenLifetimeSeconds.asKnown().isPresent) 1 else 0) +
                (if (workspaceId.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                appliesToAllWorkspaces == other.appliesToAllWorkspaces &&
                attributes == other.attributes &&
                description == other.description &&
                match == other.match &&
                name == other.name &&
                oauthScope == other.oauthScope &&
                target == other.target &&
                tokenLifetimeSeconds == other.tokenLifetimeSeconds &&
                workspaceId == other.workspaceId &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                appliesToAllWorkspaces,
                attributes,
                description,
                match,
                name,
                oauthScope,
                target,
                tokenLifetimeSeconds,
                workspaceId,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{appliesToAllWorkspaces=$appliesToAllWorkspaces, attributes=$attributes, description=$description, match=$match, name=$name, oauthScope=$oauthScope, target=$target, tokenLifetimeSeconds=$tokenLifetimeSeconds, workspaceId=$workspaceId, additionalProperties=$additionalProperties}"
    }

    /**
     * Replaces the CEL expressions `{name: expr}` extracting named values from claims. Send null to
     * clear them. Not yet supported; any non-empty value is rejected with 400.
     */
    class Attributes
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

            /** Returns a mutable builder for constructing an instance of [Attributes]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Attributes]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(attributes: Attributes) = apply {
                additionalProperties = attributes.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Attributes].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Attributes = Attributes(additionalProperties.toImmutable())
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
        fun validate(): Attributes = apply {
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

            return other is Attributes && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Attributes{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RuleUpdateParams &&
            federationRuleId == other.federationRuleId &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(federationRuleId, betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "RuleUpdateParams{federationRuleId=$federationRuleId, betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
