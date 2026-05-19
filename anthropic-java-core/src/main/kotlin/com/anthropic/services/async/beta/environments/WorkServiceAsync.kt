// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.environments

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.environments.work.BetaSelfHostedWork
import com.anthropic.models.beta.environments.work.BetaSelfHostedWorkHeartbeatResponse
import com.anthropic.models.beta.environments.work.BetaSelfHostedWorkQueueStats
import com.anthropic.models.beta.environments.work.WorkAckParams
import com.anthropic.models.beta.environments.work.WorkHeartbeatParams
import com.anthropic.models.beta.environments.work.WorkListPageAsync
import com.anthropic.models.beta.environments.work.WorkListParams
import com.anthropic.models.beta.environments.work.WorkPollParams
import com.anthropic.models.beta.environments.work.WorkRetrieveParams
import com.anthropic.models.beta.environments.work.WorkStatsParams
import com.anthropic.models.beta.environments.work.WorkStopParams
import com.anthropic.models.beta.environments.work.WorkUpdateParams
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface WorkServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkServiceAsync

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * Retrieve detailed information about a specific work item.
     */
    fun retrieve(
        workId: String,
        params: WorkRetrieveParams,
    ): CompletableFuture<BetaSelfHostedWork> = retrieve(workId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        workId: String,
        params: WorkRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWork> =
        retrieve(params.toBuilder().workId(workId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: WorkRetrieveParams): CompletableFuture<BetaSelfHostedWork> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: WorkRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWork>

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * Update work item metadata with merge semantics.
     */
    fun update(workId: String, params: WorkUpdateParams): CompletableFuture<BetaSelfHostedWork> =
        update(workId, params, RequestOptions.none())

    /** @see update */
    fun update(
        workId: String,
        params: WorkUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWork> =
        update(params.toBuilder().workId(workId).build(), requestOptions)

    /** @see update */
    fun update(params: WorkUpdateParams): CompletableFuture<BetaSelfHostedWork> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: WorkUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWork>

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * List work items in an environment.
     */
    fun list(environmentId: String): CompletableFuture<WorkListPageAsync> =
        list(environmentId, WorkListParams.none())

    /** @see list */
    fun list(
        environmentId: String,
        params: WorkListParams = WorkListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkListPageAsync> =
        list(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see list */
    fun list(
        environmentId: String,
        params: WorkListParams = WorkListParams.none(),
    ): CompletableFuture<WorkListPageAsync> = list(environmentId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: WorkListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkListPageAsync>

    /** @see list */
    fun list(params: WorkListParams): CompletableFuture<WorkListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        environmentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<WorkListPageAsync> =
        list(environmentId, WorkListParams.none(), requestOptions)

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * Acknowledge receipt of a work item, transitioning it from 'queued' to 'starting' and removing
     * it from the queue.
     */
    fun ack(workId: String, params: WorkAckParams): CompletableFuture<BetaSelfHostedWork> =
        ack(workId, params, RequestOptions.none())

    /** @see ack */
    fun ack(
        workId: String,
        params: WorkAckParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWork> =
        ack(params.toBuilder().workId(workId).build(), requestOptions)

    /** @see ack */
    fun ack(params: WorkAckParams): CompletableFuture<BetaSelfHostedWork> =
        ack(params, RequestOptions.none())

    /** @see ack */
    fun ack(
        params: WorkAckParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWork>

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * Record a heartbeat for a work item to maintain the lease.
     */
    fun heartbeat(
        workId: String,
        params: WorkHeartbeatParams,
    ): CompletableFuture<BetaSelfHostedWorkHeartbeatResponse> =
        heartbeat(workId, params, RequestOptions.none())

    /** @see heartbeat */
    fun heartbeat(
        workId: String,
        params: WorkHeartbeatParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWorkHeartbeatResponse> =
        heartbeat(params.toBuilder().workId(workId).build(), requestOptions)

    /** @see heartbeat */
    fun heartbeat(
        params: WorkHeartbeatParams
    ): CompletableFuture<BetaSelfHostedWorkHeartbeatResponse> =
        heartbeat(params, RequestOptions.none())

    /** @see heartbeat */
    fun heartbeat(
        params: WorkHeartbeatParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWorkHeartbeatResponse>

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * Long poll for work items in the queue.
     */
    fun poll(environmentId: String): CompletableFuture<Optional<BetaSelfHostedWork>> =
        poll(environmentId, WorkPollParams.none())

    /** @see poll */
    fun poll(
        environmentId: String,
        params: WorkPollParams = WorkPollParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Optional<BetaSelfHostedWork>> =
        poll(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see poll */
    fun poll(
        environmentId: String,
        params: WorkPollParams = WorkPollParams.none(),
    ): CompletableFuture<Optional<BetaSelfHostedWork>> =
        poll(environmentId, params, RequestOptions.none())

    /** @see poll */
    fun poll(
        params: WorkPollParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Optional<BetaSelfHostedWork>>

    /** @see poll */
    fun poll(params: WorkPollParams): CompletableFuture<Optional<BetaSelfHostedWork>> =
        poll(params, RequestOptions.none())

    /** @see poll */
    fun poll(
        environmentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<Optional<BetaSelfHostedWork>> =
        poll(environmentId, WorkPollParams.none(), requestOptions)

    /** Get statistics about the work queue for an environment. */
    fun stats(environmentId: String): CompletableFuture<BetaSelfHostedWorkQueueStats> =
        stats(environmentId, WorkStatsParams.none())

    /** @see stats */
    fun stats(
        environmentId: String,
        params: WorkStatsParams = WorkStatsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWorkQueueStats> =
        stats(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see stats */
    fun stats(
        environmentId: String,
        params: WorkStatsParams = WorkStatsParams.none(),
    ): CompletableFuture<BetaSelfHostedWorkQueueStats> =
        stats(environmentId, params, RequestOptions.none())

    /** @see stats */
    fun stats(
        params: WorkStatsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWorkQueueStats>

    /** @see stats */
    fun stats(params: WorkStatsParams): CompletableFuture<BetaSelfHostedWorkQueueStats> =
        stats(params, RequestOptions.none())

    /** @see stats */
    fun stats(
        environmentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaSelfHostedWorkQueueStats> =
        stats(environmentId, WorkStatsParams.none(), requestOptions)

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * Stop a work item, initiating graceful or forced shutdown.
     */
    fun stop(workId: String, params: WorkStopParams): CompletableFuture<BetaSelfHostedWork> =
        stop(workId, params, RequestOptions.none())

    /** @see stop */
    fun stop(
        workId: String,
        params: WorkStopParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWork> =
        stop(params.toBuilder().workId(workId).build(), requestOptions)

    /** @see stop */
    fun stop(params: WorkStopParams): CompletableFuture<BetaSelfHostedWork> =
        stop(params, RequestOptions.none())

    /** @see stop */
    fun stop(
        params: WorkStopParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSelfHostedWork>

    /** A view of [WorkServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/environments/{environment_id}/work/{work_id}?beta=true`, but is otherwise the same as
         * [WorkServiceAsync.retrieve].
         */
        fun retrieve(
            workId: String,
            params: WorkRetrieveParams,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            retrieve(workId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            workId: String,
            params: WorkRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            retrieve(params.toBuilder().workId(workId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            params: WorkRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: WorkRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>>

        /**
         * Returns a raw HTTP response for `post
         * /v1/environments/{environment_id}/work/{work_id}?beta=true`, but is otherwise the same as
         * [WorkServiceAsync.update].
         */
        fun update(
            workId: String,
            params: WorkUpdateParams,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            update(workId, params, RequestOptions.none())

        /** @see update */
        fun update(
            workId: String,
            params: WorkUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            update(params.toBuilder().workId(workId).build(), requestOptions)

        /** @see update */
        fun update(
            params: WorkUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: WorkUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>>

        /**
         * Returns a raw HTTP response for `get /v1/environments/{environment_id}/work?beta=true`,
         * but is otherwise the same as [WorkServiceAsync.list].
         */
        fun list(environmentId: String): CompletableFuture<HttpResponseFor<WorkListPageAsync>> =
            list(environmentId, WorkListParams.none())

        /** @see list */
        fun list(
            environmentId: String,
            params: WorkListParams = WorkListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkListPageAsync>> =
            list(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see list */
        fun list(
            environmentId: String,
            params: WorkListParams = WorkListParams.none(),
        ): CompletableFuture<HttpResponseFor<WorkListPageAsync>> =
            list(environmentId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: WorkListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkListPageAsync>>

        /** @see list */
        fun list(params: WorkListParams): CompletableFuture<HttpResponseFor<WorkListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            environmentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WorkListPageAsync>> =
            list(environmentId, WorkListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/environments/{environment_id}/work/{work_id}/ack?beta=true`, but is otherwise the
         * same as [WorkServiceAsync.ack].
         */
        fun ack(
            workId: String,
            params: WorkAckParams,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            ack(workId, params, RequestOptions.none())

        /** @see ack */
        fun ack(
            workId: String,
            params: WorkAckParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            ack(params.toBuilder().workId(workId).build(), requestOptions)

        /** @see ack */
        fun ack(params: WorkAckParams): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            ack(params, RequestOptions.none())

        /** @see ack */
        fun ack(
            params: WorkAckParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>>

        /**
         * Returns a raw HTTP response for `post
         * /v1/environments/{environment_id}/work/{work_id}/heartbeat?beta=true`, but is otherwise
         * the same as [WorkServiceAsync.heartbeat].
         */
        fun heartbeat(
            workId: String,
            params: WorkHeartbeatParams,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkHeartbeatResponse>> =
            heartbeat(workId, params, RequestOptions.none())

        /** @see heartbeat */
        fun heartbeat(
            workId: String,
            params: WorkHeartbeatParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkHeartbeatResponse>> =
            heartbeat(params.toBuilder().workId(workId).build(), requestOptions)

        /** @see heartbeat */
        fun heartbeat(
            params: WorkHeartbeatParams
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkHeartbeatResponse>> =
            heartbeat(params, RequestOptions.none())

        /** @see heartbeat */
        fun heartbeat(
            params: WorkHeartbeatParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkHeartbeatResponse>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/environments/{environment_id}/work/poll?beta=true`, but is otherwise the same as
         * [WorkServiceAsync.poll].
         */
        fun poll(
            environmentId: String
        ): CompletableFuture<HttpResponseFor<Optional<BetaSelfHostedWork>>> =
            poll(environmentId, WorkPollParams.none())

        /** @see poll */
        fun poll(
            environmentId: String,
            params: WorkPollParams = WorkPollParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Optional<BetaSelfHostedWork>>> =
            poll(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see poll */
        fun poll(
            environmentId: String,
            params: WorkPollParams = WorkPollParams.none(),
        ): CompletableFuture<HttpResponseFor<Optional<BetaSelfHostedWork>>> =
            poll(environmentId, params, RequestOptions.none())

        /** @see poll */
        fun poll(
            params: WorkPollParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Optional<BetaSelfHostedWork>>>

        /** @see poll */
        fun poll(
            params: WorkPollParams
        ): CompletableFuture<HttpResponseFor<Optional<BetaSelfHostedWork>>> =
            poll(params, RequestOptions.none())

        /** @see poll */
        fun poll(
            environmentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Optional<BetaSelfHostedWork>>> =
            poll(environmentId, WorkPollParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get
         * /v1/environments/{environment_id}/work/stats?beta=true`, but is otherwise the same as
         * [WorkServiceAsync.stats].
         */
        fun stats(
            environmentId: String
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkQueueStats>> =
            stats(environmentId, WorkStatsParams.none())

        /** @see stats */
        fun stats(
            environmentId: String,
            params: WorkStatsParams = WorkStatsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkQueueStats>> =
            stats(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see stats */
        fun stats(
            environmentId: String,
            params: WorkStatsParams = WorkStatsParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkQueueStats>> =
            stats(environmentId, params, RequestOptions.none())

        /** @see stats */
        fun stats(
            params: WorkStatsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkQueueStats>>

        /** @see stats */
        fun stats(
            params: WorkStatsParams
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkQueueStats>> =
            stats(params, RequestOptions.none())

        /** @see stats */
        fun stats(
            environmentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkQueueStats>> =
            stats(environmentId, WorkStatsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/environments/{environment_id}/work/{work_id}/stop?beta=true`, but is otherwise the
         * same as [WorkServiceAsync.stop].
         */
        fun stop(
            workId: String,
            params: WorkStopParams,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            stop(workId, params, RequestOptions.none())

        /** @see stop */
        fun stop(
            workId: String,
            params: WorkStopParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            stop(params.toBuilder().workId(workId).build(), requestOptions)

        /** @see stop */
        fun stop(params: WorkStopParams): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> =
            stop(params, RequestOptions.none())

        /** @see stop */
        fun stop(
            params: WorkStopParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>>
    }
}
