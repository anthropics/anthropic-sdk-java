// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.messages

import com.anthropic.core.ClientOptions
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
    fun retrieve(messageBatchId: String): CompletableFuture<MessageBatch> =
        retrieve(messageBatchId, BatchRetrieveParams.none())

    /** @see [retrieve] */
    fun retrieve(
        messageBatchId: String,
        params: BatchRetrieveParams = BatchRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MessageBatch> =
        retrieve(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see [retrieve] */
    fun retrieve(
        messageBatchId: String,
        params: BatchRetrieveParams = BatchRetrieveParams.none(),
    ): CompletableFuture<MessageBatch> = retrieve(messageBatchId, params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(
        params: BatchRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MessageBatch>

    /** @see [retrieve] */
    fun retrieve(params: BatchRetrieveParams): CompletableFuture<MessageBatch> =
        retrieve(params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(
        messageBatchId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<MessageBatch> =
        retrieve(messageBatchId, BatchRetrieveParams.none(), requestOptions)

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
    fun delete(messageBatchId: String): CompletableFuture<DeletedMessageBatch> =
        delete(messageBatchId, BatchDeleteParams.none())

    /** @see [delete] */
    fun delete(
        messageBatchId: String,
        params: BatchDeleteParams = BatchDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeletedMessageBatch> =
        delete(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see [delete] */
    fun delete(
        messageBatchId: String,
        params: BatchDeleteParams = BatchDeleteParams.none(),
    ): CompletableFuture<DeletedMessageBatch> =
        delete(messageBatchId, params, RequestOptions.none())

    /** @see [delete] */
    fun delete(
        params: BatchDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeletedMessageBatch>

    /** @see [delete] */
    fun delete(params: BatchDeleteParams): CompletableFuture<DeletedMessageBatch> =
        delete(params, RequestOptions.none())

    /** @see [delete] */
    fun delete(
        messageBatchId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<DeletedMessageBatch> =
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
    fun cancel(messageBatchId: String): CompletableFuture<MessageBatch> =
        cancel(messageBatchId, BatchCancelParams.none())

    /** @see [cancel] */
    fun cancel(
        messageBatchId: String,
        params: BatchCancelParams = BatchCancelParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MessageBatch> =
        cancel(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see [cancel] */
    fun cancel(
        messageBatchId: String,
        params: BatchCancelParams = BatchCancelParams.none(),
    ): CompletableFuture<MessageBatch> = cancel(messageBatchId, params, RequestOptions.none())

    /** @see [cancel] */
    fun cancel(
        params: BatchCancelParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MessageBatch>

    /** @see [cancel] */
    fun cancel(params: BatchCancelParams): CompletableFuture<MessageBatch> =
        cancel(params, RequestOptions.none())

    /** @see [cancel] */
    fun cancel(
        messageBatchId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<MessageBatch> =
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
    fun resultsStreaming(
        messageBatchId: String
    ): AsyncStreamResponse<MessageBatchIndividualResponse> =
        resultsStreaming(messageBatchId, BatchResultsParams.none())

    /** @see [resultsStreaming] */
    fun resultsStreaming(
        messageBatchId: String,
        params: BatchResultsParams = BatchResultsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<MessageBatchIndividualResponse> =
        resultsStreaming(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

    /** @see [resultsStreaming] */
    fun resultsStreaming(
        messageBatchId: String,
        params: BatchResultsParams = BatchResultsParams.none(),
    ): AsyncStreamResponse<MessageBatchIndividualResponse> =
        resultsStreaming(messageBatchId, params, RequestOptions.none())

    /** @see [resultsStreaming] */
    fun resultsStreaming(
        params: BatchResultsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<MessageBatchIndividualResponse>

    /** @see [resultsStreaming] */
    fun resultsStreaming(
        params: BatchResultsParams
    ): AsyncStreamResponse<MessageBatchIndividualResponse> =
        resultsStreaming(params, RequestOptions.none())

    /** @see [resultsStreaming] */
    fun resultsStreaming(
        messageBatchId: String,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<MessageBatchIndividualResponse> =
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
         * Returns a raw HTTP response for `post /v1/messages/batches`, but is otherwise the same as
         * [BatchServiceAsync.create].
         */
        fun create(params: BatchCreateParams): CompletableFuture<HttpResponseFor<MessageBatch>> =
            create(params, RequestOptions.none())

        /** @see [create] */
        fun create(
            params: BatchCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MessageBatch>>

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches/{message_batch_id}`, but is
         * otherwise the same as [BatchServiceAsync.retrieve].
         */
        fun retrieve(messageBatchId: String): CompletableFuture<HttpResponseFor<MessageBatch>> =
            retrieve(messageBatchId, BatchRetrieveParams.none())

        /** @see [retrieve] */
        fun retrieve(
            messageBatchId: String,
            params: BatchRetrieveParams = BatchRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MessageBatch>> =
            retrieve(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

        /** @see [retrieve] */
        fun retrieve(
            messageBatchId: String,
            params: BatchRetrieveParams = BatchRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<MessageBatch>> =
            retrieve(messageBatchId, params, RequestOptions.none())

        /** @see [retrieve] */
        fun retrieve(
            params: BatchRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MessageBatch>>

        /** @see [retrieve] */
        fun retrieve(
            params: BatchRetrieveParams
        ): CompletableFuture<HttpResponseFor<MessageBatch>> =
            retrieve(params, RequestOptions.none())

        /** @see [retrieve] */
        fun retrieve(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MessageBatch>> =
            retrieve(messageBatchId, BatchRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches`, but is otherwise the same as
         * [BatchServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<BatchListPageAsync>> =
            list(BatchListParams.none())

        /** @see [list] */
        fun list(
            params: BatchListParams = BatchListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BatchListPageAsync>>

        /** @see [list] */
        fun list(
            params: BatchListParams = BatchListParams.none()
        ): CompletableFuture<HttpResponseFor<BatchListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see [list] */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<BatchListPageAsync>> =
            list(BatchListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/messages/batches/{message_batch_id}`, but is
         * otherwise the same as [BatchServiceAsync.delete].
         */
        fun delete(
            messageBatchId: String
        ): CompletableFuture<HttpResponseFor<DeletedMessageBatch>> =
            delete(messageBatchId, BatchDeleteParams.none())

        /** @see [delete] */
        fun delete(
            messageBatchId: String,
            params: BatchDeleteParams = BatchDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeletedMessageBatch>> =
            delete(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

        /** @see [delete] */
        fun delete(
            messageBatchId: String,
            params: BatchDeleteParams = BatchDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<DeletedMessageBatch>> =
            delete(messageBatchId, params, RequestOptions.none())

        /** @see [delete] */
        fun delete(
            params: BatchDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeletedMessageBatch>>

        /** @see [delete] */
        fun delete(
            params: BatchDeleteParams
        ): CompletableFuture<HttpResponseFor<DeletedMessageBatch>> =
            delete(params, RequestOptions.none())

        /** @see [delete] */
        fun delete(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<DeletedMessageBatch>> =
            delete(messageBatchId, BatchDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/messages/batches/{message_batch_id}/cancel`,
         * but is otherwise the same as [BatchServiceAsync.cancel].
         */
        fun cancel(messageBatchId: String): CompletableFuture<HttpResponseFor<MessageBatch>> =
            cancel(messageBatchId, BatchCancelParams.none())

        /** @see [cancel] */
        fun cancel(
            messageBatchId: String,
            params: BatchCancelParams = BatchCancelParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MessageBatch>> =
            cancel(params.toBuilder().messageBatchId(messageBatchId).build(), requestOptions)

        /** @see [cancel] */
        fun cancel(
            messageBatchId: String,
            params: BatchCancelParams = BatchCancelParams.none(),
        ): CompletableFuture<HttpResponseFor<MessageBatch>> =
            cancel(messageBatchId, params, RequestOptions.none())

        /** @see [cancel] */
        fun cancel(
            params: BatchCancelParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MessageBatch>>

        /** @see [cancel] */
        fun cancel(params: BatchCancelParams): CompletableFuture<HttpResponseFor<MessageBatch>> =
            cancel(params, RequestOptions.none())

        /** @see [cancel] */
        fun cancel(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MessageBatch>> =
            cancel(messageBatchId, BatchCancelParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/messages/batches/{message_batch_id}/results`,
         * but is otherwise the same as [BatchServiceAsync.resultsStreaming].
         */
        @MustBeClosed
        fun resultsStreaming(
            messageBatchId: String
        ): CompletableFuture<HttpResponseFor<StreamResponse<MessageBatchIndividualResponse>>> =
            resultsStreaming(messageBatchId, BatchResultsParams.none())

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            messageBatchId: String,
            params: BatchResultsParams = BatchResultsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<MessageBatchIndividualResponse>>> =
            resultsStreaming(
                params.toBuilder().messageBatchId(messageBatchId).build(),
                requestOptions,
            )

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            messageBatchId: String,
            params: BatchResultsParams = BatchResultsParams.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<MessageBatchIndividualResponse>>> =
            resultsStreaming(messageBatchId, params, RequestOptions.none())

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            params: BatchResultsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<MessageBatchIndividualResponse>>>

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            params: BatchResultsParams
        ): CompletableFuture<HttpResponseFor<StreamResponse<MessageBatchIndividualResponse>>> =
            resultsStreaming(params, RequestOptions.none())

        /** @see [resultsStreaming] */
        @MustBeClosed
        fun resultsStreaming(
            messageBatchId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<StreamResponse<MessageBatchIndividualResponse>>> =
            resultsStreaming(messageBatchId, BatchResultsParams.none(), requestOptions)
    }
}
