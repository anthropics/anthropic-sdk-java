// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.deploymentruns.BetaManagedAgentsDeploymentRun
import com.anthropic.models.beta.deploymentruns.DeploymentRunListPage
import com.anthropic.models.beta.deploymentruns.DeploymentRunListParams
import com.anthropic.models.beta.deploymentruns.DeploymentRunRetrieveParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface DeploymentRunService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): DeploymentRunService

    /** Get Deployment Run */
    fun retrieve(deploymentRunId: String): BetaManagedAgentsDeploymentRun =
        retrieve(deploymentRunId, DeploymentRunRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        deploymentRunId: String,
        params: DeploymentRunRetrieveParams = DeploymentRunRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeploymentRun =
        retrieve(params.toBuilder().deploymentRunId(deploymentRunId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        deploymentRunId: String,
        params: DeploymentRunRetrieveParams = DeploymentRunRetrieveParams.none(),
    ): BetaManagedAgentsDeploymentRun = retrieve(deploymentRunId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: DeploymentRunRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeploymentRun

    /** @see retrieve */
    fun retrieve(params: DeploymentRunRetrieveParams): BetaManagedAgentsDeploymentRun =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        deploymentRunId: String,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeploymentRun =
        retrieve(deploymentRunId, DeploymentRunRetrieveParams.none(), requestOptions)

    /** List Deployment Runs */
    fun list(): DeploymentRunListPage = list(DeploymentRunListParams.none())

    /** @see list */
    fun list(
        params: DeploymentRunListParams = DeploymentRunListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeploymentRunListPage

    /** @see list */
    fun list(
        params: DeploymentRunListParams = DeploymentRunListParams.none()
    ): DeploymentRunListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): DeploymentRunListPage =
        list(DeploymentRunListParams.none(), requestOptions)

    /**
     * A view of [DeploymentRunService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): DeploymentRunService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/deployment_runs/{deployment_run_id}?beta=true`,
         * but is otherwise the same as [DeploymentRunService.retrieve].
         */
        @MustBeClosed
        fun retrieve(deploymentRunId: String): HttpResponseFor<BetaManagedAgentsDeploymentRun> =
            retrieve(deploymentRunId, DeploymentRunRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            deploymentRunId: String,
            params: DeploymentRunRetrieveParams = DeploymentRunRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeploymentRun> =
            retrieve(params.toBuilder().deploymentRunId(deploymentRunId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            deploymentRunId: String,
            params: DeploymentRunRetrieveParams = DeploymentRunRetrieveParams.none(),
        ): HttpResponseFor<BetaManagedAgentsDeploymentRun> =
            retrieve(deploymentRunId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: DeploymentRunRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeploymentRun>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: DeploymentRunRetrieveParams
        ): HttpResponseFor<BetaManagedAgentsDeploymentRun> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            deploymentRunId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeploymentRun> =
            retrieve(deploymentRunId, DeploymentRunRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/deployment_runs?beta=true`, but is otherwise the
         * same as [DeploymentRunService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<DeploymentRunListPage> = list(DeploymentRunListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: DeploymentRunListParams = DeploymentRunListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeploymentRunListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: DeploymentRunListParams = DeploymentRunListParams.none()
        ): HttpResponseFor<DeploymentRunListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<DeploymentRunListPage> =
            list(DeploymentRunListParams.none(), requestOptions)
    }
}
