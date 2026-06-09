// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.DeploymentRunService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see DeploymentRunService.list */
class DeploymentRunListPage
private constructor(
    private val service: DeploymentRunService,
    private val params: DeploymentRunListParams,
    private val response: DeploymentRunListPageResponse,
) : Page<BetaManagedAgentsDeploymentRun> {

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

    override fun nextPage(): DeploymentRunListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaManagedAgentsDeploymentRun> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): DeploymentRunListParams = params

    /** The response that this page was parsed from. */
    fun response(): DeploymentRunListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [DeploymentRunListPage].
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

    /** A builder for [DeploymentRunListPage]. */
    class Builder internal constructor() {

        private var service: DeploymentRunService? = null
        private var params: DeploymentRunListParams? = null
        private var response: DeploymentRunListPageResponse? = null

        @JvmSynthetic
        internal fun from(deploymentRunListPage: DeploymentRunListPage) = apply {
            service = deploymentRunListPage.service
            params = deploymentRunListPage.params
            response = deploymentRunListPage.response
        }

        fun service(service: DeploymentRunService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: DeploymentRunListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: DeploymentRunListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [DeploymentRunListPage].
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
        fun build(): DeploymentRunListPage =
            DeploymentRunListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is DeploymentRunListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "DeploymentRunListPage{service=$service, params=$params, response=$response}"
}
