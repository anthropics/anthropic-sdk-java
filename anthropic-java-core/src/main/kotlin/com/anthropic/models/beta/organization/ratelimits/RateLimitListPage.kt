// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.ratelimits

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.RateLimitService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see RateLimitService.list */
class RateLimitListPage
private constructor(
    private val service: RateLimitService,
    private val params: RateLimitListParams,
    private val response: RateLimitListPageResponse,
) : Page<BetaOrganizationRateLimit> {

    /**
     * Delegates to [RateLimitListPageResponse], but gracefully handles missing data.
     *
     * @see RateLimitListPageResponse.data
     */
    fun data(): List<BetaOrganizationRateLimit> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [RateLimitListPageResponse], but gracefully handles missing data.
     *
     * @see RateLimitListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaOrganizationRateLimit> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): RateLimitListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): RateLimitListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaOrganizationRateLimit> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): RateLimitListParams = params

    /** The response that this page was parsed from. */
    fun response(): RateLimitListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [RateLimitListPage].
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

    /** A builder for [RateLimitListPage]. */
    class Builder internal constructor() {

        private var service: RateLimitService? = null
        private var params: RateLimitListParams? = null
        private var response: RateLimitListPageResponse? = null

        @JvmSynthetic
        internal fun from(rateLimitListPage: RateLimitListPage) = apply {
            service = rateLimitListPage.service
            params = rateLimitListPage.params
            response = rateLimitListPage.response
        }

        fun service(service: RateLimitService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: RateLimitListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: RateLimitListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [RateLimitListPage].
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
        fun build(): RateLimitListPage =
            RateLimitListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RateLimitListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "RateLimitListPage{service=$service, params=$params, response=$response}"
}
