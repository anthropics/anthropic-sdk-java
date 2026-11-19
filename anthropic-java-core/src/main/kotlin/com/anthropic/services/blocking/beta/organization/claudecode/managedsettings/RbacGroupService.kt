package com.anthropic.services.blocking.beta.organization.claudecode.managedsettings

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.BetaClaudeCodeRbacGroupManagedSettings
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.BetaDeletedClaudeCodeRbacGroupManagedSettings
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupDeleteParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupListPage
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupListParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupSetParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface RbacGroupService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupService

    /**
     * Retrieve an RBAC Group's Claude Code managed settings.
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
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaRbacGroupManagedSettingsNotFoundException
     */
    fun retrieve(rbacGroupId: String): BetaClaudeCodeRbacGroupManagedSettings =
        retrieve(rbacGroupId, RbacGroupRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        rbacGroupId: String,
        params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeCodeRbacGroupManagedSettings =
        retrieve(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        rbacGroupId: String,
        params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
    ): BetaClaudeCodeRbacGroupManagedSettings = retrieve(rbacGroupId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: RbacGroupRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeCodeRbacGroupManagedSettings

    /** @see retrieve */
    fun retrieve(params: RbacGroupRetrieveParams): BetaClaudeCodeRbacGroupManagedSettings =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        rbacGroupId: String,
        requestOptions: RequestOptions,
    ): BetaClaudeCodeRbacGroupManagedSettings =
        retrieve(rbacGroupId, RbacGroupRetrieveParams.none(), requestOptions)

    /**
     * List the RBAC Groups that have Claude Code managed settings, highest precedence first.
     *
     * Each entry gives the group's status and timestamps, not its settings. The list includes
     * deleted groups that still have settings, and leaves out the default settings. If the order
     * changes between pages, the request returns `409`: list again from the first page.
     *
     * **Accepted credentials:** an organization-level Admin API key with the
     * `read:claude_code_managed_settings` scope.
     *
     * Every request must include the beta header `anthropic-beta:
     * ce-claude-code-managed-settings-2026-10-06`. A request without it, or from an organization
     * not enabled for this beta, returns `404`. The Claude Code Managed Settings API is in beta and
     * is available to Claude Enterprise organizations only.
     */
    fun list(): RbacGroupListPage = list(RbacGroupListParams.none())

    /** @see list */
    fun list(
        params: RbacGroupListParams = RbacGroupListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RbacGroupListPage

    /** @see list */
    fun list(params: RbacGroupListParams = RbacGroupListParams.none()): RbacGroupListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): RbacGroupListPage =
        list(RbacGroupListParams.none(), requestOptions)

    /**
     * Delete an RBAC Group's Claude Code managed settings and remove the group from
     * `rbac_group_order`.
     *
     * A group with no settings returns `404` with the error code
     * `rbac_group_managed_settings_not_found`. That code on a retry of a delete that returned `503`
     * means the settings are gone.
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
     * @throws com.anthropic.models.CmekKeyDisabledException
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaSettingsHistoryLimitExceededException
     * @throws com.anthropic.models.SandboxEgressRestrictedException
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaRbacGroupManagedSettingsNotFoundException
     */
    fun delete(rbacGroupId: String): BetaDeletedClaudeCodeRbacGroupManagedSettings =
        delete(rbacGroupId, RbacGroupDeleteParams.none())

    /** @see delete */
    fun delete(
        rbacGroupId: String,
        params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedClaudeCodeRbacGroupManagedSettings =
        delete(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see delete */
    fun delete(
        rbacGroupId: String,
        params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
    ): BetaDeletedClaudeCodeRbacGroupManagedSettings =
        delete(rbacGroupId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: RbacGroupDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedClaudeCodeRbacGroupManagedSettings

    /** @see delete */
    fun delete(params: RbacGroupDeleteParams): BetaDeletedClaudeCodeRbacGroupManagedSettings =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        rbacGroupId: String,
        requestOptions: RequestOptions,
    ): BetaDeletedClaudeCodeRbacGroupManagedSettings =
        delete(rbacGroupId, RbacGroupDeleteParams.none(), requestOptions)

    /**
     * Create or replace an RBAC Group's Claude Code managed settings.
     *
     * New settings join the end of `rbac_group_order`, and existing settings keep their place.
     * Until the group is moved up, its members who also belong to an earlier group keep that
     * group's settings. In `organization` mode a write changes no member's settings until the
     * organization switches to `rbac_groups` mode. Once a group has settings, that switch no longer
     * copies the organization settings into default settings, so members in no RBAC Group in
     * `rbac_group_order` receive no settings unless you set the default settings before the switch.
     *
     * Get the group's ID from the RBAC Groups API. One key can hold `read:rbac_groups` and
     * `write:claude_code_managed_settings`.
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
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaRbacGroupOrderLimitExceededException
     * @throws com.anthropic.models.beta.BetaRequestBodyTimeoutException
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaSettingsDocumentLimitExceededException
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaSettingsHistoryLimitExceededException
     * @throws com.anthropic.models.SandboxEgressRestrictedException
     * @throws com.anthropic.models.beta.organization.rbacgroups.BetaRbacGroupNotFoundException
     */
    fun set(
        rbacGroupId: String,
        params: RbacGroupSetParams,
    ): BetaClaudeCodeRbacGroupManagedSettings = set(rbacGroupId, params, RequestOptions.none())

    /** @see set */
    fun set(
        rbacGroupId: String,
        params: RbacGroupSetParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeCodeRbacGroupManagedSettings =
        set(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see set */
    fun set(params: RbacGroupSetParams): BetaClaudeCodeRbacGroupManagedSettings =
        set(params, RequestOptions.none())

    /** @see set */
    fun set(
        params: RbacGroupSetParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeCodeRbacGroupManagedSettings

    /** A view of [RbacGroupService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/claude_code/managed_settings/rbac_groups/{rbac_group_id}`, but is
         * otherwise the same as [RbacGroupService.retrieve].
         */
        @MustBeClosed
        fun retrieve(rbacGroupId: String): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings> =
            retrieve(rbacGroupId, RbacGroupRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            rbacGroupId: String,
            params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings> =
            retrieve(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            rbacGroupId: String,
            params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
        ): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings> =
            retrieve(rbacGroupId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: RbacGroupRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: RbacGroupRetrieveParams
        ): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            rbacGroupId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings> =
            retrieve(rbacGroupId, RbacGroupRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/claude_code/managed_settings/rbac_groups`, but is otherwise the same as
         * [RbacGroupService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<RbacGroupListPage> = list(RbacGroupListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: RbacGroupListParams = RbacGroupListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RbacGroupListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: RbacGroupListParams = RbacGroupListParams.none()
        ): HttpResponseFor<RbacGroupListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<RbacGroupListPage> =
            list(RbacGroupListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/claude_code/managed_settings/rbac_groups/{rbac_group_id}`, but is
         * otherwise the same as [RbacGroupService.delete].
         */
        @MustBeClosed
        fun delete(
            rbacGroupId: String
        ): HttpResponseFor<BetaDeletedClaudeCodeRbacGroupManagedSettings> =
            delete(rbacGroupId, RbacGroupDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            rbacGroupId: String,
            params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedClaudeCodeRbacGroupManagedSettings> =
            delete(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            rbacGroupId: String,
            params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
        ): HttpResponseFor<BetaDeletedClaudeCodeRbacGroupManagedSettings> =
            delete(rbacGroupId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: RbacGroupDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedClaudeCodeRbacGroupManagedSettings>

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: RbacGroupDeleteParams
        ): HttpResponseFor<BetaDeletedClaudeCodeRbacGroupManagedSettings> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            rbacGroupId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedClaudeCodeRbacGroupManagedSettings> =
            delete(rbacGroupId, RbacGroupDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/claude_code/managed_settings/rbac_groups/{rbac_group_id}`, but is
         * otherwise the same as [RbacGroupService.set].
         */
        @MustBeClosed
        fun set(
            rbacGroupId: String,
            params: RbacGroupSetParams,
        ): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings> =
            set(rbacGroupId, params, RequestOptions.none())

        /** @see set */
        @MustBeClosed
        fun set(
            rbacGroupId: String,
            params: RbacGroupSetParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings> =
            set(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see set */
        @MustBeClosed
        fun set(
            params: RbacGroupSetParams
        ): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings> =
            set(params, RequestOptions.none())

        /** @see set */
        @MustBeClosed
        fun set(
            params: RbacGroupSetParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings>
    }
}
