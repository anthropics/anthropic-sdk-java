package com.anthropic.services.async.beta.organization.claudecode.managedsettings

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.BetaClaudeCodeRbacGroupDefaultManagedSettings
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.RbacGroupDefaultRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.RbacGroupDefaultUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface RbacGroupDefaultServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupDefaultServiceAsync

    /**
     * Retrieve the default Claude Code managed settings, which members who belong to no RBAC Group
     * in `rbac_group_order` receive in `rbac_groups` mode.
     *
     * **Accepted credentials:** an organization-level Admin API key with the
     * `read:claude_code_managed_settings` scope.
     *
     * Every request must include the beta header `anthropic-beta:
     * ce-claude-code-managed-settings-2026-10-06`. A request without it, or from an organization
     * not enabled for this beta, returns `404`. The Claude Code Managed Settings API is in beta and
     * is available to Claude Enterprise organizations only.
     *
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaCmekFeatureUnsupportedException
     * @throws com.anthropic.models.CmekKeyDisabledException
     */
    fun retrieve(): CompletableFuture<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
        retrieve(RbacGroupDefaultRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        params: RbacGroupDefaultRetrieveParams = RbacGroupDefaultRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeCodeRbacGroupDefaultManagedSettings>

    /** @see retrieve */
    fun retrieve(
        params: RbacGroupDefaultRetrieveParams = RbacGroupDefaultRetrieveParams.none()
    ): CompletableFuture<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        requestOptions: RequestOptions
    ): CompletableFuture<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
        retrieve(RbacGroupDefaultRetrieveParams.none(), requestOptions)

    /**
     * Set or replace the default Claude Code managed settings, or clear them with `null`.
     *
     * **Accepted credentials:** an organization-level Admin API key with the
     * `write:claude_code_managed_settings` scope.
     *
     * Every request must include the beta header `anthropic-beta:
     * ce-claude-code-managed-settings-2026-10-06`. A request without it, or from an organization
     * not enabled for this beta, returns `404`. The Claude Code Managed Settings API is in beta and
     * is available to Claude Enterprise organizations only.
     *
     * A write that returns `503` may have applied. Retrying it is safe.
     *
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaCmekFeatureUnsupportedException
     * @throws com.anthropic.models.CmekKeyDisabledException
     * @throws com.anthropic.models.beta.BetaRequestBodyTimeoutException
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaSettingsDocumentLimitExceededException
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaSettingsHistoryLimitExceededException
     * @throws com.anthropic.models.SandboxEgressRestrictedException
     */
    fun update(
        params: RbacGroupDefaultUpdateParams
    ): CompletableFuture<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: RbacGroupDefaultUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeCodeRbacGroupDefaultManagedSettings>

    /**
     * A view of [RbacGroupDefaultServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RbacGroupDefaultServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/claude_code/managed_settings/rbac_group_default`, but is otherwise the
         * same as [RbacGroupDefaultServiceAsync.retrieve].
         */
        fun retrieve():
            CompletableFuture<HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings>> =
            retrieve(RbacGroupDefaultRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            params: RbacGroupDefaultRetrieveParams = RbacGroupDefaultRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings>>

        /** @see retrieve */
        fun retrieve(
            params: RbacGroupDefaultRetrieveParams = RbacGroupDefaultRetrieveParams.none()
        ): CompletableFuture<HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings>> =
            retrieve(RbacGroupDefaultRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/claude_code/managed_settings/rbac_group_default`, but is otherwise the
         * same as [RbacGroupDefaultServiceAsync.update].
         */
        fun update(
            params: RbacGroupDefaultUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: RbacGroupDefaultUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings>>
    }
}
