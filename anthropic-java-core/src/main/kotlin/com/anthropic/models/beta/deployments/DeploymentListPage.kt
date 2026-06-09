// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.DeploymentService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see DeploymentService.list */
class DeploymentListPage
private constructor(
    private val service: DeploymentService,
    private val params: DeploymentListParams,
    private val response: DeploymentListPageResponse,
) : Page<BetaManagedAgentsDeployment> {

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

    override fun nextPage(): DeploymentListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaManagedAgentsDeployment> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): DeploymentListParams = params

    /** The response that this page was parsed from. */
    fun response(): DeploymentListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [DeploymentListPage].
         *
         * The following fields are required:
         * ```java
         * .service()
         * .params()
         * .response()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [DeploymentListPage]. */
    class Builder internal constructor() {

        private var service: DeploymentService? = null
        private var params: DeploymentListParams? = null
        private var response: DeploymentListPageResponse? = null

        @JvmSynthetic
        internal fun from(deploymentListPage: DeploymentListPage) = apply {
            service = deploymentListPage.service
            params = deploymentListPage.params
            response = deploymentListPage.response
        }

        fun service(service: DeploymentService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: DeploymentListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: DeploymentListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [DeploymentListPage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .service()
         * .params()
         * .response()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): DeploymentListPage =
            DeploymentListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is DeploymentListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "DeploymentListPage{service=$service, params=$params, response=$response}"
}
