// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.environments

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.environments.work.BetaSelfHostedWork
import com.anthropic.models.beta.environments.work.BetaSelfHostedWorkHeartbeatResponse
import com.anthropic.models.beta.environments.work.BetaSelfHostedWorkQueueStats
import com.anthropic.models.beta.environments.work.WorkAckParams
import com.anthropic.models.beta.environments.work.WorkHeartbeatParams
import com.anthropic.models.beta.environments.work.WorkListPage
import com.anthropic.models.beta.environments.work.WorkListParams
import com.anthropic.models.beta.environments.work.WorkPollParams
import com.anthropic.models.beta.environments.work.WorkRetrieveParams
import com.anthropic.models.beta.environments.work.WorkStatsParams
import com.anthropic.models.beta.environments.work.WorkStopParams
import com.anthropic.models.beta.environments.work.WorkUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.Optional
import java.util.function.Consumer

interface WorkService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkService

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * Retrieve detailed information about a specific work item.
     */
    fun retrieve(workId: String, params: WorkRetrieveParams): BetaSelfHostedWork =
        retrieve(workId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        workId: String,
        params: WorkRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWork = retrieve(params.toBuilder().workId(workId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: WorkRetrieveParams): BetaSelfHostedWork =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: WorkRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWork

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * Update work item metadata with merge semantics.
     */
    fun update(workId: String, params: WorkUpdateParams): BetaSelfHostedWork =
        update(workId, params, RequestOptions.none())

    /** @see update */
    fun update(
        workId: String,
        params: WorkUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWork = update(params.toBuilder().workId(workId).build(), requestOptions)

    /** @see update */
    fun update(params: WorkUpdateParams): BetaSelfHostedWork = update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: WorkUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWork

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * List work items in an environment.
     */
    fun list(environmentId: String): WorkListPage = list(environmentId, WorkListParams.none())

    /** @see list */
    fun list(
        environmentId: String,
        params: WorkListParams = WorkListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkListPage = list(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see list */
    fun list(environmentId: String, params: WorkListParams = WorkListParams.none()): WorkListPage =
        list(environmentId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: WorkListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkListPage

    /** @see list */
    fun list(params: WorkListParams): WorkListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(environmentId: String, requestOptions: RequestOptions): WorkListPage =
        list(environmentId, WorkListParams.none(), requestOptions)

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * Acknowledge receipt of a work item, transitioning it from 'queued' to 'starting' and removing
     * it from the queue.
     */
    fun ack(workId: String, params: WorkAckParams): BetaSelfHostedWork =
        ack(workId, params, RequestOptions.none())

    /** @see ack */
    fun ack(
        workId: String,
        params: WorkAckParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWork = ack(params.toBuilder().workId(workId).build(), requestOptions)

    /** @see ack */
    fun ack(params: WorkAckParams): BetaSelfHostedWork = ack(params, RequestOptions.none())

    /** @see ack */
    fun ack(
        params: WorkAckParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWork

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
    ): BetaSelfHostedWorkHeartbeatResponse = heartbeat(workId, params, RequestOptions.none())

    /** @see heartbeat */
    fun heartbeat(
        workId: String,
        params: WorkHeartbeatParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWorkHeartbeatResponse =
        heartbeat(params.toBuilder().workId(workId).build(), requestOptions)

    /** @see heartbeat */
    fun heartbeat(params: WorkHeartbeatParams): BetaSelfHostedWorkHeartbeatResponse =
        heartbeat(params, RequestOptions.none())

    /** @see heartbeat */
    fun heartbeat(
        params: WorkHeartbeatParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWorkHeartbeatResponse

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * Long poll for work items in the queue.
     */
    fun poll(environmentId: String): Optional<BetaSelfHostedWork> =
        poll(environmentId, WorkPollParams.none())

    /** @see poll */
    fun poll(
        environmentId: String,
        params: WorkPollParams = WorkPollParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Optional<BetaSelfHostedWork> =
        poll(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see poll */
    fun poll(
        environmentId: String,
        params: WorkPollParams = WorkPollParams.none(),
    ): Optional<BetaSelfHostedWork> = poll(environmentId, params, RequestOptions.none())

    /** @see poll */
    fun poll(
        params: WorkPollParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Optional<BetaSelfHostedWork>

    /** @see poll */
    fun poll(params: WorkPollParams): Optional<BetaSelfHostedWork> =
        poll(params, RequestOptions.none())

    /** @see poll */
    fun poll(environmentId: String, requestOptions: RequestOptions): Optional<BetaSelfHostedWork> =
        poll(environmentId, WorkPollParams.none(), requestOptions)

    /** Get statistics about the work queue for an environment. */
    fun stats(environmentId: String): BetaSelfHostedWorkQueueStats =
        stats(environmentId, WorkStatsParams.none())

    /** @see stats */
    fun stats(
        environmentId: String,
        params: WorkStatsParams = WorkStatsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWorkQueueStats =
        stats(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see stats */
    fun stats(
        environmentId: String,
        params: WorkStatsParams = WorkStatsParams.none(),
    ): BetaSelfHostedWorkQueueStats = stats(environmentId, params, RequestOptions.none())

    /** @see stats */
    fun stats(
        params: WorkStatsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWorkQueueStats

    /** @see stats */
    fun stats(params: WorkStatsParams): BetaSelfHostedWorkQueueStats =
        stats(params, RequestOptions.none())

    /** @see stats */
    fun stats(environmentId: String, requestOptions: RequestOptions): BetaSelfHostedWorkQueueStats =
        stats(environmentId, WorkStatsParams.none(), requestOptions)

    /**
     * Note: these endpoints are called automatically by the pre-built environment worker provided
     * in the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They
     * are included here as a reference; you do not need to invoke them directly.
     *
     * Stop a work item, initiating graceful or forced shutdown.
     */
    fun stop(workId: String, params: WorkStopParams): BetaSelfHostedWork =
        stop(workId, params, RequestOptions.none())

    /** @see stop */
    fun stop(
        workId: String,
        params: WorkStopParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWork = stop(params.toBuilder().workId(workId).build(), requestOptions)

    /** @see stop */
    fun stop(params: WorkStopParams): BetaSelfHostedWork = stop(params, RequestOptions.none())

    /** @see stop */
    fun stop(
        params: WorkStopParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSelfHostedWork

    /** A view of [WorkService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/environments/{environment_id}/work/{work_id}?beta=true`, but is otherwise the same as
         * [WorkService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            workId: String,
            params: WorkRetrieveParams,
        ): HttpResponseFor<BetaSelfHostedWork> = retrieve(workId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            workId: String,
            params: WorkRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWork> =
            retrieve(params.toBuilder().workId(workId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: WorkRetrieveParams): HttpResponseFor<BetaSelfHostedWork> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: WorkRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWork>

        /**
         * Returns a raw HTTP response for `post
         * /v1/environments/{environment_id}/work/{work_id}?beta=true`, but is otherwise the same as
         * [WorkService.update].
         */
        @MustBeClosed
        fun update(workId: String, params: WorkUpdateParams): HttpResponseFor<BetaSelfHostedWork> =
            update(workId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            workId: String,
            params: WorkUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWork> =
            update(params.toBuilder().workId(workId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(params: WorkUpdateParams): HttpResponseFor<BetaSelfHostedWork> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: WorkUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWork>

        /**
         * Returns a raw HTTP response for `get /v1/environments/{environment_id}/work?beta=true`,
         * but is otherwise the same as [WorkService.list].
         */
        @MustBeClosed
        fun list(environmentId: String): HttpResponseFor<WorkListPage> =
            list(environmentId, WorkListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            environmentId: String,
            params: WorkListParams = WorkListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkListPage> =
            list(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            environmentId: String,
            params: WorkListParams = WorkListParams.none(),
        ): HttpResponseFor<WorkListPage> = list(environmentId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: WorkListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: WorkListParams): HttpResponseFor<WorkListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            environmentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WorkListPage> =
            list(environmentId, WorkListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/environments/{environment_id}/work/{work_id}/ack?beta=true`, but is otherwise the
         * same as [WorkService.ack].
         */
        @MustBeClosed
        fun ack(workId: String, params: WorkAckParams): HttpResponseFor<BetaSelfHostedWork> =
            ack(workId, params, RequestOptions.none())

        /** @see ack */
        @MustBeClosed
        fun ack(
            workId: String,
            params: WorkAckParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWork> =
            ack(params.toBuilder().workId(workId).build(), requestOptions)

        /** @see ack */
        @MustBeClosed
        fun ack(params: WorkAckParams): HttpResponseFor<BetaSelfHostedWork> =
            ack(params, RequestOptions.none())

        /** @see ack */
        @MustBeClosed
        fun ack(
            params: WorkAckParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWork>

        /**
         * Returns a raw HTTP response for `post
         * /v1/environments/{environment_id}/work/{work_id}/heartbeat?beta=true`, but is otherwise
         * the same as [WorkService.heartbeat].
         */
        @MustBeClosed
        fun heartbeat(
            workId: String,
            params: WorkHeartbeatParams,
        ): HttpResponseFor<BetaSelfHostedWorkHeartbeatResponse> =
            heartbeat(workId, params, RequestOptions.none())

        /** @see heartbeat */
        @MustBeClosed
        fun heartbeat(
            workId: String,
            params: WorkHeartbeatParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWorkHeartbeatResponse> =
            heartbeat(params.toBuilder().workId(workId).build(), requestOptions)

        /** @see heartbeat */
        @MustBeClosed
        fun heartbeat(
            params: WorkHeartbeatParams
        ): HttpResponseFor<BetaSelfHostedWorkHeartbeatResponse> =
            heartbeat(params, RequestOptions.none())

        /** @see heartbeat */
        @MustBeClosed
        fun heartbeat(
            params: WorkHeartbeatParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWorkHeartbeatResponse>

        /**
         * Returns a raw HTTP response for `get
         * /v1/environments/{environment_id}/work/poll?beta=true`, but is otherwise the same as
         * [WorkService.poll].
         */
        @MustBeClosed
        fun poll(environmentId: String): HttpResponseFor<Optional<BetaSelfHostedWork>> =
            poll(environmentId, WorkPollParams.none())

        /** @see poll */
        @MustBeClosed
        fun poll(
            environmentId: String,
            params: WorkPollParams = WorkPollParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Optional<BetaSelfHostedWork>> =
            poll(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see poll */
        @MustBeClosed
        fun poll(
            environmentId: String,
            params: WorkPollParams = WorkPollParams.none(),
        ): HttpResponseFor<Optional<BetaSelfHostedWork>> =
            poll(environmentId, params, RequestOptions.none())

        /** @see poll */
        @MustBeClosed
        fun poll(
            params: WorkPollParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Optional<BetaSelfHostedWork>>

        /** @see poll */
        @MustBeClosed
        fun poll(params: WorkPollParams): HttpResponseFor<Optional<BetaSelfHostedWork>> =
            poll(params, RequestOptions.none())

        /** @see poll */
        @MustBeClosed
        fun poll(
            environmentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Optional<BetaSelfHostedWork>> =
            poll(environmentId, WorkPollParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get
         * /v1/environments/{environment_id}/work/stats?beta=true`, but is otherwise the same as
         * [WorkService.stats].
         */
        @MustBeClosed
        fun stats(environmentId: String): HttpResponseFor<BetaSelfHostedWorkQueueStats> =
            stats(environmentId, WorkStatsParams.none())

        /** @see stats */
        @MustBeClosed
        fun stats(
            environmentId: String,
            params: WorkStatsParams = WorkStatsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWorkQueueStats> =
            stats(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see stats */
        @MustBeClosed
        fun stats(
            environmentId: String,
            params: WorkStatsParams = WorkStatsParams.none(),
        ): HttpResponseFor<BetaSelfHostedWorkQueueStats> =
            stats(environmentId, params, RequestOptions.none())

        /** @see stats */
        @MustBeClosed
        fun stats(
            params: WorkStatsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWorkQueueStats>

        /** @see stats */
        @MustBeClosed
        fun stats(params: WorkStatsParams): HttpResponseFor<BetaSelfHostedWorkQueueStats> =
            stats(params, RequestOptions.none())

        /** @see stats */
        @MustBeClosed
        fun stats(
            environmentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaSelfHostedWorkQueueStats> =
            stats(environmentId, WorkStatsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/environments/{environment_id}/work/{work_id}/stop?beta=true`, but is otherwise the
         * same as [WorkService.stop].
         */
        @MustBeClosed
        fun stop(workId: String, params: WorkStopParams): HttpResponseFor<BetaSelfHostedWork> =
            stop(workId, params, RequestOptions.none())

        /** @see stop */
        @MustBeClosed
        fun stop(
            workId: String,
            params: WorkStopParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWork> =
            stop(params.toBuilder().workId(workId).build(), requestOptions)

        /** @see stop */
        @MustBeClosed
        fun stop(params: WorkStopParams): HttpResponseFor<BetaSelfHostedWork> =
            stop(params, RequestOptions.none())

        /** @see stop */
        @MustBeClosed
        fun stop(
            params: WorkStopParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSelfHostedWork>
    }
}
