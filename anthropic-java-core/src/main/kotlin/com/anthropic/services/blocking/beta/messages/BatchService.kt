// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.messages

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.beta.messages.batches.BatchCancelParams
import com.anthropic.models.beta.messages.batches.BatchCreateParams
import com.anthropic.models.beta.messages.batches.BatchDeleteParams
import com.anthropic.models.beta.messages.batches.BatchListPage
import com.anthropic.models.beta.messages.batches.BatchListParams
import com.anthropic.models.beta.messages.batches.BatchResultsParams
import com.anthropic.models.beta.messages.batches.BatchRetrieveParams
import com.anthropic.models.beta.messages.batches.BetaDeletedMessageBatch
import com.anthropic.models.beta.messages.batches.BetaMessageBatch
import com.anthropic.models.beta.messages.batches.BetaMessageBatchIndividualResponse
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface BatchService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): BatchService

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
    fun create(params: BatchCreateParams): BetaMessageBatch = create(params, RequestOptions.none())

    /** @see [create] */
    fun create(
        params: BatchCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch

    /**
     * This endpoint is idempotent and can be used to poll for Message Batch completion. To access
     * the results of a Message Batch, make a request to the `results_url` field in the response.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    fun retrieve(messageBatchId: String): BetaMessageBatch =
        retrieve(messageBatchId, BatchRetrieveParams.none())

    /** @see [retrieve] */
    fun retrieve(
        messageBatchId: String,
        params: BatchRetrieveParams = BatchRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch =
        retrieve(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see [retrieve] */
    fun retrieve(
        messageBatchId: String,
        params: BatchRetrieveParams = BatchRetrieveParams.none(),
    ): BetaMessageBatch = retrieve(messageBatchId, params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(
        params: BatchRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch

    /** @see [retrieve] */
    fun retrieve(params: BatchRetrieveParams): BetaMessageBatch =
        retrieve(params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(messageBatchId: String, requestOptions: RequestOptions): BetaMessageBatch =
        retrieve(messageBatchId, BatchRetrieveParams.none(), requestOptions)

    /**
     * List all Message Batches within a Workspace. Most recently created batches are returned
     * first.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    fun list(): BatchListPage = list(BatchListParams.none())

    /** @see [list] */
    fun list(
        params: BatchListParams = BatchListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BatchListPage

    /** @see [list] */
    fun list(params: BatchListParams = BatchListParams.none()): BatchListPage =
        list(params, RequestOptions.none())

    /** @see [list] */
    fun list(requestOptions: RequestOptions): BatchListPage =
        list(BatchListParams.none(), requestOptions)

    /**
     * Delete a Message Batch.
     *
     * Message Batches can only be deleted once they've finished processing. If you'd like to delete
     * an in-progress batch, you must first cancel it.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    fun delete(messageBatchId: String): BetaDeletedMessageBatch =
        delete(messageBatchId, BatchDeleteParams.none())

    /** @see [delete] */
    fun delete(
        messageBatchId: String,
        params: BatchDeleteParams = BatchDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedMessageBatch =
        delete(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see [delete] */
    fun delete(
        messageBatchId: String,
        params: BatchDeleteParams = BatchDeleteParams.none(),
    ): BetaDeletedMessageBatch = delete(messageBatchId, params, RequestOptions.none())

    /** @see [delete] */
    fun delete(
        params: BatchDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedMessageBatch

    /** @see [delete] */
    fun delete(params: BatchDeleteParams): BetaDeletedMessageBatch =
        delete(params, RequestOptions.none())

    /** @see [delete] */
    fun delete(messageBatchId: String, requestOptions: RequestOptions): BetaDeletedMessageBatch =
        delete(messageBatchId, BatchDeleteParams.none(), requestOptions)

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
    fun cancel(messageBatchId: String): BetaMessageBatch =
        cancel(messageBatchId, BatchCancelParams.none())

    /** @see [cancel] */
    fun cancel(
        messageBatchId: String,
        params: BatchCancelParams = BatchCancelParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch =
        cancel(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see [cancel] */
    fun cancel(
        messageBatchId: String,
        params: BatchCancelParams = BatchCancelParams.none(),
    ): BetaMessageBatch = cancel(messageBatchId, params, RequestOptions.none())

    /** @see [cancel] */
    fun cancel(
        params: BatchCancelParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch

    /** @see [cancel] */
    fun cancel(params: BatchCancelParams): BetaMessageBatch = cancel(params, RequestOptions.none())

    /** @see [cancel] */
    fun cancel(messageBatchId: String, requestOptions: RequestOptions): BetaMessageBatch =
        cancel(messageBatchId, BatchCancelParams.none(), requestOptions)

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
        messageBatchId: String
    ): StreamResponse<BetaMessageBatchIndividualResponse> =
        resultsStreaming(messageBatchId, BatchResultsParams.none())

    /** @see [resultsStreaming] */
    @MustBeClosed
    fun resultsStreaming(
        messageBatchId: String,
        params: BatchResultsParams = BatchResultsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<BetaMessageBatchIndividualResponse> =
        resultsStreaming(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see [resultsStreaming] */
    @MustBeClosed
    fun resultsStreaming(
        messageBatchId: String,
        params: BatchResultsParams = BatchResultsParams.none(),
    ): StreamResponse<BetaMessageBatchIndividualResponse> =
        resultsStreaming(messageBatchId, params, RequestOptions.none())

    /** @see [resultsStreaming] */
    @MustBeClosed
    fun resultsStreaming(
        params: BatchResultsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<BetaMessageBatchIndividualResponse>

    /** @see [resultsStreaming] */
    @MustBeClosed
    fun resultsStreaming(
        params: BatchResultsParams
    ): StreamResponse<BetaMessageBatchIndividualResponse> =
        resultsStreaming(params, RequestOptions.none())

    /** @see [resultsStreaming] */
    @MustBeClosed
    fun resultsStreaming(
        messageBatchId: String,
        requestOptions: RequestOptions,
    ): StreamResponse<BetaMessageBatchIndividualResponse> =
        resultsStreaming(messageBatchId, BatchResultsParams.none(), requestOptions)

    /** A view of [BatchService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): BatchService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/messages/batches?beta=true`, but is otherwise
         * the same as [BatchService.create].
         */
        @MustBeClosed
        fun create(params: BatchCreateParams): HttpResponseFor<BetaMessageBatch> =
            create(params, RequestOptions.none())

        /** @see [create] */
        @MustBeClosed
        fun create(
            params: BatchCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch>

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches/{message_batch_id}?beta=true`,
         * but is otherwise the same as [BatchService.retrieve].
         */
        @MustBeClosed
        fun retrieve(messageBatchId: String): HttpResponseFor<BetaMessageBatch> =
            retrieve(messageBatchId, BatchRetrieveParams.none())

        /** @see [retrieve] */
        @MustBeClosed
        fun retrieve(
            messageBatchId: String,
            params: BatchRetrieveParams = BatchRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch> =
            retrieve(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

        /** @see [retrieve] */
        @MustBeClosed
        fun retrieve(
            messageBatchId: String,
            params: BatchRetrieveParams = BatchRetrieveParams.none(),
        ): HttpResponseFor<BetaMessageBatch> =
            retrieve(messageBatchId, params, RequestOptions.none())

        /** @see [retrieve] */
        @MustBeClosed
        fun retrieve(
            params: BatchRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch>

        /** @see [retrieve] */
        @MustBeClosed
        fun retrieve(params: BatchRetrieveParams): HttpResponseFor<BetaMessageBatch> =
            retrieve(params, RequestOptions.none())

        /** @see [retrieve] */
        @MustBeClosed
        fun retrieve(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageBatch> =
            retrieve(messageBatchId, BatchRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches?beta=true`, but is otherwise
         * the same as [BatchService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<BatchListPage> = list(BatchListParams.none())

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: BatchListParams = BatchListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BatchListPage>

        /** @see [list] */
        @MustBeClosed
        fun list(params: BatchListParams = BatchListParams.none()): HttpResponseFor<BatchListPage> =
            list(params, RequestOptions.none())

        /** @see [list] */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<BatchListPage> =
            list(BatchListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/messages/batches/{message_batch_id}?beta=true`, but is otherwise the same as
         * [BatchService.delete].
         */
        @MustBeClosed
        fun delete(messageBatchId: String): HttpResponseFor<BetaDeletedMessageBatch> =
            delete(messageBatchId, BatchDeleteParams.none())

        /** @see [delete] */
        @MustBeClosed
        fun delete(
            messageBatchId: String,
            params: BatchDeleteParams = BatchDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedMessageBatch> =
            delete(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

        /** @see [delete] */
        @MustBeClosed
        fun delete(
            messageBatchId: String,
            params: BatchDeleteParams = BatchDeleteParams.none(),
        ): HttpResponseFor<BetaDeletedMessageBatch> =
            delete(messageBatchId, params, RequestOptions.none())

        /** @see [delete] */
        @MustBeClosed
        fun delete(
            params: BatchDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedMessageBatch>

        /** @see [delete] */
        @MustBeClosed
        fun delete(params: BatchDeleteParams): HttpResponseFor<BetaDeletedMessageBatch> =
            delete(params, RequestOptions.none())

        /** @see [delete] */
        @MustBeClosed
        fun delete(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedMessageBatch> =
            delete(messageBatchId, BatchDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/messages/batches/{message_batch_id}/cancel?beta=true`, but is otherwise the same as
         * [BatchService.cancel].
         */
        @MustBeClosed
        fun cancel(messageBatchId: String): HttpResponseFor<BetaMessageBatch> =
            cancel(messageBatchId, BatchCancelParams.none())

        /** @see [cancel] */
        @MustBeClosed
        fun cancel(
            messageBatchId: String,
            params: BatchCancelParams = BatchCancelParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch> =
            cancel(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

        /** @see [cancel] */
        @MustBeClosed
        fun cancel(
            messageBatchId: String,
            params: BatchCancelParams = BatchCancelParams.none(),
        ): HttpResponseFor<BetaMessageBatch> = cancel(messageBatchId, params, RequestOptions.none())

        /** @see [cancel] */
        @MustBeClosed
        fun cancel(
            params: BatchCancelParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch>

        /** @see [cancel] */
        @MustBeClosed
        fun cancel(params: BatchCancelParams): HttpResponseFor<BetaMessageBatch> =
            cancel(params, RequestOptions.none())

        /** @see [cancel] */
        @MustBeClosed
        fun cancel(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageBatch> =
            cancel(messageBatchId, BatchCancelParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get
         * /v1/messages/batches/{message_batch_id}/results?beta=true`, but is otherwise the same as
         * [BatchService.resultsStreaming].
         */
        @MustBeClosed
        fun resultsStreaming(
            messageBatchId: String
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> =
            resultsStreaming(messageBatchId, BatchResultsParams.none())

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            messageBatchId: String,
            params: BatchResultsParams = BatchResultsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> =
            resultsStreaming(
                params.toBuilder().messageBatchId(messageBatchId).build(),
                requestOptions,
            )

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            messageBatchId: String,
            params: BatchResultsParams = BatchResultsParams.none(),
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> =
            resultsStreaming(messageBatchId, params, RequestOptions.none())

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            params: BatchResultsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>>

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            params: BatchResultsParams
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> =
            resultsStreaming(params, RequestOptions.none())

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> =
            resultsStreaming(messageBatchId, BatchResultsParams.none(), requestOptions)
    }
}
