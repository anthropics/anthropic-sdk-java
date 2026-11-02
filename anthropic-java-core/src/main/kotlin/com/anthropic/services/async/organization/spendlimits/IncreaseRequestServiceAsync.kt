package com.anthropic.services.async.organization.spendlimits

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.spendlimits.increaserequests.IncreaseRequestApproveParams
import com.anthropic.models.organization.spendlimits.increaserequests.IncreaseRequestApproveResponse
import com.anthropic.models.organization.spendlimits.increaserequests.IncreaseRequestDenyParams
import com.anthropic.models.organization.spendlimits.increaserequests.IncreaseRequestListPageAsync
import com.anthropic.models.organization.spendlimits.increaserequests.IncreaseRequestListParams
import com.anthropic.models.organization.spendlimits.increaserequests.IncreaseRequestRetrieveParams
import com.anthropic.models.organization.spendlimits.increaserequests.SpendLimitIncreaseRequest
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface IncreaseRequestServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): IncreaseRequestServiceAsync

    /**
     * Retrieve a spend limit increase request.
     *
     * While `pending`, the response includes a live `spend_summary` for the requester at the
     * request's period.
     */
    fun retrieve(
        spendLimitIncreaseRequestId: String
    ): CompletableFuture<SpendLimitIncreaseRequest> =
        retrieve(spendLimitIncreaseRequestId, IncreaseRequestRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        spendLimitIncreaseRequestId: String,
        params: IncreaseRequestRetrieveParams = IncreaseRequestRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SpendLimitIncreaseRequest> =
        retrieve(
            params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
            requestOptions,
        )

    /** @see retrieve */
    fun retrieve(
        spendLimitIncreaseRequestId: String,
        params: IncreaseRequestRetrieveParams = IncreaseRequestRetrieveParams.none(),
    ): CompletableFuture<SpendLimitIncreaseRequest> =
        retrieve(spendLimitIncreaseRequestId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: IncreaseRequestRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SpendLimitIncreaseRequest>

    /** @see retrieve */
    fun retrieve(
        params: IncreaseRequestRetrieveParams
    ): CompletableFuture<SpendLimitIncreaseRequest> = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        spendLimitIncreaseRequestId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<SpendLimitIncreaseRequest> =
        retrieve(spendLimitIncreaseRequestId, IncreaseRequestRetrieveParams.none(), requestOptions)

    /**
     * List spend limit increase requests, most recent first.
     *
     * Pending requests include a live `spend_summary` for the requester. Requests whose requester
     * is no longer a member are excluded.
     */
    fun list(): CompletableFuture<IncreaseRequestListPageAsync> =
        list(IncreaseRequestListParams.none())

    /** @see list */
    fun list(
        params: IncreaseRequestListParams = IncreaseRequestListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<IncreaseRequestListPageAsync>

    /** @see list */
    fun list(
        params: IncreaseRequestListParams = IncreaseRequestListParams.none()
    ): CompletableFuture<IncreaseRequestListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<IncreaseRequestListPageAsync> =
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
    ): CompletableFuture<IncreaseRequestApproveResponse> =
        approve(spendLimitIncreaseRequestId, params, RequestOptions.none())

    /** @see approve */
    fun approve(
        spendLimitIncreaseRequestId: String,
        params: IncreaseRequestApproveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<IncreaseRequestApproveResponse> =
        approve(
            params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
            requestOptions,
        )

    /** @see approve */
    fun approve(
        params: IncreaseRequestApproveParams
    ): CompletableFuture<IncreaseRequestApproveResponse> = approve(params, RequestOptions.none())

    /** @see approve */
    fun approve(
        params: IncreaseRequestApproveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<IncreaseRequestApproveResponse>

    /**
     * Deny a pending spend limit increase request.
     *
     * Idempotent on `denied`; denying an already-`approved` request returns
     * 400. Anthropic emails the requester unless `suppress_notification` is set.
     */
    fun deny(spendLimitIncreaseRequestId: String): CompletableFuture<SpendLimitIncreaseRequest> =
        deny(spendLimitIncreaseRequestId, IncreaseRequestDenyParams.none())

    /** @see deny */
    fun deny(
        spendLimitIncreaseRequestId: String,
        params: IncreaseRequestDenyParams = IncreaseRequestDenyParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SpendLimitIncreaseRequest> =
        deny(
            params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
            requestOptions,
        )

    /** @see deny */
    fun deny(
        spendLimitIncreaseRequestId: String,
        params: IncreaseRequestDenyParams = IncreaseRequestDenyParams.none(),
    ): CompletableFuture<SpendLimitIncreaseRequest> =
        deny(spendLimitIncreaseRequestId, params, RequestOptions.none())

    /** @see deny */
    fun deny(
        params: IncreaseRequestDenyParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SpendLimitIncreaseRequest>

    /** @see deny */
    fun deny(params: IncreaseRequestDenyParams): CompletableFuture<SpendLimitIncreaseRequest> =
        deny(params, RequestOptions.none())

    /** @see deny */
    fun deny(
        spendLimitIncreaseRequestId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<SpendLimitIncreaseRequest> =
        deny(spendLimitIncreaseRequestId, IncreaseRequestDenyParams.none(), requestOptions)

    /**
     * A view of [IncreaseRequestServiceAsync] that provides access to raw HTTP responses for each
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
        ): IncreaseRequestServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/spend_limit_increase_requests/{spend_limit_increase_request_id}`, but
         * is otherwise the same as [IncreaseRequestServiceAsync.retrieve].
         */
        fun retrieve(
            spendLimitIncreaseRequestId: String
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>> =
            retrieve(spendLimitIncreaseRequestId, IncreaseRequestRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestRetrieveParams = IncreaseRequestRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>> =
            retrieve(
                params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
                requestOptions,
            )

        /** @see retrieve */
        fun retrieve(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestRetrieveParams = IncreaseRequestRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>> =
            retrieve(spendLimitIncreaseRequestId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: IncreaseRequestRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>>

        /** @see retrieve */
        fun retrieve(
            params: IncreaseRequestRetrieveParams
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            spendLimitIncreaseRequestId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>> =
            retrieve(
                spendLimitIncreaseRequestId,
                IncreaseRequestRetrieveParams.none(),
                requestOptions,
            )

        /**
         * Returns a raw HTTP response for `get /v1/organizations/spend_limit_increase_requests`,
         * but is otherwise the same as [IncreaseRequestServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<IncreaseRequestListPageAsync>> =
            list(IncreaseRequestListParams.none())

        /** @see list */
        fun list(
            params: IncreaseRequestListParams = IncreaseRequestListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<IncreaseRequestListPageAsync>>

        /** @see list */
        fun list(
            params: IncreaseRequestListParams = IncreaseRequestListParams.none()
        ): CompletableFuture<HttpResponseFor<IncreaseRequestListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<IncreaseRequestListPageAsync>> =
            list(IncreaseRequestListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/spend_limit_increase_requests/{spend_limit_increase_request_id}/approve`,
         * but is otherwise the same as [IncreaseRequestServiceAsync.approve].
         */
        fun approve(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestApproveParams,
        ): CompletableFuture<HttpResponseFor<IncreaseRequestApproveResponse>> =
            approve(spendLimitIncreaseRequestId, params, RequestOptions.none())

        /** @see approve */
        fun approve(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestApproveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<IncreaseRequestApproveResponse>> =
            approve(
                params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
                requestOptions,
            )

        /** @see approve */
        fun approve(
            params: IncreaseRequestApproveParams
        ): CompletableFuture<HttpResponseFor<IncreaseRequestApproveResponse>> =
            approve(params, RequestOptions.none())

        /** @see approve */
        fun approve(
            params: IncreaseRequestApproveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<IncreaseRequestApproveResponse>>

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/spend_limit_increase_requests/{spend_limit_increase_request_id}/deny`,
         * but is otherwise the same as [IncreaseRequestServiceAsync.deny].
         */
        fun deny(
            spendLimitIncreaseRequestId: String
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>> =
            deny(spendLimitIncreaseRequestId, IncreaseRequestDenyParams.none())

        /** @see deny */
        fun deny(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestDenyParams = IncreaseRequestDenyParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>> =
            deny(
                params.toBuilder().spendLimitIncreaseRequestId(spendLimitIncreaseRequestId).build(),
                requestOptions,
            )

        /** @see deny */
        fun deny(
            spendLimitIncreaseRequestId: String,
            params: IncreaseRequestDenyParams = IncreaseRequestDenyParams.none(),
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>> =
            deny(spendLimitIncreaseRequestId, params, RequestOptions.none())

        /** @see deny */
        fun deny(
            params: IncreaseRequestDenyParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>>

        /** @see deny */
        fun deny(
            params: IncreaseRequestDenyParams
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>> =
            deny(params, RequestOptions.none())

        /** @see deny */
        fun deny(
            spendLimitIncreaseRequestId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SpendLimitIncreaseRequest>> =
            deny(spendLimitIncreaseRequestId, IncreaseRequestDenyParams.none(), requestOptions)
    }
}
