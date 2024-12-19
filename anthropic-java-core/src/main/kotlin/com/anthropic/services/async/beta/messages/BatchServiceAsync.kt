// File generated from our OpenAPI spec by Stainless.

@file:Suppress("OVERLOADS_INTERFACE") // See https://youtrack.jetbrains.com/issue/KT-36102

package com.anthropic.services.async.beta.messages

import com.anthropic.core.RequestOptions
import com.anthropic.models.BetaDeletedMessageBatch
import com.anthropic.models.BetaMessageBatch
import com.anthropic.models.BetaMessageBatchCancelParams
import com.anthropic.models.BetaMessageBatchCreateParams
import com.anthropic.models.BetaMessageBatchDeleteParams
import com.anthropic.models.BetaMessageBatchListPageAsync
import com.anthropic.models.BetaMessageBatchListParams
import com.anthropic.models.BetaMessageBatchRetrieveParams
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
        params: BetaMessageBatchCreateParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): CompletableFuture<BetaMessageBatch>

    /**
     * This endpoint is idempotent and can be used to poll for Message Batch completion. To access
     * the results of a Message Batch, make a request to the `results_url` field in the response.
     */
    @JvmOverloads
    fun retrieve(
        params: BetaMessageBatchRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): CompletableFuture<BetaMessageBatch>

    /**
     * List all Message Batches within a Workspace. Most recently created batches are returned
     * first.
     */
    @JvmOverloads
    fun list(
        params: BetaMessageBatchListParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): CompletableFuture<BetaMessageBatchListPageAsync>

    /**
     * This endpoint is idempotent and can be used to poll for Message Batch completion. To access
     * the results of a Message Batch, make a request to the `results_url` field in the response.
     */
    @JvmOverloads
    fun delete(
        params: BetaMessageBatchDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): CompletableFuture<BetaDeletedMessageBatch>

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
        params: BetaMessageBatchCancelParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): CompletableFuture<BetaMessageBatch>
}
