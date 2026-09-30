package com.anthropic.services.blocking.organization.federation

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.federation.rules.FederationRule
import com.anthropic.models.organization.federation.rules.RuleArchiveParams
import com.anthropic.models.organization.federation.rules.RuleCreateParams
import com.anthropic.models.organization.federation.rules.RuleListPage
import com.anthropic.models.organization.federation.rules.RuleListParams
import com.anthropic.models.organization.federation.rules.RuleRetrieveParams
import com.anthropic.models.organization.federation.rules.RuleUpdateParams
import com.anthropic.services.blocking.organization.federation.rules.WorkspaceService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface RuleService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): RuleService

    fun workspaces(): WorkspaceService

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
    fun create(params: RuleCreateParams): FederationRule = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: RuleCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationRule

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Retrieve a federation rule by its ID (`fdrl_...`).
     */
    fun retrieve(federationRuleId: String): FederationRule =
        retrieve(federationRuleId, RuleRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        federationRuleId: String,
        params: RuleRetrieveParams = RuleRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationRule =
        retrieve(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        federationRuleId: String,
        params: RuleRetrieveParams = RuleRetrieveParams.none(),
    ): FederationRule = retrieve(federationRuleId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: RuleRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationRule

    /** @see retrieve */
    fun retrieve(params: RuleRetrieveParams): FederationRule =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(federationRuleId: String, requestOptions: RequestOptions): FederationRule =
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
    fun update(federationRuleId: String): FederationRule =
        update(federationRuleId, RuleUpdateParams.none())

    /** @see update */
    fun update(
        federationRuleId: String,
        params: RuleUpdateParams = RuleUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationRule =
        update(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

    /** @see update */
    fun update(
        federationRuleId: String,
        params: RuleUpdateParams = RuleUpdateParams.none(),
    ): FederationRule = update(federationRuleId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: RuleUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationRule

    /** @see update */
    fun update(params: RuleUpdateParams): FederationRule = update(params, RequestOptions.none())

    /** @see update */
    fun update(federationRuleId: String, requestOptions: RequestOptions): FederationRule =
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
    fun list(): RuleListPage = list(RuleListParams.none())

    /** @see list */
    fun list(
        params: RuleListParams = RuleListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RuleListPage

    /** @see list */
    fun list(params: RuleListParams = RuleListParams.none()): RuleListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): RuleListPage =
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
    fun archive(federationRuleId: String): FederationRule =
        archive(federationRuleId, RuleArchiveParams.none())

    /** @see archive */
    fun archive(
        federationRuleId: String,
        params: RuleArchiveParams = RuleArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationRule =
        archive(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

    /** @see archive */
    fun archive(
        federationRuleId: String,
        params: RuleArchiveParams = RuleArchiveParams.none(),
    ): FederationRule = archive(federationRuleId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: RuleArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationRule

    /** @see archive */
    fun archive(params: RuleArchiveParams): FederationRule = archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(federationRuleId: String, requestOptions: RequestOptions): FederationRule =
        archive(federationRuleId, RuleArchiveParams.none(), requestOptions)

    /** A view of [RuleService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): RuleService.WithRawResponse

        fun workspaces(): WorkspaceService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/federation_rules`, but is
         * otherwise the same as [RuleService.create].
         */
        @MustBeClosed
        fun create(params: RuleCreateParams): HttpResponseFor<FederationRule> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: RuleCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationRule>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/federation_rules/{federation_rule_id}`, but is otherwise the same as
         * [RuleService.retrieve].
         */
        @MustBeClosed
        fun retrieve(federationRuleId: String): HttpResponseFor<FederationRule> =
            retrieve(federationRuleId, RuleRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            federationRuleId: String,
            params: RuleRetrieveParams = RuleRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationRule> =
            retrieve(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            federationRuleId: String,
            params: RuleRetrieveParams = RuleRetrieveParams.none(),
        ): HttpResponseFor<FederationRule> =
            retrieve(federationRuleId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: RuleRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationRule>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: RuleRetrieveParams): HttpResponseFor<FederationRule> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            federationRuleId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationRule> =
            retrieve(federationRuleId, RuleRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/federation_rules/{federation_rule_id}`, but is otherwise the same as
         * [RuleService.update].
         */
        @MustBeClosed
        fun update(federationRuleId: String): HttpResponseFor<FederationRule> =
            update(federationRuleId, RuleUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            federationRuleId: String,
            params: RuleUpdateParams = RuleUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationRule> =
            update(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            federationRuleId: String,
            params: RuleUpdateParams = RuleUpdateParams.none(),
        ): HttpResponseFor<FederationRule> = update(federationRuleId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: RuleUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationRule>

        /** @see update */
        @MustBeClosed
        fun update(params: RuleUpdateParams): HttpResponseFor<FederationRule> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            federationRuleId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationRule> =
            update(federationRuleId, RuleUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/federation_rules`, but is
         * otherwise the same as [RuleService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<RuleListPage> = list(RuleListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: RuleListParams = RuleListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RuleListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: RuleListParams = RuleListParams.none()): HttpResponseFor<RuleListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<RuleListPage> =
            list(RuleListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/federation_rules/{federation_rule_id}/archive`, but is otherwise the
         * same as [RuleService.archive].
         */
        @MustBeClosed
        fun archive(federationRuleId: String): HttpResponseFor<FederationRule> =
            archive(federationRuleId, RuleArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            federationRuleId: String,
            params: RuleArchiveParams = RuleArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationRule> =
            archive(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            federationRuleId: String,
            params: RuleArchiveParams = RuleArchiveParams.none(),
        ): HttpResponseFor<FederationRule> =
            archive(federationRuleId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: RuleArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationRule>

        /** @see archive */
        @MustBeClosed
        fun archive(params: RuleArchiveParams): HttpResponseFor<FederationRule> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            federationRuleId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationRule> =
            archive(federationRuleId, RuleArchiveParams.none(), requestOptions)
    }
}
