package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.plugins.BetaDeletedPlugin
import com.anthropic.models.beta.organization.plugins.BetaPlugin
import com.anthropic.models.beta.organization.plugins.PluginCreateParams
import com.anthropic.models.beta.organization.plugins.PluginDeleteParams
import com.anthropic.models.beta.organization.plugins.PluginListPage
import com.anthropic.models.beta.organization.plugins.PluginListParams
import com.anthropic.models.beta.organization.plugins.PluginRetrieveParams
import com.anthropic.models.beta.organization.plugins.PluginUpdateParams
import com.anthropic.services.blocking.beta.organization.plugins.InstallationSettingService
import com.anthropic.services.blocking.beta.organization.plugins.ShareService
import com.anthropic.services.blocking.beta.organization.plugins.VersionService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface PluginService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): PluginService

    fun versions(): VersionService

    fun installationSettings(): InstallationSettingService

    fun shares(): ShareService

    /**
     * Create an organization-owned Plugin and its first version by uploading the version's files.
     *
     * The upload is `multipart/form-data`: the version's files (`files`, each part sent as
     * `files[]`), with an optional `marketplace_id` and `release_notes`. The manifest's `name`
     * becomes the Plugin's `name`, and `display_name`, `description` and `manifest_version` come
     * from the manifest too.
     *
     * `name` may contain lowercase letters (from any alphabet), digits, and hyphens, up to 64
     * characters. Uppercase letters, spaces, underscores, and other punctuation are rejected.
     *
     * The `name` must be unique within the marketplace: a name already taken returns a 409 with
     * `error_code` `plugin_name_taken` and, when a Plugin holds it, that Plugin's ID in
     * `details.plugin_id`. A Plugin going into the organization's library marketplace is also
     * refused with a 409 when one of its skills has the name of an organization skill (a skill an
     * administrator uploaded for the whole organization in claude.ai): `error_code`
     * `skill_name_taken`, with that name in `details.skill_name`; rename the skill, or remove the
     * organization skill in claude.ai. A 503 with `error_code` `registration_pending` means the
     * Plugin and its version were stored (their IDs are in `details`) but are not yet usable in
     * claude.ai: do not retry the create (the retry would return `plugin_name_taken`); create a
     * version on the stored Plugin instead, which completes it.
     *
     * For a worked example, see
     * [Create a plugin](/docs/en/manage-claude/plugins-api#create-a-plugin) in the Plugins API
     * guide.
     *
     * **Accepted credentials:** an Admin API key with the `write:plugins` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun create(params: PluginCreateParams): BetaPlugin = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: PluginCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPlugin

    /**
     * Retrieve a Plugin by ID.
     *
     * **Accepted credentials:** an Admin API key with the `read:plugins` or `read:org_audit` scope,
     * or a Compliance Access Key with the `read:compliance_org_data` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun retrieve(pluginId: String): BetaPlugin = retrieve(pluginId, PluginRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        pluginId: String,
        params: PluginRetrieveParams = PluginRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPlugin = retrieve(params.toBuilder().pluginId(pluginId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        pluginId: String,
        params: PluginRetrieveParams = PluginRetrieveParams.none(),
    ): BetaPlugin = retrieve(pluginId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: PluginRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPlugin

    /** @see retrieve */
    fun retrieve(params: PluginRetrieveParams): BetaPlugin = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(pluginId: String, requestOptions: RequestOptions): BetaPlugin =
        retrieve(pluginId, PluginRetrieveParams.none(), requestOptions)

    /**
     * Change which stored version of an organization-owned Plugin is served to members, for example
     * to roll back to an earlier one. This pins the served version: later uploads are stored but no
     * longer change what is served, and pinning cannot currently be undone, here or in claude.ai.
     *
     * Pass the version as `served_version_id`: an earlier one to roll back, a later one to start
     * serving a version that was stored without being served, or the one already served to pin it
     * without changing what is served. No new version is created.
     *
     * When the organization has content scanning enabled, a version whose scan is still running is
     * refused with a 409 (`error_code` `scan_pending`; retry once the scan finishes) and one whose
     * scan failed, errored or reached no verdict with a 400 (`scan_failed`; a `warn` is accepted).
     * When the Plugin is in the organization's library marketplace, a version other than the one
     * served is also refused with a 409 when one of its skills has a name that an organization
     * skill (one an administrator uploaded for the whole organization in claude.ai) has since
     * taken: `error_code` `skill_name_taken`, with that name in `details.skill_name`. A
     * member-owned Plugin cannot be updated here (403).
     *
     * This endpoint does not write installation settings; they are written at
     * `/v1/organizations/plugins/{plugin_id}/installation_settings/{target}`.
     *
     * **Accepted credentials:** an Admin API key with the `write:plugins` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun update(pluginId: String, params: PluginUpdateParams): BetaPlugin =
        update(pluginId, params, RequestOptions.none())

    /** @see update */
    fun update(
        pluginId: String,
        params: PluginUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPlugin = update(params.toBuilder().pluginId(pluginId).build(), requestOptions)

    /** @see update */
    fun update(params: PluginUpdateParams): BetaPlugin = update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: PluginUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPlugin

    /**
     * List the Plugins created under the organization, newest first: those in the organization's
     * own plugin marketplaces and those in members' personal plugin marketplaces.
     *
     * Plugins in members' personal marketplaces are listed with the same detail as the
     * organization's own, and their files can be downloaded through the version archive endpoint,
     * which records each such download on the Compliance API activity feed.
     *
     * **Accepted credentials:** an Admin API key with the `read:plugins` or `read:org_audit` scope,
     * or a Compliance Access Key with the `read:compliance_org_data` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun list(): PluginListPage = list(PluginListParams.none())

    /** @see list */
    fun list(
        params: PluginListParams = PluginListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PluginListPage

    /** @see list */
    fun list(params: PluginListParams = PluginListParams.none()): PluginListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): PluginListPage =
        list(PluginListParams.none(), requestOptions)

    /**
     * Permanently delete a Plugin and every version it holds, exactly as when an administrator
     * deletes it in claude.ai. The Plugin may belong to the organization or to a member, including
     * a member who has since left the organization.
     *
     * An organization-owned Plugin's installation settings go with it; a member-owned Plugin's
     * shares are withdrawn and its owner no longer has it.
     *
     * To take an organization-owned Plugin out of use reversibly, set its organization-wide
     * installation setting to `not_available` instead (and remove or change any group settings,
     * which override it for their members). Only a Plugin in a `manual` marketplace can be deleted
     * here; one synchronized from a repository is removed by removing it from the repository (400).
     *
     * **Accepted credentials:** an Admin API key with the `write:plugins` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun delete(pluginId: String): BetaDeletedPlugin = delete(pluginId, PluginDeleteParams.none())

    /** @see delete */
    fun delete(
        pluginId: String,
        params: PluginDeleteParams = PluginDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedPlugin = delete(params.toBuilder().pluginId(pluginId).build(), requestOptions)

    /** @see delete */
    fun delete(
        pluginId: String,
        params: PluginDeleteParams = PluginDeleteParams.none(),
    ): BetaDeletedPlugin = delete(pluginId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: PluginDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedPlugin

    /** @see delete */
    fun delete(params: PluginDeleteParams): BetaDeletedPlugin =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(pluginId: String, requestOptions: RequestOptions): BetaDeletedPlugin =
        delete(pluginId, PluginDeleteParams.none(), requestOptions)

    /** A view of [PluginService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): PluginService.WithRawResponse

        fun versions(): VersionService.WithRawResponse

        fun installationSettings(): InstallationSettingService.WithRawResponse

        fun shares(): ShareService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/plugins?beta=true`, but is
         * otherwise the same as [PluginService.create].
         */
        @MustBeClosed
        fun create(params: PluginCreateParams): HttpResponseFor<BetaPlugin> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: PluginCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPlugin>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/plugins/{plugin_id}?beta=true`,
         * but is otherwise the same as [PluginService.retrieve].
         */
        @MustBeClosed
        fun retrieve(pluginId: String): HttpResponseFor<BetaPlugin> =
            retrieve(pluginId, PluginRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            pluginId: String,
            params: PluginRetrieveParams = PluginRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPlugin> =
            retrieve(params.toBuilder().pluginId(pluginId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            pluginId: String,
            params: PluginRetrieveParams = PluginRetrieveParams.none(),
        ): HttpResponseFor<BetaPlugin> = retrieve(pluginId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: PluginRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPlugin>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: PluginRetrieveParams): HttpResponseFor<BetaPlugin> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            pluginId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPlugin> =
            retrieve(pluginId, PluginRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/plugins/{plugin_id}?beta=true`,
         * but is otherwise the same as [PluginService.update].
         */
        @MustBeClosed
        fun update(pluginId: String, params: PluginUpdateParams): HttpResponseFor<BetaPlugin> =
            update(pluginId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            pluginId: String,
            params: PluginUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPlugin> =
            update(params.toBuilder().pluginId(pluginId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(params: PluginUpdateParams): HttpResponseFor<BetaPlugin> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: PluginUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPlugin>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/plugins?beta=true`, but is
         * otherwise the same as [PluginService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<PluginListPage> = list(PluginListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: PluginListParams = PluginListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PluginListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: PluginListParams = PluginListParams.none()
        ): HttpResponseFor<PluginListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<PluginListPage> =
            list(PluginListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/organizations/plugins/{plugin_id}?beta=true`,
         * but is otherwise the same as [PluginService.delete].
         */
        @MustBeClosed
        fun delete(pluginId: String): HttpResponseFor<BetaDeletedPlugin> =
            delete(pluginId, PluginDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            pluginId: String,
            params: PluginDeleteParams = PluginDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedPlugin> =
            delete(params.toBuilder().pluginId(pluginId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            pluginId: String,
            params: PluginDeleteParams = PluginDeleteParams.none(),
        ): HttpResponseFor<BetaDeletedPlugin> = delete(pluginId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: PluginDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedPlugin>

        /** @see delete */
        @MustBeClosed
        fun delete(params: PluginDeleteParams): HttpResponseFor<BetaDeletedPlugin> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            pluginId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedPlugin> =
            delete(pluginId, PluginDeleteParams.none(), requestOptions)
    }
}
