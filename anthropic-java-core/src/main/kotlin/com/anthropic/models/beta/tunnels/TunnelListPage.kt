// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.TunnelService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see TunnelService.list */
class TunnelListPage
private constructor(
    private val service: TunnelService,
    private val params: TunnelListParams,
    private val response: TunnelListPageResponse,
) : Page<BetaTunnel> {

    /**
     * Delegates to [TunnelListPageResponse], but gracefully handles missing data.
     *
     * @see TunnelListPageResponse.data
     */
    fun data(): List<BetaTunnel> = response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [TunnelListPageResponse], but gracefully handles missing data.
     *
     * @see TunnelListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaTunnel> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): TunnelListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): TunnelListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaTunnel> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): TunnelListParams = params

    /** The response that this page was parsed from. */
    fun response(): TunnelListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [TunnelListPage].
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

    /** A builder for [TunnelListPage]. */
    class Builder internal constructor() {

        private var service: TunnelService? = null
        private var params: TunnelListParams? = null
        private var response: TunnelListPageResponse? = null

        @JvmSynthetic
        internal fun from(tunnelListPage: TunnelListPage) = apply {
            service = tunnelListPage.service
            params = tunnelListPage.params
            response = tunnelListPage.response
        }

        fun service(service: TunnelService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: TunnelListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: TunnelListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [TunnelListPage].
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
        fun build(): TunnelListPage =
            TunnelListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TunnelListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "TunnelListPage{service=$service, params=$params, response=$response}"
}
