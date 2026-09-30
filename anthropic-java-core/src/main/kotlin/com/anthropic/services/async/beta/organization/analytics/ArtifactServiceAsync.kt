package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.artifacts.ArtifactListPageAsync
import com.anthropic.models.beta.organization.analytics.artifacts.ArtifactListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ArtifactServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ArtifactServiceAsync

    /**
     * Get artifact-creation activity for a given day, broken out by MIME type.
     *
     * Returns the full (`artifact_type`, `is_shared`) cube for the organization; `next_page` is
     * null except for grouped queries, which paginate. The cube can be broken out per product, per
     * member, or per RBAC group via `group_by[]`, and scoped via `filter[]`. Requires an API key
     * with the `read:analytics` scope.
     */
    fun list(params: ArtifactListParams): CompletableFuture<ArtifactListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: ArtifactListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ArtifactListPageAsync>

    /**
     * A view of [ArtifactServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ArtifactServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/artifacts?beta=true`,
         * but is otherwise the same as [ArtifactServiceAsync.list].
         */
        fun list(
            params: ArtifactListParams
        ): CompletableFuture<HttpResponseFor<ArtifactListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            params: ArtifactListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ArtifactListPageAsync>>
    }
}
