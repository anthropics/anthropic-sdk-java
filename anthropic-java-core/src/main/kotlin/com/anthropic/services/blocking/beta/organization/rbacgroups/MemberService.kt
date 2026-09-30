package com.anthropic.services.blocking.beta.organization.rbacgroups

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.rbacgroups.members.BetaRbacGroupMember
import com.anthropic.models.beta.organization.rbacgroups.members.MemberAddParams
import com.anthropic.models.beta.organization.rbacgroups.members.MemberListPage
import com.anthropic.models.beta.organization.rbacgroups.members.MemberListParams
import com.anthropic.models.beta.organization.rbacgroups.members.MemberRemoveParams
import com.anthropic.models.beta.organization.rbacgroups.members.MemberRemoveResponse
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

    /**
     * List members of an RBAC Group.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun list(rbacGroupId: String): MemberListPage = list(rbacGroupId, MemberListParams.none())

    /** @see list */
    fun list(
        rbacGroupId: String,
        params: MemberListParams = MemberListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MemberListPage = list(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see list */
    fun list(
        rbacGroupId: String,
        params: MemberListParams = MemberListParams.none(),
    ): MemberListPage = list(rbacGroupId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: MemberListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MemberListPage

    /** @see list */
    fun list(params: MemberListParams): MemberListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(rbacGroupId: String, requestOptions: RequestOptions): MemberListPage =
        list(rbacGroupId, MemberListParams.none(), requestOptions)

    /**
     * Add a User to an RBAC Group. Membership of groups provisioned by an identity provider (source
     * type `"scim"`) cannot be modified via the API while an organization in the tenant uses SCIM
     * provisioning.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun add(rbacGroupId: String, params: MemberAddParams): BetaRbacGroupMember =
        add(rbacGroupId, params, RequestOptions.none())

    /** @see add */
    fun add(
        rbacGroupId: String,
        params: MemberAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaRbacGroupMember =
        add(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see add */
    fun add(params: MemberAddParams): BetaRbacGroupMember = add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: MemberAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaRbacGroupMember

    /**
     * Remove a User from an RBAC Group. Membership of groups provisioned by an identity provider
     * (source type `"scim"`) cannot be modified via the API while an organization in the tenant
     * uses SCIM provisioning.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
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
         * /v1/organizations/rbac_groups/{rbac_group_id}/members?beta=true`, but is otherwise the
         * same as [MemberService.list].
         */
        @MustBeClosed
        fun list(rbacGroupId: String): HttpResponseFor<MemberListPage> =
            list(rbacGroupId, MemberListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            rbacGroupId: String,
            params: MemberListParams = MemberListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MemberListPage> =
            list(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            rbacGroupId: String,
            params: MemberListParams = MemberListParams.none(),
        ): HttpResponseFor<MemberListPage> = list(rbacGroupId, params, RequestOptions.none())

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
            rbacGroupId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MemberListPage> =
            list(rbacGroupId, MemberListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/rbac_groups/{rbac_group_id}/members?beta=true`, but is otherwise the
         * same as [MemberService.add].
         */
        @MustBeClosed
        fun add(
            rbacGroupId: String,
            params: MemberAddParams,
        ): HttpResponseFor<BetaRbacGroupMember> = add(rbacGroupId, params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            rbacGroupId: String,
            params: MemberAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaRbacGroupMember> =
            add(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see add */
        @MustBeClosed
        fun add(params: MemberAddParams): HttpResponseFor<BetaRbacGroupMember> =
            add(params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            params: MemberAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaRbacGroupMember>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/rbac_groups/{rbac_group_id}/members/{user_id}?beta=true`, but is
         * otherwise the same as [MemberService.remove].
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
