// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.workspaces.BetaWorkspace
import com.anthropic.models.beta.organization.workspaces.WorkspaceArchiveParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceCreateParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceListPage
import com.anthropic.models.beta.organization.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceRetrieveParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceUpdateParams
import com.anthropic.services.blocking.beta.organization.workspaces.MemberService
import com.anthropic.services.blocking.beta.organization.workspaces.RateLimitService
import com.anthropic.services.blocking.beta.organization.workspaces.ServiceAccountService
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
    fun create(params: WorkspaceCreateParams): BetaWorkspace = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: WorkspaceCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaWorkspace

    /** Get Workspace */
    fun retrieve(workspaceId: String): BetaWorkspace =
        retrieve(workspaceId, WorkspaceRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        workspaceId: String,
        params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaWorkspace = retrieve(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        workspaceId: String,
        params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
    ): BetaWorkspace = retrieve(workspaceId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: WorkspaceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaWorkspace

    /** @see retrieve */
    fun retrieve(params: WorkspaceRetrieveParams): BetaWorkspace =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(workspaceId: String, requestOptions: RequestOptions): BetaWorkspace =
        retrieve(workspaceId, WorkspaceRetrieveParams.none(), requestOptions)

    /** Update Workspace */
    fun update(workspaceId: String): BetaWorkspace =
        update(workspaceId, WorkspaceUpdateParams.none())

    /** @see update */
    fun update(
        workspaceId: String,
        params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaWorkspace = update(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see update */
    fun update(
        workspaceId: String,
        params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
    ): BetaWorkspace = update(workspaceId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: WorkspaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaWorkspace

    /** @see update */
    fun update(params: WorkspaceUpdateParams): BetaWorkspace = update(params, RequestOptions.none())

    /** @see update */
    fun update(workspaceId: String, requestOptions: RequestOptions): BetaWorkspace =
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
    fun archive(workspaceId: String): BetaWorkspace =
        archive(workspaceId, WorkspaceArchiveParams.none())

    /** @see archive */
    fun archive(
        workspaceId: String,
        params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaWorkspace = archive(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see archive */
    fun archive(
        workspaceId: String,
        params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
    ): BetaWorkspace = archive(workspaceId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: WorkspaceArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaWorkspace

    /** @see archive */
    fun archive(params: WorkspaceArchiveParams): BetaWorkspace =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(workspaceId: String, requestOptions: RequestOptions): BetaWorkspace =
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
         * Returns a raw HTTP response for `post /v1/organizations/workspaces?beta=true`, but is
         * otherwise the same as [WorkspaceService.create].
         */
        @MustBeClosed
        fun create(params: WorkspaceCreateParams): HttpResponseFor<BetaWorkspace> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: WorkspaceCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaWorkspace>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/workspaces/{workspace_id}?beta=true`, but is otherwise the same as
         * [WorkspaceService.retrieve].
         */
        @MustBeClosed
        fun retrieve(workspaceId: String): HttpResponseFor<BetaWorkspace> =
            retrieve(workspaceId, WorkspaceRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            workspaceId: String,
            params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaWorkspace> =
            retrieve(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            workspaceId: String,
            params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
        ): HttpResponseFor<BetaWorkspace> = retrieve(workspaceId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: WorkspaceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaWorkspace>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: WorkspaceRetrieveParams): HttpResponseFor<BetaWorkspace> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaWorkspace> =
            retrieve(workspaceId, WorkspaceRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/workspaces/{workspace_id}?beta=true`, but is otherwise the same as
         * [WorkspaceService.update].
         */
        @MustBeClosed
        fun update(workspaceId: String): HttpResponseFor<BetaWorkspace> =
            update(workspaceId, WorkspaceUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            workspaceId: String,
            params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaWorkspace> =
            update(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            workspaceId: String,
            params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
        ): HttpResponseFor<BetaWorkspace> = update(workspaceId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: WorkspaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaWorkspace>

        /** @see update */
        @MustBeClosed
        fun update(params: WorkspaceUpdateParams): HttpResponseFor<BetaWorkspace> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaWorkspace> =
            update(workspaceId, WorkspaceUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/workspaces?beta=true`, but is
         * otherwise the same as [WorkspaceService.list].
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
         * /v1/organizations/workspaces/{workspace_id}/archive?beta=true`, but is otherwise the same
         * as [WorkspaceService.archive].
         */
        @MustBeClosed
        fun archive(workspaceId: String): HttpResponseFor<BetaWorkspace> =
            archive(workspaceId, WorkspaceArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            workspaceId: String,
            params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaWorkspace> =
            archive(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            workspaceId: String,
            params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
        ): HttpResponseFor<BetaWorkspace> = archive(workspaceId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: WorkspaceArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaWorkspace>

        /** @see archive */
        @MustBeClosed
        fun archive(params: WorkspaceArchiveParams): HttpResponseFor<BetaWorkspace> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaWorkspace> =
            archive(workspaceId, WorkspaceArchiveParams.none(), requestOptions)
    }
}
