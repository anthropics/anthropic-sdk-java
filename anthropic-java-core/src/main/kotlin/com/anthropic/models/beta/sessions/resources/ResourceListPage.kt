// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.sessions.ResourceService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see ResourceService.list */
class ResourceListPage
private constructor(
    private val service: ResourceService,
    private val params: ResourceListParams,
    private val response: ResourceListPageResponse,
) : Page<BetaManagedAgentsSessionResource> {

    /**
     * Delegates to [ResourceListPageResponse], but gracefully handles missing data.
     *
     * @see ResourceListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsSessionResource> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [ResourceListPageResponse], but gracefully handles missing data.
     *
     * @see ResourceListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsSessionResource> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): ResourceListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): ResourceListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaManagedAgentsSessionResource> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): ResourceListParams = params

    /** The response that this page was parsed from. */
    fun response(): ResourceListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ResourceListPage].
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

    /** A builder for [ResourceListPage]. */
    class Builder internal constructor() {

        private var service: ResourceService? = null
        private var params: ResourceListParams? = null
        private var response: ResourceListPageResponse? = null

        @JvmSynthetic
        internal fun from(resourceListPage: ResourceListPage) = apply {
            service = resourceListPage.service
            params = resourceListPage.params
            response = resourceListPage.response
        }

        fun service(service: ResourceService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: ResourceListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ResourceListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ResourceListPage].
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
        fun build(): ResourceListPage =
            ResourceListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ResourceListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "ResourceListPage{service=$service, params=$params, response=$response}"
}
