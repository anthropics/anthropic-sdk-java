// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.DeploymentServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see DeploymentServiceAsync.list */
class DeploymentListPageAsync
private constructor(
    private val service: DeploymentServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: DeploymentListParams,
    private val response: DeploymentListPageResponse,
) : PageAsync<BetaManagedAgentsDeployment> {

    /**
     * Delegates to [DeploymentListPageResponse], but gracefully handles missing data.
     *
     * @see DeploymentListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsDeployment> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [DeploymentListPageResponse], but gracefully handles missing data.
     *
     * @see DeploymentListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsDeployment> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): DeploymentListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<DeploymentListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaManagedAgentsDeployment> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): DeploymentListParams = params

    /** The response that this page was parsed from. */
    fun response(): DeploymentListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [DeploymentListPageAsync].
         *
         * The following fields are required:
         * ```java
         * .service()
         * .streamHandlerExecutor()
         * .params()
         * .response()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [DeploymentListPageAsync]. */
    class Builder internal constructor() {

        private var service: DeploymentServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: DeploymentListParams? = null
        private var response: DeploymentListPageResponse? = null

        @JvmSynthetic
        internal fun from(deploymentListPageAsync: DeploymentListPageAsync) = apply {
            service = deploymentListPageAsync.service
            streamHandlerExecutor = deploymentListPageAsync.streamHandlerExecutor
            params = deploymentListPageAsync.params
            response = deploymentListPageAsync.response
        }

        fun service(service: DeploymentServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: DeploymentListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: DeploymentListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [DeploymentListPageAsync].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .service()
         * .streamHandlerExecutor()
         * .params()
         * .response()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): DeploymentListPageAsync =
            DeploymentListPageAsync(
                checkRequired("service", service),
                checkRequired("streamHandlerExecutor", streamHandlerExecutor),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is DeploymentListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "DeploymentListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
