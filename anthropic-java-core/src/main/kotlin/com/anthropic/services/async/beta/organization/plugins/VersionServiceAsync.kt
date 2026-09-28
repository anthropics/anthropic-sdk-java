package com.anthropic.services.async.beta.organization.plugins

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.plugins.versions.BetaPluginVersion
import com.anthropic.models.beta.organization.plugins.versions.VersionCreateParams
import com.anthropic.models.beta.organization.plugins.versions.VersionDownloadParams
import com.anthropic.models.beta.organization.plugins.versions.VersionListPageAsync
import com.anthropic.models.beta.organization.plugins.versions.VersionListParams
import com.anthropic.models.beta.organization.plugins.versions.VersionRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface VersionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): VersionServiceAsync

    /**
     * Add a version to an organization-owned Plugin by uploading the new version's files; it
     * becomes the version served to members unless the Plugin's served version has been pinned.
     *
     * The upload is the same `multipart/form-data` as creating a Plugin: the version's files
     * (`files`, each part sent as `files[]`) and optional `release_notes`. The uploaded manifest's
     * `name` must equal the Plugin's `name`. Returns the stored version; read the Plugin back to
     * see which version it serves.
     *
     * Only a Plugin in a `manual` marketplace takes uploads; a Plugin synchronized from a
     * repository gets its versions from the repository. When the Plugin is in the organization's
     * library marketplace, a version that adds a skill with the name of an organization skill (a
     * skill an administrator uploaded for the whole organization in claude.ai) is refused with a
     * 409: `error_code` `skill_name_taken`, with that name in `details.skill_name`. A 503 with
     * `error_code` `registration_pending` means the version was stored but is not yet usable; a
     * later version create on the Plugin completes it.
     *
     * For a worked example, see
     * [Create a version](/docs/en/manage-claude/plugins-api#create-a-version) in the Plugins API
     * guide.
     *
     * **Accepted credentials:** an Admin API key with the `write:plugins` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun create(
        pluginId: String,
        params: VersionCreateParams,
    ): CompletableFuture<BetaPluginVersion> = create(pluginId, params, RequestOptions.none())

    /** @see create */
    fun create(
        pluginId: String,
        params: VersionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaPluginVersion> =
        create(params.toBuilder().pluginId(pluginId).build(), requestOptions)

    /** @see create */
    fun create(params: VersionCreateParams): CompletableFuture<BetaPluginVersion> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: VersionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaPluginVersion>

    /**
     * Retrieve one version of a Plugin by its ID, or the Plugin's newest version.
     *
     * **Accepted credentials:** an Admin API key with the `read:plugins` or `read:org_audit` scope,
     * or a Compliance Access Key with the `read:compliance_org_data` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun retrieve(
        version: String,
        params: VersionRetrieveParams,
    ): CompletableFuture<BetaPluginVersion> = retrieve(version, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        version: String,
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaPluginVersion> =
        retrieve(params.toBuilder().version(version).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: VersionRetrieveParams): CompletableFuture<BetaPluginVersion> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaPluginVersion>

    /**
     * List a Plugin's versions, newest first.
     *
     * The first item of the first page is the version the Plugin's `latest_version_id` refers to.
     *
     * **Accepted credentials:** an Admin API key with the `read:plugins` or `read:org_audit` scope,
     * or a Compliance Access Key with the `read:compliance_org_data` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun list(pluginId: String): CompletableFuture<VersionListPageAsync> =
        list(pluginId, VersionListParams.none())

    /** @see list */
    fun list(
        pluginId: String,
        params: VersionListParams = VersionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<VersionListPageAsync> =
        list(params.toBuilder().pluginId(pluginId).build(), requestOptions)

    /** @see list */
    fun list(
        pluginId: String,
        params: VersionListParams = VersionListParams.none(),
    ): CompletableFuture<VersionListPageAsync> = list(pluginId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: VersionListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<VersionListPageAsync>

    /** @see list */
    fun list(params: VersionListParams): CompletableFuture<VersionListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        pluginId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<VersionListPageAsync> =
        list(pluginId, VersionListParams.none(), requestOptions)

    /**
     * Download one version's `.zip` archive, exactly as stored. Each download of a Plugin from a
     * member's personal plugin marketplace is recorded on the Compliance API activity feed.
     *
     * The response body is the archive (`Content-Type: application/zip`), sent as an attachment
     * whose filename is derived from the Plugin's name; name saved files from the IDs in the
     * request path, since that filename is not unique.
     *
     * **Accepted credentials:** an Admin API key with the `read:plugins` or `read:org_audit` scope,
     * or a Compliance Access Key with the `read:compliance_org_data` scope.
     *
     * Every read scope above (`read:plugins`, `read:org_audit`, and `read:compliance_org_data`) can
     * download the files of plugins in members' personal marketplaces, including files that
     * claude.ai's admin settings do not show, and a `read:org_audit` or `read:compliance_org_data`
     * key created for all of your parent organization's linked organizations can do this in any
     * organization under it that has access to this API, by passing `organization_id`. Each such
     * download records a `claude_plugin_archive_accessed` event on the Compliance API activity
     * feed, identifying the key, the plugin, the version, and the member. Downloads of
     * organization-owned plugins are not recorded.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun download(version: String, params: VersionDownloadParams): CompletableFuture<HttpResponse> =
        download(version, params, RequestOptions.none())

    /** @see download */
    fun download(
        version: String,
        params: VersionDownloadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<HttpResponse> =
        download(params.toBuilder().version(version).build(), requestOptions)

    /** @see download */
    fun download(params: VersionDownloadParams): CompletableFuture<HttpResponse> =
        download(params, RequestOptions.none())

    /** @see download */
    fun download(
        params: VersionDownloadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<HttpResponse>

    /**
     * A view of [VersionServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): VersionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/plugins/{plugin_id}/versions?beta=true`, but is otherwise the same as
         * [VersionServiceAsync.create].
         */
        fun create(
            pluginId: String,
            params: VersionCreateParams,
        ): CompletableFuture<HttpResponseFor<BetaPluginVersion>> =
            create(pluginId, params, RequestOptions.none())

        /** @see create */
        fun create(
            pluginId: String,
            params: VersionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaPluginVersion>> =
            create(params.toBuilder().pluginId(pluginId).build(), requestOptions)

        /** @see create */
        fun create(
            params: VersionCreateParams
        ): CompletableFuture<HttpResponseFor<BetaPluginVersion>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: VersionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaPluginVersion>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/plugins/{plugin_id}/versions/{version}?beta=true`, but is otherwise the
         * same as [VersionServiceAsync.retrieve].
         */
        fun retrieve(
            version: String,
            params: VersionRetrieveParams,
        ): CompletableFuture<HttpResponseFor<BetaPluginVersion>> =
            retrieve(version, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            version: String,
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaPluginVersion>> =
            retrieve(params.toBuilder().version(version).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            params: VersionRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaPluginVersion>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaPluginVersion>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/plugins/{plugin_id}/versions?beta=true`, but is otherwise the same as
         * [VersionServiceAsync.list].
         */
        fun list(pluginId: String): CompletableFuture<HttpResponseFor<VersionListPageAsync>> =
            list(pluginId, VersionListParams.none())

        /** @see list */
        fun list(
            pluginId: String,
            params: VersionListParams = VersionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<VersionListPageAsync>> =
            list(params.toBuilder().pluginId(pluginId).build(), requestOptions)

        /** @see list */
        fun list(
            pluginId: String,
            params: VersionListParams = VersionListParams.none(),
        ): CompletableFuture<HttpResponseFor<VersionListPageAsync>> =
            list(pluginId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: VersionListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<VersionListPageAsync>>

        /** @see list */
        fun list(
            params: VersionListParams
        ): CompletableFuture<HttpResponseFor<VersionListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            pluginId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<VersionListPageAsync>> =
            list(pluginId, VersionListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/plugins/{plugin_id}/versions/{version}/content?beta=true`, but is
         * otherwise the same as [VersionServiceAsync.download].
         */
        fun download(
            version: String,
            params: VersionDownloadParams,
        ): CompletableFuture<HttpResponse> = download(version, params, RequestOptions.none())

        /** @see download */
        fun download(
            version: String,
            params: VersionDownloadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponse> =
            download(params.toBuilder().version(version).build(), requestOptions)

        /** @see download */
        fun download(params: VersionDownloadParams): CompletableFuture<HttpResponse> =
            download(params, RequestOptions.none())

        /** @see download */
        fun download(
            params: VersionDownloadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponse>
    }
}
