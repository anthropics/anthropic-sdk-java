package com.anthropic.models.beta.organization.analytics.userusagereport

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageUsersItem
import com.anthropic.services.blocking.beta.organization.analytics.UserUsageReportService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see UserUsageReportService.list */
class UserUsageReportListPage
private constructor(
    private val service: UserUsageReportService,
    private val params: UserUsageReportListParams,
    private val response: UserUsageReportListPageResponse,
) : Page<BetaAnalyticsUsageUsersItem> {

    /**
     * Delegates to [UserUsageReportListPageResponse], but gracefully handles missing data.
     *
     * @see UserUsageReportListPageResponse.data
     */
    fun data(): List<BetaAnalyticsUsageUsersItem> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [UserUsageReportListPageResponse], but gracefully handles missing data.
     *
     * @see UserUsageReportListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsUsageUsersItem> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): UserUsageReportListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): UserUsageReportListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsUsageUsersItem> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): UserUsageReportListParams = params

    /** The response that this page was parsed from. */
    fun response(): UserUsageReportListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UserUsageReportListPage].
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

    /** A builder for [UserUsageReportListPage]. */
    class Builder internal constructor() {

        private var service: UserUsageReportService? = null
        private var params: UserUsageReportListParams? = null
        private var response: UserUsageReportListPageResponse? = null

        @JvmSynthetic
        internal fun from(userUsageReportListPage: UserUsageReportListPage) = apply {
            service = userUsageReportListPage.service
            params = userUsageReportListPage.params
            response = userUsageReportListPage.response
        }

        fun service(service: UserUsageReportService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: UserUsageReportListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: UserUsageReportListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [UserUsageReportListPage].
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
        fun build(): UserUsageReportListPage =
            UserUsageReportListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserUsageReportListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "UserUsageReportListPage{service=$service, params=$params, response=$response}"
}
