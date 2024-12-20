// File generated from our OpenAPI spec by Stainless.

@file:Suppress("OVERLOADS_INTERFACE") // See https://youtrack.jetbrains.com/issue/KT-36102

package com.anthropic.services.async.messages

import com.anthropic.core.RequestOptions
import com.anthropic.models.MessageBatch
import com.anthropic.models.MessageBatchCancelParams
import com.anthropic.models.MessageBatchCreateParams
import com.anthropic.models.MessageBatchListPageAsync
import com.anthropic.models.MessageBatchListParams
import com.anthropic.models.MessageBatchRetrieveParams
import java.util.concurrent.CompletableFuture

interface BatchServiceAsync {

    /**
     * Send a batch of Message creation requests.
     *
     * The Message Batches API can be used to process multiple Messages API requests at once. Once a
     * Message Batch is created, it begins processing immediately. Batches can take up to 24 hours
     * to complete.
     */
    @JvmOverloads
    fun create(
        params: MessageBatchCreateParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): CompletableFuture<MessageBatch>

    /**
     * This endpoint is idempotent and can be used to poll for Message Batch completion. To access
     * the results of a Message Batch, make a request to the `results_url` field in the response.
     */
    @JvmOverloads
    fun retrieve(
        params: MessageBatchRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): CompletableFuture<MessageBatch>

    /**
     * List all Message Batches within a Workspace. Most recently created batches are returned
     * first.
     */
    @JvmOverloads
    fun list(
        params: MessageBatchListParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): CompletableFuture<MessageBatchListPageAsync>

    /**
     * Batches may be canceled any time before processing ends. Once cancellation is initiated, the
     * batch enters a `canceling` state, at which time the system may complete any in-progress,
     * non-interruptible requests before finalizing cancellation.
     *
     * The number of canceled requests is specified in `request_counts`. To determine which requests
     * were canceled, check the individual results within the batch. Note that cancellation may not
     * result in any canceled requests if they were non-interruptible.
     */
    @JvmOverloads
    fun cancel(
        params: MessageBatchCancelParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): CompletableFuture<MessageBatch>
}
