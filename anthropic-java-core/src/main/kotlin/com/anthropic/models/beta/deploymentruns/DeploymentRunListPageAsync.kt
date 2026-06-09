// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.DeploymentRunServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see DeploymentRunServiceAsync.list */
class DeploymentRunListPageAsync
private constructor(
    private val service: DeploymentRunServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: DeploymentRunListParams,
    private val response: DeploymentRunListPageResponse,
) : PageAsync<BetaManagedAgentsDeploymentRun> {

    /**
     * Delegates to [DeploymentRunListPageResponse], but gracefully handles missing data.
     *
     * @see DeploymentRunListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsDeploymentRun> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [DeploymentRunListPageResponse], but gracefully handles missing data.
     *
     * @see DeploymentRunListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsDeploymentRun> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): DeploymentRunListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<DeploymentRunListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaManagedAgentsDeploymentRun> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): DeploymentRunListParams = params

    /** The response that this page was parsed from. */
    fun response(): DeploymentRunListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [DeploymentRunListPageAsync].
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

    /** A builder for [DeploymentRunListPageAsync]. */
    class Builder internal constructor() {

        private var service: DeploymentRunServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: DeploymentRunListParams? = null
        private var response: DeploymentRunListPageResponse? = null

        @JvmSynthetic
        internal fun from(deploymentRunListPageAsync: DeploymentRunListPageAsync) = apply {
            service = deploymentRunListPageAsync.service
            streamHandlerExecutor = deploymentRunListPageAsync.streamHandlerExecutor
            params = deploymentRunListPageAsync.params
            response = deploymentRunListPageAsync.response
        }

        fun service(service: DeploymentRunServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: DeploymentRunListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: DeploymentRunListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [DeploymentRunListPageAsync].
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
        fun build(): DeploymentRunListPageAsync =
            DeploymentRunListPageAsync(
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

        return other is DeploymentRunListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "DeploymentRunListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
