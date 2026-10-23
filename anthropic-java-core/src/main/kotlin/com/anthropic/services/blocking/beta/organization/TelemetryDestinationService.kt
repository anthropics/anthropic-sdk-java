package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.telemetrydestinations.BetaDeletedTelemetryDestination
import com.anthropic.models.beta.organization.telemetrydestinations.BetaTelemetryDestination
import com.anthropic.models.beta.organization.telemetrydestinations.BetaTelemetryDestinationTestResponse
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationCreateParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationDeleteParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationListPage
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationListParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationRetrieveParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationTestParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationUpdateParams
import com.anthropic.services.blocking.beta.organization.telemetrydestinations.WorkspaceService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface TelemetryDestinationService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): TelemetryDestinationService

    fun workspaces(): WorkspaceService

    /**
     * Register an OpenTelemetry destination for the organization.
     *
     * The destination starts in state `enabled`. An organization can have at most 10 destinations,
     * including disabled ones.
     *
     * See the
     * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#add-a-destination)
     * to learn more about adding a destination.
     */
    fun create(params: TelemetryDestinationCreateParams): BetaTelemetryDestination =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: TelemetryDestinationCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTelemetryDestination

    /**
     * Read a destination's configuration, state, and most recent delivery results.
     *
     * See the
     * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#delivery-and-health)
     * to learn how the API delivers spans and when it disables a destination.
     */
    fun retrieve(destinationId: String): BetaTelemetryDestination =
        retrieve(destinationId, TelemetryDestinationRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        destinationId: String,
        params: TelemetryDestinationRetrieveParams = TelemetryDestinationRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTelemetryDestination =
        retrieve(params.toBuilder().destinationId(destinationId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        destinationId: String,
        params: TelemetryDestinationRetrieveParams = TelemetryDestinationRetrieveParams.none(),
    ): BetaTelemetryDestination = retrieve(destinationId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: TelemetryDestinationRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTelemetryDestination

    /** @see retrieve */
    fun retrieve(params: TelemetryDestinationRetrieveParams): BetaTelemetryDestination =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(destinationId: String, requestOptions: RequestOptions): BetaTelemetryDestination =
        retrieve(destinationId, TelemetryDestinationRetrieveParams.none(), requestOptions)

    /**
     * Change a telemetry destination's settings, its state, or which workspaces it covers.
     *
     * Send at least one field. Omitted fields keep their stored values, except the registered
     * headers, which a change of the endpoint's hostname or port removes. Only `display_name`,
     * `traces`, `traces.endpoint_url`, `traces.content_mode`, `traces.max_content_bytes`, and
     * individual `headers` values accept `null`.
     *
     * If another request changes the destination while the update runs, the API returns a 409
     * `conflict_error`. Read the destination again, then retry the update.
     *
     * See the
     * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#delivery-and-health)
     * to learn more about changing a destination.
     */
    fun update(destinationId: String): BetaTelemetryDestination =
        update(destinationId, TelemetryDestinationUpdateParams.none())

    /** @see update */
    fun update(
        destinationId: String,
        params: TelemetryDestinationUpdateParams = TelemetryDestinationUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTelemetryDestination =
        update(params.toBuilder().destinationId(destinationId).build(), requestOptions)

    /** @see update */
    fun update(
        destinationId: String,
        params: TelemetryDestinationUpdateParams = TelemetryDestinationUpdateParams.none(),
    ): BetaTelemetryDestination = update(destinationId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: TelemetryDestinationUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTelemetryDestination

    /** @see update */
    fun update(params: TelemetryDestinationUpdateParams): BetaTelemetryDestination =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(destinationId: String, requestOptions: RequestOptions): BetaTelemetryDestination =
        update(destinationId, TelemetryDestinationUpdateParams.none(), requestOptions)

    /**
     * List the organization's destinations, newest first, optionally only those that cover given
     * workspaces.
     *
     * See the
     * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#set-up-api-tracing)
     * to learn more about destinations.
     */
    fun list(): TelemetryDestinationListPage = list(TelemetryDestinationListParams.none())

    /** @see list */
    fun list(
        params: TelemetryDestinationListParams = TelemetryDestinationListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TelemetryDestinationListPage

    /** @see list */
    fun list(
        params: TelemetryDestinationListParams = TelemetryDestinationListParams.none()
    ): TelemetryDestinationListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): TelemetryDestinationListPage =
        list(TelemetryDestinationListParams.none(), requestOptions)

    /**
     * Delete a destination and its workspace list.
     *
     * The API stops sending spans to the destination. The spans of the workspaces it covered still
     * go to the other destinations that cover them. A deleted destination can't be read, listed, or
     * restored.
     *
     * See the
     * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#delivery-and-health)
     * to learn more about managing a destination.
     */
    fun delete(destinationId: String): BetaDeletedTelemetryDestination =
        delete(destinationId, TelemetryDestinationDeleteParams.none())

    /** @see delete */
    fun delete(
        destinationId: String,
        params: TelemetryDestinationDeleteParams = TelemetryDestinationDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedTelemetryDestination =
        delete(params.toBuilder().destinationId(destinationId).build(), requestOptions)

    /** @see delete */
    fun delete(
        destinationId: String,
        params: TelemetryDestinationDeleteParams = TelemetryDestinationDeleteParams.none(),
    ): BetaDeletedTelemetryDestination = delete(destinationId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: TelemetryDestinationDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedTelemetryDestination

    /** @see delete */
    fun delete(params: TelemetryDestinationDeleteParams): BetaDeletedTelemetryDestination =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        destinationId: String,
        requestOptions: RequestOptions,
    ): BetaDeletedTelemetryDestination =
        delete(destinationId, TelemetryDestinationDeleteParams.none(), requestOptions)

    /**
     * Send one test span, named `anthropic.test`, to the destination with its registered headers,
     * once and without retries, and report the destination's answer. The response is 200 whether or
     * not the destination answered with a 2xx status: its `result` has the type `success`, or the
     * type `failure` and the reason in `error`. The destination's configuration, `state` and
     * `delivery_health` do not change. A disabled destination can be tested too. Wait 10 seconds
     * between tests of one destination; a request that comes sooner may be answered with
     * status 429.
     */
    fun test(destinationId: String): BetaTelemetryDestinationTestResponse =
        test(destinationId, TelemetryDestinationTestParams.none())

    /** @see test */
    fun test(
        destinationId: String,
        params: TelemetryDestinationTestParams = TelemetryDestinationTestParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTelemetryDestinationTestResponse =
        test(params.toBuilder().destinationId(destinationId).build(), requestOptions)

    /** @see test */
    fun test(
        destinationId: String,
        params: TelemetryDestinationTestParams = TelemetryDestinationTestParams.none(),
    ): BetaTelemetryDestinationTestResponse = test(destinationId, params, RequestOptions.none())

    /** @see test */
    fun test(
        params: TelemetryDestinationTestParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTelemetryDestinationTestResponse

    /** @see test */
    fun test(params: TelemetryDestinationTestParams): BetaTelemetryDestinationTestResponse =
        test(params, RequestOptions.none())

    /** @see test */
    fun test(
        destinationId: String,
        requestOptions: RequestOptions,
    ): BetaTelemetryDestinationTestResponse =
        test(destinationId, TelemetryDestinationTestParams.none(), requestOptions)

    /**
     * A view of [TelemetryDestinationService] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): TelemetryDestinationService.WithRawResponse

        fun workspaces(): WorkspaceService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/telemetry_destinations`, but is
         * otherwise the same as [TelemetryDestinationService.create].
         */
        @MustBeClosed
        fun create(
            params: TelemetryDestinationCreateParams
        ): HttpResponseFor<BetaTelemetryDestination> = create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: TelemetryDestinationCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTelemetryDestination>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/telemetry_destinations/{destination_id}`, but is otherwise the same as
         * [TelemetryDestinationService.retrieve].
         */
        @MustBeClosed
        fun retrieve(destinationId: String): HttpResponseFor<BetaTelemetryDestination> =
            retrieve(destinationId, TelemetryDestinationRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            destinationId: String,
            params: TelemetryDestinationRetrieveParams = TelemetryDestinationRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTelemetryDestination> =
            retrieve(params.toBuilder().destinationId(destinationId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            destinationId: String,
            params: TelemetryDestinationRetrieveParams = TelemetryDestinationRetrieveParams.none(),
        ): HttpResponseFor<BetaTelemetryDestination> =
            retrieve(destinationId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: TelemetryDestinationRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTelemetryDestination>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: TelemetryDestinationRetrieveParams
        ): HttpResponseFor<BetaTelemetryDestination> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            destinationId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTelemetryDestination> =
            retrieve(destinationId, TelemetryDestinationRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/telemetry_destinations/{destination_id}`, but is otherwise the same as
         * [TelemetryDestinationService.update].
         */
        @MustBeClosed
        fun update(destinationId: String): HttpResponseFor<BetaTelemetryDestination> =
            update(destinationId, TelemetryDestinationUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            destinationId: String,
            params: TelemetryDestinationUpdateParams = TelemetryDestinationUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTelemetryDestination> =
            update(params.toBuilder().destinationId(destinationId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            destinationId: String,
            params: TelemetryDestinationUpdateParams = TelemetryDestinationUpdateParams.none(),
        ): HttpResponseFor<BetaTelemetryDestination> =
            update(destinationId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: TelemetryDestinationUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTelemetryDestination>

        /** @see update */
        @MustBeClosed
        fun update(
            params: TelemetryDestinationUpdateParams
        ): HttpResponseFor<BetaTelemetryDestination> = update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            destinationId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTelemetryDestination> =
            update(destinationId, TelemetryDestinationUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/telemetry_destinations`, but is
         * otherwise the same as [TelemetryDestinationService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<TelemetryDestinationListPage> =
            list(TelemetryDestinationListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: TelemetryDestinationListParams = TelemetryDestinationListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TelemetryDestinationListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: TelemetryDestinationListParams = TelemetryDestinationListParams.none()
        ): HttpResponseFor<TelemetryDestinationListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<TelemetryDestinationListPage> =
            list(TelemetryDestinationListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/telemetry_destinations/{destination_id}`, but is otherwise the same as
         * [TelemetryDestinationService.delete].
         */
        @MustBeClosed
        fun delete(destinationId: String): HttpResponseFor<BetaDeletedTelemetryDestination> =
            delete(destinationId, TelemetryDestinationDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            destinationId: String,
            params: TelemetryDestinationDeleteParams = TelemetryDestinationDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedTelemetryDestination> =
            delete(params.toBuilder().destinationId(destinationId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            destinationId: String,
            params: TelemetryDestinationDeleteParams = TelemetryDestinationDeleteParams.none(),
        ): HttpResponseFor<BetaDeletedTelemetryDestination> =
            delete(destinationId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: TelemetryDestinationDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedTelemetryDestination>

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: TelemetryDestinationDeleteParams
        ): HttpResponseFor<BetaDeletedTelemetryDestination> = delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            destinationId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedTelemetryDestination> =
            delete(destinationId, TelemetryDestinationDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/telemetry_destinations/{destination_id}/test`, but is otherwise the
         * same as [TelemetryDestinationService.test].
         */
        @MustBeClosed
        fun test(destinationId: String): HttpResponseFor<BetaTelemetryDestinationTestResponse> =
            test(destinationId, TelemetryDestinationTestParams.none())

        /** @see test */
        @MustBeClosed
        fun test(
            destinationId: String,
            params: TelemetryDestinationTestParams = TelemetryDestinationTestParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTelemetryDestinationTestResponse> =
            test(params.toBuilder().destinationId(destinationId).build(), requestOptions)

        /** @see test */
        @MustBeClosed
        fun test(
            destinationId: String,
            params: TelemetryDestinationTestParams = TelemetryDestinationTestParams.none(),
        ): HttpResponseFor<BetaTelemetryDestinationTestResponse> =
            test(destinationId, params, RequestOptions.none())

        /** @see test */
        @MustBeClosed
        fun test(
            params: TelemetryDestinationTestParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTelemetryDestinationTestResponse>

        /** @see test */
        @MustBeClosed
        fun test(
            params: TelemetryDestinationTestParams
        ): HttpResponseFor<BetaTelemetryDestinationTestResponse> =
            test(params, RequestOptions.none())

        /** @see test */
        @MustBeClosed
        fun test(
            destinationId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTelemetryDestinationTestResponse> =
            test(destinationId, TelemetryDestinationTestParams.none(), requestOptions)
    }
}
