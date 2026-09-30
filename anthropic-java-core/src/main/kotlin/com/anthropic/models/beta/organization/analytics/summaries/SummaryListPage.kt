package com.anthropic.models.beta.organization.analytics.summaries

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSingleDayActivitySummary
import com.anthropic.services.blocking.beta.organization.analytics.SummaryService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see SummaryService.list */
class SummaryListPage
private constructor(
    private val service: SummaryService,
    private val params: SummaryListParams,
    private val response: SummaryListPageResponse,
) : Page<BetaAnalyticsSingleDayActivitySummary> {

    /**
     * Delegates to [SummaryListPageResponse], but gracefully handles missing data.
     *
     * @see SummaryListPageResponse.data
     */
    fun data(): List<BetaAnalyticsSingleDayActivitySummary> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [SummaryListPageResponse], but gracefully handles missing data.
     *
     * @see SummaryListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsSingleDayActivitySummary> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): SummaryListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): SummaryListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsSingleDayActivitySummary> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): SummaryListParams = params

    /** The response that this page was parsed from. */
    fun response(): SummaryListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SummaryListPage].
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

    /** A builder for [SummaryListPage]. */
    class Builder internal constructor() {

        private var service: SummaryService? = null
        private var params: SummaryListParams? = null
        private var response: SummaryListPageResponse? = null

        @JvmSynthetic
        internal fun from(summaryListPage: SummaryListPage) = apply {
            service = summaryListPage.service
            params = summaryListPage.params
            response = summaryListPage.response
        }

        fun service(service: SummaryService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: SummaryListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: SummaryListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [SummaryListPage].
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
        fun build(): SummaryListPage =
            SummaryListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SummaryListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "SummaryListPage{service=$service, params=$params, response=$response}"
}
