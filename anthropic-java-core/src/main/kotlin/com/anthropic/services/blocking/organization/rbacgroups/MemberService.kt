package com.anthropic.services.blocking.organization.rbacgroups

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.rbacgroups.members.DeletedRbacGroupMember
import com.anthropic.models.organization.rbacgroups.members.MemberAddParams
import com.anthropic.models.organization.rbacgroups.members.MemberListPage
import com.anthropic.models.organization.rbacgroups.members.MemberListParams
import com.anthropic.models.organization.rbacgroups.members.MemberRemoveParams
import com.anthropic.models.organization.rbacgroups.members.RbacGroupMember
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
     *
     * @throws com.anthropic.models.organization.rbacgroups.RbacGroupGrantsOwnerAccessException A
     *   role attached to an RBAC Group in the request grants owner access, so this API does not add
     *   members to that group (directly or through an invite), remove its members, or delete it.
     *   Make the change in the organization's admin settings in Claude.
     * @throws com.anthropic.models.organization.rbacgroups.RbacGroupOwnerAccessUnconfirmedException
     *   The API could not finish checking whether an RBAC Group in the request grants owner access
     *   (the check reached a fixed limit), so it made no change. Retrying the same request will not
     *   help. Make the change in the organization's admin settings in Claude.
     */
    fun add(rbacGroupId: String, params: MemberAddParams): RbacGroupMember =
        add(rbacGroupId, params, RequestOptions.none())

    /** @see add */
    fun add(
        rbacGroupId: String,
        params: MemberAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RbacGroupMember = add(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see add */
    fun add(params: MemberAddParams): RbacGroupMember = add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: MemberAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RbacGroupMember

    /**
     * Remove a User from an RBAC Group. Membership of groups provisioned by an identity provider
     * (source type `"scim"`) cannot be modified via the API while an organization in the tenant
     * uses SCIM provisioning.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     *
     * @throws com.anthropic.models.organization.rbacgroups.RbacGroupGrantsOwnerAccessException A
     *   role attached to an RBAC Group in the request grants owner access, so this API does not add
     *   members to that group (directly or through an invite), remove its members, or delete it.
     *   Make the change in the organization's admin settings in Claude.
     * @throws com.anthropic.models.organization.rbacgroups.RbacGroupOwnerAccessUnconfirmedException
     *   The API could not finish checking whether an RBAC Group in the request grants owner access
     *   (the check reached a fixed limit), so it made no change. Retrying the same request will not
     *   help. Make the change in the organization's admin settings in Claude.
     */
    fun remove(userId: String, params: MemberRemoveParams): DeletedRbacGroupMember =
        remove(userId, params, RequestOptions.none())

    /** @see remove */
    fun remove(
        userId: String,
        params: MemberRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedRbacGroupMember = remove(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see remove */
    fun remove(params: MemberRemoveParams): DeletedRbacGroupMember =
        remove(params, RequestOptions.none())

    /** @see remove */
    fun remove(
        params: MemberRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedRbacGroupMember

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
         * /v1/organizations/rbac_groups/{rbac_group_id}/members`, but is otherwise the same as
         * [MemberService.list].
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
         * /v1/organizations/rbac_groups/{rbac_group_id}/members`, but is otherwise the same as
         * [MemberService.add].
         */
        @MustBeClosed
        fun add(rbacGroupId: String, params: MemberAddParams): HttpResponseFor<RbacGroupMember> =
            add(rbacGroupId, params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            rbacGroupId: String,
            params: MemberAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RbacGroupMember> =
            add(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see add */
        @MustBeClosed
        fun add(params: MemberAddParams): HttpResponseFor<RbacGroupMember> =
            add(params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            params: MemberAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RbacGroupMember>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/rbac_groups/{rbac_group_id}/members/{user_id}`, but is otherwise the
         * same as [MemberService.remove].
         */
        @MustBeClosed
        fun remove(
            userId: String,
            params: MemberRemoveParams,
        ): HttpResponseFor<DeletedRbacGroupMember> = remove(userId, params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            userId: String,
            params: MemberRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedRbacGroupMember> =
            remove(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see remove */
        @MustBeClosed
        fun remove(params: MemberRemoveParams): HttpResponseFor<DeletedRbacGroupMember> =
            remove(params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            params: MemberRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedRbacGroupMember>
    }
}
