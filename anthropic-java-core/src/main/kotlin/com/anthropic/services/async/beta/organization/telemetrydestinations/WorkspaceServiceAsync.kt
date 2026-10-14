package com.anthropic.services.async.beta.organization.telemetrydestinations

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.BetaDeletedTelemetryDestinationWorkspace
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.BetaTelemetryDestinationWorkspace
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceListPageAsync
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceRemoveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface WorkspaceServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceServiceAsync

    /**
     * List the workspaces on a destination's workspace list, newest entry first.
     *
     * A destination whose `workspaces.type` is `all` doesn't have a list, so its page is empty. The
     * list includes archived workspaces.
     *
     * See the
     * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#choose-workspaces-for-a-destination)
     * to learn more about choosing the workspaces of a destination.
     */
    fun list(destinationId: String): CompletableFuture<WorkspaceListPageAsync> =
        list(destinationId, WorkspaceListParams.none())

    /** @see list */
    fun list(
        destinationId: String,
        params: WorkspaceListParams = WorkspaceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceListPageAsync> =
        list(params.toBuilder().destinationId(destinationId).build(), requestOptions)

    /** @see list */
    fun list(
        destinationId: String,
        params: WorkspaceListParams = WorkspaceListParams.none(),
    ): CompletableFuture<WorkspaceListPageAsync> =
        list(destinationId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: WorkspaceListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceListPageAsync>

    /** @see list */
    fun list(params: WorkspaceListParams): CompletableFuture<WorkspaceListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        destinationId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<WorkspaceListPageAsync> =
        list(destinationId, WorkspaceListParams.none(), requestOptions)

    /**
     * Add one workspace to a destination's workspace list.
     *
     * Under `include` the workspace's spans start going to the destination. Under `exclude` they
     * stop. A list holds at most 1,000 workspaces.
     *
     * Adding a workspace that is already on the list changes nothing and returns the existing
     * entry. An archived workspace that isn't on the list yet can't be added. A destination whose
     * `workspaces.type` is `all` doesn't have a list, so the API returns 400. To change which
     * workspaces such a destination covers, update its `workspaces`.
     *
     * See the
     * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#choose-workspaces-for-a-destination)
     * to learn more about choosing the workspaces of a destination.
     */
    fun add(
        workspaceId: String,
        params: WorkspaceAddParams,
    ): CompletableFuture<BetaTelemetryDestinationWorkspace> =
        add(workspaceId, params, RequestOptions.none())

    /** @see add */
    fun add(
        workspaceId: String,
        params: WorkspaceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTelemetryDestinationWorkspace> =
        add(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see add */
    fun add(params: WorkspaceAddParams): CompletableFuture<BetaTelemetryDestinationWorkspace> =
        add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: WorkspaceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTelemetryDestinationWorkspace>

    /**
     * Remove one workspace from a destination's workspace list.
     *
     * Under `include` the workspace's spans stop going to the destination. Under `exclude` they
     * start. An archived workspace can be removed too.
     *
     * The API returns 404 when the workspace isn't on the list. A destination whose
     * `workspaces.type` is `all` doesn't have a list, so the API returns 400.
     *
     * See the
     * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#choose-workspaces-for-a-destination)
     * to learn more about choosing the workspaces of a destination.
     */
    fun remove(
        workspaceId: String,
        params: WorkspaceRemoveParams,
    ): CompletableFuture<BetaDeletedTelemetryDestinationWorkspace> =
        remove(workspaceId, params, RequestOptions.none())

    /** @see remove */
    fun remove(
        workspaceId: String,
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedTelemetryDestinationWorkspace> =
        remove(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see remove */
    fun remove(
        params: WorkspaceRemoveParams
    ): CompletableFuture<BetaDeletedTelemetryDestinationWorkspace> =
        remove(params, RequestOptions.none())

    /** @see remove */
    fun remove(
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedTelemetryDestinationWorkspace>

    /**
     * A view of [WorkspaceServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WorkspaceServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/telemetry_destinations/{destination_id}/workspaces?beta=true`, but is
         * otherwise the same as [WorkspaceServiceAsync.list].
         */
        fun list(
            destinationId: String
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(destinationId, WorkspaceListParams.none())

        /** @see list */
        fun list(
            destinationId: String,
            params: WorkspaceListParams = WorkspaceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(params.toBuilder().destinationId(destinationId).build(), requestOptions)

        /** @see list */
        fun list(
            destinationId: String,
            params: WorkspaceListParams = WorkspaceListParams.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(destinationId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: WorkspaceListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>>

        /** @see list */
        fun list(
            params: WorkspaceListParams
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            destinationId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(destinationId, WorkspaceListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/telemetry_destinations/{destination_id}/workspaces/{workspace_id}?beta=true`,
         * but is otherwise the same as [WorkspaceServiceAsync.add].
         */
        fun add(
            workspaceId: String,
            params: WorkspaceAddParams,
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestinationWorkspace>> =
            add(workspaceId, params, RequestOptions.none())

        /** @see add */
        fun add(
            workspaceId: String,
            params: WorkspaceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestinationWorkspace>> =
            add(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see add */
        fun add(
            params: WorkspaceAddParams
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestinationWorkspace>> =
            add(params, RequestOptions.none())

        /** @see add */
        fun add(
            params: WorkspaceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestinationWorkspace>>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/telemetry_destinations/{destination_id}/workspaces/{workspace_id}?beta=true`,
         * but is otherwise the same as [WorkspaceServiceAsync.remove].
         */
        fun remove(
            workspaceId: String,
            params: WorkspaceRemoveParams,
        ): CompletableFuture<HttpResponseFor<BetaDeletedTelemetryDestinationWorkspace>> =
            remove(workspaceId, params, RequestOptions.none())

        /** @see remove */
        fun remove(
            workspaceId: String,
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedTelemetryDestinationWorkspace>> =
            remove(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see remove */
        fun remove(
            params: WorkspaceRemoveParams
        ): CompletableFuture<HttpResponseFor<BetaDeletedTelemetryDestinationWorkspace>> =
            remove(params, RequestOptions.none())

        /** @see remove */
        fun remove(
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedTelemetryDestinationWorkspace>>
    }
}
