package com.anthropic.services.async.organization.workspaces

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.workspaces.WorkspaceMember
import com.anthropic.models.organization.workspaces.members.MemberAddParams
import com.anthropic.models.organization.workspaces.members.MemberListPageAsync
import com.anthropic.models.organization.workspaces.members.MemberListParams
import com.anthropic.models.organization.workspaces.members.MemberRemoveParams
import com.anthropic.models.organization.workspaces.members.MemberRemoveResponse
import com.anthropic.models.organization.workspaces.members.MemberRetrieveParams
import com.anthropic.models.organization.workspaces.members.MemberUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface MemberServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemberServiceAsync

    /** Get Workspace Member */
    fun retrieve(userId: String, params: MemberRetrieveParams): CompletableFuture<WorkspaceMember> =
        retrieve(userId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        userId: String,
        params: MemberRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceMember> =
        retrieve(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: MemberRetrieveParams): CompletableFuture<WorkspaceMember> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: MemberRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceMember>

    /** Update Workspace Member */
    fun update(userId: String, params: MemberUpdateParams): CompletableFuture<WorkspaceMember> =
        update(userId, params, RequestOptions.none())

    /** @see update */
    fun update(
        userId: String,
        params: MemberUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceMember> =
        update(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see update */
    fun update(params: MemberUpdateParams): CompletableFuture<WorkspaceMember> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: MemberUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceMember>

    /** List Workspace Members */
    fun list(workspaceId: String): CompletableFuture<MemberListPageAsync> =
        list(workspaceId, MemberListParams.none())

    /** @see list */
    fun list(
        workspaceId: String,
        params: MemberListParams = MemberListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MemberListPageAsync> =
        list(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see list */
    fun list(
        workspaceId: String,
        params: MemberListParams = MemberListParams.none(),
    ): CompletableFuture<MemberListPageAsync> = list(workspaceId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: MemberListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MemberListPageAsync>

    /** @see list */
    fun list(params: MemberListParams): CompletableFuture<MemberListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        workspaceId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<MemberListPageAsync> =
        list(workspaceId, MemberListParams.none(), requestOptions)

    /** Create Workspace Member */
    fun add(workspaceId: String, params: MemberAddParams): CompletableFuture<WorkspaceMember> =
        add(workspaceId, params, RequestOptions.none())

    /** @see add */
    fun add(
        workspaceId: String,
        params: MemberAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceMember> =
        add(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see add */
    fun add(params: MemberAddParams): CompletableFuture<WorkspaceMember> =
        add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: MemberAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceMember>

    /** Delete Workspace Member */
    fun remove(
        userId: String,
        params: MemberRemoveParams,
    ): CompletableFuture<MemberRemoveResponse> = remove(userId, params, RequestOptions.none())

    /** @see remove */
    fun remove(
        userId: String,
        params: MemberRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MemberRemoveResponse> =
        remove(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see remove */
    fun remove(params: MemberRemoveParams): CompletableFuture<MemberRemoveResponse> =
        remove(params, RequestOptions.none())

    /** @see remove */
    fun remove(
        params: MemberRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MemberRemoveResponse>

    /**
     * A view of [MemberServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MemberServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/workspaces/{workspace_id}/members/{user_id}`, but is otherwise the same
         * as [MemberServiceAsync.retrieve].
         */
        fun retrieve(
            userId: String,
            params: MemberRetrieveParams,
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>> =
            retrieve(userId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            userId: String,
            params: MemberRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>> =
            retrieve(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            params: MemberRetrieveParams
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: MemberRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>>

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/workspaces/{workspace_id}/members/{user_id}`, but is otherwise the same
         * as [MemberServiceAsync.update].
         */
        fun update(
            userId: String,
            params: MemberUpdateParams,
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>> =
            update(userId, params, RequestOptions.none())

        /** @see update */
        fun update(
            userId: String,
            params: MemberUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>> =
            update(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see update */
        fun update(
            params: MemberUpdateParams
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: MemberUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/workspaces/{workspace_id}/members`, but is otherwise the same as
         * [MemberServiceAsync.list].
         */
        fun list(workspaceId: String): CompletableFuture<HttpResponseFor<MemberListPageAsync>> =
            list(workspaceId, MemberListParams.none())

        /** @see list */
        fun list(
            workspaceId: String,
            params: MemberListParams = MemberListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MemberListPageAsync>> =
            list(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see list */
        fun list(
            workspaceId: String,
            params: MemberListParams = MemberListParams.none(),
        ): CompletableFuture<HttpResponseFor<MemberListPageAsync>> =
            list(workspaceId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: MemberListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MemberListPageAsync>>

        /** @see list */
        fun list(
            params: MemberListParams
        ): CompletableFuture<HttpResponseFor<MemberListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MemberListPageAsync>> =
            list(workspaceId, MemberListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/workspaces/{workspace_id}/members`, but is otherwise the same as
         * [MemberServiceAsync.add].
         */
        fun add(
            workspaceId: String,
            params: MemberAddParams,
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>> =
            add(workspaceId, params, RequestOptions.none())

        /** @see add */
        fun add(
            workspaceId: String,
            params: MemberAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>> =
            add(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see add */
        fun add(params: MemberAddParams): CompletableFuture<HttpResponseFor<WorkspaceMember>> =
            add(params, RequestOptions.none())

        /** @see add */
        fun add(
            params: MemberAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/workspaces/{workspace_id}/members/{user_id}`, but is otherwise the same
         * as [MemberServiceAsync.remove].
         */
        fun remove(
            userId: String,
            params: MemberRemoveParams,
        ): CompletableFuture<HttpResponseFor<MemberRemoveResponse>> =
            remove(userId, params, RequestOptions.none())

        /** @see remove */
        fun remove(
            userId: String,
            params: MemberRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MemberRemoveResponse>> =
            remove(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see remove */
        fun remove(
            params: MemberRemoveParams
        ): CompletableFuture<HttpResponseFor<MemberRemoveResponse>> =
            remove(params, RequestOptions.none())

        /** @see remove */
        fun remove(
            params: MemberRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MemberRemoveResponse>>
    }
}
