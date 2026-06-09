// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.deploymentruns.BetaManagedAgentsDeploymentRun
import com.anthropic.models.beta.deployments.BetaManagedAgentsDeployment
import com.anthropic.models.beta.deployments.DeploymentArchiveParams
import com.anthropic.models.beta.deployments.DeploymentCreateParams
import com.anthropic.models.beta.deployments.DeploymentListPageAsync
import com.anthropic.models.beta.deployments.DeploymentListParams
import com.anthropic.models.beta.deployments.DeploymentPauseParams
import com.anthropic.models.beta.deployments.DeploymentRetrieveParams
import com.anthropic.models.beta.deployments.DeploymentRunParams
import com.anthropic.models.beta.deployments.DeploymentUnpauseParams
import com.anthropic.models.beta.deployments.DeploymentUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface DeploymentServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): DeploymentServiceAsync

    /** Create Deployment */
    fun create(params: DeploymentCreateParams): CompletableFuture<BetaManagedAgentsDeployment> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: DeploymentCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment>

    /** Get Deployment */
    fun retrieve(deploymentId: String): CompletableFuture<BetaManagedAgentsDeployment> =
        retrieve(deploymentId, DeploymentRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        deploymentId: String,
        params: DeploymentRetrieveParams = DeploymentRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        retrieve(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        deploymentId: String,
        params: DeploymentRetrieveParams = DeploymentRetrieveParams.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        retrieve(deploymentId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: DeploymentRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment>

    /** @see retrieve */
    fun retrieve(params: DeploymentRetrieveParams): CompletableFuture<BetaManagedAgentsDeployment> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        deploymentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        retrieve(deploymentId, DeploymentRetrieveParams.none(), requestOptions)

    /** Update Deployment */
    fun update(deploymentId: String): CompletableFuture<BetaManagedAgentsDeployment> =
        update(deploymentId, DeploymentUpdateParams.none())

    /** @see update */
    fun update(
        deploymentId: String,
        params: DeploymentUpdateParams = DeploymentUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        update(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see update */
    fun update(
        deploymentId: String,
        params: DeploymentUpdateParams = DeploymentUpdateParams.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        update(deploymentId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: DeploymentUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment>

    /** @see update */
    fun update(params: DeploymentUpdateParams): CompletableFuture<BetaManagedAgentsDeployment> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        deploymentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        update(deploymentId, DeploymentUpdateParams.none(), requestOptions)

    /** List Deployments */
    fun list(): CompletableFuture<DeploymentListPageAsync> = list(DeploymentListParams.none())

    /** @see list */
    fun list(
        params: DeploymentListParams = DeploymentListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeploymentListPageAsync>

    /** @see list */
    fun list(
        params: DeploymentListParams = DeploymentListParams.none()
    ): CompletableFuture<DeploymentListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<DeploymentListPageAsync> =
        list(DeploymentListParams.none(), requestOptions)

    /** Archive Deployment */
    fun archive(deploymentId: String): CompletableFuture<BetaManagedAgentsDeployment> =
        archive(deploymentId, DeploymentArchiveParams.none())

    /** @see archive */
    fun archive(
        deploymentId: String,
        params: DeploymentArchiveParams = DeploymentArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        archive(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see archive */
    fun archive(
        deploymentId: String,
        params: DeploymentArchiveParams = DeploymentArchiveParams.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        archive(deploymentId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: DeploymentArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment>

    /** @see archive */
    fun archive(params: DeploymentArchiveParams): CompletableFuture<BetaManagedAgentsDeployment> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        deploymentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        archive(deploymentId, DeploymentArchiveParams.none(), requestOptions)

    /** Pause Deployment */
    fun pause(deploymentId: String): CompletableFuture<BetaManagedAgentsDeployment> =
        pause(deploymentId, DeploymentPauseParams.none())

    /** @see pause */
    fun pause(
        deploymentId: String,
        params: DeploymentPauseParams = DeploymentPauseParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        pause(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see pause */
    fun pause(
        deploymentId: String,
        params: DeploymentPauseParams = DeploymentPauseParams.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        pause(deploymentId, params, RequestOptions.none())

    /** @see pause */
    fun pause(
        params: DeploymentPauseParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment>

    /** @see pause */
    fun pause(params: DeploymentPauseParams): CompletableFuture<BetaManagedAgentsDeployment> =
        pause(params, RequestOptions.none())

    /** @see pause */
    fun pause(
        deploymentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        pause(deploymentId, DeploymentPauseParams.none(), requestOptions)

    /** Run Deployment Now */
    fun run(deploymentId: String): CompletableFuture<BetaManagedAgentsDeploymentRun> =
        run(deploymentId, DeploymentRunParams.none())

    /** @see run */
    fun run(
        deploymentId: String,
        params: DeploymentRunParams = DeploymentRunParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeploymentRun> =
        run(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see run */
    fun run(
        deploymentId: String,
        params: DeploymentRunParams = DeploymentRunParams.none(),
    ): CompletableFuture<BetaManagedAgentsDeploymentRun> =
        run(deploymentId, params, RequestOptions.none())

    /** @see run */
    fun run(
        params: DeploymentRunParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeploymentRun>

    /** @see run */
    fun run(params: DeploymentRunParams): CompletableFuture<BetaManagedAgentsDeploymentRun> =
        run(params, RequestOptions.none())

    /** @see run */
    fun run(
        deploymentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeploymentRun> =
        run(deploymentId, DeploymentRunParams.none(), requestOptions)

    /** Unpause Deployment */
    fun unpause(deploymentId: String): CompletableFuture<BetaManagedAgentsDeployment> =
        unpause(deploymentId, DeploymentUnpauseParams.none())

    /** @see unpause */
    fun unpause(
        deploymentId: String,
        params: DeploymentUnpauseParams = DeploymentUnpauseParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        unpause(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see unpause */
    fun unpause(
        deploymentId: String,
        params: DeploymentUnpauseParams = DeploymentUnpauseParams.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        unpause(deploymentId, params, RequestOptions.none())

    /** @see unpause */
    fun unpause(
        params: DeploymentUnpauseParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeployment>

    /** @see unpause */
    fun unpause(params: DeploymentUnpauseParams): CompletableFuture<BetaManagedAgentsDeployment> =
        unpause(params, RequestOptions.none())

    /** @see unpause */
    fun unpause(
        deploymentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeployment> =
        unpause(deploymentId, DeploymentUnpauseParams.none(), requestOptions)

    /**
     * A view of [DeploymentServiceAsync] that provides access to raw HTTP responses for each
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
        ): DeploymentServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/deployments?beta=true`, but is otherwise the
         * same as [DeploymentServiceAsync.create].
         */
        fun create(
            params: DeploymentCreateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: DeploymentCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>>

        /**
         * Returns a raw HTTP response for `get /v1/deployments/{deployment_id}?beta=true`, but is
         * otherwise the same as [DeploymentServiceAsync.retrieve].
         */
        fun retrieve(
            deploymentId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            retrieve(deploymentId, DeploymentRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            deploymentId: String,
            params: DeploymentRetrieveParams = DeploymentRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            retrieve(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            deploymentId: String,
            params: DeploymentRetrieveParams = DeploymentRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            retrieve(deploymentId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: DeploymentRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>>

        /** @see retrieve */
        fun retrieve(
            params: DeploymentRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            retrieve(deploymentId, DeploymentRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/deployments/{deployment_id}?beta=true`, but is
         * otherwise the same as [DeploymentServiceAsync.update].
         */
        fun update(
            deploymentId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            update(deploymentId, DeploymentUpdateParams.none())

        /** @see update */
        fun update(
            deploymentId: String,
            params: DeploymentUpdateParams = DeploymentUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            update(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see update */
        fun update(
            deploymentId: String,
            params: DeploymentUpdateParams = DeploymentUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            update(deploymentId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: DeploymentUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>>

        /** @see update */
        fun update(
            params: DeploymentUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            update(deploymentId, DeploymentUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/deployments?beta=true`, but is otherwise the
         * same as [DeploymentServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<DeploymentListPageAsync>> =
            list(DeploymentListParams.none())

        /** @see list */
        fun list(
            params: DeploymentListParams = DeploymentListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeploymentListPageAsync>>

        /** @see list */
        fun list(
            params: DeploymentListParams = DeploymentListParams.none()
        ): CompletableFuture<HttpResponseFor<DeploymentListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<DeploymentListPageAsync>> =
            list(DeploymentListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/deployments/{deployment_id}/archive?beta=true`,
         * but is otherwise the same as [DeploymentServiceAsync.archive].
         */
        fun archive(
            deploymentId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            archive(deploymentId, DeploymentArchiveParams.none())

        /** @see archive */
        fun archive(
            deploymentId: String,
            params: DeploymentArchiveParams = DeploymentArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            archive(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see archive */
        fun archive(
            deploymentId: String,
            params: DeploymentArchiveParams = DeploymentArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            archive(deploymentId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: DeploymentArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>>

        /** @see archive */
        fun archive(
            params: DeploymentArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            archive(deploymentId, DeploymentArchiveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/deployments/{deployment_id}/pause?beta=true`,
         * but is otherwise the same as [DeploymentServiceAsync.pause].
         */
        fun pause(
            deploymentId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            pause(deploymentId, DeploymentPauseParams.none())

        /** @see pause */
        fun pause(
            deploymentId: String,
            params: DeploymentPauseParams = DeploymentPauseParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            pause(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see pause */
        fun pause(
            deploymentId: String,
            params: DeploymentPauseParams = DeploymentPauseParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            pause(deploymentId, params, RequestOptions.none())

        /** @see pause */
        fun pause(
            params: DeploymentPauseParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>>

        /** @see pause */
        fun pause(
            params: DeploymentPauseParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            pause(params, RequestOptions.none())

        /** @see pause */
        fun pause(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            pause(deploymentId, DeploymentPauseParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/deployments/{deployment_id}/run?beta=true`, but
         * is otherwise the same as [DeploymentServiceAsync.run].
         */
        fun run(
            deploymentId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>> =
            run(deploymentId, DeploymentRunParams.none())

        /** @see run */
        fun run(
            deploymentId: String,
            params: DeploymentRunParams = DeploymentRunParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>> =
            run(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see run */
        fun run(
            deploymentId: String,
            params: DeploymentRunParams = DeploymentRunParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>> =
            run(deploymentId, params, RequestOptions.none())

        /** @see run */
        fun run(
            params: DeploymentRunParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>>

        /** @see run */
        fun run(
            params: DeploymentRunParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>> =
            run(params, RequestOptions.none())

        /** @see run */
        fun run(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>> =
            run(deploymentId, DeploymentRunParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/deployments/{deployment_id}/unpause?beta=true`,
         * but is otherwise the same as [DeploymentServiceAsync.unpause].
         */
        fun unpause(
            deploymentId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            unpause(deploymentId, DeploymentUnpauseParams.none())

        /** @see unpause */
        fun unpause(
            deploymentId: String,
            params: DeploymentUnpauseParams = DeploymentUnpauseParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            unpause(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see unpause */
        fun unpause(
            deploymentId: String,
            params: DeploymentUnpauseParams = DeploymentUnpauseParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            unpause(deploymentId, params, RequestOptions.none())

        /** @see unpause */
        fun unpause(
            params: DeploymentUnpauseParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>>

        /** @see unpause */
        fun unpause(
            params: DeploymentUnpauseParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            unpause(params, RequestOptions.none())

        /** @see unpause */
        fun unpause(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeployment>> =
            unpause(deploymentId, DeploymentUnpauseParams.none(), requestOptions)
    }
}
