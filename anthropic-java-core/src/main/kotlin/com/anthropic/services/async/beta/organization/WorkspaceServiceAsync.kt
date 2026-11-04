package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.workspaces.BetaWorkspace
import com.anthropic.models.beta.organization.workspaces.WorkspaceArchiveParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceCreateParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceListPageAsync
import com.anthropic.models.beta.organization.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceRetrieveParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceUpdateParams
import com.anthropic.services.async.beta.organization.workspaces.MemberServiceAsync
import com.anthropic.services.async.beta.organization.workspaces.RateLimitServiceAsync
import com.anthropic.services.async.beta.organization.workspaces.ServiceAccountServiceAsync
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

    /**
     * Create Workspace
     *
     * @throws com.anthropic.models.organization.workspaces.KeyProfileMismatchException The External
     *   Key is bound to a user profile, and the Workspace does not have that user profile. A
     *   Workspace can use only a key bound to its own user profile or a key that isn't bound to
     *   one.
     * @throws com.anthropic.models.userprofiles.UserProfileNotFoundException The request depends on
     *   a user profile that does not exist in the organization or is no longer active. The request
     *   either names this user profile by ID, or names a resource, such as an External Key, that is
     *   bound to it.
     * @throws com.anthropic.models.organization.workspaces.WorkspacePolicyViolationException The
     *   request breaks the organization's Workspace Policy. The message says which rule it broke;
     *   an organization admin can see and change the policy in the Console.
     * @throws com.anthropic.models.organization.externalkeys.ExternalKeyChangedException The
     *   external key changed while the request was validating it. Send the request again.
     */
    fun create(params: WorkspaceCreateParams): CompletableFuture<BetaWorkspace> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: WorkspaceCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaWorkspace>

    /** Get Workspace */
    fun retrieve(workspaceId: String): CompletableFuture<BetaWorkspace> =
        retrieve(workspaceId, WorkspaceRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        workspaceId: String,
        params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaWorkspace> =
        retrieve(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        workspaceId: String,
        params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
    ): CompletableFuture<BetaWorkspace> = retrieve(workspaceId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: WorkspaceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaWorkspace>

    /** @see retrieve */
    fun retrieve(params: WorkspaceRetrieveParams): CompletableFuture<BetaWorkspace> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        workspaceId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaWorkspace> =
        retrieve(workspaceId, WorkspaceRetrieveParams.none(), requestOptions)

    /**
     * Update Workspace
     *
     * @throws com.anthropic.models.organization.workspaces.KeyProfileMismatchException The External
     *   Key is bound to a user profile, and the Workspace does not have that user profile. A
     *   Workspace can use only a key bound to its own user profile or a key that isn't bound to
     *   one.
     * @throws com.anthropic.models.userprofiles.UserProfileNotFoundException The request depends on
     *   a user profile that does not exist in the organization or is no longer active. The request
     *   either names this user profile by ID, or names a resource, such as an External Key, that is
     *   bound to it.
     * @throws com.anthropic.models.organization.workspaces.WorkspacePolicyViolationException The
     *   request breaks the organization's Workspace Policy. The message says which rule it broke;
     *   an organization admin can see and change the policy in the Console.
     * @throws com.anthropic.models.organization.externalkeys.ExternalKeyChangedException The
     *   external key changed while the request was validating it. Send the request again.
     */
    fun update(workspaceId: String): CompletableFuture<BetaWorkspace> =
        update(workspaceId, WorkspaceUpdateParams.none())

    /** @see update */
    fun update(
        workspaceId: String,
        params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaWorkspace> =
        update(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see update */
    fun update(
        workspaceId: String,
        params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
    ): CompletableFuture<BetaWorkspace> = update(workspaceId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: WorkspaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaWorkspace>

    /** @see update */
    fun update(params: WorkspaceUpdateParams): CompletableFuture<BetaWorkspace> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        workspaceId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaWorkspace> =
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
    fun archive(workspaceId: String): CompletableFuture<BetaWorkspace> =
        archive(workspaceId, WorkspaceArchiveParams.none())

    /** @see archive */
    fun archive(
        workspaceId: String,
        params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaWorkspace> =
        archive(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see archive */
    fun archive(
        workspaceId: String,
        params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
    ): CompletableFuture<BetaWorkspace> = archive(workspaceId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: WorkspaceArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaWorkspace>

    /** @see archive */
    fun archive(params: WorkspaceArchiveParams): CompletableFuture<BetaWorkspace> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        workspaceId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaWorkspace> =
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
        fun create(
            params: WorkspaceCreateParams
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> = create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: WorkspaceCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/workspaces/{workspace_id}`, but is
         * otherwise the same as [WorkspaceServiceAsync.retrieve].
         */
        fun retrieve(workspaceId: String): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            retrieve(workspaceId, WorkspaceRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            workspaceId: String,
            params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            retrieve(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            workspaceId: String,
            params: WorkspaceRetrieveParams = WorkspaceRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            retrieve(workspaceId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: WorkspaceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>>

        /** @see retrieve */
        fun retrieve(
            params: WorkspaceRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            retrieve(workspaceId, WorkspaceRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/workspaces/{workspace_id}`, but
         * is otherwise the same as [WorkspaceServiceAsync.update].
         */
        fun update(workspaceId: String): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            update(workspaceId, WorkspaceUpdateParams.none())

        /** @see update */
        fun update(
            workspaceId: String,
            params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            update(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see update */
        fun update(
            workspaceId: String,
            params: WorkspaceUpdateParams = WorkspaceUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            update(workspaceId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: WorkspaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>>

        /** @see update */
        fun update(
            params: WorkspaceUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> = update(params, RequestOptions.none())

        /** @see update */
        fun update(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
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
        fun archive(workspaceId: String): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            archive(workspaceId, WorkspaceArchiveParams.none())

        /** @see archive */
        fun archive(
            workspaceId: String,
            params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            archive(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see archive */
        fun archive(
            workspaceId: String,
            params: WorkspaceArchiveParams = WorkspaceArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            archive(workspaceId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: WorkspaceArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>>

        /** @see archive */
        fun archive(
            params: WorkspaceArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> =
            archive(workspaceId, WorkspaceArchiveParams.none(), requestOptions)
    }
}
