package com.anthropic.models.beta.organization.analytics.costreport

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsCostReportTimeBucket
import com.anthropic.services.blocking.beta.organization.analytics.CostReportService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see CostReportService.list */
class CostReportListPage
private constructor(
    private val service: CostReportService,
    private val params: CostReportListParams,
    private val response: CostReportListPageResponse,
) : Page<BetaAnalyticsCostReportTimeBucket> {

    /**
     * Delegates to [CostReportListPageResponse], but gracefully handles missing data.
     *
     * @see CostReportListPageResponse.data
     */
    fun data(): List<BetaAnalyticsCostReportTimeBucket> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [CostReportListPageResponse], but gracefully handles missing data.
     *
     * @see CostReportListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsCostReportTimeBucket> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): CostReportListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CostReportListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsCostReportTimeBucket> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): CostReportListParams = params

    /** The response that this page was parsed from. */
    fun response(): CostReportListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CostReportListPage].
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

    /** A builder for [CostReportListPage]. */
    class Builder internal constructor() {

        private var service: CostReportService? = null
        private var params: CostReportListParams? = null
        private var response: CostReportListPageResponse? = null

        @JvmSynthetic
        internal fun from(costReportListPage: CostReportListPage) = apply {
            service = costReportListPage.service
            params = costReportListPage.params
            response = costReportListPage.response
        }

        fun service(service: CostReportService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: CostReportListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: CostReportListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [CostReportListPage].
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
        fun build(): CostReportListPage =
            CostReportListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CostReportListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "CostReportListPage{service=$service, params=$params, response=$response}"
}
