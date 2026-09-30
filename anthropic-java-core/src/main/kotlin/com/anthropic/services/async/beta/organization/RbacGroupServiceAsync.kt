package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.rbacgroups.BetaRbacGroup
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupCreateParams
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupDeleteParams
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupDeleteResponse
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupListPageAsync
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupListParams
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupRetrieveParams
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupUpdateParams
import com.anthropic.services.async.beta.organization.rbacgroups.MemberServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface RbacGroupServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupServiceAsync

    fun members(): MemberServiceAsync

    /**
     * Create an RBAC Group in the Claude Enterprise tenant. Groups created via the API have source
     * type `"direct"`.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun create(params: RbacGroupCreateParams): CompletableFuture<BetaRbacGroup> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: RbacGroupCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaRbacGroup>

    /**
     * Retrieve an RBAC Group by ID.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun retrieve(rbacGroupId: String): CompletableFuture<BetaRbacGroup> =
        retrieve(rbacGroupId, RbacGroupRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        rbacGroupId: String,
        params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaRbacGroup> =
        retrieve(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        rbacGroupId: String,
        params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
    ): CompletableFuture<BetaRbacGroup> = retrieve(rbacGroupId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: RbacGroupRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaRbacGroup>

    /** @see retrieve */
    fun retrieve(params: RbacGroupRetrieveParams): CompletableFuture<BetaRbacGroup> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        rbacGroupId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaRbacGroup> =
        retrieve(rbacGroupId, RbacGroupRetrieveParams.none(), requestOptions)

    /**
     * Update an RBAC Group's name. Groups provisioned by an identity provider (source type
     * `"scim"`) cannot be modified via the API while an organization in the tenant uses SCIM
     * provisioning.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun update(rbacGroupId: String): CompletableFuture<BetaRbacGroup> =
        update(rbacGroupId, RbacGroupUpdateParams.none())

    /** @see update */
    fun update(
        rbacGroupId: String,
        params: RbacGroupUpdateParams = RbacGroupUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaRbacGroup> =
        update(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see update */
    fun update(
        rbacGroupId: String,
        params: RbacGroupUpdateParams = RbacGroupUpdateParams.none(),
    ): CompletableFuture<BetaRbacGroup> = update(rbacGroupId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: RbacGroupUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaRbacGroup>

    /** @see update */
    fun update(params: RbacGroupUpdateParams): CompletableFuture<BetaRbacGroup> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        rbacGroupId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaRbacGroup> =
        update(rbacGroupId, RbacGroupUpdateParams.none(), requestOptions)

    /**
     * List RBAC Groups in the Claude Enterprise tenant.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun list(): CompletableFuture<RbacGroupListPageAsync> = list(RbacGroupListParams.none())

    /** @see list */
    fun list(
        params: RbacGroupListParams = RbacGroupListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<RbacGroupListPageAsync>

    /** @see list */
    fun list(
        params: RbacGroupListParams = RbacGroupListParams.none()
    ): CompletableFuture<RbacGroupListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<RbacGroupListPageAsync> =
        list(RbacGroupListParams.none(), requestOptions)

    /**
     * Delete an RBAC Group. Groups provisioned by an identity provider (source type `"scim"`)
     * cannot be deleted via the API while an organization in the tenant uses SCIM provisioning.
     *
     * The RBAC Groups API is available to Claude Enterprise organizations only.
     */
    fun delete(rbacGroupId: String): CompletableFuture<RbacGroupDeleteResponse> =
        delete(rbacGroupId, RbacGroupDeleteParams.none())

    /** @see delete */
    fun delete(
        rbacGroupId: String,
        params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<RbacGroupDeleteResponse> =
        delete(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

    /** @see delete */
    fun delete(
        rbacGroupId: String,
        params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
    ): CompletableFuture<RbacGroupDeleteResponse> =
        delete(rbacGroupId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: RbacGroupDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<RbacGroupDeleteResponse>

    /** @see delete */
    fun delete(params: RbacGroupDeleteParams): CompletableFuture<RbacGroupDeleteResponse> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        rbacGroupId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<RbacGroupDeleteResponse> =
        delete(rbacGroupId, RbacGroupDeleteParams.none(), requestOptions)

    /**
     * A view of [RbacGroupServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RbacGroupServiceAsync.WithRawResponse

        fun members(): MemberServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/rbac_groups?beta=true`, but is
         * otherwise the same as [RbacGroupServiceAsync.create].
         */
        fun create(
            params: RbacGroupCreateParams
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>> = create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: RbacGroupCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/rbac_groups/{rbac_group_id}?beta=true`, but is otherwise the same as
         * [RbacGroupServiceAsync.retrieve].
         */
        fun retrieve(rbacGroupId: String): CompletableFuture<HttpResponseFor<BetaRbacGroup>> =
            retrieve(rbacGroupId, RbacGroupRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            rbacGroupId: String,
            params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>> =
            retrieve(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            rbacGroupId: String,
            params: RbacGroupRetrieveParams = RbacGroupRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>> =
            retrieve(rbacGroupId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: RbacGroupRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>>

        /** @see retrieve */
        fun retrieve(
            params: RbacGroupRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            rbacGroupId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>> =
            retrieve(rbacGroupId, RbacGroupRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/rbac_groups/{rbac_group_id}?beta=true`, but is otherwise the same as
         * [RbacGroupServiceAsync.update].
         */
        fun update(rbacGroupId: String): CompletableFuture<HttpResponseFor<BetaRbacGroup>> =
            update(rbacGroupId, RbacGroupUpdateParams.none())

        /** @see update */
        fun update(
            rbacGroupId: String,
            params: RbacGroupUpdateParams = RbacGroupUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>> =
            update(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see update */
        fun update(
            rbacGroupId: String,
            params: RbacGroupUpdateParams = RbacGroupUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>> =
            update(rbacGroupId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: RbacGroupUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>>

        /** @see update */
        fun update(
            params: RbacGroupUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>> = update(params, RequestOptions.none())

        /** @see update */
        fun update(
            rbacGroupId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaRbacGroup>> =
            update(rbacGroupId, RbacGroupUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/rbac_groups?beta=true`, but is
         * otherwise the same as [RbacGroupServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<RbacGroupListPageAsync>> =
            list(RbacGroupListParams.none())

        /** @see list */
        fun list(
            params: RbacGroupListParams = RbacGroupListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<RbacGroupListPageAsync>>

        /** @see list */
        fun list(
            params: RbacGroupListParams = RbacGroupListParams.none()
        ): CompletableFuture<HttpResponseFor<RbacGroupListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<RbacGroupListPageAsync>> =
            list(RbacGroupListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/rbac_groups/{rbac_group_id}?beta=true`, but is otherwise the same as
         * [RbacGroupServiceAsync.delete].
         */
        fun delete(
            rbacGroupId: String
        ): CompletableFuture<HttpResponseFor<RbacGroupDeleteResponse>> =
            delete(rbacGroupId, RbacGroupDeleteParams.none())

        /** @see delete */
        fun delete(
            rbacGroupId: String,
            params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<RbacGroupDeleteResponse>> =
            delete(params.toBuilder().rbacGroupId(rbacGroupId).build(), requestOptions)

        /** @see delete */
        fun delete(
            rbacGroupId: String,
            params: RbacGroupDeleteParams = RbacGroupDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<RbacGroupDeleteResponse>> =
            delete(rbacGroupId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: RbacGroupDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<RbacGroupDeleteResponse>>

        /** @see delete */
        fun delete(
            params: RbacGroupDeleteParams
        ): CompletableFuture<HttpResponseFor<RbacGroupDeleteResponse>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            rbacGroupId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<RbacGroupDeleteResponse>> =
            delete(rbacGroupId, RbacGroupDeleteParams.none(), requestOptions)
    }
}
