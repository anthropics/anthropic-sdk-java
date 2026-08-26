// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.federation

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.federation.rules.BetaFederationRule
import com.anthropic.models.beta.organization.federation.rules.RuleArchiveParams
import com.anthropic.models.beta.organization.federation.rules.RuleCreateParams
import com.anthropic.models.beta.organization.federation.rules.RuleListPageAsync
import com.anthropic.models.beta.organization.federation.rules.RuleListParams
import com.anthropic.models.beta.organization.federation.rules.RuleRetrieveParams
import com.anthropic.models.beta.organization.federation.rules.RuleUpdateParams
import com.anthropic.services.async.beta.organization.federation.rules.WorkspaceServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface RuleServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): RuleServiceAsync

    fun workspaces(): WorkspaceServiceAsync

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Create a federation rule owned by your organization.
     *
     * The referenced issuer and the target service account must already exist in the same
     * organization; invalid references are rejected with a 400 error. The workspace reference is
     * validated. Membership is not checked at rule creation: token exchange resolves a single
     * enabled workspace per call and is rejected unless the target service account is a member of
     * that workspace (it is implicitly a member of the default workspace). Rules on well-known
     * shared issuers (GitHub Actions, GitLab, Buildkite, Terraform Cloud, Google) must constrain
     * tenant identity via an identity-bearing claim, a tenant-pinning subject prefix (such as
     * `repo:YOUR_ORG/...`), or a CEL condition referencing one of those identity claims (e.g.
     * `claims.repository_owner`). OAuth callers may only manage rules whose `oauth_scope` is
     * `workspace:developer` or `workspace:inference`; other scopes require a Console session.
     */
    fun create(params: RuleCreateParams): CompletableFuture<BetaFederationRule> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: RuleCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationRule>

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Retrieve a federation rule by its ID (`fdrl_...`).
     */
    fun retrieve(federationRuleId: String): CompletableFuture<BetaFederationRule> =
        retrieve(federationRuleId, RuleRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        federationRuleId: String,
        params: RuleRetrieveParams = RuleRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationRule> =
        retrieve(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        federationRuleId: String,
        params: RuleRetrieveParams = RuleRetrieveParams.none(),
    ): CompletableFuture<BetaFederationRule> =
        retrieve(federationRuleId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: RuleRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationRule>

    /** @see retrieve */
    fun retrieve(params: RuleRetrieveParams): CompletableFuture<BetaFederationRule> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        federationRuleId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaFederationRule> =
        retrieve(federationRuleId, RuleRetrieveParams.none(), requestOptions)

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Partially update a federation rule.
     *
     * `issuer_id` is immutable. `match` and `target` are replaced as whole objects when set.
     * Referenced service accounts and workspaces must exist in your organization; invalid
     * references are rejected with a 400 error. Archived rules cannot be updated; this returns 400.
     * Create a new rule instead. Rules on well-known shared issuers (GitHub Actions, GitLab,
     * Buildkite, Terraform Cloud, Google) must constrain tenant identity via an identity-bearing
     * claim, a tenant-pinning subject prefix (such as `repo:YOUR_ORG/...`), or a CEL condition
     * referencing one of those identity claims (e.g. `claims.repository_owner`). On these issuers
     * the requirement is re-checked on every update; if an existing rule's stored match does not
     * yet constrain tenant identity, any update (even a rename or description change) must also
     * supply a conforming `match` in the same request. OAuth callers may only manage rules whose
     * `oauth_scope` is `workspace:developer` or `workspace:inference`; other scopes require a
     * Console session.
     */
    fun update(federationRuleId: String): CompletableFuture<BetaFederationRule> =
        update(federationRuleId, RuleUpdateParams.none())

    /** @see update */
    fun update(
        federationRuleId: String,
        params: RuleUpdateParams = RuleUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationRule> =
        update(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

    /** @see update */
    fun update(
        federationRuleId: String,
        params: RuleUpdateParams = RuleUpdateParams.none(),
    ): CompletableFuture<BetaFederationRule> =
        update(federationRuleId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: RuleUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationRule>

    /** @see update */
    fun update(params: RuleUpdateParams): CompletableFuture<BetaFederationRule> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        federationRuleId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaFederationRule> =
        update(federationRuleId, RuleUpdateParams.none(), requestOptions)

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * List federation rules in your organization.
     *
     * Optionally filter by issuer with `issuer_id`. Archived rules are excluded unless
     * `include_archived=true`.
     */
    fun list(): CompletableFuture<RuleListPageAsync> = list(RuleListParams.none())

    /** @see list */
    fun list(
        params: RuleListParams = RuleListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<RuleListPageAsync>

    /** @see list */
    fun list(params: RuleListParams = RuleListParams.none()): CompletableFuture<RuleListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<RuleListPageAsync> =
        list(RuleListParams.none(), requestOptions)

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Archive a federation rule.
     *
     * Token exchange through this rule stops immediately. Idempotent; re-archiving returns the rule
     * with its original `archived_at`. Archiving clears the rule's workspace targeting
     * (`workspace_id` and `workspace_ids` are emptied). Tokens already minted before archive remain
     * valid until they expire. OAuth callers may only manage rules whose `oauth_scope` is
     * `workspace:developer` or `workspace:inference`; other scopes require a Console session.
     */
    fun archive(federationRuleId: String): CompletableFuture<BetaFederationRule> =
        archive(federationRuleId, RuleArchiveParams.none())

    /** @see archive */
    fun archive(
        federationRuleId: String,
        params: RuleArchiveParams = RuleArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationRule> =
        archive(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

    /** @see archive */
    fun archive(
        federationRuleId: String,
        params: RuleArchiveParams = RuleArchiveParams.none(),
    ): CompletableFuture<BetaFederationRule> =
        archive(federationRuleId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: RuleArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationRule>

    /** @see archive */
    fun archive(params: RuleArchiveParams): CompletableFuture<BetaFederationRule> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        federationRuleId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaFederationRule> =
        archive(federationRuleId, RuleArchiveParams.none(), requestOptions)

    /** A view of [RuleServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): RuleServiceAsync.WithRawResponse

        fun workspaces(): WorkspaceServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/federation_rules?beta=true`, but
         * is otherwise the same as [RuleServiceAsync.create].
         */
        fun create(
            params: RuleCreateParams
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: RuleCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/federation_rules/{federation_rule_id}?beta=true`, but is otherwise the
         * same as [RuleServiceAsync.retrieve].
         */
        fun retrieve(
            federationRuleId: String
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            retrieve(federationRuleId, RuleRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            federationRuleId: String,
            params: RuleRetrieveParams = RuleRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            retrieve(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            federationRuleId: String,
            params: RuleRetrieveParams = RuleRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            retrieve(federationRuleId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: RuleRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>>

        /** @see retrieve */
        fun retrieve(
            params: RuleRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            federationRuleId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            retrieve(federationRuleId, RuleRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/federation_rules/{federation_rule_id}?beta=true`, but is otherwise the
         * same as [RuleServiceAsync.update].
         */
        fun update(
            federationRuleId: String
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            update(federationRuleId, RuleUpdateParams.none())

        /** @see update */
        fun update(
            federationRuleId: String,
            params: RuleUpdateParams = RuleUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            update(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

        /** @see update */
        fun update(
            federationRuleId: String,
            params: RuleUpdateParams = RuleUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            update(federationRuleId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: RuleUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>>

        /** @see update */
        fun update(
            params: RuleUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            federationRuleId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            update(federationRuleId, RuleUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/federation_rules?beta=true`, but
         * is otherwise the same as [RuleServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<RuleListPageAsync>> =
            list(RuleListParams.none())

        /** @see list */
        fun list(
            params: RuleListParams = RuleListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<RuleListPageAsync>>

        /** @see list */
        fun list(
            params: RuleListParams = RuleListParams.none()
        ): CompletableFuture<HttpResponseFor<RuleListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<RuleListPageAsync>> =
            list(RuleListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/federation_rules/{federation_rule_id}/archive?beta=true`, but is
         * otherwise the same as [RuleServiceAsync.archive].
         */
        fun archive(
            federationRuleId: String
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            archive(federationRuleId, RuleArchiveParams.none())

        /** @see archive */
        fun archive(
            federationRuleId: String,
            params: RuleArchiveParams = RuleArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            archive(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

        /** @see archive */
        fun archive(
            federationRuleId: String,
            params: RuleArchiveParams = RuleArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            archive(federationRuleId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: RuleArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>>

        /** @see archive */
        fun archive(
            params: RuleArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            federationRuleId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> =
            archive(federationRuleId, RuleArchiveParams.none(), requestOptions)
    }
}
