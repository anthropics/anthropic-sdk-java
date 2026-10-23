package com.anthropic.models.beta.organization.analytics.cost

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.analytics.CostService
import java.time.OffsetDateTime
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see CostService.list */
class CostListPage
private constructor(
    private val service: CostService,
    private val params: CostListParams,
    private val response: CostListPageResponse,
) : Page<BetaAnalyticsCostItem> {

    /**
     * Delegates to [CostListPageResponse], but gracefully handles missing data.
     *
     * @see CostListPageResponse.data
     */
    fun data(): List<BetaAnalyticsCostItem> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [CostListPageResponse], but gracefully handles missing data.
     *
     * @see CostListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    /**
     * Delegates to [CostListPageResponse], but gracefully handles missing data.
     *
     * @see CostListPageResponse.hasMore
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [CostListPageResponse], but gracefully handles missing data.
     *
     * @see CostListPageResponse.dataRefreshedAt
     */
    fun dataRefreshedAt(): Optional<OffsetDateTime> =
        response._dataRefreshedAt().getOptional("data_refreshed_at")

    override fun items(): List<BetaAnalyticsCostItem> = data()

    override fun hasNextPage(): Boolean {
        if (hasMore().getOrNull() == false) {
            return false
        }

        return nextPageRaw().isPresent
    }

    fun nextPageParams(): CostListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CostListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsCostItem> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): CostListParams = params

    /** The response that this page was parsed from. */
    fun response(): CostListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CostListPage].
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

    /** A builder for [CostListPage]. */
    class Builder internal constructor() {

        private var service: CostService? = null
        private var params: CostListParams? = null
        private var response: CostListPageResponse? = null

        @JvmSynthetic
        internal fun from(costListPage: CostListPage) = apply {
            service = costListPage.service
            params = costListPage.params
            response = costListPage.response
        }

        fun service(service: CostService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: CostListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: CostListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [CostListPage].
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
        fun build(): CostListPage =
            CostListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CostListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "CostListPage{service=$service, params=$params, response=$response}"
}
