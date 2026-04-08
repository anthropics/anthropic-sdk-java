// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.environments.BetaEnvironment
import com.anthropic.models.beta.environments.BetaEnvironmentDeleteResponse
import com.anthropic.models.beta.environments.EnvironmentArchiveParams
import com.anthropic.models.beta.environments.EnvironmentCreateParams
import com.anthropic.models.beta.environments.EnvironmentDeleteParams
import com.anthropic.models.beta.environments.EnvironmentListPage
import com.anthropic.models.beta.environments.EnvironmentListParams
import com.anthropic.models.beta.environments.EnvironmentRetrieveParams
import com.anthropic.models.beta.environments.EnvironmentUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface EnvironmentService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): EnvironmentService

    /** Create a new environment with the specified configuration. */
    fun create(params: EnvironmentCreateParams): BetaEnvironment =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: EnvironmentCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaEnvironment

    /** Retrieve a specific environment by ID. */
    fun retrieve(environmentId: String): BetaEnvironment =
        retrieve(environmentId, EnvironmentRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        environmentId: String,
        params: EnvironmentRetrieveParams = EnvironmentRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaEnvironment =
        retrieve(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        environmentId: String,
        params: EnvironmentRetrieveParams = EnvironmentRetrieveParams.none(),
    ): BetaEnvironment = retrieve(environmentId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: EnvironmentRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaEnvironment

    /** @see retrieve */
    fun retrieve(params: EnvironmentRetrieveParams): BetaEnvironment =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(environmentId: String, requestOptions: RequestOptions): BetaEnvironment =
        retrieve(environmentId, EnvironmentRetrieveParams.none(), requestOptions)

    /** Update an existing environment's configuration. */
    fun update(environmentId: String): BetaEnvironment =
        update(environmentId, EnvironmentUpdateParams.none())

    /** @see update */
    fun update(
        environmentId: String,
        params: EnvironmentUpdateParams = EnvironmentUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaEnvironment =
        update(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see update */
    fun update(
        environmentId: String,
        params: EnvironmentUpdateParams = EnvironmentUpdateParams.none(),
    ): BetaEnvironment = update(environmentId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: EnvironmentUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaEnvironment

    /** @see update */
    fun update(params: EnvironmentUpdateParams): BetaEnvironment =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(environmentId: String, requestOptions: RequestOptions): BetaEnvironment =
        update(environmentId, EnvironmentUpdateParams.none(), requestOptions)

    /** List environments with pagination support. */
    fun list(): EnvironmentListPage = list(EnvironmentListParams.none())

    /** @see list */
    fun list(
        params: EnvironmentListParams = EnvironmentListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EnvironmentListPage

    /** @see list */
    fun list(params: EnvironmentListParams = EnvironmentListParams.none()): EnvironmentListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): EnvironmentListPage =
        list(EnvironmentListParams.none(), requestOptions)

    /** Delete an environment by ID. Returns a confirmation of the deletion. */
    fun delete(environmentId: String): BetaEnvironmentDeleteResponse =
        delete(environmentId, EnvironmentDeleteParams.none())

    /** @see delete */
    fun delete(
        environmentId: String,
        params: EnvironmentDeleteParams = EnvironmentDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaEnvironmentDeleteResponse =
        delete(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see delete */
    fun delete(
        environmentId: String,
        params: EnvironmentDeleteParams = EnvironmentDeleteParams.none(),
    ): BetaEnvironmentDeleteResponse = delete(environmentId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: EnvironmentDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaEnvironmentDeleteResponse

    /** @see delete */
    fun delete(params: EnvironmentDeleteParams): BetaEnvironmentDeleteResponse =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        environmentId: String,
        requestOptions: RequestOptions,
    ): BetaEnvironmentDeleteResponse =
        delete(environmentId, EnvironmentDeleteParams.none(), requestOptions)

    /**
     * Archive an environment by ID. Archived environments cannot be used to create new sessions.
     */
    fun archive(environmentId: String): BetaEnvironment =
        archive(environmentId, EnvironmentArchiveParams.none())

    /** @see archive */
    fun archive(
        environmentId: String,
        params: EnvironmentArchiveParams = EnvironmentArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaEnvironment =
        archive(params.toBuilder().environmentId(environmentId).build(), requestOptions)

    /** @see archive */
    fun archive(
        environmentId: String,
        params: EnvironmentArchiveParams = EnvironmentArchiveParams.none(),
    ): BetaEnvironment = archive(environmentId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: EnvironmentArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaEnvironment

    /** @see archive */
    fun archive(params: EnvironmentArchiveParams): BetaEnvironment =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(environmentId: String, requestOptions: RequestOptions): BetaEnvironment =
        archive(environmentId, EnvironmentArchiveParams.none(), requestOptions)

    /**
     * A view of [EnvironmentService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): EnvironmentService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/environments?beta=true`, but is otherwise the
         * same as [EnvironmentService.create].
         */
        @MustBeClosed
        fun create(params: EnvironmentCreateParams): HttpResponseFor<BetaEnvironment> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: EnvironmentCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaEnvironment>

        /**
         * Returns a raw HTTP response for `get /v1/environments/{environment_id}?beta=true`, but is
         * otherwise the same as [EnvironmentService.retrieve].
         */
        @MustBeClosed
        fun retrieve(environmentId: String): HttpResponseFor<BetaEnvironment> =
            retrieve(environmentId, EnvironmentRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            environmentId: String,
            params: EnvironmentRetrieveParams = EnvironmentRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaEnvironment> =
            retrieve(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            environmentId: String,
            params: EnvironmentRetrieveParams = EnvironmentRetrieveParams.none(),
        ): HttpResponseFor<BetaEnvironment> = retrieve(environmentId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: EnvironmentRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaEnvironment>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: EnvironmentRetrieveParams): HttpResponseFor<BetaEnvironment> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            environmentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaEnvironment> =
            retrieve(environmentId, EnvironmentRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/environments/{environment_id}?beta=true`, but
         * is otherwise the same as [EnvironmentService.update].
         */
        @MustBeClosed
        fun update(environmentId: String): HttpResponseFor<BetaEnvironment> =
            update(environmentId, EnvironmentUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            environmentId: String,
            params: EnvironmentUpdateParams = EnvironmentUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaEnvironment> =
            update(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            environmentId: String,
            params: EnvironmentUpdateParams = EnvironmentUpdateParams.none(),
        ): HttpResponseFor<BetaEnvironment> = update(environmentId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: EnvironmentUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaEnvironment>

        /** @see update */
        @MustBeClosed
        fun update(params: EnvironmentUpdateParams): HttpResponseFor<BetaEnvironment> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            environmentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaEnvironment> =
            update(environmentId, EnvironmentUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/environments?beta=true`, but is otherwise the
         * same as [EnvironmentService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<EnvironmentListPage> = list(EnvironmentListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: EnvironmentListParams = EnvironmentListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EnvironmentListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: EnvironmentListParams = EnvironmentListParams.none()
        ): HttpResponseFor<EnvironmentListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<EnvironmentListPage> =
            list(EnvironmentListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/environments/{environment_id}?beta=true`, but
         * is otherwise the same as [EnvironmentService.delete].
         */
        @MustBeClosed
        fun delete(environmentId: String): HttpResponseFor<BetaEnvironmentDeleteResponse> =
            delete(environmentId, EnvironmentDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            environmentId: String,
            params: EnvironmentDeleteParams = EnvironmentDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaEnvironmentDeleteResponse> =
            delete(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            environmentId: String,
            params: EnvironmentDeleteParams = EnvironmentDeleteParams.none(),
        ): HttpResponseFor<BetaEnvironmentDeleteResponse> =
            delete(environmentId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: EnvironmentDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaEnvironmentDeleteResponse>

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: EnvironmentDeleteParams
        ): HttpResponseFor<BetaEnvironmentDeleteResponse> = delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            environmentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaEnvironmentDeleteResponse> =
            delete(environmentId, EnvironmentDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/environments/{environment_id}/archive?beta=true`, but is otherwise the same as
         * [EnvironmentService.archive].
         */
        @MustBeClosed
        fun archive(environmentId: String): HttpResponseFor<BetaEnvironment> =
            archive(environmentId, EnvironmentArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            environmentId: String,
            params: EnvironmentArchiveParams = EnvironmentArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaEnvironment> =
            archive(params.toBuilder().environmentId(environmentId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            environmentId: String,
            params: EnvironmentArchiveParams = EnvironmentArchiveParams.none(),
        ): HttpResponseFor<BetaEnvironment> = archive(environmentId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: EnvironmentArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaEnvironment>

        /** @see archive */
        @MustBeClosed
        fun archive(params: EnvironmentArchiveParams): HttpResponseFor<BetaEnvironment> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            environmentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaEnvironment> =
            archive(environmentId, EnvironmentArchiveParams.none(), requestOptions)
    }
}
