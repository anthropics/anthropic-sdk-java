// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.messages

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.messages.batches.BatchCancelParams
import com.anthropic.models.messages.batches.BatchCreateParams
import com.anthropic.models.messages.batches.BatchDeleteParams
import com.anthropic.models.messages.batches.BatchListPageAsync
import com.anthropic.models.messages.batches.BatchListParams
import com.anthropic.models.messages.batches.BatchResultsParams
import com.anthropic.models.messages.batches.BatchRetrieveParams
import com.anthropic.models.messages.batches.DeletedMessageBatch
import com.anthropic.models.messages.batches.MessageBatch
import com.anthropic.models.messages.batches.MessageBatchIndividualResponse
import com.google.errorprone.annotations.MustBeClosed
import java.util.concurrent.CompletableFuture

interface BatchServiceAsync {

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
    fun create(params: BatchCreateParams): CompletableFuture<MessageBatch> =
        create(params, RequestOptions.none())

    /** @see [create] */
    fun create(
        params: BatchCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MessageBatch>

    /**
     * This endpoint is idempotent and can be used to poll for Message Batch completion. To access
     * the results of a Message Batch, make a request to the `results_url` field in the response.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    fun retrieve(params: BatchRetrieveParams): CompletableFuture<MessageBatch> =
        retrieve(params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(
        params: BatchRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MessageBatch>

    /**
     * List all Message Batches within a Workspace. Most recently created batches are returned
     * first.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    fun list(): CompletableFuture<BatchListPageAsync> = list(BatchListParams.none())

    /** @see [list] */
    fun list(
        params: BatchListParams = BatchListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BatchListPageAsync>

    /** @see [list] */
    fun list(
        params: BatchListParams = BatchListParams.none()
    ): CompletableFuture<BatchListPageAsync> = list(params, RequestOptions.none())

    /** @see [list] */
    fun list(requestOptions: RequestOptions): CompletableFuture<BatchListPageAsync> =
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
    fun delete(params: BatchDeleteParams): CompletableFuture<DeletedMessageBatch> =
        delete(params, RequestOptions.none())

    /** @see [delete] */
    fun delete(
        params: BatchDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeletedMessageBatch>

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
    fun cancel(params: BatchCancelParams): CompletableFuture<MessageBatch> =
        cancel(params, RequestOptions.none())

    /** @see [cancel] */
    fun cancel(
        params: BatchCancelParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MessageBatch>

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
    fun resultsStreaming(
        params: BatchResultsParams
    ): AsyncStreamResponse<MessageBatchIndividualResponse> =
        resultsStreaming(params, RequestOptions.none())

    /** @see [resultsStreaming] */
    fun resultsStreaming(
        params: BatchResultsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<MessageBatchIndividualResponse>

    /** A view of [BatchServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a raw HTTP response for `post /v1/messages/batches`, but is otherwise the same as
         * [BatchServiceAsync.create].
         */
        @MustBeClosed
        fun create(params: BatchCreateParams): CompletableFuture<HttpResponseFor<MessageBatch>> =
            create(params, RequestOptions.none())

        /** @see [create] */
        @MustBeClosed
        fun create(
            params: BatchCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MessageBatch>>

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches/{message_batch_id}`, but is
         * otherwise the same as [BatchServiceAsync.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            params: BatchRetrieveParams
        ): CompletableFuture<HttpResponseFor<MessageBatch>> =
            retrieve(params, RequestOptions.none())

        /** @see [retrieve] */
        @MustBeClosed
        fun retrieve(
            params: BatchRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MessageBatch>>

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches`, but is otherwise the same as
         * [BatchServiceAsync.list].
         */
        @MustBeClosed
        fun list(): CompletableFuture<HttpResponseFor<BatchListPageAsync>> =
            list(BatchListParams.none())

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: BatchListParams = BatchListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BatchListPageAsync>>

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: BatchListParams = BatchListParams.none()
        ): CompletableFuture<HttpResponseFor<BatchListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see [list] */
        @MustBeClosed
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<BatchListPageAsync>> =
            list(BatchListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/messages/batches/{message_batch_id}`, but is
         * otherwise the same as [BatchServiceAsync.delete].
         */
        @MustBeClosed
        fun delete(
            params: BatchDeleteParams
        ): CompletableFuture<HttpResponseFor<DeletedMessageBatch>> =
            delete(params, RequestOptions.none())

        /** @see [delete] */
        @MustBeClosed
        fun delete(
            params: BatchDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeletedMessageBatch>>

        /**
         * Returns a raw HTTP response for `post /v1/messages/batches/{message_batch_id}/cancel`,
         * but is otherwise the same as [BatchServiceAsync.cancel].
         */
        @MustBeClosed
        fun cancel(params: BatchCancelParams): CompletableFuture<HttpResponseFor<MessageBatch>> =
            cancel(params, RequestOptions.none())

        /** @see [cancel] */
        @MustBeClosed
        fun cancel(
            params: BatchCancelParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MessageBatch>>

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches/{message_batch_id}/results`,
         * but is otherwise the same as [BatchServiceAsync.resultsStreaming].
         */
        @MustBeClosed
        fun resultsStreaming(
            params: BatchResultsParams
        ): CompletableFuture<HttpResponseFor<StreamResponse<MessageBatchIndividualResponse>>> =
            resultsStreaming(params, RequestOptions.none())

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            params: BatchResultsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<MessageBatchIndividualResponse>>>
    }
}
