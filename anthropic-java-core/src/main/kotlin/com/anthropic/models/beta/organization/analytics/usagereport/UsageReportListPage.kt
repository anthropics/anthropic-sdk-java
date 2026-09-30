package com.anthropic.models.beta.organization.analytics.usagereport

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageReportTimeBucket
import com.anthropic.services.blocking.beta.organization.analytics.UsageReportService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see UsageReportService.list */
class UsageReportListPage
private constructor(
    private val service: UsageReportService,
    private val params: UsageReportListParams,
    private val response: UsageReportListPageResponse,
) : Page<BetaAnalyticsUsageReportTimeBucket> {

    /**
     * Delegates to [UsageReportListPageResponse], but gracefully handles missing data.
     *
     * @see UsageReportListPageResponse.data
     */
    fun data(): List<BetaAnalyticsUsageReportTimeBucket> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [UsageReportListPageResponse], but gracefully handles missing data.
     *
     * @see UsageReportListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsUsageReportTimeBucket> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): UsageReportListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): UsageReportListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsUsageReportTimeBucket> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): UsageReportListParams = params

    /** The response that this page was parsed from. */
    fun response(): UsageReportListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UsageReportListPage].
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

    /** A builder for [UsageReportListPage]. */
    class Builder internal constructor() {

        private var service: UsageReportService? = null
        private var params: UsageReportListParams? = null
        private var response: UsageReportListPageResponse? = null

        @JvmSynthetic
        internal fun from(usageReportListPage: UsageReportListPage) = apply {
            service = usageReportListPage.service
            params = usageReportListPage.params
            response = usageReportListPage.response
        }

        fun service(service: UsageReportService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: UsageReportListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: UsageReportListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [UsageReportListPage].
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
        fun build(): UsageReportListPage =
            UsageReportListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UsageReportListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "UsageReportListPage{service=$service, params=$params, response=$response}"
}
