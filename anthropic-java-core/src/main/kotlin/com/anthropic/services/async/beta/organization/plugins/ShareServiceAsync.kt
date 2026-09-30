package com.anthropic.services.async.beta.organization.plugins

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.plugins.shares.ShareListPageAsync
import com.anthropic.models.beta.organization.plugins.shares.ShareListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ShareServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ShareServiceAsync

    /**
     * List the shares the owner of a member-owned Plugin has given — to every member of the
     * organization, to an RBAC Group, or to one member — most recently granted first.
     *
     * Shares are read-only in this API: members give and withdraw them in claude.ai, and who gave a
     * share is recorded on the Compliance API activity feed rather than on the share. An
     * organization-owned Plugin has installation settings instead, so this path returns 404 for
     * one.
     *
     * **Accepted credentials:** an Admin API key with the `read:plugins` or `read:org_audit` scope,
     * or a Compliance Access Key with the `read:compliance_org_data` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun list(pluginId: String): CompletableFuture<ShareListPageAsync> =
        list(pluginId, ShareListParams.none())

    /** @see list */
    fun list(
        pluginId: String,
        params: ShareListParams = ShareListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ShareListPageAsync> =
        list(params.toBuilder().pluginId(pluginId).build(), requestOptions)

    /** @see list */
    fun list(
        pluginId: String,
        params: ShareListParams = ShareListParams.none(),
    ): CompletableFuture<ShareListPageAsync> = list(pluginId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: ShareListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ShareListPageAsync>

    /** @see list */
    fun list(params: ShareListParams): CompletableFuture<ShareListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        pluginId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<ShareListPageAsync> =
        list(pluginId, ShareListParams.none(), requestOptions)

    /** A view of [ShareServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ShareServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/plugins/{plugin_id}/shares?beta=true`, but is otherwise the same as
         * [ShareServiceAsync.list].
         */
        fun list(pluginId: String): CompletableFuture<HttpResponseFor<ShareListPageAsync>> =
            list(pluginId, ShareListParams.none())

        /** @see list */
        fun list(
            pluginId: String,
            params: ShareListParams = ShareListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ShareListPageAsync>> =
            list(params.toBuilder().pluginId(pluginId).build(), requestOptions)

        /** @see list */
        fun list(
            pluginId: String,
            params: ShareListParams = ShareListParams.none(),
        ): CompletableFuture<HttpResponseFor<ShareListPageAsync>> =
            list(pluginId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: ShareListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ShareListPageAsync>>

        /** @see list */
        fun list(params: ShareListParams): CompletableFuture<HttpResponseFor<ShareListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            pluginId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ShareListPageAsync>> =
            list(pluginId, ShareListParams.none(), requestOptions)
    }
}
