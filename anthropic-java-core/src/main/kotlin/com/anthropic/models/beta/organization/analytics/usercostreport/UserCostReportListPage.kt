package com.anthropic.models.beta.organization.analytics.usercostreport

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsCostUsersItem
import com.anthropic.services.blocking.beta.organization.analytics.UserCostReportService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see UserCostReportService.list */
class UserCostReportListPage
private constructor(
    private val service: UserCostReportService,
    private val params: UserCostReportListParams,
    private val response: UserCostReportListPageResponse,
) : Page<BetaAnalyticsCostUsersItem> {

    /**
     * Delegates to [UserCostReportListPageResponse], but gracefully handles missing data.
     *
     * @see UserCostReportListPageResponse.data
     */
    fun data(): List<BetaAnalyticsCostUsersItem> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [UserCostReportListPageResponse], but gracefully handles missing data.
     *
     * @see UserCostReportListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsCostUsersItem> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): UserCostReportListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): UserCostReportListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsCostUsersItem> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): UserCostReportListParams = params

    /** The response that this page was parsed from. */
    fun response(): UserCostReportListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UserCostReportListPage].
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

    /** A builder for [UserCostReportListPage]. */
    class Builder internal constructor() {

        private var service: UserCostReportService? = null
        private var params: UserCostReportListParams? = null
        private var response: UserCostReportListPageResponse? = null

        @JvmSynthetic
        internal fun from(userCostReportListPage: UserCostReportListPage) = apply {
            service = userCostReportListPage.service
            params = userCostReportListPage.params
            response = userCostReportListPage.response
        }

        fun service(service: UserCostReportService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: UserCostReportListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: UserCostReportListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [UserCostReportListPage].
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
        fun build(): UserCostReportListPage =
            UserCostReportListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserCostReportListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "UserCostReportListPage{service=$service, params=$params, response=$response}"
}
