// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.environments.BetaEnvironment
import com.anthropic.models.beta.environments.BetaEnvironmentDeleteResponse
import com.anthropic.models.beta.environments.EnvironmentArchiveParams
import com.anthropic.models.beta.environments.EnvironmentCreateParams
import com.anthropic.models.beta.environments.EnvironmentDeleteParams
import com.anthropic.models.beta.environments.EnvironmentListPageAsync
import com.anthropic.models.beta.environments.EnvironmentListParams
import com.anthropic.models.beta.environments.EnvironmentRetrieveParams
import com.anthropic.models.beta.environments.EnvironmentUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface EnvironmentServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): EnvironmentServiceAsync

    /** Create a new environment with the specified configuration. */
    fun create(params: EnvironmentCreateParams): CompletableFuture<BetaEnvironment> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: EnvironmentCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaEnvironment>

    /** Retrieve a specific environment by ID. */
    fun retrieve(environmentId: String): CompletableFuture<BetaEnvironment> =
        retrieve(environmentId, EnvironmentRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        environmentId: String,
        params: EnvironmentRetrieveParams = EnvironmentRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaEnvironment> =
        retrieve(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        environmentId: String,
        params: EnvironmentRetrieveParams = EnvironmentRetrieveParams.none(),
    ): CompletableFuture<BetaEnvironment> = retrieve(environmentId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: EnvironmentRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaEnvironment>

    /** @see retrieve */
    fun retrieve(params: EnvironmentRetrieveParams): CompletableFuture<BetaEnvironment> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        environmentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaEnvironment> =
        retrieve(environmentId, EnvironmentRetrieveParams.none(), requestOptions)

    /** Update an existing environment's configuration. */
    fun update(environmentId: String): CompletableFuture<BetaEnvironment> =
        update(environmentId, EnvironmentUpdateParams.none())

    /** @see update */
    fun update(
        environmentId: String,
        params: EnvironmentUpdateParams = EnvironmentUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaEnvironment> =
        update(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see update */
    fun update(
        environmentId: String,
        params: EnvironmentUpdateParams = EnvironmentUpdateParams.none(),
    ): CompletableFuture<BetaEnvironment> = update(environmentId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: EnvironmentUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaEnvironment>

    /** @see update */
    fun update(params: EnvironmentUpdateParams): CompletableFuture<BetaEnvironment> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        environmentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaEnvironment> =
        update(environmentId, EnvironmentUpdateParams.none(), requestOptions)

    /** List environments with pagination support. */
    fun list(): CompletableFuture<EnvironmentListPageAsync> = list(EnvironmentListParams.none())

    /** @see list */
    fun list(
        params: EnvironmentListParams = EnvironmentListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<EnvironmentListPageAsync>

    /** @see list */
    fun list(
        params: EnvironmentListParams = EnvironmentListParams.none()
    ): CompletableFuture<EnvironmentListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<EnvironmentListPageAsync> =
        list(EnvironmentListParams.none(), requestOptions)

    /** Delete an environment by ID. Returns a confirmation of the deletion. */
    fun delete(environmentId: String): CompletableFuture<BetaEnvironmentDeleteResponse> =
        delete(environmentId, EnvironmentDeleteParams.none())

    /** @see delete */
    fun delete(
        environmentId: String,
        params: EnvironmentDeleteParams = EnvironmentDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaEnvironmentDeleteResponse> =
        delete(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see delete */
    fun delete(
        environmentId: String,
        params: EnvironmentDeleteParams = EnvironmentDeleteParams.none(),
    ): CompletableFuture<BetaEnvironmentDeleteResponse> =
        delete(environmentId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: EnvironmentDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaEnvironmentDeleteResponse>

    /** @see delete */
    fun delete(params: EnvironmentDeleteParams): CompletableFuture<BetaEnvironmentDeleteResponse> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        environmentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaEnvironmentDeleteResponse> =
        delete(environmentId, EnvironmentDeleteParams.none(), requestOptions)

    /**
     * Archive an environment by ID. Archived environments cannot be used to create new sessions.
     */
    fun archive(environmentId: String): CompletableFuture<BetaEnvironment> =
        archive(environmentId, EnvironmentArchiveParams.none())

    /** @see archive */
    fun archive(
        environmentId: String,
        params: EnvironmentArchiveParams = EnvironmentArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaEnvironment> =
        archive(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see archive */
    fun archive(
        environmentId: String,
        params: EnvironmentArchiveParams = EnvironmentArchiveParams.none(),
    ): CompletableFuture<BetaEnvironment> = archive(environmentId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: EnvironmentArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaEnvironment>

    /** @see archive */
    fun archive(params: EnvironmentArchiveParams): CompletableFuture<BetaEnvironment> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        environmentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaEnvironment> =
        archive(environmentId, EnvironmentArchiveParams.none(), requestOptions)

    /**
     * A view of [EnvironmentServiceAsync] that provides access to raw HTTP responses for each
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
        ): EnvironmentServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/environments?beta=true`, but is otherwise the
         * same as [EnvironmentServiceAsync.create].
         */
        fun create(
            params: EnvironmentCreateParams
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: EnvironmentCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>>

        /**
         * Returns a raw HTTP response for `get /v1/environments/{environment_id}?beta=true`, but is
         * otherwise the same as [EnvironmentServiceAsync.retrieve].
         */
        fun retrieve(environmentId: String): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            retrieve(environmentId, EnvironmentRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            environmentId: String,
            params: EnvironmentRetrieveParams = EnvironmentRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            retrieve(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            environmentId: String,
            params: EnvironmentRetrieveParams = EnvironmentRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            retrieve(environmentId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: EnvironmentRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>>

        /** @see retrieve */
        fun retrieve(
            params: EnvironmentRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            environmentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            retrieve(environmentId, EnvironmentRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/environments/{environment_id}?beta=true`, but
         * is otherwise the same as [EnvironmentServiceAsync.update].
         */
        fun update(environmentId: String): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            update(environmentId, EnvironmentUpdateParams.none())

        /** @see update */
        fun update(
            environmentId: String,
            params: EnvironmentUpdateParams = EnvironmentUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            update(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see update */
        fun update(
            environmentId: String,
            params: EnvironmentUpdateParams = EnvironmentUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            update(environmentId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: EnvironmentUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>>

        /** @see update */
        fun update(
            params: EnvironmentUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            environmentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            update(environmentId, EnvironmentUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/environments?beta=true`, but is otherwise the
         * same as [EnvironmentServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<EnvironmentListPageAsync>> =
            list(EnvironmentListParams.none())

        /** @see list */
        fun list(
            params: EnvironmentListParams = EnvironmentListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<EnvironmentListPageAsync>>

        /** @see list */
        fun list(
            params: EnvironmentListParams = EnvironmentListParams.none()
        ): CompletableFuture<HttpResponseFor<EnvironmentListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<EnvironmentListPageAsync>> =
            list(EnvironmentListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/environments/{environment_id}?beta=true`, but
         * is otherwise the same as [EnvironmentServiceAsync.delete].
         */
        fun delete(
            environmentId: String
        ): CompletableFuture<HttpResponseFor<BetaEnvironmentDeleteResponse>> =
            delete(environmentId, EnvironmentDeleteParams.none())

        /** @see delete */
        fun delete(
            environmentId: String,
            params: EnvironmentDeleteParams = EnvironmentDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironmentDeleteResponse>> =
            delete(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see delete */
        fun delete(
            environmentId: String,
            params: EnvironmentDeleteParams = EnvironmentDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironmentDeleteResponse>> =
            delete(environmentId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: EnvironmentDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironmentDeleteResponse>>

        /** @see delete */
        fun delete(
            params: EnvironmentDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaEnvironmentDeleteResponse>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            environmentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaEnvironmentDeleteResponse>> =
            delete(environmentId, EnvironmentDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/environments/{environment_id}/archive?beta=true`, but is otherwise the same as
         * [EnvironmentServiceAsync.archive].
         */
        fun archive(environmentId: String): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            archive(environmentId, EnvironmentArchiveParams.none())

        /** @see archive */
        fun archive(
            environmentId: String,
            params: EnvironmentArchiveParams = EnvironmentArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            archive(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see archive */
        fun archive(
            environmentId: String,
            params: EnvironmentArchiveParams = EnvironmentArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            archive(environmentId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: EnvironmentArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>>

        /** @see archive */
        fun archive(
            params: EnvironmentArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            environmentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> =
            archive(environmentId, EnvironmentArchiveParams.none(), requestOptions)
    }
}
