package com.anthropic.services.blocking.beta.organization.analytics.apps.chat

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.apps.chat.projects.ProjectListPage
import com.anthropic.models.beta.organization.analytics.apps.chat.projects.ProjectListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ProjectService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProjectService

    /**
     * Get per-project activity for a given day, with cursor-based pagination.
     *
     * Returns activity metrics for each project in the organization, sorted by project ID. Use
     * `group_by[]` to break projects out per member or per RBAC group, and `filter[]` to scope
     * results; the parameter descriptions list the supported dimensions. Available to organizations
     * on a Claude Enterprise plan. Requires an API key with the `read:analytics` scope.
     */
    fun list(): ProjectListPage = list(ProjectListParams.none())

    /** @see list */
    fun list(
        params: ProjectListParams = ProjectListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ProjectListPage

    /** @see list */
    fun list(params: ProjectListParams = ProjectListParams.none()): ProjectListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): ProjectListPage =
        list(ProjectListParams.none(), requestOptions)

    /** A view of [ProjectService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProjectService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/analytics/apps/chat/projects?beta=true`, but is otherwise the same as
         * [ProjectService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<ProjectListPage> = list(ProjectListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: ProjectListParams = ProjectListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ProjectListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: ProjectListParams = ProjectListParams.none()
        ): HttpResponseFor<ProjectListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<ProjectListPage> =
            list(ProjectListParams.none(), requestOptions)
    }
}
