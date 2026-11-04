package com.anthropic.services.blocking.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.rbacgroups.DeletedRbacGroup
import com.anthropic.models.organization.rbacgroups.RbacGroup
import com.anthropic.models.organization.rbacgroups.RbacGroupCreateParams
import com.anthropic.models.organization.rbacgroups.RbacGroupDeleteParams
import com.anthropic.models.organization.rbacgroups.RbacGroupListPage
import com.anthropic.models.organization.rbacgroups.RbacGroupListParams
import com.anthropic.models.organization.rbacgroups.RbacGroupRetrieveParams
import com.anthropic.models.organization.rbacgroups.RbacGroupUpdateParams
import com.anthropic.services.blocking.organization.rbacgroups.MemberService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface RbacGroupService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupService

    fun members(): MemberService

    /**
     * Create an RBAC Group in the Claude Enterprise tenant. Groups created via the API have source
     * type `"direct"`.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun create(params: RbacGroupCreateParams): RbacGroup = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: RbacGroupCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RbacGroup

    /**
     * Retrieve an RBAC Group by ID.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun retrieve(rbacGroupId: String): RbacGroup =
        retrieve(rbacGroupId, RbacGroupRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        rbacGroupId: String,
        params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RbacGroup = retrieve(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        rbacGroupId: String,
        params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
    ): RbacGroup = retrieve(rbacGroupId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: RbacGroupRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RbacGroup

    /** @see retrieve */
    fun retrieve(params: RbacGroupRetrieveParams): RbacGroup =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(rbacGroupId: String, requestOptions: RequestOptions): RbacGroup =
        retrieve(rbacGroupId, RbacGroupRetrieveParams.none(), requestOptions)

    /**
     * Update an RBAC Group's name. Groups provisioned by an identity provider (source type
     * `"scim"`) cannot be modified via the API while an organization in the tenant uses SCIM
     * provisioning.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun update(rbacGroupId: String): RbacGroup = update(rbacGroupId, RbacGroupUpdateParams.none())

    /** @see update */
    fun update(
        rbacGroupId: String,
        params: RbacGroupUpdateParams = RbacGroupUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RbacGroup = update(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see update */
    fun update(
        rbacGroupId: String,
        params: RbacGroupUpdateParams = RbacGroupUpdateParams.none(),
    ): RbacGroup = update(rbacGroupId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: RbacGroupUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RbacGroup

    /** @see update */
    fun update(params: RbacGroupUpdateParams): RbacGroup = update(params, RequestOptions.none())

    /** @see update */
    fun update(rbacGroupId: String, requestOptions: RequestOptions): RbacGroup =
        update(rbacGroupId, RbacGroupUpdateParams.none(), requestOptions)

    /**
     * List RBAC Groups in the Claude Enterprise tenant.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun list(): RbacGroupListPage = list(RbacGroupListParams.none())

    /** @see list */
    fun list(
        params: RbacGroupListParams = RbacGroupListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RbacGroupListPage

    /** @see list */
    fun list(params: RbacGroupListParams = RbacGroupListParams.none()): RbacGroupListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): RbacGroupListPage =
        list(RbacGroupListParams.none(), requestOptions)

    /**
     * Delete an RBAC Group. Groups provisioned by an identity provider (source type `"scim"`)
     * cannot be deleted via the API while an organization in the tenant uses SCIM provisioning.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     *
     * @throws com.anthropic.models.organization.rbacgroups.RbacGroupGrantsOwnerAccessException A
     *   role attached to an RBAC Group in the request grants owner access, so this API does not add
     *   members to that group (directly or through an invite), remove its members, or delete it.
     *   Make the change in the organization's admin settings in Claude.
     * @throws com.anthropic.models.organization.rbacgroups.RbacGroupOwnerAccessUnconfirmedException
     *   The API could not finish checking whether an RBAC Group in the request grants owner access,
     *   so it made no change. Retrying the same request will not help. Make the change in the
     *   organization's admin settings in Claude.
     */
    fun delete(rbacGroupId: String): DeletedRbacGroup =
        delete(rbacGroupId, RbacGroupDeleteParams.none())

    /** @see delete */
    fun delete(
        rbacGroupId: String,
        params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedRbacGroup =
        delete(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see delete */
    fun delete(
        rbacGroupId: String,
        params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
    ): DeletedRbacGroup = delete(rbacGroupId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: RbacGroupDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedRbacGroup

    /** @see delete */
    fun delete(params: RbacGroupDeleteParams): DeletedRbacGroup =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(rbacGroupId: String, requestOptions: RequestOptions): DeletedRbacGroup =
        delete(rbacGroupId, RbacGroupDeleteParams.none(), requestOptions)

    /** A view of [RbacGroupService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupService.WithRawResponse

        fun members(): MemberService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/rbac_groups`, but is otherwise
         * the same as [RbacGroupService.create].
         */
        @MustBeClosed
        fun create(params: RbacGroupCreateParams): HttpResponseFor<RbacGroup> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: RbacGroupCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RbacGroup>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/rbac_groups/{rbac_group_id}`, but
         * is otherwise the same as [RbacGroupService.retrieve].
         */
        @MustBeClosed
        fun retrieve(rbacGroupId: String): HttpResponseFor<RbacGroup> =
            retrieve(rbacGroupId, RbacGroupRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            rbacGroupId: String,
            params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RbacGroup> =
            retrieve(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            rbacGroupId: String,
            params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
        ): HttpResponseFor<RbacGroup> = retrieve(rbacGroupId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: RbacGroupRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RbacGroup>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: RbacGroupRetrieveParams): HttpResponseFor<RbacGroup> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            rbacGroupId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<RbacGroup> =
            retrieve(rbacGroupId, RbacGroupRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/rbac_groups/{rbac_group_id}`, but
         * is otherwise the same as [RbacGroupService.update].
         */
        @MustBeClosed
        fun update(rbacGroupId: String): HttpResponseFor<RbacGroup> =
            update(rbacGroupId, RbacGroupUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            rbacGroupId: String,
            params: RbacGroupUpdateParams = RbacGroupUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RbacGroup> =
            update(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            rbacGroupId: String,
            params: RbacGroupUpdateParams = RbacGroupUpdateParams.none(),
        ): HttpResponseFor<RbacGroup> = update(rbacGroupId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: RbacGroupUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RbacGroup>

        /** @see update */
        @MustBeClosed
        fun update(params: RbacGroupUpdateParams): HttpResponseFor<RbacGroup> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            rbacGroupId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<RbacGroup> =
            update(rbacGroupId, RbacGroupUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/rbac_groups`, but is otherwise the
         * same as [RbacGroupService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<RbacGroupListPage> = list(RbacGroupListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: RbacGroupListParams = RbacGroupListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RbacGroupListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: RbacGroupListParams = RbacGroupListParams.none()
        ): HttpResponseFor<RbacGroupListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<RbacGroupListPage> =
            list(RbacGroupListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/organizations/rbac_groups/{rbac_group_id}`,
         * but is otherwise the same as [RbacGroupService.delete].
         */
        @MustBeClosed
        fun delete(rbacGroupId: String): HttpResponseFor<DeletedRbacGroup> =
            delete(rbacGroupId, RbacGroupDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            rbacGroupId: String,
            params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedRbacGroup> =
            delete(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            rbacGroupId: String,
            params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
        ): HttpResponseFor<DeletedRbacGroup> = delete(rbacGroupId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: RbacGroupDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedRbacGroup>

        /** @see delete */
        @MustBeClosed
        fun delete(params: RbacGroupDeleteParams): HttpResponseFor<DeletedRbacGroup> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            rbacGroupId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DeletedRbacGroup> =
            delete(rbacGroupId, RbacGroupDeleteParams.none(), requestOptions)
    }
}
