package com.anthropic.services.blocking.beta.organization.spendlimits

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.spendlimits.increaserequests.BetaSpendLimitIncreaseRequest
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestApproveParams
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestApproveResponse
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestDenyParams
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestListPage
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestListParams
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestRetrieveParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface IncreaseRequestService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): IncreaseRequestService

    /**
     * Retrieve a spend limit increase request.
     *
     * While `pending`, the response includes a live `spend_summary` for the requester at the
     * request's period.
     */
    fun retrieve(spendLimitIncreaseRequestId: String): BetaSpendLimitIncreaseRequest =
        retrieve(spendLimitIncreaseRequestId, IncreaseRequestRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        spendLimitIncreaseRequestId: String,
        params: IncreaseRequestRetrieveParams = IncreaseRequestRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSpendLimitIncreaseRequest =
        retrieve(
            params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
            requestOptions,
        )

    /** @see retrieve */
    fun retrieve(
        spendLimitIncreaseRequestId: String,
        params: IncreaseRequestRetrieveParams = IncreaseRequestRetrieveParams.none(),
    ): BetaSpendLimitIncreaseRequest =
        retrieve(spendLimitIncreaseRequestId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: IncreaseRequestRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSpendLimitIncreaseRequest

    /** @see retrieve */
    fun retrieve(params: IncreaseRequestRetrieveParams): BetaSpendLimitIncreaseRequest =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        spendLimitIncreaseRequestId: String,
        requestOptions: RequestOptions,
    ): BetaSpendLimitIncreaseRequest =
        retrieve(spendLimitIncreaseRequestId, IncreaseRequestRetrieveParams.none(), requestOptions)

    /**
     * List spend limit increase requests, most recent first.
     *
     * Pending requests include a live `spend_summary` for the requester. Requests whose requester
     * is no longer a member are excluded.
     */
    fun list(): IncreaseRequestListPage = list(IncreaseRequestListParams.none())

    /** @see list */
    fun list(
        params: IncreaseRequestListParams = IncreaseRequestListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): IncreaseRequestListPage

    /** @see list */
    fun list(
        params: IncreaseRequestListParams = IncreaseRequestListParams.none()
    ): IncreaseRequestListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): IncreaseRequestListPage =
        list(IncreaseRequestListParams.none(), requestOptions)

    /**
     * Approve a pending spend limit increase request.
     *
     * Writes a per-user spend limit at `amount` for the requester and transitions the request to
     * `approved`. `period` defaults to the period the member was blocked on. Anthropic emails the
     * requester unless `suppress_notification` is set.
     */
    fun approve(
        spendLimitIncreaseRequestId: String,
        params: IncreaseRequestApproveParams,
    ): IncreaseRequestApproveResponse =
        approve(spendLimitIncreaseRequestId, params, RequestOptions.none())

    /** @see approve */
    fun approve(
        spendLimitIncreaseRequestId: String,
        params: IncreaseRequestApproveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): IncreaseRequestApproveResponse =
        approve(
            params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
            requestOptions,
        )

    /** @see approve */
    fun approve(params: IncreaseRequestApproveParams): IncreaseRequestApproveResponse =
        approve(params, RequestOptions.none())

    /** @see approve */
    fun approve(
        params: IncreaseRequestApproveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): IncreaseRequestApproveResponse

    /**
     * Deny a pending spend limit increase request.
     *
     * Idempotent on `denied`; denying an already-`approved` request returns
     * 400. Anthropic emails the requester unless `suppress_notification` is set.
     */
    fun deny(spendLimitIncreaseRequestId: String): BetaSpendLimitIncreaseRequest =
        deny(spendLimitIncreaseRequestId, IncreaseRequestDenyParams.none())

    /** @see deny */
    fun deny(
        spendLimitIncreaseRequestId: String,
        params: IncreaseRequestDenyParams = IncreaseRequestDenyParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSpendLimitIncreaseRequest =
        deny(
            params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
            requestOptions,
        )

    /** @see deny */
    fun deny(
        spendLimitIncreaseRequestId: String,
        params: IncreaseRequestDenyParams = IncreaseRequestDenyParams.none(),
    ): BetaSpendLimitIncreaseRequest =
        deny(spendLimitIncreaseRequestId, params, RequestOptions.none())

    /** @see deny */
    fun deny(
        params: IncreaseRequestDenyParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSpendLimitIncreaseRequest

    /** @see deny */
    fun deny(params: IncreaseRequestDenyParams): BetaSpendLimitIncreaseRequest =
        deny(params, RequestOptions.none())

    /** @see deny */
    fun deny(
        spendLimitIncreaseRequestId: String,
        requestOptions: RequestOptions,
    ): BetaSpendLimitIncreaseRequest =
        deny(spendLimitIncreaseRequestId, IncreaseRequestDenyParams.none(), requestOptions)

    /**
     * A view of [IncreaseRequestService] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): IncreaseRequestService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/spend_limit_increase_requests/{spend_limit_increase_request_id}?beta=true`,
         * but is otherwise the same as [IncreaseRequestService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            spendLimitIncreaseRequestId: String
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> =
            retrieve(spendLimitIncreaseRequestId, IncreaseRequestRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestRetrieveParams = IncreaseRequestRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> =
            retrieve(
                params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
                requestOptions,
            )

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestRetrieveParams = IncreaseRequestRetrieveParams.none(),
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> =
            retrieve(spendLimitIncreaseRequestId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: IncreaseRequestRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: IncreaseRequestRetrieveParams
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            spendLimitIncreaseRequestId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> =
            retrieve(
                spendLimitIncreaseRequestId,
                IncreaseRequestRetrieveParams.none(),
                requestOptions,
            )

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/spend_limit_increase_requests?beta=true`, but is otherwise the same as
         * [IncreaseRequestService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<IncreaseRequestListPage> =
            list(IncreaseRequestListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: IncreaseRequestListParams = IncreaseRequestListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<IncreaseRequestListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: IncreaseRequestListParams = IncreaseRequestListParams.none()
        ): HttpResponseFor<IncreaseRequestListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<IncreaseRequestListPage> =
            list(IncreaseRequestListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/spend_limit_increase_requests/{spend_limit_increase_request_id}/approve?beta=true`,
         * but is otherwise the same as [IncreaseRequestService.approve].
         */
        @MustBeClosed
        fun approve(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestApproveParams,
        ): HttpResponseFor<IncreaseRequestApproveResponse> =
            approve(spendLimitIncreaseRequestId, params, RequestOptions.none())

        /** @see approve */
        @MustBeClosed
        fun approve(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestApproveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<IncreaseRequestApproveResponse> =
            approve(
                params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
                requestOptions,
            )

        /** @see approve */
        @MustBeClosed
        fun approve(
            params: IncreaseRequestApproveParams
        ): HttpResponseFor<IncreaseRequestApproveResponse> = approve(params, RequestOptions.none())

        /** @see approve */
        @MustBeClosed
        fun approve(
            params: IncreaseRequestApproveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<IncreaseRequestApproveResponse>

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/spend_limit_increase_requests/{spend_limit_increase_request_id}/deny?beta=true`,
         * but is otherwise the same as [IncreaseRequestService.deny].
         */
        @MustBeClosed
        fun deny(
            spendLimitIncreaseRequestId: String
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> =
            deny(spendLimitIncreaseRequestId, IncreaseRequestDenyParams.none())

        /** @see deny */
        @MustBeClosed
        fun deny(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestDenyParams = IncreaseRequestDenyParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> =
            deny(
                params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
                requestOptions,
            )

        /** @see deny */
        @MustBeClosed
        fun deny(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestDenyParams = IncreaseRequestDenyParams.none(),
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> =
            deny(spendLimitIncreaseRequestId, params, RequestOptions.none())

        /** @see deny */
        @MustBeClosed
        fun deny(
            params: IncreaseRequestDenyParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest>

        /** @see deny */
        @MustBeClosed
        fun deny(
            params: IncreaseRequestDenyParams
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> = deny(params, RequestOptions.none())

        /** @see deny */
        @MustBeClosed
        fun deny(
            spendLimitIncreaseRequestId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> =
            deny(spendLimitIncreaseRequestId, IncreaseRequestDenyParams.none(), requestOptions)
    }
}
