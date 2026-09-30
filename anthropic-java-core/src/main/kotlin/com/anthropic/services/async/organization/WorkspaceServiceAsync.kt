package com.anthropic.services.async.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.workspaces.Workspace
import com.anthropic.models.organization.workspaces.WorkspaceArchiveParams
import com.anthropic.models.organization.workspaces.WorkspaceCreateParams
import com.anthropic.models.organization.workspaces.WorkspaceListPageAsync
import com.anthropic.models.organization.workspaces.WorkspaceListParams
import com.anthropic.models.organization.workspaces.WorkspaceRetrieveParams
import com.anthropic.models.organization.workspaces.WorkspaceUpdateParams
import com.anthropic.services.async.organization.workspaces.MemberServiceAsync
import com.anthropic.services.async.organization.workspaces.RateLimitServiceAsync
import com.anthropic.services.async.organization.workspaces.ServiceAccountServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface WorkspaceServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceServiceAsync

    fun rateLimits(): RateLimitServiceAsync

    fun members(): MemberServiceAsync

    fun serviceAccounts(): ServiceAccountServiceAsync

    /** Create Workspace */
    fun create(params: WorkspaceCreateParams): CompletableFuture<Workspace> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: WorkspaceCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Workspace>

    /** Get Workspace */
    fun retrieve(workspaceId: String): CompletableFuture<Workspace> =
        retrieve(workspaceId, WorkspaceRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        workspaceId: String,
        params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Workspace> =
        retrieve(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        workspaceId: String,
        params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
    ): CompletableFuture<Workspace> = retrieve(workspaceId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: WorkspaceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Workspace>

    /** @see retrieve */
    fun retrieve(params: WorkspaceRetrieveParams): CompletableFuture<Workspace> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        workspaceId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<Workspace> =
        retrieve(workspaceId, WorkspaceRetrieveParams.none(), requestOptions)

    /** Update Workspace */
    fun update(workspaceId: String): CompletableFuture<Workspace> =
        update(workspaceId, WorkspaceUpdateParams.none())

    /** @see update */
    fun update(
        workspaceId: String,
        params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Workspace> =
        update(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see update */
    fun update(
        workspaceId: String,
        params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
    ): CompletableFuture<Workspace> = update(workspaceId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: WorkspaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Workspace>

    /** @see update */
    fun update(params: WorkspaceUpdateParams): CompletableFuture<Workspace> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(workspaceId: String, requestOptions: RequestOptions): CompletableFuture<Workspace> =
        update(workspaceId, WorkspaceUpdateParams.none(), requestOptions)

    /** List Workspaces */
    fun list(): CompletableFuture<WorkspaceListPageAsync> = list(WorkspaceListParams.none())

    /** @see list */
    fun list(
        params: WorkspaceListParams = WorkspaceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceListPageAsync>

    /** @see list */
    fun list(
        params: WorkspaceListParams = WorkspaceListParams.none()
    ): CompletableFuture<WorkspaceListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<WorkspaceListPageAsync> =
        list(WorkspaceListParams.none(), requestOptions)

    /** Archive Workspace */
    fun archive(workspaceId: String): CompletableFuture<Workspace> =
        archive(workspaceId, WorkspaceArchiveParams.none())

    /** @see archive */
    fun archive(
        workspaceId: String,
        params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Workspace> =
        archive(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see archive */
    fun archive(
        workspaceId: String,
        params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
    ): CompletableFuture<Workspace> = archive(workspaceId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: WorkspaceArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Workspace>

    /** @see archive */
    fun archive(params: WorkspaceArchiveParams): CompletableFuture<Workspace> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(workspaceId: String, requestOptions: RequestOptions): CompletableFuture<Workspace> =
        archive(workspaceId, WorkspaceArchiveParams.none(), requestOptions)

    /**
     * A view of [WorkspaceServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WorkspaceServiceAsync.WithRawResponse

        fun rateLimits(): RateLimitServiceAsync.WithRawResponse

        fun members(): MemberServiceAsync.WithRawResponse

        fun serviceAccounts(): ServiceAccountServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/workspaces`, but is otherwise the
         * same as [WorkspaceServiceAsync.create].
         */
        fun create(params: WorkspaceCreateParams): CompletableFuture<HttpResponseFor<Workspace>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: WorkspaceCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Workspace>>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/workspaces/{workspace_id}`, but is
         * otherwise the same as [WorkspaceServiceAsync.retrieve].
         */
        fun retrieve(workspaceId: String): CompletableFuture<HttpResponseFor<Workspace>> =
            retrieve(workspaceId, WorkspaceRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            workspaceId: String,
            params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Workspace>> =
            retrieve(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            workspaceId: String,
            params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<Workspace>> =
            retrieve(workspaceId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: WorkspaceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Workspace>>

        /** @see retrieve */
        fun retrieve(
            params: WorkspaceRetrieveParams
        ): CompletableFuture<HttpResponseFor<Workspace>> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Workspace>> =
            retrieve(workspaceId, WorkspaceRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/workspaces/{workspace_id}`, but
         * is otherwise the same as [WorkspaceServiceAsync.update].
         */
        fun update(workspaceId: String): CompletableFuture<HttpResponseFor<Workspace>> =
            update(workspaceId, WorkspaceUpdateParams.none())

        /** @see update */
        fun update(
            workspaceId: String,
            params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Workspace>> =
            update(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see update */
        fun update(
            workspaceId: String,
            params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<Workspace>> =
            update(workspaceId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: WorkspaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Workspace>>

        /** @see update */
        fun update(params: WorkspaceUpdateParams): CompletableFuture<HttpResponseFor<Workspace>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Workspace>> =
            update(workspaceId, WorkspaceUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/workspaces`, but is otherwise the
         * same as [WorkspaceServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(WorkspaceListParams.none())

        /** @see list */
        fun list(
            params: WorkspaceListParams = WorkspaceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>>

        /** @see list */
        fun list(
            params: WorkspaceListParams = WorkspaceListParams.none()
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(WorkspaceListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/workspaces/{workspace_id}/archive`, but is otherwise the same as
         * [WorkspaceServiceAsync.archive].
         */
        fun archive(workspaceId: String): CompletableFuture<HttpResponseFor<Workspace>> =
            archive(workspaceId, WorkspaceArchiveParams.none())

        /** @see archive */
        fun archive(
            workspaceId: String,
            params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Workspace>> =
            archive(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see archive */
        fun archive(
            workspaceId: String,
            params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<Workspace>> =
            archive(workspaceId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: WorkspaceArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Workspace>>

        /** @see archive */
        fun archive(params: WorkspaceArchiveParams): CompletableFuture<HttpResponseFor<Workspace>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Workspace>> =
            archive(workspaceId, WorkspaceArchiveParams.none(), requestOptions)
    }
}
