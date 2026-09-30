package com.anthropic.services.async.beta.organization.rbacgroups

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.rbacgroups.members.BetaRbacGroupMember
import com.anthropic.models.beta.organization.rbacgroups.members.MemberAddParams
import com.anthropic.models.beta.organization.rbacgroups.members.MemberListPageAsync
import com.anthropic.models.beta.organization.rbacgroups.members.MemberListParams
import com.anthropic.models.beta.organization.rbacgroups.members.MemberRemoveParams
import com.anthropic.models.beta.organization.rbacgroups.members.MemberRemoveResponse
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

    /**
     * List members of an RBAC Group.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun list(rbacGroupId: String): CompletableFuture<MemberListPageAsync> =
        list(rbacGroupId, MemberListParams.none())

    /** @see list */
    fun list(
        rbacGroupId: String,
        params: MemberListParams = MemberListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MemberListPageAsync> =
        list(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see list */
    fun list(
        rbacGroupId: String,
        params: MemberListParams = MemberListParams.none(),
    ): CompletableFuture<MemberListPageAsync> = list(rbacGroupId, params, RequestOptions.none())

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
        rbacGroupId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<MemberListPageAsync> =
        list(rbacGroupId, MemberListParams.none(), requestOptions)

    /**
     * Add a User to an RBAC Group. Membership of groups provisioned by an identity provider (source
     * type `"scim"`) cannot be modified via the API while an organization in the tenant uses SCIM
     * provisioning.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun add(rbacGroupId: String, params: MemberAddParams): CompletableFuture<BetaRbacGroupMember> =
        add(rbacGroupId, params, RequestOptions.none())

    /** @see add */
    fun add(
        rbacGroupId: String,
        params: MemberAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaRbacGroupMember> =
        add(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see add */
    fun add(params: MemberAddParams): CompletableFuture<BetaRbacGroupMember> =
        add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: MemberAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaRbacGroupMember>

    /**
     * Remove a User from an RBAC Group. Membership of groups provisioned by an identity provider
     * (source type `"scim"`) cannot be modified via the API while an organization in the tenant
     * uses SCIM provisioning.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
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
         * /v1/organizations/rbac_groups/{rbac_group_id}/members?beta=true`, but is otherwise the
         * same as [MemberServiceAsync.list].
         */
        fun list(rbacGroupId: String): CompletableFuture<HttpResponseFor<MemberListPageAsync>> =
            list(rbacGroupId, MemberListParams.none())

        /** @see list */
        fun list(
            rbacGroupId: String,
            params: MemberListParams = MemberListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MemberListPageAsync>> =
            list(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see list */
        fun list(
            rbacGroupId: String,
            params: MemberListParams = MemberListParams.none(),
        ): CompletableFuture<HttpResponseFor<MemberListPageAsync>> =
            list(rbacGroupId, params, RequestOptions.none())

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
            rbacGroupId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MemberListPageAsync>> =
            list(rbacGroupId, MemberListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/rbac_groups/{rbac_group_id}/members?beta=true`, but is otherwise the
         * same as [MemberServiceAsync.add].
         */
        fun add(
            rbacGroupId: String,
            params: MemberAddParams,
        ): CompletableFuture<HttpResponseFor<BetaRbacGroupMember>> =
            add(rbacGroupId, params, RequestOptions.none())

        /** @see add */
        fun add(
            rbacGroupId: String,
            params: MemberAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacGroupMember>> =
            add(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see add */
        fun add(params: MemberAddParams): CompletableFuture<HttpResponseFor<BetaRbacGroupMember>> =
            add(params, RequestOptions.none())

        /** @see add */
        fun add(
            params: MemberAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacGroupMember>>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/rbac_groups/{rbac_group_id}/members/{user_id}?beta=true`, but is
         * otherwise the same as [MemberServiceAsync.remove].
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
