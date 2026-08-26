// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.invites.BetaOrganizationInvite
import com.anthropic.models.beta.organization.invites.InviteCreateParams
import com.anthropic.models.beta.organization.invites.InviteDeleteParams
import com.anthropic.models.beta.organization.invites.InviteDeleteResponse
import com.anthropic.models.beta.organization.invites.InviteListPage
import com.anthropic.models.beta.organization.invites.InviteListParams
import com.anthropic.models.beta.organization.invites.InviteRetrieveParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface InviteService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): InviteService

    /**
     * Invite a user to join the organization by email.
     *
     * On plans that draw members from a finite pool of purchased seats, the invite automatically
     * consumes a seat from the lowest tier with availability; there is no seat-tier parameter. When
     * no seat is free the request fails with a 400 error rather than purchasing a seat.
     */
    fun create(params: InviteCreateParams): BetaOrganizationInvite =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: InviteCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationInvite

    /** Retrieve an invite by ID. */
    fun retrieve(inviteId: String): BetaOrganizationInvite =
        retrieve(inviteId, InviteRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        inviteId: String,
        params: InviteRetrieveParams = InviteRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationInvite =
        retrieve(params.toBuilder().inviteId(inviteId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        inviteId: String,
        params: InviteRetrieveParams = InviteRetrieveParams.none(),
    ): BetaOrganizationInvite = retrieve(inviteId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: InviteRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationInvite

    /** @see retrieve */
    fun retrieve(params: InviteRetrieveParams): BetaOrganizationInvite =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(inviteId: String, requestOptions: RequestOptions): BetaOrganizationInvite =
        retrieve(inviteId, InviteRetrieveParams.none(), requestOptions)

    /** List the organization's invites. */
    fun list(): InviteListPage = list(InviteListParams.none())

    /** @see list */
    fun list(
        params: InviteListParams = InviteListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): InviteListPage

    /** @see list */
    fun list(params: InviteListParams = InviteListParams.none()): InviteListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): InviteListPage =
        list(InviteListParams.none(), requestOptions)

    /** Delete a pending invite. */
    fun delete(inviteId: String): InviteDeleteResponse = delete(inviteId, InviteDeleteParams.none())

    /** @see delete */
    fun delete(
        inviteId: String,
        params: InviteDeleteParams = InviteDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): InviteDeleteResponse = delete(params.toBuilder().inviteId(inviteId).build(), requestOptions)

    /** @see delete */
    fun delete(
        inviteId: String,
        params: InviteDeleteParams = InviteDeleteParams.none(),
    ): InviteDeleteResponse = delete(inviteId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: InviteDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): InviteDeleteResponse

    /** @see delete */
    fun delete(params: InviteDeleteParams): InviteDeleteResponse =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(inviteId: String, requestOptions: RequestOptions): InviteDeleteResponse =
        delete(inviteId, InviteDeleteParams.none(), requestOptions)

    /** A view of [InviteService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): InviteService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/invites?beta=true`, but is
         * otherwise the same as [InviteService.create].
         */
        @MustBeClosed
        fun create(params: InviteCreateParams): HttpResponseFor<BetaOrganizationInvite> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: InviteCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationInvite>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/invites/{invite_id}?beta=true`,
         * but is otherwise the same as [InviteService.retrieve].
         */
        @MustBeClosed
        fun retrieve(inviteId: String): HttpResponseFor<BetaOrganizationInvite> =
            retrieve(inviteId, InviteRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            inviteId: String,
            params: InviteRetrieveParams = InviteRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationInvite> =
            retrieve(params.toBuilder().inviteId(inviteId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            inviteId: String,
            params: InviteRetrieveParams = InviteRetrieveParams.none(),
        ): HttpResponseFor<BetaOrganizationInvite> =
            retrieve(inviteId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: InviteRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationInvite>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: InviteRetrieveParams): HttpResponseFor<BetaOrganizationInvite> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            inviteId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganizationInvite> =
            retrieve(inviteId, InviteRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/invites?beta=true`, but is
         * otherwise the same as [InviteService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<InviteListPage> = list(InviteListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: InviteListParams = InviteListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<InviteListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: InviteListParams = InviteListParams.none()
        ): HttpResponseFor<InviteListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<InviteListPage> =
            list(InviteListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/organizations/invites/{invite_id}?beta=true`,
         * but is otherwise the same as [InviteService.delete].
         */
        @MustBeClosed
        fun delete(inviteId: String): HttpResponseFor<InviteDeleteResponse> =
            delete(inviteId, InviteDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            inviteId: String,
            params: InviteDeleteParams = InviteDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<InviteDeleteResponse> =
            delete(params.toBuilder().inviteId(inviteId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            inviteId: String,
            params: InviteDeleteParams = InviteDeleteParams.none(),
        ): HttpResponseFor<InviteDeleteResponse> = delete(inviteId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: InviteDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<InviteDeleteResponse>

        /** @see delete */
        @MustBeClosed
        fun delete(params: InviteDeleteParams): HttpResponseFor<InviteDeleteResponse> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            inviteId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<InviteDeleteResponse> =
            delete(inviteId, InviteDeleteParams.none(), requestOptions)
    }
}
