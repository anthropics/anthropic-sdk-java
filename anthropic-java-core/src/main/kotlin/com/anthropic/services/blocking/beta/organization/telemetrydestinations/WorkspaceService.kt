package com.anthropic.services.blocking.beta.organization.telemetrydestinations

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.BetaDeletedTelemetryDestinationWorkspace
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.BetaTelemetryDestinationWorkspace
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceListPage
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceRemoveParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface WorkspaceService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceService

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
    fun list(destinationId: String): WorkspaceListPage =
        list(destinationId, WorkspaceListParams.none())

    /** @see list */
    fun list(
        destinationId: String,
        params: WorkspaceListParams = WorkspaceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceListPage =
        list(params.toBuilder().destinationId(destinationId).build(), requestOptions)

    /** @see list */
    fun list(
        destinationId: String,
        params: WorkspaceListParams = WorkspaceListParams.none(),
    ): WorkspaceListPage = list(destinationId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: WorkspaceListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceListPage

    /** @see list */
    fun list(params: WorkspaceListParams): WorkspaceListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(destinationId: String, requestOptions: RequestOptions): WorkspaceListPage =
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
    fun add(workspaceId: String, params: WorkspaceAddParams): BetaTelemetryDestinationWorkspace =
        add(workspaceId, params, RequestOptions.none())

    /** @see add */
    fun add(
        workspaceId: String,
        params: WorkspaceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTelemetryDestinationWorkspace =
        add(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see add */
    fun add(params: WorkspaceAddParams): BetaTelemetryDestinationWorkspace =
        add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: WorkspaceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTelemetryDestinationWorkspace

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
    ): BetaDeletedTelemetryDestinationWorkspace = remove(workspaceId, params, RequestOptions.none())

    /** @see remove */
    fun remove(
        workspaceId: String,
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedTelemetryDestinationWorkspace =
        remove(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see remove */
    fun remove(params: WorkspaceRemoveParams): BetaDeletedTelemetryDestinationWorkspace =
        remove(params, RequestOptions.none())

    /** @see remove */
    fun remove(
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedTelemetryDestinationWorkspace

    /** A view of [WorkspaceService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/telemetry_destinations/{destination_id}/workspaces?beta=true`, but is
         * otherwise the same as [WorkspaceService.list].
         */
        @MustBeClosed
        fun list(destinationId: String): HttpResponseFor<WorkspaceListPage> =
            list(destinationId, WorkspaceListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            destinationId: String,
            params: WorkspaceListParams = WorkspaceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceListPage> =
            list(params.toBuilder().destinationId(destinationId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            destinationId: String,
            params: WorkspaceListParams = WorkspaceListParams.none(),
        ): HttpResponseFor<WorkspaceListPage> = list(destinationId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: WorkspaceListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: WorkspaceListParams): HttpResponseFor<WorkspaceListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            destinationId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WorkspaceListPage> =
            list(destinationId, WorkspaceListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/telemetry_destinations/{destination_id}/workspaces/{workspace_id}?beta=true`,
         * but is otherwise the same as [WorkspaceService.add].
         */
        @MustBeClosed
        fun add(
            workspaceId: String,
            params: WorkspaceAddParams,
        ): HttpResponseFor<BetaTelemetryDestinationWorkspace> =
            add(workspaceId, params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            workspaceId: String,
            params: WorkspaceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTelemetryDestinationWorkspace> =
            add(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see add */
        @MustBeClosed
        fun add(params: WorkspaceAddParams): HttpResponseFor<BetaTelemetryDestinationWorkspace> =
            add(params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            params: WorkspaceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTelemetryDestinationWorkspace>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/telemetry_destinations/{destination_id}/workspaces/{workspace_id}?beta=true`,
         * but is otherwise the same as [WorkspaceService.remove].
         */
        @MustBeClosed
        fun remove(
            workspaceId: String,
            params: WorkspaceRemoveParams,
        ): HttpResponseFor<BetaDeletedTelemetryDestinationWorkspace> =
            remove(workspaceId, params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            workspaceId: String,
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedTelemetryDestinationWorkspace> =
            remove(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see remove */
        @MustBeClosed
        fun remove(
            params: WorkspaceRemoveParams
        ): HttpResponseFor<BetaDeletedTelemetryDestinationWorkspace> =
            remove(params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedTelemetryDestinationWorkspace>
    }
}
