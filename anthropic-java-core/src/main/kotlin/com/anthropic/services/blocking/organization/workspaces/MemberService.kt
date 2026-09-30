package com.anthropic.services.blocking.organization.workspaces

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.workspaces.WorkspaceMember
import com.anthropic.models.organization.workspaces.members.MemberAddParams
import com.anthropic.models.organization.workspaces.members.MemberListPage
import com.anthropic.models.organization.workspaces.members.MemberListParams
import com.anthropic.models.organization.workspaces.members.MemberRemoveParams
import com.anthropic.models.organization.workspaces.members.MemberRemoveResponse
import com.anthropic.models.organization.workspaces.members.MemberRetrieveParams
import com.anthropic.models.organization.workspaces.members.MemberUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface MemberService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemberService

    /** Get Workspace Member */
    fun retrieve(userId: String, params: MemberRetrieveParams): WorkspaceMember =
        retrieve(userId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        userId: String,
        params: MemberRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceMember = retrieve(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: MemberRetrieveParams): WorkspaceMember =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: MemberRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceMember

    /** Update Workspace Member */
    fun update(userId: String, params: MemberUpdateParams): WorkspaceMember =
        update(userId, params, RequestOptions.none())

    /** @see update */
    fun update(
        userId: String,
        params: MemberUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceMember = update(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see update */
    fun update(params: MemberUpdateParams): WorkspaceMember = update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: MemberUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceMember

    /** List Workspace Members */
    fun list(workspaceId: String): MemberListPage = list(workspaceId, MemberListParams.none())

    /** @see list */
    fun list(
        workspaceId: String,
        params: MemberListParams = MemberListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MemberListPage = list(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see list */
    fun list(
        workspaceId: String,
        params: MemberListParams = MemberListParams.none(),
    ): MemberListPage = list(workspaceId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: MemberListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MemberListPage

    /** @see list */
    fun list(params: MemberListParams): MemberListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(workspaceId: String, requestOptions: RequestOptions): MemberListPage =
        list(workspaceId, MemberListParams.none(), requestOptions)

    /** Create Workspace Member */
    fun add(workspaceId: String, params: MemberAddParams): WorkspaceMember =
        add(workspaceId, params, RequestOptions.none())

    /** @see add */
    fun add(
        workspaceId: String,
        params: MemberAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceMember = add(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see add */
    fun add(params: MemberAddParams): WorkspaceMember = add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: MemberAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceMember

    /** Delete Workspace Member */
    fun remove(userId: String, params: MemberRemoveParams): MemberRemoveResponse =
        remove(userId, params, RequestOptions.none())

    /** @see remove */
    fun remove(
        userId: String,
        params: MemberRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MemberRemoveResponse = remove(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see remove */
    fun remove(params: MemberRemoveParams): MemberRemoveResponse =
        remove(params, RequestOptions.none())

    /** @see remove */
    fun remove(
        params: MemberRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MemberRemoveResponse

    /** A view of [MemberService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemberService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/workspaces/{workspace_id}/members/{user_id}`, but is otherwise the same
         * as [MemberService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            userId: String,
            params: MemberRetrieveParams,
        ): HttpResponseFor<WorkspaceMember> = retrieve(userId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userId: String,
            params: MemberRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceMember> =
            retrieve(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: MemberRetrieveParams): HttpResponseFor<WorkspaceMember> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: MemberRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceMember>

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/workspaces/{workspace_id}/members/{user_id}`, but is otherwise the same
         * as [MemberService.update].
         */
        @MustBeClosed
        fun update(userId: String, params: MemberUpdateParams): HttpResponseFor<WorkspaceMember> =
            update(userId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            userId: String,
            params: MemberUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceMember> =
            update(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(params: MemberUpdateParams): HttpResponseFor<WorkspaceMember> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: MemberUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceMember>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/workspaces/{workspace_id}/members`, but is otherwise the same as
         * [MemberService.list].
         */
        @MustBeClosed
        fun list(workspaceId: String): HttpResponseFor<MemberListPage> =
            list(workspaceId, MemberListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            workspaceId: String,
            params: MemberListParams = MemberListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MemberListPage> =
            list(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            workspaceId: String,
            params: MemberListParams = MemberListParams.none(),
        ): HttpResponseFor<MemberListPage> = list(workspaceId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: MemberListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MemberListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: MemberListParams): HttpResponseFor<MemberListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MemberListPage> =
            list(workspaceId, MemberListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/workspaces/{workspace_id}/members`, but is otherwise the same as
         * [MemberService.add].
         */
        @MustBeClosed
        fun add(workspaceId: String, params: MemberAddParams): HttpResponseFor<WorkspaceMember> =
            add(workspaceId, params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            workspaceId: String,
            params: MemberAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceMember> =
            add(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see add */
        @MustBeClosed
        fun add(params: MemberAddParams): HttpResponseFor<WorkspaceMember> =
            add(params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            params: MemberAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceMember>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/workspaces/{workspace_id}/members/{user_id}`, but is otherwise the same
         * as [MemberService.remove].
         */
        @MustBeClosed
        fun remove(
            userId: String,
            params: MemberRemoveParams,
        ): HttpResponseFor<MemberRemoveResponse> = remove(userId, params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            userId: String,
            params: MemberRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MemberRemoveResponse> =
            remove(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see remove */
        @MustBeClosed
        fun remove(params: MemberRemoveParams): HttpResponseFor<MemberRemoveResponse> =
            remove(params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            params: MemberRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MemberRemoveResponse>
    }
}
