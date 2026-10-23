package com.anthropic.models.beta.organization.analytics.usage

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.analytics.UsageService
import java.time.OffsetDateTime
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see UsageService.list */
class UsageListPage
private constructor(
    private val service: UsageService,
    private val params: UsageListParams,
    private val response: UsageListPageResponse,
) : Page<BetaAnalyticsUsageItem> {

    /**
     * Delegates to [UsageListPageResponse], but gracefully handles missing data.
     *
     * @see UsageListPageResponse.data
     */
    fun data(): List<BetaAnalyticsUsageItem> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [UsageListPageResponse], but gracefully handles missing data.
     *
     * @see UsageListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    /**
     * Delegates to [UsageListPageResponse], but gracefully handles missing data.
     *
     * @see UsageListPageResponse.hasMore
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [UsageListPageResponse], but gracefully handles missing data.
     *
     * @see UsageListPageResponse.dataRefreshedAt
     */
    fun dataRefreshedAt(): Optional<OffsetDateTime> =
        response._dataRefreshedAt().getOptional("data_refreshed_at")

    override fun items(): List<BetaAnalyticsUsageItem> = data()

    override fun hasNextPage(): Boolean {
        if (hasMore().getOrNull() == false) {
            return false
        }

        return nextPageRaw().isPresent
    }

    fun nextPageParams(): UsageListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): UsageListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsUsageItem> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): UsageListParams = params

    /** The response that this page was parsed from. */
    fun response(): UsageListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UsageListPage].
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

    /** A builder for [UsageListPage]. */
    class Builder internal constructor() {

        private var service: UsageService? = null
        private var params: UsageListParams? = null
        private var response: UsageListPageResponse? = null

        @JvmSynthetic
        internal fun from(usageListPage: UsageListPage) = apply {
            service = usageListPage.service
            params = usageListPage.params
            response = usageListPage.response
        }

        fun service(service: UsageService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: UsageListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: UsageListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [UsageListPage].
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
        fun build(): UsageListPage =
            UsageListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UsageListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "UsageListPage{service=$service, params=$params, response=$response}"
}
