package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.telemetrydestinations.BetaDeletedTelemetryDestination
import com.anthropic.models.beta.organization.telemetrydestinations.BetaTelemetryDestination
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationCreateParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationDeleteParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationListPageAsync
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationListParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationRetrieveParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationUpdateParams
import com.anthropic.services.async.beta.organization.telemetrydestinations.WorkspaceServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface TelemetryDestinationServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): TelemetryDestinationServiceAsync

    fun workspaces(): WorkspaceServiceAsync

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
    fun create(
        params: TelemetryDestinationCreateParams
    ): CompletableFuture<BetaTelemetryDestination> = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: TelemetryDestinationCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTelemetryDestination>

    /**
     * Read a destination's configuration, state, and most recent delivery results.
     *
     * See the
     * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#delivery-and-health)
     * to learn how the API delivers spans and when it disables a destination.
     */
    fun retrieve(destinationId: String): CompletableFuture<BetaTelemetryDestination> =
        retrieve(destinationId, TelemetryDestinationRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        destinationId: String,
        params: TelemetryDestinationRetrieveParams = TelemetryDestinationRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTelemetryDestination> =
        retrieve(params.toBuilder().destinationId(destinationId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        destinationId: String,
        params: TelemetryDestinationRetrieveParams = TelemetryDestinationRetrieveParams.none(),
    ): CompletableFuture<BetaTelemetryDestination> =
        retrieve(destinationId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: TelemetryDestinationRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTelemetryDestination>

    /** @see retrieve */
    fun retrieve(
        params: TelemetryDestinationRetrieveParams
    ): CompletableFuture<BetaTelemetryDestination> = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        destinationId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaTelemetryDestination> =
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
    fun update(destinationId: String): CompletableFuture<BetaTelemetryDestination> =
        update(destinationId, TelemetryDestinationUpdateParams.none())

    /** @see update */
    fun update(
        destinationId: String,
        params: TelemetryDestinationUpdateParams = TelemetryDestinationUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTelemetryDestination> =
        update(params.toBuilder().destinationId(destinationId).build(), requestOptions)

    /** @see update */
    fun update(
        destinationId: String,
        params: TelemetryDestinationUpdateParams = TelemetryDestinationUpdateParams.none(),
    ): CompletableFuture<BetaTelemetryDestination> =
        update(destinationId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: TelemetryDestinationUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTelemetryDestination>

    /** @see update */
    fun update(
        params: TelemetryDestinationUpdateParams
    ): CompletableFuture<BetaTelemetryDestination> = update(params, RequestOptions.none())

    /** @see update */
    fun update(
        destinationId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaTelemetryDestination> =
        update(destinationId, TelemetryDestinationUpdateParams.none(), requestOptions)

    /**
     * List the organization's destinations, newest first, optionally only those that cover given
     * workspaces.
     *
     * See the
     * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#set-up-api-tracing)
     * to learn more about destinations.
     */
    fun list(): CompletableFuture<TelemetryDestinationListPageAsync> =
        list(TelemetryDestinationListParams.none())

    /** @see list */
    fun list(
        params: TelemetryDestinationListParams = TelemetryDestinationListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<TelemetryDestinationListPageAsync>

    /** @see list */
    fun list(
        params: TelemetryDestinationListParams = TelemetryDestinationListParams.none()
    ): CompletableFuture<TelemetryDestinationListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<TelemetryDestinationListPageAsync> =
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
    fun delete(destinationId: String): CompletableFuture<BetaDeletedTelemetryDestination> =
        delete(destinationId, TelemetryDestinationDeleteParams.none())

    /** @see delete */
    fun delete(
        destinationId: String,
        params: TelemetryDestinationDeleteParams = TelemetryDestinationDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedTelemetryDestination> =
        delete(params.toBuilder().destinationId(destinationId).build(), requestOptions)

    /** @see delete */
    fun delete(
        destinationId: String,
        params: TelemetryDestinationDeleteParams = TelemetryDestinationDeleteParams.none(),
    ): CompletableFuture<BetaDeletedTelemetryDestination> =
        delete(destinationId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: TelemetryDestinationDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedTelemetryDestination>

    /** @see delete */
    fun delete(
        params: TelemetryDestinationDeleteParams
    ): CompletableFuture<BetaDeletedTelemetryDestination> = delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        destinationId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaDeletedTelemetryDestination> =
        delete(destinationId, TelemetryDestinationDeleteParams.none(), requestOptions)

    /**
     * A view of [TelemetryDestinationServiceAsync] that provides access to raw HTTP responses for
     * each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): TelemetryDestinationServiceAsync.WithRawResponse

        fun workspaces(): WorkspaceServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/telemetry_destinations?beta=true`, but is otherwise the same as
         * [TelemetryDestinationServiceAsync.create].
         */
        fun create(
            params: TelemetryDestinationCreateParams
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: TelemetryDestinationCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/telemetry_destinations/{destination_id}?beta=true`, but is otherwise
         * the same as [TelemetryDestinationServiceAsync.retrieve].
         */
        fun retrieve(
            destinationId: String
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>> =
            retrieve(destinationId, TelemetryDestinationRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            destinationId: String,
            params: TelemetryDestinationRetrieveParams = TelemetryDestinationRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>> =
            retrieve(params.toBuilder().destinationId(destinationId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            destinationId: String,
            params: TelemetryDestinationRetrieveParams = TelemetryDestinationRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>> =
            retrieve(destinationId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: TelemetryDestinationRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>>

        /** @see retrieve */
        fun retrieve(
            params: TelemetryDestinationRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            destinationId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>> =
            retrieve(destinationId, TelemetryDestinationRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/telemetry_destinations/{destination_id}?beta=true`, but is otherwise
         * the same as [TelemetryDestinationServiceAsync.update].
         */
        fun update(
            destinationId: String
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>> =
            update(destinationId, TelemetryDestinationUpdateParams.none())

        /** @see update */
        fun update(
            destinationId: String,
            params: TelemetryDestinationUpdateParams = TelemetryDestinationUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>> =
            update(params.toBuilder().destinationId(destinationId).build(), requestOptions)

        /** @see update */
        fun update(
            destinationId: String,
            params: TelemetryDestinationUpdateParams = TelemetryDestinationUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>> =
            update(destinationId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: TelemetryDestinationUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>>

        /** @see update */
        fun update(
            params: TelemetryDestinationUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            destinationId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestination>> =
            update(destinationId, TelemetryDestinationUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/telemetry_destinations?beta=true`,
         * but is otherwise the same as [TelemetryDestinationServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<TelemetryDestinationListPageAsync>> =
            list(TelemetryDestinationListParams.none())

        /** @see list */
        fun list(
            params: TelemetryDestinationListParams = TelemetryDestinationListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<TelemetryDestinationListPageAsync>>

        /** @see list */
        fun list(
            params: TelemetryDestinationListParams = TelemetryDestinationListParams.none()
        ): CompletableFuture<HttpResponseFor<TelemetryDestinationListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<TelemetryDestinationListPageAsync>> =
            list(TelemetryDestinationListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/telemetry_destinations/{destination_id}?beta=true`, but is otherwise
         * the same as [TelemetryDestinationServiceAsync.delete].
         */
        fun delete(
            destinationId: String
        ): CompletableFuture<HttpResponseFor<BetaDeletedTelemetryDestination>> =
            delete(destinationId, TelemetryDestinationDeleteParams.none())

        /** @see delete */
        fun delete(
            destinationId: String,
            params: TelemetryDestinationDeleteParams = TelemetryDestinationDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedTelemetryDestination>> =
            delete(params.toBuilder().destinationId(destinationId).build(), requestOptions)

        /** @see delete */
        fun delete(
            destinationId: String,
            params: TelemetryDestinationDeleteParams = TelemetryDestinationDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedTelemetryDestination>> =
            delete(destinationId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: TelemetryDestinationDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedTelemetryDestination>>

        /** @see delete */
        fun delete(
            params: TelemetryDestinationDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaDeletedTelemetryDestination>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            destinationId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDeletedTelemetryDestination>> =
            delete(destinationId, TelemetryDestinationDeleteParams.none(), requestOptions)
    }
}
