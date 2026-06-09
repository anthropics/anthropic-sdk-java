// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.deploymentruns.BetaManagedAgentsDeploymentRun
import com.anthropic.models.beta.deployments.BetaManagedAgentsDeployment
import com.anthropic.models.beta.deployments.DeploymentArchiveParams
import com.anthropic.models.beta.deployments.DeploymentCreateParams
import com.anthropic.models.beta.deployments.DeploymentListPage
import com.anthropic.models.beta.deployments.DeploymentListParams
import com.anthropic.models.beta.deployments.DeploymentPauseParams
import com.anthropic.models.beta.deployments.DeploymentRetrieveParams
import com.anthropic.models.beta.deployments.DeploymentRunParams
import com.anthropic.models.beta.deployments.DeploymentUnpauseParams
import com.anthropic.models.beta.deployments.DeploymentUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface DeploymentService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): DeploymentService

    /** Create Deployment */
    fun create(params: DeploymentCreateParams): BetaManagedAgentsDeployment =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: DeploymentCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeployment

    /** Get Deployment */
    fun retrieve(deploymentId: String): BetaManagedAgentsDeployment =
        retrieve(deploymentId, DeploymentRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        deploymentId: String,
        params: DeploymentRetrieveParams = DeploymentRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeployment =
        retrieve(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        deploymentId: String,
        params: DeploymentRetrieveParams = DeploymentRetrieveParams.none(),
    ): BetaManagedAgentsDeployment = retrieve(deploymentId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: DeploymentRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeployment

    /** @see retrieve */
    fun retrieve(params: DeploymentRetrieveParams): BetaManagedAgentsDeployment =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        deploymentId: String,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeployment =
        retrieve(deploymentId, DeploymentRetrieveParams.none(), requestOptions)

    /** Update Deployment */
    fun update(deploymentId: String): BetaManagedAgentsDeployment =
        update(deploymentId, DeploymentUpdateParams.none())

    /** @see update */
    fun update(
        deploymentId: String,
        params: DeploymentUpdateParams = DeploymentUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeployment =
        update(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see update */
    fun update(
        deploymentId: String,
        params: DeploymentUpdateParams = DeploymentUpdateParams.none(),
    ): BetaManagedAgentsDeployment = update(deploymentId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: DeploymentUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeployment

    /** @see update */
    fun update(params: DeploymentUpdateParams): BetaManagedAgentsDeployment =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(deploymentId: String, requestOptions: RequestOptions): BetaManagedAgentsDeployment =
        update(deploymentId, DeploymentUpdateParams.none(), requestOptions)

    /** List Deployments */
    fun list(): DeploymentListPage = list(DeploymentListParams.none())

    /** @see list */
    fun list(
        params: DeploymentListParams = DeploymentListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeploymentListPage

    /** @see list */
    fun list(params: DeploymentListParams = DeploymentListParams.none()): DeploymentListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): DeploymentListPage =
        list(DeploymentListParams.none(), requestOptions)

    /** Archive Deployment */
    fun archive(deploymentId: String): BetaManagedAgentsDeployment =
        archive(deploymentId, DeploymentArchiveParams.none())

    /** @see archive */
    fun archive(
        deploymentId: String,
        params: DeploymentArchiveParams = DeploymentArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeployment =
        archive(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see archive */
    fun archive(
        deploymentId: String,
        params: DeploymentArchiveParams = DeploymentArchiveParams.none(),
    ): BetaManagedAgentsDeployment = archive(deploymentId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: DeploymentArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeployment

    /** @see archive */
    fun archive(params: DeploymentArchiveParams): BetaManagedAgentsDeployment =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(deploymentId: String, requestOptions: RequestOptions): BetaManagedAgentsDeployment =
        archive(deploymentId, DeploymentArchiveParams.none(), requestOptions)

    /** Pause Deployment */
    fun pause(deploymentId: String): BetaManagedAgentsDeployment =
        pause(deploymentId, DeploymentPauseParams.none())

    /** @see pause */
    fun pause(
        deploymentId: String,
        params: DeploymentPauseParams = DeploymentPauseParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeployment =
        pause(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see pause */
    fun pause(
        deploymentId: String,
        params: DeploymentPauseParams = DeploymentPauseParams.none(),
    ): BetaManagedAgentsDeployment = pause(deploymentId, params, RequestOptions.none())

    /** @see pause */
    fun pause(
        params: DeploymentPauseParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeployment

    /** @see pause */
    fun pause(params: DeploymentPauseParams): BetaManagedAgentsDeployment =
        pause(params, RequestOptions.none())

    /** @see pause */
    fun pause(deploymentId: String, requestOptions: RequestOptions): BetaManagedAgentsDeployment =
        pause(deploymentId, DeploymentPauseParams.none(), requestOptions)

    /** Run Deployment Now */
    fun run(deploymentId: String): BetaManagedAgentsDeploymentRun =
        run(deploymentId, DeploymentRunParams.none())

    /** @see run */
    fun run(
        deploymentId: String,
        params: DeploymentRunParams = DeploymentRunParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeploymentRun =
        run(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see run */
    fun run(
        deploymentId: String,
        params: DeploymentRunParams = DeploymentRunParams.none(),
    ): BetaManagedAgentsDeploymentRun = run(deploymentId, params, RequestOptions.none())

    /** @see run */
    fun run(
        params: DeploymentRunParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeploymentRun

    /** @see run */
    fun run(params: DeploymentRunParams): BetaManagedAgentsDeploymentRun =
        run(params, RequestOptions.none())

    /** @see run */
    fun run(deploymentId: String, requestOptions: RequestOptions): BetaManagedAgentsDeploymentRun =
        run(deploymentId, DeploymentRunParams.none(), requestOptions)

    /** Unpause Deployment */
    fun unpause(deploymentId: String): BetaManagedAgentsDeployment =
        unpause(deploymentId, DeploymentUnpauseParams.none())

    /** @see unpause */
    fun unpause(
        deploymentId: String,
        params: DeploymentUnpauseParams = DeploymentUnpauseParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeployment =
        unpause(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

    /** @see unpause */
    fun unpause(
        deploymentId: String,
        params: DeploymentUnpauseParams = DeploymentUnpauseParams.none(),
    ): BetaManagedAgentsDeployment = unpause(deploymentId, params, RequestOptions.none())

    /** @see unpause */
    fun unpause(
        params: DeploymentUnpauseParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeployment

    /** @see unpause */
    fun unpause(params: DeploymentUnpauseParams): BetaManagedAgentsDeployment =
        unpause(params, RequestOptions.none())

    /** @see unpause */
    fun unpause(deploymentId: String, requestOptions: RequestOptions): BetaManagedAgentsDeployment =
        unpause(deploymentId, DeploymentUnpauseParams.none(), requestOptions)

    /** A view of [DeploymentService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): DeploymentService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/deployments?beta=true`, but is otherwise the
         * same as [DeploymentService.create].
         */
        @MustBeClosed
        fun create(params: DeploymentCreateParams): HttpResponseFor<BetaManagedAgentsDeployment> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: DeploymentCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment>

        /**
         * Returns a raw HTTP response for `get /v1/deployments/{deployment_id}?beta=true`, but is
         * otherwise the same as [DeploymentService.retrieve].
         */
        @MustBeClosed
        fun retrieve(deploymentId: String): HttpResponseFor<BetaManagedAgentsDeployment> =
            retrieve(deploymentId, DeploymentRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            deploymentId: String,
            params: DeploymentRetrieveParams = DeploymentRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            retrieve(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            deploymentId: String,
            params: DeploymentRetrieveParams = DeploymentRetrieveParams.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            retrieve(deploymentId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: DeploymentRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: DeploymentRetrieveParams
        ): HttpResponseFor<BetaManagedAgentsDeployment> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            retrieve(deploymentId, DeploymentRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/deployments/{deployment_id}?beta=true`, but is
         * otherwise the same as [DeploymentService.update].
         */
        @MustBeClosed
        fun update(deploymentId: String): HttpResponseFor<BetaManagedAgentsDeployment> =
            update(deploymentId, DeploymentUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            deploymentId: String,
            params: DeploymentUpdateParams = DeploymentUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            update(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            deploymentId: String,
            params: DeploymentUpdateParams = DeploymentUpdateParams.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            update(deploymentId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: DeploymentUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment>

        /** @see update */
        @MustBeClosed
        fun update(params: DeploymentUpdateParams): HttpResponseFor<BetaManagedAgentsDeployment> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            update(deploymentId, DeploymentUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/deployments?beta=true`, but is otherwise the
         * same as [DeploymentService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<DeploymentListPage> = list(DeploymentListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: DeploymentListParams = DeploymentListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeploymentListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: DeploymentListParams = DeploymentListParams.none()
        ): HttpResponseFor<DeploymentListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<DeploymentListPage> =
            list(DeploymentListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/deployments/{deployment_id}/archive?beta=true`,
         * but is otherwise the same as [DeploymentService.archive].
         */
        @MustBeClosed
        fun archive(deploymentId: String): HttpResponseFor<BetaManagedAgentsDeployment> =
            archive(deploymentId, DeploymentArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            deploymentId: String,
            params: DeploymentArchiveParams = DeploymentArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            archive(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            deploymentId: String,
            params: DeploymentArchiveParams = DeploymentArchiveParams.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            archive(deploymentId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: DeploymentArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment>

        /** @see archive */
        @MustBeClosed
        fun archive(params: DeploymentArchiveParams): HttpResponseFor<BetaManagedAgentsDeployment> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            archive(deploymentId, DeploymentArchiveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/deployments/{deployment_id}/pause?beta=true`,
         * but is otherwise the same as [DeploymentService.pause].
         */
        @MustBeClosed
        fun pause(deploymentId: String): HttpResponseFor<BetaManagedAgentsDeployment> =
            pause(deploymentId, DeploymentPauseParams.none())

        /** @see pause */
        @MustBeClosed
        fun pause(
            deploymentId: String,
            params: DeploymentPauseParams = DeploymentPauseParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            pause(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see pause */
        @MustBeClosed
        fun pause(
            deploymentId: String,
            params: DeploymentPauseParams = DeploymentPauseParams.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            pause(deploymentId, params, RequestOptions.none())

        /** @see pause */
        @MustBeClosed
        fun pause(
            params: DeploymentPauseParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment>

        /** @see pause */
        @MustBeClosed
        fun pause(params: DeploymentPauseParams): HttpResponseFor<BetaManagedAgentsDeployment> =
            pause(params, RequestOptions.none())

        /** @see pause */
        @MustBeClosed
        fun pause(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            pause(deploymentId, DeploymentPauseParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/deployments/{deployment_id}/run?beta=true`, but
         * is otherwise the same as [DeploymentService.run].
         */
        @MustBeClosed
        fun run(deploymentId: String): HttpResponseFor<BetaManagedAgentsDeploymentRun> =
            run(deploymentId, DeploymentRunParams.none())

        /** @see run */
        @MustBeClosed
        fun run(
            deploymentId: String,
            params: DeploymentRunParams = DeploymentRunParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeploymentRun> =
            run(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see run */
        @MustBeClosed
        fun run(
            deploymentId: String,
            params: DeploymentRunParams = DeploymentRunParams.none(),
        ): HttpResponseFor<BetaManagedAgentsDeploymentRun> =
            run(deploymentId, params, RequestOptions.none())

        /** @see run */
        @MustBeClosed
        fun run(
            params: DeploymentRunParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeploymentRun>

        /** @see run */
        @MustBeClosed
        fun run(params: DeploymentRunParams): HttpResponseFor<BetaManagedAgentsDeploymentRun> =
            run(params, RequestOptions.none())

        /** @see run */
        @MustBeClosed
        fun run(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeploymentRun> =
            run(deploymentId, DeploymentRunParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/deployments/{deployment_id}/unpause?beta=true`,
         * but is otherwise the same as [DeploymentService.unpause].
         */
        @MustBeClosed
        fun unpause(deploymentId: String): HttpResponseFor<BetaManagedAgentsDeployment> =
            unpause(deploymentId, DeploymentUnpauseParams.none())

        /** @see unpause */
        @MustBeClosed
        fun unpause(
            deploymentId: String,
            params: DeploymentUnpauseParams = DeploymentUnpauseParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            unpause(params.toBuilder().deploymentId(deploymentId).build(), requestOptions)

        /** @see unpause */
        @MustBeClosed
        fun unpause(
            deploymentId: String,
            params: DeploymentUnpauseParams = DeploymentUnpauseParams.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            unpause(deploymentId, params, RequestOptions.none())

        /** @see unpause */
        @MustBeClosed
        fun unpause(
            params: DeploymentUnpauseParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeployment>

        /** @see unpause */
        @MustBeClosed
        fun unpause(params: DeploymentUnpauseParams): HttpResponseFor<BetaManagedAgentsDeployment> =
            unpause(params, RequestOptions.none())

        /** @see unpause */
        @MustBeClosed
        fun unpause(
            deploymentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeployment> =
            unpause(deploymentId, DeploymentUnpauseParams.none(), requestOptions)
    }
}
