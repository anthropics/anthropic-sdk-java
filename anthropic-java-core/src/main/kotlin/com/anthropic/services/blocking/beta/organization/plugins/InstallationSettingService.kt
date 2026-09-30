package com.anthropic.services.blocking.beta.organization.plugins

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.plugins.installationsettings.BetaDeletedPluginInstallationSetting
import com.anthropic.models.beta.organization.plugins.installationsettings.BetaPluginInstallationSetting
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingListPage
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingListParams
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingRemoveParams
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingSetParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface InstallationSettingService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): InstallationSettingService

    /**
     * List an organization-owned Plugin's installation settings, which say which members it is for,
     * most recently created first.
     *
     * The list holds the Plugin's own organization-wide setting (absent while the Plugin inherits
     * its marketplace's default) and each RBAC Group's own setting. A member-owned Plugin has
     * shares instead, so this path returns 404 for one.
     *
     * **Accepted credentials:** an Admin API key with the `read:plugins` or `read:org_audit` scope,
     * or a Compliance Access Key with the `read:compliance_org_data` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun list(pluginId: String): InstallationSettingListPage =
        list(pluginId, InstallationSettingListParams.none())

    /** @see list */
    fun list(
        pluginId: String,
        params: InstallationSettingListParams = InstallationSettingListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): InstallationSettingListPage =
        list(params.toBuilder().pluginId(pluginId).build(), requestOptions)

    /** @see list */
    fun list(
        pluginId: String,
        params: InstallationSettingListParams = InstallationSettingListParams.none(),
    ): InstallationSettingListPage = list(pluginId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: InstallationSettingListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): InstallationSettingListPage

    /** @see list */
    fun list(params: InstallationSettingListParams): InstallationSettingListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(pluginId: String, requestOptions: RequestOptions): InstallationSettingListPage =
        list(pluginId, InstallationSettingListParams.none(), requestOptions)

    /**
     * Remove an organization-owned Plugin's own installation setting for the whole organization or
     * for one RBAC Group.
     *
     * Removing the `organization` target returns the Plugin to its marketplace's default
     * installation setting and leaves the groups' settings in place. Removing a group's setting
     * makes the group's members fall back to the Plugin's organization-wide setting or to the
     * settings of their other groups.
     *
     * A target that holds no setting of its own returns 404 (a Plugin that already inherits its
     * marketplace's default holds no `organization` setting), and so does a member-owned Plugin.
     *
     * A removal counts as one of the Plugin's installation-setting writes: send all of those writes
     * one at a time. If several arrive for the same Plugin at the same time, the server handles
     * them one after another and can answer some of them with `503` and `x-should-retry: true`
     * instead of applying them; wait a second or two and send the removal again. A `404` on the
     * repeat means the setting is already gone.
     *
     * **Accepted credentials:** an Admin API key with the `write:plugins` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun remove(
        target: String,
        params: InstallationSettingRemoveParams,
    ): BetaDeletedPluginInstallationSetting = remove(target, params, RequestOptions.none())

    /** @see remove */
    fun remove(
        target: String,
        params: InstallationSettingRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedPluginInstallationSetting =
        remove(params.toBuilder().target(target).build(), requestOptions)

    /** @see remove */
    fun remove(params: InstallationSettingRemoveParams): BetaDeletedPluginInstallationSetting =
        remove(params, RequestOptions.none())

    /** @see remove */
    fun remove(
        params: InstallationSettingRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedPluginInstallationSetting

    /**
     * Set or change an organization-owned Plugin's installation setting for the whole organization
     * or for one RBAC Group.
     *
     * Writing the value a target already holds of its own changes nothing.
     *
     * A member-owned Plugin has shares instead of installation settings, so this path returns 404
     * for one.
     *
     * Send a Plugin's installation-setting writes one at a time. If several writes for the same
     * Plugin arrive at the same time, the server handles them one after another and can answer some
     * of them with `503` instead of applying them. That `503` carries `x-should-retry: true`, and
     * the write is safe to repeat: wait a second or two, then send it again.
     *
     * **Accepted credentials:** an Admin API key with the `write:plugins` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun set(target: String, params: InstallationSettingSetParams): BetaPluginInstallationSetting =
        set(target, params, RequestOptions.none())

    /** @see set */
    fun set(
        target: String,
        params: InstallationSettingSetParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPluginInstallationSetting =
        set(params.toBuilder().target(target).build(), requestOptions)

    /** @see set */
    fun set(params: InstallationSettingSetParams): BetaPluginInstallationSetting =
        set(params, RequestOptions.none())

    /** @see set */
    fun set(
        params: InstallationSettingSetParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPluginInstallationSetting

    /**
     * A view of [InstallationSettingService] that provides access to raw HTTP responses for each
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
        ): InstallationSettingService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/plugins/{plugin_id}/installation_settings?beta=true`, but is otherwise
         * the same as [InstallationSettingService.list].
         */
        @MustBeClosed
        fun list(pluginId: String): HttpResponseFor<InstallationSettingListPage> =
            list(pluginId, InstallationSettingListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            pluginId: String,
            params: InstallationSettingListParams = InstallationSettingListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<InstallationSettingListPage> =
            list(params.toBuilder().pluginId(pluginId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            pluginId: String,
            params: InstallationSettingListParams = InstallationSettingListParams.none(),
        ): HttpResponseFor<InstallationSettingListPage> =
            list(pluginId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: InstallationSettingListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<InstallationSettingListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: InstallationSettingListParams
        ): HttpResponseFor<InstallationSettingListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            pluginId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<InstallationSettingListPage> =
            list(pluginId, InstallationSettingListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/plugins/{plugin_id}/installation_settings/{target}?beta=true`, but is
         * otherwise the same as [InstallationSettingService.remove].
         */
        @MustBeClosed
        fun remove(
            target: String,
            params: InstallationSettingRemoveParams,
        ): HttpResponseFor<BetaDeletedPluginInstallationSetting> =
            remove(target, params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            target: String,
            params: InstallationSettingRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedPluginInstallationSetting> =
            remove(params.toBuilder().target(target).build(), requestOptions)

        /** @see remove */
        @MustBeClosed
        fun remove(
            params: InstallationSettingRemoveParams
        ): HttpResponseFor<BetaDeletedPluginInstallationSetting> =
            remove(params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            params: InstallationSettingRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedPluginInstallationSetting>

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/plugins/{plugin_id}/installation_settings/{target}?beta=true`, but is
         * otherwise the same as [InstallationSettingService.set].
         */
        @MustBeClosed
        fun set(
            target: String,
            params: InstallationSettingSetParams,
        ): HttpResponseFor<BetaPluginInstallationSetting> =
            set(target, params, RequestOptions.none())

        /** @see set */
        @MustBeClosed
        fun set(
            target: String,
            params: InstallationSettingSetParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPluginInstallationSetting> =
            set(params.toBuilder().target(target).build(), requestOptions)

        /** @see set */
        @MustBeClosed
        fun set(
            params: InstallationSettingSetParams
        ): HttpResponseFor<BetaPluginInstallationSetting> = set(params, RequestOptions.none())

        /** @see set */
        @MustBeClosed
        fun set(
            params: InstallationSettingSetParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPluginInstallationSetting>
    }
}
