// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.messages

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.BetaDeletedMessageBatch
import com.anthropic.models.BetaMessageBatch
import com.anthropic.models.BetaMessageBatchCancelParams
import com.anthropic.models.BetaMessageBatchCreateParams
import com.anthropic.models.BetaMessageBatchDeleteParams
import com.anthropic.models.BetaMessageBatchIndividualResponse
import com.anthropic.models.BetaMessageBatchListPage
import com.anthropic.models.BetaMessageBatchListParams
import com.anthropic.models.BetaMessageBatchResultsParams
import com.anthropic.models.BetaMessageBatchRetrieveParams
import com.google.errorprone.annotations.MustBeClosed

interface BatchService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Send a batch of Message creation requests.
     *
     * The Message Batches API can be used to process multiple Messages API requests at once. Once a
     * Message Batch is created, it begins processing immediately. Batches can take up to 24 hours
     * to complete.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    fun create(params: BetaMessageBatchCreateParams): BetaMessageBatch =
        create(params, RequestOptions.none())

    /** @see [create] */
    fun create(
        params: BetaMessageBatchCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch

    /**
     * This endpoint is idempotent and can be used to poll for Message Batch completion. To access
     * the results of a Message Batch, make a request to the `results_url` field in the response.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    fun retrieve(params: BetaMessageBatchRetrieveParams): BetaMessageBatch =
        retrieve(params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(
        params: BetaMessageBatchRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch

    /**
     * List all Message Batches within a Workspace. Most recently created batches are returned
     * first.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    fun list(): BetaMessageBatchListPage = list(BetaMessageBatchListParams.none())

    /** @see [list] */
    fun list(
        params: BetaMessageBatchListParams = BetaMessageBatchListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatchListPage

    /** @see [list] */
    fun list(
        params: BetaMessageBatchListParams = BetaMessageBatchListParams.none()
    ): BetaMessageBatchListPage = list(params, RequestOptions.none())

    /** @see [list] */
    fun list(requestOptions: RequestOptions): BetaMessageBatchListPage =
        list(BetaMessageBatchListParams.none(), requestOptions)

    /**
     * Delete a Message Batch.
     *
     * Message Batches can only be deleted once they've finished processing. If you'd like to delete
     * an in-progress batch, you must first cancel it.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    fun delete(params: BetaMessageBatchDeleteParams): BetaDeletedMessageBatch =
        delete(params, RequestOptions.none())

    /** @see [delete] */
    fun delete(
        params: BetaMessageBatchDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedMessageBatch

    /**
     * Batches may be canceled any time before processing ends. Once cancellation is initiated, the
     * batch enters a `canceling` state, at which time the system may complete any in-progress,
     * non-interruptible requests before finalizing cancellation.
     *
     * The number of canceled requests is specified in `request_counts`. To determine which requests
     * were canceled, check the individual results within the batch. Note that cancellation may not
     * result in any canceled requests if they were non-interruptible.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    fun cancel(params: BetaMessageBatchCancelParams): BetaMessageBatch =
        cancel(params, RequestOptions.none())

    /** @see [cancel] */
    fun cancel(
        params: BetaMessageBatchCancelParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch

    /**
     * Streams the results of a Message Batch as a `.jsonl` file.
     *
     * Each line in the file is a JSON object containing the result of a single request in the
     * Message Batch. Results are not guaranteed to be in the same order as requests. Use the
     * `custom_id` field to match results to requests.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    @MustBeClosed
    fun resultsStreaming(
        params: BetaMessageBatchResultsParams
    ): StreamResponse<BetaMessageBatchIndividualResponse> =
        resultsStreaming(params, RequestOptions.none())

    /** @see [resultsStreaming] */
    @MustBeClosed
    fun resultsStreaming(
        params: BetaMessageBatchResultsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<BetaMessageBatchIndividualResponse>

    /** A view of [BatchService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a raw HTTP response for `post /v1/messages/batches?beta=true`, but is otherwise
         * the same as [BatchService.create].
         */
        @MustBeClosed
        fun create(params: BetaMessageBatchCreateParams): HttpResponseFor<BetaMessageBatch> =
            create(params, RequestOptions.none())

        /** @see [create] */
        @MustBeClosed
        fun create(
            params: BetaMessageBatchCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch>

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches/{message_batch_id}?beta=true`,
         * but is otherwise the same as [BatchService.retrieve].
         */
        @MustBeClosed
        fun retrieve(params: BetaMessageBatchRetrieveParams): HttpResponseFor<BetaMessageBatch> =
            retrieve(params, RequestOptions.none())

        /** @see [retrieve] */
        @MustBeClosed
        fun retrieve(
            params: BetaMessageBatchRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch>

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches?beta=true`, but is otherwise
         * the same as [BatchService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<BetaMessageBatchListPage> =
            list(BetaMessageBatchListParams.none())

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: BetaMessageBatchListParams = BetaMessageBatchListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatchListPage>

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: BetaMessageBatchListParams = BetaMessageBatchListParams.none()
        ): HttpResponseFor<BetaMessageBatchListPage> = list(params, RequestOptions.none())

        /** @see [list] */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<BetaMessageBatchListPage> =
            list(BetaMessageBatchListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/messages/batches/{message_batch_id}?beta=true`, but is otherwise the same as
         * [BatchService.delete].
         */
        @MustBeClosed
        fun delete(params: BetaMessageBatchDeleteParams): HttpResponseFor<BetaDeletedMessageBatch> =
            delete(params, RequestOptions.none())

        /** @see [delete] */
        @MustBeClosed
        fun delete(
            params: BetaMessageBatchDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedMessageBatch>

        /**
         * Returns a raw HTTP response for `post
         * /v1/messages/batches/{message_batch_id}/cancel?beta=true`, but is otherwise the same as
         * [BatchService.cancel].
         */
        @MustBeClosed
        fun cancel(params: BetaMessageBatchCancelParams): HttpResponseFor<BetaMessageBatch> =
            cancel(params, RequestOptions.none())

        /** @see [cancel] */
        @MustBeClosed
        fun cancel(
            params: BetaMessageBatchCancelParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch>

        /**
         * Returns a raw HTTP response for `get
         * /v1/messages/batches/{message_batch_id}/results?beta=true`, but is otherwise the same as
         * [BatchService.resultsStreaming].
         */
        @MustBeClosed
        fun resultsStreaming(
            params: BetaMessageBatchResultsParams
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> =
            resultsStreaming(params, RequestOptions.none())

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            params: BetaMessageBatchResultsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>>
    }
}
