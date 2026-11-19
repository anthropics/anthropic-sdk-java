package com.anthropic.services.blocking.beta.organization.claudecode.managedsettings

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.BetaClaudeCodeManagedSettingsConfiguration
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.ConfigurationRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.ConfigurationUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ConfigurationService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ConfigurationService

    /**
     * Retrieve the organization's Claude Code managed settings mode and RBAC Group order.
     *
     * **Accepted credentials:** an organization-level Admin API key with the
     * `read:claude_code_managed_settings` scope.
     *
     * Every request must include the beta header `anthropic-beta:
     * ce-claude-code-managed-settings-2026-10-06`. A request without it, or from an organization
     * not enabled for this beta, returns `404`. The Claude Code Managed Settings API is in beta and
     * is available to Claude Enterprise organizations only.
     */
    fun retrieve(): BetaClaudeCodeManagedSettingsConfiguration =
        retrieve(ConfigurationRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        params: ConfigurationRetrieveParams = ConfigurationRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeCodeManagedSettingsConfiguration

    /** @see retrieve */
    fun retrieve(
        params: ConfigurationRetrieveParams = ConfigurationRetrieveParams.none()
    ): BetaClaudeCodeManagedSettingsConfiguration = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(requestOptions: RequestOptions): BetaClaudeCodeManagedSettingsConfiguration =
        retrieve(ConfigurationRetrieveParams.none(), requestOptions)

    /**
     * Change the organization's Claude Code managed settings mode, the RBAC Group order, or both,
     * in one atomic write.
     *
     * To change the order, send `rbac_group_order` with the `rbac_group_order_version` from the
     * read the change is based on. If the order has changed since that read, the request returns
     * `409`: read it again and retry. To switch modes only if the order has not changed, send the
     * current `rbac_group_order` and `rbac_group_order_version` with the new `mode`.
     *
     * A switch to `rbac_groups` mode with no RBAC Group settings and no default settings copies the
     * organization settings into a new default, in the same write, so members keep the settings
     * they had. The copy does not follow later changes to the organization settings. If the
     * organization settings cannot be copied, the request returns `400` and changes nothing. If
     * they cannot be read, are over 1 MiB or are not valid, setting the default settings first
     * avoids the copy.
     *
     * After a switch to `rbac_groups` mode, a member in no listed RBAC Group receives the default
     * settings, or no settings if no default is set, not the organization settings.
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
     * @throws com.anthropic.models.beta.BetaRequestBodyTimeoutException
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaSettingsDocumentLimitExceededException
     * @throws
     *   com.anthropic.models.beta.organization.claudecode.managedsettings.BetaSettingsHistoryLimitExceededException
     * @throws com.anthropic.models.SandboxEgressRestrictedException
     */
    fun update(): BetaClaudeCodeManagedSettingsConfiguration =
        update(ConfigurationUpdateParams.none())

    /** @see update */
    fun update(
        params: ConfigurationUpdateParams = ConfigurationUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeCodeManagedSettingsConfiguration

    /** @see update */
    fun update(
        params: ConfigurationUpdateParams = ConfigurationUpdateParams.none()
    ): BetaClaudeCodeManagedSettingsConfiguration = update(params, RequestOptions.none())

    /** @see update */
    fun update(requestOptions: RequestOptions): BetaClaudeCodeManagedSettingsConfiguration =
        update(ConfigurationUpdateParams.none(), requestOptions)

    /**
     * A view of [ConfigurationService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ConfigurationService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/claude_code/managed_settings/configuration`, but is otherwise the same
         * as [ConfigurationService.retrieve].
         */
        @MustBeClosed
        fun retrieve(): HttpResponseFor<BetaClaudeCodeManagedSettingsConfiguration> =
            retrieve(ConfigurationRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ConfigurationRetrieveParams = ConfigurationRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeCodeManagedSettingsConfiguration>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ConfigurationRetrieveParams = ConfigurationRetrieveParams.none()
        ): HttpResponseFor<BetaClaudeCodeManagedSettingsConfiguration> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            requestOptions: RequestOptions
        ): HttpResponseFor<BetaClaudeCodeManagedSettingsConfiguration> =
            retrieve(ConfigurationRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/claude_code/managed_settings/configuration`, but is otherwise the same
         * as [ConfigurationService.update].
         */
        @MustBeClosed
        fun update(): HttpResponseFor<BetaClaudeCodeManagedSettingsConfiguration> =
            update(ConfigurationUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: ConfigurationUpdateParams = ConfigurationUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeCodeManagedSettingsConfiguration>

        /** @see update */
        @MustBeClosed
        fun update(
            params: ConfigurationUpdateParams = ConfigurationUpdateParams.none()
        ): HttpResponseFor<BetaClaudeCodeManagedSettingsConfiguration> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            requestOptions: RequestOptions
        ): HttpResponseFor<BetaClaudeCodeManagedSettingsConfiguration> =
            update(ConfigurationUpdateParams.none(), requestOptions)
    }
}
