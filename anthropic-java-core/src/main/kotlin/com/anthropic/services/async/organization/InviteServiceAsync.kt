package com.anthropic.services.async.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.invites.InviteCreateParams
import com.anthropic.models.organization.invites.InviteDeleteParams
import com.anthropic.models.organization.invites.InviteDeleteResponse
import com.anthropic.models.organization.invites.InviteListPageAsync
import com.anthropic.models.organization.invites.InviteListParams
import com.anthropic.models.organization.invites.InviteRetrieveParams
import com.anthropic.models.organization.invites.OrganizationInvite
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface InviteServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): InviteServiceAsync

    /**
     * Invite a user to join the organization by email.
     *
     * On plans that draw members from a finite pool of purchased seats, the invite automatically
     * consumes a seat from the lowest tier with availability; there is no seat-tier parameter. When
     * no seat is free the request fails with a 400 error rather than purchasing a seat.
     */
    fun create(params: InviteCreateParams): CompletableFuture<OrganizationInvite> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: InviteCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<OrganizationInvite>

    /** Retrieve an invite by ID. */
    fun retrieve(inviteId: String): CompletableFuture<OrganizationInvite> =
        retrieve(inviteId, InviteRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        inviteId: String,
        params: InviteRetrieveParams = InviteRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<OrganizationInvite> =
        retrieve(params.toBuilder().inviteId(inviteId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        inviteId: String,
        params: InviteRetrieveParams = InviteRetrieveParams.none(),
    ): CompletableFuture<OrganizationInvite> = retrieve(inviteId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: InviteRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<OrganizationInvite>

    /** @see retrieve */
    fun retrieve(params: InviteRetrieveParams): CompletableFuture<OrganizationInvite> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        inviteId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<OrganizationInvite> =
        retrieve(inviteId, InviteRetrieveParams.none(), requestOptions)

    /** List the organization's invites. */
    fun list(): CompletableFuture<InviteListPageAsync> = list(InviteListParams.none())

    /** @see list */
    fun list(
        params: InviteListParams = InviteListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<InviteListPageAsync>

    /** @see list */
    fun list(
        params: InviteListParams = InviteListParams.none()
    ): CompletableFuture<InviteListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<InviteListPageAsync> =
        list(InviteListParams.none(), requestOptions)

    /** Delete a pending invite. */
    fun delete(inviteId: String): CompletableFuture<InviteDeleteResponse> =
        delete(inviteId, InviteDeleteParams.none())

    /** @see delete */
    fun delete(
        inviteId: String,
        params: InviteDeleteParams = InviteDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<InviteDeleteResponse> =
        delete(params.toBuilder().inviteId(inviteId).build(), requestOptions)

    /** @see delete */
    fun delete(
        inviteId: String,
        params: InviteDeleteParams = InviteDeleteParams.none(),
    ): CompletableFuture<InviteDeleteResponse> = delete(inviteId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: InviteDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<InviteDeleteResponse>

    /** @see delete */
    fun delete(params: InviteDeleteParams): CompletableFuture<InviteDeleteResponse> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        inviteId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<InviteDeleteResponse> =
        delete(inviteId, InviteDeleteParams.none(), requestOptions)

    /**
     * A view of [InviteServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): InviteServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/invites`, but is otherwise the
         * same as [InviteServiceAsync.create].
         */
        fun create(
            params: InviteCreateParams
        ): CompletableFuture<HttpResponseFor<OrganizationInvite>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: InviteCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<OrganizationInvite>>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/invites/{invite_id}`, but is
         * otherwise the same as [InviteServiceAsync.retrieve].
         */
        fun retrieve(inviteId: String): CompletableFuture<HttpResponseFor<OrganizationInvite>> =
            retrieve(inviteId, InviteRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            inviteId: String,
            params: InviteRetrieveParams = InviteRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<OrganizationInvite>> =
            retrieve(params.toBuilder().inviteId(inviteId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            inviteId: String,
            params: InviteRetrieveParams = InviteRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<OrganizationInvite>> =
            retrieve(inviteId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: InviteRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<OrganizationInvite>>

        /** @see retrieve */
        fun retrieve(
            params: InviteRetrieveParams
        ): CompletableFuture<HttpResponseFor<OrganizationInvite>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            inviteId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<OrganizationInvite>> =
            retrieve(inviteId, InviteRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/invites`, but is otherwise the
         * same as [InviteServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<InviteListPageAsync>> =
            list(InviteListParams.none())

        /** @see list */
        fun list(
            params: InviteListParams = InviteListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<InviteListPageAsync>>

        /** @see list */
        fun list(
            params: InviteListParams = InviteListParams.none()
        ): CompletableFuture<HttpResponseFor<InviteListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<InviteListPageAsync>> =
            list(InviteListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/organizations/invites/{invite_id}`, but is
         * otherwise the same as [InviteServiceAsync.delete].
         */
        fun delete(inviteId: String): CompletableFuture<HttpResponseFor<InviteDeleteResponse>> =
            delete(inviteId, InviteDeleteParams.none())

        /** @see delete */
        fun delete(
            inviteId: String,
            params: InviteDeleteParams = InviteDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<InviteDeleteResponse>> =
            delete(params.toBuilder().inviteId(inviteId).build(), requestOptions)

        /** @see delete */
        fun delete(
            inviteId: String,
            params: InviteDeleteParams = InviteDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<InviteDeleteResponse>> =
            delete(inviteId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: InviteDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<InviteDeleteResponse>>

        /** @see delete */
        fun delete(
            params: InviteDeleteParams
        ): CompletableFuture<HttpResponseFor<InviteDeleteResponse>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            inviteId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<InviteDeleteResponse>> =
            delete(inviteId, InviteDeleteParams.none(), requestOptions)
    }
}
