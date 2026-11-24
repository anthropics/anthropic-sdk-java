package com.anthropic.services.async.beta.compliance.review

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.compliance.review.cases.BetaComplianceReviewCase
import com.anthropic.models.beta.compliance.review.cases.CaseListPageAsync
import com.anthropic.models.beta.compliance.review.cases.CaseListParams
import com.anthropic.models.beta.compliance.review.cases.CaseRespondParams
import com.anthropic.models.beta.compliance.review.cases.CaseRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface CaseServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CaseServiceAsync

    /**
     * Read one review case.
     *
     * A case that the API key can't reach, or that Anthropic hasn't sent, returns the same 404 as a
     * case that doesn't exist.
     *
     * An Admin API key with the `read:compliance_review_cases` scope can call this endpoint.
     */
    fun retrieve(caseId: String): CompletableFuture<BetaComplianceReviewCase> =
        retrieve(caseId, CaseRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        caseId: String,
        params: CaseRetrieveParams = CaseRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaComplianceReviewCase> =
        retrieve(params.toBuilder().caseId(caseId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        caseId: String,
        params: CaseRetrieveParams = CaseRetrieveParams.none(),
    ): CompletableFuture<BetaComplianceReviewCase> = retrieve(caseId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: CaseRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaComplianceReviewCase>

    /** @see retrieve */
    fun retrieve(params: CaseRetrieveParams): CompletableFuture<BetaComplianceReviewCase> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        caseId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaComplianceReviewCase> =
        retrieve(caseId, CaseRetrieveParams.none(), requestOptions)

    /**
     * List the review cases of every organization that the API key can reach, newest first by
     * default. Both open and closed cases are returned unless `status` is set.
     *
     * To sync, store the largest `updated_at` you have read, and send it as the at-or-after filter
     * on `updated_at`, sorted by `updated_at` ascending. That finds new cases, newly closed cases,
     * and cases with a new notice. Read every page, match results by `id`, and store the new
     * largest value after the last page.
     *
     * A request with the `anthropic-user-profile-id` header and no `user_profile_id` parameter
     * returns a 400 error.
     *
     * An Admin API key with the `read:compliance_review_cases` scope can call this endpoint.
     */
    fun list(): CompletableFuture<CaseListPageAsync> = list(CaseListParams.none())

    /** @see list */
    fun list(
        params: CaseListParams = CaseListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<CaseListPageAsync>

    /** @see list */
    fun list(params: CaseListParams = CaseListParams.none()): CompletableFuture<CaseListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<CaseListPageAsync> =
        list(CaseListParams.none(), requestOptions)

    /**
     * Send your conclusion on a review case. A response closes the case, and a closed case can't be
     * reopened.
     *
     * Only the first response counts. A request is safe to send again: if the closed case has a
     * response with the same field values, in any list order, the API returns 200 and changes
     * nothing. If the closed case has a different response, or none, the API returns a 400 with
     * `case_closed` in `error.details.error_code`.
     *
     * If your end customer reviews this case instead of you, the API returns a 403 with
     * `case_reviewed_by_end_customer` in `error.details.error_code`.
     *
     * An Admin API key with the `write:compliance_review_cases` scope can call this endpoint.
     */
    fun respond(
        caseId: String,
        params: CaseRespondParams,
    ): CompletableFuture<BetaComplianceReviewCase> = respond(caseId, params, RequestOptions.none())

    /** @see respond */
    fun respond(
        caseId: String,
        params: CaseRespondParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaComplianceReviewCase> =
        respond(params.toBuilder().caseId(caseId).build(), requestOptions)

    /** @see respond */
    fun respond(params: CaseRespondParams): CompletableFuture<BetaComplianceReviewCase> =
        respond(params, RequestOptions.none())

    /** @see respond */
    fun respond(
        params: CaseRespondParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaComplianceReviewCase>

    /** A view of [CaseServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): CaseServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/compliance/review/cases/{case_id}`, but is
         * otherwise the same as [CaseServiceAsync.retrieve].
         */
        fun retrieve(caseId: String): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>> =
            retrieve(caseId, CaseRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            caseId: String,
            params: CaseRetrieveParams = CaseRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>> =
            retrieve(params.toBuilder().caseId(caseId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            caseId: String,
            params: CaseRetrieveParams = CaseRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>> =
            retrieve(caseId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: CaseRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>>

        /** @see retrieve */
        fun retrieve(
            params: CaseRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            caseId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>> =
            retrieve(caseId, CaseRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/compliance/review/cases`, but is otherwise the
         * same as [CaseServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<CaseListPageAsync>> =
            list(CaseListParams.none())

        /** @see list */
        fun list(
            params: CaseListParams = CaseListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<CaseListPageAsync>>

        /** @see list */
        fun list(
            params: CaseListParams = CaseListParams.none()
        ): CompletableFuture<HttpResponseFor<CaseListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<CaseListPageAsync>> =
            list(CaseListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/compliance/review/cases/{case_id}/respond`, but
         * is otherwise the same as [CaseServiceAsync.respond].
         */
        fun respond(
            caseId: String,
            params: CaseRespondParams,
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>> =
            respond(caseId, params, RequestOptions.none())

        /** @see respond */
        fun respond(
            caseId: String,
            params: CaseRespondParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>> =
            respond(params.toBuilder().caseId(caseId).build(), requestOptions)

        /** @see respond */
        fun respond(
            params: CaseRespondParams
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>> =
            respond(params, RequestOptions.none())

        /** @see respond */
        fun respond(
            params: CaseRespondParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>>
    }
}
