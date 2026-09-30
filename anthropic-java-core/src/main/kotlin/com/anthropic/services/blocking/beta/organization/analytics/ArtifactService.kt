package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.artifacts.ArtifactListPage
import com.anthropic.models.beta.organization.analytics.artifacts.ArtifactListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ArtifactService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ArtifactService

    /**
     * Get artifact-creation activity for a given day, broken out by MIME type.
     *
     * Returns the full (`artifact_type`, `is_shared`) cube for the organization; `next_page` is
     * null except for grouped queries, which paginate. The cube can be broken out per product, per
     * member, or per RBAC group via `group_by[]`, and scoped via `filter[]`. Requires an API key
     * with the `read:analytics` scope.
     */
    fun list(params: ArtifactListParams): ArtifactListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: ArtifactListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ArtifactListPage

    /** A view of [ArtifactService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ArtifactService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/artifacts?beta=true`,
         * but is otherwise the same as [ArtifactService.list].
         */
        @MustBeClosed
        fun list(params: ArtifactListParams): HttpResponseFor<ArtifactListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: ArtifactListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ArtifactListPage>
    }
}
