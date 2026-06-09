// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.deploymentruns.BetaManagedAgentsDeploymentRun
import com.anthropic.models.beta.deploymentruns.DeploymentRunListPageAsync
import com.anthropic.models.beta.deploymentruns.DeploymentRunListParams
import com.anthropic.models.beta.deploymentruns.DeploymentRunRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface DeploymentRunServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): DeploymentRunServiceAsync

    /** Get Deployment Run */
    fun retrieve(deploymentRunId: String): CompletableFuture<BetaManagedAgentsDeploymentRun> =
        retrieve(deploymentRunId, DeploymentRunRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        deploymentRunId: String,
        params: DeploymentRunRetrieveParams = DeploymentRunRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeploymentRun> =
        retrieve(params.toBuilder().deploymentRunId(deploymentRunId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        deploymentRunId: String,
        params: DeploymentRunRetrieveParams = DeploymentRunRetrieveParams.none(),
    ): CompletableFuture<BetaManagedAgentsDeploymentRun> =
        retrieve(deploymentRunId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: DeploymentRunRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeploymentRun>

    /** @see retrieve */
    fun retrieve(
        params: DeploymentRunRetrieveParams
    ): CompletableFuture<BetaManagedAgentsDeploymentRun> = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        deploymentRunId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeploymentRun> =
        retrieve(deploymentRunId, DeploymentRunRetrieveParams.none(), requestOptions)

    /** List Deployment Runs */
    fun list(): CompletableFuture<DeploymentRunListPageAsync> = list(DeploymentRunListParams.none())

    /** @see list */
    fun list(
        params: DeploymentRunListParams = DeploymentRunListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeploymentRunListPageAsync>

    /** @see list */
    fun list(
        params: DeploymentRunListParams = DeploymentRunListParams.none()
    ): CompletableFuture<DeploymentRunListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<DeploymentRunListPageAsync> =
        list(DeploymentRunListParams.none(), requestOptions)

    /**
     * A view of [DeploymentRunServiceAsync] that provides access to raw HTTP responses for each
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
        ): DeploymentRunServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/deployment_runs/{deployment_run_id}?beta=true`,
         * but is otherwise the same as [DeploymentRunServiceAsync.retrieve].
         */
        fun retrieve(
            deploymentRunId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>> =
            retrieve(deploymentRunId, DeploymentRunRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            deploymentRunId: String,
            params: DeploymentRunRetrieveParams = DeploymentRunRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>> =
            retrieve(params.toBuilder().deploymentRunId(deploymentRunId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            deploymentRunId: String,
            params: DeploymentRunRetrieveParams = DeploymentRunRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>> =
            retrieve(deploymentRunId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: DeploymentRunRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>>

        /** @see retrieve */
        fun retrieve(
            params: DeploymentRunRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            deploymentRunId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeploymentRun>> =
            retrieve(deploymentRunId, DeploymentRunRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/deployment_runs?beta=true`, but is otherwise the
         * same as [DeploymentRunServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<DeploymentRunListPageAsync>> =
            list(DeploymentRunListParams.none())

        /** @see list */
        fun list(
            params: DeploymentRunListParams = DeploymentRunListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeploymentRunListPageAsync>>

        /** @see list */
        fun list(
            params: DeploymentRunListParams = DeploymentRunListParams.none()
        ): CompletableFuture<HttpResponseFor<DeploymentRunListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<DeploymentRunListPageAsync>> =
            list(DeploymentRunListParams.none(), requestOptions)
    }
}
