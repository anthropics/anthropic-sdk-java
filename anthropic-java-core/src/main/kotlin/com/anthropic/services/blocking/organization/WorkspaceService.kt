package com.anthropic.services.blocking.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.workspaces.Workspace
import com.anthropic.models.organization.workspaces.WorkspaceArchiveParams
import com.anthropic.models.organization.workspaces.WorkspaceCreateParams
import com.anthropic.models.organization.workspaces.WorkspaceListPage
import com.anthropic.models.organization.workspaces.WorkspaceListParams
import com.anthropic.models.organization.workspaces.WorkspaceRetrieveParams
import com.anthropic.models.organization.workspaces.WorkspaceUpdateParams
import com.anthropic.services.blocking.organization.workspaces.MemberService
import com.anthropic.services.blocking.organization.workspaces.RateLimitService
import com.anthropic.services.blocking.organization.workspaces.ServiceAccountService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface WorkspaceService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceService

    fun rateLimits(): RateLimitService

    fun members(): MemberService

    fun serviceAccounts(): ServiceAccountService

    /** Create Workspace */
    fun create(params: WorkspaceCreateParams): Workspace = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: WorkspaceCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Workspace

    /** Get Workspace */
    fun retrieve(workspaceId: String): Workspace =
        retrieve(workspaceId, WorkspaceRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        workspaceId: String,
        params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Workspace = retrieve(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        workspaceId: String,
        params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
    ): Workspace = retrieve(workspaceId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: WorkspaceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Workspace

    /** @see retrieve */
    fun retrieve(params: WorkspaceRetrieveParams): Workspace =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(workspaceId: String, requestOptions: RequestOptions): Workspace =
        retrieve(workspaceId, WorkspaceRetrieveParams.none(), requestOptions)

    /** Update Workspace */
    fun update(workspaceId: String): Workspace = update(workspaceId, WorkspaceUpdateParams.none())

    /** @see update */
    fun update(
        workspaceId: String,
        params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Workspace = update(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see update */
    fun update(
        workspaceId: String,
        params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
    ): Workspace = update(workspaceId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: WorkspaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Workspace

    /** @see update */
    fun update(params: WorkspaceUpdateParams): Workspace = update(params, RequestOptions.none())

    /** @see update */
    fun update(workspaceId: String, requestOptions: RequestOptions): Workspace =
        update(workspaceId, WorkspaceUpdateParams.none(), requestOptions)

    /** List Workspaces */
    fun list(): WorkspaceListPage = list(WorkspaceListParams.none())

    /** @see list */
    fun list(
        params: WorkspaceListParams = WorkspaceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceListPage

    /** @see list */
    fun list(params: WorkspaceListParams = WorkspaceListParams.none()): WorkspaceListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): WorkspaceListPage =
        list(WorkspaceListParams.none(), requestOptions)

    /** Archive Workspace */
    fun archive(workspaceId: String): Workspace =
        archive(workspaceId, WorkspaceArchiveParams.none())

    /** @see archive */
    fun archive(
        workspaceId: String,
        params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Workspace = archive(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see archive */
    fun archive(
        workspaceId: String,
        params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
    ): Workspace = archive(workspaceId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: WorkspaceArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Workspace

    /** @see archive */
    fun archive(params: WorkspaceArchiveParams): Workspace = archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(workspaceId: String, requestOptions: RequestOptions): Workspace =
        archive(workspaceId, WorkspaceArchiveParams.none(), requestOptions)

    /** A view of [WorkspaceService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceService.WithRawResponse

        fun rateLimits(): RateLimitService.WithRawResponse

        fun members(): MemberService.WithRawResponse

        fun serviceAccounts(): ServiceAccountService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/workspaces`, but is otherwise the
         * same as [WorkspaceService.create].
         */
        @MustBeClosed
        fun create(params: WorkspaceCreateParams): HttpResponseFor<Workspace> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: WorkspaceCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Workspace>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/workspaces/{workspace_id}`, but is
         * otherwise the same as [WorkspaceService.retrieve].
         */
        @MustBeClosed
        fun retrieve(workspaceId: String): HttpResponseFor<Workspace> =
            retrieve(workspaceId, WorkspaceRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            workspaceId: String,
            params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Workspace> =
            retrieve(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            workspaceId: String,
            params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
        ): HttpResponseFor<Workspace> = retrieve(workspaceId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: WorkspaceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Workspace>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: WorkspaceRetrieveParams): HttpResponseFor<Workspace> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Workspace> =
            retrieve(workspaceId, WorkspaceRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/workspaces/{workspace_id}`, but
         * is otherwise the same as [WorkspaceService.update].
         */
        @MustBeClosed
        fun update(workspaceId: String): HttpResponseFor<Workspace> =
            update(workspaceId, WorkspaceUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            workspaceId: String,
            params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Workspace> =
            update(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            workspaceId: String,
            params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
        ): HttpResponseFor<Workspace> = update(workspaceId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: WorkspaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Workspace>

        /** @see update */
        @MustBeClosed
        fun update(params: WorkspaceUpdateParams): HttpResponseFor<Workspace> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Workspace> =
            update(workspaceId, WorkspaceUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/workspaces`, but is otherwise the
         * same as [WorkspaceService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<WorkspaceListPage> = list(WorkspaceListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: WorkspaceListParams = WorkspaceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: WorkspaceListParams = WorkspaceListParams.none()
        ): HttpResponseFor<WorkspaceListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<WorkspaceListPage> =
            list(WorkspaceListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/workspaces/{workspace_id}/archive`, but is otherwise the same as
         * [WorkspaceService.archive].
         */
        @MustBeClosed
        fun archive(workspaceId: String): HttpResponseFor<Workspace> =
            archive(workspaceId, WorkspaceArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            workspaceId: String,
            params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Workspace> =
            archive(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            workspaceId: String,
            params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
        ): HttpResponseFor<Workspace> = archive(workspaceId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: WorkspaceArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Workspace>

        /** @see archive */
        @MustBeClosed
        fun archive(params: WorkspaceArchiveParams): HttpResponseFor<Workspace> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Workspace> =
            archive(workspaceId, WorkspaceArchiveParams.none(), requestOptions)
    }
}
