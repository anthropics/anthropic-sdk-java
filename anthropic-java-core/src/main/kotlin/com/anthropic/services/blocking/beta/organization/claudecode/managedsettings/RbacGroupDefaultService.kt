package com.anthropic.services.blocking.beta.organization.claudecode.managedsettings

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.BetaClaudeCodeRbacGroupDefaultManagedSettings
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.RbacGroupDefaultRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.RbacGroupDefaultUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface RbacGroupDefaultService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupDefaultService

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
    fun retrieve(): BetaClaudeCodeRbacGroupDefaultManagedSettings =
        retrieve(RbacGroupDefaultRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        params: RbacGroupDefaultRetrieveParams = RbacGroupDefaultRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeCodeRbacGroupDefaultManagedSettings

    /** @see retrieve */
    fun retrieve(
        params: RbacGroupDefaultRetrieveParams = RbacGroupDefaultRetrieveParams.none()
    ): BetaClaudeCodeRbacGroupDefaultManagedSettings = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(requestOptions: RequestOptions): BetaClaudeCodeRbacGroupDefaultManagedSettings =
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
    ): BetaClaudeCodeRbacGroupDefaultManagedSettings = update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: RbacGroupDefaultUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeCodeRbacGroupDefaultManagedSettings

    /**
     * A view of [RbacGroupDefaultService] that provides access to raw HTTP responses for each
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
        ): RbacGroupDefaultService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/claude_code/managed_settings/rbac_group_default`, but is otherwise the
         * same as [RbacGroupDefaultService.retrieve].
         */
        @MustBeClosed
        fun retrieve(): HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
            retrieve(RbacGroupDefaultRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: RbacGroupDefaultRetrieveParams = RbacGroupDefaultRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: RbacGroupDefaultRetrieveParams = RbacGroupDefaultRetrieveParams.none()
        ): HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            requestOptions: RequestOptions
        ): HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
            retrieve(RbacGroupDefaultRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/claude_code/managed_settings/rbac_group_default`, but is otherwise the
         * same as [RbacGroupDefaultService.update].
         */
        @MustBeClosed
        fun update(
            params: RbacGroupDefaultUpdateParams
        ): HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: RbacGroupDefaultUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings>
    }
}
