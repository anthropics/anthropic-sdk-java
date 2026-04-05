// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.messages

import kotlinx.kmp.util.core.ClientOptions
import kotlinx.kmp.util.core.RequestOptions
import kotlinx.kmp.util.core.http.AsyncStreamResponse
import kotlinx.kmp.util.core.http.HttpResponseFor
import kotlinx.kmp.util.core.http.StreamResponse
import com.anthropic.models.beta.messages.batches.BatchCancelParams
import com.anthropic.models.beta.messages.batches.BatchCreateParams
import com.anthropic.models.beta.messages.batches.BatchDeleteParams
import com.anthropic.models.beta.messages.batches.BatchListPageAsync
import com.anthropic.models.beta.messages.batches.BatchListParams
import com.anthropic.models.beta.messages.batches.BatchResultsParams
import com.anthropic.models.beta.messages.batches.BatchRetrieveParams
import com.anthropic.models.beta.messages.batches.BetaDeletedMessageBatch
import com.anthropic.models.beta.messages.batches.BetaMessageBatch
import com.anthropic.models.beta.messages.batches.BetaMessageBatchIndividualResponse
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface BatchServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): BatchServiceAsync

    /**
     * Send a batch of Message creation requests.
     *
     * The Message Batches API can be used to process multiple Messages API requests at once. Once a
     * Message Batch is created, it begins processing immediately. Batches can take up to 24 hours
     * to complete.
     *
     * Learn more about the Message Batches API in our
     * [user guide](https://docs.claude.com/en/docs/build-with-claude/batch-processing)
     */
    suspend fun create(params: BatchCreateParams): BetaMessageBatch =
        create(params, RequestOptions.none())

    /** @see create */
    suspend fun create(
        params: BatchCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch

    /**
     * This endpoint is idempotent and can be used to poll for Message Batch completion. To access
     * the results of a Message Batch, make a request to the `results_url` field in the response.
     *
     * Learn more about the Message Batches API in our
     * [user guide](https://docs.claude.com/en/docs/build-with-claude/batch-processing)
     */
    suspend fun retrieve(messageBatchId: String): BetaMessageBatch =
        retrieve(messageBatchId, BatchRetrieveParams.none())

    /** @see retrieve */
    suspend fun retrieve(
        messageBatchId: String,
        params: BatchRetrieveParams = BatchRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch =
        retrieve(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        messageBatchId: String,
        params: BatchRetrieveParams = BatchRetrieveParams.none(),
    ): BetaMessageBatch = retrieve(messageBatchId, params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(
        params: BatchRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch

    /** @see retrieve */
    suspend fun retrieve(params: BatchRetrieveParams): BetaMessageBatch =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(
        messageBatchId: String,
        requestOptions: RequestOptions,
    ): BetaMessageBatch =
        retrieve(messageBatchId, BatchRetrieveParams.none(), requestOptions)

    /**
     * List all Message Batches within a Workspace. Most recently created batches are returned
     * first.
     *
     * Learn more about the Message Batches API in our
     * [user guide](https://docs.claude.com/en/docs/build-with-claude/batch-processing)
     */
    suspend fun list(): BatchListPageAsync = list(BatchListParams.none())

    /** @see list */
    suspend fun list(
        params: BatchListParams = BatchListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BatchListPageAsync

    /** @see list */
    suspend fun list(
        params: BatchListParams = BatchListParams.none()
    ): BatchListPageAsync = list(params, RequestOptions.none())

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): BatchListPageAsync =
        list(BatchListParams.none(), requestOptions)

    /**
     * Delete a Message Batch.
     *
     * Message Batches can only be deleted once they've finished processing. If you'd like to delete
     * an in-progress batch, you must first cancel it.
     *
     * Learn more about the Message Batches API in our
     * [user guide](https://docs.claude.com/en/docs/build-with-claude/batch-processing)
     */
    suspend fun delete(messageBatchId: String): BetaDeletedMessageBatch =
        delete(messageBatchId, BatchDeleteParams.none())

    /** @see delete */
    suspend fun delete(
        messageBatchId: String,
        params: BatchDeleteParams = BatchDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedMessageBatch =
        delete(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        messageBatchId: String,
        params: BatchDeleteParams = BatchDeleteParams.none(),
    ): BetaDeletedMessageBatch =
        delete(messageBatchId, params, RequestOptions.none())

    /** @see delete */
    suspend fun delete(
        params: BatchDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedMessageBatch

    /** @see delete */
    suspend fun delete(params: BatchDeleteParams): BetaDeletedMessageBatch =
        delete(params, RequestOptions.none())

    /** @see delete */
    suspend fun delete(
        messageBatchId: String,
        requestOptions: RequestOptions,
    ): BetaDeletedMessageBatch =
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
     * [user guide](https://docs.claude.com/en/docs/build-with-claude/batch-processing)
     */
    suspend fun cancel(messageBatchId: String): BetaMessageBatch =
        cancel(messageBatchId, BatchCancelParams.none())

    /** @see cancel */
    suspend fun cancel(
        messageBatchId: String,
        params: BatchCancelParams = BatchCancelParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch =
        cancel(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see cancel */
    suspend fun cancel(
        messageBatchId: String,
        params: BatchCancelParams = BatchCancelParams.none(),
    ): BetaMessageBatch = cancel(messageBatchId, params, RequestOptions.none())

    /** @see cancel */
    suspend fun cancel(
        params: BatchCancelParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaMessageBatch

    /** @see cancel */
    suspend fun cancel(params: BatchCancelParams): BetaMessageBatch =
        cancel(params, RequestOptions.none())

    /** @see cancel */
    suspend fun cancel(
        messageBatchId: String,
        requestOptions: RequestOptions,
    ): BetaMessageBatch =
        cancel(messageBatchId, BatchCancelParams.none(), requestOptions)

    /**
     * Streams the results of a Message Batch as a `.jsonl` file.
     *
     * Each line in the file is a JSON object containing the result of a single request in the
     * Message Batch. Results are not guaranteed to be in the same order as requests. Use the
     * `custom_id` field to match results to requests.
     *
     * Learn more about the Message Batches API in our
     * [user guide](https://docs.claude.com/en/docs/build-with-claude/batch-processing)
     */
    suspend fun resultsStreaming(
        messageBatchId: String
    ): AsyncStreamResponse<BetaMessageBatchIndividualResponse> =
        resultsStreaming(messageBatchId, BatchResultsParams.none())

    /** @see resultsStreaming */
    suspend fun resultsStreaming(
        messageBatchId: String,
        params: BatchResultsParams = BatchResultsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<BetaMessageBatchIndividualResponse> =
        resultsStreaming(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see resultsStreaming */
    suspend fun resultsStreaming(
        messageBatchId: String,
        params: BatchResultsParams = BatchResultsParams.none(),
    ): AsyncStreamResponse<BetaMessageBatchIndividualResponse> =
        resultsStreaming(messageBatchId, params, RequestOptions.none())

    /** @see resultsStreaming */
    suspend fun resultsStreaming(
        params: BatchResultsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<BetaMessageBatchIndividualResponse>

    /** @see resultsStreaming */
    suspend fun resultsStreaming(
        params: BatchResultsParams
    ): AsyncStreamResponse<BetaMessageBatchIndividualResponse> =
        resultsStreaming(params, RequestOptions.none())

    /** @see resultsStreaming */
    suspend fun resultsStreaming(
        messageBatchId: String,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<BetaMessageBatchIndividualResponse> =
        resultsStreaming(messageBatchId, BatchResultsParams.none(), requestOptions)

    /** A view of [BatchServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): BatchServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/messages/batches?beta=true`, but is otherwise
         * the same as [BatchServiceAsync.create].
         */
        suspend fun create(
            params: BatchCreateParams
        ): HttpResponseFor<BetaMessageBatch> =
            create(params, RequestOptions.none())

        /** @see create */
        suspend fun create(
            params: BatchCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch>

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches/{message_batch_id}?beta=true`,
         * but is otherwise the same as [BatchServiceAsync.retrieve].
         */
        suspend fun retrieve(messageBatchId: String): HttpResponseFor<BetaMessageBatch> =
            retrieve(messageBatchId, BatchRetrieveParams.none())

        /** @see retrieve */
        suspend fun retrieve(
            messageBatchId: String,
            params: BatchRetrieveParams = BatchRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch> =
            retrieve(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

        /** @see retrieve */
        suspend fun retrieve(
            messageBatchId: String,
            params: BatchRetrieveParams = BatchRetrieveParams.none(),
        ): HttpResponseFor<BetaMessageBatch> =
            retrieve(messageBatchId, params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(
            params: BatchRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch>

        /** @see retrieve */
        suspend fun retrieve(
            params: BatchRetrieveParams
        ): HttpResponseFor<BetaMessageBatch> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageBatch> =
            retrieve(messageBatchId, BatchRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches?beta=true`, but is otherwise
         * the same as [BatchServiceAsync.list].
         */
        suspend fun list(): HttpResponseFor<BatchListPageAsync> =
            list(BatchListParams.none())

        /** @see list */
        suspend fun list(
            params: BatchListParams = BatchListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BatchListPageAsync>

        /** @see list */
        suspend fun list(
            params: BatchListParams = BatchListParams.none()
        ): HttpResponseFor<BatchListPageAsync> =
            list(params, RequestOptions.none())

        /** @see list */
        suspend fun list(
            requestOptions: RequestOptions
        ): HttpResponseFor<BatchListPageAsync> =
            list(BatchListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/messages/batches/{message_batch_id}?beta=true`, but is otherwise the same as
         * [BatchServiceAsync.delete].
         */
        suspend fun delete(
            messageBatchId: String
        ): HttpResponseFor<BetaDeletedMessageBatch> =
            delete(messageBatchId, BatchDeleteParams.none())

        /** @see delete */
        suspend fun delete(
            messageBatchId: String,
            params: BatchDeleteParams = BatchDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedMessageBatch> =
            delete(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

        /** @see delete */
        suspend fun delete(
            messageBatchId: String,
            params: BatchDeleteParams = BatchDeleteParams.none(),
        ): HttpResponseFor<BetaDeletedMessageBatch> =
            delete(messageBatchId, params, RequestOptions.none())

        /** @see delete */
        suspend fun delete(
            params: BatchDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedMessageBatch>

        /** @see delete */
        suspend fun delete(
            params: BatchDeleteParams
        ): HttpResponseFor<BetaDeletedMessageBatch> =
            delete(params, RequestOptions.none())

        /** @see delete */
        suspend fun delete(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedMessageBatch> =
            delete(messageBatchId, BatchDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/messages/batches/{message_batch_id}/cancel?beta=true`, but is otherwise the same as
         * [BatchServiceAsync.cancel].
         */
        suspend fun cancel(messageBatchId: String): HttpResponseFor<BetaMessageBatch> =
            cancel(messageBatchId, BatchCancelParams.none())

        /** @see cancel */
        suspend fun cancel(
            messageBatchId: String,
            params: BatchCancelParams = BatchCancelParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch> =
            cancel(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

        /** @see cancel */
        suspend fun cancel(
            messageBatchId: String,
            params: BatchCancelParams = BatchCancelParams.none(),
        ): HttpResponseFor<BetaMessageBatch> =
            cancel(messageBatchId, params, RequestOptions.none())

        /** @see cancel */
        suspend fun cancel(
            params: BatchCancelParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaMessageBatch>

        /** @see cancel */
        suspend fun cancel(
            params: BatchCancelParams
        ): HttpResponseFor<BetaMessageBatch> =
            cancel(params, RequestOptions.none())

        /** @see cancel */
        suspend fun cancel(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageBatch> =
            cancel(messageBatchId, BatchCancelParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get
         * /v1/messages/batches/{message_batch_id}/results?beta=true`, but is otherwise the same as
         * [BatchServiceAsync.resultsStreaming].
         */
        @MustBeClosed
        suspend fun resultsStreaming(
            messageBatchId: String
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> =
            resultsStreaming(messageBatchId, BatchResultsParams.none())

        /** @see resultsStreaming */
        @MustBeClosed
        suspend fun resultsStreaming(
            messageBatchId: String,
            params: BatchResultsParams = BatchResultsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> =
            resultsStreaming(
                params.toBuilder().messageBatchId(messageBatchId).build(),
                requestOptions,
            )

        /** @see resultsStreaming */
        @MustBeClosed
        suspend fun resultsStreaming(
            messageBatchId: String,
            params: BatchResultsParams = BatchResultsParams.none(),
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> =
            resultsStreaming(messageBatchId, params, RequestOptions.none())

        /** @see resultsStreaming */
        @MustBeClosed
        suspend fun resultsStreaming(
            params: BatchResultsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>>

        /** @see resultsStreaming */
        @MustBeClosed
        suspend fun resultsStreaming(
            params: BatchResultsParams
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> =
            resultsStreaming(params, RequestOptions.none())

        /** @see resultsStreaming */
        @MustBeClosed
        suspend fun resultsStreaming(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> =
            resultsStreaming(messageBatchId, BatchResultsParams.none(), requestOptions)
    }
}
